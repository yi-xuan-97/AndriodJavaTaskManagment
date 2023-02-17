package main.java.pdx.edu.CS506;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.json.JsonObject;


import java.util.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.lt;

public class MongoDB {

    ConnectionString connectionString;
    MongoClientSettings settings;
    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> collectionUserList;
    MongoCollection<Document> collectionTaskList;

    public MongoDB(String name){
        connectionString = new ConnectionString("mongodb+srv://yfeng:iRqMRFryF8tIThqD@taskcluster.3tpukmi.mongodb.net/?retryWrites=true&w=majority");
        settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase("Application");
        collectionUserList = database.getCollection("UserList");
        collectionTaskList = database.getCollection(name);
        if(collectionTaskList==null){
            System.err.println("There is no such database!!");
            database.createCollection(name);
            collectionTaskList = database.getCollection(name);
        }
    }

    public ArrayList<User> getUsers(){
        MongoCursor<Document> cursor = collectionUserList.find().cursor();
        ArrayList<User> temp = new ArrayList<>();

        try {
            while(cursor.hasNext()) {
                JSONObject obj = new JSONObject(cursor.next().toJson());
                User user = new User((String) obj.get("username"), (String) obj.get("email"), (String) obj.get("password"));
                temp.add(user);
            }
        } finally {
            cursor.close();
        }

        return temp;
    }

    public PriorityQueue<Task> getTasks(){
        MongoCursor<Document> cursor = collectionTaskList.find().cursor();
        PriorityQueue<Task> temp = new PriorityQueue<>(new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                Date d1 = t1.getDate();
                Date d2 = t2.getDate();
                return d1.compareTo(d2);
            }
        });

        try {
            while(cursor.hasNext()) {
                JSONObject obj = new JSONObject(cursor.next().toJson());
                Task t = new Task((String) obj.get("title"), (String) obj.get("detail"), (String) obj.get("location"),new Date((String) obj.get("date")));
                temp.add(t);
            }
        } finally {
            cursor.close();
        }

        return temp;
    }
    public void addUser(String username,String email, String password){
        Document doc = collectionUserList.find(eq("_id", username+"_"+password)).first();
        if (doc != null) {
            System.err.println("Account already exist!");
            return;
        }
        Document tempDoc = new Document("_id", username+"_"+password).append("username",username).append("email",email).append("password",password);
        collectionUserList.insertOne(tempDoc);
    }

    public void addTask(String title, String date, String location, String detail){
        Document tempDoc = new Document("_id", title).append("title",title).append("date",date).append("location",location).append("detail",detail);
        collectionTaskList.insertOne(tempDoc);
    }
}

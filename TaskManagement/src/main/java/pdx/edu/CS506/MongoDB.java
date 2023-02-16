package main.java.pdx.edu.CS506;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

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

//    public User getUseer(String username,String password){
//        collectionUserList.
//    }
    public void addUser(String username,String email, String password){
        Document tempDoc = new Document("_id", email+"_"+password).append("username",username).append("email",email).append("password",password);
        collectionUserList.insertOne(tempDoc);
    }

    public void addTask(String title, String date, String location, String detail){
        Document tempDoc = new Document("_id", title).append("title",title).append("date",date).append("location",location).append("detail",detail);
        collectionTaskList.insertOne(tempDoc);
    }
}

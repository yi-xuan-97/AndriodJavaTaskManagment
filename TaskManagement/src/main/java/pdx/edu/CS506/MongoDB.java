package main.java.pdx.edu.CS506;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
public class MongoDB {

    ConnectionString connectionString = new ConnectionString("mongodb+srv://yfeng:iRqMRFryF8tIThqD@taskcluster.3tpukmi.mongodb.net/?retryWrites=true&w=majority");
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();
    MongoClient mongoClient = MongoClients.create(settings);
    MongoDatabase database = mongoClient.getDatabase("test");
    MongoCollection collection = database.getCollection("sampletest");
}

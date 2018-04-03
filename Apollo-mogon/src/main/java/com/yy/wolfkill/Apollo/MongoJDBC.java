package com.yy.wolfkill.Apollo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class MongoJDBC {
    public static void main(String[] args) {
        try {
            //建立连接
            MongoClient mongo = new MongoClient("localhost",27017 );
            System.out.println("Connect to database successfully");
            //选择DB
            MongoDatabase database = mongo.getDatabase("chechaodb");
            
            //创建一个collection
            database.createCollection("javaTest");
            System.out.println("创建集合成功");
            //选择connection
            MongoCollection<Document> collection= database.getCollection("javaTest");
            System.out.println("选择集合成功");
            //新建一个Document
            Document document = new Document().append("name", "ApolloJava5").append("address", "YY5");
            List<String> phoneList = new ArrayList<>();
            phoneList.add("123");
            phoneList.add("222");
            document.append("phones", phoneList);
            
            //插入Document
            collection.insertOne(document);
            System.out.println("文档插入成功");
            findAll(collection);
            update(collection);
            findAll(collection);
            
            collection.deleteMany(Filters.gt("name", "ApolloJava3"));
            findAll(collection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void findAll(MongoCollection<Document> collection) {
        FindIterable<Document> findIterable = collection.find().sort(new Document("name",-1));
        MongoCursor<Document> mongoCusor = findIterable.iterator();
        while(mongoCusor.hasNext()) {
            System.out.println(mongoCusor.next());
        }
    }
    public static void update(MongoCollection<Document> collection) {
        collection.updateMany(Filters.eq("name","ApolloJava"), new Document("$set",new Document("name","ApolloUpdated")));
    }
}

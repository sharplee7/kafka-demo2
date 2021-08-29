package com.example.sub.config;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongoConfig;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;

import java.io.IOException;

@Configuration
public class MongoConfig {
/*

    @Value("${mongodb.url}")
    private String url;

    @Bean
    public SimpleMongoClientDbFactory mongoDatabaseFactory() {
        return new SimpleMongoClientDbFactory(url);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDatabaseFactory());
    }
*/
    @Bean
    public MongoTemplate mongoTemplate() throws IOException {
        MongoTemplate mongoTemplate = null;
        MongodStarter starter = MongodStarter.getDefaultInstance();
        String ip = "127.0.0.1";
        int port = 27017;
        IMongodConfig mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(ip,port, Network.localhostIsIPv6()))
                .build();

        MongodExecutable mongodExecutable = null;

        try {
            mongodExecutable = starter.prepare(mongodConfig);
            MongodProcess mongod = mongodExecutable.start();

            MongoClient mongo = new MongoClient(ip, port);
            mongoTemplate = new MongoTemplate(mongo, "local");
            DB db = mongo.getDB("local");
            DBCollection col = db.createCollection("memberdb", new BasicDBObject());

            for(int i=0; i < 4; i++) {
                if (i==3) {
                    col.save(new BasicDBObject("username", "admin" + (i+1)).append("password","admin" + (i+1)).append("role","ROLE_USER"));
                } else {

                    col.save(new BasicDBObject("username", "admin" + (i+1)).append("password","admin" + (i+1)).append("role","ROLE_ADMIN"));
                }
            }

        } finally {
            if (mongodExecutable != null) {

            } else {

            }
        }

        return mongoTemplate;
    }
}

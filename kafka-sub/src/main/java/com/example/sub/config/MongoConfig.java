package com.example.sub.config;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;

@Configuration
public class MongoConfig {
    @Bean
    public MongoTemplate mongoTemplate() throws IOException {
        String ip = "127.0.0.1";
        int port = 27017;

        MongoTemplate mongoTemplate = null;
        MongodStarter starter = MongodStarter.getDefaultInstance();

        IMongodConfig mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(ip, port, Network.localhostIsIPv6()))
                .build();

        MongodExecutable mongodExecutable = null;

        mongodExecutable = starter.prepare(mongodConfig);
        MongodProcess mongod = mongodExecutable.start();

        MongoClient mongo = new MongoClient(ip, port);
        mongoTemplate = new MongoTemplate(mongo, "local");
        DB db = mongo.getDB("local");
        DBCollection col = db.createCollection("memberdb", new BasicDBObject());

        int i = 0;
        while (i < 4) {
            col.save(new BasicDBObject("username", "admin" + (i + 1)).append("password", "admin" + (i + 1)).append("role", "ROLE_ADMIN"));
            i++;
        }
        col.save(new BasicDBObject("username", "admin" + (i + 1)).append("password", "admin" + (i + 1)).append("role", "ROLE_USER"));

        return mongoTemplate;
    }
}

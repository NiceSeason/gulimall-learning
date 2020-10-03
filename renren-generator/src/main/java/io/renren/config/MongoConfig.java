package io.renren.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import io.renren.factory.MongoDBCollectionFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: gxz gongxuanzhang@foxmail.com
 **/
@Component
@ConfigurationProperties(prefix = "mongodb")

public class MongoConfig {
    private String host;
    private int port;
    private String username;
    private String password;
    private String dataBase;
    private boolean auth;
    private String source;


    @Bean
    @Conditional(MongoCondition.class)
    private MongoClient getMongoClient() {
        List<ServerAddress> adds = new ArrayList<>();
        ServerAddress serverAddress = new ServerAddress(this.host, this.port);
        adds.add(serverAddress);
        if (this.auth) {
            MongoCredential mongoCredential = MongoCredential.
                    createScramSha1Credential(this.username, this.source, this.password.toCharArray());
            MongoClientOptions mongoClientOptions = MongoClientOptions.builder().build();
            return new MongoClient(adds, mongoCredential, mongoClientOptions);
        }
        return new MongoClient(adds);
    }

    @Bean
    @Conditional(MongoCondition.class)
    public MongoDatabase getDataBase() {
        return getMongoClient().getDatabase(dataBase);
    }



    public MongoConfig setHost(String host) {
        this.host = host;
        return this;
    }

    public MongoConfig setPort(int port) {
        this.port = port;
        return this;
    }

    public MongoConfig setUsername(String username) {
        this.username = username;
        return this;
    }

    public MongoConfig setPassword(String password) {
        this.password = password;
        return this;
    }

    public MongoConfig setDataBase(String dataBase) {
        this.dataBase = dataBase;
        return this;
    }

    public MongoConfig setAuth(boolean auth) {
        this.auth = auth;
        return this;
    }

    public MongoConfig setSource(String source) {
        this.source = source;
        return this;
    }
}

package io.renren.factory;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import io.renren.config.MongoCondition;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: gxz gongxuanzhang@foxmail.com
 **/

@Component
@Conditional(MongoCondition.class)
public class MongoDBCollectionFactory {

    private static  final String TABLE_NAME_KEY = "tableName";
    private static final String LIMIT_KEY = "limit";
    private static final String OFFSET_KEY = "offset";

    private static MongoDatabase mongoDatabase;

    // 此处是为了兼容mongo相关内容和关系型数据库的静态耦合所导致的问题

    @Autowired
    private MongoDatabase database;
    @PostConstruct
    public void initMongoDatabase(){
        mongoDatabase = database;
    }

    /***
     * 通过表名获得查询对象
     * @author gxz
     * @date  2020/5/9
     * @param collectionName mongo的集合名(表名)
     * @return 连接查询对象
     **/
    public MongoCollection<Document> getCollection(String collectionName) {
        return mongoDatabase.getCollection(collectionName);
    }

    /***
     * 获得当前数据库的集合名称
     * 注: mongo相对关系型数据库较为特殊，查询表名无法分页，用stream实现
     * @author gxz
     * @date  2020/5/9
     * @param map 这是查询条件 和关系型数据库一致
     * @return 集合名称
     **/
    public static List<String>  getCollectionNames(Map<String, Object> map) {
        int limit = Integer.valueOf(map.get(LIMIT_KEY).toString());
        int skip = Integer.valueOf(map.get(OFFSET_KEY).toString());
        List<String> names;
        if (map.containsKey(TABLE_NAME_KEY)) {
            names = getCollectionNames(map.get(TABLE_NAME_KEY).toString());
        } else {
            names = getCollectionNames();
        }
        return names.stream().skip(skip).limit(limit).collect(Collectors.toList());
    }
    /***
     * 获得集合名称总数(表的数量) 为了适配MyBatisPlus的分页插件 提供方法
     * @author gxz
     * @date  2020/5/9
     * @param map 这是查询条件 和关系型数据库一致
     * @return int
     **/
    public static int getCollectionTotal(Map<String, Object> map) {
        if (map.containsKey(TABLE_NAME_KEY)) {
            return getCollectionNames(map.get(TABLE_NAME_KEY).toString()).size();
        }
        return getCollectionNames().size();

    }


    private static List<String> getCollectionNames() {
        MongoIterable<String> names = mongoDatabase.listCollectionNames();
        List<String> result = new ArrayList<>();
        for (String name : names) {
            result.add(name);
        }
        return result;
    }

    private static List<String> getCollectionNames(String likeName) {
        return getCollectionNames()
                .stream()
                .filter((name) -> name.contains(likeName)).collect(Collectors.toList());
    }
}

package io.renren.adaptor;

import io.renren.entity.mongo.MongoDefinition;
import io.renren.entity.mongo.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * mongo适配器
 *
 * @author: gxz gongxuanzhang@foxmail.com
 **/
public class MongoTableInfoAdaptor {

    /**
     * 查询表信息的时候 mongo只能获得表名 其他只能手动填写
     *
     * @param names 表名
     */
    public static List<Map<String, String>> tableInfo(List<String> names) {
        List<Map<String, String>> result = new ArrayList<>(names.size());
        for (String name : names) {
            result.add(tableInfo(name));
        }
        return result;
    }

    public static Map<String, String> tableInfo(String name) {
        Map<String, String> tableInfo = new HashMap<>(4 * 4 / 3 + 1);
        tableInfo.put("engine", "mongo无引擎");
        tableInfo.put("createTime", "mongo无法查询创建时间");
        tableInfo.put("tableComment", "mongo无备注");
        tableInfo.put("tableName", name);
        return tableInfo;
    }

    /**
     * 在查询列名的时候 需要将解析出的mongo信息适配成关系型数据库所需要的信息形式
     * 此方法只针对主Bean
     */
    public static List<Map<String, String>> columnInfo(MongoDefinition mongoDefinition) {
        List<MongoDefinition> child = mongoDefinition.getChild();
        List<Map<String, String>> result = new ArrayList<>(child.size());
        final String mongoKey = "_id";
        for (MongoDefinition definition : child) {
            Map<String, String> map = new HashMap<>(5 * 4 / 3 + 1);
            String type = Type.typeInfo(definition.getType());
            String propertyName = definition.getPropertyName();
            String extra = definition.isArray() ? "array" : "";
            map.put("extra", extra);
            map.put("columnComment", "");
            map.put("dataType", definition.hasChild() ? propertyName : type);
            map.put("columnName", propertyName);
            // mongo默认主键是_id
            String columnKey = propertyName.equals(mongoKey) ? "PRI" : "";
            map.put("columnKey", columnKey);
            result.add(map);
        }
        return result;
    }


}

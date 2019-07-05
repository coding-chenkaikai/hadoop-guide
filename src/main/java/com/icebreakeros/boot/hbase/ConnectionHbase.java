package com.icebreakeros.boot.hbase;

import com.icebreakeros.boot.utils.ResourceUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

@Deprecated
public class ConnectionHbase {

    public void connection() throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", ResourceUtil.getKey("hbase.zookeeper.quorum"));
        configuration.set("hbase.master", ResourceUtil.getKey("hbase.master"));

        Connection connection = ConnectionFactory.createConnection(configuration);
        Admin admin = connection.getAdmin();

        String tableName = "user";
        delete(admin, tableName);
        create(admin, tableName, "address", "info");
        put(connection, tableName, "xiaoming", "info", "age", "24");
        put(connection, tableName, "xiaoming", "address", "city", "beijing");
        put(connection, tableName, "lihua", "address", "contry", "china");
        put(connection, tableName, "lihua", "info", "company", "alibaba");
        get(connection, tableName, "lihua");
        getAll(connection, tableName);

        admin.close();
        connection.close();
    }

    // 删除表
    private void delete(Admin admin, String tableName) throws IOException {
        TableName table = TableName.valueOf(tableName);
        if (admin.tableExists(table)) {
            admin.disableTable(table);
            admin.deleteTable(table);
        }
    }

    // 创建表
    private void create(Admin admin, String tableName, String... columns) throws IOException {
        TableName table = TableName.valueOf(tableName);
        if (!admin.tableExists(table)) {
            HTableDescriptor tableDescriptor = new HTableDescriptor(table);
            for (String column : columns) {
                tableDescriptor.addFamily(new HColumnDescriptor(column));
            }
            admin.createTable(tableDescriptor);
        }
    }

    // 插入
    private void put(Connection connection, String tableName,
                     String rowKey, String family, String qualifier, String value) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes.toBytes(value));
        table.put(put);
        table.close();
    }

    // 获取
    private void get(Connection connection, String tableName, String row) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Get get = new Get(Bytes.toBytes(row));
        Result result = table.get(get);
        System.out.println(String.valueOf(result));
    }

    // 获取全部
    private void getAll(Connection connection, String tableName) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        ResultScanner scanner = table.getScanner(new Scan());
        for (Result result : scanner) {
            System.out.println(String.valueOf(result));
        }
    }
}

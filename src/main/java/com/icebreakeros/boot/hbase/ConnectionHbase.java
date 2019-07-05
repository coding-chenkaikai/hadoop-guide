package com.icebreakeros.boot.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;

public class ConnectionHbase {

    public void connection() {
        Configuration configuration = HBaseConfiguration.create();
    }
}

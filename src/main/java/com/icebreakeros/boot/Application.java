package com.icebreakeros.boot;

import com.icebreakeros.boot.hbase.ConnectionHbase;
import com.icebreakeros.boot.wordcount.WordCount;

import java.io.IOException;

@Deprecated
public class Application {
    public static void main(String[] args) throws IOException {
        ConnectionHbase hbase = new ConnectionHbase();
        hbase.connection();
    }
}

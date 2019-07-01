package com.icebreakeros.boot;

import com.icebreakeros.boot.wordcount.WordCount;

public class Application {
    public static void main(String[] args) {
        try {
            boolean result = WordCount.wordCount("word count", Application.class, args);
            System.exit(result ? 0 : 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("done.");
    }
}

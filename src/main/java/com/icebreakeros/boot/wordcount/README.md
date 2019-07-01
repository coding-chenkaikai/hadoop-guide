# wordcount
## main函数
```$xslt
boolean result = WordCount.wordCount("word count", Application.class, args);
System.exit(result ? 0 : 1);
```

## 执行示例
```$xslt
hadoop jar \
    hadoop-1.0-SNAPSHOT.jar \
    com.icebreakeros.boot.Application \
    /user/hadoop/wordcount/in/* \
    /user/hadoop/wordcount/out
```
package com.icebreakeros.boot.hive;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

public class CaseConversionUdf extends UDF {

    public Text evaluate(Text text, String model) {
        if (model.equals('l')) {
            return new Text(text.toString().toLowerCase());
        } else {
            return new Text(text.toString().toUpperCase());
        }
    }
}

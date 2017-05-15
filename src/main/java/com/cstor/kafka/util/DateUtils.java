package com.cstor.kafka.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fengwei on 17/5/15.
 */
public class DateUtils {

    public static String format(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(time));
    }

}

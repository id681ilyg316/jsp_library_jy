package moweifeng.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static SimpleDateFormat sdf = null;
    public static String  dateToString(Date date){
        //转换成24小时制的时间格式
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = sdf.format(date);
        return dateString;
    }
}

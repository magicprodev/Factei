package ma.ormvasm.factei;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Helper {
    public static String getCurrentDate(){

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = df.format(c);
        return formattedDate;
    }

    public static String getCurrentDateTime(){

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c);
        return formattedDate;
    }

    public static String rowId(String st){

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String formattedDate = df.format(c);
        return st + "_M" + formattedDate;
    }

    public static String convertFormatDate(String sdate){
        String reformattedStr="";
        SimpleDateFormat formUser = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            reformattedStr = formUser.format(myFormat.parse(sdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return reformattedStr;
    }
}

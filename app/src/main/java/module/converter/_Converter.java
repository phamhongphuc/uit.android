package module.converter;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class _Converter {
    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public static String Date(Date date) {
        return dateFormat.format(date);
    }

    public static String Gender(Boolean gender) {
        return gender ? "Nam" : "Nữ";
    }
}

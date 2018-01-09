package module.converter;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Nullable;

public class _Converter {
    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static String Date(@Nullable Date date) {
        return (date == null) ? "???" : dateFormat.format(date);
    }

    public static String Gender(Boolean gender) {
        return (gender == null) ? "???" : (gender ? "Nam" : "Nữ");
    }
}

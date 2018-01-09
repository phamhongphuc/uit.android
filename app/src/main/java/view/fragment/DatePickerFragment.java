package view.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

import module.callback.DateCallback;

@SuppressLint("ValidFragment")
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    public Calendar calendar = Calendar.getInstance();
    private Date date;
    private DateCallback dateCallback;

    public DatePickerFragment(DateCallback dateCallback) {
        this.dateCallback = dateCallback;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(
                getActivity(), this,
                year, month, day
        );
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        calendar.set(year, month, day);
        date = calendar.getTime();
        dateCallback.Response(date);
    }

    public Date getDate() {
        return date;
    }
}

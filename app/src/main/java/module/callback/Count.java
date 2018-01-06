package module.callback;

import android.databinding.Observable;
import android.databinding.ObservableInt;

import javax.annotation.Nullable;

public class Count extends ObservableInt {
    private final VoidCallback done;

    public Count(int value, @Nullable final VoidCallback done) {
        set(value);
        this.done = done;
        responseIfZero();
        addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                responseIfZero();
            }
        });
    }

    public void increase() {
        set(get() + 1);
    }

    public void decrease() {
        set(get() - 1);
    }

    public void responseIfZero() {
        if (get() == 0) done.Response();
    }
}

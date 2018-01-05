package view.fragmentAdapter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("ValidFragment")
public class FragmentPage extends android.support.v4.app.Fragment {
    protected int mLayout;

    public FragmentPage(@LayoutRes int layout) {
        mLayout = layout;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(mLayout, container, false);
    }
}

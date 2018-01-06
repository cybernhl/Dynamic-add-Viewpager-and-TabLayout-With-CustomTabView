package com.example.dat.testdynamicviewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by DAT on 9/1/2015.
 */
public class FragmentChild extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View childview = inflater.inflate(R.layout.fragment_child, container, false);
        ((TextView) childview.findViewById(R.id.textViewChild)).setText(getArguments().getString("data"));
        ((EditText) childview.findViewById(R.id.editText)).setText("");
        return childview;
    }
}

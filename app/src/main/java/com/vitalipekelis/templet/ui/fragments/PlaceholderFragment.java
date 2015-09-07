package com.vitalipekelis.templet.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vitalipekelis.templet.R;
import com.vitalipekelis.templet.services.responses.QueryResult;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    private final static String TAG = PlaceholderFragment.class.getSimpleName();
    private TextView mTvText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initUI(rootView);
        return rootView;
    }

    private void initUI(View v) {
        mTvText = (TextView) v.findViewById(R.id.tv_section_label);

    }

    public void setText(QueryResult item){
        mTvText.setText(item.toString());
    }


}

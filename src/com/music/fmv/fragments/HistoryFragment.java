package com.music.fmv.fragments;

import android.os.Bundle;
import com.music.fmv.R;
import com.music.fmv.core.BaseFragment;

/**
 * User: vitaliylebedinskiy
 * Date: 7/22/13
 * Time: 12:07 PM
 */
public class HistoryFragment extends BaseFragment {

    @Override
    protected void createView(Bundle savedInstanceState) {
        mainView = inflateView(R.layout.history_fragment);
    }
}

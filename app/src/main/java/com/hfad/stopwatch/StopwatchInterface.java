package com.hfad.stopwatch;

import android.view.View;

/**
 * Created by admin on 10-Mar-18.
 */

public interface StopwatchInterface {
    void onClickStart(View view);
    void onClickStop(View view);
    void onClickReset(View view);
     void runTimer();
}

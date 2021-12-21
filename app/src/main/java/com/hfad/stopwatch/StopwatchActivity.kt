package com.hfad.stopwatch

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.*
import kotlinx.android.synthetic.main.activity_stopwatch.*

/**
 * Created by androminor on 22-Apr-18.
 */

interface StopwatchInterface {
    fun onClickStart(view: View?)
    fun onClickPause(view: View?)
    fun onClickReset(view: View?)
    fun onClickResume(view:View?)
    fun runTimer()
}
class StopwatchActivity : Activity(), StopwatchInterface {
    //Number of seconds displayed on the stopwatch.
    private var seconds = 0

    //Is the stopwatch running?
    private var running = false
    private var wasRunning = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stopwatch)

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds")
            running = savedInstanceState.getBoolean("running")
            wasRunning = savedInstanceState.getBoolean("wasRunning")
        }
        runTimer()
    }

    override fun onPause() {
        super.onPause()
        wasRunning = running

    }


    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putInt("seconds", seconds)
        savedInstanceState.putBoolean("running", running)
        savedInstanceState.putBoolean("wasRunning", wasRunning)
    }

    override fun onClickStart(view: View?) {
        pause_button.visibility = View.VISIBLE
        running = true
    }

    override fun onClickPause(view: View?) {
            running = false
            pause_button.visibility = View.GONE
            resume_button.visibility = View.VISIBLE

    }

    override fun onClickReset(view: View?) {
        running = false
        seconds = 0
        pause_button.visibility = View.GONE
        resume_button.visibility = View.GONE

    }

    override fun onClickResume(view: View?) {
                if(running == true)
                {
                 seconds = 0
                }
           else {
                    running = true
                    pause_button.visibility = View.VISIBLE
                    resume_button.visibility = View.GONE

                }

                }








    //Sets the number of seconds on the timer.
    override fun runTimer() {
        val timeView = findViewById<View>(R.id.time_view) as TextView
        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                val hours = seconds / 3600
                val minutes = seconds % 3600 / 60
                val secs = seconds % 60
                val time = String.format("%d:%02d:%02d", hours, minutes, secs)
                timeView.text = time
                if (running) {
                    seconds++
                }
                handler.postDelayed(this, 0)
            }
        })

    }

}
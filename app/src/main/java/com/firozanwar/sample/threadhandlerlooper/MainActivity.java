package com.firozanwar.sample.threadhandlerlooper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    /**
     * Case-1  - updating the UI from seperate thread
     */
    //private ProgressBar progressBar;
    // Thread thread;
    //Handler handler;

    /**
     * Case-2 - Send message from main thread to seperate thread.
     */
    MyPostThread myPostThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Case-1
         */
        /*progressBar = (ProgressBar) findViewById(R.id.progressBar);
        thread = new Thread(new Mythread());
        thread.start();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                progressBar.setProgress(msg.arg1);

            }
        };*/

        /**
         * Case-2
         */
        myPostThread = new MyPostThread();
        myPostThread.start();
    }

    /**
     * Case-2
     */
    public void sendMessage(View view) {
        myPostThread.myhandler.post(new Runnable() {
            @Override
            public void run() {
                Log.i("Handler", myPostThread.getName());
            }
        });
    }

    /**
     * Case-1
     */
    /*class Mythread implements Runnable {

        @Override
        public void run() {

            for (int i = 0; i < 100; i++) {


                Message message = Message.obtain();
                message.arg1 = i;
                handler.sendMessage(message);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }*/
}

/**
 * Case-2
 */
class MyPostThread extends Thread {

    Handler myhandler;

    public MyPostThread() {

    }

    @Override
    public void run() {
        Looper.prepare();
        myhandler = new Handler();
        Looper.loop();
    }
}

package com.linecutfeng.handlerdemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private ProgressDialog progressDialog;

    Handler mHandler1 = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 100) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "下载完毕!", Toast.LENGTH_SHORT).show();
            }
        }
    };

    Handler mHandler2 = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle("下载中");

        button1 = findViewById(R.id.bt1);
        button2 = findViewById(R.id.bt2);
        button3 = findViewById(R.id.bt3);
        button4 = findViewById(R.id.bt4);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        dismissProgressDialog();
        switch (v.getId()) {
            case R.id.bt1:
                beginProgress(1);
                break;
            case R.id.bt2:
                beginProgress(2);
                break;
            case R.id.bt3:
                beginProgress(3);
                break;
            case R.id.bt4:
                beginProgress(4);
                break;
        }
    }

    private void beginProgress(int i) {
        progressDialog.show();
        progressDialog.setProgress(0);
        switch (i) {
            case 1:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i1 = 1; i1 <= 5; i1++) {
                            try {
                                Thread.sleep(500);
                                progressDialog.setProgress(i1 * 20);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        Message message = mHandler1.obtainMessage();
                        message.what = 100;
                        mHandler1.sendMessageDelayed(message, 500);

                    }

                }).start();
                break;


            case 2:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i1 = 1; i1 <= 5; i1++) {
                            try {
                                Thread.sleep(500);
                                progressDialog.setProgress(i1 * 20);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        mHandler2.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this, "下载完毕!", Toast.LENGTH_SHORT).show();
                            }
                        }, 500);
                    }
                }).start();
                break;



            case 3:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i1 = 1; i1 <= 5; i1++) {
                            try {
                                progressDialog.setProgress(i1 * 20);
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this, "下载完毕!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).start();
                break;


            case 4:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i1 = 1; i1 <= 5; i1++) {
                            try {
                                Thread.sleep(500);
                                progressDialog.setProgress(i1 * 20);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Looper.prepare();
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "下载完毕!", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }).start();
                break;
            default:
                break;
        }
    }

    private void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.setProgress(0);
            progressDialog.dismiss();
        }
    }
}

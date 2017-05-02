package mx.com.baware.exampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import static mx.com.baware.exampleapp.ImageViewActivity.IMAGE_ARG;

public class MainActivity extends AppCompatActivity {

    public static final int PORT = 5555;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                startServerSocket();
            }
        });
        thread.start();
    }

    private void startServerSocket() {
        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(PORT);

            while (true) {
                // block the call until connection is created and return
                // Socket object
                Socket socket = serverSocket.accept();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                final String message = bufferedReader.readLine();

                if(!message.matches("[A-Za-z0-9/. ]*")) continue;
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showNextActivity(message);
                    }
                });

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showNextActivity(String message){
        Intent intent = new Intent(getBaseContext(), ImageViewActivity.class);
        intent.putExtra(IMAGE_ARG, message);
        startActivity(intent);
    }
}

package wigwam.labdemo.connection;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Process;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by freddy on 11/10/15.
 */
public class ConnectionService extends Service {
    private static final String TAG = "ConnectionService";
    private final IBinder mBinder = new ConnectionBinder();

    ServerSocket serverSocket;
    Socket client;
    BufferedReader in;
    PrintWriter out;

    public class ConnectionBinder extends Binder {
        public ConnectionService getService() {
            return ConnectionService.this;
        }
    }

    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "Attempting to start socket connection.");
        Thread sThread = new Thread(new ServerThread());
        sThread.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (serverSocket != null && !serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (client != null && !client.isClosed()) {
            try {
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public class ServerThread implements Runnable {
        public void run() {
            try {
                Log.d(TAG, "Creating socket");
                serverSocket = new ServerSocket(1234);
                client = serverSocket.accept();
                Log.d(TAG, "Connection from " + client.getInetAddress());
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out = new PrintWriter(client.getOutputStream(), true);

//                while (true) {
//                    String line = null;
//                    line = in.readLine();
//                    Log.d(TAG, "GOT " + line);
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String doStuff(String s) {
        try {
            if (client.isConnected()) {
                out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
}

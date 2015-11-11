package wigwam.labdemo.Connection;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by freddy on 11/10/15.
 */
public class ConnectionService extends Service {
    private final IBinder mBinder = new ConnectionBinder();

    public class ConnectionBinder extends Binder {
        public ConnectionService getService() {
            return ConnectionService.this;
        }
    }

    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public int doStuff() {
        return 1234;
    }
}

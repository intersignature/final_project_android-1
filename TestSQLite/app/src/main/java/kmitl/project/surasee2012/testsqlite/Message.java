package kmitl.project.surasee2012.testsqlite;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Gun on 11/1/2017.
 */

public class Message {
    public static void message(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
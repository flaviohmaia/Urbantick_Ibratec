package adaz.urbantick_ibratec;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SessionManager {

    private static String TAG = SessionManager.class.getSimpleName();
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "AndroidHiveLogin";
    private static final String KEY_IS_LOGGEDIN = "idLoggedIn";

    public SessionManager(Context context){
        this._context = context;
        preferences = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public void setLogin(boolean isLoggedIn){
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.commit();
        Log.d(TAG, "Sessão do login foi modificada");
    }

    public boolean isLoggedIn(){
        return preferences.getBoolean(KEY_IS_LOGGEDIN, false);
    }
}

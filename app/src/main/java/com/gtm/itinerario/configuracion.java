package com.gtm.itinerario;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by PRACTICAS on 29/03/2016.
 */

public class configuracion {
    private final String shared_file = "HMPrefs";
    private final String key_ema = "email";
    private final String key_pas="password";
    private Context mContext;

    public configuracion(Context context) {
        mContext = context;
        //Sharedpreferences

    }
    private SharedPreferences getSettings(){
        return mContext.getSharedPreferences(shared_file, 3);
    }

    public String getUserEmail(){
        return getSettings().getString(key_ema, null);
    }

    public void setUserEmail(String email){
        SharedPreferences.Editor editor = getSettings().edit();
        editor.putString(key_ema, email );
        editor.commit();
    }

    public String getPassword(){
    return getSettings().getString(key_pas,null);
}
    public void setPassword(String password){
        SharedPreferences.Editor editor1 = getSettings().edit();
        editor1.putString(key_pas, password );
        editor1.commit();
    }


}

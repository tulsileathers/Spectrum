package apps.jayceleathers.me.spectrum;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseUser;

import apps.jayceleathers.me.spectrum.Data.SpectrumUser;

/**
 * Created by Jayce on 12/5/14.
 */
public class SpectrumApplication extends Application {

    public void onCreate() {
        ParseUser.registerSubclass(SpectrumUser.class);
        Parse.initialize(this, "xSVqxBs4EoMFp61sPqTPodhV0w5Znkj1YXlvyECG", "uEcIoZ5Wz1gXnlWi4BRvjrri33Zj8lQmETAyf65H");
    }
}

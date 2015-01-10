package apps.jayceleathers.me.spectrum.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseUser;

import apps.jayceleathers.me.spectrum.Fragments.LoginFragment;
import apps.jayceleathers.me.spectrum.Fragments.RegisterFragment;
import apps.jayceleathers.me.spectrum.R;


public class LaunchActivity extends Activity {

    public static final String KEY_FIRST_RUN = "first run";
    public static final String SP_DATA = "sp data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (savedInstanceState == null) {
            SharedPreferences sp = getSharedPreferences(SP_DATA, MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();

            if(sp.getBoolean(KEY_FIRST_RUN,true)) {
                getFragmentManager().beginTransaction()
                        .add(R.id.container, new RegisterFragment())
                        .commit();
            }
            else if(currentUser != null)
            {
                Intent mainIntent = new Intent(LaunchActivity.this, MainActivity.class);
                LaunchActivity.this.startActivity(mainIntent);
            }
            else{
                getFragmentManager().beginTransaction()
                        .add(R.id.container, new LoginFragment())
                        .commit();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_launch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */

}

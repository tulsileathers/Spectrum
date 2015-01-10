package apps.jayceleathers.me.spectrum.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import apps.jayceleathers.me.spectrum.Activities.MainActivity;
import apps.jayceleathers.me.spectrum.R;


public class LoginFragment extends Fragment {
    private EditText etUsername;
    private EditText etPassword;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        container = (ViewGroup) inflater.inflate(R.layout.fragment_login, container, false);
        etUsername = (EditText) container.findViewById(R.id.etLoginUsername);
        etPassword = (EditText) container.findViewById(R.id.etLoginPassword);
        Button btnLogin = (Button) container.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(etUsername.getText().toString(), etPassword.getText()
                        .toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(e!=null){
                            e.printStackTrace();
                        }
                        else {
                            Intent mainIntent = new Intent(getActivity(), MainActivity.class);
                            getActivity().startActivity(mainIntent);
                        }
                    }
                });
            }
        });
        return container;
    }

}

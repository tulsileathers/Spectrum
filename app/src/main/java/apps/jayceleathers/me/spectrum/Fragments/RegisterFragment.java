package apps.jayceleathers.me.spectrum.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.SignUpCallback;

import apps.jayceleathers.me.spectrum.Data.SpectrumUser;
import apps.jayceleathers.me.spectrum.R;



public class RegisterFragment extends Fragment {
    private EditText etName;
    private EditText etPassword;
    private EditText etEmail;
    private EditText etPasswordAgain;

    public RegisterFragment() {
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
        container = (ViewGroup) inflater.inflate(R.layout.fragment_register, container, false);
        etName = (EditText) container.findViewById(R.id.etName);
        etEmail = (EditText) container.findViewById(R.id.etEmail);
        etPassword = (EditText) container.findViewById(R.id.etPassword);
        etPasswordAgain = (EditText) container.findViewById(R.id.etPasswordAgain);
        Button btnRegister = (Button) container.findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
        return container;
    }


    private boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.subSequence(email.length() - 5, email.length() - 1).equals(".edu");
    }

    private void signUp() {



            String username = etName.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String passwordAgain = etPasswordAgain.getText().toString().trim();
            String email = etEmail.getText().toString().trim();

// other fields can be set just like with ParseObject

            boolean validationError = false;
            StringBuilder validationErrorMessage = new StringBuilder("Error: ");
            if (isEmailValid(etEmail.getText())) {
                validationError = true;
                validationErrorMessage.append("Please enter a valid email");
            }
            if (username.length() == 0) {
                validationError = true;
                validationErrorMessage.append("Please enter a valid username");
            }
            if (password.length() == 0) {
                if (validationError) {
                    validationErrorMessage.append("error join");
                }
                validationError = true;
                validationErrorMessage.append("Please enter a valid password");
            }
            if (!password.equals(passwordAgain)) {
                if (validationError) {
                    validationErrorMessage.append("error join");
                }
                validationError = true;
                validationErrorMessage.append("Passwords do not match");
            }
            validationErrorMessage.append("end error");
            if (validationError) {
                Toast.makeText(getActivity(), validationErrorMessage.toString(), Toast.LENGTH_LONG)
                        .show();
                return;
            }

            SpectrumUser user = new SpectrumUser();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(com.parse.ParseException e) {
                    //TODO Dismiss the dialog

                    if (e != null) {
                        // Show the error message
                        Toast.makeText(getActivity(), e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    } else {
                        // Switch to prof pic fragment

                        CameraFragment newFragment = new CameraFragment();
                        FragmentManager fragmentManager = getActivity().getFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.container, newFragment);
                        transaction.commit();

                    }
                }
            });
        }
    }





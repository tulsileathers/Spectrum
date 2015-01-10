package apps.jayceleathers.me.spectrum.Fragments;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseUser;

import apps.jayceleathers.me.spectrum.Activities.LaunchActivity;
import apps.jayceleathers.me.spectrum.Data.SpectrumUser;
import apps.jayceleathers.me.spectrum.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DescriptionFragment extends Fragment {

    private EditText etDescription;


    public DescriptionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        container = (ViewGroup) inflater.inflate(R.layout.fragment_description, container, false);

        etDescription = (EditText) container.findViewById(R.id.etDescription);
        Button btnSave = (Button) container.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpectrumUser user = (SpectrumUser) ParseUser.getCurrentUser();

                user.setDescription(etDescription.getText().toString().trim());
                user.saveInBackground();
                SharedPreferences sp = getActivity().getSharedPreferences(LaunchActivity.SP_DATA, LaunchActivity.MODE_PRIVATE);
                FragmentManager fm = getActivity().getFragmentManager();
                if(sp.getBoolean(LaunchActivity.KEY_FIRST_RUN, true)){
                    MatchIdentifyFragment newFragment = new MatchIdentifyFragment();
                    FragmentManager fragmentManager = getActivity().getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.container, newFragment);
                    transaction.commit();
                }
                else {
                    fm.popBackStack();
                }
            }
        });
        return container;
    }


}

package apps.jayceleathers.me.spectrum.Fragments;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseUser;

import apps.jayceleathers.me.spectrum.Data.SpectrumUser;
import apps.jayceleathers.me.spectrum.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    ParseImageView ivPhoto;
    TextView tvDescription;
    TextView tvUsername;

    private SeekBar sbGenderBiological;
    private SeekBar sbGenderIdentity;
    private SeekBar sbGenderExpression;
    private SeekBar sbSexualIdentity;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        container = (ViewGroup) inflater.inflate(R.layout.fragment_profile, container, false);
        freezeSeekBars(container);
        ivPhoto = (ParseImageView) container.findViewById(R.id.ivPhoto);
        SpectrumUser user = (SpectrumUser) ParseUser.getCurrentUser();
        ParseFile photoFile = user.getProfPic();
        if (photoFile != null) {
            ivPhoto.setParseFile(photoFile);
            ivPhoto.loadInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] data, ParseException e) {
                    ivPhoto.setVisibility(View.VISIBLE);
                }
            });
        }
            tvDescription = (TextView) container.findViewById(R.id.tvDescription);
            tvUsername = (TextView) container.findViewById(R.id.tvUsername);

            tvDescription.setText(user.getDescription());
            tvUsername.setText(user.getUsername());
        Button btnMatches = (Button) container.findViewById(R.id.btnMatches);
        btnMatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MatchesFragment newFragment = new MatchesFragment();
                FragmentManager fragmentManager = getActivity().getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.mainContainer, newFragment);
                transaction.addToBackStack("null");
                transaction.commit();
            }
        });

        return container;
    }


    private void freezeSeekBars(ViewGroup container){
        sbGenderBiological = (SeekBar) container.findViewById(R.id.sbGenderBiological);
        sbGenderIdentity = (SeekBar) container.findViewById(R.id.sbGenderIdentity);
        sbGenderExpression = (SeekBar) container.findViewById(R.id.sbGenderExpression);
        sbSexualIdentity = (SeekBar) container.findViewById(R.id.sbSexualIdentity);
        SpectrumUser user = (SpectrumUser) ParseUser.getCurrentUser();
        sbGenderBiological.setProgress(user.getGenderBiological());
        sbGenderIdentity.setProgress(user.getGenderIdentity());
        sbGenderExpression.setProgress(user.getGenderExpression());
        sbSexualIdentity.setProgress(user.getSexualIdentity());

        sbGenderBiological.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int originalProgress;

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Nothing here..
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                originalProgress = seekBar.getProgress();
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int arg1, boolean fromUser) {
                if (fromUser) {
                    seekBar.setProgress(originalProgress);
                }
            }
        });

        sbGenderIdentity.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {

            int originalProgress;

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Nothing here..
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                originalProgress = seekBar.getProgress();
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int arg1, boolean fromUser) {
                if( fromUser){
                    seekBar.setProgress( originalProgress);
                }
            }
        });

        sbGenderExpression.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int originalProgress;

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Nothing here..
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                originalProgress = seekBar.getProgress();
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int arg1, boolean fromUser) {
                if (fromUser) {
                    seekBar.setProgress(originalProgress);
                }
            }
        });
        sbSexualIdentity.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {

            int originalProgress;

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Nothing here..
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                originalProgress = seekBar.getProgress();
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int arg1, boolean fromUser) {
                if(fromUser){
                    seekBar.setProgress( originalProgress);
                }
            }
        });
    }

}

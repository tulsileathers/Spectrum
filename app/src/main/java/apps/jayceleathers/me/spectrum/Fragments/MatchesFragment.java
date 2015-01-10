package apps.jayceleathers.me.spectrum.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import apps.jayceleathers.me.spectrum.Data.SpectrumUser;
import apps.jayceleathers.me.spectrum.NextMatchOnClickListener;
import apps.jayceleathers.me.spectrum.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatchesFragment extends Fragment {
    private ArrayList<ParseUser> matches;
    ListIterator<ParseUser> iterator;
    private ParseImageView ivPhoto;
    private TextView tvDescription;
    private TextView tvUsername;
    private SpectrumUser next;
    private SeekBar sbGenderBiological;
    private SeekBar sbGenderIdentity;
    private SeekBar sbGenderExpression;
    private SeekBar sbSexualIdentity;
    public MatchesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        container = (ViewGroup) inflater.inflate(R.layout.fragment_matches, container, false);
        final ViewGroup finalContainer = container;
        ParseQuery<ParseUser> getMatches = ParseUser.getQuery();
        getMatches.findInBackground(new FindCallback<ParseUser>() {

            @Override
            public void done(List<ParseUser> parseUsers, com.parse.ParseException e) {

                if (e == null) {
                    matches = (ArrayList<ParseUser>) parseUsers;
                    if(matches != null) {
                        Log.d("matches", "Matches are not null");
                        iterator = matches.listIterator();

                        nextMatch(finalContainer);
                        freezeSeekBars(finalContainer);
                        
                        Button btnLike = (Button) finalContainer.findViewById(R.id.btnLike);
                        Button btnPass = (Button) finalContainer.findViewById(R.id.btnPass);

                        btnLike.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //TODO IMPLEMENT MATCH LOGIC
                            }
                        });


                        btnPass.setOnClickListener(new NextMatchOnClickListener(finalContainer) {
                            @Override
                            public void onClick(View v) {
                                //TODO FIX MATCH QUERY
                                nextMatch(finalContainer);
                            }
                        });

                    }
                    Log.d("matches", "Retrieved");
                } else {
                    Log.d("matches", "Error: " + e.getMessage());
                }
            }
        });

        return finalContainer;
    }

    private void nextMatch(ViewGroup container){
        if(iterator.hasNext()) {

            next = (SpectrumUser) iterator.next();

            ivPhoto = (ParseImageView) container.findViewById(R.id.ivPhoto);
            ParseFile photoFile = next.getProfPic();
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

            tvDescription.setText(next.getDescription());
            tvUsername.setText(next.getUsername());
        }
    }


    private void freezeSeekBars(ViewGroup container){
        sbGenderBiological = (SeekBar) container.findViewById(R.id.sbGenderBiological);
        sbGenderIdentity = (SeekBar) container.findViewById(R.id.sbGenderIdentity);
        sbGenderExpression = (SeekBar) container.findViewById(R.id.sbGenderExpression);
        sbSexualIdentity = (SeekBar) container.findViewById(R.id.sbSexualIdentity);

        sbGenderBiological.setProgress(next.getGenderBiological());
        sbGenderIdentity.setProgress(next.getGenderIdentity());
        sbGenderExpression.setProgress(next.getGenderExpression());
        sbSexualIdentity.setProgress(next.getSexualIdentity());

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
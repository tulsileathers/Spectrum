package apps.jayceleathers.me.spectrum.Fragments;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
public class PhotoReviewFragment extends Fragment {

    private ParseImageView ivPhoto;

    public PhotoReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        container = (ViewGroup) inflater.inflate(R.layout.fragment_photo_review, container, false);
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



        Button btnRetake = (Button) container.findViewById(R.id.btnRetake);
        btnRetake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraFragment newFragment = new CameraFragment();
                FragmentManager fragmentManager = getActivity().getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.container, newFragment);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        Button btnSave = (Button) container.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IdentifyFragment newFragment = new IdentifyFragment();
                FragmentManager fragmentManager = getActivity().getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.container, newFragment);
                transaction.commit();
            }
        });
        return container;
    }


}

package com.example.patirk.vikingworkout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by Patirk on 23/10/2015.
 */
public class FragmentSettings extends android.support.v4.app.Fragment implements View.OnClickListener {
    ImageView iv;
    Button bEditPic, bEditText;
    TextView save;
    EditText t;
    public static FragmentSettings newInstance() {
        FragmentSettings fragment = new FragmentSettings();
        Bundle args = new Bundle();
        //   args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    public FragmentSettings() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        bEditPic = (Button) rootView.findViewById(R.id.bSettingsCamera);
        bEditText = (Button) rootView.findViewById(R.id.bSettingsName);
        t = (EditText) rootView.findViewById(R.id.etSettingsName);
        iv = (ImageView) rootView.findViewById(R.id.ivSettingsPic);
        save = (TextView) rootView.findViewById(R.id.tvSettingsSave);
        save.setOnClickListener(this);
        bEditText.setOnClickListener(this);
        bEditPic.setOnClickListener(this);

        return rootView;
    }

    public void onClick(View v) {
        if (v == bEditText) {
            String newName = t.getText().toString();
            if(!newName.equals("Name")){
                MainActivity.profile.setName(newName);
            }else{
                Toast.makeText(getActivity(), "You need to press 'Done' on your keyboard to change name", Toast.LENGTH_SHORT).show();
            }
        }else if(v == bEditPic){
            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 0);
        }else if(v == save){
            MainActivity.saveProfile(MainActivity.mainActivity);
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        ExernalFunctions EF = new ExernalFunctions();

        Bitmap bp = (Bitmap) data.getExtras().get("data");
        Bitmap circlePic = EF.getCroppedBitmap(bp);
        MainActivity.profile.setProfilePicture(circlePic);
        iv.setImageBitmap(circlePic);
    }
}

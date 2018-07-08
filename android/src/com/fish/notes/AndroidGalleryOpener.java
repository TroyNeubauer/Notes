package com.fish.notes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.badlogic.gdx.Gdx;


import static android.app.Activity.RESULT_OK;

public class AndroidGalleryOpener implements OpenGallery {

    Activity activity;
    public static final int SELECT_IMAGE_CODE = 1;

    private String currentImagePath;
    private Uri currImageURI;

    public AndroidGalleryOpener(Activity activity){
        this.activity = activity;
    }

    @Override
    public void getGalleryImagePath() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(Intent.createChooser(intent, "Select Users Image"), SELECT_IMAGE_CODE);


    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {

            if (requestCode == 1) {

                // currImageURI is the global variable I'm using to hold the content:// URI of the image
                currImageURI = data.getData();
            }
        }
        getRealPathFromURI(currImageURI);
    }

        public void getRealPathFromURI(Uri contentUri) {
            String res = null;
            String[] proj = { MediaStore.Images.Media.DATA };
            Cursor cursor = activity.getContentResolver().query(contentUri, proj, null, null, null);
            if(cursor.moveToFirst()){;
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                res = cursor.getString(column_index);
            }
            cursor.close();
            setImageResult(res);
        }


    public void setImageResult(String path){
        currentImagePath = path;
    }

    public String getSelectedFilePath(){
        return currentImagePath;
    }
}

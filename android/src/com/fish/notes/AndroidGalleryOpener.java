package com.fish.notes;

import android.app.Activity;
import android.content.Intent;

import com.badlogic.gdx.Gdx;

public class AndroidGalleryOpener implements OpenGallery {

    Activity activity;
    public static final int SELECT_IMAGE_CODE = 1;

    private String currentImagePath;

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

    public void setImageResult(String path){
        currentImagePath = path;
    }

    public String getSelectedFilePath(){
        return currentImagePath;
    }

}

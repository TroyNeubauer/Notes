package com.fish.notes.desktop;

import com.badlogic.gdx.Gdx;
import com.fish.notes.OpenGallery;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DesktopGalleryOpener implements OpenGallery {

    private String currentImagePath;

    @Override
    public void getGalleryImagePath() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "JPG & PNG Images", "jpg", "png");
                chooser.setFileFilter(filter);
                JFrame f = new JFrame();
                f.setVisible(true);
                f.toFront();
                f.setVisible(false);
                int res = chooser.showOpenDialog(f);
                f.dispose();
                if (res == JFileChooser.APPROVE_OPTION) {
                    //Do some stuff
                    File file = new File(chooser.getSelectedFile().getName());
                    currentImagePath = file.getAbsolutePath();
                }
            }
        }).start();
    }

    public void setImageResult(String path){
        currentImagePath = path;
    }
    public String getSelectedFilePath(){
        return currentImagePath;
    }
}

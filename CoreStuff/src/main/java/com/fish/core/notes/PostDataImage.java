package com.fish.core.notes;

public class PostDataImage extends PostData {
    byte[] jpegImage;

    public PostDataImage(byte[] jpegImage) {
        this.jpegImage = jpegImage;
    }

    public byte[] getJpegImage() {
        return jpegImage;
    }
}

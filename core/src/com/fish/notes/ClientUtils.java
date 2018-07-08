package com.fish.notes;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class ClientUtils {
    public static Texture jpegToTexture(byte[] jpeg) {
        return new Texture(new Pixmap(jpeg, 0, jpeg.length));
    }
}

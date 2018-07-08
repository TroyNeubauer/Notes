package com.fish.notes;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;

import java.nio.ByteBuffer;

public class ClientUtils {
    public static Texture jpegToTexture(byte[] jpeg) {
        return new Texture(new Pixmap(jpeg, 0, jpeg.length));
    }

    public static byte[] textureToJPEG(Texture texture) {
        TextureData data = texture.getTextureData();
        data.prepare();
        ByteBuffer buffer = data.consumePixmap().getPixels();
        byte[] result = new byte[buffer.limit()];
        buffer.get(result);
        return result;
    }
}

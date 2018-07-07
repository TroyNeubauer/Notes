package com.fish.notes.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.files.FileHandle;
import com.fish.notes.Notes;

import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class DesktopLauncher {

	public static void main (String[] arg)  {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.addIcon("mac.png", Files.FileType.Internal);
        config.addIcon("windows.png", Files.FileType.Internal);
        config.addIcon("windows2.png", Files.FileType.Internal);
        try {
            Class<?> cls = Class.forName("com.apple.eawt.Application");
            Object application = cls.newInstance().getClass().getMethod("getApplication").invoke(null);
            FileHandle icon = Gdx.files.local("mac.png");
            application.getClass().getMethod("setDockIconImage", java.awt.Image.class).invoke(application, new ImageIcon(icon.file().getAbsolutePath()).getImage());
        } catch (Exception e) {
            System.out.println("Failed to set mac thing");
        }
		new LwjglApplication(new Notes(), config);
	}
}

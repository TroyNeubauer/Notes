package com.fish.notes;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import de.tomgrill.gdxdialogs.core.dialogs.GDXButtonDialog;
import de.tomgrill.gdxdialogs.core.listener.ButtonClickListener;

public class Notes extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	public static Skin skin;
	public static GDXButtonDialog warning;
	
	@Override
	public void create () {
        GDXDialogs dialogs = GDXDialogsSystem.install();
		batch = new SpriteBatch();
		img = new Texture("Fishnotes.png");
		skin = new Skin(Gdx.files.internal("default.json"));
		warning = dialogs.newDialog(GDXButtonDialog.class);

		}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 50, 50);
        batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

}

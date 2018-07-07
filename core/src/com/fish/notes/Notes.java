package com.fish.notes;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.fish.core.game.Account;
import com.fish.core.game.Core;
import com.fish.core.game.LoginResult;
import com.fish.core.game.Post;
import com.fish.core.game.PostDataImage;
import com.fish.core.game.PostDataText;

import de.tomgrill.gdxdialogs.core.GDXDialogs;
import de.tomgrill.gdxdialogs.core.GDXDialogsSystem;
import de.tomgrill.gdxdialogs.core.dialogs.GDXButtonDialog;
import de.tomgrill.gdxdialogs.core.listener.ButtonClickListener;

public class Notes extends Game {
	SpriteBatch batch;
	Texture img;
	public static Skin skin;
	public static GDXButtonDialog warning;
	public static Account account;
    private static BitmapFont font;
	
	@Override
	public void create () {
        GDXDialogs dialogs = GDXDialogsSystem.install();
		batch = new SpriteBatch();
		img = new Texture("Fishnotes.png");
		skin = new Skin(Gdx.files.internal("default.json"));
		warning = dialogs.newDialog(GDXButtonDialog.class);
        addCreators();
        font = skin.get(TextButton.TextButtonStyle.class).font;
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

	public static void showDialog(String title, String message) {
        final GDXButtonDialog warningDialog = Notes.warning;
        warningDialog.setTitle(title).setMessage(message);
        warningDialog.addButton("Ok");

        warningDialog.setClickListener(new ButtonClickListener() {
            @Override
            public void click(int button) {
                warningDialog.dismiss();
            }
        });
        warningDialog.build().show();
    }

    private void addCreators() {
        Core.creatorMap.put(PostDataImage.class, new Core.Creator() {
            @Override
            public Object create(Post post) {
                return new BaseActor(post){
                    @Override
                    public void addImpl(Table table) {

                    }
                };
            }
        });
        Core.creatorMap.put(PostDataText.class, new Core.Creator() {
            @Override
            public Object create(Post post) {
                return new BaseActor(post){
                    @Override
                    public void addImpl(Table table) {

                    }
                };
            }
        });
    }

    public static boolean validateResult(LoginResult result, String title) {
        if(result == null) {
            Notes.showDialog("No connection", "Check your internet connection or try again later!");
        } else {
            if (result.isSuccess()) {
                return true;
            } else {
                Notes.showDialog(title, result.getMessage());
            }
        }
        return false;
    }

    static abstract class BaseActor extends HorizontalGroup {

	    private Post post;

        public BaseActor(Post post) {
            this.post = post;
            Table left = new Table();

            left.add(new Label("", skin));


            Table right = new Table();
            addImpl(right);

            this.addActor(left);
            this.addActor(right);
        }

        public abstract void addImpl(Table table);
    }

}

package com.fish.notes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.fish.core.notes.Account;
import com.fish.core.notes.Core;
import com.fish.core.notes.LoginResult;
import com.fish.core.notes.Post;
import com.fish.core.notes.PostDataImage;
import com.fish.core.notes.PostDataText;

import de.tomgrill.gdxdialogs.core.GDXDialogs;
import de.tomgrill.gdxdialogs.core.GDXDialogsSystem;
import de.tomgrill.gdxdialogs.core.dialogs.GDXButtonDialog;
import de.tomgrill.gdxdialogs.core.listener.ButtonClickListener;

public class Notes extends Game {
	public static Skin skin;
	public static GDXDialogs dialogs;
	public static Account account;
	
	@Override
	public void create () {
        dialogs = GDXDialogsSystem.install();
		skin = new Skin(Gdx.files.internal("default.json"));
        addCreators();
        account = new Account(12546772, "testuser", new byte[0], "testeremail@tester.com");
        setScreen(new ProfileScreen(this));
    }

	public static void showDialog(String title, String message) {
        final GDXButtonDialog warningDialog = dialogs.newDialog(GDXButtonDialog.class);
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
        if(result == Backend.DISCONNECTED_FROM_SERVER) {
            Notes.showDialog("No connection", "Check your internet connection or try again later!");
        } else {
            if(result == null) {
                Notes.showDialog("Invalid server request!", "");
            } else if (result.isSuccess()) {
                return true;
            } else {
                Notes.showDialog(title, result.getMessage());
            }
        }
        return false;
    }

    static abstract class BaseActor extends HorizontalGroup {

	    private Post post;
	    private Texture texture;

        public BaseActor(Post post) {
            this.post = post;
            byte[] jpeg = Backend.getAccount(post.getPosterID()).getProfilePic();
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

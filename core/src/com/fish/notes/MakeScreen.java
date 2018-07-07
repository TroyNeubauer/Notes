package com.fish.notes;

import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.fish.core.game.Account;
import com.fish.core.game.LoginResult;
import com.fish.core.packet.LoginData;

import de.tomgrill.gdxdialogs.core.dialogs.GDXButtonDialog;
import de.tomgrill.gdxdialogs.core.listener.ButtonClickListener;

public class MakeScreen implements Screen {
    private Stage stage;
    private TextButton sumbit, picchoice, textchoice;
    private Label label;
    private TextField title, postdata, classbox;
    private Notes game;

    @Override
    public void hide() {
    }

    public MakeScreen(final Notes game) {
        this.game = game;
        this.stage = stage;

        /*
        this.picchoice = new TextButton("Picture Post");
        picchoice
        */

        this.label = new Label("Create a post", Notes.skin);

        this.title = new TextField("", Notes.skin);
        title.setMessageText("Title");

        this.postdata = new TextField("", Notes.skin);
        postdata.setMessageText("Enter Text");

        this.classbox = new TextField("", Notes.skin);
        classbox.setMessageText("Class");

        title.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(title.getText().length() > 0) {
                    //Backend.post(title.getText, new Course (0, "Math"), postdata.getText());
                }
            }
        });



        //container.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Table container = new Table();
        container.add(title);

        this.sumbit = new TextButton("Post", Notes.skin);
        //sumbit.addListener(finalize);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void show() {

    }
}

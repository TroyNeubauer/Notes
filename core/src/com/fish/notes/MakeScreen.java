package com.fish.notes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.awt.TextField;

public class MakeScreen implements Screen {
    private Stage stage;
    private TextButton loginButton, registerBtn;
    private Label label;
    private TextField title;

    private Notes game;
    public MakeScreen() {
        this.game = game;
        this.stage = stage;


        this.title = new TextField("", Notes.skin);
        title.setMessageText("Title");

        container.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Table container = new Table();
        container.add(title);
    }

    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }
    public void dispose() {
        stage.dispose();
    }


}

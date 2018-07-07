package com.fish.notes;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import de.tomgrill.gdxdialogs.core.dialogs.GDXButtonDialog;

public class MainScreen implements Screen {
    private Stage stage;
    private TextField usernameField, passwordField;
    private TextButton loginButton, registerBtn;
    private Label label;

    public MainScreen() {
        this.stage = new Stage();

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
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

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
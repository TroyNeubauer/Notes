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

public class RegisterScreen implements Screen
{
    private Stage stage;
    private TextField username, password, verify, email;
    private Notes notes;
    private Label label;

    public RegisterScreen(Notes notes)
    {
        this.notes = notes;
        this.stage = new Stage();

        this.username = new TextField("", Notes.skin);
        username.setMessageText("Create a username");

        this.password = new TextField("", Notes.skin);
        password.setMessageText("Create a password");
        password.setPasswordMode(true);
        password.setPasswordCharacter('*');

        this.verify = new TextField("", Notes.skin);
        verify.setMessageText("Re-enter the password");
        verify.setPasswordMode(true);
        verify.setPasswordCharacter('*');

        this.email = new TextField("", Notes.skin);
        email.setMessageText("Enter email");

        this.label = new Label("Welcome to Notes", Notes.skin);
        label.setAlignment(Align.center);

        Table container = new Table();
        container.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        container.add(label).prefSize(label.getWidth(), label.getHeight()).row();
        container.add(email).prefSize(email.getWidth(), email.getHeight()).row();
        container.add(username).prefSize(username.getWidth(), username.getHeight()).row();
        container.add(password).prefSize(password.getWidth(), password.getHeight()).row();
        container.add(verify).prefSize(verify.getWidth(), verify.getHeight()).row();
        stage.addActor(container);

        Gdx.input.setInputProcessor(stage);

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


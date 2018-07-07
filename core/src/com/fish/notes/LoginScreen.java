/*package com.fish.notes;


import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import de.tomgrill.gdxdialogs.core.dialogs.GDXButtonDialog;

public class LoginScreen implements Screen {
    private Stage stage;
    private TextField usernameField, passwordField;
    private TextButton loginButton, registerBtn;
    private Label label;
    private Notes game;

    public LoginScreen(final Notes game) {
        this.game = game;
        this.stage = new Stage();

        this.usernameField = new TextField("", Notes.skin);
        usernameField.setMessageText("Username");

        this.passwordField = new TextField("", Notes.skin);
        passwordField.setMessageText("Password");
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');

        this.loginButton = new TextButton("Login", Notes.skin);

        this.registerBtn = new TextButton("Create Account", Notes.skin);

        this.label = new Label("The Game of Diplomacy!", Notes.skin);
        label.setAlignment(Align.center);

        setSizes(Notes);

        Table container = new Table();
        container.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        container.add(label).prefSize(label.getWidth(), label.getHeight()).row();
        container.add(usernameField).prefSize(usernameField.getWidth(), usernameField.getHeight()).row();
        container.add(passwordField).prefSize(passwordField.getWidth(), passwordField.getHeight()).row();
        container.add(loginButton).prefSize(loginButton.getWidth(), loginButton.getHeight()).row();
        container.add(registerBtn).prefSize(registerBtn.getWidth(), registerBtn.getHeight()).row();
        stage.addActor(container);

        Gdx.input.setInputProcessor(stage);

        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(checkFields()) return;
                DiploClientNet net = game.getNet();
                if (net.isConnected()) {
                    net.getContext().writeAndFlush(new LoginData(usernameField.getText().toCharArray(), passwordField.getText().toCharArray()));
                } else {
                    GDXButtonDialog warningDialog = game.getDialogs().newDialog(GDXButtonDialog.class);
                    warningDialog.setTitle("No connection!").setMessage("Check your internet connection or try again later!");
                    warningDialog.addButton("Ok");
                    warningDialog.setClickListener((button) -> warningDialog.dismiss());
                    warningDialog.build().show();
                }
            }
        });

        registerBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new RegisterScreen(game, usernameField.getText(), Notes));
            }
        });

    }
*/
    /*
     * Returns true if any of the fields are invalid.
     * @return {@code true} if any of the fields are invalid
     */
/*
    protected boolean checkFields() {
        if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            GDXButtonDialog warningDialog = game.getDialogs().newDialog(GDXButtonDialog.class);
            warningDialog.setTitle("Cannot login!").setMessage("You must input a username and password!");
            warningDialog.addButton("Ok");
            warningDialog.setClickListener((button) -> warningDialog.dismiss());
            warningDialog.build().show();
            return true;
        }
        return false;
    }

    public void setNotes(Notes notes) {
        setSizes(Notes);
    }

    private void setSizes(Notes Notes) {
        loginButton.setSize(getWidth(0.22 * Notes.guiScale), getHeightAbsloute(0.1 * Notes.guiScale));
        label.setSize(getWidth(0.4 * Notes.guiScale), getHeightAbsloute(0.08 * Notes.guiScale));
        passwordField.setSize(getWidth(0.4 * Notes.guiScale), getHeightAbsloute(0.08 * Notes.guiScale));
        usernameField.setSize(getWidth(0.4 * Notes.guiScale), getHeightAbsloute(0.08 * Notes.guiScale));
        registerBtn.setSize(getWidth(0.2 * Notes.guiScale), getHeightAbsloute(0.07 * Notes.guiScale));
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
        setSizes(game.getNotes());
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
*/
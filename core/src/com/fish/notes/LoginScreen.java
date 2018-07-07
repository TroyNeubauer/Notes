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

        this.label = new Label("Welcome to Notes", Notes.skin);
        label.setAlignment(Align.center);


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


                LoginResult result = Backend.login(usernameField.getText(), passwordField.getText());
                String title, message;
                if(result != null) {
                    if (result.isSuccess()) {
                        result.getAccount();
                        //Set account
                        return;
                    } else {
                        title = "Error logging in!";
                        message = result.getMessage();
                    }
                } else {
                    title = "No connection";
                    message = "Check your internet connection or try again later!";

                }
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
        });

        registerBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //Show create account screen
            }
        });

    }

    /*
     * Returns true if any of the fields are invalid.
     * @return {@code true} if any of the fields are invalid
     */

    protected boolean checkFields() {
        if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            final GDXButtonDialog warningDialog = Notes.warning;
            warningDialog.setTitle("Cannot login!").setMessage("You must input a username and password!");
            warningDialog.addButton("Ok");
            warningDialog.setClickListener(new ButtonClickListener() {
                @Override
                public void click(int button) {
                    warningDialog.dismiss();
                }
            });
            warningDialog.build().show();
            return true;
        }
        return false;
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

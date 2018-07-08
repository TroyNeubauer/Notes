package com.fish.notes;


import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
<<<<<<< Updated upstream
import com.fish.core.notes.LoginResult;
=======
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fish.core.game.Account;
import com.fish.core.game.LoginResult;
import com.fish.core.packet.LoginData;

import java.awt.Color;

import de.tomgrill.gdxdialogs.core.dialogs.GDXButtonDialog;
import de.tomgrill.gdxdialogs.core.listener.ButtonClickListener;
>>>>>>> Stashed changes

import static java.awt.Color.RED;

public class LoginScreen extends MyScreen {
    private Viewport viewport;
    private OrthographicCamera camera;
    private Stage stage;
    Image img;
    private TextField usernameField, passwordField;
    private TextButton loginButton, registerBtn;
    private Label label;
    private Notes notes;

    public LoginScreen(final Notes notes) {
        this.notes = notes;
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
        label.setColor((Color) Color.RED);

        img = new Image(new Texture("Fishnotes.png"));
        Table container = new Table();
        container.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        container.add(img).row();
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
                if(Notes.validateResult(result, "Unable to log in!")) {
                    Notes.account = result.getAccount();
                    //Set account
                }
            }
        });

        registerBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //Show create account screen
                notes.setScreen(new RegisterScreen());
            }
        });

    }

    /*
     * Returns true if any of the fields are invalid.
     * @return {@code true} if any of the fields are invalid
     */

    protected boolean checkFields() {
        if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            Notes.showDialog("Cannot login!","You must input a username and password!" );
            return true;
        }
        return false;
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.update();
    }
   @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
       batch.setTransformMatrix(notes.getCamera().view);
       batch.setProjectionMatrix(notes.getCamera().projection);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}

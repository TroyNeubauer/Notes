package com.fish.notes;


import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.fish.core.notes.LoginResult;


public class LoginScreen extends MyScreen {
    Image img;
    private TextField usernameField, passwordField;
    private TextButton loginButton, registerBtn;
    private Label label;
    private Notes notes;

    public LoginScreen(final Notes notes) {
        super(notes);

        this.usernameField = new TextField("", Notes.skin);
        usernameField.setMessageText("Username");

        this.passwordField = new TextField("", Notes.skin);
        passwordField.setMessageText("Password");
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');

        this.loginButton = new TextButton("Login", Notes.skin);
        loginButton.setColor(com.badlogic.gdx.graphics.Color.GRAY);

        this.registerBtn = new TextButton("Create Account", Notes.skin);
        registerBtn.setColor(com.badlogic.gdx.graphics.Color.GRAY);

        this.label = new Label("Welcome to Notes", Notes.skin);
        label.setAlignment(Align.center);

        img = new Image(new Texture("Fishnotes.png"));
        Table container = new Table();
        container.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        container.add(img).row();
        container.add(label).prefSize(label.getWidth(), label.getHeight()).row();
        container.add(usernameField).prefSize(usernameField.getWidth(), usernameField.getHeight()).row();
        container.add(passwordField).prefSize(passwordField.getWidth(), passwordField.getHeight()).row();
        container.add(loginButton).prefSize(loginButton.getWidth(), loginButton.getHeight()).row();
        container.add(registerBtn).prefSize(registerBtn.getWidth(), registerBtn.getHeight()).row();
        container.row();
        container.setColor(com.badlogic.gdx.graphics.Color.TEAL);
        stage.addActor(container);


        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(checkFields()) return;
                LoginResult result = Backend.login(usernameField.getText(), passwordField.getText());
                if(Notes.validateResult(result, "Unable to log in!")) {
                    Notes.account = result.getAccount();
                    notes.setScreen(Notes.screensManager);
                }
            }
        });

        registerBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //Show create account screen
                notes.setScreen(new RegisterScreen(notes));
            }
        });
        usernameField.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char c) {
                if ((c == '\r' || c == '\n')){
                    textField.next( Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT));
                }
            }
        });
        passwordField.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char c) {
                if ((c == '\r' || c == '\n')){
                    textField.next(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) );
                }
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

   @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}

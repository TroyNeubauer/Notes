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
    private Label label, passwords;

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

        this.passwords = new Label("", Notes.skin);
        passwords.setAlignment(Align.center);

        Table container = new Table();
        container.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        container.add(label).prefSize(label.getWidth(), label.getHeight()).row();
        container.add(email).prefSize(email.getWidth(), email.getHeight()).row();
        container.add(username).prefSize(username.getWidth(), username.getHeight()).row();
        container.add(password).prefSize(password.getWidth(), password.getHeight()).row();
        container.add(verify).prefSize(verify.getWidth(), verify.getHeight()).row();
        container.add(passwords).prefSize(passwords.getWidth(), passwords.getHeight()).row();
        stage.addActor(container);

        Gdx.input.setInputProcessor(stage);

        password.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y){
                //check if username is already taken
                if(password.getText() != null)
                {

                }
            }
        });
        verify.addListener(new TextFieldListener()
        {
            @Override
            public void keyTyped(TextField textfield, char key){
                //check if username is already taken
                if(verify.getText() != null)
                {

                }
            }
        });
        username.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y){
            //check if username is already taken
                if(username.getText() != null)
                {

                }
            }
        });


    }

    public void checkSamePasswords()
    {
        if(!password.getText().equals(verify.getText()));
            passwords.setText("Passwords do not match");
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


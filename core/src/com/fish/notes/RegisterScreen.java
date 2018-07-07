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
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.fish.core.game.Account;
import com.fish.core.game.Core;
import com.fish.core.game.PostDataImage;
import com.fish.core.game.PostDataText;

import de.tomgrill.gdxdialogs.core.GDXDialogs;
import de.tomgrill.gdxdialogs.core.GDXDialogsSystem;
import de.tomgrill.gdxdialogs.core.dialogs.GDXButtonDialog;
import de.tomgrill.gdxdialogs.core.listener.ButtonClickListener;

public class RegisterScreen implements Screen
{
    private Stage stage;
    private Image img;
    private TextField username, password, verify, email;
    private TextButton register;
    private Label label, passwords;

    public RegisterScreen()
    {
        this.stage = new Stage();

        this.username = new TextField("", Notes.skin);
        username.setMessageText("Create a username");
        username.setAlignment(Align.center);

        this.password = new TextField("", Notes.skin);
        password.setMessageText("Create a password");
        password.setPasswordMode(true);
        password.setPasswordCharacter('*');
        password.setAlignment(Align.center);

        this.verify = new TextField("", Notes.skin);
        verify.setMessageText("Re-enter the password");
        verify.setPasswordMode(true);
        verify.setPasswordCharacter('*');
        verify.setAlignment(Align.center);

        this.email = new TextField("", Notes.skin);
        email.setMessageText("Enter email");
        email.setAlignment(Align.center);

        this.label = new Label("Welcome to Notes", Notes.skin);
        label.setAlignment(Align.center);

        this.passwords = new Label("", Notes.skin);
        passwords.setAlignment(Align.center);

        this.img = new Image(new Texture("Fishnotes.png"));
        img.setAlign(Align.center);

        this.register = new TextButton("Create Account", Notes.skin);
        register.setDisabled(true);

        Table container = new Table();
        container.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        container.add(label).prefSize(label.getWidth(), label.getHeight()).row();
        container.add(img).row();
        container.add(email).prefSize(email.getWidth(), email.getHeight()).row();
        container.add(username).prefSize(username.getWidth(), username.getHeight()).row();
        container.add(password).prefSize(password.getWidth(), password.getHeight()).row();
        container.add(verify).prefSize(verify.getWidth(), verify.getHeight()).row();
        container.add(passwords).prefSize(passwords.getWidth(), passwords.getHeight()).row();
        container.add(register).prefSize(register.getWidth(), register.getHeight()).row();
        stage.addActor(container);

        Gdx.input.setInputProcessor(stage);

        password.setTextFieldListener(new TextField.TextFieldListener(){
            @Override
            public void keyTyped(TextField textfield, char key){
                //check if password is same both times
                    checkSamePasswords();
            }
        });
        verify.setTextFieldListener(new TextField.TextFieldListener(){
            @Override
            public void keyTyped(TextField textfield, char key){
                //check if password is same both times
                    checkSamePasswords();
            }
        });

        register.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //create new account
                if(checkFields()) return;
                LoginResult result = Backend.register(username.getText(), password.getText(), email.getText());
                if(Notes.validateResult(result, "Unable to create account")){
                    Notes.account = result.getAccount();
                }

            }
        });
    }

    public boolean checkSamePasswords()
    {
        if(!password.getText().equals(verify.getText())) {
            passwords.setText("Passwords do not match");
            return false;
        }
        return true;
    }
    protected boolean checkFields() {
    if(username.getText().isEmpty() || password.getText().isEmpty() || verify.getText().isEmpty()) {
        Notes.showDialog("Cannot create account!","You must complete all fields" );
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
        System.out.println("drawing...");
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


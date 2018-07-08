package com.fish.notes;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.fish.core.notes.LoginResult;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class RegisterScreen extends MyScreen
{

    private Image img;
    private TextField username, password, verify, email;
    private TextButton register;
    private Label label, passwords;

    public RegisterScreen(final Notes notes) {
        super(notes);
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
        email.setTextFieldListener(new TextField.TextFieldListener(){
            @Override
            public void keyTyped(TextField textfield, char key){
                //check if password is same both times
                if ((key == '\r' || key == '\n')){
                    textfield.next( Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT));
                }
            }
        });
        username.setTextFieldListener(new TextField.TextFieldListener(){
            @Override
            public void keyTyped(TextField textfield, char key){
                //check if password is same both times
                if ((key == '\r' || key == '\n')){
                    textfield.next(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT));
                }
            }
        });
        password.setTextFieldListener(new TextField.TextFieldListener(){
            @Override
            public void keyTyped(TextField textfield, char key){
                //check if password is same both times
                checkSamePasswords();
                if ((key == '\r' || key == '\n')){
                    textfield.next(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT));
                }
            }
        });
        verify.setTextFieldListener(new TextField.TextFieldListener(){
            @Override
            public void keyTyped(TextField textfield, char key){
                //check if password is same both times
                    checkSamePasswords();
                if ((key == '\r' || key == '\n')){
                    textfield.next(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT));
                }
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
                    notes.setScreen(Notes.screensManager);
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
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }


    @Override
    public void dispose() {
        stage.dispose();
    }

}


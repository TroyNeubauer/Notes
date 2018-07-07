package com.fish.notes;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;

public class ProfileScreen implements Screen {
    private Stage stage;
    private Texture pfp;
    private Label name, email, points, coins, label;
    private Notes game;
    

    public ProfileScreen(final Notes game)
    {
        this.game = game;
        this.stage = new Stage();

        this.label = new Label("User Profile", Notes.skin);
        label.setAlignment(Align.center);

        this.pfp = new Texture("Fishnotes_udp.png");
        pfp.setAlignment(Align.left);

        this.name = new Label("User: " + Notes.account.getUsername(), Notes.skin);
        this.email = new Label("Email: " + Notes.account.getEmail(), Notes.skin);
        this.points = new Label("Email: " + Notes.account.getPoints(), Notes.skin);
        this.coins = new Label("Email: " + Notes.account.getCoins(), Notes.skin);

        name.setAlignment(Align.left);
        email.setAlignment(Align.left);
        points.setAlignment(Align.left);
        coins.setAlignment(Align.left);
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

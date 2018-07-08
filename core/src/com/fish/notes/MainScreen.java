package com.fish.notes;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import java.awt.Color;

import de.tomgrill.gdxdialogs.core.dialogs.GDXButtonDialog;

public class MainScreen extends MyScreen {
    private Stage stage;
    private TextButton loginButton, registerBtn;
    private Label username, coins, totalvotes;
    private Image user, coin, votes;
    private Notes game;

    public MainScreen(final Notes notes) {
        this.game = new Notes();
        this.stage = new Stage();

        //this.username = new Label("" + Backend.getAccount(.getPosterID()).getUsername(), Notes.skin);
        //username.setColor(Color.DARK_GRAY);

        //this.coins = new Label("Coins: " + );

        //this.totalvotes = new Label("" + );

        this.user = new Image(new Texture("Fishnotes_udp.png"));
        this.coin = new Image(new Texture("coins.png"));
        this.votes = new Image(new Texture("Fishnotes_upvote.png"));

        Table table = new Table();
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        

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
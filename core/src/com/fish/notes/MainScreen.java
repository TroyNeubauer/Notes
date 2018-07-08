package com.fish.notes;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.fish.core.notes.Post;

import java.awt.Color;

import de.tomgrill.gdxdialogs.core.dialogs.GDXButtonDialog;

import static com.badlogic.gdx.scenes.scene2d.InputEvent.Type.scrolled;

public class MainScreen extends MyScreen {
    private Stage stage;
    private Label username, coins, totalvotes;
    private Image user, coin, votes;
    private Notes notes;

    public MainScreen(final Notes notes) {
        this.notes = new Notes();
        this.stage = new Stage();

        this.username = new Label("" + Notes.account, Notes.skin);
        username.setColor(com.badlogic.gdx.graphics.Color.DARK_GRAY);

        this.coins = new Label("Coins: " + Notes.account.getCoins(), Notes.skin);
        coins.setColor(com.badlogic.gdx.graphics.Color.DARK_GRAY);

        this.totalvotes = new Label("Total Votes: " + Notes.account.getPoints(), Notes.skin);
        totalvotes.setColor(com.badlogic.gdx.graphics.Color.DARK_GRAY);

        this.user = new Image(new Texture("Fishnotes_udp.png"));
        this.coin = new Image(new Texture("coins.png"));
        this.votes = new Image(new Texture("Fishnotes_upvote.png"));

        Table table = new Table();
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        table.add(user).height(30).width(30).right();
        table.add(username).height(30).width(30).left();
        table.add(coin).height(30).width(30).right();
        table.add(coins).height(30).width(30).left();
        table.add(votes).height(30).width(30).right();
        table.add(totalvotes).height(30).width(30).left();
        table.row();

        for(int i = 0; i < Backend.getRelevantPosts().size(); i++) {
            PostScreen item = new PostScreen(notes);
        }
        //Gdx.input.setInputProcessor(new GestureDetector(new MyGestureListener()));
        //stage.scrolled(notes.setScreen();)
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
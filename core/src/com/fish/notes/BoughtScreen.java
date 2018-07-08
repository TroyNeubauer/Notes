package com.fish.notes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class BoughtScreen extends MyScreen {
    private Stage stage;
    private Label username, coins, totalvotes;
    private Image user, coin, votes;
    private Notes notes;



    public BoughtScreen(final Notes notes)
    {
        this.stage = new Stage();
        this.notes = notes;

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

        for(int i = 0; i < Backend.getBoughtPosts().size(); i++) {
            PostScreen item = new PostScreen();
        }
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

package com.fish.notes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.fish.core.notes.Post;

public class BoughtScreen extends MyScreen {
    private Stage stage;
    private Label username, coins, totalvotes, votes, title;
    private Image user, coin, vote;
    private Notes notes;


    public BoughtScreen(final Notes notes) {
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
        this.vote = new Image(new Texture("Fishnotes_upvote.png"));

        Table table = new Table();
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        table.add(user).height(30).width(30).right();
        table.add(username).height(30).width(30).left();
        table.add(coin).height(30).width(30).right();
        table.add(coins).height(30).width(30).left();
        table.add(vote).height(30).width(30).right();
        table.add(totalvotes).height(30).width(30).left();
        table.row();

        for (int i = 0; i < Backend.getBoughtPosts().size(); i++) {
            this.stage = new Stage();
            for (Post p : Backend.getBoughtPosts()) {
                this.title = new Label("" + p.getTitle(), Notes.skin);
                title.setColor(Color.BLUE);
                this.votes = new Label("Votes: " + Backend.getUpvotes(p), Notes.skin);
                votes.setColor(Color.BLUE);
                this.username = new Label("" + Backend.getAccount(Notes.account.getID()).getUsername(), Notes.skin);
                Table supertable = new Table();
                supertable.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                supertable.add(title).width(40).height(40).expand().left();
                supertable.add(votes).width(40).height(40).expand().right();
                supertable.row();
                supertable.add(username).width(40).height(40).expand().bottom().left();
                supertable.row();
                stage.addActor(supertable);
                Gdx.input.setInputProcessor(stage);
            }
        }
    }
        public void render ( float delta){
            stage.act(delta);
            stage.draw();
        }
        public void dispose () {
            stage.dispose();
        }

}

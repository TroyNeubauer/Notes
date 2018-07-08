package com.fish.notes;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.fish.core.notes.Post;

public class MainScreen extends MyScreen {
    private Label username, coins, totalvotes;
    private Image user, coin, votes;

    public MainScreen(final Notes notes) {
        super(notes);
        this.username = new Label("" + Notes.account, Notes.skin);
        username.setColor(com.badlogic.gdx.graphics.Color.DARK_GRAY);

        this.coins = new Label("Coins: " + Notes.account.getCoins(), Notes.skin);
        coins.setColor(com.badlogic.gdx.graphics.Color.DARK_GRAY);

        this.totalvotes = new Label("Total Votes: " + Notes.account.getPoints(), Notes.skin);
        totalvotes.setColor(com.badlogic.gdx.graphics.Color.DARK_GRAY);

        this.user = new Image(new Texture("Fishnotes_udp.png"));
        this.coin = new Image(new Texture("coins.png"));
        this.votes = new Image(new Texture("Fishnotes_upvote.png"));

        MyGestureListener input = new MyGestureListener();
        Gdx.input.setInputProcessor(input);

        Table table = new Table();
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        table.add(user).height(30).width(30).right();
        table.add(username).height(30).width(30).left();
        table.add(coin).height(30).width(30).right();
        table.add(coins).height(30).width(30).left();
        table.add(votes).height(30).width(30).right();
        table.add(totalvotes).height(30).width(30).left();
        table.row();
        java.util.List<Post> relevantPosts = Backend.getRelevantPosts();
        System.out.println("relevant posts: " + relevantPosts);
        for(int i = 0; i < relevantPosts.size(); i++) {
            PostScreen item = new PostScreen(notes);
            stage.addActor(item);
        }
        //Gdx.input.setInputProcessor(new GestureDetector(new MyGestureListener()));
        //stage.scrolled(notes.setScreen();)
    }

}
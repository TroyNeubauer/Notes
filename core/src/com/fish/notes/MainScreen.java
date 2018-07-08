package com.fish.notes;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.fish.core.notes.Post;

public class MainScreen extends MyScreen {
    private Label username, coins, totalvotes;
    private Image user, coin, votes;
    private ImageButton makePost;

    public MainScreen(final Notes notes) {
        super(notes);
        this.username = new Label("" + Notes.account, Notes.skin);
        username.setColor(com.badlogic.gdx.graphics.Color.DARK_GRAY);
        username.setAlignment(Align.topLeft);

        this.coins = new Label("Coins: " + Notes.account.getAccount().getCoins(), Notes.skin);
        coins.setColor(com.badlogic.gdx.graphics.Color.DARK_GRAY);
        coins.setAlignment(Align.top);

        this.totalvotes = new Label("Total Votes: " + Notes.account.getAccount().getPoints(), Notes.skin);
        totalvotes.setColor(com.badlogic.gdx.graphics.Color.DARK_GRAY);
        totalvotes.setAlignment(Align.topRight);

        this.user = new Image(ClientUtils.jpegToTexture(Notes.account.toPublicAccount().getProfilePic()));
        user.setAlign(Align.topLeft);
        this.coin = new Image(new Texture("coins.png"));
        coin.setAlign(Align.top);
        this.votes = new Image(new Texture("Fishnotes_upvote.png"));
        votes.setAlign(Align.topRight);

        Drawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture("Fishnotes_post.png")));
        makePost = new ImageButton(drawable);

        Table table = new Table();
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        table.add(user).height(30).width(30);
        table.add(username).height(30).width(30);
        table.add(coin).height(30).width(30);
        table.add(coins).height(30).width(30);
        table.add(votes).height(30).width(30);
        table.add(totalvotes).height(30).width(30);
        table.row();
        stage.addActor(table);
        java.util.List<Post> relevantPosts = Backend.getRelevantPosts();
        System.out.println("relevant posts: " + relevantPosts);
        for(int i = 0; i < relevantPosts.size(); i++) {
            PostScreen item = new PostScreen(notes, relevantPosts.get(i));
            stage.addActor(item);
        }
        Table container = new Table();
        container.setBounds(0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        container.add(makePost).maxSize(30,30).bottom();
        stage.addActor(container);

        makePost.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            notes.setScreen(new MakeScreen(notes));
            }
        });
    }

}
package com.fish.notes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.fish.core.notes.Post;

public class BoughtScreen extends MyScreen {
    private Label username, coins, totalvotes, votes, title;
    private TextButton back;
    private Image user, coin, vote;


    public BoughtScreen(final Notes notes) {
        super(notes);

        this.back = new TextButton("Back", Notes.skin);
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                notes.setScreen(new MainScreen(notes));
            }
        });
        back.setColor(Color.DARK_GRAY);

        this.username = new Label("" + Notes.account, Notes.skin);
        username.setColor(com.badlogic.gdx.graphics.Color.DARK_GRAY);

        this.coins = new Label("Coins: " + Notes.account.getAccount().getCoins(), Notes.skin);
        coins.setColor(com.badlogic.gdx.graphics.Color.DARK_GRAY);

        this.totalvotes = new Label("Total Votes: " + Notes.account.getAccount().getPoints(), Notes.skin);
        totalvotes.setColor(com.badlogic.gdx.graphics.Color.DARK_GRAY);

        this.user = new Image(new Texture("Fishnotes_udp.png"));
        this.coin = new Image(new Texture("coins.png"));
        this.vote = new Image(new Texture("Fishnotes_upvote.png"));

        Table table = new Table();
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        table.add(user).height(30).width(30);
        table.add(username).height(30).width(60);
        table.add(coin).height(30).width(60);
        table.add(coins).height(30).width(30);
        table.add(vote).height(30).width(60);
        table.add(totalvotes).height(30).width(30);
        table.row();

            for (Long postID : Backend.getBoughtPosts()) {
                Post post = Backend.getPost(postID);
                this.title = new Label("" + post.getTitle(), Notes.skin);
                title.setColor(Color.BLUE);
                this.votes = new Label("Votes: " + Backend.getUpvotes(post), Notes.skin);
                votes.setColor(Color.BLUE);
                this.username = new Label("" + Backend.getAccount(Notes.account.getAccount().getID()).getUsername(), Notes.skin);
                Table supertable = new Table();
                supertable.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                supertable.add(back).width(40).height(40).top().right();
                supertable.row();
                supertable.add(title).width(40).height(40).expand().left();
                supertable.add(votes).width(40).height(40).expand().right();
                supertable.row();
                supertable.add(username).width(40).height(40).expand().bottom().left();
                supertable.row();
                supertable.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("bgPost.png"))));
                stage.addActor(supertable);


            }
    }

    @Override
    public void hide() {

    }
}

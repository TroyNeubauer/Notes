package com.fish.notes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.fish.core.notes.Post;

import javax.xml.soap.Text;

public class PostScreen extends Table {
    private TextButton back;
    private Label title, votes, username;
    private Image pfp;
    private ImageButton up, down;
    private Notes notes;

    public PostScreen(final Notes notes, final Post p) {
        this.notes = notes;
        {
            this.title = new Label("" + p.getTitle(), Notes.skin);
            title.setColor(Color.BLUE);
            this.votes = new Label("Votes: " + Backend.getUpvotes(p), Notes.skin);
            votes.setColor(Color.BLUE);
            this.username = new Label("" + Notes.account.getAccount().getUsername(), Notes.skin);
            this.back = new TextButton("Back", Notes.skin);
            back.setColor(Color.DARK_GRAY);
            back.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    notes.setScreen(new MainScreen(notes));
                }
            });
            Drawable thing1 = new TextureRegionDrawable(new TextureRegion(new Texture("Fishnotes_upvote.png")));
            this.up = new ImageButton(thing1);
            up.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Backend.addUpvote(p, 1);
                }
            });
            Drawable thing2 = new TextureRegionDrawable(new TextureRegion(new Texture("Fishnotes_downvote.png")));
            this.down = new ImageButton(thing2);
            down.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Backend.addUpvote(p, -1);
                }
            });
            Table table = new Table();
            table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            table.add(back).prefSize(100, 50).right().top().row();
            table.add(title).prefSize(300, 85).expand().left();
            table.add(votes).prefSize(60, 60).right();
            table.row();
            table.add(username).width(40).height(40).expand().bottom().left();
            table.add(up).right().width(40).height(40);
            table.add(down).left().width(40).height(40);
            table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("bgPost.png"))));


        }
    }
}

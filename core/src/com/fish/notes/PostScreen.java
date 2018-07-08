package com.fish.notes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.fish.core.notes.Post;

import javax.xml.soap.Text;

public class PostScreen extends MyScreen {
    private TextButton back;
    private Stage stage;
    private Notes notes;
    private Label title, votes, username;
    private Image pfp;

    public PostScreen(final Notes notes)
    {
        this.stage = new Stage();
        this.notes = new Notes();
        for(Post p: Backend.getRelevantPosts()) {
            this.title = new Label("" + p.getTitle(), Notes.skin);
            title.setColor(Color.BLUE);
            this.votes = new Label("Votes: " + Backend.getUpvotes(p), Notes.skin);
            votes.setColor(Color.BLUE);
            this.username = new Label("" + Backend.getAccount(Notes.account.getID()).getUsername(), Notes.skin);
            this.back = new TextButton("Back", Notes.skin);
            back.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    notes.setScreen(new MainScreen(notes));
                }
            });
            Table table = new Table();
            table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            table.add(back).right().top().height(40).width(40);
            table.add(title).width(40).height(40).expand().left();
            table.add(votes).width(40).height(40).expand().right();
            table.row();
            table.add(username).width(40).height(40).expand().bottom().left();
            table.row();
            stage.addActor(table);
            Gdx.input.setInputProcessor(stage);
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

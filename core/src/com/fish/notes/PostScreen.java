package com.fish.notes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.fish.core.notes.Post;

public class PostScreen extends MyScreen {
    private Stage stage;
    private Label title, votes, username;
    private Image pfp;

    public PostScreen()
    {
        this.stage = new Stage();
        for(Post p: Backend.getRelevantPosts()) {
            this.title = new Label("" + p.getTitle(), Notes.skin);
            title.setColor(Color.BLUE);
            this.votes = new Label("Votes: " + Backend.getUpvotes(p), Notes.skin);
            votes.setColor(Color.BLUE);
            this.username = new Label("" + Backend.getAccount(p.getPosterID()).getUsername(), Notes.skin);
            Table table = new Table();
            table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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

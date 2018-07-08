package com.fish.notes;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.fish.core.notes.LoginResult;

public class MakeScreen extends MyScreen {
    private Stage stage;
    private TextButton sumbit, picchoice, textchoice;
    private Label label;
    private TextField title, postdata, classbox;
    private Notes game;

    @Override
    public void hide() {
    }

    public MakeScreen(final Notes game) {
        this.game = game;
        this.stage = stage;

        this.label = new Label("Create a post", Notes.skin);
        this.label.setColor(Color.DARK_GRAY);

        this.title = new TextField("", Notes.skin);
        title.setMessageText("Title");
        title.setColor(Color.BLUE);

        this.postdata = new TextField("", Notes.skin);
        postdata.setMessageText("Enter Text");
        postdata.setColor(Color.BLUE);

        this.classbox = new TextField("", Notes.skin);
        classbox.setMessageText("Class");
        classbox.setColor(Color.BLUE);

        this.picchoice = new TextButton("Picture Post", Notes.skin);

        this.textchoice = new TextButton("Text Post", Notes.skin);

        Table table = new Table();
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        table.add(picchoice).width(Gdx.graphics.getWidth()/2).height(30).left();
        table.add(textchoice).width(Gdx.graphics.getWidth()/2).height(30).right();
        table.row();
        table.add(title).width(40).height(40).expand().top().center();
        table.row();
        table.add(postdata).width(40).height(40).expand().top().center();
        table.row();
        table.add(classbox).width(40).height(40).expand().top().center();
        table.row();
        table.add(title).width(40).height(40).expand().top().center();
        table.row();
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

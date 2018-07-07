package com.fish.notes;

import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import de.tomgrill.gdxdialogs.core.dialogs.GDXButtonDialog;

public class MakeScreen implements Screen {
    private Stage stage;
    private TextButton sumbit, picchoice, textchoice;
    private Label label;
    private TextField title;
    private Notes game;


    public MakeScreen(final Notes game) {
        this.game = game;
        this.stage = stage;

    
        this.title = new TextField("", Notes.skin);
        title.setMessageText("Title");

        container.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Table container = new Table();
        container.add(title);

        this.sumbit = new TextButton("Post", Notes.skin);
        sumbit.addListener(finalize);

        EventListener finalize = new EventListener() {
            @Override
            public boolean handle(Event event) {
                //Post temp = new Post(new PostData post, title.getText());
                return false;
            }
        }
    }



    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void show() {

    }
}

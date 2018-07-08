package com.fish.notes;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

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

        /*
        this.picchoice = new TextButton("Picture Post");
        picchoice
        */

        this.label = new Label("Create a post", Notes.skin);

        this.title = new TextField("", Notes.skin);
        title.setMessageText("Title");
        title.setColor(Color.BLUE);

        this.postdata = new TextField("", Notes.skin);
        postdata.setMessageText("Enter Text");
        postdata.setColor(Color.BLUE);

        this.classbox = new TextField("", Notes.skin);
        classbox.setMessageText("Class");
        postdata.setColor(Color.BLUE);
        /*
        title.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(title.getText().length() > 0) {
                    //Backend.post(title.getText, new Course (0, "Math"), postdata.getText());
                }
            }
        });
    */


        this.sumbit = new TextButton("Post", Notes.skin);
        //sumbit.addListener(finalize);

        Gdx.input.setInputProcessor(stage);


        Table table = new Table();
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        table.add(title).width(50).height(50).expand().bottom().left();
        table.row();
        table.add(postdata).width(50).height(50).expand().bottom().left();
        table.row();
        table.add(classbox).width(50).height(50).expand().bottom().left();
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

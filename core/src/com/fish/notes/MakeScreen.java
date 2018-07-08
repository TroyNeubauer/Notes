package com.fish.notes;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.fish.core.notes.Course;
import com.fish.core.notes.LoginResult;
import com.fish.core.notes.PostData;
import com.fish.core.notes.PostDataText;

import java.util.Set;

public class MakeScreen extends MyScreen {

    private TextButton submit,back;
    private CheckBox picchoice, textchoice;
    private Label label;
    private TextField title, classbox;
    private TextArea postdata;
    private Course course;
    private PostData data;

    @Override
    public void hide() {
    }

    public MakeScreen(final Notes notes) {
        super(notes);
        this.back = new TextButton("Back", Notes.skin);
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                notes.setScreen(new MainScreen(notes));
            }
        });

        this.label = new Label("Create a post", Notes.skin);
        this.label.setColor(Color.DARK_GRAY);

        this.title = new TextField("", Notes.skin);
        title.setMessageText("Title");
        title.setColor(Color.BLUE);

        this.postdata = new TextArea("", Notes.skin);
        postdata.setMessageText("Enter Text");
        postdata.setColor(Color.BLUE);

        this.classbox = new TextField("", Notes.skin);
        classbox.setMessageText("Class");
        classbox.setColor(Color.BLUE);


        this.picchoice = new CheckBox("Picture Post", Notes.skin);

        this.textchoice = new CheckBox("Text Post", Notes.skin);

        final Set<Course> courses = Backend.getAllClasses();

        for(Course temp : courses){
            if(temp.getName().equals(classbox.getText())){
                course = temp;
            }
        }
        this.submit = new TextButton("Submit Post", Notes.skin);

        Table superTable = new Table();
        Table table = new Table();
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        table.add(back).width(40).height(40).top().right();
        table.row();
        table.add(title).width(40).height(40).expand().top().left();
        table.add(classbox).width(40).height(40).expand().top().right().row();
        table.add(picchoice).width(Gdx.graphics.getWidth()/2).height(30).left();
        table.add(textchoice).width(Gdx.graphics.getWidth()/2).height(30).right().row();
        superTable.add(table);
        final Table container = new Table();

        picchoice.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){

            }
        });
        textchoice.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                container.add(postdata).prefSize(postdata.getWidth(), postdata.getHeight()).row();
                container.add(submit).prefSize(submit.getWidth(),submit.getHeight()).row();
            }
        });
        superTable.add(container);
        stage.addActor(superTable);
        Gdx.input.setInputProcessor(stage);


        postdata.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textfield, char key)
            {
                data = data = new PostDataText(postdata.getText());
            }
        });

        submit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Backend.post(title.getText(), course, data);
            }
        });
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

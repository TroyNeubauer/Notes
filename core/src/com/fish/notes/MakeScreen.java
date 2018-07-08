package com.fish.notes;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.fish.core.notes.Course;
import com.fish.core.notes.LoginResult;
import com.fish.core.notes.PostData;
import com.fish.core.notes.PostDataImage;
import com.fish.core.notes.PostDataText;

import java.util.Set;

public class MakeScreen extends MyScreen {

    private TextButton submit,back;
    private ImageButton img;
    private CheckBox picchoice, textchoice;
    private Label label;
    private TextField title, classbox;
    private TextArea textPost;
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

        this.textPost = new TextArea("", Notes.skin);
        textPost.setMessageText("Enter Text");
        textPost.setColor(Color.BLUE);

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

        Drawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture("bgPost.png")));
        img = new ImageButton(drawable);

        final Table superTable = new Table();
        Table table = new Table();
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        table.add(back).width(40).height(40).top().right();
        table.row();
        table.add(title).width(40).height(40).expand().top().left();
        table.add(classbox).width(40).height(40).expand().top().right().row();
        table.add(picchoice).width(Gdx.graphics.getWidth()/2).height(30).left();
        table.add(textchoice).width(Gdx.graphics.getWidth()/2).height(30).right().row();
        table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("bgPost.png"))));
        superTable.add(table);

        final Table container = new Table();

        picchoice.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                container.add(img).prefSize(img.getWidth(), img.getHeight()).row();
                container.add(submit).prefSize(submit.getWidth(), submit.getHeight()).row();
            }
        });
        textchoice.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                container.add(textPost).prefSize(textPost.getWidth(), textPost.getHeight()).row();
                container.add(submit).prefSize(submit.getWidth(),submit.getHeight()).row();
            }
        });
        container.setColor(Color.TEAL);
        superTable.add(container);

        stage.addActor(superTable);

        title.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textfield, char key)
            {
                if ((key == '\r' || key == '\n')){
                    textfield.next(Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT) );
                }
            }
        });
        classbox.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textfield, char key)
            {
                if ((key == '\r' || key == '\n')){
                    textfield.next(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT) );
                }
            }
        });
        textPost.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textfield, char key)
            {
                data = data = new PostDataText(textPost.getText());
            }
        });

        img.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Texture img = Notes.getPics(Notes.gallery.getSelectedFilePath());
                data  = new PostDataImage(ClientUtils.textureToJPEG(img));
            }
        });

        submit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Backend.post(title.getText(), course, data);
            }
        });
    }

}

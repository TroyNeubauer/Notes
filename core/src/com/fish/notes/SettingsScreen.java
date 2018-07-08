package com.fish.notes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.fish.core.notes.Course;
import com.fish.core.notes.School;

import java.util.Set;

public class SettingsScreen extends MyScreen
{
    private Stage stage;
    private TextField changeSchool, addCourse;
    //private ImageButton img;
    private TextButton save;
    private Notes notes;


    public SettingsScreen(final Notes notes)
    {
        this.stage = new Stage();
        this.notes = notes;

        final Set<School> schools = Backend.getAllSchools();
        final Set<Long> courses = Notes.account.getAllClasses();

        this.changeSchool = new TextField("", Notes.skin);
        changeSchool.setMessageText("Enter a new school");
        changeSchool.setAlignment(Align.center);

        this.addCourse = new TextField("", Notes.skin);
        addCourse.setMessageText("Add a new course");
        addCourse.setAlignment(Align.center);

        //Drawable drawable = new TextureRegionDrawable(new TextureRegion( new))
       // this.img = new ImageButton(drawable);

        this.save = new TextButton("Save changes", Notes.skin);

        Table container = new Table();
        container.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //container.add(img).prefSize(img.getWidth(), img.getHeight()).row();
        container.add(changeSchool).prefSize(changeSchool.getWidth(), changeSchool.getHeight()).row();
        container.add(addCourse).prefSize(addCourse.getWidth(), addCourse.getHeight()).row();
        for(long ID : Notes.account.getClasses())
        {
            final Course course = Backend.getClass(ID);
            Label temp = new Label ("" + course.getName(), Notes.skin);
            container.add(temp).prefSize(temp.getWidth(), temp.getHeight()).left();

            Drawable drawable2 = new TextureRegionDrawable(new TextureRegion(new Texture("Fishnotes_downvote.png")));
            ImageButton img = new ImageButton(drawable2);
            img.addListener(new ClickListener()
            {
                @Override
                public void clicked(InputEvent event, float x, float y)
                {
                    Backend.removeClass(course);
                }
            });
            container.add(img).prefSize(img.getWidth(), img.getHeight()).right().row();

        }
        container.add(save).prefSize(save.getWidth(), save.getHeight()).row();

        stage.addActor(container);

        changeSchool.setTextFieldListener(new TextField.TextFieldListener(){
            @Override
            public void keyTyped(TextField textfield, char key) {
                //changes school
                School school = null;
                for(School temp : schools){
                    if(temp.getName().equals(changeSchool.getText()))
                        school = temp;
                }
                Backend.setSchool(school);

            }
        });

        addCourse.setTextFieldListener(new TextField.TextFieldListener(){
            @Override
            public void keyTyped(TextField textfield, char key) {
                //adds course to list
                Course course = null;
                for(Long ID : courses)
                {
                   if(Backend.getClass(ID).getName().equals(addCourse.getText()))
                       course = Backend.getClass(ID);
                }
                Backend.joinClass(Notes.account,course);
            }
        });

        save.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                //save changes, go back to profile screen
                notes.setScreen(new ProfileScreen(notes));
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

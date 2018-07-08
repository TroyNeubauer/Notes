package com.fish.notes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

public class ProfileScreen extends MyScreen {
    private Stage stage;
    private Image pfp;
    private Label name, email, points, coins, label, school, courses;
    private TextButton edit, back;
    private Notes notes;


    public ProfileScreen(final Notes notes) {
        this.notes = notes;
        this.stage = new Stage();

        this.back = new TextButton("Back", Notes.skin);
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                notes.setScreen(new MainScreen(notes));
            }
        });

        this.label = new Label("User Profile", Notes.skin);
        label.setAlignment(Align.center);
        label.setColor(Color.TEAL);

        this.pfp = new Image(new Texture("Fishnotes_udp.png"));

        this.back = new TextButton("Back", Notes.skin);
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                notes.setScreen(new MainScreen(notes));
            }
        });



        this.name = new Label("User: " + Notes.account.getUsername(), Notes.skin);
        this.email = new Label("Email: " + Notes.account.getEmail(), Notes.skin);
        this.school = new Label("School: " + Backend.getSchool(Notes.account.getID()), Notes.skin);
        this.courses = new Label("Courses: ", Notes.skin);
        this.points = new Label("Points: " + Notes.account.getPoints(), Notes.skin);
        this.coins = new Label("Coins: " + Notes.account.getCoins(), Notes.skin);

        name.setAlignment(Align.right);
        email.setAlignment(Align.right);
        school.setAlignment(Align.right);
        courses.setAlignment(Align.right);
        points.setAlignment(Align.right);
        coins.setAlignment(Align.right);

        name.setColor(Color.TEAL);
        email.setColor(Color.TEAL);
        school.setColor(Color.TEAL);
        courses.setColor(Color.TEAL);
        points.setColor(Color.TEAL);
        coins.setColor(Color.TEAL);

        this.edit = new TextButton("Edit profile", Notes.skin);

        Table container = new Table();
        container.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        container.add(back).right().top().width(40).height(40);
        container.row();
        container.add(pfp).left().row();
        container.add(courses).prefSize(courses.getWidth(), courses.getHeight()).left().row();

        for(long ID : Notes.account.getClasses())
        {
            Label temp = new Label("" + Backend.getClass(ID), Notes.skin);
            container.add(temp).prefSize(temp.getWidth(), temp.getHeight()).left().row();
        }

        container.add(name).prefSize(name.getWidth(), name.getHeight()).row();
        container.add(email).prefSize(email.getWidth(), email.getHeight()).row();
        container.add(school).prefSize(school.getWidth(), school.getHeight()).row();
        container.add(points).prefSize(points.getWidth(), points.getHeight()).row();
        container.add(coins).prefSize(coins.getWidth(), coins.getHeight()).row();
        container.add(edit).prefSize(edit.getWidth(), edit.getHeight()).left().row();
        stage.addActor(container);

        Gdx.input.setInputProcessor(stage);

        edit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                notes.setScreen(new SettingsScreen(notes));
            }

        });

    }
        @Override
        public void render ( float delta){
            Gdx.gl.glClearColor(.123f, .675f,.89f, 1);
            stage.act(delta);
            stage.draw();

        }

        @Override
        public void dispose () {
            stage.dispose();
        }

    }



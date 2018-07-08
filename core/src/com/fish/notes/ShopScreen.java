package com.fish.notes;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class ShopScreen extends MyScreen {

    private Stage stage;
    private TextButton buysmall, buymed, buylarge;
    private Notes notes;
    private Label title, name, coins, smallpack, mediumpack, largepack;
    private Image small, med, large;

    public ShopScreen(Notes notes) {
        this.notes = notes;
        this.stage = new Stage();
        this.title = new Label("Shop", Notes.skin);
        title.setFontScaleX(2);
        title.setFontScaleY(2);
        title.setColor(Color.TEAL);

        this.name = new Label("User: " + Backend.getAccount(Notes.account.getID()).getUsername(), Notes.skin);
        name.setAlignment(Align.left);
        name.setColor(Color.DARK_GRAY);

        this.coins = new Label("Coins: " + Notes.account.getCoins(), Notes.skin);
        coins.setAlignment(Align.left);
        
        this.smallpack = new Label("Purchase Small", Notes.skin);
        this.small = new Image(new Texture("small.png"));
        smallpack.setColor(Color.BLUE);
        smallpack.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //
            }
        });
        this.buysmall = new TextButton("$0.99", Notes.skin);
        this.mediumpack = new Label("Purchase Medium", Notes.skin);
        mediumpack.setColor(Color.BLUE);
        mediumpack.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //
            }
        });
        this.med = new Image(new Texture("medium.png"));
        this.buymed = new TextButton("$4.29", Notes.skin);
        this.largepack = new Label("Purchase Large", Notes.skin);
        largepack.setColor(Color.BLUE);
        largepack.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //
            }
        });
        this.large = new Image(new Texture("large.png" ));
        this.buylarge = new TextButton("$14.99", Notes.skin);


        Table table = new Table();
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        table.add(title).width(40).height(40).expand().top().center();
        table.row();
        table.add(name).width(40).height(40).expand().top().left();
        table.row();
        table.add(coins).width(40).height(40).expand().top().left();
        table.row();
        table.add(smallpack).width(20).height(20).expand().bottom().left();
        table.add(mediumpack).width(20).height(20).expand().bottom().left();
        table.add(largepack).width(20).height(20).expand().bottom().left();
        table.row();
        table.add(small).width(200).height(200);
        table.add(med).width(200).height(200);
        table.add(large).width(200).height(200);
        table.row();
        table.add(buysmall);
        table.add(buymed);
        table.add(buylarge);
        table.row();
        table.setColor(Color.WHITE);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }
    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
        //Gdx.gl.glClearColor(0.9f, .5f, .5f, 5);
    }
    @Override
    public void dispose() {
        stage.dispose();
    }
}

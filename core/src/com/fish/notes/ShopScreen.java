package com.fish.notes;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.pay.PurchaseSystem;
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
import com.fish.core.NotesConstants;

public class ShopScreen extends MyScreen {

    private TextButton buysmall, buymed, buylarge, back;
    private Label title, name, coins, smallpack, mediumpack, largepack;
    private Image small, med, large;

    public ShopScreen(final Notes notes) {
        super(notes);
        this.title = new Label("Shop", Notes.skin);
        title.setFontScaleX(2);
        title.setFontScaleY(2);
        title.setColor(Color.TEAL);

        this.back = new TextButton("Back", Notes.skin);
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                notes.setScreen(new MainScreen(notes));
            }
        });

        this.name = new Label("User: " + Backend.getAccount(Notes.account.getID()).getUsername(), Notes.skin);
        name.setAlignment(Align.left);
        name.setColor(Color.DARK_GRAY);

        this.coins = new Label("Coins: " + Notes.account.getCoins(), Notes.skin);
        coins.setAlignment(Align.left);
        
        this.smallpack = new Label("Purchase Small", Notes.skin);
        this.small = new Image(new Texture("small.png"));
        smallpack.setColor(Color.BLUE);
        this.buysmall = new TextButton("$0.99", Notes.skin);
        buysmall.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                notes.getPlatformResolver().requestPurchase(NotesConstants.ONE_COIN_ID);
                notes.checkTransaction(NotesConstants.ONE_COIN_ID);
            }
        });
        this.mediumpack = new Label("Purchase Medium", Notes.skin);
        mediumpack.setColor(Color.BLUE);
        this.med = new Image(new Texture("medium.png"));
        this.buymed = new TextButton("$4.29", Notes.skin);
        buymed.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                notes.getPlatformResolver().requestPurchase(NotesConstants.FIVE_COIN_ID);
                notes.checkTransaction(NotesConstants.FIVE_COIN_ID);
            }
        });
        this.largepack = new Label("Purchase Large", Notes.skin);
        largepack.setColor(Color.BLUE);
        this.large = new Image(new Texture("large.png" ));
        this.buylarge = new TextButton("$14.99", Notes.skin);
        buylarge.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                notes.getPlatformResolver().requestPurchase(NotesConstants.TWENTY_COIN_ID);
                notes.checkTransaction(NotesConstants.TWENTY_COIN_ID);
            }
        });


        Table table = new Table();
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        table.add(back).prefSize(150, 50).top().right().row();
        table.add(title).prefSize(300, 85).expand().top().center();
        table.row();
        table.add(name).prefSize(50, 50).expand().top().left();
        table.row();
        table.add(coins).prefSize(50, 50).expand().top().left();
        table.row();
        table.add(smallpack).prefSize(50, 50).expand().bottom().left();
        table.add(mediumpack).prefSize(50, 50).expand().bottom().left();
        table.add(largepack).prefSize(50, 50).expand().bottom().left();
        table.row();
        table.add(small).prefSize(200, 200);
        table.add(med).prefSize(200, 200);
        table.add(large).prefSize(200, 200);
        table.row();
        table.add(buysmall);
        table.add(buymed);
        table.add(buylarge);
        table.row();
        table.setColor(Color.TEAL);
        stage.addActor(table);
    }

}

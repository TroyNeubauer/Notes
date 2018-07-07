package com.fish.notes;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class SettingsScreen extends MyScreen
{
    private Stage stage;

    public SettingsScreen()
    {
        this.stage = new Stage();
    }


    @Override
    public void render(float delta) {

    }

    @Override
    public void dispose() {
        stage.dispose();

    }
}

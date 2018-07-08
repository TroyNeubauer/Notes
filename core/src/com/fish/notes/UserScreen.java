package com.fish.notes;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class UserScreen extends MyScreen
{
    public UserScreen(final Notes notes)
   {
       super(notes);
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

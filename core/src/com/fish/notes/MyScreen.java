package com.fish.notes;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class MyScreen implements Screen
{
    protected Stage stage;
    protected Notes notes;

    public MyScreen(Notes notes) {
        this.notes = notes;
        this.stage = new Stage();
    }
    /** Called when this screen becomes the current screen for a {@link Game}. */
    public void show () {

    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    /** @see ApplicationListener#resize(int, int) */
    public void resize (int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    /** @see ApplicationListener#pause() */
    public void pause () {

    }
    /** @see ApplicationListener#resume() */
    public void resume () {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}

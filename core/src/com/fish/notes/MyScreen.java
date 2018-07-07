package com.fish.notes;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public abstract class MyScreen implements Screen
{
    public MyScreen()
    {
    }
    /** Called when this screen becomes the current screen for a {@link Game}. */
    public void show ()
    {

    }

    /** @see ApplicationListener#resize(int, int) */
    public void resize (int width, int height)
    {

    }

    /** @see ApplicationListener#pause() */
    public void pause ()
    {

    }
    /** @see ApplicationListener#resume() */
    public void resume ()
    {

    }

    /** Called when this screen is no longer the current screen for a {@link Game}. */
    public void hide ()
    {
        dispose();
    }
}

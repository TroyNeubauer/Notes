package com.fish.notes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class ScreensManager implements Screen {
    private Class<? extends MyScreen>[] classes = new Class[]{ShopScreen.class, MakeScreen.class,MainScreen.class,BoughtScreen.class, ProfileScreen.class};
    private MyScreen[] screens = new MyScreen[classes.length];
    private int currentIndex = 2;
    private Notes notes;

    public ScreensManager(Notes notes) {
        this.notes = notes;
    }

    @Override
    public void show() {
        setScreen(currentIndex);
    }

    public void incrementScreen(int incrememt) {
        setScreen(currentIndex + incrememt);
    }

    private void setScreen(int currentIndex) {
        this.currentIndex = Math.min(classes.length, Math.max(0, currentIndex));
        if(screens[currentIndex] == null) {
            try {
                screens[currentIndex] = classes[currentIndex].getConstructor(Notes.class).newInstance(notes);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        screens[currentIndex].show();
        screens[currentIndex].resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

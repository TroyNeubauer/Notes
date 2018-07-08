package com.fish.notes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class ScreensManager implements Screen {
    private Class<? extends MyScreen>[] classes = new Class[]{ShopScreen.class, MakeScreen.class,MainScreen.class,BoughtScreen.class, ProfileScreen.class};
    private MyScreen[] screens = new MyScreen[classes.length];
    private int currentIndex = -1;
    private Notes notes;

    public ScreensManager(Notes notes) {
        this.notes = notes;
        setScreen(2);
    }

    @Override
    public void show() {
        setScreen(currentIndex);
    }

    public void incrementScreen(int incrememt) {
        setScreen(currentIndex + incrememt);
    }

    private void setScreen(int newIndex) {
        int oldIndex = currentIndex;
        this.currentIndex = Math.min(classes.length - 1, Math.max(0, newIndex));
        if(screens[currentIndex] == null) {
            try {
                screens[currentIndex] = classes[currentIndex].getConstructor(Notes.class).newInstance(notes);
                System.out.println("Creating class " + classes[currentIndex]);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if(oldIndex != currentIndex) {
            screens[currentIndex].show();
            screens[currentIndex].resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            if(screens[oldIndex] != null) {
                screens[oldIndex].hide();
            }
        }
    }

    @Override
    public void render(float delta) {
        screens[currentIndex].render(delta);
    }

    @Override
    public void resize(int width, int height) {
        screens[currentIndex].resize(width, height);
    }

    @Override
    public void pause() {
        screens[currentIndex].pause();
    }

    @Override
    public void resume() {
        screens[currentIndex].resume();
    }

    @Override
    public void hide() {
        screens[currentIndex].hide();
    }

    @Override
    public void dispose() {

    }
}

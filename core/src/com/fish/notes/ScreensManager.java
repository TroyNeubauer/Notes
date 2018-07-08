package com.fish.notes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

public class ScreensManager implements Screen {
    private Class<? extends MyScreen>[] classes = new Class[]{ShopScreen.class, MakeScreen.class,MainScreen.class,BoughtScreen.class, ProfileScreen.class};
    private MyScreen[] screens = new MyScreen[classes.length];
    private int currentIndex = 0;
    private Notes notes;

    public ScreensManager(Notes notes) {
        this.notes = notes;
        setScreen(currentIndex, false);
    }

    public void setListener() {
        Gdx.input.setInputProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                screens[currentIndex].stage.keyDown(keycode);
                return true;
            }

            @Override
            public boolean keyUp(int keycode) {
                screens[currentIndex].stage.keyUp(keycode);
                return true;
            }

            @Override
            public boolean keyTyped(char character) {
                screens[currentIndex].stage.keyTyped(character);
                return true;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                screens[currentIndex].stage.touchDown(screenX, screenY, pointer, button);
                return true;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                screens[currentIndex].stage.touchUp(screenX, screenY, pointer, button);
                return true;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                screens[currentIndex].stage.touchDragged(screenX, screenY, pointer);
                return true;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                screens[currentIndex].stage.mouseMoved(screenX, screenY);
                return true;
            }

            @Override
            public boolean scrolled(int amount) {
                System.out.println("amount " + amount);
                incrementScreen(amount);
                return true;
            }
        });
    }

    @Override
    public void show() {
        setListener();
        setScreen(currentIndex, true);
    }

    public void incrementScreen(int incrememt) {
        setScreen(currentIndex + incrememt, true);
    }

    private void setScreen(int newIndex, boolean forceCreate) {
        int oldIndex = currentIndex;
        this.currentIndex = Math.min(classes.length - 1, Math.max(0, newIndex));
        if(forceCreate && screens[currentIndex] == null) {
            try {
                System.out.println("Creating class " + classes[currentIndex]);
                screens[currentIndex] = classes[currentIndex].getConstructor(Notes.class).newInstance(notes);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if(forceCreate && oldIndex != currentIndex) {
            screens[currentIndex].show();
            screens[currentIndex].resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            if(screens[oldIndex] != null) {
                screens[oldIndex].hide();
            }
        }
    }

    @Override
    public void render(float delta) {
        System.out.println("Rendering screen " + currentIndex);
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

package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.screen.AbstractScreen;
import com.mygdx.game.screen.ScreenType;

import java.util.EnumMap;

public class MyGdxGame extends Game {
    private static final String TAG = MyGdxGame.class.getSimpleName();
    private EnumMap<ScreenType, AbstractScreen> screenCache;
    private FitViewport screenViewPort;
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private WorldContactListener worldContactListener;

    // 0000 0000 0000 0000
    // 0000 0000 0000 0001
    // 0000 0000 0000 0010
    // 0000 0000 0000 0100
    public static final short BIT_CIRCLE = 1 << 0;
    public static final short BIT_BOX = 1 << 1;
    public static final short BIT_GROUND = 1 << 2;

    private static final float FIXED_TIME_STEP = 1 / 60f;
    private float accumulator;


    @Override
    public void create() {
        accumulator = 0f;
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Box2D.init();
        world = new World(new Vector2(0f, -9.81f), true);
        worldContactListener = new WorldContactListener();
        world.setContactListener(worldContactListener);

        box2DDebugRenderer = new Box2DDebugRenderer();
        screenCache = new EnumMap<>(ScreenType.class);
        screenViewPort = new FitViewport(9, 16);
        setScreen(ScreenType.LOADING);
    }

    public void setScreen(final ScreenType screenType) {
        final Screen screen = screenCache.get(screenType);
        if (screen == null) {
            try {
                Gdx.app.debug(TAG, "Creating new screen: " + screenType);
                final AbstractScreen newScreen = (AbstractScreen) ClassReflection.getConstructor(screenType.getScreenClass(), MyGdxGame.class)
                        .newInstance(this);
                screenCache.put(screenType, newScreen);
                Gdx.app.debug(TAG, "Switching to new screen: " + screenType);
                setScreen(newScreen);
            } catch (ReflectionException e) {
                throw new GdxRuntimeException("Screen:: " + screenType + " could not be created.", e);
            }
        } else {
            Gdx.app.debug(TAG, "Switching to screen: " + screenType);
            setScreen(screen);
        }
    }

    @Override
    public void render() {
        super.render();
        accumulator += Math.min(0.25f, Gdx.graphics.getDeltaTime());
        while (accumulator >= FIXED_TIME_STEP) {
            world.step(FIXED_TIME_STEP, 6, 2);
            accumulator -= FIXED_TIME_STEP;
        }
        // final float alpha = accumulator / FIXED_TIME_STEP;
    }

    @Override
    public void dispose() {
        super.dispose();
        box2DDebugRenderer.dispose();
        world.dispose();
    }

    public FitViewport getScreenViewPort() {
        return screenViewPort;
    }

    public Box2DDebugRenderer getBox2DDebugRenderer() {
        return box2DDebugRenderer;
    }

    public World getWorld() {
        return world;
    }

}

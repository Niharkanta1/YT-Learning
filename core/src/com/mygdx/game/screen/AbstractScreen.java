package com.mygdx.game.screen;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.MyGdxGame;

/*
 * @created 16/01/2022
 * @project YT-Learning
 * @author nihar
 */
public abstract class AbstractScreen implements Screen {
    protected final MyGdxGame context;
    protected final FitViewport viewport;
    protected final World world;
    protected final Box2DDebugRenderer box2DDebugRenderer;

    public AbstractScreen(final MyGdxGame context) {
        this.context = context;
        viewport = context.getScrrenViewPort();
        box2DDebugRenderer = context.getBox2DDebugRenderer();
        world = context.getWorld();
    }

    @Override
    public void resize(final int width, final int height) {
        viewport.update(width, height);
    }
}

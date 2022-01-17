package com.mygdx.game.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.MyGdxGame;

import static com.mygdx.game.MyGdxGame.*;

/*
 * @created 16/01/2022
 * @project YT-Learning
 * @author nihar
 */
public class TestScreen extends AbstractScreen {
    private final BodyDef bodyDef;
    private final FixtureDef fixtureDef;

    public TestScreen(final MyGdxGame context) {
        super(context);
        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();

        // Create a circle
        bodyDef.position.set(4.5f, 15);
        bodyDef.gravityScale = 1;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(bodyDef);

        fixtureDef.isSensor = false;
        fixtureDef.restitution = 0.5f;
        fixtureDef.friction = 0.2f;
        fixtureDef.filter.categoryBits = BIT_CIRCLE;
        fixtureDef.filter.maskBits = BIT_GROUND | BIT_BOX;
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(0.5f);
        fixtureDef.shape = circleShape;
        body.createFixture(fixtureDef);
        body.setUserData("The Circle");
        circleShape.dispose(); // Dispose every shape for memory management

        // Create a Sensor
        fixtureDef.isSensor = true;
        fixtureDef.restitution = 0;
        fixtureDef.friction = 0.2f;
        fixtureDef.filter.categoryBits = BIT_CIRCLE;
        fixtureDef.filter.maskBits = BIT_BOX;
        ChainShape chainShape = new ChainShape();
        chainShape.createChain(new float[]{-0.5f, -0.7f, 0.5f, -0.7f});
        fixtureDef.shape = chainShape;
        body.createFixture(fixtureDef);
        chainShape.dispose();

        // Create a Box
        bodyDef.position.set(5.3f, 6);
        bodyDef.gravityScale = 1;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);
        fixtureDef.isSensor = false;
        fixtureDef.restitution = 0.5f;
        fixtureDef.friction = 0.2f;
        fixtureDef.filter.categoryBits = BIT_BOX;
        fixtureDef.filter.maskBits = BIT_GROUND | BIT_CIRCLE;
        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(0.5f, 0.5f);
        fixtureDef.shape = boxShape;
        body.createFixture(fixtureDef);
        body.setUserData("The Box");
        boxShape.dispose(); // Dispose every shape for memory management

        // Create a Platform
        bodyDef.position.set(4.5f, 2);
        bodyDef.gravityScale = 1;
        bodyDef.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(bodyDef);
        fixtureDef.isSensor = false;
        fixtureDef.restitution = 0.5f;
        fixtureDef.friction = 0.2f;
        fixtureDef.filter.categoryBits = BIT_GROUND;
        fixtureDef.filter.maskBits = -1;
        boxShape = new PolygonShape();
        boxShape.setAsBox(4f, 0.5f);
        fixtureDef.shape = boxShape;
        body.createFixture(fixtureDef);
        body.setUserData("The Platform");
        boxShape.dispose(); // Dispose every shape for memory management
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply(true);
        box2DDebugRenderer.render(world, viewport.getCamera().combined);
        //System.out.println(Gdx.graphics.getFramesPerSecond());
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

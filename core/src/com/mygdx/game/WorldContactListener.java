package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;

/*
 * @created 16-01-2022
 * @project YT-Learning
 * @author Nihar
 */
public class WorldContactListener implements ContactListener {
    private static final String TAG = WorldContactListener.class.getSimpleName();

    @Override
    public void beginContact(Contact contact) {
        final Fixture fixtureA = contact.getFixtureA();
        final Fixture fixtureB = contact.getFixtureB();
        Gdx.app.debug(TAG, "Begin:: " + fixtureA.getBody().getUserData() + " " + fixtureA.isSensor());
    }

    @Override
    public void endContact(Contact contact) {
        final Fixture fixtureA = contact.getFixtureA();
        final Fixture fixtureB = contact.getFixtureB();
        Gdx.app.debug(TAG, "End:: " + fixtureA.getBody().getUserData() + " " + fixtureA.isSensor());
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}

package com.mygdx.game.screen;

/*
 * @created 16/01/2022
 * @project YT-Learning
 * @author nihar
 */
public enum ScreenType {
    GAME(TestScreen.class), LOADING(LoadingScreen.class);
    private final Class<? extends AbstractScreen> screenClass;

    ScreenType(Class<? extends AbstractScreen> screenClass) {
        this.screenClass = screenClass;
    }

    public Class<? extends AbstractScreen> getScreenClass() {
        return screenClass;
    }
}

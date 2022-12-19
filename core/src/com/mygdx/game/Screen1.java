package com.mygdx.game;

import com.badlogic.gdx.*;
//import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
//import com.badlogic.gdx.scenes.scene2d.ui.Skin;
//import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class Screen1 implements Screen {

    private MyGdxGame object;
    private SpriteBatch batch;
    private Texture img , bg;
    private Stage stage;
    private Drawable drawable;
    private ImageButton button1;

    Screen1(MyGdxGame x){
        this.object = x;
        stage = new Stage(new ScreenViewport());
        bg = new Texture(Gdx.files.internal("MainPage.png"));
        img = new Texture(Gdx.files.internal("startButton.png"));
        drawable = new TextureRegionDrawable(new TextureRegion(img));
        button1 = new ImageButton(drawable);
        button1.setBounds(430, -10,300, 100);
        stage.addActor(button1);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {


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
    public void render (float delta) {
        ScreenUtils.clear(1, 1, 1, 1);
        object.batch.begin();
        object.batch.draw(bg, 0, 0);
        object.batch.end();
        stage.draw();
        button1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                img.dispose();
                stage.dispose();
                object.setScreen(new Screen2(object));
            }
        });
    }
    @Override
    public void dispose () {
        batch.dispose();
        img.dispose();
    }
}

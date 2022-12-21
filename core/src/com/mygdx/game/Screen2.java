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

import java.util.Iterator;

public class Screen2 implements Screen {

    private MyGdxGame object;
    private SpriteBatch batch;
    private Texture button_image1, button_image2 ,  button_image3  , bg;
    private Stage stage;
    private Drawable drawable_newGame , drawable_loadGame , drawable_exitGame;
    private ImageButton newGame , loadGame , exitGame;




    Screen2(MyGdxGame x){
        this.object = x;
        stage = new Stage(new ScreenViewport());
        bg = new Texture(Gdx.files.internal("secondPage.png"));
        button_image1 = new Texture(Gdx.files.internal("newGame.png"));
        button_image2 = new Texture(Gdx.files.internal("loadGameButton.png"));
        button_image3 = new Texture(Gdx.files.internal("exitGame.png"));
        drawable_newGame = new TextureRegionDrawable(new TextureRegion(button_image1));
        drawable_loadGame = new TextureRegionDrawable(new TextureRegion(button_image2));
        drawable_exitGame = new TextureRegionDrawable(new TextureRegion(button_image3));
        newGame = new ImageButton(drawable_newGame);
        loadGame = new ImageButton(drawable_loadGame);
        exitGame = new ImageButton(drawable_exitGame);
        newGame.setBounds(550, 350,500, 150);
        loadGame.setBounds(550, 190,500, 150);
        exitGame.setBounds(550, 30,500, 150);
        stage.addActor(newGame);
        stage.addActor(loadGame);
        stage.addActor(exitGame);
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
    public void render (final float delta) {
        ScreenUtils.clear(1, 1, 1, 1);
        object.batch.begin();
        object.batch.draw(bg, 0, 0);
        object.batch.end();
        stage.draw();
        exitGame.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.exit(0);
            }
        });
        newGame.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                button_image1.dispose();
                button_image2.dispose();
                button_image3.dispose();
                stage.dispose();
                object.setScreen(new TankSelection(object));
            }
        });
        loadGame.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                button_image1.dispose();
                button_image2.dispose();
                button_image3.dispose();
//                stage.dispose();

                object.setScreen(new Load_game_page(object));
            }
        });

    }

    @Override
    public void dispose () {
        batch.dispose();
        button_image1.dispose();
        button_image2.dispose();
        button_image3.dispose();
        bg.dispose();
        stage.dispose();
    }
}

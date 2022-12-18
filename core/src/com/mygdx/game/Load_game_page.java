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


public class Load_game_page implements Screen {

    private MyGdxGame object;
    private SpriteBatch batch;
    private Texture back_button_Texture, bg;
    private Stage stage;
    private Drawable back_button_Drawable;
    private ImageButton back_button_ImageButton;




    Load_game_page(MyGdxGame x){
        this.object = x;
        stage = new Stage(new ScreenViewport());
        bg = new Texture(Gdx.files.internal("loadGame.png"));
        back_button_Texture = new Texture(Gdx.files.internal("leftButton.png"));
        back_button_Drawable = new TextureRegionDrawable(new TextureRegion(back_button_Texture));
        back_button_ImageButton = new ImageButton(back_button_Drawable);
        back_button_ImageButton.setBounds(800, 10,50, 50);
        stage.addActor(back_button_ImageButton);
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
        back_button_ImageButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                stage.dispose();
                object.setScreen(new Screen2(object));
            }
        });

    }

    @Override
    public void dispose () {
        batch.dispose();
        bg.dispose();
        stage.dispose();
    }
}

package com.mygdx.game;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Game implements Screen{
    private MyGdxGame object;
    private SpriteBatch batch;
    private Texture img;
    private Drawable drawable;;
    float x , y;




    Game(MyGdxGame x){
        this.object= x;
        batch = new SpriteBatch();
//        img = new Texture(Gdx.files.internal(TankSelection.tank_chosen_file_name));
        img = new Texture("leftButton.png");






    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);
        Gdx.gl.glClearColor(0 , 0 , 0 , 0 );
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            x = x+10;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            x = x-10;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){

        }
        batch.begin();
        batch.draw(img , x , y);
        batch.end();
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
    public void dispose(){
        batch.dispose();
    }
}

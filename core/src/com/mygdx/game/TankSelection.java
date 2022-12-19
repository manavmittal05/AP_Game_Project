package com.mygdx.game;

import com.badlogic.gdx.*;
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


public class TankSelection implements Screen {

    private MyGdxGame object;
    private SpriteBatch batch;
    private Texture left_arrow_image, right_arrow_image ,  play_button_image  , bg;
    private Stage stage;
    private Drawable drawable_left_arrow , drawable_right_arrow , drawable_play_button;
    private ImageButton left_button , right_button , play_button;
    private String[] array_of_tank_images = {"tank1withBg.png" ,"tank2withBg.png" , "tank3withBg.png"};
    private int array_of_tank_images_ptr = 99999999;
    static String tank_chosen_file_name ;
    private int tank_chosen_index;
    static Hill loadGame1 ;


    TankSelection(MyGdxGame x){
        this.object = x;
        stage_create();
    }

    private void stage_create(){
        stage = new Stage(new ScreenViewport());
        bg = new Texture(Gdx.files.internal("tank1withBg.png"));
        left_arrow_image = new Texture(Gdx.files.internal("leftButton.png"));
        right_arrow_image = new Texture(Gdx.files.internal("rightButton.png"));
        play_button_image = new Texture(Gdx.files.internal("playGame.png"));
        drawable_left_arrow = new TextureRegionDrawable(new TextureRegion(left_arrow_image));
        drawable_right_arrow = new TextureRegionDrawable(new TextureRegion(right_arrow_image));
        drawable_play_button = new TextureRegionDrawable(new TextureRegion(play_button_image));
        left_button = new ImageButton(drawable_left_arrow);
        right_button = new ImageButton(drawable_right_arrow);
        play_button = new ImageButton(drawable_play_button);
        left_button.setBounds(550, 350,500, 150);
        right_button.setBounds(550, 190,500, 150);
        play_button.setBounds(550, 30,500, 150);
        stage.addActor(left_button);
        stage.addActor(right_button);
        stage.addActor(play_button);
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
        play_button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                tank_chosen_file_name  = array_of_tank_images[tank_chosen_index];
                map_tankwithbg_image_to_tank(tank_chosen_file_name);
                stage.dispose();
                loadGame1 = new Hill(object);
                object.setScreen(loadGame1);
            }
        });
        right_button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                bg.dispose();
                stage.dispose();
                stage_create();
                array_of_tank_images_ptr += 1;
                bg = new Texture(Gdx.files.internal(array_of_tank_images[(array_of_tank_images_ptr)%3]));
                tank_chosen_index= (array_of_tank_images_ptr)%3;
            }
        });
        left_button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                bg.dispose();
                stage.dispose();
                stage_create();
                array_of_tank_images_ptr -= 1;
                bg = new Texture(Gdx.files.internal(array_of_tank_images[array_of_tank_images_ptr%3]));
                tank_chosen_index= (array_of_tank_images_ptr)%3;
            }
        });


    }

    @Override
    public void dispose () {
        left_arrow_image.dispose();
        right_arrow_image.dispose();
        play_button_image.dispose();
        bg.dispose();
        batch.dispose();
    }

    private void map_tankwithbg_image_to_tank(String filename){
        if(filename.equals("tank1withBg.png")){
            tank_chosen_file_name = "tank1.png";
        }
        else if(filename.equals("tank2withBg.png")){
            tank_chosen_file_name = "tank2.png";
        }
        else if(filename.equals("tank3withBg.png")){
            tank_chosen_file_name = "tank3.png";
        }
    }
}

package com.mygdx.game;
import java.io.*;

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

import java.io.FileOutputStream;


public class Hill implements Screen {

    private MyGdxGame object;
    private SpriteBatch batch;
    private Texture img, bg;
    private Stage stage;
    private Drawable drawable;
    private Texture tank1,tank2;
    private Texture Ingame_menu_button_Texture, resumeButton_Texture, In_game_save_state_button_Texture, In_game_back_to_main_menu_Texture;
    private Drawable Ingame_menu_button_Drawable, resumeButton_Drawable, In_game_save_state_button_Drawable, In_game_back_to_main_menu_Drawable;
    private ImageButton Ingame_menu_button_ImageButton, resumeButton_ImageButton, In_game_save_state_button_ImageButton, In_game_back_to_main_menu_ImageButton;
    private int menu_page_show_flag = 0;
    private int tank1_position_x= 0 , tank1_position_y = -94;
    private int tank2_position_x= 1100 , tank2_position_y =-94;
    private TextureRegionDrawable tank1_drawable;
    private ImageButton tank1_image_button;
    private int [] terrain_y_coordinate_mapping = new int[1500];
    private TextureRegionDrawable tank2_drawable;
    private ImageButton tank2_image_button;


    Hill(MyGdxGame x) {
        this.object = x;
        stage = new Stage(new ScreenViewport());
        bg = new Texture(Gdx.files.internal("terrain.png"));
        img = new Texture(Gdx.files.internal(TankSelection.tank_chosen_file_name));
        drawable = new TextureRegionDrawable(new TextureRegion(img));
        set_values_of_terrain_y_coordinate_mapping();
        tank1 = new Texture(TankSelection.tank_chosen_file_name);
        tank1_drawable =  new TextureRegionDrawable(new TextureRegion(tank1));
        tank1_image_button = new ImageButton(tank1_drawable);
        tank1_image_button.setBounds(tank1_position_x , tank1_position_y, 80 , 90);
        stage.addActor(tank1_image_button);
        Gdx.input.setInputProcessor(stage);

        String choice_of_opponent_tank = TankSelection.tank_chosen_file_name.substring(0, 5) + "flip.png";
        tank2 = new Texture(choice_of_opponent_tank);
        tank2_drawable =  new TextureRegionDrawable(new TextureRegion(tank2));
        tank2_image_button = new ImageButton(tank2_drawable);
        tank2_image_button.setBounds(tank2_position_x , tank2_position_y, 80 , 90);
        stage.addActor(tank2_image_button);
        Gdx.input.setInputProcessor(stage);

        Ingame_menu_button_Texture = new Texture(Gdx.files.internal("inGameMenuButton.png"));
        Ingame_menu_button_Drawable = new TextureRegionDrawable(new TextureRegion(Ingame_menu_button_Texture));
        Ingame_menu_button_ImageButton = new ImageButton(Ingame_menu_button_Drawable);
        Ingame_menu_button_ImageButton.setBounds(450, 350, 80, 50);
        stage.addActor(Ingame_menu_button_ImageButton);
        Gdx.input.setInputProcessor(stage);

        resumeButton_Texture = new Texture(Gdx.files.internal("inGameResumeButton.png"));
        resumeButton_Drawable = new TextureRegionDrawable(new TextureRegion(resumeButton_Texture));
        resumeButton_ImageButton = new ImageButton(resumeButton_Drawable);
        stage.addActor(resumeButton_ImageButton);
        Gdx.input.setInputProcessor(stage);

        In_game_save_state_button_Texture = new Texture(Gdx.files.internal("inGameSaveStateButton.png"));
        In_game_save_state_button_Drawable = new TextureRegionDrawable(new TextureRegion(In_game_save_state_button_Texture));
        In_game_save_state_button_ImageButton = new ImageButton(In_game_save_state_button_Drawable);
        stage.addActor(In_game_save_state_button_ImageButton);
        Gdx.input.setInputProcessor(stage);

        In_game_back_to_main_menu_Texture = new Texture(Gdx.files.internal("inGameExitButton.png"));
        In_game_back_to_main_menu_Drawable = new TextureRegionDrawable(new TextureRegion(In_game_back_to_main_menu_Texture));
        In_game_back_to_main_menu_ImageButton = new ImageButton(In_game_back_to_main_menu_Drawable);
        stage.addActor(In_game_back_to_main_menu_ImageButton);
        Gdx.input.setInputProcessor(stage);




    }

    private void set_values_of_terrain_y_coordinate_mapping(){
        for(int i = 0 ; i<1500 ; i++){
           terrain_y_coordinate_mapping[i] = (int)Math.sqrt((2112*2112) - ((i-555)*(i-555))) - 2132 ;
        }
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
    public void render(float delta){
        ScreenUtils.clear(1, 1, 1, 1);
        object.batch.begin();
        object.batch.draw(bg, 0, 0);
        tank1_image_button.setBounds(tank1_position_x , tank1_position_y , 60 , 430);
        tank2_image_button.setBounds(tank2_position_x , tank2_position_y , 60 , 430);


        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            tank1_position_x = tank1_position_x+1;
            if(tank1_position_x > 1100){
                tank1_position_x = 1100;
            }
            tank1_position_y = terrain_y_coordinate_mapping[tank1_position_x];
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            tank1_position_x= tank1_position_x-1;
            if(tank1_position_x < 0){
                tank1_position_x = 0;
            }
            tank1_position_y = terrain_y_coordinate_mapping[tank1_position_x];
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            tank2_position_x = tank2_position_x+1;
            if(tank2_position_x > 1100){
                tank2_position_x = 1100;
            }
            tank2_position_y = terrain_y_coordinate_mapping[tank2_position_x];
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            tank2_position_x= tank2_position_x-1;
            if(tank2_position_x < 0){
                tank2_position_x = 0;
            }
            tank2_position_y = terrain_y_coordinate_mapping[tank2_position_x];
        }





        if (menu_page_show_flag == 1) {
            resumeButton_ImageButton.setBounds(50, 290, 1000, 300);
            In_game_save_state_button_ImageButton.setBounds(50, 150, 1000, 300);
            In_game_back_to_main_menu_ImageButton.setBounds(50, 10, 1000, 300);
            Ingame_menu_button_ImageButton.setBounds(0, 0, 0, 0);

        } else {
            resumeButton_ImageButton.setBounds(0, 0, 0, 0);
            In_game_save_state_button_ImageButton.setBounds(0, 0, 0, 0);
            In_game_back_to_main_menu_ImageButton.setBounds(0, 0, 0, 0);
            Ingame_menu_button_ImageButton.setBounds(20, 450, 50, 50);
        }
        object.batch.end();
        stage.draw();

        Ingame_menu_button_ImageButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menu_page_show_flag = 1;
            }
        });
        resumeButton_ImageButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menu_page_show_flag = 0;
            }
        });
        In_game_save_state_button_ImageButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                try {
                    FileOutputStream fo = new FileOutputStream("/home/manav/Desktop/savestate.txt");
                    ObjectOutputStream out = new ObjectOutputStream(fo);
                    out.writeObject(TankSelection.loadGame1);
                    out.close();
                    fo.close();
                }
                catch (Exception e){
                    System.out.println("Exception");
                }
                finally{
                    System.out.println("Successfully saved!");
                    menu_page_show_flag = 0;
                }
            }
        });
        In_game_back_to_main_menu_ImageButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                menu_page_show_flag = 0;
                stage.dispose();
                object.setScreen(new Screen2(object));
            }
        });
    }

        @Override
        public void dispose() {
            batch.dispose();
            img.dispose();
        }


    }


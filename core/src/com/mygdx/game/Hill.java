package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.bullet.collision._btMprSimplex_t;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

abstract class Tank{
    int image_index;
    int fuel;
}

interface _Tank{
    void getTankInfo(String x);
}

class TankAngle{
    int getAngleTheta(int radian){
        return 3*radian;
    }
    int getAngleRadian(int theta){
        return (100*theta)+1;
    }
}

class TankVelocity{
    double getVelocityInPixelsPerSec(int velocity){
        return (1800-(velocity*20)/400);
    }
}

class getCoordinates{
    int getcoordinates(String tank) {
        if (tank.equals("tank1")) {
            return 28;
        }
        else{
            return 16;
        }
    }
}

class Terrain{
    private int index_number=1;
}

//SINGLETON
class Tank1 extends Tank implements _Tank {
    private float health;
    private float speed ;
    private Texture texture;
    private Sprite sprite;
    private Rectangle button;
    static private Rectangle left;
    static private Rectangle right;
    static private Rectangle top;
    private static Tank1 x = null;

    private Tank1() {
    }

    static Tank1 getInstance() {
        if (x == null) {
            return new Tank1();
        }
        return x;
    }
    @Override
    public void getTankInfo(String tank1) {
        System.out.println(x.image_index);
        System.out.println(x.fuel);
    }
}

class Tank2 extends Tank implements _Tank{
    private static Tank2 x = null;
    private float health;
    private float speed ;
    private Texture texture;
    private Sprite sprite;
    private Rectangle button;
    static private Rectangle left;
    static private Rectangle right;
    static private Rectangle top;
    private Tank2(){}
    static Tank2 getInstance(){
        if(x==null){
            return new Tank2();
        }
        return x;
    }
    @Override
    public void getTankInfo(String tank2) {
        System.out.println(x.image_index);
        System.out.println(x.fuel);
    }
}

class Tank3 extends Tank implements _Tank{
    private static Tank3 x = null;
    private float health;
    private float speed ;
    private Texture texture;
    private Sprite sprite;
    private Rectangle button;
    static private Rectangle left;
    static private Rectangle right;
    static private Rectangle top;
    private Tank3(){}
    static Tank3 getInstance(){
        if(x==null){
            return new Tank3();
        }
        return x;
    }
    @Override
    public void getTankInfo(String tank3) {
        System.out.println(x.image_index);
        System.out.println(x.fuel);
    }
}

class SavedStates{
    static Hill savedState1;
    static Hill savedState2;
    static Hill savedState3;
    static void setSavedState(Hill x , int num){
        if(num==1) {
            savedState1 = x;
        }
        if(num==2) {
            savedState2 = x;
        }
        if(num==3) {
            savedState3 = x;
        }
    }
}


public class Hill implements Screen , Serializable {
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
    private int [] terrain_y_coordinate_mapping = new int[3000];
    private TextureRegionDrawable tank2_drawable;
    private ImageButton tank2_image_button;
    private int airdrop_flag=1;
    private int timeForAirdrop= 0 ;
    private Texture airdropImage;
    private Texture airDropSpawnedImage;
    private int airDropSpawnedImageFlag=1 , airDropSpawnedImageTime=1;
    private int trajectoryArray[] = new int[3000];
    private Texture Cannon1, Cannon2;
    private Drawable Cannon1_drawable , Cannon2_drawable;
    private ImageButton Cannon1_ImageButton, Cannon2_ImageButton;
    private int cannon1_position_x;
    private int cannon1_width , cannon1_height;
    private double tank1_angle_of_fire=0.9 , tank1_velocity_of_fire=100;
    private int cannon2_position_x;
    private int cannon2_width , cannon2_height;
    private double tank2_angle_of_fire=0.9 , tank2_velocity_of_fire=100;
    private int tankTurnFlag=1;
    private Texture player1turn , player2turn ;
    int health_tank1=100 , health_tank2=100;
    private Texture full_health_image , two_third_health_image ,  one_third_health_image , player1_has_won_image , critically_low_health_image , player2_has_won_image;
    private Tank1 tankone = Tank1.getInstance();
    private int serialisationTankExistenceFlag = 1 ;
    final private int tankWidth = 60 ;
    final private int tankHeight = 430;
    private int savedGameNumber=0;

    //attributes
    TankVelocity p = new TankVelocity();
    getCoordinates m = new getCoordinates();

    void setAttributes(MyGdxGame x){
        this.object = x;
        stage = new Stage(new ScreenViewport());
        bg = new Texture(Gdx.files.internal("terrain.png"));
        airdropImage = new Texture(Gdx.files.internal("airdrop.png"));
        airDropSpawnedImage = new Texture(Gdx.files.internal("airDropSpawnedImage.png"));
        img = new Texture(Gdx.files.internal(TankSelection.tank_chosen_file_name));
        drawable = new TextureRegionDrawable(new TextureRegion(img));
        set_values_of_terrain_y_coordinate_mapping();
        tank1 = new Texture(TankSelection.tank_chosen_file_name);
        tank1_drawable =  new TextureRegionDrawable(new TextureRegion(tank1));
        tank1_image_button = new ImageButton(tank1_drawable);
        tank1_image_button.setBounds(tank1_position_x , tank1_position_y, 80 , 90);
        Cannon1 = new Texture("missile.png");
        Cannon1_drawable =  new TextureRegionDrawable(new TextureRegion(Cannon1));
        Cannon1_ImageButton = new ImageButton(Cannon1_drawable);
        Cannon1_ImageButton.setBounds(tank1_position_x , terrain_y_coordinate_mapping[tank1_position_x], 0 , 0);
        cannon1_position_x=0;
        stage.addActor(Cannon1_ImageButton);
        Cannon2 = new Texture("missile.png");
        Cannon2_drawable =  new TextureRegionDrawable(new TextureRegion(Cannon2));
        Cannon2_ImageButton = new ImageButton(Cannon2_drawable);
        Cannon2_ImageButton.setBounds(tank2_position_x , terrain_y_coordinate_mapping[tank2_position_x], 0 , 0);
        cannon2_position_x=0;
        stage.addActor(Cannon2_ImageButton);
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

        player1turn = new Texture(Gdx.files.internal("player1turn.png"));
        player2turn = new Texture(Gdx.files.internal("player2turn.png"));
        full_health_image = new Texture(Gdx.files.internal("fullhealth.png"));
        two_third_health_image = new Texture(Gdx.files.internal("twothirdhealth.png"));
        one_third_health_image = new Texture(Gdx.files.internal("onethirdhealth.png"));
        player1_has_won_image= new Texture(Gdx.files.internal("player1_Winner.png"));
        player2_has_won_image= new Texture(Gdx.files.internal("player2_Winner.png"));
        critically_low_health_image= new Texture(Gdx.files.internal("criticallylowhealthimage.png"));

        //attributes
        p.getVelocityInPixelsPerSec(5);
        m.getcoordinates("tank2");
    }

    Hill(MyGdxGame x) {
        setAttributes(x);

    }

    private void set_values_of_trajectory_array(String tankFiredFrom , double angle , double velocity){
        for(int i = 0 ; i<3000 ; i++){
            trajectoryArray[i] = ((int) ((i * Math.tan(angle)) - ((0.5 * 10 * i * i) / ((velocity * velocity * Math.cos(angle) * Math.cos(angle))))));
        }
//        else if(tankFiredFrom.equals("tank2")) {
//            for (int i = 0; i < 1500; i++) {
//                trajectoryArray[i] = (int) (((i-tank2_position_x) * Math.tan(angle)) - ((0.5 * 10 * (i - tank2_position_x) * (i - tank2_position_x)) / ((velocity * velocity * Math.cos(angle) * Math.cos(angle)))) + tank2_position_y);
//            }
//        }

    }
    private void set_values_of_terrain_y_coordinate_mapping(){
        for(int i = 0 ; i<3000; i++){
           terrain_y_coordinate_mapping[i] = (int)Math.sqrt((2112*2112) - ((i-555)*(i-555))) - 2136 ;
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
        tank1_image_button.setBounds(tank1_position_x , tank1_position_y , tankWidth , tankHeight);
        tank2_image_button.setBounds(tank2_position_x , tank2_position_y , tankWidth , tankHeight);
        Cannon1_ImageButton.setBounds(cannon1_position_x + tank1_position_x, trajectoryArray[cannon1_position_x] + tank1_position_y + 156, cannon1_width , cannon1_height);
        Cannon2_ImageButton.setBounds( 2*tank2_position_x- (cannon2_position_x + tank2_position_x), trajectoryArray[cannon2_position_x] + tank2_position_y + 156, cannon2_width , cannon2_height);
        if(timeForAirdrop%400==0 && timeForAirdrop>=1 && airdrop_flag==1){
            airDropSpawnedImageFlag=0;
            airdrop_flag=0;
        }
        if(airDropSpawnedImageTime>=100){
            airDropSpawnedImageFlag=1;
            airDropSpawnedImageTime=0;
        }
        if(airdrop_flag==0){
            object.batch.draw(airdropImage , 570 , 170);//terrain_y_coordinate_mapping[550]);
        }
        if(airDropSpawnedImageFlag==0){
            ScreenUtils.clear(0, 0, 0 , 0);
            tank1_image_button.setBounds(tank1_position_x , tank1_position_y , 0, 0);
            tank2_image_button.setBounds(tank2_position_x , tank2_position_y , 0 , 0);
            object.batch.draw(airDropSpawnedImage, 270, 60);
            airDropSpawnedImageTime+=2;
        }
        if(airdrop_flag==0 && (tank1_position_y==terrain_y_coordinate_mapping[500])){
            health_tank2=-1;
        }
        if(airdrop_flag==0 && tank2_position_y==terrain_y_coordinate_mapping[500]){
            health_tank1=-1;
        }

        if(tankTurnFlag==1){
            object.batch.draw(player1turn , 495 , 420);
        }
        if(tankTurnFlag==2){
            object.batch.draw(player2turn , 495 , 420);
        }
        if(tankTurnFlag==2){
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                tank2_position_x = tank2_position_x + 1;
                if (tank2_position_x > 1100) {
                    tank2_position_x = 1100;
                }
                tank2_position_y = terrain_y_coordinate_mapping[tank2_position_x];
                timeForAirdrop += 2;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                tank2_position_x = tank2_position_x - 1;
                if (tank2_position_x < 0) {
                    tank2_position_x = 0;
                }
                tank2_position_y = terrain_y_coordinate_mapping[tank2_position_x];
                timeForAirdrop += 2;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.X)) {
                tankTurnFlag=1;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                cannon2_width = 60;
                cannon2_height = 90;
                set_values_of_trajectory_array("tank1", tank2_angle_of_fire, tank2_velocity_of_fire);
                cannon2_position_x += 20;
            if (trajectoryArray[cannon2_position_x] + tank2_position_y <= terrain_y_coordinate_mapping[cannon2_position_x + tank2_position_x]) {
                    cannon2_width = 0;
                    cannon2_height = 0;
                    double recoil=0;
                    recoil = tank1_position_x-cannon2_position_x;
                    if(recoil>=100|| recoil<=-100) {
                        recoil = 0;
                    }
                    cannon2_position_x = tank2_position_x;
                    System.out.println("RECOIL: "+recoil);
                    if(recoil<0){
                        recoil=-1*recoil;
                    }
                    if(recoil !=0) {
                        health_tank1 -= 10;
                    }
                    if(recoil >= 40){
                        health_tank1 -= 20;
                    }
                    System.out.println(health_tank1);
                    tank1_position_x +=recoil/9;
                    tank1_position_y = terrain_y_coordinate_mapping[tank1_position_x];
                    tankTurnFlag=1;
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
                if(tank2_velocity_of_fire<=100) {
                    tank2_velocity_of_fire += 1;
                }
                System.out.println(tank2_velocity_of_fire);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.E)) {
                if(tank2_velocity_of_fire>=1) {
                    tank2_velocity_of_fire -= 1;
                }
                System.out.println(tank2_velocity_of_fire);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                cannon2_position_x = tank2_position_x;
                if (tank2_angle_of_fire >= 0.1) {
                    tank2_angle_of_fire -= 0.1;
                    System.out.println(tank2_angle_of_fire);
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                cannon2_position_x = tank1_position_x;
                if (tank2_angle_of_fire <= 1.5) {
                    tank1_angle_of_fire += 0.1;
                    System.out.println(tank2_angle_of_fire);
                }
            }
            if(serialisationTankExistenceFlag==0){
//                tank1_image_button.setBounds(tank1_position_x , tank1_position_y , 60 , 90);

            }
        }
        //CONTROLS
        if(tankTurnFlag==1) {
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                cannon1_width = 60;
                cannon1_height = 90;
                set_values_of_trajectory_array("tank1", tank1_angle_of_fire, tank1_velocity_of_fire);
                if(tank1_angle_of_fire>=1.2) {
                    cannon1_position_x += 5;
                }
                else{
                    cannon1_position_x+=20;
                }
                if (trajectoryArray[cannon1_position_x] + tank1_position_y <= terrain_y_coordinate_mapping[cannon1_position_x + tank1_position_x]) {
                    cannon1_width = 0;
                    cannon1_height = 0;
                    double recoil=0;
                    recoil = tank2_position_x-cannon1_position_x;
                    if(recoil>=100|| recoil<=-100) {
                        recoil = 0;
                    }
                    cannon1_position_x = tank1_position_x;
                    System.out.println("RECOIL: "+recoil);
                    if(recoil<0){
                        recoil=-1*recoil;
                    }
                    if(recoil !=0) {
                        health_tank2 -= 10;
                    }
                    if(recoil >= 40){
                        health_tank2 -= 20;
                    }
                    System.out.println(health_tank2);
                    if(recoil<=0){
                        tank2_position_x -=recoil/5;
                    }
                    else {
                        tank2_position_x += recoil / 2;
                    }
                    tank2_position_y = terrain_y_coordinate_mapping[tank2_position_x];
                    tankTurnFlag=2;
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                cannon1_position_x = tank1_position_x;
                if (tank1_angle_of_fire >= 0.1) {
                    tank1_angle_of_fire -= 0.1;
                    System.out.println(tank1_angle_of_fire);
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                cannon1_position_x = tank1_position_x;
                if (tank1_angle_of_fire <= 1.5) {
                    tank1_angle_of_fire += 0.1;
                    System.out.println(tank1_angle_of_fire);
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.P)) {
                if(tank1_velocity_of_fire<=100) {
                    tank1_velocity_of_fire += 1;
                }
                System.out.println(tank1_velocity_of_fire);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.O)) {
                if(tank1_velocity_of_fire>=1) {
                    tank1_velocity_of_fire -= 1;
                }
                System.out.println(tank1_velocity_of_fire);
            }

            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                tank1_position_x = tank1_position_x+1;
                if(tank1_position_x > 1100){
                    tank1_position_x = 1100;
                }
                tank1_position_y = terrain_y_coordinate_mapping[tank1_position_x];
                timeForAirdrop+=2;
            }

            if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                tank1_position_x= tank1_position_x-1;
                if(tank1_position_x < 0){
                    tank1_position_x = 0;
                }
                tank1_position_y = terrain_y_coordinate_mapping[tank1_position_x];
                timeForAirdrop+=2;
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.NUM_1)){
            try {
                FileOutputStream fo = new FileOutputStream("/Users/utkarsh/Desktop/serialisationFolder/savedstate1.txt");
                ObjectOutputStream out = new ObjectOutputStream(fo);
                out.writeObject(TankSelection.loadGame1);
                out.close();
                fo.close();
                System.out.println("Successfully saved!");
                menu_page_show_flag = 0;
            }
            catch (Exception e){

            }
            finally{
                SavedStates.setSavedState(this , 1);
                serialisationTankExistenceFlag=0;
                setAttributes(object);
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_2)){
            try {
                FileOutputStream fo = new FileOutputStream("/Users/utkarsh/Desktop/serialisationFolder/savedstate2.txt");
                ObjectOutputStream out = new ObjectOutputStream(fo);
                out.writeObject(TankSelection.loadGame1);
                out.close();
                fo.close();
                System.out.println("Successfully saved!");
                menu_page_show_flag = 0;
            }
            catch (Exception e){

            }
            finally{
                SavedStates.setSavedState(this, 2);
                serialisationTankExistenceFlag=0;
                setAttributes(object);
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_3)){
            try {
                FileOutputStream fo = new FileOutputStream("/Users/utkarsh/Desktop/serialisationFolder/savedstate3.txt");
                ObjectOutputStream out = new ObjectOutputStream(fo);
                out.writeObject(TankSelection.loadGame1);
                out.close();
                fo.close();
                System.out.println("Successfully saved!");
                menu_page_show_flag = 0;
            }
            catch (Exception e){

            }
            finally{
                SavedStates.setSavedState(this , 3);
                serialisationTankExistenceFlag=0;
                setAttributes(object);
            }
        }
        //HEALTH

        if(health_tank1>90 && health_tank1<=100){
            object.batch.draw(full_health_image , 0 , 0);
        }
        else if(health_tank1>60 && health_tank1<=90){
            object.batch.draw(two_third_health_image , 0, 0);
        }
        else if(health_tank1>30 && health_tank1<=60){
            object.batch.draw(one_third_health_image , 0, 0);
        }
        else if(health_tank1>0 && health_tank1<=30){
            object.batch.draw(critically_low_health_image , 0, 0);
        }
        else if(health_tank1<=0){
            ScreenUtils.clear(0, 0, 0 , 0);
            tank1_image_button.setBounds(tank1_position_x , tank1_position_y , 0, 0);
            tank2_image_button.setBounds(tank2_position_x , tank2_position_y , 0 , 0);
            object.batch.draw(player2_has_won_image , 50 , 0);
        }
        if(health_tank2>90 && health_tank2<=100){
            object.batch.draw(full_health_image , 1015 , 0);
        }
        else if(health_tank2>60 && health_tank2<=90){
            object.batch.draw(two_third_health_image , 1015, 0);
        }
        else if(health_tank2>30 && health_tank2<=60){
            object.batch.draw(one_third_health_image , 1015, 0);
        }
        else if(health_tank2>0 && health_tank2<=30){
            object.batch.draw(critically_low_health_image , 1015, 0);
        }
        else if(health_tank2<=0){
            ScreenUtils.clear(0, 0, 0 , 0);
            tank1_image_button.setBounds(tank1_position_x , tank1_position_y , 0, 0);
            tank2_image_button.setBounds(tank2_position_x , tank2_position_y , 0 , 0);
            object.batch.draw(player1_has_won_image , 50 , 0);
        }
        //MENU
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
                    FileOutputStream fo = new FileOutputStream("/Users/utkarsh/Desktop/serialisationFolder/savedstate1.txt");
                    ObjectOutputStream out = new ObjectOutputStream(fo);
                    out.writeObject(TankSelection.loadGame1);
                    out.close();
                    fo.close();
                    System.out.println("Successfully saved!");
                    menu_page_show_flag = 0;
                }
                catch (Exception e){}
                finally{

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


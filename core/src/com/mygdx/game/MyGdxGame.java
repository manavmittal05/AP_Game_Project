package com.mygdx.game;

//import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.InputProcessor;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
//import com.badlogic.gdx.scenes.scene2d.ui.Skin;
//import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
//import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
//import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
//import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
//import com.badlogic.gdx.utils.ScreenUtils;
//import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class MyGdxGame extends Game {
	SpriteBatch batch;
	private Music backgroundMusic;


	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new Screen1(this));
		Music music=Gdx.audio.newMusic(Gdx.files.internal("backgroundsound1.mp3"));
		music.play();
		music.setVolume(0.1f);
		music.setLooping(true);


	}
	@Override
	public void render () {
		super.render();
	}


	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
	}
}

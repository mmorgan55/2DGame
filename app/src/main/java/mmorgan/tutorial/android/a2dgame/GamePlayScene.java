package mmorgan.tutorial.android.a2dgame;

import android.graphics.Canvas;
import android.view.MotionEvent;

public class GamePlayScene implements Scene {
  private SceneManager manager;

  @Override
  public void update() {

  }

  @Override
  public void draw(Canvas canvas) {

  }

  @Override
  public void terminate() {
    SceneManager.ACTIVE_SCENE = 0;
  }

  @Override
  public void receiveTouch(MotionEvent event) {

  }
}

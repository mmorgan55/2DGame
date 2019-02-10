package mmorgan.tutorial.android.a2dgame;

import android.graphics.Canvas;

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
}

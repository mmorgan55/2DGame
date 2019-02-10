package mmorgan.tutorial.android.a2dgame;

import android.graphics.Canvas;

public interface Scene {
  void update();
  void draw(Canvas canvas);
  void terminate();
}

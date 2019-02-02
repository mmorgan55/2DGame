package mmorgan.tutorial.android.a2dgame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Obstacle implements GameObject {

  private Rect rectangle;
  private Rect rectangle2;
  private int color;
  private int startX;
  private int startY;
  private int playerGap;

  public Obstacle(int rectHeight, int color, int startX, int startY, int playerGap) {
    this.color = color;
    this.startX = startX;
    this.startY = startY;
    this.playerGap = playerGap;
    rectangle = new Rect(0, startY, startX, startY + rectHeight);
    rectangle2 = new Rect(startX + playerGap, startY, Constants.SCREEN_WIDTH, startY + rectHeight);
  }

  @Override
  public void draw(Canvas canvas) {
    Paint paint = new Paint();
    paint.setColor(color);
    canvas.drawRect(rectangle, paint);
    canvas.drawRect(rectangle2, paint);
  }

  @Override
  public void update() {

  }

  public boolean playerCollide(Player player) {
    return Rect.intersects(rectangle, player.getRectangle()) || Rect
        .intersects(rectangle2, player.getRectangle());
  }

  public void incrementY(float y) {
    rectangle.top += y;
    rectangle.bottom += y;
    rectangle2.top += y;
    rectangle2.bottom += y;

  }

  public Rect getRectangle() {
    return rectangle;
  }
}

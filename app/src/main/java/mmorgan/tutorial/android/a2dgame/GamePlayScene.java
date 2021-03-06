package mmorgan.tutorial.android.a2dgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

public class GamePlayScene implements Scene {

  private Rect r = new Rect();
  private Player player;
  private Point playerPoint;
  private ObstacleManager obstacleManager;
  private boolean movingPlayer;
  private boolean gameOver;
  private long gameOverTime;

  public GamePlayScene() {
    player = new Player(new Rect(100, 100, 200, 200), Color.rgb(0, 0, 255));
    playerPoint = new Point(Constants.SCREEN_WIDTH / 2, 3 * Constants.SCREEN_HEIGHT / 4);
    player.update(playerPoint);
    obstacleManager = new ObstacleManager(200, 350, 75, Color.BLACK);
  }

  @Override
  public void update() {
    if (!gameOver) {
      player.update(playerPoint);
      obstacleManager.update();
      if (obstacleManager.playerCollide(player)) {
        gameOver = true;
        gameOverTime = System.currentTimeMillis();
      }
    }
  }

  @Override
  public void draw(Canvas canvas) {
    canvas.drawColor(Color.WHITE);
    player.draw(canvas);
    obstacleManager.draw(canvas);

    if (gameOver) {
      Paint paint = new Paint();
      paint.setTextSize(100);
      paint.setColor(Color.MAGENTA);
      drawCenterText(canvas, paint, "Game Over");
    }
  }

  @Override
  public void terminate() {
    SceneManager.ACTIVE_SCENE = 0;
  }

  @Override
  public void receiveTouch(MotionEvent event) {
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        if (!gameOver && player.getRectangle().contains((int) event.getX(), (int) event.getY())) {
          movingPlayer = true;
        }
        if (gameOver && System.currentTimeMillis() - gameOverTime >= 2000) {
          reset();
          gameOver = false;
        }
        break;
      case MotionEvent.ACTION_MOVE:
        if (!gameOver && movingPlayer) {
          playerPoint.set((int) event.getX(), (int) event.getY());
        }
        break;
      case MotionEvent.ACTION_UP:
        movingPlayer = false;
        break;
    }
  }

  private void drawCenterText(Canvas canvas, Paint paint, String text) {
    paint.setTextAlign(Paint.Align.LEFT);
    canvas.getClipBounds(r);
    int cHeight = r.height();
    int cWidth = r.width();
    paint.getTextBounds(text, 0, text.length(), r);
    float x = cWidth / 2f - r.width() / 2f - r.left;
    float y = cHeight / 2f + r.height() / 2f - r.bottom;
    canvas.drawText(text, x, y, paint);
  }


  public void reset() {
    playerPoint = new Point(Constants.SCREEN_WIDTH / 2, 3 * Constants.SCREEN_HEIGHT / 4);
    player.update(playerPoint);
    obstacleManager = new ObstacleManager(200, 350, 75, Color.BLACK);
    movingPlayer = false;
  }
}

package mmorgan.tutorial.android.a2dgame;

import android.graphics.Canvas;
import java.util.ArrayList;

public class ObstacleManager {

  private ArrayList<Obstacle> obstacles;
  private int playerGap;
  private int obstacleGap;
  private int obstacleHeight;
  private int color;

  private long startTime;

  public ObstacleManager(int playerGap, int obstacleGap, int obstacleHeight, int color) {
    this.playerGap = playerGap;
    this.obstacleGap = obstacleGap;
    this.obstacleHeight = obstacleHeight;
    this.color = color;

    startTime = System.currentTimeMillis();
    obstacles = new ArrayList<>();

    populateObstacles();
  }

  private void populateObstacles() {
    int currentY = -5 * Constants.SCREEN_HEIGHT / 4;
    while (currentY < 0) {
      int startX = (int) (Math.random() * (Constants.SCREEN_WIDTH - playerGap));
      obstacles.add(new Obstacle(obstacleHeight, color, startX, currentY, playerGap));
      currentY += obstacleHeight + obstacleGap;
    }
  }

  public void update() {
    int elapsedTime = (int) (System.currentTimeMillis() - startTime);
    startTime = System.currentTimeMillis();
    float speed = Constants.SCREEN_HEIGHT / 10000.0f;
    for (Obstacle ob : obstacles) {
      ob.incrementY(speed * elapsedTime);
    }

    if (obstacles.get(obstacles.size() - 1).getRectangle().top >= Constants.SCREEN_HEIGHT) {
      int startX = (int) (Math.random() * Constants.SCREEN_WIDTH - playerGap);
      obstacles.add(0, new Obstacle(obstacleHeight, color, startX,
          obstacles.get(0).getRectangle().top - obstacleHeight - obstacleGap, playerGap));
      obstacles.remove(obstacles.size() - 1);
    }

  }

  public void draw(Canvas canvas) {
    for (Obstacle ob : obstacles) {
      ob.draw(canvas);
    }
  }
}

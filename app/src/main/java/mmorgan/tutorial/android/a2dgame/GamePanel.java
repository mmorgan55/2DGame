package mmorgan.tutorial.android.a2dgame;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

  private MainThread thread;
  private SceneManager manager;


  public GamePanel(Context context) {
    super(context);
    getHolder().addCallback(this);
    thread = new MainThread(getHolder(), this);
    manager = new SceneManager();

    setFocusable(true);
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    thread = new MainThread(getHolder(), this);

    thread.setRunning(true);
    thread.start();
  }

  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

  }

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
    while (true) {
      try {
        thread.setRunning(false);
        thread.join();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    manager.recieveTouch(event);
    return true;
  }

  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);

    manager.draw(canvas);
  }

  public void update() {
    manager.update();
  }


}

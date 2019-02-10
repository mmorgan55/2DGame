package mmorgan.tutorial.android.a2dgame;

import java.util.ArrayList;

public class SceneManager {
  private ArrayList<Scene> scenes = new ArrayList<>();
  public static int ACTIVE_SCENE;

  public SceneManager() {
    ACTIVE_SCENE = 0;
  }
  
}

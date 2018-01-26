package yapps.dodgeit;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by yonty on 26/05/2017.
 */

public interface Scene {
    public void update();

    public void draw(Canvas canvas);

    public void terminate();

    public void receiveTouch(MotionEvent event);
}

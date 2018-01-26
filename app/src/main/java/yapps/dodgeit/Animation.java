package yapps.dodgeit;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by yonty on 26/01/2018.
 */

public class Animation {
    private Bitmap[] frames;
    private int frameIdx;

    private boolean isPlaying = false;

    public boolean isPlaying() {
        return isPlaying;
    }

    public void play() {
        isPlaying = true;
        frameIdx = 0;

    }

    public void stop() {
        isPlaying = false;
    }

    private float frameTime;
    private long lastFrame;


    public Animation(Bitmap[] frames, float animTime) {
        this.frames = frames;
        frameIdx = 0;
        frameTime = animTime / frames.length;
        lastFrame = System.currentTimeMillis();
    }

    public void draw(Canvas canvas, Rect destination) {
        if (!isPlaying) {
            return;
        }

        scaleRect(destination);
        canvas.drawBitmap(frames[frameIdx], null, destination, new Paint());

    }

    private void scaleRect(Rect rect) {
        float whRatio = (float) (frames[frameIdx].getWidth()) / frames[frameIdx].getHeight();
        if (rect.width() > rect.height())
            rect.left = rect.left - (int) (rect.width() * whRatio);
        else
            rect.top = rect.top - (int) (rect.height() * (1 / whRatio));
    }

    public void update() {
        if (!isPlaying) {
            return;
        }
        if (System.currentTimeMillis() - lastFrame > frameTime * 1000) {
            frameIdx++;
            frameIdx = frameIdx >= frames.length ? 0 : frameIdx;
            lastFrame = System.currentTimeMillis();
        }
    }
}

package yapps.dodgeit;


import android.graphics.Canvas;
import android.graphics.Rect;

public class AnimationManager {
    private Animation[] animations;
    private int animationIndex = 0;


    public AnimationManager(Animation[] animations) {
        this.animations = animations;
    }

    public void playAnim(int animIdx) {
        for (int i = 0; i < animations.length; i++) {
            if (i == animIdx)
                if (!animations[i].isPlaying())
                    animations[i].play();

             else
                animations[i].stop();

        }
        animationIndex = animIdx;

    }

    public void draw(Canvas canvas, Rect player) {
        if (animations[animationIndex].isPlaying()) {
            animations[animationIndex].draw(canvas, player);
        }

    }

    public void update() {
        if (animations[animationIndex].isPlaying()) {
            animations[animationIndex].update();
        }
    }
}

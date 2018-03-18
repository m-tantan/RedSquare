package yapps.dodgeit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by yonty on 26/05/2017.
 */

public class RectPlayer implements GameObject {
    private Rect rectangle;
    private int color;


    Animation wait;
    Animation walkRight;
    Animation walkLeft;
//    public AnimationManager animManager;

    public RectPlayer(Rect rectangle, int color) {
        this.rectangle = rectangle;
        this.color = color;

//        BitmapFactory bf = new BitmapFactory();
//        Bitmap holds = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.alienyellow);
//        Bitmap walk_step1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.alienyellow_walk1);
//        Bitmap walk_step2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.alienyellow_walk2);
//
//        wait = new Animation(new Bitmap[]{holds}, 2);
//        walkRight= new Animation(new Bitmap[]{walk_step1, walk_step2}, 0.5f);
//
//        Matrix m = new Matrix();
//        m.preScale(-1, 1);
//        walk_step1 = Bitmap.createBitmap(walk_step1, 0, 0, walk_step1.getWidth(), walk_step1.getHeight(), m, false);
//        walk_step2 = Bitmap.createBitmap(walk_step2, 0, 0, walk_step2.getWidth(), walk_step2.getHeight(), m, false);
//
//        walkLeft = new Animation(new Bitmap[]{walk_step1, walk_step2}, 0.5f);
//
//        animManager = new AnimationManager(new Animation[] {wait, walkRight, walkLeft});
    }

    public Rect getRectangle() {
        return rectangle;
    }

    public int getWidth() {
        return rectangle.right - rectangle.left;
    }

    public void setRectangle(Rect rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
//        animManager.draw(canvas, rectangle);
    }
//
    @Override
    public void update() {

    }

    public void update(Point point) {
//        float last_pos = rectangle.left;

        rectangle.set(point.x - rectangle.width() / 2,
                point.y - rectangle.height() / 2,
                point.x + rectangle.width() / 2,
                point.y + rectangle.height() / 2);

//        int state = 0;
//        if (rectangle.left - last_pos > 5) {
//            state = 1;
//        }
//        if (rectangle.left - last_pos < -5) {
//            state = 2;
//        }
//        animManager.playAnim(state);
//        animManager.update();
    }

}

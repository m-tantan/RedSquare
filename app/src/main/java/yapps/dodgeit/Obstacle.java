package yapps.dodgeit;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by yonty on 26/05/2017.
 */

public class Obstacle implements GameObject {
    private Rect rectangle;
    private Rect rectangle2;
    private int color;

    public Obstacle(int rectHeight, int color, int startX, int startY, long playerGap) {
        this.color = color;
        rectangle = new Rect(0, startY, startX, startY + rectHeight);
        rectangle2 = new Rect(startX + (int) playerGap, startY, Constants.SCREEN_WIDTH, startY + rectHeight);
    }


    public Rect getRectangle() {
        return rectangle;
    }

    public boolean playerCollide(RectPlayer player) {
        return Rect.intersects(rectangle, player.getRectangle())|| Rect.intersects(rectangle2, player.getRectangle());
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
}

package yapps.dodgeit;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

public class ObstacleManager {
    private static final int SAFE_AREA = 120;
    //Higher Index = Lower onscreen = Higher Y value
    private ArrayList<Obstacle> obstacles;
    private int playerGap;
    private long obstacleGap;
    private int obstacleHeight;
    private int color;
    private int score = 0;
    private long currentTime;
    private long initTime;
    private int playerWidth;

    public ObstacleManager(int playerGap, int color, long obstacleGap, int obstacleHeight, int playerWidth) {
        this.playerGap = playerGap;
        this.obstacleGap = obstacleGap;
        this.color = color;
        this.obstacleHeight = obstacleHeight;
        this.playerWidth = playerWidth;
        obstacles = new ArrayList<>();
        currentTime = initTime = System.currentTimeMillis();
        populateObstacles();
    }


    public boolean playerCollide(RectPlayer player) {
        for (Obstacle ob : obstacles) {
            if (ob.playerCollide(player)) {
                return true;
            }
        }
        return false;
    }

    private void populateObstacles() {
        int currY = -5*Constants.SCREEN_HEIGHT/4;
        while (currY < 0) {
            int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH - playerGap));
            obstacles.add(new Obstacle((obstacleHeight), color, xStart, currY, playerGap));
            currY += obstacleHeight + obstacleGap;
        }
    }


    public void update() {
        int elapsedTime = (int)(System.currentTimeMillis() - currentTime);
        currentTime = System.currentTimeMillis();


        float playerGapInObstacles = (float) (Math.sqrt(1 + (currentTime - initTime) / 6140.0f) * (Constants.SCREEN_WIDTH / 144000000.0f));
        if (playerGap > playerWidth + SAFE_AREA)
            playerGap -= playerGapInObstacles;
        float speed = (float) (Math.sqrt(1 + (currentTime - initTime) / 3120.0f) * (Constants.SCREEN_HEIGHT / 10000.0f));
//        for (Obstacle ob : obstacles) {
//            ob.incrementY(speed * elapsedTime);
//        }

        if (obstacles.get(obstacles.size() - 1).getRectangle().top >= Constants.SCREEN_HEIGHT) {
            int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH - playerGap));
            obstacles.add(0, new Obstacle(obstacleHeight, color, xStart, obstacles.get(0).getRectangle().top -  obstacleHeight - (int) obstacleGap, playerGap));
            obstacles.remove(obstacles.size() - 1);
            score++;

        }
    }

    public void draw(Canvas canvas) {
        for (Obstacle ob : obstacles) {
            ob.draw(canvas);
        }
        Paint paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.BLUE);
        canvas.drawText("" + score, 50, 50 + paint.descent() - paint.ascent(), paint);
    }

    public int getScore() {
        return score;
    }
}

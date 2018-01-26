package yapps.dodgeit;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

import android.widget.Toast;


public class GameplayScene extends Activity implements Scene {
    private static final int PLAYER_GAP = 600;
    public static final String MY_PREFS = "My Prefs";
    private static long OBSTACLE_GAP = 550;
    private RectPlayer player;
    private Rect r = new Rect();
    private Point playerPoint;
    private ObstacleManager obstacleManager;
    private boolean movingPlayer = false;
    private boolean gameOver = false;
    private long gameOverTime;
    int playerWidth;


    public GameplayScene() {
        player = new RectPlayer(new Rect(100, 100, 350, 350), Color.rgb(255, 0, 0));
        playerWidth = player.getWidth();
        playerPoint = new Point(Constants.SCREEN_WIDTH/2, 3*Constants.SCREEN_HEIGHT/4);
        player.update(playerPoint);
        obstacleManager = new ObstacleManager(PLAYER_GAP,Color.BLACK, OBSTACLE_GAP, 75, playerWidth);
    }

    public void reset() {
        obstacleManager = new ObstacleManager(PLAYER_GAP, Color.BLACK, OBSTACLE_GAP, 75, playerWidth);

        playerPoint = new Point(Constants.SCREEN_WIDTH/2, 3*Constants.SCREEN_HEIGHT/4);
        player.update(playerPoint);

        movingPlayer = false;
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
        obstacleManager.draw(canvas);
        player.draw(canvas);

        if (gameOver) {
            Paint paint = new Paint();
            paint.setTextSize(100);
            paint.setColor(Color.GREEN);
//            SharedPreferences mySP = getSharedPreferences(MY_PREFS, MODE_PRIVATE);
//            SharedPreferences.Editor myEditor = mySP.edit();
//            myEditor.commit();
//            String prvsHighScore = mySP.getString("HighScore", "0");
//            if (obstacleManager.getScore() > Integer.valueOf(prvsHighScore)) {
//                drawCenterText(canvas, paint, "You got hit, try again?"+ obstacleManager.getScore());
//                myEditor.putInt("HighScore", obstacleManager.getScore());
//            } else {
//                drawCenterText(canvas, paint, "Someone beat your score already");
//            }
//
            drawCenterText(canvas, paint, "You got hit, try again?");
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
                if (gameOver && System.currentTimeMillis() - gameOverTime >= 1250) {
                    gameOver = false;
                    reset();
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (!gameOver && movingPlayer) {
                    playerPoint.set((int) event.getX(), (int)event.getY());
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

}

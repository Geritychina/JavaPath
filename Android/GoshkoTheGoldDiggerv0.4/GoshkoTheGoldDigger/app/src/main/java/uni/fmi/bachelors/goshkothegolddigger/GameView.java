package uni.fmi.bachelors.goshkothegolddigger;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameView extends SurfaceView implements Runnable{

    boolean isGameOver = false;
    int sizeX;
    int sizeY;

    int score;

    Hero player;
    List<MiningObject> miningElements = new ArrayList<>();

    SurfaceHolder surfaceHolder;
    Canvas canvas;
    Paint paint;

    Thread gameThread;

    Random random = new Random();

    Bitmap background;

    public GameView(Context context, int sizeX, int sizeY) {
        super(context);
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        background = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.world);
        background = Bitmap.createScaledBitmap(background, sizeX, sizeY, true);

        player = new Hero(context, sizeX, sizeY);

        int rocks = random.nextInt(4);// 0 - 3
        int gold = random.nextInt(3) + 1; // 1 - 3

        for(int i = 0; i < rocks; i++){
            int weight = random.nextInt(40) + 11; // (0 - 39)  + 11

            miningElements.add(new MiningObject(context, sizeX, sizeY, weight));
        }

        for(int i = 0; i < gold; i++){
            int weight = random.nextInt(10) + 1;

            miningElements.add(new MiningObject(context, sizeX, sizeY, weight));
        }

        surfaceHolder = getHolder();
        paint = new Paint();

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        while(!isGameOver){
            try {
                gameThread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            update();

            draw();

        }

    }

    private void draw() {
        if(surfaceHolder.getSurface().isValid()){
            canvas = surfaceHolder.lockCanvas();

            canvas.drawBitmap(background,0,0, paint);

            canvas.drawBitmap(player.bitmap, player.x, player.y, paint);

            for(MiningObject el : miningElements){
                canvas.drawBitmap(
                        el.bitmap,
                        el.x,
                        el.y,
                        paint);
            }

            paint.setColor(Color.WHITE);
            paint.setTextSize(60);

            canvas.drawText("Score: " + score, canvas.getWidth() / 2, 150, paint);

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }


    static int speedX = 5;
    static int speedY = 0;
    static boolean detectedCollision = false;

    private void update() {

      //  player.rotate(1);

        player.update(speedX,speedY);
        for(MiningObject el : miningElements){
            el.update();
        }

        if(!detectedCollision){
            for(MiningObject el : miningElements){
                if(Rect.intersects(player.collisionDetection, el.detectCollision)){
                    speedY = speedY - el.weight / 7;
                    player.directionY *= -1;
                    el.speed = speedY;
                    el.detectCollision = new Rect(0,0,0,0);
                    detectedCollision = true;
                    break;
                }
            }
        }

    }

    public static void stopMotion(){
        speedX = 0;
        speedY = 0;
        detectedCollision = false;
    }
    //1. Куката не се поклаща
    //2. тръгва веднага на долу а не на страни
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(speedX == 0 && speedY == 0){
            speedX = 5;
        }else if(speedY == 0){
            speedX = 0;
            speedY = 10;
        }

        return true;
    }
}

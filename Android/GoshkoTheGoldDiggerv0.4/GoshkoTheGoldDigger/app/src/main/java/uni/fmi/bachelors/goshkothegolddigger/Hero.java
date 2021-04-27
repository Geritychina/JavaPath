package uni.fmi.bachelors.goshkothegolddigger;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;

public class Hero {

    Bitmap bitmap;
    int startingX;
    int startingY;

    int x;
    int y;

    int maxX;
    int maxY;

    int directionX = 1;
    int directionY = 1;
    Rect collisionDetection;

    int width;
    int height;

    public Hero(Context context, int sizeX, int sizeY){
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.anchor);

        maxX = sizeX - bitmap.getWidth();
        maxY = sizeY - bitmap.getHeight();

        y = maxY / 7;
        x = maxX / 2 + bitmap.getWidth() / 2;

        startingX = x;
        startingY = y;

        collisionDetection = new Rect(x, y, x + bitmap.getWidth(), y + bitmap.getHeight());

        width = bitmap.getWidth();
        height = bitmap.getHeight();
    }

    public void rotate(int degree){

        Matrix matrix = new Matrix();
        matrix.setRotate(degree, startingX + width / 2, startingY + height / 2);

        bitmap = Bitmap.createBitmap(bitmap,0 ,0,width, height, matrix, true);

    }

    public void update(int moveX, int moveY){

        x += moveX * directionX;
        y += moveY * directionY;

        if(x < 0){
            x = 0;
            directionX = 1;
        }else if(x > maxX){
            x = maxX;
            directionX = -1;
        }

        if(y > maxY){
            y = maxY;
            directionY = -1;
        }else if(y < startingY){
            y = startingY;
            directionY = 1;

            GameView.stopMotion();
        }

        collisionDetection.left = x;
        collisionDetection.right = x + bitmap.getWidth();
        collisionDetection.top = y;
        collisionDetection.bottom = y + bitmap.getHeight();
    }


}

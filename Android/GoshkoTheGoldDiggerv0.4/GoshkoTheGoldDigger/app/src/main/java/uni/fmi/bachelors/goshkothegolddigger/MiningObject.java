package uni.fmi.bachelors.goshkothegolddigger;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.Log;

import java.util.Random;

public class MiningObject {

    //камъни 0 - 3     11  - 50
    //злато 1 - 3      0 - 10
    int x;
    int y;
    int maxX;
    int maxY;
    int minY;
    Bitmap bitmap;

    Rect detectCollision;
    Random random = new Random();

    int weight;
    int value;

    int[] skinsGold = {
            R.drawable.gold1,
            R.drawable.gold2
    };

    int[] skinsRock = {
            R.drawable.rock1,
            R.drawable.rock2
    };

    int speed;

    public void update(){
        y -= speed;
    }

    public MiningObject(Context context, int sizeX, int sizeY, int weight){
        maxX = sizeX;
        maxY = sizeY;
        minY = maxY / 3;

        this.weight = weight;

        if(weight < 11){
            value = weight * 50;

            float scale = (float)weight / 7;

            Log.wtf("size", "Gold - weigh:" + weight + "scale:" + scale);

            bitmap = BitmapFactory.decodeResource(
                    context.getResources(),
                    skinsGold[random.nextInt(skinsGold.length)]
                    );

            bitmap = Bitmap.createScaledBitmap(bitmap, (int)(bitmap.getWidth() * scale), (int)(bitmap.getHeight() * scale), true);
        }else{
            value = weight;

            float scale = (float)weight / 18;

            Log.wtf("size", "Rock - weigh:" + weight + "scale:" + scale);

            bitmap = BitmapFactory.decodeResource(
                    context.getResources(),
                    skinsRock[random.nextInt(skinsRock.length)]
            );

            bitmap = Bitmap.createScaledBitmap(bitmap, (int)(bitmap.getWidth() * scale), (int)(bitmap.getHeight() * scale), true);


        }

        x = random.nextInt(maxX - bitmap.getWidth());
        y = minY + random.nextInt(maxY - minY - bitmap.getHeight());

        detectCollision = new Rect(
                x,
                y,
                x + bitmap.getWidth(),
                y + bitmap.getHeight()
        );

    }




}

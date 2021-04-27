package uni.fmi.bachelors.goshkothegolddigger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "goshkothegolddigger.sqlite";
    public static final String ERRO_TAG = "MyErroTag";

    public static final String TABLE_USER = "user";
    public static final String USER_COLUMN_ID = "id";
    public static final String USER_COLUMN_USERNAME = "username";
    public static final String USER_COLUMN_PASSWORD = "password";
    public static final String USER_COLUMN_GENDER = "gender";

    public static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "('" +
            USER_COLUMN_ID + "' INTEGER PRIMARY KEY AUTOINCREMENT," +
            "'" + USER_COLUMN_USERNAME + "' VARCHAR(40) NOT NULL UNIQUE," +
            "'" + USER_COLUMN_PASSWORD + "' VARCHAR(40) NOT NULL," +
            "'" + USER_COLUMN_GENDER + "' VARCHAR(20) DEFAULT 'APACHE HELICOPTER')";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        onCreate(db);
    }

    public boolean registerUser(User user){
        SQLiteDatabase db = null;

        try {
            db = getWritableDatabase();

            ContentValues cv = new ContentValues();

            cv.put(USER_COLUMN_USERNAME, user.getUsername());
            cv.put(USER_COLUMN_PASSWORD, user.getPassword());
            cv.put(USER_COLUMN_GENDER, user.getGender());

            return db.insert(TABLE_USER, null, cv) != -1;
        }catch(SQLException e){
            Log.wtf(ERRO_TAG, e.getMessage());
        }finally {
            if(db != null)
                db.close();
        }

        return false;
    }

    public User getUser(String username, String password){
        SQLiteDatabase db = null;
        Cursor c = null;

        try{
            db = getReadableDatabase();

            String query = "SELECT * FROM " + TABLE_USER +
                    " WHERE " + USER_COLUMN_USERNAME + " = '" + username + "' AND " +
                    USER_COLUMN_PASSWORD + " = '" + password + "'";

            c = db.rawQuery(query, null);

            if(c.moveToFirst()){
                User user = new User();

                user.setUsername(username);
                user.setGender(c.getString(c.getColumnIndex(USER_COLUMN_GENDER)));
                //всичко за user

                return user;
            }

        }catch(SQLException e){
            Log.wtf(ERRO_TAG, e.getMessage());
        }finally {
            if(db != null){
                if(c != null)
                    c.close();

                db.close();
            }
        }

        return null;

    }

    public boolean isLoginSuccessful (String username, String password){
        SQLiteDatabase db = null;
        Cursor c = null;

        try{
            db = getReadableDatabase();

            String query = "SELECT * FROM " + TABLE_USER +
                    " WHERE " + USER_COLUMN_USERNAME + " = '" + username + "' AND " +
                    USER_COLUMN_PASSWORD + " = '" + password + "'";

            c = db.rawQuery(query, null);

            return c.moveToFirst();

        }catch(SQLException e){
            Log.wtf(ERRO_TAG, e.getMessage());
        }finally {
            if(db != null){
                if(c != null)
                    c.close();

                db.close();
            }
        }

        return false;
    }
}

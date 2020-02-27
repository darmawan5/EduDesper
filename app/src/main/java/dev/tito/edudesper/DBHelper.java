package dev.tito.edudesper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "EduDesper.db";

    public static final String TABLE_NAME = "user";

    public static final String COL_ID = "id";
    public static final String COL_NAME = "nama";
    public static final String COL_DATE = "tanggal";
    public static final String COL_ADDRESS = "alamat";
    public static final String COL_EMAIL = "email";
    public static final String COL_PASSWORD = "password ";


    public static final String SQL_TABLE_USERS = " CREATE TABLE "+ TABLE_NAME + " ( " + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_NAME + " TEXT, " + COL_DATE + " TEXT, " + COL_ADDRESS + " TEXT, " + COL_EMAIL + " TEXT, " + COL_PASSWORD + " TEXT " + " )";

   public DBHelper(Context context) {
       super(context,DATABASE_NAME,null,DATABASE_VERSION);

   }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public void addUser(User user){

       SQLiteDatabase db = this.getWritableDatabase();
       ContentValues cv = new ContentValues();

       cv.put(COL_NAME, user.nama);
       cv.put(COL_DATE, user.tgl);
       cv.put(COL_ADDRESS, user.alamat);
       cv.put(COL_EMAIL, user.email);
       cv.put(COL_PASSWORD, user.password);

       db.insert(TABLE_NAME,null, cv);

    }

    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,// Selecting Table
                new String[]{COL_ID,
                        COL_NAME,
                        COL_DATE,
                        COL_ADDRESS,
                        COL_EMAIL,
                        COL_PASSWORD},//Selecting columns want to query
                COL_EMAIL + "=?",
                new String[]{user.email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email
            User user1 = new User(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5));

            //Match both passwords check they are same or not
            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }

        //if user password does not matches or there is no record with that email then return @false
        return null;
    }

    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COL_ID,
                COL_NAME,
                COL_DATE,
                COL_ADDRESS,
                COL_EMAIL,
                COL_PASSWORD
        };
        // sorting orders
        String sortOrder =
                COL_NAME + " ASC";
        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table

        Cursor cursor = db.query(TABLE_NAME, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
                user.setId(cursor.getString(cursor.getColumnIndex(COL_ID)));
                user.setNama(cursor.getString(cursor.getColumnIndex(COL_NAME)));
                user.setTgl(cursor.getString(cursor.getColumnIndex(COL_DATE)));
                user.setAlamat(cursor.getString(cursor.getColumnIndex(COL_ADDRESS)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COL_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COL_PASSWORD)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }



    public boolean isEmailExists(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,// Selecting Table
                new String[]{COL_ID, COL_NAME, COL_DATE, COL_ADDRESS, COL_EMAIL,COL_PASSWORD},//Selecting columns want to query
                COL_EMAIL + "=?",
                new String[]{email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email so return true
            return true;
        }

        //if email does not exist return false
        return false;
    }
    }

//    public boolean checkUser(String email, String password) {
//
//        // array of columns to fetch
//        String[] columns = {
//                COL_ID
//        };
//        SQLiteDatabase db = this.getReadableDatabase();
//        // selection criteria
//        String selection = COL_EMAIL + " = ?" + " AND " + COL_PASSWORD + " = ?";
//
//        // selection arguments
//        String[] selectionArgs = {email, password};
//
//
//        Cursor cursor = db.query(TABLE_NAME, //Table to query
//                columns,                    //columns to return
//                selection,                  //columns for the WHERE clause
//                selectionArgs,              //The values for the WHERE clause
//                null,                       //group the rows
//                null,                       //filter by row groups
//                null);                      //The sort order
//
//        int cursorCount = cursor.getCount();
//
//        cursor.close();
//        db.close();
//        if (cursorCount > 0) {
//            return true;
//        }
//
//        return false;
//    }
//
//
//}

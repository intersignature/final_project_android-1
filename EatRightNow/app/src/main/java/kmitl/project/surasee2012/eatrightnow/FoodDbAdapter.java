package kmitl.project.surasee2012.eatrightnow;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Gun on 11/6/2017.
 */

public class FoodDbAdapter {

    FoodDB foodDB;
    public FoodDbAdapter(Context context) {
        foodDB = new FoodDB(context);
        if (getData().equals("")) {
            for(int i=0;i<5;i++) {
                insertData("gun" + Integer.toString(i), Integer.toString(i));
            }
        }
    }

    public long insertData(String name, String pass) {
        SQLiteDatabase dbb = foodDB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FoodDB.NAME, name);
        long id = dbb.insert(FoodDB.TABLE_NAME, null , contentValues);
        return id;
    }

    public String getData() {
        SQLiteDatabase db = foodDB.getWritableDatabase();
        String[] columns = {FoodDB.ID, FoodDB.NAME};
        Cursor cursor =db.query(FoodDB.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext()) {
            int cid =cursor.getInt(cursor.getColumnIndex(FoodDB.ID));
            String name =cursor.getString(cursor.getColumnIndex(FoodDB.NAME));
            buffer.append(cid+ "   " + name + " \n");
        }
        return buffer.toString();
    }

    public  int delete(String uname) {
        SQLiteDatabase db = foodDB.getWritableDatabase();
        String[] whereArgs ={uname};

        int count =db.delete(FoodDB.TABLE_NAME ,FoodDB.NAME+" = ?",whereArgs);
        return  count;
    }

    public int updateName(String oldName , String newName) {
        SQLiteDatabase db = foodDB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FoodDB.NAME,newName);
        String[] whereArgs= {oldName};
        int count =db.update(FoodDB.TABLE_NAME,contentValues, FoodDB.NAME+" = ?",whereArgs );
        return count;
    }

    static class FoodDB extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "eatRightNow?Database";    // Database Name
        private static final String TABLE_NAME = "foodTable";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version
        private static final String ID = "id";     // Column I (Primary Key)
        private static final String NAME = "Name";    //Column II
        private static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME +
                " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255));";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS " + TABLE_NAME;
        private Context context;

        public FoodDB(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public FoodDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        public FoodDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
            super(context, name, factory, version, errorHandler);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            try {
                Message.message(context,"OnUpgrade");
                sqLiteDatabase.execSQL(DROP_TABLE);
                onCreate(sqLiteDatabase);
            }catch (Exception e) {
                Message.message(context,""+e);
            }
        }

    }
}

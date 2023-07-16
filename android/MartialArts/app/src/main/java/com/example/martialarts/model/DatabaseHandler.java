package com.example.martialarts.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "martialArtsDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String MARTIAL_ARTS_TABLE = "martialArts";
    private static final String ID_KEY = "id";
    private static final String NAME_KEY = "name";
    private static final String COLOR_KEY = "color";
    private static final String PRICE_KEY = "price";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableCommand = "create table " + MARTIAL_ARTS_TABLE +
                " ( " + ID_KEY + " INTEGER primary key autoincrement" +
                ", " + NAME_KEY + " text" +
                ", " + COLOR_KEY + " text" +
                ", " + PRICE_KEY + " real" + " )";

        db.execSQL(createTableCommand);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableIfExistCommand = "drop table if exists " + MARTIAL_ARTS_TABLE;
        db.execSQL(dropTableIfExistCommand);
        onCreate(db);
    }

    public void addMartialArt(MartialArt martialArt) {
        String addMartialArtCommand = "insert into " + MARTIAL_ARTS_TABLE +
                " values" + "( null, '" + martialArt.getMartialArtName() +
                "', '" + martialArt.getMartialArtColor() +
                "', '" + martialArt.getMartialArtPrice() + "')";

        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(addMartialArtCommand);
        database.close();
    }

    public void removeMartialArtById(int id) {
        String removeMartialArtCommand = "delete from " +
                MARTIAL_ARTS_TABLE +
                " where " +
                ID_KEY + " = '" + id + "'";

        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(removeMartialArtCommand);
        database.close();
    }

    public void removeAllMartialArt() {
        String removeAllMartialArtCommand = "delete from " + MARTIAL_ARTS_TABLE;

        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(removeAllMartialArtCommand);
        database.close();
    }

    public void updateMartialArt(int id, String name, String color, double price) {
        String updateMartialArtCommand = "update " +
                MARTIAL_ARTS_TABLE + " set " +
                NAME_KEY + " = '" + name + "', " +
                COLOR_KEY + " = '" + color + "', " +
                PRICE_KEY + " = '" + price +
                "' where " + ID_KEY + " = " + id;

        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(updateMartialArtCommand);
        database.close();
    }

    public ArrayList<MartialArt> getAllMartialArts() {
        ArrayList<MartialArt> martialArts = new ArrayList<>();

        String getMartialArtsCommand = "select * from " + MARTIAL_ARTS_TABLE;

        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery(getMartialArtsCommand, null);
        while (cursor.moveToNext()) {
            MartialArt martialArt = new MartialArt(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getDouble(3)
            );
            martialArts.add(martialArt);
        }
        cursor.close();
        database.close();

        return martialArts;
    }

    public MartialArt getMartialArtById(int id) {
        MartialArt martialArt = null;

        String getMartialArtCommand = "select * from " + MARTIAL_ARTS_TABLE +
                " where " + ID_KEY + " = " + id;

        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery(getMartialArtCommand, null);
        while (cursor.moveToFirst()) {
            martialArt = new MartialArt(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getDouble(3)
            );
        }
        cursor.close();
        database.close();

        return martialArt;
    }
}

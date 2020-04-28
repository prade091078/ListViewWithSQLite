package com.prade.listviewwithsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.prade.listviewwithsqlite.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listview = (ListView)findViewById(R.id.listFestival);
        ArrayList<String> fest = new ArrayList<String>();
        try
        {
            SQLiteDatabase myDB = this.openOrCreateDatabase("MyTestDB", MODE_PRIVATE, null);
  /*    myDB.execSQL("CREATE TABLE IF NOT EXISTS Festivals (name VARCHAR,month INT(2),id INTEGER PRIMARY KEY)");
        myDB.execSQL("CREATE TABLE IF NOT EXISTS Festival (name VARCHAR,month INT(2),id INTEGER)");
        myDB.execSQL("INSERT INTO Festival VALUES ('PONGAL',01,1) ");
        myDB.execSQL("INSERT INTO Festival VALUES ('NEW YEAR',01,2) ");
        myDB.execSQL("INSERT INTO Festival VALUES ('DIWALI',10,3) ");
        myDB.execSQL("INSERT INTO Festival VALUES ('RAMZAN',06,4) ");
        myDB.execSQL("INSERT INTO Festival VALUES ('CHRISTMAS',12,5) ");
*/

       /*
                myDB.execSQL("CREATE TABLE IF NOT EXISTS FestivalNames (name VARCHAR,month INT(2))");
        myDB.execSQL("INSERT INTO FestivalNames VALUES ('PONGAL',01) ");
        myDB.execSQL("INSERT INTO FestivalNames VALUES ('NEW YEAR',01) ");
        myDB.execSQL("INSERT INTO FestivalNames VALUES ('DIWALI',10) ");
        myDB.execSQL("INSERT INTO FestivalNames VALUES ('RAMZAN',06) ");
        myDB.execSQL("INSERT INTO FestivalNames VALUES ('CHRISTMAS',12) ");

        Cursor c = myDB.rawQuery("SELECT * FROM FestivalNames WHERE name = 'PONGAL' AND month=01",null );

        myDB.execSQL("UPDATE FestivalNames SET name='MAATU PONGAL' WHERE name='PONGAL'");

        Cursor c = myDB.rawQuery("SELECT * FROM FestivalNames WHERE name like '%PONGAL' AND month=01",null );

        myDB.execSQL("DELETE FROM FestivalNames where name ='PONGAL'");

    */

            Cursor c = myDB.rawQuery("SELECT  * FROM Festival",null );

            int name = c.getColumnIndex("name");
            int age = c.getColumnIndex("month");
            int id = c.getColumnIndex("id");
            int col = 0;

            c.moveToFirst();

            while (c != null && c.getCount() > col) {
                Log.i("FESTIVALName - Month", Integer.toString((id)) + ". "+ c.getString(name) + "-"+Integer.toString(c.getInt(age)));
                fest.add(Integer.toString(c.getInt(id)) + ". "+ c.getString(name) + "-"+Integer.toString(c.getInt(age)));
                col+=1;
                c.moveToNext();
            }

            c.close();

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,fest);
            listview.setAdapter(adapter);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
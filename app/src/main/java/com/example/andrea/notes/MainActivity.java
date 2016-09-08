package com.example.andrea.notes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity
{

   public static ArrayList<String> list = new ArrayList<>();
   public static ArrayAdapter<String> stringArrayAdapter;
   static Set<String> set;
   private SharedPreferences sharedPreferences;
   public static ArrayList<Note> notesList = new ArrayList<Note>();

   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);

      ListView listView = (ListView) findViewById(R.id.notes_list);

      /*sharedPreferences = this.getSharedPreferences("com.example.andrea.notes", Context.MODE_PRIVATE);
      set = sharedPreferences.getStringSet("list", null);
      if (set != null)
      {
         list.clear();
         list.addAll(set);
      }
      else
      {
         list.add("Sample Note");
         set = new HashSet<>();
         set.addAll(list);
         updateSharedPreferences();
      }*/


      stringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

      listView.setAdapter(stringArrayAdapter);
      listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
         @Override
         public void onItemClick(AdapterView<?> parent, View view, int position, long id)
         {
            Intent i = new Intent(getApplicationContext(), EditNote.class);
            i.putExtra("noteId", position);
            startActivity(i);

         }
      });
      listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
      {
         @Override
         public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id)
         {
            new AlertDialog.Builder(MainActivity.this)
                    .setIcon(android.R.drawable.ic_delete)
                    .setTitle("Are you sure you want to delete this note?")
                    .setMessage("Click yes to delete this note")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int which)
                       {
                          list.remove(position);
                          updateSet();
                          set.addAll(list);

                          stringArrayAdapter.notifyDataSetChanged();
                       }
                    }).setNegativeButton("No", null)
                    .show();
            return true;
         }
      });


   }

   private void updateSharedPreferences()
   {
      sharedPreferences.edit().remove("list").apply();
      sharedPreferences.edit().putStringSet("list", set).apply();
   }


   @Override
   public boolean onCreateOptionsMenu(Menu menu)
   {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.menu_main, menu);
      return true;
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item)
   {
      // Handle action bar item clicks here. The action bar will
      // automatically handle clicks on the Home/Up button, so long
      // as you specify a parent activity in AndroidManifest.xml.
      int id = item.getItemId();

      //noinspection SimplifiableIfStatement
      if (id == R.id.add_note)
      {
         list.add("");
         updateSet();
         Intent i = new Intent(getApplicationContext(), EditNote.class);
         i.putExtra("noteId", list.size() - 1);
         startActivity(i);
         return true;
      }

      return super.onOptionsItemSelected(item);
   }

   private void updateSet()
   {
      if (set == null)
      {
         set = new HashSet<>();
      }
      else
      {
         set.clear();
      }
      set.addAll(list);
      updateSharedPreferences();
   }
}

package com.example.andrea.notes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;


public class EditNote extends AppCompatActivity implements TextWatcher
{

   public EditText noteContent;
   public EditText noteTitle;
   private int noteId;

   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_edit_note);
      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);
      noteContent = (EditText) findViewById(R.id.noteContent);
      noteTitle = (EditText) findViewById(R.id.noteTitle);
      Intent i = getIntent();
      noteId = i.getIntExtra("noteId", -1);
      if (noteId != -1)
      {
         noteContent.setText(MainActivity.list.get(noteId));
      }
      noteContent.addTextChangedListener(this);
      //noteTitle.addTextChangedListener(this);

      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
   }


   @Override
   public void beforeTextChanged(CharSequence s, int start, int count, int after)
   {

   }

   @Override
   public void onTextChanged(CharSequence s, int start, int count, int after)
   {
      MainActivity.list.set(noteId, String.valueOf(s));
      MainActivity.stringArrayAdapter.notifyDataSetChanged();
      MainActivity.set.clear();
      MainActivity.set.addAll(MainActivity.list);
      /*SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.andrea.notes", Context.MODE_PRIVATE);
      sharedPreferences.edit().remove("list").apply();
      sharedPreferences.edit().putStringSet("list", MainActivity.set).apply();*/


   }

   @Override
   public void afterTextChanged(Editable editable)
   {

   }
}

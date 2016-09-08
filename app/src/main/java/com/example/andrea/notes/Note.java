package com.example.andrea.notes;

/**
 * Created by Andrea on 09/07/2016.
 * Houses the note title and the note content
 */
public class Note
{
   private int _id;
   private String _title;
   private String _content;

   public int get_id()
   {
      return _id;
   }

   public Note(String title, String content)
   {
      this._title = title;
      this._content = content;
   }

   public Note()
   {
      this._title = "";
      this._content = "";
   }

   public String get_title()
   {
      return _title;
   }

   public void set_title(String title)
   {
      this._title = title;
   }

   public String get_content()
   {
      return _content;
   }

   public void set_content(String content)
   {
      this._content = content;
   }
}

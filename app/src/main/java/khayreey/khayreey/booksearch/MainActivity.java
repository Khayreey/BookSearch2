package khayreey.khayreey.booksearch;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText book_name;
    Button search;
    ListView listView;
    ProgressBar bar;
    Adapter adapter;
    String book_title;
    String GOOGLE_BOOKS_API;
    ImageView bo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         bo=findViewById(R.id.bo);
        book_name=findViewById(R.id.book_name);
        bar=findViewById(R.id.bar);
        bar.setVisibility(View.INVISIBLE);
        search=findViewById(R.id.search_btn);
        listView=findViewById(R.id.book_list);
        adapter =new Adapter(getApplicationContext(),0, new ArrayList<dataclass>());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dataclass current=adapter.getItem(position);
                String title=current.getBook_title();
                String author=current.getBook_author();
                String publisher=current.getPublisher();
                String desc=current.getDescription();
                String url=current.getImageurl();
                Intent intent=new Intent(getApplicationContext(),book.class);
                intent.putExtra("title",title);
                intent.putExtra("author",author);
                intent.putExtra("pub",publisher);
                intent.putExtra("desc",desc);
                intent.putExtra("url",url);
           startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view)
    {
        adapter.clear();
    book_title=book_name.getText().toString();
   if (book_title.length()== 0)
   {
       Toast.makeText(getApplicationContext(),"enter book title",Toast.LENGTH_SHORT).show();
   }
   else
   {

       GOOGLE_BOOKS_API= " https://www.googleapis.com/books/v1/volumes?q= " + book_title;
       AsyncTask operation=new AsyncTask();
       operation.execute(GOOGLE_BOOKS_API);
       InputMethodManager inputMethodManager= (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
       inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY,0);
        book_name.setText("");
        bo.setVisibility(View.GONE);
   }
    }
});
    }
    public  class AsyncTask extends android.os. AsyncTask <String,Void,ArrayList<dataclass>>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            bar.setVisibility(View.VISIBLE);
        }
        @Override
        protected ArrayList<dataclass> doInBackground(String... strings) {
             if (strings.length < 1 || strings[0]==null)
             {
                 return null;
             }

                 ArrayList<dataclass> books = Utils.fetchdata(strings[0]);

                 return books;
        }
        @Override
        protected void onPostExecute(ArrayList<dataclass> dataclasses) {
            super.onPostExecute(dataclasses);

        if(dataclasses!=null && !dataclasses.isEmpty())
        {
            bar.setVisibility(View.INVISIBLE);
             adapter.addAll(dataclasses);
        }
        }
    }
}
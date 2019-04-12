package khayreey.khayreey.booksearch;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Objects;

public class book extends AppCompatActivity {
ImageView image;
TextView title,author,publisher,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
       image=findViewById(R.id.image3);
       title=findViewById(R.id.title2);
        author=findViewById(R.id.author2);
        publisher=findViewById(R.id.publisher);
        description=findViewById(R.id.description);
        String title2= getIntent().getStringExtra("title");
        String author2= getIntent().getStringExtra("author");
        String publisher2= getIntent().getStringExtra("pub");
        String description2= getIntent().getStringExtra("desc");
        String url= getIntent().getStringExtra("url");
        title.setText(title2);
        author.setText(author2);
        publisher.setText(String.format("published by: %s", publisher2));
        description.setText(description2);
        if (url.length()!=0)
        {
            Picasso.get().load(url).placeholder(R.drawable.as).error(R.drawable.as).resize(180,180)
                    .into(image);
        }
        else {
            image.setImageResource(R.drawable.as);
        }
    }
}

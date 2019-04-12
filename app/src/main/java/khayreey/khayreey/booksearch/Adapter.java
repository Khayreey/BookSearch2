package khayreey.khayreey.booksearch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<dataclass>
{
    ImageView image;
    TextView title,author;
    public Adapter(@NonNull Context context, int resource , ArrayList<dataclass> list3) {
        super(context, resource ,list3);
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
      View view= convertView;
   if (view==null)
   {
       view = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
   }

     dataclass data= getItem (position);

     title=view.findViewById(R.id.title);
     author=view.findViewById(R.id.author);
        image=view.findViewById(R.id.image);
        title.setText(data.getBook_title());
        author.setText(data.getBook_author());

     if (data.getImageurl().length()!=0)
     {
         Picasso.get().load(data.getImageurl()).
                 resize(100,100).centerCrop().into(image);
     }
else {
         image.setImageResource(R.drawable.book);
     }

        return view;

    }
}


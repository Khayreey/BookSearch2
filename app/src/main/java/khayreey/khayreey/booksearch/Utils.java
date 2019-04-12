package khayreey.khayreey.booksearch;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public  class  Utils
{
    public static String title,author,publisher,date,description,image;
    public static final String LOG_TAG=Utils.class.getSimpleName();

    public static ArrayList<dataclass> fetchdata(String requesturl)
    {
        URL url=  crateurl(requesturl);
        String jasonresponse=null;
       try {
           jasonresponse=makehttprequest(url);
       }catch (IOException e)
       {
           Log.e(LOG_TAG,"Error closing input stream",e);
       }
       ArrayList<dataclass> book=extractFromJson(jasonresponse);
       return book;
    }


    private static URL crateurl (String stringurl)
    {
        URL url=null;
        try {
            url =new URL(stringurl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static String makehttprequest (URL url) throws IOException
    {
        String jasonResponse="";
        if (url == null)
        {
            return jasonResponse;
        }

        HttpURLConnection urlConnection=null;
        InputStream inputStream=null;
        try {
            urlConnection =(HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode()==200)
            {
           inputStream=urlConnection.getInputStream();
           jasonResponse=readFromStream(inputStream);

            }
            else {
                Log.e(TAG, "error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (urlConnection!=null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {

                    inputStream.close();

                }
            }

        return jasonResponse;
    }
    private static String  readFromStream (InputStream inputStream) throws IOException
    {
     StringBuilder stringBuilder=new StringBuilder();
     if (inputStream!=null)
     {
         InputStreamReader inputStreamReader=new InputStreamReader(inputStream,Charset.forName("UTF-8"));
         BufferedReader reader= new BufferedReader(inputStreamReader);
         String line =reader.readLine();
         while (line !=null)
         {
             stringBuilder.append(line);
              line=reader.readLine();

         }
     }
return stringBuilder.toString();

    }

private static ArrayList<dataclass> extractFromJson (String jason)
{
if (TextUtils.isEmpty(jason))
{
    return null;
}
     ArrayList<dataclass> result =new ArrayList<>();

    try {

        JSONObject jsonObject = new JSONObject(jason);
        JSONArray jsonArray=jsonObject.getJSONArray("items");
        for (int i=0;i < jsonArray.length() ; i++) {
            JSONObject firstitem = jsonArray.getJSONObject(i);
            JSONObject info = firstitem.getJSONObject("volumeInfo");
            if (info.has("title")) {
                title = info.getString("title");
            } else {
                title = "title not found";
            }

            if (info.has("authors")) {
                author = info.getJSONArray("authors").getString(0);
            } else {
                author = "author not found";
            }
            if (info.has("publisher")) {
                publisher = info.getString("publisher");
            } else {
                publisher = "publisher not found";
            }
            if (info.has("publishedDate")) {
                date = info.getString("publishedDate");
            } else {
                date = "published Date not found";
            }
            if (info.has("description")) {
                description = info.getString("description");
            } else {
                description = "description not found";
            }
            if (info.has("imageLinks")) {
                JSONObject imageurl = info.getJSONObject("imageLinks");
                image =imageurl.getString("thumbnail");
            } else {
                image = "";
            }
            result.add(new dataclass(title, author, image, publisher, date, description));
        }
    } catch (JSONException e) {
        e.printStackTrace();
    }
return result;
}
}

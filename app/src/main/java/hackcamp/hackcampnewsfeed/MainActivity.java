package hackcamp.hackcampnewsfeed;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String URL = "https://newsapi.org/v1/articles?source=techcrunch&sortBy=latest&apikey=bad0ad7059bb4b38bcdf4c207a5a51c6";

    // avoid creating several instances, should be singleon
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                makeApiCall();

                            }
                        }).show();
            }
        });
    }

    private void makeApiCall() {
        final Request request = new Request.Builder()
                .url(URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // For the example, you can show an error dialog or a toast
                        // on the main UI thread
                        Toast.makeText(MainActivity.this,response.toString(),Toast.LENGTH_LONG).show();
//                        ArrayAdapter<FeedItem> adapter = new ArrayAdapter<Feed);
                        ListView listView = (ListView) findViewById(R.id.listview_feed);
                        Log.d("response",response.toString());
                        try {
//                            Log.d("response",response.body().string());
                            Gson gson = new Gson();
                            FeedResult result = gson.fromJson(response.body().string(),FeedResult.class);
                            ArrayList<Article> articleList = new ArrayList<Article>();
                            articleList = (ArrayList<Article>) result.getArticles();
                            ArrayList<String> listItems = new ArrayList<String>();
                            for (Article article : articleList){
                                listItems.add(article.getTitle());
                            }

                            NewsFeedAdapter adapter = new NewsFeedAdapter(MainActivity.this,R.layout.item_list_view,articleList);
//                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,android.R.id.text1,listItems);
                            listView.setAdapter(adapter);
//
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

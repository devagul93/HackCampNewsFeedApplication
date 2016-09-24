package hackcamp.hackcampnewsfeed;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Codestation on 24/09/16.
 */
public class NewsFeedAdapter extends ArrayAdapter<FeedItem>{

    public NewsFeedAdapter(Context context, int resource, List<FeedItem> objects) {
        super(context, resource, objects);
    }


}


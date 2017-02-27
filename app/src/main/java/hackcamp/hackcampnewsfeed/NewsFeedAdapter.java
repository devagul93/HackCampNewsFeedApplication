package hackcamp.hackcampnewsfeed;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Codestation on 24/09/16.
 */
public class NewsFeedAdapter extends ArrayAdapter<Article>{

    ArrayList<Article> articleList = new ArrayList<>();
    Context mContext;

    public NewsFeedAdapter(Context context, int resource, List<Article> objects) {
        super(context, resource, objects);
        this.articleList = (ArrayList<Article>) objects;
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = null;


           v = LayoutInflater.from(getContext()).inflate(R.layout.item_list_view,parent,false);
            TextView titleText = (TextView) v.findViewById(R.id.item_textview);
            ImageView priviewImage = (ImageView) v.findViewById(R.id.item_imageview);

            titleText.setText(articleList.get(position).getTitle());
        Glide.with(mContext).load(articleList.get(position).getUrlToImage()).into(priviewImage);

        return v;

//        return super.getView(position, convertView, parent);
    }
}


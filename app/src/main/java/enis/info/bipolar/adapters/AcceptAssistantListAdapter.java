package enis.info.bipolar.adapters;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import enis.info.bipolar.R;
import enis.info.bipolar.models.User;


/**
 * Created by be on 15/10/16.
 */
public class AcceptAssistantListAdapter extends BaseAdapter {

    private ArrayList<User> items;
    private Context context;
    private LayoutInflater inflater;

    public AcceptAssistantListAdapter(ArrayList<User> items, Context context) {
        this.items = items;
        this.context = context;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final View rowView;
        Holder holder = new Holder();
        rowView = inflater.inflate(R.layout.accept_assistant_list_row,null);

        holder.profilePhoto = (ImageView) rowView.findViewById(R.id.profile_photo);
        holder.firstLastName = (TextView) rowView.findViewById(R.id.first_last_name);
        holder.emailTextViex = (TextView) rowView.findViewById(R.id.email_text_view);
        holder.confirmButton = (Button) rowView.findViewById(R.id.confirm_assistant_button);
        holder.deletRequstButton = (Button) rowView.findViewById(R.id.refuse_assistant_button);

        holder.firstLastName.setText(items.get(i).getFirstName() + " " + items.get(i).getLastName());
        holder.emailTextViex.setText(items.get(i).getEmail());
        holder.confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(rowView, "Request confirmed..", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        holder.deletRequstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(rowView, "Request deleted..", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        return rowView;
    }


    private class Holder{
        ImageView profilePhoto;
        TextView firstLastName;
        TextView emailTextViex;
        Button confirmButton;
        Button deletRequstButton;
    }
}

package com.example.publictransport;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CardInfoAdapter extends ArrayAdapter<CardInfo> {

    public CardInfoAdapter(Activity context, ArrayList<CardInfo> cardInfos){
        super(context, 0, cardInfos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.path_card_list_item, parent, false);
        }

        CardInfo currentCard = getItem(position);

        TextView estimatedTimeTextView =listItemView.findViewById(R.id.estimated_time);
        estimatedTimeTextView.setText(currentCard.getmEstimatedTime());

        TextView numberOfStationsTextView =listItemView.findViewById(R.id.number_of_stations);
        numberOfStationsTextView.setText(currentCard.getmNumberOfStations());

        TextView costTextView =listItemView.findViewById(R.id.cost);
        costTextView.setText(String.valueOf(currentCard.getmCost()));

        TextView pathTextView =listItemView.findViewById(R.id.path);
        pathTextView.setText(currentCard.getmRoute());

        return listItemView;
    }
}

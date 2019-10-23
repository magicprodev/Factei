package ma.ormvasm.factei;

import android.content.Context;
import android.graphics.Color;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ma.ormvasm.factei.DAO.Cmv;

public class SpinCmvAdapter extends ArrayAdapter<Cmv> {

    private Context context;
    private  ArrayList<Cmv> values;

    public SpinCmvAdapter(Context context, int textViewResourceId,
                          ArrayList<Cmv> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount(){
        return values.size();
    }

    @Override
    public Cmv getItem(int position){
        return values.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setTextSize(18);
        label.setText(values.get(position).getCmv());

        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = new TextView (new ContextThemeWrapper(context, R.style.spinnerItemStyle), null, 0);


        label.setText(values.get(position).getCmv());

        return label;
    }
}


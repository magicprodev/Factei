package ma.ormvasm.factei;

import android.content.Context;
import android.graphics.Color;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ma.ormvasm.factei.DAO.Secteur;

public class SpinSecteurAdapter extends ArrayAdapter<Secteur> {

    // Your sent context
    private Context context;
    // Your custom values for the spinnerExam (User)
    private  ArrayList<Secteur> values;

    public SpinSecteurAdapter(Context context, int textViewResourceId,
                              ArrayList<Secteur> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount(){
        return values.size();
    }

    @Override
    public Secteur getItem(int position){
        return values.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }


    // And the "magic" goes here
    // This is for the "passive" state of the spinnerExam
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinnerExam item
        // TextView label = new TextView(context);

        //label.setTextColor(Color.BLACK);
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setTextSize(18);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.setText(values.get(position).getSecteur());

        // And finally return your dynamic (or custom) view for each spinnerExam item
        return label;
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        //TextView label = new TextView(context);
        //label.setTextColor(Color.BLACK);
        TextView label = new TextView (new ContextThemeWrapper(context, R.style.spinnerItemStyle), null, 0);


        label.setText(values.get(position).getSecteur());

        return label;
    }
}


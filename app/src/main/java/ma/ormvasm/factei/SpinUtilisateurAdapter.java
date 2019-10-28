package ma.ormvasm.factei;

import android.content.Context;
import android.graphics.Color;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ma.ormvasm.factei.DAO.Utilisateur;

public class SpinUtilisateurAdapter extends ArrayAdapter<Utilisateur> {

    private Context context;
    private  ArrayList<Utilisateur> values;

    public SpinUtilisateurAdapter(Context context, int textViewResourceId,
                          ArrayList<Utilisateur> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount(){
        return values.size();
    }

    @Override
    public Utilisateur getItem(int position){
        if (position==-1)  return null;
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
        label.setText(values.get(position).getUtilisateur());

        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = new TextView (new ContextThemeWrapper(context, R.style.spinnerItemStyle), null, 0);


        label.setText(values.get(position).getUtilisateur());

        return label;
    }



}


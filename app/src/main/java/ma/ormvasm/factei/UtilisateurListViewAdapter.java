package ma.ormvasm.factei;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import ma.ormvasm.factei.DAO.Utilisateur;

public class UtilisateurListViewAdapter extends BaseAdapter {

    private List<Utilisateur> dataArray;
    private Context context ;
    private static LayoutInflater inflater=null;
    private int position=-1;
    private int lastPosition;

    public UtilisateurListViewAdapter(List<Utilisateur> listArray ,Context c) {
        this.dataArray = listArray;
        this.context = c;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    public void setPosition(int i){
        this.position = i;
    }
    @Override
    public int getCount() {
        return this.dataArray.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }




    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ListCell cell;
        if (convertView==null){

            convertView = inflater.inflate(R.layout.custom_row_utilisateur,null);
            cell = new ListCell();
            cell.codeUtilisateur=(TextView)convertView.findViewById(R.id.txtCodeUtilisateur);
            cell.utilisateur=(TextView)convertView.findViewById(R.id.txtUtilisateur);
            cell.codeCmv=(TextView)convertView.findViewById(R.id.txtCodeCmv);

            convertView.setTag(cell);
        }
        else {
            cell = (ListCell) convertView.getTag();
        }

        Utilisateur item = this.dataArray.get(i);
        cell.codeUtilisateur.setText(item.getCode_utilisateur()+"");
        cell.utilisateur.setText(item.getUtilisateur()+"");
        cell.codeCmv.setText(item.getCode_cmv()+"");


        if (this.position  == i){
            convertView.setBackgroundResource(R.color.green_200);
        }
        else{
            convertView.setBackgroundResource(R.color.colorWhiteDefault);
        }

        return convertView;
    }

    private class ListCell
    {
        private TextView codeUtilisateur;
        private TextView utilisateur;
        private TextView codeCmv;

    }

}

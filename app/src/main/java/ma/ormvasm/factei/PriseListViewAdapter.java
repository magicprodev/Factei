package ma.ormvasm.factei;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import ma.ormvasm.factei.DAO.Prise;

public class PriseListViewAdapter extends BaseAdapter {

    private List<Prise> dataArray;
    private Context context ;
    private static LayoutInflater inflater=null;
    private int position=-1;
    private int lastPosition;

    public PriseListViewAdapter(List<Prise> listArray ,Context c) {
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

            convertView = inflater.inflate(R.layout.custom_row_prise,null);
            cell = new ListCell();
            cell.codePrise=(TextView)convertView.findViewById(R.id.txtCodePrise);
            cell.prise=(TextView)convertView.findViewById(R.id.txtPrise);
            cell.antenne=(TextView)convertView.findViewById(R.id.txtAntenne);
            cell.secteur=(TextView)convertView.findViewById(R.id.txtSecteur);

            convertView.setTag(cell);
        }
        else {
            cell = (ListCell) convertView.getTag();
        }


        Prise item = this.dataArray.get(i);
        cell.codePrise.setText(item.getCode_prise()+"");
        cell.prise.setText(item.getN_prise()+"");
        cell.antenne.setText(item.getAntenne()+"");
        cell.secteur.setText(item.getSecteur()+"");


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
        private TextView codePrise;
        private TextView prise;
        private TextView antenne;
        private TextView secteur;

    }

}

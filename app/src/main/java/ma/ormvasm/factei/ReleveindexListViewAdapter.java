package ma.ormvasm.factei;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import ma.ormvasm.factei.DAO.Releveindex;

public class ReleveindexListViewAdapter extends BaseAdapter {

    private List<Releveindex> dataArray;
    private Context context ;
    private static LayoutInflater inflater=null;
    private int position=-1;
    private int lastPosition;

    public ReleveindexListViewAdapter(List<Releveindex> listArray ,Context c) {
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

            convertView = inflater.inflate(R.layout.custom_row_releveindex,null);
            cell = new ListCell();
            cell.idReleveindex=(TextView)convertView.findViewById(R.id.txtIdReleveindex);
            cell.codePrise=(TextView)convertView.findViewById(R.id.txtCodePrise);
            cell.dateReleve=(TextView)convertView.findViewById(R.id.txtDateFinIndex);
            cell.etatPrise=(TextView)convertView.findViewById(R.id.txtEtatPrise);
            cell.indexFin=(TextView)convertView.findViewById(R.id.txtIndexFin);
            convertView.setTag(cell);
        }
        else {
            cell = (ListCell) convertView.getTag();
        }

        Releveindex item = this.dataArray.get(i);
        cell.idReleveindex.setText(item.getId_releveindex()+"");
        cell.idReleveindex.setVisibility(View.GONE);
        cell.codePrise.setText(item.getCode_prise()+"");
        cell.dateReleve.setText(Helper.convertFormatDate(item.getDate_fin_index())+"");
        cell.etatPrise.setText(item.getCode_etat_prise()+"");
        DecimalFormat decim = new DecimalFormat("#,###");
        cell.indexFin.setText(decim.format(item.getIndex_fin())+"");


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
        private TextView idReleveindex;
        private TextView codePrise;
        private TextView dateReleve;
        private TextView etatPrise;
        private TextView indexFin;


    }

}

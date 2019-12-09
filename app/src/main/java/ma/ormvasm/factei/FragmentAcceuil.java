package ma.ormvasm.factei;


import android.os.Bundle;

import android.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ma.ormvasm.factei.DAO.Cmv;
import ma.ormvasm.factei.DAO.CmvDAO;
import ma.ormvasm.factei.DAO.Parametre;
import ma.ormvasm.factei.DAO.ParametreDAO;
import ma.ormvasm.factei.DAO.Utilisateur;
import ma.ormvasm.factei.DAO.UtilisateurDAO;


public class FragmentAcceuil extends Fragment  {
    TextView txtcmv;
    TextView txtutilisateur;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View myView;
        myView = inflater.inflate(R.layout.activity_fragment_acceuil, container, false);
        getActivity().setTitle(getString(R.string.app_name));

        txtcmv=(TextView) myView.findViewById(R.id.cmv);
        txtutilisateur=(TextView) myView.findViewById(R.id.utilisateur);

        Parametre p ;
        ParametreDAO pdao=new ParametreDAO(getActivity());

        p=pdao.getData("CODE_CMV");

        if (TextUtils.isEmpty(p.getValeur_parametre())){
            txtcmv.setText(txtcmv.getText()+": ");
        }
        else{
            CmvDAO cdao = new CmvDAO(getActivity());
            Cmv c;
            c=cdao.getData(p.getValeur_parametre());

            if (c!=null){
                txtcmv.setText(txtcmv.getText()+": "+c.getCmv());
                }
            }



        p=pdao.getData("UTILISATEUR");


        if (TextUtils.isEmpty(p.getValeur_parametre())){
            txtutilisateur.setText(txtutilisateur.getText()+" : ");
        }
        else{
            UtilisateurDAO udao = new UtilisateurDAO(getActivity());
            Utilisateur u;
            u=udao.getData(p.getValeur_parametre());

            if (u!=null){
                txtutilisateur.setText(txtutilisateur.getText()+" : "+u.getUtilisateur());
            }
        }



        return  myView;
    }
}

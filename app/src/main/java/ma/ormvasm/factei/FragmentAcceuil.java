package ma.ormvasm.factei;


import android.os.Bundle;

import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FragmentAcceuil extends Fragment  {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View myView;
        myView = inflater.inflate(R.layout.activity_fragment_acceuil, container, false);

        return  myView;
    }
}
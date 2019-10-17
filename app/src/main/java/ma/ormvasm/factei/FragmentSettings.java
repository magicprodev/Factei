package ma.ormvasm.factei;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentSettings extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View myView;
        myView = inflater.inflate(R.layout.activity_fragment_acceuil, container, false);
        getActivity().setTitle(getString(R.string.app_name));
        return  myView;
    }


}

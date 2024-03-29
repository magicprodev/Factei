package ma.ormvasm.factei;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import ma.ormvasm.factei.DAO.Parametre;
import ma.ormvasm.factei.DAO.ParametreDAO;
import ma.ormvasm.factei.DAO.Utilisateur;
import ma.ormvasm.factei.DAO.UtilisateurDAO;

public class LoginActivity extends  BaseActivity {

    UtilisateurDAO udao ;
    Utilisateur u;
    ParametreDAO pdao;
    Parametre p;
    ScrollView scroll;
    RelativeLayout footer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        attachKeyboardListeners();

        final EditText usernameEditText = findViewById(R.id.txtutilisateur);
        final EditText passwordEditText = findViewById(R.id.txtmotpasse);
        final Button loginButton = findViewById(R.id.btnlogin);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);
        scroll = findViewById(R.id.scroll);
        footer =findViewById(R.id.footer);

        //scroll.scrollTo(0, (int) loginButton.getY());



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                udao=new UtilisateurDAO(LoginActivity.this);
                u = udao.getUtilisateur(usernameEditText.getText()+"",passwordEditText.getText()+"");

                if (u!=null){
                    String grp = u.getGroupe();
                    pdao =new ParametreDAO(LoginActivity.this);
                    p=pdao.getData("UTILISATEUR");
                    String usr =p.getValeur_parametre();

                    if (grp.equals("AIG") && !(usr.equals(u.getCode_utilisateur())) ){
                        Helper.showMessage(LoginActivity.this,getString(R.string.user_not_authorized_for_this_device),getString(R.string.title_login),R.drawable.ic_error_red);
                    }
                    else {
                        pdao = new ParametreDAO(LoginActivity.this);
                        Parametre p = pdao.getData("UTILISATEUR_CNX");
                        p.setValeur_parametre(u.getCode_utilisateur());
                        pdao.modifier(p);

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else{
                    Helper.showMessage(LoginActivity.this,getString(R.string.invalid_username),getString(R.string.title_login),R.drawable.ic_error_red);
                }

                loadingProgressBar.setVisibility(View.GONE);

            }
        });
    }

    @Override
    protected void onShowKeyboard(int keyboardHeight) {
        // do things when keyboard is shown
        scroll.fullScroll(View.FOCUS_DOWN);
    }

    @Override
    protected void onHideKeyboard() {
        // do things when keyboard is hidden
        scroll.fullScroll(View.FOCUS_UP);
    }

}

package adaz.urbantick_ibratec.Gui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import adaz.urbantick_ibratec.Model.Usuario;
import adaz.urbantick_ibratec.R;
import adaz.urbantick_ibratec.Retrofit.IUsuarioREST;
import adaz.urbantick_ibratec.SQLiteHelper;
import adaz.urbantick_ibratec.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;


public class MainActivity extends AppCompatActivity {

    ProgressDialog dialog;

    private static final String TAG = ConsultaActivity.class.getSimpleName();
    private EditText editEmail;
    private EditText editSenha;
    private Button btnEntrar;
    private ProgressBar progressBar;
    private SessionManager sessionManager;
    private SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText email = (EditText) findViewById(R.id.editEmail);
        final EditText senha = (EditText) findViewById(R.id.editSenha);
        Button adicionar = (Button) findViewById(R.id.btnEntrar);

        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(MainActivity.this);
                dialog.setMessage("Carregando...");
                dialog.setCancelable(false);
                dialog.show();

                Usuario u = new Usuario();
                u.setEmail(email.getText().toString());
                u.setSenha(senha.getText().toString());

                IUsuarioREST iUsuarioREST = IUsuarioREST.retrofit.create(IUsuarioREST.class);
                final Call<Void> call = iUsuarioREST.login(u);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            Toast.makeText(getBaseContext(), "Seja bem vindo!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, ConsultaActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        Toast.makeText(getBaseContext(), "Não foi possível fazer a conexão", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}

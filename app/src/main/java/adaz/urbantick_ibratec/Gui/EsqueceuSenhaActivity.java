package adaz.urbantick_ibratec.Gui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import adaz.urbantick_ibratec.Model.Usuario;
import adaz.urbantick_ibratec.R;
import adaz.urbantick_ibratec.Retrofit.IUsuarioREST;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by lucasnascimento on 25/09/17.
 */

public class EsqueceuSenhaActivity extends AppCompatActivity {

    private Button btnEsqueceuSenha;
    private EditText edtEmail;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueceu_senha);

        final EditText edtEmail = (EditText) findViewById(R.id.editEmail2);
        Button btnEsqueceuSenha = (Button) findViewById(R.id.btnEsqueceuSenha);

        btnEsqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(EsqueceuSenhaActivity.this);
                dialog.setMessage("Carregando...");
                dialog.setCancelable(false);
                dialog.show();

                Usuario u = new Usuario();
                u.setEmail(edtEmail.getText().toString());

                IUsuarioREST iUsuarioREST = IUsuarioREST.retrofit.create(IUsuarioREST.class);
                final Call<Void> call = iUsuarioREST.resetPassword(u);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            Toast.makeText(getBaseContext(), "Solicitação enviada com sucesso! \n Verifique seu email", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(EsqueceuSenhaActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getBaseContext(), "Ocorreu um erro ao processar a requisição", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EsqueceuSenhaActivity.this, MainActivity.class);
        startActivity(intent);
    }
}

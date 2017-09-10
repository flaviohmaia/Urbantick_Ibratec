package adaz.urbantick_ibratec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editEmail;
    private EditText editSenha;
    private Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editEmail = (EditText) findViewById(R.id.editEmail);
        editSenha = (EditText) findViewById(R.id.editSenha);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);

    }

    public void doLogin(View view){
        //checaRestricoes();
        Intent intent = new Intent(getApplicationContext(), ConsultaActivity.class);
        startActivity(intent);
    }


    private void checaRestricoes(){
        if(editEmail.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Informe o email", Toast.LENGTH_LONG).show();
        }

        if(editSenha.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Informe a senha", Toast.LENGTH_LONG).show();
        }
    }
}

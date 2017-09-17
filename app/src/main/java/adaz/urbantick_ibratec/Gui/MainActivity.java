package adaz.urbantick_ibratec.Gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import adaz.urbantick_ibratec.AppConfig;
import adaz.urbantick_ibratec.AppController;
import adaz.urbantick_ibratec.R;
import adaz.urbantick_ibratec.SQLiteHelper;
import adaz.urbantick_ibratec.SessionManager;


public class MainActivity extends AppCompatActivity {

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

        editEmail = (EditText) findViewById(R.id.editEmail);
        editSenha = (EditText) findViewById(R.id.editSenha);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        db = new SQLiteHelper(getApplicationContext());
        progressBar = new ProgressBar(this);
        sessionManager = new SessionManager(getApplicationContext());

        if(sessionManager.isLoggedIn()){
            Intent intent = new Intent(MainActivity.this, ConsultaActivity.class);
            startActivity(intent);
            finish();
        }

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmail.getText().toString().trim();
                String senha = editSenha.getText().toString().trim();

                if(!email.isEmpty() && !senha.isEmpty()){
                    doLogin(email, senha);
                }else{
                    Toast.makeText(getApplicationContext(), "Informe suas credenciais", Toast.LENGTH_LONG).show();
                }
            }
        });
    }



    public void doLogin(final String email, final String senha) {

        String tag_string_req = "req_login";

        StringRequest strRequest = new StringRequest(Request.Method.POST, AppConfig.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login response: " + response.toString());

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean error = jsonObject.getBoolean("error");

                    if (!error) {
                        sessionManager.setLogin(true);

                        JSONObject usuario = jsonObject.getJSONObject("usuario");
                        String nome = usuario.getString("nome");
                        String email = usuario.getString("email");
                        String senha = usuario.getString("senha");

                        db.adicionar(nome, email, senha);

                        Intent intent = new Intent(MainActivity.this, ConsultaActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        String errorMsg = jsonObject.getString("errorMsg");
                        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("senha", senha);

                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(strRequest, tag_string_req);
    }
}

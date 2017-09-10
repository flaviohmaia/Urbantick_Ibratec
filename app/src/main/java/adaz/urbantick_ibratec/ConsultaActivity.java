package adaz.urbantick_ibratec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class ConsultaActivity extends AppCompatActivity {

    private Spinner spinCategoria;
    private Spinner spinSubCategoria;
    private Spinner spinEstado;
    private EditText editCidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        spinCategoria = (Spinner) findViewById(R.id.editCategoria);
        spinSubCategoria = (Spinner) findViewById(R.id.editSubCategoria);
        spinEstado = (Spinner) findViewById(R.id.editUf);
        editCidade = (EditText) findViewById(R.id.editCidade);
    }

    public void buscarFornecedor(View view){
        Intent intent = new Intent(getApplicationContext(), FornecedorListaActivity.class);
        startActivity(intent);
    }
}

package adaz.urbantick_ibratec.Gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import adaz.urbantick_ibratec.R;

public class ConsultaActivity extends AppCompatActivity {

    Spinner spinCategoria;
    Spinner spinSubCategoria;
    Spinner spinEstado;
    EditText editCidade;
    Button btnBuscar1;

    private void buscarUsuarios(){
        Intent intent = new Intent(ConsultaActivity.this, ListagemFornecedorActivity.class);
        String uf = spinEstado.getSelectedItem().toString();
        String cidade = editCidade.getText().toString();
        String categoria = spinCategoria.getSelectedItem().toString();
        String subcategoria = spinSubCategoria.getSelectedItem().toString();

        Bundle bundle = new Bundle();

        bundle.putString("uf", uf);
        bundle.putString("cidade", cidade);
        bundle.putString("categoria", categoria);
        bundle.putString("subcategoria", subcategoria);

        intent.putExtras(bundle);

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        Toolbar tlb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tlb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Urbantick");

        btnBuscar1 = (Button) findViewById(R.id.btnBuscar);
        spinCategoria = (Spinner) findViewById(R.id.editCategoria);
        spinSubCategoria = (Spinner) findViewById(R.id.editSubCategoria);
        spinEstado = (Spinner) findViewById(R.id.editUf);
        editCidade = (EditText) findViewById(R.id.editCidade);

        btnBuscar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarUsuarios();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

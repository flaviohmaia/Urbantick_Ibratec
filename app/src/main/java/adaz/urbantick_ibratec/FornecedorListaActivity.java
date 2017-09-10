package adaz.urbantick_ibratec;

import android.support.annotation.NonNull;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import adaz.urbantick_ibratec.Model.Fornecedor;

public class FornecedorListaActivity extends AppCompatActivity{

    List<Fornecedor> listaFornecedor;
    ArrayAdapter<Fornecedor> adapterFornecedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fornecedor_lista);

        listaFornecedor = carregarFornecedor();
        adapterFornecedor = new ArrayAdapter<Fornecedor>(this, android.R.layout.simple_list_item_1, listaFornecedor);
    }

    private List<Fornecedor> carregarFornecedor() {
        List<Fornecedor> f = new ArrayList<Fornecedor>();
        f.add(new Fornecedor(1, "Mateus Martins", "Fumado", "Cinegrafista", "", "32254411", "Lentes Próprias"));
        f.add(new Fornecedor(2, "Flávio Maia", "Maia", "Músico", "Guitarrista", "32154698", "Gibson"));
        f.add(new Fornecedor(3, "Lucas Nascimento", "Narigão", "Técnico de Som", "", "124578986", "Mesas Digitais"));
        f.add(new Fornecedor(4, "José Edson", "Coentro", "Ator", "Pornô", "32214569", "Ator de Filmes Gay"));

        return f;
    }
}

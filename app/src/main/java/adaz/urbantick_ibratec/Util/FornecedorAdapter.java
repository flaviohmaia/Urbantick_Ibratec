package adaz.urbantick_ibratec.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import adaz.urbantick_ibratec.Model.Fornecedor;
import adaz.urbantick_ibratec.R;

/**
 * Created by lucasnascimento on 08/10/17.
 */

public class FornecedorAdapter extends ArrayAdapter<Fornecedor> {
    private final Context context;
    private final List<Fornecedor> elementos;

    public FornecedorAdapter(Context context, List<Fornecedor> elementos) {
        super(context, R.layout.linha, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.linha, parent, false);//tira o rg véi

        TextView nome = (TextView) rowView.findViewById(R.id.txtNomeFornecedor);
        TextView categoria = (TextView) rowView.findViewById(R.id.txtCategoria);
        TextView telefone = (TextView) rowView.findViewById(R.id.txtTelefone);


        nome.setText(elementos.get(position).getApelido());
        categoria.setText(elementos.get(position).getCategoria());
        telefone.setText(elementos.get(position).getTelefone());

        return rowView;
    }
}

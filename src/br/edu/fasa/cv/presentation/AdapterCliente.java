package br.edu.fasa.cv.presentation;

import java.util.List;

import br.edu.fasa.cv.R;
import br.edu.fasa.cv.domainmodel.Cliente;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterCliente extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Cliente> itens;

    public AdapterCliente(Context context, List<Cliente> itens) {
        //Itens que preencheram o listview
        this.itens = itens;
        //responsavel por pegar o Layout do item.
        mInflater = LayoutInflater.from(context);
    }

    /**
     * Retorna a quantidade de itens
     *
     * @return
     */
    public int getCount() {
        return itens.size();
    }

    /**
     * Retorna o item de acordo com a posicao dele na tela.
     *
     * @param position
     * @return
     */
    public Cliente getItem(int position) {
        return itens.get(position);
    }

    /**
     * Sem implementa��o
     *
     * @param position
     * @return
     */
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        //Pega o item de acordo com a pos��o.
        Cliente item = itens.get(position);
        //infla o layout para podermos preencher os dados
        view = mInflater.inflate(R.layout.item_list, null);

        //atravez do layout pego pelo LayoutInflater, pegamos cada id relacionado
        //ao item e definimos as informa��es.
        ((TextView) view.findViewById(R.id.listnome)).setText(item.getNome());
        ((TextView) view.findViewById(R.id.listtelefone)).setText(item.getTelefone());

        return view;
    }
}

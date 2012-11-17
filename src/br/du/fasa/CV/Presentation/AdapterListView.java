package br.du.fasa.CV.Presentation;

import java.util.List;

import br.du.fasa.CV.DomainModel.Cliente;
import br.edu.fasa.CV.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterListView extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Cliente> itens;

    public AdapterListView(Context context, List<Cliente> itens) {
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
     * Sem implementação
     *
     * @param position
     * @return
     */
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        //Pega o item de acordo com a posção.
        Cliente item = itens.get(position);
        //infla o layout para podermos preencher os dados
        view = mInflater.inflate(R.layout.item_list, null);

        //atravez do layout pego pelo LayoutInflater, pegamos cada id relacionado
        //ao item e definimos as informações.
        ((TextView) view.findViewById(R.id.listnome)).setText(item.getNome());
        ((TextView) view.findViewById(R.id.listtelefone)).setText(item.getTelefone());

        return view;
    }
}

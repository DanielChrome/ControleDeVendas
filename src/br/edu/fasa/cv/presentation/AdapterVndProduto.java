package br.edu.fasa.cv.presentation;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.edu.fasa.cv.R;
import br.edu.fasa.cv.domainmodel.VendaProduto;

public class AdapterVndProduto extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<VendaProduto> itens;

    public AdapterVndProduto(Context context, List<VendaProduto> itens) {
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
    public VendaProduto getItem(int position) {
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
    	VendaProduto item = itens.get(position);
        //infla o layout para podermos preencher os dados
        view = mInflater.inflate(R.layout.produto_vndlist, null);

        //atravez do layout pego pelo LayoutInflater, pegamos cada id relacionado
        //ao item e definimos as informações.
        ((TextView) view.findViewById(R.id.list_vndp_descricao)).setText(item.getProduto().getDescricao());
        ((TextView) view.findViewById(R.id.list_vndp_qtde)).setText(Double.toString(item.getQuantidade()));
        ((TextView) view.findViewById(R.id.list_vndp_total)).setText(Double.toString(item.getTotal()));
        return view;
    }
}

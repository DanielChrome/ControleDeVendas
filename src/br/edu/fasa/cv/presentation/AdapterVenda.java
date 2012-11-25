package br.edu.fasa.cv.presentation;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.edu.fasa.cv.R;
import br.edu.fasa.cv.dataaccess.VendaProdutoDAO;
import br.edu.fasa.cv.domainmodel.Venda;
import br.edu.fasa.cv.util.Util;

public class AdapterVenda extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Venda> itens;
    private Context ctx;

    public AdapterVenda(Context context, List<Venda> itens) {
        //Itens que preencheram o listview
        this.itens = itens;
        ctx = context;
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
    public Venda getItem(int position) {
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
    	Venda item = itens.get(position);
        //infla o layout para podermos preencher os dados
        view = mInflater.inflate(R.layout.venda_list, null);
        VendaProdutoDAO vpdao = new VendaProdutoDAO(ctx);
        //atravez do layout pego pelo LayoutInflater, pegamos cada id relacionado
        //ao item e definimos as informações.
        ((TextView) view.findViewById(R.id.list_vnd_codigo)).setText(Long.toString(item.getId()));
        ((TextView) view.findViewById(R.id.list_vnd_cliente)).setText(item.getCliente().getNome());
        ((TextView) view.findViewById(R.id.list_vnd_data)).setText(Util.dateToStr(item.getDataVenda()));
        ((TextView) view.findViewById(R.id.list_vnd_total)).setText(Double.toString(vpdao.getTotalVenda(item)));
        return view;
    }
}

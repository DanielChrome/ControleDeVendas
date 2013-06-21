package br.edu.fasa.cv.presentation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import br.edu.fasa.cv.dataaccess.ClienteDAO;
import br.edu.fasa.cv.domainmodel.Cliente;

public class Replicacao extends AsyncTask<Integer, Integer, Void>{
    public static int replicados = 0;
    public static ClienteDAO cdao;
	public static Cliente cliente;
	public static List<Cliente> clientes;
	public static Context ctx;
	
	@Override
	protected Void doInBackground(Integer... arg0) {
		StringBuilder strURL = new StringBuilder();
		cdao = new ClienteDAO(ctx);
		clientes = cdao.listarTodosOrdemNome();
		Iterator<Cliente> i = clientes.iterator();
		while(i.hasNext()){
			cliente = i.next();
			strURL.append("http://10.0.2.2/salvar.php?nome=");
			strURL.append(cliente.getNome());
			strURL.append("&endereco=");
			strURL.append(cliente.getEndereco());
			strURL.append("&telefone=");
			strURL.append(cliente.getTelefone());
			
			try{
				URL url = new URL(strURL.toString().replace(" ", "%20"));
				HttpURLConnection http = (HttpURLConnection) url.openConnection();
				InputStreamReader ips = new InputStreamReader(http.getInputStream());
				BufferedReader line = new BufferedReader(ips);
				
				String linhaRetorno = line.readLine();
				
				if(linhaRetorno.equals("Y")){
					cdao.deletar(cliente);
					replicados++;
				}
			}catch(Exception e){
				publishProgress(2);
			}
			
		}
		publishProgress(1,replicados);
		return null;
	}
	
	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
		if(values[0]==1){
		    Toast.makeText(ctx,Integer.toString(values[1])+" registros replicados!", Toast.LENGTH_LONG).show();
		}
		else
		if(values[0]==2){
			Toast.makeText(ctx,"Erro, nenhum registro replicado!", Toast.LENGTH_LONG).show();
		}
	}
}

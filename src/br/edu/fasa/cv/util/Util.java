package br.edu.fasa.cv.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.text.method.KeyListener;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class Util {
	
	public static AlertDialog createDialog(Context context, int title,
			int message, int positiveLabel, OnClickListener positiveListener,
			int negativeLabel, OnClickListener negativeListener) {

		Log.d("Utility", title + " - " + message);
		Log.d("Utility", title + " - " + positiveLabel);
		Log.d("Utility", positiveListener == null ? "posL nulo" : "posL OK");
		Log.d("Utility", title + " - " + negativeLabel);
		Log.d("Utility", negativeListener == null ? "negL nulo" : "negL OK");

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton(positiveLabel, positiveListener);
		builder.setNegativeButton(negativeLabel, negativeListener);

		return builder.create();
	}
	
	public static AlertDialog createDialog(Context context, int title,
			int message, int Label, OnClickListener Listener) {

		Log.d("Utility", title + " - " + message);
		Log.d("Utility", title + " - " + Label);
		Log.d("Utility", Listener == null ? "posL nulo" : "posL OK");

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton(Label, Listener);

		return builder.create();
	}
	
	public static void toast(Context ctx,String message){
		Toast.makeText(ctx,message,Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * Função para habilitar e desabilitar campos EditText
	 * @param e Objeto EditText a ser alterado
	 * @param estado Estado que o objeto receberá
	 */
	public static void setEstado(EditText e,Boolean estado){
    	if (!estado){
    		e.setTag(e.getKeyListener());
    		e.setKeyListener(null);
    		e.setEnabled(false);
    	}
    	else{
    		e.setKeyListener((KeyListener)e.getTag());
    		e.setEnabled(true);
    	}    	
    }
	/**
	 * Função generica para validar campos vazios ou nulos
	 * @param ctx - contexto da aplicação em questão
	 * @param e - Objeto EditText que será validado
	 * @param campo - Descrição do campo validado para aparecer no Toast
	 * @return True se o campo estiver preenchido, False se ele estiver vazio ou nulo
	 */
	public static Boolean validaCampo(Context ctx,EditText e,String campo){
		if ((e.getText().toString() == null) ||("".equals(e.getText().toString().trim()))){
			toast(ctx, "Campo "+campo+" não pode ficar vazio");
    		e.requestFocus();
    		return false;
    	}
    	return true;
    }
	
	public static Date strToDate(String data){
		Date dt = new Date(data);
		return dt;
	}
	
	public static String dateToStr(Date data){
		return new SimpleDateFormat("dd/MM/yyyy", Locale.US)
		.format(data).toString();
	}

}


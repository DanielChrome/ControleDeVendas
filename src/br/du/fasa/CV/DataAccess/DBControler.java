package br.du.fasa.CV.DataAccess;

import java.io.IOException;

import android.content.Context;
import android.util.Log;

import com.db4o.Db4o;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

public class DBControler extends Db4o {
	
	private static ObjectContainer oc = null;
	private Context context;
	
	public DBControler(Context ctx) {
    	context = ctx;
    }
	
	public ObjectContainer db(){
    	try {
    		if (oc == null || oc.ext().isClosed()) {
                oc = Db4oEmbedded.openFile(dbConfig(), caminhoDoBanco(context));
    		}
    		return oc;
    	} catch (Exception e) {
        	Log.e(DBControler.class.getName(), e.toString());
        	return null;
        }
    }
	
	 private EmbeddedConfiguration dbConfig() throws IOException {
	    EmbeddedConfiguration c = Db4oEmbedded.newConfiguration();
	    //Index entries by Id
	    //c.common().objectClass(PassEntry.class).objectField("id").indexed(true); 
	    //Configure proper activation + update depth
	    //TODO
	    return c;
	 }
	 
	 public String caminhoDoBanco(Context ctx) {
		return ctx.getDir("data", 0) + "/" + "android.db4o";
	 }
	 
	 public void close() {
		 if(oc != null){
	    	oc.close();
	    	oc = null;
	    }
	 }
	 
	
	 public void commit(){
	   	db().commit();
	 }
	    
	 public void rollback(){
	   	db().rollback();
	 }
}

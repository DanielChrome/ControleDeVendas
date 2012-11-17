package br.du.fasa.CV.Presentation;

import br.edu.fasa.CV.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CadCategoria extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cad_categoria);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cad_categoria, menu);
        return true;
    }
}

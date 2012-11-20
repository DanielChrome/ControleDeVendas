package br.edu.fasa.cv.presentation;

import br.edu.fasa.cv.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CadProduto extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cad_produto);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cad_produto, menu);
        return true;
    }
}

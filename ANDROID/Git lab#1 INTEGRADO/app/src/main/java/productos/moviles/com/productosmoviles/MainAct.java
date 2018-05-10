package productos.moviles.com.productosmoviles;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import productos.moviles.com.productosmoviles.Conexion.clConexion;

public class MainAct extends AppCompatActivity {

    clConexion cl = new clConexion();
    Button btn;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = super.findViewById(R.id.respuesta);
        btn = super.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.widget.EditText t = findViewById(R.id.ip);
                String res = cl.getOutputFromUrl("ProductosService?action=eco",t.getText().toString());
                txt.setText(res);
            }
        });
    }
}

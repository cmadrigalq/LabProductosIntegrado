package productos.moviles.com.lab_productos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import labProductosBE.LogicaNegocio.Producto;
import labProductosBE.LogicaNegocio.TipoProducto;
import productos.moviles.com.lab_productos.Conexion.Proxy;

public class MainAct extends AppCompatActivity {
    String url = "192.168.0.17:8084";
    Button btn;
    TextView txt;
    Proxy proxy =Proxy.instancia();
    Spinner s1;
    int tipoSeleccionado = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.btnAceptarA);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregar();
            }
        });
        proxy.getProductos();
        proxy.getTipos( url );
        cargarSpinner(proxy.getTipos());
        Button cancelar = findViewById(R.id.btnCancelarA);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    void cargarSpinner(List<TipoProducto>tipos){
        String[] tpsArr = new String[tipos.size()];
        for(int i = 0; i<tipos.size();i++){
            tpsArr[i] = tipos.get(i).getDescripcion();
        }
        s1 = findViewById(R.id.spinTipoP);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, tpsArr);
        s1.setAdapter(adapter);
        //para que cuando seleccionen un tipo el lo detecte
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                MainAct.this.tipoSeleccionado = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    void agregar(){
        EditText codigo = findViewById(R.id.txCodigo),
                nombre = findViewById(R.id.txNombre),
                precio = findViewById(R.id.txPrecio);
        String codigoStr = codigo.getText().toString();
        String nombreStr = nombre.getText().toString();
        String precioStr = precio.getText().toString();
        if(!validar(codigoStr,nombreStr,precioStr)){
            return;
        }
        CheckBox importa = findViewById(R.id.chkImportado);
        int precioD = Integer.parseInt(precioStr);
        Producto producto = new Producto(codigoStr,
                nombreStr,
                precioD,
                importa.isChecked(),
                proxy.getTipos().get(this.tipoSeleccionado));
        proxy.agregar(producto,url);
        mensaje("Producto enviado, por favor, verifique.");
    }

    boolean validar(String codigo,String nombre,String precio){
        boolean res = true;
        if(codigo.trim().isEmpty()){
            mensaje("Debe Ingresar el código del producto");
            res = false;
        }
        if(nombre.trim().isEmpty()){
            mensaje("Debe Ingresar el nombre del producto");
            res = false;
        }
        if(precio.trim().isEmpty()){
            mensaje("Debe Ingresar el precio del producto");
            res = false;
        }
        return res;
    }
    public void mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
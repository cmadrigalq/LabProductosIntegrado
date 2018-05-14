package productos.moviles.com.lab_productos;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import labProductosBE.LogicaNegocio.Producto;
import labProductosBE.LogicaNegocio.TipoProducto;
import productos.moviles.com.lab_productos.Conexion.Proxy;

public class ListaProductos extends AppCompatActivity {
    String url = "192.168.0.17:8084";
    Proxy proxy =Proxy.instancia();
    Spinner s1;
    List<Producto> listaProductos = null;
    int tipoSeleccionado = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);
        setTitle("Lista Productos");
        listaProductos = proxy.getProductos(url);
        proxy.getTipos(url);
        cargarSpinner(proxy.getTipos());
        dibujaTabla(listaProductos);
        addListeners();
    }

    void addListeners(){
        Button buscar = (Button) findViewById(R.id.btnBuscar);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaProductos = proxy.getProductos(url);
                Spinner spinCarrerasOnClick = findViewById(R.id.spinTipoT);
                if(!spinCarrerasOnClick.getSelectedItem().toString().equals(""))
                    listaProductos = productosFiltroTipo(spinCarrerasOnClick.getSelectedItem().toString());
                else
                    listaProductos = proxy.getProductos(url);
                dibujaTabla(listaProductos);
            }
        });
        Button productoNuevo = findViewById(R.id.btnNuevoProducto);
        productoNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaProductos.this,MainAct.class);
                startActivity(intent);
            }
        });
    }

    void cargarSpinner(List<TipoProducto>tipos){
        String[] tpsArr = new String[tipos.size()+1];
        tpsArr[0]="";
        for(int i = 0; i<tipos.size();i++){
            tpsArr[i+1] = tipos.get(i).getDescripcion();
        }
        s1 = findViewById(R.id.spinTipoT);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, tpsArr);
        s1.setAdapter(adapter);
        //para que cuando seleccionen un tipo el lo detecte
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ListaProductos.this.tipoSeleccionado = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private List<Producto> productosFiltroTipo(String descripcion){
        List<Producto> resultado = new ArrayList<>();
        for(int i = 0; i < listaProductos.size(); i++){
            if(listaProductos.get(i).getTipo().getDescripcion().equals(descripcion)){
                resultado.add(listaProductos.get(i));            }
        }
        return resultado;
    }

    private void dibujaTabla(List<Producto> p){
        TableLayout tabla = findViewById(R.id.idTableProductos);
        limpiaTabla(tabla);
        TableRow row;
        TextView tv1;
        TextView tv2;
        ImageButton btnEdit;
        TextView buscNombre = findViewById(R.id.txBuscNombreA);
        for(int i=0;i<p.size();i++){
            if( p.get(i).getNombre().toLowerCase().contains(buscNombre.getText().toString().toLowerCase())){
                row = new TableRow(getBaseContext());
                tv1 = new TextView(getBaseContext());
                tv2 = new TextView(getBaseContext());
                btnEdit = new ImageButton(getBaseContext());
                btnEdit.setImageResource(R.drawable.ic_if_new_24_103173);
                tv1.setGravity(Gravity.CENTER);
                tv2.setGravity(Gravity.CENTER);
                tv1.setPadding(15,15,15,15);
                tv2.setPadding(15,15,15,15);
                tv1.setText(p.get(i).getCodigo());
                tv2.setText(p.get(i).getNombre());
                //final Producto producto = p.get(i);
                btnEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*Intent iEditar = new Intent(MantenimientoAlumnosActivity.this,NuevoAlumno.class);
                        Log.d("Mensaje",listaCarreras.get(1).toString());
                        iEditar.putExtra("carreras", listaCarreras);
                        iEditar.putExtra("alumnoEdit",alum);
                        iEditar.putExtra("usuarioEdit",usuarioPorEditar(alum));
                        startActivityForResult(iEditar,request_Code);*/
                        mensaje("Mostrar datos");
                    }
                });

                row.addView(tv1);
                row.addView(tv2);
                row.addView(btnEdit);
                tabla.addView(row);
            }
        }
    }

    private void limpiaTabla(TableLayout tabla){
        int tam = tabla.getChildCount();
        for(int i=1; i<tam; i++){
            View fila = tabla.getChildAt(i);
            if(fila instanceof TableRow)
                ((ViewGroup) fila).removeAllViews();
        }
    }

    public void mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

}

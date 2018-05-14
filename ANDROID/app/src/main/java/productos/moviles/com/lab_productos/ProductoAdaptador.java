package productos.moviles.com.lab_productos;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import labProductosBE.LogicaNegocio.Producto;

public class ProductoAdaptador extends RecyclerView.Adapter<ProductoAdaptador.ViewHolderProducto> {

    ArrayList<Producto> listaProducto;

    public ProductoAdaptador(ArrayList<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }

    @Override
    public ViewHolderProducto onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.productos_item,null,false);
        return new ViewHolderProducto(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProducto holder, int position) {
        holder.tvCodigo.setText(listaProducto.get(position).getCodigo());
        holder.tvNombre.setText(listaProducto.get(position).getNombre());
        holder.tvTipoProd.setText(listaProducto.get(position).getTipo().getDescripcion());
        holder.tvPrecio.setText(listaProducto.get(position).getPrecio());
        holder.tvPorcentaje.setText(String.valueOf(listaProducto.get(position).getTipo().getPorcentaje()));
        holder.tvImpuesto.setText(String.valueOf(listaProducto.get(position).getImpuesto()));
        holder.tvPrecioFin.setText(String.valueOf(listaProducto.get(position).getPrecioFinal()));

    }

    @Override
    public int getItemCount() {
        return listaProducto.size();
    }


    public class ViewHolderProducto extends RecyclerView.ViewHolder {
        TextView tvCodigo, tvNombre, tvTipoProd, tvPrecio, tvPorcentaje, tvImpuesto, tvPrecioFin;
        public ViewHolderProducto(View itemView) {
            super(itemView);
            tvCodigo = (TextView) itemView.findViewById(R.id.tvCodigo);
            tvNombre = (TextView) itemView.findViewById(R.id.tvNombre);
            tvTipoProd = (TextView) itemView.findViewById(R.id.tvTipoProd);
            tvPrecio = (TextView) itemView.findViewById(R.id.tvPrecio);
            tvPorcentaje = (TextView) itemView.findViewById(R.id.tvPorcentaje);
            tvImpuesto = (TextView) itemView.findViewById(R.id.tvImpuesto);
            tvPrecioFin = (TextView) itemView.findViewById(R.id.tvPrecioFin);

        }
    }
}

package com.example.mercadoesclavospinaalan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ViewHolderProducto> {

    private List<Producto> listaDeProductos;

    public ProductosAdapter(List<Producto> listaDeProductos) {
        this.listaDeProductos = listaDeProductos;
    }

    @NonNull
    @Override
    public ViewHolderProducto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        View view = from.inflate(R.layout.celda_producto, parent, false);

        return new ViewHolderProducto(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProducto holder, int position) {
        Producto producto = this.listaDeProductos.get(position);
        holder.onBind(producto);
    }

    @Override
    public int getItemCount() {
        return listaDeProductos.size();
    }

    protected class ViewHolderProducto extends RecyclerView.ViewHolder{
        private ImageView imageViewProducto;
        private TextView textViewNombreProducto;
        private TextView textViewPrecioProducto;

        public ViewHolderProducto(@NonNull View itemView) {
            super(itemView);
            imageViewProducto = itemView.findViewById(R.id.celdaProductoImagenProducto);
            textViewNombreProducto = itemView.findViewById(R.id.celdaProductoNombreProducto);
        }

        public void onBind(Producto producto) {
            imageViewProducto.setImageResource(producto.getImagen());
            textViewNombreProducto.setText(producto.getNombre());
            textViewPrecioProducto.setText(producto.getPrecio());

        }
    }

}

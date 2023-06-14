package com.example.proyectocoches;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdaptador extends RecyclerView.Adapter<RecyclerViewAdaptador.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView marca, modelo, combustible, fechaMatriculacion, km, potencia, ubicacion, precio;
        private ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);

            marca = itemView.findViewById(R.id.tvMarca);
            modelo = itemView.findViewById(R.id.tvModelo);
            combustible = itemView.findViewById(R.id.tvCombustible);
            fechaMatriculacion = itemView.findViewById(R.id.tvFecha);
            km = itemView.findViewById(R.id.tvKm);
            potencia = itemView.findViewById(R.id.tvPotencia);
            ubicacion = itemView.findViewById(R.id.tvUbicacion);
            image = itemView.findViewById(R.id.idImagenVehiculo);
            precio = itemView.findViewById(R.id.tvPrecio);


        }
    }


    public List<VehiculosDos> listaUsuarios;

    public RecyclerViewAdaptador(List<VehiculosDos> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vehiculo, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.marca.setText(listaUsuarios.get(position).getMarca());
        holder.modelo.setText(listaUsuarios.get(position).getModelo());
        holder.combustible.setText(listaUsuarios.get(position).getCombustible());
        holder.fechaMatriculacion.setText(listaUsuarios.get(position).getFechaMatriculacion());
        holder.km.setText(listaUsuarios.get(position).getKm() + " km");
        holder.potencia.setText(listaUsuarios.get(position).getPotencia() + "cv");
        holder.ubicacion.setText(listaUsuarios.get(position).getUbicacion());
        holder.precio.setText(String.valueOf(listaUsuarios.get(position).getPrecio()) + "€");


        Picasso.get()
                .load(listaUsuarios.get(position).getImagen())
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

}

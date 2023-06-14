package com.example.proyectocoches;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdaptadorContacto extends RecyclerView.Adapter<RecyclerViewAdaptadorContacto.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nombre, apellidos, telefono, localizacion;
        private ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.tVNombre);
            apellidos = itemView.findViewById(R.id.tVApellidos);
            telefono = itemView.findViewById(R.id.tVTelefono);
            localizacion = itemView.findViewById(R.id.tVLocalidad);

            image = itemView.findViewById(R.id.idImagenItemContacto);



        }
    }


    public List<PersonaContacto> listaPersonas;

    public RecyclerViewAdaptadorContacto(List<PersonaContacto> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacto, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nombre.setText(listaPersonas.get(position).getNombre());
        holder.apellidos.setText(listaPersonas.get(position).getApellidos());
        holder.telefono.setText(listaPersonas.get(position).getTelefono());
        holder.localizacion.setText(listaPersonas.get(position).getLocalizacion());


        Picasso.get()
                .load(listaPersonas.get(position).getImagen())
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return listaPersonas.size();
    }

}

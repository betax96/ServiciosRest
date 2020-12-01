package co.edu.uac.apmoviles.serviciosrest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class AtractivosTuristicosAdapter extends ArrayAdapter<AtractivosTuristicos> {
    public AtractivosTuristicosAdapter(@NonNull Context context, @NonNull ArrayList<AtractivosTuristicos> atractivosTuristicos) {
        super(context, 0, atractivosTuristicos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        AtractivosTuristicos comp = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_atractivosturisticos, parent, false);
        }
        // Lookup view for data population
        TextView nombresitio = (TextView) convertView.findViewById(R.id.nombresitio);
        TextView tipo = (TextView) convertView.findViewById(R.id.tipo);
        TextView descripcion = (TextView) convertView.findViewById(R.id.descripcion);
        TextView nombremunicipio = (TextView) convertView.findViewById(R.id.nombremunicipio);
        TextView direccion = (TextView) convertView.findViewById(R.id.direccion);
        TextView telefono = (TextView) convertView.findViewById(R.id.telefono);
        // Populate the data into the template view using the data object
        nombresitio.setText(comp.getNombresitio());
        tipo.setText(comp.getTipo());
        descripcion.setText(comp.getDescripcion());
        nombremunicipio.setText(comp.getNombremunicipio());
        direccion.setText(comp.getDireccion());
        telefono.setText(comp.getTelefono());
        // Return the completed view to render on screen
        return convertView;
    }

}

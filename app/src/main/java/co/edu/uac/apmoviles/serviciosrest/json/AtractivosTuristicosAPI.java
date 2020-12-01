package co.edu.uac.apmoviles.serviciosrest.json;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import co.edu.uac.apmoviles.serviciosrest.AtractivosTuristicos;


public class AtractivosTuristicosAPI {

    public static final String API_URL = "https://www.datos.gov.co/resource/jj37-fvz6.json";
    public static final String API_TOKEN = "joBW2TDPtljnOlH9UepZ2ne36";

    public static ArrayList<AtractivosTuristicos> parseJSON(String jsonString) throws JSONException {
        JSONArray jsonArray = new JSONArray(jsonString);
        ArrayList<AtractivosTuristicos> list = new ArrayList<>();
        for (int i = 0 ; i<jsonArray.length(); i++) {
            JSONObject com = jsonArray.getJSONObject(i);
            String nombresitio = com.getString("nombresitio");
            String tipo = com.getString("tipo");
            String descripcion = com.getString("descripcion");
            String nombremunicipio = com.getString("nombremunicipio");
            String direccion = com.getString("direccion");
            String telefono = com.getString("telefono");
            AtractivosTuristicos co = new AtractivosTuristicos(nombresitio,tipo,descripcion,nombremunicipio,direccion,telefono);
            list.add(co);
        }
        return list;
    }
}

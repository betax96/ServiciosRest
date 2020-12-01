package co.edu.uac.apmoviles.serviciosrest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;

import java.util.ArrayList;

import co.edu.uac.apmoviles.serviciosrest.json.AtractivosTuristicosAPI;
import co.edu.uac.apmoviles.serviciosrest.json.JSONConnection;

public class ListadoAtractivosTuristicos extends AppCompatActivity {
    ArrayList<AtractivosTuristicos> comp;
    ListView listado;
    TextInputLayout tilFiltrar;
    Button btnFiltrar;

    WaitDialog waitDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_atractivosturisticos);
        tilFiltrar = findViewById(R.id.tilFiltrar);
        btnFiltrar = findViewById(R.id.btnFiltrar);
        waitDialog = new WaitDialog(this);

        listado = findViewById(R.id.listado);

        tilFiltrar.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0){
                    AtractivosTuristicosAdapter adapter = new AtractivosTuristicosAdapter(
                            ListadoAtractivosTuristicos.this, comp);
                    listado.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        btnFiltrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waitDialog.show();

                String text = tilFiltrar.getEditText().getText().toString();

                JSONConnection.getJsonString(AtractivosTuristicosAPI.API_URL+"?nombremunicipio="+text, AtractivosTuristicosAPI.API_TOKEN
                        , new JSONConnection.JSONResponseListener() {
                    @Override
                    public void onSuccess(String jsonString) {
                        try {
                            AtractivosTuristicosAdapter adapter = new AtractivosTuristicosAdapter(
                                    ListadoAtractivosTuristicos.this, AtractivosTuristicosAPI.parseJSON(jsonString));
                            listado.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            Log.e("Error",Log.getStackTraceString(e));
                            Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                        waitDialog.dismiss();
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e("Error",Log.getStackTraceString(e));
                        Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_LONG).show();
                        waitDialog.dismiss();
                    }
                });
            }
        });


        waitDialog.show();
        JSONConnection.getJsonString(AtractivosTuristicosAPI.API_URL, AtractivosTuristicosAPI.API_TOKEN
                , new JSONConnection.JSONResponseListener() {
                    @Override
                    public void onSuccess(String jsonString) {
                        try {
                            comp = AtractivosTuristicosAPI.parseJSON(jsonString);
                            AtractivosTuristicosAdapter adapter = new AtractivosTuristicosAdapter(
                                    ListadoAtractivosTuristicos.this, AtractivosTuristicosAPI.parseJSON(jsonString));
                            listado.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            Log.e("Error",Log.getStackTraceString(e));
                            Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                        waitDialog.dismiss();
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e("Error",Log.getStackTraceString(e));
                        Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_LONG).show();
                        waitDialog.dismiss();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        if(tilFiltrar.getEditText().hasFocus()&&tilFiltrar.getEditText().getText().length()>0){
            tilFiltrar.getEditText().setText("");
        }else{
            super.onBackPressed();
        }
    }
}
package com.example.tareafirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    Button btnagregar, btnactualizar, btneliminar;


   private List<String> listaresgistros = new ArrayList<String>();
   ArrayAdapter<String> registrosArrayAdapter;

String seleccionado;
   ListView listpersonas;
    EditText txtnombre, txtapellidos, txtgenero,txtforo;
    EditText txtdate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listpersonas = (ListView)findViewById(R.id.datospersonas);
        btnagregar = (Button) findViewById(R.id.btnagregar);
        btnactualizar = (Button) findViewById(R.id.btnactualizar);
        btneliminar = (Button) findViewById(R.id.btneliminar);
        txtgenero = (EditText) findViewById(R.id.txtgenero);
        txtnombre = (EditText) findViewById(R.id.txtnombre);
        txtforo = (EditText) findViewById(R.id.txtforo);
        txtapellidos = (EditText) findViewById(R.id.txtapellidos);
        txtdate = (EditText) findViewById(R.id.txtdate);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Registros");

        listdatos();

        listpersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                seleccionado = (String)  parent.getItemAtPosition(position);

            }
        });

        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Agregar();
            }
        });

    }

    private void listdatos() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Registros");

        registrosArrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, listaresgistros);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaresgistros.clear();
                for (DataSnapshot objSnapshot : snapshot.getChildren()){
                            Registros r = objSnapshot.getValue(Registros.class);
                            listaresgistros.add(r.getNombres() + " " +r.getApellidos() + " " +r.getGenero());


                            listpersonas.setAdapter(registrosArrayAdapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void Agregar(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Registros");


        String foro = txtnombre.getText().toString();
        String nombres = txtnombre.getText().toString();
        String apellidos = txtapellidos.getText().toString();
        String genero = txtgenero.getText().toString();
        String fechanacimiento = txtdate.getText().toString();

        Registros registros = new Registros(foro,nombres,apellidos,genero,fechanacimiento);
        registros.setUid(UUID.randomUUID().toString());
        databaseReference.child(registros.getUid()).setValue(registros);

        Toast.makeText(this, "Registro Agregado Correctamente", Toast.LENGTH_SHORT).show();

        limpiar();
    }


    public void limpiar(){
        txtapellidos.setText("");
        txtgenero.setText("");
        txtdate.setText("");
        txtnombre.setText("");
        txtforo.setText("");
    }

}
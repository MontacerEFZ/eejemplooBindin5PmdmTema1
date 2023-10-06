package montacer.elfazazi.ejemplobindin5pmdmtema1;

import android.content.Intent;
import android.os.Bundle;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import montacer.elfazazi.ejemplobindin5pmdmtema1.databinding.ActivityMainBinding;
import montacer.elfazazi.ejemplobindin5pmdmtema1.modelos.Alumno;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ActivityResultLauncher<Intent> launcherAlumno;
    private ArrayList<Alumno> listaAlumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        listaAlumnos = new ArrayList<>();

        inicializarLauncher();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //lanzar la actividad Alumno
                Toast.makeText(MainActivity.this, "gggg", Toast.LENGTH_SHORT).show();
                launcherAlumno.launch(new Intent(MainActivity.this, AddAlumnoActivity.class));



            }
        });
    }

    private void inicializarLauncher() {
        launcherAlumno = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK){
                            if (result.getData() != null && result.getData().getExtras() != null){
                                Alumno alumno = (Alumno) result.getData().getExtras().getSerializable("ALUMNO");
                                listaAlumnos.add(alumno);
                            }
                        }else {
                            Toast.makeText(MainActivity.this, "Accion cancelada", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}
/*
queda por hacer:
1. elemnto para mostrar la info del alumno en el principal(textview)
2. el conjunto de datos a mostrar (listaAlumnos)
3. contenedor para poner cada elemento alumno (scroll)
4.la logica para mostrar los elementos en el scroll del principal
 */
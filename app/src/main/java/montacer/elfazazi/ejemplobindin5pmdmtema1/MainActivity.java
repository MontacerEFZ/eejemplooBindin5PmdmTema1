package montacer.elfazazi.ejemplobindin5pmdmtema1;

import android.content.Intent;
import android.os.Bundle;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import montacer.elfazazi.ejemplobindin5pmdmtema1.databinding.ActivityMainBinding;
import montacer.elfazazi.ejemplobindin5pmdmtema1.modelos.Alumno;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ActivityResultLauncher<Intent> launcherAlumno;
    private ActivityResultLauncher<Intent> editAlumnoLauncher;

    private ArrayList<Alumno> listaAlumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        listaAlumnos = new ArrayList<>();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //lanzar la actividad Alumno
                launcherAlumno.launch(new Intent(MainActivity.this, AddAlumnoActivity.class));



            }
        });

        inicializarLauncher(); //es indiferente si lo ponemos antes o despues del metodo del boton pero nos aclaramos
            //mejor si lo ponemos despues
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
                                Log.e("LISTA", listaAlumnos.toString());
                                mostrarAlumnos();
                            }
                        }else {
                            Toast.makeText(MainActivity.this, "Accion cancelada", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        editAlumnoLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                //que ocurrira cuando vuelva de la actividad
            }
        });
    }

    private void mostrarAlumnos() {
        //eliminar lo q haya en el linear layout
        binding.contentMain.contenedorMain.removeAllViews(); //limpiar scroll entero

        for (Alumno alumno:listaAlumnos){
            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);

            View alumnoView = layoutInflater.inflate(R.layout.alumno_fila_view, null); //la R es un elemento que por defecto lee todos los elementos del layout
            TextView txtNombre = alumnoView.findViewById(R.id.lbNombreAlumnoView);
            TextView txtApellidos = alumnoView.findViewById(R.id.lbApellidosAlumnoView);
            TextView txtCiclo = alumnoView.findViewById(R.id.lbCicloAlumnoView);
            TextView txtGrupo = alumnoView.findViewById(R.id.lbGrupoAlumnoView);

            txtNombre.setText(alumno.getNombre());
            txtApellidos.setText(alumno.getApellidos());
            txtCiclo.setText(alumno.getCiclo());
            txtGrupo.setText(String.valueOf(alumno.getGrupo()));

            alumnoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //enviar alumno
                        Intent intent = new Intent(MainActivity.this, EditAlumnoActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("ALUMNO", alumno);
                        intent.putExtras(bundle);
                    //recibir alumno modificado o la orden de eliminar
                        editAlumnoLauncher.launch(intent); //para enviar informacion es el intent y el bundle con un new,
                                //para recibirlo en la nueva actividad es con un intent y bundle pero con get en lugar de new; y
                                //para tratar la informacion que devuelva la actvidad es con el launcher.
                }
            });

            binding.contentMain.contenedorMain.addView(alumnoView);
        }
    }
}
/*
queda por hacer:
1. elemnto para mostrar la info del alumno en el principal(textview)
2. el conjunto de datos a mostrar (listaAlumnos)
3. contenedor para poner cada elemento alumno (scroll)
4.la logica para mostrar los elementos en el scroll del principall
 */

/*
datos: ningun nombre de layout puede tener mayusculas o espacios

 */
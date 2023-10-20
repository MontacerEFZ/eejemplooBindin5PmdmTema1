package montacer.elfazazi.ejemplobindin5pmdmtema1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import montacer.elfazazi.ejemplobindin5pmdmtema1.databinding.ActivityEditAlumnoBinding;
import montacer.elfazazi.ejemplobindin5pmdmtema1.modelos.Alumno;

public class EditAlumnoActivity extends AppCompatActivity {

    private ActivityEditAlumnoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditAlumnoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent(); //get en ugar de new para coger la informqcion enviada desde el main en el launcher
        Bundle bundle = intent.getExtras(); //get en lugar de new para sacar la info enviada desde el main
        Alumno alumno = (Alumno) bundle.getSerializable("ALUMNO");

        rellenarFormulario(alumno);
    }

    private void rellenarFormulario(Alumno alumno) {
        binding.txtNombreEditAlumno.setText(alumno.getNombre());
        binding.txtApellidosEditAlumno.setText(alumno.getApellidos());
        switch (alumno.getCiclo()){ //doble comillas porq devuelve string, ver clase alumno
            case "SMR": binding.spCiclosEditAlumno.setSelection(1);
            break;
            case "DAM": binding.spCiclosEditAlumno.setSelection(2);
            break;
            case "DAW": binding.spCiclosEditAlumno.setSelection(3);
            break;
            case "3D": binding.spCiclosEditAlumno.setSelection(4);
            break;
            case "Marketing": binding.spCiclosEditAlumno.setSelection(5);
            break;
        }

        switch (alumno.getGrupo()){ //comillas simples porq devuelve string, ver clase alumno
            case 'A': binding.rbGrupoAEditAlumno.setChecked(true);
            break;
            case 'B': binding.rbGrupoBEditAlumno.setChecked(true);
            break;
            case 'C': binding.rbGrupoCEditAlumno.setChecked(true);
            break;
        }
    }
}
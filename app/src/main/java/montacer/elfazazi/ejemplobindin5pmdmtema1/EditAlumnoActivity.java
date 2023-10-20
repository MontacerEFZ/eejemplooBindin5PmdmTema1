package montacer.elfazazi.ejemplobindin5pmdmtema1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
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

        binding.btnEditarEditAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //crear alumno
                Alumno alu = crearAlumno();
                //enviar info al principal
                if (alu != null){
                    Intent intentVolver = new Intent();
                    Bundle bundleVovler = new Bundle();
                    bundleVovler.putSerializable("ALUMNO",alu);
                    intentVolver.putExtras(bundleVovler);
                    setResult(RESULT_OK, intentVolver);
                    finish();
                }else {
                    Toast.makeText(EditAlumnoActivity.this, "faltan datos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.btnEliminarEditAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    private Alumno crearAlumno() {
        if (binding.txtNombreEditAlumno.getText().toString().isEmpty()){
            return null;
        }
        if (binding.txtApellidosEditAlumno.getText().toString().isEmpty()){
            return null;
        }
        if (binding.spCiclosEditAlumno.getSelectedItemPosition() == 0){
            return null;
        }
        if (!binding.rbGrupoAEditAlumno.isChecked() && !binding.rbGrupoBEditAlumno.isChecked() && !binding.rbGrupoCEditAlumno.isChecked()){
            return null;
        }

        RadioButton rb = findViewById(binding.rgGrupoEditAlumno.getCheckedRadioButtonId());

        char letra = rb.getText().charAt(rb.getText().length()-1);
        Alumno alumno = new Alumno(binding.txtNombreEditAlumno.getText().toString(), binding.txtApellidosEditAlumno.getText().toString(),
                binding.spCiclosEditAlumno.getSelectedItem().toString(), letra);

        return alumno;
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
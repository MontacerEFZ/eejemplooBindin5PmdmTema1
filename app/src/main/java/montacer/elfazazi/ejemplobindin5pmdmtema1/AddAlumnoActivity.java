package montacer.elfazazi.ejemplobindin5pmdmtema1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import montacer.elfazazi.ejemplobindin5pmdmtema1.databinding.ActivityAddAlumnoBinding;
import montacer.elfazazi.ejemplobindin5pmdmtema1.modelos.Alumno;

public class AddAlumnoActivity extends AppCompatActivity {
    private ActivityAddAlumnoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_add_alumno); esta linea se borraa


        binding = ActivityAddAlumnoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCancelarAddAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        binding.btnCrearAddAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //añadi lo q escriben al alumno
                Alumno alumno = crearAlumno();

                if (alumno == null){
                    Toast.makeText(AddAlumnoActivity.this, "faltan datos", Toast.LENGTH_SHORT).show();
                }else {
                    //enviar info al main con resultado ok

                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ALUMNO", alumno);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);

                    //terminar
                    finish();
                }


            }
        });
    }

    private Alumno crearAlumno() {
        if (binding.txtNombreAddAlumno.getText().toString().isEmpty()){
        return null;
        }
        if (binding.txtApellidosAddAlumno.getText().toString().isEmpty()){
            return null;
        }
        if (binding.spCiclosAddAlumno.getSelectedItemPosition() == 0){
            return null;
        }
        if (!binding.rbGrupoAAddAlumno.isChecked() && !binding.rbGrupoBAddAlumno.isChecked() && !binding.rbGrupoCAddAlumno.isChecked()){
            return null;
        }

        RadioButton rb = findViewById(binding.rgGrupoAddAlumno.getCheckedRadioButtonId());

        char letra = rb.getText().charAt(rb.getText().length()-1);
        Alumno alumno = new Alumno(binding.txtNombreAddAlumno.getText().toString(), binding.txtApellidosAddAlumno.getText().toString(),
                binding.spCiclosAddAlumno.getSelectedItem().toString(), letra);

        return alumno;
    }
}
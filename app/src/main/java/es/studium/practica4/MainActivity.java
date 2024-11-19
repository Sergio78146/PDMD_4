package es.studium.practica4;

 import android.os.Bundle;
 import android.view.View;
 import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    String name, sex, species, profession;
    TextView avatarInfo;
    ImageView avatarImage;
    Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        avatarInfo = findViewById(R.id.avatarInfo);
        avatarImage = findViewById(R.id.avatarImage);
        resetButton = findViewById(R.id.resetButton);

        showNameDialog();
    }

    private void showNameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.name_prompt));
        final EditText nameInput = new EditText(this);
        nameInput.setHint(getString(R.string.name_prompt));
        builder.setView(nameInput);

        builder.setPositiveButton(getString(R.string.button_accept), (dialog, which) -> {
            name = nameInput.getText().toString();
            showSexDialog();
        });

        builder.setNegativeButton(getString(R.string.button_cancel), null);
        builder.show();
    }

    private void showSexDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.sex_prompt));
        final String[] options = {"Hombre", "Mujer"};
        builder.setItems(options, (dialog, which) -> {
            sex = options[which];
            showSpeciesDialog();
        });

        builder.setNegativeButton(getString(R.string.button_cancel), null);
        builder.show();
    }

    private void showSpeciesDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.species_prompt));
        final String[] options = {"Elfo", "Enano", "Hobbit", "Humano"};
        builder.setItems(options, (dialog, which) -> {
            species = options[which];
            showProfessionDialog();
        });

        builder.setNegativeButton(getString(R.string.button_cancel), null);
        builder.show();
    }

    private void showProfessionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.profession_prompt));
        final String[] options = {"Arquero", "Guerrero", "Mago", "Herrero", "Minero"};
        builder.setItems(options, (dialog, which) -> {
            profession = options[which];
            generateAvatar();
        });

        builder.setNegativeButton(getString(R.string.button_cancel), null);
        builder.show();
    }

    private void generateAvatar() {
        int life = new Random().nextInt(101);
        int magic = new Random().nextInt(11);
        int strength = new Random().nextInt(21);
        int speed = new Random().nextInt(6);

        String avatarDescription = getString(R.string.life) + ": " + life + "\n" +
                getString(R.string.magic) + ": " + magic + "\n" +
                getString(R.string.strength) + ": " + strength + "\n" +
                getString(R.string.speed) + ": " + speed;

        avatarInfo.setText("Nombre: " + name + "\n" +
                "Sexo: " + sex + "\n" +
                "Especie: " + species + "\n" +
                "Profesión: " + profession + "\n\n" +
                avatarDescription);

        String avatarImageName = "crea_un_" + species.toLowerCase() + "_" + sex.toLowerCase() + "_" + profession.toLowerCase();
        int avatarResId = getResources().getIdentifier(avatarImageName, "drawable", getPackageName());
        avatarImage.setImageResource(avatarResId);

        resetButton.setVisibility(View.VISIBLE);  // Asegúrate de que el botón de reinicio sea visible
        resetButton.setOnClickListener(v -> reset());
    }

    private void reset() {
        name = sex = species = profession = null;
        avatarInfo.setText("");
        avatarImage.setImageResource(0);
        resetButton.setVisibility(View.INVISIBLE);  // Ocultar el botón después de resetear
        showNameDialog();
    }
}

package com.tp3.exercices;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentSaisie extends Fragment {

    private EditText editTextNom, editTextPrenom, editTextDateNaissance, editTextNumeroTelephone, editTextAdresseMail;
    private CheckBox checkBoxSport, checkBoxMusique, checkBoxLecture, checkBoxVoyage, checkBoxCuisine, checkBoxCinema;
    private SwitchCompat switchSynchronisation;
    private Button buttonSoumettre, buttonTelecharger;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_saisie, container, false);

        editTextNom = view.findViewById(R.id.editTextNom);
        editTextPrenom = view.findViewById(R.id.editTextPrenom);
        editTextDateNaissance = view.findViewById(R.id.editTextDateNaissance);
        editTextNumeroTelephone = view.findViewById(R.id.editTextTelephone);
        editTextAdresseMail = view.findViewById(R.id.editTextEmail);

        checkBoxSport = view.findViewById(R.id.checkboxSport);
        checkBoxSport.setChecked(false);
        checkBoxLecture = view.findViewById(R.id.checkboxLecture);
        checkBoxLecture.setChecked(false);
        checkBoxMusique = view.findViewById(R.id.checkboxMusique);
        checkBoxMusique.setChecked(false);
        checkBoxVoyage = view.findViewById(R.id.checkboxVoyage);
        checkBoxVoyage.setChecked(false);
        checkBoxCuisine = view.findViewById(R.id.checkboxCuisine);
        checkBoxCuisine.setChecked(false);
        checkBoxCinema = view.findViewById(R.id.checkboxCinema);
        checkBoxCinema.setChecked(false);

        switchSynchronisation = view.findViewById(R.id.syncSwitch);
        switchSynchronisation.setChecked(false);

        buttonSoumettre = view.findViewById(R.id.submitBtn);
        buttonTelecharger = view.findViewById(R.id.downloadBtn);


        buttonSoumettre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = editTextNom.getText().toString();
                String prenom = editTextPrenom.getText().toString();
                String dateNaissance = editTextDateNaissance.getText().toString();
                String numeroTelephone = (editTextNumeroTelephone.getText().toString());
                String adresseMail = editTextAdresseMail.getText().toString();

                boolean sport = checkBoxSport.isChecked();
                boolean musique = checkBoxMusique.isChecked();
                boolean lecture = checkBoxLecture.isChecked();
                boolean voyage = checkBoxVoyage.isChecked();
                boolean cuisine = checkBoxCuisine.isChecked();
                boolean cinema = checkBoxCinema.isChecked();

                boolean synchronisation = switchSynchronisation.isChecked();

                FragmentAffichage fragmentAffichage = new FragmentAffichage();
                Bundle bundle = new Bundle();

                // On ajoute les champs dans le bundle
                bundle.putString("nom", nom);
                bundle.putString("prenom", prenom);
                bundle.putString("naissance", dateNaissance);
                bundle.putString("telephone", numeroTelephone);
                bundle.putString("mail", adresseMail);

                bundle.putBoolean("sport", sport);
                bundle.putBoolean("musique", musique);
                bundle.putBoolean("lecture", lecture);
                bundle.putBoolean("voyage", voyage);
                bundle.putBoolean("cuisine", cuisine);
                bundle.putBoolean("cinema", cinema);

                bundle.putBoolean("synchro", synchronisation);

                fragmentAffichage.setArguments(bundle);


                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Remplace le conteneur de fragment par le FragmentAffichage
                fragmentTransaction.replace(R.id.fragment_container_view, fragmentAffichage);

                // Ajoute la transaction à la pile de backstack
                fragmentTransaction.addToBackStack(null);

                // Confirme la transaction
                fragmentTransaction.commit();

            }
        });

        buttonTelecharger.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(this, HelloService.class);
                startService(intent);
            }
        });

        return view;
    }
}


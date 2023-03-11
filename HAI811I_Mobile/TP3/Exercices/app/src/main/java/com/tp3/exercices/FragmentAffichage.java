package com.tp3.exercices;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class FragmentAffichage extends Fragment
{

    private TextView textViewNom, textViewPrenom, textViewDateNaissance, textViewNumeroTelephone, textViewAdresseMail, textViewCentresInteret, textViewSynchronisation;
    private Button buttonValider;
    private String nom, prenom, dateNaissance, numeroTelephone, adresseMail, centresInteret;
    private boolean sport, musique, lecture, voyage, cuisine, cinema, synchronisation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_affichage, container, false);

        textViewNom = view.findViewById(R.id.tv_nom);
        textViewPrenom = view.findViewById(R.id.tv_prenom);
        textViewDateNaissance = view.findViewById(R.id.tv_date_naissance);
        textViewNumeroTelephone = view.findViewById(R.id.tv_num_telephone);
        textViewAdresseMail = view.findViewById(R.id.tv_adresse_mail);
        textViewCentresInteret = view.findViewById(R.id.tv_centres_interet);
        textViewSynchronisation = view.findViewById(R.id.tv_synchro);
        buttonValider = view.findViewById(R.id.btn_valider);

        Bundle bundle = getArguments();
        setNom(bundle.getString("nom"));
        setPrenom(bundle.getString("prenom"));
        setDateNaissance(bundle.getString("naissance"));
        setNumeroTelephone(bundle.getString("telephone"));
        setAdresseMail(bundle.getString("mail"));

        setCentresInteret(bundle.getBoolean("sport"), bundle.getBoolean("musique"), bundle.getBoolean("lecture"), bundle.getBoolean("voyage"), bundle.getBoolean("cuisine"), bundle.getBoolean("cinema"));

        setSynchronisation(bundle.getBoolean("synchro"));

        buttonValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("nom", nom);
                editor.putString("prenom", prenom);
                editor.putString("dateNaissance", dateNaissance);
                editor.putString("numeroTelephone", numeroTelephone);
                editor.putString("adresseMail", adresseMail);
                editor.putString("centresInteret", centresInteret);

                editor.apply();

                Toast.makeText(getActivity(),"Informations ajoutés au fichier data.XML", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    public void setNom(String nom) {
        this.nom = nom;
        textViewNom.setText(nom);
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
        textViewPrenom.setText(prenom);
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
        textViewDateNaissance.setText(dateNaissance);
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
        textViewNumeroTelephone.setText(numeroTelephone);
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
        textViewAdresseMail.setText(adresseMail);
    }

    public void setCentresInteret(boolean sport, boolean musique, boolean lecture, boolean voyage, boolean cuisine, boolean cinema) {
        this.sport = sport;
        this.musique = musique;
        this.lecture = lecture;
        this.voyage = voyage;
        this.cuisine = cuisine;
        this.cinema = cinema;

        String centresInteret = "";

        if (sport) {
            centresInteret += "Sport, ";
        }
        if (musique) {
            centresInteret += "Musique, ";
        }
        if (lecture) {
            centresInteret += "Lecture, ";
        }
        if (voyage) {
            centresInteret += "Voyage, ";
        }
        if (cuisine) {
            centresInteret += "Cuisine, ";
        }
        if (cinema) {
            centresInteret += "Cinema, ";
        }
        if (!centresInteret.isEmpty()) {
            centresInteret = centresInteret.substring(0, centresInteret.length() - 2);
        }

        this.centresInteret = centresInteret;

        textViewCentresInteret.setText(centresInteret);
    }

    public void setSynchronisation(boolean synchronisation) {
        this.synchronisation = synchronisation;
        String text = synchronisation ? "Synchronisées" : "Non synchronisées";
        textViewSynchronisation.setText(text);
    }
}


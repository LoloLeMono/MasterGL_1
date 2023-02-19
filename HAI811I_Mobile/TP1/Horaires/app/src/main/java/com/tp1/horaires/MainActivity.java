package com.tp1.horaires;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button searchButton = findViewById(R.id.search_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String departure = findViewById(R.id.departureEditText).toString();
                String arrival = findViewById(R.id.arrivalEditText).toString();

                // Faire la recherche en utilisant les villes de départ et d'arrivée saisies par l'utilisateur
                // Horaires à titre indicatif pour l'exemple

                List<String> horairesDeTrains = Arrays.asList("08h00 - Gare St Roch / 2566",
                                                                "10h00 - Gare St Roch / 2598",
                                                                "12h30 - Gare Du Sud / 5053",
                                                                "14h00 - Gare St Roch / 2286");

                // Créer l'adaptateur pour afficher les horaires de trains
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, horairesDeTrains);

                // Récupérer la vue de liste et lui associer l'adaptateur
                ListView listView = findViewById(R.id.trains_listview);
                listView.setAdapter(adapter);
            }
        });



    }
}
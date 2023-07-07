package com.tp3.exercices;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class FragmentSaisie extends Fragment {
    private EditText editTextNom, editTextPrenom, editTextDateNaissance, editTextNumeroTelephone, editTextAdresseMail;
    private CheckBox checkBoxSport, checkBoxMusique, checkBoxLecture, checkBoxVoyage, checkBoxCuisine, checkBoxCinema;
    private SwitchCompat switchSynchronisation;
    private Button buttonSoumettre, buttonTelecharger;

    private TextView textResult;

    private String url;

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

        textResult = view.findViewById(R.id.result);


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
                // Intent intent = new Intent(getActivity(), DownloadService.class);
                // getActivity().startService(intent);

                String stringUrl = "https://jsonplaceholder.typicode.com/todos/1";
                ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    new DownloadWebpageTask().execute(stringUrl);
                } else {
                    //textView.setText("No network connection available.");
                }
            }
        });

        return view;
    }

    private class DownloadWebpageTask extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... urls)
        {
            // params comes from the execute() call: params[0] is the url.
            try
            {
                return downloadUrl(urls[0]);
            } catch (IOException e)
            {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result)
        {
            textResult.setText(result);
        }
    }

    private String downloadUrl(String myurl) throws IOException
    {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved web page content.
        int len = 500;
        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("DEBUG", "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;
            //Makes sure that the InputStream is closed after the app is finished using it.
        } finally {
            if (is != null)
            {
                is.close();
            }
        }
    }

    private String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException
    {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }
}


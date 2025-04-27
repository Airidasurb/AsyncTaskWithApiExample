package com.example.asynctaskwithapiexample.utilities;

import android.os.AsyncTask;

public class AsyncDataLoader extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        // Paprasčiausiai iškviečiam EcbXmlDataParser
        return EcbXmlDataParser.getExchangeRates();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}

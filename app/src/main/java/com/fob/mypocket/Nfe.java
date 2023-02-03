package com.fob.mypocket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Nfe {
    private JSONObject nfe;

    public Nfe(JSONObject nfe) {
        this.nfe = nfe;
    }

    public JSONArray getProducts() throws JSONException {
        JSONArray arrayProducts = nfe.getJSONArray("produtos");
        JSONArray productNames = new JSONArray();
        for (int i = 0; i < arrayProducts.length(); i++) {
            productNames.put(arrayProducts.getJSONObject(i).getString("descricao"));
        }
        return productNames;
    }

    public String getTotalValue() throws JSONException{
        JSONObject cabecalho = nfe.getJSONObject("cabecalho");
        String totalValue = cabecalho.getString("total");
        return totalValue;
    }

}

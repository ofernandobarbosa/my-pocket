package com.fob.mypocket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Nfe {
    private JSONObject nfe;

    public Nfe(JSONObject nfe) {
        this.nfe = nfe;
    }

    public JSONObject getProducts() throws JSONException {
        JSONArray arrayProducts = getArrayProducts();
        return arrayProducts.getJSONObject(0);
    }
    private JSONArray getArrayProducts() throws JSONException{
        return nfe.getJSONArray("produtos");
    }
}

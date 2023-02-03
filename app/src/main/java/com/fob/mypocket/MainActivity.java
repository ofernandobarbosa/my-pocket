package com.fob.mypocket;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    private ImageButton qrImage;
    private String seFazUri;

    public Nfe nfe;

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        components();

        qrImage.setOnClickListener(v ->
        {
            scanCode();
        });
    }

    private void components() {
        qrImage = findViewById(R.id.qr_image);
        queue = Volley.newRequestQueue(this);
    }

    private void scanCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLaucher.launch(options);

    }

    private void showAlert(JSONArray s) throws JSONException {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Result");
        StringBuilder messages = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            messages.append(s.getString(i)).append("\n");
        }
        builder.setMessage(messages);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }

    private void showAlertTotalValue(String c) throws JSONException {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Result");
        StringBuilder messages = new StringBuilder();

        messages.append("O valor total gasto foi de R$").append(c);

        builder.setMessage(messages);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }
    private void showAnyAlert(String c) throws JSONException {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Result");
        builder.setMessage(c);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }

    private void requestNfe(String uri) throws JSONException {
        JSONObject body = new JSONObject();
        body.put("url", uri);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST,
                        U.BASE_URL,
                        body,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                nfe = new Nfe(response);
                                try {
                                    showAlertTotalValue(nfe.getTotalValue());
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error

                            }
                        });
        queue.add(jsonObjectRequest);
    }

    ActivityResultLauncher<ScanOptions> barLaucher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() != null) {
            seFazUri = result.getContents();

            if (seFazUri.contains("https://")) {
                try {
                    requestNfe(seFazUri);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }else {
                try {
                    showAnyAlert("QR CODE INVÁLIDO \n Leia um QR Code válido!");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    });

}
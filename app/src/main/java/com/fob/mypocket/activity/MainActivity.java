package com.fob.mypocket.activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fob.mypocket.adapter.AdapterPillars;
import com.fob.mypocket.model.Pillar;
import com.fob.mypocket.utils.CaptureAct;
import com.fob.mypocket.model.Nfe;
import com.fob.mypocket.R;
import com.fob.mypocket.utils.U;
import com.fob.mypocket.adapter.AdapterExpenses;
import com.fob.mypocket.model.Expense;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewExpenses;
    private RecyclerView recyclerViewPillars;
    private List<Expense> expenseList = new ArrayList<>();
    private List<Pillar> pillarList = new ArrayList<>();
    private ImageButton qrImage;
    private String seFazUri;

    public Nfe nfe;

    private RequestQueue queue;

    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth user = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerPillars();
        recyclerExpenses();

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

    private void recyclerPillars(){
        recyclerViewPillars = findViewById(R.id.recyclerViewPillar);

        this.createPillars();

        AdapterPillars adapter = new AdapterPillars( pillarList );

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPillars.setLayoutManager(layoutManager);
        recyclerViewPillars.setHasFixedSize(true);
        recyclerViewPillars.setAdapter( adapter );

    }

    private void createPillars(){

        Pillar pillar = new Pillar("Alimentação", "R$5.640,02", "R$1.023,23" , R.drawable.img_food_example);
        this.pillarList.add(pillar);

        pillar = new Pillar("Essencial", "R$15.640,02", "R$1.023,23" , R.drawable.img_essencial_example);
        this.pillarList.add(pillar);

        pillar = new Pillar("Lazer", "R$5.640,02", "R$1.023,23" , R.drawable.img_lazer_example);
        this.pillarList.add(pillar);

        pillar = new Pillar("Investimento", "R$5.640,02", "R$1.023,23" , R.drawable.img_investimento_example);
        this.pillarList.add(pillar);

    }

    private void recyclerExpenses(){
        recyclerViewExpenses = findViewById(R.id.recyclerViewExpenses);

        //Listar gastos
        this.createExpense();

        //Configurar adapter
        AdapterExpenses adapter = new AdapterExpenses( expenseList );

        //Configurar Recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewExpenses.setLayoutManager(layoutManager);
        recyclerViewExpenses.setHasFixedSize(true);
        recyclerViewExpenses.setAdapter( adapter );
    }

    public void createExpense(){

        Expense expense = new Expense("Jun", "26", "Stock Center", "Supermercado", "Dinheiro", R.drawable.ic_dim, "À vista", "R$394,20");
        this.expenseList.add(expense);

        expense = new Expense("Jun", "27", "Condomínio", "Moradia", "PicPay", R.drawable.ic_picpay,  "À vista", "R$280,00");
        this.expenseList.add(expense);

        expense = new Expense("Jun", "27", "Dog do Alemão", "Regalia", "Nubank", R.drawable.ic_nubank, "1x", "R$39,20");
        this.expenseList.add(expense);

        expense = new Expense("Jun", "28", "Gasolina", "Gasolina", "Samsung", R.drawable.ic_samsung, "1x", "R$100,00");
        this.expenseList.add(expense);

        expense = new Expense("Jun", "28", "Ceee", "Moradia", "PicPay", R.drawable.ic_picpay, "À vista", "R$132,20");
        this.expenseList.add(expense);

        expense = new Expense("Jun", "27", "Condomínio", "Moradia", "PicPay", R.drawable.ic_picpay,  "À vista", "R$280,00");
        this.expenseList.add(expense);

        expense = new Expense("Jun", "27", "Dog do Alemão", "Regalia", "Nubank", R.drawable.ic_nubank, "1x", "R$39,20");
        this.expenseList.add(expense);

        expense = new Expense("Jun", "28", "Gasolina", "Gasolina", "Samsung", R.drawable.ic_samsung, "1x", "R$100,00");
        this.expenseList.add(expense);

        expense = new Expense("Jun", "28", "Ceee", "Moradia", "PicPay", R.drawable.ic_picpay, "À vista", "R$132,20");
        this.expenseList.add(expense);

        expense = new Expense("Jun", "27", "Condomínio", "Moradia", "PicPay", R.drawable.ic_picpay,  "À vista", "R$280,00");
        this.expenseList.add(expense);

        expense = new Expense("Jun", "27", "Dog do Alemão", "Regalia", "Nubank", R.drawable.ic_nubank, "1x", "R$39,20");
        this.expenseList.add(expense);

        expense = new Expense("Jun", "28", "Gasolina", "Gasolina", "Samsung", R.drawable.ic_samsung, "1x", "R$100,00");
        this.expenseList.add(expense);

        expense = new Expense("Jun", "28", "Ceee", "Moradia", "PicPay", R.drawable.ic_picpay, "À vista", "R$132,20");
        this.expenseList.add(expense);
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
                                try {
                                    showAnyAlert("Erro na Leitura do QR Code " + error.toString());
                                } catch (JSONException ex) {
                                    throw new RuntimeException(ex);
                                }
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
                    showAnyAlert("QR CODE INVÁLIDO \n Leia um QR Code válido! \n" + seFazUri);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    });

}
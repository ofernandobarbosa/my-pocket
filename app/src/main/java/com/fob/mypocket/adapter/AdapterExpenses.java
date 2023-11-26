package com.fob.mypocket.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fob.mypocket.R;
import com.fob.mypocket.model.Expense;

import java.util.List;

public class AdapterExpenses extends RecyclerView.Adapter<AdapterExpenses.MyViewHolder> {

    private List<Expense> expenseList;
    public AdapterExpenses(List<Expense> list) {
        this.expenseList = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_expenses_list, parent, false);

        return new MyViewHolder(itemLista);
    }

    //implementar método parar buscar informações no banco de dados
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Expense itemList = expenseList.get(position);

        holder.mes.setText(itemList.getMes());
        holder.dia.setText(itemList.getDia());
        holder.descricaoCompra.setText(itemList.getDescricaoCompra());
        holder.categoria.setText(itemList.getCategoria());
        holder.metodoPagamento.setText(itemList.getMetodoPagamento());
        holder.imagemMetodoPagamento.setImageResource(itemList.getImagemMetodoPagamento());
        holder.condicaoPagamento.setText(itemList.getCondicaoPagamento());
        holder.valorPagamento.setText(itemList.getValorPagamento());
    }

    @Override
    public int getItemCount() {
        if (expenseList.size() > 10){
            return 10;
        }
        return expenseList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mes;
        TextView dia;
        TextView descricaoCompra;
        TextView categoria;
        TextView metodoPagamento;
        ImageView imagemMetodoPagamento;
        TextView condicaoPagamento;
        TextView valorPagamento;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mes = itemView.findViewById(R.id.textMes);
            dia = itemView.findViewById(R.id.textDia);
            descricaoCompra = itemView.findViewById(R.id.textDescricaoCompra);
            categoria = itemView.findViewById(R.id.textCategoriaCompra);
            metodoPagamento = itemView.findViewById(R.id.textMetodoPagamento);
            imagemMetodoPagamento = itemView.findViewById(R.id.imagemMetodoPagamento);
            condicaoPagamento = itemView.findViewById(R.id.textCondicaoPagamento);
            valorPagamento = itemView.findViewById(R.id.textValorPagamento);
        }
    }

}

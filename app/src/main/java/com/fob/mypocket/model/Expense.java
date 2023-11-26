package com.fob.mypocket.model;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.widget.ImageView;

public class Expense {

    private String mes;
    private String dia;
    private String descricaoCompra;
    private String categoria;
    private String metodoPagamento;
    private int imagemMetodoPagamento;
    private String condicaoPagamento;
    private String valorPagamento;

    public Expense(){

    }

    public Expense(String mes, String dia, String descricaoCompra, String categoria, String metodoPagamento, int imagemMetodoPagamento, String condicaoPagamento, String valorPagamento) {
        this.mes = mes;
        this.dia = dia;
        this.descricaoCompra = descricaoCompra;
        this.categoria = categoria;
        this.metodoPagamento = metodoPagamento;
        this.imagemMetodoPagamento = imagemMetodoPagamento;
        this.condicaoPagamento = condicaoPagamento;
        this.valorPagamento = valorPagamento;
    }

    public int getImagemMetodoPagamento() {
        return imagemMetodoPagamento;
    }

    public void setImagemMetodoPagamento(int imagemMetodoPagamento) {
        this.imagemMetodoPagamento = imagemMetodoPagamento;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getDescricaoCompra() {
        return descricaoCompra;
    }

    public void setDescricaoCompra(String descricaoCompra) {
        this.descricaoCompra = descricaoCompra;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public String getCondicaoPagamento() {
        return condicaoPagamento;
    }

    public void setCondicaoPagamento(String condicaoPagamento) {
        this.condicaoPagamento = condicaoPagamento;
    }

    public String getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(String valorPagamento) {
        this.valorPagamento = valorPagamento;
    }
}

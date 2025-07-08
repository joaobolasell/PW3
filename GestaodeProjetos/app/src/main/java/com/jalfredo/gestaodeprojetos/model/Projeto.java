package com.jalfredo.gestaodeprojetos.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Projeto implements Parcelable {

    private String id;
    private String nome;
    private String descricao;
    private String status;

    public Projeto() {
    }

    public Projeto(String nome, String descricao, String status) {
        this.nome = nome;
        this.descricao = descricao;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    // Implementação do Parcelable
    // Construtor que lê do Parcel
    protected Projeto(Parcel in) {
        id = in.readString();
        nome = in.readString();
        descricao = in.readString();
        status = in.readString();
    }

    // Campo estático CREATOR
    public static final Creator<Projeto> CREATOR = new Creator<Projeto>() {
        @Override
        public Projeto createFromParcel(Parcel in) {
            return new Projeto(in);
        }

        @Override
        public Projeto[] newArray(int size) {
            return new Projeto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0; // Geralmente 0, a menos que você esteja lidando com FileDescriptors
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nome);
        dest.writeString(descricao);
        dest.writeString(status);
    }






}

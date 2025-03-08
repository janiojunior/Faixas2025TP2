package br.unitins.tp2.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Regiao {
    CENTRO_OESTE(1, "Centro-Oeste"), 
    NORTE(2, "Norte"), 
    NORDESTE(3, "Nordeste"), 
    SUDESTE(4, "Suldeste"), 
    SUL(5, "Sul");

    private final int ID;
    private final String NOME;

    Regiao(int id, String nome) {
        this.ID = id;
        this.NOME = nome;
    }

    public int getId() {
        return ID;
    }

    public String getNome() {
        return NOME;
    }

     public static Regiao valueOf(int id) {
        for (Regiao r : Regiao.values()) {
            if (r.getId() == id)
                return r;
        }
        return null;
     }

}

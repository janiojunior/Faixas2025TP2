package br.unitins.tp2.model;


import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Modalidade {

    JIU_JITSU(1, "Jiu Jitsu"), 
    JUDO(2, "Judo"), 
    KARATE(3, "Karate");

    private int id;
    private String label;

    Modalidade(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Modalidade valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;

        for(Modalidade perfil : Modalidade.values()) {
            if (id.equals(perfil.getId()))
                return perfil;
        } 
        throw new IllegalArgumentException("Id inválido:" + id);
    }

}

package Tema1;

public class Fraude {
    private String numeCircumscriptie;
    private String cnp;
    private String nume;

    public Fraude() {}
    public Fraude(String numeCircumscriptie, String cnp, String nume) {
        this.numeCircumscriptie = numeCircumscriptie;
        this.cnp = cnp;
        this.nume = nume;
    }

    public String getNumeCircumscriptie() {
        return numeCircumscriptie;
    }

    public void setNumeCircumscriptie(String numeCircumscriptie) {
        this.numeCircumscriptie = numeCircumscriptie;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
}

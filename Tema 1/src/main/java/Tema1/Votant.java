package Tema1;

public class Votant extends Persoana{
    private String neindemanatic;
    private boolean votat;
    private boolean frauda;
    public Votant() {}
    public Votant(String cnp, String varsta, String neindemanatic, String nume) {
        super.setCnp(cnp);
        super.setVarsta(varsta);
        this.neindemanatic = neindemanatic;
        super.setNume(nume);
        this.votat = false;
        this.frauda = false;
    }

    public String getNeindemanatic() {
        return neindemanatic;
    }

    public boolean getVotat() {
        return votat;
    }

    public void setVotat(boolean votat) {
        this.votat = votat;
    }

    public boolean getFrauda() {
        return frauda;
    }

    public void setFrauda(boolean frauda) {
        this.frauda = frauda;
    }

}

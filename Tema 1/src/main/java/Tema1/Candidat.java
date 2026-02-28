package Tema1;

public class Candidat extends Persoana{
    private Integer nrVoturi;
    public Candidat() {}
    public Candidat(String cnp, String varsta, String nume) {
        super.setCnp(cnp);
        super.setVarsta(varsta);
        super.setNume(nume);
        this.nrVoturi = 0;
    }

    public Candidat(Candidat altCandidat) {
        super.setCnp(altCandidat.getCnp());
        super.setVarsta(altCandidat.getVarsta());
        super.setNume(altCandidat.getNume());
        this.nrVoturi = altCandidat.getNrVoturi();
    }

    public Integer getNrVoturi() {
        return nrVoturi;
    }

    public void incrementVoturi() {
        nrVoturi++;
    }

    public void adaugaVoturi(Integer voturi) {
        this.nrVoturi += voturi;
    }
}

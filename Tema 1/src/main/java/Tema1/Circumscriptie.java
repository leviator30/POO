package Tema1;

import java.util.ArrayList;
import java.util.Comparator;

public class Circumscriptie {
    private String numeCircumscriptie;
    private String regiune;
    private ArrayList<Votant> listaVotanti = new ArrayList<>();
    private ArrayList<Candidat> listaCandidatiLocali = new ArrayList<>();

    public Circumscriptie() {
    }
    public Circumscriptie(String numeCircumscriptie, String regiune) {
        this.numeCircumscriptie = numeCircumscriptie;
        this.regiune = regiune;
    }

    public String getNumeCircumscriptie() {
        return numeCircumscriptie;
    }

    public void setNumeCircumscriptie(String numeCircumscriptie) {
        this.numeCircumscriptie = numeCircumscriptie;
    }

    public String getRegiune() {
        return regiune;
    }

    public void setRegiune(String regiune) {
        this.regiune = regiune;
    }

    public ArrayList<Votant> getListaVotanti() {
        return listaVotanti;
    }

    public void setListaVotanti(ArrayList<Votant> listaVotanti) {
        this.listaVotanti = listaVotanti;
    }

    public ArrayList<Candidat> getListaCandidatiLocali() {
        return listaCandidatiLocali;
    }

    public void setListaCandidatiLocali(ArrayList<Candidat> listaCandidatiLocali) {
        this.listaCandidatiLocali = listaCandidatiLocali;
    }

    /**
     * Adaugam un votant la nivel local
     * Consideram erorile legate de cnp, varsta si votant cu cnp duplicitar
     * @param cnp
     * @param varsta
     * @param neindemanatic
     * @param nume
     */
    public void addVotant(String cnp, String varsta, String neindemanatic, String nume) {
        boolean exist = false;

        if(cnp.length() != 13)
            System.out.println("EROARE: CNP invalid ");

        if(Integer.valueOf(varsta) < 18)
            System.out.println("EROARE: Varsta invalida ");

        for(Votant votant : listaVotanti) {
            if (votant.getCnp().equals(cnp)) {
                System.out.println("EROARE: Votantul " + nume + " are deja acelasi CNP");
                exist = true;
                break;
            }
        }

        if(!exist) {
            Votant newVotant = new Votant(cnp, varsta, neindemanatic, nume);
            listaVotanti.add(newVotant);
            System.out.println("S-a adaugat votantul " + nume);
        }

    }

    /**
     * Afisare lista de votanti
     */
    public void listVotanti() {
        if(listaVotanti.isEmpty()) {
            System.out.println("GOL: Nu sunt votanti in " + getNumeCircumscriptie());
            return;
        }

        System.out.println("Votantii din " + getNumeCircumscriptie() +":");
        for(Votant votant : listaVotanti)
            System.out.println(votant.getNume() + " " + votant.getCnp() + " " + votant.getVarsta());
    }

    /**
     * Verificare existenta votant in circumscriptie
     * @param cnp
     * @return
     */
    public boolean existVotant(String cnp) {
        for(Votant votant : listaVotanti)
            if(votant.getCnp().equals(cnp))
                return true;

        return false;
    }

    /**
     * Setare a fraudei pentru votant
     * @param cnpVotant
     */
    public void setFrauda(String cnpVotant) {
        for(Votant votant : listaVotanti)
            if(votant.getCnp().equals(cnpVotant)) {
                votant.setFrauda(true);
                break;
            }
    }

    /**
     * Adaugare vot la nivel local
     * Se incrementeaza numarul de voturi sau se adauga un candidat nou daca acesta inca nu a
     * fost adaugat, dar a fost votat
     * @param cnp
     * @param varsta
     * @param numeCandidat
     */
    public void adaugareVotLocal(String cnp, String varsta, String numeCandidat) {
        for(Candidat candidat : listaCandidatiLocali) {
            if(candidat.getNume().equals(numeCandidat)) {
                candidat.incrementVoturi();
                return;
            }
        }

        Candidat newCandidat = new Candidat(cnp, varsta, numeCandidat);
        listaCandidatiLocali.add(newCandidat);
        newCandidat.incrementVoturi();
    }

    /**
     * Raport local
     * Se afiseaza lista de candidati si voturile acestora
     */
    public void raportLocal() {
        listaCandidatiLocali.sort(Comparator.comparingInt(Candidat::getNrVoturi).reversed());

        if(listaCandidatiLocali.isEmpty() || listaCandidatiLocali.get(0).getNrVoturi().equals(0)) {
            System.out.println("GOL: Lumea nu isi exercita dreptul de vot in " + numeCircumscriptie);
            return;
        }

        System.out.println("Raport voturi " + numeCircumscriptie + ":");
        for(Candidat candidat : listaCandidatiLocali) {
            System.out.println(candidat.getNume() + " " + candidat.getCnp() + " - " + candidat.getNrVoturi());
        }
    }

    /**
     * Numarul total de voturi in circumscriptie
     * @return
     */
    public Integer nrVoturiCircumscriptie() {
        Integer nrVoturi = 0;

        for(Candidat candidat : listaCandidatiLocali)
            nrVoturi += candidat.getNrVoturi();

        return  nrVoturi;
    }

    /**
     * Raport local detaliat
     * Se calculeaza procente legate de candidatul castigator pe circumscriptia curenta
     * @param nrVoturiNational
     */
    public void raportLocalDetaliat(Integer nrVoturiNational) {
        listaCandidatiLocali.sort(Comparator.comparingInt(Candidat::getNrVoturi).reversed());

        if(listaCandidatiLocali.isEmpty() || listaCandidatiLocali.get(0).getNrVoturi().equals(0)) {
            System.out.println("GOL: Lumea nu isi exercita dreptul de vot in " + numeCircumscriptie);
            return;
        }

        System.out.println("In " + numeCircumscriptie + " au fost " + nrVoturiCircumscriptie() + " voturi din " + nrVoturiNational +
                ". Adica " + nrVoturiCircumscriptie() * 100 /nrVoturiNational + "%. Cele mai multe voturi au fost stranse de " +
                listaCandidatiLocali.get(0).getCnp() + " " + listaCandidatiLocali.get(0).getNume() +  ". Acestea constituie " +
                listaCandidatiLocali.get(0).getNrVoturi() * 100 / nrVoturiCircumscriptie() + "% din voturile circumscriptiei.");

    }

}

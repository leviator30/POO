package Tema1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class Alegeri {
    private String idAlegeri;
    private String numeAlegeri;
    private String auInceput;
    private ArrayList<Circumscriptie> listaCircumscriptii = new ArrayList<>();
    private ArrayList<Candidat> listaCandidati = new ArrayList<>();
    private ArrayList<Fraude> listaFraude = new ArrayList<>();

    public Alegeri() {}
    public Alegeri(String idAlegeri, String numeAlegeri, String auInceput) {
        this.idAlegeri = idAlegeri;
        this.numeAlegeri = numeAlegeri;
        this.auInceput = auInceput;
    }

    public String getIdAlegeri() {
        return idAlegeri;
    }

    public void setIdAlegeri(String idAlegeri) {
        this.idAlegeri = idAlegeri;
    }

    public String getNumeAlegeri() {
        return numeAlegeri;
    }

    public void setNumeAlegeri(String numeAlegeri) {
        this.numeAlegeri = numeAlegeri;
    }

    public String getAuInceput() {
        return auInceput;
    }

    public void setAuInceput(String auInceput) {
        this.auInceput = auInceput;
    }

    /**
     * Adaugam o circumscriptie noua in lista de circumscriptii;
     * @param numeCircumscriptie
     * @param regiune
     */
    public void addCircumscriptie(String numeCircumscriptie, String regiune) {
        boolean exist = false;
        for(Circumscriptie circumscriptie : listaCircumscriptii) {
            if(circumscriptie.getNumeCircumscriptie().equals(numeCircumscriptie)) {
                System.out.println("EROARE: Deja exista o circumscriptie cu numele " + numeCircumscriptie);
                exist = true;
                break;
            }
        }
        if(!exist) {
            Circumscriptie newCircumscriptie = new Circumscriptie(numeCircumscriptie, regiune);
            listaCircumscriptii.add(newCircumscriptie);
            System.out.println("S-a adaugat circumscriptia " + numeCircumscriptie);
        }
    }

    /**
     * Stergem circumscriptia selectata
     * @param numeCircumscriptie
     */
    public void deleteCircumscriptie(String numeCircumscriptie) {
        boolean exist = false;
        for(Circumscriptie circumscriptie : listaCircumscriptii) {
            if(circumscriptie.getNumeCircumscriptie().equals(numeCircumscriptie)) {
                System.out.println("S-a sters circumscriptia " + numeCircumscriptie);
                listaCircumscriptii.remove(circumscriptie);
                exist = true;
                break;
            }
        }
        if(!exist) {
            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
        }
    }

    /**
     * Adaugam un candidat in lista de candidati
     * Consideram ca statisticile candidatilor din clasa Alegeri sunt pe plan national
     * @param cnp
     * @param varsta
     * @param nume
     */
    public void addCandidat(String cnp, String varsta, String nume) {
        boolean exist = false;

        if(cnp.length() != 13)
            System.out.println("EROARE: CNP invalid ");

        if(Integer.valueOf(varsta) < 35)
            System.out.println("EROARE: Varsta invalida ");

        for(Candidat candidat : listaCandidati) {
            if(candidat.getCnp().equals(cnp)) {
                System.out.println("EROARE: Candidatul " + nume + " are deja acelasi CNP ");
                exist = true;
                break;
            }
        }

        if(!exist) {
            Candidat candidat = new Candidat(cnp, varsta, nume);
            listaCandidati.add(candidat);
            System.out.println("S-a adaugat candidatul " + nume);
        }
    }

    /**
     * Stergem candidatul selectat
     * @param cnp
     */
    public void deleteCandidat(String cnp) {
        boolean exist = false;

        for(Candidat candidat : listaCandidati)
            if(candidat.getCnp().equals(cnp)) {
                System.out.println("S-a sters candidatul " + candidat.getNume());
                listaCandidati.remove(candidat);
                exist = true;
                break;
            }

        if(!exist)
            System.out.println("EROARE: Nu exista un candidat cu CNP-ul " + cnp);

    }

    /**
     * Adaugam un votant in circumscipria in care este arondat
     * @param numeCircumscriptie
     * @param cnp
     * @param varsta
     * @param neindemanatic
     * @param nume
     */
    public void addVotant(String numeCircumscriptie, String cnp, String varsta, String neindemanatic, String nume) {
        boolean exist = false;

        for(Circumscriptie circumscriptie : listaCircumscriptii) {
            if(circumscriptie.getNumeCircumscriptie().equals(numeCircumscriptie)) {
                circumscriptie.addVotant(cnp, varsta, neindemanatic, nume);
                exist = true;
                break;
            }
        }

        if(!exist)
            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
    }

    /**
     * Se afiseaza candidatii la nivel national
     */
    public void listCandidati() {
        if(listaCandidati.isEmpty()) {
            System.out.println("GOL: Nu sunt candidati ");
            return;
        }

        System.out.println("Candidatii:");
        for(Candidat candidat : listaCandidati) {
            System.out.println(candidat.getNume() + " " + candidat.getCnp() + " " + candidat.getVarsta());
        }
    }

    /**
     * Se afiseaza toti votantii, din toate circumscriptiile
     * @param numeCircumscriptie
     */
    public void listVotanti(String numeCircumscriptie) {
        boolean exist = false;

        for(Circumscriptie circumscriptie : listaCircumscriptii) {
            if(circumscriptie.getNumeCircumscriptie().equals(numeCircumscriptie)) {
                circumscriptie.listVotanti();
                exist = true;
                break;
            }
        }

        if(!exist)
            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
    }

    /**
     * Verificare daca exista candidatul dorit
     * @param cnp
     * @return
     */
    public boolean existCandidat(String cnp) {
        for(Candidat candidat : listaCandidati)
            if(candidat.getCnp().equals(cnp))
                return true;

        return false;
    }

    /**
     * Verificare daca exista circumscriptia dorita
     * @param numeCircumscriptie
     * @return
     */
    public boolean existCircumscriptie(String numeCircumscriptie) {
        for(Circumscriptie circumscriptie : listaCircumscriptii)
            if(circumscriptie.getNumeCircumscriptie().equals(numeCircumscriptie))
                return true;

        return false;
    }

    /**
     * Functia de votare
     * @param numeCircumscriptie
     * @param cnpVotant
     * @param cnpCandidat
     */
    public void voting(String numeCircumscriptie, String cnpVotant, String cnpCandidat) {
        // nu exista circumscriptia
        if(!existCircumscriptie(numeCircumscriptie)) {
            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
            return;
        }

        // nu exista candidatul
        if(!existCandidat(cnpCandidat)) {
            System.out.println("EROARE: Nu exista un candidat cu CNP-ul " + cnpCandidat);
            return;
        }

        // nu exista votantul
        boolean existVotant = false;
        for(Circumscriptie circumscriptie : listaCircumscriptii) {
            if(circumscriptie.existVotant(cnpVotant)){
                existVotant = true;
                break;
            }
        }
        if(!existVotant) {
            System.out.println("EROARE: Nu exista un votant cu CNP-ul " + cnpVotant);
            return;
        }


        // cautam circumscriptia dorita
        for(Circumscriptie circumscriptie : listaCircumscriptii) {
            if(circumscriptie.getNumeCircumscriptie().equals(numeCircumscriptie)) {

                // votantul nu exista in circumscriptia data, se incearca frauda
                if (!circumscriptie.existVotant(cnpVotant)) {

                    // consideram frauda
                    System.out.println("FRAUDA: Votantul cu CNP-ul " + cnpVotant + " a incercat sa comita o frauda. Votul a fost anulat");

                    // adaugam ca statistica de frauda
                    // cautam circumscriptia unde e arondat votantul
                    for(Circumscriptie circumscriptieCautare : listaCircumscriptii)
                        if(circumscriptieCautare.existVotant(cnpVotant))
                            for(Votant votant : circumscriptieCautare.getListaVotanti())
                                if(votant.getCnp().equals(cnpVotant)) {
                                    Fraude frauda = new Fraude(circumscriptie.getNumeCircumscriptie(), cnpVotant, votant.getNume());
                                    listaFraude.add(frauda);
                                    votant.setFrauda(true);
                                }

                    return;

                } else {

                    for (Votant votant : circumscriptie.getListaVotanti()) {

                        // votantul exista in circumscriptia dorita
                        if (votant.getCnp().equals(cnpVotant)) {

                            // vom lua in considerare doar votantii care nu au comis frauda
                            if (!votant.getFrauda()) {

                                // frauda - vot multiplu (a votat deja)
                                if (votant.getVotat()) {

                                    // consideram frauda
                                    System.out.println("FRAUDA: Votantul cu CNP-ul " + cnpVotant + " a incercat sa comita o frauda. Votul a fost anulat ");
                                    circumscriptie.setFrauda(cnpVotant);

                                    // adaugam ca statistica de frauda
                                    Fraude frauda = new Fraude(circumscriptie.getNumeCircumscriptie(), votant.getCnp(), votant.getNume());
                                    listaFraude.add(frauda);

                                    return;

                                    // nu a votat, nu a comis frauda inca, rezulta are drept de vot
                                } else if (!votant.getFrauda()) {
                                    for (Candidat candidat : listaCandidati) {
                                        if (candidat.getCnp().equals(cnpCandidat)) {

                                            //daca e indemanatic, contorizam voturile si la nivel local, si la nivel national
                                            if (votant.getNeindemanatic().equals("nu")) {
                                                circumscriptie.adaugareVotLocal(candidat.getCnp(), candidat.getVarsta(), candidat.getNume());
                                                candidat.incrementVoturi();
                                            }

                                            // consideram vot valid, daca e neindemanatic, nu se va contoriza, dar e valid
                                            votant.setVotat(true);
                                            System.out.println(votant.getNume() + " a votat pentru " + candidat.getNume());

                                            return;
                                        }
                                    }
                                }
                            } else {
                                // Afisare mesaj de frauda pentru votantul care deja a comis o frauda si incearca din nou
                                System.out.println("FRAUDA: Votantul cu CNP-ul " + cnpVotant + " a incercat sa comita o frauda. Votul a fost anulat");
                            }

                        }
                    }
                }
            }
        }

    }

    /**
     * Functie care creeaza cate un raport local pt fiecare circumscriptie
     * @param numeCircumscriptie
     */
    public void raportLocal(String numeCircumscriptie) {
        boolean exist = false;

        for(Circumscriptie circumscriptie : listaCircumscriptii) {
            if(circumscriptie.getNumeCircumscriptie().equals(numeCircumscriptie)) {
                circumscriptie.raportLocal();
                exist = true;
                break;
            }
        }

        if(!exist)
            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
    }

    /**
     * Raport national cu numarul de voturi ale fiecarui candidat
     */
    public void raportNational() {
        listaCandidati.sort(Comparator.comparingInt(Candidat::getNrVoturi).reversed());

        if(listaCandidati.get(0).getNrVoturi().equals(0)) {
            System.out.println("GOL: Lumea nu isi exercita dreptul de vot in Romania ");
            return;
        }

        System.out.println("Raport voturi Romania:");
        for(Candidat candidat : listaCandidati) {
            System.out.println(candidat.getNume() + " " + candidat.getCnp() + " - " + candidat.getNrVoturi());
        }
    }

    /**
     * Numar total de voturi la nicel national
     * @return
     */
    public Integer nrVoturiNational() {
        Integer nrVoturi = 0;

        for(Candidat candidat : listaCandidati)
            nrVoturi += candidat.getNrVoturi();

        return  nrVoturi;
    }

    /**
     * Numar de voturi la nivel de regiune
     * @param regiune
     * @return
     */
    public Integer nrVoturiRegiune(String regiune) {
        Integer nrVoturi = 0;

        // luam fiecare circumscriptie care apartine de regiunea data si adaugam voturile
        for(Circumscriptie circumscriptie : listaCircumscriptii) {
            if(circumscriptie.getRegiune().equals(regiune))
                nrVoturi += circumscriptie.nrVoturiCircumscriptie();
        }

        return nrVoturi;
    }

    /**
     * Se creeaza un raport detaliat in fiecare circumscriptie
     * @param numeCircumscriptie
     */
    public void raportLocalDetaliat(String numeCircumscriptie) {
        boolean exist = false;

        for(Circumscriptie circumscriptie : listaCircumscriptii) {
            if(circumscriptie.getNumeCircumscriptie().equals(numeCircumscriptie)) {
                circumscriptie.raportLocalDetaliat(nrVoturiNational());
                exist = true;
                break;
            }
        }

        if(!exist)
            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
    }

    /**
     * Raport national detaliat
     */
    public void raportNationalDetaliat() {
        listaCandidati.sort(Comparator.comparingInt(Candidat::getNrVoturi).reversed());

        if(listaCandidati.isEmpty() || listaCandidati.get(0).getNrVoturi().equals(0)) {
            System.out.println("GOL: Lumea nu isi exercita dreptul de vot in Romania ");
            return;
        }

        /*
        Cream o lista de regiuni pe baza circumscriptiilor,
        daca am adaugat deja o data, nu mai adaugam aceeasi regiune
        */
        ArrayList<String> regiuni = new ArrayList<>();
        for(Circumscriptie circumscriptie : listaCircumscriptii) {
            if(!regiuni.contains(circumscriptie.getRegiune()))
                regiuni.add(circumscriptie.getRegiune());
        }
        Collections.sort(regiuni);
        Collections.reverse(regiuni);

        /*
        Pentru fiecare regiune, cautam circumscriptiile aferente
        Din fiecare circumscriptie, luam candidatii si ii adaugam intr-o lista de candidati regionali
        Lista va contine duplicate deoarece am considerat fiecare candidat drept o entitate diferita per circumscriptie
        Cream obiecte noi de candidati deoarece primesc doar referinta
         */
        for(String regiune : regiuni) {
            ArrayList<Candidat> listaCandidatiRegionali = new ArrayList<>();
            for(Circumscriptie circumscriptie : listaCircumscriptii) {
                if (circumscriptie.getRegiune().equals(regiune)) {
                    for (Candidat candidatLocal : circumscriptie.getListaCandidatiLocali()) {
                        Candidat candidatCopie = new Candidat(candidatLocal);
                        listaCandidatiRegionali.add(candidatCopie);
                    }
                }
            }

            /*
            Elementele duplicate sunt eliminate
            Voturile sunt contorizate intr-un singur obiect
             */
            for (int i = 0; i < listaCandidatiRegionali.size(); i++) {
                Candidat candidat1 = listaCandidatiRegionali.get(i);
                for (int j = i + 1; j < listaCandidatiRegionali.size(); j++) {
                    Candidat candidat2 = listaCandidatiRegionali.get(j);

                    if (candidat1.getCnp().equals(candidat2.getCnp())) {
                        candidat1.adaugaVoturi(candidat2.getNrVoturi());

                        listaCandidatiRegionali.remove(j);
                        j--;
                    }
                }
            }

            // Sortam candidatii regionali in functie de numarul de voturi
            listaCandidatiRegionali.sort(Comparator.comparingInt(Candidat::getNrVoturi).reversed());

            // Afisare raport
            System.out.println("In Romania au fost " + nrVoturiNational() + " voturi.");
            System.out.println("In " + regiune + " au fost " + nrVoturiRegiune(regiune) + " voturi din " + nrVoturiNational() +
                    ". Adica " + nrVoturiRegiune(regiune) * 100 / nrVoturiNational() + "%. Cele mai multe voturi au fost stranse de " +
                    listaCandidatiRegionali.get(0).getCnp() + " " + listaCandidatiRegionali.get(0).getNume() + ". Acestea constituie " +
                    listaCandidatiRegionali.get(0).getNrVoturi() * 100 / nrVoturiRegiune(regiune) + "% din voturile regiunii.");
        }
    }

    // Raport pentru fraude, afisam lista de fraude
    public void raportFraude() {
        if(listaFraude.isEmpty()) {
            System.out.println("GOL: Romanii sunt cinstiti ");
            return;
        }

        System.out.println("Fraude comise:");
        for(Fraude frauda : listaFraude)
            System.out.println("In " + frauda.getNumeCircumscriptie() + ": " + frauda.getCnp() + " " + frauda.getNume());

    }
}

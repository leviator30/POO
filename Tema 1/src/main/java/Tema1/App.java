package Tema1;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.text.*;

public class App {
    private Scanner scanner;

    public App(InputStream input) {
        this.scanner = new Scanner(input);
    }

    public void run() {
        // Implementati aici cerintele din enunt
        // Pentru citirea datelor de la tastatura se foloseste campul scanner.
        boolean running = true;

        ArrayList<Alegeri> alegeri = new ArrayList<>();

        while(running) {
            String idComanda = scanner.nextLine().trim();

            switch(Integer.parseInt(idComanda)) {
                // Comanda 0 - Creare alegeri
                case 0:
                    String command0 = scanner.nextLine().trim();
                    String[] commandParts0 = command0.split(" ", 2);

                    Alegeri alegere0 = new Alegeri(commandParts0[0], commandParts0[1], "neinceput");
                    boolean added = false;

                    for(Alegeri alegere : alegeri)
                        if (alegere.getIdAlegeri().equals(alegere0.getIdAlegeri())) {
                            added = true;
                            break;
                        }

                    if(added) {
                        System.out.println("EROARE: Deja exista alegeri cu id " + alegere0.getIdAlegeri());
                        break;
                    }

                    alegeri.add(alegere0);
                    System.out.println("S-au creat alegerile " + alegere0.getNumeAlegeri());

                    break;

                // Comanda 1 - Pornire alegeri
                case 1:
                    String idAlegeri1 = scanner.nextLine().trim();
                    boolean exist1 = false;

                    for(Alegeri alegere : alegeri)
                        if (alegere.getIdAlegeri().equals(idAlegeri1)) {
                            if (!alegere.getAuInceput().equals("neinceput")) {
                                System.out.println("EROARE: Alegerile deja au inceput ");
                            } else {
                                alegere.setAuInceput("in_curs");
                                System.out.println("Au pornit alegerile " + alegere.getNumeAlegeri());
                            }
                            exist1 = true;
                            break;
                        }

                    if(!exist1)
                        System.out.println("EROARE: Nu exista alegeri cu acest id ");

                    break;

                // Comanda 2 - Adaugare circumscriptie
                case 2:
                    String command2 = scanner.nextLine().trim();
                    String[] commandParts2 = command2.split(" ", 3);

                    String idAlegeri2 = commandParts2[0];
                    String numeCircumscriptie2 = commandParts2[1];
                    String regiune2 = commandParts2[2];

                    boolean exist2 = false;

                    for(Alegeri alegere : alegeri)
                        if (alegere.getIdAlegeri().equals(idAlegeri2)) {
                            if (alegere.getAuInceput().equals("in_curs")) {
                                alegere.addCircumscriptie(numeCircumscriptie2, regiune2);
                            } else {
                                System.out.println("EROARE: Nu este perioada de votare ");
                            }
                            exist2 = true;
                            break;
                        }

                    if(!exist2)
                        System.out.println("EROARE: Nu exista alegeri cu acest id ");

                    break;

                // Comanda 3 - Eliminare circumscriptie
                case 3:
                    String command3 = scanner.nextLine().trim();
                    String[] commandParts3 = command3.split(" ", 2);

                    String idAlegeri3 = commandParts3[0];
                    String numeCircumscriptie3 = commandParts3[1];

                    boolean exist3 = false;

                    for(Alegeri alegere : alegeri)
                        if (alegere.getIdAlegeri().equals(idAlegeri3)) {
                            if (alegere.getAuInceput().equals("in_curs")) {
                                alegere.deleteCircumscriptie(numeCircumscriptie3);
                            } else {
                                System.out.println("EROARE: Nu este perioada de votare ");
                            }
                            exist3 = true;
                            break;
                        }

                    if(!exist3)
                        System.out.println("EROARE: Nu exista alegeri cu acest id ");

                    break;

                // Comanda 4 - Adaugare candidat in alegeri
                case 4:
                    String command4 = scanner.nextLine().trim();
                    String[] commandParts4 = command4.split(" ", 4);

                    String idAlegeri4 = commandParts4[0];
                    String cnp4 = commandParts4[1];
                    String varsta4 = commandParts4[2];
                    String nume4 = commandParts4[3];

                    boolean exist4 = false;

                    for(Alegeri alegere : alegeri)
                        if (alegere.getIdAlegeri().equals(idAlegeri4)) {
                            if(alegere.getAuInceput().equals("in_curs")) {
                                alegere.addCandidat(cnp4, varsta4, nume4);
                            } else {
                                System.out.println("EROARE: Nu este perioada de votare");
                            }
                            exist4 = true;
                            break;
                        }

                    if(!exist4)
                        System.out.println("EROARE: Nu exista alegeri cu acest id ");

                    break;

                // Comanda 5 - Eliminare candidat din alegeri
                case 5:
                    String command5 = scanner.nextLine().trim();
                    String[] commandParts5 = command5.split(" ", 2);

                    String idAlegeri5 = commandParts5[0];
                    String cnp5 = commandParts5[1];

                    boolean exist5 = false;

                    for(Alegeri alegere : alegeri)
                        if(alegere.getIdAlegeri().equals(idAlegeri5)) {
                            if(alegere.getAuInceput().equals("in_curs")) {
                                alegere.deleteCandidat(cnp5);
                            } else {
                                System.out.println("EROARE: Nu este perioada de votare ");
                            }
                            exist5 = true;
                            break;
                        }

                    if(!exist5)
                        System.out.println("EROARE: Nu exista alegeri cu acest id ");

                    break;

                // Comanda 6 - Adaugare votant in circumscriptie
                case 6:
                    String command6 = scanner.nextLine().trim();
                    String[] commandParts6 = command6.split(" ", 6);

                    String idAlegeri6 = commandParts6[0];
                    String numeCircumscriptie6 = commandParts6[1];
                    String cnp6 = commandParts6[2];
                    String varsta6 = commandParts6[3];
                    String neindemanatic6 = commandParts6[4];
                    String nume6 = commandParts6[5];

                    boolean exist6 = false;

                    for(Alegeri alegere : alegeri) {
                        if(alegere.getIdAlegeri().equals(idAlegeri6)) {
                            if(alegere.getAuInceput().equals("in_curs")) {
                                alegere.addVotant(numeCircumscriptie6, cnp6, varsta6, neindemanatic6, nume6);
                            } else {
                                System.out.println("EROARE: Nu este perioada de votare");
                            }
                            exist6 = true;
                            break;
                        }
                    }

                    if(!exist6)
                        System.out.println("EROARE: Nu exista alegeri cu acest id ");

                    break;

                // Comanda 7 - Listarea candidatilor din alegeri
                case 7:
                    String idAlegeri7 = scanner.nextLine().trim();

                    boolean exist7 = false;

                    for(Alegeri alegere : alegeri) {
                        if(alegere.getIdAlegeri().equals(idAlegeri7)) {
                            if(!alegere.getAuInceput().equals("neinceput")) {
                                alegere.listCandidati();
                            } else {
                                System.out.println("EROARE: inca nu au inceput alegerile ");
                            }
                            exist7 = true;
                            break;
                        }
                    }

                    if(!exist7)
                        System.out.println("EROARE: Nu exista alegeri cu acest id ");

                    break;

                // Comanda 8 - Listarea votantilor dintr-o circumscriptie
                case 8:
                    String command8 = scanner.nextLine().trim();
                    String[] commandParts8 = command8.split(" ", 6);

                    String idAlegeri8 = commandParts8[0];
                    String numeCircumscriptie8 = commandParts8[1];

                    boolean exist8 = false;

                    for(Alegeri alegere : alegeri) {
                        if(alegere.getIdAlegeri().equals(idAlegeri8)) {
                            if(!alegere.getAuInceput().equals("neinceput")) {
                                alegere.listVotanti(numeCircumscriptie8);
                            } else {
                                System.out.println("EROARE: inca nu au inceput alegerile ");
                            }
                            exist8 = true;
                            break;
                        }
                    }

                    if(!exist8)
                        System.out.println("EROARE: Nu exista alegeri cu acest id ");

                    break;

                // Comanda 9 - Votare
                case 9:
                    String command9 = scanner.nextLine().trim();
                    String[] commandParts9 = command9.split(" ", 4);

                    String idAlegeri9 = commandParts9[0];
                    String numeCircumscriptie9 = commandParts9[1];
                    String cnpVotant = commandParts9[2];
                    String cnpCandidat = commandParts9[3];

                    boolean exist9 = false;

                    for(Alegeri alegere : alegeri) {
                        if(alegere.getIdAlegeri().equals(idAlegeri9)) {
                            if(alegere.getAuInceput().equals("in_curs")) {
                                alegere.voting(numeCircumscriptie9, cnpVotant, cnpCandidat);
                            } else {
                                System.out.println("EROARE: Nu este perioada de votare");
                            }

                            exist9 = true;
                            break;
                        }
                    }

                    if(!exist9)
                        System.out.println("EROARE: Nu exista alegeri cu acest id ");

                    break;

                // Comanda 10 - Oprire alegeri
                case 10:
                    String idAlegeri10 = scanner.nextLine().trim();
                    boolean exist10 = false;

                    for(Alegeri alegere : alegeri) {
                        if(alegere.getIdAlegeri().equals(idAlegeri10)) {
                            if(alegere.getAuInceput().equals("in_curs")) {
                                System.out.println("S-au terminat alegerile " + alegere.getNumeAlegeri());
                                alegere.setAuInceput("terminat");
                            } else {
                                System.out.println("EROARE: Nu este perioada de votare");
                            }
                            exist10 = true;
                            break;
                        }
                    }

                    if(!exist10)
                        System.out.println("EROARE: Nu exista alegeri cu acest id");

                    break;

                // Comanda 11 - Creeaza raport voturi per circumscriptie
                case 11:
                    String command11 = scanner.nextLine().trim();
                    String[] commandParts11 = command11.split(" ", 2);

                    String idAlegeri11 = commandParts11[0];
                    String numeCircumscriptie11 = commandParts11[1];
                    boolean exist11 = false;

                    for(Alegeri alegere : alegeri) {
                        if(alegere.getIdAlegeri().equals(idAlegeri11)) {
                            if(alegere.getAuInceput().equals("terminat")) {
                                alegere.raportLocal(numeCircumscriptie11);
                            } else {
                                System.out.println("EROARE: Inca nu s-a terminat votarea");
                            }
                            exist11 = true;
                            break;
                        }
                    }

                    if(!exist11)
                        System.out.println("EROARE: Nu exista alegeri cu acest id");

                    break;

                // Comanda 12 - Creeaza raport voturi per circumscriptie
                case 12:
                    String idAlegeri12 = scanner.nextLine().trim();
                    boolean exist12 = false;

                    for(Alegeri alegere : alegeri) {
                        if(alegere.getIdAlegeri().equals(idAlegeri12)) {
                            if(alegere.getAuInceput().equals("terminat")) {
                                alegere.raportNational();
                            } else {
                                System.out.println("EROARE: Inca nu s-a terminat votarea");
                            }
                            exist12 = true;
                            break;
                        }
                    }

                    if(!exist12)
                        System.out.println("EROARE: Nu exista alegeri cu acest id");

                    break;

                // Comanda 13 - Analiza detaliata per circumscriptie
                case 13:
                    String command13 = scanner.nextLine().trim();
                    String[] commandParts13 = command13.split(" ", 2);

                    String idAlegeri13 = commandParts13[0];
                    String numeCircumscriptie13 = commandParts13[1];
                    boolean exist13 = false;

                    for(Alegeri alegere : alegeri) {
                        if(alegere.getIdAlegeri().equals(idAlegeri13)) {
                            if(alegere.getAuInceput().equals("terminat")) {
                                alegere.raportLocalDetaliat(numeCircumscriptie13);
                            } else {
                                System.out.println("EROARE: Inca nu s-a terminat votarea");
                            }
                            exist13 = true;
                            break;
                        }
                    }

                    if(!exist13)
                        System.out.println("EROARE: Nu exista alegeri cu acest id");

                    break;

                // Comanda 14 - Analiza detaliata pe plan national
                case 14:
                    String idAlegeri14 = scanner.nextLine().trim();
                    boolean exist14 = false;

                    for(Alegeri alegere : alegeri) {
                        if(alegere.getIdAlegeri().equals(idAlegeri14)) {
                            if(alegere.getAuInceput().equals("terminat")) {
                                alegere.raportNationalDetaliat();
                            } else {
                                System.out.println("EROARE: Inca nu s-a terminat votarea");
                            }
                            exist14 = true;
                            break;
                        }
                    }

                    if(!exist14)
                        System.out.println("EROARE: Nu exista alegeri cu acest id");

                    break;

                // Comanda 15 - Raportare fraude
                case 15:
                    String idAlegeri15 = scanner.nextLine().trim();
                    boolean exist15 = false;

                    for(Alegeri alegere : alegeri) {
                        if(alegere.getIdAlegeri().equals(idAlegeri15)) {
                            if(alegere.getAuInceput().equals("terminat")) {
                                alegere.raportFraude();
                            } else {
                                System.out.println("EROARE: Inca nu s-a terminat votarea");
                            }
                            exist15 = true;
                            break;
                        }
                    }

                    if(!exist15)
                        System.out.println("EROARE: Nu exista alegeri cu acest id");

                    break;

                // Comanda 16 - Sterge alegeri
                case 16:
                    String idAlegeri16 = scanner.nextLine().trim();
                    boolean exist16 = false;

                    for(Alegeri alegere : alegeri) {
                        if(alegere.getIdAlegeri().equals(idAlegeri16)) {
                            System.out.println("S-au sters alegerile " + alegere.getNumeAlegeri());
                            alegeri.remove(alegere);
                            exist16 = true;
                            break;
                        }
                    }

                    if(!exist16)
                        System.out.println("EROARE: Nu exista alegeri cu acest id");

                    break;

                // Comanda 17 - Listare alegeri
                case 17:
                    if(alegeri.isEmpty())
                        System.out.println("GOL: Nu sunt alegeri ");

                    System.out.println("Alegeri:");
                    for(Alegeri alegere : alegeri)
                        System.out.println(alegere.getIdAlegeri() + " " + alegere.getNumeAlegeri());

                    break;

                // Comanda 18 - Iesire
                case 18:
                    running = false;
                    break;

                default:
                    System.out.println("Wrong format!");
            }
        }

    }

    public static void main(String[] args) {
        App app = new App(System.in);
        app.run();
    }
}
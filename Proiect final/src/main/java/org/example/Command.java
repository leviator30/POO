package org.example;

public interface Command {
    /**
     * Comanda execute, care va fi implementata de fiecare comanda in parte.
     * Implementarea pattern-ului Command.
     * @param database
     * @param parts
     * @return
     */
    String execute(Database database, String[] parts);
}


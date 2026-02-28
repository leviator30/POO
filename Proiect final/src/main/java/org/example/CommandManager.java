package org.example;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();

    // Tipurile de comenzi posibile
    public CommandManager() {
        commands.put("ADD MUSEUM", new AddMuseumCommand());
        commands.put("ADD GUIDE", new AddGuideCommand());
        commands.put("REMOVE GUIDE", new RemoveGuideCommand());
        commands.put("ADD MEMBER", new AddMemberCommand());
        commands.put("REMOVE MEMBER", new RemoveMemberCommand());
        commands.put("FIND GUIDE", new FindGuideCommand());
        commands.put("FIND MEMBER", new FindMemberCommand());
        commands.put("ADD EVENT", new AddEventCommand());
    }

    /**
     * Functia de executare, se parseaza argumentele pentru fiecare comanda.
     * @param database
     * @param parts
     * @return
     */
    public String executeCommand(Database database, String[] parts) {
        Command command = commands.get(parts[0]);
        if (command != null) {
            return command.execute(database, parts);
        }
        return "";
    }
}

package com.lev_prav.client.utility;

import com.lev_prav.client.commands.AverageOfHeightCommand;
import com.lev_prav.client.commands.ClearCommand;
import com.lev_prav.client.commands.Command;
import com.lev_prav.client.commands.CommandAdd;
import com.lev_prav.client.commands.CommandUpdate;
import com.lev_prav.client.commands.ExecuteScriptCommand;
import com.lev_prav.client.commands.ExitCommand;
import com.lev_prav.client.commands.FilterLessThanNationalityCommand;
import com.lev_prav.client.commands.GroupCountingByCreationDateCommand;
import com.lev_prav.client.commands.HeadCommand;
import com.lev_prav.client.commands.HelpCommand;
import com.lev_prav.client.commands.HistoryCommand;
import com.lev_prav.client.commands.InfoCommand;
import com.lev_prav.client.commands.RemoveByIdCommand;
import com.lev_prav.client.commands.RemoveLowerCommand;
import com.lev_prav.client.commands.SaveCommand;
import com.lev_prav.client.commands.ShowCommand;
import com.lev_prav.client.userio.UserIO;

import java.util.HashMap;

public class CommandManager {
    private final HashMap<String, Command> commands = new HashMap<>();
    private final HistoryCommand historyCommand;

    public CommandManager(CollectionManager collectionManager, UserIO userIO, PersonFiller personFiller, String fileName) {
        ClearCommand clearCommand = new ClearCommand(collectionManager);
        commands.put(clearCommand.getName(), clearCommand);
        CommandAdd commandAdd = new CommandAdd(collectionManager, personFiller);
        commands.put(commandAdd.getName(), commandAdd);
        CommandUpdate commandUpdate = new CommandUpdate(collectionManager, personFiller);
        commands.put(commandUpdate.getName(), commandUpdate);
        ExecuteScriptCommand executeScriptCommand = new ExecuteScriptCommand(userIO);
        commands.put(executeScriptCommand.getName(), executeScriptCommand);
        ExitCommand exitCommand = new ExitCommand();
        commands.put(exitCommand.getName(), exitCommand);
        HeadCommand headCommand = new HeadCommand(collectionManager, userIO);
        commands.put(headCommand.getName(), headCommand);
        HelpCommand helpCommand = new HelpCommand(userIO, commands);
        commands.put(helpCommand.getName(), helpCommand);
        historyCommand = new HistoryCommand(userIO);
        commands.put(historyCommand.getName(), historyCommand);
        InfoCommand infoCommand = new InfoCommand(collectionManager, userIO);
        commands.put(infoCommand.getName(), infoCommand);
        RemoveByIdCommand removeByIdCommand = new RemoveByIdCommand(collectionManager);
        commands.put(removeByIdCommand.getName(), removeByIdCommand);
        RemoveLowerCommand removeLowerCommand = new RemoveLowerCommand(collectionManager, personFiller);
        commands.put(removeLowerCommand.getName(), removeLowerCommand);
        SaveCommand saveCommand = new SaveCommand(collectionManager, fileName);
        commands.put(saveCommand.getName(), saveCommand);
        ShowCommand showCommand = new ShowCommand(collectionManager, userIO);
        commands.put(showCommand.getName(), showCommand);
        AverageOfHeightCommand averageOfHeightCommand = new AverageOfHeightCommand(collectionManager, userIO);
        commands.put(averageOfHeightCommand.getName(), averageOfHeightCommand);
        GroupCountingByCreationDateCommand groupCountingByCreationDateCommand = new GroupCountingByCreationDateCommand(collectionManager, userIO);
        commands.put(groupCountingByCreationDateCommand.getName(), groupCountingByCreationDateCommand);
        FilterLessThanNationalityCommand filterLessThanNationalityCommand = new FilterLessThanNationalityCommand(collectionManager, userIO, personFiller);
        commands.put(filterLessThanNationalityCommand.getName(), filterLessThanNationalityCommand);
    }

    public void addToHistory(Command command) {
        historyCommand.add(command);
    }

    public HashMap<String, Command> getCommands() {
        return commands;
    }
}

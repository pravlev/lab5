package com.lev_prav.client.utility;

import com.lev_prav.client.exceptions.IllegalValueException;
import com.lev_prav.client.exceptions.ScriptException;
import com.lev_prav.client.userio.UserIO;

public class SimplePersonFiller<T> {
    private UserIO userIO;

    public SimplePersonFiller(UserIO userIO) {
        this.userIO = userIO;
    }

    public T fill(String message, Reader<T> reader) throws ScriptException {
        T returns;
        while (true) {
            try {
                userIO.write(message + ": ");
                returns = reader.read();
                break;
            } catch (NumberFormatException e) {
                userIO.writeln("Value must be a number");
                if (userIO.isScriptMode()) {
                    throw new ScriptException();
                }
            } catch (IllegalArgumentException e) {
                userIO.writeln("Chose anything from list");
                if (userIO.isScriptMode()) {
                    throw new ScriptException();
                }
            } catch (IllegalValueException e) {
                userIO.writeln(e.getMessage());
                if (userIO.isScriptMode()) {
                    throw new ScriptException();
                }
            }
        }
        return returns;
    }
}

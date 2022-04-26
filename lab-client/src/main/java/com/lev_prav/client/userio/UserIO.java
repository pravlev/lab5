package com.lev_prav.client.userio;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Stack;

public class UserIO {
    private Scanner fin;
    private Stack<Scanner> fileScanners;
    private BufferedWriter fout;
    private boolean scriptMode;

    public UserIO(Scanner fin, Writer fout) {
        this.fin = fin;
        this.fout = new BufferedWriter(fout);
        scriptMode = false;
    }

    public UserIO(Scanner fin, Writer fout, boolean scriptMode) {
        this(fin, fout);
        this.scriptMode = scriptMode;
    }

    public String readline() {
        if (!scriptMode) {
            return fin.nextLine();
        } else {
            if (fileScanners.peek().hasNext()) {
                return fileScanners.peek().nextLine();
            } else {
                finishReadScript();
                try {
                    fout.write("Reached the end of the file.");
                    fout.flush();
                } catch (IOException e) {
                    System.err.println("Exception while writing to output stream: \n" + e);
                }
                return fin.nextLine();
            }
        }
    }

    public void write(Object s) {
        if (scriptMode) {
            return;
        }
        try {
            fout.write(s.toString());
            fout.flush();
        } catch (IOException e) {
            System.err.println("Exception while writing to output stream: \n" + e);
        }
    }

    public void writeln(Object s) {
        if (scriptMode) {
            return;
        }
        try {
            fout.write(s.toString());
            fout.newLine();
            fout.flush();
        } catch (IOException e) {
            System.err.println("Exception while writing to output stream: \n" + e);
        }
    }

    public Scanner getFin() {
        return fin;
    }

    public void setFin(Scanner fin) {
        this.fin = fin;
    }

    public BufferedWriter getFout() {
        return fout;
    }

    public void setFout(BufferedWriter fout) {
        this.fout = fout;
    }

    public void setScriptMode(boolean scriptMode) {
        this.scriptMode = scriptMode;
    }

    public boolean isScriptMode() {
        return scriptMode;
    }

    public void startReadScript(String fileName) {
        if (fileScanners == null) {
            fileScanners = new Stack<>();
        }
        try {
            writeln("Start reading from file " + fileName + "...");
            fileScanners.push(new Scanner(Paths.get(fileName)));
            scriptMode = true;
        } catch (IOException e) {
            writeln("Cannot find file with this name");
        }

    }

    public void finishReadScript() {
        writeln("Reading from file was finished");
        if (!fileScanners.empty()) {
            fileScanners.peek().close();
            fileScanners.pop();
        }
        if (fileScanners.empty()) {
            scriptMode = false;
        }
    }
}

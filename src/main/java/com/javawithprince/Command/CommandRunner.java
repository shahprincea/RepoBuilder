package com.javawithprince.Command;

import java.util.ArrayList;

public class CommandRunner {

    private Command cmd;

    public CommandRunner(Command cmd) {
        this.cmd = cmd;
    }

    public ArrayList<String> run(String arg) {
        return this.cmd.execute(arg);
    }
}

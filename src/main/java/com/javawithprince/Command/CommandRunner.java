package com.javawithprince.Command;

import com.javawithprince.POJO.RepoContext;

import java.util.ArrayList;

public class CommandRunner {

    private final Command cmd;

    public CommandRunner(Command cmd) {
        this.cmd = cmd;
    }

    public ArrayList<String> run(String arg, RepoContext context) {
        return this.cmd.execute(arg, context);
    }
}

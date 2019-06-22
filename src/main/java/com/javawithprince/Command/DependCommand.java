package com.javawithprince.Command;

import com.javawithprince.POJO.RepoContext;

import java.util.ArrayList;

public class DependCommand implements Command {

    @Override
    public ArrayList<String> execute(String arg, RepoContext context) throws IllegalArgumentException {

        ArrayList<String> list = new ArrayList<>();

        //No - op
        if(arg == null || arg.length() == 0)
            return list;

        //No - op
        String[] tokens = arg.split("\\s+");
        if(tokens.length == 1)
            return list;
        else {

            String src = tokens[0];
            for(int i = 1; i < tokens.length; i++) {
                if(context.addDependencies(src, tokens[i])) {
                   //TODO logging
                }
                if(context.isCyclic()) {
                    throw new IllegalArgumentException("Cyclic depedencies detected");
                }
            }
        }
        return list;
    }
}

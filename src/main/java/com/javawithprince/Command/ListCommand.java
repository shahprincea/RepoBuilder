package com.javawithprince.Command;

import com.javawithprince.POJO.RepoContext;

import java.util.ArrayList;

public class ListCommand implements Command {

    @Override
    public ArrayList<String> execute(String arg) {
        RepoContext context = RepoContext.getInstance();
        ArrayList<String> result = new ArrayList<>();
        for(String pkg : context.getInstalledPkg())
            result.add("    "+ pkg);
        return result;
    }
}

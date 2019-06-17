package com.javawithprince.Command;

import com.javawithprince.POJO.RepoContext;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class RemoveCommand implements Command {

    private static String STILL_NEEDED = "  {0} is still needed";
    private static String SUCCESSFULLY_REMOVED = "  {0} successfully removed";
    private static String NOT_NEEDED = "  {0} is no longer needed";
    private static String NOT_INSTALLED = "  {0} is not installed";

    @Override
    public ArrayList<String> execute(String pkg) {

        RepoContext context = RepoContext.getInstance();
        ArrayList<String> pkgReverseDependencies = context.getReverseDependencies(pkg);
        HashSet<String> installedPkgs = context.getInstalledPkg();
        HashSet<String> installedPKgsExplicit = context.getInstalledPKgsExplicit();

        ArrayList<String> result = new ArrayList<>();

        if(getUsedByAndInstalled(installedPkgs, context.getReverseDependencies(pkg)) > 0)
            result.add(MessageFormat.format(STILL_NEEDED, pkg));
        else
            removeRecursively(context, pkg, installedPkgs, installedPKgsExplicit, result);

        return result;
    }

    private void removeRecursively(RepoContext context, String pkg, HashSet<String> installedPkgs, HashSet<String> installedPKgsExplicit, ArrayList<String> result) {

        if(!installedPkgs.contains(pkg)) {
            result.add(MessageFormat.format(NOT_INSTALLED, pkg));
            return;
        }


        //Find out which all packets uses this pkg
        ArrayList<String> usedBy = context.getReverseDependencies(pkg);

        int usedByAndInstalled = getUsedByAndInstalled(installedPkgs, usedBy);

        //You should only delete the package if it is not explicitly installed and has zero dependencies
        if(usedByAndInstalled == 0) {
            installedPkgs.remove(pkg);
            result.add(MessageFormat.format(SUCCESSFULLY_REMOVED, pkg));
            installedPKgsExplicit.remove(pkg);

            //Adjust reverse dependencies
            for(String dependentPkg : context.getDependencies(pkg)) {
                HashMap<String, HashSet<String>> reverseDependenciesMap = context.getReverseDependenciesMap(dependentPkg);
                reverseDependenciesMap.get(dependentPkg).remove(pkg);

                if(getUsedByAndInstalled(installedPkgs, new ArrayList<>(reverseDependenciesMap.get(dependentPkg))) == 0 && !installedPKgsExplicit.contains(dependentPkg)) {
                    result.add(MessageFormat.format(NOT_NEEDED, dependentPkg));
                    removeRecursively(context, dependentPkg, installedPkgs, installedPKgsExplicit, result);
                }
            }

        }
    }

    private int getUsedByAndInstalled(HashSet<String> installedPkgs, ArrayList<String> usedBy) {
        int count = 0;
        for(String usedByPkg : usedBy) {
            if(installedPkgs.contains(usedByPkg))
                count++;
        }
        return count;
    }
}

package com.javawithprince.Command;

import com.javawithprince.POJO.RepoContext;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * This class is responsible for installing pkg and its dependencies
 *
 * Find connected compo
 * Find Cycle
 *
 */
public class InstallCommand implements Command {

    private static String INSTALLED_SUCCESSFUL = "  {0} successfully installed";
    private static String ALREADY_INSTALLED = "  {0} is already installed";

    @Override
    public ArrayList<String> execute(String pkg) {

        RepoContext context = RepoContext.getInstance();
        ArrayList<String> pkgDependencies = context.getDependencies(pkg);
        HashSet<String> installedPkgs = context.getInstalledPkg();
        HashSet<String> installedPKgsExplicit = context.getInstalledPKgsExplicit();

        ArrayList<String> result = new ArrayList<>();

        installRecursively(context, pkg, installedPkgs, result);

        installedPKgsExplicit.add(pkg);

        return result;
    }

    private void installRecursively(RepoContext context, String pkg, HashSet<String> installedPkgs, ArrayList<String> result) {

        //If pkg is already installed skipped
        if(installedPkgs.contains(pkg)) {
            result.add(MessageFormat.format(ALREADY_INSTALLED, pkg));
        }

        for(String dependentPkg : context.getDependencies(pkg)) {
            if(!installedPkgs.contains(dependentPkg)) {
                installRecursively(context, dependentPkg, installedPkgs, result);
            }
        }

        if(context.install(pkg)) {
            result.add(MessageFormat.format(INSTALLED_SUCCESSFUL, pkg));
            installedPkgs.add(pkg);
        }

    }
}

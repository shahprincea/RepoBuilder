package com.javawithprince;

import com.javawithprince.Command.*;
import com.javawithprince.POJO.RepoContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App
{

    public ArrayList<String> run(List<String> lines) {

        RepoContext context = new RepoContext();

        Command listCommand = new ListCommand();
        Command dependCommand = new DependCommand();
        Command installCommand = new InstallCommand();
        Command removeCommand = new RemoveCommand();


        HashMap<String, Command> map = new HashMap<>();
        map.put("DEPEND", dependCommand);
        map.put("INSTALL", installCommand);
        map.put("REMOVE", removeCommand);
        map.put("LIST", listCommand);

        ArrayList<String> result = new ArrayList<>();

        for(String line : lines) {

            result.add(line);

            String[] tokens = line.split("\\s+");

            String cmdArgs = "";
            String cmdType = tokens[0];
            if(tokens.length > 1) {
                StringBuilder stringBuilder = new StringBuilder();
                for(int i = 1; i < tokens.length; i++)
                    stringBuilder.append(tokens[i]).append(" ");
                stringBuilder.deleteCharAt(stringBuilder.length()-1);
                cmdArgs = stringBuilder.toString();
            }
            Command cmd = map.get(cmdType);
            if(cmd != null)
                result.addAll(new CommandRunner(cmd).run(cmdArgs, context));

        }

        return result;
    }
}

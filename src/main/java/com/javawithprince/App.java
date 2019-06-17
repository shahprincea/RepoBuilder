package com.javawithprince;

import com.javawithprince.Command.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App
{

    public static ArrayList<String> run(List<String> lines) {

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
                result.addAll(new CommandRunner(cmd).run(cmdArgs));

        }

        return result;
    }

    public static void main(String[] args) throws IllegalArgumentException {

        List<String> lines = new ArrayList<>();
        lines = Arrays.asList("DEPEND TELNET TCPIP NETCARD",
                "DEPEND TCPIP NETCARD",
                "DEPEND DNS TCPIP NETCARD",
                "DEPEND BROWSER TCPIP HTML",
                "INSTALL NETCARD",
                "INSTALL TELNET",
                "INSTALL FOO",
                "REMOVE NETCARD",
                "INSTALL BROWSER",
                "INSTALL DNS",
                "LIST",
                "REMOVE TELNET",
                "REMOVE NETCARD",
                "REMOVE DNS",
                "REMOVE NETCARD",
                "INSTALL NETCARD",
                "REMOVE TCPIP",
                "REMOVE BROWSER",
                "REMOVE TCPIP",
                "LIST");



        //lines = Arrays.asList("DEPEND A B", "DEPEND B A");

        /*lines = Arrays.asList(
                "DEPEND A B",
                "DEPEND B C",
                "DEPEND C D",
                "DEPEND D E",
                "INSTALL A",
                "LIST",
                "REMOVE D",
                "REMOVE A",
                "LIST");*/

        /*lines = Arrays.asList(
                "DEPEND A B C",
                "DEPEND B C",
                "INSTALL A"
                );*/

        /*lines = Arrays.asList(
                "DEPEND A D C",
                "DEPEND D C",
                "INSTALL A"
        );*/

        //Cycle detected
        /*lines = Arrays.asList(
                "DEPEND J D C A",
                "DEPEND D C",
                "DEPEND C J",
                "INSTALL J"
        );*/

        //Recursively Remove
        /*lines = Arrays.asList(
                "DEPEND A B",
                "DEPEND B C",
                "DEPEND D B", //BUG dep defined by not installed it still blocks removal
                "INSTALL A",
                //"INSTALL D",
                "LIST",
                "REMOVE C",
                "REMOVE B",
                "REMOVE A",
                "LIST"
        );*/


        //recursively Depend
        /*lines = Arrays.asList(
                "DEPEND A B",
                "DEPEND B C",
                "INSTALL A",
                "REMOVE A",
                "LIST"
                );*/


        //Depend
        /*lines = Arrays.asList(
                "DEPEND A B",
                "DEPEND B C",
                "DEPEND D C",
                "INSTALL A",
                "REMOVE A",
                "LIST"
                );*/



        ArrayList<String> result = run(lines);
        for(String line : result)
            System.out.println(line);
    }
}

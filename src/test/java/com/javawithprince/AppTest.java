package com.javawithprince;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppTest 
{

    @Test
    public void longInterdependentTest()
    {
        List<String> lines = new ArrayList<>();
        App app = new App();

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

        List<String> result = app.run(lines);

        StringBuilder stringBuilder = new StringBuilder();
        //Store result of last command LIST
        stringBuilder.append(result.get(result.size()-1)).append(" ").append(result.get(result.size()-2));
        String output = stringBuilder.toString().trim();

        assertTrue( output.equals("NETCARD     FOO"));
    }

    //@Test(expected = IllegalArgumentException.class)
    public void detectCycleTest()
    {
        App app = new App();
        List<String> lines = new ArrayList<>();

        lines = Arrays.asList("DEPEND J D C A",
                "DEPEND D C",
                "DEPEND C J",
                "INSTALL J");

        app.run(lines);
    }

    //@Test
    public void recursivelyRemoveDependency()
    {
        List<String> lines = new ArrayList<>();
        App app = new App();

        lines = Arrays.asList("DEPEND A B",
                "DEPEND B C",
                "DEPEND D B",
                "INSTALL A",
                //"INSTALL D",
                "LIST",
                "REMOVE C",
                "REMOVE B",
                "REMOVE A",
                "LIST");

        List<String> result = app.run(lines);

        assertTrue("LIST".equals(result.get(result.size()-1)));
    }
}

package com.javawithprince.Command;

import com.javawithprince.POJO.RepoContext;

import java.util.ArrayList;

public interface Command {

    ArrayList<String> execute(String arg, RepoContext context);
}

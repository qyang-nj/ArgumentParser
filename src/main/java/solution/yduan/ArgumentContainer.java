package solution.yduan;

import solution.yduan.argument.Argument;

import java.util.HashMap;
import java.util.Map;

public class ArgumentContainer {
    private  Map<Character, Argument> argumentDict;

    public ArgumentContainer() {
        this.argumentDict = new HashMap<>();
    }

    public Argument get(char arg) {
        return argumentDict.get(arg);
    }

    public boolean has(char arg) {
        return argumentDict.containsKey(arg);
    }

    public void put(char elementId, Argument newArg) {
        argumentDict.put(elementId, newArg);
    }
}

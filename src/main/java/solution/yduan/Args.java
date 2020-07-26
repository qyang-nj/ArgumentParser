package solution.yduan;

import solution.yduan.argument.*;

import java.util.*;

public class Args extends base.Args {
    private ArgumentContainer argumentContainer;

    public Args(String schema, String[] args) throws ArgsException {
        argumentContainer = new ArgSchemaParser().buildArgContainerBasedOnSchema(schema);
        new ArgStringParser()
                .parseArgStrIntoContainer(Arrays.asList(args),
                        argumentContainer);
    }

    public boolean has(char arg) {
        return argumentContainer.has(arg);
    }

    public boolean getBoolean(char arg) {
        return BooleanArgument.getValue(argumentContainer.get(arg));
    }

    public String getString(char arg) {
        return StringArgument.getValue(argumentContainer.get(arg));
    }

    public int getInt(char arg) {
        return IntegerArgument.getValue(argumentContainer.get(arg));
    }

    public double getDouble(char arg) {
        return DoubleArgument.getValue(argumentContainer.get(arg));
    }

    public String[] getStringArray(char arg) {
        return StringArrayArgument.getValue(argumentContainer.get(arg));
    }
}

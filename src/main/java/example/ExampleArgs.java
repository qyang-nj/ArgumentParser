package example;

import base.Args;
import base.ArgsException;

public class ExampleArgs extends Args {
    public ExampleArgs(String schema, String[] args) throws ArgsException {
//        super(schema, args);
    }

    @Override
    public boolean has(char arg) {
        return false;
    }

    @Override
    public boolean getBoolean(char arg) {
        return false;
    }

    @Override
    public String getString(char arg) {
        return null;
    }

    @Override
    public int getInt(char arg) {
        return 0;
    }

    @Override
    public double getDouble(char arg) {
        return 0;
    }

    @Override
    public String[] getStringArray(char arg) {
        return new String[0];
    }
}

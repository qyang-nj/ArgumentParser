package base;

public abstract class Args {
    public Args(String schema, String[] args) throws ArgsException {
    }

    public abstract boolean has(char arg);

    public abstract boolean getBoolean(char arg);

    public abstract String getString(char arg);

    public abstract int getInt(char arg);

    public abstract double getDouble(char arg);

    public abstract String[] getStringArray(char arg);
}

package solution.qyang;

public abstract class ArgumentBox {
    protected Object value;

    public abstract void set(String value) throws ArgsException;

    public Object get() {
        return value;
    }
}

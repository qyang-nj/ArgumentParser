package solution.qyang;

public class BooleanBox extends ArgumentBox {

    @Override
    public void set(String value) throws ArgsException {
        this.value = Boolean.parseBoolean(value);
    }
}

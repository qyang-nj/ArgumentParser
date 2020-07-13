package solution.qyang;

public class StringBox extends ArgumentBox {
    @Override
    public void set(String value) throws ArgsException {
        this.value = value;
    }
}

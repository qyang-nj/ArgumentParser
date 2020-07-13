package solution.qyang;

public class StringArrayBox extends ArgumentBox {
    @Override
    public void set(String value) throws ArgsException {
        this.value = new String[]{};
    }
}

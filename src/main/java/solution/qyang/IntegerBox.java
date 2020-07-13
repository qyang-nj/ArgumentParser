package solution.qyang;

public class IntegerBox extends ArgumentBox {
    @Override
    public void set(String value) throws ArgsException {
        try {
            this.value = Integer.parseInt(value);
        }
        catch (NumberFormatException e) {
            throw new ArgsException();
        }
    }
}

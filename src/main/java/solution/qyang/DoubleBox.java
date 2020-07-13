package solution.qyang;

public class DoubleBox extends ArgumentBox {

    @Override
    public void set(String value) throws ArgsException {
        try {
            this.value = Double.parseDouble(value);
        }
        catch (NumberFormatException e) {
            throw new ArgsException();
        }
    }
}

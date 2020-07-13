package solution.qyang;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Args extends base.Args {
    Map<Character, ArgumentBox> boxes = new HashMap<>();

    public Args(String schema, String[] args) throws ArgsException {
        parseSchema(schema);
        parseArgumentStrings(args);
    }

    private void parseSchema(String schema) throws ArgsException {
        for (String element : schema.split(",")) {
            if (element.length() > 0) {
                parseSchemaElement(element.trim());
            }
        }
    }

    private void parseSchemaElement(String element) throws ArgsException {
        char elementId = element.charAt(0);
        String elementTail = element.substring(1);
        validateSchemaElementId(elementId);
        if (elementTail.length() == 0) {
            boxes.put(elementId, new BooleanBox());
        }
        else if (elementTail.equals("*")) {
            boxes.put(elementId, new StringBox());
        }
        else if (elementTail.equals("#")) {
            boxes.put(elementId, new IntegerBox());
        }
        else if (elementTail.equals("##")) {
            boxes.put(elementId, new DoubleBox());
        }
        else if (elementTail.equals("[*]")) {
            boxes.put(elementId, new StringArrayBox());
        }
        else {
            throw new ArgsException();
        }
    }

    private void validateSchemaElementId(char elementId) throws ArgsException {
        if (!Character.isLetter(elementId)) {
            throw new ArgsException();
        }
    }

    private void parseArgumentStrings(String[] args) throws ArgsException {
        String argChars = parseArgumentCharacters(args[0]);
        String[] argValues = Arrays.copyOfRange(args, 1, args.length);

        for (int i = 0; i < argChars.length(); ++i) {
            char key = argChars.charAt(i);
            parseArgumentCharacter(key, argValues, i);
        }
    }

    private String parseArgumentCharacters(String argCharsString) throws ArgsException {
        String argChars = argCharsString;
        if (argChars.length() == 0 || argChars.charAt(0) != '-') {
            throw new ArgsException();
        }
        return argChars.substring(1);
    }

    private void parseArgumentCharacter(char arg, String[] argValues, int index) throws ArgsException {
        if (index >= argValues.length) {
            throw new ArgsException();
        }
        String value = argValues[index];

        ArgumentBox box = boxes.get(arg);
        if (box == null) {
            throw new ArgsException();
        }

        box.set(value);
    }

    @Override
    public boolean has(char arg) {
        return boxes.get(arg).get() != null;
    }

    @Override
    public boolean getBoolean(char arg) {
        return getValue(arg, false);
    }

    @Override
    public String getString(char arg) {
        return getValue(arg, "");
    }

    @Override
    public int getInt(char arg) {
        return getValue(arg, 0);
    }

    @Override
    public double getDouble(char arg) {
        return getValue(arg, 0.0);
    }

    @Override
    public String[] getStringArray(char arg) {
        return getValue(arg, new String[]{});
    }

    public <T> T getValue(char arg, T defaultValue) {
        T value = null;
        try {
            Object unboxed = boxes.get(arg).get();
            value = unboxed == null ? defaultValue : (T)unboxed;
        }
        catch (ClassCastException e) {
            value = defaultValue;
        }
        return value;
    }
}

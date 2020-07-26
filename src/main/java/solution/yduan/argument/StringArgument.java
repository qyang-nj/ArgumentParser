package solution.yduan.argument;

import solution.yduan.ArgsException;

import static solution.yduan.ArgsException.ErrorCode.MISSING_STRING;

public class StringArgument implements Argument {
    private String stringValue = "";

    public void set(String currentArgument) throws ArgsException {
        stringValue = currentArgument;
    }

    @Override
    public ArgsException.ErrorCode getMissingElementErrorCode() {
        return MISSING_STRING;
    }

    public static String getValue(Argument am) {
        if (am != null && am instanceof StringArgument)
            return ((StringArgument) am).stringValue;
        else
            return "";
    }
}

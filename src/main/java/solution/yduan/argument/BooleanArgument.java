package solution.yduan.argument;

import solution.yduan.ArgsException;

import static solution.yduan.ArgsException.ErrorCode.MISSING_BOOLEAN;

public class BooleanArgument implements Argument {
    private boolean booleanValue = false;

    public void set(String currentArgument) throws ArgsException {
        booleanValue = Boolean.parseBoolean(currentArgument);
    }

    @Override
    public ArgsException.ErrorCode getMissingElementErrorCode() {
        return MISSING_BOOLEAN;
    }

    public static boolean getValue(Argument am) {
        if (am != null && am instanceof BooleanArgument)
            return ((BooleanArgument) am).booleanValue;
        else
            return false;
    }
}

package solution.yduan.argument;

import solution.yduan.ArgsException;

import static solution.yduan.ArgsException.ErrorCode.INVALID_INTEGER;
import static solution.yduan.ArgsException.ErrorCode.MISSING_INTEGER;

public class IntegerArgument implements Argument {
    private int intValue = 0;

    public void set(String currentArgument) throws ArgsException {
        try {
            intValue = Integer.parseInt(currentArgument);
        } catch (NumberFormatException e) {
            throw new ArgsException(INVALID_INTEGER, currentArgument);
        }
    }

    @Override
    public ArgsException.ErrorCode getMissingElementErrorCode() {
        return MISSING_INTEGER;
    }

    public static int getValue(Argument am) {
        if (am != null && am instanceof IntegerArgument)
            return ((IntegerArgument) am).intValue;
        else
            return 0;
    }
}
package solution.yduan.argument;

import solution.yduan.ArgsException;

import static solution.yduan.ArgsException.ErrorCode.INVALID_DOUBLE;
import static solution.yduan.ArgsException.ErrorCode.MISSING_DOUBLE;

public class DoubleArgument implements Argument {
    private double doubleValue = 0;

    public void set(String currentArgument) throws ArgsException {
        try {
            doubleValue = Double.parseDouble(currentArgument);
        } catch (NumberFormatException e) {
            throw new ArgsException(INVALID_DOUBLE, currentArgument);
        }
    }

    @Override
    public ArgsException.ErrorCode getMissingElementErrorCode() {
        return MISSING_DOUBLE;
    }

    public static double getValue(Argument am) {
        if (am != null && am instanceof DoubleArgument)
            return ((DoubleArgument) am).doubleValue;
        else
            return 0;
    }
}

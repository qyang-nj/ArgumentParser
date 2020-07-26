package solution.yduan.argument;

import solution.yduan.ArgsException;

public interface Argument {
    void set(String currentArgument) throws ArgsException;

    ArgsException.ErrorCode getMissingElementErrorCode();
}

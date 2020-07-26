package solution.yduan.argument;

import solution.yduan.ArgsException;

public class StringArrayArgument implements Argument {
    @Override
    public void set(String currentArgument) throws ArgsException {
        //TODO: Auto-generated
    }

    @Override
    public ArgsException.ErrorCode getMissingElementErrorCode() {
        //TODO: Auto-generated
        return null;
    }

    public static String[] getValue(Argument argument) {
        return new String[0];  //TODO: Auto-generated
    }
}
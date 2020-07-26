package solution.yduan;

import solution.yduan.argument.Argument;

import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import static solution.yduan.ArgsException.ErrorCode.*;

public class ArgStringParser {
    private ListIterator<String> currentArgument;
    private ArgumentContainer argumentContainer;

    public void parseArgStrIntoContainer(List<String> argsList,
                                         ArgumentContainer argumentContainer) throws ArgsException {
        this.argumentContainer = argumentContainer;
        if (argsList.size() > 0) {
            currentArgument = argsList.listIterator();
            String argString = currentArgument.next();
            if (argString.startsWith("-")) {
                parseArgumentCharacters(argString.substring(1));
            } else {
                throw new ArgsException(INVALID_ARGUMENT_FORMAT, '-', "");
            }
        }
    }

    private void parseArgumentCharacters(String argChars) throws ArgsException {
        for (int i = 0; i < argChars.length(); i++)
            parseArgumentCharacter(argChars.charAt(i));
    }

    private void parseArgumentCharacter(char argChar) throws ArgsException {
        Argument m = argumentContainer.get(argChar);
        if (m == null) {
            throw new ArgsException(UNEXPECTED_ARGUMENT, argChar, null);
        } else {
            try {
                m.set(currentArgument.next());
            } catch (NoSuchElementException e) {
                throw new ArgsException(m.getMissingElementErrorCode());
            }catch (ArgsException e) {
                e.setErrorArgumentId(argChar);
                throw e;
            }
        }
    }
}

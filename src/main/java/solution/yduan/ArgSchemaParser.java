package solution.yduan;

import solution.yduan.argument.*;

import static solution.yduan.ArgsException.ErrorCode.INVALID_ARGUMENT_FORMAT;
import static solution.yduan.ArgsException.ErrorCode.INVALID_ARGUMENT_NAME;

public class ArgSchemaParser {
    private ArgumentContainer argumentContainer;

    public ArgumentContainer buildArgContainerBasedOnSchema(String schema) throws ArgsException {
        argumentContainer = new ArgumentContainer();
        for (String element : schema.split(",")) {
            if (element.length() > 0) {
                parseSchemaElement(element.trim());
            }
        }

        return argumentContainer;
    }

    private void parseSchemaElement(String element) throws ArgsException {
        char elementId = element.charAt(0);
        String elementTail = element.substring(1);

        validateSchemaElementId(elementId);

        Argument newArg = ArgumentGenerator.generateArgumentBasedOn(elementTail);
        if (newArg != null) {
            argumentContainer.put(elementId, newArg);
        } else {
            throw new ArgsException(INVALID_ARGUMENT_FORMAT, elementId, elementTail);
        }
    }

    private void validateSchemaElementId(char elementId) throws ArgsException {
        if (!Character.isLetter(elementId))
            throw new ArgsException(INVALID_ARGUMENT_NAME, elementId, null);
    }

    private static class ArgumentGenerator {
        private static Argument generateArgumentBasedOn(String schemaElementTail) {
            if (schemaElementTail.length() == 0)
                return new BooleanArgument();
            else if (schemaElementTail.equals("*"))
                return new StringArgument();
            else if (schemaElementTail.equals("#"))
                return new IntegerArgument();
            else if (schemaElementTail.equals("##"))
                return new DoubleArgument();
            else if (schemaElementTail.equals("[*]"))
                return new StringArrayArgument();
            else
                return null;
        }
    }
}

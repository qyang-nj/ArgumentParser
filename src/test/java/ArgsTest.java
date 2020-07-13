
import base.Args;
import base.ArgsException;
import org.junit.Ignore;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;


public class ArgsTest {

    Args newInstance(String schema, String[] args) throws ArgsException {
        Args arg = null;
        try {
            String classNane = System.getProperty("argsClass");
            Class cl = Class.forName(classNane);
            java.lang.reflect.Constructor constructor = cl.getConstructor(new Class[] {String.class, String[].class});
            arg = (Args)constructor.newInstance(new Object[]{schema, args});
        }
        catch(InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException | ClassNotFoundException e) {
            if (e.getCause() instanceof ArgsException) {
                throw (ArgsException)e.getCause();
            } else {
                throw new IllegalArgumentException(e);
            }
        }
        return arg;
    }

    @Test
    public void testSingleArg_Boolean_whenValueIsTrue() throws ArgsException {
        Args arg = newInstance("b", new String[]{"-b", "true"});
        assertTrue(arg.getBoolean('b'));
    }

    @Test
    public void testSingleArg_Boolean_whenValueIsFalse() throws ArgsException {
        Args arg = newInstance("b", new String[]{"-b", "false"});
        assertFalse(arg.getBoolean('b'));
    }

    @Test
    public void testSingleArg_Boolean_whenValueIsMalformed() throws ArgsException {
        Args arg = newInstance("b", new String[]{"-b", "haha"});
        // Uncle Bob's implement uses `Boolean.parseBoolean`, which parses non "true" string to false.
        assertFalse(arg.getBoolean('b'));
    }

    @Test
    public void testSingleArg_Integer() throws ArgsException {
        Args arg = newInstance("i#", new String[]{"-i", "12"});
        assertEquals(arg.getInt('i'), 12);
    }

    @Test(expected = ArgsException.class)
    public void testSingleArg_Integer_whenValueIsMalformed() throws ArgsException {
        newInstance("i#", new String[]{"-i", "1x2"});
    }

    @Test(expected = ArgsException.class)
    public void testSingleArg_Integer_whenValueIsMissing() throws ArgsException {
        newInstance("i#", new String[]{"-i"});
    }

    @Test(expected = ArgsException.class)
    public void testSingleArg_Integer_whenArgIsMissing() throws ArgsException {
        newInstance("i#", new String[]{""});
    }

    @Test
    public void testSingleArg_String() throws ArgsException {
        Args arg = newInstance("s*", new String[]{"-s", "string"});
        assertEquals(arg.getString('s'), "string");
    }

    @Test(expected = ArgsException.class)
    public void testSingleArg_String_whenValueIsMissing() throws ArgsException {
        newInstance("s*", new String[]{"-s"});
    }

    @Test(expected = ArgsException.class)
    public void testSingleArg_String_whenArgIsMissing() throws ArgsException {
        Args arg = newInstance("s*", new String[]{""});
        assertEquals(arg.getString('s'), "");
    }

    @Test
    public void testSingleArg_String_whenValueHasSpace() throws ArgsException {
        Args arg = newInstance("s*", new String[]{"-s", "a string"});
        assertEquals(arg.getString('s'), "a string");
    }

    @Test
    public void testSingleArg_Double() throws ArgsException {
        Args arg = newInstance("d##", new String[]{"-d", "10.1"});
        assertEquals(arg.getDouble('d'), 10.1, 0.0001);
    }

    @Test(expected = ArgsException.class)
    public void testSingleArg_Double_whenValueIsMissing() throws ArgsException {
        newInstance("d##", new String[]{"-d"});
    }

    @Test(expected = ArgsException.class)
    public void testSingleArg_Double_whenValueIsMalformed() throws ArgsException {
        newInstance("d##", new String[]{"-d", "10.x1"});
    }

    @Test(expected = ArgsException.class)
    public void testSingleArg_Double_whenArgIsMissing() throws ArgsException {
        Args arg = newInstance("d##", new String[]{""});
        assertEquals(arg.getDouble('d'), 0, 0.0001);
    }

    @Test
    public void testMultiArgs() throws ArgsException {
        Args arg = newInstance("b,i#,s*", new String[]{"-bis", "true", "12", "string"});
        assertTrue(arg.getBoolean('b'));
        assertEquals(arg.getInt('i'), 12);
        assertEquals(arg.getString('s'), "string");
    }

    @Test(expected = ArgsException.class)
    public void testMultiArgs_whenStringValueIsMissing() throws ArgsException {
        newInstance("b,i#,s*", new String[]{"-bis", "true", "12"});
    }

    @Test(expected = ArgsException.class)
    public void testMultiArgs_whenStringArgIsMissing2() throws ArgsException {
        newInstance("b,i#", new String[]{"-bis", "true", "12", "string"});
    }

    @Ignore
    @Test
    public void testMultiArgs_separate() throws ArgsException {
        Args arg = newInstance("b,s*", new String[]{"-b", "true", "-s", "string"});
        assertTrue(arg.getBoolean('b'));
        assertEquals(arg.getString('s'), "string");
    }

    @Ignore
    @Test
    public void testSingleArg_StringArray() throws ArgsException {
        Args arg = newInstance("s[*]", new String[]{"-s", "item1", "item2"});
        assertArrayEquals(arg.getStringArray('s'), new String[]{"item1", "item2"});
    }
}
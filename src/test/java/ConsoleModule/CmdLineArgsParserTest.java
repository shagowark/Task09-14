package ConsoleModule;

import org.junit.jupiter.api.Test;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;

class CmdLineArgsParserTest {

    @Test
    void cmdParserTest1() {
        assert new CmdLineArgsParser("-i input.txt -o output.txt").getArgsDict().equals(new Hashtable<>() {{
            put("-i", "input.txt");
            put("-o", "output.txt");
        }});
    }

    @Test
    void cmdParserTest2() {
        assert new CmdLineArgsParser("--input-file input.txt --output-file output.txt").getArgsDict().equals(new Hashtable<>() {{
            put("--input-file", "input.txt");
            put("--output-file", "output.txt");
        }});
    }

}
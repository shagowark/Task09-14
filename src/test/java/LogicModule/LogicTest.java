package LogicModule;

import ConsoleModule.CmdLineArgsParser;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

class LogicTest {

    @Test
    void solutionTest1() {
        List<Integer> list = Arrays.asList(2, 11, 11, 11, 2, 3, 2, 2, 6, 6, 2, 3, 3, 3, 10);
        List<Integer> newList = Arrays.asList(2, 11, 2, 3, 2, 6, 2, 3, 10);
        MatcherAssert.assertThat(Logic.createNewList(list), is(newList));
    }

    @Test
    void solutionTest2() {
        List<Integer> list = Arrays.asList(2, 2, 2, 2, 2, 2, 2);
        List<Integer> newList = List.of(2);
        MatcherAssert.assertThat(Logic.createNewList(list), is(newList));
    }

    @Test
    void solutionTest3() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> newList = Arrays.asList(1, 2, 3, 4, 5);
        MatcherAssert.assertThat(Logic.createNewList(list), is(newList));
    }

    @Test
    void solutionTest4() {
        List<Integer> list = Arrays.asList(100, 100, 2, 2, 1, 2, 3, 4, 5, 4);
        List<Integer> newList = Arrays.asList(100, 2, 1, 2, 3, 4, 5, 4);
        MatcherAssert.assertThat(Logic.createNewList(list), is(newList));
    }

    @Test
    void solutionTest5() {
        List<Integer> list = Arrays.asList(100, -100, 2, 12, -1, -1, 3, 5, 5, 4);
        List<Integer> newList = Arrays.asList(100, -100, 2, 12, -1, 3, 5, 4);
        MatcherAssert.assertThat(Logic.createNewList(list), is(newList));
    }

}
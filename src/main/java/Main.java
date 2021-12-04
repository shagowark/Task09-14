import ConsoleModule.CmdLineArgsParser;
import JFrameModule.Frame;
import LogicModule.Logic;
import Utils.ArrayUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Таск 9, вариант 14. Усталов Д.В. группа 6.2

public class Main {
    public static void main(String[] args) throws Exception{

        if (args.length == 0) {
            new Frame();
        } else {
            runInConsole(args);
        }
    }

    private static void runInConsole (String[] args) throws Exception{
        CmdLineArgsParser argsParser = new CmdLineArgsParser(args);

        String inputFilePath = argsParser.getArgumentValue("-i", "--input-file");
        String outputFilePath = argsParser.getArgumentValue("-o", "--output-file");

        List<Integer> list = new ArrayList<>();
        int[] fileList = ArrayUtils.readIntArrayFromFile(inputFilePath);
        Logic.checkIfArrayIsNull(fileList);
        Logic.checkIfArrayIsEmpty(fileList);
        for (int elem : fileList){
            list.add(elem);
        }

        Logic.saveOutputIntoFile(outputFilePath, Logic.createNewList(list));
    }

}

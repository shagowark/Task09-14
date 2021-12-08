package JFrameModule;

import LogicModule.Logic;
import Utils.ArrayUtils;
import Utils.JTableUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.List;

public class Frame extends JFrame{

    private JPanel panelMain;
    private JButton loadInputFromFileButton;
    private JButton getSolutionButton;
    private JButton saveOutputIntoFile;
    private JTable tableInput;
    private JTable tableOutput;

    private final JFileChooser fileChooserOpen;
    private final JFileChooser fileChooserSave;

    public Frame() {
        this.setTitle("Task9(14)");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panelMain);
        this.pack();

        JTableUtils.initJTableForArray(tableInput, 40, true, true, false, true);
        JTableUtils.resizeJTable(tableInput, 0,0, 30, 30);

        JTableUtils.initJTableForArray(tableOutput, 40, true, true, false, false);
        JTableUtils.resizeJTable(tableOutput, 0,0, 30, 30);

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        this.setVisible(true);
        this.setSize(340, 350);

        loadInputFromFileButton.addActionListener(e -> {
            try {
                if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    int[] arr = ArrayUtils.readIntArrayFromFile(fileChooserOpen.getSelectedFile().getPath());
                    Logic.checkIfArrayIsNull(arr);
                    Logic.checkIfArrayIsEmpty(arr);
                    JTableUtils.writeArrayToJTable(tableInput, arr);
                }
            } catch (Exception ex) {
                displayError("Пустой input файл");
            }
        });

        getSolutionButton.addActionListener(e -> {
            try {
                int[] arr = JTableUtils.readIntArrayFromJTable(tableInput);
                Logic.checkIfArrayIsNull(arr);
                Logic.checkIfArrayIsEmpty(arr);
                List<Integer> list = Logic.arrIntoList(arr);
                arr = Logic.listIntoArr(Logic.createNewList(list));
                JTableUtils.writeArrayToJTable(tableOutput, arr);
            }
            catch (Exception ex){
                displayError("Ошибка в исходных данных");
            }
        });

        saveOutputIntoFile.addActionListener(e -> {
            try {
                if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    int[] res = JTableUtils.readIntArrayFromJTable(tableOutput);
                    Logic.checkIfArrayIsNull(res);
                    Logic.checkIfArrayIsEmpty(res);
                    String file = fileChooserSave.getSelectedFile().getPath();
                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }
                    Logic.saveOutputIntoFile(file, Logic.arrIntoList(res));
                }
            } catch (Exception ex) {
               displayError("Ошибка при попытке сохранить файл");
            }
        });
    }

    private void displayError(String errorText) {
        JOptionPane.showMessageDialog(this, errorText,
                "Ошибка", JOptionPane.ERROR_MESSAGE);
    }
}


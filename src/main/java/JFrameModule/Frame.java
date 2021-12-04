package JFrameModule;

import LogicModule.Logic;
import Utils.ArrayUtils;
import Utils.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Frame extends JFrame{

    private JPanel panelMain;
    private JTextField textFieldInput;
    private JButton loadInputFromFileButton;
    private JButton getSolutionButton;
    private JButton saveOutputIntoFile;
    private JTextField textFieldOutput;

    private final JFileChooser fileChooserOpen;
    private final JFileChooser fileChooserSave;

    public Frame() {
        this.setTitle("Task8_22");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panelMain);
        this.pack();

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
        this.setSize(340, 250);

        loadInputFromFileButton.addActionListener(e -> {
            try {
                if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    int[] arr = ArrayUtils.readIntArrayFromFile(fileChooserOpen.getSelectedFile().getPath());
                    Logic.checkIfArrayIsNull(arr);
                    Logic.checkIfArrayIsEmpty(arr);
                    StringBuilder sb = new StringBuilder();
                    for (int elem : arr){
                        sb.append(elem);
                        sb.append(' ');
                    }
                    textFieldInput.setText(new String(sb).trim());
                }
            } catch (Exception ex) {
                SwingUtils.showErrorMessageBox("Ошибка при попытке получить данные из input файла", ex);
            }
        });

        getSolutionButton.addActionListener(e -> {
            try {
                String str = textFieldInput.getText();
                List<Integer> list = new ArrayList<>();
                String[] strArr = str.split(" ");
                for (String elem : strArr) {
                    list.add(Integer.parseInt(elem));
                }
                StringBuilder sb = new StringBuilder();
                for (int elem : Logic.createNewList(list)) {
                    sb.append(elem);
                    sb.append(' ');
                }
                textFieldOutput.setText(new String(sb).trim());
            }
            catch (Exception ex){
                SwingUtils.showErrorMessageBox("Исходные числа должны иметь вид \"1 2 3 4\" (разделяться пробелами)", ex);
            }
        });

        saveOutputIntoFile.addActionListener(e -> {
            try {
                if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    String str = textFieldOutput.getText();
                    List<Integer> res = new ArrayList<>();
                    String[] strArr = str.split(" ");
                    for (String elem : strArr){
                        res.add(Integer.parseInt(elem));
                    }
                    String file = fileChooserSave.getSelectedFile().getPath();
                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }
                    Logic.saveOutputIntoFile(file, res);
                }
            } catch (Exception ex) {
                SwingUtils.showErrorMessageBox("Ошибка при попытке сохранить файл", ex);
            }
        });
    }
}


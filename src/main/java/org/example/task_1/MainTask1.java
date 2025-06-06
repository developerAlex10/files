package org.example.task_1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MainTask1 {
    public static void main(String[] args) {
        String path = "D:/Games";
        StringBuilder log = new StringBuilder();

        List<String> directories = Arrays.asList(
                "/src",
                "/res",
                "/savegames",
                "/temp",
                "/src/main",
                "/src/test",
                "/res/drawables",
                "/res/vectors",
                "/res/icons"
        );

        List<String> filesDirectories = Arrays.asList(
                "/src/main/Main.java",
                "/src/main/Utils.java"
        );

        for (String dirPath : directories) {
            createDir(path, dirPath, log);
        }

        for (String dirPath : filesDirectories) {
            createFile(path, dirPath, log);
        }

        File tempFile = createFile(path + "/temp", "/temp.txt", log);

        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(log.toString());
            System.out.println("Лог успешно записан в temp.txt");
        } catch (IOException e) {
            System.out.println("Ошибка при записи лога: " + e.getMessage());
        }
    }

    public static File createDir(String path, String nameDir, StringBuilder log) {
        File dir = new File(path + nameDir);
        if (dir.mkdir()) {
            log.append("Каталог ").append(nameDir.substring(1)).append(" создан успешно\n");
        }
        return dir;
    }

    private static File createFile(String path, String nameFile, StringBuilder log) {
        File file = new File(path + nameFile);
        try {
            if (file.createNewFile()) {
                log.append("Файл ").append(nameFile.substring(1)).append(" создан успешно\n");
            }
        } catch (IOException e) {
            log.append("Ошибка: Каталог  ").append(nameFile.substring(1)).append(" не создан\n").append(e.getMessage());
        }
        return file;
    }
}
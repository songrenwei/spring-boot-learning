package com.srw.common.component;

import cn.hutool.json.JSONUtil;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileDirectory {

    private static Directory root = new Directory();

    private static String ROOT_LABEL = "fabcar";

    public static Directory fileList(File file, String rootName, List<Directory> child) {
        if (file.isFile() || 0 == file.listFiles().length) {
            return null;
        } else {
            // 绑定到根部录下
            if (ROOT_LABEL.equals(rootName)) {
                root.setLabel(rootName);
                root.setChildren(child);
            }

            // 遍历目录
            File[] files = file.listFiles();
            for(File f : files) {
                if(f.isFile()) {
                    // 如果是文件
                    Directory content = new Directory();
                    content.setLabel(f.getName());
                    child.add(content);
                } else if(f.isDirectory()) {
                    // 如果是目录
                    List<Directory> children = new ArrayList<>(); // 生成子目录用于存放递归目录
                    Directory content = new Directory();
                    content.setLabel(f.getName());
                    content.setChildren(children);
                    child.add(content);

                    // 递归目录
                    fileList(f, f.getName(), children);
                }
            }
        }

        return root;
    }

    public static void main(String[] args) {
        File file = new File("E:\\path\\tmp\\src\\fabcar");
        List<Directory> childList = new ArrayList<>();
        Directory directory = fileList(file, ROOT_LABEL, childList);

        System.out.println(JSONUtil.toJsonStr(directory));
    }

}

@Data
class Directory {

    // 目录标签
    private String label;

    // 子目录
    private List<Directory> children;

}



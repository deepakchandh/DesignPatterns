package com.java.designpatterns.structural.composite;


import java.util.*;

/*
You’re building a file system viewer. Both files and folders should be treated uniformly:

You can open a file or a folder.

A folder can contain files or other folders, recursively.

Question:
👉 Which design pattern would best solve this?
 */

interface FileSystemComponent {
    void display();
}

// Leaf
class File implements FileSystemComponent {
    private String name;

    public File(String name) {
        this.name = name;
    }

    public void display() {
        System.out.println("File: " + name);
    }
}

class Folder implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> children = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void add(FileSystemComponent component) {
        children.add(component);
    }

    public void display() {
        System.out.println("Folder: " + name);
        for (FileSystemComponent child : children) {
            child.display();
        }
    }
}
public class CompositeExample {
    public static void main(String[] args) {
        Folder root = new Folder("root");
        root.add(new File("file1.txt"));

        Folder subFolder = new Folder("subfolder");
        subFolder.add(new File("file2.txt"));

        root.add(subFolder);

        root.display();
    }
}

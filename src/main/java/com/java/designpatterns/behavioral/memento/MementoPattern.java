package com.java.designpatterns.behavioral.memento;

// example - text editors where we can save it’s data anytime and use undo to restore it to previous saved state.
class FileWriterUtil{
    private String fileName;
    private StringBuilder content;

    public FileWriterUtil(String file){
        this.fileName = file;
        this.content = new StringBuilder();
    }

    public void write(String str){
        content.append(str);
    }

    public Memento save(){
        return new Memento(this.fileName, this.content);
    }

    public void undoToLastSave(Object obj){
        Memento memento = (Memento) obj;
        this.fileName= memento.fileName;
        this.content=memento.content;
    }

    @Override
    public String toString(){
        return this.content.toString();
    }

    private class Memento{
        private String fileName;
        private StringBuilder content;

        public Memento(String file, StringBuilder content){
            this.fileName=file;
            //notice the deep copy so that Memento and FileWriterUtil content variables don't refer to same object
            this.content=new StringBuilder(content);
        }
    }

}

class FileWriterCareTaker{

    private Object object;

    public void save(FileWriterUtil fileWriterUtil){
        this.object = fileWriterUtil.save();
    }

    public void undo(FileWriterUtil fileWriter){
        fileWriter.undoToLastSave(object);
    }
}

public class MementoPattern {
    public static void main(String[] args) {
        FileWriterCareTaker fileWriterCareTaker = new FileWriterCareTaker();

        FileWriterUtil fileWriter = new FileWriterUtil("data.txt");
        fileWriter.write("First Set of Data\\n\"");
        System.out.println(fileWriter+"\n\n");

        fileWriterCareTaker.save(fileWriter);
        fileWriter.write("Second Set of Data\n");
        System.out.println(fileWriter+"\n\n");

        //lets undo to last save
        fileWriterCareTaker.undo(fileWriter);

        //checking file content again
        System.out.println(fileWriter+"\n\n");

    }
}

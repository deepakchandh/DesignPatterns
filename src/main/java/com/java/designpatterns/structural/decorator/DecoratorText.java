package com.java.designpatterns.structural.decorator;


import java.util.Scanner;

interface Text{
    String render();
}

class PlainText implements Text{

    private String content;

    public PlainText(String content) {
        this.content = content;
    }

    @Override
    public String render() {
        return content;
    }
}

class BoldDecorator implements Text{

    private Text text;

    public BoldDecorator(Text text){
        this.text = text;
    }

    public String render(){
        return "<b>" + text.render() + "/<b>";
    }

}

class ItalicDecorator implements Text{

    private Text text;

    public ItalicDecorator(Text text){
        this.text = text;
    }

    @Override
    public String render() {
        return "<i>" + text.render() + "/<i>";
    }
}

public class DecoratorText {

    public static void main(String[] args) {
        Text text = new ItalicDecorator(new BoldDecorator(new PlainText("Welcome to the Text")));
        System.out.println(text.render());
    }
}

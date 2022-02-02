package com.java.designpatterns.creational.abstractFactory;

interface Button{
    void paint();
}

class MacOSButton implements Button{

    @Override
    public void paint() {
        System.out.println("You have created MacOSButton.");
    }
}

class WindowsButton implements Button {

    @Override
    public void paint() {
        System.out.println("You have created WindowsButton.");
    }
}

interface Checkbox{
    void paint();
}

class MacOSCheckbox implements Checkbox {

    @Override
    public void paint() {
        System.out.println("You have created MacOSCheckbox.");
    }
}

class WindowsCheckbox implements Checkbox {

    @Override
    public void paint() {
        System.out.println("You have created WindowsCheckbox.");
    }
}

// Abstract Factory
interface GUIFactory{
    Button createButton();
    Checkbox createCheckBox();
}

class MacOsFactory implements GUIFactory{

    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public Checkbox createCheckBox() {
        return new MacOSCheckbox();
    }
}

class WindowsFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckBox() {
        return new WindowsCheckbox();
    }
}

// client Code
class Application{
    private Button button;
    private Checkbox checkbox;

    Application(GUIFactory guiFactory){
        this.button = guiFactory.createButton();
        this.checkbox = guiFactory.createCheckBox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }

}

public class Demo {

    private static Application configureApplication() {
        Application app;
        GUIFactory factory;
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("mac")) {
            factory = new MacOsFactory();
            app = new Application(factory);
        } else {
            factory = new WindowsFactory();
            app = new Application(factory);
        }
        return app;
    }

    public static void main(String[] args) {
        Application app = configureApplication();
        app.paint();
    }
}

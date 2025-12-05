package com.java.designpatterns.behavioral.command.gptBooking;

// // https://chatgpt.com/c/68f75142-c180-8323-9246-c7b674a046f8

import java.util.LinkedList;
import java.util.Queue;

interface Command {
    void execute();
    default void undo() {
        throw new UnsupportedOperationException("Undo not supported");
    }
}


class CancelBookingCommand implements Command{

    private final Booking booking;

    CancelBookingCommand(Booking booking) {
        this.booking = booking;
    }

    @Override
    public void execute() {
        System.out.println("[CMD] User requested cancellation");
        booking.cancel(); // Delegates to state
    }
}

class ModifyDatesCommand implements Command {

    private final Booking booking;

    public ModifyDatesCommand(Booking booking) {
        this.booking = booking;
    }

    @Override
    public void execute() {
        System.out.println("[CMD] User requested date modification");
        booking.modify();
        auditLog();
    }

    private void auditLog() {
        System.out.println("[AUDIT] Modify dates command logged.");
    }
}


class CommandInvoker {
    private final Queue<Command> queue = new LinkedList<>();

    public void addCommand(Command command) {
        queue.add(command);
    }

    public void processCommands() {
        while (!queue.isEmpty()) {
            Command cmd = queue.poll();
            cmd.execute();
        }
    }
}

/// ----------------------

interface BookingState {

    void next(Booking booking);
    void prev(Booking booking);

    void cancel(Booking booking);
    void modify(Booking booking);

    void printStatus();
}

class Booking {
    private BookingState state;

    public Booking() {
        this.state = new CreatedState();
    }

    public void setState(BookingState state) {
        this.state = state;
    }

    public void cancel() {
        state.cancel(this);
    }

    public void modify() {
        state.modify(this);
    }

    public void nextState() {
        state.next(this);
    }

    public void previousState() {
        state.prev(this);
    }

    public void printStatus() {
        state.printStatus();
    }
}


class CreatedState implements BookingState {

    @Override
    public void next(Booking booking) {
//        booking.setState(new ConfirmedState());
    }

    @Override
    public void prev(Booking booking) {
        System.out.println("Booking is already in the initial state.");
    }

    @Override
    public void cancel(Booking booking) {
        System.out.println("Booking cancelled with FULL refund.");
//        booking.setState(new CancelledState());
    }

    @Override
    public void modify(Booking booking) {
        System.out.println("Booking modified successfully in CREATED state.");
    }

    @Override
    public void printStatus() {
        System.out.println("Booking created but not yet confirmed.");
    }
}

public class BookingCommandExample {

    public static void main(String[] args) {
        Booking booking = new Booking();
        CommandInvoker invoker = new CommandInvoker();

        invoker.addCommand(new ModifyDatesCommand(booking));
        invoker.addCommand(new CancelBookingCommand(booking));

        invoker.processCommands();

        booking.printStatus();
    }
}

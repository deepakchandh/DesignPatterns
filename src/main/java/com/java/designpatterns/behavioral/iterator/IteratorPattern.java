package com.java.designpatterns.behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

enum ChannelType{
    ENGLISH, HINDI, TAMIL, ALL;
}

class Channel{
    private double frequency;
    private ChannelType channelType;

    public Channel(double frequency, ChannelType channelType) {
        this.frequency = frequency;
        this.channelType = channelType;
    }

    public double getFrequency() {
        return frequency;
    }

    public ChannelType getChannelType() {
        return channelType;
    }

    @Override
    public String toString(){
        return "Frequency="+this.frequency+", Type="+this.channelType;
    }
}

interface ChannelCollection{
    public void addChannel(Channel channel);

    public void removeChannel(Channel channel);

    public ChannelIterator iterator(ChannelType channelType);
}

interface ChannelIterator {

    public boolean hasNext();

    public Channel next();
}

class ChannelCollectionImpl implements ChannelCollection{

    private List<Channel> channelsList;

    public ChannelCollectionImpl(){
        channelsList = new ArrayList<>();
    }

    @Override
    public void addChannel(Channel channel) {
        this.channelsList.add(channel);
    }

    @Override
    public void removeChannel(Channel channel) {
        this.channelsList.remove(channel);
    }

    @Override
    public ChannelIterator iterator(ChannelType channelType) {
        return new ChannelIteratorImpl(channelType, this.channelsList);
    }
// inner class implementation of iterator interface so that the implementation can’t be used by any other collection. Same approach is followed
// by collection classes also and all of them have inner class implementation of Iterator interface.
    private class ChannelIteratorImpl implements ChannelIterator {

        private ChannelType type;
        private List<Channel> channels;
        private int position;

        public ChannelIteratorImpl(ChannelType ty,
                                   List<Channel> channelsList) {
            this.type = ty;
            this.channels = channelsList;
        }

        @Override
        public boolean hasNext() {
            while (position < channels.size()) {
                Channel c = channels.get(position);
                if (c.getChannelType().equals(type) || type.equals(ChannelType.ALL)) {
                    return true;
                } else
                    position++;
            }
            return false;
        }

        @Override
        public Channel next() {
            Channel c = channels.get(position);
            position++;
            return c;
        }

    }
}

public class IteratorPattern {
    public static void main(String[] args) {
        ChannelCollection channels = populateChannels();
        ChannelIterator baseIterator = channels.iterator(ChannelType.ALL);
        while (baseIterator.hasNext()) {
            Channel c = baseIterator.next();
            System.out.println(c.toString());
        }
        System.out.println("******");
        // Channel Type Iterator
        ChannelIterator englishIterator = channels.iterator(ChannelType.ENGLISH);
        while (englishIterator.hasNext()) {
            Channel c = englishIterator.next();
            System.out.println(c.toString());
        }
    }
    private static ChannelCollection populateChannels() {
        ChannelCollection channels = new ChannelCollectionImpl();
        channels.addChannel(new Channel(98.5, ChannelType.ENGLISH));
        channels.addChannel(new Channel(99.5, ChannelType.HINDI));
        channels.addChannel(new Channel(100.5, ChannelType.TAMIL));
        channels.addChannel(new Channel(101.5, ChannelType.ENGLISH));
        channels.addChannel(new Channel(102.5, ChannelType.HINDI));
        channels.addChannel(new Channel(103.5, ChannelType.TAMIL));
        channels.addChannel(new Channel(104.5, ChannelType.ENGLISH));
        channels.addChannel(new Channel(105.5, ChannelType.HINDI));
        channels.addChannel(new Channel(106.5, ChannelType.TAMIL));
        return channels;
    }
}

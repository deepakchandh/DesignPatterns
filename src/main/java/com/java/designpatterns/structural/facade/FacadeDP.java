package com.java.designpatterns.structural.facade;


import java.io.File;

class VideoFile{
    private String name;
    private String codecType;

    public VideoFile(String name) {
        this.name = name;
        this.codecType = name.substring(name.indexOf(".") + 1);
    }

    public String getName() {
        return name;
    }

    public String getCodecType() {
        return codecType;
    }
}


interface Codec{

}


class MPEG4Compression implements Codec{
    public String type = "mp4";
}


class OggCompression implements Codec{
    public String type = "ogg";
}

class CodecFactory{
    public static Codec extract(VideoFile file) {
        String type = file.getCodecType();
        if (type.equals("mp4")){
            System.out.println("CodecFactory: extracting mpeg audio...");
            return new MPEG4Compression();
        }else {
            System.out.println("CodecFactory: extracting ogg audio...");
            return new OggCompression();
        }
    }
}

class BitrateReader {
    public static VideoFile read(VideoFile file, Codec codec) {
        System.out.println("BitrateReader: reading file...");
        return file;
    }

    public static VideoFile convert(VideoFile buffer, Codec codec) {
        System.out.println("BitrateReader: writing file...");
        return buffer;
    }
}

class AudioMixer {
    public File fix(VideoFile result){
        System.out.println("AudioMixer: fixing audio...");
        return new File("tmp");
    }
}

class videoConversionFacade{

    public File convertVideo(String fileName, String format){
        System.out.println("VideoConversionFacade: conversion started.");
        VideoFile file = new VideoFile(fileName);
        Codec sourceCodec = CodecFactory.extract(file);
        Codec destinationCodec;
        if (format.equals("mp4")) {
            destinationCodec = new MPEG4Compression();
        } else {
            destinationCodec = new OggCompression();
        }
        VideoFile buffer = BitrateReader.read(file, sourceCodec);
        VideoFile intermediateResult = BitrateReader.convert(buffer, destinationCodec);
        File result = (new AudioMixer()).fix(intermediateResult);
        System.out.println("VideoConversionFacade: conversion completed.");
        return result;
    }
}


public class FacadeDP {
}

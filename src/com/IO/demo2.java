package com.IO;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class demo2 {

    void writeFile() throws IOException {
        OutputStream outputStream = new FileOutputStream("");
        outputStream.write("hello".getBytes(StandardCharsets.UTF_8));
        outputStream.close();
    }

    public static void main(String[] args) {
        arrayByte();
    }
    static void arrayByte() {
        byte[] data;
        try (OutputStream outputStream = new ByteArrayOutputStream()) {
            outputStream.write("hello, ".getBytes(StandardCharsets.UTF_8));
            outputStream.write("world!".getBytes(StandardCharsets.UTF_8));
            data = ((ByteArrayOutputStream) outputStream).toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(new String(data,StandardCharsets.UTF_8));
    }

}

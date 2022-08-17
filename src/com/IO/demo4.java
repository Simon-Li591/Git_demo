package com.IO;

import java.io.*;
import java.util.Arrays;

public class demo4 {
    public static void main(String[] args) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try (ObjectOutputStream outputStream = new ObjectOutputStream(buffer)) {
            outputStream.writeInt(2121);
            outputStream.writeUTF("fjalskdfj");
            outputStream.writeObject(Double.valueOf(121.12));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Arrays.toString(buffer.toByteArray()));
    }
}

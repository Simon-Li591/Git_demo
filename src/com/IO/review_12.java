package com.IO;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class review_12 {
    public void readFileR() {
        try (InputStream inputStream = new FileInputStream("x.txt")) {
            int n;
            while ((n = inputStream.read()) != -1) {
                System.out.println(n);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readFileR2() {
        byte[] data = {12, 12, 24, 54, 65, 43};
        try (InputStream inputStream = new ByteArrayInputStream(data)) {
            int n;
            while ((n = inputStream.read()) != -1) {
                System.out.println((char) n);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeFileR() {
        byte[] data;
        try (OutputStream outputStream = new ByteArrayOutputStream()) {
            outputStream.write("hello".getBytes(StandardCharsets.UTF_8));
            outputStream.write(" world".getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

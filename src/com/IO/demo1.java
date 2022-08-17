package com.IO;

import java.io.*;

/*
stream 需要close()
可以直接使用
try(resource)
语法与IO
 */
class read {
    public static void main(String[] args) throws IOException {
        readFile();
    }

    public static void readFile() throws IOException {
        InputStream inputStream = new FileInputStream("./x.txt");
        for (; ; ) {
            int n = inputStream.read();
            if (n == -1) {
                break;
            }
            System.out.println(n);
        }
        inputStream.close();
    }

    public static void readFile2() throws IOException {
        InputStream inputStream = new FileInputStream("./x.txt");
        try {
            int n;
            while ((n = inputStream.read()) != -1) {
                System.out.println(n);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            inputStream.close();
        }
    }

    public static void readFile3() {
        try (InputStream inputStream = new FileInputStream("./x.txt")) {
            int n;
            while ((n = inputStream.read()) != -1) {
                System.out.println(n);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class readbyte {
    public void readFile() {
        try (InputStream inputStream = new FileInputStream("./x.txt")) {
            byte[] buffer = new byte[1000];
            int n;
            while ((n = inputStream.read(buffer)) != -1) {
                System.out.println("read " + n + "byte ");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class demo {
    public static void main(String[] args) {
        byte[] data = {111, 123, 123, 11, 99};
        try (InputStream inputStream = new ByteArrayInputStream(data)) {
            int n;
            while ((n = inputStream.read()) != -1) {
                System.out.println((char) n);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
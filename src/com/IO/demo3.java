package com.IO;

import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/*
下面的例子演示了如何编写一个CountInputStream，
它的作用是对输入的字节进行计数：


 */
public class demo3 {
    public static void main(String[] args) {
        byte[] data = "he;;".getBytes(StandardCharsets.UTF_8);
        try (CountInputStream inputStream = new CountInputStream(new ByteArrayInputStream(data))) {
            int n;
            while ((n = inputStream.read()) != -1) {
                System.out.println((char) n);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class CountInputStream extends FilterInputStream {

    /**
     * Creates a {@code FilterInputStream}
     * by assigning the  argument {@code in}
     * to the field {@code this.in} so as
     * to remember it for later use.
     *
     * @param in the underlying input stream, or {@code null} if
     * this instance is to be created without an underlying stream.
     */
    private int count = 0;

    protected CountInputStream(InputStream in) {
        super(in);
    }

    public int getBytesRead() {
        return this.count;
    }

    public int read() throws IOException {
        int n = in.read();
        if (n != -1) {
            this.count++;
        }
        return n;
    }

    public int read(byte[] b, int off, int len) throws IOException {
        int n = in.read(b, off, len);
        if (n != -1) {
            this.count++;
        }
        return n;
    }
}

class Test {
    public void main(String[] args) {
        try (InputStream inputStream = getClass().getResourceAsStream("/defualt/s.txt")) {
            if ((inputStream == null)) {
                System.out.println("null");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
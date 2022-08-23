package com.Network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class demo3_UDPServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(6666);
        for (; ; ) {
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(packet);
            String s = new String(packet.getData(), packet.getOffset(), packet.getLength(),
                    StandardCharsets.UTF_8);


            byte[] data = "ACK".getBytes(StandardCharsets.UTF_8);
            packet.setData(data);
            datagramSocket.send(packet);
        }
    }
}
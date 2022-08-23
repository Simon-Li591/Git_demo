package com.Network;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class demo4_UDPClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(6666);
        datagramSocket.setSoTimeout(1000);
        datagramSocket.connect(InetAddress.getByName("localhost"),6666);
        byte[] data = "hello".getBytes(StandardCharsets.UTF_8);
        DatagramPacket packet = new DatagramPacket(data, data.length);
        datagramSocket.send(packet);

        byte[] buffer = new byte[1024];
        packet = new DatagramPacket(buffer, buffer.length);
        datagramSocket.receive(packet);
        String resp = new String(packet.getData(), packet.getOffset(),packet.getLength());
        datagramSocket.disconnect();
    }
}

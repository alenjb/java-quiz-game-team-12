package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private final Socket socket;
    private final DataInputStream dis;
    private final DataOutputStream dos;

    public Client() {
        try {
            int port = 0;
            socket = new Socket("ip", port);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String message) {
        try {
            this.dos.writeUTF(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String waitMessage() {
        try {
            return this.dis.readUTF();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

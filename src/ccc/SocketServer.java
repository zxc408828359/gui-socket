package ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class SocketServer {
    private ArrayList<PrintWriter> clientOutputStreams;
    private HashMap<String,Socket> clientOutputMap;
    public SocketServer() {
        clientOutputStreams = new ArrayList<>();
        clientOutputMap=new HashMap<>();
    }

    public void starup() {

        // 等待客户端连接的线程
        Runnable serverRunnable = new Runnable() {
            @Override
            public void run() {
                ServerSocket serverSocket;
                try {
                    serverSocket = new ServerSocket(Integer.parseInt("8080"));
                    System.out.println("正在等待客户端连接...\n");
                    //serverTextArea.append();
                    while (true) {
                        Socket clientSocket = serverSocket.accept();
                        //serverTextArea.append("客户端已连接...\n");
                        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                        clientOutputStreams.add(writer);
                        InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
                        String ID= new BufferedReader(isReader).readLine();
                        clientOutputMap.put(ID,clientSocket);
                        // clientOutputStreamsMap.put(clientSocket.getLocalAddress(),writer);}
                        Thread t = new Thread(new SocketServer.ClientHandler(clientSocket));
                        t.start();
                    }
                } catch (NumberFormatException | IOException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread serverThread = new Thread(serverRunnable);
        serverThread.start();
    }

    public static void main(String[] args) {
        SocketServer socketServer = new SocketServer();
        socketServer.starup();
    }

    // 多客户端的线程
    public class ClientHandler implements Runnable {
        BufferedReader bReader;
        Socket aSocket;

        public ClientHandler(Socket clientSocket) {
            try {
                aSocket = clientSocket;
                InputStreamReader isReader = new InputStreamReader(aSocket.getInputStream());
                InetAddress inetAddress = aSocket.getInetAddress();
                System.out.println(inetAddress);
                bReader = new BufferedReader(isReader);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void run() {
            String message;
            try {
                while ((message = bReader.readLine()) != null) {
                    sendToEveryClient(message);
                    System.out.println(message);
                    //  sendToEveryClientSin(message);
                    // serverTextArea.append(message + "\n");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


    }


    // 发送消息给所有客户端的方法
    private void sendToEveryClient(String message) {
        Iterator<PrintWriter> it = clientOutputStreams.iterator();
        while (it.hasNext()) {
            try {
                PrintWriter writer = (PrintWriter) it.next();
                writer.println(message);
                writer.flush();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
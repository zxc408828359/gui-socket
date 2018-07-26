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
import java.util.Map;

public class SocketServer2 {
    private ArrayList<PrintWriter> clientOutputStreams;
    private HashMap<String,Socket> clientOutputMap;
    public SocketServer2() {
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
                    serverSocket = new ServerSocket(Integer.parseInt("8088"));
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
                        System.out.println(ID);
                        writer.println(ID);
                        writer.flush();
                        // clientOutputStreamsMap.put(clientSocket.getLocalAddress(),writer);}
                        Thread t = new Thread(new SocketServer2.ClientHandler(clientSocket,ID));
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
        SocketServer2 socketServer = new SocketServer2();
        socketServer.starup();
    }

    // 多客户端的线程
    public class ClientHandler implements Runnable {
        BufferedReader bReader;
        Socket aSocket;
        String ID;
        InputStreamReader isReader;
        public ClientHandler(Socket clientSocket,String ID) {
            try {
                this.ID=ID;
                aSocket = clientSocket;
                isReader = new InputStreamReader(aSocket.getInputStream());
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
                    //从map中去取socket 对象
                    //sendToOneClient(message,ID);

                    //使用从带过来的socket
                    sendToOneClient2(message);
                    System.out.println("threadName"+Thread.currentThread().getName()+"，"+ID+":"+message);
                    aSocket.shutdownInput();


                    //  sendToEveryClientSin(message);
                    // serverTextArea.append(message + "\n");
                }
            } catch (Exception ex) {
                System.out.println("线程终止");
            }
        }
        private void sendToOneClient2(String message) throws Exception{
            PrintWriter writer2 = new PrintWriter(aSocket.getOutputStream());
            writer2.println(message);
            writer2.flush();
        }

    }


    // 发送消息给所有客户端的方法
    private void sendToOneClient(String message,String ID) {
        //Iterator<PrintWriter> it = clientOutputStreams.iterator();

        Iterator<Map.Entry<String, Socket>> iterator = clientOutputMap.entrySet().iterator();

        while (iterator.hasNext()) {
            try {
                Map.Entry<String, Socket> socketEntry = iterator.next();
                if(ID.equals(socketEntry.getKey())){
                    Socket client = socketEntry.getValue();
                    PrintWriter writer = new PrintWriter(client.getOutputStream());
                    writer.println(message);
                    writer.flush();
                }
               /* PrintWriter writer = (PrintWriter) it.next();
                writer.println(message);
                writer.flush();*/
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
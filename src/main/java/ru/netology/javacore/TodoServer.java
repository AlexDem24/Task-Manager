package ru.netology.javacore;

import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {
    protected int port;
    protected Todos todos;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        System.out.println("Starting server at " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Gson gson = new Gson();
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {
                    String json = in.readLine();
                    Operation op = gson.fromJson(json, Operation.class);
                    switch (op.getType()) {
                        case "ADD":
                            todos.addTask(op.getTask());
                            break;
                        case "REMOVE":
                            todos.removeTask(op.getTask());
                            break;
                    }
                    out.println("Актуальные задачи: " + todos.getAllTasks());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


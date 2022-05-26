package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {
    int port;
    protected Todos todos;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        System.out.println("Starting server at " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String json = in.readLine();
                Gson gson = new GsonBuilder().create();
                Todos td = gson.fromJson(json, Todos.class);
                if (td.type.equals("ADD")) {
                    todos.addTask(td.task);
                } else {
                    todos.removeTask(td.task);
                }
                out.println(todos.getAllTasks());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


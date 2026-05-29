package src.main.java.com.rhomdev.api;

import src.main.java.com.rhomdev.api.data.Produce;
import src.main.java.com.rhomdev.api.data.Task;
import src.main.java.com.rhomdev.api.data.TaskFinish;
import src.main.java.com.rhomdev.api.data.Worker;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class API_COM{

    public static class Serveur extends Thread{
        private int _PORT;
        private Socket clientSocket;

        private RequestType cmd;

        private ObjectInputStream in;
        private ObjectOutputStream out;


        private List<Task> Data_Task;
        private List<TaskFinish> Data_TaskFinish;
        private List<Worker> Data_Worker;
        private List<Produce> Data_Consu_Prod;
        private List<Task> Data_TaskLoad;


        public enum RequestType {
            TASK_LOAD_READ,
            TASK_LOAD_UPDATE,
            TASK_FINISH_READ,
            TASK_FINISH_UPDATE,
            CONSU_PROD_READ,
            CONSU_PROD_UPDATE,
            WORKER_READ,
            WORKER_UPDATE
        }

        public Serveur(int port) {
            this._PORT = port;
            Data_Task = new ArrayList<>();
            Data_TaskFinish = new ArrayList<>();
            Data_Worker = new ArrayList<>();
            Data_Consu_Prod = new ArrayList<>();
            Data_TaskLoad = new ArrayList<>();
        }

        @Override
        public void run() {
            try (ServerSocket serverSocket = new ServerSocket(_PORT)) {
                clientSocket = serverSocket.accept();
                out = new ObjectOutputStream(clientSocket.getOutputStream());
                out.flush();
                in = new ObjectInputStream(clientSocket.getInputStream());
                while (true) {
                    if (cmd != null) {
                        switch (cmd) {
                        case TASK_LOAD_READ -> read_data_task_load();
                        case TASK_LOAD_UPDATE -> update_data_task_load();
                        case TASK_FINISH_READ -> read_data_task_finish();
                        case TASK_FINISH_UPDATE -> update_data_task_finish();
                        case CONSU_PROD_READ -> read_data_consu_prod();
                        case CONSU_PROD_UPDATE -> update_data_consu_prod();
                        case WORKER_READ -> read_data_worker();
                        case WORKER_UPDATE -> update_data_worker();
                        }
                    }
                    Thread.sleep(100); // Petite pause pour éviter une boucle trop rapide
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void update_data_worker() {
             try {
                // Envoyer une demande au client
                out.writeObject("REQUEST_WORKER_UPDATE");
                // Attendre une réponse du client
                String response = (String) in.readObject();
                if ("READY".equals(response)) {
                    // Envoyer les données à mettre à jour
                    out.writeObject(getData_Worker());
                    cmd = null; // Réinitialiser la commande après l'opération
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void read_data_worker() {
                try {
                    // Envoyer une demande au client
                    out.writeObject("REQUEST_WORKER_READ");
                    // Recevoir la liste de données : Produce, Integer, Long
                    @SuppressWarnings("unchecked")
                    List<Worker> receivedData = (List<Worker>) in.readObject();
                    setData_Worker(receivedData);
                    cmd = null; // Réinitialiser la commande après l'opération
                } catch (Exception e) {
                    e.printStackTrace();
                }            
        }

        private void update_data_consu_prod() {
            try {
                // Envoyer une demande au client
                out.writeObject("REQUEST_CONSU_PROD_UPDATE");
                // Attendre une réponse du client
                String response = (String) in.readObject();
                if ("READY".equals(response)) {
                    // Envoyer les données à mettre à jour
                    out.writeObject(getData_Consu_Prod());
                    cmd = null; // Réinitialiser la commande après l'opération
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void read_data_consu_prod() {
            try {
                // Envoyer une demande au client
                out.writeObject("REQUEST_CONSU_PROD_READ");
                // Recevoir la liste de données : Produce, Integer, Long
                @SuppressWarnings("unchecked")
                List<Produce> receivedData = (List<Produce>) in.readObject();
                setData_Consu_Prod(receivedData);
                cmd = null; // Réinitialiser la commande après l'opération
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void update_data_task_finish() {
             try {
                // Envoyer une demande au client
                out.writeObject("REQUEST_TASK_FINISH_UPDATE");
                // Attendre une réponse du client
                String response = (String) in.readObject();
                if ("READY".equals(response)) {
                    // Envoyer les données à mettre à jour
                    out.writeObject(getData_TaskFinish());
                    cmd = null; // Réinitialiser la commande après l'opération
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void read_data_task_finish() {
             try {
                // Envoyer une demande au client
                out.writeObject("REQUEST_TASK_FINISH_READ");
                // Recevoir la liste de données : Produce, Integer, Long
                @SuppressWarnings("unchecked")
                List<TaskFinish> receivedData = (List<TaskFinish>) in.readObject();
                setData_TaskFinish(receivedData);
                cmd = null; // Réinitialiser la commande après l'opération
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void update_data_task_load() {
             try {
                // Envoyer une demande au client
                out.writeObject("REQUEST_TASK_LOAD_UPDATE");
                // Attendre une réponse du client
                String response = (String) in.readObject();
                if ("READY".equals(response)) {
                    // Envoyer les données à mettre à jour
                    out.writeObject(getData_TaskLoad());
                    cmd = null; // Réinitialiser la commande après l'opération
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void read_data_task_load() {
                try {
                    // Envoyer une demande au client
                    out.writeObject("REQUEST_TASK_LOAD_READ");
                    // Recevoir la liste de données : Task, Integer, Long
                    @SuppressWarnings("unchecked")
                    List<Task> receivedData = (List<Task>) in.readObject();
                    setData_TaskLoad(receivedData);
                    cmd = null; // Réinitialiser la commande après l'opération
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }


        public List<Task> getData_Task() {
            return Data_Task;
        }

        public List<TaskFinish> getData_TaskFinish() {
            return Data_TaskFinish;
        }

        public List<Worker> getData_Worker() {
            return Data_Worker;
        }

        public List<Task> getData_TaskLoad() {
            return Data_TaskLoad;
        }

        public List<Produce> getData_Consu_Prod() {
            return Data_Consu_Prod;
        }

        
        public void setData_Task(List<Task> data_Task) {
            Data_Task = data_Task;
        }

        public void setData_TaskFinish(List<TaskFinish> data_TaskFinish) {
            Data_TaskFinish = data_TaskFinish;
        }

        public void setData_Worker(List<Worker> data_Worker) {
            Data_Worker = data_Worker;
        }

        public void setData_Consu_Prod(List<Produce> data_Consu_Prod) {
            Data_Consu_Prod = data_Consu_Prod;
        }

        public void setData_TaskLoad(List<Task> data_TaskLoad) {
            Data_TaskLoad = data_TaskLoad;
        }


        public RequestType getCmd() {
            return cmd;
        }
        public void setCmd(RequestType cmd) {
            this.cmd = cmd;
        }
    }

    public static class Client extends Thread {
        /**
         * Interface pour permettre au client de déléguer la gestion des données 
         * (ex: vers une base de données dans un autre programme).
         */
        public interface DataHandler {
            List<Task> getTasks() throws Exception;
            void updateTasks(List<Task> tasks) throws Exception;
            List<TaskFinish> getTasksFinish() throws Exception;
            void updateTasksFinish(List<TaskFinish> tasks) throws Exception;
            List<Worker> getWorkers() throws Exception;
            void updateWorkers(List<Worker> workers) throws Exception;
            List<Produce> getConsuProd() throws Exception;
            void updateConsuProd(List<Produce> items) throws Exception;
        }

        private Socket serverSocket;
        private ObjectInputStream in;
        private ObjectOutputStream out;
        private final DataHandler dataHandler;

        public Client(String host, int port, DataHandler handler) {
            this.dataHandler = handler;
            try {
                serverSocket = new Socket(host, port);
                out = new ObjectOutputStream(serverSocket.getOutputStream());
                out.flush();
                in = new ObjectInputStream(serverSocket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            // Vérification pour éviter NullPointerException si la connexion a échoué au départ
            if (serverSocket == null || in == null || out == null) {
                System.err.println("Client non initialisé (problème de connexion).");
                return;
            }

            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Object obj = in.readObject();
                    if (!(obj instanceof String)) continue;
                    String request = (String) obj;

                    switch (request) {
                        case "REQUEST_TASK_LOAD_READ" -> {
                            List<Task> data = dataHandler.getTasks();
                            out.writeObject(data != null ? data : new ArrayList<Task>());
                        }
                        case "REQUEST_TASK_LOAD_UPDATE" -> {
                            out.writeObject("READY");
                            out.flush();
                            @SuppressWarnings("unchecked")
                            List<Task> received = (List<Task>) in.readObject();
                            dataHandler.updateTasks(received);
                        }
                        case "REQUEST_TASK_FINISH_READ" -> {
                            List<TaskFinish> data = dataHandler.getTasksFinish();
                            out.writeObject(data != null ? data : new ArrayList<TaskFinish>());
                        }
                        case "REQUEST_TASK_FINISH_UPDATE" -> {
                            out.writeObject("READY");
                            out.flush();
                            @SuppressWarnings("unchecked")
                            List<TaskFinish> received = (List<TaskFinish>) in.readObject();
                            dataHandler.updateTasksFinish(received);
                        }
                        case "REQUEST_CONSU_PROD_READ" -> {
                            List<Produce> data = dataHandler.getConsuProd();
                            out.writeObject(data != null ? data : new ArrayList<Produce>());
                        }
                        case "REQUEST_CONSU_PROD_UPDATE" -> {
                            out.writeObject("READY");
                            out.flush();
                            @SuppressWarnings("unchecked")
                            List<Produce> received = (List<Produce>) in.readObject();
                            dataHandler.updateConsuProd(received);
                        }
                        case "REQUEST_WORKER_READ" -> {
                            List<Worker> data = dataHandler.getWorkers();
                            out.writeObject(data != null ? data : new ArrayList<Worker>());
                        }
                        case "REQUEST_WORKER_UPDATE" -> {
                            out.writeObject("READY");
                            out.flush();
                            @SuppressWarnings("unchecked")
                            List<Worker> received = (List<Worker>) in.readObject();
                            dataHandler.updateWorkers(received);
                        }
                    }
                    out.flush();
                }
            } catch (Exception e) {
                System.err.println("Client communication error: " + e.getMessage());
            } finally {
                // Fermeture manuelle plus sécurisée et compatible
                try {
                    if (in != null) in.close();
                    if (out != null) out.close();
                    if (serverSocket != null) serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

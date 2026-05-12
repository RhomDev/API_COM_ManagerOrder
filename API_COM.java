import data.Employee;
import data.Produce;
import data.Task;
import data.TaskFinish;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class API_COM{

    public class Serveur extends Thread{
        private int _PORT;
        private Socket clientSocket;

        private RequestType cmd;

        private ObjectInputStream in;
        private ObjectOutputStream out;


        private List<Task> Data_Task;
        private List<TaskFinish> Data_TaskFinish;
        private List<Employee> Data_Employee;
        private List<Produce> Data_Consu_Prod;
        private List<Task> Data_TaskLoad;


        enum RequestType {
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
            Data_Employee = new ArrayList<>();
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
                    switch (cmd) {
                        case TASK_LOAD_READ -> read_data_task_load();
                        case TASK_LOAD_UPDATE -> update_data_task_load();
                        case TASK_FINISH_READ -> read_data_task_finish();
                        case TASK_FINISH_UPDATE -> update_data_task_finish();
                        case CONSU_PROD_READ -> read_data_consu_prod();
                        case CONSU_PROD_UPDATE -> update_data_consu_prod();
                        case WORKER_READ -> read_data_worker();
                        case WORKER_UPDATE -> update_data_worker();
                        case null, default -> {}
                    }
                    Thread.sleep(100); // Petite pause pour éviter une boucle trop rapide
                }
            } catch (IOException e) {
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
                    out.writeObject(getData_Employee());
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
                    List<Employee> receivedData = (List<Employee>) in.readObject();
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

        public List<Employee> getData_Worker() {
            return Data_Employee;
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

        public void setData_Worker(List<Employee> data_Worker) {
            Data_Employee = data_Worker;
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

    public class Client {
        private Socket serverSocket;
        private ObjectInputStream in;
        private ObjectOutputStream out;

        private List<Task> Data_Task;
        private List<TaskFinish> Data_TaskFinish;
        private List<Employee> Data_Employee;
        private List<Produce> Data_Consu_Prod;

        public Client(String host, int port) {
            try {
                serverSocket = new Socket(host, port);
                out = new ObjectOutputStream(serverSocket.getOutputStream());
                out.flush();
                in = new ObjectInputStream(serverSocket.getInputStream());

                Data_Task = new ArrayList<>();
                Data_TaskFinish = new ArrayList<>();
                Data_Employee = new ArrayList<>();
                Data_Consu_Prod = new ArrayList<>();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            try {
                while (true) {
                    String request = (String) in.readObject();
                    switch (request) {
                        case "REQUEST_TASK_LOAD_READ" -> {
                            out.writeObject(getData_Task());
                        }
                        case "REQUEST_TASK_LOAD_UPDATE" -> {
                            out.writeObject("READY");
                            @SuppressWarnings("unchecked")
                            List<Task> received = (List<Task>) in.readObject();
                            setData_Task(received);
                        }
                        case "REQUEST_TASK_FINISH_READ" -> {
                            out.writeObject(getData_TaskFinish());
                        }
                        case "REQUEST_TASK_FINISH_UPDATE" -> {
                            out.writeObject("READY");
                            @SuppressWarnings("unchecked")
                            List<TaskFinish> received = (List<TaskFinish>) in.readObject();
                            setData_TaskFinish(received);
                        }
                        case "REQUEST_CONSU_PROD_READ" -> {
                            out.writeObject(getData_Consu_Prod());
                        }
                        case "REQUEST_CONSU_PROD_UPDATE" -> {
                            out.writeObject("READY");
                            @SuppressWarnings("unchecked")
                            List<Produce> received = (List<Produce>) in.readObject();
                            setData_Consu_Prod(received);
                        }
                        case "REQUEST_WORKER_READ" -> {
                            out.writeObject(getData_Employee());
                        }
                        case "REQUEST_WORKER_UPDATE" -> {
                            out.writeObject("READY");
                            @SuppressWarnings("unchecked")
                            List<Employee> received = (List<Employee>) in.readObject();
                            setData_Employee(received);
                        }
                    }
                }
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

        public List<Employee> getData_Employee() {
            return Data_Employee;
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

        public void setData_Employee(List<Employee> data_Employee) {
            Data_Employee = data_Employee;
        }

        public void setData_Consu_Prod(List<Produce> data_Consu_Prod) {
            Data_Consu_Prod = data_Consu_Prod;
        }
    }

}

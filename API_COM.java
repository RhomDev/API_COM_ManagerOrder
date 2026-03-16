import data.Employee;
import data.Produce;
import data.Task;
import data.TaskFinish;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class API_COM{

    public class Serveur extends Thread{
        private int _PORT;
        private Socket clientSocket;

        private RequestType cmd;


        private List<Task> Data_Task;
        private List<TaskFinish> Data_TaskFinish;
        private List<Employee> Data_Employee;
        private List<Produce> Data_Consu_Prod;


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
        }

        @Override
        public void run() {
            try (ServerSocket serverSocket = new ServerSocket(_PORT)) {
                clientSocket = serverSocket.accept();
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

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void update_data_worker() {

        }

        private void read_data_worker() {
        }

        private void update_data_consu_prod() {
        }

        private void read_data_consu_prod() {
        }

        private void update_data_task_finish() {
        }

        private void read_data_task_finish() {
        }

        private void update_data_task_load() {
        }

        private void read_data_task_load() {
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

        public List<Produce> getData_Consu_Prod() {
            return Data_Consu_Prod;
        }

        public RequestType getCmd() {
            return cmd;
        }

        public void setCmd(RequestType cmd) {
            this.cmd = cmd;
        }
    }



}


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class Log {
        private String fileName = "log.csv";

    public void createF() throws Exception {
        createFile();
        writeOnTime();
    }



    private void createFile() throws Exception {


        String t = "log file created at: "+getDTF()+ "\r\n";
        System.out.println(t);


        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(t);
        writer.close();

    }



    private void writeToFile() throws Exception {
        String t = getDTF() + ", ";
        FileWriter fw = new FileWriter(fileName, true);
        PrintWriter pw = new PrintWriter(fw);

        pw.print(t + "active ports, ");
        for(int port=0; port<10000; port++)
        {
            try
            {
                ServerSocket server = new ServerSocket(port);
                server.close();
                //clean up socket here
            }
            catch (IOException e)
            {
                pw.print( port + ", ");

            }

        }
        pw.println(" ");


        pw.close();
    }

    private String getDTF() throws Exception {

        Date dtf = new Date();
        String t = dtf.toString();
        return t;
    }

    private void writeOnTime() throws Exception {
        Timer timer = new Timer();


        TimerTask taskNew = new TimerTask() {
            @Override
            public void run() {
                try {
                    writeToFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timer.scheduleAtFixedRate(taskNew, 0,10000);


    }


    }


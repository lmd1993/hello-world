package org.apache.asterix.lang.common.dataFrame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.ProcessBuilder;
import java.lang.Process;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.File;
/**
 * Created by MingdaLi on 4/5/16.
 */
public class dataFrame {

    public void startCluster(){
        try {

            File directory = new File("");
            String a=directory.getAbsolutePath();
            Runtime rtime = Runtime.getRuntime();
            String b=a+"/asterix-lang-common/src/main/java/org/apache/asterix/lang/common/dataFrame/dataFrameStartAsterix.sh";
            Process child = rtime.exec(b);
            child.waitFor();
            BufferedReader br = new BufferedReader(new InputStreamReader(child.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String result = sb.toString();
            System.out.println(result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void show(){

    }

    private String executeCommand(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();

    }

    public String writeintosh(){
        try {
            String name = "dataFrameStartAsterix.sh";
            boolean flag = false;
            File directory = new File("");
            String a=directory.getAbsolutePath();
            String b="asterix-lang-common/src/main/java/org/apache/asterix/lang/common/dataFrame/dataFrameStartAsterix.sh";
            String c=a+"/asterix-installer/target/asterix-installer-0.8.9-SNAPSHOT-binary-assembly/";
            String d=c+"bin";


            String path = directory.getAbsolutePath() + "/asterix-installer/target/asterix-installer-0.8.9-SNAPSHOT-binary-assembly/";
            String filenameTemp = path + name;
            File writename = new File(filenameTemp);
            writename.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            //String command04="echo \"export MANAGIX_HOME="+c+"\" >> ~/.bash_profile \r\n"+"echo \"export PATH=$PATH:"+d+"\" >> ~/.bash_profile \r\n "+"~/.bash_profile\r\n ";
            String command04="export MANAGIX_HOME="+c+"\n"+"export PATH=$PATH:"+d+"\n";
            //out.write("#!/bin/sh\r\n");
            out.write(command04);
            //out.write("source ~/.bash_profile\r\n");
         //   out.write("ping -c 3 google.com\n");
           // out.write("jenv use java 1.8\n");
            out.write("./asterix-installer/target/asterix-installer-0.8.9-SNAPSHOT-binary-assembly/bin/managix configure\n");
            out.write("./asterix-installer/target/asterix-installer-0.8.9-SNAPSHOT-binary-assembly/bin/managix validate\n");
            //out.write("./bin/managix configure\r\n");
            //out.write("./bin/managix validate\r\n");
            out.write("./asterix-installer/target/asterix-installer-0.8.9-SNAPSHOT-binary-assembly/bin/managix stop -n my_asterix\n");
            out.write("./asterix-installer/target/asterix-installer-0.8.9-SNAPSHOT-binary-assembly/bin/managix delete -n my_asterix\n");
            out.write("./asterix-installer/target/asterix-installer-0.8.9-SNAPSHOT-binary-assembly/bin/managix create -n my_asterix -c "+c+"/clusters/local/local.xml");
            out.flush();
            out.close();
            return filenameTemp;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
    public static void main(String[] args) {
        File directory = new File("");
        String a=directory.getAbsolutePath();
        dataFrame df=new dataFrame();
        String filename=df.writeintosh();
        String scriptpath="/asterix-installer/target/asterix-installer-0.8.9-SNAPSHOT-binary-assembly/";
        String script="dataFrameStartAsterix.sh";
        String runsh="/asterix-installer/target/asterix-installer-0.8.9-SNAPSHOT-binary-assembly";
        String output="";
        String command="chmod +x "+filename;
        try {
            Process p = Runtime.getRuntime().exec(command);
            p.waitFor();
        }catch(Exception e){
            e.printStackTrace();
        }
        Process p3;
        try {
            String shpath="./asterix-installer/target/asterix-installer-0.8.9-SNAPSHOT-binary-assembly/dataFrameStartAsterix.sh";
            Process ps = Runtime.getRuntime().exec(shpath);
            ps.waitFor();
            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String result = sb.toString();
            System.out.println(result);

           /* p3 = Runtime.getRuntime().exec(new String[]{"/bin/sh","-c",runsh},null,null);

            p3=new ProcessBuilder("/bin/bash", scriptpath + script).start();
           // p3 = Runtime.getRuntime().exec(runsh);
            p3.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p3.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                output=output+line + "\n";
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(output);


    /*    File directory = new File("");
        String a=directory.getAbsolutePath();
        String b="asterix-lang-common/src/main/java/org/apache/asterix/lang/common/dataFrame/dataFrameStartAsterix.sh";
        String c=a+"/asterix-installer/target/asterix-installer-0.8.9-SNAPSHOT-binary-assembly/";
        //String command="cp -f "+b+" "+c;
        String d=c+"bin";

        String command04="echo \"export MANAGIX_HOME="+c+"\" >> ~/.bash_profile && "+"echo \"export PATH=$PATH:"+d+"\" >> ~/.bash_profile && "+"~/.bash_profile ";
        Process p1;
        System.out.println(command04);
        try{
            p1=Runtime.getRuntime().exec(command04);
            p1.waitFor();
        }catch(Exception e){
            e.printStackTrace();
        }
        String d=c+"bin";
        String command05="echo \"export PATH=$PATH:"+d+"\" >> ~/.bash_profile";
        System.out.println(command05);
        try{
            p1=Runtime.getRuntime().exec(command05);
            p1.waitFor();
        }catch(Exception e){
            e.printStackTrace();
        }

        String command06="~/.bash_profile";
        System.out.println(command06);
        try{
            p1=Runtime.getRuntime().exec(command06);
            p1.waitFor();
        }catch(Exception e){
            e.printStackTrace();
        }




      /*  String command3="./asterix-installer/target/asterix-installer-0.8.9-SNAPSHOT-binary-assembly/dataFrameStartAsterix.sh";
        System.out.println(command3);
        String output="";
        Process p3;
        try {
            p3 = Runtime.getRuntime().exec(command3);
            p3.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p3.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                output=output+line + "\n";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(output);*/


    }

}

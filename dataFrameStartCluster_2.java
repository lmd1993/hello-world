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

    public void buildAsterixDBInstance(){
        File directory = new File("");
        String a=directory.getAbsolutePath();
        dataFrame df=new dataFrame();
        String filename=df.writeintosh();
        String command="chmod +x "+filename;
        try {
            Process p = Runtime.getRuntime().exec(command);
            p.waitFor();
        }catch(Exception e){
            e.printStackTrace();
        }
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

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void show(){


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
            String command04="export MANAGIX_HOME="+c+"\n"+"export PATH=$PATH:"+d+"\n";
            out.write(command04);

            out.write("./asterix-installer/target/asterix-installer-0.8.9-SNAPSHOT-binary-assembly/bin/managix configure\n");
            out.write("./asterix-installer/target/asterix-installer-0.8.9-SNAPSHOT-binary-assembly/bin/managix validate\n");

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
        dataFrame df=new dataFrame();
        df.buildAsterixDBInstance();


    }

}

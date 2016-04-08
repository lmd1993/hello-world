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
import org.json.*;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

/**
 * Created by MingdaLi on 4/5/16.
 */
public class dataFrame {

    private String dataverse;
    private String dataset;

    public dataFrame(){
        this.dataverse=null;
        this.dataset=null;
    }

    public void buildDataverse(){
        dataFrame AsterixDB=new dataFrame();
        String result=null;
        AsterixDB.buildAsterixDBInstance();
        String urlQuery="http://localhost:19002/ddl?ddl=drop%20dataverse%20company%20if%20exists;create%20dataverse%20company;use%20dataverse%20company;create%20type%20Emp%20as%20open%20{id%20:%20int32,name%20:%20string};create%20dataset%20Employee(Emp)%20primary%20key%20id;";
        result=AsterixDB.httpQueryAsterix(urlQuery);
        String urlQuery2="http://localhost:19002/update?statements=use%20dataverse%20company;insert%20into%20dataset%20Employee({%20%22id%22:123,%22name%22:%22John%20Doe%22});";
        result=AsterixDB.httpQueryAsterix(urlQuery2);
    }
    public String show(String dataverseName, String datasetName){
        String urlQuery="http://localhost:19002/query?query=use%20dataverse%20"+dataverseName+";for%20$l%20in%20dataset('"+datasetName+"')%20return%20$l;";
        return this.httpQueryAsterix(urlQuery);
    }

    public void load(){

    }

    /*execut rest http to read from AsterixDB or write to AsterixDB*/
    public String httpQueryAsterix(String AsterixQueryURL){
        try {
            URL restServiceURL = new URL(AsterixQueryURL);
            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
            httpConnection.setRequestMethod("GET");

            httpConnection.setRequestProperty("Accept", "application/json");

            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : "
                        + httpConnection.getResponseCode());
            }

            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(
                    (httpConnection.getInputStream())));
            String output;
            String result=null;
            while ((output = responseBuffer.readLine()) != null) {
                System.out.println(output);
                result = result+output;
            }
            httpConnection.disconnect();
            return result;
        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return null;

    }
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
/*build a shell script to establish an asterixDB instance

 */

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
        df.buildDataverse();
        String result=df.show("company","Employee");


    }

}

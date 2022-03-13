import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SqlTryOut {
    static ArrayList<String> movies = new ArrayList<String>();
    public static void main(String[] args) {

            File movies = new File("src/Resources/imdb_data.csv");
            String filename = "imdb_data";


      try {

        Scanner sc = new Scanner(movies);


        String moviesDetails = sc.nextLine();

        String[] stringsOfMovies = moviesDetails.split(";");

        String sql = "";

        for (String movie:stringsOfMovies) {
            sql = sql + movie + " varChar(255), \n";
        }

        String createTable = "CREATE TABLE " + filename + " (\n" + sql + ");";

        System.out.println(createTable);


        File ddl = new File("src/Resources/DDL.sql");

        FileWriter writer = new FileWriter(ddl, true);

        writer.write(createTable + "\n\n");

        writer.close();

        //DML
        try {
            Scanner scannerToDML = new Scanner(movies);
            scannerToDML.nextLine();

            while (scannerToDML.hasNextLine()) {

                String line = scannerToDML.nextLine();

                String[] value = line.split(";");

                String points = "'";
                String defaultValue = "DEFAULT";

                String values = defaultValue + "," + points+ value[0] +points + "," + points+ value[1] +points + "," + points+ value[2] +points + ","
                        + points+ value[3] +points + "," + points+ value[4] +points + "," + points+ value[5] +points;

                String insertInto = "INSERT INTO " + filename + " VALUES(" + values + ");";
                System.out.println(insertInto);

                File dml = new File("src/Resources/DML.sql");
                FileWriter dmlWriter = new FileWriter(dml,true);

                dmlWriter.write(insertInto + "\n");
                dmlWriter.close();
            }


        } catch (IOException e) {
            System.out.println("Nope");
            e.printStackTrace();
        }
      } catch (IOException e) {
          System.out.println("not found");
          e.printStackTrace();
      }

    }
}

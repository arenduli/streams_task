import java.io.*;
import java.util.Scanner;

public class KopiranjeDatoteke {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // pitaj korisnika za putanju do izvorne datoteke
            System.out.print("Unesite putanju do izvorne datoteke: ");
            String sourcePath = scanner.nextLine();

            // pitaj korisnika za naziv destinacijske datoteke
            System.out.print("Unesite naziv destinacijske datoteke: ");
            String destinationName = scanner.nextLine();

            File sourceFile = new File(sourcePath);
            File destinationFile = new File(sourceFile.getParent(), destinationName);

            // provjeri da li izvorna datoteka postoji
            if (!sourceFile.exists()) {
                System.out.println("Izvorna datoteka ne postoji!");
                return;
            }

            // kopiraj byte po byte iz izvorne datoteke u destinacijsku
            try (FileInputStream fis = new FileInputStream(sourceFile);
                 FileOutputStream fos = new FileOutputStream(destinationFile)) {

                int byteData;
                while ((byteData = fis.read()) != -1) {
                    fos.write(byteData);
                }
                System.out.println("File copied successfully.");
            }

            // provjeri da li je kopija datoteke uspjesno napravljena
            if (destinationFile.exists()) {
                System.out.println("Kopija datoteke postoji.");
                // otvori kopiju datoteke u File Exploreru
                Runtime.getRuntime().exec("explorer.exe /select," + destinationFile.getAbsolutePath());

                destinationFile.delete();
                System.out.println("Kopija datoteke je obrisana.");
            }  else {
            System.out.println("Kopija datoteke ne postoji!");
            }

        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            scanner.close();
        }
    }
}

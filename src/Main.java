import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String file = "src/persoane.txt";
        List<Persoana> listaPersoane = citireFisier(file);

        if (listaPersoane.isEmpty()) {
            System.out.println("Nu s-au gasit persoane in fisier.");
            return;
        }

        listaPersoane.sort(Comparator.comparing(Persoana::getNume));

        afisareEcran(listaPersoane);
    }

    public static List<Persoana> citireFisier(String file) {
        List<Persoana> listaPersoane = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(file))) {
            while (sc.hasNextLine()) {
                String linie = sc.nextLine().trim();
                if (!linie.isEmpty()) {
                    String[] date = linie.split("\\s+");
                    if (date.length == 3) {
                        String nume = date[0].trim();
                        String prenume = date[1].trim();
                        int varsta = Integer.parseInt(date[2].trim());
                        listaPersoane.add(new Persoana(nume, prenume, varsta));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu a fost gasit");
        } catch (NumberFormatException e) {
            System.out.println("Eroare la citirea varstei din fisier.");
        }
        return listaPersoane;
    }

    public static void afisareEcran(List<Persoana> listaPersoane) {
        for (Persoana p : listaPersoane) {
            System.out.println(p);
        }
    }
}
//
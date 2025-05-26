import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String file = "src/persoane.txt";
        List<Persoana> listaPersoane = citireFisier(file);// Citeste persoanele din fisier si le salveaza intr-o lista

        if (listaPersoane.isEmpty()) {
            System.out.println("Nu s-au gasit persoane in fisier.");
            return;
        }

        listaPersoane.sort(Comparator.comparing(Persoana::getNume));

        afisareEcran(listaPersoane);
    }

    // Metoda care citeste datele din fisier si returneaza o lista de obiecte Persoana
    public static List<Persoana> citireFisier(String file) {
        List<Persoana> listaPersoane = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(file))) {
            while (sc.hasNextLine()) {
                String linie = sc.nextLine().trim();// elimina spatiile de la inceput si sfarsit
                if (!linie.isEmpty()) {
                    String[] date = linie.split("\\s+");// separa cuvintele din linie folosind spatii
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

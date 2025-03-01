import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hash: ");
        String hash = scanner.nextLine();

        System.out.println("Wordlist: ");
        String wordListFilePath = scanner.nextLine();

        File wordListFile = new File(wordListFilePath);
        try (Scanner fileScanner = new Scanner(wordListFile)) {
            while (fileScanner.hasNextLine()) {
                String word = fileScanner.nextLine().trim();

                try {
                    String takeGuessHash = hashMD5(word);
                    if (takeGuessHash.equals(hash)) {
                        System.out.println("""
                                              ...
                                             ;::::;
                                           ;::::; :;
                                         ;:::::'   :;
                                        ;:::::;     ;.
                                       ,:::::'       ;           OOO\\
                                       ::::::;       ;          OOOOO\\
                                       ;:::::;       ;         OOOOOOOO
                                      ,;::::::;     ;'         / OOOOOOO
                                    ;:::::::::`. ,,,;.        /  / DOOOOOO
                                  .';:::::::::::::::::;,     /  /     DOOOO
                                 ,::::::;::::::;;;;::::;,   /  /        DOOO
                                ;`::::::`'::::::;;;::::: ,#/  /          DOOO
                                :`:::::::`;::::::;;::: ;::#  /            DOOO
                                ::`:::::::`;:::::::: ;::::# /              DOO
                                `:`:::::::`;:::::: ;::::::#/               DOO
                                 :::`:::::::`;; ;:::::::::##                OO
                                 ::::`:::::::`;::::::::;:::#                OO
                                 `:::::`::::::::::::;'`:;::#                O
                                  `:::::`::::::::;' /  / `:#
                                   ::::::`:::::;'  /  /   `#
                                """);
                        System.out.println("Hash Cracked: " + word);
                        break;
                    }
                } catch (NoSuchAlgorithmException e) {
                    System.err.println("Error: " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: Word list file not found.");
        } finally {
            scanner.close();
        }
    }

    private static String hashMD5(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
}

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordEncryptionDecryption {

    // Method to generate a new AES Secret Key
    public static SecretKey generateSecretKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256); // for AES-256 encryption
        return keyGen.generateKey();
    }

    public static IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    // Method to encrypt a password
    public static String encrypt(String password) {

        try{

            SecretKey secretKey = generateSecretKey();
            IvParameterSpec iv = generateIv();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            // initializing the Cipher in ENCRYPT_MODE with the AES key and encrypts the password
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            byte[] encryptedBytes = cipher.doFinal(password.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e){
            System.out.println("Error in code encyption");
            return "Error in code encyption";
        }

    }

    // Method to decrypt a password
    public static String decrypt(String encryptedPassword) {
        System.out.println(encryptedPassword);
        try{
            // Generate a new AES secret key
            SecretKey secretKey = generateSecretKey();
            String[] parts = encryptedPassword.split(":");
            byte[] encryptedBytes = Base64.getDecoder().decode(parts[0]);
            byte[] ivBytes = Base64.getDecoder().decode(parts[1]);
            IvParameterSpec iv = new IvParameterSpec(ivBytes);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return "Error in code decryption";
        }
    }

    public static void main(String[] args) {
        try {
            // Generate a new AES secret key


            // Encrypt a password
            String password = "mySecurePassword";
            String encryptedPassword = encrypt(password);
            System.out.println("Encrypted Password: " + encryptedPassword);

            // Decrypt the password
            String decryptedPassword = decrypt(encryptedPassword);
            System.out.println("Decrypted Password: " + decryptedPassword);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

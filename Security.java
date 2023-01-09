import java.io.*;
import java.util.*;

public class Security {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int choice = 3;
        String path;
        while (choice != 0 && choice != 1) {
            System.out.println("请问你是要加密还是解密(0是加密  1是解密):");
            choice = scanner.nextInt();
            if (choice == 0) {
                System.out.println(
                        "既然你想加密文件，那么你的文件路径是什么呢(路径\\请输入成\\\\，并在最后加上文件名如:D:\\\\Users\\\\Yonghu\\\\Desktop\\\\a.txt):");
                path = scanner.next();
                Encryption encryption = new Encryption(path);
                encryption.encryption();
            } else if (choice == 1) {
                System.out.println(
                        "既然你想解密文件，那么你的文件路径是什么呢(路径\\请输入成\\\\，并在最后加上文件名如:D:\\\\Users\\\\Yonghu\\\\Desktop\\\\a.txt):");
                path = scanner.next();
                Decryption decryption = new Decryption(path);
                decryption.decryption();
            } else {
                System.out.println("不好意思，我们没有" + choice + "选项。");
            }
        }
    }
}

class Encryption {
    String path;

    Encryption(String path) {
        this.path = path;
    }

    void encryption() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int len = 0;
        FileInputStream filec = new FileInputStream(path);// 获取加密密钥
        int code = 0;
        byte[] bytes = new byte[1024 * 1024 * 5];
        while ((len = filec.read(bytes)) != -1) {
            code += len;
        }
        code = code % 17;
        filec.close();
        File encrypted = new File("encrypted");//创建encrypted文件夹，存放加密后的文件
        encrypted.mkdir();
        String encryptedfile;
        System.out.println("你想要加密后的文件叫什么(必填):");
        encryptedfile = scanner.next();
        int exists = 1;
        while (exists == 1) {
            File filetest = new File("encrypted\\" + encryptedfile + ".txt");
            if (filetest.exists()) {
            } else {
                break;
            }
            System.out.println("不好意思" + encryptedfile + ".txt文件已存在，请输入另外一个名字:");
            encryptedfile = scanner.next();
        }
        FileOutputStream fileo = new FileOutputStream("encrypted\\" + encryptedfile + ".txt");
        FileInputStream filei = new FileInputStream(path);
        while ((len = filei.read(bytes)) != -1) {
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (bytes[i] + code);
            }
            fileo.write(bytes, 0, len);
        }
        System.out.println("已加密完成，加密后的文件在项目文件夹的encrypted文件夹中\n谢谢使用");

        filei.close();
        fileo.close();
    }

}

class Decryption {
    String path;

    Decryption(String path) {
        this.path = path;
    }

    void decryption() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int len = 0;
        FileInputStream filec = new FileInputStream(path);// 获取解密密钥
        int code = 0;
        byte[] bytes = new byte[1024 * 1024 * 5];
        while ((len = filec.read(bytes)) != -1) {
            code += len;
        }
        code = code % 17;
        filec.close();
        File decrypted = new File("decrypted");//创建decrypted文件夹，存放解密后的文件
        decrypted.mkdir();
        String decryptedfile;
        System.out.println("你想要解密后的文件叫什么(必填):");
        decryptedfile = scanner.next();
        int exists = 1;
        while (exists == 1) {
            File filetest = new File("decrypted\\" + decryptedfile + ".txt");
            if (filetest.exists()) {
            } else {
                break;
            }
            System.out.println("不好意思" + decryptedfile + ".txt文件已存在，请输入另外一个名字:");
            decryptedfile = scanner.next();
        }
        FileOutputStream fileo = new FileOutputStream("decrypted\\" + decryptedfile + ".txt");
        FileInputStream filei = new FileInputStream(path);
        while ((len = filei.read(bytes)) != -1) {
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (bytes[i] - code);
            }
            fileo.write(bytes, 0, len);
        }
        System.out.println("已解密完成，解密后的文件在项目文件夹的decrypted文件夹中\n谢谢使用");
        filei.close();
        fileo.close();

    }
}
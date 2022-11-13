package Day4_JavaIO;

import org.junit.jupiter.api.Test;

import java.io.File;

public class MakeDirectories {
    private static void usage() {
        System.err.println(
                "Usage: MakeDirectories path1...\n"
                        + "Creates each path\n"
                        + "Usage: MakeDirectories -d path1...\n"
                        + "Deletes each path\n"
                        + "Usage: MakeDirectories -r path1 path2...\n"
                        + "Renames from path1 to path2");
        System.exit(1);
    }

    public static void fileData(File f) {
        System.out.println(
                "Absolute path: " + f.getAbsolutePath()
                        + "\n Can read: " + f.canRead()
                        + "\n Can write: " + f.canWrite()
                        + "\n getName: " + f.getName()
                        + "\n getParent " + f.getParent()
                        + "\n getPath " + f.getPath()
                        + "\n length " + f.length()
                        + "\n lastModified " + f.lastModified());
        if (f.isFile())
            System.out.println("It is a file");
        else if (f.isDirectory())
            System.out.println("It is a directory");
    }

    @Test
    public void usageTest() {
        usage();
    }

    public static void directoriesModify(String[] args) {

        if (args.length < 1) usage();
        if (args[0].equals("-r")) {
            if (args.length != 3) usage();
            File
                    old = new File(args[1]),
                    rname = new File(args[2]);
            old.renameTo(rname);
            fileData(old);
            fileData(rname);
            return;
        }
        int count = 0;
        boolean del = false;
        if (args[0].equals("-d")) {
            count++;
            del = true;
        }
        count--;
        while (++count < args.length) {
            File f = new File(args[count]);
            if (f.exists()) {
                System.out.println(f + "exists");
                if (del) {
                    System.out.println("deleting..." + f);
                    f.delete();
                }
            } else { // 不存在
                if (!del) {
                    f.mkdirs();
                    System.out.println("creating..." + f);
                }
            }
            fileData(f);
        }
    }
    @Test
    public void testDirectoriesModify(){
        String[] args1 = {"-r","./test1","./NEWtest1"};
        String[] args2 = {"-d","./test2"};
        String[] args3 = {"./NEWtest4","./NEWtest5"};

//        directoriesModify(args1);
//        directoriesModify(args2);
        directoriesModify(args3);

    }

    public static void main(String[] args) {
        directoriesModify(args);
    }
}

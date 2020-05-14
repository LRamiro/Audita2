package lrbresca.com.audita2.Utils;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DirectoriesUtils {

    private static File[] files;

    public static boolean verifyExternalStorageIsAvailable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public static boolean createDirectory(Context context, String nameDirectory){

        //Before to create the directory we need to check if it already exists or if it's a valid one
        Boolean directoryIsOk = false;
        directoryIsOk = checkDirectory(context, nameDirectory);
        if(!directoryIsOk){
            File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), nameDirectory);
            file.mkdirs();
        }

        return directoryIsOk;
    }

    private static boolean checkDirectory(Context context, String nameDirectory){
        boolean exist = false;
        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES) + nameDirectory);
        if(file.exists()){
            exist = true;
        }
        //String[] subdirectory = nameDirectory.split("\\/");
        return exist;
    }

    public static List<String> getDirectories(Context context){
        //We define an Array to save the names of places
        List<String> places = new ArrayList<>();
        List<String> roooms = new ArrayList<>();

        //We got the App root directory for pictures
        File appRootDirectoryForPictures = getRootOfAppDirectory(context);


        files = appRootDirectoryForPictures.listFiles();
        //Hacemos un Loop por cada fichero para extraer el nombre de cada uno
        for (int i = 0; i < files.length; i++){
            //Sacamos del array files un fichero
            File directories = files[i];

            //Si es directorio...
            if (directories.isDirectory()) {

                places.add(directories.getName());

                //directories.listFiles();
                //File[] subdirectories = directories.listFiles();
               // for(int)
            }
        }
        return places;
    }

    public static List<String> getSubdirectories(String directoryString){
        List<String> subdirectories = new ArrayList<>();
        for(int i = 0; i < files.length; i++) {
            if (files[i].getName().equalsIgnoreCase(directoryString)) {
                subdirectories = Arrays.asList(files[i].list());
            }
        }
        return subdirectories;
    }

    private static File getRootOfAppDirectory(Context context){
        File file = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        return file;
    }
}

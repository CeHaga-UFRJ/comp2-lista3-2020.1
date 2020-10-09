package covid.util;

import java.util.*;
import java.util.zip.GZIPInputStream;
import java.io.*;
import covid.data.Caso;

public abstract class Leitura {
    public static HashMap<String, HashMap<String, List<Caso>>> leDados(){
        FileInputStream file;
        try{
            file = new FileInputStream("data/casos/caso.csv.gz");
        }catch(FileNotFoundException e){
            System.out.println("Arquivo não existe");
            return null;
        }
        List<Byte> buffer = new ArrayList<Byte>();
        GZIPInputStream gzip;
        try {
            gzip = new GZIPInputStream(file);
        }catch(IOException e){
            System.out.println("Erro ao ler arquivo");
            return null;
        }
        return null;
    }
}

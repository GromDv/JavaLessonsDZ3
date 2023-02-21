import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.*;

/**
 * mergeSorting
 */
public class mergeSorting {

    static Logger logger = Logger.getLogger(mergeSorting.class.getName());

    public static void main(String[] args) throws SecurityException, IOException {

        FileHandler fh = new FileHandler("log.txt");
        logger.addHandler(fh);
        SimpleFormatter sFormat = new SimpleFormatter();
        fh.setFormatter(sFormat);
        logger.info("Начало!");

        String [] st = readData("input.txt").split(",");
        int [] arr = new int[st.length];
        arr = Arrays.stream(st).mapToInt(s-> Integer.valueOf(s.trim())).toArray();

        logger.info(Arrays.toString(arr));
        mergeSort(arr, arr.length);
        logger.info(Arrays.toString(arr));
        
        writeData("output.txt", Arrays.toString(arr));
    }

    static void mergeSort(int[] a, int n) {
        if (n < 2)
            return;

        int mid = n / 2;
        int[] left = new int[mid];
        int[] right = new int[n - mid];

        for (int i = 0; i < mid; i++)
            left[i] = a[i];

        for (int i = mid; i < n; i++)
            right[i - mid] = a[i];

        mergeSort(left, mid);
        mergeSort(right, n - mid);

        merge(a, left, right, mid, n - mid);
    }

    static void merge(int[] a, int[] l, int[] r, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j])
                a[k++] = l[i++];
            else
                a[k++] = r[j++];
        }
        while (i < left)
            a[k++] = l[i++];
        while (j < right)
            a[k++] = r[j++];
    }

    static String readData(String file) {
        String line = "";
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader(file));
            line = bufReader.readLine();
            bufReader.close();
            logger.info("Входные данные прочитаны!");
        } catch (IOException ex) {
            System.out.println("Error reading!");
            System.out.println(ex.getMessage());
        }
        return line;        
    }

    static void writeData(String file, String data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(data);
            logger.info("Выходные данные записаны!");
        } catch (IOException ex) {
            System.out.println("Error writing!");
            System.out.println(ex.getMessage());
        }
    }
}
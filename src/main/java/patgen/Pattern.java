package patgen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.stream.Stream;

public class Pattern implements Iterable<int[]> {
    private final int[][] pat;
    private final int size;
    private final String patName;

    public Pattern(int[][] pat, String patName) {
        this.pat = pat;
        this.patName = patName;
        size = pat.length;
    }

    public int getSize() {
        return size;
    }

    public String getPatName() {
        return patName;
    }

    @Override
    public Iterator<int[]> iterator() {
        return Arrays.stream(pat).iterator();
    }

    public static Pattern fromFile(String patternName, Path path, String delimiter) throws IOException {
        return new Pattern(Files.lines(path)
                .map(line -> line.split(delimiter))
                .map(vec -> Stream.of(vec).mapToInt(Integer::parseInt).toArray())
                .toArray(int[][]::new), patternName);
    }

    public static Pattern fromFile(String patternName, Path path) throws IOException {
        return fromFile(patternName, path,";");
    }

    public static Pattern fromFile(Path path) throws IOException {
        return fromFile("", path,";");
    }

    public static Pattern randomized(String patternName, int size, int length) {
        Random random = new Random();
        int[][] arr = new int[length][size];
        Arrays.stream(arr).forEach(ints -> Arrays.setAll(ints, i -> random.nextInt()));
        return new Pattern(arr, patternName);
    }

    public static Pattern randomized(int size, int length) {
        return randomized("", size, length);
    }

}

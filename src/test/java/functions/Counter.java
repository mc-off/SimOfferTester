package functions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Counter {
    public Counter(){}
    public long numberOfFilesInDirectory(String path) throws  IOException{
        long count;
        try (Stream<Path> files = Files.list(Paths.get(path))) {
            count = files.count();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            throw e;
        }
        return count;
    }
}

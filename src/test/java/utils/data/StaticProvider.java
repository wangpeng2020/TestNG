package utils.data;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.Iterator;

public class StaticProvider {
    @DataProvider(name="sum", parallel=true)
    public Iterator<Object[]> Numbers() throws IOException {
        return (Iterator<Object[]>)new CSVData("csv/sum.csv", "\t| |,|:");
    }
}

package utils.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;

public class CSVData implements Iterator<Object[]> {
    BufferedReader in;
    ArrayList<String> csvList=new ArrayList<String>();
    int rowNum=0;     //行数
    int columnNum=0;  //列数
    int curRowNo=0;   //当前行数
    String columnName[];  //列名
    String regex= "\t";  //默认分隔符
    String path=".src.test.java.resources."; //默认目录
    /**
     * 在TestNG中由@DataProvider(dataProvider = "name")修饰的方法
     * 取csv文件数据时时，调用此类构造方法（此方法会得到列名并将当前行移到下以后）执行后，转发到
     * TestNG自己的方法中去，然后由它们调用此类实现的hasNext()、next()方法
     * 得到一行数据，然后返回给由@Test(dataProvider = "name")修饰的方法，如此
     * 反复到数据读完为止
     * @param regex 指定分割符
     * @param fileName 文件名
     * @throws IOException
     */
    public CSVData(String fileName, String regex) throws IOException {
        this.regex =regex;
        readCSV(fileName);
    }

    public CSVData(String fileName) throws IOException{
        readCSV(fileName);
    }

    private void readCSV(String fileName) throws IOException {
        File directory=new File(".");
        path=".src.test.resources.";
        String absolutePath=directory.getCanonicalPath()+path.replaceAll("\\.", Matcher.quoteReplacement("\\"))+fileName;
        System.out.println(absolutePath);
        File csv=new File(absolutePath);
        in=new BufferedReader(new FileReader(csv));
        while (in.ready()) {
            csvList.add(in.readLine());
            this.rowNum++;
        }
        String[] str=csvList.get(0).split(regex);
        this.columnNum=str.length;
        columnName=new String[columnNum];
        //获取列名
        for (int i = 0; i < columnNum; i++) {
            columnName[i]=str[i];
        }
        this.curRowNo++;
    }

    @Override
    public boolean hasNext() {
        // TODO Auto-generated method stub
        if(rowNum==0||curRowNo>=rowNum){
            try {
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return false;
        }else{
            return true;
        }
    }
    /**
     * 获取一组参数，即一行数据
     */
    @Override
    public Object[] next() {
        // TODO Auto-generated method stub
        Map<String,String> s=new TreeMap<String,String>();
        String csvCell[]=csvList.get(curRowNo).split(regex);
        for(int i=0;i<this.columnNum;i++){
            s.put(columnName[i], csvCell[i]);
        }
        Object[] d=new Object[1];
        d[0]=s;
        this.curRowNo++;
        return d;
    }

    @Override
    public void remove() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("remove unsupported");
    }

}
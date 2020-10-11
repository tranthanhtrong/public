import java.io.Serializable;
/*
 * @author Tran Thanh Trong 
 * Student code: CE130169
 * Project 1
 * Subject CSD201x
 */
//This class is given for the project submission.
public class MyFile implements Serializable {

    //contains information of a File
    private String name;
    private long size;
    private String fullPath;

    public MyFile() {
    }

    public MyFile(String name, long size, String fullPath) {
        this.name = name;
        this.size = size;
        this.fullPath = fullPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    @Override
    public String toString() {
        return String.format("%-20s%-10d", name, size);
    }
}



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Directory {
    private String name;
    private  String timestamp;
    private List<Directory>childDirectory;
    private Directory parent;
     
    public Directory(String name,Directory parent){
        this.name = name;
        this.parent = parent;
        childDirectory = new ArrayList<>();
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        timestamp = formattedDateTime;
    }

    public String getName(){
        return name;

    }
    public Directory getPerent(){
       return this.parent;
    }
    public List<Directory> getChildDirectories(){
        return this.childDirectory;
    }
  public String getTime(){
    return this.timestamp;
  }
    public boolean addDirectory(Directory newDir){
      
        for(Directory dir:childDirectory){
            if(dir.getName().equals(newDir.getName())){
                System.out.println("already Exist");
                return false;
            }
        }
        childDirectory.add(newDir);
        return true;
    }
    public Directory findDirectory(String name){
    if(this.name == name)return this;

    for(Directory dir:childDirectory){
        if(dir.getName()==name){
            Directory found = dir.findDirectory(name);
            if(found !=null){
                return found;
            }
        }
    }
    return null;
    }

}

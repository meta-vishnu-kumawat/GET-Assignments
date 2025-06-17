import java.util.List;
import java.util.Scanner;

public class CommandPrompt {

    private Directory root;
    private Directory currDirectory;
    public CommandPrompt(){
        this.root = new Directory("R", null);
        this.currDirectory = root;
    }
    private String getPath() {
        StringBuilder path = new StringBuilder();
        Directory temp = currDirectory;
        while (temp != null) {
            path.insert(0, temp.getName() + "\\");
            temp = temp.getPerent();
        }
        return path.toString();
    }


    public void executeCommand(String command){
        String [] parts = command.split(" ");
        String operation = parts[0].toLowerCase();

            switch (operation) {
                case "mkdir":
                   makeDirectory(parts);
                    break;
                case "cd":
                    chnageDirectory(parts);
                    break;
                case "bk":
                case "back":
                    if(currDirectory.getPerent() != null){
                        currDirectory = currDirectory.getPerent();
                    }
                    else{
                        System.out.println("Error : Already at the root directory");
                    }
                    break;
                case "ls":
                   printList();
                    break;
                case "find":
                   findDirectory(parts);
                    break;
                case "tree":
                displayTree(currDirectory, "", true);
                    break;
                case "exit":
                    System.out.println("Exiting Virtual Command Prompt...");
                  
                 
                default:
                    System.out.println("Invalid command. Type 'exit' to close.");
                    break;
            }
        }

        private void makeDirectory(String [] parts){
        if(parts.length>2) throw new IllegalArgumentException("Usage:mkdir <directory_name>");

        String name = parts[1];
        Directory newDir = new Directory(name, currDirectory);
        if(!currDirectory.addDirectory(newDir)){
            System.out.println("Directory already exists:");

        }
        return;
    }

    private void chnageDirectory(String parts[]){
        if (parts.length < 2) throw new IllegalArgumentException("Usage: cd <directory_name>");
        String targetDir = parts[1];
        boolean found = false;
        for (Directory child : currDirectory.getChildDirectories()) {
            if (child.getName().equals(targetDir)) {
                currDirectory = child;
                found = true;
                break;
            }
        }
        if (!found) System.out.println("Error: Directory not found.");
    }
        private void printList(){
            List<Directory> childDirectory = currDirectory.getChildDirectories();
           if (childDirectory.isEmpty()) {
            System.out.println("No directories found");
           }
            
           else{
            System.out.println("Directories in current directories");
            for(Directory dir:childDirectory){
                System.out.println(dir.getName() + "[" + dir.getTime()+"]");
            }
            System.out.println("Total Subfolders:"+currDirectory.getChildDirectories().size());
           }
        }

        private void findDirectory(String [] parts){
            if (parts.length < 2) throw new IllegalArgumentException("Usage: find <directory_name>");
                    Directory foundDir = currDirectory.findDirectory(parts[1]);
                    if (foundDir != null) {
                        System.out.println("Directory found: " + foundDir.getName());
                    } else {
                        System.out.println("Directory not found.");
                    }

            
        }
        private void displayTree(Directory dir, String prefix, boolean isLast) {
            System.out.println(prefix + (isLast ? "\u2514\u2500 " : "\u251c\u2500 ") + dir.getName());
            for (int i = 0; i < dir.getChildDirectories().size(); i++) {
                displayTree(dir.getChildDirectories().get(i), prefix + (isLast ? "    " : "\u2502   "), i == dir.getChildDirectories().size() - 1);}
            }
           
    
        public static void main(String[] args) {
            CommandPrompt vcp = new CommandPrompt();
            Scanner scanner = new Scanner(System.in);
            System.out.println("::::::::::: Welcome to Metacube Virtual Command Prompt :::::::::");
    
    
            while (true) {
                System.out.print(vcp.getPath() + "> ");
                String command = scanner.nextLine();
                vcp.executeCommand(command);
            }
           
        }

    
    
    }
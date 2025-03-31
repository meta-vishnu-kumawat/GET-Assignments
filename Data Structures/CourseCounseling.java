import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class CourseCounseling {
  

     private HashMap<String,Integer> courses;

     public CourseCounseling(){
        this.courses = new HashMap<>();
     }

     public void addCourse(){
        courses.put("Computer Science", 3);
courses.put("Mechanical", 2);
courses.put("Electronics", 0);
courses.put("Civil Engineering", 1);
courses.put("Data Science", 2);

     }
  
     public HashMap<String,String>  counseling(Queue<Student> studentQueue){
        HashMap<String,String>allocatedCourse = new HashMap<>();
        while (!studentQueue.isEmpty()) {
            Student student = studentQueue.poll();
            int index =  0;
            while (index <student.preferences.size()) {
                int availableSeat = courses.get(student.preferences.get(index));
                if(availableSeat>0){
                    allocatedCourse.put(student.name,student.preferences.get(index));
                    courses.put(student.preferences.get(index), --availableSeat);
                    break;
                }
               index++;
            }
            if(index ==student.preferences.size()){
                allocatedCourse.put(student.name,"No Course Allocated");
            }
            
        }
        return allocatedCourse;
     }
     public static void main(String[] args) {
        Queue<Student> studentQueue = new LinkedList<>();

// Adding students to the queue

studentQueue.add(new Student("Alice", new ArrayList<>(Arrays.asList("Computer Science", "Electronics", "Data Science", "Mechanical", "Civil Engineering"))));
studentQueue.add(new Student("Bob", new ArrayList<>(Arrays.asList("Mechanical", "Civil Engineering", "Computer Science", "Data Science", "Electronics"))));
studentQueue.add(new Student("Carol", new ArrayList<>(Arrays.asList("Electronics", "Computer Science", "Data Science", "Mechanical", "Civil Engineering"))));
studentQueue.add(new Student("David", new ArrayList<>(Arrays.asList("Data Science", "Computer Science", "Electronics", "Civil Engineering", "Mechanical"))));
studentQueue.add(new Student("Eve", new ArrayList<>(Arrays.asList("Civil Engineering", "Mechanical", "Computer Science", "Data Science", "Electronics"))));
studentQueue.add(new Student("Frank", new ArrayList<>(Arrays.asList("Computer Science", "Data Science", "Electronics", "Mechanical", "Civil Engineering"))));
studentQueue.add(new Student("Grace", new ArrayList<>(Arrays.asList("Mechanical", "Electronics", "Data Science", "Civil Engineering", "Computer Science"))));
studentQueue.add(new Student("Hank", new ArrayList<>(Arrays.asList("Electronics", "Mechanical", "Computer Science", "Civil Engineering", "Data Science"))));
studentQueue.add(new Student("Ivy", new ArrayList<>(Arrays.asList("Civil Engineering", "Data Science", "Mechanical", "Electronics", "Computer Science"))));
studentQueue.add(new Student("Jack", new ArrayList<>(Arrays.asList("Data Science", "Civil Engineering", "Computer Science", "Mechanical", "Electronics"))));
  
        CourseCounseling college = new CourseCounseling();
        college.addCourse();
        HashMap<String,String> allocatedCourse = college.counseling(studentQueue);
      LinkedHashMap<String, String> sortedByKey = new LinkedHashMap<>();
        allocatedCourse.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByKey())
            .forEachOrdered(entry -> sortedByKey.put(entry.getKey(), entry.getValue()));

        for(String student : sortedByKey.keySet()){
            System.out.println(student+" ->" + allocatedCourse.get(student));
        }
     }
    

     public static class Student {
        private String name;
        private ArrayList<String> preferences;
    
        public Student(String name,ArrayList<String> preferences) {
            this.name = name;
            this.preferences = preferences;
        }
    
        public String getName() {
            return name;
        }
    
        public ArrayList<String> getPreferences() {
            return preferences;
        }
    }
}

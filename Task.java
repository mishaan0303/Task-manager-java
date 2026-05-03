
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

class Task {
String title;
boolean isCompleted;

Task(String title) {
this.title = title;
this.isCompleted = false;
}
}

class Main {

   static ArrayList<Task>tasks = new ArrayList<>();

      // to save to file   

   static void saveTofile(){
    try {
        FileWriter writer = new FileWriter("task.txt");

        for(Task t : tasks){

            writer.write(t.title + "," + t.isCompleted +"\n");

        }
        writer.close();
    
    } catch (Exception e) {
        System.out.println("Error loading File");
    }

   }

   static void LoadFile() {
    try {

        java.io.File file = new File("task.txt");

        if (file.exists()) return;

        Scanner sc = new Scanner(file);

        while(sc.hasNextLine()){
            String line = sc.nextLine();

            String[] parts = line.split(",");

            String title = parts[0];
            boolean status = Boolean.parseBoolean(parts[1]);

            Task t = new Task(title);

            t.isCompleted = status; 

            tasks.add(t);
        }
        sc.close();
        

    } catch(Exception e) {
        System.out.println("Error loading tasks");
    }
}
    
    
       
   

        //  to add tasks

static void addTask(Scanner sc){
    System.out.print("Enter task :");
    String name = sc.nextLine();

     if(name.trim().isEmpty()) {    // to trim extra spaces in starting and end of the input
        System.out.println("Task cannot be empty!");
        return;
    }

    tasks.add(new Task(name)); 

    System.out.println("Task Added");



}

               // to show task

static void showTask(){
    if(tasks.isEmpty()){
        System.out.println("no task available");
        return;
     }

         for(int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);

         String status = t.isCompleted ? "✔" : "❌";
        System.out.println("Index :" + i +  " | Task : " +  t.title + "| Status :" + status );
            
        }
    }


          // to mark task as completed 

    static void markCompleted(Scanner sc) {


            System.out.println("Enter the index :");
            int index = sc.nextInt();
            if(index >= 0 && index < tasks.size()){
                tasks.get(index).isCompleted = true;
                System.out.println("Task marked as completed!");

            }
            else{
                System.out.println("INVALID INDEX");
            }


        }

        // to delet task

        static void deleteTask(Scanner sc) {
    System.out.print("Enter task index: ");
    int index = sc.nextInt();

    if(index >= 0 && index < tasks.size()) {
        tasks.remove(index);
        System.out.println("Task deleted!");
    } else {
        System.out.println("Invalid index");
    }
}
       
        


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
     // menu system
        LoadFile();

        while(true){
            System.out.println("===== TASK MANAGER =====");
            System.out.println("------------------------------");
            System.out.println("1 : Add Task ");
            System.out.println("2 : Show Task ");
            System.out.println("3 : Mark As Complete ");
            System.out.println("4 : Delet Task ");
            System.out.println("5 : EXIT ");
            System.out.println(" CHOOSE : ");

            int choice = sc.nextInt();
            sc.nextLine();

            if(choice == 1)  addTask(sc);
            else if ( choice == 2) showTask();  
             else if(choice == 3) markCompleted(sc);
            else if(choice == 4) deleteTask(sc);
            else if(choice == 5) {
                saveTofile();
             System.out.println("Exiting");
             break;
            }
             else {
                System.out.println("invalid choice");
             }
           

             }

             sc.close();

     
        }
    }



/**
 * @author Allen G. Saakov
 */

import java.util.*;
import java.text.SimpleDateFormat;
import javax.swing.*;

public class ArrayListQueue {

    public static int front = -1;
    public static int rear = -1;
    public static int customerServed = 0;

    static ArrayList<String> list = new ArrayList<String>();

    public static boolean isEmpty() {
        return front == -1 && rear == -1;
    }

    public static boolean isFull() {
        return ((rear + 1) % list.size() == front);
    }

    public static void enqueue(String item) {
        if (isFull()) {
            JOptionPane.showMessageDialog(null, "Your Queue is full, dequeue a customer!");
            return;
        } else if (isEmpty()) {
            front = rear = 0;
        } else {
            rear = (rear + 1) % list.size();
        }
        list.set(rear, item);
    }

    public static String dequeue() {
        String x = "";
        if (isEmpty()) {
            JOptionPane.showMessageDialog(null, "Queue is empty! You need more customers!");
        } else if (front == rear) {
            x = list.get(front);
            list.set(front, "----------");
            front = rear = -1;
            customerServed++;
        } else {
            x = list.get(front);
            list.set(front, "----------");
            front = (front + 1) % list.size();
            customerServed++;
        }
        return x;
    }

    public static int custCount() {
        return customerServed;
    }

    public static void main(String[] args) {
        // declare a Date object, Frame, and set date format
        Date myDate = new Date();
        JFrame frame = new JFrame("Customer Queue Tracker");
        String myDateFormat = "MM/dd/yyyy";
        SimpleDateFormat dtToday = new
                SimpleDateFormat(myDateFormat);
        list.add("----------");
        list.add("----------");
        list.add("----------");
        list.add("----------");
        list.add("----------");

        boolean flag = false;
        while (!flag)  {
            String[] queueActions = { "Enqueue", "Dequeue"};
            int selection = JOptionPane.showOptionDialog(frame, "Select one:", "Pick the action.",
                    0, 2, null, queueActions, queueActions[0]);
            if (selection == 0) {
                String item = JOptionPane.showInputDialog("Customer to enqueue: ");
                enqueue(item);
            }
            if(selection == 1) {
               String itemDequeued = dequeue();
                JOptionPane.showMessageDialog(frame, "Now serving customer: " + itemDequeued);
            }
            // declare a StringBuilder object to display the list elements
            StringBuilder sb = new StringBuilder();
            // declare an Iterator object to cycle through the list elements
            Iterator<String> itr = list.iterator();

            // use a looping structure to display the list elements
            System.out.println("iterate through ArrayList elements...");
            while(itr.hasNext())
            {
                // append the elements to the StringBuilder object
                sb.append(itr.next() + "\n");
            }
            // display the elements that are in the list (queue)
            JOptionPane.showMessageDialog(null, dtToday.format(myDate) + "\n" + "display queue \n" + sb);
            String[] exitOptions = { "Yes", "No" };
            int exitSelect = JOptionPane.showOptionDialog(frame, "Do you want to exit?", "End Program Option",
                    0, 1, null, exitOptions,exitOptions[0]);
            if (exitSelect == 0) flag = true;
        }
        JOptionPane.showMessageDialog(frame, "The number of customers served successfully (i.e. the number of customers who have been dequeued) are: " + custCount());
    }
}
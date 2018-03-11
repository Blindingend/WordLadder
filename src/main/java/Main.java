import java.io.*;
import java.util.*;


public class Main
{
    static Set<String> dict = new HashSet<String>();

    static Set<String> Read(String filename) throws Exception
    {
        Set<String> words = new HashSet<String>();
        try
        {
            File file = new File(filename);
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine())
            {
                words.add(sc.nextLine());
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Unable to open that file.Try again.");
        }
        return words;
    }

    static void Prints(Stack<String> stack)
    {
        Stack<String> sforp1 = (Stack<String>)stack.clone();
        int n = stack.size();
        for (int j = 0; j < n; ++j)
        {
            System.out.print(sforp1.peek() + " ");
            sforp1.pop();
        }
        System.out.println();
    }

    static boolean Find(String start, String end)
    {
        Set<String> used = new HashSet<String>();
        if(start == end)
        {
            System.out.println("The two words must be different.");
            return false;
        }

        int n = start.length();
        if(n < 1 || n != end.length())
        {
            System.out.println("The two words must be the same length.");
            return false;
        }

        Queue<Stack<String>> Q = new LinkedList<Stack<String>>();
        Stack<String> S = new Stack<String>();
        S.push(start);
        Q.offer(S);
        if(dict.contains(start) || dict.contains(end))
        {
            used.add(start);
            Q.offer(S);
            while(!Q.isEmpty())
            {
                int size = Q.size();
                for (int i = 0; i < size; ++i)
                {
                    Stack<String> stack1 = Q.poll();
                    String word = stack1.peek();
                    for (int j = 0; j < n; ++j)
                    {
                        char oldChar = word.charAt(j);
                        for (char c = 'a'; c <= 'z' ; c++)
                        {
                            StringBuilder wordb = new StringBuilder(word);
                            if (c == oldChar)continue;
                            wordb.setCharAt(j,c);
                            String nw = wordb.toString();
                            if (dict.contains(nw) && !used.contains(nw))
                            {
                                if (nw.endsWith(end))
                                {
                                    stack1.push(nw);
                                    System.out.print("A ladder from " + start + " back to " + end + ":");
                                    Prints(stack1);
                                    return true;
                                }
                                Stack<String> temp = (Stack<String>) stack1.clone();
                                temp.push(nw);
                                Q.offer(temp);
                                used.add(nw);
                            }
                        }
                    }
                }
            }
            System.out.println("No word ladder found from " + start + " back to " + end);
            return false;
        }
        System.out.println("The two words must be found in the dictionary.");
        return false;
    }

    public static void main(String[] args) throws Exception
    {
        Scanner scan = new Scanner(System.in);
        String filename,start,end;
        filename = scan.nextLine();

        dict = Read(filename);


        while(true)
        {
            System.out.print("Word #1 (or Enter to quit): ");
            start = scan.nextLine();
            if(start.length() == 0)
                break;
            System.out.print("Word #2 (or Enter to quit): ");
            end = scan.nextLine();
            if(end.length() == 0)
                break;
            System.out.println(start.length() + " " + end.length());
            start = start.toLowerCase();
            end = end.toLowerCase();
            Find(start,end);
        }
        System.out.println("Have a nice day.");
    }
}
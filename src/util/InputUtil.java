
package util;

import java.util.Scanner;

public class InputUtil {
    static Scanner sc = new Scanner(System.in);

    public static int getInt(){
        while(true){
            try{
                return Integer.parseInt(sc.nextLine());
            }catch(Exception e){
                System.out.print("Invalid input! Enter number: ");
            }
        }
    }

    public static String getString(){
        return sc.nextLine();
    }
}

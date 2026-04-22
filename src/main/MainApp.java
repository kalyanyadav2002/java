package main;

import model.*;
import service.*;
import service.impl.*;
import pattern.singleton.*;
import pattern.factory.*;
import util.*;

import java.util.*;

public class MainApp {

    static List<Member> members = new ArrayList<>();
    static Member loggedIn = null;

    static BookingService booking = new BookingServiceImpl();
    static ReportService report = new ReportServiceImpl();

    public static void main(String[] args){

        TimetableSingleton timetable = TimetableSingleton.getInstance();
        timetable.generate();

        while(true){
            printMenu();

            int ch = InputUtil.getInt();

            switch(ch){
                case 1 -> register();
                case 2 -> login();
                case 3 -> view(timetable);
                case 4 -> book(timetable);
                case 5 -> change(timetable);
                case 6 -> cancel(timetable);
                case 7 -> review(timetable);
                case 8 -> showReport(timetable);
                case 9 -> exit();
                default -> System.out.println("❌ Invalid choice!");
            }
        }
    }

    // ================= MENU =================
    static void printMenu() {
        System.out.println("\n====================================");
        System.out.println("      FLC BOOKING SYSTEM");
        System.out.println("====================================");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. View Timetable");
        System.out.println("4. Book Lesson");
        System.out.println("5. Change Booking");
        System.out.println("6. Cancel Booking");
        System.out.println("7. Add Review");
        System.out.println("8. View Reports");
        System.out.println("9. Exit");
        System.out.println("====================================");

        if (loggedIn != null) {
            System.out.println("Logged in as: " + loggedIn.getName());
        } else {
            System.out.println("Not logged in");
        }

        System.out.print("Enter choice: ");
    }

    // ================= REGISTER =================
    static void register(){
        System.out.println("\n--- REGISTER ---");

        System.out.print("Enter name: ");
        String name = InputUtil.getString();

        System.out.print("Enter password: ");
        String pass = InputUtil.getString();

        Member m = MemberFactory.createMember(members.size()+1,name,pass);
        members.add(m);

        System.out.println("✅ Registered successfully! Your ID: " + m.getId());
    }

    // ================= LOGIN =================
    static void login(){
        System.out.println("\n--- LOGIN ---");

        System.out.print("Enter ID: ");
        int id = InputUtil.getInt();

        System.out.print("Enter password: ");
        String pass = InputUtil.getString();

        for(Member m:members){
            if(m.getId()==id && m.getPassword().equals(pass)){
                loggedIn=m;
                System.out.println("✅ Welcome " + m.getName());
                return;
            }
        }

        System.out.println("❌ Invalid credentials!");
    }

    // ================= VIEW TIMETABLE =================
    static void view(TimetableSingleton t){
        List<Lesson> ls = t.getLessons();

        System.out.println("\n---------------- TIMETABLE ----------------");
        System.out.printf("%-5s %-10s %-12s %-12s %-10s %-8s\n",
                "ID", "Day", "Time", "Type", "Trainer", "Seats");

        for(int i=0;i<ls.size();i++){
            Lesson l=ls.get(i);

            System.out.printf("%-5d %-10s %-12s %-12s %-10s %-8d\n",
                    i,
                    l.getDay(),
                    l.getTimeSlot(),
                    l.getType(),
                    l.getTrainer().getName(),
                    (4 - l.getBookingCount()));
        }

        System.out.println("-------------------------------------------");
    }

    // ================= BOOK =================
    static void book(TimetableSingleton t){
        if(loggedIn==null){
            System.out.println("⚠ Please login first!");
            return;
        }

        view(t);

        System.out.print("Enter lesson ID to book: ");
        int i=InputUtil.getInt();

        boolean result = booking.book(loggedIn,t.getLessons().get(i));

        System.out.println(result ? "✅ Booking successful!" : "❌ Booking failed (full or conflict)");
    }

    // ================= CHANGE =================
    static void change(TimetableSingleton t){
        if(loggedIn==null){
            System.out.println("⚠ Please login first!");
            return;
        }

        System.out.print("Old lesson ID: ");
        int o=InputUtil.getInt();

        System.out.print("New lesson ID: ");
        int n=InputUtil.getInt();

        boolean result = booking.change(loggedIn,
                t.getLessons().get(o),
                t.getLessons().get(n));

        System.out.println(result ? "✅ Booking updated!" : "❌ Change failed");
    }

    // ================= CANCEL =================
    static void cancel(TimetableSingleton t){
        if(loggedIn==null){
            System.out.println("⚠ Please login first!");
            return;
        }

        System.out.print("Lesson ID to cancel: ");
        int i=InputUtil.getInt();

        booking.cancel(loggedIn,t.getLessons().get(i));

        System.out.println("✅ Booking cancelled");
    }

    // ================= REVIEW =================
    static void review(TimetableSingleton t){
        if(loggedIn==null){
            System.out.println("⚠ Please login first!");
            return;
        }

        System.out.print("Lesson ID: ");
        int i=InputUtil.getInt();

        System.out.print("Rating (1-5): ");
        int r=InputUtil.getInt();

        try {
            t.getLessons().get(i).addRating(r);
            System.out.println("✅ Review added");
        } catch (Exception e){
            System.out.println("❌ Invalid rating");
        }
    }

    // ================= REPORT =================
    static void showReport(TimetableSingleton t){
        System.out.println("\n--- REPORT ---");
        System.out.println(report.generateLessonReport(t.getLessons()));
    }

    // ================= EXIT =================
    static void exit(){
        System.out.println("Thank you for using FLC System ");
        System.exit(0);
    }
}
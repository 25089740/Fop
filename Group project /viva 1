/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Viva1;
import java.util.Scanner;
/**
 *
 * @author Lenovo
 */
public class Q2 {
    public static void main(String[]args){
        Scanner in = new Scanner(System.in);
        double balance, R, interest, miniPayment;       
        char holiday;
        int month=0;       
        double totalInterest = 0.00;       
        double paid = 0.00;
        int raised=0;   
        double late=0.00;
        double[] debtYear = new double[3];
        do{
            System.out.print("Enter outstanding balance(RM): ");
            balance = in.nextDouble();
            if(balance<500.00||balance>50000.00){
            System.out.println("Error: Balance must be between RM 500 and RM 50000.");            
            }
        }while(balance<500.00||balance>50000.00);
        do{
        System.out.print("Enter annual interest rate(R%): ");
        R = in.nextDouble();
        if(R<0){
            System.out.println("Invaild annual interest rate, please retry.");
        }
        }while(R<0);
        
        double rate_M = R/(100*12);
        
        System.out.print("Enter planned monthly payment(RM): ");
        double payment = in.nextDouble();
        do{
        System.out.print("Take a payment holiday?(Y/N): ");
        holiday = in.next().charAt(0);
        if(holiday!='Y' && holiday!='N'){
        System.out.println("Invalid selection, please retry.");
        }
        }while(holiday!='Y' && holiday!='N');
        if(holiday=='Y'){
            do{
                System.out.print("Enter holiday month(1-36): ");
                month = in.nextInt();
                if(month<1||month>36){
                    System.out.println("Invalid holiday month, please retry.");
                }
            }while(month<1||month>36);
            System.out.println("Month "+month+": Payment Holiday - late fee RM 25.00 charged.");
           
            }
        int i=1;
        for(; i<=36; i++){   
           
            interest = balance*rate_M;
            totalInterest+=interest;
            balance+=interest;
            if(i==month){
                late+=25;
                balance+=25;
            }else{
            miniPayment = Math.max(balance*0.05,50);
            double pay = payment;
            if(pay<miniPayment){
                pay=miniPayment;
                raised++;
            }
            if(pay>balance){
                pay=balance;
            }
            balance-=pay;
            paid+=pay;
            }
            if(i%12==0){
                debtYear[i/12-1]=balance;
            }
            if(balance<0.005){
                balance=0.00;                
                System.out.println("Debt fully cleared in Month " +i+ "!");
                break;
            }
            
        }
        System.out.printf("End of Year 1 Balance: RM %.2f%n",debtYear[0]);
        System.out.printf("End of Year 2 Balance: RM %.2f%n",debtYear[1]);
        System.out.printf("End of Year 3 Balance: RM %.2f%n",debtYear[2]);
        System.out.printf("Total Interest charged: RM %.2f%n",totalInterest);
        System.out.printf("Total Late Fees: RM %.2f%n",late);
        System.out.printf("Total Amount Paid: RM %.2f%n",paid);
        System.out.println("Months Raised to Minimum Payment: "+raised);
        
        if(balance>=0.005){
        System.out.println("Debt not cleared after 36 months.");        
    }
        in.close();
    }
}
       
        
        
    


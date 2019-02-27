package com.bank.useroperations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.bank.setget.UserSetGet;

public class UserOperations {
	
		static Scanner sc = new Scanner(System.in);
		public static void main(String[] args) {
			
			
			System.out.println("Enter 1 for Registration. 2 for Login");
			int menu = sc.nextInt();
			switch(menu)
			{
			case 1: customerRegistration();
			break;
			
			case 2: customerLogin();
			break;
			
			default: System.out.println("Invalid Choice");
			break;
			
			}

		}

		static long defaultAccountNo;
		static double defaultBalance;
		static String defaultFirstName;
		static int defaultIndex;
		private static void customerLogin() {
			
			System.out.println("Enter Account no. and Password");
			long acno = sc.nextLong();
			long pwd = sc.nextLong();
			FileInputStream f1;
			try {
				f1 = new FileInputStream("C:\\\\Users\\\\VJIT\\\\Desktop\\\\CapgPrograms\\\\BankDatabase.txt");
				ObjectInputStream in = new ObjectInputStream(f1);
				ArrayList<UserSetGet> al1 = (ArrayList<UserSetGet>)in.readObject();
				UserSetGet u = new UserSetGet();
				int count = 0;
				for (int i = 0; i < al1.size(); i++) {
					u = al1.get(i);
					if((u.getAccountNo() == acno) && (u.getPassword() == pwd)) {
						defaultAccountNo = u.getAccountNo();
						defaultFirstName = u.getFirstName();
						defaultBalance = u.getBalance();
						defaultIndex = i;
						operations();
						break;
					}
						
					else
					{
						count++;
					}
						
				}
				if(count == al1.size()) {
					System.out.println("Account no. or Password Incorrect. Enter correct details");
					customerLogin();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		private static void operations() {
			
			System.out.println("Welcome "+defaultFirstName);
			System.out.println("Choose required operation. 1. Withdrawal 2. Deposit 3. Exit");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter withdrawal amount");
				double amt = sc.nextDouble();
				withdraw(amt);
				break;
			case 2:
				System.out.println("Enter deposit amount");
				double amt1 = sc.nextDouble();
				deposit(amt1);
				break;
			case 3:
				System.exit(0);
			default:
				break;
			}
		}

		private static void deposit(double amt1) {
			
			defaultBalance += amt1;
			System.out.print("Remaining Balance: "+defaultBalance);
			System.out.println();
			try {
				FileInputStream f1 = new FileInputStream("C:\\\\Users\\\\VJIT\\\\Desktop\\\\CapgPrograms\\\\BankDatabase.txt");
				ObjectInputStream in = new ObjectInputStream(f1);
				ArrayList<UserSetGet> al1 = (ArrayList<UserSetGet>)in.readObject();
				UserSetGet u = new UserSetGet();
				u = al1.get(defaultIndex);
				u.setBalance(defaultBalance);
				al1.set(defaultIndex, u);
				FileOutputStream file = new FileOutputStream("C:\\Users\\VJIT\\Desktop\\CapgPrograms\\BankDatabase.txt");
				ObjectOutputStream out = new ObjectOutputStream(file);
				out.writeObject(al1);
				out.close();
				System.out.println("Press 1 to continue. Any other to exit");
				int op = sc.nextInt();
				if(op == 1)
					operations();
				else
					System.exit(0);
			} catch (FileNotFoundException e) {		
				e.printStackTrace();
			} catch (IOException e) {	
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}

		private static void withdraw(double amt) {
			
			try {
				FileInputStream f1 = new FileInputStream("C:\\\\Users\\\\VJIT\\\\Desktop\\\\CapgPrograms\\\\BankDatabase.txt");
				ObjectInputStream in = new ObjectInputStream(f1);
				ArrayList<UserSetGet> al1 = (ArrayList<UserSetGet>)in.readObject();
				UserSetGet u = new UserSetGet();
				u = al1.get(defaultIndex);
				if(u.getBalance() >= amt)
				{
					defaultBalance -= amt;
					System.out.print("Remaining Balance: "+defaultBalance);
					System.out.println();
					u.setBalance(defaultBalance);
					al1.set(defaultIndex, u);
					FileOutputStream file = new FileOutputStream("C:\\Users\\VJIT\\Desktop\\CapgPrograms\\BankDatabase.txt");
					ObjectOutputStream out = new ObjectOutputStream(file);
					out.writeObject(al1);
					out.close();
					System.out.println("Press 1 to continue. Any other to exit");
					int op = sc.nextInt();
					if(op == 1)
						operations();
					else
						System.exit(0);
				}
				else
				{
					System.out.println("Insufficient fund");
					operations();
				}
			} catch (FileNotFoundException e) {		
				e.printStackTrace();
			} catch (IOException e) {	
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}

		private static void customerRegistration() {
			
			Random r = new Random();
			long accno = r.nextLong();
			System.out.println("Enter first name and last name");
			String fname = sc.next();
			String lname = sc.next();
			System.out.println("Enter account type");
			String accountType = sc.next();
			System.out.println("Register a new password");
			long pswd = sc.nextLong();
			System.out.println("Enter the balance");
			double bal = sc.nextDouble();
			System.out.println("Enter aadhaar card number");
			long aadhaarNo = sc.nextLong();
			System.out.println("Enter pan card number");
			String panNo = sc.next();
			System.out.println("Enter mobile number");
			long mobileNo = sc.nextLong();
			System.out.println("Enter the address");
			String address = sc.next();
			
			try {
				FileInputStream f1 = new FileInputStream("C:\\\\Users\\\\VJIT\\\\Desktop\\\\CapgPrograms\\\\BankDatabase.txt");
				ObjectInputStream in = new ObjectInputStream(f1);
				ArrayList<UserSetGet> al1 = (ArrayList<UserSetGet>)in.readObject();
				UserSetGet u = new UserSetGet();
				int n = al1.size();
				int count = 0;
				for (int i = 0; i < al1.size(); i++) {
					u = al1.get(i);
					if(u.getAadhaarNo() == aadhaarNo) {
						System.out.println("User Already Exists");
						break;}
					else
						count++;
				}
				if(count == n)
				{
					
					u.setAccountNo(accno);
					u.setPassword(pswd);
					u.setFirstName(fname);
					u.setLastName(lname);
					u.setAccountType(accountType);
					u.setBalance(bal);
					u.setAadhaarNo(aadhaarNo);
					u.setPanCardNo(panNo);
					u.setPhoneNo(mobileNo);
					u.setAddress(address);
					al1.add(u);
					System.out.println(u.getAccountNo());
					FileOutputStream file = new FileOutputStream("C:\\Users\\VJIT\\Desktop\\CapgPrograms\\BankDatabase.txt");
					ObjectOutputStream out = new ObjectOutputStream(file);
					out.writeObject(al1);
					out.close();
					customerLogin();
				}
				else
					customerLogin();
			} catch (FileNotFoundException e) {		
				e.printStackTrace();
			} catch (IOException e) {	
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
			
}

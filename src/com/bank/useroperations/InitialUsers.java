package com.bank.useroperations;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


import com.bank.setget.UserSetGet;

public class InitialUsers {

	static int count = 1;
	public static void main(String[] args) {
		
		ArrayList<UserSetGet> al = new ArrayList<>();
		al.add(setInfo());
		al.add(setInfo());
		//al.add(setInfo());
		//al.add(setInfo());
		//al.add(setInfo());
		try {
			FileOutputStream file = new FileOutputStream("C:\\Users\\VJIT\\Desktop\\CapgPrograms\\BankDatabase.txt");
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(al);
			out.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

	private static UserSetGet setInfo() {
		Random r = new Random();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the details of customer "+count);
		UserSetGet u = new UserSetGet();
		u.setAccountNo(r.nextLong());
		System.out.println(u.getAccountNo());
		System.out.println("Enter password");
		u.setPassword(sc.nextLong());
		System.out.println("Enter first name and last name");
		u.setFirstName(sc.next());
		u.setLastName(sc.next());
		System.out.println("Enter account type");
		u.setAccountType(sc.next());
		System.out.println("Enter the balance");
		u.setBalance(sc.nextDouble());
		System.out.println("Enter aadhaar card number");
		u.setAadhaarNo(sc.nextLong());
		System.out.println("Enter pan card number");
		u.setPanCardNo(sc.next());
		System.out.println("Enter mobile number");
		u.setPhoneNo(sc.nextLong());
		System.out.println("Enter the address");
		u.setAddress(sc.next());
		count++;
		return u;
	}
}

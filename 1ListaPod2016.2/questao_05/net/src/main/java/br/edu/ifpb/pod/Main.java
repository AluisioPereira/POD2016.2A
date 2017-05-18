package br.edu.ifpb.pod;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		
		//
		System.out.println("Hello World");
		
		//
		File sum_file = new File("/opt/shared/sum.txt");
		if (sum_file.exists()){
			System.out.println("Can write: " + sum_file.canWrite());
			System.out.println("Can read: " + sum_file.canRead());
		}
		else {
			System.out.println("Sum file not found");
		}
		
		//
		File diff_file = new File("/opt/shared/diff.txt");
		if (diff_file.exists()){
			System.out.println("Can write: " + diff_file.canWrite());
			System.out.println("Can read: " + diff_file.canRead());
		}
		else {
			System.out.println("Diff file not found");
		}
		
	}
}

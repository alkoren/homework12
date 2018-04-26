package by.epam.hw11library.main;

import java.util.List;


import by.epam.hw11library.entity.Book;
import by.epam.hw11library.entity.Library;
import by.epam.hw11library.entity.Magazine;
import by.epam.hw11library.entity.PrintedEdition;
import by.epam.hw11library.service.LibraryService;
import by.epam.hw11library.service.find.FindByPrice;
import by.epam.hw11library.service.find.Findable;
import by.epam.hw11library.view.PrintAsList;
import by.epam.hw11library.view.PrintAsTable;
import by.epam.hw11library.view.Printable;
import by.epam.hw11library.view.ViewAction;
import java.io.*;
public class Main {

	public  static void main(String[] args) {
		Library myLibrary = new Library();
		String importFile = "d:\\EDU\\HW12\\Books.txt"; // Import format is Book name and price, devided by ;
		BufferedReader br = null;
		String line = "";
		String SplitChar = ";";
		try {

			br = new BufferedReader(new FileReader(importFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] scv = line.split(SplitChar);
				System.out.println("Imported Book: " + scv[0] + "\t with price= " + scv[1] );

				for (int i = 0; i < scv.length; i++) {
					myLibrary.add(new Book(scv[0], Integer.parseInt(scv[1])));
				}

			}

		} catch (FileNotFoundException e) {
			System.out.println("Please check import file d:\\EDU\\HW12\\Books.txt exist");
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("Please check Import format is Book name and price, devided by ;");
			e.printStackTrace();
		}
		 catch (IOException e) {
				e.printStackTrace();
		}
		finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		//myLibrary.add(new Book("Java", 300));
		//myLibrary.add(new Book("C++", 250));
		myLibrary.add(new Magazine(40, 50));
		myLibrary.add(new Magazine(20, 300));

		
		Findable matcher = new FindByPrice(300);

		LibraryService libService = new LibraryService();

		List<PrintedEdition> lists = libService.find(myLibrary, matcher);

		System.out.println("Found " + lists.size() + " of PrintedEdition with price 300, here is list :");
		print(new PrintAsList(), lists);
		System.out.println("");
		System.out.println("Table of PrintedEdition with price 300 :");
		print(new PrintAsTable(), lists);
	}

	public static void print(Printable printObj, List<PrintedEdition> lists) {
		printObj.print(lists);
	}

}

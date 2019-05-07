/*
 * NAME: Zhaoyi Guo
 * PID: A15180402
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class SearchEngine {

	/* Populate a BST from a file
	 * @param searchTree - BST to be populated
	 * @param fileName - name of the input file
	 * @returns false if file not found, true otherwise
	 */
	public static boolean populateSearchTree(BSTree<String> searchTree, String fileName) {
		File file = new File(fileName);
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				//read two lines - one for document and the next line for the list of keywords
				String document = scanner.nextLine().trim();
				String keywords[] = scanner.nextLine().split(" ");
				
				//reads the file and populates the BST with nodes.
				// Each keyword is a key in the BST
				// and the document name is added to the LinkedList of the keyword.
				for (int i = 0; i < keywords.length; i++) {
					String keyword = keywords[i].toLowerCase();
					if (!searchTree.findKey(keyword)) {
						searchTree.insert(keyword);
						searchTree.insertData(keyword, document);
					}
					else {
						searchTree.insertData(keyword, document);
					}
				}
				
			}
			scanner.close();
		} catch(FileNotFoundException e) {
			System.out.println("\nFile not found!!");
			return false;
		}
		return true;
	}
	
	/*Search a query in a BST
	 * @param searchTree - BST to be searched
	 * @param fileName - query string
	 * @returns LinkedList of documents in which the query string is found
	 */
	public static void searchMyQuery(BSTree<String> searchTree, String query) {
		
		//Given any query string, you must print all documents that contain
		// that query string. If there are multiple words in a query string,
		// then you must return the intersection* of documents that contain all the words.

		String[] list = query.split(" ");
		LinkedList<String> documents = new LinkedList<>();
		if (list.length == 1) {
			LinkedList<String> document = new LinkedList<String>();
			try{
				document = searchTree.findDataList(query.toLowerCase());
				print(query, document);
			}
			catch(IllegalArgumentException e)
			{
				print(query, null);
			}

		}
		else if (list.length > 1) {
			//find the documents that all of them appeared
			boolean found = true;
			for (int i = 0; i < list.length; i++) {
				LinkedList<String> currDocuments;
				try{
					currDocuments = searchTree.findDataList(list[i].toLowerCase());
				}
				catch(IllegalArgumentException e)
				{
					found = false;
					continue;
				}
				for (int j = 0; j < currDocuments.size(); j++) {
					if (!documents.contains(currDocuments.get(j))) {
						documents.add(currDocuments.get(j));
					}
				}
			}
			LinkedList<String> allFiles = new LinkedList<String>(documents);

			for (int i = 0; i < list.length; i++) {
				LinkedList<String> currDocuments;
				try{
					currDocuments = searchTree.findDataList(list[i].toLowerCase());
				}
				catch(IllegalArgumentException e)
				{
					continue;
				}
				for (int j = 0; j < documents.size(); j++) {
					if (!currDocuments.contains(documents.get(j))) {
						documents.remove(documents.get(j));
						j--;
					}
				}
			}

			//remove files that has all three from allFiles


			if(found) {
				print(query, documents);
				allFiles.removeAll(documents);
			}
			else
				print(query, null);

			//find the documents that each appeared
			for (int i = 0; i < list.length; i++) {
				LinkedList<String> currDocuments;
				try{
					currDocuments = searchTree.findDataList(list[i].toLowerCase());
				}
				catch(IllegalArgumentException e)
				{
					print(list[i], null);
					continue;
				}
				for (int j = 0; j < currDocuments.size(); j++) {
					if (!allFiles.contains(currDocuments.get(j))) {
						currDocuments.remove(currDocuments.get(j));
						j--;
					}
				}
				if (!currDocuments.isEmpty()) {
					print(list[i], currDocuments);
				}
				allFiles.removeAll(currDocuments);
			}
		}
	}
	
	/*Print method 
	 * @param query input
	 * @param documents - result of SearchMyQuery
	 */
	 
	public static void print(String query, LinkedList<String> documents) {
		if(documents==null || documents.isEmpty())
			System.out.println("The search yielded no results for "+query);
		else {
			Object[] converted = documents.toArray();
			Arrays.sort(converted);
			System.out.println("Documents related to "+ query +" are: "+Arrays.toString(converted));
		}
	}
	
	public static void main( String[] args ) {
		
		if(args.length < 2) {
			System.err.println("Invalid number of arguments passed");
			return;
		}
		
		BSTree<String> searchTree = new BSTree<>();
		
		String fileName = args[0];
		String query = args[1];
		System.out.println(query);
		//Create my BST from file
		boolean check = populateSearchTree(searchTree, fileName);
		if(check == false) {
			System.out.println("\nUnable to create search tree");
		}
		
		searchMyQuery(searchTree, query);
	}
}

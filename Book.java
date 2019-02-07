/**
 * 
 */
package Book;

/**
 * @author jpacheco
 * Made for CS272 Data Structures
 * Lab1 Jan/29/2019
 */
public class Book{
	/**
	 * @param args
	 * 
	 */

	/**
	 * Instance Variables  
	 */
	String title;
	int authorNumber;
	String[] authors; 
	String ISBN;

	/**
	 * Default constructor
	 */	
	public Book()
	{
		title = null;
		authorNumber = 0;
		authors = new String[3];
		ISBN = null;
	}

	/**
	 * 1 Argument Constructor
	 */
	public Book(String _title)
	{
		title = _title;
		authorNumber = 0;
		authors = new String[3];
		ISBN = null;
	}

	/**
	 * Copy Constructor
	 */	
	public Book(Object obj)
	{ 
	//Make a copy of a Book object		
		if(obj != null)
		{
			if(obj instanceof Book) 
			{		
				try 
				{
				//Make new empty array for authors and copy all else
					this.authors = new String[3];
					this.ISBN = ((Book) obj).getISBN();
					this.title = ((Book) obj).getTitle();					
					for (int i = 0; i < ((Book) obj).authorNumber; i++)
					{
						String name = ((Book) obj).getAuthors(i);
						this.addAuthor(name);
					}					
				}
				catch(NullPointerException e) {
					throw new NullPointerException("Error: Original has null values.");
				}
			}
		}
	}

	/**
	 * Main
	 */
	public static void main(String[] args) throws Exception
	{
		//Initial constructor
		Book numOne = new Book();
		System.out.println(numOne.toString());
		System.out.println();

		//One argument constructor
		Book numTwo = new Book("Adventures of Sherlock Holmes");
		System.out.println(numTwo.toString());
		System.out.println();

		//Add two authors to test method
		boolean added = false;
		added = numTwo.addAuthor("Doyle");
		System.out.println("Author added condition: " + added);
		
		added = numTwo.addAuthor("Twian");
		System.out.println("Author added condition: " + added);
		System.out.println(numTwo.toString());
		System.out.println();

		//Copy with null values in original
		Book twoPt2 = new Book(numTwo);
		System.out.println(twoPt2.toString());
		System.out.println();

		//Add ISBN
		numTwo.setISBN("JIGYN8675309");
		System.out.println(numTwo.toString());
		System.out.println();

		//Copy with no null values in original
		Book twoPt3 = new Book(numTwo);
		System.out.println(twoPt3.toString());
		System.out.println();

		added = numTwo.addAuthor("Hamel");				//Add 3rd author Book2
		System.out.println("Author added condition: " + added);
		added = numTwo.addAuthor("Mr.Villian");			//Try to add 4th author Book2
		System.out.println("Author added condition: " + added);
		System.out.println();
		System.out.println(numTwo.toString());
		System.out.println();
				
		added = twoPt3.addAuthor("Wells");				//Add 3rd author Copy2
		System.out.println("Author added condition: " + added);
		System.out.println(twoPt3.toString());
		System.out.println();

		boolean diff = true;			//Start true desired false
		diff = numOne.equals(numTwo);
		System.out.println("Book1 is same as book2:" + diff);

		diff = false;					//Start false desired true
		diff = numTwo.equals(numTwo);
		System.out.println("Book2 is same as self:" + diff);

		diff = true;					//Start true desire false (only tests ISBN)
		diff = numTwo.equals(twoPt2);
		System.out.println("Book1 is same as copy1:" + diff);

		diff = false;					//Start false desire true (only tests ISBN)
		diff = numTwo.equals(twoPt3);
		System.out.println("Book1 is same as copy2:" + diff);
		System.out.println();

		//Change ISBN
		twoPt3.setISBN("ISBN5309867");
		System.out.println(twoPt3.toString());
		System.out.println();
		
		diff = true;					//Start true desire false (only tests ISBN)
		diff = numTwo.equals(twoPt3);
		System.out.println("Book1 is same as copy2:" + diff);
		System.out.println();

		//Get all authors from Book2 and Copy2 (should be 4 unique with 2 copies)
		String[] listOfAuthors = new String[6];

		listOfAuthors = Book.getAllAuthors(numTwo, twoPt3);  

		System.out.println("The unique authors are: ");
		for(int i = 0; i< listOfAuthors.length; i++)
		{
			if (listOfAuthors[i] != null) 
			{
				System.out.println(listOfAuthors[i]);
			}
			else
			{
				System.out.print("");
			}
		}

		return;
	}

	/**
	 * Getters and Setters  
	 */	

	public String getTitle() {
		return title;
	}

	public int getAuthorNumber() {
		return authorNumber;
	}

	public String getAuthors(int i) {
		return authors[i];		
	}

	public String getISBN() {
		return ISBN;
	}

	public void setTitle(String _title) {
		title = _title;
	}

	public void setISBN(String _ISBN) {
		ISBN = _ISBN;
	}

	/**
	 * addAuthor
	 * @param _author
	 * @return boolean 
	 */
	public boolean addAuthor(String _author)
	{//Add an author, returns whether or not author was added
		boolean worked = false;

		if(this.authors[0] == null)
		{
			this.authors[0] = _author;
			this.authorNumber++;
			worked = true;
		}
		else if(this.authors[1] == null)
		{
			this.authors[1] = _author;
			this.authorNumber++;
			worked = true;
		}
		else if(this.authors[2] == null)
		{
			this.authors[2] = _author;
			this.authorNumber++;
			worked = true;
		}
		else
		{
			worked = false;
		}			
		return worked;		
	}

	public boolean equals(Object obj)
	{//check if object equals book
		boolean equals = false;

		if(obj instanceof Book)
		{
			Book candidate = (Book) obj;
			return (candidate.ISBN == ISBN);
		}

		return equals;
	}

	public static String[] getAllAuthors(Book b1, Book b2)
	{//return all authors of two non-null books
		//check if one of the books is null
		if((b1 == null) || (b2 == null))
		{
			return null;
		}

		String[] authorsNames = new String[6];

		for(int i = 0; i < b1.authorNumber; i++)
		{			
			authorsNames[i] = b1.authors[i];
		}

		int duplicates = 0;
		int nextOpen = 0;
		for(int j = 0; j < b2.authorNumber; j++)
		{
			if(b2.authors[j] == b1.authors[0] || 
					b2.authors[j] == b1.authors[1] ||
					b2.authors[j] == b1.authors[2])
			{
				duplicates++;	  
			}			
			else
			{
				nextOpen = (j+b1.authorNumber)-duplicates;
				authorsNames[nextOpen] = b2.authors[j];
			}
		}


		return authorsNames;		
	}

	public String toString() 
	{
		String name1 = "";
		String name2 = "";
		String name3 = "";

		if (authorNumber == 1) {
			name1 = authors[0];
		}
		else if (authorNumber == 2) {
			name1 = authors[0];
			name2 = authors[1];
		}
		else if (authorNumber == 3) {
			name1 = authors[0];
			name2 = authors[1];
			name3 = authors[2];
		}

		String details = "Title: " + title 
				+ "\nISBN: " + ISBN
				+ "\nNumber of" + " authors: " + authorNumber
				+ "\nAuthor(s): "+name1+", " +name2 + ", " + name3;

		return details;
	}	
}

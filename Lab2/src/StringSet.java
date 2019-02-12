/**
 * @author jpacheco
 * @class CS272 - Data Structures
 * @lab Lab #2 
 */

/**
 * @invariables count must always be <= cap
 * @invariables arrays are filled from 0 to count-1
 */


public class StringSet {

	/**
	 * Instance Variables
	 */
	public String[] auths;	
	public int cap;
	public int count;

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{

		StringSet tester = new StringSet();
		if (tester != null) {System.out.println("Tester not null!");}
		System.out.println("Default constructor works");

		int capaCD = 10;
		StringSet test2 = new StringSet(capaCD);
		if(test2.cap == capaCD){System.out.println("Pre-sized constructor works");}

		StringSet copy = new StringSet(tester);
		if (copy != null) {System.out.println("Copy constructor works");}

		test2.Add("Earth");
		System.out.println("Add method works");

		System.out.println("Test2 is size of: " + test2.Size() + "");
		System.out.println("Size method works");

		boolean in = false;
		in = test2.Contains("Earth");
		System.out.println("Earth in test2: " + in);
		System.out.println("Contains method works");

		in = test2.Contains("Mars");
		System.out.println("Mars in test2: " + in);

		test2.Add("Mars");

		in = test2.Contains("Mars");
		System.out.println("Mars in test2: " + in);

		for(int i = 0;i<test2.count;i++) 
		{
			System.out.println(test2.auths[i]);
		}

		test2.AddOrdered("Mercury");
		for(int i = 0;i<test2.count;i++) 
		{
			System.out.println(test2.auths[i]);
		}

		System.out.println("AddOrdered method works as add");

		test2.AddOrdered("Jupiter");
		for(int i = 0;i<test2.count;i++) 
		{
			System.out.println(test2.auths[i]);
		}

		System.out.println("AddOrdered method works");

		test2.AddOrdered("Venus");
		test2.AddOrdered("Sun");
		test2.AddOrdered("Moon");
		test2.AddOrdered("Saturn");
		test2.AddOrdered("Uranus");
		test2.AddOrdered("Neptune");
		test2.AddOrdered("Pluto");
		test2.AddOrdered("Planet-X");
		test2.AddOrdered("Moon-Y");

		for(int i = 0;i<test2.count;i++) 
		{
			System.out.println(test2.auths[i]);
		}

		System.out.println("Test2 is size of: " + test2.Size() + "");
		System.out.println("Test2 capacity is: " + test2.cap + "");
		System.out.println("Ensure capacity method works");

		boolean out = false;
		out = test2.Remove("Planet-X");
		System.out.println("Input removed: " + out);
		
		for(int i = 0;i<test2.count;i++) 
		{
			System.out.println(test2.auths[i]);
		}		
		if(out == true) {System.out.println("Remove method works for Good input");}
		
		out = test2.Remove("All the planets");
		System.out.println("Input removed: " + out);
		for(int i = 0;i<test2.count;i++) 
		{
			System.out.println(test2.auths[i]);
		}
		if(out == false) {System.out.println("Remove method works for Bad input");}
		
		return;		
	}//end main

	public StringSet() 
	{
		cap = 2;		
		auths = new String[cap];
		count = 0;
	}//end default constructor

	/**
	 * @param _capacity
	 * @precondition _capacity must be positive integer value
	 */
	public StringSet(int _capacity)
	{
		//Uses this as instance of StringSet
		//Make the copy's cap and array _capacity sized
		this.cap = _capacity;
		this.auths = new String[this.cap];
		this.count = 0;  
	}//end sized array constructor

	/**
	 * @param obj
	 * @precondition obj should not be null
	 * @precondition obj should be instance of StringSet 
	 */
	public StringSet(Object obj)
	{
		//Make a copy of a StringSet object
		StringSet copy = new StringSet();

		if(obj != null)
		{			
			if(obj instanceof StringSet) 
			{		
				try 
				{
					int names = ((StringSet) obj).count;
					//Make the copy's cap and array obj.cap sized
					copy.cap = ((StringSet) obj).cap;;
					copy.auths = new String[copy.cap];

					//Copy all elements
					for (int i = 0; i < names; i++)
					{
						copy.auths[i] = ((StringSet) obj).auths[i]+ "";
						copy.count++;
					}//end array copy 					
				}//end try block
				catch(NullPointerException e)
				{
					throw new NullPointerException("Error: Original has null values.");
				}//end null error
			}//end instance if
			else
			{
				System.out.println("Error: Object not a StringSet.");}
		}//end not instance error
		else
		{
			System.out.println("Error: Object null.");
		}//end null input error
	}//end copy constructor

	public int Size()
	{
		return count;
	}

	public int Capacity()
	{
		return cap;
	}

	/**
	 * @param int minimumCapacity
	 * @return returns double sized array with same data
	 */
	private void EnsureCapacity(int minimumCapacity)
	{
		//Adds 1 in case current capacity is 0
		//Double capacity size and make new interim array
		int newCap = (minimumCapacity+1)*2;
		StringSet inter = new StringSet(newCap);

		String temp = "";
		//Change capacity of working array
		this.cap = newCap;

		//Copy all elements
		for (int j = 0; j < count; j++)
		{
			temp = this.auths[j];
			inter.auths[j]=temp;
		}//end copy for

		//Change array for new bigger array
		this.auths = inter.auths;
	}//end EnsureCapacity

	/**
	 * @param a
	 * @precondition String value a should not be null
	 * @precondition String value a should be unique
	 * list will be ordered after add
	 */
	public void Add(String a)
	{
		//All names lower cased to ensure accuracy 
		String aCopy = a;
		aCopy = aCopy.toLowerCase();

		if(a != null)
		{
			/**
			 * Check if to small first then
			 * call ensureCapacity()
			 * make recursive call to add();
			 */
			if (cap <= count)
			{
				this.EnsureCapacity(cap);
				this.Add(a);			
			}//end doubling if

			//If cap larger than count 
			else
			{						
				//count++ adds at count then increases count
				this.auths[count++] = a;											
			}//end else cap check	
		}//end null check
		else
		{
			System.out.println("Error: a's value is null.");
		}//end null error	
	}//end Add

	/**
	 * @param a 
	 * @precondition a should not be null
	 * @return If a is in array of authors:true 
	 * @return If a not in array of authors:falsee
	 */
	public boolean Contains(String a)
	{
		//All names lower cased to ensure accuracy 
		String aCopy = a;
		aCopy = aCopy.toLowerCase();

		boolean dupes = false;

		//Check for a
		for (int k = 0; k < count; k++)
		{
			String name = auths[k];
			name = name.toLowerCase();
			if (name.equals(aCopy))
			{
				dupes = true;
			}					
		}//end dupes check

		return dupes;
	}//end contains

	/**
	 * @param a 
	 * @precondition a should not be null
	 * @precondition array should already be ordered
	 */
	public void AddOrdered(String a) 
	{
		//All names lower cased to ensure accuracy 
		String aCopy = a;
		String[] reOrdered = new String[cap];
		aCopy = aCopy.toLowerCase();

		boolean dupes = false;
		if(a != null)
		{
			//ensure a not in list
			dupes = this.Contains(a);
			if(dupes == false) 
			{
				/**
				 * Check if to small first then
				 * call ensureCapacity()
				 * make recursive call to add();
				 */

				if (cap <= count)
				{
					this.EnsureCapacity(cap);
					this.AddOrdered(a);			
				}//end doubling if

				//If cap larger than count 
				else
				{
					int index = 0;

					//First loop used for name lowering
					//all names lower cased for ensured accuracy
					for(int n = 0; n < count; n++)
					{
						String name = auths[n];
						name = name.toLowerCase();

						//Second loop used for letters
						for(int l = 0; l < name.length();l++)
						{
							//if names letter comes before a's skip to next name 
							if(name.charAt(l) < aCopy.charAt(l))
							{
								l = name.length();
							}//end higher name check 
							//if names letter comes after a's set index and exit loops
							else if(name.charAt(l) > aCopy.charAt(l))
							{
								index = n;
								l = name.length();
								n = count;
							}//end lower name check						
						}//end for to find index			
					}//end names for

					if (index!=0) {
						//Using index build first section of ordered array
						for(int m = 0; m < index; m++)
						{
							reOrdered[m] = auths[m];
						}

						//Add a to reordered list and increase count 
						reOrdered[index] = a;
						count++;

						//Using count finish final section of ordered array
						for(int p = index; p < count; p++)
						{
							this.EnsureCapacity(count);
							reOrdered[p+1] = auths[p];
						}
						this.auths = reOrdered;
					}
					else
					{
						this.auths[count++] = a;
					}
				}
			}
		}
	}
	/**
	 * @param a 
	 * @precondition a should not be null
	 * @precondition array should already be ordered
	 */	
	public boolean Remove(String a)
	{
		boolean del = false;

		String aCopy = a;
		aCopy = aCopy.toLowerCase();

		//Check array not empty
		if (this.auths[0] != null)
		{
			//Check a not null
			if(a!=null)
			{			
				//Check for a
				for (int k = 0; k < count; k++)
				{
					String name = auths[k];
					name = name.toLowerCase();
					//if item is found
					if (name.equals(aCopy))
					{
						auths[k] = auths[count-1];
						auths[count-1] = null;
						k = count;
						count--;
						del = true;
					}//End contains check					
				}//End contains for loop			
			}//End a null
			else 
			{
				System.out.println("Error: a is null");
			}
		}//End array empty
		else 
		{
			System.out.println("Error: Array is empty");
		}

		return del;
	}//End Remove
}//EndClass
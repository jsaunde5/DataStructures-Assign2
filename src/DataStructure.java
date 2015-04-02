/*
this data structure is comprised of an array of DataStructureRecord. This structure is large enough to hold 100
records. We have an index for the ID, for the LastName and for the FirstName. Each index creates an Double ended, 
doubly linked list for each field. This class also contains methods for organizing data structure.
*/

public class DataStructure 
{
	private DataStructureRecord [] data;  //big array of all students
	private int nextRec;
	private int nElems;
	private LinkedList fNameIndex;
	private LinkedList lNameIndex;
	private LinkedList IDindex;
	
//DataStructure constructor with size 100
	public DataStructure()
	{
		data = new DataStructureRecord [100];
		nextRec = 0;  
		nElems = 0;
		fNameIndex = new LinkedList(1);  //size 100, field type
		lNameIndex = new LinkedList(2);
		IDindex = new LinkedList(3);
	}
//get LinkedList of each field
	public LinkedList getFnameIndex()
	{
		return fNameIndex;
	}	
	public LinkedList getLnameIndex()
	{
		return lNameIndex;
	}
	public LinkedList getIDindex()
	{
		return IDindex;
	}
	
//get nElems of big array
	public int getNelems()
	{
		return nElems;
	}
//get record within big array at specific index
	public DataStructureRecord getRecord(int index)
	{
		return this.data[index];
	}
	
//calling findID(), Return false if ID is not found.
	public boolean search(String tempID) 
	{
		return ((findID(tempID)) != -1); 
	}

//findID method. Returns -1 if ID is not found. Returns index of record in big array
	public int findID(String keyVal) 
	{
		IndexRecord currentNode = this.IDindex.getFirst();
		IndexRecord lastNode = this.IDindex.getLast();
		boolean found = false;
		
		while(!found)
		{
			if (currentNode.getKey().equals(keyVal))
			{
				found = true;
			}
		//we're at the end and we still haven't found it, return -1
			else if (currentNode == lastNode)
			{
				return -1;
			}
		//didn't find it, move onto the next one
			else
				currentNode = currentNode.getNext();			
		}		
		return currentNode.getRecordNumber();
	}


//insert into big array
//check if deleteStack is empty. Insert in popped index from stack if available
	public void insert(String name1, String name2, String tempID, Stack deleteStack) 
	{
		if (deleteStack.isEmpty())
		{
			nextRec = nElems;	
			nElems++; //only add nElems if put at end of Big Array (for lastRecord)
		}
		else
		{
			nextRec = deleteStack.pop();  //put new record in deleted index
		}
	//create new dataStructureRecord	
		DataStructureRecord newRecord = new DataStructureRecord( name1,  name2,  tempID);
		fNameIndex.insert(newRecord, nextRec);  //(newDataStrcRec, reference to big array)
		lNameIndex.insert(newRecord, nextRec);
		IDindex.insert(newRecord, nextRec);
		data[nextRec] = newRecord;  //big Array
	}

//different listings: Ascending/Descending order by field type fName, lName, ID
//using printArray method in LinkedList class to print each record
	public void listIt(int field, int order) 
	{
	// 1 -> First Name
		if(field == 1)
			fNameIndex.printList(order, this); //pass dataStructure instance into printArray
	// 2 -> Last Name	
		else if(field == 2)
			lNameIndex.printList(order, this);
	// 3 -> ID	
		else
			IDindex.printList(order, this);
	}

//takes in index from Big Array and uses deleteRec to delete from each OrderedArray from recordNumber index
	public void delete(int index) 
	{
		fNameIndex.deleteRec(getFnameIndex(), index);
		lNameIndex.deleteRec(getLnameIndex(), index);
		IDindex.deleteRec(getIDindex(), index);	
	}


}//end DataStructure class

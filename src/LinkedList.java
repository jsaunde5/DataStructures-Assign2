/*
This class creates Doubly Linked, Double ended linked list from IndexRecord (nodes). This class
also contains methods for inserting, deleting, searching and printing along with getters and setters.
*/

public class LinkedList 
{
	private IndexRecord first;
	private IndexRecord last;
	private int listType;  //fName, lName, ID
	
//constructor, creating a first and last instead of putting null to make 
//inserting and deleting easier (less corner cases)
	public LinkedList(int field)
	{
		IndexRecord veryFirst = new IndexRecord("!", -9); //early ascii
		IndexRecord veryLast = new IndexRecord("~~~~~~", -8);  //late ascii
		first = veryFirst;
		last = veryLast;
		this.listType = field;		
		first.setNext(last);  //was not working until added setNext and setPrev
		last.setPrev(first);
	}
//getters for first and last nodes	
	public IndexRecord getFirst()
	{
		return first;
	}	
	public IndexRecord getLast()
	{
		return last;
	}

//insert in order
	public void insert (DataStructureRecord dataStructRec, int indexPosition)
	{
		
		String key = "";
		IndexRecord current = first;
		IndexRecord previous = first;
		switch(this.listType)  //get key for appropriate field
		{
		case 1: key = dataStructRec.getFirstName();
			break;
		case 2: key = dataStructRec.getLastName();
			break;
		case 3: key = dataStructRec.getID();
			break;
		default:
			break;		
		}
		while(current.getKey().compareTo(key) < 0)
		{
			current = current.getNext();
			previous = current;			
		}
	//found node after where we want to insert, now back up one
		current = current.getPrev();
	//insert after current, now insert and update pointers
		IndexRecord newNode = new IndexRecord(key, indexPosition);
		newNode.setNext(current.getNext());
		newNode.setPrev(current);
		current.setNext(newNode);
		newNode.getNext().setPrev(newNode);		
	}
	
//delete from linked list. Record to delete, index of big array
	public void deleteRec(LinkedList delRec, int index)
	{
		IndexRecord current = linSearch(index);	
		current.getPrev().setNext(current.getNext()); //old prev -> old next
		current.getNext().setPrev(current.getPrev()); //old prev <- old next  		
	}
	
//search for recordNumber of IndexRecord (index of big array)
	public IndexRecord linSearch(int keyVal)
	{
	//start at beginning
		IndexRecord rover = first;
		while(rover != null)
		{
			if (rover.getRecordNumber() == keyVal)
				break;  //found it, break out and return IndexRecord
			else 
				rover = rover.getNext();
		}
		return rover;  //return null if not found, but would never happen
	}           //because the index will always exist (checked earlier)

	public void printList(int order, DataStructure dataStructure) 
	{
	//true if ascending
		boolean ascending = (order == 1);  
	//Ternary operators. If ascending is true, start at first.next and end at last
	//skip the very first and very last Nodes (they are dummies)
		IndexRecord currentNode = ascending ? first.getNext() : last.getPrev();
		IndexRecord endNode = ascending ? last : first;
		
		while(currentNode!=endNode)
		{	
			System.out.println(dataStructure.getRecord(currentNode.getRecordNumber()).toString());
		//update current node; dependent on ascending or descending
			currentNode = ascending ? currentNode.getNext() : currentNode.getPrev();
		}		
	}	

}

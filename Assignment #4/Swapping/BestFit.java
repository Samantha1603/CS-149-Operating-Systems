import java.util.ArrayList;
import java.util.LinkedList;

public class BestFit {

	private static int MINUTES, SMALLEST_SIZE, MAIN_MEMORY_SIZE;
	private LinkedList<Process> process;
	private ArrayList<Process> currentProcess;
	private char[] mainMemory;
	private int freeSpace, swappedIn, swappedOut; 
	
	public int getSwappedIn() {
		return swappedIn;
	}

	public int getSwappedOut() {
		return swappedOut;
	}

	public BestFit(LinkedList<Process> p) {
		MINUTES = 60; SMALLEST_SIZE = 5; MAIN_MEMORY_SIZE = 100;
		process = p;
		mainMemory = new char[MAIN_MEMORY_SIZE];
		currentProcess = new ArrayList<Process>();	
		freeSpace = 100;
	    swappedIn = 0;
	    swappedOut = 0;
		
		for(int i = 0; i < MAIN_MEMORY_SIZE; i++) {
			mainMemory[i] = '.';
		}
	}
	
	public void run() {
		initialFill();
		for(int i = 1; i < MINUTES; i++) {
			int j = 0, tempDuration = 0; 
			while(j < currentProcess.size()) {
				tempDuration = currentProcess.get(j).getDuration()-1;
				currentProcess.get(j).setDuration(tempDuration);
				if(tempDuration == 0) {
					Process p = currentProcess.remove(j);
					swappedOut++;
					freeSpace += p.getSize();
					
					String t = String.format("time  %2d: Swapped Out Process %s", i, p.getName());
					System.out.println(t);
					swapOut(p);
					print();
				} else {
					j++;
				}
			}
			
			j = 0;
			while(freeSpace >= SMALLEST_SIZE && j < process.size()) {
				int[] bestFitBlock = bestFitEmptyBlock(process.get(j).getSize());
				int count = bestFitBlock[1];
				if(process.get(j).getSize() <= bestFitBlock[0]) {
					Process p = process.remove(j);
					int size = p.getSize();
					char name = p.getName();
					for(int k = 0; k < size; k++) {
						mainMemory[count] = name;
						count++;
					}
					currentProcess.add(p);
					freeSpace -= size;
					swappedIn++;
					String t = String.format("time %2d: Swapped In Process %s Size is %2d Duration is %d", i, p.getName(), p.getSize(), p.getDuration());
					System.out.println(t);
					print();
				} else {
					j++;
				}
			}
		} 
	}
	
	private void initialFill() {
		int count = 0, i = 0;
		while(freeSpace >= SMALLEST_SIZE && i < process.size()) {
			if(process.get(i).getSize() <= freeSpace) {
				Process p = process.remove(i);
				int size = p.getSize();
				char name = p.getName();
				for(int j = 0; j < size; j++) {
					mainMemory[count] = name;
					count++;
				}
				currentProcess.add(p);
				freeSpace -= size;
				swappedIn++;
				String t = String.format("time  0: Swapped In Process %s Size is %2d Duration is %d", p.getName(), p.getSize(), p.getDuration());
				System.out.println(t);
				print();
			} else {
				i++;
			}
		}
	}
	
	private void swapOut(Process p) {
		char name = p.getName();
		int size = p.getSize(), i = 0;
		
		for(;i < MAIN_MEMORY_SIZE; i++) {
			if(mainMemory[i] == name) {
				break;
			}
		}
		size += i;
		for(;i < size && i < MAIN_MEMORY_SIZE; i++) {
			mainMemory[i] = '.';
		}
	}
	
	/**
	 * Returns the Best Fit Empty block size, and the index of the Best Fit Empty Block
	 * @return index of the Best Fit Empty block.
	 */
	private int[] bestFitEmptyBlock(int x) {
		int emptyBlocks = 0, currentSize = 0, tempIndex = -1;
		int[] result = new int[2];
		boolean isFirst = true;
		
		int emptyMemSize[] = new int[MAIN_MEMORY_SIZE];	// worst case
		
		for(int i = 0; i < MAIN_MEMORY_SIZE; i++) {
			if(mainMemory[i] == '.') {
				currentSize++;
				if(isFirst) {
					isFirst = false;
					tempIndex = i;
				}
			} else {
				
				if(currentSize > 0){
				emptyMemSize[tempIndex] = currentSize;
				} 
				currentSize = 0;
				isFirst = true;
			}
		}
		
		if(currentSize > 0){
		emptyMemSize[tempIndex] = currentSize;
		}
		currentSize = 0;
		isFirst = true;
		
		for(int i=0; i<emptyMemSize.length; i++){
			if(emptyMemSize[i]>0){
				emptyBlocks++;
			}
		}
		
		int blockSize[] = new int[emptyBlocks];
		int blockIndex[] = new int[emptyBlocks];
		int ctr = 0;
		for(int i=0; i<emptyMemSize.length; i++){
			if(emptyMemSize[i]>0){
				blockSize[ctr] = emptyMemSize[i];
				blockIndex[ctr++] = i;
			}
		}
		emptyMemSize = null;
		/*System.out.println("Total empty Blocks = "+emptyBlocks);
		System.out.println("Index: "+Arrays.toString(blockIndex));
		System.out.println("Size : "+Arrays.toString(blockSize));
		System.out.println("Next process size = "+x);*/
		
		int finalIndex=0, finalSize = 0;
		isFirst = true;
		for(int i=0; i<emptyBlocks; i++){
			if(blockSize[i] >= x){
				if(isFirst)
				{
					finalIndex = blockIndex[i];
					finalSize = blockSize[i];
					isFirst = false;
				}
				else if(blockSize[i] < finalSize)
				{
						finalIndex = blockIndex[i];
						finalSize = blockSize[i];
				}
			}
		}
		
		result[0] = finalSize;
		result[1] = finalIndex;
		
		//System.out.println("Result: "+Arrays.toString(result));
		
		return result;
	}
	
	public void print() {
		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i < MAIN_MEMORY_SIZE; i++) {
			buffer.append(mainMemory[i]);
		}
		System.out.println(buffer.toString());
	}
}

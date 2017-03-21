import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;
public class blockProblem {

	public static void main(String[] args) {
		System.out.println("Initialize World:");
		// Scanner that asks the user to specify the length of the array that will represent the "Block World".
		Scanner initialize = new Scanner(System.in);
		Stack<Integer>[] world = new Stack[initialize.nextInt()];
		// The for loop initializes each stack (or else they will all be Null) and adds the value equivalent to which numbered "block" they are.
		// This will allow for pushing and popping onto other stacks in the array, as the problem specifies.
		for(int i = 0; i<world.length-1;i++){
			world[i] = new Stack<Integer>();
			world[i].add(i);
		}
		Scanner keyboard = new Scanner(System.in);
		// This loop essentially will have the method continuously take commands until "quit" is given.
		while (true) {
			String command = keyboard.nextLine();
			if (command.equals("quit")){
				
				break;
			}
			String[] com = command.split(" ");
			if (com[0].equals("get")){
				printStack(world[Integer.parseInt(com[1])]);
			}
			else if (com.length!=4 || Integer.parseInt(com[1])==Integer.parseInt(com[3])){
				System.out.println("Invalid Command");
			}
			else{
				if(com[0].equals("move")){
					if(com[2].equals("onto")){
						moveOnto(world,Integer.parseInt(com[1]),Integer.parseInt(com[3]));
					}
					else if(com[2].equals("over")){
						moveOver(world,Integer.parseInt(com[1]),Integer.parseInt(com[3]));
					}
				}
				if(com[0].equals("pile")){
					if(com[2].equals("onto")){
						pileOnto(world,Integer.parseInt(com[1]),Integer.parseInt(com[3]));
					}
					else if(com[2].equals("over")){
						pileOver(world,Integer.parseInt(com[1]),Integer.parseInt(com[3]));
					}
				}
			}
		}

	}

	// The following comments on top of each method are the exact specifications included on the UVa page.
	
	
	 /* where a and b are block numbers, puts block a onto block b after returning any blocks that are
	 stacked on top of blocks a and b to their initial positions. */
	public static void moveOnto(Stack<Integer>[] arr, int a, int b){
		int aLoc = 0;
		int bLoc = 0;
		for (int i = 0; i < arr.length-1; i++){
			if (arr[i].contains(a)){
				aLoc = i;
			}
			if (arr[i].contains(b)){
				bLoc = i;
			}
		}
		while(arr[aLoc].peek()!=a){
			arr[arr[aLoc].peek()].push(arr[aLoc].pop());
		}
		while(arr[bLoc].peek()!=b){
			arr[arr[bLoc].peek()].push(arr[bLoc].pop());
		}
		arr[bLoc].push(arr[aLoc].pop());
		printStack(arr[bLoc]);
	}
	
	
	 /* where a and b are block numbers, puts block a onto the top of the stack containing block b, after
	 returning any blocks that are stacked on top of block a to their initial positions. */
	public static void moveOver(Stack<Integer>[] arr, int a, int b){
		int aLoc = 0;
		int bLoc = 0;
		for (int i = 0; i < arr.length-1;i++){
			if (arr[i].contains(a)){
				aLoc = i;
			}
			if (arr[i].contains(b)){
				bLoc = i;
			}
		}
		while(arr[aLoc].peek()!=a){
			arr[arr[aLoc].peek()].push(arr[aLoc].pop());
		}
		arr[bLoc].push(arr[aLoc].pop());
		printStack(arr[bLoc]);
	}

	/* where a and b are block numbers, moves the pile of blocks consisting of block a, and any blocks
	that are stacked above block a, onto block b. All blocks on top of block b are moved to their
	initial positions prior to the pile taking place. The blocks stacked above block a retain their order
	when moved. */
	public static void pileOnto(Stack<Integer>[] arr, int a, int b){
		int aLoc = 0;
		int bLoc = 0;
		for (int i = 0; i < arr.length-1;i++){
			if (arr[i].contains(a)){
				aLoc = i;
			}
			if (arr[i].contains(b)){
				bLoc = i;
			}
		}
		while(arr[b].size()!=1){
			arr[arr[b].peek()].push(arr[b].pop());
		}
		while(!arr[a].empty()){
			arr[b].push(arr[a].pop());
		}
		printStack(arr[b]);
	}
	
	/* where a and b are block numbers, puts the pile of blocks consisting of block a, and any blocks
	that are stacked above block a, onto the top of the stack containing block b. The blocks stacked
	above block a retain their original order when moved. */
	public static void pileOver(Stack<Integer>[] arr, int a, int b){
		int aLoc = 0;
		int bLoc = 0;
		for (int i = 0; i < arr.length-1;i++){
			if (arr[i].contains(a)){
				aLoc = i;
			}
			if (arr[i].contains(b)){
				bLoc = i;
			}
		}
		Stack<Integer> temp = new Stack<Integer>();
		int j = 0;
		while(j < arr.length-1){
			temp.push(arr[aLoc].pop());
			j++;
			if (temp.peek()==a){
				break;
			}
		}
		printStack(temp);
		System.out.println("//");
		while(!temp.empty()){
			arr[bLoc].push(temp.pop());
		}
		printStack(arr[b]);
	}
	//This method is simply for testing that my methods are doing the correct things.
	public static void printStack(Stack<Integer> stack){
		for(Integer i : stack){
			System.out.println(i);
		}
	}
}

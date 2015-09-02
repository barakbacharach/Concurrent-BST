import java.util.concurrent.Semaphore;

public class BST{
	public int val;
	public BST left;
	public BST right;
	public Semaphore sem;
	public static void main(String[] args) {
	}
	//This next program is for creating the initial BST and is accessed by constructor.
	public void create_bst(int starting_val){
		this.val=starting_val;
		this.left=null;
		this.right=null;
		this.sem= new Semaphore(1);
	}
	
	//This is the main constructor -- it is main way to input values into the BST
	
	public void add_value(int to_add){
		if (to_add==this.val){
			return; // This is to prevent duplicates. If the value already exists, do nothing. 
		}
		if (to_add<this.val){
			if (this.left==null){
				try {
					sem.acquire();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				BST temp = new BST();
				temp.create_bst(to_add);
				this.left=temp;
				sem.release();
			}
			else {
				this.left.add_value(to_add);
				
			}
		
		}
		if (to_add>this.val){
			if (this.right==null){
				BST temp = new BST();
				temp.create_bst(to_add);
				this.right=temp;			}
			else{
				this.right.add_value(to_add);
			}
		}
	}
	public void delete_node(int delete_val){
		//These two are basic cases; if the node to be deleted has no children.
		if (this.left!=null){
		if (this.left.val==delete_val){
			try {
				this.left.sem.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			if (this.left.right == null && this.left.left==null){
				this.left=null;
				this.left.sem.release();
				return;	
			}
		}
		}
		if (this.right!=null){
		if (this.right.val==delete_val){
			if (this.right.right == null && this.right.left==null){
				try {
					this.right.sem.acquire();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();}
				this.right=null;
				this.right.sem.release();
				return;
			}
		}
		}
		//These are two slightly more advanced cases -- if the node to be deleted has one child.
		//First you change its value to its only child node, 
		//then you change its children to its child's children.
		//In this way, it essentially becomes a new node, rather than deleting another one.
		if (this.val==delete_val){
			try {
				this.sem.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (right!=null^left!=null){
				if (right!=null){
					try {
						this.right.sem.acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					this.val=this.right.val;
					this.left=this.right.left;
					this.right=this.right.right;}
					this.right.sem.release();
				if (left!=null){
					try {
						this.left.sem.acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					this.val=this.left.val;
					this.right=this.left.right;
					this.left=this.left.left;
					this.left.sem.release();
					
				}
			}
		//This is the most complicated case -- where the node has two children.
		//The proper way of doing this is to take the least value of the right subtree 
			//and switch the two.
		if (this.right!=null && this.left!=null){
			BST temp= new BST();
			temp=this.right;
			while (temp.left!=null){
				temp=temp.left;
			}
			this.val=temp.val;
			this.delete_node(temp.val);
		}
		}
		 if (this.val>delete_val){
			if (this.left!=null){
				this.left.delete_node(delete_val);
			}
		}
		 if (this.val<delete_val){
			if (this.right!=null){
			this.right.delete_node(delete_val);
			}
		}
	}
	
	//This is a print method for the BST class
	public void print_BST(){
		System.out.println(this.val);
		if (this.left!=null){
			System.out.println("left child of" +this.val+" is" +this.left.val);
			this.left.print_BST();
		}
		if (this.right!=null){
			System.out.println("right child of is" +this.val+"is" +this.right.val);
			this.right.print_BST();
		}
	}
	//This is a program used only by the delete node function. It removes the pointer from a BST.
	public void erase_node(BST to_erase){
		to_erase =null;
	}
}

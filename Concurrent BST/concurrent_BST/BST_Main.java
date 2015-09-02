
public class BST_Main extends Thread{
	
	public static void main(String args[]){
		BST test= new BST();
		test.create_bst(10);
		test.add_value(8);
		test.add_value(14);
		test.add_value(25);
		test.add_value(5);
		test.add_value(7);
		test.add_value(3);
		test.add_value(11);
		test.add_value(9);
		//test.delete_node(8);
		test.delete_node(9);
		test.delete_node(14);
		test.print_BST();

		





	}
}
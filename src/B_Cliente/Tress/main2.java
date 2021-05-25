package B_Cliente.Tress;
public class main2 {
    public static void main(String[] args){
        AVLTree<Integer> avlTree= new AVLTree<>();
        avlTree.insert(10);
        avlTree.insert(20);
        avlTree.insert(30);
        avlTree.insert(40);
        avlTree.insert(50);
        avlTree.insert(25);
        avlTree.insert(100);
        avlTree.insert(120);
        avlTree.insert(125);
        avlTree.insert(64);
        avlTree.printTree();
    }
}


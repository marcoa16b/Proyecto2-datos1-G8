package B_Cliente.Tress;
public class TreeNode<T extends Comparable<T>> {
    private T value;
    private TreeNode<T> right;
    private TreeNode<T> left;
    public TreeNode(T value){
        this.value = value;
        this.right = null;
        this.left = null;
    }
    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }
    public TreeNode<T> getRight() {
        return right;
    }
    public void setRight(TreeNode<T> right) {
        this.right = right;
    }
    public TreeNode<T> getLeft() {
        return left;
    }
    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }
}

package tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {

        BinaryTree binaryTree = new BinaryTree();

        TreeNode root = new TreeNode(1,"小红");
        TreeNode node2 = new TreeNode(2,"小黑");
        TreeNode node3 = new TreeNode(3,"小白");
        TreeNode node4 = new TreeNode(4,"小绿");

        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        binaryTree.setRoot(root);
        System.out.println("前序遍历");
        binaryTree.preOder();
        System.out.println("========================");
        binaryTree.preOrderSearch(6);

        //测试删除
        System.out.println("删除前，前序遍历");
        binaryTree.preOder();
        binaryTree.delNode(3);
        System.out.println("删除后，前序遍历");
        binaryTree.preOder();
    }

}

//定义BinaryTree 二叉树
class BinaryTree{
    private TreeNode root;

    public void setRoot(TreeNode root){
        this.root = root;
    }

    //删除节点
    public void delNode(int no){
        if (root != null){
            //判断根节点是不是要删除的
            if (root.getNo() == no){
                root = null;
            } else {
                root.delNode(no);
            }
        } else {
            System.out.println("空树不能删除");
        }
    }
    //前序遍历
    public void preOder(){
        if (this.root != null){
            this.root.preOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }
    //中序遍历
    public void infixOrder(){
        if (this.root != null){
            this.root.infixOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }
    //后序遍历
    public void postOrder(){
        if (this.root != null){
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }

    //前序查找
    public TreeNode preOrderSearch(int no){
        if (root != null){
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序查找
    public TreeNode infixOrderSearch(int no){
        if (root != null){
           return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序查找
    public TreeNode sa(int no){
        if (root != null){
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

}

class TreeNode{
    private int no;
    private String name;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //递归删除节点
    public void delNode(int no){
        //如果当前节点的左子树节点不为空，并且左子节点就是要删除节点，就将this.left=null并且返回
        if (this.left != null && this.left.no == no){
            this.left = null;
            return;
        }
        //如果当前节点的右子节点不为空，并且右子节点就是要删除节点，就将this.right=null并且就返回
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        //我们就需要向左子树进行删除
        if (this.left != null){
            this.left.delNode(no);
        }
        //则应当向右子树进行递归删除
        if (this.right != null){
            this.right.delNode(no);
        }
    }

    //编写前序遍历的方法
    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }
    //中序遍历
    public void infixOrder(){
        if (this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null){
            this.right.infixOrder();
        }
    }
    //后序遍历
    public void postOrder(){
        if (this.left != null){
            this.left.postOrder();
        }
        if (this.right != null){
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //前序查找
    public TreeNode preOrderSearch(int no){
        if (this.no == no){
            return this;
        }
        TreeNode resNode = null;
        if (this.left != null){
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        if (this.right != null){
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序查找
    public TreeNode infixOrderSearch(int no){
        TreeNode resNode = null;
        if (this.left != null){
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        if (this.no == no){
            return this;
        }
        if (this.right != null){
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后序查找
    public TreeNode postOrderSearch(int no){
        TreeNode resNode = null;
        if (this.left != null){
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        if (this.right != null){
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        if (this.no == no){
            return this;
        }
        return null;
    }
}
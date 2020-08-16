package tree.threadedbinarytree;

public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,"小黑");
        TreeNode node2 = new TreeNode(3,"小黑");
        TreeNode node3 = new TreeNode(6,"小黑");
        TreeNode node4 = new TreeNode(8,"小黑");
        TreeNode node5 = new TreeNode(10,"小黑");
        TreeNode node6 = new TreeNode(14,"小黑");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        System.out.println(node5.getLeft());
        System.out.println(node5.getRight());
        System.out.println("=============================");
        threadedBinaryTree.threadedList();

    }
}

//线索二叉树
class ThreadedBinaryTree{
    private TreeNode root;

    //为了实现线索化，需要创建要给指向当前节点的前驱节点指针
    //在递归进行线索化时，pre总是保留前一个节点
    private TreeNode pre = null;

    public void setRoot(TreeNode root){
        this.root = root;
    }

    public void threadedNodes(){
        this.threadedNodes(root);
    }

    //遍历中序线索化二叉树的方法
    public void threadedList(){
        //定义一个变量，存储当前节点，从root开始
        TreeNode treeNode = root;
        while (treeNode != null){
            //循环找到leftType==1的节点，第一个找到就是8节点
            //后面随着遍历而变化，因为当leftType==1说明该节点按照线索化
            //处理后的有效节点
            while (treeNode.getLeftType() == 0){
                treeNode = treeNode.getLeft();
            }
            //打印当前节点
            System.out.println(treeNode);
            //如果当前节点右指针指向的是后继节点，就一直输出
            while (treeNode.getRightType() == 1){
                //获取当前节点
                treeNode = treeNode.getRight();
                System.out.println(treeNode);
            }
            //替换这个遍历节点
            treeNode = treeNode.getRight();
        }
    }

    //前序线索化二叉树
    public void preThreadNodes(TreeNode node){
        if (node == null){
            return;
        }
        //线索化当前节点

    }

    //中序线索化
    public void threadedNodes(TreeNode node){
        //如果node = null不能线索化
        if (node == null){
            return;
        }
        //(1)先线索化左子树
        threadedNodes(node.getLeft());
        //(2)线索化当前节点
        //处理当前节点的前驱节点
        if (node.getLeft() == null){
            //让左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针的类型，指向前驱节点
            node.setLeftType(1);
        }

        //处理后继节点
        if (pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        //没处理一个节点后让pre指向node，让当前节点是下一个节点的前驱节点
        pre = node;
        //(3)线索化右子树
        threadedNodes(node.getRight());
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
    public TreeNode postOrderSearch(int no){
        if (root != null){
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

}

//节点
class TreeNode{
    private int no;
    private String name;
    private TreeNode left;
    private TreeNode right;

    //如果leftType=0指向左子树，1指向前驱节点
    //rightType=0指向右子树，1表示指向后继节点
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

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

    /*@Override
    public String toString() {
        return "TreeNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }*/

    @Override
    public String toString() {
        return "TreeNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", leftType=" + leftType +
                ", rightType=" + rightType +
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
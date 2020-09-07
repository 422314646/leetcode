package avl;

public class AVLTreeDemo {
    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        //int[] arr = {4,3,6,5,7,8};
        int[] arr = {10,12,8,9,7,6};
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        System.out.println("中序遍历");
        avlTree.infixOrder();

        System.out.println("没有平衡前的高度");
        System.out.println(avlTree.getRoot().height());
        System.out.println(avlTree.getRoot().leftHeight());
        System.out.println(avlTree.getRoot().rightHeight());
        System.out.println(avlTree.getRoot().value);
        System.out.println(avlTree.getRoot().left.left);
    }

}
//创建AVL
class AVLTree{
    private Node root;
    //查找要删除的点
    public Node search(int value){
        if (root == null){
            return null;
        } else {
            return root.search(value);
        }

    }

    //查找父节点
    public Node searchParent(int value){
        if (root == null){
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    //编写方法帮助删除两颗指数的情况
    //返回以删除节点为根节点的最小节点的值
    //删除这个最小节点的值(右子树)
    public int delRightTreeMin(Node node){
        Node target = node;
        while (target.left != null){
            target = target.left;
        }//循环控制找到最少的节点
        //删除节点同时记录节点
        deNode(target.value);
        return target.value;
    }
    //删除这个最大节点的值(左子树)
    public int delLeftTreeMax(Node node){
        Node target = node;
        while (target.right != null){
            target = target.right;
        }
        deNode(target.value);
        return target.value;
    }

    //删除节点
    public void deNode(int value){
        if (root == null){
            return;
        } else {
            //找到要删除点
            Node targetNode = search(value);
            if (targetNode == null){
                return;
            }
            //如果要删除的节点就是根节点
            if (root.left == null && root.right == null){
                root = null;
                return;
            }
            //找到要删除节点的父节点
            Node parentNode = searchParent(value);
            //第一种情况如果删除的节点是叶子节点
            if (targetNode.right == null && targetNode.left == null){
                if (parentNode.left != null && parentNode.left.value == value){
                    parentNode.left = null;
                } else if (parentNode.right != null && parentNode.right.value == value){
                    parentNode.right = null;
                }
                //第三种情况有二棵子树
            } else if (targetNode.right != null && targetNode.left != null){
                int minVal = delLeftTreeMax(targetNode.left);
                targetNode.value = minVal;
                //第二种情况只有一棵子树
            } else {
                //左子树不为空
                if (targetNode.left != null){
                    if (parentNode != null){
                        if (parentNode.left.value == value){
                            parentNode.left = targetNode.left;
                        } else {
                            parentNode.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else if (targetNode.right != null){
                    if (parentNode != null){
                        if (parentNode.left.value == value){
                            parentNode.left = targetNode.right;
                        } else {
                            parentNode.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }


        }

    }

    //添加节点
    public void add(Node node){
        if (root == null){
            root = node;
        } else {
            root.add(node);
        }
    }

    //遍历方法
    public void infixOrder(){
        if (root != null){
            root.infixOrder();
        } else {
            System.out.println("二叉排序是为空");
        }
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}

//创建节点
class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //左旋转
    private void leftRotate(){
        //创建新节点，以当前根节点的值
        Node newNode = new Node(this.value);
        //把新节点的左子树设置成当前节点的左子树
        newNode.left = this.left;
        //把新节点的右子树设置为当前节点的右子树的左子树
        newNode.right = this.right.left;
        //把当前节点的值替换成右子树节点的值
        this.value = this.right.value;
        //把当前节点的右子树设置成右子树的右子树
        this.right = this.right.right;
        //把当前的节点的左子树设置成新的节点
        this.left = newNode;
    }

    //右旋转
    private void rightRotate(){
        Node newNode = new Node(this.value);
        newNode.right = this.right;
        newNode.left = this.left.right;
        this.value = left.value;
        this.left = this.left.left;
        this.right = newNode;
    }

    //返回左子树高度
    public int leftHeight(){
        if (this.left == null){
            return 0;
        }
        return this.left.height();
    }
    //返回右子树高度
    public int rightHeight(){
        if (this.right == null){
            return 0;
        }
        return this.right.height();
    }

    //当前节点的高度
    public int height(){
        return Math.max(this.left == null ? 0 : this.left.height(), this.right == null ? 0 : this.right.height()) + 1;
    }

    //查找要删除的点
    public Node search(int value){
        if (this.value == value){
            return this;
        } else if (value < this.value){
            if (this.left == null){
                return null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null){
                return null;
            }
            return this.right.search(value);
        }

    }

    //查找要删除节点的父节点
    public Node searchParent(int value){
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)){
            return this;
        } else if (value < this.value && this.left != null){
            return this.left.searchParent(value);
        } else if (value >= this.value && this.right != null){
            return this.right.searchParent(value);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
    //递归添加节点
    public void add(Node node){
        if (node == null){
            return;
        }
        if (node.value < this.value){
            if (this.left == null){
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null){
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
        //添加完了，判断是否右子树-左子树>1,左旋转
        if (rightHeight() - leftHeight() > 1){
            if (right != null && this.left.leftHeight() > this.right.rightHeight()){
                this.rightRotate();
                leftRotate();
            } else {
                leftRotate();
            }
            return;
        }

        //添加完一个节点后，如果左子树-右子树>1
        if (leftHeight() - rightHeight() > 1){
            //如果它的左子树的右子树高度大于它的左子树高度
            if (left != null && this.left.rightHeight() > this.left.leftHeight()){
                this.left.leftRotate();
                rightRotate();
            } else {
                rightRotate();
            }
        }
    }

    //中序遍历
    public void infixOrder(){
        if (this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this.value);
        if (this.right != null){
            this.right.infixOrder();
        }
    }
}
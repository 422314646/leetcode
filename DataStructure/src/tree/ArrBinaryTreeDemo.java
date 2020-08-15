package tree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.postOrder();
    }

}

//编写一个ArrayBinaryTree，实现顺序存储二叉树遍历
class ArrBinaryTree{
    private int[] arr;  //存储数据节点的数组

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载
    public void preOrder(){
        this.preOrder(0);
    }
    public void infixOrder(){
        this.infixOrder(0);
    }
    public void postOrder(){
        this.postOrder(0);
    }
    //编写一个方法，完成顺序存储二叉树的前序遍历
    private void preOrder(int index){
        if (arr == null || arr.length == 0){
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //输出元素
        System.out.print(arr[index]+", ");
        //向左递归
        if ((index * 2 + 1) < arr.length) {
            preOrder(index * 2 + 1);
        }
        //向又递归
        if ((index * 2 + 2) < arr.length){
            preOrder(index * 2 + 2);
        }
    }

    //编写中序排序
    private void infixOrder(int index){
        if (arr == null || arr.length == 0){
            System.out.println("数组为空，不能进行中序排序");
        }
        if ((index * 2 + 1) < arr.length) {
            infixOrder(index * 2 + 1);
        }
        System.out.print(arr[index] + ", ");
        if ((index * 2 + 2) < arr.length) {
            infixOrder(index * 2 + 2);
        }
    }
    //编写后序排序
    private void postOrder(int index){
        if (arr == null || arr.length == 0){
            System.out.println("数组为空，不能进行后序排序");
        }
        if ((index * 2 + 1) < arr.length) {
            postOrder(index * 2 + 1);
        }
        if ((index * 2 + 2) < arr.length) {
            postOrder(index * 2 + 2);
        }
        System.out.print(arr[index] + ", ");
    }
}

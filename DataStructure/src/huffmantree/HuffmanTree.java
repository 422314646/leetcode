package huffmantree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        //Arrays.sort(arr);
        preOder(createHuffmanTree(arr));
        System.out.println("===================");
        indexOder(createHuffmanTree(arr));

    }

    //前序遍历
    public static void preOder(Node root){
        if (root != null){
            root.preOder();
        } else {
            System.out.println("此树为空");
        }
    }

    //中序排序
    public static void indexOder(Node root){
        if (root != null){
            root.indexOder();
        } else {
            System.out.println("此树为空");
        }
    }

    //后序排序
    public static void postOder(Node root){
        if (root != null){
            root.postOder();
        } else {
            System.out.println("此树为空");
        }
    }

    public static Node createHuffmanTree(int[] arr){
        //1.遍历arr，存入list里面 同时按从小到大排序
        List<Node> nodes = new ArrayList<>();
        for (int value: arr){
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1){
            //按从小到大排序
            Collections.sort(nodes);
            //取出权值最小的2个节点
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //移除已经使用的权值最少的2个最小的节点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}

//为了实现Node对象持续排序Collections集合排序实现Compareble接口

class Node implements Comparable<Node>{
   int value;
   Node left;
   Node right;

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        //从小到大排序
        return this.value - o.value;
        /*
        *从大到小排序
        * —(this.value - o.value)
        * */
    }

    //前序遍历
    public void preOder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOder();
        }
        if (this.right != null){
            this.right.preOder();
        }
    }

    //中序排序
    public void indexOder(){
        if (this.left != null){
            this.left.preOder();
        }
        System.out.println(this);
        if (this.right != null){
            this.right.preOder();
        }
    }

    //后序排序
    public void postOder(){
        if (this.left != null){
            this.left.postOder();
        }
        if (this.right != null){
            this.right.postOder();
        }
        System.out.println(this);
    }

}

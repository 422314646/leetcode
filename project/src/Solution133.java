import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.*;

public class Solution133 {
    private HashMap<Node, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {
       //广度优先遍历
        if (node == null){
            return node;
        }
        HashMap<Node, Node> visited = new HashMap<>();
        visited.put(node, new Node(node.val, new ArrayList()));
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);
        while (!queue.isEmpty()){
            Node tmp = queue.poll();
            for (Node neighbor: tmp.neighbors) {
                if (!visited.containsKey(neighbor)){
                    visited.put(neighbor, new Node(neighbor.val, new ArrayList()));
                    queue.add(neighbor);
                }
                visited.get(tmp).neighbors.add(visited.get(neighbor));
            }
        }
        return visited.get(node);
    }

    public Node cloneGraph1(Node node){
        //深度优先遍历
        if (node == null){
            return node;
        }
        if (map.containsKey(node)){
            return map.get(node);
        }

        Node cloneNode = new Node(node.val, new ArrayList());
        map.put(node, cloneNode);
        for (Node neighbor: node.neighbors) {
            cloneNode.neighbors.add(cloneGraph1(neighbor));
        }
        return cloneNode;
    }

}
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
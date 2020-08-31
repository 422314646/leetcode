package huffmancode;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {

        String srcFile = "d://2(11).bmp";
        String dstFile = "d://2.zip";
        //zipFile(srcFile,dstFile);
        unZipFile(dstFile,srcFile);
        System.out.println("压缩成功");
        /*String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println("contentBytes= " +Arrays.toString(contentBytes));
        System.out.println(contentBytes.length); //40

        byte[] res = huffmanZip(contentBytes);
        System.out.println("res= " + Arrays.toString(res));

        byte[] res1 = decode(huffmanCodes,res);
        System.out.println("未压缩 = " + content);
        System.out.println("解码结果 = " + new String(res1));*/

        /*List<Node> nodes = getNodes(contentBytes);
        System.out.println("nodes= " + nodes);
        //创建huffman
        System.out.println("哈夫曼树");
        Node nodeHead = createHuffmanTree(nodes);
        System.out.println("前序遍历");
        //preOrder(nodeHead);
        nodeHead.preOrder();

        //测试哈夫曼树编码
        Map<Byte, String> huffmanCodes = getCodes(nodeHead);
        System.out.println("哈夫曼表" + huffmanCodes);

        byte[] huffmanCodeByte = zip(contentBytes, huffmanCodes);
        System.out.println("huffmanCodeByte= " + Arrays.toString(huffmanCodeByte));*/


    }

    //实现对一个文件的压缩也解码
    //压缩文件
    /*
    * srcFile 原始文件
    * dstFile 压缩过的文件
    * */
    public static void zipFile(String srcFile, String dstFile){
        //创建文件输入流
        FileInputStream is = null;
        //创建文件输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            //创建文件输入流
            is = new FileInputStream(srcFile);
            //创建一个和源文件一样大小的byte[]
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //System.out.println(b.length);
            //直接对源文件进行压缩
            byte[] huffmanBytes = huffmanZip(b);
            //System.out.println(huffmanBytes.length);
            //创建文件输出流,存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件相关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //把哈夫曼树压缩过后的数组写入其中
            oos.writeObject(huffmanBytes);
            //写入哈夫曼树编码不然后面无法解码
            oos.writeObject(huffmanCodes);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                is.close();
                os.close();
                oos.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //文件解压
    public static void unZipFile(String zipFile, String dstFile){
        //定义文件输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义一个输出流
        OutputStream os = null;
        try {
            //创建文件流
            is = new FileInputStream(zipFile);
            //创建一个 和is相关联对像输入流
            ois = new ObjectInputStream(is);
            //读取byte数组 huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取哈夫曼树编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            //解压
            byte[] res = decode(huffmanCodes, huffmanBytes);
            //将文件数组写入到目的文件
            os = new FileOutputStream(dstFile);
            //写入数组到disFile文件
            os.write(res);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    //解码
    //编写一个方法，完成对压缩数据的解码
    /*
    *huffmanCodes 哈夫曼编码表
    *huffmanBytes 需要解码的哈夫曼字节数组
    * */
    private static byte[] decode(Map<Byte,String> huffmanCodes, byte[] huffmanBytes){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
        //把字符串安装指定的哈夫曼树进行解码
        //把哈夫曼树表进行调换，a=100 变成 100=a
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry: huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        //创建要给集合，存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length();) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag){
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null){
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        byte[] b = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            b[i] = list.get(i);
        }
        return b;
    }
    /*
    * 将一个byte转成一个二进制的字符串
    * */
    private static String byteToBitString(boolean flag, byte b){
        //使用变量保存b
        int temp = b; //将b转成int
        //如果是正数我们还存在补高位
        if (flag){
            temp |= 256;//按位与256
        }
        String str = Integer.toBinaryString(temp); //返回的是temp对应的二进制补码
        //按照补码返回的
        if (flag){
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    //封装压缩代码
    private static byte[] huffmanZip(byte[] contentBytes){
        //创建NodeList
        List<Node> nodes = getNodes(contentBytes);
        //创建huffmanTree
        Node nodeHead = createHuffmanTree(nodes);
        //生成huffmanTree编码表
        Map<Byte, String> huffmanCodes = getCodes(nodeHead);
        //压缩成相应的字节码文件
        byte[] huffmanCodeByte = zip(contentBytes, huffmanCodes);

        return huffmanCodeByte;
    }

    //编写一个方法，将字符串对应的byte[]数组，通过生成的哈夫曼树编码表，返回一个哈夫曼树编码压缩后的byte[]
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes){
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b: bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //将二进制转为10进制保存在byte[]
        int len;
        if (stringBuilder.length() % 8 == 0){
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //创建存储压缩byte数组
        byte[] by = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()){
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            by[index] = (byte)Integer.parseInt(strByte, 2);
            index++;
        }
        return by;
    }

    private static Map<Byte, String> getCodes(Node root){
        if (root == null){
            return null;
        } else {
            //先处理左子节点
            getCodes(root.left, "0", stringBuilder);
            //处理右子节点
            getCodes(root.right, "1", stringBuilder);
            return huffmanCodes;
        }
    }

    private static List<Node> getNodes(byte[] bytes){
        //创建list
        ArrayList<Node> nodes = new ArrayList<>();
        //遍历byte，统计每一个byte出现的次数
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes){
            Integer count = counts.get(b);
            if (count == null){
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }

        //把每一个键值转化成Node对象，加入nodes集合
        for (Map.Entry<Byte, Integer> entry: counts.entrySet()){
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }

        return nodes;
    }

    private static Node createHuffmanTree(List<Node> nodes){
        while (nodes.size() > 1){
            Collections.sort(nodes);
            //取出最小的
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    //生成哈夫曼树对应的哈夫曼编码
    //将哈夫曼编码表存放在map<byte,string>
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    //生成的哈夫曼编码需要去拼接路径，定义一个StringBuilder存储叶子节点路径
    static StringBuilder stringBuilder = new StringBuilder();

    /*
    * 将传入的node节点所有的叶子节点的哈夫曼树编码得到，并放入到huffmantree集合
    * node 传入节点
    * code 路径：左节点是0， 右节点1
    * stringBuilder 用于拼接路径
    * */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder){
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null){
            //判断当前node是叶子节点还是非叶子节点
            if (node.data == null){
                //先处理左子节点
                getCodes(node.left, "0", stringBuilder2);
                //处理右子节点
                getCodes(node.right, "1", stringBuilder2);
            } else {
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    private static void preOrder(Node node){
        if (node != null){
            node.preOrder();
        } else {
            System.out.println("哈夫曼树为空！");
        }
    }
}

//创建Node
class Node implements Comparable<Node>{
    Byte data; //存放数据本身比如 ‘a’ = 97 ‘ ’ = 32
    int weight; //权值出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }


    @Override
    public int compareTo(Node o) {
        //从小到大排序
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }
}

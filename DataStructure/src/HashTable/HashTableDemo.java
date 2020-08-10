package HashTable;
import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);

        //写一个菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("add: 添加");
            System.out.println("delete: 删除");
            System.out.println("list: 显示");
            System.out.println("find: 查找");
            System.out.println("exit: 退出");
            key = scanner.next();
            switch (key){
                case "delete":
                    System.out.println("输入要删除的id");
                    int id = scanner.nextInt();
                    hashTab.delete(id);
                    break;
                case "add":
                    System.out.println("输入id");
                    id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTab.addByOrder(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

//创建HashTab管理多条链表
class HashTab{
    private EmpLinkedList[] empLinkedListArray;
    private int size;

    //构造器
    public HashTab(int size) {
       this.size = size;
       //初始化empLinkedListArray
        empLinkedListArray = new EmpLinkedList[size];
        //每一个链表都需要初始化
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp){
        //根据id，得到该节点应该添加到那一条链表
        int empLinkedListNo = hashFun(emp.id);
        //将emp添加到对应的链表中
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    //添加节点不重复顺序
    public void addByOrder(Emp emp){
        int empLinkedListNo = hashFun(emp.id);
        empLinkedListArray[empLinkedListNo].add1(emp);
    }

    //删除节点
    public void delete(int id){
        int empLinkedListNo = hashFun(id);
        empLinkedListArray[empLinkedListNo].delete(id);

    }

    //遍历所有的链表
    public void list(){
        for (int i = 0; i < size; i++){
            empLinkedListArray[i].list(i);
        }
    }

    //根据id查找节点
    public void findEmpById(int id){
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNo].findEmpById(id);
        if (emp != null){
            System.out.printf("在第"+empLinkedListNo+"找到该节点  节点id = %d\n",id);
        }else {
            System.out.println("没有该节点");
        }
    }

    //编写散列函数，使用一个简单的去模法
    public int hashFun(int id){
        return id % size;
    }

}

//表示一个雇员
class Emp{
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class EmpLinkedList{
    //头指针，默认为空
    private Emp head;

    //添加雇员到链表
    public void add(Emp emp){
        if (head == null){
            head = emp;
            return;
        }
        //如果不是第一个节点，则需要使用一个辅助指针
        Emp curEmp = head;
        while (true){
            if (curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    //按顺序添加
    public void add1(Emp emp){
        if (head == null){
            head = emp;
            return;
        }
        Emp curEmp = head;
        if (emp.id < head.id){
            emp.next = head;
            head = emp;
        }else if (emp.id == curEmp.id){
            System.out.println("请勿重复添加");
        }else {
            while (true){
                if (curEmp.next == null){
                    break;
                }else if (curEmp.next.id > emp.id){
                   break;
                }
                curEmp = curEmp.next;
            }
            emp.next = curEmp.next;
            curEmp.next = emp;
        }
    }

    //删除相应id节点
    public void delete(int id){
        if (head == null){
            System.out.println("删除失败，该链表为空");
            return;
        }
        if (head.id == id){
            head = head.next;
            System.out.println("删除成功");
        }else {
            boolean flag;
            Emp prev = head;
            while (true){
                if (prev.next == null){
                    flag = false;
                    break;
                }else if (prev.next.id == id){
                    flag = true;
                    break;
                }
                prev = prev.next;
            }
            if (flag){
                prev.next = prev.next.next;
                System.out.println("删除成功");
            }else {
                System.out.println("没有该节点删除失败");
            }
        }

    }

    //遍历链表
    public void list(int no){
        if (head == null){
            System.out.println("第"+ no +"链表信息为：");
            return;
        }
        System.out.print("第"+ no +"链表信息为：");
        Emp curEmp = head;
        while (true){
            System.out.printf("=> id = %d name = %s\t", curEmp.id,curEmp.name);
            if (curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    //根据id查找雇员
    public Emp findEmpById(int id){
        if (head == null){
            System.out.println("链表为空");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while (true){
            if (curEmp.id == id){
                break;
            }
            if (curEmp.next == null){
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }
}

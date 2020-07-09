package sparseArray;

import java.io.*;

/*稀疏二维数组*/
public class SparseArray {

    public static void main(String[] args) {
        //创建原始二维数组11*11
        //0表示没有棋子，1表示黑子，2表示蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        int sum = 0;
        System.out.println("原始二维数组");
        for (int[] row: chessArr1){
            for (int data: row){
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //二维数组转稀疏数组
        //1.先遍历二维数组看有多少数据
        for (int i = 0; i < 11; i++){
            for (int j = 0; j < 11; j++){
                if (chessArr1[i][j] != 0){
                    sum++;
                }
            }
        }

        //2.创建对应的稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        int count = 0;//判断有多少个非零数据
        for (int i = 0; i < 11; i++){
            for (int j = 0; j < 11; j++){
                if (chessArr1[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        //输出稀疏数组的形式
        System.out.println();
        System.out.println("稀疏数组如下");
        for (int i = 0; i < sparseArr.length; i++){
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }

        //将稀疏数组还原到原始数组
        //1.先读取稀疏数组第一行，根据第一行的数据，创建原始的二维数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];

        //2.在读取稀疏数组后几行的数据(从第二行开始)，并赋值给原始的二维数组即可
        for (int i = 1; i < sparseArr.length; i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        //输出恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组");

        for (int[] row: chessArr1){
            for (int data: row){
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        System.out.println("---------------------------------------");
        System.out.println("---------------------------------------");

        //将稀疏数组存入磁盘中
        System.out.println("存入D:\\map.data");
        try {
            File file = new File("D:\\map.data");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream, "UTF-8");
            System.out.println("正在写入");
            for (int i = 0; i < sparseArr.length; i++){
                writer.write(sparseArr[i][0] + "," + sparseArr[i][1] + "," + sparseArr[i][2]+",");
            }
            writer.close();
            fileOutputStream.close();
            System.out.println("写入成功");
            //读取文件
            System.out.println("读取文件D:\\map.data");
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,"UTF-8");
            StringBuffer stringBuffer = new StringBuffer();
            while (inputStreamReader.ready()){
                stringBuffer.append((char) inputStreamReader.read());
            }
            fileInputStream.close();
            inputStreamReader.close();
            System.out.println("读取成功");
            String string = stringBuffer.toString();
            String[] strings = string.split(",");
            System.out.printf("磁盘读取的字符串为:\n%s\n",string);
            //恢复二维稀疏数组
            int sum1 = 0;
            int sparseArr1[][] = new int[strings.length / 3][3];
            sparseArr1[0][0] = Integer.parseInt(strings[0]);
            sparseArr1[0][1] = Integer.parseInt(strings[1]);
            sparseArr1[0][2] = Integer.parseInt(strings[2]);
            for (int i = 3; i < strings.length; i += 3){
                sum1++;
                sparseArr1[sum1][0] = Integer.parseInt(strings[i]);
                sparseArr1[sum1][1] = Integer.parseInt(strings[i + 1]);
                sparseArr1[sum1][2] = Integer.parseInt(strings[i + 2]);
            }
            System.out.println();
            System.out.println("还原后的稀疏二维数组：");
            for (int i = 0; i < sparseArr1.length; i++){
                System.out.printf("%d\t%d\t%d\t\n", sparseArr1[i][0], sparseArr1[i][1], sparseArr1[i][2]);
            }

            //稀疏数组还原二维数组
            int[][] chessArr3 = new int[sparseArr1[0][0]][sparseArr1[0][1]];
            for (int i = 1; i < sparseArr1.length; i++){
                chessArr3[sparseArr1[i][0]][sparseArr1[i][1]] = sparseArr1[i][2];
            }
            for (int[] row: chessArr3){
                for (int data: row){
                    System.out.printf("%d\t", data);
                }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}

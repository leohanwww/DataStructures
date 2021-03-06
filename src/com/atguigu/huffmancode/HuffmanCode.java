package com.atguigu.huffmancode;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
//        String src = "F://src.jpg";
//        String dst = "F://dst.zip";
//        zipFile(src,dst);
//        System.out.println("压缩成功");
        String src = "F://dst.zip";
        String dst = "F://src2.jpg";
        unZipFile(src,dst);
        System.out.println("解压成功");
//        String str = "i like like like java do you like a java";
//        //字符串转为byte数组
//        byte[] strBytes = str.getBytes();
//        System.out.println(strBytes.length); //40
//        byte[] huffmanCodesBytes = huffmanZip(strBytes);
//        System.out.println("huffmanCodeBytes:" + Arrays.toString(huffmanCodesBytes));
//        System.out.println("长度:" + huffmanCodesBytes.length);
////        byte b = -88;
////        String s = byteToBitString(true, b);
////        System.out.println(s); //10101000
//        byte[] decode = decode(huffmanCodes, huffmanCodesBytes);
//        System.out.println(new String(decode)); //i like like like java do you like a java

//        System.out.println(strBytes.length); //40
//        ArrayList<Node> list = getList(strBytes);
////        for (Node node : list) {
////            System.out.println(node);
////        }
//        Node root = createHuffmanTree(list);
//        System.out.println("前序遍历");
//        preOrder(root);
////        getCode(root,"",stringBuilder);
//        Map<Byte, String> huffmanCodes = getCode(root);
//        System.out.println("生成的赫夫曼编码表:" + huffmanCodes);
//        //测试stringBuilder:1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100
//        byte[] huffmanCodeBytes = zip(strBytes, huffmanCodes);
//        System.out.println("huffmanCodeBytes:" + Arrays.toString(huffmanCodeBytes));

    }

    //解压文件
    /**
     *
     * description
     * @param zipFile	准备解压的文件
     * @param dst	解压后放到哪里
     * @return void
    */
    public static void unZipFile(String zipFile,String dst){
        //创建对象流
        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            //读byte数组
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取赫夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            //解码
            byte[] decode = decode(huffmanCodes, huffmanBytes);
            //写入解压后的文件
            os = new FileOutputStream(dst);
            os.write(decode);
        } catch (Exception e) {
            e.printStackTrace();
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

    //将一个文件进行压缩
    /**
     * 读取文件-得到赫夫曼编码表-完成压缩
     * description
     *
     * @param src 要压缩文件的地址
     * @param dst 创建的压缩后的文件地址
     * @return void
     */
    public static void zipFile(String src, String dst) {
        FileInputStream is = null;
        FileOutputStream os = null;
        ObjectOutputStream oos = null;
        try {//创建输入流
            is = new FileInputStream(src);
            //创建一个和原文件大小一样的字节数组
            byte[] b = new byte[is.available()];
            //读文件
            is.read(b);
            //使用赫夫曼编码进行编码
            //获取对应的赫夫曼编码表
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件输出流
            os = new FileOutputStream(dst);
            oos = new ObjectOutputStream(os);
            //写入压缩后的字节数组
            oos.writeObject(huffmanBytes);
            //写入赫夫曼编码表,为了恢复文件时使用
            oos.writeObject(huffmanCodes);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                oos.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    //数据解码
    //将[-88, -65, -56, -65, -56, -65,...转成"11000010101001......"
    //将"11000010101001......"对照编码转成byte[]

    /**
     * 数据的解码
     * description
     *
     * @param huffmanCodes     map 赫夫曼编码表
     * @param huffmanCodeBytes 赫夫曼编码后的字节数组[-88,.....]
     * @return byte[] 原字符串字节数组
     */
    public static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanCodeBytes) {
        //用于接收拼接的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //获取huffmanCodeBytes中的字节
        for (int i = 0; i < huffmanCodeBytes.length; i++) {
            //最后一个字节不用补高位
            boolean flag = (i == huffmanCodeBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, huffmanCodeBytes[i]));
            //拼接到stringBuilder
        }
        HashMap<String, Byte> map = new HashMap<>();
        //反转huffmanCodes这个map,把k,v对调
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        List<Byte> list = new ArrayList<>();
        //遍历stringBuilder,按位扫描,扫描到就放到list,根据map转成string
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                //i不动,count走,直到匹配到字符
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    //没有匹配到
                    count++;
                } else {
                    //匹配到
                    flag = false;
                }
            }
            //循环结束把b加到列表中
            list.add(b);
            i += count;
        }
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;

    }

    /**
     * 将byte转成对应的二进制字符串
     * description
     *
     * @param b    需要转换的byte
     * @param flag 标识是否需要补高位,true表示已经是8位,不用补其8位,最后一个byte无需补高位
     * @return java.lang.String 该byte对应的二进制字符串,按补码返回
     */
    public static String byteToBitString(boolean flag, byte b) {
        int temp = b;
        if (flag) {
            temp |= 256;//按位与256 1 0000 0000 | 0000 0001 -> 1 0000 0001
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }

    }


    //封装几个方法

    /**
     * description
     *
     * @param strBytes 原始字符串对应的字节数组
     * @return byte[] 哈夫曼编码后的字节数组
     */
    public static byte[] huffmanZip(byte[] strBytes) {
        //获得节点列表
        ArrayList<Node> list = getList(strBytes);
        //创建huffman树
        Node root = createHuffmanTree(list);
        //生成的赫夫曼编码表:{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
        Map<Byte, String> huffmanCodes = getCode(root);
        //生成压缩后得到字节数组
        //huffmanCodeBytes:[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
        return zip(strBytes, huffmanCodes);
        //发送
    }


    /**
     * 将字符串数组byte[],生成赫夫曼编码后的byte[]
     * description
     *
     * @param bytes        原始字符串"i like like like java do you like a java"对应的byte[]
     * @param huffmanCodes 生成的霍夫曼编码map
     * @return byte[] 处理后的byte[]数组
     * 传入strBytes[],返回"10100011000...."对应的字符串数组
     * huffmanCodes[0] = 10101000 -> byte[10101000 -1 ->10100111(取反码)->(11011000)=-88]
     */
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //将原bytes[]转成huffman编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //System.out.println("测试stringBuilder:"+stringBuilder.toString());
        //return null;
        //将stringBuilder转成byte[]
        int len; //计算byte[] huffmanCodeBytes的长度
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //存储压缩后的byte[]数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        //遍历stringbuilder
        for (int i = 0; i < stringBuilder.length(); i += 8) { //每8位对应一个byte
            String substring;
            if (i + 8 > stringBuilder.length()) { //不够8位
                substring = stringBuilder.substring(i);
            } else {
                substring = stringBuilder.substring(i, i + 8);
            }
            //放入by中
            huffmanCodeBytes[index] = (byte) Integer.parseInt(substring, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    //生成huffman树对应的编码表
    //存放在map<Byte,String>
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    //32->01 97->100 100->11000.....
    //需要不停的拼接字符串0110011000....
    static StringBuilder stringBuilder = new StringBuilder();

    //重载getCode
    public static Map<Byte, String> getCode(Node root) {
        if (root == null) {
            return null;
        }
        getCode(root.left, "0", stringBuilder);
        getCode(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 将传入的node节点的叶子节点的huffman编码,存放到map中
     * description
     *
     * @param node          默认root
     * @param code          路径:左子节点0 右子节点1
     * @param stringBuilder 用于拼接路径
     * @return void
     */
    public static void getCode(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder); //用来拼接map里的string
        stringBuilder1.append(code); //拼接当前节点
        if (node.data == null) { //不是叶子节点,需要向左右递归
            getCode(node.left, "0", stringBuilder1);
            getCode(node.right, "1", stringBuilder1);
        } else { //是叶子节点,加入map
            huffmanCodes.put(node.data, stringBuilder1.toString());

        }
    }


    //前序遍历huffmanTree
    public static void preOrder(Node root) {
        if (root == null) {
            System.out.println("根节点为空,无法遍历");
            return;
        }
        root.preOrder();
    }

    /**
     * 将字节数组放到列表中
     * description
     *
     * @param bytes 要放入列表的字节数组
     * @return java.util.ArrayList<com.atguigu.huffmancode.Node>
     */
    public static ArrayList<Node> getList(byte[] bytes) {
        ArrayList<Node> nodes = new ArrayList<>();
        HashMap<Byte, Integer> map = new HashMap<>();
        //遍历列表,将b的出现次数存入map
        for (byte b : bytes) { //使用map{b,count}
            Integer count = map.get(b);
            if (count == null) {
                //一次都没有存放
                map.put(b, 1);
            } else {
                map.put(b, count + 1);
            }
        }
        //map构建好以后,创建node
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue());
            // 加入list
            nodes.add(node);
        }
        return nodes;
    }

    //创建huffman数
    public static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //排序
            Collections.sort(nodes);
            //取出第1,2个节点
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //创建1,2的父节点,没有data,只有weight
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}


//节点类
class Node implements Comparable<Node> {
    Byte data; // 节点数据 'a'=>97 ' '=>32
    int weight; //节点权值,字符出现的次数
    Node left; //指向左子节点
    Node right; //指向右子节点

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public Node() {

    }

    //前序遍历所以节点
    public void preOrder() {
        //输出当前节点
        System.out.println(this);
        //左递归遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //右递归遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    public int getData() {
        return data;
    }

    public void setData(byte data) {
        this.data = data;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Node node) {
        //从小到大进行排序
        return this.weight - node.weight;
    }
}

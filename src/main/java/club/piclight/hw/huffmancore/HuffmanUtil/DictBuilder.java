package club.piclight.hw.huffmancore.HuffmanUtil;

import java.util.*;

/**
 * 字典Builder的实现类
 */
public class DictBuilder {
    private String content;
    private Map<Character, String> charDict = new HashMap<>();

    /**
     * 统计字符权重，Key字符 Value权重
     *
     * @return 字符权重Map
     */
    private Map<Character, Integer> getCharWeight() {
        //统计字符权重
        Map<Character, Integer> charWeight = new HashMap<>();
        content.chars().forEach(i -> {
            Character tempChar = (char) i;
            charWeight.merge(tempChar, 1, Integer::sum);
        });
        return charWeight;
    }

    /**
     * 将权重Map全部提取建立Node，并返回一个List
     *
     * @param charWeight 字符权重Map
     * @return NodeList
     */
    private List<Node> mapNodeToListNode(Map<Character, Integer> charWeight) {
        List<Node> nodeList = new ArrayList<>();
        charWeight.forEach((key, value) -> {
            Node charNode = new Node();
            charNode.setCharacter(key);
            charNode.setWeight(value);
            nodeList.add(charNode);
        });
        return nodeList;
    }

    /**
     * 构建哈夫曼树
     *
     * @param nodeList 所有节点List
     * @return 返回根节点
     */
    private Node buildHuffmanTree(List<Node> nodeList) {
        while (nodeList.size() > 1) { //剩余多余两个节点
            nodeList.sort(Comparator.comparingInt(node -> ((Node) node).getWeight())); //将节点通过权重排序
            Node leftNode = nodeList.remove(0);
            Node rightNode = nodeList.remove(0);
            Node tempNode = new Node(leftNode, rightNode); //出栈List头（权重小）两个节点链接到一个节点
            nodeList.add(tempNode); //该节点回栈
        }
        return nodeList.get(0); //返回最后一个根节点
    }

    /**
     * 建立哈夫曼树字典
     *
     * @return Map Key:哈夫曼01编码 Value:字符
     */
    private Map<Character, String> buildHuffmanDict(Node rootNode, String huffmanCode) {
        if (rootNode != null) {
            if (rootNode.getLeft() == null && rootNode.getRight() == null) {
                Character character = rootNode.getCharacter();
                charDict.put(character, huffmanCode);
            }
            buildHuffmanDict(rootNode.getLeft(), huffmanCode + "0");
            buildHuffmanDict(rootNode.getRight(), huffmanCode + "1");
        }
        return charDict;
    }

    public DictBuilder loadContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * 构建哈夫曼字典
     *
     * @return
     */
    public Map<Character, String> build() {
        //统计字符权重
        Map<Character, Integer> charWeight = getCharWeight();

        //将权重字典哈希表转换到Node List
        List<Node> nodeList = mapNodeToListNode(charWeight);

        //建树
        buildHuffmanTree(nodeList);

        return buildHuffmanDict(nodeList.get(0), "");
    }

    /**
     * 节点内部类
     */
    class Node {
        private Node left; //左节点
        private Node right; //右节点
        private int weight; //权重
        private Character character; //字符

        public Node() {
        }

        public Node(Node left, Node right) {
            this.left = left;
            this.right = right;
            this.weight = left.getWeight() + right.getWeight();
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Character getCharacter() {
            return character;
        }

        public void setCharacter(Character character) {
            this.character = character;
        }
    }
}

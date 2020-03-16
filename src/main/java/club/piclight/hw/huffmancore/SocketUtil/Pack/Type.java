package club.piclight.hw.huffmancore.SocketUtil.Pack;

/**
 * 协议类型
 */
public enum Type {
    DICT(1, (byte) 17),
    CONTENT(2, (byte) 18);

    private int id; //类型id
    private byte head; //类型头（协议版本 + 数据类型）

    Type(int id, byte head) {
        this.id = id;
        this.head = head;
    }

    public int getId() {
        return id;
    }

    public byte getHead() {
        return head;
    }
}

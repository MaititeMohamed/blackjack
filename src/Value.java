public enum Value {
    ONE("one", (byte) 1 ),// Can hold Two values 1 and 11 but by default i give it 1
    TOW("two", (byte) 2) ,
    THREE("three",  (byte) 3) ,
    FOUR("four",  (byte) 4) ,
    FIVE("five",  (byte) 5) ,
    SIX("six",  (byte) 6) ,
    SEVEN("seven",  (byte) 7) ,
    EIGHT("eight",  (byte) 8) ,
    NINE("nine",  (byte) 9) ,
    TEN("ten", (byte) 10) ,
    JACK("Jack",  (byte) 10),
    QUEEN("Queen",  (byte) 10),
    KING("King",  (byte) 10);
    private final String name;
    private final byte value;

    Value(String name , byte value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public byte getCardGameVal() {
        return value;
    }

}
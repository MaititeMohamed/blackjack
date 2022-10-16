public enum Value {
    LAS("Las", (byte) 1 ),// Can hold Two values 1 and 11 but by default i give it 1
    DOS("Dos", (byte) 2) ,
    TRES("Tres",  (byte) 3) ,
    CUATRO("Cuatro",  (byte) 4) ,
    CINCO("Cinco",  (byte) 5) ,
    SEIS("Seis",  (byte) 6) ,
    SIETE("Siete",  (byte) 7) ,
    OCHO("Ocho",  (byte) 8) ,
    NUEVO("Nuevo",  (byte) 9) ,
    DIEZ("Diez", (byte) 10) ,
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
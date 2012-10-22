package edu.ucsb.cs.rpc.base;

public class DataObject {

    private String string;
    private int integer;
    private float decimal;
    private char character;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public int getInteger() {
        return integer;
    }

    public void setInteger(int integer) {
        this.integer = integer;
    }

    public float getDecimal() {
        return decimal;
    }

    public void setDecimal(float decimal) {
        this.decimal = decimal;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }
}

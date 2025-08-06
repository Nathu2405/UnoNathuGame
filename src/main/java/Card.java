package com.uno.uno_backend;

public class Card {
    public enum Color { RED, GREEN, BLUE, YELLOW }
    public enum Value {
        ONE("1"), TWO("2"), THREE("3"), FOUR("4"),
        FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"),
        SKIP("⊘"), REVERSE("\uD83D\uDD04"), DRAW_ONE("+1"), WILD("★");

        private final String display;

        Value(String display) {
            this.display = display;
        }

        @Override
        public String toString() {
            return display;
        }
    }

    private final Color color;
    private final Value value;

    public Card(Color color, Value value) {
        this.color = color;
        this.value = value;
    }

    public Color getColor() { return color; }
    public Value getValue() { return value; }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return (color == card.color && value == card.value);
    }

    @Override
    public int hashCode() {
        return 31 * color.hashCode() + value.hashCode();
    }

    @Override
    public String toString() {
        String colorStr = (color == null) ? "null" : color.toString();
        return colorStr + " " + value;
    }
}

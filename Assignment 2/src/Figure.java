public interface Figure {
    public void perfromMove(String command, Player player, int turnNumber);
    public boolean canPerformMove(String command, int turnNumber);
    Color getColor();
    Field getField();
    void setField(Field field);
    FieldNumber getFieldNumber();
    String getName();
    //boolean canEatKing(King king);
}

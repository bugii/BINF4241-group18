public interface Figure {
    public void perfromMove(String command, Player player, int turnNumber);
    public boolean canPerformMove(String command);
    Color getColor();
    Field getField();
    FieldNumber getFieldNumber();
    String getName();
}

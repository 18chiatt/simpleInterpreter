public interface Expression {
    public double getVal();
    public boolean needsReplacing(Replacement replacement);
    public void replace(Replacement replacement);
    public String toString();
    public void doReplacements();
    public boolean contains(IdE toCheck);

}

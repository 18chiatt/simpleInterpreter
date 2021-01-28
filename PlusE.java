import java.util.Objects;

public class PlusE implements Expression {
    private Expression lhs;
    private Expression rhs;

    public PlusE(Expression lhs, Expression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public double getVal() {
        return lhs.getVal() + rhs.getVal();
    }

    @Override
    public boolean needsReplacing(Replacement replacement) {
        return false;
    }

    @Override
    public void replace(Replacement replacement) {
        if(lhs.needsReplacing(replacement)){
            this.lhs = replacement.getToReplaceWith();
        }
        if(rhs.needsReplacing(replacement)){
            this.rhs = replacement.getToReplaceWith();
        }
        lhs.replace(replacement);
        rhs.replace(replacement);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlusE plusE = (PlusE) o;
        return Objects.equals(lhs, plusE.lhs) && Objects.equals(rhs, plusE.rhs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lhs, rhs);
    }

    public String toString(){
        return "( + " + lhs.toString() + " " + rhs.toString() + " )";
    }
}

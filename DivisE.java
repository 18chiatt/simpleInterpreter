import java.util.Objects;

public class DivisE implements Expression {
    private Expression lhs;
    private Expression rhs;

    public DivisE(Expression lhs, Expression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public double getVal() {
        return (lhs.getVal() / rhs.getVal());
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
    public String toString(){
        return "( / " + lhs.toString() + " " + rhs.toString() + " )";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DivisE divisE = (DivisE) o;
        return lhs.equals(divisE.lhs) && rhs.equals(divisE.rhs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lhs, rhs);
    }
}

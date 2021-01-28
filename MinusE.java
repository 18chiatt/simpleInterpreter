import java.util.Objects;

public class MinusE implements Expression{
    private Expression lhs;
    private Expression rhs;

    public MinusE(Expression lhs, Expression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public double getVal() {
        return lhs.getVal() - rhs.getVal();
    }

    @Override
    public boolean needsReplacing(Replacement replacement) {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MinusE minusE = (MinusE) o;
        return Objects.equals(lhs, minusE.lhs) && Objects.equals(rhs, minusE.rhs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lhs, rhs);
    }

    @Override
    public void replace(Replacement replacement) {
        if(lhs.needsReplacing(replacement)){
            lhs = replacement.getToReplaceWith();
        }
        if(rhs.needsReplacing(replacement)){
            rhs = replacement.getToReplaceWith();
        }
        lhs.replace(replacement);
        rhs.replace(replacement);
    }
    public String toString(){
        return "( - " + lhs.toString() + " " + rhs.toString() + " )";
    }
}

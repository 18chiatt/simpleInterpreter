import java.util.Objects;

public class MultE implements Expression {
    public MultE(Expression lhs, Expression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    private Expression lhs;
    private Expression rhs;

    @Override
    public double getVal() {
        return (lhs.getVal() * rhs.getVal());
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
        MultE multE = (MultE) o;
        return Objects.equals(lhs, multE.lhs) && Objects.equals(rhs, multE.rhs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lhs, rhs);
    }

    public String toString(){
        return "( * " + lhs.toString() + " " + rhs.toString() + " )";
    }

    @Override
    public void doReplacements() {
        lhs.doReplacements();
        rhs.doReplacements();
    }

    @Override
    public boolean contains(IdE toCheck) {
        return (lhs.contains(toCheck) || rhs.contains(toCheck));
    }
}

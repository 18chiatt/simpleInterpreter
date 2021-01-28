import java.util.Objects;

public class NumberE implements Expression {
    public NumberE(double value) {
        this.value = value;
    }

    private double value;

    @Override
    public double getVal() {
        return value;
    }

    @Override
    public boolean needsReplacing(Replacement replacement) {
        return false;
    }

    @Override
    public void replace(Replacement replacement) {
        return;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberE numberE = (NumberE) o;
        return  (Math.abs(numberE.value -value ) < .01);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public String toString(){
        return Double.toString(value);
    }
}

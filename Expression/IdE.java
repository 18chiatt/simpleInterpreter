package Expression;

import java.util.Objects;

public class IdE implements Expression {
    public IdE(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    String identifier;
    @Override
    public double getVal() {
        throw new CalculationException("un-bound identifier");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdE idE = (IdE) o;
        return identifier.equals(idE.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }

    @Override
    public boolean needsReplacing(Replacement replacement) {
        return replacement.getToReplaceID().getIdentifier().equals(identifier);
    }

    @Override
    public void replace(Replacement replacement) {
        //"".charAt(30);
    }

    @Override
    public String toString(){
        return identifier;
    }

    @Override
    public void doReplacements() {
        return;
    }

    @Override
    public boolean contains(IdE toCheck) {
        return (identifier.equals(toCheck.identifier));

    }

}

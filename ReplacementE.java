import java.util.Objects;

public class ReplacementE implements Expression {
    public ReplacementE(IdE toReplace, Expression toReplaceWith, Expression body) {
        this.toReplace = toReplace;
        this.toReplaceWith = toReplaceWith;
        this.body = body;
    }

    public IdE getToReplace() {
        return toReplace;
    }

    public Expression getToReplaceWith() {
        return toReplaceWith;
    }

    public Expression getBody() {
        return body;
    }

    private IdE toReplace;
    private Expression toReplaceWith;
    private Expression body;


    @Override
    public double getVal() {
        return body.getVal();
    }

    @Override
    public boolean needsReplacing(Replacement replacement) {
        return false;
    }

    @Override
    public void replace(Replacement replacement) {
        if(toReplace.getIdentifier().equals(replacement.getToReplaceID().identifier)){
            if(toReplaceWith.needsReplacing(replacement)){
                toReplaceWith = replacement.getToReplaceWith();
            }
            toReplaceWith.replace(replacement);
            return;
        }
        if(toReplaceWith.needsReplacing(replacement)){
            toReplaceWith = replacement.getToReplaceWith();
        }
        if(body.needsReplacing(replacement)){
            body = replacement.getToReplaceWith();
        }
        body.replace(replacement);
        toReplaceWith.replace(replacement);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReplacementE that = (ReplacementE) o;
        return Objects.equals(toReplace, that.toReplace) && Objects.equals(toReplaceWith, that.toReplaceWith) && Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toReplace, toReplaceWith, body);
    }

    public String toString(){
        return "( with " + "([ " + toReplace.toString() + " " + toReplaceWith.toString() + " ]) " + body.toString() + " )";
    }

    @Override
    public void doReplacements() {
        Replacement newReplacement = new Replacement(toReplace,toReplaceWith);
        if(toReplaceWith.contains(toReplace)){
            throw new ParseException("Cyclic dependency " + toReplace.toString() + " Depends on Expression " + toReplaceWith.toString() + " which contains " + toReplace.toString());
        }
        if(body.needsReplacing(newReplacement)){
            body = newReplacement.getToReplaceWith();
            System.out.println("Replaced!");
            System.out.println(body.toString());
        } else {
            body.replace(newReplacement);
        }
        body.doReplacements();

    }

    @Override
    public boolean contains(IdE toCheck) {
        return (body.contains(toCheck) || toReplaceWith.contains(toCheck) || toReplace.contains(toCheck));
    }


}

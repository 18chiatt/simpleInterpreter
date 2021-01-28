public class Replacement {
    private IdE toReplaceID;

    public Replacement(IdE toReplaceID, Expression toReplaceWith) {
        this.toReplaceID = toReplaceID;
        this.toReplaceWith = toReplaceWith;
    }

    private Expression toReplaceWith;

    public IdE getToReplaceID() {
        return toReplaceID;
    }

    public Expression getToReplaceWith() {
        return toReplaceWith;
    }
}

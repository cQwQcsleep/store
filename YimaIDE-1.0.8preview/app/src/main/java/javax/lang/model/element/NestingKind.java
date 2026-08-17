package javax.lang.model.element;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public enum NestingKind {
    TOP_LEVEL,
    MEMBER,
    LOCAL,
    ANONYMOUS;

    public boolean isNested() {
        return this != TOP_LEVEL;
    }
}

package org.jetbrains.kotlin.metadata.deserialization;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public class Flags$BooleanFlagField extends Flags$FlagField<Boolean> {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "org/jetbrains/kotlin/metadata/deserialization/Flags$BooleanFlagField", "get"));
    }

    public Flags$BooleanFlagField(int i) {
        super(i, 1);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.jetbrains.kotlin.metadata.deserialization.Flags$FlagField
    public Boolean get(int i) {
        return Boolean.valueOf(((1 << this.offset) & i) != 0);
    }

    public int invert(int i) {
        return (1 << this.offset) ^ i;
    }

    @Override // org.jetbrains.kotlin.metadata.deserialization.Flags$FlagField
    public int toFlags(Boolean bool) {
        if (bool.booleanValue()) {
            return 1 << this.offset;
        }
        return 0;
    }
}

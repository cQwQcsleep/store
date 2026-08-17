package org.jetbrains.kotlin.metadata.deserialization;

import org.jetbrains.kotlin.protobuf.Internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public abstract class Flags$FlagField<E> {
    public final int bitWidth;
    public final int offset;

    private Flags$FlagField(int i, int i2) {
        this.offset = i;
        this.bitWidth = i2;
    }

    /* JADX WARN: Incorrect types in method signature: <E::Lorg/jetbrains/kotlin/protobuf/Internal$EnumLite;>(Lorg/jetbrains/kotlin/metadata/deserialization/Flags$FlagField<*>;[TE;)Lorg/jetbrains/kotlin/metadata/deserialization/Flags$FlagField<TE;>; */
    public static Flags$FlagField after(Flags$FlagField flags$FlagField, Internal.EnumLite[] enumLiteArr) {
        return new Flags$EnumLiteFlagField(flags$FlagField.offset + flags$FlagField.bitWidth, enumLiteArr);
    }

    public static Flags$BooleanFlagField booleanAfter(Flags$FlagField<?> flags$FlagField) {
        return new Flags$BooleanFlagField(flags$FlagField.offset + flags$FlagField.bitWidth);
    }

    public static Flags$BooleanFlagField booleanFirst() {
        return new Flags$BooleanFlagField(0);
    }

    /* JADX WARN: Incorrect types in method signature: <E::Lorg/jetbrains/kotlin/protobuf/Internal$EnumLite;>([TE;)Lorg/jetbrains/kotlin/metadata/deserialization/Flags$FlagField<TE;>; */
    public static Flags$FlagField first(Internal.EnumLite[] enumLiteArr) {
        return new Flags$EnumLiteFlagField(0, enumLiteArr);
    }

    public abstract E get(int i);

    public abstract int toFlags(E e);
}

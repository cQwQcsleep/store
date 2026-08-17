package org.jetbrains.kotlin.library.abi.impl;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.library.abi.AbiCompoundName;
import org.jetbrains.kotlin.metadata.deserialization.Flags$FlagField;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0013\b \u0018\u0000 \u0016*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0003\u0014\u0015\u0016B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00020\u0004H&¢\u0006\u0002\u0010\rJ\u0015\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0010J\u0010\u0010\u0012\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0004J\u0010\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004H\u0004R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u000e\u0010\u0011\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/library/abi/impl/FlagFieldEx;", "T", "", "offset", "", "bitWidth", "<init>", "(II)V", "getOffset", "()I", "getBitWidth", "get", "flags", "(I)Ljava/lang/Object;", "toFlags", "value", "(Ljava/lang/Object;)I", "bitMask", "readSignificantBitsFromFlags", "storeSignificantBitsAsFlags", "IntFlagFieldEx", "EnumFlagFieldEx", "Companion", "org.jetbrains.kotlin:kotlin-util-klib-abi"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class FlagFieldEx<T> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int bitMask;
    private final int bitWidth;
    private final int offset;

    public FlagFieldEx(int i, int i2) {
        this.offset = i;
        this.bitWidth = i2;
        if (i < 0) {
            v1f.a("Invalid offset: ", i);
            throw null;
        }
        if (i2 <= 0) {
            v1f.a("Invalid bit width: ", i2);
            throw null;
        }
        int i3 = (i + i2) - 32;
        if (i3 <= 0) {
            this.bitMask = (1 << i2) - 1;
            return;
        }
        throw new IllegalArgumentException(("Not enough bit space for storage. Offset=" + i + ", width=" + i2 + ", overflow=" + i3 + AbiCompoundName.SEPARATOR).toString());
    }

    public abstract T get(int flags);

    public final int getBitWidth() {
        return this.bitWidth;
    }

    public final int getOffset() {
        return this.offset;
    }

    public final int readSignificantBitsFromFlags(int flags) {
        return this.bitMask & (flags >>> this.offset);
    }

    public final int storeSignificantBitsAsFlags(int value) {
        if ((value >>> this.bitWidth) == 0) {
            return value << this.offset;
        }
        v1f.a("Not enough space to store ", value);
        return 0;
    }

    public abstract int toFlags(T value);

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u000b\u0018\u0000 \u0011*\u000e\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\u0011B\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\u000b\u001a\u00028\u00012\u0006\u0010\f\u001a\u00020\u0005H\u0016¢\u0006\u0002\u0010\rJ\u0015\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0010R\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\n¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/library/abi/impl/FlagFieldEx$EnumFlagFieldEx;", "E", "", "Lorg/jetbrains/kotlin/library/abi/impl/FlagFieldEx;", "offset", "", "entries", "", "<init>", "(I[Ljava/lang/Enum;)V", "[Ljava/lang/Enum;", "get", "flags", "(I)Ljava/lang/Enum;", "toFlags", "value", "(Ljava/lang/Enum;)I", "Companion", "org.jetbrains.kotlin:kotlin-util-klib-abi"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class EnumFlagFieldEx<E extends Enum<E>> extends FlagFieldEx<E> {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final E[] entries;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public EnumFlagFieldEx(int i, E[] eArr) {
            super(i, INSTANCE.computeBitWidth(eArr));
            eArr.getClass();
            this.entries = eArr;
        }

        @Override // org.jetbrains.kotlin.library.abi.impl.FlagFieldEx
        public E get(int flags) {
            return this.entries[readSignificantBitsFromFlags(flags)];
        }

        @Override // org.jetbrains.kotlin.library.abi.impl.FlagFieldEx
        public int toFlags(E value) {
            value.getClass();
            return storeSignificantBitsAsFlags(value.ordinal());
        }

        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J+\u0010\u0004\u001a\u00020\u0005\"\u000e\b\u0002\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00060\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00060\tH\u0002¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/library/abi/impl/FlagFieldEx$EnumFlagFieldEx$Companion;", "", "<init>", "()V", "computeBitWidth", "", "E", "", "entries", "", "([Ljava/lang/Enum;)I", "org.jetbrains.kotlin:kotlin-util-klib-abi"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final <E extends Enum<E>> int computeBitWidth(E[] entries) {
                if (!(entries.length == 0)) {
                    return Math.max(1, 32 - Integer.numberOfLeadingZeros(ArraysKt.getLastIndex(entries)));
                }
                w01.a("No enum entries");
                return 0;
            }

            private Companion() {
            }
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\u0006J \u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\u0007\u001a\u00020\u0006J \u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\u0007\u001a\u00020\u0006J!\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\f0\u0005\"\u0010\b\u0001\u0010\f\u0018\u0001*\b\u0012\u0004\u0012\u0002H\f0\rH\u0086\bJ-\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\f0\u0005\"\u0010\b\u0001\u0010\f\u0018\u0001*\b\u0012\u0004\u0012\u0002H\f0\r2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nH\u0086\bJ-\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\f0\u0005\"\u0010\b\u0001\u0010\f\u0018\u0001*\b\u0012\u0004\u0012\u0002H\f0\r2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0086\bR\u001c\u0010\u000f\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u000f\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0012¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/library/abi/impl/FlagFieldEx$Companion;", "", "<init>", "()V", "intFirst", "Lorg/jetbrains/kotlin/library/abi/impl/FlagFieldEx;", "", "bitWidth", "intAfter", "previous", "Lorg/jetbrains/kotlin/metadata/deserialization/Flags$FlagField;", "first", "E", "", "after", "nextOffset", "getNextOffset", "(Lorg/jetbrains/kotlin/metadata/deserialization/Flags$FlagField;)I", "(Lorg/jetbrains/kotlin/library/abi/impl/FlagFieldEx;)I", "org.jetbrains.kotlin:kotlin-util-klib-abi"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int getNextOffset(FlagFieldEx<?> flagFieldEx) {
            return flagFieldEx.getOffset() + flagFieldEx.getBitWidth();
        }

        public final /* synthetic */ <E extends Enum<E>> FlagFieldEx<E> after(Flags$FlagField<?> previous) {
            previous.getClass();
            int nextOffset = getNextOffset(previous);
            Intrinsics.reifiedOperationMarker(5, "E");
            return new EnumFlagFieldEx(nextOffset, new Enum[0]);
        }

        public final /* synthetic */ <E extends Enum<E>> FlagFieldEx<E> first() {
            Intrinsics.reifiedOperationMarker(5, "E");
            return new EnumFlagFieldEx(0, new Enum[0]);
        }

        public final FlagFieldEx<Integer> intAfter(Flags$FlagField<?> previous, int bitWidth) {
            previous.getClass();
            return new IntFlagFieldEx(getNextOffset(previous), bitWidth);
        }

        public final FlagFieldEx<Integer> intFirst(int bitWidth) {
            return new IntFlagFieldEx(0, bitWidth);
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int getNextOffset(Flags$FlagField<?> flags$FlagField) {
            return flags$FlagField.offset + flags$FlagField.bitWidth;
        }

        public final FlagFieldEx<Integer> intAfter(FlagFieldEx<?> previous, int bitWidth) {
            previous.getClass();
            return new IntFlagFieldEx(getNextOffset(previous), bitWidth);
        }

        public final /* synthetic */ <E extends Enum<E>> FlagFieldEx<E> after(FlagFieldEx<?> previous) {
            previous.getClass();
            int nextOffset = getNextOffset(previous);
            Intrinsics.reifiedOperationMarker(5, "E");
            return new EnumFlagFieldEx(nextOffset, new Enum[0]);
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/library/abi/impl/FlagFieldEx$IntFlagFieldEx;", "Lorg/jetbrains/kotlin/library/abi/impl/FlagFieldEx;", "", "offset", "bitWidth", "<init>", "(II)V", "get", "flags", "(I)Ljava/lang/Integer;", "toFlags", "value", "org.jetbrains.kotlin:kotlin-util-klib-abi"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class IntFlagFieldEx extends FlagFieldEx<Integer> {
        public IntFlagFieldEx(int i, int i2) {
            super(i, i2);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.jetbrains.kotlin.library.abi.impl.FlagFieldEx
        public Integer get(int flags) {
            return Integer.valueOf(readSignificantBitsFromFlags(flags));
        }

        @Override // org.jetbrains.kotlin.library.abi.impl.FlagFieldEx
        public /* bridge */ /* synthetic */ int toFlags(Integer num) {
            return toFlags(num.intValue());
        }

        public int toFlags(int value) {
            return storeSignificantBitsAsFlags(value);
        }
    }
}

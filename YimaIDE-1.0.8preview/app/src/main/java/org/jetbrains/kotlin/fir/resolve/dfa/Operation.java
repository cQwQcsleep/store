package org.jetbrains.kotlin.fir.resolve.dfa;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u0000¢\u0006\u0002\u0010\u000bJ\n\u0010\f\u001a\u00020\rH\u0096\u0080\u0004j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/Operation;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "EqTrue", "EqFalse", "EqNull", "NotEqNull", "valueIfKnown", Argument.Delimiters.none, "given", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/Operation;)Ljava/lang/Boolean;", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public enum Operation {
    EqTrue,
    EqFalse,
    EqNull,
    NotEqNull;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Operation.values().length];
            try {
                iArr[Operation.EqTrue.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Operation.EqFalse.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Operation.EqNull.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Operation.NotEqNull.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static EnumEntries<Operation> getEntries() {
        return $ENTRIES;
    }

    @Override // java.lang.Enum
    public String toString() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return "== True";
        }
        if (i == 2) {
            return "== False";
        }
        if (i == 3) {
            return "== Null";
        }
        if (i == 4) {
            return "!= Null";
        }
        bu8.a();
        return null;
    }

    public final Boolean valueIfKnown(Operation given) {
        given.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1 || i == 2) {
            if (given == NotEqNull) {
                return null;
            }
            return Boolean.valueOf(given == this);
        }
        if (i == 3) {
            return Boolean.valueOf(given == EqNull);
        }
        if (i == 4) {
            return Boolean.valueOf(given != EqNull);
        }
        bu8.a();
        return null;
    }
}

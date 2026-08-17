package org.jetbrains.kotlin.resolve.calls.inference.model;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\bJ\u0006\u0010\n\u001a\u00020\bJ\u0006\u0010\u000b\u001a\u00020\bJ\u0006\u0010\f\u001a\u00020\u0000j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/resolve/calls/inference/model/ConstraintKind;", "", "<init>", "(Ljava/lang/String;I)V", "LOWER", "UPPER", "EQUALITY", "isLower", "", "isUpper", "isEqual", "impliesLower", "opposite", "org.jetbrains.kotlin:resolution.common"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public enum ConstraintKind {
    LOWER,
    UPPER,
    EQUALITY;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    @Metadata(k = 3, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ConstraintKind.values().length];
            try {
                iArr[ConstraintKind.LOWER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ConstraintKind.UPPER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ConstraintKind.EQUALITY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static EnumEntries<ConstraintKind> getEntries() {
        return $ENTRIES;
    }

    public final boolean impliesLower() {
        return !isUpper();
    }

    public final boolean isEqual() {
        return this == EQUALITY;
    }

    public final boolean isLower() {
        return this == LOWER;
    }

    public final boolean isUpper() {
        return this == UPPER;
    }

    public final ConstraintKind opposite() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return UPPER;
        }
        if (i == 2) {
            return LOWER;
        }
        if (i == 3) {
            return EQUALITY;
        }
        bu8.a();
        return null;
    }
}

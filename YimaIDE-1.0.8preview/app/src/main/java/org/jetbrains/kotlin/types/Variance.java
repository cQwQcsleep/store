package org.jetbrains.kotlin.types;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jline.terminal.TerminalBuilder;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0011\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B)\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0000J\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0000J\u0006\u0010\u0017\u001a\u00020\u0000J\n\u0010\u0018\u001a\u00020\u0003H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/types/Variance;", "", "label", "", "allowsInPosition", "", "allowsOutPosition", "superpositionFactor", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;ZZI)V", "getLabel", "()Ljava/lang/String;", "getAllowsInPosition", "()Z", "getAllowsOutPosition", "INVARIANT", "IN_VARIANCE", "OUT_VARIANCE", "allowsPosition", "position", "superpose", "other", "opposite", "toString", "org.jetbrains.kotlin:language.model"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public enum Variance {
    INVARIANT("", true, true, 0),
    IN_VARIANCE("in", true, false, -1),
    OUT_VARIANCE(TerminalBuilder.PROP_OUTPUT_OUT, false, true, 1);

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final boolean allowsInPosition;
    private final boolean allowsOutPosition;
    private final String label;
    private final int superpositionFactor;

    @Metadata(k = 3, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Variance.values().length];
            try {
                iArr[Variance.IN_VARIANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Variance.OUT_VARIANCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Variance.INVARIANT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    Variance(String str, boolean z, boolean z2, int i) {
        this.label = str;
        this.allowsInPosition = z;
        this.allowsOutPosition = z2;
        this.superpositionFactor = i;
    }

    public static EnumEntries<Variance> getEntries() {
        return $ENTRIES;
    }

    public final boolean allowsPosition(Variance position) {
        position.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[position.ordinal()];
        if (i == 1) {
            return this.allowsInPosition;
        }
        if (i == 2) {
            return this.allowsOutPosition;
        }
        if (i == 3) {
            return this.allowsInPosition && this.allowsOutPosition;
        }
        bu8.a();
        return false;
    }

    public final boolean getAllowsInPosition() {
        return this.allowsInPosition;
    }

    public final boolean getAllowsOutPosition() {
        return this.allowsOutPosition;
    }

    public final String getLabel() {
        return this.label;
    }

    public final Variance opposite() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return OUT_VARIANCE;
        }
        if (i == 2) {
            return IN_VARIANCE;
        }
        if (i == 3) {
            return INVARIANT;
        }
        bu8.a();
        return null;
    }

    public final Variance superpose(Variance other) {
        other.getClass();
        int i = this.superpositionFactor * other.superpositionFactor;
        if (i == -1) {
            return IN_VARIANCE;
        }
        if (i == 0) {
            return INVARIANT;
        }
        if (i == 1) {
            return OUT_VARIANCE;
        }
        pu7.a("Illegal factor: ", i);
        return null;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.label;
    }
}

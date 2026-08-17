package org.jetbrains.kotlin.util;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\n\u001a\u00020\u00002\b\u0010\u000b\u001a\u0004\u0018\u00010\u0000H\u0086\u0002R\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\tj\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/util/CompilerType;", "", "<init>", "(Ljava/lang/String;I)V", "K1", "K2", "K1andK2", "isK2", "", "()Z", "plus", "other", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public enum CompilerType {
    K1,
    K2,
    K1andK2;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    @Metadata(k = 3, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CompilerType.values().length];
            try {
                iArr[CompilerType.K1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CompilerType.K2.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CompilerType.K1andK2.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static EnumEntries<CompilerType> getEntries() {
        return $ENTRIES;
    }

    public final boolean isK2() {
        return this == K2 || this == K1andK2;
    }

    public final CompilerType plus(CompilerType other) {
        if (other == null) {
            return this;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return other.isK2() ? K1andK2 : K1;
        }
        if (i == 2) {
            return (other == K1 || other == K1andK2) ? K1andK2 : K2;
        }
        if (i == 3) {
            return K1andK2;
        }
        bu8.a();
        return null;
    }
}

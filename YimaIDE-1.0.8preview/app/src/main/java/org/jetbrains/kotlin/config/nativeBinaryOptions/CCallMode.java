package org.jetbrains.kotlin.config.nativeBinaryOptions;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J3\u0010\b\u001a\u0004\u0018\u0001H\t\"\u0004\b\u0000\u0010\t2\u000e\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\t0\u000b2\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\t0\u000b¢\u0006\u0002\u0010\rj\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/config/nativeBinaryOptions/CCallMode;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "Indirect", "IndirectOrDirect", "DirectOrIndirect", "Direct", "select", "T", "indirect", "Lkotlin/Function0;", "direct", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "org.jetbrains.kotlin:binary-options"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public enum CCallMode {
    Indirect,
    IndirectOrDirect,
    DirectOrIndirect,
    Direct;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CCallMode.values().length];
            try {
                iArr[CCallMode.Indirect.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CCallMode.IndirectOrDirect.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CCallMode.DirectOrIndirect.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[CCallMode.Direct.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static EnumEntries<CCallMode> getEntries() {
        return $ENTRIES;
    }

    public final <T> T select(Function0<? extends T> indirect, Function0<? extends T> direct) {
        indirect.getClass();
        direct.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return (T) indirect.invoke();
        }
        if (i == 2) {
            T t = (T) indirect.invoke();
            return t == null ? (T) direct.invoke() : t;
        }
        if (i == 3) {
            T t2 = (T) direct.invoke();
            return t2 == null ? (T) indirect.invoke() : t2;
        }
        if (i == 4) {
            return (T) direct.invoke();
        }
        bu8.a();
        return null;
    }
}

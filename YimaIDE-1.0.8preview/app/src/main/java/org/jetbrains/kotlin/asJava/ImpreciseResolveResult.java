package org.jetbrains.kotlin.asJava;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\b0\nH\u0086\bø\u0001\u0000j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/asJava/ImpreciseResolveResult;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;I)V", "MATCH", "NO_MATCH", "UNSURE", "ifSure", "", "body", "Lkotlin/Function1;", "", "org.jetbrains.kotlin:light-classes-base"}, k = 1, mv = {2, 4, 0}, xi = 48)
public enum ImpreciseResolveResult {
    MATCH,
    NO_MATCH,
    UNSURE;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    @Metadata(k = 3, mv = {2, 4, 0}, xi = 176)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ImpreciseResolveResult.values().length];
            try {
                iArr[ImpreciseResolveResult.MATCH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ImpreciseResolveResult.NO_MATCH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ImpreciseResolveResult.UNSURE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static EnumEntries<ImpreciseResolveResult> getEntries() {
        return $ENTRIES;
    }

    public final void ifSure(Function1<? super Boolean, Unit> body) {
        body.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            body.invoke(Boolean.TRUE);
        } else if (i == 2) {
            body.invoke(Boolean.FALSE);
        } else {
            if (i == 3) {
                return;
            }
            bu8.a();
        }
    }
}

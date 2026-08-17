package org.jetbrains.kotlin.psi.stubs;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.psi.KtImplementationDetail;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\n\u0002\u0018\u0002\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005Ê\u0001\u0002\b\u0007¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/psi/stubs/KotlinModifierListStub$SpecialFlag;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;I)V", "MustUseReturnValue", "IgnorableReturnValue", "org.jetbrains.kotlin:psi-api", "Lorg/jetbrains/kotlin/psi/KtImplementationDetail;"}, k = 1, mv = {2, 4, 0}, xi = 48)
@KtImplementationDetail
public enum KotlinModifierListStub$SpecialFlag {
    MustUseReturnValue,
    IgnorableReturnValue;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<KotlinModifierListStub$SpecialFlag> getEntries() {
        return $ENTRIES;
    }
}

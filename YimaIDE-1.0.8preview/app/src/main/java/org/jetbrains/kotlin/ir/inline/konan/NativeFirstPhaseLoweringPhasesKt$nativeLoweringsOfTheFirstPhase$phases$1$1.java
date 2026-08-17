package org.jetbrains.kotlin.ir.inline.konan;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.backend.common.LoweringContext;
import org.jetbrains.kotlin.backend.common.lower.UpgradeCallableReferences;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final /* synthetic */ class NativeFirstPhaseLoweringPhasesKt$nativeLoweringsOfTheFirstPhase$phases$1$1 extends FunctionReferenceImpl implements Function1<LoweringContext, UpgradeCallableReferences> {
    public static final NativeFirstPhaseLoweringPhasesKt$nativeLoweringsOfTheFirstPhase$phases$1$1 INSTANCE = new NativeFirstPhaseLoweringPhasesKt$nativeLoweringsOfTheFirstPhase$phases$1$1();

    public NativeFirstPhaseLoweringPhasesKt$nativeLoweringsOfTheFirstPhase$phases$1$1() {
        super(1, NativeFirstPhaseLoweringPhasesKt.class, "createUpgradeCallableReferencesPhase", "createUpgradeCallableReferencesPhase(Lorg/jetbrains/kotlin/backend/common/LoweringContext;)Lorg/jetbrains/kotlin/backend/common/lower/UpgradeCallableReferences;", 1);
    }

    public final UpgradeCallableReferences invoke(LoweringContext loweringContext) {
        loweringContext.getClass();
        return NativeFirstPhaseLoweringPhasesKt.createUpgradeCallableReferencesPhase(loweringContext);
    }
}

package org.jetbrains.kotlin.ir.inline.konan;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.backend.common.LoweringContext;
import org.jetbrains.kotlin.backend.common.lower.UpgradeCallableReferences;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final /* synthetic */ class NativeFirstPhaseLoweringPhasesKt$upgradeCallableReferencesPhase$1 extends AdaptedFunctionReference implements Function1<LoweringContext, UpgradeCallableReferences> {
    public static final NativeFirstPhaseLoweringPhasesKt$upgradeCallableReferencesPhase$1 INSTANCE = new NativeFirstPhaseLoweringPhasesKt$upgradeCallableReferencesPhase$1();

    public NativeFirstPhaseLoweringPhasesKt$upgradeCallableReferencesPhase$1() {
        super(1, UpgradeCallableReferences.class, "<init>", "<init>(Lorg/jetbrains/kotlin/backend/common/LoweringContext;ZZZZZZZ)V", 0);
    }

    public final UpgradeCallableReferences invoke(LoweringContext loweringContext) {
        loweringContext.getClass();
        return new UpgradeCallableReferences(loweringContext, false, false, false, false, false, false, false, 254, (DefaultConstructorMarker) null);
    }
}

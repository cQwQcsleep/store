package org.jetbrains.kotlin.ir.inline;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.backend.common.LoweringContext;
import org.jetbrains.kotlin.backend.common.lower.VersionOverloadsLowering;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final /* synthetic */ class CommonLoweringPhasesKt$loweringsOfTheFirstPhase$1$2 extends FunctionReferenceImpl implements Function1<LoweringContext, VersionOverloadsLowering> {
    public static final CommonLoweringPhasesKt$loweringsOfTheFirstPhase$1$2 INSTANCE = new CommonLoweringPhasesKt$loweringsOfTheFirstPhase$1$2();

    public CommonLoweringPhasesKt$loweringsOfTheFirstPhase$1$2() {
        super(1, VersionOverloadsLowering.class, "<init>", "<init>(Lorg/jetbrains/kotlin/backend/common/LoweringContext;)V", 0);
    }

    public final VersionOverloadsLowering invoke(LoweringContext loweringContext) {
        loweringContext.getClass();
        return new VersionOverloadsLowering(loweringContext);
    }
}

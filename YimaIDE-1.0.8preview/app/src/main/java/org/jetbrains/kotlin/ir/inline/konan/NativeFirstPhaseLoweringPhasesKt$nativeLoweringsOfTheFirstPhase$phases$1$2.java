package org.jetbrains.kotlin.ir.inline.konan;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.backend.common.LoweringContext;
import org.jetbrains.kotlin.backend.konan.lower.NativeAssertionWrapperLowering;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final /* synthetic */ class NativeFirstPhaseLoweringPhasesKt$nativeLoweringsOfTheFirstPhase$phases$1$2 extends FunctionReferenceImpl implements Function1<LoweringContext, NativeAssertionWrapperLowering> {
    public static final NativeFirstPhaseLoweringPhasesKt$nativeLoweringsOfTheFirstPhase$phases$1$2 INSTANCE = new NativeFirstPhaseLoweringPhasesKt$nativeLoweringsOfTheFirstPhase$phases$1$2();

    public NativeFirstPhaseLoweringPhasesKt$nativeLoweringsOfTheFirstPhase$phases$1$2() {
        super(1, NativeAssertionWrapperLowering.class, "<init>", "<init>(Lorg/jetbrains/kotlin/backend/common/LoweringContext;)V", 0);
    }

    public final NativeAssertionWrapperLowering invoke(LoweringContext loweringContext) {
        loweringContext.getClass();
        return new NativeAssertionWrapperLowering(loweringContext);
    }
}

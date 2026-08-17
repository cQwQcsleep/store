package androidx.compose.material3.internal;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1 implements Function3<Transition.Segment<InputPhase>, Composer, Integer, FiniteAnimationSpec<Float>> {
    final /* synthetic */ FiniteAnimationSpec<Float> $fastOpacityTransitionSpec;

    public TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1(FiniteAnimationSpec<Float> finiteAnimationSpec) {
        this.$fastOpacityTransitionSpec = finiteAnimationSpec;
    }

    public final FiniteAnimationSpec<Float> invoke(Transition.Segment<InputPhase> segment, Composer composer, int i) {
        composer.startReplaceGroup(2126293195);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2126293195, i, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:426)");
        }
        FiniteAnimationSpec<Float> finiteAnimationSpec = this.$fastOpacityTransitionSpec;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return finiteAnimationSpec;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ FiniteAnimationSpec<Float> invoke(Transition.Segment<InputPhase> segment, Composer composer, Integer num) {
        return invoke(segment, composer, num.intValue());
    }
}

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
public final class TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1 implements Function3<Transition.Segment<InputPhase>, Composer, Integer, FiniteAnimationSpec<Float>> {
    final /* synthetic */ FiniteAnimationSpec<Float> $fastOpacityTransitionSpec;
    final /* synthetic */ FiniteAnimationSpec<Float> $slowOpacityTransitionSpec;

    public TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1(FiniteAnimationSpec<Float> finiteAnimationSpec, FiniteAnimationSpec<Float> finiteAnimationSpec2) {
        this.$fastOpacityTransitionSpec = finiteAnimationSpec;
        this.$slowOpacityTransitionSpec = finiteAnimationSpec2;
    }

    public final FiniteAnimationSpec<Float> invoke(Transition.Segment<InputPhase> segment, Composer composer, int i) {
        FiniteAnimationSpec<Float> finiteAnimationSpec;
        composer.startReplaceGroup(-984009111);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-984009111, i, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:404)");
        }
        InputPhase inputPhase = InputPhase.Focused;
        InputPhase inputPhase2 = InputPhase.UnfocusedEmpty;
        if (segment.isTransitioningTo(inputPhase, inputPhase2)) {
            finiteAnimationSpec = this.$fastOpacityTransitionSpec;
        } else {
            finiteAnimationSpec = (segment.isTransitioningTo(inputPhase2, inputPhase) || segment.isTransitioningTo(InputPhase.UnfocusedNotEmpty, inputPhase2)) ? this.$slowOpacityTransitionSpec : this.$fastOpacityTransitionSpec;
        }
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

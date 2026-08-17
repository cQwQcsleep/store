package androidx.compose.animation.core;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: Add missing generic type declarations: [S] */
/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 176)
public final class TransitionKt$animateValue$targetValue$1$1<S> implements Function0<S> {
    final /* synthetic */ Transition<S> $this_animateValue;

    public TransitionKt$animateValue$targetValue$1$1(Transition<S> transition) {
        this.$this_animateValue = transition;
    }

    public final S invoke() {
        return this.$this_animateValue.getTargetState();
    }
}

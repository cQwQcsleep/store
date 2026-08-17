package androidx.compose.material3;

import androidx.activity.BackEventCompat;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
@DebugMetadata(c = "androidx.compose.material3.SearchBarKt$SearchBar$3$1", f = "SearchBar.kt", i = {}, l = {559}, m = "invokeSuspend", n = {}, s = {})
public final class SearchBarKt$SearchBar$3$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Animatable<Float, AnimationVector1D> $animationProgress;
    final /* synthetic */ MutableState<BackEventCompat> $currentBackEvent;
    final /* synthetic */ boolean $expanded;
    final /* synthetic */ MutableFloatState $finalBackProgress;
    final /* synthetic */ MutableState<BackEventCompat> $firstBackEvent;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchBarKt$SearchBar$3$1(Animatable<Float, AnimationVector1D> animatable, boolean z, MutableFloatState mutableFloatState, MutableState<BackEventCompat> mutableState, MutableState<BackEventCompat> mutableState2, Continuation<? super SearchBarKt$SearchBar$3$1> continuation) {
        super(2, continuation);
        this.$animationProgress = animatable;
        this.$expanded = z;
        this.$finalBackProgress = mutableFloatState;
        this.$firstBackEvent = mutableState;
        this.$currentBackEvent = mutableState2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SearchBarKt$SearchBar$3$1(this.$animationProgress, this.$expanded, this.$finalBackProgress, this.$firstBackEvent, this.$currentBackEvent, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0080  */
    public final Object invokeSuspend(Object obj) {
        FiniteAnimationSpec finiteAnimationSpec;
        SearchBarKt$SearchBar$3$1 searchBarKt$SearchBar$3$1;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (((Number) this.$animationProgress.getValue()).floatValue() <= 0.0f || ((Number) this.$animationProgress.getValue()).floatValue() >= 1.0f) {
                finiteAnimationSpec = this.$expanded ? SearchBarKt.AnimationEnterFloatSpec : SearchBarKt.AnimationExitFloatSpec;
            } else {
                finiteAnimationSpec = SearchBarKt.AnimationPredictiveBackExitFloatSpec;
            }
            FiniteAnimationSpec finiteAnimationSpec2 = finiteAnimationSpec;
            float f = this.$expanded ? 1.0f : 0.0f;
            if (((Number) this.$animationProgress.getValue()).floatValue() != f) {
                Animatable<Float, AnimationVector1D> animatable = this.$animationProgress;
                Float fBoxFloat = Boxing.boxFloat(f);
                this.label = 1;
                searchBarKt$SearchBar$3$1 = this;
                if (Animatable.animateTo$default(animatable, fBoxFloat, finiteAnimationSpec2, (Object) null, (Function1) null, searchBarKt$SearchBar$3$1, 12, (Object) null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            if (!searchBarKt$SearchBar$3$1.$expanded) {
                searchBarKt$SearchBar$3$1.$finalBackProgress.setFloatValue(Float.NaN);
                searchBarKt$SearchBar$3$1.$firstBackEvent.setValue(null);
                searchBarKt$SearchBar$3$1.$currentBackEvent.setValue(null);
            }
            return Unit.INSTANCE;
        }
        if (i != 1) {
            k2d.a("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        searchBarKt$SearchBar$3$1 = this;
        if (!searchBarKt$SearchBar$3$1.$expanded) {
            searchBarKt$SearchBar$3$1.$finalBackProgress.setFloatValue(Float.NaN);
            searchBarKt$SearchBar$3$1.$firstBackEvent.setValue(null);
            searchBarKt$SearchBar$3$1.$currentBackEvent.setValue(null);
        }
        return Unit.INSTANCE;
    }
}

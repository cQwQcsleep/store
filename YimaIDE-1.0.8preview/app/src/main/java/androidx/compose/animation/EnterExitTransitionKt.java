package androidx.compose.animation;

import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.animation.core.VisibilityThresholdsKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.graphics.TransformOriginKt;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.compose.ui.graphics.colorspace.ColorSpaces;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000¬\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0080\u0004\u001a\u0015\u0010\u0000\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0002\u001a\u00020\u0003H\u0080\u0004\u001a\"\u0010\u0005\u001a\u00020\u00012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\bH\u0007\u001a\"\u0010\n\u001a\u00020\u00042\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\u000b\u001a\u00020\bH\u0007\u001a;\u0010\f\u001a\u00020\u00012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\r0\u00072!\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\r0\u000fH\u0007\u001a;\u0010\u0014\u001a\u00020\u00042\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\r0\u00072!\u0010\u0015\u001a\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\r0\u000fH\u0007\u001a3\u0010\u0016\u001a\u00020\u00012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\u0017\u001a\u00020\b2\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0007¢\u0006\u0004\b\u001a\u0010\u001b\u001a3\u0010\u001c\u001a\u00020\u00042\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\u001d\u001a\u00020\b2\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0007¢\u0006\u0004\b\u001e\u0010\u001f\u001a3\u0010 \u001a\u00020\u00012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020!0\u00072\b\b\u0002\u0010\"\u001a\u00020!2\b\b\u0002\u0010#\u001a\u00020$H\u0007¢\u0006\u0004\b%\u0010&\u001a3\u0010'\u001a\u00020\u00042\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020!0\u00072\b\b\u0002\u0010(\u001a\u00020!2\b\b\u0002\u0010#\u001a\u00020$H\u0007¢\u0006\u0004\b)\u0010*\u001aQ\u0010+\u001a\u00020\u00012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00100\u00072\b\b\u0002\u0010,\u001a\u00020-2\b\b\u0002\u0010.\u001a\u00020$2#\b\u0002\u0010/\u001a\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00100\u000fH\u0007\u001aQ\u00100\u001a\u00020\u00042\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00100\u00072\b\b\u0002\u00101\u001a\u00020-2\b\b\u0002\u0010.\u001a\u00020$2#\b\u0002\u00102\u001a\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00100\u000fH\u0007\u001aQ\u00103\u001a\u00020\u00012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00100\u00072\b\b\u0002\u0010,\u001a\u0002042\b\b\u0002\u0010.\u001a\u00020$2#\b\u0002\u00105\u001a\u001d\u0012\u0013\u0012\u001106¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(7\u0012\u0004\u0012\u0002060\u000fH\u0007\u001aQ\u00108\u001a\u00020\u00012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00100\u00072\b\b\u0002\u0010,\u001a\u0002092\b\b\u0002\u0010.\u001a\u00020$2#\b\u0002\u0010:\u001a\u001d\u0012\u0013\u0012\u001106¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(;\u0012\u0004\u0012\u0002060\u000fH\u0007\u001aQ\u0010<\u001a\u00020\u00042\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00100\u00072\b\b\u0002\u00101\u001a\u0002042\b\b\u0002\u0010.\u001a\u00020$2#\b\u0002\u0010=\u001a\u001d\u0012\u0013\u0012\u001106¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(7\u0012\u0004\u0012\u0002060\u000fH\u0007\u001aQ\u0010>\u001a\u00020\u00042\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00100\u00072\b\b\u0002\u00101\u001a\u0002092\b\b\u0002\u0010.\u001a\u00020$2#\b\u0002\u0010?\u001a\u001d\u0012\u0013\u0012\u001106¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(;\u0012\u0004\u0012\u0002060\u000fH\u0007\u001a=\u0010@\u001a\u00020\u00012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\r0\u00072#\b\u0002\u0010A\u001a\u001d\u0012\u0013\u0012\u001106¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(7\u0012\u0004\u0012\u0002060\u000fH\u0007\u001a=\u0010B\u001a\u00020\u00012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\r0\u00072#\b\u0002\u0010C\u001a\u001d\u0012\u0013\u0012\u001106¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(;\u0012\u0004\u0012\u0002060\u000fH\u0007\u001a=\u0010D\u001a\u00020\u00042\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\r0\u00072#\b\u0002\u0010E\u001a\u001d\u0012\u0013\u0012\u001106¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(7\u0012\u0004\u0012\u0002060\u000fH\u0007\u001a=\u0010F\u001a\u00020\u00042\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\r0\u00072#\b\u0002\u0010G\u001a\u001d\u0012\u0013\u0012\u001106¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(;\u0012\u0004\u0012\u0002060\u000fH\u0007\u001a\f\u0010H\u001a\u00020-*\u000204H\u0002\u001a\f\u0010H\u001a\u00020-*\u000209H\u0002\u001a,\u0010I\u001a\u0004\u0018\u0001HJ\"\b\b\u0000\u0010J*\u00020\u0003*\u00020\u00012\f\u0010K\u001a\b\u0012\u0004\u0012\u0002HJ0LH\u0080\u0002¢\u0006\u0002\u0010M\u001a,\u0010I\u001a\u0004\u0018\u0001HJ\"\b\b\u0000\u0010J*\u00020\u0003*\u00020\u00042\f\u0010K\u001a\b\u0012\u0004\u0012\u0002HJ0LH\u0080\u0002¢\u0006\u0002\u0010N\u001a?\u0010O\u001a\u00020P*\b\u0012\u0004\u0012\u00020R0Q2\u0006\u0010S\u001a\u00020\u00012\u0006\u0010T\u001a\u00020\u00042\u000e\b\u0002\u0010U\u001a\b\u0012\u0004\u0012\u00020$0V2\u0006\u0010W\u001a\u00020XH\u0001¢\u0006\u0002\u0010Y\u001a\u001f\u0010Z\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020R0Q2\u0006\u0010S\u001a\u00020\u0001H\u0001¢\u0006\u0002\u0010[\u001a\u001f\u0010\\\u001a\u00020\u0004*\b\u0012\u0004\u0012\u00020R0Q2\u0006\u0010T\u001a\u00020\u0004H\u0001¢\u0006\u0002\u0010]\u001a/\u0010^\u001a\u00020_*\b\u0012\u0004\u0012\u00020R0Q2\u0006\u0010S\u001a\u00020\u00012\u0006\u0010T\u001a\u00020\u00042\u0006\u0010W\u001a\u00020XH\u0003¢\u0006\u0002\u0010`\"\u001a\u0010a\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020c0bX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010d\u001a\b\u0012\u0004\u0012\u00020\b0eX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010f\u001a\b\u0012\u0004\u0012\u00020!0eX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010g\u001a\b\u0012\u0004\u0012\u00020\r0eX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010h\u001a\b\u0012\u0004\u0012\u00020\u00100eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006i²\u0006\n\u0010j\u001a\u00020\u0001X\u008a\u008e\u0002²\u0006\n\u0010k\u001a\u00020\u0004X\u008a\u008e\u0002"}, d2 = {"withEffect", "Landroidx/compose/animation/EnterTransition;", "effect", "Landroidx/compose/animation/TransitionEffect;", "Landroidx/compose/animation/ExitTransition;", "fadeIn", "animationSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "", "initialAlpha", "fadeOut", "targetAlpha", "slideIn", "Landroidx/compose/ui/unit/IntOffset;", "initialOffset", "Lkotlin/Function1;", "Landroidx/compose/ui/unit/IntSize;", "Lkotlin/ParameterName;", "name", "fullSize", "slideOut", "targetOffset", "scaleIn", "initialScale", "transformOrigin", "Landroidx/compose/ui/graphics/TransformOrigin;", "scaleIn-L8ZKh-E", "(Landroidx/compose/animation/core/FiniteAnimationSpec;FJ)Landroidx/compose/animation/EnterTransition;", "scaleOut", "targetScale", "scaleOut-L8ZKh-E", "(Landroidx/compose/animation/core/FiniteAnimationSpec;FJ)Landroidx/compose/animation/ExitTransition;", "unveilIn", "Landroidx/compose/ui/graphics/Color;", "initialColor", "matchParentSize", "", "unveilIn-bw27NRU", "(Landroidx/compose/animation/core/FiniteAnimationSpec;JZ)Landroidx/compose/animation/EnterTransition;", "veilOut", "targetColor", "veilOut-bw27NRU", "(Landroidx/compose/animation/core/FiniteAnimationSpec;JZ)Landroidx/compose/animation/ExitTransition;", "expandIn", "expandFrom", "Landroidx/compose/ui/Alignment;", "clip", "initialSize", "shrinkOut", "shrinkTowards", "targetSize", "expandHorizontally", "Landroidx/compose/ui/Alignment$Horizontal;", "initialWidth", "", "fullWidth", "expandVertically", "Landroidx/compose/ui/Alignment$Vertical;", "initialHeight", "fullHeight", "shrinkHorizontally", "targetWidth", "shrinkVertically", "targetHeight", "slideInHorizontally", "initialOffsetX", "slideInVertically", "initialOffsetY", "slideOutHorizontally", "targetOffsetX", "slideOutVertically", "targetOffsetY", "toAlignment", "get", "T", "key", "Landroidx/compose/animation/TransitionEffectKey;", "(Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/TransitionEffectKey;)Landroidx/compose/animation/TransitionEffect;", "(Landroidx/compose/animation/ExitTransition;Landroidx/compose/animation/TransitionEffectKey;)Landroidx/compose/animation/TransitionEffect;", "createModifier", "Landroidx/compose/ui/Modifier;", "Landroidx/compose/animation/core/Transition;", "Landroidx/compose/animation/EnterExitState;", "enter", "exit", "isEnabled", "Lkotlin/Function0;", "label", "", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Lkotlin/jvm/functions/Function0;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)Landroidx/compose/ui/Modifier;", "trackActiveEnter", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/EnterTransition;Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/EnterTransition;", "trackActiveExit", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/ExitTransition;Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/ExitTransition;", "createGraphicsLayerBlock", "Landroidx/compose/animation/GraphicsLayerBlockForEnterExit;", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/GraphicsLayerBlockForEnterExit;", "TransformOriginVectorConverter", "Landroidx/compose/animation/core/TwoWayConverter;", "Landroidx/compose/animation/core/AnimationVector2D;", "DefaultAlphaAndScaleSpring", "Landroidx/compose/animation/core/SpringSpec;", "DefaultColorAnimationSpec", "DefaultOffsetAnimationSpec", "DefaultSizeAnimationSpec", "animation", "activeEnter", "activeExit"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class EnterExitTransitionKt {
    private static final TwoWayConverter<TransformOrigin, AnimationVector2D> TransformOriginVectorConverter = VectorConvertersKt.TwoWayConverter(new Function1<TransformOrigin, AnimationVector2D>() { // from class: androidx.compose.animation.EnterExitTransitionKt$TransformOriginVectorConverter$1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return m106invoke__ExYCQ(((TransformOrigin) obj).unbox-impl());
        }

        /* JADX INFO: renamed from: invoke-__ExYCQ, reason: not valid java name */
        public final AnimationVector2D m106invoke__ExYCQ(long j) {
            return new AnimationVector2D(TransformOrigin.getPivotFractionX-impl(j), TransformOrigin.getPivotFractionY-impl(j));
        }
    }, new Function1<AnimationVector2D, TransformOrigin>() { // from class: androidx.compose.animation.EnterExitTransitionKt$TransformOriginVectorConverter$2
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return TransformOrigin.box-impl(m107invokeLIALnN8((AnimationVector2D) obj));
        }

        /* JADX INFO: renamed from: invoke-LIALnN8, reason: not valid java name */
        public final long m107invokeLIALnN8(AnimationVector2D animationVector2D) {
            return TransformOriginKt.TransformOrigin(animationVector2D.getV1(), animationVector2D.getV2());
        }
    });
    private static final SpringSpec<Float> DefaultAlphaAndScaleSpring = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
    private static final SpringSpec<Color> DefaultColorAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
    private static final SpringSpec<IntOffset> DefaultOffsetAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.box-impl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.Companion)), 1, null);
    private static final SpringSpec<IntSize> DefaultSizeAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.box-impl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.Companion)), 1, null);

    /* JADX WARN: Code duplicated, block: B:43:0x011e A[PHI: r1
      0x011e: PHI (r1v11 androidx.compose.animation.EnterTransition) = (r1v9 androidx.compose.animation.EnterTransition), (r1v12 androidx.compose.animation.EnterTransition) binds: [B:42:0x011c, B:38:0x0115] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:47:0x012a  */
    /* JADX WARN: Code duplicated, block: B:50:0x0133  */
    /* JADX WARN: Code duplicated, block: B:53:0x0139 A[PHI: r2
      0x0139: PHI (r2v10 androidx.compose.animation.ExitTransition) = (r2v8 androidx.compose.animation.ExitTransition), (r2v11 androidx.compose.animation.ExitTransition) binds: [B:52:0x0137, B:48:0x0130] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:54:0x013b  */
    /* JADX WARN: Code duplicated, block: B:57:0x014b  */
    /* JADX WARN: Code duplicated, block: B:59:0x0151  */
    /* JADX WARN: Code duplicated, block: B:65:0x0163  */
    /* JADX WARN: Code duplicated, block: B:67:0x016b  */
    /* JADX WARN: Code duplicated, block: B:70:0x0182  */
    private static final GraphicsLayerBlockForEnterExit createGraphicsLayerBlock(final Transition<EnterExitState> transition, EnterTransition enterTransition, ExitTransition exitTransition, String str, Composer composer, int i) {
        final Transition.DeferredAnimation deferredAnimation;
        final Transition.DeferredAnimation deferredAnimation2;
        EnterTransition enterTransition2;
        boolean z;
        ExitTransition exitTransition2;
        boolean z2;
        boolean zChangedInstance;
        Object objRememberedValue;
        Composer composer2 = composer;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(642253525, i, -1, "androidx.compose.animation.createGraphicsLayerBlock (EnterExitTransition.kt:1052)");
        }
        boolean z3 = true;
        boolean z4 = (enterTransition.getData().getFade() == null && exitTransition.getData().getFade() == null) ? false : true;
        boolean z5 = (enterTransition.getData().getScale() == null && exitTransition.getData().getScale() == null) ? false : true;
        Transition.DeferredAnimation deferredAnimationCreateDeferredAnimation = null;
        if (z4) {
            composer2.startReplaceGroup(-703879421);
            TwoWayConverter<Float, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            Object objRememberedValue2 = composer2.rememberedValue();
            if (objRememberedValue2 == Composer.Companion.getEmpty()) {
                objRememberedValue2 = str + " alpha";
                composer2.updateRememberedValue(objRememberedValue2);
            }
            Transition.DeferredAnimation deferredAnimationCreateDeferredAnimation2 = androidx.compose.animation.core.TransitionKt.createDeferredAnimation(transition, vectorConverter, (String) objRememberedValue2, composer2, (i & 14) | 384, 0);
            composer2 = composer2;
            composer2.endReplaceGroup();
            deferredAnimation = deferredAnimationCreateDeferredAnimation2;
        } else {
            composer2.startReplaceGroup(-703709976);
            composer2.endReplaceGroup();
            deferredAnimation = null;
        }
        if (z5) {
            composer2.startReplaceGroup(-703642333);
            TwoWayConverter<Float, AnimationVector1D> vectorConverter2 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            Object objRememberedValue3 = composer2.rememberedValue();
            if (objRememberedValue3 == Composer.Companion.getEmpty()) {
                objRememberedValue3 = str + " scale";
                composer2.updateRememberedValue(objRememberedValue3);
            }
            Transition.DeferredAnimation deferredAnimationCreateDeferredAnimation3 = androidx.compose.animation.core.TransitionKt.createDeferredAnimation(transition, vectorConverter2, (String) objRememberedValue3, composer2, (i & 14) | 384, 0);
            composer2.endReplaceGroup();
            deferredAnimation2 = deferredAnimationCreateDeferredAnimation3;
        } else {
            composer2.startReplaceGroup(-703472888);
            composer2.endReplaceGroup();
            deferredAnimation2 = null;
        }
        if (z5) {
            composer2.startReplaceGroup(-703395232);
            deferredAnimationCreateDeferredAnimation = androidx.compose.animation.core.TransitionKt.createDeferredAnimation(transition, TransformOriginVectorConverter, "TransformOriginInterruptionHandling", composer2, (i & 14) | 384, 0);
            composer2.endReplaceGroup();
        } else {
            composer2.startReplaceGroup(-703222904);
            composer2.endReplaceGroup();
        }
        boolean zChangedInstance2 = composer2.changedInstance(deferredAnimation);
        if (((i & 112) ^ 48) > 32) {
            enterTransition2 = enterTransition;
            if (composer2.changed(enterTransition2)) {
                z = true;
            }
            boolean z6 = zChangedInstance2 | z;
            if (((i & 896) ^ 384) > 256) {
                exitTransition2 = exitTransition;
                if (!composer2.changed(exitTransition2)) {
                    z2 = true;
                }
                boolean zChangedInstance3 = z6 | z2 | composer2.changedInstance(deferredAnimation2);
                if ((((i & 14) ^ 6) > 4 || !composer2.changed(transition)) && (i & 6) != 4) {
                }
                zChangedInstance = zChangedInstance3 | z3 | composer2.changedInstance(deferredAnimationCreateDeferredAnimation);
                objRememberedValue = composer2.rememberedValue();
                if (zChangedInstance || objRememberedValue == Composer.Companion.getEmpty()) {
                    final EnterTransition enterTransition3 = enterTransition2;
                    final ExitTransition exitTransition3 = exitTransition2;
                    final Transition.DeferredAnimation deferredAnimation3 = deferredAnimationCreateDeferredAnimation;
                    GraphicsLayerBlockForEnterExit graphicsLayerBlockForEnterExit = new GraphicsLayerBlockForEnterExit() { // from class: rb4
                        @Override // androidx.compose.animation.GraphicsLayerBlockForEnterExit
                        public final Function1 init() {
                            return EnterExitTransitionKt.createGraphicsLayerBlock$lambda$2$0(deferredAnimation, deferredAnimation2, transition, enterTransition3, exitTransition3, deferredAnimation3);
                        }
                    };
                    composer2.updateRememberedValue(graphicsLayerBlockForEnterExit);
                    objRememberedValue = graphicsLayerBlockForEnterExit;
                }
                GraphicsLayerBlockForEnterExit graphicsLayerBlockForEnterExit2 = (GraphicsLayerBlockForEnterExit) objRememberedValue;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                return graphicsLayerBlockForEnterExit2;
            }
            exitTransition2 = exitTransition;
            if ((i & 384) == 256) {
                z2 = true;
            } else {
                z2 = false;
            }
            boolean zChangedInstance4 = z6 | z2 | composer2.changedInstance(deferredAnimation2);
            z3 = ((i & 14) ^ 6) > 4 ? false : false;
            zChangedInstance = zChangedInstance4 | z3 | composer2.changedInstance(deferredAnimationCreateDeferredAnimation);
            objRememberedValue = composer2.rememberedValue();
            if (zChangedInstance) {
                final EnterTransition enterTransition4 = enterTransition2;
                final ExitTransition exitTransition4 = exitTransition2;
                final Transition.DeferredAnimation deferredAnimation4 = deferredAnimationCreateDeferredAnimation;
                GraphicsLayerBlockForEnterExit graphicsLayerBlockForEnterExit3 = new GraphicsLayerBlockForEnterExit() { // from class: rb4
                    @Override // androidx.compose.animation.GraphicsLayerBlockForEnterExit
                    public final Function1 init() {
                        return EnterExitTransitionKt.createGraphicsLayerBlock$lambda$2$0(deferredAnimation, deferredAnimation2, transition, enterTransition4, exitTransition4, deferredAnimation4);
                    }
                };
                composer2.updateRememberedValue(graphicsLayerBlockForEnterExit3);
                objRememberedValue = graphicsLayerBlockForEnterExit3;
            } else {
                final EnterTransition enterTransition5 = enterTransition2;
                final ExitTransition exitTransition5 = exitTransition2;
                final Transition.DeferredAnimation deferredAnimation5 = deferredAnimationCreateDeferredAnimation;
                GraphicsLayerBlockForEnterExit graphicsLayerBlockForEnterExit4 = new GraphicsLayerBlockForEnterExit() { // from class: rb4
                    @Override // androidx.compose.animation.GraphicsLayerBlockForEnterExit
                    public final Function1 init() {
                        return EnterExitTransitionKt.createGraphicsLayerBlock$lambda$2$0(deferredAnimation, deferredAnimation2, transition, enterTransition5, exitTransition5, deferredAnimation5);
                    }
                };
                composer2.updateRememberedValue(graphicsLayerBlockForEnterExit4);
                objRememberedValue = graphicsLayerBlockForEnterExit4;
            }
            GraphicsLayerBlockForEnterExit graphicsLayerBlockForEnterExit5 = (GraphicsLayerBlockForEnterExit) objRememberedValue;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            return graphicsLayerBlockForEnterExit5;
        }
        enterTransition2 = enterTransition;
        if ((i & 48) == 32) {
            z = true;
        } else {
            z = false;
        }
        boolean z7 = zChangedInstance2 | z;
        if (((i & 896) ^ 384) > 256) {
            exitTransition2 = exitTransition;
            if (!composer2.changed(exitTransition2)) {
                z2 = true;
            }
            boolean zChangedInstance5 = z7 | z2 | composer2.changedInstance(deferredAnimation2);
            if (((i & 14) ^ 6) > 4) {
            }
            zChangedInstance = zChangedInstance5 | z3 | composer2.changedInstance(deferredAnimationCreateDeferredAnimation);
            objRememberedValue = composer2.rememberedValue();
            if (zChangedInstance) {
                final EnterTransition enterTransition6 = enterTransition2;
                final ExitTransition exitTransition6 = exitTransition2;
                final Transition.DeferredAnimation deferredAnimation6 = deferredAnimationCreateDeferredAnimation;
                GraphicsLayerBlockForEnterExit graphicsLayerBlockForEnterExit6 = new GraphicsLayerBlockForEnterExit() { // from class: rb4
                    @Override // androidx.compose.animation.GraphicsLayerBlockForEnterExit
                    public final Function1 init() {
                        return EnterExitTransitionKt.createGraphicsLayerBlock$lambda$2$0(deferredAnimation, deferredAnimation2, transition, enterTransition6, exitTransition6, deferredAnimation6);
                    }
                };
                composer2.updateRememberedValue(graphicsLayerBlockForEnterExit6);
                objRememberedValue = graphicsLayerBlockForEnterExit6;
            } else {
                final EnterTransition enterTransition7 = enterTransition2;
                final ExitTransition exitTransition7 = exitTransition2;
                final Transition.DeferredAnimation deferredAnimation7 = deferredAnimationCreateDeferredAnimation;
                GraphicsLayerBlockForEnterExit graphicsLayerBlockForEnterExit7 = new GraphicsLayerBlockForEnterExit() { // from class: rb4
                    @Override // androidx.compose.animation.GraphicsLayerBlockForEnterExit
                    public final Function1 init() {
                        return EnterExitTransitionKt.createGraphicsLayerBlock$lambda$2$0(deferredAnimation, deferredAnimation2, transition, enterTransition7, exitTransition7, deferredAnimation7);
                    }
                };
                composer2.updateRememberedValue(graphicsLayerBlockForEnterExit7);
                objRememberedValue = graphicsLayerBlockForEnterExit7;
            }
            GraphicsLayerBlockForEnterExit graphicsLayerBlockForEnterExit8 = (GraphicsLayerBlockForEnterExit) objRememberedValue;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            return graphicsLayerBlockForEnterExit8;
        }
        exitTransition2 = exitTransition;
        if ((i & 384) == 256) {
            z2 = true;
        } else {
            z2 = false;
        }
        boolean zChangedInstance6 = z7 | z2 | composer2.changedInstance(deferredAnimation2);
        if (((i & 14) ^ 6) > 4) {
        }
        zChangedInstance = zChangedInstance6 | z3 | composer2.changedInstance(deferredAnimationCreateDeferredAnimation);
        objRememberedValue = composer2.rememberedValue();
        if (zChangedInstance) {
            final EnterTransition enterTransition8 = enterTransition2;
            final ExitTransition exitTransition8 = exitTransition2;
            final Transition.DeferredAnimation deferredAnimation8 = deferredAnimationCreateDeferredAnimation;
            GraphicsLayerBlockForEnterExit graphicsLayerBlockForEnterExit9 = new GraphicsLayerBlockForEnterExit() { // from class: rb4
                @Override // androidx.compose.animation.GraphicsLayerBlockForEnterExit
                public final Function1 init() {
                    return EnterExitTransitionKt.createGraphicsLayerBlock$lambda$2$0(deferredAnimation, deferredAnimation2, transition, enterTransition8, exitTransition8, deferredAnimation8);
                }
            };
            composer2.updateRememberedValue(graphicsLayerBlockForEnterExit9);
            objRememberedValue = graphicsLayerBlockForEnterExit9;
        } else {
            final EnterTransition enterTransition9 = enterTransition2;
            final ExitTransition exitTransition9 = exitTransition2;
            final Transition.DeferredAnimation deferredAnimation9 = deferredAnimationCreateDeferredAnimation;
            GraphicsLayerBlockForEnterExit graphicsLayerBlockForEnterExit10 = new GraphicsLayerBlockForEnterExit() { // from class: rb4
                @Override // androidx.compose.animation.GraphicsLayerBlockForEnterExit
                public final Function1 init() {
                    return EnterExitTransitionKt.createGraphicsLayerBlock$lambda$2$0(deferredAnimation, deferredAnimation2, transition, enterTransition9, exitTransition9, deferredAnimation9);
                }
            };
            composer2.updateRememberedValue(graphicsLayerBlockForEnterExit10);
            objRememberedValue = graphicsLayerBlockForEnterExit10;
        }
        GraphicsLayerBlockForEnterExit graphicsLayerBlockForEnterExit11 = (GraphicsLayerBlockForEnterExit) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return graphicsLayerBlockForEnterExit11;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:17:0x004b  */
    public static final Function1 createGraphicsLayerBlock$lambda$2$0(Transition.DeferredAnimation deferredAnimation, Transition.DeferredAnimation deferredAnimation2, Transition transition, final EnterTransition enterTransition, final ExitTransition exitTransition, Transition.DeferredAnimation deferredAnimation3) {
        final TransformOrigin transformOrigin;
        final State stateAnimate = deferredAnimation != null ? deferredAnimation.animate(new Function1<Transition.Segment<EnterExitState>, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$alpha$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final FiniteAnimationSpec<Float> invoke(Transition.Segment<EnterExitState> segment) {
                FiniteAnimationSpec<Float> animationSpec;
                FiniteAnimationSpec<Float> animationSpec2;
                EnterExitState enterExitState = EnterExitState.PreEnter;
                EnterExitState enterExitState2 = EnterExitState.Visible;
                if (segment.isTransitioningTo(enterExitState, enterExitState2)) {
                    Fade fade = enterTransition.getData().getFade();
                    return (fade == null || (animationSpec2 = fade.getAnimationSpec()) == null) ? EnterExitTransitionKt.DefaultAlphaAndScaleSpring : animationSpec2;
                }
                if (!segment.isTransitioningTo(enterExitState2, EnterExitState.PostExit)) {
                    return EnterExitTransitionKt.DefaultAlphaAndScaleSpring;
                }
                Fade fade2 = exitTransition.getData().getFade();
                return (fade2 == null || (animationSpec = fade2.getAnimationSpec()) == null) ? EnterExitTransitionKt.DefaultAlphaAndScaleSpring : animationSpec;
            }
        }, new Function1<EnterExitState, Float>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$alpha$2

            @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
            public static final /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[EnterExitState.values().length];
                    try {
                        iArr[EnterExitState.Visible.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[EnterExitState.PreEnter.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    try {
                        iArr[EnterExitState.PostExit.ordinal()] = 3;
                    } catch (NoSuchFieldError unused3) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final Float invoke(EnterExitState enterExitState) {
                int i = WhenMappings.$EnumSwitchMapping$0[enterExitState.ordinal()];
                float alpha = 1.0f;
                if (i != 1) {
                    if (i == 2) {
                        Fade fade = enterTransition.getData().getFade();
                        if (fade != null) {
                            alpha = fade.getAlpha();
                        }
                    } else {
                        if (i != 3) {
                            bu8.a();
                            return null;
                        }
                        Fade fade2 = exitTransition.getData().getFade();
                        if (fade2 != null) {
                            alpha = fade2.getAlpha();
                        }
                    }
                }
                return Float.valueOf(alpha);
            }
        }) : null;
        final State stateAnimate2 = deferredAnimation2 != null ? deferredAnimation2.animate(new Function1<Transition.Segment<EnterExitState>, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$scale$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final FiniteAnimationSpec<Float> invoke(Transition.Segment<EnterExitState> segment) {
                FiniteAnimationSpec<Float> animationSpec;
                FiniteAnimationSpec<Float> animationSpec2;
                EnterExitState enterExitState = EnterExitState.PreEnter;
                EnterExitState enterExitState2 = EnterExitState.Visible;
                if (segment.isTransitioningTo(enterExitState, enterExitState2)) {
                    Scale scale = enterTransition.getData().getScale();
                    return (scale == null || (animationSpec2 = scale.getAnimationSpec()) == null) ? EnterExitTransitionKt.DefaultAlphaAndScaleSpring : animationSpec2;
                }
                if (!segment.isTransitioningTo(enterExitState2, EnterExitState.PostExit)) {
                    return EnterExitTransitionKt.DefaultAlphaAndScaleSpring;
                }
                Scale scale2 = exitTransition.getData().getScale();
                return (scale2 == null || (animationSpec = scale2.getAnimationSpec()) == null) ? EnterExitTransitionKt.DefaultAlphaAndScaleSpring : animationSpec;
            }
        }, new Function1<EnterExitState, Float>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$scale$2

            @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
            public static final /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[EnterExitState.values().length];
                    try {
                        iArr[EnterExitState.Visible.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[EnterExitState.PreEnter.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    try {
                        iArr[EnterExitState.PostExit.ordinal()] = 3;
                    } catch (NoSuchFieldError unused3) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final Float invoke(EnterExitState enterExitState) {
                int i = WhenMappings.$EnumSwitchMapping$0[enterExitState.ordinal()];
                float scale = 1.0f;
                if (i != 1) {
                    if (i == 2) {
                        Scale scale2 = enterTransition.getData().getScale();
                        if (scale2 != null) {
                            scale = scale2.getScale();
                        }
                    } else {
                        if (i != 3) {
                            bu8.a();
                            return null;
                        }
                        Scale scale3 = exitTransition.getData().getScale();
                        if (scale3 != null) {
                            scale = scale3.getScale();
                        }
                    }
                }
                return Float.valueOf(scale);
            }
        }) : null;
        if (transition.getCurrentState() == EnterExitState.PreEnter) {
            Scale scale = enterTransition.getData().getScale();
            if (scale == null && (scale = exitTransition.getData().getScale()) == null) {
                transformOrigin = null;
            } else {
                transformOrigin = TransformOrigin.box-impl(scale.m136getTransformOriginSzJe1aQ());
            }
        } else {
            Scale scale2 = exitTransition.getData().getScale();
            if (scale2 == null && (scale2 = enterTransition.getData().getScale()) == null) {
                transformOrigin = null;
            } else {
                transformOrigin = TransformOrigin.box-impl(scale2.m136getTransformOriginSzJe1aQ());
            }
        }
        final State stateAnimate3 = deferredAnimation3 != null ? deferredAnimation3.animate(new Function1<Transition.Segment<EnterExitState>, FiniteAnimationSpec<TransformOrigin>>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$transformOrigin$1
            public final FiniteAnimationSpec<TransformOrigin> invoke(Transition.Segment<EnterExitState> segment) {
                return AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
            }
        }, new Function1<EnterExitState, TransformOrigin>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$transformOrigin$2

            @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
            public static final /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[EnterExitState.values().length];
                    try {
                        iArr[EnterExitState.Visible.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[EnterExitState.PreEnter.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    try {
                        iArr[EnterExitState.PostExit.ordinal()] = 3;
                    } catch (NoSuchFieldError unused3) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return TransformOrigin.box-impl(m108invokeLIALnN8((EnterExitState) obj));
            }

            /* JADX INFO: renamed from: invoke-LIALnN8, reason: not valid java name */
            public final long m108invokeLIALnN8(EnterExitState enterExitState) {
                TransformOrigin transformOrigin2;
                long jM136getTransformOriginSzJe1aQ;
                long jM136getTransformOriginSzJe1aQ2;
                int i = WhenMappings.$EnumSwitchMapping$0[enterExitState.ordinal()];
                if (i != 1) {
                    transformOrigin2 = null;
                    if (i == 2) {
                        Scale scale3 = enterTransition.getData().getScale();
                        if (scale3 != null) {
                            jM136getTransformOriginSzJe1aQ = scale3.m136getTransformOriginSzJe1aQ();
                        } else {
                            Scale scale4 = exitTransition.getData().getScale();
                            if (scale4 != null) {
                                jM136getTransformOriginSzJe1aQ = scale4.m136getTransformOriginSzJe1aQ();
                            }
                        }
                        transformOrigin2 = TransformOrigin.box-impl(jM136getTransformOriginSzJe1aQ);
                    } else {
                        if (i != 3) {
                            bu8.a();
                            return 0L;
                        }
                        Scale scale5 = exitTransition.getData().getScale();
                        if (scale5 != null) {
                            jM136getTransformOriginSzJe1aQ2 = scale5.m136getTransformOriginSzJe1aQ();
                        } else {
                            Scale scale6 = enterTransition.getData().getScale();
                            if (scale6 != null) {
                                jM136getTransformOriginSzJe1aQ2 = scale6.m136getTransformOriginSzJe1aQ();
                            }
                        }
                        transformOrigin2 = TransformOrigin.box-impl(jM136getTransformOriginSzJe1aQ2);
                    }
                } else {
                    transformOrigin2 = transformOrigin;
                }
                return transformOrigin2 != null ? transformOrigin2.unbox-impl() : TransformOrigin.Companion.getCenter-SzJe1aQ();
            }
        }) : null;
        return new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$block$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(GraphicsLayerScope graphicsLayerScope) {
                State<Float> state = stateAnimate;
                graphicsLayerScope.setAlpha(state != null ? ((Number) state.getValue()).floatValue() : 1.0f);
                State<Float> state2 = stateAnimate2;
                graphicsLayerScope.setScaleX(state2 != null ? ((Number) state2.getValue()).floatValue() : 1.0f);
                State<Float> state3 = stateAnimate2;
                graphicsLayerScope.setScaleY(state3 != null ? ((Number) state3.getValue()).floatValue() : 1.0f);
                State<TransformOrigin> state4 = stateAnimate3;
                graphicsLayerScope.setTransformOrigin-__ExYCQ(state4 != null ? ((TransformOrigin) state4.getValue()).unbox-impl() : TransformOrigin.Companion.getCenter-SzJe1aQ());
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((GraphicsLayerScope) obj);
                return Unit.INSTANCE;
            }
        };
    }

    public static final Modifier createModifier(Transition<EnterExitState> transition, EnterTransition enterTransition, ExitTransition exitTransition, Function0<Boolean> function0, String str, Composer composer, int i, int i2) {
        final Function0<Boolean> function1;
        Transition.DeferredAnimation deferredAnimation;
        Transition.DeferredAnimation deferredAnimation2;
        Transition.DeferredAnimation deferredAnimation3;
        ChangeSize changeSize;
        ColorSpace srgb;
        Transition<EnterExitState> transition2;
        VeilModifierElement veilModifierElement;
        if ((i2 & 4) != 0) {
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = new Function0<Boolean>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createModifier$1$1
                    /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                    public final Boolean m109invoke() {
                        return Boolean.TRUE;
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            function1 = (Function0) objRememberedValue;
        } else {
            function1 = function0;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(28261782, i, -1, "androidx.compose.animation.createModifier (EnterExitTransition.kt:933)");
        }
        int i3 = i & 14;
        EnterTransition enterTransitionTrackActiveEnter = trackActiveEnter(transition, enterTransition, composer, i & 126);
        int i4 = i >> 3;
        ExitTransition exitTransitionTrackActiveExit = trackActiveExit(transition, exitTransition, composer, (i4 & 112) | i3);
        boolean z = true;
        boolean z2 = (enterTransitionTrackActiveEnter.getData().getVeil() == null && exitTransitionTrackActiveExit.getData().getVeil() == null) ? false : true;
        boolean z3 = (enterTransitionTrackActiveEnter.getData().getSlide() == null && exitTransitionTrackActiveExit.getData().getSlide() == null) ? false : true;
        boolean z4 = (enterTransitionTrackActiveEnter.getData().getChangeSize() == null && exitTransitionTrackActiveExit.getData().getChangeSize() == null) ? false : true;
        if (z3) {
            composer.startReplaceGroup(133792645);
            TwoWayConverter<IntOffset, AnimationVector2D> vectorConverter = VectorConvertersKt.getVectorConverter(IntOffset.Companion);
            Object objRememberedValue2 = composer.rememberedValue();
            if (objRememberedValue2 == Composer.Companion.getEmpty()) {
                objRememberedValue2 = str + " slide";
                composer.updateRememberedValue(objRememberedValue2);
            }
            Transition.DeferredAnimation deferredAnimationCreateDeferredAnimation = androidx.compose.animation.core.TransitionKt.createDeferredAnimation(transition, vectorConverter, (String) objRememberedValue2, composer, i3 | 384, 0);
            composer.endReplaceGroup();
            deferredAnimation = deferredAnimationCreateDeferredAnimation;
        } else {
            composer.startReplaceGroup(133898448);
            composer.endReplaceGroup();
            deferredAnimation = null;
        }
        if (z4) {
            composer.startReplaceGroup(133990239);
            TwoWayConverter<IntSize, AnimationVector2D> vectorConverter2 = VectorConvertersKt.getVectorConverter(IntSize.Companion);
            Object objRememberedValue3 = composer.rememberedValue();
            if (objRememberedValue3 == Composer.Companion.getEmpty()) {
                objRememberedValue3 = str + " shrink/expand";
                composer.updateRememberedValue(objRememberedValue3);
            }
            Transition.DeferredAnimation deferredAnimationCreateDeferredAnimation2 = androidx.compose.animation.core.TransitionKt.createDeferredAnimation(transition, vectorConverter2, (String) objRememberedValue3, composer, i3 | 384, 0);
            composer.endReplaceGroup();
            deferredAnimation2 = deferredAnimationCreateDeferredAnimation2;
        } else {
            composer.startReplaceGroup(134101063);
            composer.endReplaceGroup();
            deferredAnimation2 = null;
        }
        if (z4) {
            composer.startReplaceGroup(134174689);
            TwoWayConverter<IntOffset, AnimationVector2D> vectorConverter3 = VectorConvertersKt.getVectorConverter(IntOffset.Companion);
            Object objRememberedValue4 = composer.rememberedValue();
            if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                objRememberedValue4 = str + " InterruptionHandlingOffset";
                composer.updateRememberedValue(objRememberedValue4);
            }
            Transition.DeferredAnimation deferredAnimationCreateDeferredAnimation3 = androidx.compose.animation.core.TransitionKt.createDeferredAnimation(transition, vectorConverter3, (String) objRememberedValue4, composer, i3 | 384, 0);
            composer.endReplaceGroup();
            deferredAnimation3 = deferredAnimationCreateDeferredAnimation3;
        } else {
            composer.startReplaceGroup(134345095);
            composer.endReplaceGroup();
            deferredAnimation3 = null;
        }
        ChangeSize changeSize2 = enterTransitionTrackActiveEnter.getData().getChangeSize();
        final boolean z5 = ((changeSize2 == null || changeSize2.getClip()) && ((changeSize = exitTransitionTrackActiveExit.getData().getChangeSize()) == null || changeSize.getClip()) && z4) ? false : true;
        Veil veil = enterTransitionTrackActiveEnter.getData().getVeil();
        if (veil == null || (srgb = Color.getColorSpace-impl(veil.m196getInitialColor0d7_KjU())) == null) {
            Veil veil2 = enterTransitionTrackActiveEnter.getData().getVeil();
            if (veil2 != null) {
                srgb = Color.getColorSpace-impl(veil2.m197getTargetColor0d7_KjU());
            } else {
                Veil veil3 = exitTransitionTrackActiveExit.getData().getVeil();
                srgb = veil3 != null ? Color.getColorSpace-impl(veil3.m196getInitialColor0d7_KjU()) : null;
                if (srgb == null) {
                    Veil veil4 = exitTransitionTrackActiveExit.getData().getVeil();
                    ColorSpace colorSpace = veil4 != null ? Color.getColorSpace-impl(veil4.m197getTargetColor0d7_KjU()) : null;
                    srgb = colorSpace == null ? ColorSpaces.INSTANCE.getSrgb() : colorSpace;
                }
            }
        }
        if (z2) {
            composer.startReplaceGroup(134871569);
            TwoWayConverter twoWayConverter = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.Companion).invoke(srgb);
            Object objRememberedValue5 = composer.rememberedValue();
            if (objRememberedValue5 == Composer.Companion.getEmpty()) {
                objRememberedValue5 = str + " veil";
                composer.updateRememberedValue(objRememberedValue5);
            }
            transition2 = transition;
            veilModifierElement = new VeilModifierElement(transition2, androidx.compose.animation.core.TransitionKt.createDeferredAnimation(transition2, twoWayConverter, (String) objRememberedValue5, composer, i3 | 384, 0), enterTransitionTrackActiveEnter, exitTransitionTrackActiveExit);
            composer.endReplaceGroup();
        } else {
            transition2 = transition;
            composer.startReplaceGroup(135150476);
            composer.endReplaceGroup();
            veilModifierElement = Modifier.Companion;
        }
        VeilModifierElement veilModifierElement2 = veilModifierElement;
        Veil veil5 = enterTransitionTrackActiveEnter.getData().getVeil();
        boolean matchParentSize = (veil5 == null && (veil5 = exitTransitionTrackActiveExit.getData().getVeil()) == null) ? false : veil5.getMatchParentSize();
        GraphicsLayerBlockForEnterExit graphicsLayerBlockForEnterExitCreateGraphicsLayerBlock = createGraphicsLayerBlock(transition2, enterTransitionTrackActiveEnter, exitTransitionTrackActiveExit, str, composer, i3 | (i4 & 7168));
        VeilModifierElement veilModifierElement3 = matchParentSize ? veilModifierElement2 : Modifier.Companion;
        VeilModifierElement veilModifierElement4 = Modifier.Companion;
        boolean zChanged = composer.changed(z5);
        if ((((i & 7168) ^ 3072) <= 2048 || !composer.changed(function1)) && (i & 3072) != 2048) {
            z = false;
        }
        boolean z6 = zChanged | z;
        Object objRememberedValue6 = composer.rememberedValue();
        if (z6 || objRememberedValue6 == Composer.Companion.getEmpty()) {
            objRememberedValue6 = new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createModifier$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                public final void invoke(GraphicsLayerScope graphicsLayerScope) {
                    graphicsLayerScope.setClip(!z5 && ((Boolean) function1.invoke()).booleanValue());
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((GraphicsLayerScope) obj);
                    return Unit.INSTANCE;
                }
            };
            composer.updateRememberedValue(objRememberedValue6);
        }
        Modifier modifierThen = veilModifierElement3.then(GraphicsLayerModifierKt.graphicsLayer(veilModifierElement4, (Function1) objRememberedValue6)).then(new EnterExitTransitionElement(transition, deferredAnimation2, deferredAnimation3, deferredAnimation, enterTransitionTrackActiveEnter, exitTransitionTrackActiveExit, function1, graphicsLayerBlockForEnterExitCreateGraphicsLayerBlock));
        if (!matchParentSize) {
            veilModifierElement4 = veilModifierElement2;
        }
        Modifier modifierThen2 = modifierThen.then(veilModifierElement4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return modifierThen2;
    }

    public static final EnterTransition expandHorizontally(FiniteAnimationSpec<IntSize> finiteAnimationSpec, Alignment.Horizontal horizontal, boolean z, final Function1<? super Integer, Integer> function1) {
        return expandIn(finiteAnimationSpec, toAlignment(horizontal), z, new Function1<IntSize, IntSize>() { // from class: androidx.compose.animation.EnterExitTransitionKt.expandHorizontally.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return IntSize.box-impl(m110invokemzRDjE0(((IntSize) obj).unbox-impl()));
            }

            /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
            public final long m110invokemzRDjE0(long j) {
                return IntSize.constructor-impl((((long) ((int) (j & 4294967295L))) & 4294967295L) | (((long) ((Number) function1.invoke(Integer.valueOf((int) (j >> 32)))).intValue()) << 32));
            }
        });
    }

    public static /* synthetic */ EnterTransition expandHorizontally$default(FiniteAnimationSpec finiteAnimationSpec, Alignment.Horizontal horizontal, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.box-impl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.Companion)), 1, null);
        }
        if ((i & 2) != 0) {
            horizontal = Alignment.Companion.getEnd();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt.expandHorizontally.1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    return invoke(((Number) obj2).intValue());
                }

                public final Integer invoke(int i2) {
                    return 0;
                }
            };
        }
        return expandHorizontally(finiteAnimationSpec, horizontal, z, function1);
    }

    public static final EnterTransition expandIn(FiniteAnimationSpec<IntSize> finiteAnimationSpec, Alignment alignment, boolean z, Function1<? super IntSize, IntSize> function1) {
        return new EnterTransitionImpl(new TransitionData(null, null, new ChangeSize(alignment, function1, finiteAnimationSpec, z), null, null, false, null, 123, null));
    }

    public static /* synthetic */ EnterTransition expandIn$default(FiniteAnimationSpec finiteAnimationSpec, Alignment alignment, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.box-impl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.Companion)), 1, null);
        }
        if ((i & 2) != 0) {
            alignment = Alignment.Companion.getBottomEnd();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            function1 = new Function1<IntSize, IntSize>() { // from class: androidx.compose.animation.EnterExitTransitionKt.expandIn.1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    return IntSize.box-impl(m111invokemzRDjE0(((IntSize) obj2).unbox-impl()));
                }

                /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
                public final long m111invokemzRDjE0(long j) {
                    return IntSize.constructor-impl(0L);
                }
            };
        }
        return expandIn(finiteAnimationSpec, alignment, z, function1);
    }

    public static final EnterTransition expandVertically(FiniteAnimationSpec<IntSize> finiteAnimationSpec, Alignment.Vertical vertical, boolean z, final Function1<? super Integer, Integer> function1) {
        return expandIn(finiteAnimationSpec, toAlignment(vertical), z, new Function1<IntSize, IntSize>() { // from class: androidx.compose.animation.EnterExitTransitionKt.expandVertically.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return IntSize.box-impl(m112invokemzRDjE0(((IntSize) obj).unbox-impl()));
            }

            /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
            public final long m112invokemzRDjE0(long j) {
                return IntSize.constructor-impl((((long) ((int) (j >> 32))) << 32) | (((long) ((Number) function1.invoke(Integer.valueOf((int) (j & 4294967295L)))).intValue()) & 4294967295L));
            }
        });
    }

    public static /* synthetic */ EnterTransition expandVertically$default(FiniteAnimationSpec finiteAnimationSpec, Alignment.Vertical vertical, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.box-impl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.Companion)), 1, null);
        }
        if ((i & 2) != 0) {
            vertical = Alignment.Companion.getBottom();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt.expandVertically.1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    return invoke(((Number) obj2).intValue());
                }

                public final Integer invoke(int i2) {
                    return 0;
                }
            };
        }
        return expandVertically(finiteAnimationSpec, vertical, z, function1);
    }

    public static final EnterTransition fadeIn(FiniteAnimationSpec<Float> finiteAnimationSpec, float f) {
        return new EnterTransitionImpl(new TransitionData(new Fade(f, finiteAnimationSpec), null, null, null, null, false, null, 126, null));
    }

    public static /* synthetic */ EnterTransition fadeIn$default(FiniteAnimationSpec finiteAnimationSpec, float f, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
        }
        if ((i & 2) != 0) {
            f = 0.0f;
        }
        return fadeIn(finiteAnimationSpec, f);
    }

    public static final ExitTransition fadeOut(FiniteAnimationSpec<Float> finiteAnimationSpec, float f) {
        return new ExitTransitionImpl(new TransitionData(new Fade(f, finiteAnimationSpec), null, null, null, null, false, null, 126, null));
    }

    public static /* synthetic */ ExitTransition fadeOut$default(FiniteAnimationSpec finiteAnimationSpec, float f, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
        }
        if ((i & 2) != 0) {
            f = 0.0f;
        }
        return fadeOut(finiteAnimationSpec, f);
    }

    public static final <T extends TransitionEffect> T get(EnterTransition enterTransition, TransitionEffectKey<T> transitionEffectKey) {
        TransitionEffect transitionEffect = enterTransition.getData().getEffectsMap().get(transitionEffectKey);
        if (transitionEffect instanceof TransitionEffect) {
            return (T) transitionEffect;
        }
        return null;
    }

    /* JADX INFO: renamed from: scaleIn-L8ZKh-E, reason: not valid java name */
    public static final EnterTransition m98scaleInL8ZKhE(FiniteAnimationSpec<Float> finiteAnimationSpec, float f, long j) {
        return new EnterTransitionImpl(new TransitionData(null, null, null, new Scale(f, j, finiteAnimationSpec, null), null, false, null, 119, null));
    }

    /* JADX INFO: renamed from: scaleIn-L8ZKh-E$default, reason: not valid java name */
    public static /* synthetic */ EnterTransition m99scaleInL8ZKhE$default(FiniteAnimationSpec finiteAnimationSpec, float f, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
        }
        if ((i & 2) != 0) {
            f = 0.0f;
        }
        if ((i & 4) != 0) {
            j = TransformOrigin.Companion.getCenter-SzJe1aQ();
        }
        return m98scaleInL8ZKhE(finiteAnimationSpec, f, j);
    }

    /* JADX INFO: renamed from: scaleOut-L8ZKh-E, reason: not valid java name */
    public static final ExitTransition m100scaleOutL8ZKhE(FiniteAnimationSpec<Float> finiteAnimationSpec, float f, long j) {
        return new ExitTransitionImpl(new TransitionData(null, null, null, new Scale(f, j, finiteAnimationSpec, null), null, false, null, 119, null));
    }

    /* JADX INFO: renamed from: scaleOut-L8ZKh-E$default, reason: not valid java name */
    public static /* synthetic */ ExitTransition m101scaleOutL8ZKhE$default(FiniteAnimationSpec finiteAnimationSpec, float f, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
        }
        if ((i & 2) != 0) {
            f = 0.0f;
        }
        if ((i & 4) != 0) {
            j = TransformOrigin.Companion.getCenter-SzJe1aQ();
        }
        return m100scaleOutL8ZKhE(finiteAnimationSpec, f, j);
    }

    public static final ExitTransition shrinkHorizontally(FiniteAnimationSpec<IntSize> finiteAnimationSpec, Alignment.Horizontal horizontal, boolean z, final Function1<? super Integer, Integer> function1) {
        return shrinkOut(finiteAnimationSpec, toAlignment(horizontal), z, new Function1<IntSize, IntSize>() { // from class: androidx.compose.animation.EnterExitTransitionKt.shrinkHorizontally.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return IntSize.box-impl(m113invokemzRDjE0(((IntSize) obj).unbox-impl()));
            }

            /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
            public final long m113invokemzRDjE0(long j) {
                return IntSize.constructor-impl((((long) ((int) (j & 4294967295L))) & 4294967295L) | (((long) ((Number) function1.invoke(Integer.valueOf((int) (j >> 32)))).intValue()) << 32));
            }
        });
    }

    public static /* synthetic */ ExitTransition shrinkHorizontally$default(FiniteAnimationSpec finiteAnimationSpec, Alignment.Horizontal horizontal, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.box-impl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.Companion)), 1, null);
        }
        if ((i & 2) != 0) {
            horizontal = Alignment.Companion.getEnd();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt.shrinkHorizontally.1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    return invoke(((Number) obj2).intValue());
                }

                public final Integer invoke(int i2) {
                    return 0;
                }
            };
        }
        return shrinkHorizontally(finiteAnimationSpec, horizontal, z, function1);
    }

    public static final ExitTransition shrinkOut(FiniteAnimationSpec<IntSize> finiteAnimationSpec, Alignment alignment, boolean z, Function1<? super IntSize, IntSize> function1) {
        return new ExitTransitionImpl(new TransitionData(null, null, new ChangeSize(alignment, function1, finiteAnimationSpec, z), null, null, false, null, 123, null));
    }

    public static /* synthetic */ ExitTransition shrinkOut$default(FiniteAnimationSpec finiteAnimationSpec, Alignment alignment, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.box-impl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.Companion)), 1, null);
        }
        if ((i & 2) != 0) {
            alignment = Alignment.Companion.getBottomEnd();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            function1 = new Function1<IntSize, IntSize>() { // from class: androidx.compose.animation.EnterExitTransitionKt.shrinkOut.1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    return IntSize.box-impl(m114invokemzRDjE0(((IntSize) obj2).unbox-impl()));
                }

                /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
                public final long m114invokemzRDjE0(long j) {
                    return IntSize.constructor-impl(0L);
                }
            };
        }
        return shrinkOut(finiteAnimationSpec, alignment, z, function1);
    }

    public static final ExitTransition shrinkVertically(FiniteAnimationSpec<IntSize> finiteAnimationSpec, Alignment.Vertical vertical, boolean z, final Function1<? super Integer, Integer> function1) {
        return shrinkOut(finiteAnimationSpec, toAlignment(vertical), z, new Function1<IntSize, IntSize>() { // from class: androidx.compose.animation.EnterExitTransitionKt.shrinkVertically.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return IntSize.box-impl(m115invokemzRDjE0(((IntSize) obj).unbox-impl()));
            }

            /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
            public final long m115invokemzRDjE0(long j) {
                return IntSize.constructor-impl((((long) ((int) (j >> 32))) << 32) | (((long) ((Number) function1.invoke(Integer.valueOf((int) (j & 4294967295L)))).intValue()) & 4294967295L));
            }
        });
    }

    public static /* synthetic */ ExitTransition shrinkVertically$default(FiniteAnimationSpec finiteAnimationSpec, Alignment.Vertical vertical, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.box-impl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.Companion)), 1, null);
        }
        if ((i & 2) != 0) {
            vertical = Alignment.Companion.getBottom();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt.shrinkVertically.1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    return invoke(((Number) obj2).intValue());
                }

                public final Integer invoke(int i2) {
                    return 0;
                }
            };
        }
        return shrinkVertically(finiteAnimationSpec, vertical, z, function1);
    }

    public static final EnterTransition slideIn(FiniteAnimationSpec<IntOffset> finiteAnimationSpec, Function1<? super IntSize, IntOffset> function1) {
        return new EnterTransitionImpl(new TransitionData(null, new Slide(function1, finiteAnimationSpec), null, null, null, false, null, 125, null));
    }

    public static /* synthetic */ EnterTransition slideIn$default(FiniteAnimationSpec finiteAnimationSpec, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.box-impl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.Companion)), 1, null);
        }
        return slideIn(finiteAnimationSpec, function1);
    }

    public static final EnterTransition slideInHorizontally(FiniteAnimationSpec<IntOffset> finiteAnimationSpec, final Function1<? super Integer, Integer> function1) {
        return slideIn(finiteAnimationSpec, new Function1<IntSize, IntOffset>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideInHorizontally.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return IntOffset.box-impl(m116invokemHKZG7I(((IntSize) obj).unbox-impl()));
            }

            /* JADX INFO: renamed from: invoke-mHKZG7I, reason: not valid java name */
            public final long m116invokemHKZG7I(long j) {
                return IntOffset.constructor-impl(((long) ((Number) function1.invoke(Integer.valueOf((int) (j >> 32)))).intValue()) << 32);
            }
        });
    }

    public static /* synthetic */ EnterTransition slideInHorizontally$default(FiniteAnimationSpec finiteAnimationSpec, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.box-impl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.Companion)), 1, null);
        }
        if ((i & 2) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideInHorizontally.1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    return invoke(((Number) obj2).intValue());
                }

                public final Integer invoke(int i2) {
                    return Integer.valueOf((-i2) / 2);
                }
            };
        }
        return slideInHorizontally(finiteAnimationSpec, function1);
    }

    public static final EnterTransition slideInVertically(FiniteAnimationSpec<IntOffset> finiteAnimationSpec, final Function1<? super Integer, Integer> function1) {
        return slideIn(finiteAnimationSpec, new Function1<IntSize, IntOffset>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideInVertically.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return IntOffset.box-impl(m117invokemHKZG7I(((IntSize) obj).unbox-impl()));
            }

            /* JADX INFO: renamed from: invoke-mHKZG7I, reason: not valid java name */
            public final long m117invokemHKZG7I(long j) {
                return IntOffset.constructor-impl(((long) ((Number) function1.invoke(Integer.valueOf((int) (j & 4294967295L)))).intValue()) & 4294967295L);
            }
        });
    }

    public static /* synthetic */ EnterTransition slideInVertically$default(FiniteAnimationSpec finiteAnimationSpec, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.box-impl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.Companion)), 1, null);
        }
        if ((i & 2) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideInVertically.1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    return invoke(((Number) obj2).intValue());
                }

                public final Integer invoke(int i2) {
                    return Integer.valueOf((-i2) / 2);
                }
            };
        }
        return slideInVertically(finiteAnimationSpec, function1);
    }

    public static final ExitTransition slideOut(FiniteAnimationSpec<IntOffset> finiteAnimationSpec, Function1<? super IntSize, IntOffset> function1) {
        return new ExitTransitionImpl(new TransitionData(null, new Slide(function1, finiteAnimationSpec), null, null, null, false, null, 125, null));
    }

    public static /* synthetic */ ExitTransition slideOut$default(FiniteAnimationSpec finiteAnimationSpec, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.box-impl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.Companion)), 1, null);
        }
        return slideOut(finiteAnimationSpec, function1);
    }

    public static final ExitTransition slideOutHorizontally(FiniteAnimationSpec<IntOffset> finiteAnimationSpec, final Function1<? super Integer, Integer> function1) {
        return slideOut(finiteAnimationSpec, new Function1<IntSize, IntOffset>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideOutHorizontally.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return IntOffset.box-impl(m118invokemHKZG7I(((IntSize) obj).unbox-impl()));
            }

            /* JADX INFO: renamed from: invoke-mHKZG7I, reason: not valid java name */
            public final long m118invokemHKZG7I(long j) {
                return IntOffset.constructor-impl(((long) ((Number) function1.invoke(Integer.valueOf((int) (j >> 32)))).intValue()) << 32);
            }
        });
    }

    public static /* synthetic */ ExitTransition slideOutHorizontally$default(FiniteAnimationSpec finiteAnimationSpec, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.box-impl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.Companion)), 1, null);
        }
        if ((i & 2) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideOutHorizontally.1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    return invoke(((Number) obj2).intValue());
                }

                public final Integer invoke(int i2) {
                    return Integer.valueOf((-i2) / 2);
                }
            };
        }
        return slideOutHorizontally(finiteAnimationSpec, function1);
    }

    public static final ExitTransition slideOutVertically(FiniteAnimationSpec<IntOffset> finiteAnimationSpec, final Function1<? super Integer, Integer> function1) {
        return slideOut(finiteAnimationSpec, new Function1<IntSize, IntOffset>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideOutVertically.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return IntOffset.box-impl(m119invokemHKZG7I(((IntSize) obj).unbox-impl()));
            }

            /* JADX INFO: renamed from: invoke-mHKZG7I, reason: not valid java name */
            public final long m119invokemHKZG7I(long j) {
                return IntOffset.constructor-impl(((long) ((Number) function1.invoke(Integer.valueOf((int) (j & 4294967295L)))).intValue()) & 4294967295L);
            }
        });
    }

    public static /* synthetic */ ExitTransition slideOutVertically$default(FiniteAnimationSpec finiteAnimationSpec, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.box-impl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.Companion)), 1, null);
        }
        if ((i & 2) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideOutVertically.1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    return invoke(((Number) obj2).intValue());
                }

                public final Integer invoke(int i2) {
                    return Integer.valueOf((-i2) / 2);
                }
            };
        }
        return slideOutVertically(finiteAnimationSpec, function1);
    }

    private static final Alignment toAlignment(Alignment.Horizontal horizontal) {
        Alignment.Companion companion = Alignment.Companion;
        if (Intrinsics.areEqual(horizontal, companion.getStart())) {
            return companion.getCenterStart();
        }
        return Intrinsics.areEqual(horizontal, companion.getEnd()) ? companion.getCenterEnd() : companion.getCenter();
    }

    public static final EnterTransition trackActiveEnter(Transition<EnterExitState> transition, EnterTransition enterTransition, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(21614502, i, -1, "androidx.compose.animation.trackActiveEnter (EnterExitTransition.kt:1004)");
        }
        boolean z = (((i & 14) ^ 6) > 4 && composer.changed(transition)) || (i & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = SnapshotStateKt.mutableStateOf$default(enterTransition, (SnapshotMutationPolicy) null, 2, (Object) null);
            composer.updateRememberedValue(objRememberedValue);
        }
        MutableState mutableState = (MutableState) objRememberedValue;
        if (transition.getCurrentState() == transition.getTargetState() && transition.getCurrentState() == EnterExitState.Visible) {
            if (transition.isSeeking()) {
                mutableState.setValue(enterTransition);
            } else {
                mutableState.setValue(EnterTransition.INSTANCE.getNone());
            }
        } else if (transition.getTargetState() == EnterExitState.Visible) {
            mutableState.setValue(trackActiveEnter$lambda$1(mutableState).plus(enterTransition));
        }
        EnterTransition enterTransitionTrackActiveEnter$lambda$1 = trackActiveEnter$lambda$1(mutableState);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return enterTransitionTrackActiveEnter$lambda$1;
    }

    private static final EnterTransition trackActiveEnter$lambda$1(MutableState<EnterTransition> mutableState) {
        return (EnterTransition) mutableState.getValue();
    }

    public static final ExitTransition trackActiveExit(Transition<EnterExitState> transition, ExitTransition exitTransition, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1363864804, i, -1, "androidx.compose.animation.trackActiveExit (EnterExitTransition.kt:1024)");
        }
        boolean z = (((i & 14) ^ 6) > 4 && composer.changed(transition)) || (i & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = SnapshotStateKt.mutableStateOf$default(exitTransition, (SnapshotMutationPolicy) null, 2, (Object) null);
            composer.updateRememberedValue(objRememberedValue);
        }
        MutableState mutableState = (MutableState) objRememberedValue;
        if (transition.getCurrentState() == transition.getTargetState() && transition.getCurrentState() == EnterExitState.Visible) {
            if (transition.isSeeking()) {
                mutableState.setValue(exitTransition);
            } else {
                mutableState.setValue(ExitTransition.INSTANCE.getNone());
            }
        } else if (transition.getTargetState() != EnterExitState.Visible) {
            mutableState.setValue(trackActiveExit$lambda$1(mutableState).plus(exitTransition));
        }
        ExitTransition exitTransitionTrackActiveExit$lambda$1 = trackActiveExit$lambda$1(mutableState);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return exitTransitionTrackActiveExit$lambda$1;
    }

    private static final ExitTransition trackActiveExit$lambda$1(MutableState<ExitTransition> mutableState) {
        return (ExitTransition) mutableState.getValue();
    }

    /* JADX INFO: renamed from: unveilIn-bw27NRU, reason: not valid java name */
    public static final EnterTransition m102unveilInbw27NRU(FiniteAnimationSpec<Color> finiteAnimationSpec, long j, boolean z) {
        return new EnterTransitionImpl(new TransitionData(null, null, null, null, new Veil(j, Color.copy-wmQWz5c$default(j, 0.0f, 0.0f, 0.0f, 0.0f, 14, (Object) null), finiteAnimationSpec, z, null), false, null, 111, null));
    }

    /* JADX INFO: renamed from: unveilIn-bw27NRU$default, reason: not valid java name */
    public static /* synthetic */ EnterTransition m103unveilInbw27NRU$default(FiniteAnimationSpec finiteAnimationSpec, long j, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
        }
        if ((i & 2) != 0) {
            j = Color.copy-wmQWz5c$default(Color.Companion.getBlack-0d7_KjU(), 0.5f, 0.0f, 0.0f, 0.0f, 14, (Object) null);
        }
        if ((i & 4) != 0) {
            z = false;
        }
        return m102unveilInbw27NRU(finiteAnimationSpec, j, z);
    }

    /* JADX INFO: renamed from: veilOut-bw27NRU, reason: not valid java name */
    public static final ExitTransition m104veilOutbw27NRU(FiniteAnimationSpec<Color> finiteAnimationSpec, long j, boolean z) {
        return new ExitTransitionImpl(new TransitionData(null, null, null, null, new Veil(Color.copy-wmQWz5c$default(j, 0.0f, 0.0f, 0.0f, 0.0f, 14, (Object) null), j, finiteAnimationSpec, z, null), false, null, 111, null));
    }

    /* JADX INFO: renamed from: veilOut-bw27NRU$default, reason: not valid java name */
    public static /* synthetic */ ExitTransition m105veilOutbw27NRU$default(FiniteAnimationSpec finiteAnimationSpec, long j, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
        }
        if ((i & 2) != 0) {
            j = Color.copy-wmQWz5c$default(Color.Companion.getBlack-0d7_KjU(), 0.5f, 0.0f, 0.0f, 0.0f, 14, (Object) null);
        }
        if ((i & 4) != 0) {
            z = false;
        }
        return m104veilOutbw27NRU(finiteAnimationSpec, j, z);
    }

    public static final EnterTransition withEffect(EnterTransition enterTransition, TransitionEffect transitionEffect) {
        return new EnterTransitionImpl(new TransitionData(null, null, null, null, null, false, MapsKt.mapOf(TuplesKt.to(transitionEffect.getKey$animation(), transitionEffect)), 63, null));
    }

    public static final <T extends TransitionEffect> T get(ExitTransition exitTransition, TransitionEffectKey<T> transitionEffectKey) {
        TransitionEffect transitionEffect = exitTransition.getData().getEffectsMap().get(transitionEffectKey);
        if (transitionEffect instanceof TransitionEffect) {
            return (T) transitionEffect;
        }
        return null;
    }

    public static final ExitTransition withEffect(ExitTransition exitTransition, TransitionEffect transitionEffect) {
        return new ExitTransitionImpl(new TransitionData(null, null, null, null, null, false, MapsKt.mapOf(TuplesKt.to(transitionEffect.getKey$animation(), transitionEffect)), 63, null));
    }

    private static final Alignment toAlignment(Alignment.Vertical vertical) {
        Alignment.Companion companion = Alignment.Companion;
        if (Intrinsics.areEqual(vertical, companion.getTop())) {
            return companion.getTopCenter();
        }
        return Intrinsics.areEqual(vertical, companion.getBottom()) ? companion.getBottomCenter() : companion.getCenter();
    }
}

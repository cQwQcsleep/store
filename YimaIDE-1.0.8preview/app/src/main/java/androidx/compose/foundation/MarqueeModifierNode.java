package androidx.compose.foundation;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusEventModifierNode;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.graphics.layer.GraphicsLayerKt;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.math.MathKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B7\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\b\u0010:\u001a\u00020;H\u0016J\b\u0010<\u001a\u00020;H\u0016J=\u0010=\u001a\u00020;2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b>\u0010?J\u0010\u0010@\u001a\u00020;2\u0006\u0010A\u001a\u00020BH\u0016J#\u0010C\u001a\u00020D*\u00020E2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020IH\u0016¢\u0006\u0004\bJ\u0010KJ\u001c\u0010L\u001a\u00020\u0006*\u00020M2\u0006\u0010F\u001a\u00020N2\u0006\u0010O\u001a\u00020\u0006H\u0016J\u001c\u0010P\u001a\u00020\u0006*\u00020M2\u0006\u0010F\u001a\u00020N2\u0006\u0010O\u001a\u00020\u0006H\u0016J\u001c\u0010Q\u001a\u00020\u0006*\u00020M2\u0006\u0010F\u001a\u00020N2\u0006\u0010R\u001a\u00020\u0006H\u0016J\u001c\u0010S\u001a\u00020\u0006*\u00020M2\u0006\u0010F\u001a\u00020N2\u0006\u0010R\u001a\u00020\u0006H\u0016J\f\u0010T\u001a\u00020;*\u00020UH\u0016J\b\u0010V\u001a\u00020;H\u0002J\u000e\u0010W\u001a\u00020;H\u0082@¢\u0006\u0002\u0010XR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0011R+\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00068B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R+\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00068B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001d\u0010\u0019\u001a\u0004\b\u001b\u0010\u0015\"\u0004\b\u001c\u0010\u0017R+\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0012\u001a\u00020\u001e8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b.\u0010%\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R+\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\b8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b1\u0010%\u001a\u0004\b/\u0010\u0015\"\u0004\b0\u0010\u0017R\u001a\u00102\u001a\u000e\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u00020503X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u00106\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b8\u00109\u001a\u0004\b7\u0010\u0015¨\u0006Y"}, d2 = {"Landroidx/compose/foundation/MarqueeModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/node/DrawModifierNode;", "Landroidx/compose/ui/focus/FocusEventModifierNode;", "iterations", "", "animationMode", "Landroidx/compose/foundation/MarqueeAnimationMode;", "delayMillis", "initialDelayMillis", "spacing", "Landroidx/compose/foundation/MarqueeSpacing;", "velocity", "Landroidx/compose/ui/unit/Dp;", "<init>", "(IIIILandroidx/compose/foundation/MarqueeSpacing;FLkotlin/jvm/internal/DefaultConstructorMarker;)V", "F", "<set-?>", "contentWidth", "getContentWidth", "()I", "setContentWidth", "(I)V", "contentWidth$delegate", "Landroidx/compose/runtime/MutableIntState;", "containerWidth", "getContainerWidth", "setContainerWidth", "containerWidth$delegate", "", "hasFocus", "getHasFocus", "()Z", "setHasFocus", "(Z)V", "hasFocus$delegate", "Landroidx/compose/runtime/MutableState;", "animationJob", "Lkotlinx/coroutines/Job;", "marqueeLayer", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "getSpacing", "()Landroidx/compose/foundation/MarqueeSpacing;", "setSpacing", "(Landroidx/compose/foundation/MarqueeSpacing;)V", "spacing$delegate", "getAnimationMode-ZbEOnfQ", "setAnimationMode-97h66l8", "animationMode$delegate", "offset", "Landroidx/compose/animation/core/Animatable;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "spacingPx", "getSpacingPx", "spacingPx$delegate", "Landroidx/compose/runtime/State;", "onAttach", "", "onDetach", "update", "update-lWfNwf4", "(IIIILandroidx/compose/foundation/MarqueeSpacing;F)V", "onFocusEvent", "focusState", "Landroidx/compose/ui/focus/FocusState;", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicWidth", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "maxIntrinsicWidth", "minIntrinsicHeight", "width", "maxIntrinsicHeight", "draw", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "restartAnimation", "runAnimation", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class MarqueeModifierNode extends Modifier.Node implements LayoutModifierNode, DrawModifierNode, FocusEventModifierNode {
    private Job animationJob;

    /* JADX INFO: renamed from: animationMode$delegate, reason: from kotlin metadata */
    private final MutableState animationMode;

    /* JADX INFO: renamed from: containerWidth$delegate, reason: from kotlin metadata */
    private final MutableIntState containerWidth;

    /* JADX INFO: renamed from: contentWidth$delegate, reason: from kotlin metadata */
    private final MutableIntState contentWidth;
    private int delayMillis;

    /* JADX INFO: renamed from: hasFocus$delegate, reason: from kotlin metadata */
    private final MutableState hasFocus;
    private int initialDelayMillis;
    private int iterations;
    private GraphicsLayer marqueeLayer;
    private final Animatable<Float, AnimationVector1D> offset;

    /* JADX INFO: renamed from: spacing$delegate, reason: from kotlin metadata */
    private final MutableState spacing;

    /* JADX INFO: renamed from: spacingPx$delegate, reason: from kotlin metadata */
    private final State spacingPx;
    private float velocity;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LayoutDirection.values().length];
            try {
                iArr[LayoutDirection.Ltr.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LayoutDirection.Rtl.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.MarqueeModifierNode$restartAnimation$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.MarqueeModifierNode$restartAnimation$1", f = "BasicMarquee.kt", i = {}, l = {390, 391}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Job $oldJob;
        int label;
        final /* synthetic */ MarqueeModifierNode this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Job job, MarqueeModifierNode marqueeModifierNode, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$oldJob = job;
            this.this$0 = marqueeModifierNode;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$oldJob, this.this$0, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:16:0x0035, code lost:
        
            if (r5.runAnimation(r4) == r0) goto L17;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Job job = this.$oldJob;
                if (job != null) {
                    this.label = 1;
                    if (job.join(this) != coroutine_suspended) {
                    }
                }
                return coroutine_suspended;
            }
            if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                if (i != 2) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
            MarqueeModifierNode marqueeModifierNode = this.this$0;
            this.label = 2;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.MarqueeModifierNode$runAnimation$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.MarqueeModifierNode$runAnimation$2", f = "BasicMarquee.kt", i = {}, l = {413}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.MarqueeModifierNode$runAnimation$2$2, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n"}, d2 = {"<anonymous>", "", "contentWithSpacingWidth", ""}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.MarqueeModifierNode$runAnimation$2$2", f = "BasicMarquee.kt", i = {0, 0}, l = {427, 429, 433, 433}, m = "invokeSuspend", n = {"contentWithSpacingWidth", "spec"}, s = {"L$0", "L$1"}, v = 1)
        public static final class C00092 extends SuspendLambda implements Function2<Float, Continuation<? super Unit>, Object> {
            /* synthetic */ Object L$0;
            Object L$1;
            int label;
            final /* synthetic */ MarqueeModifierNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00092(MarqueeModifierNode marqueeModifierNode, Continuation<? super C00092> continuation) {
                super(2, continuation);
                this.this$0 = marqueeModifierNode;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00092 c00092 = new C00092(this.this$0, continuation);
                c00092.L$0 = obj;
                return c00092;
            }

            public final Object invoke(Float f, Continuation<? super Unit> continuation) {
                return create(f, continuation).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code restructure failed: missing block: B:30:0x00bd, code lost:
            
                if (r0.snapTo(r1, r20) == r8) goto L36;
             */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final Object invokeSuspend(Object obj) throws Throwable {
                Float f;
                AnimationSpec animationSpec;
                Object objAnimateTo$default;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                try {
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        f = (Float) this.L$0;
                        if (f == null) {
                            return Unit.INSTANCE;
                        }
                        AnimationSpec animationSpecM330createMarqueeAnimationSpecZ4HSEVQ = BasicMarqueeKt.m330createMarqueeAnimationSpecZ4HSEVQ(this.this$0.iterations, f.floatValue(), this.this$0.initialDelayMillis, this.this$0.delayMillis, this.this$0.velocity, DelegatableNodeKt.requireDensity(this.this$0));
                        Animatable animatable = this.this$0.offset;
                        Float fBoxFloat = Boxing.boxFloat(0.0f);
                        this.L$0 = f;
                        this.L$1 = animationSpecM330createMarqueeAnimationSpecZ4HSEVQ;
                        this.label = 1;
                        if (animatable.snapTo(fBoxFloat, this) != coroutine_suspended) {
                            animationSpec = animationSpecM330createMarqueeAnimationSpecZ4HSEVQ;
                        }
                        return coroutine_suspended;
                    }
                    if (i == 1) {
                        AnimationSpec animationSpec2 = (AnimationSpec) this.L$1;
                        Float f2 = (Float) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        animationSpec = animationSpec2;
                        f = f2;
                    } else if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                        objAnimateTo$default = obj;
                        Animatable animatable2 = this.this$0.offset;
                        Float fBoxFloat2 = Boxing.boxFloat(0.0f);
                        this.label = 3;
                    } else {
                        if (i != 3) {
                            if (i != 4) {
                                k2d.a("call to 'resume' before 'invoke' with coroutine");
                                return null;
                            }
                            Throwable th = (Throwable) this.L$0;
                            ResultKt.throwOnFailure(obj);
                            throw th;
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                    Animatable animatable3 = this.this$0.offset;
                    this.L$0 = null;
                    this.L$1 = null;
                    this.label = 2;
                    objAnimateTo$default = Animatable.animateTo$default(animatable3, f, animationSpec, null, null, this, 12, null);
                    if (objAnimateTo$default != coroutine_suspended) {
                        Animatable animatable4 = this.this$0.offset;
                        Float fBoxFloat3 = Boxing.boxFloat(0.0f);
                        this.label = 3;
                    }
                } catch (Throwable th2) {
                    Animatable animatable5 = this.this$0.offset;
                    Float fBoxFloat4 = Boxing.boxFloat(0.0f);
                    this.L$0 = th2;
                    this.L$1 = null;
                    this.label = 4;
                    if (animatable5.snapTo(fBoxFloat4, this) != coroutine_suspended) {
                        throw th2;
                    }
                }
                return coroutine_suspended;
            }
        }

        public AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        public static Float b(MarqueeModifierNode marqueeModifierNode) {
            if (marqueeModifierNode.getContentWidth() <= marqueeModifierNode.getContainerWidth()) {
                return null;
            }
            if (!MarqueeAnimationMode.m406equalsimpl0(marqueeModifierNode.m418getAnimationModeZbEOnfQ(), MarqueeAnimationMode.INSTANCE.m411getWhileFocusedZbEOnfQ()) || marqueeModifierNode.getHasFocus()) {
                return Float.valueOf(marqueeModifierNode.getContentWidth() + marqueeModifierNode.getSpacingPx());
            }
            return null;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MarqueeModifierNode.this.new AnonymousClass2(continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final MarqueeModifierNode marqueeModifierNode = MarqueeModifierNode.this;
                Flow flowSnapshotFlow = SnapshotStateKt.snapshotFlow(new Function0() { // from class: androidx.compose.foundation.n
                    public final Object invoke() {
                        return MarqueeModifierNode.AnonymousClass2.b(marqueeModifierNode);
                    }
                });
                C00092 c00092 = new C00092(MarqueeModifierNode.this, null);
                this.label = 1;
                if (FlowKt.collectLatest(flowSnapshotFlow, c00092, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    private MarqueeModifierNode(int i, int i2, int i3, int i4, final MarqueeSpacing marqueeSpacing, float f) {
        this.iterations = i;
        this.delayMillis = i3;
        this.initialDelayMillis = i4;
        this.velocity = f;
        this.contentWidth = SnapshotIntStateKt.mutableIntStateOf(0);
        this.containerWidth = SnapshotIntStateKt.mutableIntStateOf(0);
        this.hasFocus = SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.spacing = SnapshotStateKt.mutableStateOf$default(marqueeSpacing, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.animationMode = SnapshotStateKt.mutableStateOf$default(MarqueeAnimationMode.m403boximpl(i2), (SnapshotMutationPolicy) null, 2, (Object) null);
        this.offset = AnimatableKt.Animatable$default(0.0f, 0.0f, 2, null);
        this.spacingPx = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.foundation.m
            public final Object invoke() {
                return Integer.valueOf(MarqueeModifierNode.c(marqueeSpacing, this));
            }
        });
    }

    public static Unit a(Placeable placeable, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.placeWithLayer$default(placementScope, placeable, 0, 0, 0.0f, (Function1) null, 12, (Object) null);
        return Unit.INSTANCE;
    }

    public static int c(MarqueeSpacing marqueeSpacing, MarqueeModifierNode marqueeModifierNode) {
        return marqueeSpacing.calculateSpacing(DelegatableNodeKt.requireDensity(marqueeModifierNode), marqueeModifierNode.getContentWidth(), marqueeModifierNode.getContainerWidth());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit draw$lambda$0$0(ContentDrawScope contentDrawScope, DrawScope drawScope) {
        contentDrawScope.drawContent();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getContainerWidth() {
        return this.containerWidth.getIntValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getContentWidth() {
        return this.contentWidth.getIntValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getHasFocus() {
        return ((Boolean) this.hasFocus.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getSpacingPx() {
        return ((Number) this.spacingPx.getValue()).intValue();
    }

    private final void restartAnimation() {
        Job job = this.animationJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        if (isAttached()) {
            this.animationJob = BuildersKt.launch$default(getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(job, this, null), 3, (Object) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object runAnimation(Continuation<? super Unit> continuation) {
        Object objWithContext;
        return (this.iterations > 0 && (objWithContext = BuildersKt.withContext(FixedMotionDurationScale.INSTANCE, new AnonymousClass2(null), continuation)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? objWithContext : Unit.INSTANCE;
    }

    private final void setContainerWidth(int i) {
        this.containerWidth.setIntValue(i);
    }

    private final void setContentWidth(int i) {
        this.contentWidth.setIntValue(i);
    }

    private final void setHasFocus(boolean z) {
        this.hasFocus.setValue(Boolean.valueOf(z));
    }

    public void draw(final ContentDrawScope contentDrawScope) {
        float contentWidth;
        float fFloatValue;
        int containerWidth;
        if (Dp.compareTo-0680j_4(this.velocity, Dp.constructor-impl(0.0f)) > 0) {
            int i = WhenMappings.$EnumSwitchMapping$0[contentDrawScope.getLayoutDirection().ordinal()];
            if (i == 1) {
                contentWidth = this.offset.getValue().floatValue();
            } else if (i != 2) {
                bu8.a();
                return;
            } else {
                fFloatValue = (-this.offset.getValue().floatValue()) + (getContentWidth() * 2) + getSpacingPx();
                containerWidth = getContainerWidth();
                contentWidth = fFloatValue - containerWidth;
            }
        } else {
            int i2 = WhenMappings.$EnumSwitchMapping$0[contentDrawScope.getLayoutDirection().ordinal()];
            if (i2 == 1) {
                contentWidth = (-this.offset.getValue().floatValue()) + getContentWidth() + getSpacingPx();
            } else if (i2 != 2) {
                bu8.a();
                return;
            } else {
                fFloatValue = this.offset.getValue().floatValue() + getContentWidth();
                containerWidth = getContainerWidth();
                contentWidth = fFloatValue - containerWidth;
            }
        }
        boolean z = contentWidth < ((float) getContentWidth());
        boolean z2 = ((float) getContainerWidth()) + contentWidth > ((float) (getContentWidth() + getSpacingPx()));
        float contentWidth2 = getContentWidth() + getSpacingPx();
        float fIntBitsToFloat = Float.intBitsToFloat((int) (contentDrawScope.getSize-NH-jbRc() & 4294967295L));
        GraphicsLayer graphicsLayer = this.marqueeLayer;
        if (graphicsLayer != null) {
            contentDrawScope.record-JVtK1S4(graphicsLayer, IntSize.constructor-impl((((long) getContentWidth()) << 32) | (((long) MathKt.roundToInt(fIntBitsToFloat)) & 4294967295L)), new Function1() { // from class: androidx.compose.foundation.k
                public final Object invoke(Object obj) {
                    return MarqueeModifierNode.draw$lambda$0$0(contentDrawScope, (DrawScope) obj);
                }
            });
        }
        float containerWidth2 = getContainerWidth();
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (contentDrawScope.getSize-NH-jbRc() & 4294967295L));
        int i3 = ClipOp.Companion.getIntersect-rtfAjoo();
        DrawContext drawContext = contentDrawScope.getDrawContext();
        long j = drawContext.getSize-NH-jbRc();
        drawContext.getCanvas().save();
        try {
            drawContext.getTransform().clipRect-N_I0leg(0.0f, 0.0f, containerWidth2, fIntBitsToFloat2, i3);
            float f = -contentWidth;
            contentDrawScope.getDrawContext().getTransform().translate(f, 0.0f);
            try {
                GraphicsLayer graphicsLayer2 = this.marqueeLayer;
                if (graphicsLayer2 != null) {
                    if (z) {
                        GraphicsLayerKt.drawLayer(contentDrawScope, graphicsLayer2);
                    }
                    if (z2) {
                        contentDrawScope.getDrawContext().getTransform().translate(contentWidth2, 0.0f);
                        try {
                            GraphicsLayerKt.drawLayer(contentDrawScope, graphicsLayer2);
                            contentDrawScope.getDrawContext().getTransform().translate(-contentWidth2, -0.0f);
                        } catch (Throwable th) {
                            contentDrawScope.getDrawContext().getTransform().translate(-contentWidth2, -0.0f);
                            throw th;
                        }
                    }
                } else {
                    if (z) {
                        contentDrawScope.drawContent();
                    }
                    if (z2) {
                        contentDrawScope.getDrawContext().getTransform().translate(contentWidth2, 0.0f);
                        try {
                            contentDrawScope.drawContent();
                            contentDrawScope.getDrawContext().getTransform().translate(-contentWidth2, -0.0f);
                        } catch (Throwable th2) {
                            contentDrawScope.getDrawContext().getTransform().translate(-contentWidth2, -0.0f);
                            throw th2;
                        }
                    }
                }
                contentDrawScope.getDrawContext().getTransform().translate(-f, -0.0f);
                drawContext.getCanvas().restore();
                drawContext.setSize-uvyYCjk(j);
            } catch (Throwable th3) {
                contentDrawScope.getDrawContext().getTransform().translate(-f, -0.0f);
                throw th3;
            }
        } catch (Throwable th4) {
            drawContext.getCanvas().restore();
            drawContext.setSize-uvyYCjk(j);
            throw th4;
        }
    }

    /* JADX INFO: renamed from: getAnimationMode-ZbEOnfQ, reason: not valid java name */
    public final int m418getAnimationModeZbEOnfQ() {
        return ((MarqueeAnimationMode) this.animationMode.getValue()).getValue();
    }

    public final MarqueeSpacing getSpacing() {
        return (MarqueeSpacing) this.spacing.getValue();
    }

    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return intrinsicMeasurable.maxIntrinsicHeight(Integer.MAX_VALUE);
    }

    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return intrinsicMeasurable.maxIntrinsicWidth(i);
    }

    /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
    public MeasureResult m419measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        final Placeable placeable = measurable.measure-BRTryo0(Constraints.copy-Zbe2FdA$default(j, 0, Integer.MAX_VALUE, 0, 0, 13, (Object) null));
        setContainerWidth(ConstraintsKt.constrainWidth-K40F9xA(j, placeable.getWidth()));
        setContentWidth(placeable.getWidth());
        return MeasureScope.layout$default(measureScope, getContainerWidth(), placeable.getHeight(), (Map) null, new Function1() { // from class: androidx.compose.foundation.l
            public final Object invoke(Object obj) {
                return MarqueeModifierNode.a(placeable, (Placeable.PlacementScope) obj);
            }
        }, 4, (Object) null);
    }

    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return intrinsicMeasurable.minIntrinsicHeight(Integer.MAX_VALUE);
    }

    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return 0;
    }

    public void onAttach() {
        GraphicsLayer graphicsLayer = this.marqueeLayer;
        GraphicsContext graphicsContextRequireGraphicsContext = DelegatableNodeKt.requireGraphicsContext(this);
        if (graphicsLayer != null) {
            graphicsContextRequireGraphicsContext.releaseGraphicsLayer(graphicsLayer);
        }
        this.marqueeLayer = graphicsContextRequireGraphicsContext.createGraphicsLayer();
        restartAnimation();
    }

    public void onDetach() {
        Job job = this.animationJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.animationJob = null;
        GraphicsLayer graphicsLayer = this.marqueeLayer;
        if (graphicsLayer != null) {
            DelegatableNodeKt.requireGraphicsContext(this).releaseGraphicsLayer(graphicsLayer);
            this.marqueeLayer = null;
        }
    }

    public void onFocusEvent(FocusState focusState) {
        setHasFocus(focusState.getHasFocus());
    }

    /* JADX INFO: renamed from: setAnimationMode-97h66l8, reason: not valid java name */
    public final void m420setAnimationMode97h66l8(int i) {
        this.animationMode.setValue(MarqueeAnimationMode.m403boximpl(i));
    }

    public final void setSpacing(MarqueeSpacing marqueeSpacing) {
        this.spacing.setValue(marqueeSpacing);
    }

    /* JADX INFO: renamed from: update-lWfNwf4, reason: not valid java name */
    public final void m421updatelWfNwf4(int iterations, int animationMode, int delayMillis, int initialDelayMillis, MarqueeSpacing spacing, float velocity) {
        setSpacing(spacing);
        m420setAnimationMode97h66l8(animationMode);
        if (this.iterations == iterations && this.delayMillis == delayMillis && this.initialDelayMillis == initialDelayMillis && Dp.equals-impl0(this.velocity, velocity)) {
            return;
        }
        this.iterations = iterations;
        this.delayMillis = delayMillis;
        this.initialDelayMillis = initialDelayMillis;
        this.velocity = velocity;
        restartAnimation();
    }

    public /* synthetic */ MarqueeModifierNode(int i, int i2, int i3, int i4, MarqueeSpacing marqueeSpacing, float f, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, i3, i4, marqueeSpacing, f);
    }
}

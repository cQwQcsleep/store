package androidx.compose.material3.internal;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DensityKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ!\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u0014\u001a\u00020\u0015¢\u0006\u0004\b \u0010!J!\u0010\u001d\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u0014\u001a\u00020\u0015¢\u0006\u0004\b\"\u0010!J!\u0010\u001e\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u0014\u001a\u00020\u0015¢\u0006\u0004\b#\u0010!J!\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u0014\u001a\u00020\u0015¢\u0006\u0004\b$\u0010!J\u0016\u0010%\u001a\u00020&2\u0006\u0010\u0002\u001a\u00020'H\u0086@¢\u0006\u0002\u0010(R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001a\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001e\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Landroidx/compose/material3/internal/AnimatedShapeState;", "", "shape", "Landroidx/compose/foundation/shape/RoundedCornerShape;", "spec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "", "<init>", "(Landroidx/compose/foundation/shape/RoundedCornerShape;Landroidx/compose/animation/core/FiniteAnimationSpec;)V", "getShape", "()Landroidx/compose/foundation/shape/RoundedCornerShape;", "getSpec", "()Landroidx/compose/animation/core/FiniteAnimationSpec;", "size", "Landroidx/compose/ui/geometry/Size;", "getSize-NH-jbRc", "()J", "setSize-uvyYCjk", "(J)V", "J", "density", "Landroidx/compose/ui/unit/Density;", "getDensity", "()Landroidx/compose/ui/unit/Density;", "setDensity", "(Landroidx/compose/ui/unit/Density;)V", "topStart", "Landroidx/compose/animation/core/Animatable;", "Landroidx/compose/animation/core/AnimationVector1D;", "topEnd", "bottomStart", "bottomEnd", "topStart-TmRCtEA", "(JLandroidx/compose/ui/unit/Density;)F", "topEnd-TmRCtEA", "bottomStart-TmRCtEA", "bottomEnd-TmRCtEA", "animateToShape", "Lkotlinx/coroutines/Job;", "Landroidx/compose/foundation/shape/CornerBasedShape;", "(Landroidx/compose/foundation/shape/CornerBasedShape;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class AnimatedShapeState {
    public static final int $stable = 0;
    private Animatable<Float, AnimationVector1D> bottomEnd;
    private Animatable<Float, AnimationVector1D> bottomStart;
    private final RoundedCornerShape shape;
    private final FiniteAnimationSpec<Float> spec;
    private Animatable<Float, AnimationVector1D> topEnd;
    private Animatable<Float, AnimationVector1D> topStart;
    private long size = Size.INSTANCE.m2967getZeroNHjbRc();
    private Density density = DensityKt.Density(0.0f, 0.0f);

    /* JADX INFO: renamed from: androidx.compose.material3.internal.AnimatedShapeState$animateToShape$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.compose.material3.internal.AnimatedShapeState$animateToShape$2", f = "AnimatedShape.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Job>, Object> {
        final /* synthetic */ CornerBasedShape $shape;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.material3.internal.AnimatedShapeState$animateToShape$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
        @DebugMetadata(c = "androidx.compose.material3.internal.AnimatedShapeState$animateToShape$2$1", f = "AnimatedShape.kt", i = {}, l = {82}, m = "invokeSuspend", n = {}, s = {})
        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ CornerBasedShape $shape;
            int label;
            final /* synthetic */ AnimatedShapeState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(AnimatedShapeState animatedShapeState, CornerBasedShape cornerBasedShape, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = animatedShapeState;
                this.$shape = cornerBasedShape;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, this.$shape, continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Animatable animatable = this.this$0.topStart;
                    if (animatable != null) {
                        Float fBoxFloat = Boxing.boxFloat(this.$shape.getTopStart().toPx-TmRCtEA(this.this$0.getSize(), this.this$0.getDensity()));
                        FiniteAnimationSpec<Float> spec = this.this$0.getSpec();
                        this.label = 1;
                        obj = Animatable.animateTo$default(animatable, fBoxFloat, spec, (Object) null, (Function1) null, this, 12, (Object) null);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
                if (i != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: renamed from: androidx.compose.material3.internal.AnimatedShapeState$animateToShape$2$2, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
        @DebugMetadata(c = "androidx.compose.material3.internal.AnimatedShapeState$animateToShape$2$2", f = "AnimatedShape.kt", i = {}, l = {83}, m = "invokeSuspend", n = {}, s = {})
        public static final class C00172 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ CornerBasedShape $shape;
            int label;
            final /* synthetic */ AnimatedShapeState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00172(AnimatedShapeState animatedShapeState, CornerBasedShape cornerBasedShape, Continuation<? super C00172> continuation) {
                super(2, continuation);
                this.this$0 = animatedShapeState;
                this.$shape = cornerBasedShape;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00172(this.this$0, this.$shape, continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Animatable animatable = this.this$0.topEnd;
                    if (animatable != null) {
                        Float fBoxFloat = Boxing.boxFloat(this.$shape.getTopEnd().toPx-TmRCtEA(this.this$0.getSize(), this.this$0.getDensity()));
                        FiniteAnimationSpec<Float> spec = this.this$0.getSpec();
                        this.label = 1;
                        obj = Animatable.animateTo$default(animatable, fBoxFloat, spec, (Object) null, (Function1) null, this, 12, (Object) null);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
                if (i != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: renamed from: androidx.compose.material3.internal.AnimatedShapeState$animateToShape$2$3, reason: invalid class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
        @DebugMetadata(c = "androidx.compose.material3.internal.AnimatedShapeState$animateToShape$2$3", f = "AnimatedShape.kt", i = {}, l = {84}, m = "invokeSuspend", n = {}, s = {})
        public static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ CornerBasedShape $shape;
            int label;
            final /* synthetic */ AnimatedShapeState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(AnimatedShapeState animatedShapeState, CornerBasedShape cornerBasedShape, Continuation<? super AnonymousClass3> continuation) {
                super(2, continuation);
                this.this$0 = animatedShapeState;
                this.$shape = cornerBasedShape;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass3(this.this$0, this.$shape, continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Animatable animatable = this.this$0.bottomStart;
                    if (animatable != null) {
                        Float fBoxFloat = Boxing.boxFloat(this.$shape.getBottomStart().toPx-TmRCtEA(this.this$0.getSize(), this.this$0.getDensity()));
                        FiniteAnimationSpec<Float> spec = this.this$0.getSpec();
                        this.label = 1;
                        obj = Animatable.animateTo$default(animatable, fBoxFloat, spec, (Object) null, (Function1) null, this, 12, (Object) null);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
                if (i != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: renamed from: androidx.compose.material3.internal.AnimatedShapeState$animateToShape$2$4, reason: invalid class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
        @DebugMetadata(c = "androidx.compose.material3.internal.AnimatedShapeState$animateToShape$2$4", f = "AnimatedShape.kt", i = {}, l = {85}, m = "invokeSuspend", n = {}, s = {})
        public static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ CornerBasedShape $shape;
            int label;
            final /* synthetic */ AnimatedShapeState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass4(AnimatedShapeState animatedShapeState, CornerBasedShape cornerBasedShape, Continuation<? super AnonymousClass4> continuation) {
                super(2, continuation);
                this.this$0 = animatedShapeState;
                this.$shape = cornerBasedShape;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass4(this.this$0, this.$shape, continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Animatable animatable = this.this$0.bottomEnd;
                    if (animatable != null) {
                        Float fBoxFloat = Boxing.boxFloat(this.$shape.getBottomEnd().toPx-TmRCtEA(this.this$0.getSize(), this.this$0.getDensity()));
                        FiniteAnimationSpec<Float> spec = this.this$0.getSpec();
                        this.label = 1;
                        obj = Animatable.animateTo$default(animatable, fBoxFloat, spec, (Object) null, (Function1) null, this, 12, (Object) null);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
                if (i != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(CornerBasedShape cornerBasedShape, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$shape = cornerBasedShape;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = AnimatedShapeState.this.new AnonymousClass2(this.$shape, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Job> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(AnimatedShapeState.this, this.$shape, null), 3, (Object) null);
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C00172(AnimatedShapeState.this, this.$shape, null), 3, (Object) null);
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass3(AnimatedShapeState.this, this.$shape, null), 3, (Object) null);
            return BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass4(AnimatedShapeState.this, this.$shape, null), 3, (Object) null);
        }
    }

    public AnimatedShapeState(RoundedCornerShape roundedCornerShape, FiniteAnimationSpec<Float> finiteAnimationSpec) {
        this.shape = roundedCornerShape;
        this.spec = finiteAnimationSpec;
    }

    /* JADX INFO: renamed from: bottomEnd-TmRCtEA$default, reason: not valid java name */
    public static /* synthetic */ float m1369bottomEndTmRCtEA$default(AnimatedShapeState animatedShapeState, long j, Density density, int i, Object obj) {
        if ((i & 1) != 0) {
            j = animatedShapeState.size;
        }
        if ((i & 2) != 0) {
            density = animatedShapeState.density;
        }
        return animatedShapeState.m1373bottomEndTmRCtEA(j, density);
    }

    /* JADX INFO: renamed from: bottomStart-TmRCtEA$default, reason: not valid java name */
    public static /* synthetic */ float m1370bottomStartTmRCtEA$default(AnimatedShapeState animatedShapeState, long j, Density density, int i, Object obj) {
        if ((i & 1) != 0) {
            j = animatedShapeState.size;
        }
        if ((i & 2) != 0) {
            density = animatedShapeState.density;
        }
        return animatedShapeState.m1374bottomStartTmRCtEA(j, density);
    }

    /* JADX INFO: renamed from: topEnd-TmRCtEA$default, reason: not valid java name */
    public static /* synthetic */ float m1371topEndTmRCtEA$default(AnimatedShapeState animatedShapeState, long j, Density density, int i, Object obj) {
        if ((i & 1) != 0) {
            j = animatedShapeState.size;
        }
        if ((i & 2) != 0) {
            density = animatedShapeState.density;
        }
        return animatedShapeState.m1377topEndTmRCtEA(j, density);
    }

    /* JADX INFO: renamed from: topStart-TmRCtEA$default, reason: not valid java name */
    public static /* synthetic */ float m1372topStartTmRCtEA$default(AnimatedShapeState animatedShapeState, long j, Density density, int i, Object obj) {
        if ((i & 1) != 0) {
            j = animatedShapeState.size;
        }
        if ((i & 2) != 0) {
            density = animatedShapeState.density;
        }
        return animatedShapeState.m1378topStartTmRCtEA(j, density);
    }

    public final Object animateToShape(CornerBasedShape cornerBasedShape, Continuation<? super Job> continuation) {
        return CoroutineScopeKt.coroutineScope(new AnonymousClass2(cornerBasedShape, null), continuation);
    }

    /* JADX INFO: renamed from: bottomEnd-TmRCtEA, reason: not valid java name */
    public final float m1373bottomEndTmRCtEA(long size, Density density) {
        Animatable<Float, AnimationVector1D> animatableAnimatable$default = this.bottomEnd;
        if (animatableAnimatable$default == null) {
            animatableAnimatable$default = AnimatableKt.Animatable$default(this.shape.getBottomEnd().toPx-TmRCtEA(size, density), 0.0f, 2, (Object) null);
            this.bottomEnd = animatableAnimatable$default;
        }
        return ((Number) animatableAnimatable$default.getValue()).floatValue();
    }

    /* JADX INFO: renamed from: bottomStart-TmRCtEA, reason: not valid java name */
    public final float m1374bottomStartTmRCtEA(long size, Density density) {
        Animatable<Float, AnimationVector1D> animatableAnimatable$default = this.bottomStart;
        if (animatableAnimatable$default == null) {
            animatableAnimatable$default = AnimatableKt.Animatable$default(this.shape.getBottomStart().toPx-TmRCtEA(size, density), 0.0f, 2, (Object) null);
            this.bottomStart = animatableAnimatable$default;
        }
        return ((Number) animatableAnimatable$default.getValue()).floatValue();
    }

    public final Density getDensity() {
        return this.density;
    }

    public final RoundedCornerShape getShape() {
        return this.shape;
    }

    /* JADX INFO: renamed from: getSize-NH-jbRc, reason: not valid java name and from getter */
    public final long getSize() {
        return this.size;
    }

    public final FiniteAnimationSpec<Float> getSpec() {
        return this.spec;
    }

    public final void setDensity(Density density) {
        this.density = density;
    }

    /* JADX INFO: renamed from: setSize-uvyYCjk, reason: not valid java name */
    public final void m1376setSizeuvyYCjk(long j) {
        this.size = j;
    }

    /* JADX INFO: renamed from: topEnd-TmRCtEA, reason: not valid java name */
    public final float m1377topEndTmRCtEA(long size, Density density) {
        Animatable<Float, AnimationVector1D> animatableAnimatable$default = this.topEnd;
        if (animatableAnimatable$default == null) {
            animatableAnimatable$default = AnimatableKt.Animatable$default(this.shape.getTopEnd().toPx-TmRCtEA(size, density), 0.0f, 2, (Object) null);
            this.topEnd = animatableAnimatable$default;
        }
        return ((Number) animatableAnimatable$default.getValue()).floatValue();
    }

    /* JADX INFO: renamed from: topStart-TmRCtEA, reason: not valid java name */
    public final float m1378topStartTmRCtEA(long size, Density density) {
        Animatable<Float, AnimationVector1D> animatableAnimatable$default = this.topStart;
        if (animatableAnimatable$default == null) {
            animatableAnimatable$default = AnimatableKt.Animatable$default(this.shape.getTopStart().toPx-TmRCtEA(size, density), 0.0f, 2, (Object) null);
            this.topStart = animatableAnimatable$default;
        }
        return ((Number) animatableAnimatable$default.getValue()).floatValue();
    }
}

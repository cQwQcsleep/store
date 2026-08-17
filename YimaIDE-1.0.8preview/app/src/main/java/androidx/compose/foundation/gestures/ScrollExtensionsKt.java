package androidx.compose.foundation.gestures;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a*\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0086@¢\u0006\u0002\u0010\u0006\u001a,\u0010\u0000\u001a\u00020\u0007*\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00072\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00070\u0005H\u0086@¢\u0006\u0004\b\t\u0010\n\u001a\u001a\u0010\u000b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0086@¢\u0006\u0002\u0010\f\u001a\u001c\u0010\u000b\u001a\u00020\u0007*\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0007H\u0086@¢\u0006\u0004\b\r\u0010\u000e\u001a\u001c\u0010\u000f\u001a\u00020\u0010*\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0086@¢\u0006\u0002\u0010\u0013\u001a\u001c\u0010\u000f\u001a\u00020\u0010*\u00020\b2\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0086@¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"animateScrollBy", "", "Landroidx/compose/foundation/gestures/ScrollableState;", "value", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "(Landroidx/compose/foundation/gestures/ScrollableState;FLandroidx/compose/animation/core/AnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/compose/ui/geometry/Offset;", "Landroidx/compose/foundation/gestures/Scrollable2DState;", "animateScrollBy-ubNVwUQ", "(Landroidx/compose/foundation/gestures/Scrollable2DState;JLandroidx/compose/animation/core/AnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scrollBy", "(Landroidx/compose/foundation/gestures/ScrollableState;FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scrollBy-d-4ec7I", "(Landroidx/compose/foundation/gestures/Scrollable2DState;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "stopScroll", "", "scrollPriority", "Landroidx/compose/foundation/MutatePriority;", "(Landroidx/compose/foundation/gestures/ScrollableState;Landroidx/compose/foundation/MutatePriority;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Landroidx/compose/foundation/gestures/Scrollable2DState;Landroidx/compose/foundation/MutatePriority;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ScrollExtensionsKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollExtensionsKt$animateScrollBy$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollExtensionsKt", f = "ScrollExtensions.kt", i = {0}, l = {40}, m = "animateScrollBy", n = {"previousValue"}, s = {"L$0"}, v = 1)
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ScrollExtensionsKt.animateScrollBy(null, 0.0f, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollExtensionsKt$animateScrollBy$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/ScrollScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollExtensionsKt$animateScrollBy$2", f = "ScrollExtensions.kt", i = {}, l = {41}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<ScrollScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ AnimationSpec<Float> $animationSpec;
        final /* synthetic */ Ref.FloatRef $previousValue;
        final /* synthetic */ float $value;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(float f, AnimationSpec<Float> animationSpec, Ref.FloatRef floatRef, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$value = f;
            this.$animationSpec = animationSpec;
            this.$previousValue = floatRef;
        }

        public static Unit b(Ref.FloatRef floatRef, ScrollScope scrollScope, float f, float f2) {
            float f3 = floatRef.element;
            floatRef.element = f3 + scrollScope.scrollBy(f - f3);
            return Unit.INSTANCE;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$value, this.$animationSpec, this.$previousValue, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        public final Object invoke(ScrollScope scrollScope, Continuation<? super Unit> continuation) {
            return create(scrollScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final ScrollScope scrollScope = (ScrollScope) this.L$0;
                float f = this.$value;
                AnimationSpec<Float> animationSpec = this.$animationSpec;
                final Ref.FloatRef floatRef = this.$previousValue;
                Function2 function2 = new Function2() { // from class: androidx.compose.foundation.gestures.y
                    public final Object invoke(Object obj2, Object obj3) {
                        return ScrollExtensionsKt.AnonymousClass2.b(floatRef, scrollScope, ((Float) obj2).floatValue(), ((Float) obj3).floatValue());
                    }
                };
                this.label = 1;
                if (SuspendAnimationKt.animate$default(0.0f, f, 0.0f, animationSpec, function2, this, 4, null) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollExtensionsKt$animateScrollBy$3, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollExtensionsKt", f = "ScrollExtensions.kt", i = {0}, l = {62}, m = "animateScrollBy-ubNVwUQ", n = {"previousValue"}, s = {"L$0"}, v = 1)
    public static final class AnonymousClass3 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ScrollExtensionsKt.m669animateScrollByubNVwUQ(null, 0L, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollExtensionsKt$animateScrollBy$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/Scroll2DScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollExtensionsKt$animateScrollBy$4", f = "ScrollExtensions.kt", i = {}, l = {63}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class AnonymousClass4 extends SuspendLambda implements Function2<Scroll2DScope, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: $$v$c$androidx-compose-ui-geometry-Offset$-value$0, reason: not valid java name */
        final /* synthetic */ long f14$$v$c$androidxcomposeuigeometryOffset$value$0;
        final /* synthetic */ AnimationSpec<Offset> $animationSpec;
        final /* synthetic */ Ref.LongRef $previousValue;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(long j, AnimationSpec<Offset> animationSpec, Ref.LongRef longRef, Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
            this.f14$$v$c$androidxcomposeuigeometryOffset$value$0 = j;
            this.$animationSpec = animationSpec;
            this.$previousValue = longRef;
        }

        public static Unit b(Ref.LongRef longRef, Scroll2DScope scroll2DScope, Offset offset, Offset offset2) {
            longRef.element = Offset.plus-MK-Hz9U(longRef.element, scroll2DScope.mo544scrollByMKHz9U(Offset.minus-MK-Hz9U(offset.unbox-impl(), longRef.element)));
            return Unit.INSTANCE;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(this.f14$$v$c$androidxcomposeuigeometryOffset$value$0, this.$animationSpec, this.$previousValue, continuation);
            anonymousClass4.L$0 = obj;
            return anonymousClass4;
        }

        public final Object invoke(Scroll2DScope scroll2DScope, Continuation<? super Unit> continuation) {
            return create(scroll2DScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final Scroll2DScope scroll2DScope = (Scroll2DScope) this.L$0;
                Offset.Companion companion = Offset.Companion;
                TwoWayConverter<Offset, AnimationVector2D> vectorConverter = VectorConvertersKt.getVectorConverter(companion);
                Offset offset = Offset.box-impl(companion.getZero-F1C5BW0());
                Offset offset2 = Offset.box-impl(this.f14$$v$c$androidxcomposeuigeometryOffset$value$0);
                AnimationSpec<Offset> animationSpec = this.$animationSpec;
                final Ref.LongRef longRef = this.$previousValue;
                Function2 function2 = new Function2() { // from class: androidx.compose.foundation.gestures.z
                    public final Object invoke(Object obj2, Object obj3) {
                        return ScrollExtensionsKt.AnonymousClass4.b(longRef, scroll2DScope, (Offset) obj2, (Offset) obj3);
                    }
                };
                this.label = 1;
                if (SuspendAnimationKt.animate$default(vectorConverter, offset, offset2, null, animationSpec, function2, this, 8, null) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollExtensionsKt$scrollBy$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollExtensionsKt", f = "ScrollExtensions.kt", i = {0}, l = {83}, m = "scrollBy", n = {"consumed"}, s = {"L$0"}, v = 1)
    public static final class C01181 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C01181(Continuation<? super C01181> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ScrollExtensionsKt.scrollBy(null, 0.0f, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollExtensionsKt$scrollBy$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/ScrollScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollExtensionsKt$scrollBy$2", f = "ScrollExtensions.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class C01192 extends SuspendLambda implements Function2<ScrollScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.FloatRef $consumed;
        final /* synthetic */ float $value;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C01192(Ref.FloatRef floatRef, float f, Continuation<? super C01192> continuation) {
            super(2, continuation);
            this.$consumed = floatRef;
            this.$value = f;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01192 c01192 = new C01192(this.$consumed, this.$value, continuation);
            c01192.L$0 = obj;
            return c01192;
        }

        public final Object invoke(ScrollScope scrollScope, Continuation<? super Unit> continuation) {
            return create(scrollScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            ScrollScope scrollScope = (ScrollScope) this.L$0;
            this.$consumed.element = scrollScope.scrollBy(this.$value);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollExtensionsKt$scrollBy$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollExtensionsKt", f = "ScrollExtensions.kt", i = {0}, l = {98}, m = "scrollBy-d-4ec7I", n = {"consumed"}, s = {"L$0"}, v = 1)
    public static final class C01203 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C01203(Continuation<? super C01203> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ScrollExtensionsKt.m671scrollByd4ec7I(null, 0L, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollExtensionsKt$scrollBy$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/Scroll2DScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollExtensionsKt$scrollBy$4", f = "ScrollExtensions.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class C01214 extends SuspendLambda implements Function2<Scroll2DScope, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: $$v$c$androidx-compose-ui-geometry-Offset$-value$0, reason: not valid java name */
        final /* synthetic */ long f15$$v$c$androidxcomposeuigeometryOffset$value$0;
        final /* synthetic */ Ref.LongRef $consumed;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C01214(Ref.LongRef longRef, long j, Continuation<? super C01214> continuation) {
            super(2, continuation);
            this.$consumed = longRef;
            this.f15$$v$c$androidxcomposeuigeometryOffset$value$0 = j;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01214 c01214 = new C01214(this.$consumed, this.f15$$v$c$androidxcomposeuigeometryOffset$value$0, continuation);
            c01214.L$0 = obj;
            return c01214;
        }

        public final Object invoke(Scroll2DScope scroll2DScope, Continuation<? super Unit> continuation) {
            return create(scroll2DScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            Scroll2DScope scroll2DScope = (Scroll2DScope) this.L$0;
            this.$consumed.element = scroll2DScope.mo544scrollByMKHz9U(this.f15$$v$c$androidxcomposeuigeometryOffset$value$0);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollExtensionsKt$stopScroll$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/ScrollScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollExtensionsKt$stopScroll$2", f = "ScrollExtensions.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class C01222 extends SuspendLambda implements Function2<ScrollScope, Continuation<? super Unit>, Object> {
        int label;

        public C01222(Continuation<? super C01222> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C01222(continuation);
        }

        public final Object invoke(ScrollScope scrollScope, Continuation<? super Unit> continuation) {
            return create(scrollScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            k2d.a("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollExtensionsKt$stopScroll$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/Scroll2DScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollExtensionsKt$stopScroll$4", f = "ScrollExtensions.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class C01234 extends SuspendLambda implements Function2<Scroll2DScope, Continuation<? super Unit>, Object> {
        int label;

        public C01234(Continuation<? super C01234> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C01234(continuation);
        }

        public final Object invoke(Scroll2DScope scroll2DScope, Continuation<? super Unit> continuation) {
            return create(scroll2DScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            k2d.a("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0014  */
    public static final Object animateScrollBy(ScrollableState scrollableState, float f, AnimationSpec<Float> animationSpec, Continuation<? super Float> continuation) {
        AnonymousClass1 anonymousClass1;
        Ref.FloatRef floatRef;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i = anonymousClass1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object obj = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass2.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            Ref.FloatRef floatRef2 = new Ref.FloatRef();
            AnonymousClass2 anonymousClass3 = new AnonymousClass2(f, animationSpec, floatRef2, null);
            anonymousClass2.L$0 = floatRef2;
            anonymousClass2.label = 1;
            if (ScrollableState.scroll$default(scrollableState, null, anonymousClass3, anonymousClass2, 1, null) == coroutine_suspended) {
                return coroutine_suspended;
            }
            floatRef = floatRef2;
        } else {
            if (i2 != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            floatRef = (Ref.FloatRef) anonymousClass2.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return Boxing.boxFloat(floatRef.element);
    }

    public static /* synthetic */ Object animateScrollBy$default(ScrollableState scrollableState, float f, AnimationSpec animationSpec, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            animationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
        }
        return animateScrollBy(scrollableState, f, animationSpec, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0014  */
    /* JADX INFO: renamed from: animateScrollBy-ubNVwUQ, reason: not valid java name */
    public static final Object m669animateScrollByubNVwUQ(Scrollable2DState scrollable2DState, long j, AnimationSpec<Offset> animationSpec, Continuation<? super Offset> continuation) {
        AnonymousClass3 anonymousClass3;
        Ref.LongRef longRef;
        if (continuation instanceof AnonymousClass3) {
            anonymousClass3 = (AnonymousClass3) continuation;
            int i = anonymousClass3.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                anonymousClass3.label = i - Integer.MIN_VALUE;
            } else {
                anonymousClass3 = new AnonymousClass3(continuation);
            }
        } else {
            anonymousClass3 = new AnonymousClass3(continuation);
        }
        AnonymousClass3 anonymousClass4 = anonymousClass3;
        Object obj = anonymousClass4.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass4.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            Ref.LongRef longRef2 = new Ref.LongRef();
            longRef2.element = Offset.Companion.getZero-F1C5BW0();
            AnonymousClass4 anonymousClass5 = new AnonymousClass4(j, animationSpec, longRef2, null);
            anonymousClass4.L$0 = longRef2;
            anonymousClass4.label = 1;
            if (Scrollable2DState.scroll$default(scrollable2DState, null, anonymousClass5, anonymousClass4, 1, null) == coroutine_suspended) {
                return coroutine_suspended;
            }
            longRef = longRef2;
        } else {
            if (i2 != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            longRef = (Ref.LongRef) anonymousClass4.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return Offset.box-impl(longRef.element);
    }

    /* JADX INFO: renamed from: animateScrollBy-ubNVwUQ$default, reason: not valid java name */
    public static /* synthetic */ Object m670animateScrollByubNVwUQ$default(Scrollable2DState scrollable2DState, long j, AnimationSpec animationSpec, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            animationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
        }
        return m669animateScrollByubNVwUQ(scrollable2DState, j, animationSpec, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0014  */
    public static final Object scrollBy(ScrollableState scrollableState, float f, Continuation<? super Float> continuation) {
        C01181 c01181;
        Ref.FloatRef floatRef;
        if (continuation instanceof C01181) {
            c01181 = (C01181) continuation;
            int i = c01181.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c01181.label = i - Integer.MIN_VALUE;
            } else {
                c01181 = new C01181(continuation);
            }
        } else {
            c01181 = new C01181(continuation);
        }
        C01181 c01182 = c01181;
        Object obj = c01182.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c01182.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            Ref.FloatRef floatRef2 = new Ref.FloatRef();
            C01192 c01192 = new C01192(floatRef2, f, null);
            c01182.L$0 = floatRef2;
            c01182.label = 1;
            if (ScrollableState.scroll$default(scrollableState, null, c01192, c01182, 1, null) == coroutine_suspended) {
                return coroutine_suspended;
            }
            floatRef = floatRef2;
        } else {
            if (i2 != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            floatRef = (Ref.FloatRef) c01182.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return Boxing.boxFloat(floatRef.element);
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0014  */
    /* JADX INFO: renamed from: scrollBy-d-4ec7I, reason: not valid java name */
    public static final Object m671scrollByd4ec7I(Scrollable2DState scrollable2DState, long j, Continuation<? super Offset> continuation) {
        C01203 c01203;
        Ref.LongRef longRef;
        if (continuation instanceof C01203) {
            c01203 = (C01203) continuation;
            int i = c01203.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c01203.label = i - Integer.MIN_VALUE;
            } else {
                c01203 = new C01203(continuation);
            }
        } else {
            c01203 = new C01203(continuation);
        }
        C01203 c01204 = c01203;
        Object obj = c01204.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c01204.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            Ref.LongRef longRef2 = new Ref.LongRef();
            longRef2.element = Offset.Companion.getZero-F1C5BW0();
            C01214 c01214 = new C01214(longRef2, j, null);
            c01204.L$0 = longRef2;
            c01204.label = 1;
            if (Scrollable2DState.scroll$default(scrollable2DState, null, c01214, c01204, 1, null) == coroutine_suspended) {
                return coroutine_suspended;
            }
            longRef = longRef2;
        } else {
            if (i2 != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            longRef = (Ref.LongRef) c01204.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return Offset.box-impl(longRef.element);
    }

    public static final Object stopScroll(ScrollableState scrollableState, MutatePriority mutatePriority, Continuation<? super Unit> continuation) {
        Object objScroll = scrollableState.scroll(mutatePriority, new C01222(null), continuation);
        return objScroll == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objScroll : Unit.INSTANCE;
    }

    public static /* synthetic */ Object stopScroll$default(ScrollableState scrollableState, MutatePriority mutatePriority, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return stopScroll(scrollableState, mutatePriority, (Continuation<? super Unit>) continuation);
    }

    public static /* synthetic */ Object stopScroll$default(Scrollable2DState scrollable2DState, MutatePriority mutatePriority, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return stopScroll(scrollable2DState, mutatePriority, (Continuation<? super Unit>) continuation);
    }

    public static final Object stopScroll(Scrollable2DState scrollable2DState, MutatePriority mutatePriority, Continuation<? super Unit> continuation) {
        Object objScroll = scrollable2DState.scroll(mutatePriority, new C01234(null), continuation);
        return objScroll == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objScroll : Unit.INSTANCE;
    }
}

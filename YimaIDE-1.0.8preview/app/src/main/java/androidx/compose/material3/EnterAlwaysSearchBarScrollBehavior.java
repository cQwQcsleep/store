package androidx.compose.material3;

import androidx.compose.animation.core.AnimationScope;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.AnimationStateKt;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.foundation.gestures.DraggableKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.material3.EnterAlwaysSearchBarScrollBehavior;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.saveable.ListSaverKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.OnRemeasuredModifierKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.Velocity;
import androidx.compose.ui.unit.VelocityKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u0000 02\u00020\u0001:\u00010BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f¢\u0006\u0004\b\r\u0010\u000eJ\f\u0010%\u001a\u00020&*\u00020&H\u0016J\u0018\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u0003H\u0082@¢\u0006\u0004\b.\u0010/R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00038V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR+\u0010 \u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u00038V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b#\u0010$\u001a\u0004\b!\u0010\u001c\"\u0004\b\"\u0010\u001eR\u0014\u0010'\u001a\u00020(X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*¨\u00061"}, d2 = {"Landroidx/compose/material3/EnterAlwaysSearchBarScrollBehavior;", "Landroidx/compose/material3/SearchBarScrollBehavior;", "initialOffset", "", "initialOffsetLimit", "canScroll", "Lkotlin/Function0;", "", "reverseLayout", "snapAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "flingAnimationSpec", "Landroidx/compose/animation/core/DecayAnimationSpec;", "<init>", "(FFLkotlin/jvm/functions/Function0;ZLandroidx/compose/animation/core/AnimationSpec;Landroidx/compose/animation/core/DecayAnimationSpec;)V", "getCanScroll", "()Lkotlin/jvm/functions/Function0;", "getReverseLayout", "()Z", "getSnapAnimationSpec", "()Landroidx/compose/animation/core/AnimationSpec;", "getFlingAnimationSpec", "()Landroidx/compose/animation/core/DecayAnimationSpec;", "_offset", "Landroidx/compose/runtime/MutableFloatState;", "newOffset", "scrollOffset", "getScrollOffset", "()F", "setScrollOffset", "(F)V", "<set-?>", "scrollOffsetLimit", "getScrollOffsetLimit", "setScrollOffsetLimit", "scrollOffsetLimit$delegate", "Landroidx/compose/runtime/MutableFloatState;", "searchBarScrollBehavior", "Landroidx/compose/ui/Modifier;", "nestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "getNestedScrollConnection", "()Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "settleSearchBar", "Landroidx/compose/ui/unit/Velocity;", "velocity", "settleSearchBar-OhffZ5M", "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class EnterAlwaysSearchBarScrollBehavior implements SearchBarScrollBehavior {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private MutableFloatState _offset;
    private final Function0<Boolean> canScroll;
    private final DecayAnimationSpec<Float> flingAnimationSpec;
    private final NestedScrollConnection nestedScrollConnection = new NestedScrollConnection() { // from class: androidx.compose.material3.EnterAlwaysSearchBarScrollBehavior$nestedScrollConnection$1
        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
        public Object mo428onPostFlingRZ2iAVY(long j, long j2, Continuation<? super Velocity> continuation) {
            return !((Boolean) this.this$0.getCanScroll().invoke()).booleanValue() ? Velocity.m6251boximpl(Velocity.INSTANCE.m6271getZero9UxMQ8M()) : this.this$0.m432settleSearchBarOhffZ5M(Velocity.m6261getYimpl(j2), continuation);
        }

        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
        public long mo429onPostScrollDzOQY0M(long consumed, long available, int source) {
            if (!((Boolean) this.this$0.getCanScroll().invoke()).booleanValue()) {
                return Offset.INSTANCE.m2905getZeroF1C5BW0();
            }
            if (this.this$0.getReverseLayout()) {
                int i = (int) (available & 4294967295L);
                if (Float.intBitsToFloat(i) > 0.0f) {
                    EnterAlwaysSearchBarScrollBehavior enterAlwaysSearchBarScrollBehavior = this.this$0;
                    enterAlwaysSearchBarScrollBehavior.setScrollOffset(enterAlwaysSearchBarScrollBehavior.getScrollOffset() + Float.intBitsToFloat(i));
                    return Offset.m2883copydBAh8RU$default(available, 0.0f, 0.0f, 2, null);
                }
            }
            if (!this.this$0.getReverseLayout()) {
                EnterAlwaysSearchBarScrollBehavior enterAlwaysSearchBarScrollBehavior2 = this.this$0;
                enterAlwaysSearchBarScrollBehavior2.setScrollOffset(enterAlwaysSearchBarScrollBehavior2.getScrollOffset() + Float.intBitsToFloat((int) (consumed & 4294967295L)));
            }
            return Offset.INSTANCE.m2905getZeroF1C5BW0();
        }

        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* JADX INFO: renamed from: onPreScroll-OzD1aCk */
        public long mo430onPreScrollOzD1aCk(long available, int source) {
            if (!((Boolean) this.this$0.getCanScroll().invoke()).booleanValue()) {
                return Offset.INSTANCE.m2905getZeroF1C5BW0();
            }
            float scrollOffset = this.this$0.getScrollOffset();
            EnterAlwaysSearchBarScrollBehavior enterAlwaysSearchBarScrollBehavior = this.this$0;
            enterAlwaysSearchBarScrollBehavior.setScrollOffset(enterAlwaysSearchBarScrollBehavior.getScrollOffset() + Float.intBitsToFloat((int) (4294967295L & available)));
            return (this.this$0.getReverseLayout() || scrollOffset == this.this$0.getScrollOffset()) ? Offset.INSTANCE.m2905getZeroF1C5BW0() : Offset.m2883copydBAh8RU$default(available, 0.0f, 0.0f, 2, null);
        }
    };
    private final boolean reverseLayout;

    /* JADX INFO: renamed from: scrollOffsetLimit$delegate, reason: from kotlin metadata */
    private final MutableFloatState scrollOffsetLimit;
    private final AnimationSpec<Float> snapAnimationSpec;

    public EnterAlwaysSearchBarScrollBehavior(float f, float f2, Function0<Boolean> function0, boolean z, AnimationSpec<Float> animationSpec, DecayAnimationSpec<Float> decayAnimationSpec) {
        this.canScroll = function0;
        this.reverseLayout = z;
        this.snapAnimationSpec = animationSpec;
        this.flingAnimationSpec = decayAnimationSpec;
        this._offset = PrimitiveSnapshotStateKt.mutableFloatStateOf(f);
        this.scrollOffsetLimit = PrimitiveSnapshotStateKt.mutableFloatStateOf(f2);
    }

    public static Unit a(Ref.FloatRef floatRef, EnterAlwaysSearchBarScrollBehavior enterAlwaysSearchBarScrollBehavior, Ref.FloatRef floatRef2, AnimationScope animationScope) {
        float fFloatValue = ((Number) animationScope.getValue()).floatValue() - floatRef.element;
        float scrollOffset = enterAlwaysSearchBarScrollBehavior.getScrollOffset();
        enterAlwaysSearchBarScrollBehavior.setScrollOffset(scrollOffset + fFloatValue);
        float fAbs = Math.abs(scrollOffset - enterAlwaysSearchBarScrollBehavior.getScrollOffset());
        floatRef.element = ((Number) animationScope.getValue()).floatValue();
        floatRef2.element = ((Number) animationScope.getVelocity()).floatValue();
        if (Math.abs(fFloatValue - fAbs) > 0.5f) {
            animationScope.cancelAnimation();
        }
        return Unit.INSTANCE;
    }

    public static Unit b(Placeable placeable, int i, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.placeWithLayer$default(placementScope, placeable, 0, i, 0.0f, (Function1) null, 12, (Object) null);
        return Unit.INSTANCE;
    }

    public static Unit c(EnterAlwaysSearchBarScrollBehavior enterAlwaysSearchBarScrollBehavior, AnimationScope animationScope) {
        enterAlwaysSearchBarScrollBehavior.setScrollOffset(((Number) animationScope.getValue()).floatValue());
        return Unit.INSTANCE;
    }

    public static MeasureResult d(EnterAlwaysSearchBarScrollBehavior enterAlwaysSearchBarScrollBehavior, MeasureScope measureScope, Measurable measurable, Constraints constraints) {
        final Placeable placeableMo4605measureBRTryo0 = measurable.mo4605measureBRTryo0(constraints.getValue());
        final int iRoundToInt = MathKt.roundToInt(enterAlwaysSearchBarScrollBehavior.getScrollOffset());
        return MeasureScope.layout$default(measureScope, placeableMo4605measureBRTryo0.getWidth(), RangesKt.coerceAtLeast(placeableMo4605measureBRTryo0.getHeight() + iRoundToInt, 0), null, new Function1() { // from class: androidx.compose.material3.l1
            public final Object invoke(Object obj) {
                return EnterAlwaysSearchBarScrollBehavior.b(placeableMo4605measureBRTryo0, iRoundToInt, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    public static Unit e(EnterAlwaysSearchBarScrollBehavior enterAlwaysSearchBarScrollBehavior, float f) {
        enterAlwaysSearchBarScrollBehavior.setScrollOffset(enterAlwaysSearchBarScrollBehavior.getScrollOffset() + f);
        return Unit.INSTANCE;
    }

    public static Unit f(EnterAlwaysSearchBarScrollBehavior enterAlwaysSearchBarScrollBehavior, IntSize intSize) {
        enterAlwaysSearchBarScrollBehavior.setScrollOffsetLimit(-((int) (intSize.m6197unboximpl() & 4294967295L)));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:39:0x00df  */
    /* JADX WARN: Code duplicated, block: B:40:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:44:0x0100  */
    /* JADX WARN: Code duplicated, block: B:8:0x0018  */
    /* JADX INFO: renamed from: settleSearchBar-OhffZ5M, reason: not valid java name */
    public final Object m432settleSearchBarOhffZ5M(float f, Continuation<? super Velocity> continuation) {
        EnterAlwaysSearchBarScrollBehavior$settleSearchBar$1 enterAlwaysSearchBarScrollBehavior$settleSearchBar$1;
        float scrollOffset;
        final Ref.FloatRef floatRef;
        float f2;
        Ref.FloatRef floatRef2;
        AnimationState animationStateAnimationState$default;
        float scrollOffsetLimit;
        Float fBoxFloat;
        AnimationSpec<Float> animationSpec;
        Function1 function1;
        Ref.FloatRef floatRef3;
        if (continuation instanceof EnterAlwaysSearchBarScrollBehavior$settleSearchBar$1) {
            enterAlwaysSearchBarScrollBehavior$settleSearchBar$1 = (EnterAlwaysSearchBarScrollBehavior$settleSearchBar$1) continuation;
            int i = enterAlwaysSearchBarScrollBehavior$settleSearchBar$1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                enterAlwaysSearchBarScrollBehavior$settleSearchBar$1.label = i - Integer.MIN_VALUE;
            } else {
                enterAlwaysSearchBarScrollBehavior$settleSearchBar$1 = new EnterAlwaysSearchBarScrollBehavior$settleSearchBar$1(this, continuation);
            }
        } else {
            enterAlwaysSearchBarScrollBehavior$settleSearchBar$1 = new EnterAlwaysSearchBarScrollBehavior$settleSearchBar$1(this, continuation);
        }
        EnterAlwaysSearchBarScrollBehavior$settleSearchBar$1 enterAlwaysSearchBarScrollBehavior$settleSearchBar$2 = enterAlwaysSearchBarScrollBehavior$settleSearchBar$1;
        Object obj = enterAlwaysSearchBarScrollBehavior$settleSearchBar$2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = enterAlwaysSearchBarScrollBehavior$settleSearchBar$2.label;
        if (i2 != 0) {
            if (i2 == 1) {
                f2 = enterAlwaysSearchBarScrollBehavior$settleSearchBar$2.F$0;
                floatRef2 = (Ref.FloatRef) enterAlwaysSearchBarScrollBehavior$settleSearchBar$2.L$0;
                ResultKt.throwOnFailure(obj);
            } else {
                if (i2 != 2) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                floatRef3 = (Ref.FloatRef) enterAlwaysSearchBarScrollBehavior$settleSearchBar$2.L$0;
                ResultKt.throwOnFailure(obj);
            }
            floatRef = floatRef3;
            return Velocity.m6251boximpl(VelocityKt.Velocity(0.0f, floatRef.element));
        }
        ResultKt.throwOnFailure(obj);
        scrollOffset = getScrollOffsetLimit() == 0.0f ? 0.0f : getScrollOffset() / getScrollOffsetLimit();
        if (scrollOffset < 0.01f || scrollOffset == 1.0f) {
            return Velocity.m6251boximpl(Velocity.INSTANCE.m6271getZero9UxMQ8M());
        }
        floatRef = new Ref.FloatRef();
        floatRef.element = f;
        if (Math.abs(f) <= 1.0f) {
            if (getScrollOffsetLimit() < getScrollOffset() && getScrollOffset() < 0.0f) {
                animationStateAnimationState$default = AnimationStateKt.AnimationState$default(getScrollOffset(), 0.0f, 0L, 0L, false, 30, (Object) null);
                if (scrollOffset < 0.5f) {
                    scrollOffsetLimit = 0.0f;
                } else {
                    scrollOffsetLimit = getScrollOffsetLimit();
                }
                fBoxFloat = Boxing.boxFloat(scrollOffsetLimit);
                animationSpec = this.snapAnimationSpec;
                function1 = new Function1() { // from class: androidx.compose.material3.k1
                    public final Object invoke(Object obj2) {
                        return EnterAlwaysSearchBarScrollBehavior.c(this.b, (AnimationScope) obj2);
                    }
                };
                enterAlwaysSearchBarScrollBehavior$settleSearchBar$2.L$0 = floatRef;
                enterAlwaysSearchBarScrollBehavior$settleSearchBar$2.label = 2;
                if (SuspendAnimationKt.animateTo$default(animationStateAnimationState$default, fBoxFloat, animationSpec, false, function1, enterAlwaysSearchBarScrollBehavior$settleSearchBar$2, 4, (Object) null) != coroutine_suspended) {
                    floatRef3 = floatRef;
                    floatRef = floatRef3;
                }
            }
            return Velocity.m6251boximpl(VelocityKt.Velocity(0.0f, floatRef.element));
        }
        final Ref.FloatRef floatRef4 = new Ref.FloatRef();
        AnimationState animationStateAnimationState$default2 = AnimationStateKt.AnimationState$default(0.0f, f, 0L, 0L, false, 28, (Object) null);
        DecayAnimationSpec<Float> decayAnimationSpec = this.flingAnimationSpec;
        Function1 function2 = new Function1() { // from class: androidx.compose.material3.j1
            public final Object invoke(Object obj2) {
                return EnterAlwaysSearchBarScrollBehavior.a(floatRef4, this, floatRef, (AnimationScope) obj2);
            }
        };
        enterAlwaysSearchBarScrollBehavior$settleSearchBar$2.L$0 = floatRef;
        enterAlwaysSearchBarScrollBehavior$settleSearchBar$2.F$0 = scrollOffset;
        enterAlwaysSearchBarScrollBehavior$settleSearchBar$2.label = 1;
        if (SuspendAnimationKt.animateDecay$default(animationStateAnimationState$default2, decayAnimationSpec, false, function2, enterAlwaysSearchBarScrollBehavior$settleSearchBar$2, 2, (Object) null) != coroutine_suspended) {
            f2 = scrollOffset;
            floatRef2 = floatRef;
        }
        return coroutine_suspended;
        scrollOffset = f2;
        floatRef = floatRef2;
        if (getScrollOffsetLimit() < getScrollOffset()) {
            animationStateAnimationState$default = AnimationStateKt.AnimationState$default(getScrollOffset(), 0.0f, 0L, 0L, false, 30, (Object) null);
            if (scrollOffset < 0.5f) {
                scrollOffsetLimit = 0.0f;
            } else {
                scrollOffsetLimit = getScrollOffsetLimit();
            }
            fBoxFloat = Boxing.boxFloat(scrollOffsetLimit);
            animationSpec = this.snapAnimationSpec;
            function1 = new Function1() { // from class: androidx.compose.material3.k1
                public final Object invoke(Object obj2) {
                    return EnterAlwaysSearchBarScrollBehavior.c(this.b, (AnimationScope) obj2);
                }
            };
            enterAlwaysSearchBarScrollBehavior$settleSearchBar$2.L$0 = floatRef;
            enterAlwaysSearchBarScrollBehavior$settleSearchBar$2.label = 2;
            if (SuspendAnimationKt.animateTo$default(animationStateAnimationState$default, fBoxFloat, animationSpec, false, function1, enterAlwaysSearchBarScrollBehavior$settleSearchBar$2, 4, (Object) null) != coroutine_suspended) {
                floatRef3 = floatRef;
                floatRef = floatRef3;
            }
            return coroutine_suspended;
        }
        return Velocity.m6251boximpl(VelocityKt.Velocity(0.0f, floatRef.element));
    }

    public final Function0<Boolean> getCanScroll() {
        return this.canScroll;
    }

    public final DecayAnimationSpec<Float> getFlingAnimationSpec() {
        return this.flingAnimationSpec;
    }

    @Override // androidx.compose.material3.SearchBarScrollBehavior
    public NestedScrollConnection getNestedScrollConnection() {
        return this.nestedScrollConnection;
    }

    public final boolean getReverseLayout() {
        return this.reverseLayout;
    }

    @Override // androidx.compose.material3.SearchBarScrollBehavior
    public float getScrollOffset() {
        return this._offset.getFloatValue();
    }

    @Override // androidx.compose.material3.SearchBarScrollBehavior
    public float getScrollOffsetLimit() {
        return this.scrollOffsetLimit.getFloatValue();
    }

    public final AnimationSpec<Float> getSnapAnimationSpec() {
        return this.snapAnimationSpec;
    }

    @Override // androidx.compose.material3.SearchBarScrollBehavior
    public Modifier searchBarScrollBehavior(Modifier modifier) {
        return OnRemeasuredModifierKt.onSizeChanged(LayoutModifierKt.layout(ClipKt.clipToBounds(DraggableKt.draggable$default(modifier, DraggableKt.DraggableState(new Function1() { // from class: androidx.compose.material3.m1
            public final Object invoke(Object obj) {
                return EnterAlwaysSearchBarScrollBehavior.e(this.b, ((Float) obj).floatValue());
            }
        }), Orientation.Vertical, ((Boolean) this.canScroll.invoke()).booleanValue(), (MutableInteractionSource) null, false, (Function3) null, new AnonymousClass2(null), false, 184, (Object) null)), new Function3() { // from class: androidx.compose.material3.n1
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return EnterAlwaysSearchBarScrollBehavior.d(this.b, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
            }
        }), new Function1() { // from class: androidx.compose.material3.o1
            public final Object invoke(Object obj) {
                return EnterAlwaysSearchBarScrollBehavior.f(this.b, (IntSize) obj);
            }
        });
    }

    @Override // androidx.compose.material3.SearchBarScrollBehavior
    public void setScrollOffset(float f) {
        this._offset.setFloatValue(RangesKt.coerceIn(f, getScrollOffsetLimit(), 0.0f));
    }

    @Override // androidx.compose.material3.SearchBarScrollBehavior
    public void setScrollOffsetLimit(float f) {
        this.scrollOffsetLimit.setFloatValue(f);
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J:\u0010\u0004\u001a\f\u0012\u0004\u0012\u00020\u0006\u0012\u0002\b\u00030\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000e¨\u0006\u000f"}, d2 = {"Landroidx/compose/material3/EnterAlwaysSearchBarScrollBehavior$Companion;", "", "<init>", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/material3/EnterAlwaysSearchBarScrollBehavior;", "canScroll", "Lkotlin/Function0;", "", "snapAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "flingAnimationSpec", "Landroidx/compose/animation/core/DecayAnimationSpec;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static EnterAlwaysSearchBarScrollBehavior a(Function0 function0, AnimationSpec animationSpec, DecayAnimationSpec decayAnimationSpec, List list) {
            Object obj = list.get(0);
            obj.getClass();
            float fFloatValue = ((Float) obj).floatValue();
            Object obj2 = list.get(1);
            obj2.getClass();
            float fFloatValue2 = ((Float) obj2).floatValue();
            Object obj3 = list.get(2);
            obj3.getClass();
            return new EnterAlwaysSearchBarScrollBehavior(fFloatValue, fFloatValue2, function0, ((Boolean) obj3).booleanValue(), animationSpec, decayAnimationSpec);
        }

        public static List b(SaverScope saverScope, EnterAlwaysSearchBarScrollBehavior enterAlwaysSearchBarScrollBehavior) {
            return CollectionsKt.listOf(new Object[]{Float.valueOf(enterAlwaysSearchBarScrollBehavior.getScrollOffset()), Float.valueOf(enterAlwaysSearchBarScrollBehavior.getScrollOffsetLimit()), Boolean.valueOf(enterAlwaysSearchBarScrollBehavior.getReverseLayout())});
        }

        public final Saver<EnterAlwaysSearchBarScrollBehavior, ?> Saver(final Function0<Boolean> canScroll, final AnimationSpec<Float> snapAnimationSpec, final DecayAnimationSpec<Float> flingAnimationSpec) {
            return ListSaverKt.listSaver(new Function2() { // from class: androidx.compose.material3.p1
                public final Object invoke(Object obj, Object obj2) {
                    return EnterAlwaysSearchBarScrollBehavior.Companion.b((SaverScope) obj, (EnterAlwaysSearchBarScrollBehavior) obj2);
                }
            }, new Function1() { // from class: qb4
                public final Object invoke(Object obj) {
                    return EnterAlwaysSearchBarScrollBehavior.Companion.a(canScroll, snapAnimationSpec, flingAnimationSpec, (List) obj);
                }
            });
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.EnterAlwaysSearchBarScrollBehavior$searchBarScrollBehavior$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "velocity", ""}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.compose.material3.EnterAlwaysSearchBarScrollBehavior$searchBarScrollBehavior$2", f = "SearchBar.kt", i = {}, l = {904}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass2 extends SuspendLambda implements Function3<CoroutineScope, Float, Continuation<? super Unit>, Object> {
        /* synthetic */ float F$0;
        int label;

        public AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(3, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, float f, Continuation<? super Unit> continuation) {
            AnonymousClass2 anonymousClass2 = EnterAlwaysSearchBarScrollBehavior.this.new AnonymousClass2(continuation);
            anonymousClass2.F$0 = f;
            return anonymousClass2.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                float f = this.F$0;
                EnterAlwaysSearchBarScrollBehavior enterAlwaysSearchBarScrollBehavior = EnterAlwaysSearchBarScrollBehavior.this;
                this.label = 1;
                if (enterAlwaysSearchBarScrollBehavior.m432settleSearchBarOhffZ5M(f, this) == coroutine_suspended) {
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

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Float f, Continuation<? super Unit> continuation) {
            return invoke(coroutineScope, f.floatValue(), continuation);
        }
    }
}

package androidx.compose.material3;

import androidx.compose.animation.ColorVectorConverterKt;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.AnimationVector4D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.interaction.FocusInteraction;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.text.selection.TextSelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.material3.IndicatorLineNode;
import androidx.compose.material3.tokens.FilledTextFieldTokens;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.ui.draw.CacheDrawModifierNode;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.OutlineKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u00012\u00020\u0002BC\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010JI\u0010%\u001a\u00020&2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b'\u0010(J\b\u0010,\u001a\u00020&H\u0016J\u000e\u0010-\u001a\u00020&H\u0082@¢\u0006\u0002\u0010.J\b\u0010/\u001a\u00020&H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0011R\u0010\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u001d\u001a\u0004\u0018\u00010\u000b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u000b@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u001a\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020$0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010)\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+R\u000e\u00100\u001a\u000201X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Landroidx/compose/material3/IndicatorLineNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "enabled", "", "isError", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "colors", "Landroidx/compose/material3/TextFieldColors;", "textFieldShape", "Landroidx/compose/ui/graphics/Shape;", "focusedIndicatorWidth", "Landroidx/compose/ui/unit/Dp;", "unfocusedIndicatorWidth", "<init>", "(ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/ui/graphics/Shape;FFLkotlin/jvm/internal/DefaultConstructorMarker;)V", "F", "focused", "trackFocusStateJob", "Lkotlinx/coroutines/Job;", "_colors", "getColors", "()Landroidx/compose/material3/TextFieldColors;", "colorAnimatable", "Landroidx/compose/animation/core/Animatable;", "Landroidx/compose/ui/graphics/Color;", "Landroidx/compose/animation/core/AnimationVector4D;", "value", "_shape", "set_shape", "(Landroidx/compose/ui/graphics/Shape;)V", "shape", "getShape", "()Landroidx/compose/ui/graphics/Shape;", "widthAnimatable", "Landroidx/compose/animation/core/AnimationVector1D;", "update", "", "update-gv0btCI", "(ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/ui/graphics/Shape;FF)V", "shouldAutoInvalidate", "getShouldAutoInvalidate", "()Z", "onAttach", "trackFocusState", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "invalidateIndicator", "drawWithCacheModifierNode", "Landroidx/compose/ui/draw/CacheDrawModifierNode;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class IndicatorLineNode extends DelegatingNode implements CompositionLocalConsumerModifierNode {
    public static final int $stable = 8;
    private TextFieldColors _colors;
    private Shape _shape;
    private Animatable<Color, AnimationVector4D> colorAnimatable;
    private final CacheDrawModifierNode drawWithCacheModifierNode;
    private boolean enabled;
    private boolean focused;
    private float focusedIndicatorWidth;
    private InteractionSource interactionSource;
    private boolean isError;
    private Job trackFocusStateJob;
    private float unfocusedIndicatorWidth;
    private final Animatable<Dp, AnimationVector1D> widthAnimatable;

    /* JADX INFO: renamed from: androidx.compose.material3.IndicatorLineNode$invalidateIndicator$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.compose.material3.IndicatorLineNode$invalidateIndicator$1", f = "TextField.kt", i = {}, l = {1599}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return IndicatorLineNode.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Animatable animatable = IndicatorLineNode.this.colorAnimatable;
                if (animatable != null) {
                    Color colorM3124boximpl = Color.m3124boximpl(IndicatorLineNode.this.getColors().m1057indicatorColorXeAY9LY$material3(IndicatorLineNode.this.enabled, IndicatorLineNode.this.isError, IndicatorLineNode.this.focused));
                    FiniteAnimationSpec finiteAnimationSpecFromToken = IndicatorLineNode.this.enabled ? MotionSchemeKt.fromToken((MotionScheme) CompositionLocalConsumerModifierNodeKt.currentValueOf(IndicatorLineNode.this, MaterialTheme.INSTANCE.getLocalMotionScheme$material3()), MotionSchemeKeyTokens.FastEffects) : AnimationSpecKt.snap$default(0, 1, (Object) null);
                    this.label = 1;
                    obj = Animatable.animateTo$default(animatable, colorM3124boximpl, finiteAnimationSpecFromToken, (Object) null, (Function1) null, this, 12, (Object) null);
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

    /* JADX INFO: renamed from: androidx.compose.material3.IndicatorLineNode$invalidateIndicator$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.compose.material3.IndicatorLineNode$invalidateIndicator$2", f = "TextField.kt", i = {}, l = {1611}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return IndicatorLineNode.this.new AnonymousClass2(continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Animatable animatable = IndicatorLineNode.this.widthAnimatable;
                Dp dpM6020boximpl = Dp.m6020boximpl((IndicatorLineNode.this.focused && IndicatorLineNode.this.enabled) ? IndicatorLineNode.this.focusedIndicatorWidth : IndicatorLineNode.this.unfocusedIndicatorWidth);
                FiniteAnimationSpec finiteAnimationSpecFromToken = IndicatorLineNode.this.enabled ? MotionSchemeKt.fromToken((MotionScheme) CompositionLocalConsumerModifierNodeKt.currentValueOf(IndicatorLineNode.this, MaterialTheme.INSTANCE.getLocalMotionScheme$material3()), MotionSchemeKeyTokens.FastSpatial) : AnimationSpecKt.snap$default(0, 1, (Object) null);
                this.label = 1;
                if (Animatable.animateTo$default(animatable, dpM6020boximpl, finiteAnimationSpecFromToken, (Object) null, (Function1) null, this, 12, (Object) null) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: androidx.compose.material3.IndicatorLineNode$onAttach$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.compose.material3.IndicatorLineNode$onAttach$1", f = "TextField.kt", i = {}, l = {1569}, m = "invokeSuspend", n = {}, s = {})
    public static final class C00491 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public C00491(Continuation<? super C00491> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return IndicatorLineNode.this.new C00491(continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                IndicatorLineNode indicatorLineNode = IndicatorLineNode.this;
                this.label = 1;
                if (indicatorLineNode.trackFocusState(this) == coroutine_suspended) {
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

    private IndicatorLineNode(boolean z, boolean z2, InteractionSource interactionSource, TextFieldColors textFieldColors, Shape shape, float f, float f2) {
        this.enabled = z;
        this.isError = z2;
        this.interactionSource = interactionSource;
        this.focusedIndicatorWidth = f;
        this.unfocusedIndicatorWidth = f2;
        this._colors = textFieldColors;
        this._shape = shape;
        this.widthAnimatable = new Animatable<>(Dp.m6020boximpl((this.focused && this.enabled) ? this.focusedIndicatorWidth : this.unfocusedIndicatorWidth), VectorConvertersKt.getVectorConverter(Dp.INSTANCE), (Object) null, (String) null, 12, (DefaultConstructorMarker) null);
        this.drawWithCacheModifierNode = (CacheDrawModifierNode) delegate(DrawModifierKt.CacheDrawModifierNode(new Function1() { // from class: dn6
            public final Object invoke(Object obj) {
                return IndicatorLineNode.a(this.b, (CacheDrawScope) obj);
            }
        }));
    }

    public static DrawResult a(final IndicatorLineNode indicatorLineNode, CacheDrawScope cacheDrawScope) {
        float fMo4557toPx0680j_4 = cacheDrawScope.mo4557toPx0680j_4(((Dp) indicatorLineNode.widthAnimatable.getValue()).m6036unboximpl());
        Path Path = AndroidPath_androidKt.Path();
        OutlineKt.addOutline(Path, indicatorLineNode.getShape().mo44createOutlinePq9zytI(cacheDrawScope.m2664getSizeNHjbRc(), cacheDrawScope.getLayoutDirection(), cacheDrawScope));
        Path Path2 = AndroidPath_androidKt.Path();
        Path.addRect$default(Path2, new Rect(0.0f, Float.intBitsToFloat((int) (cacheDrawScope.m2664getSizeNHjbRc() & 4294967295L)) - fMo4557toPx0680j_4, Float.intBitsToFloat((int) (cacheDrawScope.m2664getSizeNHjbRc() >> 32)), Float.intBitsToFloat((int) (cacheDrawScope.m2664getSizeNHjbRc() & 4294967295L))), null, 2, null);
        final Path pathAnd = Path2.and(Path);
        return cacheDrawScope.onDrawWithContent(new Function1() { // from class: en6
            public final Object invoke(Object obj) {
                return IndicatorLineNode.b(pathAnd, indicatorLineNode, (ContentDrawScope) obj);
            }
        });
    }

    public static Unit b(Path path, IndicatorLineNode indicatorLineNode, ContentDrawScope contentDrawScope) {
        contentDrawScope.drawContent();
        Animatable<Color, AnimationVector4D> animatable = indicatorLineNode.colorAnimatable;
        animatable.getClass();
        DrawScope.m3697drawPathGBMwjPU$default(contentDrawScope, path, new SolidColor(((Color) animatable.getValue()).m3144unboximpl(), null), 0.0f, null, null, 0, 60, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TextFieldColors getColors() {
        TextFieldColors textFieldColors = this._colors;
        return textFieldColors == null ? TextFieldDefaults.INSTANCE.defaultTextFieldColors$material3((ColorScheme) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, ColorSchemeKt.getLocalColorScheme()), (TextSelectionColors) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, TextSelectionColorsKt.getLocalTextSelectionColors())) : textFieldColors;
    }

    private final Shape getShape() {
        Shape shape = this._shape;
        return shape == null ? ShapesKt.fromToken((Shapes) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, ShapesKt.getLocalShapes()), FilledTextFieldTokens.INSTANCE.getContainerShape()) : shape;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void invalidateIndicator() {
        BuildersKt.launch$default(getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(null), 3, (Object) null);
        BuildersKt.launch$default(getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(null), 3, (Object) null);
    }

    private final void set_shape(Shape shape) {
        if (Intrinsics.areEqual(this._shape, shape)) {
            return;
        }
        this._shape = shape;
        this.drawWithCacheModifierNode.invalidateDrawCache();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object trackFocusState(Continuation<? super Unit> continuation) {
        this.focused = false;
        final ArrayList arrayList = new ArrayList();
        Object objCollect = this.interactionSource.getInteractions().collect(new FlowCollector() { // from class: androidx.compose.material3.IndicatorLineNode.trackFocusState.2
            public final Object emit(Interaction interaction, Continuation<? super Unit> continuation2) {
                if (interaction instanceof FocusInteraction.Focus) {
                    arrayList.add(interaction);
                } else if (interaction instanceof FocusInteraction.Unfocus) {
                    arrayList.remove(((FocusInteraction.Unfocus) interaction).getFocus());
                }
                boolean z = !arrayList.isEmpty();
                if (z != this.focused) {
                    this.focused = z;
                    this.invalidateIndicator();
                }
                return Unit.INSTANCE;
            }

            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation2) {
                return emit((Interaction) obj, (Continuation<? super Unit>) continuation2);
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public boolean getShouldAutoInvalidate() {
        return false;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        this.trackFocusStateJob = BuildersKt.launch$default(getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new C00491(null), 3, (Object) null);
        if (this.colorAnimatable == null) {
            long jM1057indicatorColorXeAY9LY$material3 = getColors().m1057indicatorColorXeAY9LY$material3(this.enabled, this.isError, this.focused);
            this.colorAnimatable = new Animatable<>(Color.m3124boximpl(jM1057indicatorColorXeAY9LY$material3), (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(Color.m3138getColorSpaceimpl(jM1057indicatorColorXeAY9LY$material3)), (Object) null, (String) null, 12, (DefaultConstructorMarker) null);
        }
    }

    /* JADX INFO: renamed from: update-gv0btCI, reason: not valid java name */
    public final void m555updategv0btCI(boolean enabled, boolean isError, InteractionSource interactionSource, TextFieldColors colors, Shape textFieldShape, float focusedIndicatorWidth, float unfocusedIndicatorWidth) {
        boolean z;
        boolean z2 = true;
        if (this.enabled != enabled) {
            this.enabled = enabled;
            z = true;
        } else {
            z = false;
        }
        if (this.isError != isError) {
            this.isError = isError;
            z = true;
        }
        if (this.interactionSource != interactionSource) {
            this.interactionSource = interactionSource;
            Job job = this.trackFocusStateJob;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            this.trackFocusStateJob = BuildersKt.launch$default(getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new IndicatorLineNode$update$1(this, null), 3, (Object) null);
        }
        if (!Intrinsics.areEqual(this._colors, colors)) {
            this._colors = colors;
            z = true;
        }
        if (!Intrinsics.areEqual(this._shape, textFieldShape)) {
            set_shape(textFieldShape);
            z = true;
        }
        if (!Dp.m6027equalsimpl0(this.focusedIndicatorWidth, focusedIndicatorWidth)) {
            this.focusedIndicatorWidth = focusedIndicatorWidth;
            z = true;
        }
        if (Dp.m6027equalsimpl0(this.unfocusedIndicatorWidth, unfocusedIndicatorWidth)) {
            z2 = z;
        } else {
            this.unfocusedIndicatorWidth = unfocusedIndicatorWidth;
        }
        if (z2) {
            invalidateIndicator();
        }
    }

    public /* synthetic */ IndicatorLineNode(boolean z, boolean z2, InteractionSource interactionSource, TextFieldColors textFieldColors, Shape shape, float f, float f2, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, z2, interactionSource, textFieldColors, shape, f, f2);
    }
}

package androidx.compose.foundation.text.selection;

import androidx.collection.SieveCacheKt;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.text.selection.SelectionMagnifierKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aC\u0010\u000f\u001a\u00020\u0010*\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u00122'\u0010\u0013\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00040\u0012¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00100\u0014H\u0000\u001a!\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u0012H\u0003¢\u0006\u0002\u0010\u001b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\" \u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0016\u0010\u0007\u001a\u00020\u0004X\u0080\u0004¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\t\"\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001c²\u0006\n\u0010\u0017\u001a\u00020\u0004X\u008a\u0084\u0002²\u0006\n\u0010\u001d\u001a\u00020\u0004X\u008a\u0084\u0002"}, d2 = {"UnspecifiedAnimationVector2D", "Landroidx/compose/animation/core/AnimationVector2D;", "UnspecifiedSafeOffsetVectorConverter", "Landroidx/compose/animation/core/TwoWayConverter;", "Landroidx/compose/ui/geometry/Offset;", "getUnspecifiedSafeOffsetVectorConverter", "()Landroidx/compose/animation/core/TwoWayConverter;", "OffsetDisplacementThreshold", "getOffsetDisplacementThreshold", "()J", "J", "MagnifierSpringSpec", "Landroidx/compose/animation/core/SpringSpec;", "getMagnifierSpringSpec", "()Landroidx/compose/animation/core/SpringSpec;", "animatedSelectionMagnifier", "Landroidx/compose/ui/Modifier;", "magnifierCenter", "Lkotlin/Function0;", "platformMagnifier", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "animatedCenter", "rememberAnimatedMagnifierPosition", "Landroidx/compose/runtime/State;", "targetCalculation", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "foundation", "targetValue"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SelectionMagnifierKt {
    private static final SpringSpec<Offset> MagnifierSpringSpec;
    private static final long OffsetDisplacementThreshold;
    private static final AnimationVector2D UnspecifiedAnimationVector2D = new AnimationVector2D(Float.NaN, Float.NaN);
    private static final TwoWayConverter<Offset, AnimationVector2D> UnspecifiedSafeOffsetVectorConverter = VectorConvertersKt.TwoWayConverter(new Function1() { // from class: g4d
        public final Object invoke(Object obj) {
            return SelectionMagnifierKt.b((Offset) obj);
        }
    }, new Function1() { // from class: h4d
        public final Object invoke(Object obj) {
            return SelectionMagnifierKt.d((AnimationVector2D) obj);
        }
    });

    static {
        long j = Offset.constructor-impl((((long) Float.floatToRawIntBits(0.01f)) << 32) | (((long) Float.floatToRawIntBits(0.01f)) & 4294967295L));
        OffsetDisplacementThreshold = j;
        MagnifierSpringSpec = new SpringSpec<>(0.0f, 0.0f, Offset.box-impl(j), 3, null);
    }

    public static Modifier a(Function0 function0, Function1 function1, Modifier modifier, Composer composer, int i) {
        composer.startReplaceGroup(759876635);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(759876635, i, -1, "androidx.compose.foundation.text.selection.animatedSelectionMagnifier.<anonymous> (SelectionMagnifier.kt:64)");
        }
        final State<Offset> stateRememberAnimatedMagnifierPosition = rememberAnimatedMagnifierPosition(function0, composer, 0);
        boolean zChanged = composer.changed(stateRememberAnimatedMagnifierPosition);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = new Function0() { // from class: f4d
                public final Object invoke() {
                    return SelectionMagnifierKt.animatedSelectionMagnifier$lambda$0$1$0(stateRememberAnimatedMagnifierPosition);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        Modifier modifier2 = (Modifier) function1.invoke((Function0) objRememberedValue);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return modifier2;
    }

    public static final Modifier animatedSelectionMagnifier(Modifier modifier, final Function0<Offset> function0, final Function1<? super Function0<Offset>, ? extends Modifier> function1) {
        return ComposedModifierKt.composed$default(modifier, (Function1) null, new Function3() { // from class: i4d
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return SelectionMagnifierKt.a(function0, function1, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }, 1, (Object) null);
    }

    private static final long animatedSelectionMagnifier$lambda$0$0(State<Offset> state) {
        return ((Offset) state.getValue()).unbox-impl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Offset animatedSelectionMagnifier$lambda$0$1$0(State state) {
        return Offset.box-impl(animatedSelectionMagnifier$lambda$0$0(state));
    }

    public static AnimationVector2D b(Offset offset) {
        return (offset.unbox-impl() & SieveCacheKt.InvalidMapping) != 9205357640488583168L ? new AnimationVector2D(Float.intBitsToFloat((int) (offset.unbox-impl() >> 32)), Float.intBitsToFloat((int) (offset.unbox-impl() & 4294967295L))) : UnspecifiedAnimationVector2D;
    }

    public static Offset d(AnimationVector2D animationVector2D) {
        float v1 = animationVector2D.getV1();
        float v2 = animationVector2D.getV2();
        return Offset.box-impl(Offset.constructor-impl((((long) Float.floatToRawIntBits(v1)) << 32) | (((long) Float.floatToRawIntBits(v2)) & 4294967295L)));
    }

    public static final SpringSpec<Offset> getMagnifierSpringSpec() {
        return MagnifierSpringSpec;
    }

    public static final long getOffsetDisplacementThreshold() {
        return OffsetDisplacementThreshold;
    }

    public static final TwoWayConverter<Offset, AnimationVector2D> getUnspecifiedSafeOffsetVectorConverter() {
        return UnspecifiedSafeOffsetVectorConverter;
    }

    private static final State<Offset> rememberAnimatedMagnifierPosition(Function0<Offset> function0, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1589795249, i, -1, "androidx.compose.foundation.text.selection.rememberAnimatedMagnifierPosition (SelectionMagnifier.kt:73)");
        }
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.Companion;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = SnapshotStateKt.derivedStateOf(function0);
            composer.updateRememberedValue(objRememberedValue);
        }
        State state = (State) objRememberedValue;
        Object objRememberedValue2 = composer.rememberedValue();
        if (objRememberedValue2 == companion.getEmpty()) {
            Animatable animatable = new Animatable(Offset.box-impl(rememberAnimatedMagnifierPosition$lambda$1(state)), UnspecifiedSafeOffsetVectorConverter, Offset.box-impl(OffsetDisplacementThreshold), null, 8, null);
            composer.updateRememberedValue(animatable);
            objRememberedValue2 = animatable;
        }
        Animatable animatable2 = (Animatable) objRememberedValue2;
        Unit unit = Unit.INSTANCE;
        boolean zChangedInstance = composer.changedInstance(animatable2);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue3 == companion.getEmpty()) {
            objRememberedValue3 = new SelectionMagnifierKt$rememberAnimatedMagnifierPosition$1$1(state, animatable2, null);
            composer.updateRememberedValue(objRememberedValue3);
        }
        EffectsKt.LaunchedEffect(unit, (Function2) objRememberedValue3, composer, 6);
        State<Offset> stateAsState = animatable2.asState();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return stateAsState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long rememberAnimatedMagnifierPosition$lambda$1(State<Offset> state) {
        return ((Offset) state.getValue()).unbox-impl();
    }
}

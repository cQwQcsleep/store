package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.material3.SegmentedButtonContentMeasurePolicy;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.IntCompanionObject;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ/\u0010\u0016\u001a\u00020\u0017*\u00020\u00182\u0012\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a0\u001a2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016¢\u0006\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR(\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0015¨\u0006 "}, d2 = {"Landroidx/compose/material3/SegmentedButtonContentMeasurePolicy;", "Landroidx/compose/ui/layout/MultiContentMeasurePolicy;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "<init>", "(Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/animation/core/AnimationSpec;)V", "getScope", "()Lkotlinx/coroutines/CoroutineScope;", "getAnimationSpec", "()Landroidx/compose/animation/core/AnimationSpec;", "animatable", "Landroidx/compose/animation/core/Animatable;", "Landroidx/compose/animation/core/AnimationVector1D;", "getAnimatable", "()Landroidx/compose/animation/core/Animatable;", "setAnimatable", "(Landroidx/compose/animation/core/Animatable;)V", "initialOffset", "Ljava/lang/Integer;", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SegmentedButtonContentMeasurePolicy implements MultiContentMeasurePolicy {
    public static final int $stable = 0;
    private Animatable<Integer, AnimationVector1D> animatable;
    private final AnimationSpec<Integer> animationSpec;
    private Integer initialOffset;
    private final CoroutineScope scope;

    public SegmentedButtonContentMeasurePolicy(CoroutineScope coroutineScope, AnimationSpec<Integer> animationSpec) {
        this.scope = coroutineScope;
        this.animationSpec = animationSpec;
    }

    public static Unit a(List list, MeasureScope measureScope, SegmentedButtonContentMeasurePolicy segmentedButtonContentMeasurePolicy, int i, List list2, int i2, Placeable.PlacementScope placementScope) {
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            Placeable placeable = (Placeable) list.get(i3);
            Placeable.PlacementScope.place$default(placementScope, placeable, 0, (i2 - placeable.getHeight()) / 2, 0.0f, 4, null);
        }
        int iMo4551roundToPx0680j_4 = measureScope.mo4551roundToPx0680j_4(SegmentedButtonDefaults.INSTANCE.m845getIconSizeD9Ej5fM()) + measureScope.mo4551roundToPx0680j_4(SegmentedButtonKt.IconSpacing);
        Animatable<Integer, AnimationVector1D> animatable = segmentedButtonContentMeasurePolicy.animatable;
        int iIntValue = iMo4551roundToPx0680j_4 + (animatable != null ? ((Number) animatable.getValue()).intValue() : i);
        int size2 = list2.size();
        for (int i4 = 0; i4 < size2; i4++) {
            Placeable placeable2 = (Placeable) list2.get(i4);
            Placeable.PlacementScope.place$default(placementScope, placeable2, iIntValue, (i2 - placeable2.getHeight()) / 2, 0.0f, 4, null);
        }
        return Unit.INSTANCE;
    }

    public final Animatable<Integer, AnimationVector1D> getAnimatable() {
        return this.animatable;
    }

    public final AnimationSpec<Integer> getAnimationSpec() {
        return this.animationSpec;
    }

    public final CoroutineScope getScope() {
        return this.scope;
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo127measure3p2s80s(final MeasureScope measureScope, List<? extends List<? extends Measurable>> list, long j) {
        Object obj;
        Object obj2;
        Object obj3;
        List<? extends Measurable> list2 = list.get(0);
        int i = 1;
        List<? extends Measurable> list3 = list.get(1);
        final ArrayList arrayList = new ArrayList(list2.size());
        int size = list2.size();
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(list2.get(i2).mo4605measureBRTryo0(j));
        }
        if (!arrayList.isEmpty()) {
            obj = arrayList.get(0);
            int width = ((Placeable) obj).getWidth();
            int lastIndex = CollectionsKt.getLastIndex(arrayList);
            if (1 <= lastIndex) {
                int i3 = 1;
                while (true) {
                    Object obj4 = arrayList.get(i3);
                    int width2 = ((Placeable) obj4).getWidth();
                    if (width < width2) {
                        obj = obj4;
                        width = width2;
                    }
                    if (i3 == lastIndex) {
                        break;
                    }
                    i3++;
                }
            }
        } else {
            obj = null;
        }
        Placeable placeable = (Placeable) obj;
        int width3 = placeable != null ? placeable.getWidth() : 0;
        final ArrayList arrayList2 = new ArrayList(list3.size());
        int size2 = list3.size();
        for (int i4 = 0; i4 < size2; i4++) {
            arrayList2.add(list3.get(i4).mo4605measureBRTryo0(j));
        }
        if (!arrayList2.isEmpty()) {
            obj2 = arrayList2.get(0);
            int width4 = ((Placeable) obj2).getWidth();
            int lastIndex2 = CollectionsKt.getLastIndex(arrayList2);
            if (1 <= lastIndex2) {
                int i5 = 1;
                while (true) {
                    Object obj5 = arrayList2.get(i5);
                    int width5 = ((Placeable) obj5).getWidth();
                    if (width4 < width5) {
                        obj2 = obj5;
                        width4 = width5;
                    }
                    if (i5 == lastIndex2) {
                        break;
                    }
                    i5++;
                }
            }
        } else {
            obj2 = null;
        }
        Placeable placeable2 = (Placeable) obj2;
        Integer numValueOf = placeable2 != null ? Integer.valueOf(placeable2.getWidth()) : null;
        if (!arrayList2.isEmpty()) {
            obj3 = arrayList2.get(0);
            int height = ((Placeable) obj3).getHeight();
            int lastIndex3 = CollectionsKt.getLastIndex(arrayList2);
            if (1 <= lastIndex3) {
                while (true) {
                    Object obj6 = arrayList2.get(i);
                    int height2 = ((Placeable) obj6).getHeight();
                    if (height < height2) {
                        obj3 = obj6;
                        height = height2;
                    }
                    if (i == lastIndex3) {
                        break;
                    }
                    i++;
                }
            }
        } else {
            obj3 = null;
        }
        Placeable placeable3 = (Placeable) obj3;
        int height3 = placeable3 != null ? placeable3.getHeight() : 0;
        SegmentedButtonDefaults segmentedButtonDefaults = SegmentedButtonDefaults.INSTANCE;
        int iMax = Math.max(measureScope.mo4551roundToPx0680j_4(segmentedButtonDefaults.m845getIconSizeD9Ej5fM()), width3) + measureScope.mo4551roundToPx0680j_4(SegmentedButtonKt.IconSpacing) + (numValueOf != null ? numValueOf.intValue() : 0);
        int i6 = width3 == 0 ? (-(measureScope.mo4551roundToPx0680j_4(segmentedButtonDefaults.m845getIconSizeD9Ej5fM()) + measureScope.mo4551roundToPx0680j_4(SegmentedButtonKt.IconSpacing))) / 2 : 0;
        if (this.initialOffset == null) {
            this.initialOffset = Integer.valueOf(i6);
        } else {
            Animatable<Integer, AnimationVector1D> animatable = this.animatable;
            if (animatable == null) {
                Integer num = this.initialOffset;
                num.getClass();
                Animatable<Integer, AnimationVector1D> animatable2 = new Animatable<>(num, VectorConvertersKt.getVectorConverter(IntCompanionObject.INSTANCE), (Object) null, (String) null, 12, (DefaultConstructorMarker) null);
                this.animatable = animatable2;
                animatable = animatable2;
            }
            if (((Number) animatable.getTargetValue()).intValue() != i6) {
                BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new SegmentedButtonContentMeasurePolicy$measure$1(animatable, i6, this, null), 3, (Object) null);
            }
        }
        final int i7 = height3;
        final int i8 = i6;
        return MeasureScope.layout$default(measureScope, iMax, i7, null, new Function1() { // from class: m2d
            public final Object invoke(Object obj7) {
                return SegmentedButtonContentMeasurePolicy.a(arrayList, measureScope, this, i8, arrayList2, i7, (Placeable.PlacementScope) obj7);
            }
        }, 4, null);
    }

    public final void setAnimatable(Animatable<Integer, AnimationVector1D> animatable) {
        this.animatable = animatable;
    }
}

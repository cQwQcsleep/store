package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.material3.TabIndicatorOffsetNode;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u00012\u00020\u0002B9\u0012\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0004\b\u000e\u0010\u000fJ#\u0010&\u001a\u00020'*\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0016¢\u0006\u0004\b-\u0010.R&\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR \u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001c\u0010 \u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\"\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010#\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\"\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Landroidx/compose/material3/TabIndicatorOffsetNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/LayoutModifierNode;", "tabPositionsState", "Landroidx/compose/runtime/State;", "", "Landroidx/compose/material3/TabPosition;", "selectedTabIndex", "", "followContentSize", "", "animationSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "Landroidx/compose/ui/unit/Dp;", "<init>", "(Landroidx/compose/runtime/State;IZLandroidx/compose/animation/core/FiniteAnimationSpec;)V", "getTabPositionsState", "()Landroidx/compose/runtime/State;", "setTabPositionsState", "(Landroidx/compose/runtime/State;)V", "getSelectedTabIndex", "()I", "setSelectedTabIndex", "(I)V", "getFollowContentSize", "()Z", "setFollowContentSize", "(Z)V", "getAnimationSpec", "()Landroidx/compose/animation/core/FiniteAnimationSpec;", "setAnimationSpec", "(Landroidx/compose/animation/core/FiniteAnimationSpec;)V", "offsetAnimatable", "Landroidx/compose/animation/core/Animatable;", "Landroidx/compose/animation/core/AnimationVector1D;", "widthAnimatable", "initialOffset", "initialWidth", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class TabIndicatorOffsetNode extends Modifier.Node implements LayoutModifierNode {
    public static final int $stable = 8;
    private FiniteAnimationSpec<Dp> animationSpec;
    private boolean followContentSize;
    private Dp initialOffset;
    private Dp initialWidth;
    private Animatable<Dp, AnimationVector1D> offsetAnimatable;
    private int selectedTabIndex;
    private State<? extends List<TabPosition>> tabPositionsState;
    private Animatable<Dp, AnimationVector1D> widthAnimatable;

    public TabIndicatorOffsetNode(State<? extends List<TabPosition>> state, int i, boolean z, FiniteAnimationSpec<Dp> finiteAnimationSpec) {
        this.tabPositionsState = state;
        this.selectedTabIndex = i;
        this.followContentSize = z;
        this.animationSpec = finiteAnimationSpec;
    }

    public static Unit a(Placeable.PlacementScope placementScope) {
        return Unit.INSTANCE;
    }

    public static Unit b(Placeable placeable, MeasureScope measureScope, float f, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.place$default(placementScope, placeable, measureScope.mo4551roundToPx0680j_4(f), 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    public final FiniteAnimationSpec<Dp> getAnimationSpec() {
        return this.animationSpec;
    }

    public final boolean getFollowContentSize() {
        return this.followContentSize;
    }

    public final int getSelectedTabIndex() {
        return this.selectedTabIndex;
    }

    public final State<List<TabPosition>> getTabPositionsState() {
        return this.tabPositionsState;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo628measure3p2s80s(final MeasureScope measureScope, Measurable measurable, long j) {
        if (this.tabPositionsState.getValue().isEmpty()) {
            return MeasureScope.layout$default(measureScope, 0, 0, null, new Function1() { // from class: j1e
                public final Object invoke(Object obj) {
                    return TabIndicatorOffsetNode.a((Placeable.PlacementScope) obj);
                }
            }, 4, null);
        }
        boolean z = this.followContentSize;
        State<? extends List<TabPosition>> state = this.tabPositionsState;
        float contentWidth = z ? state.getValue().get(this.selectedTabIndex).getContentWidth() : state.getValue().get(this.selectedTabIndex).getWidth();
        if (this.initialWidth != null) {
            Animatable<Dp, AnimationVector1D> animatable = this.widthAnimatable;
            if (animatable == null) {
                Dp dp = this.initialWidth;
                dp.getClass();
                Animatable<Dp, AnimationVector1D> animatable2 = new Animatable<>(dp, VectorConvertersKt.getVectorConverter(Dp.INSTANCE), (Object) null, (String) null, 12, (DefaultConstructorMarker) null);
                this.widthAnimatable = animatable2;
                animatable = animatable2;
            }
            if (!Dp.m6027equalsimpl0(contentWidth, ((Dp) animatable.getTargetValue()).m6036unboximpl())) {
                BuildersKt.launch$default(getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new TabIndicatorOffsetNode$measure$2(animatable, contentWidth, this, null), 3, (Object) null);
            }
        } else {
            this.initialWidth = Dp.m6020boximpl(contentWidth);
        }
        float left = this.tabPositionsState.getValue().get(this.selectedTabIndex).getLeft();
        if (this.initialOffset != null) {
            Animatable<Dp, AnimationVector1D> animatable3 = this.offsetAnimatable;
            if (animatable3 == null) {
                Dp dp2 = this.initialOffset;
                dp2.getClass();
                Animatable<Dp, AnimationVector1D> animatable4 = new Animatable<>(dp2, VectorConvertersKt.getVectorConverter(Dp.INSTANCE), (Object) null, (String) null, 12, (DefaultConstructorMarker) null);
                this.offsetAnimatable = animatable4;
                animatable3 = animatable4;
            }
            if (!Dp.m6027equalsimpl0(left, ((Dp) animatable3.getTargetValue()).m6036unboximpl())) {
                BuildersKt.launch$default(getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new TabIndicatorOffsetNode$measure$3(animatable3, left, this, null), 3, (Object) null);
            }
        } else {
            this.initialOffset = Dp.m6020boximpl(left);
        }
        LayoutDirection layoutDirection = measureScope.getLayoutDirection();
        LayoutDirection layoutDirection2 = LayoutDirection.Ltr;
        Animatable<Dp, AnimationVector1D> animatable5 = this.offsetAnimatable;
        if (layoutDirection != layoutDirection2) {
            if (animatable5 != null) {
                left = ((Dp) animatable5.getValue()).m6036unboximpl();
            }
            left = Dp.m6022constructorimpl(-left);
        } else if (animatable5 != null) {
            left = ((Dp) animatable5.getValue()).m6036unboximpl();
        }
        Animatable<Dp, AnimationVector1D> animatable6 = this.widthAnimatable;
        if (animatable6 != null) {
            contentWidth = ((Dp) animatable6.getValue()).m6036unboximpl();
        }
        final Placeable placeableMo4605measureBRTryo0 = measurable.mo4605measureBRTryo0(Constraints.m5965copyZbe2FdA$default(j, measureScope.mo4551roundToPx0680j_4(contentWidth), measureScope.mo4551roundToPx0680j_4(contentWidth), 0, 0, 12, null));
        final float f = left;
        return MeasureScope.layout$default(measureScope, placeableMo4605measureBRTryo0.getWidth(), placeableMo4605measureBRTryo0.getHeight(), null, new Function1() { // from class: k1e
            public final Object invoke(Object obj) {
                return TabIndicatorOffsetNode.b(placeableMo4605measureBRTryo0, measureScope, f, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    public final void setAnimationSpec(FiniteAnimationSpec<Dp> finiteAnimationSpec) {
        this.animationSpec = finiteAnimationSpec;
    }

    public final void setFollowContentSize(boolean z) {
        this.followContentSize = z;
    }

    public final void setSelectedTabIndex(int i) {
        this.selectedTabIndex = i;
    }

    public final void setTabPositionsState(State<? extends List<TabPosition>> state) {
        this.tabPositionsState = state;
    }
}

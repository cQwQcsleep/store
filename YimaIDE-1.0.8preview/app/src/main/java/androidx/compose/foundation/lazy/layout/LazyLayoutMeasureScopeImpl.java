package androidx.compose.foundation.lazy.layout;

import androidx.collection.IntObjectMapKt;
import androidx.collection.MutableIntObjectMap;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.RulerScope;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.unit.DpRect;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u00012\u00020\u0002B\u0019\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\r2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J%\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0017¢\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u001a*\u00020\u001bH\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u0013\u0010\u0019\u001a\u00020\u001a*\u00020\u0013H\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u0013\u0010\u0019\u001a\u00020\u001a*\u00020 H\u0016¢\u0006\u0004\b\u001e\u0010!J\u0013\u0010\"\u001a\u00020\u001b*\u00020 H\u0016¢\u0006\u0004\b#\u0010$J\u0013\u0010\"\u001a\u00020\u001b*\u00020\u0013H\u0016¢\u0006\u0004\b#\u0010%J\u0013\u0010\"\u001a\u00020\u001b*\u00020\u001aH\u0016¢\u0006\u0004\b&\u0010$J\u0013\u0010'\u001a\u00020(*\u00020)H\u0016¢\u0006\u0004\b*\u0010+J\u0013\u0010,\u001a\u00020)*\u00020(H\u0016¢\u0006\u0004\b-\u0010+JF\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\u00132\u0006\u00101\u001a\u00020\u00132\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u00020\u0013032\u0017\u00105\u001a\u0013\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020806¢\u0006\u0002\b9H\u0096\u0001Ja\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\u00132\u0006\u00101\u001a\u00020\u00132\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u00020\u0013032\u0019\u0010:\u001a\u0015\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u000208\u0018\u000106¢\u0006\u0002\b92\u0017\u00105\u001a\u0013\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020806¢\u0006\u0002\b9H\u0096\u0001J\u0014\u0010<\u001a\u00020\u0013*\u00020\u001aH\u0097\u0001¢\u0006\u0004\b=\u0010>J\u0014\u0010<\u001a\u00020\u0013*\u00020\u001bH\u0097\u0001¢\u0006\u0004\b?\u0010@J\u0014\u0010A\u001a\u00020 *\u00020\u001aH\u0097\u0001¢\u0006\u0004\bB\u0010!J\u0014\u0010A\u001a\u00020 *\u00020\u001bH\u0097\u0001¢\u0006\u0004\bC\u0010\u001dJ\r\u0010D\u001a\u00020E*\u00020FH\u0097\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010G\u001a\u00020 8\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\bH\u0010IR\u0014\u0010J\u001a\u00020 8\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\bK\u0010IR\u0014\u0010L\u001a\u00020M8VX\u0096\u0005¢\u0006\u0006\u001a\u0004\bL\u0010NR\u0012\u0010O\u001a\u00020PX\u0096\u0005¢\u0006\u0006\u001a\u0004\bQ\u0010R¨\u0006S"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScopeImpl;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "Landroidx/compose/ui/layout/MeasureScope;", "itemContentFactory", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemContentFactory;", "subcomposeMeasureScope", "Landroidx/compose/ui/layout/SubcomposeMeasureScope;", "<init>", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutItemContentFactory;Landroidx/compose/ui/layout/SubcomposeMeasureScope;)V", "itemProvider", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemProvider;", "placeablesCache", "Landroidx/collection/MutableIntObjectMap;", "", "Landroidx/compose/ui/layout/Placeable;", "measurablesCache", "Landroidx/compose/ui/layout/Measurable;", "compose", "index", "", "measure", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-0kLqBqw", "(IJ)Ljava/util/List;", "toDp", "Landroidx/compose/ui/unit/Dp;", "Landroidx/compose/ui/unit/TextUnit;", "toDp-GaN1DYA", "(J)F", "toDp-u2uoSUM", "(I)F", "", "(F)F", "toSp", "toSp-kPz2Gy4", "(F)J", "(I)J", "toSp-0xMU5do", "toSize", "Landroidx/compose/ui/geometry/Size;", "Landroidx/compose/ui/unit/DpSize;", "toSize-XkaWNTQ", "(J)J", "toDpSize", "toDpSize-k-rfVVM", "layout", "Landroidx/compose/ui/layout/MeasureResult;", "width", "height", "alignmentLines", "", "Landroidx/compose/ui/layout/AlignmentLine;", "placementBlock", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "", "Lkotlin/ExtensionFunctionType;", "rulers", "Landroidx/compose/ui/layout/RulerScope;", "roundToPx", "roundToPx-0680j_4", "(F)I", "roundToPx--R2X_6o", "(J)I", "toPx", "toPx-0680j_4", "toPx--R2X_6o", "toRect", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/unit/DpRect;", "density", "getDensity", "()F", "fontScale", "getFontScale", "isLookingAhead", "", "()Z", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class LazyLayoutMeasureScopeImpl implements LazyLayoutMeasureScope, MeasureScope {
    public static final int $stable = 0;
    private final LazyLayoutItemContentFactory itemContentFactory;
    private final LazyLayoutItemProvider itemProvider;
    private final SubcomposeMeasureScope subcomposeMeasureScope;
    private final MutableIntObjectMap<List<Placeable>> placeablesCache = IntObjectMapKt.mutableIntObjectMapOf();
    private final MutableIntObjectMap<List<Measurable>> measurablesCache = IntObjectMapKt.mutableIntObjectMapOf();

    public LazyLayoutMeasureScopeImpl(LazyLayoutItemContentFactory lazyLayoutItemContentFactory, SubcomposeMeasureScope subcomposeMeasureScope) {
        this.itemContentFactory = lazyLayoutItemContentFactory;
        this.subcomposeMeasureScope = subcomposeMeasureScope;
        this.itemProvider = (LazyLayoutItemProvider) lazyLayoutItemContentFactory.getItemProvider().invoke();
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope
    public List<Measurable> compose(int index) {
        List<Measurable> list = this.measurablesCache.get(index);
        if (list != null) {
            return list;
        }
        Object key = this.itemProvider.getKey(index);
        List<Measurable> listSubcompose = this.subcomposeMeasureScope.subcompose(key, this.itemContentFactory.getContent(index, key, this.itemProvider.getContentType(index)));
        this.measurablesCache.set(index, listSubcompose);
        return listSubcompose;
    }

    public float getDensity() {
        return this.subcomposeMeasureScope.getDensity();
    }

    public float getFontScale() {
        return this.subcomposeMeasureScope.getFontScale();
    }

    public LayoutDirection getLayoutDirection() {
        return this.subcomposeMeasureScope.getLayoutDirection();
    }

    public boolean isLookingAhead() {
        return this.subcomposeMeasureScope.isLookingAhead();
    }

    public MeasureResult layout(int width, int height, Map<AlignmentLine, Integer> alignmentLines, Function1<? super Placeable.PlacementScope, Unit> placementBlock) {
        return this.subcomposeMeasureScope.layout(width, height, alignmentLines, placementBlock);
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope
    @Deprecated(message = "Please use compose and measure")
    /* JADX INFO: renamed from: measure-0kLqBqw */
    public List<Placeable> mo1129measure0kLqBqw(int index, long constraints) {
        List<Placeable> list = this.placeablesCache.get(index);
        if (list != null) {
            return list;
        }
        Object key = this.itemProvider.getKey(index);
        List listSubcompose = this.subcomposeMeasureScope.subcompose(key, this.itemContentFactory.getContent(index, key, this.itemProvider.getContentType(index)));
        int size = listSubcompose.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(((Measurable) listSubcompose.get(i)).measure-BRTryo0(constraints));
        }
        this.placeablesCache.set(index, arrayList);
        return arrayList;
    }

    /* JADX INFO: renamed from: roundToPx--R2X_6o, reason: not valid java name */
    public int m1130roundToPxR2X_6o(long j) {
        return this.subcomposeMeasureScope.roundToPx--R2X_6o(j);
    }

    /* JADX INFO: renamed from: roundToPx-0680j_4, reason: not valid java name */
    public int m1131roundToPx0680j_4(float f) {
        return this.subcomposeMeasureScope.roundToPx-0680j_4(f);
    }

    /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
    public float m1132toDpGaN1DYA(long j) {
        return this.subcomposeMeasureScope.toDp-GaN1DYA(j);
    }

    /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
    public float m1134toDpu2uoSUM(int i) {
        return this.subcomposeMeasureScope.toDp-u2uoSUM(i);
    }

    /* JADX INFO: renamed from: toDpSize-k-rfVVM, reason: not valid java name */
    public long m1135toDpSizekrfVVM(long j) {
        return this.subcomposeMeasureScope.toDpSize-k-rfVVM(j);
    }

    /* JADX INFO: renamed from: toPx--R2X_6o, reason: not valid java name */
    public float m1136toPxR2X_6o(long j) {
        return this.subcomposeMeasureScope.toPx--R2X_6o(j);
    }

    /* JADX INFO: renamed from: toPx-0680j_4, reason: not valid java name */
    public float m1137toPx0680j_4(float f) {
        return this.subcomposeMeasureScope.toPx-0680j_4(f);
    }

    public Rect toRect(DpRect dpRect) {
        return this.subcomposeMeasureScope.toRect(dpRect);
    }

    /* JADX INFO: renamed from: toSize-XkaWNTQ, reason: not valid java name */
    public long m1138toSizeXkaWNTQ(long j) {
        return this.subcomposeMeasureScope.toSize-XkaWNTQ(j);
    }

    /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
    public long m1139toSp0xMU5do(float f) {
        return this.subcomposeMeasureScope.toSp-0xMU5do(f);
    }

    /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
    public long m1140toSpkPz2Gy4(float f) {
        return this.subcomposeMeasureScope.toSp-kPz2Gy4(f);
    }

    public MeasureResult layout(int width, int height, Map<AlignmentLine, Integer> alignmentLines, Function1<? super RulerScope, Unit> rulers, Function1<? super Placeable.PlacementScope, Unit> placementBlock) {
        return this.subcomposeMeasureScope.layout(width, height, alignmentLines, rulers, placementBlock);
    }

    /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
    public float m1133toDpu2uoSUM(float f) {
        return this.subcomposeMeasureScope.toDp-u2uoSUM(f);
    }

    /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
    public long m1141toSpkPz2Gy4(int i) {
        return this.subcomposeMeasureScope.toSp-kPz2Gy4(i);
    }
}

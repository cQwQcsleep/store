package androidx.compose.ui.node;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.HorizontalAlignmentLine;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0019\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b1\u0018\u00002\u00020\u0001B\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\"\u001a\u00020#J\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020'0)J\u0014\u0010.\u001a\u00020'*\u00020+2\u0006\u0010/\u001a\u00020&H$J \u00100\u001a\u00020#2\u0006\u0010/\u001a\u00020&2\u0006\u00101\u001a\u00020'2\u0006\u00102\u001a\u00020+H\u0002J\u0006\u00103\u001a\u00020#J\r\u00104\u001a\u00020#H\u0000¢\u0006\u0002\b5J\u0006\u00106\u001a\u00020#J\u001b\u00107\u001a\u000208*\u00020+2\u0006\u00109\u001a\u000208H$¢\u0006\u0004\b:\u0010;R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\tX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\tX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\rR\u001a\u0010\u0011\u001a\u00020\tX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\rR\u001a\u0010\u0014\u001a\u00020\tX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000b\"\u0004\b\u0016\u0010\rR\u001a\u0010\u0017\u001a\u00020\tX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000b\"\u0004\b\u0019\u0010\rR\u001a\u0010\u001a\u001a\u00020\tX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u000b\"\u0004\b\u001c\u0010\rR\u0014\u0010\u001d\u001a\u00020\t8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u000bR\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\u00020\t8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u000bR\u001a\u0010$\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020'0%X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010*\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020'0)*\u00020+X¤\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-\u0082\u0001\u0002<=¨\u0006>"}, d2 = {"Landroidx/compose/ui/node/AlignmentLines;", "", "alignmentLinesOwner", "Landroidx/compose/ui/node/AlignmentLinesOwner;", "<init>", "(Landroidx/compose/ui/node/AlignmentLinesOwner;)V", "getAlignmentLinesOwner", "()Landroidx/compose/ui/node/AlignmentLinesOwner;", "dirty", "", "getDirty$ui", "()Z", "setDirty$ui", "(Z)V", "usedDuringParentMeasurement", "getUsedDuringParentMeasurement$ui", "setUsedDuringParentMeasurement$ui", "usedDuringParentLayout", "getUsedDuringParentLayout$ui", "setUsedDuringParentLayout$ui", "previousUsedDuringParentLayout", "getPreviousUsedDuringParentLayout$ui", "setPreviousUsedDuringParentLayout$ui", "usedByModifierMeasurement", "getUsedByModifierMeasurement$ui", "setUsedByModifierMeasurement$ui", "usedByModifierLayout", "getUsedByModifierLayout$ui", "setUsedByModifierLayout$ui", "queried", "getQueried$ui", "queryOwner", "required", "getRequired$ui", "recalculateQueryOwner", "", "alignmentLineMap", "", "Landroidx/compose/ui/layout/AlignmentLine;", "", "getLastCalculation", "", "alignmentLinesMap", "Landroidx/compose/ui/node/NodeCoordinator;", "getAlignmentLinesMap", "(Landroidx/compose/ui/node/NodeCoordinator;)Ljava/util/Map;", "getPositionFor", "alignmentLine", "addAlignmentLine", "initialPosition", "initialCoordinator", "recalculate", "reset", "reset$ui", "onAlignmentsChanged", "calculatePositionInParent", "Landroidx/compose/ui/geometry/Offset;", "position", "calculatePositionInParent-R5De75A", "(Landroidx/compose/ui/node/NodeCoordinator;J)J", "Landroidx/compose/ui/node/LayoutNodeAlignmentLines;", "Landroidx/compose/ui/node/LookaheadAlignmentLines;", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public abstract class AlignmentLines {
    public static final int $stable = 8;
    private final Map<AlignmentLine, Integer> alignmentLineMap;
    private final AlignmentLinesOwner alignmentLinesOwner;
    private boolean dirty;
    private boolean previousUsedDuringParentLayout;
    private AlignmentLinesOwner queryOwner;
    private boolean usedByModifierLayout;
    private boolean usedByModifierMeasurement;
    private boolean usedDuringParentLayout;
    private boolean usedDuringParentMeasurement;

    private AlignmentLines(AlignmentLinesOwner alignmentLinesOwner) {
        this.alignmentLinesOwner = alignmentLinesOwner;
        this.dirty = true;
        this.alignmentLineMap = new HashMap();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addAlignmentLine(AlignmentLine alignmentLine, int initialPosition, NodeCoordinator initialCoordinator) {
        float f = initialPosition;
        long jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(f)) << 32) | (((long) Float.floatToRawIntBits(f)) & 4294967295L));
        while (true) {
            jM2881constructorimpl = mo4766calculatePositionInParentR5De75A(initialCoordinator, jM2881constructorimpl);
            initialCoordinator = initialCoordinator.getWrappedBy();
            initialCoordinator.getClass();
            if (Intrinsics.areEqual(initialCoordinator, this.alignmentLinesOwner.getInnerCoordinator())) {
                break;
            } else if (getAlignmentLinesMap(initialCoordinator).containsKey(alignmentLine)) {
                float positionFor = getPositionFor(initialCoordinator, alignmentLine);
                jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(positionFor)) << 32) | (((long) Float.floatToRawIntBits(positionFor)) & 4294967295L));
            }
        }
        int iRound = Math.round(alignmentLine instanceof HorizontalAlignmentLine ? Float.intBitsToFloat((int) (jM2881constructorimpl & 4294967295L)) : Float.intBitsToFloat((int) (jM2881constructorimpl >> 32)));
        Map<AlignmentLine, Integer> map = this.alignmentLineMap;
        if (map.containsKey(alignmentLine)) {
            iRound = AlignmentLineKt.merge(alignmentLine, ((Number) MapsKt.getValue(this.alignmentLineMap, alignmentLine)).intValue(), iRound);
        }
        map.put(alignmentLine, Integer.valueOf(iRound));
    }

    /* JADX INFO: renamed from: calculatePositionInParent-R5De75A, reason: not valid java name */
    public abstract long mo4766calculatePositionInParentR5De75A(NodeCoordinator nodeCoordinator, long j);

    public abstract Map<AlignmentLine, Integer> getAlignmentLinesMap(NodeCoordinator nodeCoordinator);

    public final AlignmentLinesOwner getAlignmentLinesOwner() {
        return this.alignmentLinesOwner;
    }

    /* JADX INFO: renamed from: getDirty$ui, reason: from getter */
    public final boolean getDirty() {
        return this.dirty;
    }

    public final Map<AlignmentLine, Integer> getLastCalculation() {
        return this.alignmentLineMap;
    }

    public abstract int getPositionFor(NodeCoordinator nodeCoordinator, AlignmentLine alignmentLine);

    /* JADX INFO: renamed from: getPreviousUsedDuringParentLayout$ui, reason: from getter */
    public final boolean getPreviousUsedDuringParentLayout() {
        return this.previousUsedDuringParentLayout;
    }

    public final boolean getQueried$ui() {
        return this.usedDuringParentMeasurement || this.previousUsedDuringParentLayout || this.usedByModifierMeasurement || this.usedByModifierLayout;
    }

    public final boolean getRequired$ui() {
        recalculateQueryOwner();
        return this.queryOwner != null;
    }

    /* JADX INFO: renamed from: getUsedByModifierLayout$ui, reason: from getter */
    public final boolean getUsedByModifierLayout() {
        return this.usedByModifierLayout;
    }

    /* JADX INFO: renamed from: getUsedByModifierMeasurement$ui, reason: from getter */
    public final boolean getUsedByModifierMeasurement() {
        return this.usedByModifierMeasurement;
    }

    /* JADX INFO: renamed from: getUsedDuringParentLayout$ui, reason: from getter */
    public final boolean getUsedDuringParentLayout() {
        return this.usedDuringParentLayout;
    }

    /* JADX INFO: renamed from: getUsedDuringParentMeasurement$ui, reason: from getter */
    public final boolean getUsedDuringParentMeasurement() {
        return this.usedDuringParentMeasurement;
    }

    public final void onAlignmentsChanged() {
        this.dirty = true;
        AlignmentLinesOwner parentAlignmentLinesOwner = this.alignmentLinesOwner.getParentAlignmentLinesOwner();
        if (parentAlignmentLinesOwner == null) {
            return;
        }
        if (this.usedDuringParentMeasurement) {
            parentAlignmentLinesOwner.requestMeasure();
        } else if (this.previousUsedDuringParentLayout || this.usedDuringParentLayout) {
            parentAlignmentLinesOwner.requestLayout();
        }
        if (this.usedByModifierMeasurement) {
            this.alignmentLinesOwner.requestMeasure();
        }
        if (this.usedByModifierLayout) {
            this.alignmentLinesOwner.requestLayout();
        }
        parentAlignmentLinesOwner.getAlignmentLines().onAlignmentsChanged();
    }

    public final void recalculate() {
        this.alignmentLineMap.clear();
        this.alignmentLinesOwner.forEachChildAlignmentLinesOwner(new Function1<AlignmentLinesOwner, Unit>() { // from class: androidx.compose.ui.node.AlignmentLines.recalculate.1
            {
                super(1);
            }

            public final void invoke(AlignmentLinesOwner alignmentLinesOwner) {
                if (alignmentLinesOwner.getPlaceOrder() == Integer.MAX_VALUE) {
                    return;
                }
                if (alignmentLinesOwner.getAlignmentLines().getDirty()) {
                    alignmentLinesOwner.layoutChildren();
                }
                Map map = alignmentLinesOwner.getAlignmentLines().alignmentLineMap;
                AlignmentLines alignmentLines = AlignmentLines.this;
                for (Map.Entry entry : map.entrySet()) {
                    alignmentLines.addAlignmentLine((AlignmentLine) entry.getKey(), ((Number) entry.getValue()).intValue(), alignmentLinesOwner.getInnerCoordinator());
                }
                NodeCoordinator wrappedBy = alignmentLinesOwner.getInnerCoordinator().getWrappedBy();
                wrappedBy.getClass();
                while (!Intrinsics.areEqual(wrappedBy, AlignmentLines.this.getAlignmentLinesOwner().getInnerCoordinator())) {
                    Set<AlignmentLine> setKeySet = AlignmentLines.this.getAlignmentLinesMap(wrappedBy).keySet();
                    AlignmentLines alignmentLines2 = AlignmentLines.this;
                    for (AlignmentLine alignmentLine : setKeySet) {
                        alignmentLines2.addAlignmentLine(alignmentLine, alignmentLines2.getPositionFor(wrappedBy, alignmentLine), wrappedBy);
                    }
                    wrappedBy = wrappedBy.getWrappedBy();
                    wrappedBy.getClass();
                }
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((AlignmentLinesOwner) obj);
                return Unit.INSTANCE;
            }
        });
        this.alignmentLineMap.putAll(getAlignmentLinesMap(this.alignmentLinesOwner.getInnerCoordinator()));
        this.dirty = false;
    }

    public final void recalculateQueryOwner() {
        AlignmentLines alignmentLines;
        AlignmentLines alignmentLines2;
        boolean queried$ui = getQueried$ui();
        AlignmentLinesOwner alignmentLinesOwner = this.alignmentLinesOwner;
        if (!queried$ui) {
            AlignmentLinesOwner parentAlignmentLinesOwner = alignmentLinesOwner.getParentAlignmentLinesOwner();
            if (parentAlignmentLinesOwner == null) {
                return;
            }
            alignmentLinesOwner = parentAlignmentLinesOwner.getAlignmentLines().queryOwner;
            if (alignmentLinesOwner == null || !alignmentLinesOwner.getAlignmentLines().getQueried$ui()) {
                AlignmentLinesOwner alignmentLinesOwner2 = this.queryOwner;
                if (alignmentLinesOwner2 == null || alignmentLinesOwner2.getAlignmentLines().getQueried$ui()) {
                    return;
                }
                AlignmentLinesOwner parentAlignmentLinesOwner2 = alignmentLinesOwner2.getParentAlignmentLinesOwner();
                if (parentAlignmentLinesOwner2 != null && (alignmentLines2 = parentAlignmentLinesOwner2.getAlignmentLines()) != null) {
                    alignmentLines2.recalculateQueryOwner();
                }
                AlignmentLinesOwner parentAlignmentLinesOwner3 = alignmentLinesOwner2.getParentAlignmentLinesOwner();
                alignmentLinesOwner = (parentAlignmentLinesOwner3 == null || (alignmentLines = parentAlignmentLinesOwner3.getAlignmentLines()) == null) ? null : alignmentLines.queryOwner;
            }
        }
        this.queryOwner = alignmentLinesOwner;
    }

    public final void reset$ui() {
        this.dirty = true;
        this.usedDuringParentMeasurement = false;
        this.previousUsedDuringParentLayout = false;
        this.usedDuringParentLayout = false;
        this.usedByModifierMeasurement = false;
        this.usedByModifierLayout = false;
        this.queryOwner = null;
    }

    public final void setDirty$ui(boolean z) {
        this.dirty = z;
    }

    public final void setPreviousUsedDuringParentLayout$ui(boolean z) {
        this.previousUsedDuringParentLayout = z;
    }

    public final void setUsedByModifierLayout$ui(boolean z) {
        this.usedByModifierLayout = z;
    }

    public final void setUsedByModifierMeasurement$ui(boolean z) {
        this.usedByModifierMeasurement = z;
    }

    public final void setUsedDuringParentLayout$ui(boolean z) {
        this.usedDuringParentLayout = z;
    }

    public final void setUsedDuringParentMeasurement$ui(boolean z) {
        this.usedDuringParentMeasurement = z;
    }

    public /* synthetic */ AlignmentLines(AlignmentLinesOwner alignmentLinesOwner, DefaultConstructorMarker defaultConstructorMarker) {
        this(alignmentLinesOwner);
    }
}

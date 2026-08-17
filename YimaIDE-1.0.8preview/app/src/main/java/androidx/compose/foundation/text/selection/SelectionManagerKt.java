package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.contextmenu.ContextMenuScope;
import androidx.compose.foundation.contextmenu.ContextMenuState;
import androidx.compose.foundation.text.CommonContextMenuAreaKt;
import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.TextContextMenuItems;
import androidx.compose.foundation.text.selection.SelectionManagerKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.unit.IntSize;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000d\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\u001e\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0001H\u0000\u001a%\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\b*\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0000\u001a\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00100\u000f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\u000fH\u0002\u001a*\u0010\u0011\u001a\u00020\r2\u0018\u0010\u0012\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u00130\u000f2\u0006\u0010\u0015\u001a\u00020\u0016H\u0001\u001a\u001f\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u001bH\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a'\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020 H\u0002¢\u0006\u0004\b!\u0010\"\u001a\f\u0010#\u001a\u00020\r*\u00020\u0016H\u0000\u001a\u001b\u0010$\u001a\u00020%*\u00020\r2\u0006\u0010&\u001a\u00020\u0018H\u0000¢\u0006\u0004\b'\u0010(\"\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"merge", "Landroidx/compose/foundation/text/selection/Selection;", "lhs", "rhs", "contextMenuBuilder", "Lkotlin/Function1;", "Landroidx/compose/foundation/contextmenu/ContextMenuScope;", "", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/foundation/text/selection/SelectionManager;", "state", "Landroidx/compose/foundation/contextmenu/ContextMenuState;", "invertedInfiniteRect", "Landroidx/compose/ui/geometry/Rect;", "firstAndLast", "", "T", "getSelectedRegionRect", "selectableSubSelectionPairs", "Lkotlin/Pair;", "Landroidx/compose/foundation/text/selection/Selectable;", "containerCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "calculateSelectionMagnifierCenterAndroid", "Landroidx/compose/ui/geometry/Offset;", "manager", "magnifierSize", "Landroidx/compose/ui/unit/IntSize;", "calculateSelectionMagnifierCenterAndroid-O0kMr_c", "(Landroidx/compose/foundation/text/selection/SelectionManager;J)J", "getMagnifierCenter", "anchor", "Landroidx/compose/foundation/text/selection/Selection$AnchorInfo;", "getMagnifierCenter-JVtK1S4", "(Landroidx/compose/foundation/text/selection/SelectionManager;JLandroidx/compose/foundation/text/selection/Selection$AnchorInfo;)J", "visibleBounds", "containsInclusive", "", "offset", "containsInclusive-Uv8p0NA", "(Landroidx/compose/ui/geometry/Rect;J)Z", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SelectionManagerKt {
    private static final Rect invertedInfiniteRect = new Rect(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Handle.values().length];
            try {
                iArr[Handle.SelectionStart.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Handle.SelectionEnd.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Handle.Cursor.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static Unit b(final SelectionManager selectionManager, ContextMenuState contextMenuState, ContextMenuScope contextMenuScope) {
        contextMenuBuilder$lambda$0$selectionItem(contextMenuScope, contextMenuState, TextContextMenuItems.Copy, selectionManager.isNonEmptySelection$foundation(), new Function0() { // from class: z4d
            public final Object invoke() {
                return SelectionManagerKt.contextMenuBuilder$lambda$0$0(selectionManager);
            }
        });
        Unit unit = Unit.INSTANCE;
        contextMenuBuilder$lambda$0$selectionItem(contextMenuScope, contextMenuState, TextContextMenuItems.SelectAll, !selectionManager.isEntireContainerSelected$foundation(), new Function0() { // from class: a5d
            public final Object invoke() {
                return SelectionManagerKt.contextMenuBuilder$lambda$0$1(selectionManager);
            }
        });
        CollectionsKt.listOf(new Unit[]{unit, unit});
        return unit;
    }

    /* JADX INFO: renamed from: calculateSelectionMagnifierCenterAndroid-O0kMr_c, reason: not valid java name */
    public static final long m1792calculateSelectionMagnifierCenterAndroidO0kMr_c(SelectionManager selectionManager, long j) {
        Selection selection = selectionManager.getSelection();
        if (selection == null) {
            return Offset.Companion.getUnspecified-F1C5BW0();
        }
        Handle draggingHandle = selectionManager.getDraggingHandle();
        int i = draggingHandle == null ? -1 : WhenMappings.$EnumSwitchMapping$0[draggingHandle.ordinal()];
        if (i == -1) {
            return Offset.Companion.getUnspecified-F1C5BW0();
        }
        if (i == 1) {
            return m1794getMagnifierCenterJVtK1S4(selectionManager, j, selection.getStart());
        }
        if (i == 2) {
            return m1794getMagnifierCenterJVtK1S4(selectionManager, j, selection.getEnd());
        }
        if (i != 3) {
            bu8.a();
            return 0L;
        }
        k2d.a("SelectionContainer does not support cursor");
        return 0L;
    }

    /* JADX INFO: renamed from: containsInclusive-Uv8p0NA, reason: not valid java name */
    public static final boolean m1793containsInclusiveUv8p0NA(Rect rect, long j) {
        float left = rect.getLeft();
        float right = rect.getRight();
        float fIntBitsToFloat = Float.intBitsToFloat((int) (j >> 32));
        if (left > fIntBitsToFloat || fIntBitsToFloat > right) {
            return false;
        }
        float top = rect.getTop();
        float bottom = rect.getBottom();
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (j & 4294967295L));
        return top <= fIntBitsToFloat2 && fIntBitsToFloat2 <= bottom;
    }

    public static final Function1<ContextMenuScope, Unit> contextMenuBuilder(final SelectionManager selectionManager, final ContextMenuState contextMenuState) {
        return new Function1() { // from class: y4d
            public final Object invoke(Object obj) {
                return SelectionManagerKt.b(selectionManager, contextMenuState, (ContextMenuScope) obj);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit contextMenuBuilder$lambda$0$0(SelectionManager selectionManager) {
        selectionManager.copy$foundation();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit contextMenuBuilder$lambda$0$1(SelectionManager selectionManager) {
        selectionManager.selectAll$foundation();
        return Unit.INSTANCE;
    }

    private static final void contextMenuBuilder$lambda$0$selectionItem(ContextMenuScope contextMenuScope, ContextMenuState contextMenuState, TextContextMenuItems textContextMenuItems, boolean z, Function0<Unit> function0) {
        if (z) {
            ContextMenuScope.item$default(contextMenuScope, new CommonContextMenuAreaKt.AnonymousClass1(textContextMenuItems), null, false, null, new CommonContextMenuAreaKt.AnonymousClass2(function0, contextMenuState), 14, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> List<T> firstAndLast(List<? extends T> list) {
        int size = list.size();
        return (size == 0 || size == 1) ? list : CollectionsKt.listOf(new Object[]{CollectionsKt.first(list), CollectionsKt.last(list)});
    }

    /* JADX INFO: renamed from: getMagnifierCenter-JVtK1S4, reason: not valid java name */
    private static final long m1794getMagnifierCenterJVtK1S4(SelectionManager selectionManager, long j, Selection.AnchorInfo anchorInfo) {
        LayoutCoordinates containerLayoutCoordinates;
        LayoutCoordinates layoutCoordinates;
        int offset;
        float fCoerceIn;
        Selectable anchorSelectable$foundation = selectionManager.getAnchorSelectable$foundation(anchorInfo);
        if (anchorSelectable$foundation != null && (containerLayoutCoordinates = selectionManager.getContainerLayoutCoordinates()) != null && (layoutCoordinates = anchorSelectable$foundation.getLayoutCoordinates()) != null && (offset = anchorInfo.getOffset()) <= anchorSelectable$foundation.getLastVisibleOffset()) {
            Offset offsetM1780getCurrentDragPosition_m7T9E = selectionManager.m1780getCurrentDragPosition_m7T9E();
            offsetM1780getCurrentDragPosition_m7T9E.getClass();
            float fIntBitsToFloat = Float.intBitsToFloat((int) (layoutCoordinates.localPositionOf-R5De75A(containerLayoutCoordinates, offsetM1780getCurrentDragPosition_m7T9E.unbox-impl()) >> 32));
            long jMo1742getRangeOfLineContainingjx7JFs = anchorSelectable$foundation.mo1742getRangeOfLineContainingjx7JFs(offset);
            if (TextRange.getCollapsed-impl(jMo1742getRangeOfLineContainingjx7JFs)) {
                fCoerceIn = anchorSelectable$foundation.getLineLeft(offset);
            } else {
                float lineLeft = anchorSelectable$foundation.getLineLeft(TextRange.getStart-impl(jMo1742getRangeOfLineContainingjx7JFs));
                float lineRight = anchorSelectable$foundation.getLineRight(TextRange.getEnd-impl(jMo1742getRangeOfLineContainingjx7JFs) - 1);
                fCoerceIn = RangesKt.coerceIn(fIntBitsToFloat, Math.min(lineLeft, lineRight), Math.max(lineLeft, lineRight));
            }
            if (fCoerceIn == -1.0f) {
                return Offset.Companion.getUnspecified-F1C5BW0();
            }
            if (!IntSize.equals-impl0(j, IntSize.Companion.getZero-YbymL2g()) && Math.abs(fIntBitsToFloat - fCoerceIn) > ((int) (j >> 32)) / 2) {
                return Offset.Companion.getUnspecified-F1C5BW0();
            }
            float centerYForOffset = anchorSelectable$foundation.getCenterYForOffset(offset);
            return centerYForOffset == -1.0f ? Offset.Companion.getUnspecified-F1C5BW0() : containerLayoutCoordinates.localPositionOf-R5De75A(layoutCoordinates, Offset.constructor-impl((((long) Float.floatToRawIntBits(fCoerceIn)) << 32) | (4294967295L & ((long) Float.floatToRawIntBits(centerYForOffset)))));
        }
        return Offset.Companion.getUnspecified-F1C5BW0();
    }

    public static final Rect getSelectedRegionRect(List<? extends Pair<? extends Selectable, Selection>> list, LayoutCoordinates layoutCoordinates) {
        LayoutCoordinates layoutCoordinates2;
        int[] iArr;
        List<? extends Pair<? extends Selectable, Selection>> list2 = list;
        if (list2.isEmpty()) {
            return invertedInfiniteRect;
        }
        Rect rect = invertedInfiniteRect;
        float fComponent1 = rect.component1();
        float fComponent2 = rect.component2();
        float fComponent3 = rect.component3();
        float fComponent4 = rect.component4();
        int size = list2.size();
        char c = 0;
        int i = 0;
        while (i < size) {
            Pair<? extends Selectable, Selection> pair = list2.get(i);
            Selectable selectable = (Selectable) pair.component1();
            Selection selection = (Selection) pair.component2();
            int offset = selection.getStart().getOffset();
            int offset2 = selection.getEnd().getOffset();
            if (offset != offset2 && (layoutCoordinates2 = selectable.getLayoutCoordinates()) != null) {
                int iMin = Math.min(offset, offset2);
                int iMax = Math.max(offset, offset2) - 1;
                if (iMin == iMax) {
                    iArr = new int[1];
                    iArr[c] = iMin;
                } else {
                    int[] iArr2 = new int[2];
                    iArr2[c] = iMin;
                    iArr2[1] = iMax;
                    iArr = iArr2;
                }
                Rect rect2 = invertedInfiniteRect;
                float fComponent5 = rect2.component1();
                float fComponent6 = rect2.component2();
                float fComponent7 = rect2.component3();
                float fComponent8 = rect2.component4();
                int length = iArr.length;
                int i2 = 0;
                while (i2 < length) {
                    int i3 = i2;
                    Rect boundingBox = selectable.getBoundingBox(iArr[i3]);
                    fComponent5 = Math.min(fComponent5, boundingBox.getLeft());
                    fComponent6 = Math.min(fComponent6, boundingBox.getTop());
                    fComponent7 = Math.max(fComponent7, boundingBox.getRight());
                    fComponent8 = Math.max(fComponent8, boundingBox.getBottom());
                    i2 = i3 + 1;
                }
                long j = Offset.constructor-impl((((long) Float.floatToRawIntBits(fComponent5)) << 32) | (((long) Float.floatToRawIntBits(fComponent6)) & 4294967295L));
                long j2 = Offset.constructor-impl((((long) Float.floatToRawIntBits(fComponent8)) & 4294967295L) | (((long) Float.floatToRawIntBits(fComponent7)) << 32));
                long j3 = layoutCoordinates.localPositionOf-R5De75A(layoutCoordinates2, j);
                long j4 = layoutCoordinates.localPositionOf-R5De75A(layoutCoordinates2, j2);
                fComponent1 = Math.min(fComponent1, Float.intBitsToFloat((int) (j3 >> 32)));
                fComponent2 = Math.min(fComponent2, Float.intBitsToFloat((int) (j3 & 4294967295L)));
                fComponent3 = Math.max(fComponent3, Float.intBitsToFloat((int) (j4 >> 32)));
                fComponent4 = Math.max(fComponent4, Float.intBitsToFloat((int) (j4 & 4294967295L)));
            }
            i++;
            list2 = list;
            c = 0;
        }
        return new Rect(fComponent1, fComponent2, fComponent3, fComponent4);
    }

    public static final Selection merge(Selection selection, Selection selection2) {
        Selection selectionMerge;
        return (selection == null || (selectionMerge = selection.merge(selection2)) == null) ? selection2 : selectionMerge;
    }

    public static final Rect visibleBounds(LayoutCoordinates layoutCoordinates) {
        Rect rectBoundsInWindow$default = LayoutCoordinatesKt.boundsInWindow$default(layoutCoordinates, false, 1, (Object) null);
        return RectKt.Rect-0a9Yr6o(layoutCoordinates.windowToLocal-MK-Hz9U(rectBoundsInWindow$default.getTopLeft-F1C5BW0()), layoutCoordinates.windowToLocal-MK-Hz9U(rectBoundsInWindow$default.getBottomRight-F1C5BW0()));
    }
}

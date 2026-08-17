package androidx.compose.ui.semantics;

import androidx.collection.IntObjectMap;
import androidx.collection.IntObjectMapKt;
import androidx.collection.MutableIntObjectMap;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.semantics.SemanticsSortKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\u001aH\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u00042\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0000\u001a`\u0010\b\u001a\u00020\t*\u00020\u00022\u0016\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u000bj\b\u0012\u0004\u0012\u00020\u0002`\f2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u00042\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u00042\u0012\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00010\u000eH\u0002\u001aL\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00022\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u00042\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00010\u0012H\u0000\u001aL\u0010\u0013\u001a\u00020\u00052:\u0010\u0014\u001a6\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00170\u00150\u000bj\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00170\u0015`\f2\u0006\u0010\u0018\u001a\u00020\u0002H\u0002\"&\u0010\u0019\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00020\u001bj\b\u0012\u0004\u0012\u00020\u0002`\u001c0\u001aX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001d\" \u0010\u001e\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020 0\u001fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"subtreeSortedByGeometryGrouping", "", "Landroidx/compose/ui/semantics/SemanticsNode;", "isVisible", "Lkotlin/Function1;", "", "isFocusableContainer", "listToSort", "geometryDepthFirstSearch", "", "geometryList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "containerMapToChildren", "Landroidx/collection/MutableIntObjectMap;", "sortByGeometryGroupings", "parentListToSort", "containerChildrenMapping", "Landroidx/collection/IntObjectMap;", "placedEntryRowOverlaps", "rowGroupings", "Lkotlin/Pair;", "Landroidx/compose/ui/geometry/Rect;", "", "node", "semanticComparators", "", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "[Ljava/util/Comparator;", "UnmergedConfigComparator", "Lkotlin/Function2;", "", "ui"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SemanticsSortKt {
    private static final Function2<SemanticsNode, SemanticsNode, Integer> UnmergedConfigComparator;
    private static final Comparator<SemanticsNode>[] semanticComparators;

    static {
        Comparator<SemanticsNode>[] comparatorArr = new Comparator[2];
        int i = 0;
        while (i < 2) {
            final Comparator comparator = i == 0 ? RtlBoundsComparator.INSTANCE : LtrBoundsComparator.INSTANCE;
            final Comparator<LayoutNode> zComparator$ui = LayoutNode.INSTANCE.getZComparator$ui();
            final Comparator comparator2 = new Comparator() { // from class: androidx.compose.ui.semantics.SemanticsSortKt$special$$inlined$thenBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    int iCompare = comparator.compare(t, t2);
                    return iCompare != 0 ? iCompare : zComparator$ui.compare(((SemanticsNode) t).getLayoutNode(), ((SemanticsNode) t2).getLayoutNode());
                }
            };
            comparatorArr[i] = new Comparator() { // from class: androidx.compose.ui.semantics.SemanticsSortKt$special$$inlined$thenBy$2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    int iCompare = comparator2.compare(t, t2);
                    return iCompare != 0 ? iCompare : ComparisonsKt.compareValues(Integer.valueOf(((SemanticsNode) t).getId()), Integer.valueOf(((SemanticsNode) t2).getId()));
                }
            };
            i++;
        }
        semanticComparators = comparatorArr;
        UnmergedConfigComparator = new Function2<SemanticsNode, SemanticsNode, Integer>() { // from class: androidx.compose.ui.semantics.SemanticsSortKt$UnmergedConfigComparator$1
            public final Integer invoke(SemanticsNode semanticsNode, SemanticsNode semanticsNode2) {
                SemanticsConfiguration unmergedConfig = semanticsNode.getUnmergedConfig();
                SemanticsProperties semanticsProperties = SemanticsProperties.INSTANCE;
                return Integer.valueOf(Float.compare(((Number) unmergedConfig.getOrElse(semanticsProperties.getTraversalIndex(), new Function0<Float>() { // from class: androidx.compose.ui.semantics.SemanticsSortKt$UnmergedConfigComparator$1.1
                    /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                    public final Float m5266invoke() {
                        return Float.valueOf(0.0f);
                    }
                })).floatValue(), ((Number) semanticsNode2.getUnmergedConfig().getOrElse(semanticsProperties.getTraversalIndex(), new Function0<Float>() { // from class: androidx.compose.ui.semantics.SemanticsSortKt$UnmergedConfigComparator$1.2
                    /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                    public final Float m5267invoke() {
                        return Float.valueOf(0.0f);
                    }
                })).floatValue()));
            }
        };
    }

    public static int a(Function2 function2, Object obj, Object obj2) {
        return ((Number) function2.invoke(obj, obj2)).intValue();
    }

    private static final void geometryDepthFirstSearch(SemanticsNode semanticsNode, ArrayList<SemanticsNode> arrayList, Function1<? super SemanticsNode, Boolean> function1, Function1<? super SemanticsNode, Boolean> function2, MutableIntObjectMap<List<SemanticsNode>> mutableIntObjectMap) {
        boolean zBooleanValue = ((Boolean) semanticsNode.getUnmergedConfig().getOrElse(SemanticsProperties.INSTANCE.getIsTraversalGroup(), new Function0<Boolean>() { // from class: androidx.compose.ui.semantics.SemanticsSortKt$geometryDepthFirstSearch$isTraversalGroup$1
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Boolean m5268invoke() {
                return Boolean.FALSE;
            }
        })).booleanValue();
        if ((zBooleanValue || ((Boolean) function2.invoke(semanticsNode)).booleanValue()) && ((Boolean) function1.invoke(semanticsNode)).booleanValue()) {
            arrayList.add(semanticsNode);
        }
        if (zBooleanValue) {
            mutableIntObjectMap.set(semanticsNode.getId(), subtreeSortedByGeometryGrouping(semanticsNode, function1, function2, semanticsNode.getChildren()));
            return;
        }
        List<SemanticsNode> children = semanticsNode.getChildren();
        int size = children.size();
        for (int i = 0; i < size; i++) {
            geometryDepthFirstSearch(children.get(i), arrayList, function1, function2, mutableIntObjectMap);
        }
    }

    private static final boolean placedEntryRowOverlaps(ArrayList<Pair<Rect, List<SemanticsNode>>> arrayList, SemanticsNode semanticsNode) {
        float top = semanticsNode.getBoundsInWindow().getTop();
        float bottom = semanticsNode.getBoundsInWindow().getBottom();
        boolean z = top >= bottom;
        int lastIndex = CollectionsKt.getLastIndex(arrayList);
        if (lastIndex >= 0) {
            int i = 0;
            while (true) {
                Rect rect = (Rect) arrayList.get(i).getFirst();
                boolean z2 = rect.getTop() >= rect.getBottom();
                if (!z && !z2 && Math.max(top, rect.getTop()) < Math.min(bottom, rect.getBottom())) {
                    arrayList.set(i, new Pair<>(rect.intersect(0.0f, top, Float.POSITIVE_INFINITY, bottom), arrayList.get(i).getSecond()));
                    ((List) arrayList.get(i).getSecond()).add(semanticsNode);
                    return true;
                }
                if (i != lastIndex) {
                    i++;
                }
            }
        }
        return false;
    }

    public static final List<SemanticsNode> sortByGeometryGroupings(SemanticsNode semanticsNode, List<SemanticsNode> list, Function1<? super SemanticsNode, Boolean> function1, IntObjectMap<List<SemanticsNode>> intObjectMap) {
        int size = 0;
        char c = semanticsNode.getLayoutInfo().getLayoutDirection() == LayoutDirection.Rtl ? (char) 1 : (char) 0;
        ArrayList arrayList = new ArrayList(list.size() / 2);
        int lastIndex = CollectionsKt.getLastIndex(list);
        if (lastIndex >= 0) {
            int i = 0;
            while (true) {
                SemanticsNode semanticsNode2 = list.get(i);
                if (i == 0 || !placedEntryRowOverlaps(arrayList, semanticsNode2)) {
                    arrayList.add(new Pair(semanticsNode2.getBoundsInWindow(), CollectionsKt.mutableListOf(new SemanticsNode[]{semanticsNode2})));
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        CollectionsKt.sortWith(arrayList, TopBottomBoundsComparator.INSTANCE);
        ArrayList arrayList2 = new ArrayList();
        Comparator<SemanticsNode> comparator = semanticComparators[c ^ 1];
        int size2 = arrayList.size();
        for (int i2 = 0; i2 < size2; i2++) {
            Pair pair = (Pair) arrayList.get(i2);
            CollectionsKt.sortWith((List) pair.getSecond(), comparator);
            arrayList2.addAll((Collection) pair.getSecond());
        }
        final Function2<SemanticsNode, SemanticsNode, Integer> function2 = UnmergedConfigComparator;
        CollectionsKt.sortWith(arrayList2, new Comparator() { // from class: k6d
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return SemanticsSortKt.a(function2, obj, obj2);
            }
        });
        while (size <= CollectionsKt.getLastIndex(arrayList2)) {
            List list2 = (List) intObjectMap.get(((SemanticsNode) arrayList2.get(size)).getId());
            if (list2 != null) {
                if (((Boolean) function1.invoke(arrayList2.get(size))).booleanValue()) {
                    size++;
                } else {
                    arrayList2.remove(size);
                }
                arrayList2.addAll(size, list2);
                size += list2.size();
            } else {
                size++;
            }
        }
        return arrayList2;
    }

    public static /* synthetic */ List sortByGeometryGroupings$default(SemanticsNode semanticsNode, List list, Function1 function1, IntObjectMap intObjectMap, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1<SemanticsNode, Boolean>() { // from class: androidx.compose.ui.semantics.SemanticsSortKt.sortByGeometryGroupings.1
                public final Boolean invoke(SemanticsNode semanticsNode2) {
                    return Boolean.FALSE;
                }
            };
        }
        if ((i & 4) != 0) {
            intObjectMap = IntObjectMapKt.intObjectMapOf();
        }
        return sortByGeometryGroupings(semanticsNode, list, function1, intObjectMap);
    }

    public static final List<SemanticsNode> subtreeSortedByGeometryGrouping(SemanticsNode semanticsNode, Function1<? super SemanticsNode, Boolean> function1, Function1<? super SemanticsNode, Boolean> function2, List<SemanticsNode> list) {
        MutableIntObjectMap mutableIntObjectMapMutableIntObjectMapOf = IntObjectMapKt.mutableIntObjectMapOf();
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            geometryDepthFirstSearch(list.get(i), arrayList, function1, function2, mutableIntObjectMapMutableIntObjectMapOf);
        }
        return sortByGeometryGroupings(semanticsNode, arrayList, function2, mutableIntObjectMapMutableIntObjectMapOf);
    }
}

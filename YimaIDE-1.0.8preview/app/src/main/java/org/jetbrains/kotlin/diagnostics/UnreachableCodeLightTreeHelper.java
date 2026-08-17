package org.jetbrains.kotlin.diagnostics;

import com.intellij.lang.LighterASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.UnreachableCodeLightTreeHelper;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.util.LightTreeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0010\u0010\u0002\u001a\f\u0012\b\u0012\u00060\u0004j\u0002`\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007J \u0010\n\u001a\u00020\u000b*\u00060\u0004j\u0002`\u00052\u0010\u0010\f\u001a\f\u0012\b\u0012\u00060\u0004j\u0002`\u00050\rJ<\u0010\u000e\u001a\f\u0012\b\u0012\u00060\u0004j\u0002`\u00050\u000f*\u00060\u0004j\u0002`\u00052\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u00060\u0004j\u0002`\u00050\r2\u0010\u0010\u0011\u001a\f\u0012\b\u0012\u00060\u0004j\u0002`\u00050\rJ0\u0010\u0012\u001a\f\u0012\b\u0012\u00060\u0004j\u0002`\u00050\u000f*\f\u0012\b\u0012\u00060\u0004j\u0002`\u00050\u000f2\u0010\u0010\u0013\u001a\f\u0012\b\u0012\u00060\u0004j\u0002`\u00050\rJ\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u000f*\b\u0012\u0004\u0012\u00020\u00150\u000fR\u001b\u0010\u0002\u001a\f\u0012\b\u0012\u00060\u0004j\u0002`\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/UnreachableCodeLightTreeHelper;", Argument.Delimiters.none, "tree", "Lcom/intellij/util/diff/FlyweightCapableTreeStructure;", "Lcom/intellij/lang/LighterASTNode;", "Lorg/jetbrains/kotlin/diagnostics/Node;", "<init>", "(Lcom/intellij/util/diff/FlyweightCapableTreeStructure;)V", "getTree", "()Lcom/intellij/util/diff/FlyweightCapableTreeStructure;", "hasChildrenInSet", Argument.Delimiters.none, "set", Argument.Delimiters.none, "getLeavesOrReachableChildren", Argument.Delimiters.none, "reachable", "unreachable", "removeReachableElementsWithMeaninglessSiblings", "reachableElements", "mergeAdjacentTextRanges", "Lcom/intellij/openapi/util/TextRange;", "org.jetbrains.kotlin:frontend.common-psi"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class UnreachableCodeLightTreeHelper {
    private final FlyweightCapableTreeStructure<LighterASTNode> tree;

    public UnreachableCodeLightTreeHelper(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure) {
        flyweightCapableTreeStructure.getClass();
        this.tree = flyweightCapableTreeStructure;
    }

    public static boolean a(Ref.BooleanRef booleanRef, LighterASTNode lighterASTNode, Set set, LighterASTNode lighterASTNode2) {
        lighterASTNode2.getClass();
        if (!booleanRef.element && !Intrinsics.areEqual(lighterASTNode2, lighterASTNode) && set.contains(lighterASTNode2)) {
            booleanRef.element = true;
        }
        return !booleanRef.element;
    }

    public static boolean b(Set set, UnreachableCodeLightTreeHelper unreachableCodeLightTreeHelper, Set set2, List list, LighterASTNode lighterASTNode) {
        lighterASTNode.getClass();
        if ((!set.contains(lighterASTNode) || unreachableCodeLightTreeHelper.hasChildrenInSet(lighterASTNode, set2)) && !LightTreeUtilsKt.getChildren(lighterASTNode, unreachableCodeLightTreeHelper.tree).isEmpty()) {
            return true;
        }
        list.add(lighterASTNode);
        return false;
    }

    private static final void removeReachableElementsWithMeaninglessSiblings$collectSiblingsIfMeaningless(List<? extends LighterASTNode> list, Set<LighterASTNode> set, int i, int i2) {
        int i3 = i + i2;
        if (i3 < 0 || i3 >= list.size()) {
            return;
        }
        LighterASTNode lighterASTNode = list.get(i3);
        if (LightTreePositioningStrategyKt.isFiller(lighterASTNode) || Intrinsics.areEqual(lighterASTNode.getTokenType(), KtTokens.COMMA)) {
            set.add(lighterASTNode);
            removeReachableElementsWithMeaninglessSiblings$collectSiblingsIfMeaningless(list, set, i3, i2);
        }
    }

    public final List<LighterASTNode> getLeavesOrReachableChildren(LighterASTNode lighterASTNode, final Set<? extends LighterASTNode> set, final Set<? extends LighterASTNode> set2) {
        lighterASTNode.getClass();
        set.getClass();
        set2.getClass();
        final ArrayList arrayList = new ArrayList();
        LightTreePositioningStrategiesKt.traverseDescendants(this.tree, lighterASTNode, new Function1() { // from class: x0f
            public final Object invoke(Object obj) {
                return Boolean.valueOf(UnreachableCodeLightTreeHelper.b(set, this, set2, arrayList, (LighterASTNode) obj));
            }
        });
        return arrayList;
    }

    public final FlyweightCapableTreeStructure<LighterASTNode> getTree() {
        return this.tree;
    }

    public final boolean hasChildrenInSet(final LighterASTNode lighterASTNode, final Set<? extends LighterASTNode> set) {
        lighterASTNode.getClass();
        set.getClass();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        LightTreePositioningStrategiesKt.traverseDescendants(this.tree, lighterASTNode, new Function1() { // from class: w0f
            public final Object invoke(Object obj) {
                return Boolean.valueOf(UnreachableCodeLightTreeHelper.a(booleanRef, lighterASTNode, set, (LighterASTNode) obj));
            }
        });
        return booleanRef.element;
    }

    public final List<TextRange> mergeAdjacentTextRanges(List<? extends TextRange> list) {
        list.getClass();
        ArrayList arrayList = new ArrayList();
        TextRange textRangeUnion = null;
        for (TextRange textRange : list) {
            if (textRangeUnion != null) {
                if (textRangeUnion.getEndOffset() == textRange.getStartOffset()) {
                    textRangeUnion = textRangeUnion.union(textRange);
                } else {
                    arrayList.add(textRangeUnion);
                }
            }
            textRangeUnion = textRange;
        }
        if (textRangeUnion != null) {
            arrayList.add(textRangeUnion);
        }
        return arrayList;
    }

    public final List<LighterASTNode> removeReachableElementsWithMeaninglessSiblings(List<? extends LighterASTNode> list, Set<? extends LighterASTNode> set) {
        list.getClass();
        set.getClass();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        List<? extends LighterASTNode> list2 = list;
        int i = 0;
        for (LighterASTNode lighterASTNode : list2) {
            int i2 = i + 1;
            if (set.contains(lighterASTNode)) {
                linkedHashSet.add(lighterASTNode);
                removeReachableElementsWithMeaninglessSiblings$collectSiblingsIfMeaningless(list, linkedHashSet, i, -1);
                removeReachableElementsWithMeaninglessSiblings$collectSiblingsIfMeaningless(list, linkedHashSet, i, 1);
            }
            i = i2;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : list2) {
            if (!linkedHashSet.contains((LighterASTNode) obj)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}

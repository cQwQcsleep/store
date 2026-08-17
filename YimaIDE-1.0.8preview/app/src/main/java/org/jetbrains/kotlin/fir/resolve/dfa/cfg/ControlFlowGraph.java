package org.jetbrains.kotlin.fir.resolve.dfa.cfg;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u00015B!\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u001c\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00002\u0006\u0010-\u001a\u00020.H\u0007b\u0002\b\u0016J\f\u0010/\u001a\u00020+H\u0007b\u0002\b\u0016J\u001e\u00100\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00190\u00182\u0006\u00101\u001a\u00020&H\u0007b\u0002\b\u0016J\u000e\u00102\u001a\u00020+2\u0006\u00103\u001a\u000204R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR!\u0010\u0010\u001a\u00020\u0011@\u0007X\u0086\u000e\u0082\u0001\u0002\b\u0016¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R2\u0010\u001a\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00190\u00182\u0010\u0010\u0017\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00190\u0018@BX\u0086.¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR%\u0010\u001d\u001a\u0006\u0012\u0002\b\u00030\u0019@\u0007X\u0086.\u0082\u0001\u0002\b\u0016¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R%\u0010\"\u001a\u0006\u0012\u0002\b\u00030\u0019@\u0007X\u0086.\u0082\u0001\u0002\b\u0016¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001f\"\u0004\b$\u0010!R\u0011\u0010%\u001a\u00020&8F¢\u0006\u0006\u001a\u0004\b%\u0010'R\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00000\u00188F¢\u0006\u0006\u001a\u0004\b)\u0010\u001c¨\u00066"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", Argument.Delimiters.none, "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", ModuleXmlParser.NAME, Argument.Delimiters.none, "kind", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph$Kind;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Ljava/lang/String;Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph$Kind;)V", "getDeclaration", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "getName", "()Ljava/lang/String;", "getKind", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph$Kind;", "nodeCount", Argument.Delimiters.none, "getNodeCount", "()I", "setNodeCount", "(I)V", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CfgInternals;", "value", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "nodes", "getNodes", "()Ljava/util/List;", "enterNode", "getEnterNode", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "setEnterNode", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;)V", "exitNode", "getExitNode", "setExitNode", "isSubGraph", Argument.Delimiters.none, "()Z", "subGraphs", "getSubGraphs", "copyData", Argument.Delimiters.none, "from", "mapper", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowNodeMapper;", "complete", "orderNodes", "isComplete", "traverse", "visitor", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphVisitorVoid;", "Kind", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ControlFlowGraph {
    private final FirDeclaration declaration;
    public CFGNode<?> enterNode;
    public CFGNode<?> exitNode;
    private final Kind kind;
    private final String name;
    private int nodeCount;
    private List<? extends CFGNode<?>> nodes;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0010\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph$Kind;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "File", "Class", "Constructor", "Function", "Script", "LocalFunction", "AnonymousFunction", "AnonymousFunctionCalledInPlace", "PropertyInitializer", "ClassInitializer", "FieldInitializer", "FakeCall", "DefaultArgument", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum Kind {
        File,
        Class,
        Constructor,
        Function,
        Script,
        LocalFunction,
        AnonymousFunction,
        AnonymousFunctionCalledInPlace,
        PropertyInitializer,
        ClassInitializer,
        FieldInitializer,
        FakeCall,
        DefaultArgument;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<Kind> getEntries() {
            return $ENTRIES;
        }
    }

    public ControlFlowGraph(FirDeclaration firDeclaration, String str, Kind kind) {
        str.getClass();
        kind.getClass();
        this.declaration = firDeclaration;
        this.name = str;
        this.kind = kind;
    }

    @CfgInternals
    public final void complete() {
        this.nodes = orderNodes(true);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @CfgInternals
    public final void copyData(ControlFlowGraph from, ControlFlowNodeMapper mapper) throws UninitializedPropertyAccessException {
        from.getClass();
        mapper.getClass();
        if (from.nodes != null) {
            List<CFGNode<?>> nodes = from.getNodes();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(nodes, 10));
            Iterator<T> it = nodes.iterator();
            while (it.hasNext()) {
                arrayList.add(mapper.get((CFGNode) it.next()));
            }
            this.nodes = arrayList;
        }
        if (from.enterNode != null) {
            setEnterNode(mapper.get(from.getEnterNode()));
        }
        if (from.exitNode != null) {
            setExitNode(mapper.get(from.getExitNode()));
        }
    }

    public final FirDeclaration getDeclaration() {
        return this.declaration;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final CFGNode<?> getEnterNode() throws UninitializedPropertyAccessException {
        CFGNode<?> cFGNode = this.enterNode;
        if (cFGNode != null) {
            return cFGNode;
        }
        Intrinsics.throwUninitializedPropertyAccessException("enterNode");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final CFGNode<?> getExitNode() throws UninitializedPropertyAccessException {
        CFGNode<?> cFGNode = this.exitNode;
        if (cFGNode != null) {
            return cFGNode;
        }
        Intrinsics.throwUninitializedPropertyAccessException("exitNode");
        return null;
    }

    public final Kind getKind() {
        return this.kind;
    }

    public final String getName() {
        return this.name;
    }

    public final int getNodeCount() {
        return this.nodeCount;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final List<CFGNode<?>> getNodes() throws UninitializedPropertyAccessException {
        List list = this.nodes;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("nodes");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final List<ControlFlowGraph> getSubGraphs() throws UninitializedPropertyAccessException {
        List<ControlFlowGraph> listEmptyList;
        List<CFGNode<?>> nodes = getNodes();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = nodes.iterator();
        while (it.hasNext()) {
            CFGNode cFGNode = (CFGNode) it.next();
            CFGNodeWithSubgraphs cFGNodeWithSubgraphs = cFGNode instanceof CFGNodeWithSubgraphs ? (CFGNodeWithSubgraphs) cFGNode : null;
            if (cFGNodeWithSubgraphs == null || (listEmptyList = cFGNodeWithSubgraphs.getSubGraphs()) == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            CollectionsKt.addAll(arrayList, listEmptyList);
        }
        return arrayList;
    }

    public final boolean isSubGraph() {
        return !getEnterNode().getPreviousNodes().isEmpty();
    }

    @CfgInternals
    public final List<CFGNode<?>> orderNodes(boolean isComplete) {
        ArrayList arrayList = new ArrayList(this.nodeCount);
        arrayList.add(getEnterNode());
        int[] iArr = new int[this.nodeCount];
        int i = 0;
        while (i < arrayList.size()) {
            int i2 = i + 1;
            Object obj = arrayList.get(i);
            obj.getClass();
            CFGNode cFGNode = (CFGNode) obj;
            for (CFGNode<?> cFGNode2 : cFGNode.getFollowingNodes()) {
                if (Intrinsics.areEqual(cFGNode2.getOwner(), this)) {
                    if (cFGNode2.getPreviousNodes().size() == 1) {
                        arrayList.add(cFGNode2);
                    } else if (!cFGNode.edgeTo(cFGNode2).getKind().getIsBack()) {
                        int previousNodeCount = iArr[cFGNode2.getId()];
                        if (previousNodeCount == 0) {
                            previousNodeCount = ControlFlowGraphKt.getPreviousNodeCount(cFGNode2);
                        }
                        int i3 = previousNodeCount - 1;
                        if (i3 == 0) {
                            arrayList.add(cFGNode2);
                        }
                        iArr[cFGNode2.getId()] = i3;
                    }
                }
            }
            i = i2;
        }
        if (isComplete) {
            arrayList.size();
        }
        return arrayList;
    }

    @CfgInternals
    public final void setEnterNode(CFGNode<?> cFGNode) {
        cFGNode.getClass();
        this.enterNode = cFGNode;
    }

    @CfgInternals
    public final void setExitNode(CFGNode<?> cFGNode) {
        cFGNode.getClass();
        this.exitNode = cFGNode;
    }

    @CfgInternals
    public final void setNodeCount(int i) {
        this.nodeCount = i;
    }

    public final void traverse(ControlFlowGraphVisitorVoid visitor) {
        List<ControlFlowGraph> subGraphs;
        visitor.getClass();
        for (CFGNode<?> cFGNode : getNodes()) {
            cFGNode.accept(visitor);
            CFGNodeWithSubgraphs cFGNodeWithSubgraphs = cFGNode instanceof CFGNodeWithSubgraphs ? (CFGNodeWithSubgraphs) cFGNode : null;
            if (cFGNodeWithSubgraphs != null && (subGraphs = cFGNodeWithSubgraphs.getSubGraphs()) != null) {
                Iterator<T> it = subGraphs.iterator();
                while (it.hasNext()) {
                    ((ControlFlowGraph) it.next()).traverse(visitor);
                }
            }
        }
    }
}

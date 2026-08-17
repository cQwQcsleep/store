package org.jetbrains.kotlin.fir.resolve.dfa.cfg;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.state.InlineClassManglingUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.resolve.dfa.DataFlowVariable;
import org.jetbrains.kotlin.fir.resolve.dfa.Implication;
import org.jetbrains.kotlin.fir.resolve.dfa.OperationStatement;
import org.jetbrains.kotlin.fir.resolve.dfa.PersistentFlow;
import org.jetbrains.kotlin.fir.resolve.dfa.RealVariable;
import org.jetbrains.kotlin.fir.resolve.dfa.Statement;
import org.jetbrains.kotlin.fir.resolve.dfa.SyntheticVariable;
import org.jetbrains.kotlin.fir.resolve.dfa.TypeStatement;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphRenderer;
import org.jetbrains.kotlin.utils.DFS;
import org.jetbrains.kotlin.utils.Printer;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000 ,2\u00020\u0001:\u0001,B\u001b\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001c\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0013J\u000e\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0016J$\u0010\u0017\u001a\u00020\u000f*\u00020\n2\u0016\u0010\u0018\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001a\u0012\u0004\u0012\u00020\f0\u0019H\u0002J$\u0010\u001f\u001a\u00020\u000f*\u00020\n2\u0016\u0010\u0018\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001a\u0012\u0004\u0012\u00020\f0\u0019H\u0002J\u0014\u0010 \u001a\u00020\u000f*\u00020\n2\u0006\u0010!\u001a\u00020\u0011H\u0002J\f\u0010\"\u001a\u00020\u000f*\u00020\nH\u0002J\f\u0010#\u001a\u00020\u0011*\u00020$H\u0002J\f\u0010#\u001a\u00020\u0011*\u00020(H\u0002J\f\u0010#\u001a\u00020\u0011*\u00020)H\u0002J\f\u0010*\u001a\u00020\u0011*\u00020+H\u0002J\f\u0010#\u001a\u00020\u0011*\u00020\u0001H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u0004\u0018\u00010\u0011*\u00020\u001c8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u001a\u0010%\u001a\u000e\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020\f0&X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphRenderer;", Argument.Delimiters.none, "builder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "options", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphRenderOptions;", "<init>", "(Ljava/lang/StringBuilder;Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphRenderOptions;)V", "printer", "Lorg/jetbrains/kotlin/utils/Printer;", "nodeCounter", Argument.Delimiters.none, "clusterCounter", "renderCompleteGraph", Argument.Delimiters.none, "graphName", Argument.Delimiters.none, "printNodesAndEdges", "Lkotlin/Function0;", "renderPartialGraph", "controlFlowGraph", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "renderNodes", "nodes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "style", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/Edge;", "getStyle", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/Edge;)Ljava/lang/String;", "renderEdges", "enterCluster", "color", "exitCluster", "renderHtmlLike", "Lorg/jetbrains/kotlin/fir/resolve/dfa/PersistentFlow;", "firElementIndices", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirElement;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowVariable;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/Statement;", "renderTypeHtmlLike", "Lorg/jetbrains/kotlin/fir/resolve/dfa/TypeStatement;", "Companion", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ControlFlowGraphRenderer {
    private static final Regex DIGIT_REGEX = new Regex("\\d");
    private int clusterCounter;
    private final Map<FirElement, Integer> firElementIndices;
    private int nodeCounter;
    private final ControlFlowGraphRenderOptions options;
    private final Printer printer;

    @Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u000e"}, d2 = {"org/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphRenderer$renderHtmlLike$OutputEntity", Argument.Delimiters.none, ModuleXmlParser.NAME, Argument.Delimiters.none, "text", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getText", "compareTo", Argument.Delimiters.none, "other", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphRenderer$renderHtmlLike$OutputEntity;)I", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class OutputEntity implements Comparable<OutputEntity> {
        private final String name;
        private final String text;

        public OutputEntity(String str, String str2) {
            str.getClass();
            str2.getClass();
            this.name = str;
            this.text = str2;
        }

        public static Comparable a(OutputEntity outputEntity) {
            outputEntity.getClass();
            return outputEntity.name;
        }

        public static Comparable b(OutputEntity outputEntity) {
            outputEntity.getClass();
            return outputEntity.text;
        }

        @Override // java.lang.Comparable
        public int compareTo(OutputEntity other) {
            other.getClass();
            return ComparisonsKt.compareValuesBy(this, other, new Function1[]{new Function1() { // from class: ww2
                public final Object invoke(Object obj) {
                    return ControlFlowGraphRenderer.OutputEntity.a((ControlFlowGraphRenderer.OutputEntity) obj);
                }
            }, new Function1() { // from class: xw2
                public final Object invoke(Object obj) {
                    return ControlFlowGraphRenderer.OutputEntity.b((ControlFlowGraphRenderer.OutputEntity) obj);
                }
            }});
        }

        public final String getName() {
            return this.name;
        }

        public final String getText() {
            return this.text;
        }
    }

    public ControlFlowGraphRenderer(StringBuilder sb, ControlFlowGraphRenderOptions controlFlowGraphRenderOptions) {
        sb.getClass();
        controlFlowGraphRenderOptions.getClass();
        this.options = controlFlowGraphRenderOptions;
        this.printer = new Printer(sb, 0, (String) null, 6, (DefaultConstructorMarker) null);
        this.firElementIndices = new LinkedHashMap();
    }

    public static Iterable a(CFGNode cFGNode) {
        return cFGNode.getFollowingNodes();
    }

    public static CharSequence c(OutputEntity outputEntity) {
        outputEntity.getClass();
        return outputEntity.getText();
    }

    private final void enterCluster(Printer printer, String str) {
        StringBuilder sb = new StringBuilder("subgraph cluster_");
        int i = this.clusterCounter;
        this.clusterCounter = i + 1;
        sb.append(i);
        sb.append(" {");
        printer.println(new Object[]{sb.toString()});
        printer.pushIndent();
        printer.println(new Object[]{"color=" + str});
    }

    private final void exitCluster(Printer printer) {
        printer.popIndent();
        printer.println(new Object[]{"}"});
    }

    private final String getStyle(Edge edge) {
        String str;
        String str2;
        String str3;
        if (edge.getKind().getUsedInDfa() || edge.getKind().getUsedInDeadDfa()) {
            str = !edge.getKind().getUsedInCfa() ? "color=red" : null;
        } else {
            str = "color=green";
        }
        if (edge.getKind().getIsDead()) {
            str2 = "style=dotted";
        } else {
            str2 = edge.getKind().getIsBack() ? "style=dashed" : null;
        }
        String label = edge.getLabel().getLabel();
        if (label != null) {
            str3 = "label=\"" + label + '\"';
        } else {
            str3 = null;
        }
        List listListOfNotNull = CollectionsKt.listOfNotNull(new String[]{str, str2, str3});
        if (listListOfNotNull.isEmpty()) {
            listListOfNotNull = null;
        }
        List list = listListOfNotNull;
        if (list != null) {
            return CollectionsKt.joinToString$default(list, Argument.Delimiters.space, "[", "]", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
        }
        return null;
    }

    private final void renderEdges(Printer printer, Map<CFGNode<?>, Integer> map) {
        for (Map.Entry<CFGNode<?>, Integer> entry : map.entrySet()) {
            CFGNode<?> key = entry.getKey();
            int iIntValue = entry.getValue().intValue();
            List<CFGNode<?>> followingNodes = key.getFollowingNodes();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Object obj : followingNodes) {
                String style = getStyle(key.edgeTo((CFGNode) obj));
                Object arrayList = linkedHashMap.get(style);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    linkedHashMap.put(style, arrayList);
                }
                ((List) arrayList).add(obj);
            }
            for (Map.Entry entry2 : CollectionsKt.sortedWith(linkedHashMap.entrySet(), new Comparator() { // from class: org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphRenderer$renderEdges$$inlined$sortedBy$1
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues((String) ((Map.Entry) t).getKey(), (String) ((Map.Entry) t2).getKey());
                }
            })) {
                String str = (String) entry2.getKey();
                List list = (List) entry2.getValue();
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    arrayList2.add(Integer.valueOf(((Number) MapsKt.getValue(map, (CFGNode) it.next())).intValue()));
                }
                printer.print(new Object[]{Integer.valueOf(iIntValue), " -> ", CollectionsKt.joinToString$default(CollectionsKt.sorted(arrayList2), Argument.Delimiters.space, "{", "}", 0, (CharSequence) null, (Function1) null, 56, (Object) null)});
                if (str != null) {
                    printer.printWithNoIndent(new Object[]{Argument.Delimiters.space.concat(str)});
                }
                printer.printlnWithNoIndent(new Object[]{Argument.Delimiters.semicolon});
            }
            if (key instanceof CFGNodeWithSubgraphs) {
                List<ControlFlowGraph> subGraphs = ((CFGNodeWithSubgraphs) key).getSubGraphs();
                ArrayList arrayList3 = new ArrayList();
                Iterator<T> it2 = subGraphs.iterator();
                while (it2.hasNext()) {
                    Integer num = map.get(((ControlFlowGraph) it2.next()).getEnterNode());
                    if (num != null) {
                        arrayList3.add(num);
                    }
                }
                List listSorted = CollectionsKt.sorted(arrayList3);
                if (!listSorted.isEmpty()) {
                    printer.print(new Object[]{Integer.valueOf(iIntValue), " -> ", CollectionsKt.joinToString$default(listSorted, Argument.Delimiters.space, "{", "}", 0, (CharSequence) null, (Function1) null, 56, (Object) null)});
                    printer.printlnWithNoIndent(new Object[]{" [style=dashed];"});
                }
            }
        }
    }

    private final String renderHtmlLike(PersistentFlow persistentFlow) {
        Set<DataFlowVariable> allVariablesForDebug = persistentFlow.getAllVariablesForDebug();
        ArrayList arrayList = new ArrayList(allVariablesForDebug.size());
        for (DataFlowVariable dataFlowVariable : allVariablesForDebug) {
            String strRenderHtmlLike = renderHtmlLike(dataFlowVariable);
            StringBuilder sb = new StringBuilder();
            sb.append("<BR/>");
            sb.append("<B>");
            sb.append(strRenderHtmlLike);
            sb.append("</B>");
            if (dataFlowVariable instanceof RealVariable) {
                RealVariable realVariableUnwrapVariable = persistentFlow.unwrapVariable((RealVariable) dataFlowVariable);
                if (Intrinsics.areEqual(realVariableUnwrapVariable, dataFlowVariable)) {
                    TypeStatement typeStatement = persistentFlow.getTypeStatement(dataFlowVariable);
                    if (typeStatement != null) {
                        sb.append(": ");
                        sb.append(renderTypeHtmlLike(typeStatement));
                    }
                } else {
                    sb.append(" = ");
                    sb.append(renderHtmlLike((DataFlowVariable) realVariableUnwrapVariable));
                }
            } else if (dataFlowVariable instanceof SyntheticVariable) {
                sb.append(" = '");
                sb.append(renderHtmlLike(UtilsKt.render(((SyntheticVariable) dataFlowVariable).getFir())));
                sb.append("'");
            }
            List implications = persistentFlow.getImplications(dataFlowVariable);
            if (implications == null) {
                implications = CollectionsKt.emptyList();
            }
            ArrayList arrayList2 = new ArrayList(implications.size());
            for (Implication implication : implications) {
                arrayList2.add(new OutputEntity(renderHtmlLike(implication.getEffect().getVariable()), "<BR/> " + renderHtmlLike(implication.getCondition().getOperation()) + " =&gt; " + renderHtmlLike(implication.getEffect())));
            }
            CollectionsKt.sort(arrayList2);
            CollectionsKt.joinTo$default(arrayList2, sb, Argument.Delimiters.none, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: org.jetbrains.kotlin.fir.resolve.dfa.cfg.c
                public final Object invoke(Object obj) {
                    return ControlFlowGraphRenderer.renderHtmlLike$lambda$0$2((ControlFlowGraphRenderer.OutputEntity) obj);
                }
            }, 60, (Object) null);
            sb.append("<BR/>");
            arrayList.add(new OutputEntity(strRenderHtmlLike, sb.toString()));
        }
        CollectionsKt.sort(arrayList);
        return CollectionsKt.joinToString$default(arrayList, Argument.Delimiters.none, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: org.jetbrains.kotlin.fir.resolve.dfa.cfg.d
            public final Object invoke(Object obj) {
                return ControlFlowGraphRenderer.c((ControlFlowGraphRenderer.OutputEntity) obj);
            }
        }, 30, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence renderHtmlLike$lambda$0$2(OutputEntity outputEntity) {
        outputEntity.getClass();
        return outputEntity.getText();
    }

    private final void renderNodes(Printer printer, Map<CFGNode<?>, Integer> map) {
        PersistentFlow flow;
        String str = "red";
        for (Map.Entry<CFGNode<?>, Integer> entry : map.entrySet()) {
            CFGNode<?> key = entry.getKey();
            int iIntValue = entry.getValue().intValue();
            if (key instanceof EnterNodeMarker) {
                enterCluster(printer, str);
                str = "blue";
            }
            ArrayList arrayList = new ArrayList();
            String strRender = CFGNodeRendererKt.render(key);
            if (this.options.getRenderLevels()) {
                strRender = strRender + " [" + key.getLevel() + ']';
            }
            String str2 = strRender;
            String str3 = null;
            CFGNode<?> cFGNode = (this.options.getRenderFlow() && key.getFlowInitialized()) ? key : null;
            String strRenderHtmlLike = (cFGNode == null || (flow = cFGNode.getFlow()) == null) ? null : renderHtmlLike(flow);
            String str4 = (String) this.options.getData().invoke(key);
            if ((strRenderHtmlLike == null || strRenderHtmlLike.length() == 0) && (str4 == null || str4.length() == 0)) {
                arrayList.add("label=\"" + StringsKt.replace$default(str2, "\"", Argument.Delimiters.none, false, 4, (Object) null) + '\"');
            } else {
                StringBuilder sb = new StringBuilder("<TABLE BORDER=\"0\"><TR><TD>");
                sb.append(renderHtmlLike(str2));
                sb.append("</TD></TR>");
                if (strRenderHtmlLike != null && strRenderHtmlLike.length() != 0) {
                    sb.append("<TR><TD ALIGN=\"LEFT\" BALIGN=\"LEFT\">");
                    sb.append(strRenderHtmlLike);
                    sb.append("</TD></TR>");
                }
                if (str4 != null && str4.length() != 0) {
                    sb.append("<TR><TD ALIGN=\"LEFT\" BALIGN=\"LEFT\">");
                    sb.append(renderHtmlLike(str4));
                    sb.append("</TD></TR>");
                }
                sb.append("</TABLE>");
                arrayList.add("label=< " + sb.toString() + " >");
            }
            if (key.getIsDead()) {
                str3 = "gray";
            } else if (Intrinsics.areEqual(key, key.getOwner().getEnterNode()) || Intrinsics.areEqual(key, key.getOwner().getExitNode())) {
                str3 = "red";
            } else if (key.isUnion()) {
                str3 = "yellow";
            }
            if (str3 != null) {
                arrayList.add("style=\"filled\"");
                arrayList.add("fillcolor=".concat(str3));
            }
            printer.println(new Object[]{Integer.valueOf(iIntValue), CollectionsKt.joinToString$default(arrayList, Argument.Delimiters.space, " [", "];", 0, (CharSequence) null, (Function1) null, 56, (Object) null)});
            if (key instanceof ExitNodeMarker) {
                exitCluster(printer);
            }
        }
    }

    private final String renderTypeHtmlLike(TypeStatement typeStatement) {
        return renderHtmlLike(typeStatement.renderType());
    }

    public final void renderCompleteGraph(String graphName, Function0<Unit> printNodesAndEdges) {
        graphName.getClass();
        printNodesAndEdges.getClass();
        String strReplace$default = StringsKt.replace$default(graphName, ".", InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER, false, 4, (Object) null);
        if (strReplace$default.length() > 0 && DIGIT_REGEX.matches(String.valueOf(StringsKt.first(strReplace$default)))) {
            strReplace$default = InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER + strReplace$default;
        }
        this.printer.println(new Object[]{"digraph " + strReplace$default + " {"}).pushIndent().println(new Object[]{"graph [nodesep=3]"}).println(new Object[]{"node [shape=box penwidth=2]"}).println(new Object[]{"edge [penwidth=2]"}).println(new Object[0]);
        printNodesAndEdges.invoke();
        this.printer.popIndent().println(new Object[]{"}"});
    }

    public final void renderPartialGraph(ControlFlowGraph controlFlowGraph) {
        controlFlowGraph.getClass();
        List list = DFS.topologicalOrder(CollectionsKt.listOf(controlFlowGraph.getEnterNode()), new DFS.Neighbors() { // from class: org.jetbrains.kotlin.fir.resolve.dfa.cfg.b
            public final Iterable getNeighbors(Object obj) {
                return ControlFlowGraphRenderer.a((CFGNode) obj);
            }
        });
        list.getClass();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : list) {
            int i = this.nodeCounter;
            this.nodeCounter = i + 1;
            linkedHashMap.put(obj, Integer.valueOf(i));
        }
        Printer printer = this.printer;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            if (((CFGNode) entry.getKey()).getLevel() >= controlFlowGraph.getEnterNode().getLevel()) {
                linkedHashMap2.put(entry.getKey(), entry.getValue());
            }
        }
        renderNodes(printer, linkedHashMap2);
        renderEdges(this.printer, linkedHashMap);
        this.printer.println(new Object[0]);
    }

    private final String renderHtmlLike(DataFlowVariable dataFlowVariable) {
        if (dataFlowVariable instanceof RealVariable) {
            return renderHtmlLike(((RealVariable) dataFlowVariable).toString());
        }
        if (dataFlowVariable instanceof SyntheticVariable) {
            StringBuilder sb = new StringBuilder("#");
            Map<FirElement, Integer> map = this.firElementIndices;
            FirExpression fir = ((SyntheticVariable) dataFlowVariable).getFir();
            Integer numValueOf = map.get(fir);
            if (numValueOf == null) {
                numValueOf = Integer.valueOf(this.firElementIndices.size());
                map.put(fir, numValueOf);
            }
            sb.append(numValueOf.intValue());
            return sb.toString();
        }
        bu8.a();
        return null;
    }

    private final String renderHtmlLike(Statement statement) {
        if (statement instanceof OperationStatement) {
            StringBuilder sb = new StringBuilder();
            OperationStatement operationStatement = (OperationStatement) statement;
            sb.append(renderHtmlLike(operationStatement.getVariable()));
            sb.append(' ');
            sb.append(renderHtmlLike(operationStatement.getOperation()));
            return sb.toString();
        }
        if (!(statement instanceof TypeStatement)) {
            bu8.a();
            return null;
        }
        StringBuilder sb2 = new StringBuilder();
        TypeStatement typeStatement = (TypeStatement) statement;
        sb2.append(renderHtmlLike(typeStatement.getVariable()));
        sb2.append(": ");
        sb2.append(renderTypeHtmlLike(typeStatement));
        return sb2.toString();
    }

    private final String renderHtmlLike(Object obj) {
        String strReplace$default = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(obj.toString(), "&", "&amp;", false, 4, (Object) null), ">", "&gt;", false, 4, (Object) null), "<", "&lt;", false, 4, (Object) null);
        String strLineSeparator = System.lineSeparator();
        strLineSeparator.getClass();
        return StringsKt.replace$default(strReplace$default, strLineSeparator, "<BR/>", false, 4, (Object) null);
    }
}

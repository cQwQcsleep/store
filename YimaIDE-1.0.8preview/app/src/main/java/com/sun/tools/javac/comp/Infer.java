package com.sun.tools.javac.comp;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Lint;
import com.sun.tools.javac.code.Source;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.comp.Infer;
import com.sun.tools.javac.comp.InferenceContext;
import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.CompilerInternalException;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.GraphUtils;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Options;
import com.sun.tools.javac.util.Pair;
import com.sun.tools.javac.util.Warner;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Infer {
    static final int MAX_INCORPORATION_STEPS = 10000;
    Check chk;
    private final String dependenciesFolder;
    JCDiagnostic.Factory diags;
    private final boolean dumpStacktraceOnError;
    final InferenceContext emptyContext;
    private final boolean erasePolySigReturnType;
    Log log;
    private List<String> pendingGraphs;
    Resolve rs;
    Symtab syms;
    Types types;
    protected static final Context.Key<Infer> inferKey = new Context.Key<>();
    public static final Type anyPoly = new Type.JCNoType();
    Types.TypeMapping<Void> fromTypeVarFun = new Type.StructuralTypeMapping<Void>() { // from class: com.sun.tools.javac.comp.Infer.1
        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitTypeVar(Type.TypeVar typeVar, Void r4) {
            Infer infer = Infer.this;
            Type.UndetVar undetVar = new Type.UndetVar(typeVar, infer.incorporationEngine, infer.types);
            if ((typeVar.tsym.flags() & Flags.THROWS) != 0) {
                undetVar.setThrow();
            }
            return undetVar;
        }
    };
    IncorporationEngine incorporationEngine = new IncorporationEngine();
    Map<IncorporationBinaryOpKey, Boolean> incorporationCache = new LinkedHashMap();

    /* JADX INFO: renamed from: com.sun.tools.javac.comp.Infer$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$Type$UndetVar$InferenceBound;
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$TypeTag;
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag;

        static {
            int[] iArr = new int[Type.UndetVar.InferenceBound.values().length];
            $SwitchMap$com$sun$tools$javac$code$Type$UndetVar$InferenceBound = iArr;
            try {
                iArr[Type.UndetVar.InferenceBound.EQ.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Type$UndetVar$InferenceBound[Type.UndetVar.InferenceBound.LOWER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Type$UndetVar$InferenceBound[Type.UndetVar.InferenceBound.UPPER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[TypeTag.values().length];
            $SwitchMap$com$sun$tools$javac$code$TypeTag = iArr2;
            try {
                iArr2[TypeTag.CLASS.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.TYPEVAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr3 = new int[JCTree.Tag.values().length];
            $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag = iArr3;
            try {
                iArr3[JCTree.Tag.TYPECAST.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.EXEC.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public abstract class BestLeafSolver extends LeafSolver {
        final Pair<List<GraphSolver.InferenceGraph.Node>, Integer> noPath;
        final Map<GraphSolver.InferenceGraph.Node, Pair<List<GraphSolver.InferenceGraph.Node>, Integer>> treeCache;
        List<Type> varsToSolve;

        public BestLeafSolver(List<Type> list) {
            super();
            this.treeCache = new LinkedHashMap();
            this.noPath = new Pair<>(null, Integer.MAX_VALUE);
            this.varsToSolve = list;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Pair<List<GraphSolver.InferenceGraph.Node>, Integer> computeTreeToLeafs(GraphSolver.InferenceGraph.Node node) {
            Pair<List<GraphSolver.InferenceGraph.Node>, Integer> pair = this.treeCache.get(node);
            if (pair == null) {
                if (node.isLeaf()) {
                    pair = new Pair<>(List.of(node), Integer.valueOf(((ListBuffer) node.data).length()));
                } else {
                    pair = new Pair<>(List.of(node), Integer.valueOf(((ListBuffer) node.data).length()));
                    for (GraphSolver.InferenceGraph.Node node2 : node.getAllDependencies()) {
                        if (node2 != node) {
                            Pair<List<GraphSolver.InferenceGraph.Node>, Integer> pairComputeTreeToLeafs = computeTreeToLeafs(node2);
                            pair = new Pair<>(pair.fst.prependList(pairComputeTreeToLeafs.fst), Integer.valueOf(pair.snd.intValue() + pairComputeTreeToLeafs.snd.intValue()));
                        }
                    }
                }
                this.treeCache.put(node, pair);
            }
            return pair;
        }

        @Override // com.sun.tools.javac.comp.Infer.LeafSolver, com.sun.tools.javac.comp.Infer.GraphStrategy
        public GraphSolver.InferenceGraph.Node pickNode(GraphSolver.InferenceGraph inferenceGraph) {
            this.treeCache.clear();
            Pair<List<GraphSolver.InferenceGraph.Node>, Integer> pair = this.noPath;
            for (GraphSolver.InferenceGraph.Node node : inferenceGraph.nodes) {
                if (!Collections.disjoint((Collection) node.data, this.varsToSolve)) {
                    Pair<List<GraphSolver.InferenceGraph.Node>, Integer> pairComputeTreeToLeafs = computeTreeToLeafs(node);
                    if (pairComputeTreeToLeafs.snd.intValue() < pair.snd.intValue()) {
                        pair = pairComputeTreeToLeafs;
                    }
                }
            }
            if (pair != this.noPath) {
                return pair.fst.head;
            }
            throw new GraphStrategy.NodeNotFoundException(inferenceGraph, Infer.this.dumpStacktraceOnError);
        }
    }

    public static class BoundFilter implements Predicate<Type> {
        InferenceContext inferenceContext;

        public BoundFilter(InferenceContext inferenceContext) {
            this.inferenceContext = inferenceContext;
        }

        @Override // java.util.function.Predicate
        public boolean test(Type type) {
            return (type.isErroneous() || this.inferenceContext.free(type) || type.hasTag(TypeTag.BOT)) ? false : true;
        }
    }

    public class CheckUpperBounds extends IncorporationAction {
        public CheckUpperBounds(Type.UndetVar undetVar, Type type) {
            super(undetVar, type);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.comp.Infer.IncorporationAction
        public void apply(InferenceContext inferenceContext, Warner warner) {
            Stream<Type> stream = this.uv.getBounds(Type.UndetVar.InferenceBound.UPPER).stream();
            final Types types = Infer.this.types;
            Objects.requireNonNull(types);
            for (Type type : (List) stream.collect(types.closureCollector(true, new BiPredicate() { // from class: ln6
                @Override // java.util.function.BiPredicate
                public final boolean test(Object obj, Object obj2) {
                    return types.isSameType((Type) obj, (Type) obj2);
                }
            }))) {
                Type type2 = this.t;
                if (type2 != type && type2 != type) {
                    TypeTag typeTag = TypeTag.WILDCARD;
                    if (!type2.hasTag(typeTag) && !type.hasTag(typeTag)) {
                        for (Pair pair : Infer.this.getParameterizedSupers(this.t, type)) {
                            List listAllparams = ((Type) pair.fst).allparams();
                            List listAllparams2 = ((Type) pair.snd).allparams();
                            while (listAllparams.nonEmpty() && listAllparams2.nonEmpty()) {
                                Type type3 = (Type) listAllparams.head;
                                TypeTag typeTag2 = TypeTag.WILDCARD;
                                if (!type3.hasTag(typeTag2) && !((Type) listAllparams2.head).hasTag(typeTag2) && !isSameType(inferenceContext.asUndetVar((Type) listAllparams.head), inferenceContext.asUndetVar((Type) listAllparams2.head), inferenceContext)) {
                                    Infer.this.reportBoundError(this.uv, Type.UndetVar.InferenceBound.UPPER);
                                }
                                listAllparams = listAllparams.tail;
                                listAllparams2 = listAllparams2.tail;
                            }
                            Assert.check(listAllparams.isEmpty() && listAllparams2.isEmpty());
                        }
                    }
                }
            }
        }

        @Override // com.sun.tools.javac.comp.Infer.IncorporationAction
        public IncorporationAction dup(Type.UndetVar undetVar) {
            return Infer.this.new CheckUpperBounds(undetVar, this.t);
        }
    }

    public enum DependencyKind implements GraphUtils.DependencyKind {
        BOUND("dotted"),
        STUCK("dashed");

        final String dotStyle;

        DependencyKind(String str) {
            this.dotStyle = str;
        }
    }

    public interface FreeTypeListener {
        void typesInferred(InferenceContext inferenceContext);
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'EQ' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:399)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:364)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:349)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:315)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:288)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:153)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    public static final class GraphInferenceSteps {
        private static final /* synthetic */ GraphInferenceSteps[] $VALUES;
        public static final GraphInferenceSteps EQ;
        public static final GraphInferenceSteps EQ_LOWER;
        public static final GraphInferenceSteps EQ_LOWER_THROWS_UPPER_CAPTURED;
        final EnumSet<InferenceStep> steps;

        private static /* synthetic */ GraphInferenceSteps[] $values() {
            return new GraphInferenceSteps[]{EQ, EQ_LOWER, EQ_LOWER_THROWS_UPPER_CAPTURED};
        }

        static {
            InferenceStep inferenceStep = InferenceStep.EQ;
            EQ = new GraphInferenceSteps("EQ", 0, EnumSet.of(inferenceStep));
            InferenceStep inferenceStep2 = InferenceStep.LOWER;
            EQ_LOWER = new GraphInferenceSteps("EQ_LOWER", 1, EnumSet.of(inferenceStep, inferenceStep2));
            EQ_LOWER_THROWS_UPPER_CAPTURED = new GraphInferenceSteps("EQ_LOWER_THROWS_UPPER_CAPTURED", 2, EnumSet.of(inferenceStep, inferenceStep2, InferenceStep.UPPER, InferenceStep.THROWS, InferenceStep.CAPTURED));
            $VALUES = $values();
        }

        private GraphInferenceSteps(String str, int i, EnumSet enumSet) {
            super(str, i);
            this.steps = enumSet;
        }

        public static GraphInferenceSteps valueOf(String str) {
            return (GraphInferenceSteps) Enum.valueOf(GraphInferenceSteps.class, str);
        }

        public static GraphInferenceSteps[] values() {
            return (GraphInferenceSteps[]) $VALUES.clone();
        }
    }

    public class GraphSolver {
        InferenceContext inferenceContext;
        Warner warn;

        public class InferenceGraph {
            ArrayList<Node> nodes;

            public class Node extends GraphUtils.TarjanNode<ListBuffer<Type>, Node> implements GraphUtils.DottableNode<ListBuffer<Type>, Node> {
                Set<Node> deps;

                public Node(Type type) {
                    super(ListBuffer.of(type));
                    this.deps = new LinkedHashSet();
                }

                private void closureInternal(Set<Node> set) {
                    if (set.add(this)) {
                        Iterator<Node> it = this.deps.iterator();
                        while (it.hasNext()) {
                            it.next().closureInternal(set);
                        }
                    }
                }

                /* JADX INFO: Access modifiers changed from: private */
                public void graphChanged(Node node, Node node2) {
                    if (!removeDependency(node) || node2 == null) {
                        return;
                    }
                    addDependency(node2);
                }

                public void addDependencies(Set<Node> set) {
                    Iterator<Node> it = set.iterator();
                    while (it.hasNext()) {
                        addDependency(it.next());
                    }
                }

                public void addDependency(Node node) {
                    this.deps.add(node);
                }

                public Set<Node> closure() {
                    LinkedHashSet linkedHashSet = new LinkedHashSet();
                    closureInternal(linkedHashSet);
                    return linkedHashSet;
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.sun.tools.javac.util.GraphUtils.DottableNode
                public Properties dependencyAttributes(Node node, GraphUtils.DependencyKind dependencyKind) {
                    Properties properties = new Properties();
                    properties.put(Constants.ATTRNAME_STYLE, ((DependencyKind) dependencyKind).dotStyle);
                    StringBuilder sb = new StringBuilder();
                    Iterator it = ((ListBuffer) this.data).iterator();
                    String str = "";
                    while (it.hasNext()) {
                        for (Type type : ((Type.UndetVar) GraphSolver.this.inferenceContext.asUndetVar((Type) it.next())).getBounds(Type.UndetVar.InferenceBound.values())) {
                            if (type.containsAny(List.from((Iterable) node.data))) {
                                sb.append(str);
                                sb.append(type);
                                str = ",";
                            }
                        }
                    }
                    properties.put("label", "\"" + sb.toString() + "\"");
                    return properties;
                }

                @Override // com.sun.tools.javac.util.GraphUtils.TarjanNode
                public Iterable<? extends Node> getAllDependencies() {
                    return this.deps;
                }

                @Override // com.sun.tools.javac.util.GraphUtils.AbstractNode
                public Collection<? extends Node> getDependenciesByKind(GraphUtils.DependencyKind dependencyKind) {
                    if (dependencyKind == DependencyKind.BOUND) {
                        return this.deps;
                    }
                    g33.a();
                    return null;
                }

                @Override // com.sun.tools.javac.util.GraphUtils.AbstractNode
                public GraphUtils.DependencyKind[] getSupportedDependencyKinds() {
                    return new GraphUtils.DependencyKind[]{DependencyKind.BOUND};
                }

                public boolean isLeaf() {
                    if (this.deps.isEmpty()) {
                        return true;
                    }
                    Iterator<Node> it = this.deps.iterator();
                    while (it.hasNext()) {
                        if (it.next() != this) {
                            return false;
                        }
                    }
                    return true;
                }

                /* JADX WARN: Multi-variable type inference failed */
                public void mergeWith(List<? extends Node> list) {
                    for (Node node : list) {
                        boolean z = true;
                        if (((ListBuffer) node.data).length() != 1) {
                            z = false;
                        }
                        Assert.check(z, "Attempt to merge a compound node!");
                        ((ListBuffer) this.data).appendList((ListBuffer) node.data);
                        addDependencies(node.deps);
                    }
                    LinkedHashSet linkedHashSet = new LinkedHashSet();
                    for (Node node2 : this.deps) {
                        if (((ListBuffer) this.data).contains(((ListBuffer) node2.data).first())) {
                            linkedHashSet.add(this);
                        } else {
                            linkedHashSet.add(node2);
                        }
                    }
                    this.deps = linkedHashSet;
                }

                @Override // com.sun.tools.javac.util.GraphUtils.DottableNode
                public Properties nodeAttributes() {
                    Properties properties = new Properties();
                    properties.put("label", "\"" + toString() + "\"");
                    return properties;
                }

                public boolean removeDependency(Node node) {
                    return this.deps.remove(node);
                }
            }

            public InferenceGraph() {
                initNodes();
            }

            public void deleteNode(Node node) {
                Assert.check(this.nodes.contains(node));
                this.nodes.remove(node);
                notifyUpdate(node, null);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public Node findNode(Type type) {
                for (Node node : this.nodes) {
                    if (((ListBuffer) node.data).contains(type)) {
                        return node;
                    }
                }
                return null;
            }

            /* JADX WARN: Multi-variable type inference failed */
            public void initNodes() {
                this.nodes = new ArrayList<>();
                Iterator<Type> it = GraphSolver.this.inferenceContext.restvars().iterator();
                while (it.hasNext()) {
                    this.nodes.add(new Node(it.next()));
                }
                for (Node node : this.nodes) {
                    Type type = (Type) ((ListBuffer) node.data).first();
                    for (Node node2 : this.nodes) {
                        Type type2 = (Type) ((ListBuffer) node2.data).first();
                        if (type != type2 && Type.containsAny(((Type.UndetVar) GraphSolver.this.inferenceContext.asUndetVar(type)).getBounds(Type.UndetVar.InferenceBound.values()), List.of(type2))) {
                            node.addDependency(node2);
                        }
                    }
                }
                ArrayList<Node> arrayList = new ArrayList<>();
                for (List list : GraphUtils.tarjan(this.nodes)) {
                    if (list.length() > 1) {
                        Node node3 = (Node) list.head;
                        node3.mergeWith(list.tail);
                        Iterator it2 = list.iterator();
                        while (it2.hasNext()) {
                            notifyUpdate((Node) it2.next(), node3);
                        }
                    }
                    arrayList.add((Node) list.head);
                }
                this.nodes = arrayList;
            }

            public void notifyUpdate(Node node, Node node2) {
                Iterator<Node> it = this.nodes.iterator();
                while (it.hasNext()) {
                    it.next().graphChanged(node, node2);
                }
            }

            public String toDot() {
                StringBuilder sb = new StringBuilder();
                Iterator<Type> it = GraphSolver.this.inferenceContext.undetvars.iterator();
                while (it.hasNext()) {
                    Type.UndetVar undetVar = (Type.UndetVar) it.next();
                    sb.append(String.format("var %s - upper bounds = %s, lower bounds = %s, eq bounds = %s\\n", undetVar.qtype, undetVar.getBounds(Type.UndetVar.InferenceBound.UPPER), undetVar.getBounds(Type.UndetVar.InferenceBound.LOWER), undetVar.getBounds(Type.UndetVar.InferenceBound.EQ)));
                }
                return GraphUtils.toDot(this.nodes, "inferenceGraph" + hashCode(), sb.toString());
            }
        }

        public GraphSolver(InferenceContext inferenceContext, Warner warner) {
            this.inferenceContext = inferenceContext;
            this.warn = warner;
        }

        public void solve(GraphStrategy graphStrategy) {
            Infer.this.doIncorporation(this.inferenceContext, this.warn);
            InferenceGraph inferenceGraph = new InferenceGraph();
            while (!graphStrategy.done()) {
                if (Infer.this.dependenciesFolder != null) {
                    Infer infer = Infer.this;
                    infer.pendingGraphs = infer.pendingGraphs.prepend(inferenceGraph.toDot());
                }
                InferenceGraph.Node nodePickNode = graphStrategy.pickNode(inferenceGraph);
                List<Type> listFrom = List.from((Iterable) nodePickNode.data);
                List<Type> listSave = this.inferenceContext.save();
                while (Type.containsAny(this.inferenceContext.restvars(), listFrom)) {
                    try {
                        GraphInferenceSteps[] graphInferenceStepsArrValues = GraphInferenceSteps.values();
                        int length = graphInferenceStepsArrValues.length;
                        int i = 0;
                        while (true) {
                            if (i >= length) {
                                throw Infer.this.error(null);
                            }
                            if (this.inferenceContext.solveBasic(listFrom, graphInferenceStepsArrValues[i].steps).nonEmpty()) {
                                break;
                            } else {
                                i++;
                            }
                        }
                        Infer.this.doIncorporation(this.inferenceContext, this.warn);
                    } catch (InferenceException unused) {
                        this.inferenceContext.rollback(listSave);
                        Infer.this.instantiateAsUninferredVars(listFrom, this.inferenceContext);
                        Infer.this.doIncorporation(this.inferenceContext, this.warn);
                    }
                }
                inferenceGraph.deleteNode(nodePickNode);
            }
        }
    }

    public interface GraphStrategy {

        public static class NodeNotFoundException extends CompilerInternalException {
            private static final long serialVersionUID = 0;
            transient GraphSolver.InferenceGraph graph;

            public NodeNotFoundException(GraphSolver.InferenceGraph inferenceGraph, boolean z) {
                super(z);
                this.graph = inferenceGraph;
            }
        }

        boolean done();

        GraphSolver.InferenceGraph.Node pickNode(GraphSolver.InferenceGraph inferenceGraph) throws NodeNotFoundException;
    }

    public class ImplicitArgType extends DeferredAttr.DeferredTypeMap<Void> {
        /* JADX WARN: Illegal instructions before constructor call */
        public ImplicitArgType(Symbol symbol, Resolve.MethodResolutionPhase methodResolutionPhase) {
            DeferredAttr deferredAttr = Infer.this.rs.deferredAttr;
            Objects.requireNonNull(deferredAttr);
            super(DeferredAttr.AttrMode.SPECULATIVE, symbol, methodResolutionPhase);
        }

        @Override // com.sun.tools.javac.code.Type.StructuralTypeMapping, com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitClassType(Type.ClassType classType, Void r2) {
            return Infer.this.types.erasure(classType);
        }

        @Override // com.sun.tools.javac.comp.DeferredAttr.DeferredTypeMap, com.sun.tools.javac.code.Types.MapVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitType(Type type, Void r2) {
            if (type.hasTag(TypeTag.DEFERRED)) {
                return visit(super.visitType(type, (Object) null));
            }
            if (!type.hasTag(TypeTag.BOT)) {
                return type;
            }
            Infer infer = Infer.this;
            return infer.types.boxedClass(infer.syms.voidType).type;
        }
    }

    public abstract class IncorporationAction {
        Type t;
        Type.UndetVar uv;

        public IncorporationAction(Type.UndetVar undetVar, Type type) {
            this.uv = undetVar;
            this.t = type;
        }

        public abstract void apply(InferenceContext inferenceContext, Warner warner);

        public abstract IncorporationAction dup(Type.UndetVar undetVar);

        public boolean isSameType(Type type, Type type2, InferenceContext inferenceContext) {
            return Infer.this.doIncorporationOp(IncorporationBinaryOpKind.IS_SAME_TYPE, type, type2, null, inferenceContext);
        }

        public boolean isSubtype(Type type, Type type2, Warner warner, InferenceContext inferenceContext) {
            return Infer.this.doIncorporationOp(IncorporationBinaryOpKind.IS_SUBTYPE, type, type2, warner, inferenceContext);
        }

        public String toString() {
            return String.format("%s[undet=%s,t=%s]", getClass().getSimpleName(), this.uv.qtype, this.t);
        }
    }

    public static final class IncorporationBinaryOpKey {
        private final Type op1;
        private final Type op2;
        private final IncorporationBinaryOpKind opKind;
        private final Types types;

        public IncorporationBinaryOpKey(IncorporationBinaryOpKind incorporationBinaryOpKind, Type type, Type type2, Types types) {
            this.opKind = incorporationBinaryOpKind;
            this.op1 = type;
            this.op2 = type2;
            this.types = types;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof IncorporationBinaryOpKey)) {
                return false;
            }
            IncorporationBinaryOpKey incorporationBinaryOpKey = (IncorporationBinaryOpKey) obj;
            return this.opKind == incorporationBinaryOpKey.opKind && this.types.isSameType(this.op1, incorporationBinaryOpKey.op1) && this.types.isSameType(this.op2, incorporationBinaryOpKey.op2);
        }

        public int hashCode() {
            return (((this.opKind.hashCode() * 127) + this.types.hashCode(this.op1)) * 127) + this.types.hashCode(this.op2);
        }

        public Type op1() {
            return this.op1;
        }

        public Type op2() {
            return this.op2;
        }

        public IncorporationBinaryOpKind opKind() {
            return this.opKind;
        }

        public final String toString() {
            return "IncorporationBinaryOpKey[opKind=" + Objects.toString(this.opKind) + ", op1=" + Objects.toString(this.op1) + ", op2=" + Objects.toString(this.op2) + ", types=" + Objects.toString(this.types) + "]";
        }

        public Types types() {
            return this.types;
        }
    }

    public enum IncorporationBinaryOpKind {
        IS_SUBTYPE { // from class: com.sun.tools.javac.comp.Infer.IncorporationBinaryOpKind.1
            @Override // com.sun.tools.javac.comp.Infer.IncorporationBinaryOpKind
            public boolean apply(Type type, Type type2, Warner warner, Types types) {
                return types.isSubtypeUnchecked(type, type2, warner);
            }
        },
        IS_SAME_TYPE { // from class: com.sun.tools.javac.comp.Infer.IncorporationBinaryOpKind.2
            @Override // com.sun.tools.javac.comp.Infer.IncorporationBinaryOpKind
            public boolean apply(Type type, Type type2, Warner warner, Types types) {
                return types.isSameType(type, type2);
            }
        };

        public abstract boolean apply(Type type, Type type2, Warner warner, Types types);
    }

    public class IncorporationEngine implements Type.UndetVar.UndetVarListener {
        public IncorporationEngine() {
        }

        public List<IncorporationAction> getIncorporationActions(Type.UndetVar undetVar, Type.UndetVar.InferenceBound inferenceBound, Type type, boolean z) {
            ListBuffer listBuffer = new ListBuffer();
            if (undetVar.getInst() != null) {
                listBuffer.add(new CheckInst(Infer.this, undetVar, inferenceBound, new Type.UndetVar.InferenceBound[0]));
            }
            listBuffer.add(new CheckBounds(Infer.this, undetVar, type, inferenceBound));
            if (z) {
                return listBuffer.toList();
            }
            if (inferenceBound == Type.UndetVar.InferenceBound.UPPER) {
                listBuffer.add(Infer.this.new CheckUpperBounds(undetVar, type));
            }
            listBuffer.add(Infer.this.new PropagateBounds(undetVar, type, inferenceBound));
            return listBuffer.toList();
        }

        @Override // com.sun.tools.javac.code.Type.UndetVar.UndetVarListener
        public void varBoundChanged(Type.UndetVar undetVar, Type.UndetVar.InferenceBound inferenceBound, Type type, boolean z) {
            if (undetVar.isCaptured()) {
                return;
            }
            undetVar.incorporationActions.addAll(getIncorporationActions(undetVar, inferenceBound, type, z));
        }

        @Override // com.sun.tools.javac.code.Type.UndetVar.UndetVarListener
        public void varInstantiated(Type.UndetVar undetVar) {
            undetVar.incorporationActions.addFirst(Infer.this.new SubstBounds(undetVar));
        }
    }

    public static class InferenceException extends Resolve.InapplicableMethodException {
        private static final long serialVersionUID = 0;
        transient List<JCDiagnostic> messages;

        public InferenceException(boolean z) {
            super(null, z);
            this.messages = List.nil();
        }

        @Override // com.sun.tools.javac.comp.Resolve.InapplicableMethodException
        public JCDiagnostic getDiagnostic() {
            return this.messages.head;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static abstract class InferenceStep {
        private static final /* synthetic */ InferenceStep[] $VALUES;
        public static final InferenceStep CAPTURED;
        public static final InferenceStep EQ = new InferenceStep("EQ", 0, Type.UndetVar.InferenceBound.EQ) { // from class: com.sun.tools.javac.comp.Infer.InferenceStep.1
            @Override // com.sun.tools.javac.comp.Infer.InferenceStep
            public Type solve(Type.UndetVar undetVar, InferenceContext inferenceContext) {
                return filterBounds(undetVar, inferenceContext).head;
            }
        };
        public static final InferenceStep LOWER = new InferenceStep("LOWER", 1, Type.UndetVar.InferenceBound.LOWER) { // from class: com.sun.tools.javac.comp.Infer.InferenceStep.2
            @Override // com.sun.tools.javac.comp.Infer.InferenceStep
            public Type solve(Type.UndetVar undetVar, InferenceContext inferenceContext) {
                Infer infer = inferenceContext.infer;
                List<Type> listFilterBounds = filterBounds(undetVar, inferenceContext);
                Type typeLub = listFilterBounds.tail.tail == null ? listFilterBounds.head : infer.types.lub(listFilterBounds);
                if (typeLub.isPrimitive() || typeLub.hasTag(TypeTag.ERROR)) {
                    throw infer.error(infer.diags.fragment(CompilerProperties.Fragments.NoUniqueMinimalInstanceExists(undetVar.qtype, listFilterBounds)));
                }
                return typeLub;
            }
        };
        public static final InferenceStep THROWS;
        public static final InferenceStep UPPER;
        final Type.UndetVar.InferenceBound ib;

        /* JADX INFO: renamed from: com.sun.tools.javac.comp.Infer$InferenceStep$3, reason: invalid class name */
        public final enum AnonymousClass3 extends InferenceStep {
            public AnonymousClass3(String str, int i, Type.UndetVar.InferenceBound inferenceBound) {
                super(str, i, inferenceBound);
            }

            public static /* synthetic */ boolean b(InferenceContext inferenceContext, Type type) {
                return !inferenceContext.free(type);
            }

            @Override // com.sun.tools.javac.comp.Infer.InferenceStep
            public boolean accepts(Type.UndetVar undetVar, final InferenceContext inferenceContext) {
                if (!undetVar.isThrows()) {
                    return false;
                }
                final Types types = inferenceContext.types;
                final Symtab symtab = inferenceContext.infer.syms;
                return undetVar.getBounds(Type.UndetVar.InferenceBound.UPPER).stream().filter(new Predicate() { // from class: com.sun.tools.javac.comp.b1
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return Infer.InferenceStep.AnonymousClass3.b(inferenceContext, (Type) obj);
                    }
                }).allMatch(new Predicate() { // from class: com.sun.tools.javac.comp.c1
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return types.isSubtype(symtab.runtimeExceptionType, (Type) obj);
                    }
                });
            }

            @Override // com.sun.tools.javac.comp.Infer.InferenceStep
            public Type solve(Type.UndetVar undetVar, InferenceContext inferenceContext) {
                return inferenceContext.infer.syms.runtimeExceptionType;
            }
        }

        private static /* synthetic */ InferenceStep[] $values() {
            return new InferenceStep[]{EQ, LOWER, THROWS, UPPER, CAPTURED};
        }

        static {
            Type.UndetVar.InferenceBound inferenceBound = Type.UndetVar.InferenceBound.UPPER;
            THROWS = new AnonymousClass3("THROWS", 2, inferenceBound);
            UPPER = new InferenceStep("UPPER", 3, inferenceBound) { // from class: com.sun.tools.javac.comp.Infer.InferenceStep.4
                @Override // com.sun.tools.javac.comp.Infer.InferenceStep
                public Type solve(Type.UndetVar undetVar, InferenceContext inferenceContext) {
                    Infer infer = inferenceContext.infer;
                    List<Type> listFilterBounds = filterBounds(undetVar, inferenceContext);
                    Type typeGlb = listFilterBounds.tail.tail == null ? listFilterBounds.head : infer.types.glb(listFilterBounds);
                    if (typeGlb.isPrimitive() || typeGlb.hasTag(TypeTag.ERROR)) {
                        throw infer.error(infer.diags.fragment(CompilerProperties.Fragments.NoUniqueMaximalInstanceExists(undetVar.qtype, listFilterBounds)));
                    }
                    return typeGlb;
                }
            };
            CAPTURED = new InferenceStep("CAPTURED", 4, inferenceBound) { // from class: com.sun.tools.javac.comp.Infer.InferenceStep.5
                @Override // com.sun.tools.javac.comp.Infer.InferenceStep
                public boolean accepts(Type.UndetVar undetVar, InferenceContext inferenceContext) {
                    return undetVar.isCaptured() && !inferenceContext.free(undetVar.getBounds(Type.UndetVar.InferenceBound.UPPER, Type.UndetVar.InferenceBound.LOWER));
                }

                @Override // com.sun.tools.javac.comp.Infer.InferenceStep
                public Type solve(Type.UndetVar undetVar, InferenceContext inferenceContext) {
                    Infer infer = inferenceContext.infer;
                    InferenceStep inferenceStep = InferenceStep.UPPER;
                    Type typeSolve = inferenceStep.filterBounds(undetVar, inferenceContext).nonEmpty() ? inferenceStep.solve(undetVar, inferenceContext) : infer.syms.objectType;
                    InferenceStep inferenceStep2 = InferenceStep.LOWER;
                    Type typeSolve2 = inferenceStep2.filterBounds(undetVar, inferenceContext).nonEmpty() ? inferenceStep2.solve(undetVar, inferenceContext) : infer.syms.botType;
                    Type.CapturedType capturedType = (Type.CapturedType) undetVar.qtype;
                    Symbol.TypeSymbol typeSymbol = capturedType.tsym;
                    return new Type.CapturedType(typeSymbol.name, typeSymbol.owner, typeSolve, typeSolve2, capturedType.wildcard);
                }
            };
            $VALUES = $values();
        }

        private InferenceStep(String str, int i, Type.UndetVar.InferenceBound inferenceBound) {
            super(str, i);
            this.ib = inferenceBound;
        }

        public static InferenceStep valueOf(String str) {
            return (InferenceStep) Enum.valueOf(InferenceStep.class, str);
        }

        public static InferenceStep[] values() {
            return (InferenceStep[]) $VALUES.clone();
        }

        public boolean accepts(Type.UndetVar undetVar, InferenceContext inferenceContext) {
            return filterBounds(undetVar, inferenceContext).nonEmpty() && !undetVar.isCaptured();
        }

        public List<Type> filterBounds(Type.UndetVar undetVar, InferenceContext inferenceContext) {
            return Type.filter(undetVar.getBounds(this.ib), new BoundFilter(inferenceContext));
        }

        public abstract Type solve(Type.UndetVar undetVar, InferenceContext inferenceContext);
    }

    public abstract class LeafSolver implements GraphStrategy {
        public LeafSolver() {
        }

        @Override // com.sun.tools.javac.comp.Infer.GraphStrategy
        public GraphSolver.InferenceGraph.Node pickNode(GraphSolver.InferenceGraph inferenceGraph) {
            if (inferenceGraph.nodes.isEmpty()) {
                throw new GraphStrategy.NodeNotFoundException(inferenceGraph, Infer.this.dumpStacktraceOnError);
            }
            return inferenceGraph.nodes.get(0);
        }
    }

    public class PartiallyInferredMethodType extends Type.MethodType {
        Env<AttrContext> env;
        final InferenceContext inferenceContext;
        final Warner warn;

        public PartiallyInferredMethodType(Type.MethodType methodType, InferenceContext inferenceContext, Env<AttrContext> env, Warner warner) {
            super(methodType.mo71getParameterTypes(), methodType.mo73getReturnType(), methodType.mo74getThrownTypes(), methodType.tsym);
            this.inferenceContext = inferenceContext;
            this.env = env;
            this.warn = warner;
        }

        /* JADX WARN: Code duplicated, block: B:36:0x00b2  */
        /* JADX WARN: Not initialized variable reg: 2, insn: 0x0039: MOVE (r1 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:11:0x0039 */
        public Type check(Attr.ResultInfo resultInfo) throws Throwable {
            List<Type> listSave;
            List<Type> list;
            List<Type> list2 = null;
            Warner warner = new Warner(null);
            try {
                try {
                    listSave = this.inferenceContext.save();
                    try {
                        boolean zHasNonSilentLint = this.warn.hasNonSilentLint(Lint.LintCategory.UNCHECKED);
                        if (!zHasNonSilentLint) {
                            boolean zShouldPropagate = Infer.this.shouldPropagate(mo73getReturnType(), resultInfo, this.inferenceContext);
                            InferenceContext inferenceContextMin = this.inferenceContext;
                            if (zShouldPropagate) {
                                inferenceContextMin = inferenceContextMin.min(Infer.this.roots(asMethodType(), null), false, this.warn);
                            }
                            Type typeGenerateReturnConstraints = Infer.this.generateReturnConstraints(this.env.tree, resultInfo, (Type.MethodType) inferenceContextMin.update(asMethodType()), inferenceContextMin);
                            if (zShouldPropagate) {
                                inferenceContextMin.dupTo(resultInfo.checkContext.inferenceContext(), resultInfo.checkContext.deferredAttrContext().insideOverloadPhase());
                                if (listSave != null) {
                                    this.inferenceContext.rollback(listSave);
                                }
                                return typeGenerateReturnConstraints;
                            }
                        }
                        this.inferenceContext.solve(warner);
                        Type typeMo73getReturnType = this.inferenceContext.asInstType(this).mo73getReturnType();
                        if (zHasNonSilentLint) {
                            typeMo73getReturnType = Infer.this.types.erasure(typeMo73getReturnType);
                        }
                        Type typeCheck = resultInfo.check(this.env.tree, typeMo73getReturnType);
                        if (listSave != null) {
                            this.inferenceContext.rollback(listSave);
                        }
                        return typeCheck;
                    } catch (InferenceException e) {
                        e = e;
                        resultInfo.checkContext.report(null, e.getDiagnostic());
                        Assert.error();
                        if (listSave != null) {
                            this.inferenceContext.rollback(listSave);
                        }
                        return null;
                    }
                } catch (Throwable th) {
                    th = th;
                    list2 = list;
                    if (list2 != null) {
                        this.inferenceContext.rollback(list2);
                    }
                    throw th;
                }
            } catch (InferenceException e2) {
                e = e2;
                listSave = null;
            } catch (Throwable th2) {
                th = th2;
                if (list2 != null) {
                    this.inferenceContext.rollback(list2);
                }
                throw th;
            }
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isPartial() {
            return true;
        }
    }

    public class PropagateBounds extends IncorporationAction {
        Type.UndetVar.InferenceBound ib;

        public PropagateBounds(Type.UndetVar undetVar, Type type, Type.UndetVar.InferenceBound inferenceBound) {
            super(undetVar, type);
            this.ib = inferenceBound;
        }

        @Override // com.sun.tools.javac.comp.Infer.IncorporationAction
        public void apply(InferenceContext inferenceContext, Warner warner) {
            Type typeAsUndetVar = inferenceContext.asUndetVar(this.t);
            if (typeAsUndetVar.hasTag(TypeTag.UNDETVAR)) {
                Type.UndetVar undetVar = (Type.UndetVar) typeAsUndetVar;
                if (!undetVar.isCaptured()) {
                    undetVar.addBound(this.ib.complement(), this.uv, Infer.this.types);
                    for (Type.UndetVar.InferenceBound inferenceBound : backwards()) {
                        Iterator<Type> it = undetVar.getBounds(inferenceBound).iterator();
                        while (it.hasNext()) {
                            this.uv.addBound(inferenceBound, it.next(), Infer.this.types);
                        }
                    }
                }
            }
            Iterator<Type.UndetVar.InferenceBound> it2 = forward().iterator();
            while (it2.hasNext()) {
                Iterator<Type> it3 = this.uv.getBounds(it2.next()).iterator();
                while (it3.hasNext()) {
                    Type typeAsUndetVar2 = inferenceContext.asUndetVar(it3.next());
                    if (typeAsUndetVar2.hasTag(TypeTag.UNDETVAR)) {
                        Type.UndetVar undetVar2 = (Type.UndetVar) typeAsUndetVar2;
                        if (!undetVar2.isCaptured()) {
                            undetVar2.addBound(this.ib, inferenceContext.asInstType(this.t), Infer.this.types);
                        }
                    }
                }
            }
        }

        public EnumSet<Type.UndetVar.InferenceBound> backwards() {
            Type.UndetVar.InferenceBound inferenceBound = this.ib;
            return inferenceBound == Type.UndetVar.InferenceBound.EQ ? EnumSet.allOf(Type.UndetVar.InferenceBound.class) : EnumSet.of(inferenceBound);
        }

        @Override // com.sun.tools.javac.comp.Infer.IncorporationAction
        public IncorporationAction dup(Type.UndetVar undetVar) {
            return Infer.this.new PropagateBounds(undetVar, this.t, this.ib);
        }

        public EnumSet<Type.UndetVar.InferenceBound> forward() {
            Type.UndetVar.InferenceBound inferenceBound = this.ib;
            Type.UndetVar.InferenceBound inferenceBound2 = Type.UndetVar.InferenceBound.EQ;
            return inferenceBound == inferenceBound2 ? EnumSet.of(inferenceBound2) : EnumSet.complementOf(EnumSet.of(inferenceBound));
        }

        @Override // com.sun.tools.javac.comp.Infer.IncorporationAction
        public String toString() {
            return String.format("%s[undet=%s,t=%s,bound=%s]", getClass().getSimpleName(), this.uv.qtype, this.t, this.ib);
        }
    }

    public class SubstBounds extends CheckInst {
        public SubstBounds(Type.UndetVar undetVar) {
            super(Infer.this, undetVar, Type.UndetVar.InferenceBound.LOWER, Type.UndetVar.InferenceBound.EQ, Type.UndetVar.InferenceBound.UPPER);
        }

        @Override // com.sun.tools.javac.comp.Infer.CheckBounds, com.sun.tools.javac.comp.Infer.IncorporationAction
        public void apply(InferenceContext inferenceContext, Warner warner) {
            Iterator<Type> it = inferenceContext.undetvars.iterator();
            while (it.hasNext()) {
                Type.UndetVar undetVar = (Type.UndetVar) it.next();
                undetVar.substBounds(List.of(this.uv.qtype), List.of(this.uv.getInst()), Infer.this.types);
                checkCompatibleUpperBounds(undetVar, inferenceContext);
            }
            super.apply(inferenceContext, warner);
        }

        public void checkCompatibleUpperBounds(Type.UndetVar undetVar, InferenceContext inferenceContext) {
            Type typeGlb;
            Type.UndetVar.InferenceBound inferenceBound = Type.UndetVar.InferenceBound.UPPER;
            List<Type> listFilter = Type.filter(undetVar.getBounds(inferenceBound), new BoundFilter(inferenceContext));
            if (listFilter.isEmpty()) {
                typeGlb = Infer.this.syms.objectType;
            } else {
                typeGlb = listFilter.tail.isEmpty() ? listFilter.head : Infer.this.types.glb(listFilter);
            }
            if (typeGlb == null || typeGlb.isErroneous()) {
                Infer.this.reportBoundError(undetVar, inferenceBound);
            }
        }

        @Override // com.sun.tools.javac.comp.Infer.CheckInst, com.sun.tools.javac.comp.Infer.CheckBounds, com.sun.tools.javac.comp.Infer.IncorporationAction
        public IncorporationAction dup(Type.UndetVar undetVar) {
            return Infer.this.new SubstBounds(undetVar);
        }
    }

    public Infer(Context context) {
        context.put(inferKey, this);
        this.rs = Resolve.instance(context);
        this.chk = Check.instance(context);
        this.syms = Symtab.instance(context);
        this.types = Types.instance(context);
        this.diags = JCDiagnostic.Factory.instance(context);
        this.log = Log.instance(context);
        Options optionsInstance = Options.instance(context);
        this.dependenciesFolder = optionsInstance.get("debug.dumpInferenceGraphsTo");
        this.pendingGraphs = List.nil();
        this.emptyContext = new InferenceContext(this, List.nil());
        this.dumpStacktraceOnError = optionsInstance.isSet("dev") || optionsInstance.isSet(Option.DOE);
        this.erasePolySigReturnType = Source.Feature.ERASE_POLY_SIG_RETURN_TYPE.allowedInSource(Source.instance(context));
    }

    private Type asSuper(Type type, Type type2) {
        return type2.hasTag(TypeTag.ARRAY) ? new Type.ArrayType(asSuper(this.types.elemtype(type), this.types.elemtype(type2)), this.syms.arrayClass) : this.types.asSuper(type, type2.tsym);
    }

    public static /* synthetic */ boolean c(Type type) {
        return (type.tsym.flags() & Flags.THROWS) != 0;
    }

    private boolean commonSuperWithDiffParameterization(Type type, Type type2) {
        for (Pair<Type, Type> pair : getParameterizedSupers(type, type2)) {
            if (!this.types.isSameType(pair.fst, pair.snd)) {
                return true;
            }
        }
        return false;
    }

    public static /* synthetic */ boolean d(Type type, Type type2) {
        return !type2.containsAny(type.getTypeArguments());
    }

    private void dumpGraphsIfNeeded(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Resolve.MethodResolutionContext methodResolutionContext) {
        try {
            try {
                int i = 0;
                for (String str : this.pendingGraphs.reverse()) {
                    Assert.checkNonNull(this.dependenciesFolder);
                    Name name = symbol.name;
                    if (name == name.table.names.init) {
                        name = symbol.owner.name;
                    }
                    BufferedWriter bufferedWriterNewBufferedWriter = Files.newBufferedWriter(Paths.get(this.dependenciesFolder, String.format("%s@%s[mode=%s,step=%s]_%d.dot", name, Integer.valueOf(diagnosticPosition.getStartPosition()), methodResolutionContext.attrMode(), methodResolutionContext.step, Integer.valueOf(i))), new OpenOption[0]);
                    try {
                        bufferedWriterNewBufferedWriter.append((CharSequence) str);
                        bufferedWriterNewBufferedWriter.close();
                        i++;
                    } catch (Throwable th) {
                        if (bufferedWriterNewBufferedWriter != null) {
                            try {
                                bufferedWriterNewBufferedWriter.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        }
                        throw th;
                    }
                }
            } catch (IOException e) {
                Assert.error("Error occurred when dumping inference graph: " + e.getMessage());
            }
            this.pendingGraphs = List.nil();
        } catch (Throwable th3) {
            this.pendingGraphs = List.nil();
            throw th3;
        }
    }

    private Type generateReferenceToTargetConstraint(JCTree jCTree, Type.UndetVar undetVar, Type type, Attr.ResultInfo resultInfo, InferenceContext inferenceContext) {
        inferenceContext.solve(List.of(undetVar.qtype), new Warner());
        inferenceContext.notifyChange();
        return this.types.isConvertible(resultInfo.checkContext.inferenceContext().cachedCapture(jCTree, undetVar.getInst(), resultInfo.checkMode.updateTreeType() ^ true), resultInfo.checkContext.inferenceContext().asUndetVar(type)) ? this.syms.objectType : type;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Pair<Type, Type>> getParameterizedSupers(Type type, Type type2) {
        Type typeLub = this.types.lub(type, type2);
        Symtab symtab = this.syms;
        if (typeLub == symtab.errType || typeLub == symtab.botType) {
            return List.nil();
        }
        List<Type> components = typeLub.isIntersection() ? ((Type.IntersectionClassType) typeLub).getComponents() : List.of(typeLub);
        ListBuffer listBuffer = new ListBuffer();
        for (Type type3 : components) {
            if (type3.isParameterized()) {
                listBuffer.add(new Pair(asSuper(type, type3), asSuper(type2, type3)));
            }
        }
        return listBuffer.toList();
    }

    public static Infer instance(Context context) {
        Infer infer = (Infer) context.get(inferKey);
        return infer == null ? new Infer(context) : infer;
    }

    private List<Type> instantiatePatternVars(List<Type> list, InferenceContext inferenceContext) {
        Type typeGlb;
        Type typeLub;
        ListBuffer listBuffer = new ListBuffer();
        ListBuffer listBuffer2 = new ListBuffer();
        Iterator<Type> it = list.iterator();
        while (it.hasNext()) {
            Type.UndetVar undetVar = (Type.UndetVar) inferenceContext.asUndetVar(it.next());
            List<Type> listFilterBounds = InferenceStep.EQ.filterBounds(undetVar, inferenceContext);
            if (listFilterBounds.nonEmpty()) {
                undetVar.setInst(listFilterBounds.head);
            } else {
                List<Type> bounds = undetVar.getBounds(Type.UndetVar.InferenceBound.UPPER);
                if (Type.containsAny(bounds, list)) {
                    typeGlb = this.types.makeIntersectionType(bounds);
                    listBuffer2.append(undetVar);
                } else {
                    typeGlb = bounds.nonEmpty() ? this.types.glb(bounds) : this.syms.objectType;
                }
                List<Type> bounds2 = undetVar.getBounds(Type.UndetVar.InferenceBound.LOWER);
                if (bounds2.isEmpty()) {
                    typeLub = this.syms.botType;
                } else {
                    typeLub = bounds2.tail.isEmpty() ? bounds2.head : this.types.lub(bounds2);
                }
                Type.TypeVar typeVar = new Type.TypeVar(this.syms.noSymbol, typeGlb, typeLub);
                listBuffer.add(typeVar);
                undetVar.setInst(typeVar);
            }
        }
        replaceTypeVarsInBounds(listBuffer2.toList(), inferenceContext);
        return listBuffer.toList();
    }

    private boolean needsEagerInstantiation(Type.UndetVar undetVar, Type type, InferenceContext inferenceContext) {
        if (type.isPrimitive()) {
            Iterator<Type> it = undetVar.getBounds(Type.UndetVar.InferenceBound.values()).iterator();
            while (it.hasNext()) {
                Type typeUnboxedType = this.types.unboxedType(it.next());
                if (typeUnboxedType != null && !typeUnboxedType.hasTag(TypeTag.NONE)) {
                    return true;
                }
            }
            return false;
        }
        if (this.types.capture(type) == type) {
            for (Type type2 : undetVar.getBounds(Type.UndetVar.InferenceBound.EQ, Type.UndetVar.InferenceBound.LOWER)) {
                if (this.types.capture(type2) != type2) {
                    return true;
                }
            }
            for (Type type3 : undetVar.getBounds(Type.UndetVar.InferenceBound.LOWER)) {
                for (Type type4 : undetVar.getBounds(Type.UndetVar.InferenceBound.LOWER)) {
                    if (type3 != type4 && !inferenceContext.free(type3) && !inferenceContext.free(type4) && commonSuperWithDiffParameterization(type3, type4)) {
                        return true;
                    }
                }
            }
        }
        if (type.isParameterized()) {
            Iterator<Type> it2 = undetVar.getBounds(Type.UndetVar.InferenceBound.EQ, Type.UndetVar.InferenceBound.LOWER).iterator();
            while (it2.hasNext()) {
                Type typeAsSuper = this.types.asSuper(it2.next(), type.tsym);
                if (typeAsSuper != null && typeAsSuper.isRaw()) {
                    return true;
                }
            }
        }
        return false;
    }

    private void replaceTypeVarsInBounds(List<Type> list, InferenceContext inferenceContext) {
        Iterator<Type> it = list.iterator();
        while (it.hasNext()) {
            Type.UndetVar undetVar = (Type.UndetVar) it.next();
            Type.TypeVar typeVar = (Type.TypeVar) undetVar.getInst();
            Types types = this.types;
            typeVar.setUpperBound(types.glb(inferenceContext.asInstTypes(types.getBounds(typeVar))));
            if (typeVar.getUpperBound().isErroneous()) {
                reportBoundError(undetVar, Type.UndetVar.InferenceBound.UPPER);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Type> roots(Type.MethodType methodType, DeferredAttr.DeferredAttrContext deferredAttrContext) {
        if (deferredAttrContext == null || deferredAttrContext.mode != DeferredAttr.AttrMode.CHECK) {
            return List.of(methodType.mo73getReturnType());
        }
        ListBuffer listBuffer = new ListBuffer();
        listBuffer.add(methodType.mo73getReturnType());
        for (DeferredAttr.DeferredAttrNode deferredAttrNode : deferredAttrContext.deferredAttrNodes) {
            listBuffer.addAll(deferredAttrNode.deferredStuckPolicy.stuckVars());
            listBuffer.addAll(deferredAttrNode.deferredStuckPolicy.depVars());
        }
        List list = (List) deferredAttrContext.inferenceContext.inferencevars.stream().filter(new Predicate() { // from class: gn6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Infer.c((Type) obj);
            }
        }).collect(List.collector());
        List list2 = listBuffer.toList();
        return list2.appendList(list.diff(list2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldPropagate(Type type, Attr.ResultInfo resultInfo, InferenceContext inferenceContext) {
        if (resultInfo.checkContext.inferenceContext() == this.emptyContext || !inferenceContext.free(type)) {
            return false;
        }
        return (inferenceContext.inferencevars.contains(type) && needsEagerInstantiation((Type.UndetVar) inferenceContext.asUndetVar(type), resultInfo.pt, inferenceContext)) ? false : true;
    }

    public void doIncorporation(InferenceContext inferenceContext, Warner warner) throws InferenceException {
        boolean z;
        int i = 0;
        for (boolean z2 = true; z2 && i < 10000; z2 = z) {
            try {
                Iterator<Type> it = inferenceContext.undetvars.iterator();
                z = false;
                while (it.hasNext()) {
                    Type.UndetVar undetVar = (Type.UndetVar) it.next();
                    if (!undetVar.incorporationActions.isEmpty()) {
                        undetVar.incorporationActions.removeFirst().apply(inferenceContext, warner);
                        z = true;
                    }
                }
                i++;
            } catch (Throwable th) {
                this.incorporationCache.clear();
                throw th;
            }
        }
        this.incorporationCache.clear();
    }

    public boolean doIncorporationOp(IncorporationBinaryOpKind incorporationBinaryOpKind, Type type, Type type2, Warner warner, InferenceContext inferenceContext) {
        IncorporationBinaryOpKey incorporationBinaryOpKey = new IncorporationBinaryOpKey(incorporationBinaryOpKind, inferenceContext.asTypeVar(type), inferenceContext.asTypeVar(type2), this.types);
        Boolean bool = this.incorporationCache.get(incorporationBinaryOpKey);
        if (bool == null) {
            Map<IncorporationBinaryOpKey, Boolean> map = this.incorporationCache;
            Boolean boolValueOf = Boolean.valueOf(incorporationBinaryOpKind.apply(type, type2, warner, this.types));
            map.put(incorporationBinaryOpKey, boolValueOf);
            bool = boolValueOf;
        }
        return bool.booleanValue();
    }

    public InferenceException error(JCDiagnostic jCDiagnostic) {
        InferenceException inferenceException = new InferenceException(this.dumpStacktraceOnError);
        if (jCDiagnostic != null) {
            inferenceException.messages = inferenceException.messages.append(jCDiagnostic);
        }
        return inferenceException;
    }

    /* JADX WARN: Code duplicated, block: B:34:0x00bb A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:35:0x00bc  */
    public Type generateReturnConstraints(JCTree jCTree, Attr.ResultInfo resultInfo, Type.MethodType methodType, InferenceContext inferenceContext) {
        Infer infer;
        Attr.ResultInfo resultInfo2;
        InferenceContext inferenceContext2;
        InferenceContext inferenceContext3 = resultInfo.checkContext.inferenceContext();
        Type typeMo73getReturnType = methodType.mo73getReturnType();
        if (methodType.mo73getReturnType().containsAny(inferenceContext.inferencevars) && inferenceContext3 != this.emptyContext) {
            typeMo73getReturnType = this.types.capture(typeMo73getReturnType);
            for (Type type : typeMo73getReturnType.getTypeArguments()) {
                if (type.hasTag(TypeTag.TYPEVAR)) {
                    Type.TypeVar typeVar = (Type.TypeVar) type;
                    if (typeVar.isCaptured()) {
                        inferenceContext.addVar(typeVar);
                    }
                }
            }
        }
        Type typeAsUndetVar = inferenceContext.asUndetVar(typeMo73getReturnType);
        Type typeGenerateReferenceToTargetConstraint = resultInfo.pt;
        if (typeAsUndetVar.hasTag(TypeTag.VOID)) {
            typeGenerateReferenceToTargetConstraint = this.syms.voidType;
        } else {
            if (!typeGenerateReferenceToTargetConstraint.hasTag(TypeTag.NONE)) {
                if (typeAsUndetVar.hasTag(TypeTag.UNDETVAR)) {
                    Type.UndetVar undetVar = (Type.UndetVar) typeAsUndetVar;
                    if (needsEagerInstantiation(undetVar, typeGenerateReferenceToTargetConstraint, inferenceContext)) {
                        infer = this;
                        resultInfo2 = resultInfo;
                        inferenceContext2 = inferenceContext;
                        typeGenerateReferenceToTargetConstraint = infer.generateReferenceToTargetConstraint(jCTree, undetVar, typeGenerateReferenceToTargetConstraint, resultInfo2, inferenceContext2);
                    }
                } else {
                    infer = this;
                    resultInfo2 = resultInfo;
                    inferenceContext2 = inferenceContext;
                    if (inferenceContext3.free(resultInfo2.pt)) {
                        typeAsUndetVar = inferenceContext2.asUndetVar(inferenceContext3.cachedCapture(jCTree, typeMo73getReturnType, !resultInfo2.checkMode.updateTreeType()));
                    }
                }
                if (resultInfo2.checkContext.compatible(typeAsUndetVar, inferenceContext3.asUndetVar(typeGenerateReferenceToTargetConstraint), new Warner())) {
                    return typeMo73getReturnType;
                }
                throw infer.error(infer.diags.fragment(CompilerProperties.Fragments.InferNoConformingInstanceExists(inferenceContext2.restvars(), methodType.mo73getReturnType(), typeGenerateReferenceToTargetConstraint)));
            }
            typeGenerateReferenceToTargetConstraint = typeMo73getReturnType.isPrimitive() ? typeMo73getReturnType : this.syms.objectType;
        }
        infer = this;
        resultInfo2 = resultInfo;
        inferenceContext2 = inferenceContext;
        if (resultInfo2.checkContext.compatible(typeAsUndetVar, inferenceContext3.asUndetVar(typeGenerateReferenceToTargetConstraint), new Warner())) {
            return typeMo73getReturnType;
        }
        throw infer.error(infer.diags.fragment(CompilerProperties.Fragments.InferNoConformingInstanceExists(inferenceContext2.restvars(), methodType.mo73getReturnType(), typeGenerateReferenceToTargetConstraint)));
    }

    public JCDiagnostic.Fragment getBoundFragment(Type.UndetVar.InferenceBound inferenceBound, List<Type> list) {
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$code$Type$UndetVar$InferenceBound[inferenceBound.ordinal()];
        if (i == 1) {
            return CompilerProperties.Fragments.EqBounds(list);
        }
        if (i == 2) {
            return CompilerProperties.Fragments.LowerBounds(list);
        }
        if (i == 3) {
            return CompilerProperties.Fragments.UpperBounds(list);
        }
        x01.a("can't get to this place");
        return null;
    }

    public void instantiateAsUninferredVars(List<Type> list, InferenceContext inferenceContext) {
        ListBuffer listBuffer = new ListBuffer();
        Iterator<Type> it = list.iterator();
        while (it.hasNext()) {
            Type.UndetVar undetVar = (Type.UndetVar) inferenceContext.asUndetVar(it.next());
            Type.UndetVar.InferenceBound inferenceBound = Type.UndetVar.InferenceBound.UPPER;
            List<Type> bounds = undetVar.getBounds(inferenceBound);
            if (Type.containsAny(bounds, list)) {
                Symbol.TypeSymbol typeSymbol = undetVar.qtype.tsym;
                Symbol.TypeVariableSymbol typeVariableSymbol = new Symbol.TypeVariableSymbol(4096L, typeSymbol.name, null, typeSymbol.owner);
                typeVariableSymbol.type = new Type.TypeVar(typeVariableSymbol, this.types.makeIntersectionType(undetVar.getBounds(inferenceBound)), this.syms.botType);
                listBuffer.append(undetVar);
                undetVar.setInst(typeVariableSymbol.type);
            } else if (bounds.nonEmpty()) {
                undetVar.setInst(this.types.glb(bounds));
            } else {
                undetVar.setInst(this.syms.objectType);
            }
        }
        replaceTypeVarsInBounds(listBuffer.toList(), inferenceContext);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Type instantiateFunctionalInterface(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type, List<Type> list, Check.CheckContext checkContext) {
        if (this.types.capture(type) == type) {
            return type;
        }
        final Type type2 = type.tsym.type;
        InferenceContext inferenceContext = new InferenceContext(this, type2.getTypeArguments());
        Assert.check(list != null);
        List<Type> listMo71getParameterTypes = this.types.findDescriptorType(type2).mo71getParameterTypes();
        if (listMo71getParameterTypes.size() != list.size()) {
            checkContext.report(diagnosticPosition, this.diags.fragment(CompilerProperties.Fragments.IncompatibleArgTypesInLambda));
            return this.types.createErrorType(type);
        }
        Iterator<Type> it = listMo71getParameterTypes.iterator();
        List list2 = list;
        while (it.hasNext()) {
            if (!this.types.isSameType(inferenceContext.asUndetVar(it.next()), (Type) list2.head)) {
                checkContext.report(diagnosticPosition, this.diags.fragment(CompilerProperties.Fragments.NoSuitableFunctionalIntfInst(type)));
                return this.types.createErrorType(type);
            }
            list2 = list2.tail;
        }
        List typeArguments = type.getTypeArguments();
        Iterator<Type> it2 = inferenceContext.undetvars.iterator();
        while (it2.hasNext()) {
            Type.UndetVar undetVar = (Type.UndetVar) it2.next();
            undetVar.setInst(undetVar.getBounds(Type.UndetVar.InferenceBound.EQ).stream().filter(new Predicate() { // from class: hn6
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return Infer.d(type2, (Type) obj);
                }
            }).findFirst().orElse((Type) typeArguments.head));
            typeArguments = typeArguments.tail;
        }
        Type typeAsInstType = inferenceContext.asInstType(type2);
        if (!this.chk.checkValidGenericType(typeAsInstType)) {
            checkContext.report(diagnosticPosition, this.diags.fragment(CompilerProperties.Fragments.NoSuitableFunctionalIntfInst(type)));
        }
        checkContext.compatible(typeAsInstType, type, this.types.noWarnings);
        return typeAsInstType;
    }

    /* JADX WARN: Code duplicated, block: B:33:0x008d  */
    /* JADX WARN: Code duplicated, block: B:40:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:41:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:43:0x00c6  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v2, types: [com.sun.tools.javac.util.List] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public Type instantiateMethod(Env<AttrContext> env, List<Type> list, Type.MethodType methodType, Attr.ResultInfo resultInfo, Symbol.MethodSymbol methodSymbol, List<Type> list2, boolean z, boolean z2, Resolve.MethodResolutionContext methodResolutionContext, Warner warner) throws Throwable {
        InferenceContext inferenceContext;
        Type.MethodType methodType2;
        InferenceContext inferenceContext2 = new InferenceContext(this, list);
        try {
            DeferredAttr.DeferredAttrContext deferredAttrContext = methodResolutionContext.deferredAttrContext(methodSymbol, inferenceContext2, resultInfo, warner);
            inferenceContext = list2;
            methodResolutionContext.methodCheck.argumentsAcceptable(env, deferredAttrContext, inferenceContext, methodType.mo71getParameterTypes(), warner);
            try {
                if (resultInfo != null && resultInfo.pt == anyPoly) {
                    doIncorporation(inferenceContext2, warner);
                    PartiallyInferredMethodType partiallyInferredMethodType = new PartiallyInferredMethodType(methodType, inferenceContext2, env, warner);
                    inferenceContext2.notifyChange();
                    dumpGraphsIfNeeded(env.tree, methodSymbol, methodResolutionContext);
                    return partiallyInferredMethodType;
                }
                if (resultInfo != null) {
                    doIncorporation(inferenceContext2, warner);
                    if (warner.hasNonSilentLint(Lint.LintCategory.UNCHECKED)) {
                        methodType2 = methodType;
                    } else {
                        boolean zShouldPropagate = shouldPropagate(methodType.mo73getReturnType(), resultInfo, inferenceContext2);
                        InferenceContext inferenceContextMin = zShouldPropagate ? inferenceContext2.min(roots(methodType, deferredAttrContext), true, warner) : inferenceContext2;
                        methodType2 = (Type.MethodType) this.types.createMethodTypeWithReturn(methodType, generateReturnConstraints(env.tree, resultInfo, methodType, inferenceContextMin));
                        if (zShouldPropagate) {
                            InferenceContext inferenceContext3 = resultInfo.checkContext.inferenceContext();
                            inferenceContextMin.dupTo(inferenceContext3);
                            if (inferenceContextMin != inferenceContext2) {
                                inferenceContext3.parentIC = inferenceContext2;
                            }
                            deferredAttrContext.complete();
                            inferenceContext2.notifyChange();
                        }
                    }
                    deferredAttrContext.complete();
                    inferenceContext2.solve(warner);
                    methodType2 = (Type.MethodType) inferenceContext2.asInstType(methodType2);
                    if (resultInfo != null && this.rs.verboseResolutionMode.contains(Resolve.VerboseResolutionMode.DEFERRED_INST)) {
                        this.log.note(env.tree.pos, CompilerProperties.Notes.DeferredMethodInst(methodSymbol, methodType2, resultInfo.pt));
                    }
                    if (resultInfo != null) {
                        inferenceContext2.notifyChange();
                    } else {
                        inferenceContext2.notifyChange(inferenceContext2.boundedVars());
                    }
                    if (resultInfo == null) {
                        inferenceContext2.captureTypeCache.clear();
                    }
                } else {
                    methodType2 = methodType;
                    deferredAttrContext.complete();
                    inferenceContext2.solve(warner);
                    methodType2 = (Type.MethodType) inferenceContext2.asInstType(methodType2);
                    if (resultInfo != null) {
                        this.log.note(env.tree.pos, CompilerProperties.Notes.DeferredMethodInst(methodSymbol, methodType2, resultInfo.pt));
                    }
                    if (resultInfo != null) {
                        inferenceContext2.notifyChange();
                    } else {
                        inferenceContext2.notifyChange(inferenceContext2.boundedVars());
                    }
                    if (resultInfo == null) {
                        inferenceContext2.captureTypeCache.clear();
                    }
                }
                dumpGraphsIfNeeded(env.tree, methodSymbol, methodResolutionContext);
                return methodType2;
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
            inferenceContext = inferenceContext2;
        }
        Throwable th3 = th;
        if (resultInfo != null) {
            inferenceContext.notifyChange();
        } else {
            inferenceContext.notifyChange(inferenceContext.boundedVars());
        }
        if (resultInfo == null) {
            inferenceContext.captureTypeCache.clear();
        }
        dumpGraphsIfNeeded(env.tree, methodSymbol, methodResolutionContext);
        throw th3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Type instantiatePatternType(Type type, Symbol.TypeSymbol typeSymbol) {
        Type typeAsSuper;
        if (type.tsym == typeSymbol) {
            return type;
        }
        List listNil = List.nil();
        List<Type> listAllparams = typeSymbol.type.allparams();
        List listNil2 = List.nil();
        List listOf = List.of(type);
        while (listOf.nonEmpty()) {
            Type type2 = (Type) listOf.head;
            listOf = listOf.tail;
            int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$code$TypeTag[type2.getTag().ordinal()];
            if (i == 1) {
                boolean zIsCompound = type2.isCompound();
                Types types = this.types;
                if (zIsCompound) {
                    listOf = listOf.prependList(types.directSupertypes(type2));
                } else {
                    Type typeCapture = types.capture(type2);
                    for (Type type3 : typeCapture.getTypeArguments()) {
                        if (type3.hasTag(TypeTag.TYPEVAR)) {
                            Type.TypeVar typeVar = (Type.TypeVar) type3;
                            if (typeVar.isCaptured()) {
                                listAllparams = listAllparams.prepend(typeVar);
                                listNil2 = listNil2.prepend(type3);
                            }
                        }
                    }
                    listNil = listNil.prepend(typeCapture);
                }
            } else if (i != 2) {
                listNil = listNil.prepend(type2);
            } else {
                listOf = listOf.prepend(this.types.skipTypeVars(type2, false));
            }
        }
        final InferenceContext inferenceContext = new InferenceContext(this, listAllparams);
        Type typeAsUndetVar = inferenceContext.asUndetVar(typeSymbol.type);
        List<Type> map = listNil.map(new Function() { // from class: in6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return inferenceContext.asUndetVar((Type) obj);
            }
        });
        listNil2.forEach(new Consumer() { // from class: jn6
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Type.UndetVar) inferenceContext.asUndetVar((Type) obj)).setNormal();
            }
        });
        try {
            for (Type type4 : map) {
                if (type4.isParameterized() && ((typeAsSuper = this.types.asSuper(typeAsUndetVar, type4.tsym)) == null || !this.types.isSameType(typeAsSuper, type4))) {
                    return null;
                }
            }
            doIncorporation(inferenceContext, this.types.noWarnings);
            return this.types.upward(inferenceContext.asInstType(typeSymbol.type), instantiatePatternVars(listAllparams, inferenceContext));
        } catch (InferenceException unused) {
            return null;
        }
    }

    public Type instantiatePolymorphicSignatureInstance(Env<AttrContext> env, Symbol.MethodSymbol methodSymbol, Resolve.MethodResolutionContext methodResolutionContext, List<Type> list) {
        Type returnType = methodSymbol == null ? this.syms.objectType : methodSymbol.getReturnType();
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[env.next.tree.getTag().ordinal()];
        if (i == 1) {
            JCTree.JCTypeCast jCTypeCast = (JCTree.JCTypeCast) env.next.tree;
            if (TreeInfo.skipParens(jCTypeCast.expr) == env.tree) {
                returnType = this.erasePolySigReturnType ? this.types.erasure(jCTypeCast.clazz.type) : jCTypeCast.clazz.type;
            }
        } else if (i == 2 && TreeInfo.skipParens(((JCTree.JCExpressionStatement) env.next.tree).expr) == env.tree) {
            returnType = this.syms.voidType;
        }
        return new Type.MethodType(list.map(new ImplicitArgType(methodSymbol, methodResolutionContext.step)), returnType, methodSymbol != null ? methodSymbol.getThrownTypes() : List.of(this.syms.throwableType), this.syms.methodClass);
    }

    public void reportBoundError(Type.UndetVar undetVar, Type.UndetVar.InferenceBound inferenceBound) {
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$code$Type$UndetVar$InferenceBound[inferenceBound.ordinal()];
        if (i == 1) {
            throw error(this.diags.fragment(CompilerProperties.Fragments.IncompatibleEqBounds(undetVar.qtype, undetVar.getBounds(inferenceBound))));
        }
        if (i == 2) {
            x01.a("this case shouldn't happen");
        } else if (i == 3) {
            throw error(this.diags.fragment(CompilerProperties.Fragments.IncompatibleUpperBounds(undetVar.qtype, undetVar.getBounds(inferenceBound))));
        }
    }

    public void reportInstError(Type.UndetVar undetVar, Type.UndetVar.InferenceBound inferenceBound) {
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$code$Type$UndetVar$InferenceBound[inferenceBound.ordinal()];
        if (i == 1) {
            throw error(this.diags.fragment(CompilerProperties.Fragments.InferredDoNotConformToEqBounds(undetVar.getInst(), undetVar.getBounds(inferenceBound))));
        }
        if (i == 2) {
            throw error(this.diags.fragment(CompilerProperties.Fragments.InferredDoNotConformToLowerBounds(undetVar.getInst(), undetVar.getBounds(inferenceBound))));
        }
        if (i == 3) {
            throw error(this.diags.fragment(CompilerProperties.Fragments.InferredDoNotConformToUpperBounds(undetVar.getInst(), undetVar.getBounds(inferenceBound))));
        }
    }

    public class CheckInst extends CheckBounds {
        EnumSet<Type.UndetVar.InferenceBound> to;

        public CheckInst(Type.UndetVar undetVar, EnumSet<Type.UndetVar.InferenceBound> enumSet) {
            super(Infer.this, undetVar, undetVar.getInst(), Type.UndetVar.InferenceBound.EQ);
            this.to = enumSet;
        }

        @Override // com.sun.tools.javac.comp.Infer.CheckBounds
        public EnumSet<Type.UndetVar.InferenceBound> boundsToCheck() {
            return this.to;
        }

        @Override // com.sun.tools.javac.comp.Infer.CheckBounds, com.sun.tools.javac.comp.Infer.IncorporationAction
        public IncorporationAction dup(Type.UndetVar undetVar) {
            return Infer.this.new CheckInst(undetVar, this.to);
        }

        @Override // com.sun.tools.javac.comp.Infer.CheckBounds
        public void report(Type.UndetVar.InferenceBound inferenceBound, Type.UndetVar.InferenceBound inferenceBound2) {
            Infer.this.reportInstError(this.uv, inferenceBound2);
        }

        public CheckInst(Infer infer, Type.UndetVar undetVar, Type.UndetVar.InferenceBound inferenceBound, Type.UndetVar.InferenceBound... inferenceBoundArr) {
            this(undetVar, EnumSet.of(inferenceBound, inferenceBoundArr));
        }
    }

    public class CheckBounds extends IncorporationAction {
        Type.UndetVar.InferenceBound from;
        BiPredicate<InferenceContext, Type> optFilter;
        BiFunction<InferenceContext, Type, Type> typeFunc;

        public CheckBounds(Infer infer, Type.UndetVar undetVar, Type type, Type.UndetVar.InferenceBound inferenceBound) {
            this(undetVar, type, new BiFunction() { // from class: kn6
                @Override // java.util.function.BiFunction
                public final Object apply(Object obj, Object obj2) {
                    return ((InferenceContext) obj).asUndetVar((Type) obj2);
                }
            }, null, inferenceBound);
        }

        @Override // com.sun.tools.javac.comp.Infer.IncorporationAction
        public void apply(InferenceContext inferenceContext, Warner warner) {
            Type typeApply = this.typeFunc.apply(inferenceContext, this.t);
            this.t = typeApply;
            BiPredicate<InferenceContext, Type> biPredicate = this.optFilter;
            if (biPredicate == null || !biPredicate.test(inferenceContext, typeApply)) {
                for (Type.UndetVar.InferenceBound inferenceBound : boundsToCheck()) {
                    Iterator<Type> it = this.uv.getBounds(inferenceBound).iterator();
                    while (it.hasNext()) {
                        Type typeApply2 = this.typeFunc.apply(inferenceContext, it.next());
                        BiPredicate<InferenceContext, Type> biPredicate2 = this.optFilter;
                        if (biPredicate2 == null || !biPredicate2.test(inferenceContext, typeApply2)) {
                            CheckBounds checkBounds = this;
                            InferenceContext inferenceContext2 = inferenceContext;
                            Warner warner2 = warner;
                            if (!checkBounds.checkBound(this.t, typeApply2, this.from, inferenceBound, warner2, inferenceContext2)) {
                                checkBounds.report(checkBounds.from, inferenceBound);
                            }
                            this = checkBounds;
                            warner = warner2;
                            inferenceContext = inferenceContext2;
                        }
                    }
                }
            }
        }

        public EnumSet<Type.UndetVar.InferenceBound> boundsToCheck() {
            Type.UndetVar.InferenceBound inferenceBound = this.from;
            return inferenceBound == Type.UndetVar.InferenceBound.EQ ? EnumSet.allOf(Type.UndetVar.InferenceBound.class) : EnumSet.complementOf(EnumSet.of(inferenceBound));
        }

        public boolean checkBound(Type type, Type type2, Type.UndetVar.InferenceBound inferenceBound, Type.UndetVar.InferenceBound inferenceBound2, Warner warner, InferenceContext inferenceContext) {
            if (inferenceBound.lessThan(inferenceBound2)) {
                return isSubtype(type, type2, warner, inferenceContext);
            }
            return inferenceBound2.lessThan(inferenceBound) ? isSubtype(type2, type, warner, inferenceContext) : isSameType(type, type2, inferenceContext);
        }

        @Override // com.sun.tools.javac.comp.Infer.IncorporationAction
        public IncorporationAction dup(Type.UndetVar undetVar) {
            return Infer.this.new CheckBounds(undetVar, this.t, this.typeFunc, this.optFilter, this.from);
        }

        public void report(Type.UndetVar.InferenceBound inferenceBound, Type.UndetVar.InferenceBound inferenceBound2) {
            if (inferenceBound == inferenceBound2) {
                Infer.this.reportBoundError(this.uv, inferenceBound);
            } else if (inferenceBound == Type.UndetVar.InferenceBound.LOWER || inferenceBound2 == Type.UndetVar.InferenceBound.EQ) {
                Infer.this.reportBoundError(this.uv, inferenceBound2, inferenceBound);
            } else {
                Infer.this.reportBoundError(this.uv, inferenceBound, inferenceBound2);
            }
        }

        @Override // com.sun.tools.javac.comp.Infer.IncorporationAction
        public String toString() {
            return String.format("%s[undet=%s,t=%s,bound=%s]", getClass().getSimpleName(), this.uv.qtype, this.t, this.from);
        }

        public CheckBounds(Type.UndetVar undetVar, Type type, BiFunction<InferenceContext, Type, Type> biFunction, BiPredicate<InferenceContext, Type> biPredicate, Type.UndetVar.InferenceBound inferenceBound) {
            super(undetVar, type);
            this.from = inferenceBound;
            this.typeFunc = biFunction;
            this.optFilter = biPredicate;
        }
    }

    public void reportBoundError(Type.UndetVar undetVar, Type.UndetVar.InferenceBound inferenceBound, Type.UndetVar.InferenceBound inferenceBound2) {
        throw error(this.diags.fragment(CompilerProperties.Fragments.IncompatibleBounds(undetVar.qtype, getBoundFragment(inferenceBound, undetVar.getBounds(inferenceBound)), getBoundFragment(inferenceBound2, undetVar.getBounds(inferenceBound2)))));
    }
}

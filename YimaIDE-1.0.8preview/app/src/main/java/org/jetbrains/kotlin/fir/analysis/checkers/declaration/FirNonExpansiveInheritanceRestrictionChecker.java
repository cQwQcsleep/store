package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.TypeUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.impl.FirNestedClassifierScopeKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.ProjectionKind;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.utils.DFS;
import org.jetbrains.kotlin.utils.SmartSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003:\u0003\u0013\u0014\u0015B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0002¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirNonExpansiveInheritanceRestrictionChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirRegularClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)V", "buildTypeGraph", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirNonExpansiveInheritanceRestrictionChecker$Graph;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirNonExpansiveInheritanceRestrictionChecker$TypeParameterNode;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "TypeParameterNode", "ExpansiveEdge", "Graph", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNonExpansiveInheritanceRestrictionChecker extends FirDeclarationChecker<FirRegularClass> {
    public static final FirNonExpansiveInheritanceRestrictionChecker INSTANCE = new FirNonExpansiveInheritanceRestrictionChecker();

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\u000b\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\bJ\u000e\u0010\f\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\bJ(\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u00002\b\b\u0002\u0010\u0004\u001a\u00028\u0000HÆ\u0001¢\u0006\u0002\u0010\u000eJ\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0013\u0010\u0003\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\n\u0010\b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirNonExpansiveInheritanceRestrictionChecker$ExpansiveEdge;", "T", Argument.Delimiters.none, "from", "to", "<init>", "(Ljava/lang/Object;Ljava/lang/Object;)V", "getFrom", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getTo", "component1", "component2", "copy", "(Ljava/lang/Object;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirNonExpansiveInheritanceRestrictionChecker$ExpansiveEdge;", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class ExpansiveEdge<T> {
        private final T from;
        private final T to;

        public ExpansiveEdge(T t, T t2) {
            this.from = t;
            this.to = t2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ExpansiveEdge copy$default(ExpansiveEdge expansiveEdge, Object obj, Object obj2, int i, Object obj3) {
            if ((i & 1) != 0) {
                obj = expansiveEdge.from;
            }
            if ((i & 2) != 0) {
                obj2 = expansiveEdge.to;
            }
            return expansiveEdge.copy(obj, obj2);
        }

        public final T component1() {
            return this.from;
        }

        public final T component2() {
            return this.to;
        }

        public final ExpansiveEdge<T> copy(T from, T to) {
            return new ExpansiveEdge<>(from, to);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ExpansiveEdge)) {
                return false;
            }
            ExpansiveEdge expansiveEdge = (ExpansiveEdge) other;
            return Intrinsics.areEqual(this.from, expansiveEdge.from) && Intrinsics.areEqual(this.to, expansiveEdge.to);
        }

        public final T getFrom() {
            return this.from;
        }

        public final T getTo() {
            return this.to;
        }

        public int hashCode() {
            T t = this.from;
            int iHashCode = (t == null ? 0 : t.hashCode()) * 31;
            T t2 = this.to;
            return iHashCode + (t2 != null ? t2.hashCode() : 0);
        }

        public String toString() {
            return "ExpansiveEdge(from=" + this.from + ", to=" + this.to + ')';
        }
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J%\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u00002\b\b\u0002\u0010\u0012\u001a\u00020\u0013¢\u0006\u0002\u0010\u0014J\u0014\u0010\u0015\u001a\u00020\u00132\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007J\u001b\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u00182\u0006\u0010\u0010\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u0019R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR6\u0010\n\u001a*\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\f0\u000bj\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\f`\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirNonExpansiveInheritanceRestrictionChecker$Graph;", "T", Argument.Delimiters.none, "<init>", "()V", "expansiveEdges", "Lorg/jetbrains/kotlin/utils/SmartSet;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirNonExpansiveInheritanceRestrictionChecker$ExpansiveEdge;", "getExpansiveEdges", "()Lorg/jetbrains/kotlin/utils/SmartSet;", "edgeLists", "Ljava/util/HashMap;", Argument.Delimiters.none, "Lkotlin/collections/HashMap;", "addEdge", Argument.Delimiters.none, "from", "to", "expansive", Argument.Delimiters.none, "(Ljava/lang/Object;Ljava/lang/Object;Z)V", "isEdgeInCycle", "edge", "collectReachable", Argument.Delimiters.none, "(Ljava/lang/Object;)Ljava/util/List;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Graph<T> {
        private final SmartSet<ExpansiveEdge<T>> expansiveEdges = SmartSet.Companion.create();
        private final HashMap<T, Set<T>> edgeLists = new HashMap<>();

        public static Iterable a(Graph graph, Object obj) {
            Set<T> set = graph.edgeLists.get(obj);
            return set != null ? set : SetsKt.emptySet();
        }

        public static /* synthetic */ void addEdge$default(Graph graph, Object obj, Object obj2, boolean z, int i, Object obj3) {
            if ((i & 4) != 0) {
                z = false;
            }
            graph.addEdge(obj, obj2, z);
        }

        private final List<T> collectReachable(T from) {
            collectReachable.handler.1 r0 = new collectReachable.handler.1();
            DFS.dfs(CollectionsKt.listOf(from), new DFS.Neighbors() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.declaration.a
                public final Iterable getNeighbors(Object obj) {
                    return FirNonExpansiveInheritanceRestrictionChecker.Graph.a(this.a, obj);
                }
            }, r0);
            Iterable iterableResult = r0.result();
            iterableResult.getClass();
            return (List) iterableResult;
        }

        public final void addEdge(T from, T to, boolean expansive) {
            HashMap<T, Set<T>> map = this.edgeLists;
            SmartSet smartSetCreate = map.get(from);
            if (smartSetCreate == null) {
                smartSetCreate = SmartSet.Companion.create();
                map.put(from, smartSetCreate);
            }
            ((Set) smartSetCreate).add(to);
            if (expansive) {
                this.expansiveEdges.add(new ExpansiveEdge(from, to));
            }
        }

        public final SmartSet<ExpansiveEdge<T>> getExpansiveEdges() {
            return this.expansiveEdges;
        }

        public final boolean isEdgeInCycle(ExpansiveEdge<? extends T> edge) {
            edge.getClass();
            return collectReachable(edge.getTo()).contains(edge.getFrom());
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirNonExpansiveInheritanceRestrictionChecker$TypeParameterNode;", Argument.Delimiters.none, "container", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "typeParameter", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;)V", "getContainer", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "getTypeParameter", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class TypeParameterNode {
        private final FirRegularClassSymbol container;
        private final FirTypeParameterSymbol typeParameter;

        public TypeParameterNode(FirRegularClassSymbol firRegularClassSymbol, FirTypeParameterSymbol firTypeParameterSymbol) {
            firRegularClassSymbol.getClass();
            firTypeParameterSymbol.getClass();
            this.container = firRegularClassSymbol;
            this.typeParameter = firTypeParameterSymbol;
        }

        public static /* synthetic */ TypeParameterNode copy$default(TypeParameterNode typeParameterNode, FirRegularClassSymbol firRegularClassSymbol, FirTypeParameterSymbol firTypeParameterSymbol, int i, Object obj) {
            if ((i & 1) != 0) {
                firRegularClassSymbol = typeParameterNode.container;
            }
            if ((i & 2) != 0) {
                firTypeParameterSymbol = typeParameterNode.typeParameter;
            }
            return typeParameterNode.copy(firRegularClassSymbol, firTypeParameterSymbol);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final FirRegularClassSymbol getContainer() {
            return this.container;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final FirTypeParameterSymbol getTypeParameter() {
            return this.typeParameter;
        }

        public final TypeParameterNode copy(FirRegularClassSymbol container, FirTypeParameterSymbol typeParameter) {
            container.getClass();
            typeParameter.getClass();
            return new TypeParameterNode(container, typeParameter);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TypeParameterNode)) {
                return false;
            }
            TypeParameterNode typeParameterNode = (TypeParameterNode) other;
            return Intrinsics.areEqual(this.container, typeParameterNode.container) && Intrinsics.areEqual(this.typeParameter, typeParameterNode.typeParameter);
        }

        public final FirRegularClassSymbol getContainer() {
            return this.container;
        }

        public final FirTypeParameterSymbol getTypeParameter() {
            return this.typeParameter;
        }

        public int hashCode() {
            return (this.container.hashCode() * 31) + this.typeParameter.hashCode();
        }

        public String toString() {
            return "TypeParameterNode(container=" + this.container + ", typeParameter=" + this.typeParameter + ')';
        }
    }

    private FirNonExpansiveInheritanceRestrictionChecker() {
        super(MppCheckerKind.Common);
    }

    private final Graph<TypeParameterNode> buildTypeGraph(FirRegularClass declaration, FirSession session) {
        Graph<TypeParameterNode> graph = new Graph<>();
        SmartSet smartSetCreate = SmartSet.Companion.create();
        smartSetCreate.add(declaration.getSymbol());
        buildTypeGraph$visit(session, smartSetCreate, graph, declaration.getSymbol());
        return graph;
    }

    private static final void buildTypeGraph$addEdges(Graph<TypeParameterNode> graph, List<FirTypeParameterSymbol> list, Set<? extends ConeKotlinType> set, FirRegularClassSymbol firRegularClassSymbol, FirRegularClassSymbol firRegularClassSymbol2, FirTypeParameterSymbol firTypeParameterSymbol, boolean z) {
        for (FirTypeParameterSymbol firTypeParameterSymbol2 : list) {
            if (set.contains(FirNestedClassifierScopeKt.toConeType(firTypeParameterSymbol2, false)) || set.contains(FirNestedClassifierScopeKt.toConeType(firTypeParameterSymbol2, true))) {
                graph.addEdge(new TypeParameterNode(firRegularClassSymbol, firTypeParameterSymbol2), new TypeParameterNode(firRegularClassSymbol2, firTypeParameterSymbol), z);
            }
        }
    }

    private static final void buildTypeGraph$visit(FirSession firSession, SmartSet<FirClassifierSymbol<?>> smartSet, Graph<TypeParameterNode> graph, FirRegularClassSymbol firRegularClassSymbol) {
        boolean z;
        List<FirTypeParameterSymbol> typeParameterSymbols = firRegularClassSymbol.getTypeParameterSymbols();
        if (typeParameterSymbols.isEmpty()) {
            return;
        }
        List<ConeKotlinType> resolvedSuperTypes = firRegularClassSymbol.getResolvedSuperTypes();
        ArrayList<ConeKotlinType> arrayList = new ArrayList();
        Iterator<T> it = resolvedSuperTypes.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, FirNonExpansiveInheritanceRestrictionCheckerKt.constituentTypes((ConeKotlinType) it.next()));
        }
        for (ConeKotlinType coneKotlinType : arrayList) {
            FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(coneKotlinType, firSession);
            if (regularClassSymbol != null) {
                if (smartSet.add(regularClassSymbol)) {
                    buildTypeGraph$visit(firSession, smartSet, graph, regularClassSymbol);
                }
                List<FirTypeParameterSymbol> typeParameterSymbols2 = regularClassSymbol.getTypeParameterSymbols();
                List listAsList = ArraysKt.asList(coneKotlinType.getTypeArguments());
                if (typeParameterSymbols2.size() == listAsList.size()) {
                    List<ConeTypeProjection> list = listAsList;
                    int i = 0;
                    if (!(list instanceof Collection) || !list.isEmpty()) {
                        Iterator it2 = list.iterator();
                        while (true) {
                            if (it2.hasNext()) {
                                if (((ConeTypeProjection) it2.next()).getKind() != ProjectionKind.INVARIANT) {
                                    z = true;
                                    break;
                                }
                            } else {
                                z = false;
                                break;
                            }
                        }
                    } else {
                        z = false;
                        break;
                    }
                    ConeSubstitutor coneSubstitutorSubstitutorByType = z ? FirNonExpansiveInheritanceRestrictionCheckerKt.substitutorByType(typeParameterSymbols2, listAsList, firSession) : null;
                    for (ConeTypeProjection coneTypeProjection : list) {
                        int i2 = i + 1;
                        FirTypeParameterSymbol firTypeParameterSymbol = typeParameterSymbols2.get(i);
                        if (coneTypeProjection.getKind() == ProjectionKind.INVARIANT) {
                            ConeKotlinType type = ConeTypeProjectionKt.getType(coneTypeProjection);
                            type.getClass();
                            Set setConstituentTypes = FirNonExpansiveInheritanceRestrictionCheckerKt.constituentTypes(type);
                            ConeKotlinType type2 = ConeTypeProjectionKt.getType(coneTypeProjection);
                            type2.getClass();
                            buildTypeGraph$addEdges(graph, typeParameterSymbols, setConstituentTypes, firRegularClassSymbol, regularClassSymbol, firTypeParameterSymbol, !(ConeTypeUtilsKt.unwrapLowerBound(type2) instanceof ConeTypeParameterType));
                        } else {
                            SmartSet smartSetCreate = SmartSet.Companion.create();
                            for (FirResolvedTypeRef firResolvedTypeRef : firTypeParameterSymbol.getResolvedBounds()) {
                                coneSubstitutorSubstitutorByType.getClass();
                                ConeKotlinType coneKotlinTypeSubstituteOrNull = coneSubstitutorSubstitutorByType.substituteOrNull(firResolvedTypeRef.getConeType());
                                if (coneKotlinTypeSubstituteOrNull != null) {
                                    smartSetCreate.add(coneKotlinTypeSubstituteOrNull);
                                }
                            }
                            ConeKotlinType type3 = ConeTypeProjectionKt.getType(coneTypeProjection);
                            if (type3 != null) {
                                smartSetCreate.add(type3);
                            }
                            SmartSet smartSetCreate2 = SmartSet.Companion.create();
                            Iterator it3 = smartSetCreate.iterator();
                            while (it3.hasNext()) {
                                CollectionsKt.addAll(smartSetCreate2, TypeUtilsKt.collectUpperBounds((ConeKotlinType) it3.next(), TypeComponentsKt.getTypeContext(firSession)));
                            }
                            SmartSet smartSetCreate3 = SmartSet.Companion.create();
                            Iterator it4 = smartSetCreate2.iterator();
                            while (it4.hasNext()) {
                                CollectionsKt.addAll(smartSetCreate3, FirNonExpansiveInheritanceRestrictionCheckerKt.constituentTypes((ConeClassLikeType) it4.next()));
                            }
                            buildTypeGraph$addEdges(graph, typeParameterSymbols, smartSetCreate3, firRegularClassSymbol, regularClassSymbol, firTypeParameterSymbol, true);
                        }
                        i = i2;
                    }
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirRegularClass firRegularClass) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firRegularClass.getClass();
        if (firRegularClass.getTypeParameters().isEmpty()) {
            return;
        }
        Graph<TypeParameterNode> graphBuildTypeGraph = buildTypeGraph(firRegularClass, checkerContext.getSession());
        SmartSet<ExpansiveEdge<TypeParameterNode>> expansiveEdges = graphBuildTypeGraph.getExpansiveEdges();
        SmartSet<ExpansiveEdge> smartSetCreate = SmartSet.Companion.create();
        for (Object obj : expansiveEdges) {
            if (graphBuildTypeGraph.isEdgeInCycle((ExpansiveEdge) obj)) {
                smartSetCreate.add(obj);
            }
        }
        if (smartSetCreate.isEmpty()) {
            return;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (ExpansiveEdge expansiveEdge : smartSetCreate) {
            CollectionsKt.addAll(linkedHashSet, CollectionsKt.listOf(new TypeParameterNode[]{expansiveEdge.getFrom(), expansiveEdge.getTo()}));
        }
        for (FirTypeParameterRef firTypeParameterRef : firRegularClass.getTypeParameters()) {
            if (linkedHashSet.remove(new TypeParameterNode(firRegularClass.getSymbol(), firTypeParameterRef.getSymbol()))) {
                KtSourceElement source = firTypeParameterRef.getSource();
                if (source == null) {
                    source = firRegularClass.getSource();
                }
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getEXPANSIVE_INHERITANCE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                return;
            }
        }
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(linkedHashSet, 10));
        Iterator it = linkedHashSet.iterator();
        while (it.hasNext()) {
            arrayList.add(((TypeParameterNode) it.next()).getContainer());
        }
        if (!arrayList.isEmpty()) {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                if (!(((FirRegularClassSymbol) it2.next()).getOrigin() instanceof FirDeclarationOrigin.Java)) {
                    return;
                }
            }
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firRegularClass.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getEXPANSIVE_INHERITANCE_IN_JAVA(), (Object) arrayList, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
    }
}

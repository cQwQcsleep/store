package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.TypeUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirFiniteBoundRestrictionChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirNestedClassifierScopeKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ProjectionKind;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.utils.DFS;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ*\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00110\u000f2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J*\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00102\u0018\u0010\u0017\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00110\u000fH\u0002¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFiniteBoundRestrictionChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirRegularClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)V", "buildTypeEdges", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "isInCycle", Argument.Delimiters.none, "start", "edges", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirFiniteBoundRestrictionChecker extends FirDeclarationChecker<FirRegularClass> {
    public static final FirFiniteBoundRestrictionChecker INSTANCE = new FirFiniteBoundRestrictionChecker();

    private FirFiniteBoundRestrictionChecker() {
        super(MppCheckerKind.Common);
    }

    public static Iterable b(Map map, ConeKotlinType coneKotlinType) {
        Set set = (Set) map.get(coneKotlinType);
        return set != null ? set : CollectionsKt.emptyList();
    }

    private final Map<ConeKotlinType, Set<ConeKotlinType>> buildTypeEdges(FirRegularClass declaration, FirSession session) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = declaration.getTypeParameters().iterator();
        while (it.hasNext()) {
            buildTypeEdges$visit(session, linkedHashSet, linkedHashMap, FirNestedClassifierScopeKt.toConeType((FirTypeParameterRef) it.next()));
        }
        return linkedHashMap;
    }

    private static final void buildTypeEdges$visit(FirSession firSession, Set<FirClassifierSymbol<?>> set, Map<ConeKotlinType, Set<ConeKotlinType>> map, ConeKotlinType coneKotlinType) {
        List<FirTypeParameterSymbol> typeParameterSymbols;
        LinkedHashSet<ConeKotlinType> linkedHashSet = new LinkedHashSet();
        Iterator<ConeClassLikeType> it = TypeUtilsKt.collectUpperBounds(coneKotlinType, TypeComponentsKt.getTypeContext(firSession)).iterator();
        while (it.hasNext()) {
            List listMutableListOf = CollectionsKt.mutableListOf(new ConeKotlinType[]{it.next()});
            while (!listMutableListOf.isEmpty()) {
                ConeKotlinType coneKotlinType2 = (ConeKotlinType) AddToStdlibKt.popLast(listMutableListOf);
                linkedHashSet.add(coneKotlinType2);
                if (coneKotlinType2 instanceof ConeFlexibleType) {
                    ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinType2;
                    listMutableListOf.add(coneFlexibleType.getLowerBound());
                    if (!coneFlexibleType.getIsTrivial()) {
                        listMutableListOf.add(coneFlexibleType.getUpperBound());
                    }
                } else if (coneKotlinType2 instanceof ConeDefinitelyNotNullType) {
                    listMutableListOf.add(((ConeDefinitelyNotNullType) coneKotlinType2).getOriginal());
                } else if (coneKotlinType2 instanceof ConeIntersectionType) {
                    listMutableListOf.addAll(((ConeIntersectionType) coneKotlinType2).getIntersectedTypes());
                } else {
                    for (ConeKotlinTypeProjection coneKotlinTypeProjection : coneKotlinType2.getTypeArguments()) {
                        if (coneKotlinTypeProjection instanceof ConeKotlinTypeProjection) {
                            listMutableListOf.add(coneKotlinTypeProjection.getType());
                        }
                    }
                }
            }
        }
        for (ConeKotlinType coneKotlinType3 : linkedHashSet) {
            FirClassifierSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(coneKotlinType3, firSession);
            if (symbol != null && (typeParameterSymbols = FirHelpersKt.getTypeParameterSymbols(symbol)) != null) {
                if (set.add(symbol)) {
                    Iterator<T> it2 = typeParameterSymbols.iterator();
                    while (it2.hasNext()) {
                        buildTypeEdges$visit(firSession, set, map, FirNestedClassifierScopeKt.toConeType((FirTypeParameterSymbol) it2.next()));
                    }
                }
                if (typeParameterSymbols.size() == coneKotlinType3.getTypeArguments().length) {
                    int size = typeParameterSymbols.size();
                    for (int i = 0; i < size; i++) {
                        if (coneKotlinType3.getTypeArguments()[i].getKind() != ProjectionKind.INVARIANT) {
                            ConeTypeParameterType coneType = FirNestedClassifierScopeKt.toConeType(typeParameterSymbols.get(i));
                            Set<ConeKotlinType> linkedHashSet2 = map.get(coneKotlinType);
                            if (linkedHashSet2 == null) {
                                linkedHashSet2 = new LinkedHashSet<>();
                                map.put(coneKotlinType, linkedHashSet2);
                            }
                            linkedHashSet2.add(coneType);
                            if (map.get(coneType) == null) {
                                map.put(coneType, new LinkedHashSet());
                            }
                        }
                    }
                }
            }
        }
    }

    private final boolean isInCycle(final ConeKotlinType start, final Map<ConeKotlinType, ? extends Set<? extends ConeKotlinType>> edges) {
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        DFS.dfs(CollectionsKt.listOf(start), new DFS.Neighbors() { // from class: y75
            public final Iterable getNeighbors(Object obj) {
                return FirFiniteBoundRestrictionChecker.b(edges, (ConeKotlinType) obj);
            }
        }, new DFS.VisitedWithSet<ConeKotlinType>() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirFiniteBoundRestrictionChecker$isInCycle$dfsVisited$1
            public boolean checkAndMarkVisited(ConeKotlinType current) {
                current.getClass();
                boolean zCheckAndMarkVisited = super.checkAndMarkVisited(current);
                if (!zCheckAndMarkVisited && Intrinsics.areEqual(current, start)) {
                    booleanRef.element = true;
                }
                return zCheckAndMarkVisited;
            }
        }, new DFS.AbstractNodeHandler<ConeKotlinType, Unit>() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirFiniteBoundRestrictionChecker$isInCycle$dfsHandler$1
            public /* bridge */ /* synthetic */ Object result() {
                m230result();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: result, reason: collision with other method in class */
            public void m230result() {
            }
        });
        return booleanRef.element;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirRegularClass firRegularClass) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firRegularClass.getClass();
        if (firRegularClass.getTypeParameters().isEmpty()) {
            return;
        }
        Map<ConeKotlinType, Set<ConeKotlinType>> mapBuildTypeEdges = buildTypeEdges(firRegularClass, checkerContext.getSession());
        Set<ConeKotlinType> setKeySet = mapBuildTypeEdges.keySet();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Object obj : setKeySet) {
            if (INSTANCE.isInCycle((ConeKotlinType) obj, mapBuildTypeEdges)) {
                linkedHashSet.add(obj);
            }
        }
        if (linkedHashSet.isEmpty()) {
            return;
        }
        for (FirTypeParameterRef firTypeParameterRef : firRegularClass.getTypeParameters()) {
            if (linkedHashSet.remove(FirNestedClassifierScopeKt.toConeType(firTypeParameterRef))) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeParameterRef.getSource(), FirErrors.INSTANCE.getFINITE_BOUNDS_VIOLATION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                return;
            }
        }
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        Iterator it = linkedHashSet.iterator();
        while (it.hasNext()) {
            FirTypeParameterSymbol typeParameterSymbol = ToSymbolUtilsKt.toTypeParameterSymbol(checkerContext, (ConeKotlinType) it.next());
            if (typeParameterSymbol != null) {
                linkedHashSet2.add(typeParameterSymbol);
            }
        }
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(linkedHashSet2, 10));
        Iterator it2 = linkedHashSet2.iterator();
        while (it2.hasNext()) {
            arrayList.add(((FirTypeParameterSymbol) it2.next()).getContainingDeclarationSymbol());
        }
        if (!arrayList.isEmpty()) {
            Iterator it3 = arrayList.iterator();
            while (it3.hasNext()) {
                if (!(((FirBasedSymbol) it3.next()).getOrigin() instanceof FirDeclarationOrigin.Java)) {
                    return;
                }
            }
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firRegularClass.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getFINITE_BOUNDS_VIOLATION_IN_JAVA(), (Object) arrayList, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
    }
}

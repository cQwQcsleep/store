package org.jetbrains.kotlin.fir.resolve.dfa;

import defpackage.f2f;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.EventOccurrencesRangeKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirCodeFragment;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyBodyResolveStateKt;
import org.jetbrains.kotlin.fir.expressions.FirLoop;
import org.jetbrains.kotlin.fir.resolve.dfa.FirLocalVariableAssignmentAnalyzer;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CfgInternals;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.types.AbstractTypeChecker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 K2\u00020\u0001:\u0001KB\u007f\b\u0002\u0012\f\u0010\u0002\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\u001a\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0018\u00010\u0005\u0012\u001a\u0010\u000b\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u000e0\r0\f\u0012\u0018\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00110\u00100\f¢\u0006\u0004\b\u0012\u0010\u0013B\t\b\u0016¢\u0006\u0004\b\u0012\u0010\u0014J\u0019\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0017H\u0001b\u0002\b\u0019¢\u0006\u0002\b\u0018J\u0006\u0010\u001a\u001a\u00020\u001bJ&\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u001e2\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010 2\u0006\u0010\"\u001a\u00020#J0\u0010$\u001a\u00020\u00112\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010 2\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010 2\u0006\u0010\"\u001a\u00020#H\u0002J\u0012\u0010&\u001a\u0004\u0018\u00010\u00062\u0006\u0010'\u001a\u00020\u0001H\u0002J \u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u00052\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0002J&\u0010*\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010'\u001a\u00020\u00012\u0006\u0010+\u001a\u00020\u0011H\u0002J\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020-0 2\u0006\u0010.\u001a\u00020/J\u0006\u00100\u001a\u00020\u001bJ\u000e\u00101\u001a\u00020\u001b2\u0006\u00102\u001a\u000203J\u000e\u00104\u001a\u00020\u001b2\u0006\u00102\u001a\u000203J\u000e\u00105\u001a\u00020\u001b2\u0006\u00102\u001a\u000206J\u000e\u00107\u001a\u00020\u001b2\u0006\u00102\u001a\u000206J\u0010\u00108\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0010\u00109\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u000e\u0010:\u001a\u00020\u001b2\u0006\u0010;\u001a\u00020<J\u0006\u0010=\u001a\u00020\u001bJ\u0014\u0010>\u001a\u00020\u001b2\f\u0010?\u001a\b\u0012\u0004\u0012\u00020A0@J\u000e\u0010B\u001a\u00020\u001b2\u0006\u0010C\u001a\u00020\u0011J\u0014\u0010D\u001a\b\u0012\u0004\u0012\u00020-0 2\u0006\u0010E\u001a\u00020FJ\f\u0010G\u001a\b\u0012\u0004\u0012\u00020-0 J\u0016\u0010H\u001a\u00020\u001b2\u0006\u0010I\u001a\u00020\b2\u0006\u0010J\u001a\u00020!R\u0014\u0010\u0002\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u000b\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u000e0\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00110\u00100\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006L"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/FirLocalVariableAssignmentAnalyzer;", Argument.Delimiters.none, "rootSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "assignedLocalVariablesByDeclaration", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/FirLocalVariableAssignmentAnalyzer$Companion$Fork;", "variableAssignments", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/FirLocalVariableAssignmentAnalyzer$Companion$Assignment;", "scopes", "Lorg/jetbrains/kotlin/fir/resolve/dfa/Stack;", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/FirLocalVariableAssignmentAnalyzer$Companion$VariableAssignments;", "postponedLambdas", Argument.Delimiters.none, Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Ljava/util/Map;Ljava/util/Map;Lorg/jetbrains/kotlin/fir/resolve/dfa/Stack;Lorg/jetbrains/kotlin/fir/resolve/dfa/Stack;)V", "()V", "createSnapshot", "firMapper", "Lorg/jetbrains/kotlin/fir/resolve/dfa/SnapshotFirMapper;", "createSnapshot$org_jetbrains_kotlin_resolve", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CfgInternals;", "reset", Argument.Delimiters.none, "isUnstableInCurrentScope", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "types", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "allAssignmentsPreserveType", "assignments", "getInfoForDeclaration", "symbol", "buildInfoForRoot", "root", "enterScope", "evaluatedInPlace", "enterFunction", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "exitFunction", "enterAnonymousInitializer", "anonymousInitializer", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousInitializer;", "exitAnonymousInitializer", "enterCodeFragment", "Lorg/jetbrains/kotlin/fir/declarations/FirCodeFragment;", "exitCodeFragment", "enterNewTopLevelScopeIfNeeded", "exitNewTopLevelScopeIfNeeded", "enterClass", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "exitClass", "enterFunctionCall", "lambdaArgs", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "exitFunctionCall", "callCompleted", "enterLoop", "loop", "Lorg/jetbrains/kotlin/fir/expressions/FirLoop;", "exitLoop", "visitAssignment", "property", ModuleXmlParser.TYPE, "Companion", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirLocalVariableAssignmentAnalyzer {
    private Map<Object, Companion.Fork> assignedLocalVariablesByDeclaration;
    private final Stack<Map<Companion.Fork, Boolean>> postponedLambdas;
    private FirBasedSymbol<?> rootSymbol;
    private final Stack<Pair<Companion.Fork, Companion.VariableAssignments>> scopes;
    private Map<FirProperty, ? extends List<Companion.Assignment>> variableAssignments;

    public FirLocalVariableAssignmentAnalyzer() {
        this(null, null, null, StackKt.stackOf(new Pair[0]), StackKt.stackOf(new Map[0]));
    }

    public static Object a(SnapshotFirMapper snapshotFirMapper, Object obj) {
        return createSnapshot$clone(snapshotFirMapper, obj);
    }

    private final boolean allAssignmentsPreserveType(Set<Companion.Assignment> assignments, Set<? extends ConeKotlinType> types, FirSession session) {
        Set<Companion.Assignment> set = assignments;
        if (set == null || set.isEmpty()) {
            return true;
        }
        if (types == null) {
            return false;
        }
        Set<Companion.Assignment> set2 = assignments;
        boolean z = set2 instanceof Collection;
        if (!z || !set2.isEmpty()) {
            Iterator<T> it = set2.iterator();
            while (it.hasNext()) {
                if (((Companion.Assignment) it.next()).getType() == null) {
                    return false;
                }
            }
        }
        if (z && set2.isEmpty()) {
            return true;
        }
        for (Companion.Assignment assignment : set2) {
            Set<? extends ConeKotlinType> set3 = types;
            if (!(set3 instanceof Collection) || !set3.isEmpty()) {
                for (ConeKotlinType coneKotlinType : set3) {
                    AbstractTypeChecker abstractTypeChecker = AbstractTypeChecker.INSTANCE;
                    ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(session);
                    ConeKotlinType type = assignment.getType();
                    type.getClass();
                    if (!AbstractTypeChecker.isSubtypeOf$default(abstractTypeChecker, typeContext, type, coneKotlinType, false, 8, (Object) null)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private final Map<Object, Companion.Fork> buildInfoForRoot(FirBasedSymbol<?> root) {
        Map<Object, Companion.Fork> map = this.assignedLocalVariablesByDeclaration;
        if (map != null) {
            return map;
        }
        Companion.MiniCfgBuilder.MiniCfgData miniCfgData = new Companion.MiniCfgBuilder.MiniCfgData();
        new Companion.MiniCfgBuilder().visitElement2((FirElement) root.getFir(), miniCfgData);
        this.assignedLocalVariablesByDeclaration = miniCfgData.getForks();
        this.variableAssignments = miniCfgData.getAssignments();
        return miniCfgData.getForks();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [T, java.util.ArrayList, java.util.Collection] */
    private static final <T> T createSnapshot$clone(final SnapshotFirMapper snapshotFirMapper, T t) {
        if (t instanceof FirBasedSymbol) {
            return (T) snapshotFirMapper.mapSymbol((FirBasedSymbol) t);
        }
        if (t instanceof FirElement) {
            return (T) snapshotFirMapper.mapElement((FirElement) t);
        }
        if (t instanceof Companion.Fork) {
            return (T) ((Companion.Fork) t).createSnapshot(snapshotFirMapper);
        }
        if (t instanceof Companion.VariableAssignments) {
            return (T) ((Companion.VariableAssignments) t).createSnapshot(snapshotFirMapper);
        }
        if (t instanceof Pair) {
            Pair pair = (Pair) t;
            return (T) new Pair(createSnapshot$clone(snapshotFirMapper, pair.getFirst()), createSnapshot$clone(snapshotFirMapper, pair.getSecond()));
        }
        if (t instanceof List) {
            Iterable iterable = (Iterable) t;
            ?? r0 = (T) new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            Iterator<T> it = iterable.iterator();
            while (it.hasNext()) {
                r0.add(createSnapshot$clone(snapshotFirMapper, it.next()));
            }
            return r0;
        }
        if (t instanceof Map) {
            Map mapCreateMapBuilder = MapsKt.createMapBuilder();
            for (Map.Entry entry : ((Map) t).entrySet()) {
                mapCreateMapBuilder.put(createSnapshot$clone(snapshotFirMapper, entry.getKey()), createSnapshot$clone(snapshotFirMapper, entry.getValue()));
            }
            return (T) MapsKt.build(mapCreateMapBuilder);
        }
        if (t instanceof Stack) {
            return (T) ((Stack) t).createSnapshot(new Function1() { // from class: oa5
                public final Object invoke(Object obj) {
                    return FirLocalVariableAssignmentAnalyzer.a(snapshotFirMapper, obj);
                }
            });
        }
        if ((t instanceof Companion.Assignment) || (t instanceof Boolean) || t == 0) {
            return t;
        }
        f2f.a("Unexpected key type: ", Reflection.getOrCreateKotlinClass(t.getClass()).getSimpleName());
        return null;
    }

    private final void enterNewTopLevelScopeIfNeeded(FirDeclaration declaration) {
        if (this.rootSymbol != null) {
            return;
        }
        this.rootSymbol = declaration.getSymbol();
        this.scopes.push(TuplesKt.to(null, new Companion.VariableAssignments()));
    }

    private final Pair<Companion.Fork, Companion.VariableAssignments> enterScope(Object symbol, boolean evaluatedInPlace) {
        Companion.Fork infoForDeclaration = getInfoForDeclaration(symbol);
        Companion.VariableAssignments variableAssignmentsCopy = ((Companion.VariableAssignments) this.scopes.top().getSecond()).copy();
        this.scopes.push(TuplesKt.to(infoForDeclaration, variableAssignmentsCopy));
        if (!evaluatedInPlace) {
            for (Pair<Companion.Fork, Companion.VariableAssignments> pair : this.scopes.all()) {
                Companion.Fork fork = (Companion.Fork) pair.component1();
                Companion.VariableAssignments assignedLater = null;
                ((Companion.VariableAssignments) pair.component2()).merge(infoForDeclaration != null ? infoForDeclaration.getAssignedInside() : null);
                if (fork != null) {
                    assignedLater = fork.getAssignedLater();
                }
                variableAssignmentsCopy.merge(assignedLater);
            }
        }
        return this.scopes.top();
    }

    private final void exitNewTopLevelScopeIfNeeded(FirDeclaration declaration) {
        if (Intrinsics.areEqual(this.rootSymbol, declaration.getSymbol())) {
            this.rootSymbol = null;
            this.scopes.pop();
            this.assignedLocalVariablesByDeclaration = null;
            this.variableAssignments = null;
        }
    }

    private final Companion.Fork getInfoForDeclaration(Object symbol) {
        FirBasedSymbol<?> firBasedSymbol = this.rootSymbol;
        if (firBasedSymbol == null || Intrinsics.areEqual(firBasedSymbol, symbol)) {
            return null;
        }
        return buildInfoForRoot(firBasedSymbol).get(symbol);
    }

    @CfgInternals
    public final FirLocalVariableAssignmentAnalyzer createSnapshot$org_jetbrains_kotlin_resolve(SnapshotFirMapper firMapper) {
        firMapper.getClass();
        return new FirLocalVariableAssignmentAnalyzer(this.rootSymbol, (Map) createSnapshot$clone(firMapper, this.assignedLocalVariablesByDeclaration), (Map) createSnapshot$clone(firMapper, this.variableAssignments), (Stack) createSnapshot$clone(firMapper, this.scopes), (Stack) createSnapshot$clone(firMapper, this.postponedLambdas));
    }

    public final void enterAnonymousInitializer(FirAnonymousInitializer anonymousInitializer) {
        anonymousInitializer.getClass();
        enterNewTopLevelScopeIfNeeded(anonymousInitializer);
    }

    public final void enterClass(FirClass klass) {
        klass.getClass();
        if (this.rootSymbol == null) {
            return;
        }
        FirClassSymbol<FirClass> symbol = klass.getSymbol();
        boolean z = klass instanceof FirAnonymousObject;
        Pair<Companion.Fork, Companion.VariableAssignments> pairEnterScope = enterScope(symbol, z);
        Companion.Fork fork = (Companion.Fork) pairEnterScope.component1();
        Companion.VariableAssignments variableAssignments = (Companion.VariableAssignments) pairEnterScope.component2();
        if (!z || fork == null) {
            return;
        }
        variableAssignments.merge(fork.getAssignedInside());
    }

    public final void enterCodeFragment(FirCodeFragment anonymousInitializer) {
        anonymousInitializer.getClass();
        enterNewTopLevelScopeIfNeeded(anonymousInitializer);
    }

    public final Set<FirPropertySymbol> enterFunction(FirFunction function) {
        Companion.VariableAssignments assignedInside;
        function.getClass();
        Set<FirPropertySymbol> assignedProperties = null;
        if (this.rootSymbol == null) {
            this.rootSymbol = function.getSymbol();
            this.scopes.push(TuplesKt.to(null, new Companion.VariableAssignments()));
            return SetsKt.emptySet();
        }
        Pair<Companion.Fork, Companion.VariableAssignments> pairEnterScope = enterScope(function.getSymbol(), (function instanceof FirAnonymousFunction) && EventOccurrencesRangeKt.isInPlace(((FirAnonymousFunction) function).getInvocationKind()));
        Companion.Fork fork = (Companion.Fork) pairEnterScope.component1();
        Companion.VariableAssignments variableAssignments = (Companion.VariableAssignments) pairEnterScope.component2();
        Iterator<Map<Companion.Fork, Boolean>> it = this.postponedLambdas.all().iterator();
        while (it.hasNext()) {
            for (Map.Entry<Companion.Fork, Boolean> entry : it.next().entrySet()) {
                Companion.Fork key = entry.getKey();
                if (!entry.getValue().booleanValue() && !Intrinsics.areEqual(key, fork)) {
                    variableAssignments.merge(key.getAssignedInside());
                }
            }
        }
        Companion.Fork fork2 = (Companion.Fork) this.scopes.top().getFirst();
        if (fork2 != null && (assignedInside = fork2.getAssignedInside()) != null) {
            assignedProperties = assignedInside.getAssignedProperties();
        }
        return assignedProperties == null ? SetsKt.emptySet() : assignedProperties;
    }

    public final void enterFunctionCall(Collection<? extends FirAnonymousFunction> lambdaArgs) {
        lambdaArgs.getClass();
        if (this.rootSymbol == null) {
            return;
        }
        Stack<Map<Companion.Fork, Boolean>> stack = this.postponedLambdas;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = lambdaArgs.iterator();
        while (it.hasNext()) {
            Companion.Fork infoForDeclaration = getInfoForDeclaration(((FirAnonymousFunction) it.next()).getSymbol());
            if (infoForDeclaration != null) {
                arrayList.add(infoForDeclaration);
            }
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : arrayList) {
            linkedHashMap.put(obj, Boolean.FALSE);
        }
        stack.push(linkedHashMap);
    }

    public final Set<FirPropertySymbol> enterLoop(FirLoop loop) {
        Companion.VariableAssignments assignedInside;
        loop.getClass();
        if (this.rootSymbol == null) {
            return SetsKt.emptySet();
        }
        Companion.Fork fork = (Companion.Fork) enterScope(loop, true).component1();
        Set<FirPropertySymbol> assignedProperties = (fork == null || (assignedInside = fork.getAssignedInside()) == null) ? null : assignedInside.getAssignedProperties();
        return assignedProperties == null ? SetsKt.emptySet() : assignedProperties;
    }

    public final void exitAnonymousInitializer(FirAnonymousInitializer anonymousInitializer) {
        anonymousInitializer.getClass();
        exitNewTopLevelScopeIfNeeded(anonymousInitializer);
    }

    public final void exitClass() {
        if (this.rootSymbol == null) {
            return;
        }
        this.scopes.pop();
    }

    public final void exitCodeFragment(FirCodeFragment anonymousInitializer) {
        anonymousInitializer.getClass();
        exitNewTopLevelScopeIfNeeded(anonymousInitializer);
    }

    public final void exitFunction() {
        this.scopes.pop();
        if (StackKt.isEmpty(this.scopes)) {
            this.rootSymbol = null;
            this.assignedLocalVariablesByDeclaration = null;
            this.variableAssignments = null;
        }
    }

    public final void exitFunctionCall(boolean callCompleted) {
        if (this.rootSymbol == null) {
            return;
        }
        Map<Companion.Fork, Boolean> mapPop = this.postponedLambdas.pop();
        if (callCompleted) {
            return;
        }
        Set<Companion.Fork> setKeySet = mapPop.keySet();
        Map map = (Map) StackKt.topOrNull(this.postponedLambdas);
        if (map == null) {
            return;
        }
        for (Object obj : setKeySet) {
            map.put(obj, Boolean.TRUE);
        }
    }

    public final Set<FirPropertySymbol> exitLoop() {
        Companion.VariableAssignments assignedInside;
        if (this.rootSymbol == null) {
            return SetsKt.emptySet();
        }
        Companion.Fork fork = (Companion.Fork) this.scopes.pop().component1();
        Set<FirPropertySymbol> assignedProperties = (fork == null || (assignedInside = fork.getAssignedInside()) == null) ? null : assignedInside.getAssignedProperties();
        return assignedProperties == null ? SetsKt.emptySet() : assignedProperties;
    }

    public final boolean isUnstableInCurrentScope(FirDeclaration declaration, Set<? extends ConeKotlinType> types, FirSession session) {
        declaration.getClass();
        session.getClass();
        if (this.assignedLocalVariablesByDeclaration != null && (declaration instanceof FirProperty)) {
            FirProperty firProperty = (FirProperty) declaration;
            if (FirPropertyBodyResolveStateKt.isEffectivelyLocal(firProperty) && firProperty.getIsVar()) {
                if (!allAssignmentsPreserveType(((Companion.VariableAssignments) this.scopes.top().getSecond()).get(firProperty), types, session)) {
                    return true;
                }
                List<Map<Companion.Fork, Boolean>> listAll = this.postponedLambdas.all();
                if (!(listAll instanceof Collection) || !listAll.isEmpty()) {
                    Iterator<T> it = listAll.iterator();
                    while (it.hasNext()) {
                        Map map = (Map) it.next();
                        if (!map.isEmpty()) {
                            for (Map.Entry entry : map.entrySet()) {
                                Companion.Fork fork = (Companion.Fork) entry.getKey();
                                if (((Boolean) entry.getValue()).booleanValue() && fork.getAssignedInside().contains(firProperty)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
                return false;
            }
        }
        return false;
    }

    public final void reset() {
        this.rootSymbol = null;
        this.assignedLocalVariablesByDeclaration = null;
        this.variableAssignments = null;
        this.postponedLambdas.reset();
        this.scopes.reset();
    }

    public final void visitAssignment(FirProperty property, ConeKotlinType type) {
        List<Companion.Assignment> list;
        Object next;
        property.getClass();
        type.getClass();
        FirBasedSymbol<?> firBasedSymbol = this.rootSymbol;
        if (firBasedSymbol == null) {
            return;
        }
        buildInfoForRoot(firBasedSymbol);
        Map<FirProperty, ? extends List<Companion.Assignment>> map = this.variableAssignments;
        if (map == null || (list = map.get(property)) == null) {
            return;
        }
        Iterator<T> it = list.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (((Companion.Assignment) next).getType() != null);
        Companion.Assignment assignment = (Companion.Assignment) next;
        if (assignment == null) {
            return;
        }
        assignment.setType(type);
    }

    private FirLocalVariableAssignmentAnalyzer(FirBasedSymbol<?> firBasedSymbol, Map<Object, Companion.Fork> map, Map<FirProperty, ? extends List<Companion.Assignment>> map2, Stack<Pair<Companion.Fork, Companion.VariableAssignments>> stack, Stack<Map<Companion.Fork, Boolean>> stack2) {
        this.rootSymbol = firBasedSymbol;
        this.assignedLocalVariablesByDeclaration = map;
        this.variableAssignments = map2;
        this.scopes = stack;
        this.postponedLambdas = stack2;
    }
}

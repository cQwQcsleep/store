package org.jetbrains.kotlin.fir.resolve.dfa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.Grouping;
import kotlin.collections.GroupingKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentList;
import kotlinx.collections.immutable.PersistentMap;
import kotlinx.collections.immutable.PersistentSet;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.DfaType;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.dfa.Implication;
import org.jetbrains.kotlin.fir.resolve.dfa.LogicSystem;
import org.jetbrains.kotlin.fir.resolve.dfa.RealVariable;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeIntersector;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000´\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\f\u0010\u0011\u001a\u00020\u0012*\u00020\u0013H\u0014J*\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010\u001a\u001a\u00020\u0012J\u001e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001fJ\u0018\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010\u001d\u001a\u00020\u00152\u0006\u0010#\u001a\u00020\"J,\u0010$\u001a\b\u0012\u0004\u0012\u00020\"0%2\u0006\u0010\u001d\u001a\u00020\u00152\u0016\u0010&\u001a\u0012\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\"0'j\u0002`)J\u0016\u0010*\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00152\u0006\u0010+\u001a\u00020,J\u0014\u0010-\u001a\u00020\u0012*\u00020\u00152\u0006\u0010.\u001a\u00020\"H\u0002J6\u0010/\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00152\u0006\u00100\u001a\u00020(2\u0006\u00101\u001a\u00020(2\u0016\b\u0002\u00102\u001a\u0010\u0012\u0004\u0012\u00020,\u0012\u0006\u0012\u0004\u0018\u00010,03J&\u00104\u001a\u0012\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\"0'j\u0002`)2\u0006\u0010\u001d\u001a\u00020\u00182\u0006\u0010#\u001a\u000205J.\u00104\u001a\u0012\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\"0'j\u0002`)2\u0006\u0010\u001d\u001a\u00020\u00152\u0006\u0010#\u001a\u0002052\u0006\u00106\u001a\u00020\u0012J\u001e\u00107\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00152\u0006\u00108\u001a\u00020\u001f2\u0006\u00109\u001a\u00020:J\u001e\u0010;\u001a\u00020\u00122\u0006\u0010<\u001a\u00020\u00182\u0006\u0010=\u001a\u00020\u00182\u0006\u00108\u001a\u00020\u001fJ\u001e\u0010;\u001a\u00020\u00122\u0006\u0010<\u001a\u00020\u00182\u0006\u0010=\u001a\u00020\u00152\u0006\u00108\u001a\u00020\u001fJ\u001a\u0010>\u001a\u00020\u001c*\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0002J\u001a\u0010?\u001a\u00020\u001c*\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0002J\"\u0010@\u001a\u00020\u001c*\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010A\u001a\u00020\u0018H\u0002J*\u0010B\u001a\u00020\u001c*\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010A\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u0012H\u0002J\u001a\u0010C\u001a\u00020\u001c*\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0002J\u001e\u0010D\u001a\u00020\u001c*\u00020\u00152\u0006\u00108\u001a\u00020\u001f2\b\u0010E\u001a\u0004\u0018\u00010\u001fH\u0002JB\u00104\u001a\u0012\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\"0'j\u0002`)2\u0018\u0010F\u001a\u0014\u0012\u0004\u0012\u00020(\u0012\n\u0012\b\u0012\u0004\u0012\u00020,0G0'2\u0006\u0010H\u001a\u0002052\u0006\u00106\u001a\u00020\u0012H\u0002J\u0016\u0010I\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020J2\u0006\u0010#\u001a\u00020\"JF\u0010K\u001a\u0012\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\"0'j\u0002`)2\u0016\u0010L\u001a\u0012\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\"0'j\u0002`)2\u0016\u0010M\u001a\u0012\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\"0'j\u0002`)JF\u0010N\u001a\u0012\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\"0'j\u0002`)2\u0016\u0010L\u001a\u0012\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\"0'j\u0002`)2\u0016\u0010M\u001a\u0012\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\"0'j\u0002`)J\u0015\u0010O\u001a\u00020\u001c*\u00020P2\u0006\u0010Q\u001a\u00020\"H\u0082\u0002J\u0018\u0010R\u001a\u00020\"2\b\u0010<\u001a\u0004\u0018\u00010\"2\u0006\u0010=\u001a\u00020\"J\u0016\u0010R\u001a\u0004\u0018\u00010\"2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\"0\u0017J\u0016\u0010S\u001a\u0004\u0018\u00010\"2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\"0\u0017J\u0014\u0010T\u001a\u0004\u0018\u00010\u0013*\b\u0012\u0004\u0012\u00020\"0\u0017H\u0002J\u0014\u0010U\u001a\u0004\u0018\u00010\u0013*\b\u0012\u0004\u0012\u00020\"0\u0017H\u0002J\u0018\u0010V\u001a\b\u0012\u0004\u0012\u00020X0W*\b\u0012\u0004\u0012\u00020\"0\u0017H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u00020\u000eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006Y"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/LogicSystem;", Argument.Delimiters.none, "context", "Lorg/jetbrains/kotlin/fir/types/ConeInferenceContext;", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeInferenceContext;)V", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "nullableNothingType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "anyType", "variableStorage", "Lorg/jetbrains/kotlin/fir/resolve/dfa/VariableStorage;", "getVariableStorage", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/VariableStorage;", "isAcceptableForSmartcast", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "joinFlow", "Lorg/jetbrains/kotlin/fir/resolve/dfa/MutableFlow;", "flows", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/PersistentFlow;", "statementFlows", "union", "addLocalVariableAlias", Argument.Delimiters.none, "flow", "alias", "Lorg/jetbrains/kotlin/fir/resolve/dfa/RealVariable;", "underlyingVariable", "addTypeStatement", "Lorg/jetbrains/kotlin/fir/resolve/dfa/TypeStatement;", "statement", "addTypeStatements", Argument.Delimiters.none, "statements", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowVariable;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/TypeStatements;", "addImplication", "implication", "Lorg/jetbrains/kotlin/fir/resolve/dfa/Implication;", "containsAlready", "effect", "translateVariableFromConditionInStatements", "originalVariable", "newVariable", "transform", "Lkotlin/Function1;", "approveOperationStatement", "Lorg/jetbrains/kotlin/fir/resolve/dfa/OperationStatement;", "removeApprovedOrImpossible", "recordNewAssignment", "variable", "index", Argument.Delimiters.none, "isSameValueIn", "a", "b", "mergeAssignments", "copyCommonAliases", "copyNonConflictingAliases", "commonFlow", "copyStatements", "copyImplications", "replaceVariable", "replacement", "logicStatements", "Lkotlinx/collections/immutable/PersistentList;", "approvedStatement", "approveTypeStatement", "Lorg/jetbrains/kotlin/fir/resolve/dfa/Flow;", "orForTypeStatements", "left", "right", "andForTypeStatements", "plusAssign", "Lorg/jetbrains/kotlin/fir/resolve/dfa/MutableTypeStatement;", "other", "and", "or", "getUnifiedUpperType", "getIntersectedLowerType", "getCommonExcludedValues", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/DfaType$Symbol;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class LogicSystem {
    private final ConeClassLikeType anyType;
    private final ConeInferenceContext context;
    private final ConeClassLikeType nullableNothingType;

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Operation.values().length];
            try {
                iArr[Operation.EqTrue.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Operation.EqFalse.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public LogicSystem(ConeInferenceContext coneInferenceContext) {
        coneInferenceContext.getClass();
        this.context = coneInferenceContext;
        this.nullableNothingType = getSession().getBuiltinTypes().getNullableNothingType().getConeType();
        this.anyType = getSession().getBuiltinTypes().getAnyType().getConeType();
    }

    public static Unit a(LogicSystem logicSystem, MutableFlow mutableFlow, RealVariable realVariable, RealVariable realVariable2) {
        realVariable.getClass();
        logicSystem.replaceVariable(mutableFlow, realVariable, realVariable2);
        return Unit.INSTANCE;
    }

    private final Map<DataFlowVariable, TypeStatement> approveOperationStatement(Map<DataFlowVariable, ? extends PersistentList<Implication>> logicStatements, OperationStatement approvedStatement, boolean removeApprovedOrImpossible) {
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        final LinkedList linkedList = new LinkedList();
        linkedList.add(approvedStatement);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        while (!linkedList.isEmpty()) {
            Object objRemoveFirst = linkedList.removeFirst();
            objRemoveFirst.getClass();
            OperationStatement operationStatement = (OperationStatement) objRemoveFirst;
            if (removeApprovedOrImpossible || linkedHashSet.add(operationStatement)) {
                final Operation operation = operationStatement.getOperation();
                DataFlowVariable variable = operationStatement.getVariable();
                ConeClassLikeType coneClassLikeType = operation == Operation.EqNull ? this.nullableNothingType : this.anyType;
                Object obj = linkedHashMap.get(variable);
                if (obj == null) {
                    MutableTypeStatement mutableTypeStatement = new MutableTypeStatement(variable, null, null, 6, null);
                    linkedHashMap.put(variable, mutableTypeStatement);
                    obj = mutableTypeStatement;
                }
                MutableTypeStatement mutableTypeStatement2 = (MutableTypeStatement) obj;
                mutableTypeStatement2.getUpperTypes().add(coneClassLikeType);
                int i = WhenMappings.$EnumSwitchMapping$0[operation.ordinal()];
                if (i == 1) {
                    mutableTypeStatement2.getLowerTypes().add(new DfaType.BooleanLiteral(false));
                } else if (i == 2) {
                    mutableTypeStatement2.getLowerTypes().add(new DfaType.BooleanLiteral(true));
                }
                PersistentList<Implication> persistentList = logicStatements.get(variable);
                if (persistentList != null) {
                    final LogicSystem logicSystem = this;
                    final boolean z = removeApprovedOrImpossible;
                    PersistentList persistentListRemoveAll = persistentList.removeAll(new Function1() { // from class: qg9
                        public final Object invoke(Object obj2) {
                            return Boolean.valueOf(LogicSystem.c(operation, linkedList, logicSystem, linkedHashMap, z, (Implication) obj2));
                        }
                    });
                    if (!Intrinsics.areEqual(persistentListRemoveAll, persistentList) && TypeIntrinsics.isMutableMap(logicStatements)) {
                        if (persistentListRemoveAll.isEmpty()) {
                            logicStatements.remove(variable);
                        } else {
                            logicStatements.put(variable, persistentListRemoveAll);
                        }
                    }
                    this = logicSystem;
                    removeApprovedOrImpossible = z;
                }
            }
        }
        return linkedHashMap;
    }

    public static Implication b(Implication implication) {
        implication.getClass();
        return implication;
    }

    public static boolean c(Operation operation, LinkedList linkedList, LogicSystem logicSystem, Map map, boolean z, Implication implication) {
        implication.getClass();
        Boolean boolValueIfKnown = implication.getCondition().getOperation().valueIfKnown(operation);
        if (Intrinsics.areEqual(boolValueIfKnown, Boolean.TRUE)) {
            Statement effect = implication.getEffect();
            if (effect instanceof OperationStatement) {
                linkedList.add(effect);
            } else {
                if (!(effect instanceof TypeStatement)) {
                    bu8.a();
                    return false;
                }
                TypeStatement typeStatement = (TypeStatement) effect;
                DataFlowVariable variable = typeStatement.getVariable();
                Object obj = map.get(variable);
                if (obj == null) {
                    MutableTypeStatement mutableTypeStatement = new MutableTypeStatement(typeStatement.getVariable(), null, null, 6, null);
                    map.put(variable, mutableTypeStatement);
                    obj = mutableTypeStatement;
                }
                logicSystem.plusAssign((MutableTypeStatement) obj, typeStatement);
            }
        }
        return z && boolValueIfKnown != null;
    }

    private final boolean containsAlready(MutableFlow mutableFlow, TypeStatement typeStatement) {
        PersistentTypeStatement persistentTypeStatement = (PersistentTypeStatement) mutableFlow.getApprovedTypeStatements$org_jetbrains_kotlin_semantics().get(typeStatement.getVariable());
        return persistentTypeStatement != null && persistentTypeStatement.getUpperTypes().containsAll(typeStatement.getUpperTypes()) && persistentTypeStatement.getLowerTypes().containsAll(typeStatement.getLowerTypes());
    }

    private final void copyCommonAliases(MutableFlow mutableFlow, Collection<PersistentFlow> collection) {
        Collection<PersistentFlow> collection2 = collection;
        for (Map.Entry entry : ((PersistentFlow) CollectionsKt.first(collection2)).getDirectAliasMap$org_jetbrains_kotlin_semantics().entrySet()) {
            RealVariable realVariable = (RealVariable) entry.getKey();
            RealVariable realVariable2 = (RealVariable) entry.getValue();
            if (!Intrinsics.areEqual(mutableFlow.getDirectAliasMap$org_jetbrains_kotlin_semantics().get(realVariable), realVariable2)) {
                if (!(collection2 instanceof Collection) || !collection2.isEmpty()) {
                    Iterator<T> it = collection2.iterator();
                    do {
                        if (it.hasNext()) {
                        }
                    } while (Intrinsics.areEqual(((PersistentFlow) it.next()).unwrapVariable(realVariable), realVariable2));
                }
                addLocalVariableAlias(mutableFlow, realVariable, realVariable2);
                break;
            }
        }
    }

    private final void copyImplications(MutableFlow mutableFlow, Collection<PersistentFlow> collection) {
        if (collection.size() != 1) {
            return;
        }
        mutableFlow.getImplications$org_jetbrains_kotlin_semantics().putAll(((PersistentFlow) CollectionsKt.first(collection)).getImplications$org_jetbrains_kotlin_semantics());
    }

    private final void copyNonConflictingAliases(MutableFlow mutableFlow, Collection<PersistentFlow> collection, PersistentFlow persistentFlow) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (PersistentFlow persistentFlow2 : collection) {
            for (Map.Entry entry : persistentFlow2.getDirectAliasMap$org_jetbrains_kotlin_semantics().entrySet()) {
                RealVariable realVariable = (RealVariable) entry.getKey();
                RealVariable realVariable2 = (RealVariable) entry.getValue();
                if (!Intrinsics.areEqual(persistentFlow.getAssignmentIndex$org_jetbrains_kotlin_semantics().get(realVariable), persistentFlow2.getAssignmentIndex$org_jetbrains_kotlin_semantics().get(realVariable))) {
                    if (linkedHashMap.containsKey(realVariable) && !Intrinsics.areEqual(linkedHashMap.get(realVariable), realVariable2)) {
                        realVariable2 = null;
                    }
                    linkedHashMap.put(realVariable, realVariable2);
                }
            }
        }
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            RealVariable realVariable3 = (RealVariable) entry2.getKey();
            RealVariable realVariable4 = (RealVariable) entry2.getValue();
            if (realVariable4 != null) {
                addLocalVariableAlias(mutableFlow, realVariable3, realVariable4);
            }
        }
    }

    private final void copyStatements(MutableFlow mutableFlow, Collection<PersistentFlow> collection, PersistentFlow persistentFlow, boolean z) {
        TypeStatement typeStatementOr;
        Collection<PersistentFlow> collection2 = collection;
        LinkedHashSet<DataFlowVariable> linkedHashSet = new LinkedHashSet();
        Iterator<T> it = collection2.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(linkedHashSet, ((PersistentFlow) it.next()).getKnownVariables());
        }
        for (DataFlowVariable dataFlowVariable : linkedHashSet) {
            if (!mutableFlow.getDirectAliasMap$org_jetbrains_kotlin_semantics().containsKey(dataFlowVariable)) {
                if (z) {
                    if (Intrinsics.areEqual(mutableFlow.getAssignmentIndex$org_jetbrains_kotlin_semantics().get(dataFlowVariable), persistentFlow.getAssignmentIndex$org_jetbrains_kotlin_semantics().get(dataFlowVariable))) {
                        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
                        Iterator<T> it2 = collection2.iterator();
                        while (it2.hasNext()) {
                            TypeStatement typeStatement = ((PersistentFlow) it2.next()).getTypeStatement(dataFlowVariable);
                            if (typeStatement != null) {
                                linkedHashSet2.add(typeStatement);
                            }
                        }
                        typeStatementOr = and(linkedHashSet2);
                    } else {
                        LinkedHashMap linkedHashMap = new LinkedHashMap();
                        Iterator<T> it3 = collection2.iterator();
                        while (true) {
                            if (!it3.hasNext()) {
                                break;
                            }
                            PersistentFlow persistentFlow2 = (PersistentFlow) it3.next();
                            Integer num = (Integer) persistentFlow2.getAssignmentIndex$org_jetbrains_kotlin_semantics().get(dataFlowVariable);
                            Integer numValueOf = Integer.valueOf(num != null ? num.intValue() : -1);
                            Object arrayList = linkedHashMap.get(numValueOf);
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                                linkedHashMap.put(numValueOf, arrayList);
                            }
                            ((List) arrayList).add(persistentFlow2.getTypeStatement(dataFlowVariable));
                        }
                        Integer num2 = (Integer) persistentFlow.getAssignmentIndex$org_jetbrains_kotlin_semantics().get(dataFlowVariable);
                        linkedHashMap.remove(Integer.valueOf(num2 != null ? num2.intValue() : -1));
                        Collection collectionValues = linkedHashMap.values();
                        LinkedHashSet linkedHashSet3 = new LinkedHashSet();
                        Iterator it4 = collectionValues.iterator();
                        while (true) {
                            if (it4.hasNext()) {
                                TypeStatement typeStatementAnd = and(CollectionsKt.filterNotNull((List) it4.next()));
                                if (typeStatementAnd != null) {
                                    linkedHashSet3.add(typeStatementAnd);
                                }
                            } else {
                                typeStatementOr = or(linkedHashSet3);
                            }
                        }
                    }
                    if (typeStatementOr == null) {
                    }
                } else {
                    LinkedHashSet linkedHashSet4 = new LinkedHashSet();
                    Iterator<T> it5 = collection2.iterator();
                    while (true) {
                        if (it5.hasNext()) {
                            TypeStatement typeStatement2 = ((PersistentFlow) it5.next()).getTypeStatement(dataFlowVariable);
                            if (typeStatement2 != null) {
                                linkedHashSet4.add(typeStatement2);
                            }
                        } else {
                            typeStatementOr = or(linkedHashSet4);
                            if (typeStatementOr == null && typeStatementOr.isNotEmpty()) {
                                mutableFlow.getApprovedTypeStatements$org_jetbrains_kotlin_semantics().put(dataFlowVariable, LogicSystemKt.toPersistent(typeStatementOr));
                            }
                        }
                    }
                }
            }
        }
    }

    private final Set<DfaType.Symbol> getCommonExcludedValues(Collection<? extends TypeStatement> collection) {
        final ArrayList arrayList = new ArrayList();
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            Set<DfaType> lowerTypes = ((TypeStatement) it.next()).getLowerTypes();
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : lowerTypes) {
                if (obj instanceof DfaType.Symbol) {
                    arrayList2.add(obj);
                }
            }
            CollectionsKt.addAll(arrayList, arrayList2);
        }
        Map mapEachCount = GroupingKt.eachCount(new Grouping<DfaType.Symbol, DfaType.Symbol>() { // from class: org.jetbrains.kotlin.fir.resolve.dfa.LogicSystem$getCommonExcludedValues$$inlined$groupingBy$1
            public DfaType.Symbol keyOf(DfaType.Symbol element) {
                return element;
            }

            public Iterator<DfaType.Symbol> sourceIterator() {
                return arrayList.iterator();
            }
        });
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : mapEachCount.entrySet()) {
            if (((Number) entry.getValue()).intValue() == collection.size()) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return linkedHashMap.keySet();
    }

    private final ConeKotlinType getIntersectedLowerType(Collection<? extends TypeStatement> collection) {
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = collection.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Set<DfaType> lowerTypes = ((TypeStatement) it.next()).getLowerTypes();
            ArrayList arrayList2 = new ArrayList();
            for (DfaType dfaType : lowerTypes) {
                DfaType.Cone cone = dfaType instanceof DfaType.Cone ? (DfaType.Cone) dfaType : null;
                ConeKotlinType type = cone != null ? cone.getType() : null;
                if (type != null) {
                    arrayList2.add(type);
                }
            }
            List listListOf = arrayList2.isEmpty() ? null : arrayList2;
            if (listListOf == null) {
                listListOf = CollectionsKt.listOf(this.context.m648nothingType());
            }
            CollectionsKt.addAll(arrayList, listListOf);
        }
        ConeKotlinType coneKotlinTypeIntersectTypes = ConeTypeIntersector.INSTANCE.intersectTypes(this.context, arrayList);
        if (ConeBuiltinTypeUtilsKt.isNothing(coneKotlinTypeIntersectTypes)) {
            return null;
        }
        return coneKotlinTypeIntersectTypes;
    }

    private final ConeKotlinType getUnifiedUpperType(Collection<? extends TypeStatement> collection) {
        ConeKotlinType coneKotlinTypeMo649nullableAnyType;
        List list;
        Collection<? extends TypeStatement> collection2 = collection;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection2, 10));
        Iterator<T> it = collection2.iterator();
        while (it.hasNext()) {
            Set<ConeKotlinType> upperTypesOrNull = ((TypeStatement) it.next()).getUpperTypesOrNull();
            if (upperTypesOrNull == null || (list = CollectionsKt.toList(upperTypesOrNull)) == null || (coneKotlinTypeMo649nullableAnyType = ConeTypeIntersector.INSTANCE.intersectTypes(this.context, list)) == null) {
                coneKotlinTypeMo649nullableAnyType = this.context.mo649nullableAnyType();
            }
            arrayList.add(coneKotlinTypeMo649nullableAnyType);
        }
        return TypeUtilsKt.commonSuperTypeOrNull(this.context, arrayList);
    }

    private final void mergeAssignments(MutableFlow mutableFlow, Collection<PersistentFlow> collection) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<PersistentFlow> it = collection.iterator();
        while (it.hasNext()) {
            for (Map.Entry entry : it.next().getAssignmentIndex$org_jetbrains_kotlin_semantics().entrySet()) {
                RealVariable realVariable = (RealVariable) entry.getKey();
                int iIntValue = ((Number) entry.getValue()).intValue();
                Integer num = (Integer) mutableFlow.getAssignmentIndex$org_jetbrains_kotlin_semantics().get(realVariable);
                if (num == null || num.intValue() != iIntValue) {
                    Integer num2 = (Integer) linkedHashMap.get(realVariable);
                    linkedHashMap.put(realVariable, Integer.valueOf(Math.max(iIntValue, num2 != null ? num2.intValue() : 0)));
                }
            }
        }
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            recordNewAssignment(mutableFlow, (RealVariable) entry2.getKey(), ((Number) entry2.getValue()).intValue());
        }
    }

    private final void plusAssign(MutableTypeStatement mutableTypeStatement, TypeStatement typeStatement) {
        mutableTypeStatement.getClass();
        CollectionsKt.addAll(mutableTypeStatement.getUpperTypes(), typeStatement.getUpperTypes());
        CollectionsKt.addAll(mutableTypeStatement.getLowerTypes(), typeStatement.getLowerTypes());
    }

    private final void replaceVariable(final MutableFlow mutableFlow, RealVariable realVariable, RealVariable realVariable2) {
        PersistentSet persistentSetAddAll;
        RealVariable realVariable3 = (RealVariable) mutableFlow.getDirectAliasMap$org_jetbrains_kotlin_semantics().remove(realVariable);
        if (realVariable3 != null) {
            if (AbstractTypeChecker.RUN_SLOW_ASSERTIONS) {
                mutableFlow.getBackwardsAliasMap$org_jetbrains_kotlin_semantics().containsKey(realVariable);
                mutableFlow.getImplications$org_jetbrains_kotlin_semantics().containsKey(realVariable);
                mutableFlow.getApprovedTypeStatements$org_jetbrains_kotlin_semantics().containsKey(realVariable);
            }
            PersistentSet persistentSet = (PersistentSet) MapsKt.getValue(mutableFlow.getBackwardsAliasMap$org_jetbrains_kotlin_semantics(), realVariable3);
            if (persistentSet.size() > 1) {
                mutableFlow.getBackwardsAliasMap$org_jetbrains_kotlin_semantics().put(realVariable3, persistentSet.remove(realVariable));
            } else {
                mutableFlow.getBackwardsAliasMap$org_jetbrains_kotlin_semantics().remove(realVariable3);
            }
            if (realVariable2 != null) {
                addLocalVariableAlias(mutableFlow, realVariable2, realVariable3);
                return;
            }
            return;
        }
        PersistentSet persistentSet2 = (PersistentSet) mutableFlow.getBackwardsAliasMap$org_jetbrains_kotlin_semantics().remove(realVariable);
        if (realVariable2 == null) {
            realVariable2 = persistentSet2 != null ? (RealVariable) CollectionsKt.first(persistentSet2) : null;
        }
        getVariableStorage().replaceReceiverReferencesInMembers(realVariable, realVariable2, new Function2() { // from class: pg9
            public final Object invoke(Object obj, Object obj2) {
                return LogicSystem.a(this.b, mutableFlow, (RealVariable) obj, (RealVariable) obj2);
            }
        });
        LogicSystemKt.replaceVariableInImplications(mutableFlow.getImplications$org_jetbrains_kotlin_semantics(), realVariable, realVariable2);
        LogicSystemKt.replaceVariableInStatements(mutableFlow.getApprovedTypeStatements$org_jetbrains_kotlin_semantics(), realVariable, realVariable2);
        if (persistentSet2 == null || realVariable2 == null) {
            return;
        }
        mutableFlow.getDirectAliasMap$org_jetbrains_kotlin_semantics().remove(realVariable2);
        PersistentSet persistentSetRemove = persistentSet2.remove(realVariable2);
        if (persistentSetRemove.isEmpty()) {
            return;
        }
        PersistentMap.Builder<RealVariable, RealVariable> directAliasMap$org_jetbrains_kotlin_semantics = mutableFlow.getDirectAliasMap$org_jetbrains_kotlin_semantics();
        for (Object obj : persistentSetRemove) {
            directAliasMap$org_jetbrains_kotlin_semantics.put(obj, realVariable2);
        }
        PersistentMap.Builder<RealVariable, PersistentSet<RealVariable>> backwardsAliasMap$org_jetbrains_kotlin_semantics = mutableFlow.getBackwardsAliasMap$org_jetbrains_kotlin_semantics();
        PersistentSet persistentSet3 = (PersistentSet) mutableFlow.getBackwardsAliasMap$org_jetbrains_kotlin_semantics().get(realVariable2);
        if (persistentSet3 != null && (persistentSetAddAll = persistentSet3.addAll(persistentSetRemove)) != null) {
            persistentSetRemove = persistentSetAddAll;
        }
        backwardsAliasMap$org_jetbrains_kotlin_semantics.put(realVariable2, persistentSetRemove);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void translateVariableFromConditionInStatements$default(LogicSystem logicSystem, MutableFlow mutableFlow, DataFlowVariable dataFlowVariable, DataFlowVariable dataFlowVariable2, Function1 function1, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: translateVariableFromConditionInStatements");
            return;
        }
        if ((i & 8) != 0) {
            function1 = new Function1() { // from class: og9
                public final Object invoke(Object obj2) {
                    return LogicSystem.b((Implication) obj2);
                }
            };
        }
        logicSystem.translateVariableFromConditionInStatements(mutableFlow, dataFlowVariable, dataFlowVariable2, function1);
    }

    public final void addImplication(MutableFlow flow, Implication implication) {
        PersistentList persistentListPersistentListOf;
        flow.getClass();
        implication.getClass();
        Statement effect = implication.getEffect();
        if (Intrinsics.areEqual(effect, implication.getCondition())) {
            return;
        }
        if (effect instanceof TypeStatement) {
            TypeStatement typeStatement = (TypeStatement) effect;
            if (typeStatement.isEmpty() || containsAlready(flow, typeStatement)) {
                return;
            }
        } else if ((effect.getVariable() instanceof SyntheticVariable) && !flow.getImplications$org_jetbrains_kotlin_semantics().containsKey(effect.getVariable())) {
            return;
        }
        DataFlowVariable variable = implication.getCondition().getVariable();
        PersistentMap.Builder<DataFlowVariable, PersistentList<Implication>> implications$org_jetbrains_kotlin_semantics = flow.getImplications$org_jetbrains_kotlin_semantics();
        PersistentList persistentList = (PersistentList) flow.getImplications$org_jetbrains_kotlin_semantics().get(variable);
        if (persistentList == null || (persistentListPersistentListOf = persistentList.add(implication)) == null) {
            persistentListPersistentListOf = ExtensionsKt.persistentListOf(new Implication[]{implication});
        }
        implications$org_jetbrains_kotlin_semantics.put(variable, persistentListPersistentListOf);
    }

    public final void addLocalVariableAlias(MutableFlow flow, RealVariable alias, RealVariable underlyingVariable) {
        PersistentSet persistentSetPersistentSetOf;
        flow.getClass();
        alias.getClass();
        underlyingVariable.getClass();
        if (Intrinsics.areEqual(underlyingVariable, alias)) {
            return;
        }
        flow.getDirectAliasMap$org_jetbrains_kotlin_semantics().put(alias, underlyingVariable);
        PersistentMap.Builder<RealVariable, PersistentSet<RealVariable>> backwardsAliasMap$org_jetbrains_kotlin_semantics = flow.getBackwardsAliasMap$org_jetbrains_kotlin_semantics();
        PersistentSet persistentSet = (PersistentSet) flow.getBackwardsAliasMap$org_jetbrains_kotlin_semantics().get(underlyingVariable);
        if (persistentSet == null || (persistentSetPersistentSetOf = persistentSet.add(alias)) == null) {
            persistentSetPersistentSetOf = ExtensionsKt.persistentSetOf(new RealVariable[]{alias});
        }
        backwardsAliasMap$org_jetbrains_kotlin_semantics.put(underlyingVariable, persistentSetPersistentSetOf);
    }

    public final TypeStatement addTypeStatement(MutableFlow flow, TypeStatement statement) {
        PersistentSet<ConeKotlinType> persistentSet;
        PersistentSet<DfaType> persistentSet2;
        flow.getClass();
        statement.getClass();
        if (statement.isEmpty()) {
            return null;
        }
        DataFlowVariable variable = statement.getVariable();
        PersistentTypeStatement persistentTypeStatement = (PersistentTypeStatement) flow.getApprovedTypeStatements$org_jetbrains_kotlin_semantics().get(variable);
        PersistentSet<ConeKotlinType> upperTypes = persistentTypeStatement != null ? persistentTypeStatement.getUpperTypes() : null;
        PersistentSet<DfaType> lowerTypes = persistentTypeStatement != null ? persistentTypeStatement.getLowerTypes() : null;
        if (upperTypes == null || (persistentSet = upperTypes.addAll(statement.getUpperTypes())) == null) {
            persistentSet = ExtensionsKt.toPersistentSet(statement.getUpperTypes());
        }
        if (lowerTypes == null || (persistentSet2 = lowerTypes.addAll(statement.getLowerTypes())) == null) {
            persistentSet2 = ExtensionsKt.toPersistentSet(statement.getLowerTypes());
        }
        if (persistentSet == upperTypes && persistentSet2 == lowerTypes) {
            return null;
        }
        PersistentTypeStatement persistentTypeStatement2 = new PersistentTypeStatement(variable, persistentSet, persistentSet2);
        flow.getApprovedTypeStatements$org_jetbrains_kotlin_semantics().put(variable, persistentTypeStatement2);
        return persistentTypeStatement2;
    }

    public final List<TypeStatement> addTypeStatements(MutableFlow flow, Map<DataFlowVariable, ? extends TypeStatement> statements) {
        flow.getClass();
        statements.getClass();
        Collection<? extends TypeStatement> collectionValues = statements.values();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = collectionValues.iterator();
        while (it.hasNext()) {
            TypeStatement typeStatementAddTypeStatement = addTypeStatement(flow, (TypeStatement) it.next());
            if (typeStatementAddTypeStatement != null) {
                arrayList.add(typeStatementAddTypeStatement);
            }
        }
        return arrayList;
    }

    public final TypeStatement and(Collection<? extends TypeStatement> statements) {
        statements.getClass();
        int size = statements.size();
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            return (TypeStatement) CollectionsKt.first(statements);
        }
        Iterator<? extends TypeStatement> it = statements.iterator();
        MutableTypeStatement mutable = LogicSystemKt.toMutable(it.next());
        while (it.hasNext()) {
            plusAssign(mutable, it.next());
        }
        return mutable;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Map<DataFlowVariable, TypeStatement> andForTypeStatements(Map<DataFlowVariable, ? extends TypeStatement> left, Map<DataFlowVariable, ? extends TypeStatement> right) {
        left.getClass();
        right.getClass();
        if (left.isEmpty()) {
            return right;
        }
        if (right.isEmpty()) {
            return left;
        }
        Map<DataFlowVariable, TypeStatement> mutableMap = MapsKt.toMutableMap(left);
        for (Map.Entry entry : right.entrySet()) {
            DataFlowVariable dataFlowVariable = (DataFlowVariable) entry.getKey();
            mutableMap.put(dataFlowVariable, and(mutableMap.get(dataFlowVariable), (TypeStatement) entry.getValue()));
        }
        return mutableMap;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x00ba  */
    public final boolean approveTypeStatement(Flow flow, TypeStatement statement) {
        Map mapMapOf;
        KotlinTypeMarker kotlinTypeMarker;
        flow.getClass();
        statement.getClass();
        DataFlowVariable variable = statement.getVariable();
        ConeKotlinType coneTypeOrNull = variable instanceof SyntheticVariable ? ((SyntheticVariable) variable).getFir().getConeTypeOrNull() : variable.getOriginalType();
        TypeStatement typeStatement = flow.getTypeStatement(variable);
        if (typeStatement != null && coneTypeOrNull != null) {
            MutableTypeStatement mutable = LogicSystemKt.toMutable(typeStatement);
            mutable.getUpperTypes().add(coneTypeOrNull);
            mapMapOf = MapsKt.mapOf(TuplesKt.to(variable, mutable));
        } else if (typeStatement != null && coneTypeOrNull == null) {
            mapMapOf = MapsKt.mapOf(TuplesKt.to(variable, typeStatement));
        } else {
            if (typeStatement != null || coneTypeOrNull == null) {
                return false;
            }
            mapMapOf = MapsKt.mapOf(TuplesKt.to(variable, new MutableTypeStatement(variable, SetsKt.mutableSetOf(new ConeKotlinType[]{coneTypeOrNull}), null, 4, null)));
        }
        ConeKotlinType unifiedUpperType = getUnifiedUpperType(mapMapOf.values());
        ConeKotlinType intersectedLowerType = getIntersectedLowerType(mapMapOf.values());
        KotlinTypeMarker unifiedUpperType2 = getUnifiedUpperType(CollectionsKt.listOf(statement));
        ConeKotlinType intersectedLowerType2 = getIntersectedLowerType(CollectionsKt.listOf(statement));
        if (unifiedUpperType != null && unifiedUpperType2 != null) {
            kotlinTypeMarker = unifiedUpperType2;
            if (AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, this.context, unifiedUpperType, kotlinTypeMarker, false, 8, (Object) null)) {
            }
            return false;
        }
        kotlinTypeMarker = unifiedUpperType2;
        if (intersectedLowerType != null && kotlinTypeMarker != null) {
            if (!AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, this.context, kotlinTypeMarker, intersectedLowerType, false, 8, (Object) null)) {
                return intersectedLowerType2 != null ? true : true;
            }
        } else if (intersectedLowerType2 != null || unifiedUpperType == null || !AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, this.context, unifiedUpperType, intersectedLowerType2, false, 8, (Object) null)) {
            return true;
        }
        return false;
    }

    public final FirSession getSession() {
        return this.context.getSession();
    }

    public abstract VariableStorage getVariableStorage();

    public boolean isAcceptableForSmartcast(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return !ConeBuiltinTypeUtilsKt.isNullableNothing(coneKotlinType);
    }

    public final boolean isSameValueIn(PersistentFlow a, PersistentFlow b, RealVariable variable) {
        a.getClass();
        b.getClass();
        variable.getClass();
        return Intrinsics.areEqual(a.getAssignmentIndex$org_jetbrains_kotlin_semantics().get(variable), b.getAssignmentIndex$org_jetbrains_kotlin_semantics().get(variable));
    }

    public final MutableFlow joinFlow(Collection<PersistentFlow> flows, Collection<PersistentFlow> statementFlows, boolean union) {
        flows.getClass();
        statementFlows.getClass();
        int size = flows.size();
        if (size == 0) {
            return new MutableFlow();
        }
        if (size == 1) {
            return ((PersistentFlow) CollectionsKt.first(flows)).fork();
        }
        Iterator<T> it = flows.iterator();
        if (!it.hasNext()) {
            c41.a("Empty collection can't be reduced.");
            return null;
        }
        Object next = it.next();
        while (it.hasNext()) {
            PersistentFlow persistentFlow = (PersistentFlow) it.next();
            PersistentFlow persistentFlow2 = (PersistentFlow) next;
            PersistentFlow persistentFlowLowestCommonAncestor = persistentFlow2.lowestCommonAncestor(persistentFlow);
            if (persistentFlowLowestCommonAncestor == null) {
                a11.a("no common ancestor in ", persistentFlow2, ", ", persistentFlow);
                return null;
            }
            next = persistentFlowLowestCommonAncestor;
        }
        PersistentFlow persistentFlow3 = (PersistentFlow) next;
        MutableFlow mutableFlowFork = persistentFlow3.fork();
        mergeAssignments(mutableFlowFork, flows);
        if (union) {
            copyNonConflictingAliases(mutableFlowFork, flows, persistentFlow3);
        } else {
            copyCommonAliases(mutableFlowFork, flows);
        }
        copyStatements(mutableFlowFork, statementFlows, persistentFlow3, union);
        copyImplications(mutableFlowFork, statementFlows);
        return mutableFlowFork;
    }

    public final TypeStatement or(Collection<? extends TypeStatement> statements) {
        PersistentSet persistentSetPersistentSetOf;
        statements.getClass();
        int size = statements.size();
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            return (TypeStatement) CollectionsKt.first(statements);
        }
        Collection<? extends TypeStatement> collection = statements;
        DataFlowVariable variable = ((TypeStatement) CollectionsKt.first(collection)).getVariable();
        Collection<? extends TypeStatement> collection2 = collection;
        if (!collection2.isEmpty()) {
            Iterator<T> it = collection.iterator();
            while (it.hasNext() && Intrinsics.areEqual(((TypeStatement) it.next()).getVariable(), variable)) {
            }
        }
        if (!collection2.isEmpty()) {
            Iterator<T> it2 = collection.iterator();
            while (it2.hasNext()) {
                if (((TypeStatement) it2.next()).isEmpty()) {
                    return null;
                }
            }
        }
        ConeKotlinType unifiedUpperType = getUnifiedUpperType(statements);
        if (unifiedUpperType == null || ConeBuiltinTypeUtilsKt.isNullableAny(unifiedUpperType)) {
            persistentSetPersistentSetOf = ExtensionsKt.persistentSetOf();
        } else if (isAcceptableForSmartcast(unifiedUpperType)) {
            persistentSetPersistentSetOf = ExtensionsKt.persistentSetOf(new ConeKotlinType[]{unifiedUpperType});
        } else {
            persistentSetPersistentSetOf = TypeUtilsKt.canBeNull$default(unifiedUpperType, this.context.getSession(), false, null, 6, null) ? ExtensionsKt.persistentSetOf() : ExtensionsKt.persistentSetOf(new ConeClassLikeType[]{this.context.m621anyType()});
        }
        ConeKotlinType intersectedLowerType = getIntersectedLowerType(statements);
        Set setPlus = SetsKt.plus(SetsKt.setOfNotNull(intersectedLowerType != null ? new DfaType.Cone(intersectedLowerType) : null), getCommonExcludedValues(statements));
        if (persistentSetPersistentSetOf.isEmpty() && setPlus.isEmpty()) {
            return null;
        }
        return new PersistentTypeStatement(variable, persistentSetPersistentSetOf, ExtensionsKt.toPersistentSet(setPlus));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Map<DataFlowVariable, TypeStatement> orForTypeStatements(Map<DataFlowVariable, ? extends TypeStatement> left, Map<DataFlowVariable, ? extends TypeStatement> right) {
        TypeStatement typeStatementOr;
        left.getClass();
        right.getClass();
        if (left.isEmpty()) {
            return left;
        }
        if (right.isEmpty()) {
            return right;
        }
        Map mapCreateMapBuilder = MapsKt.createMapBuilder();
        for (Map.Entry entry : left.entrySet()) {
            DataFlowVariable dataFlowVariable = (DataFlowVariable) entry.getKey();
            TypeStatement typeStatement = (TypeStatement) entry.getValue();
            TypeStatement typeStatement2 = (TypeStatement) right.get(dataFlowVariable);
            if (typeStatement2 != null && (typeStatementOr = or(CollectionsKt.listOf(new TypeStatement[]{typeStatement, typeStatement2}))) != null) {
                mapCreateMapBuilder.put(dataFlowVariable, typeStatementOr);
            }
        }
        return MapsKt.build(mapCreateMapBuilder);
    }

    public final void recordNewAssignment(MutableFlow flow, RealVariable variable, int index) {
        flow.getClass();
        variable.getClass();
        replaceVariable(flow, variable, null);
        flow.getAssignmentIndex$org_jetbrains_kotlin_semantics().put(variable, Integer.valueOf(index));
    }

    public final void translateVariableFromConditionInStatements(MutableFlow flow, DataFlowVariable originalVariable, DataFlowVariable newVariable, Function1<? super Implication, Implication> transform) {
        flow.getClass();
        originalVariable.getClass();
        newVariable.getClass();
        transform.getClass();
        PersistentList<Implication> persistentList = ModelKt.isSynthetic(originalVariable) ? (PersistentList) flow.getImplications$org_jetbrains_kotlin_semantics().remove(originalVariable) : (PersistentList) flow.getImplications$org_jetbrains_kotlin_semantics().get(originalVariable);
        if (persistentList == null || persistentList.isEmpty()) {
            return;
        }
        PersistentList persistentListPersistentListOf = (PersistentList) flow.getImplications$org_jetbrains_kotlin_semantics().get(newVariable);
        if (persistentListPersistentListOf == null) {
            persistentListPersistentListOf = ExtensionsKt.persistentListOf();
        }
        PersistentMap.Builder<DataFlowVariable, PersistentList<Implication>> implications$org_jetbrains_kotlin_semantics = flow.getImplications$org_jetbrains_kotlin_semantics();
        PersistentList.Builder builder = persistentListPersistentListOf.builder();
        for (Implication implication : persistentList) {
            Implication implication2 = (Implication) transform.invoke(ModelKt.implies(new OperationStatement(newVariable, implication.getCondition().getOperation()), implication.getEffect()));
            if (implication2 != null) {
                builder.add(implication2);
            }
        }
        implications$org_jetbrains_kotlin_semantics.put(newVariable, builder.build());
    }

    public final boolean isSameValueIn(PersistentFlow a, MutableFlow b, RealVariable variable) {
        a.getClass();
        b.getClass();
        variable.getClass();
        return Intrinsics.areEqual(a.getAssignmentIndex$org_jetbrains_kotlin_semantics().get(variable), b.getAssignmentIndex$org_jetbrains_kotlin_semantics().get(variable));
    }

    public final TypeStatement and(TypeStatement a, TypeStatement b) {
        MutableTypeStatement mutable;
        b.getClass();
        if (a == null || (mutable = LogicSystemKt.toMutable(a)) == null) {
            return b;
        }
        plusAssign(mutable, b);
        return mutable;
    }

    public final Map<DataFlowVariable, TypeStatement> approveOperationStatement(MutableFlow flow, OperationStatement statement, boolean removeApprovedOrImpossible) {
        flow.getClass();
        statement.getClass();
        return approveOperationStatement((Map<DataFlowVariable, ? extends PersistentList<Implication>>) flow.getImplications$org_jetbrains_kotlin_semantics(), statement, removeApprovedOrImpossible);
    }

    public final Map<DataFlowVariable, TypeStatement> approveOperationStatement(PersistentFlow flow, OperationStatement statement) {
        flow.getClass();
        statement.getClass();
        return approveOperationStatement(MapsKt.toMutableMap(flow.getImplications$org_jetbrains_kotlin_semantics()), statement, false);
    }
}

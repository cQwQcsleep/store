package org.jetbrains.kotlin.fir.resolve.dfa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentList;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.dfa.Implication;
import org.jetbrains.kotlin.fir.resolve.dfa.LogicSystemKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0002H\u0002\u001a=\u0010\u0005\u001a\u00020\u0006*\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u00072\u0006\u0010\t\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\bH\u0003b\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000b¢\u0006\u0002\b\u000b\u001aC\u0010\u0005\u001a\u00020\u0006*\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u00072\u0006\u0010\t\u001a\u00020\u00102\b\u0010\n\u001a\u0004\u0018\u00010\u0010H\u0003b\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0011¢\u0006\u0002\b\u0011\u001a3\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00130\u000e\"\u0004\b\u0000\u0010\u0013*\b\u0012\u0004\u0012\u0002H\u00130\u000e2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u00130\u0015H\u0082\b\u001a\u001c\u0010\u0005\u001a\u00020\u000f*\u00020\u000f2\u0006\u0010\t\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0002\u001a\u001c\u0010\u0005\u001a\u00020\u0016*\u00020\u00162\u0006\u0010\t\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0002¨\u0006\u0017"}, d2 = {"toPersistent", "Lorg/jetbrains/kotlin/fir/resolve/dfa/PersistentTypeStatement;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/TypeStatement;", "toMutable", "Lorg/jetbrains/kotlin/fir/resolve/dfa/MutableTypeStatement;", "replaceVariable", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowVariable;", "from", "to", "replaceVariableInStatements", "Lkotlin/jvm/JvmName;", ModuleXmlParser.NAME, "Lkotlinx/collections/immutable/PersistentList;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/Implication;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/RealVariable;", "replaceVariableInImplications", "replaceAll", "T", "block", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/Statement;", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class LogicSystemKt {
    private static final Implication replaceVariable(Implication implication, RealVariable realVariable, RealVariable realVariable2) {
        if (Intrinsics.areEqual(implication.getCondition().getVariable(), realVariable)) {
            return new Implication(OperationStatement.copy$default(implication.getCondition(), realVariable2, null, 2, null), replaceVariable(implication.getEffect(), realVariable, realVariable2));
        }
        return Intrinsics.areEqual(implication.getEffect().getVariable(), realVariable) ? new Implication(implication.getCondition(), replaceVariable(implication.getEffect(), realVariable, realVariable2)) : implication;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean replaceVariable$lambda$0$1(RealVariable realVariable, Implication implication) {
        implication.getClass();
        return Intrinsics.areEqual(implication.getEffect().getVariable(), realVariable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void replaceVariableInImplications(Map<DataFlowVariable, PersistentList<Implication>> map, final RealVariable realVariable, RealVariable realVariable2) {
        PersistentList persistentListRemoveAll;
        PersistentList<Implication> persistentListRemove = map.remove(realVariable);
        Set<Map.Entry<DataFlowVariable, PersistentList<Implication>>> setEntrySet = map.entrySet();
        ArrayList<Pair> arrayList = new ArrayList();
        Iterator<T> it = setEntrySet.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            DataFlowVariable dataFlowVariable = (DataFlowVariable) entry.getKey();
            PersistentList persistentList = (PersistentList) entry.getValue();
            if (realVariable2 != null) {
                PersistentList.Builder builder = persistentList.builder();
                ListIterator listIterator = builder.listIterator();
                while (listIterator.hasNext()) {
                    listIterator.set(replaceVariable((Implication) listIterator.next(), realVariable, realVariable2));
                }
                persistentListRemoveAll = builder.build();
            } else {
                persistentListRemoveAll = persistentList.removeAll(new Function1() { // from class: rg9
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(LogicSystemKt.replaceVariable$lambda$0$1(realVariable, (Implication) obj));
                    }
                });
            }
            Pair pair = !Intrinsics.areEqual(persistentListRemoveAll, persistentList) ? TuplesKt.to(dataFlowVariable, persistentListRemoveAll) : null;
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        for (Pair pair2 : arrayList) {
            DataFlowVariable dataFlowVariable2 = (DataFlowVariable) pair2.component1();
            PersistentList<Implication> persistentList2 = (PersistentList) pair2.component2();
            if (persistentList2.isEmpty()) {
                map.remove(dataFlowVariable2);
            } else {
                map.put(dataFlowVariable2, persistentList2);
            }
        }
        if (persistentListRemove == null || realVariable2 == null) {
            return;
        }
        PersistentList.Builder builder2 = persistentListRemove.builder();
        ListIterator listIterator2 = builder2.listIterator();
        while (listIterator2.hasNext()) {
            listIterator2.set(replaceVariable((Implication) listIterator2.next(), realVariable, realVariable2));
        }
        map.put(realVariable2, builder2.build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void replaceVariableInStatements(Map<DataFlowVariable, PersistentTypeStatement> map, DataFlowVariable dataFlowVariable, DataFlowVariable dataFlowVariable2) {
        PersistentTypeStatement persistentTypeStatementRemove = map.remove(dataFlowVariable);
        if (persistentTypeStatementRemove == null || dataFlowVariable2 == null) {
            return;
        }
        map.put(dataFlowVariable2, PersistentTypeStatement.copy$default(persistentTypeStatementRemove, dataFlowVariable2, null, null, 6, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableTypeStatement toMutable(TypeStatement typeStatement) {
        if (!(typeStatement instanceof PersistentTypeStatement)) {
            return new MutableTypeStatement(typeStatement.getVariable(), new LinkedHashSet(typeStatement.getUpperTypes()), new LinkedHashSet(typeStatement.getLowerTypes()));
        }
        PersistentTypeStatement persistentTypeStatement = (PersistentTypeStatement) typeStatement;
        return new MutableTypeStatement(persistentTypeStatement.getVariable(), persistentTypeStatement.getUpperTypes().builder(), persistentTypeStatement.getLowerTypes().builder());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PersistentTypeStatement toPersistent(TypeStatement typeStatement) {
        return typeStatement instanceof PersistentTypeStatement ? (PersistentTypeStatement) typeStatement : new PersistentTypeStatement(typeStatement.getVariable(), ExtensionsKt.toPersistentSet(typeStatement.getUpperTypes()), ExtensionsKt.toPersistentSet(typeStatement.getLowerTypes()));
    }

    private static final Statement replaceVariable(Statement statement, RealVariable realVariable, RealVariable realVariable2) {
        if (!Intrinsics.areEqual(statement.getVariable(), realVariable)) {
            return statement;
        }
        if (statement instanceof OperationStatement) {
            return OperationStatement.copy$default((OperationStatement) statement, realVariable2, null, 2, null);
        }
        if (statement instanceof PersistentTypeStatement) {
            return PersistentTypeStatement.copy$default((PersistentTypeStatement) statement, realVariable2, null, null, 6, null);
        }
        if (statement instanceof MutableTypeStatement) {
            MutableTypeStatement mutableTypeStatement = (MutableTypeStatement) statement;
            return new MutableTypeStatement(realVariable2, mutableTypeStatement.getUpperTypes(), mutableTypeStatement.getLowerTypes());
        }
        bu8.a();
        return null;
    }
}

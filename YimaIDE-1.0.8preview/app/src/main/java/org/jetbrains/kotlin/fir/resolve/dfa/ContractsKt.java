package org.jetbrains.kotlin.fir.resolve.dfa;

import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.KtBinaryLogicExpression;
import org.jetbrains.kotlin.contracts.description.KtBooleanConstantReference;
import org.jetbrains.kotlin.contracts.description.KtBooleanExpression;
import org.jetbrains.kotlin.contracts.description.KtBooleanValueParameterReference;
import org.jetbrains.kotlin.contracts.description.KtConstantReference;
import org.jetbrains.kotlin.contracts.description.KtIsInstancePredicate;
import org.jetbrains.kotlin.contracts.description.KtIsNullPredicate;
import org.jetbrains.kotlin.contracts.description.KtLogicalNot;
import org.jetbrains.kotlin.contracts.description.LogicOperationKind;
import org.jetbrains.kotlin.fir.contracts.description.ConeContractConstantValues;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002j\u0002`\u0005\u001a\u0085\u0001\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007j\u0004\u0018\u0001`\n*\u00020\u000b2\u0016\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\rj\u0002`\u000e2\u0010\u0010\u000f\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\b0\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\"\u0010\u0015\u001a\u001e\u0012\u0004\u0012\u00020\u0017\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007j\u0002`\n0\u0016¢\u0006\u0002\u0010\u0018¨\u0006\u0019"}, d2 = {"toOperation", "Lorg/jetbrains/kotlin/fir/resolve/dfa/Operation;", "Lorg/jetbrains/kotlin/contracts/description/KtConstantReference;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "Lorg/jetbrains/kotlin/fir/contracts/description/ConeConstantReference;", "approveContractStatement", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowVariable;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/TypeStatement;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/TypeStatements;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/LogicSystem;", "statement", "Lorg/jetbrains/kotlin/contracts/description/KtBooleanExpression;", "Lorg/jetbrains/kotlin/fir/contracts/description/ConeBooleanExpression;", "arguments", Argument.Delimiters.none, "substitutor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "typesOnlyFromRealVars", Argument.Delimiters.none, "approveOperationStatement", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/OperationStatement;", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/LogicSystem;Lorg/jetbrains/kotlin/contracts/description/KtBooleanExpression;[Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowVariable;Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;ZLkotlin/jvm/functions/Function1;)Ljava/util/Map;", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ContractsKt {
    public static final Map<DataFlowVariable, TypeStatement> approveContractStatement(LogicSystem logicSystem, KtBooleanExpression<ConeKotlinType, ConeDiagnostic> ktBooleanExpression, DataFlowVariable[] dataFlowVariableArr, ConeSubstitutor coneSubstitutor, boolean z, Function1<? super OperationStatement, ? extends Map<DataFlowVariable, ? extends TypeStatement>> function1) {
        logicSystem.getClass();
        ktBooleanExpression.getClass();
        dataFlowVariableArr.getClass();
        function1.getClass();
        return approveContractStatement$visit(ktBooleanExpression, dataFlowVariableArr, logicSystem, coneSubstitutor, z, function1, false);
    }

    public static /* synthetic */ Map approveContractStatement$default(LogicSystem logicSystem, KtBooleanExpression ktBooleanExpression, DataFlowVariable[] dataFlowVariableArr, ConeSubstitutor coneSubstitutor, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 8) != 0) {
            z = true;
        }
        return approveContractStatement(logicSystem, ktBooleanExpression, dataFlowVariableArr, coneSubstitutor, z, function1);
    }

    private static final Map<DataFlowVariable, TypeStatement> approveContractStatement$processEqNull(DataFlowVariable dataFlowVariable, Function1<? super OperationStatement, ? extends Map<DataFlowVariable, ? extends TypeStatement>> function1, boolean z) {
        return (Map) function1.invoke(new OperationStatement(dataFlowVariable, z ? Operation.EqNull : Operation.NotEqNull));
    }

    private static final Map<DataFlowVariable, TypeStatement> approveContractStatement$visit(KtBooleanExpression<ConeKotlinType, ConeDiagnostic> ktBooleanExpression, DataFlowVariable[] dataFlowVariableArr, LogicSystem logicSystem, ConeSubstitutor coneSubstitutor, boolean z, Function1<? super OperationStatement, ? extends Map<DataFlowVariable, ? extends TypeStatement>> function1, boolean z2) {
        Map<DataFlowVariable, TypeStatement> map;
        ConeKotlinType coneKotlinTypeSubstituteOrNull;
        Map<DataFlowVariable, ? extends TypeStatement> mapMapOf;
        Map<DataFlowVariable, TypeStatement> mapAndForTypeStatements;
        if (ktBooleanExpression instanceof KtBooleanConstantReference) {
            if (z2 == Intrinsics.areEqual(ktBooleanExpression, ConeContractConstantValues.INSTANCE.getTRUE())) {
                return null;
            }
            return MapsKt.emptyMap();
        }
        if (ktBooleanExpression instanceof KtLogicalNot) {
            return approveContractStatement$visit(((KtLogicalNot) ktBooleanExpression).getArg(), dataFlowVariableArr, logicSystem, coneSubstitutor, z, function1, !z2);
        }
        if (ktBooleanExpression instanceof KtIsInstancePredicate) {
            KtIsInstancePredicate ktIsInstancePredicate = (KtIsInstancePredicate) ktBooleanExpression;
            DataFlowVariable dataFlowVariable = (DataFlowVariable) ArraysKt.getOrNull(dataFlowVariableArr, ktIsInstancePredicate.getArg().getParameterIndex() + 1);
            if (dataFlowVariable != null) {
                boolean z3 = z2 == ktIsInstancePredicate.getIsNegated();
                if (coneSubstitutor == null || (coneKotlinTypeSubstituteOrNull = coneSubstitutor.substituteOrNull((ConeKotlinType) ktIsInstancePredicate.getType())) == null) {
                    coneKotlinTypeSubstituteOrNull = (ConeKotlinType) ktIsInstancePredicate.getType();
                }
                if (ConeBuiltinTypeUtilsKt.isAny(coneKotlinTypeSubstituteOrNull)) {
                    mapAndForTypeStatements = approveContractStatement$processEqNull(dataFlowVariable, function1, !z3);
                } else if (ConeBuiltinTypeUtilsKt.isNullableNothing(coneKotlinTypeSubstituteOrNull)) {
                    mapAndForTypeStatements = approveContractStatement$processEqNull(dataFlowVariable, function1, z3);
                } else {
                    Map<DataFlowVariable, ? extends TypeStatement> mapEmptyMap = ((!z3 || TypeUtilsKt.canBeNull$default((ConeKotlinType) ktIsInstancePredicate.getType(), logicSystem.getSession(), false, null, 6, null)) && (z3 || !ConeTypeUtilsKt.isMarkedNullable((ConeKotlinType) ktIsInstancePredicate.getType()))) ? MapsKt.emptyMap() : approveContractStatement$processEqNull(dataFlowVariable, function1, false);
                    if (!z || (dataFlowVariable instanceof RealVariable)) {
                        mapMapOf = z3 ? MapsKt.mapOf(TuplesKt.to(dataFlowVariable, ModelKt.typeEq(dataFlowVariable, coneKotlinTypeSubstituteOrNull))) : MapsKt.mapOf(TuplesKt.to(dataFlowVariable, ModelKt.typeNotEq(dataFlowVariable, coneKotlinTypeSubstituteOrNull)));
                    } else {
                        mapMapOf = mapEmptyMap;
                    }
                    mapAndForTypeStatements = logicSystem.andForTypeStatements(mapEmptyMap, mapMapOf);
                }
                if (mapAndForTypeStatements != null) {
                    return mapAndForTypeStatements;
                }
            }
            return MapsKt.emptyMap();
        }
        if (ktBooleanExpression instanceof KtIsNullPredicate) {
            KtIsNullPredicate ktIsNullPredicate = (KtIsNullPredicate) ktBooleanExpression;
            DataFlowVariable dataFlowVariable2 = (DataFlowVariable) ArraysKt.getOrNull(dataFlowVariableArr, ktIsNullPredicate.getArg().getParameterIndex() + 1);
            if (dataFlowVariable2 != null) {
                Map<DataFlowVariable, TypeStatement> mapApproveContractStatement$processEqNull = approveContractStatement$processEqNull(dataFlowVariable2, function1, z2 == ktIsNullPredicate.getIsNegated());
                if (mapApproveContractStatement$processEqNull != null) {
                    return mapApproveContractStatement$processEqNull;
                }
            }
            return MapsKt.emptyMap();
        }
        if (ktBooleanExpression instanceof KtBooleanValueParameterReference) {
            DataFlowVariable dataFlowVariable3 = (DataFlowVariable) ArraysKt.getOrNull(dataFlowVariableArr, ((KtBooleanValueParameterReference) ktBooleanExpression).getParameterIndex() + 1);
            return (dataFlowVariable3 == null || (map = (Map) function1.invoke(ModelKt.eq(dataFlowVariable3, z2 ^ true))) == null) ? MapsKt.emptyMap() : map;
        }
        if (!(ktBooleanExpression instanceof KtBinaryLogicExpression)) {
            return MapsKt.emptyMap();
        }
        KtBinaryLogicExpression ktBinaryLogicExpression = (KtBinaryLogicExpression) ktBooleanExpression;
        Map<DataFlowVariable, TypeStatement> mapApproveContractStatement$visit = approveContractStatement$visit(ktBinaryLogicExpression.getLeft(), dataFlowVariableArr, logicSystem, coneSubstitutor, z, function1, z2);
        Map<DataFlowVariable, TypeStatement> mapApproveContractStatement$visit2 = approveContractStatement$visit(ktBinaryLogicExpression.getRight(), dataFlowVariableArr, logicSystem, coneSubstitutor, z, function1, z2);
        boolean z4 = z2 != (ktBinaryLogicExpression.getKind() == LogicOperationKind.AND);
        if (mapApproveContractStatement$visit == null) {
            if (z4) {
                return null;
            }
            return mapApproveContractStatement$visit2;
        }
        if (mapApproveContractStatement$visit2 != null) {
            return z4 ? logicSystem.andForTypeStatements(mapApproveContractStatement$visit, mapApproveContractStatement$visit2) : logicSystem.orForTypeStatements(mapApproveContractStatement$visit, mapApproveContractStatement$visit2);
        }
        if (z4) {
            return null;
        }
        return mapApproveContractStatement$visit;
    }

    public static final Operation toOperation(KtConstantReference<ConeKotlinType, ConeDiagnostic> ktConstantReference) {
        ktConstantReference.getClass();
        ConeContractConstantValues coneContractConstantValues = ConeContractConstantValues.INSTANCE;
        if (Intrinsics.areEqual(ktConstantReference, coneContractConstantValues.getWILDCARD())) {
            return null;
        }
        if (Intrinsics.areEqual(ktConstantReference, coneContractConstantValues.getNULL())) {
            return Operation.EqNull;
        }
        if (Intrinsics.areEqual(ktConstantReference, coneContractConstantValues.getNOT_NULL())) {
            return Operation.NotEqNull;
        }
        if (Intrinsics.areEqual(ktConstantReference, coneContractConstantValues.getTRUE())) {
            return Operation.EqTrue;
        }
        if (Intrinsics.areEqual(ktConstantReference, coneContractConstantValues.getFALSE())) {
            return Operation.EqFalse;
        }
        e4b.a(ktConstantReference, " can not be transformed to Operation");
        return null;
    }
}

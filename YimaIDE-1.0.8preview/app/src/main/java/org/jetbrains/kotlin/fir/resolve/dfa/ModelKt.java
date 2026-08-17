package org.jetbrains.kotlin.fir.resolve.dfa;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.DfaType;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.RefinedTypeForDataFlowTypeAttributeKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0004\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0086\u0004\u001a\u0017\u0010\u0004\u001a\u00020\u0005*\u00020\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\bH\u0086\u0004\u001a\u0017\u0010\t\u001a\u00020\u0005*\u00020\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\bH\u0086\u0004\u001a\u0015\u0010\n\u001a\u00020\u000b*\u00020\u00052\u0006\u0010\f\u001a\u00020\rH\u0086\u0004\u001a\u0019\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0012H\u0086\u0004\u001a\u001f\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\u0010\u0010\u0013\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00120\u0014H\u0086\u0004\u001a\u0015\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0007H\u0086\u0004\u001a\u0015\u0010\u0016\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0018H\u0086\u0004\u001a\u0015\u0010\u0019\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0018H\u0086\u0004\u001a\u001b\u0010\u001a\u001a\u00020\u0007*\u00020\u0002\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0000\u001a\u0004\u0010\u0000(\u0000\u001a\u001b\u0010\u001c\u001a\u00020\u0007*\u00020\u0002\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0000\u001a\u0004\u0010\u0000(\u0001*\"\u0010\u0000\"\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001ò\u0001\b\n\u00020\u001b\n\u00020\u0010¨\u0006\u001d"}, d2 = {"TypeStatements", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowVariable;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/TypeStatement;", "eq", "Lorg/jetbrains/kotlin/fir/resolve/dfa/OperationStatement;", "constant", Argument.Delimiters.none, Argument.Delimiters.none, "notEq", "implies", "Lorg/jetbrains/kotlin/fir/resolve/dfa/Implication;", "effect", "Lorg/jetbrains/kotlin/fir/resolve/dfa/Statement;", "valueNotEq", "Lorg/jetbrains/kotlin/fir/resolve/dfa/MutableTypeStatement;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/RealVariable;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "symbols", Argument.Delimiters.none, "boolean", "typeEq", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "typeNotEq", "isSynthetic", "Lorg/jetbrains/kotlin/fir/resolve/dfa/SyntheticVariable;", "isReal", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ModelKt {
    public static final OperationStatement eq(DataFlowVariable dataFlowVariable, boolean z) {
        dataFlowVariable.getClass();
        return new OperationStatement(dataFlowVariable, z ? Operation.EqTrue : Operation.EqFalse);
    }

    public static final Implication implies(OperationStatement operationStatement, Statement statement) {
        operationStatement.getClass();
        statement.getClass();
        return new Implication(operationStatement, statement);
    }

    public static final boolean isReal(DataFlowVariable dataFlowVariable) {
        dataFlowVariable.getClass();
        return dataFlowVariable instanceof RealVariable;
    }

    public static final boolean isSynthetic(DataFlowVariable dataFlowVariable) {
        dataFlowVariable.getClass();
        return dataFlowVariable instanceof SyntheticVariable;
    }

    public static final OperationStatement notEq(DataFlowVariable dataFlowVariable, Void r2) {
        dataFlowVariable.getClass();
        return new OperationStatement(dataFlowVariable, Operation.NotEqNull);
    }

    public static final MutableTypeStatement typeEq(DataFlowVariable dataFlowVariable, ConeKotlinType coneKotlinType) {
        dataFlowVariable.getClass();
        coneKotlinType.getClass();
        return new MutableTypeStatement(dataFlowVariable, coneKotlinType instanceof ConeErrorType ? new LinkedHashSet() : SetsKt.linkedSetOf(new ConeKotlinType[]{RefinedTypeForDataFlowTypeAttributeKt.getRefinedTypeForDataFlowOrSelf(coneKotlinType)}), null, 4, null);
    }

    public static final MutableTypeStatement typeNotEq(DataFlowVariable dataFlowVariable, ConeKotlinType coneKotlinType) {
        dataFlowVariable.getClass();
        coneKotlinType.getClass();
        return new MutableTypeStatement(dataFlowVariable, null, coneKotlinType instanceof ConeErrorType ? new LinkedHashSet() : SetsKt.linkedSetOf(new DfaType[]{new DfaType.Cone(coneKotlinType)}), 2, null);
    }

    public static final MutableTypeStatement valueNotEq(RealVariable realVariable, List<? extends FirBasedSymbol<?>> list) {
        realVariable.getClass();
        list.getClass();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(new DfaType.Symbol((FirBasedSymbol) it.next()));
        }
        return new MutableTypeStatement(realVariable, null, linkedHashSet, 2, null);
    }

    public static final OperationStatement eq(DataFlowVariable dataFlowVariable, Void r2) {
        dataFlowVariable.getClass();
        return new OperationStatement(dataFlowVariable, Operation.EqNull);
    }

    public static final MutableTypeStatement valueNotEq(RealVariable realVariable, FirBasedSymbol<?> firBasedSymbol) {
        realVariable.getClass();
        firBasedSymbol.getClass();
        return new MutableTypeStatement(realVariable, null, SetsKt.linkedSetOf(new DfaType[]{new DfaType.Symbol(firBasedSymbol)}), 2, null);
    }

    public static final MutableTypeStatement valueNotEq(RealVariable realVariable, boolean z) {
        realVariable.getClass();
        return new MutableTypeStatement(realVariable, null, SetsKt.linkedSetOf(new DfaType[]{new DfaType.BooleanLiteral(z)}), 2, null);
    }
}

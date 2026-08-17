package org.jetbrains.kotlin.fir.resolve.dfa;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.DfaType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\u0004\b\t\u0010\nR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000e¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/MutableTypeStatement;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/TypeStatement;", "variable", "Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowVariable;", "upperTypes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "lowerTypes", "Lorg/jetbrains/kotlin/fir/DfaType;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowVariable;Ljava/util/Set;Ljava/util/Set;)V", "getVariable", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowVariable;", "getUpperTypes", "()Ljava/util/Set;", "getLowerTypes", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class MutableTypeStatement extends TypeStatement {
    private final Set<DfaType> lowerTypes;
    private final Set<ConeKotlinType> upperTypes;
    private final DataFlowVariable variable;

    public /* synthetic */ MutableTypeStatement(DataFlowVariable dataFlowVariable, Set set, Set set2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(dataFlowVariable, (i & 2) != 0 ? new LinkedHashSet() : set, (i & 4) != 0 ? new LinkedHashSet() : set2);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.TypeStatement
    public Set<DfaType> getLowerTypes() {
        return this.lowerTypes;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.TypeStatement
    public Set<ConeKotlinType> getUpperTypes() {
        return this.upperTypes;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.TypeStatement, org.jetbrains.kotlin.fir.resolve.dfa.Statement
    public DataFlowVariable getVariable() {
        return this.variable;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MutableTypeStatement(DataFlowVariable dataFlowVariable, Set<ConeKotlinType> set, Set<DfaType> set2) {
        super(null);
        dataFlowVariable.getClass();
        set.getClass();
        set2.getClass();
        this.variable = dataFlowVariable;
        this.upperTypes = set;
        this.lowerTypes = set2;
    }
}

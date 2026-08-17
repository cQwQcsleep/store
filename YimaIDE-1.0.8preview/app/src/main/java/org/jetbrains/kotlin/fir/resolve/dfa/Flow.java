package org.jetbrains.kotlin.fir.resolve.dfa;

import java.util.Collection;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH&J\u0012\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000b\u001a\u00020\u0006H&J\u0018\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f2\u0006\u0010\u000b\u001a\u00020\u0006H&J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0016R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/Flow;", Argument.Delimiters.none, "<init>", "()V", "knownVariables", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowVariable;", "getKnownVariables", "()Ljava/util/Set;", "unwrapVariable", "Lorg/jetbrains/kotlin/fir/resolve/dfa/RealVariable;", "variable", "getTypeStatement", "Lorg/jetbrains/kotlin/fir/resolve/dfa/TypeStatement;", "getImplications", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/Implication;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class Flow {
    public abstract Collection<Implication> getImplications(DataFlowVariable variable);

    public abstract Set<DataFlowVariable> getKnownVariables();

    public abstract TypeStatement getTypeStatement(DataFlowVariable variable);

    public DataFlowVariable unwrapVariable(DataFlowVariable variable) {
        variable.getClass();
        return variable instanceof RealVariable ? unwrapVariable((RealVariable) variable) : variable;
    }

    public abstract RealVariable unwrapVariable(RealVariable variable);
}

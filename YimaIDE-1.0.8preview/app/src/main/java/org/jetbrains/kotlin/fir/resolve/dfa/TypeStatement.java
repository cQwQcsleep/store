package org.jetbrains.kotlin.fir.resolve.dfa;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.DfaType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0014\u001a\u00020\u0015H\u0086\u0080\u0004J\u0006\u0010\u0016\u001a\u00020\u0015R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0010\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0012R\u0019\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t8F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\fR\u0019\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\t8F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\fR\u0016\u0010\u001b\u001a\u0004\u0018\u00010\u00158BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001e\u001a\u0004\u0018\u00010\u00158BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u001d\u0082\u0001\u0002 !¨\u0006\""}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/TypeStatement;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/Statement;", "<init>", "()V", "variable", "Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowVariable;", "getVariable", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowVariable;", "upperTypes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getUpperTypes", "()Ljava/util/Set;", "lowerTypes", "Lorg/jetbrains/kotlin/fir/DfaType;", "getLowerTypes", "isEmpty", Argument.Delimiters.none, "()Z", "isNotEmpty", "toString", Argument.Delimiters.none, "renderType", "upperTypesOrNull", "getUpperTypesOrNull", "lowerTypesOrNull", "getLowerTypesOrNull", "upperTypesStringOrNull", "getUpperTypesStringOrNull", "()Ljava/lang/String;", "lowerTypesStringOrNull", "getLowerTypesStringOrNull", "Lorg/jetbrains/kotlin/fir/resolve/dfa/MutableTypeStatement;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/PersistentTypeStatement;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class TypeStatement extends Statement {
    private TypeStatement() {
        super(null);
    }

    private final String getLowerTypesStringOrNull() {
        String strJoinToString$default;
        Set<DfaType> lowerTypesOrNull = getLowerTypesOrNull();
        if (lowerTypesOrNull == null || (strJoinToString$default = CollectionsKt.joinToString$default(lowerTypesOrNull, " | ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null)) == null) {
            return null;
        }
        return "¬(" + strJoinToString$default + ')';
    }

    private final String getUpperTypesStringOrNull() {
        Set<ConeKotlinType> upperTypesOrNull = getUpperTypesOrNull();
        if (upperTypesOrNull != null) {
            return CollectionsKt.joinToString$default(upperTypesOrNull, " & ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
        }
        return null;
    }

    public abstract Set<DfaType> getLowerTypes();

    public final Set<DfaType> getLowerTypesOrNull() {
        Set<DfaType> lowerTypes = getLowerTypes();
        if (lowerTypes.isEmpty()) {
            return null;
        }
        return lowerTypes;
    }

    public abstract Set<ConeKotlinType> getUpperTypes();

    public final Set<ConeKotlinType> getUpperTypesOrNull() {
        Set<ConeKotlinType> upperTypes = getUpperTypes();
        if (upperTypes.isEmpty()) {
            return null;
        }
        return upperTypes;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.Statement
    public abstract DataFlowVariable getVariable();

    public final boolean isEmpty() {
        return getUpperTypes().isEmpty() && getLowerTypes().isEmpty();
    }

    public final boolean isNotEmpty() {
        return !isEmpty();
    }

    public final String renderType() {
        return CollectionsKt.joinToString$default(CollectionsKt.listOfNotNull(new String[]{getUpperTypesStringOrNull(), getLowerTypesStringOrNull()}), " & ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    public final String toString() {
        return getVariable() + ": " + renderType();
    }

    public /* synthetic */ TypeStatement(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

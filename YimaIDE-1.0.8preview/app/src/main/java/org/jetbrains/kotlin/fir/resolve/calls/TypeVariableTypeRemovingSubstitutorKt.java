package org.jetbrains.kotlin.fir.resolve.calls;

import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeContext;
import org.jetbrains.kotlin.types.model.TypeConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\u001a,\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¨\u0006\t"}, d2 = {"removeTypeVariableTypes", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "typeContext", "Lorg/jetbrains/kotlin/fir/types/ConeTypeContext;", "replacement", "Lorg/jetbrains/kotlin/fir/resolve/calls/TypeVariableReplacement;", "skippedOuterTypeVariables", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/types/model/TypeConstructorMarker;", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TypeVariableTypeRemovingSubstitutorKt {
    public static final ConeKotlinType removeTypeVariableTypes(ConeKotlinType coneKotlinType, ConeTypeContext coneTypeContext, TypeVariableReplacement typeVariableReplacement, Set<? extends TypeConstructorMarker> set) {
        coneKotlinType.getClass();
        coneTypeContext.getClass();
        typeVariableReplacement.getClass();
        return new TypeVariableTypeRemovingSubstitutor(coneTypeContext, typeVariableReplacement, set).substituteOrSelf(coneKotlinType);
    }

    public static /* synthetic */ ConeKotlinType removeTypeVariableTypes$default(ConeKotlinType coneKotlinType, ConeTypeContext coneTypeContext, TypeVariableReplacement typeVariableReplacement, Set set, int i, Object obj) {
        if ((i & 4) != 0) {
            set = null;
        }
        return removeTypeVariableTypes(coneKotlinType, coneTypeContext, typeVariableReplacement, set);
    }
}

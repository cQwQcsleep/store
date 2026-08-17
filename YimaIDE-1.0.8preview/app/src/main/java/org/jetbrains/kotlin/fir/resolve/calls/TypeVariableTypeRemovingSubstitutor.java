package org.jetbrains.kotlin.fir.resolve.calls;

import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.diagnostics.ConeCannotInferTypeParameterType;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnknownLambdaParameterTypeDiagnostic;
import org.jetbrains.kotlin.fir.resolve.substitution.AbstractConeSubstitutor;
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeContext;
import org.jetbrains.kotlin.fir.types.ConeTypeVariableType;
import org.jetbrains.kotlin.fir.types.impl.ConeTypeParameterTypeImpl;
import org.jetbrains.kotlin.types.model.TypeConstructorMarker;
import org.jetbrains.kotlin.types.model.TypeParameterMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ\u0012\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\u0010\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000fH\u0002J\n\u0010\u0010\u001a\u00020\u0011H\u0096\u0080\u0004R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/TypeVariableTypeRemovingSubstitutor;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/AbstractConeSubstitutor;", "typeContext", "Lorg/jetbrains/kotlin/fir/types/ConeTypeContext;", "replacement", "Lorg/jetbrains/kotlin/fir/resolve/calls/TypeVariableReplacement;", "skippedOuterTypeVariables", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/types/model/TypeConstructorMarker;", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeTypeContext;Lorg/jetbrains/kotlin/fir/resolve/calls/TypeVariableReplacement;Ljava/util/Set;)V", "substituteType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", ModuleXmlParser.TYPE, "convertTypeVariableType", "Lorg/jetbrains/kotlin/fir/types/ConeTypeVariableType;", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class TypeVariableTypeRemovingSubstitutor extends AbstractConeSubstitutor {
    private final TypeVariableReplacement replacement;
    private final Set<TypeConstructorMarker> skippedOuterTypeVariables;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TypeVariableTypeRemovingSubstitutor(ConeTypeContext coneTypeContext, TypeVariableReplacement typeVariableReplacement, Set<? extends TypeConstructorMarker> set) {
        super(coneTypeContext);
        coneTypeContext.getClass();
        typeVariableReplacement.getClass();
        this.replacement = typeVariableReplacement;
        this.skippedOuterTypeVariables = set;
    }

    private final ConeKotlinType convertTypeVariableType(ConeTypeVariableType type) {
        TypeParameterMarker originalTypeParameter = type.getTypeConstructor().getOriginalTypeParameter();
        if (originalTypeParameter == null) {
            return new ConeErrorType(new ConeUnknownLambdaParameterTypeDiagnostic(Intrinsics.areEqual(type.getTypeConstructor().getDebugName(), "_R")), false, null, null, null, null, null, 126, null);
        }
        if (!(originalTypeParameter instanceof ConeTypeParameterLookupTag)) {
            k2d.a("Check failed.");
            return null;
        }
        ConeTypeParameterLookupTag coneTypeParameterLookupTag = (ConeTypeParameterLookupTag) originalTypeParameter;
        ConeTypeParameterTypeImpl coneTypeParameterTypeImpl = new ConeTypeParameterTypeImpl(coneTypeParameterLookupTag, type.getIsMarkedNullable(), type.getAttributes());
        return this.replacement == TypeVariableReplacement.ErrorType ? new ConeErrorType(new ConeCannotInferTypeParameterType(coneTypeParameterLookupTag.getTypeParameterSymbol(), null, 2, null), true, coneTypeParameterTypeImpl, null, null, null, null, 120, null) : coneTypeParameterTypeImpl;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.substitution.AbstractConeSubstitutor
    public ConeKotlinType substituteType(ConeKotlinType type) {
        Set<TypeConstructorMarker> set;
        type.getClass();
        if ((type instanceof ConeTypeVariableType) && ((set = this.skippedOuterTypeVariables) == null || !set.contains(((ConeTypeVariableType) type).getTypeConstructor()))) {
            return convertTypeVariableType((ConeTypeVariableType) type);
        }
        if (type instanceof ConeDefinitelyNotNullType) {
            ConeKotlinType coneKotlinTypeSubstituteOriginal = substituteOriginal((ConeDefinitelyNotNullType) type);
            if (coneKotlinTypeSubstituteOriginal instanceof ConeErrorType) {
                ConeErrorType coneErrorType = (ConeErrorType) coneKotlinTypeSubstituteOriginal;
                if (coneErrorType.getIsUninferredParameter() && (coneErrorType.getDiagnostic() instanceof ConeCannotInferTypeParameterType)) {
                    return new ConeDefinitelyNotNullType((ConeSimpleKotlinType) coneKotlinTypeSubstituteOriginal);
                }
            }
        }
        return null;
    }

    public String toString() {
        return "{<Type variable> -> <Error type>}";
    }
}

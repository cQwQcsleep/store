package org.jetbrains.kotlin.fir.renderer;

import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.state.InlineClassManglingUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.SpecialNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0014J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nH\u0014¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/FirCallableSignatureRendererForReadability;", "Lorg/jetbrains/kotlin/fir/renderer/FirCallableSignatureRenderer;", "<init>", "()V", "renderCallableType", Argument.Delimiters.none, "callableDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "renderDefaultValue", "valueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "renderParameterName", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCallableSignatureRendererForReadability extends FirCallableSignatureRenderer {
    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.fir.renderer.FirCallableSignatureRenderer
    public void renderCallableType(FirCallableDeclaration callableDeclaration) throws UninitializedPropertyAccessException {
        ConeKotlinType coneKotlinTypeArrayElementType$default;
        callableDeclaration.getClass();
        FirResolvedTypeRef returnTypeRef = callableDeclaration.getReturnTypeRef();
        if ((callableDeclaration instanceof FirValueParameter) && ((FirValueParameter) callableDeclaration).getIsVararg() && (returnTypeRef instanceof FirResolvedTypeRef) && (coneKotlinTypeArrayElementType$default = FirTypeUtilsKt.arrayElementType$default(returnTypeRef.getConeType(), false, 1, null)) != null) {
            ConeTypeRenderer.render$default(getTypeRenderer(), coneKotlinTypeArrayElementType$default, null, 2, null);
        } else {
            super.renderCallableType(callableDeclaration);
        }
    }

    @Override // org.jetbrains.kotlin.fir.renderer.FirCallableSignatureRenderer
    public void renderDefaultValue(FirValueParameter valueParameter) {
        valueParameter.getClass();
        if (valueParameter.getDefaultValue() != null) {
            getPrinter().print(" = ...");
        }
    }

    @Override // org.jetbrains.kotlin.fir.renderer.FirCallableSignatureRenderer
    public String renderParameterName(FirValueParameter valueParameter) {
        valueParameter.getClass();
        return Intrinsics.areEqual(valueParameter.getName(), SpecialNames.UNDERSCORE_FOR_UNUSED_VAR) ? InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER : super.renderParameterName(valueParameter);
    }
}

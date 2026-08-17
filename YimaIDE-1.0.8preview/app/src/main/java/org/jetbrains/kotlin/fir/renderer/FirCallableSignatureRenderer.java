package org.jetbrains.kotlin.fir.renderer;

import java.util.List;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.PreprocessCommandLineArgumentsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameterKindKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirQualifierPart;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirUserTypeRef;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010#\u001a\u00020$2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&J\u000e\u0010(\u001a\u00020$2\u0006\u0010)\u001a\u00020'J\u0010\u0010*\u001a\u00020+2\u0006\u0010)\u001a\u00020'H\u0014J\u0010\u0010,\u001a\u00020$2\u0006\u0010-\u001a\u00020.H\u0016J\b\u0010/\u001a\u00020$H\u0016J\u0010\u00100\u001a\u00020$2\u0006\u0010)\u001a\u00020'H\u0014R\u001a\u0010\u0004\u001a\u00020\u0005X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0018\u0010\u000e\u001a\u00060\u000fR\u00020\u00108DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0013\u001a\u0004\u0018\u00010\u00148BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0017\u001a\u0004\u0018\u00010\u00188DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001b\u001a\u0004\u0018\u00010\u001c8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020 8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"¨\u00061"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/FirCallableSignatureRenderer;", Argument.Delimiters.none, "<init>", "()V", "components", "Lorg/jetbrains/kotlin/fir/renderer/FirRendererComponents;", "getComponents$org_jetbrains_kotlin_tree", "()Lorg/jetbrains/kotlin/fir/renderer/FirRendererComponents;", "setComponents$org_jetbrains_kotlin_tree", "(Lorg/jetbrains/kotlin/fir/renderer/FirRendererComponents;)V", "printer", "Lorg/jetbrains/kotlin/fir/renderer/FirPrinter;", "getPrinter", "()Lorg/jetbrains/kotlin/fir/renderer/FirPrinter;", "visitor", "Lorg/jetbrains/kotlin/fir/renderer/FirRenderer$Visitor;", "Lorg/jetbrains/kotlin/fir/renderer/FirRenderer;", "getVisitor", "()Lorg/jetbrains/kotlin/fir/renderer/FirRenderer$Visitor;", "annotationRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirAnnotationRenderer;", "getAnnotationRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirAnnotationRenderer;", "declarationRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirDeclarationRenderer;", "getDeclarationRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirDeclarationRenderer;", "modifierRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirModifierRenderer;", "getModifierRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirModifierRenderer;", "typeRenderer", "Lorg/jetbrains/kotlin/fir/renderer/ConeTypeRenderer;", "getTypeRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/ConeTypeRenderer;", "renderParameters", Argument.Delimiters.none, "valueParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "renderParameter", "valueParameter", "renderParameterName", Argument.Delimiters.none, "renderCallableType", "callableDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "renderReturnTypePrefix", "renderDefaultValue", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirCallableSignatureRenderer {
    public FirRendererComponents components;

    private final FirAnnotationRenderer getAnnotationRenderer() {
        return getComponents$org_jetbrains_kotlin_tree().getAnnotationRenderer();
    }

    private final FirModifierRenderer getModifierRenderer() {
        return getComponents$org_jetbrains_kotlin_tree().getModifierRenderer();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirRendererComponents getComponents$org_jetbrains_kotlin_tree() throws UninitializedPropertyAccessException {
        FirRendererComponents firRendererComponents = this.components;
        if (firRendererComponents != null) {
            return firRendererComponents;
        }
        Intrinsics.throwUninitializedPropertyAccessException("components");
        return null;
    }

    public final FirDeclarationRenderer getDeclarationRenderer() {
        return getComponents$org_jetbrains_kotlin_tree().getDeclarationRenderer();
    }

    public final FirPrinter getPrinter() {
        return getComponents$org_jetbrains_kotlin_tree().getPrinter();
    }

    public final ConeTypeRenderer getTypeRenderer() {
        return getComponents$org_jetbrains_kotlin_tree().getTypeRenderer();
    }

    public final FirRenderer.Visitor getVisitor() {
        return getComponents$org_jetbrains_kotlin_tree().getVisitor();
    }

    public void renderCallableType(FirCallableDeclaration callableDeclaration) {
        callableDeclaration.getClass();
        callableDeclaration.getReturnTypeRef().accept(getVisitor());
    }

    public void renderDefaultValue(FirValueParameter valueParameter) {
        valueParameter.getClass();
        FirExpression defaultValue = valueParameter.getDefaultValue();
        if (defaultValue != null) {
            getPrinter().print(" = ");
            defaultValue.accept(getVisitor());
        }
    }

    public final void renderParameter(FirValueParameter valueParameter) {
        ClassId classId;
        valueParameter.getClass();
        FirDeclarationRenderer declarationRenderer = getDeclarationRenderer();
        if (declarationRenderer != null) {
            declarationRenderer.renderPhaseAndAttributes$org_jetbrains_kotlin_tree(valueParameter);
        }
        FirAnnotationRenderer annotationRenderer = getAnnotationRenderer();
        Name shortClassName = null;
        if (annotationRenderer != null) {
            FirAnnotationRenderer.render$default(annotationRenderer, valueParameter, null, 2, null);
        }
        FirModifierRenderer modifierRenderer = getModifierRenderer();
        if (modifierRenderer != null) {
            modifierRenderer.renderModifiers(valueParameter);
        }
        if (!Intrinsics.areEqual(valueParameter.getName(), SpecialNames.NO_NAME_PROVIDED)) {
            if (FirValueParameterKindKt.isLegacyContextReceiver(valueParameter)) {
                FirUserTypeRef returnTypeRef = valueParameter.getReturnTypeRef();
                if (returnTypeRef instanceof FirUserTypeRef) {
                    FirQualifierPart firQualifierPart = (FirQualifierPart) CollectionsKt.lastOrNull(returnTypeRef.getQualifier());
                    if (firQualifierPart != null) {
                        shortClassName = firQualifierPart.getName();
                    }
                } else if ((returnTypeRef instanceof FirResolvedTypeRef) && (classId = ConeTypeUtilsKt.getClassId(((FirResolvedTypeRef) returnTypeRef).getConeType())) != null) {
                    shortClassName = classId.getShortClassName();
                }
                if (!Intrinsics.areEqual(valueParameter.getName(), shortClassName)) {
                    getPrinter().print(valueParameter.getName());
                    getPrinter().print(PreprocessCommandLineArgumentsKt.ARGFILE_ARGUMENT);
                }
            } else {
                getPrinter().print(renderParameterName(valueParameter));
                renderReturnTypePrefix();
            }
        }
        renderCallableType(valueParameter);
        renderDefaultValue(valueParameter);
    }

    public String renderParameterName(FirValueParameter valueParameter) {
        valueParameter.getClass();
        String string = valueParameter.getName().toString();
        string.getClass();
        return string;
    }

    public final void renderParameters(List<? extends FirValueParameter> valueParameters) {
        valueParameters.getClass();
        getPrinter().print("(");
        int i = 0;
        for (FirValueParameter firValueParameter : valueParameters) {
            int i2 = i + 1;
            if (i > 0) {
                getPrinter().print(", ");
            }
            renderParameter(firValueParameter);
            i = i2;
        }
        getPrinter().print(")");
    }

    public void renderReturnTypePrefix() {
        getPrinter().print(": ");
    }

    public final void setComponents$org_jetbrains_kotlin_tree(FirRendererComponents firRendererComponents) {
        firRendererComponents.getClass();
        this.components = firRendererComponents;
    }
}

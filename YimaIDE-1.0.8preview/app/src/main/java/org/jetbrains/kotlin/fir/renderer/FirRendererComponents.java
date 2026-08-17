package org.jetbrains.kotlin.fir.renderer;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.contracts.description.ConeContractRenderer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¾\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001R\u0016\u0010\u0002\u001a\u00060\u0003R\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u0004\u0018\u00010\fX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u0004\u0018\u00010\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u0004\u0018\u00010\u0014X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u0004\u0018\u00010\u0018X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u0004\u0018\u00010\u001cX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u0004\u0018\u00010 X¦\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0014\u0010#\u001a\u0004\u0018\u00010$X¦\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0012\u0010'\u001a\u00020(X¦\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*R\u0014\u0010+\u001a\u0004\u0018\u00010,X¦\u0004¢\u0006\u0006\u001a\u0004\b-\u0010.R\u0014\u0010/\u001a\u0004\u0018\u000100X¦\u0004¢\u0006\u0006\u001a\u0004\b1\u00102R\u0014\u00103\u001a\u0004\u0018\u000104X¦\u0004¢\u0006\u0006\u001a\u0004\b5\u00106R\u0014\u00107\u001a\u0004\u0018\u000108X¦\u0004¢\u0006\u0006\u001a\u0004\b9\u0010:R\u0012\u0010;\u001a\u00020<X¦\u0004¢\u0006\u0006\u001a\u0004\b=\u0010>R\u0012\u0010?\u001a\u00020@X¦\u0004¢\u0006\u0006\u001a\u0004\bA\u0010BR\u0014\u0010C\u001a\u0004\u0018\u00010DX¦\u0004¢\u0006\u0006\u001a\u0004\bE\u0010FR\u0014\u0010G\u001a\u0004\u0018\u00010HX¦\u0004¢\u0006\u0006\u001a\u0004\bI\u0010JR\u0012\u0010K\u001a\u00020LX¦\u0004¢\u0006\u0006\u001a\u0004\bM\u0010NR\u0012\u0010O\u001a\u00020PX¦\u0004¢\u0006\u0006\u001a\u0004\bQ\u0010RR\u0012\u0010S\u001a\u00020TX¦\u0004¢\u0006\u0006\u001a\u0004\bU\u0010VR\u0014\u0010W\u001a\u0004\u0018\u00010XX¦\u0004¢\u0006\u0006\u001a\u0004\bY\u0010Zø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006[À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/FirRendererComponents;", Argument.Delimiters.none, "visitor", "Lorg/jetbrains/kotlin/fir/renderer/FirRenderer$Visitor;", "Lorg/jetbrains/kotlin/fir/renderer/FirRenderer;", "getVisitor", "()Lorg/jetbrains/kotlin/fir/renderer/FirRenderer$Visitor;", "printer", "Lorg/jetbrains/kotlin/fir/renderer/FirPrinter;", "getPrinter", "()Lorg/jetbrains/kotlin/fir/renderer/FirPrinter;", "declarationRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirDeclarationRenderer;", "getDeclarationRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirDeclarationRenderer;", "annotationRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirAnnotationRenderer;", "getAnnotationRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirAnnotationRenderer;", "bodyRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirBodyRenderer;", "getBodyRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirBodyRenderer;", "callArgumentsRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirCallArgumentsRenderer;", "getCallArgumentsRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirCallArgumentsRenderer;", "contextArgumentRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirContextArgumentRenderer;", "getContextArgumentRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirContextArgumentRenderer;", "classMemberRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirClassMemberRenderer;", "getClassMemberRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirClassMemberRenderer;", "contractRenderer", "Lorg/jetbrains/kotlin/fir/contracts/description/ConeContractRenderer;", "getContractRenderer", "()Lorg/jetbrains/kotlin/fir/contracts/description/ConeContractRenderer;", "idRenderer", "Lorg/jetbrains/kotlin/fir/renderer/ConeIdRenderer;", "getIdRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/ConeIdRenderer;", "modifierRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirModifierRenderer;", "getModifierRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirModifierRenderer;", "packageDirectiveRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirPackageDirectiveRenderer;", "getPackageDirectiveRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirPackageDirectiveRenderer;", "propertyAccessorRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirPropertyAccessorRenderer;", "getPropertyAccessorRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirPropertyAccessorRenderer;", "resolvePhaseRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirResolvePhaseRenderer;", "getResolvePhaseRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirResolvePhaseRenderer;", "typeRenderer", "Lorg/jetbrains/kotlin/fir/renderer/ConeTypeRenderer;", "getTypeRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/ConeTypeRenderer;", "referencedSymbolRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirSymbolRenderer;", "getReferencedSymbolRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirSymbolRenderer;", "callableSignatureRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirCallableSignatureRenderer;", "getCallableSignatureRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirCallableSignatureRenderer;", "errorExpressionRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirErrorExpressionRenderer;", "getErrorExpressionRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirErrorExpressionRenderer;", "resolvedNamedReferenceRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirResolvedNamedReferenceRenderer;", "getResolvedNamedReferenceRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirResolvedNamedReferenceRenderer;", "resolvedQualifierRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirResolvedQualifierRenderer;", "getResolvedQualifierRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirResolvedQualifierRenderer;", "getClassCallRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirGetClassCallRenderer;", "getGetClassCallRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirGetClassCallRenderer;", "supertypeRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirSupertypeRenderer;", "getSupertypeRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirSupertypeRenderer;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface FirRendererComponents {
    FirAnnotationRenderer getAnnotationRenderer();

    FirBodyRenderer getBodyRenderer();

    FirCallArgumentsRenderer getCallArgumentsRenderer();

    FirCallableSignatureRenderer getCallableSignatureRenderer();

    FirClassMemberRenderer getClassMemberRenderer();

    FirContextArgumentRenderer getContextArgumentRenderer();

    ConeContractRenderer getContractRenderer();

    FirDeclarationRenderer getDeclarationRenderer();

    FirErrorExpressionRenderer getErrorExpressionRenderer();

    FirGetClassCallRenderer getGetClassCallRenderer();

    ConeIdRenderer getIdRenderer();

    FirModifierRenderer getModifierRenderer();

    FirPackageDirectiveRenderer getPackageDirectiveRenderer();

    FirPrinter getPrinter();

    FirPropertyAccessorRenderer getPropertyAccessorRenderer();

    FirSymbolRenderer getReferencedSymbolRenderer();

    FirResolvePhaseRenderer getResolvePhaseRenderer();

    FirResolvedNamedReferenceRenderer getResolvedNamedReferenceRenderer();

    FirResolvedQualifierRenderer getResolvedQualifierRenderer();

    FirSupertypeRenderer getSupertypeRenderer();

    ConeTypeRenderer getTypeRenderer();

    FirRenderer.Visitor getVisitor();
}

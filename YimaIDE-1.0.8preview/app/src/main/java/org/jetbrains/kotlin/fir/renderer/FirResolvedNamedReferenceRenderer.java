package org.jetbrains.kotlin.fir.renderer;

import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.references.FirResolvedCallableReference;
import org.jetbrains.kotlin.fir.references.FirResolvedErrorReference;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0010¢\u0006\u0002\b\u0017J\u0014\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0019*\u0006\u0012\u0002\b\u00030\u0019H\u0002R\u001a\u0010\u0004\u001a\u00020\u0005X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0018\u0010\u000e\u001a\u00060\u000fR\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/FirResolvedNamedReferenceRenderer;", Argument.Delimiters.none, "<init>", "()V", "components", "Lorg/jetbrains/kotlin/fir/renderer/FirRendererComponents;", "getComponents$org_jetbrains_kotlin_tree", "()Lorg/jetbrains/kotlin/fir/renderer/FirRendererComponents;", "setComponents$org_jetbrains_kotlin_tree", "(Lorg/jetbrains/kotlin/fir/renderer/FirRendererComponents;)V", "printer", "Lorg/jetbrains/kotlin/fir/renderer/FirPrinter;", "getPrinter", "()Lorg/jetbrains/kotlin/fir/renderer/FirPrinter;", "visitor", "Lorg/jetbrains/kotlin/fir/renderer/FirRenderer$Visitor;", "Lorg/jetbrains/kotlin/fir/renderer/FirRenderer;", "getVisitor", "()Lorg/jetbrains/kotlin/fir/renderer/FirRenderer$Visitor;", "render", Argument.Delimiters.none, "resolvedNamedReference", "Lorg/jetbrains/kotlin/fir/references/FirResolvedNamedReference;", "render$org_jetbrains_kotlin_tree", "unwrapIntersectionOverrides", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirResolvedNamedReferenceRenderer {
    public FirRendererComponents components;

    private final FirRenderer.Visitor getVisitor() {
        return getComponents$org_jetbrains_kotlin_tree().getVisitor();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final FirBasedSymbol<?> unwrapIntersectionOverrides(FirBasedSymbol<?> firBasedSymbol) {
        FirCallableSymbol firCallableSymbol = firBasedSymbol instanceof FirCallableSymbol ? (FirCallableSymbol) firBasedSymbol : null;
        if (firCallableSymbol != null) {
            FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firCallableSymbol.getFir();
            FirCallableDeclaration originalForIntersectionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
            FirCallableSymbol<FirCallableDeclaration> symbol = originalForIntersectionOverrideAttr != null ? originalForIntersectionOverrideAttr.getSymbol() : null;
            if (symbol != null) {
                return unwrapIntersectionOverrides(symbol);
            }
        }
        return firBasedSymbol;
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

    public final FirPrinter getPrinter() {
        return getComponents$org_jetbrains_kotlin_tree().getPrinter();
    }

    /* JADX WARN: Code duplicated, block: B:11:0x001f  */
    /* JADX WARN: Multi-variable type inference failed */
    public void render$org_jetbrains_kotlin_tree(FirResolvedNamedReference resolvedNamedReference) {
        boolean z;
        resolvedNamedReference.getClass();
        FirBasedSymbol<?> resolvedSymbol = resolvedNamedReference.getResolvedSymbol();
        FirDeclaration fir = resolvedSymbol.getFir();
        FirCallableDeclaration firCallableDeclaration = fir instanceof FirCallableDeclaration ? (FirCallableDeclaration) fir : null;
        int i = 0;
        if (firCallableDeclaration != null) {
            z = ClassMembersKt.isSubstitutionOverride(firCallableDeclaration);
        }
        if (z) {
            getPrinter().print("SubstitutionOverride<");
        }
        getComponents$org_jetbrains_kotlin_tree().getReferencedSymbolRenderer().printReference(unwrapIntersectionOverrides(resolvedSymbol));
        if (resolvedNamedReference instanceof FirResolvedCallableReference) {
            FirResolvedCallableReference firResolvedCallableReference = (FirResolvedCallableReference) resolvedNamedReference;
            if (!firResolvedCallableReference.getInferredTypeArguments().isEmpty()) {
                getPrinter().print("<");
                for (ConeKotlinType coneKotlinType : firResolvedCallableReference.getInferredTypeArguments()) {
                    int i2 = i + 1;
                    if (i > 0) {
                        getPrinter().print(", ");
                    }
                    ConeTypeRenderer.render$default(getComponents$org_jetbrains_kotlin_tree().getTypeRenderer(), coneKotlinType, null, 2, null);
                    i = i2;
                }
                getPrinter().print(">");
            }
        }
        if (z) {
            if (resolvedSymbol instanceof FirNamedFunctionSymbol) {
                getPrinter().print(": ");
                ((FirNamedFunction) ((FirNamedFunctionSymbol) resolvedSymbol).getFir()).getReturnTypeRef().accept(getVisitor());
            } else if (resolvedSymbol instanceof FirPropertySymbol) {
                getPrinter().print(": ");
                ((FirProperty) ((FirPropertySymbol) resolvedSymbol).getFir()).getReturnTypeRef().accept(getVisitor());
            }
            getPrinter().print(">");
        }
        if (resolvedNamedReference instanceof FirResolvedErrorReference) {
            getPrinter().print("<" + ((FirResolvedErrorReference) resolvedNamedReference).getDiagnostic().getReason() + ">#");
        }
    }

    public final void setComponents$org_jetbrains_kotlin_tree(FirRendererComponents firRendererComponents) {
        firRendererComponents.getClass();
        this.components = firRendererComponents;
    }
}

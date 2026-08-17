package org.jetbrains.kotlin.fir.analysis.checkers;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;
import org.jetbrains.kotlin.lexer.KtTokens;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002\u001a\u001d\u0010\u0003\u001a\u00020\u0004*\u00020\u0004H\u0000R\u00020\u0005j\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"toVisibilityOrNull", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "Lorg/jetbrains/kotlin/lexer/KtModifierKeywordToken;", "delegatedPropertySourceOrThis", "Lorg/jetbrains/kotlin/KtSourceElement;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/KtSourceElement;)Lorg/jetbrains/kotlin/KtSourceElement;", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SourceHelpersKt {
    public static final KtSourceElement delegatedPropertySourceOrThis(CheckerContext checkerContext, KtSourceElement ktSourceElement) {
        Object next;
        FirExpression delegate;
        KtSourceElement source;
        KtSourceElement ktSourceElementFakeElement$default;
        checkerContext.getClass();
        ktSourceElement.getClass();
        if (Intrinsics.areEqual(ktSourceElement.getKind(), KtFakeSourceElementKind.DelegatedPropertyAccessor.INSTANCE)) {
            List<FirBasedSymbol<?>> containingDeclarations = checkerContext.getContainingDeclarations();
            if (!(containingDeclarations instanceof List)) {
                Iterator it = CollectionsKt.reversed(containingDeclarations).iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                } while (!(next instanceof FirPropertySymbol));
            } else {
                int size = containingDeclarations.size() - 1;
                if (size < 0) {
                    next = null;
                    break;
                }
                while (true) {
                    int i = size - 1;
                    next = containingDeclarations.get(size);
                    if (next instanceof FirPropertySymbol) {
                        break;
                    }
                    if (i < 0) {
                        next = null;
                        break;
                    }
                    size = i;
                }
            }
            FirPropertySymbol firPropertySymbol = (FirPropertySymbol) next;
            if (firPropertySymbol != null && (delegate = firPropertySymbol.getDelegate()) != null && (source = delegate.getSource()) != null && (ktSourceElementFakeElement$default = KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.DelegatedPropertyAccessor.INSTANCE, null, 2, null)) != null) {
                return ktSourceElementFakeElement$default;
            }
        }
        return ktSourceElement;
    }

    public static final Visibility toVisibilityOrNull(KtModifierKeywordToken ktModifierKeywordToken) {
        ktModifierKeywordToken.getClass();
        if (Intrinsics.areEqual(ktModifierKeywordToken, KtTokens.PUBLIC_KEYWORD)) {
            return Visibilities.Public.INSTANCE;
        }
        if (Intrinsics.areEqual(ktModifierKeywordToken, KtTokens.PRIVATE_KEYWORD)) {
            return Visibilities.Private.INSTANCE;
        }
        if (Intrinsics.areEqual(ktModifierKeywordToken, KtTokens.PROTECTED_KEYWORD)) {
            return Visibilities.Protected.INSTANCE;
        }
        if (Intrinsics.areEqual(ktModifierKeywordToken, KtTokens.INTERNAL_KEYWORD)) {
            return Visibilities.Internal.INSTANCE;
        }
        return null;
    }
}

package org.jetbrains.kotlin.fir.analysis.checkers;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtPsiSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.psi.KtCatchClause;
import org.jetbrains.kotlin.psi.KtConstructorCalleeExpression;
import org.jetbrains.kotlin.psi.KtEnumEntry;
import org.jetbrains.kotlin.psi.KtExpression;
import org.jetbrains.kotlin.psi.KtImportAlias;
import org.jetbrains.kotlin.psi.KtNameReferenceExpression;
import org.jetbrains.kotlin.psi.KtNullableType;
import org.jetbrains.kotlin.psi.KtQualifiedExpression;
import org.jetbrains.kotlin.psi.KtTypeElement;
import org.jetbrains.kotlin.psi.KtTypeProjection;
import org.jetbrains.kotlin.psi.KtTypeReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u0004\u0018\u0001H\u0005\"\n\b\u0000\u0010\u0005\u0018\u0001*\u00020\u0006*\u00020\u0007H\u0082\b¢\u0006\u0002\u0010\bJ \u0010\u0004\u001a\u0004\u0018\u0001H\u0005\"\n\b\u0000\u0010\u0005\u0018\u0001*\u00020\u0006*\u00020\tH\u0082\b¢\u0006\u0002\u0010\nJ\f\u0010\u000b\u001a\u00020\f*\u00020\rH\u0016J\u000e\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\tH\u0016J\u000e\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\u0006H\u0002J\u000e\u0010\u0010\u001a\u0004\u0018\u00010\u0011*\u00020\u0012H\u0016J\f\u0010\u0013\u001a\u00020\f*\u00020\u0014H\u0016J\f\u0010\u0015\u001a\u00020\f*\u00020\rH\u0016J\u0013\u0010\u0016\u001a\u0004\u0018\u00010\f*\u00020\u0017H\u0016¢\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u0004\u0018\u00010\f*\u00020\u0017H\u0016¢\u0006\u0002\u0010\u0018¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/PsiSourceNavigator;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/LightTreeSourceNavigator;", "<init>", "()V", "psi", "P", "Lcom/intellij/psi/PsiElement;", "Lorg/jetbrains/kotlin/fir/FirElement;", "(Lorg/jetbrains/kotlin/fir/FirElement;)Lcom/intellij/psi/PsiElement;", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/KtSourceElement;)Lcom/intellij/psi/PsiElement;", "isInConstructorCallee", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getRawIdentifier", Argument.Delimiters.none, "getRawName", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "isCatchElementParameter", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "isRedundantNullable", "hasBody", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "(Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;)Ljava/lang/Boolean;", "hasInitializer", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class PsiSourceNavigator extends LightTreeSourceNavigator {
    public static final PsiSourceNavigator INSTANCE = new PsiSourceNavigator();

    private PsiSourceNavigator() {
    }

    private final CharSequence getRawIdentifier(PsiElement psiElement) {
        KtTypeElement typeElement;
        if (psiElement instanceof KtNameReferenceExpression) {
            return ((KtNameReferenceExpression) psiElement).getReferencedNameElement().getNode().getChars();
        }
        if (psiElement instanceof KtTypeProjection) {
            KtTypeReference typeReference = ((KtTypeProjection) psiElement).getTypeReference();
            if (typeReference == null || (typeElement = typeReference.getTypeElement()) == null) {
                return null;
            }
            return typeElement.getText();
        }
        if (psiElement instanceof LeafPsiElement) {
            LeafPsiElement leafPsiElement = (LeafPsiElement) psiElement;
            if (Intrinsics.areEqual(leafPsiElement.getElementType(), KtTokens.IDENTIFIER)) {
                return leafPsiElement.getChars();
            }
        }
        if (!(psiElement instanceof KtQualifiedExpression)) {
            if (psiElement instanceof KtImportAlias) {
                return ((KtImportAlias) psiElement).getName();
            }
            return null;
        }
        KtExpression selectorExpression = ((KtQualifiedExpression) psiElement).getSelectorExpression();
        if (selectorExpression != null) {
            return getRawIdentifier((PsiElement) selectorExpression);
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.LightTreeSourceNavigator, org.jetbrains.kotlin.fir.analysis.checkers.SourceNavigator
    public String getRawName(FirDeclaration firDeclaration) {
        PsiElement psiElement;
        PsiElement nameIdentifier;
        firDeclaration.getClass();
        KtPsiSourceElement source = firDeclaration.getSource();
        if (source != null) {
            KtPsiSourceElement ktPsiSourceElement = source instanceof KtPsiSourceElement ? source : null;
            PsiElement psi = ktPsiSourceElement != null ? ktPsiSourceElement.getPsi() : null;
            if (!(psi instanceof PsiNameIdentifierOwner)) {
                psi = null;
            }
            psiElement = (PsiNameIdentifierOwner) psi;
        } else {
            psiElement = null;
        }
        PsiNameIdentifierOwner psiNameIdentifierOwner = psiElement instanceof PsiNameIdentifierOwner ? (PsiNameIdentifierOwner) psiElement : null;
        if (psiNameIdentifierOwner == null || (nameIdentifier = psiNameIdentifierOwner.getNameIdentifier()) == null) {
            return null;
        }
        return nameIdentifier.getText();
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.LightTreeSourceNavigator, org.jetbrains.kotlin.fir.analysis.checkers.SourceNavigator
    public Boolean hasBody(FirEnumEntry firEnumEntry) {
        firEnumEntry.getClass();
        KtSourceElement source = firEnumEntry.getSource();
        PsiElement psi = source != null ? KtSourceElementKt.getPsi(source) : null;
        KtEnumEntry ktEnumEntry = psi instanceof KtEnumEntry ? (KtEnumEntry) psi : null;
        if (ktEnumEntry == null) {
            return null;
        }
        return Boolean.valueOf(ktEnumEntry.getBody() != null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.LightTreeSourceNavigator, org.jetbrains.kotlin.fir.analysis.checkers.SourceNavigator
    public Boolean hasInitializer(FirEnumEntry firEnumEntry) {
        firEnumEntry.getClass();
        KtSourceElement source = firEnumEntry.getSource();
        PsiElement psi = source != null ? KtSourceElementKt.getPsi(source) : null;
        KtEnumEntry ktEnumEntry = psi instanceof KtEnumEntry ? (KtEnumEntry) psi : null;
        if (ktEnumEntry == null) {
            return null;
        }
        return Boolean.valueOf(ktEnumEntry.getInitializerList() != null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.LightTreeSourceNavigator, org.jetbrains.kotlin.fir.analysis.checkers.SourceNavigator
    public boolean isCatchElementParameter(FirValueParameterSymbol firValueParameterSymbol) {
        PsiElement parent;
        firValueParameterSymbol.getClass();
        KtPsiSourceElement source = firValueParameterSymbol.getSource();
        PsiElement parent2 = null;
        if (source != null) {
            KtPsiSourceElement ktPsiSourceElement = source instanceof KtPsiSourceElement ? source : null;
            PsiElement psi = ktPsiSourceElement != null ? ktPsiSourceElement.getPsi() : null;
            if (!(psi instanceof PsiElement)) {
                psi = null;
            }
            if (psi != null && (parent = psi.getParent()) != null) {
                parent2 = parent.getParent();
            }
        }
        return parent2 instanceof KtCatchClause;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.LightTreeSourceNavigator, org.jetbrains.kotlin.fir.analysis.checkers.SourceNavigator
    public boolean isInConstructorCallee(FirTypeRef firTypeRef) {
        KtTypeReference ktTypeReference;
        firTypeRef.getClass();
        KtPsiSourceElement source = firTypeRef.getSource();
        if (source != null) {
            KtPsiSourceElement ktPsiSourceElement = source instanceof KtPsiSourceElement ? source : null;
            PsiElement psi = ktPsiSourceElement != null ? ktPsiSourceElement.getPsi() : null;
            if (!(psi instanceof KtTypeReference)) {
                psi = null;
            }
            ktTypeReference = (KtTypeReference) psi;
        } else {
            ktTypeReference = null;
        }
        return (ktTypeReference != null ? ktTypeReference.getParent() : null) instanceof KtConstructorCalleeExpression;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.LightTreeSourceNavigator, org.jetbrains.kotlin.fir.analysis.checkers.SourceNavigator
    public boolean isRedundantNullable(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        KtSourceElement source = firTypeRef.getSource();
        if (source == null) {
            return false;
        }
        KtTypeReference psi = KtSourceElementKt.getPsi(source);
        KtTypeReference ktTypeReference = psi instanceof KtTypeReference ? psi : null;
        if (ktTypeReference == null) {
            return false;
        }
        KtTypeElement typeElement = ktTypeReference.getTypeElement();
        KtNullableType ktNullableType = typeElement instanceof KtNullableType ? (KtNullableType) typeElement : null;
        if (ktNullableType == null) {
            return false;
        }
        return ktNullableType.getInnerType() instanceof KtNullableType;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.LightTreeSourceNavigator, org.jetbrains.kotlin.fir.analysis.checkers.SourceNavigator
    public CharSequence getRawIdentifier(KtSourceElement ktSourceElement) {
        ktSourceElement.getClass();
        KtPsiSourceElement ktPsiSourceElement = ktSourceElement instanceof KtPsiSourceElement ? (KtPsiSourceElement) ktSourceElement : null;
        PsiElement psi = ktPsiSourceElement != null ? ktPsiSourceElement.getPsi() : null;
        if (!(psi instanceof PsiElement)) {
            psi = null;
        }
        if (psi != null) {
            return getRawIdentifier(psi);
        }
        return null;
    }
}

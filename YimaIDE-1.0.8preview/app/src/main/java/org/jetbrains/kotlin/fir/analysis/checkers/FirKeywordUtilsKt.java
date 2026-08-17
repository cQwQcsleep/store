package org.jetbrains.kotlin.fir.analysis.checkers;

import com.intellij.lang.ASTNode;
import com.intellij.lang.LighterASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtLightSourceElement;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtPsiSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategiesKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.lexer.KtKeywordToken;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.psi.KtModifierList;
import org.jetbrains.kotlin.psi.KtModifierListOwner;
import org.jetbrains.kotlin.psi.KtProperty;
import org.jetbrains.kotlin.psi.KtValVarKeywordOwner;
import org.jetbrains.kotlin.util.LightTreeUtilsKt;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachementBuilderUtilsKt;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u000e\u0010\u0003\u001a\u0004\u0018\u00010\u0004*\u0004\u0018\u00010\u0005\u001a\u0017\u0010\u0006\u001a\u00020\u0007*\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\tH\u0086\u0002\u001a\f\u0010\u0003\u001a\u0004\u0018\u00010\u0004*\u00020\n\u001a\u0018\u0010\u000b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\f*\u00020\n2\u0006\u0010\b\u001a\u00020\t\u001a\u0012\u0010\r\u001a\u00020\u0007*\u00020\n2\u0006\u0010\b\u001a\u00020\t\u001a\u0016\u0010\r\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\b\u001a\u00020\t\"\u0013\u0010\u0000\u001a\u00070\u0001¢\u0006\u0002\b\u0002X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010*\u0004\u0018\u00010\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"MODIFIER_KEYWORD_SET", "Lcom/intellij/psi/tree/TokenSet;", "Lorg/jetbrains/annotations/NotNull;", "getModifierList", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirModifierList;", "Lorg/jetbrains/kotlin/KtSourceElement;", "contains", Argument.Delimiters.none, "token", "Lorg/jetbrains/kotlin/lexer/KtModifierKeywordToken;", "Lorg/jetbrains/kotlin/fir/FirElement;", "getModifier", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirModifier;", "hasModifier", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "valOrVarKeyword", "Lorg/jetbrains/kotlin/lexer/KtKeywordToken;", "getValOrVarKeyword", "(Lorg/jetbrains/kotlin/KtSourceElement;)Lorg/jetbrains/kotlin/lexer/KtKeywordToken;", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirKeywordUtilsKt {
    private static final TokenSet MODIFIER_KEYWORD_SET;

    static {
        TokenSet tokenSetOrSet = TokenSet.orSet(new TokenSet[]{KtTokens.SOFT_KEYWORDS, TokenSet.create(new IElementType[]{KtTokens.IN_KEYWORD, KtTokens.FUN_KEYWORD})});
        tokenSetOrSet.getClass();
        MODIFIER_KEYWORD_SET = tokenSetOrSet;
    }

    public static final boolean contains(FirModifierList firModifierList, KtModifierKeywordToken ktModifierKeywordToken) {
        ktModifierKeywordToken.getClass();
        return firModifierList != null && firModifierList.contains(ktModifierKeywordToken);
    }

    public static final FirModifier<?> getModifier(FirElement firElement, KtModifierKeywordToken ktModifierKeywordToken) {
        firElement.getClass();
        ktModifierKeywordToken.getClass();
        FirModifierList modifierList = getModifierList(firElement);
        if (modifierList != null) {
            return modifierList.get(ktModifierKeywordToken);
        }
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final FirModifierList getModifierList(KtSourceElement ktSourceElement) throws KotlinIllegalArgumentExceptionWithAttachments {
        Object next;
        KtModifierList modifierList;
        if (ktSourceElement == null) {
            return null;
        }
        if (ktSourceElement instanceof KtPsiSourceElement) {
            KtPsiSourceElement ktPsiSourceElement = (KtPsiSourceElement) ktSourceElement;
            KtModifierListOwner psi = ktPsiSourceElement.getPsi();
            KtModifierListOwner ktModifierListOwner = psi instanceof KtModifierListOwner ? psi : null;
            if (!Intrinsics.areEqual(ktPsiSourceElement.getKind(), KtFakeSourceElementKind.DelegatedPropertyAccessor.INSTANCE) || !(ktModifierListOwner instanceof KtProperty)) {
                if (ktModifierListOwner == null || (modifierList = ktModifierListOwner.getModifierList()) == null) {
                    return null;
                }
                return new FirModifierList.FirPsiModifierList(modifierList);
            }
            KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Don't request modifiers on fake PSI of delegated property accessors, it's not the right PSI", (Throwable) null);
            ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
            ExceptionAttachementBuilderUtilsKt.withPsiEntry(exceptionAttachmentBuilder, "property", ktModifierListOwner);
            kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
            throw kotlinIllegalArgumentExceptionWithAttachments;
        }
        if (!(ktSourceElement instanceof KtLightSourceElement)) {
            bu8.a();
            return null;
        }
        KtLightSourceElement ktLightSourceElement = (KtLightSourceElement) ktSourceElement;
        Iterator it = LightTreeUtilsKt.getChildren(ktLightSourceElement.getLighterASTNode(), ktLightSourceElement.getTreeStructure()).iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((LighterASTNode) next).getTokenType(), KtNodeTypes.MODIFIER_LIST));
        LighterASTNode lighterASTNode = (LighterASTNode) next;
        if (lighterASTNode == null) {
            return null;
        }
        return new FirModifierList.FirLightModifierList(lighterASTNode, ktLightSourceElement.getTreeStructure(), ktLightSourceElement.getStartOffset() - ktLightSourceElement.getLighterASTNode().getStartOffset());
    }

    public static final KtKeywordToken getValOrVarKeyword(KtSourceElement ktSourceElement) {
        PsiElement valOrVarKeyword;
        if (ktSourceElement == null) {
            return null;
        }
        if (ktSourceElement instanceof KtPsiSourceElement) {
            PsiElement psi = ((KtPsiSourceElement) ktSourceElement).getPsi();
            KtValVarKeywordOwner ktValVarKeywordOwner = psi instanceof KtValVarKeywordOwner ? (KtValVarKeywordOwner) psi : null;
            if (ktValVarKeywordOwner != null && (valOrVarKeyword = ktValVarKeywordOwner.getValOrVarKeyword()) != null) {
                ASTNode node = valOrVarKeyword.getNode();
                IElementType elementType = node != null ? node.getElementType() : null;
                if (elementType instanceof KtKeywordToken) {
                    return (KtKeywordToken) elementType;
                }
            }
            return null;
        }
        if (!(ktSourceElement instanceof KtLightSourceElement)) {
            bu8.a();
            return null;
        }
        KtLightSourceElement ktLightSourceElement = (KtLightSourceElement) ktSourceElement;
        LighterASTNode lighterASTNodeValOrVarKeyword = LightTreePositioningStrategiesKt.valOrVarKeyword(ktLightSourceElement.getTreeStructure(), ktLightSourceElement.getLighterASTNode());
        IElementType tokenType = lighterASTNodeValOrVarKeyword != null ? lighterASTNodeValOrVarKeyword.getTokenType() : null;
        if (tokenType instanceof KtKeywordToken) {
            return (KtKeywordToken) tokenType;
        }
        return null;
    }

    public static final boolean hasModifier(FirElement firElement, KtModifierKeywordToken ktModifierKeywordToken) {
        firElement.getClass();
        ktModifierKeywordToken.getClass();
        return contains(getModifierList(firElement), ktModifierKeywordToken);
    }

    public static final boolean hasModifier(FirBasedSymbol<?> firBasedSymbol, KtModifierKeywordToken ktModifierKeywordToken) {
        firBasedSymbol.getClass();
        ktModifierKeywordToken.getClass();
        return hasModifier(firBasedSymbol.getFir(), ktModifierKeywordToken);
    }

    public static final FirModifierList getModifierList(FirElement firElement) {
        firElement.getClass();
        KtSourceElement source = firElement.getSource();
        if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DelegatedPropertyAccessor.INSTANCE)) {
            KtSourceElement source2 = firElement.getSource();
            if (!Intrinsics.areEqual(source2 != null ? source2.getElementType() : null, KtNodeTypes.PROPERTY_ACCESSOR)) {
                return null;
            }
        }
        return getModifierList(firElement.getSource());
    }
}

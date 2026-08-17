package org.jetbrains.kotlin.fir.backend.utils;

import com.intellij.lang.LighterASTNode;
import com.intellij.psi.PsiCompiledElement;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtLightSourceElement;
import org.jetbrains.kotlin.KtPsiSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategyKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCallOrigin;
import org.jetbrains.kotlin.fir.expressions.FirImplicitInvokeCall;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirThisReceiverExpression;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularPropertySymbol;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.psi.KtFile;
import org.jetbrains.kotlin.psi.psiUtil.PsiUtilsKt;
import org.jetbrains.kotlin.util.LightTreeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000p\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u001f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005\u001aW\u0010\u0006\u001a\u0002H\u0007\"\b\b\u0000\u0010\u0007*\u00020\b*\u00020\t26\u0010\n\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u0002H\u00070\u000bH\u0080\bø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001ae\u0010\u0006\u001a\u0002H\u0007\"\b\b\u0000\u0010\u0007*\u00020\b*\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u000126\u0010\n\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u0002H\u00070\u000bH\u0000¢\u0006\u0002\u0010\u0014\u001ac\u0010\u0006\u001a\u0002H\u0007\"\b\b\u0000\u0010\u0007*\u00020\b*\u0004\u0018\u00010\u00152\b\u0010\u0003\u001a\u0004\u0018\u00010\u000426\u0010\n\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u0002H\u00070\u000bH\u0080\bø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001aS\u0010\u0006\u001a\u0002H\u0007\"\b\b\u0000\u0010\u0007*\u00020\b*\u00020\u001726\u0010\n\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u0002H\u00070\u000bH\u0000¢\u0006\u0002\u0010\u0018\u001a\n\u0010\u0019\u001a\u00020\u001a*\u00020\u0017\u001aW\u0010\u0006\u001a\u0002H\u0007\"\b\b\u0000\u0010\u0007*\u00020\b*\u00020\u001b26\u0010\n\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u0002H\u00070\u000bH\u0080\bø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a_\u0010\u0006\u001a\u0002H\u0007\"\b\b\u0000\u0010\u0007*\u00020\b*\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f26\u0010\n\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u0002H\u00070\u000bH\u0080\bø\u0001\u0000¢\u0006\u0002\u0010 \u001a\u0012\u0010!\u001a\u00020\u001a2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0002\u001a\u001a\u0010$\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0001\u0018\u00010%*\u00020&H\u0002\u001a\u001b\u0010*\u001a\u0004\u0018\u00010\u0001*\u00020\u00152\u0006\u0010+\u001a\u00020\u0004H\u0000¢\u0006\u0002\u0010,\"\u0013\u0010'\u001a\u00070\u0004¢\u0006\u0002\b(X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0013\u0010)\u001a\u00070\u0004¢\u0006\u0002\b(X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006-"}, d2 = {"startOffsetSkippingComments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/AbstractKtSourceElement;", "keywordTokens", "Lcom/intellij/psi/tree/TokenSet;", "(Lorg/jetbrains/kotlin/AbstractKtSourceElement;Lcom/intellij/psi/tree/TokenSet;)Ljava/lang/Integer;", "convertWithOffsets", "T", "Lorg/jetbrains/kotlin/ir/IrElement;", "Lorg/jetbrains/kotlin/fir/FirElement;", "f", "Lkotlin/Function2;", "Lkotlin/ParameterName;", ModuleXmlParser.NAME, "startOffset", "endOffset", "(Lorg/jetbrains/kotlin/fir/FirElement;Lkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/ir/IrElement;", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "defaultStartOffset", "defaultEndOffset", "(Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;IILkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/ir/IrElement;", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/KtSourceElement;Lcom/intellij/psi/tree/TokenSet;Lkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/ir/IrElement;", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;Lkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/ir/IrElement;", "shouldUseCalleeReferenceAsItsSourceInIr", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirThisReceiverExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirThisReceiverExpression;Lkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/ir/IrElement;", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "calleeReference", "Lorg/jetbrains/kotlin/fir/references/FirReference;", "(Lorg/jetbrains/kotlin/fir/expressions/FirStatement;Lorg/jetbrains/kotlin/fir/references/FirReference;Lkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/ir/IrElement;", "isCompiledElement", "element", "Lcom/intellij/psi/PsiElement;", "computeOffsetsWithoutInitializer", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "CONSTRUCTOR_KEYWORD_TOKENS", "Lorg/jetbrains/annotations/NotNull;", "FUNCTION_KEYWORD_TOKENS", "getChildTokenStartOffsetOrNull", "tokenSet", "(Lorg/jetbrains/kotlin/KtSourceElement;Lcom/intellij/psi/tree/TokenSet;)Ljava/lang/Integer;", "org.jetbrains.kotlin:fir2ir"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class OffsetUtilsKt {
    private static final TokenSet CONSTRUCTOR_KEYWORD_TOKENS;
    private static final TokenSet FUNCTION_KEYWORD_TOKENS;

    static {
        TokenSet tokenSetCreate = TokenSet.create(new IElementType[]{KtTokens.CONSTRUCTOR_KEYWORD});
        tokenSetCreate.getClass();
        CONSTRUCTOR_KEYWORD_TOKENS = tokenSetCreate;
        TokenSet tokenSetCreate2 = TokenSet.create(new IElementType[]{KtTokens.FUN_KEYWORD});
        tokenSetCreate2.getClass();
        FUNCTION_KEYWORD_TOKENS = tokenSetCreate2;
    }

    public static final /* synthetic */ boolean access$isCompiledElement(PsiElement psiElement) {
        return isCompiledElement(psiElement);
    }

    private static final Pair<Integer, Integer> computeOffsetsWithoutInitializer(FirProperty firProperty) {
        FirExpression initializer;
        KtSourceElement source;
        LighterASTNode lighterASTNode;
        KtSourceElement source2 = firProperty.getSource();
        if (source2 == null || (initializer = firProperty.getInitializer()) == null || (source = initializer.getSource()) == null || (lighterASTNode = source.getLighterASTNode()) == null) {
            return null;
        }
        List children = LightTreeUtilsKt.getChildren(source2.getLighterASTNode(), source2.getTreeStructure());
        int size = children.size();
        LighterASTNode lighterASTNode2 = null;
        for (int i = 0; i < size; i++) {
            LighterASTNode lighterASTNode3 = (LighterASTNode) children.get(i);
            if (Intrinsics.areEqual(lighterASTNode3, lighterASTNode)) {
                break;
            }
            if (!LightTreePositioningStrategyKt.isFiller(lighterASTNode3) && !Intrinsics.areEqual(lighterASTNode3.getTokenType(), KtTokens.EQ)) {
                lighterASTNode2 = lighterASTNode3;
            }
        }
        if (lighterASTNode2 == null) {
            return null;
        }
        int endOffset = lighterASTNode2.getEndOffset();
        Integer numStartOffsetSkippingComments = startOffsetSkippingComments(source2, KtTokens.VAL_VAR);
        return TuplesKt.to(Integer.valueOf(numStartOffsetSkippingComments != null ? numStartOffsetSkippingComments.intValue() : source2.getStartOffset()), Integer.valueOf(endOffset));
    }

    /* JADX WARN: Code duplicated, block: B:45:0x00c2  */
    /* JADX WARN: Multi-variable type inference failed */
    public static final <T extends IrElement> T convertWithOffsets(FirPropertyAccessor firPropertyAccessor, int i, int i2, Function2<? super Integer, ? super Integer, ? extends T> function2) {
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        Pair<Integer, Integer> pairComputeOffsetsWithoutInitializer;
        function2.getClass();
        if (firPropertyAccessor == null) {
            return (T) function2.invoke(Integer.valueOf(i), Integer.valueOf(i2));
        }
        KtSourceElement source = firPropertyAccessor.getSource();
        if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DefaultAccessor.INSTANCE)) {
            FirProperty firProperty = (FirProperty) firPropertyAccessor.getPropertySymbol().getFir();
            if ((firProperty.getSymbol() instanceof FirRegularPropertySymbol) && (pairComputeOffsetsWithoutInitializer = computeOffsetsWithoutInitializer(firProperty)) != null) {
                return (T) function2.invoke(Integer.valueOf(((Number) pairComputeOffsetsWithoutInitializer.component1()).intValue()), Integer.valueOf(((Number) pairComputeOffsetsWithoutInitializer.component2()).intValue()));
            }
        }
        KtSourceElement source2 = firPropertyAccessor.getSource();
        TokenSet tokenSet = KtTokens.VAL_VAR;
        int i3 = -1;
        if (isCompiledElement(KtSourceElementKt.getPsi(source2))) {
            endOffset = -1;
        } else {
            if (Intrinsics.areEqual(source2 != null ? source2.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                endOffset = -1;
            } else {
                if (Intrinsics.areEqual(source2 != null ? source2.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source2 != null ? source2.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        endOffset = -1;
                    } else {
                        if (source2 == null || (numStartOffsetSkippingComments = startOffsetSkippingComments(source2, tokenSet)) == null) {
                            startOffset = source2 != null ? source2.getStartOffset() : -1;
                        } else {
                            startOffset = numStartOffsetSkippingComments.intValue();
                        }
                        endOffset = source2 != null ? source2.getEndOffset() : -1;
                        i3 = startOffset;
                    }
                }
            }
        }
        return (T) function2.invoke(Integer.valueOf(i3), Integer.valueOf(endOffset));
    }

    public static final Integer getChildTokenStartOffsetOrNull(KtSourceElement ktSourceElement, TokenSet tokenSet) {
        Object next;
        ktSourceElement.getClass();
        tokenSet.getClass();
        Iterator it = LightTreeUtilsKt.getChildren(ktSourceElement.getLighterASTNode(), ktSourceElement.getTreeStructure()).iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!tokenSet.contains(((LighterASTNode) next).getTokenType()));
        LighterASTNode lighterASTNode = (LighterASTNode) next;
        if (lighterASTNode != null) {
            return Integer.valueOf(lighterASTNode.getStartOffset());
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isCompiledElement(PsiElement psiElement) {
        if (psiElement == null) {
            return false;
        }
        if (psiElement instanceof PsiCompiledElement) {
            return true;
        }
        KtFile containingFile = psiElement.getContainingFile();
        return !(containingFile instanceof KtFile) || containingFile.isCompiled();
    }

    public static final boolean shouldUseCalleeReferenceAsItsSourceInIr(FirQualifiedAccessExpression firQualifiedAccessExpression) {
        firQualifiedAccessExpression.getClass();
        if (firQualifiedAccessExpression instanceof FirImplicitInvokeCall) {
            return true;
        }
        if (((firQualifiedAccessExpression instanceof FirFunctionCall) && ((FirFunctionCall) firQualifiedAccessExpression).getOrigin() != FirFunctionCallOrigin.Regular) || (firQualifiedAccessExpression instanceof FirCallableReferenceAccess)) {
            return false;
        }
        FirReference calleeReference = firQualifiedAccessExpression.getCalleeReference();
        FirResolvedNamedReference firResolvedNamedReference = calleeReference instanceof FirResolvedNamedReference ? (FirResolvedNamedReference) calleeReference : null;
        return (firResolvedNamedReference != null ? firResolvedNamedReference.getResolvedSymbol() : null) instanceof FirCallableSymbol;
    }

    public static final Integer startOffsetSkippingComments(AbstractKtSourceElement abstractKtSourceElement, TokenSet tokenSet) {
        Integer childTokenStartOffsetOrNull;
        if (tokenSet != null) {
            KtSourceElement ktSourceElement = abstractKtSourceElement instanceof KtSourceElement ? (KtSourceElement) abstractKtSourceElement : null;
            if (ktSourceElement != null && (childTokenStartOffsetOrNull = getChildTokenStartOffsetOrNull(ktSourceElement, tokenSet)) != null) {
                return Integer.valueOf(childTokenStartOffsetOrNull.intValue());
            }
        }
        if (abstractKtSourceElement instanceof KtPsiSourceElement) {
            return Integer.valueOf(PsiUtilsKt.getStartOffsetSkippingComments(((KtPsiSourceElement) abstractKtSourceElement).getPsi()));
        }
        if (abstractKtSourceElement instanceof KtLightSourceElement) {
            return Integer.valueOf(LightTreePositioningStrategyKt.getStartOffsetSkippingComments((KtLightSourceElement) abstractKtSourceElement));
        }
        return null;
    }

    public static /* synthetic */ Integer startOffsetSkippingComments$default(AbstractKtSourceElement abstractKtSourceElement, TokenSet tokenSet, int i, Object obj) {
        if ((i & 1) != 0) {
            tokenSet = null;
        }
        return startOffsetSkippingComments(abstractKtSourceElement, tokenSet);
    }

    /* JADX WARN: Code duplicated, block: B:39:0x007d  */
    public static final <T extends IrElement> T convertWithOffsets(FirElement firElement, Function2<? super Integer, ? super Integer, ? extends T> function2) {
        TokenSet tokenSet;
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        firElement.getClass();
        function2.getClass();
        if (firElement instanceof FirNamedFunction) {
            tokenSet = FUNCTION_KEYWORD_TOKENS;
        } else if (firElement instanceof FirConstructor) {
            tokenSet = CONSTRUCTOR_KEYWORD_TOKENS;
        } else {
            tokenSet = firElement instanceof FirVariable ? KtTokens.VAL_VAR : null;
        }
        KtSourceElement source = firElement.getSource();
        int i = -1;
        if (isCompiledElement(KtSourceElementKt.getPsi(source))) {
            endOffset = -1;
        } else {
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                endOffset = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        endOffset = -1;
                    } else {
                        if (source == null || (numStartOffsetSkippingComments = startOffsetSkippingComments(source, tokenSet)) == null) {
                            startOffset = source != null ? source.getStartOffset() : -1;
                        } else {
                            startOffset = numStartOffsetSkippingComments.intValue();
                        }
                        endOffset = source != null ? source.getEndOffset() : -1;
                        i = startOffset;
                    }
                }
            }
        }
        return (T) function2.invoke(Integer.valueOf(i), Integer.valueOf(endOffset));
    }

    /* JADX WARN: Code duplicated, block: B:30:0x005c  */
    public static final <T extends IrElement> T convertWithOffsets(KtSourceElement ktSourceElement, TokenSet tokenSet, Function2<? super Integer, ? super Integer, ? extends T> function2) {
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        function2.getClass();
        int i = -1;
        if (isCompiledElement(KtSourceElementKt.getPsi(ktSourceElement))) {
            endOffset = -1;
        } else {
            if (Intrinsics.areEqual(ktSourceElement != null ? ktSourceElement.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                endOffset = -1;
            } else {
                if (Intrinsics.areEqual(ktSourceElement != null ? ktSourceElement.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(ktSourceElement != null ? ktSourceElement.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        endOffset = -1;
                    } else {
                        if (ktSourceElement == null || (numStartOffsetSkippingComments = startOffsetSkippingComments(ktSourceElement, tokenSet)) == null) {
                            startOffset = ktSourceElement != null ? ktSourceElement.getStartOffset() : -1;
                        } else {
                            startOffset = numStartOffsetSkippingComments.intValue();
                        }
                        endOffset = ktSourceElement != null ? ktSourceElement.getEndOffset() : -1;
                        i = startOffset;
                    }
                }
            }
        }
        return (T) function2.invoke(Integer.valueOf(i), Integer.valueOf(endOffset));
    }

    /* JADX WARN: Code duplicated, block: B:50:0x00b4  */
    public static final <T extends IrElement> T convertWithOffsets(FirQualifiedAccessExpression firQualifiedAccessExpression, Function2<? super Integer, ? super Integer, ? extends T> function2) {
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        int startOffset2;
        int endOffset2;
        Integer numStartOffsetSkippingComments$default;
        firQualifiedAccessExpression.getClass();
        function2.getClass();
        int i = -1;
        if (shouldUseCalleeReferenceAsItsSourceInIr(firQualifiedAccessExpression)) {
            FirReference calleeReference = firQualifiedAccessExpression.getCalleeReference();
            if (isCompiledElement(UtilsKt.getPsi(firQualifiedAccessExpression))) {
                endOffset2 = -1;
            } else {
                KtSourceElement source = calleeReference.getSource();
                if (source == null || (numStartOffsetSkippingComments$default = startOffsetSkippingComments$default(source, null, 1, null)) == null) {
                    KtSourceElement source2 = calleeReference.getSource();
                    startOffset2 = source2 != null ? source2.getStartOffset() : -1;
                } else {
                    startOffset2 = numStartOffsetSkippingComments$default.intValue();
                }
                KtSourceElement source3 = firQualifiedAccessExpression.getSource();
                endOffset2 = source3 != null ? source3.getEndOffset() : -1;
                i = startOffset2;
            }
            return (T) function2.invoke(Integer.valueOf(i), Integer.valueOf(endOffset2));
        }
        KtSourceElement source4 = firQualifiedAccessExpression.getSource();
        if (isCompiledElement(KtSourceElementKt.getPsi(source4))) {
            endOffset = -1;
        } else {
            if (Intrinsics.areEqual(source4 != null ? source4.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                endOffset = -1;
            } else {
                if (Intrinsics.areEqual(source4 != null ? source4.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source4 != null ? source4.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        endOffset = -1;
                    } else {
                        if (source4 == null || (numStartOffsetSkippingComments = startOffsetSkippingComments(source4, null)) == null) {
                            startOffset = source4 != null ? source4.getStartOffset() : -1;
                        } else {
                            startOffset = numStartOffsetSkippingComments.intValue();
                        }
                        endOffset = source4 != null ? source4.getEndOffset() : -1;
                        i = startOffset;
                    }
                }
            }
        }
        return (T) function2.invoke(Integer.valueOf(i), Integer.valueOf(endOffset));
    }

    /* JADX WARN: Code duplicated, block: B:31:0x0065  */
    public static final <T extends IrElement> T convertWithOffsets(FirThisReceiverExpression firThisReceiverExpression, Function2<? super Integer, ? super Integer, ? extends T> function2) {
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        firThisReceiverExpression.getClass();
        function2.getClass();
        KtSourceElement source = firThisReceiverExpression.getSource();
        int i = -1;
        if (isCompiledElement(KtSourceElementKt.getPsi(source))) {
            endOffset = -1;
        } else {
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                endOffset = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        endOffset = -1;
                    } else {
                        if (source == null || (numStartOffsetSkippingComments = startOffsetSkippingComments(source, null)) == null) {
                            startOffset = source != null ? source.getStartOffset() : -1;
                        } else {
                            startOffset = numStartOffsetSkippingComments.intValue();
                        }
                        endOffset = source != null ? source.getEndOffset() : -1;
                        i = startOffset;
                    }
                }
            }
        }
        return (T) function2.invoke(Integer.valueOf(i), Integer.valueOf(endOffset));
    }

    public static final <T extends IrElement> T convertWithOffsets(FirStatement firStatement, FirReference firReference, Function2<? super Integer, ? super Integer, ? extends T> function2) {
        int startOffset;
        int endOffset;
        Integer numStartOffsetSkippingComments$default;
        firStatement.getClass();
        firReference.getClass();
        function2.getClass();
        int i = -1;
        if (isCompiledElement(UtilsKt.getPsi(firStatement))) {
            endOffset = -1;
        } else {
            KtSourceElement source = firReference.getSource();
            if (source == null || (numStartOffsetSkippingComments$default = startOffsetSkippingComments$default(source, null, 1, null)) == null) {
                KtSourceElement source2 = firReference.getSource();
                startOffset = source2 != null ? source2.getStartOffset() : -1;
            } else {
                startOffset = numStartOffsetSkippingComments$default.intValue();
            }
            KtSourceElement source3 = firStatement.getSource();
            endOffset = source3 != null ? source3.getEndOffset() : -1;
            i = startOffset;
        }
        return (T) function2.invoke(Integer.valueOf(i), Integer.valueOf(endOffset));
    }
}

package org.jetbrains.kotlin.fir.lightTree.converter;

import com.intellij.lang.LighterASTNode;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.ElementTypeUtils;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtLightSourceElement;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.PreprocessCommandLineArgumentsKt;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder;
import org.jetbrains.kotlin.fir.builder.Context;
import org.jetbrains.kotlin.fir.types.FirImplicitTypeRef;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitTypeRefImplWithoutSource;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.psi.KtPsiUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 E2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001EB-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0016\u0010\u0011\u001a\u00020\u0012*\u00020\u00022\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\f\u0010\u001d\u001a\u00020\u001e*\u00020\u0002H\u0016J\u000e\u0010\u001f\u001a\u0004\u0018\u00010\u001a*\u00020\u0002H\u0016J\u000e\u0010 \u001a\u0004\u0018\u00010\u0002*\u00020\u0002H\u0016J\u000e\u0010!\u001a\u0004\u0018\u00010\u0002*\u00020\u0002H\u0016J\u000e\u0010\"\u001a\u0004\u0018\u00010\u0002*\u00020\u0002H\u0016J\f\u0010#\u001a\u0004\u0018\u00010\u0002*\u00020\u0002J\u000e\u0010$\u001a\u0004\u0018\u00010\u0002*\u00020\u0002H\u0002J\u000e\u0010%\u001a\u0004\u0018\u00010\u0002*\u00020\u0002H\u0004J\f\u0010&\u001a\u0004\u0018\u00010\u0002*\u00020\u0002J\u0016\u0010'\u001a\u0004\u0018\u00010\u0002*\u00020\u00022\u0006\u0010(\u001a\u00020\u0016H\u0016J\f\u00107\u001a\u0004\u0018\u00010\u0002*\u00020\u0002J\u001a\u00108\u001a\b\u0012\u0004\u0012\u00020\u000201*\u0004\u0018\u00010\u00022\u0006\u0010(\u001a\u00020\u0016J\u001b\u00109\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020:*\u0004\u0018\u00010\u0002¢\u0006\u0002\u0010;J\u000e\u0010<\u001a\u0004\u0018\u00010\u0002*\u0004\u0018\u00010\u0002J$\u0010=\u001a\u00020>*\u00020\u00022\u0012\u0010?\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020>0@H\u0084\bø\u0001\u0000J<\u0010A\u001a\b\u0012\u0004\u0012\u0002HC0B\"\u0004\b\u0000\u0010C*\u00020\u00022\u001e\u0010?\u001a\u001a\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002HC0B\u0012\u0004\u0012\u00020>0DH\u0084\bø\u0001\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000eX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0018\u0010\u0015\u001a\u00020\u0016*\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0018\u0010\u0019\u001a\u00020\u001a*\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u001c\u0010)\u001a\u0004\u0018\u00010\u0002*\u0004\u0018\u00010\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+R\u001c\u0010,\u001a\u0004\u0018\u00010\u0002*\u0004\u0018\u00010\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b-\u0010+R\u001c\u0010.\u001a\u0004\u0018\u00010\u0002*\u0004\u0018\u00010\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b/\u0010+R\"\u00100\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u000101*\u0004\u0018\u00010\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b2\u00103R\u0018\u00104\u001a\u000205*\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b4\u00106\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006F"}, d2 = {"Lorg/jetbrains/kotlin/fir/lightTree/converter/AbstractLightTreeRawFirBuilder;", "Lorg/jetbrains/kotlin/fir/builder/AbstractRawFirBuilder;", "Lcom/intellij/lang/LighterASTNode;", "baseSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "tree", "Lcom/intellij/util/diff/FlyweightCapableTreeStructure;", "context", "Lorg/jetbrains/kotlin/fir/builder/Context;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lcom/intellij/util/diff/FlyweightCapableTreeStructure;Lorg/jetbrains/kotlin/fir/builder/Context;)V", "getTree", "()Lcom/intellij/util/diff/FlyweightCapableTreeStructure;", "implicitType", "Lorg/jetbrains/kotlin/fir/types/FirImplicitTypeRef;", "getImplicitType", "()Lorg/jetbrains/kotlin/fir/types/FirImplicitTypeRef;", "toFirSourceElement", "Lorg/jetbrains/kotlin/KtLightSourceElement;", "kind", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", "elementType", "Lcom/intellij/psi/tree/IElementType;", "getElementType", "(Lcom/intellij/lang/LighterASTNode;)Lcom/intellij/psi/tree/IElementType;", "asText", Argument.Delimiters.none, "getAsText", "(Lcom/intellij/lang/LighterASTNode;)Ljava/lang/String;", "getReferencedNameAsName", "Lorg/jetbrains/kotlin/name/Name;", "getLabelName", "getExpressionInParentheses", "getAnnotatedExpression", "getLabeledExpression", "getChildExpression", "getFirstChildExpression", "getFirstChildExpressionUnwrapped", "getLastChildExpression", "getChildNodeByType", ModuleXmlParser.TYPE, "receiverExpression", "getReceiverExpression", "(Lcom/intellij/lang/LighterASTNode;)Lcom/intellij/lang/LighterASTNode;", "selectorExpression", "getSelectorExpression", "arrayExpression", "getArrayExpression", "indexExpressions", Argument.Delimiters.none, "getIndexExpressions", "(Lcom/intellij/lang/LighterASTNode;)Ljava/util/List;", "isVararg", Argument.Delimiters.none, "(Lcom/intellij/lang/LighterASTNode;)Z", "getParent", "getChildNodesByType", "getChildrenAsArray", Argument.Delimiters.none, "(Lcom/intellij/lang/LighterASTNode;)[Lcom/intellij/lang/LighterASTNode;", "getFirstChild", "forEachChildren", Argument.Delimiters.none, "f", "Lkotlin/Function1;", "forEachChildrenReturnList", Argument.Delimiters.none, "T", "Lkotlin/Function2;", "Companion", "org.jetbrains.kotlin.fir:light-tree2fir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractLightTreeRawFirBuilder extends AbstractRawFirBuilder<LighterASTNode> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final TokenSet ignoredTokens;
    private final FirImplicitTypeRef implicitType;
    private final FlyweightCapableTreeStructure<LighterASTNode> tree;

    static {
        TokenSet tokenSetOrSet = TokenSet.orSet(new TokenSet[]{KtTokens.COMMENTS, TokenSet.create(new IElementType[]{KtTokens.WHITE_SPACE, KtTokens.SEMICOLON, TokenType.ERROR_ELEMENT, TokenType.BAD_CHARACTER})});
        tokenSetOrSet.getClass();
        ignoredTokens = tokenSetOrSet;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractLightTreeRawFirBuilder(FirSession firSession, FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, Context<LighterASTNode> context) {
        super(firSession, context);
        firSession.getClass();
        flyweightCapableTreeStructure.getClass();
        context.getClass();
        this.tree = flyweightCapableTreeStructure;
        this.implicitType = FirImplicitTypeRefImplWithoutSource.INSTANCE;
    }

    private final LighterASTNode getFirstChildExpression(LighterASTNode lighterASTNode) {
        for (LighterASTNode lighterASTNode2 : getChildrenAsArray(lighterASTNode)) {
            if (lighterASTNode2 == null) {
                return null;
            }
            if (!INSTANCE.getIgnoredTokens().contains(lighterASTNode2.getTokenType()) && ElementTypeUtils.INSTANCE.isExpression(lighterASTNode2)) {
                return lighterASTNode2;
            }
        }
        return null;
    }

    public final void forEachChildren(LighterASTNode lighterASTNode, Function1<? super LighterASTNode, Unit> function1) {
        lighterASTNode.getClass();
        function1.getClass();
        for (LighterASTNode lighterASTNode2 : getChildrenAsArray(lighterASTNode)) {
            if (lighterASTNode2 == null) {
                return;
            }
            if (!INSTANCE.getIgnoredTokens().contains(lighterASTNode2.getTokenType())) {
                function1.invoke(lighterASTNode2);
            }
        }
    }

    public final <T> List<T> forEachChildrenReturnList(LighterASTNode lighterASTNode, Function2<? super LighterASTNode, ? super List<T>, Unit> function2) {
        lighterASTNode.getClass();
        function2.getClass();
        LighterASTNode[] childrenAsArray = getChildrenAsArray(lighterASTNode);
        ArrayList arrayList = new ArrayList();
        for (LighterASTNode lighterASTNode2 : childrenAsArray) {
            if (lighterASTNode2 == null) {
                break;
            }
            if (!INSTANCE.getIgnoredTokens().contains(lighterASTNode2.getTokenType())) {
                function2.invoke(lighterASTNode2, arrayList);
            }
        }
        return arrayList;
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public LighterASTNode getAnnotatedExpression(LighterASTNode lighterASTNode) {
        lighterASTNode.getClass();
        return getFirstChildExpression(lighterASTNode);
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public LighterASTNode getArrayExpression(LighterASTNode lighterASTNode) {
        if (lighterASTNode != null) {
            return getFirstChildExpression(lighterASTNode);
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public String getAsText(LighterASTNode lighterASTNode) {
        lighterASTNode.getClass();
        return lighterASTNode.toString();
    }

    public final LighterASTNode getChildExpression(LighterASTNode lighterASTNode) {
        lighterASTNode.getClass();
        return getFirstChildExpression(lighterASTNode);
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public LighterASTNode getChildNodeByType(LighterASTNode lighterASTNode, IElementType iElementType) {
        lighterASTNode.getClass();
        iElementType.getClass();
        LighterASTNode[] childrenAsArray = getChildrenAsArray(lighterASTNode);
        int length = childrenAsArray.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                return null;
            }
            LighterASTNode lighterASTNode2 = childrenAsArray[i];
            if (Intrinsics.areEqual(lighterASTNode2 != null ? lighterASTNode2.getTokenType() : null, iElementType)) {
                return lighterASTNode2;
            }
            i++;
        }
    }

    public final List<LighterASTNode> getChildNodesByType(LighterASTNode lighterASTNode, IElementType iElementType) {
        iElementType.getClass();
        if (lighterASTNode == null) {
            return CollectionsKt.emptyList();
        }
        LighterASTNode[] childrenAsArray = getChildrenAsArray(lighterASTNode);
        ArrayList arrayList = new ArrayList();
        for (LighterASTNode lighterASTNode2 : childrenAsArray) {
            if (lighterASTNode2 == null) {
                break;
            }
            if (!INSTANCE.getIgnoredTokens().contains(lighterASTNode2.getTokenType()) && Intrinsics.areEqual(lighterASTNode2.getTokenType(), iElementType)) {
                arrayList.add(lighterASTNode2);
            }
        }
        return arrayList;
    }

    public final LighterASTNode[] getChildrenAsArray(LighterASTNode lighterASTNode) {
        if (lighterASTNode == null) {
            return new LighterASTNode[0];
        }
        Ref ref = new Ref();
        this.tree.getChildren(lighterASTNode, ref);
        Object obj = ref.get();
        obj.getClass();
        return (LighterASTNode[]) obj;
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public IElementType getElementType(LighterASTNode lighterASTNode) {
        lighterASTNode.getClass();
        IElementType tokenType = lighterASTNode.getTokenType();
        tokenType.getClass();
        return tokenType;
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public LighterASTNode getExpressionInParentheses(LighterASTNode lighterASTNode) {
        lighterASTNode.getClass();
        return getFirstChildExpression(lighterASTNode);
    }

    public final LighterASTNode getFirstChild(LighterASTNode lighterASTNode) {
        return (LighterASTNode) ArraysKt.firstOrNull(getChildrenAsArray(lighterASTNode));
    }

    public final LighterASTNode getFirstChildExpressionUnwrapped(LighterASTNode lighterASTNode) {
        lighterASTNode.getClass();
        LighterASTNode firstChildExpression = getFirstChildExpression(lighterASTNode);
        if (firstChildExpression == null) {
            return null;
        }
        return Intrinsics.areEqual(firstChildExpression.getTokenType(), KtNodeTypes.PARENTHESIZED) ? getFirstChildExpressionUnwrapped(firstChildExpression) : firstChildExpression;
    }

    public final FirImplicitTypeRef getImplicitType() {
        return this.implicitType;
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public List<LighterASTNode> getIndexExpressions(LighterASTNode lighterASTNode) {
        LighterASTNode lastChildExpression;
        LighterASTNode[] childrenAsArray;
        List listFilterNotNull;
        if (lighterASTNode == null || (lastChildExpression = getLastChildExpression(lighterASTNode)) == null || (childrenAsArray = getChildrenAsArray(lastChildExpression)) == null || (listFilterNotNull = ArraysKt.filterNotNull(childrenAsArray)) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : listFilterNotNull) {
            if (ElementTypeUtils.INSTANCE.isExpression((LighterASTNode) obj)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public String getLabelName(LighterASTNode lighterASTNode) {
        lighterASTNode.getClass();
        if (Intrinsics.areEqual(lighterASTNode.getTokenType(), KtNodeTypes.FUN)) {
            LighterASTNode parent = getParent(lighterASTNode);
            if (parent != null) {
                return getLabelName(parent);
            }
            return null;
        }
        for (LighterASTNode lighterASTNode2 : getChildrenAsArray(lighterASTNode)) {
            if (lighterASTNode2 == null) {
                break;
            }
            if (!INSTANCE.getIgnoredTokens().contains(lighterASTNode2.getTokenType()) && Intrinsics.areEqual(lighterASTNode2.getTokenType(), KtNodeTypes.LABEL_QUALIFIER)) {
                return KtPsiUtil.unquoteIdentifier(StringsKt.replaceFirst$default(getAsText(lighterASTNode2), PreprocessCommandLineArgumentsKt.ARGFILE_ARGUMENT, Argument.Delimiters.none, false, 4, (Object) null));
            }
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public LighterASTNode getLabeledExpression(LighterASTNode lighterASTNode) {
        lighterASTNode.getClass();
        return getLastChildExpression(lighterASTNode);
    }

    public final LighterASTNode getLastChildExpression(LighterASTNode lighterASTNode) {
        lighterASTNode.getClass();
        LighterASTNode lighterASTNode2 = null;
        for (LighterASTNode lighterASTNode3 : getChildrenAsArray(lighterASTNode)) {
            if (lighterASTNode3 == null) {
                break;
            }
            if (!INSTANCE.getIgnoredTokens().contains(lighterASTNode3.getTokenType()) && ElementTypeUtils.INSTANCE.isExpression(lighterASTNode3)) {
                lighterASTNode2 = lighterASTNode3;
            }
        }
        return lighterASTNode2;
    }

    public final LighterASTNode getParent(LighterASTNode lighterASTNode) {
        lighterASTNode.getClass();
        return (LighterASTNode) this.tree.getParent(lighterASTNode);
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public LighterASTNode getReceiverExpression(LighterASTNode lighterASTNode) {
        if (lighterASTNode != null) {
            LighterASTNode lighterASTNode2 = null;
            for (LighterASTNode lighterASTNode3 : getChildrenAsArray(lighterASTNode)) {
                if (lighterASTNode3 == null) {
                    break;
                }
                if (!INSTANCE.getIgnoredTokens().contains(lighterASTNode3.getTokenType())) {
                    IElementType tokenType = lighterASTNode3.getTokenType();
                    if (Intrinsics.areEqual(tokenType, KtTokens.DOT) || Intrinsics.areEqual(tokenType, KtTokens.SAFE_ACCESS)) {
                        if (Intrinsics.areEqual(lighterASTNode2 != null ? getElementType(lighterASTNode2) : null, TokenType.ERROR_ELEMENT)) {
                            return null;
                        }
                        return lighterASTNode2;
                    }
                    lighterASTNode2 = lighterASTNode3;
                }
            }
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public Name getReferencedNameAsName(LighterASTNode lighterASTNode) {
        lighterASTNode.getClass();
        return ConverterUtilKt.nameAsSafeName$default(getAsText(lighterASTNode), null, 1, null);
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public LighterASTNode getSelectorExpression(LighterASTNode lighterASTNode) {
        if (lighterASTNode != null) {
            boolean z = false;
            for (LighterASTNode lighterASTNode2 : getChildrenAsArray(lighterASTNode)) {
                if (lighterASTNode2 == null) {
                    break;
                }
                if (!INSTANCE.getIgnoredTokens().contains(lighterASTNode2.getTokenType())) {
                    IElementType tokenType = lighterASTNode2.getTokenType();
                    if (Intrinsics.areEqual(tokenType, KtTokens.DOT) || Intrinsics.areEqual(tokenType, KtTokens.SAFE_ACCESS)) {
                        z = true;
                    } else if (z) {
                        if (Intrinsics.areEqual(getElementType(lighterASTNode2), TokenType.ERROR_ELEMENT)) {
                            return null;
                        }
                        return lighterASTNode2;
                    }
                }
            }
        }
        return null;
    }

    public final FlyweightCapableTreeStructure<LighterASTNode> getTree() {
        return this.tree;
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public boolean isVararg(LighterASTNode lighterASTNode) {
        LighterASTNode childNodeByType;
        lighterASTNode.getClass();
        IElementType iElementType = KtNodeTypes.MODIFIER_LIST;
        iElementType.getClass();
        LighterASTNode childNodeByType2 = getChildNodeByType(lighterASTNode, iElementType);
        if (childNodeByType2 != null) {
            KtModifierKeywordToken ktModifierKeywordToken = KtTokens.VARARG_KEYWORD;
            ktModifierKeywordToken.getClass();
            childNodeByType = getChildNodeByType(childNodeByType2, (IElementType) ktModifierKeywordToken);
        } else {
            childNodeByType = null;
        }
        return childNodeByType != null;
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public KtLightSourceElement toFirSourceElement(LighterASTNode lighterASTNode, KtFakeSourceElementKind ktFakeSourceElementKind) {
        lighterASTNode.getClass();
        int startOffset = this.tree.getStartOffset(lighterASTNode);
        int endOffset = this.tree.getEndOffset(lighterASTNode);
        FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure = this.tree;
        if (ktFakeSourceElementKind == null) {
            ktFakeSourceElementKind = KtRealSourceElementKind.INSTANCE;
        }
        return new KtLightSourceElement(lighterASTNode, startOffset, endOffset, flyweightCapableTreeStructure, ktFakeSourceElementKind);
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/lightTree/converter/AbstractLightTreeRawFirBuilder$Companion;", Argument.Delimiters.none, "<init>", "()V", "ignoredTokens", "Lcom/intellij/psi/tree/TokenSet;", "getIgnoredTokens", "()Lcom/intellij/psi/tree/TokenSet;", "org.jetbrains.kotlin.fir:light-tree2fir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final TokenSet getIgnoredTokens() {
            return AbstractLightTreeRawFirBuilder.ignoredTokens;
        }

        private Companion() {
        }
    }

    public /* synthetic */ AbstractLightTreeRawFirBuilder(FirSession firSession, FlyweightCapableTreeStructure flyweightCapableTreeStructure, Context context, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, flyweightCapableTreeStructure, (i & 4) != 0 ? new Context() : context);
    }
}

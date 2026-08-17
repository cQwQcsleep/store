package org.jetbrains.kotlin.diagnostics;

import com.intellij.lang.LighterASTNode;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.ElementTypeUtils;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategiesKt;
import org.jetbrains.kotlin.lexer.KtKeywordToken;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;
import org.jetbrains.kotlin.lexer.KtSingleValueToken;
import org.jetbrains.kotlin.lexer.KtToken;
import org.jetbrains.kotlin.lexer.KtTokens;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000d\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0011\n\u0002\b\u000f\n\u0002\u0010 \n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0004\u001a\u00020\u0001*\u00020\u0002\u001a\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006H\u0002\u001a\u001c\u0010\t\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006H\u0002\u001a\u001c\u0010\n\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006H\u0002\u001a\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006H\u0002\u001a\u001c\u0010\f\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006H\u0002\u001a\u001c\u0010\r\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006H\u0002\u001a\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006H\u0002\u001a\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006H\u0002\u001a\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006H\u0002\u001a\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006H\u0002\u001a\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006H\u0002\u001a\u001a\u0010\u0013\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006\u001a\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006H\u0002\u001a\n\u0010\u0015\u001a\u00020\u0001*\u00020\u0006\u001a%\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0017*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006¢\u0006\u0002\u0010\u0018\u001a$\u0010\u0019\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0001H\u0002\u001a\u0018\u0010\u001b\u001a\u00020\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006\u001a\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006H\u0002\u001a\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006H\u0002\u001a\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006H\u0002\u001a\u001a\u0010\u001f\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006\u001a\u001a\u0010 \u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010!\u001a\u00020\u0006\u001a\u001a\u0010\"\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010!\u001a\u00020\u0006\u001a\u001a\u0010#\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010!\u001a\u00020\u0006\u001a\u001a\u0010$\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010!\u001a\u00020\u0006\u001a\u001a\u0010%\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010!\u001a\u00020\u0006\u001a \u0010&\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010'*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006\u001a\u001a\u0010(\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006\u001a\u001c\u0010)\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006H\u0002\u001a\u001c\u0010*\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006H\u0002\u001a\u001c\u0010+\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006H\u0002\u001a\u001a\u0010,\u001a\u00020\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006H\u0002\u001a\u001c\u0010-\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006H\u0002\u001a\u001c\u0010.\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006H\u0002\u001a\u001c\u0010/\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006H\u0002\u001a \u00100\u001a\b\u0012\u0004\u0012\u00020\u00060'*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006H\u0002\u001a\u001c\u00101\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006H\u0002\u001a\u001c\u00102\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006H\u0002\u001a/\u00103\u001a\u0002042%\u00105\u001a!\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0007\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u000606¢\u0006\u0002\b7H\u0002\u001a\u001c\u00108\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006H\u0002\u001a\u001a\u00109\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006\u001a\u001a\u0010:\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006\u001a\u001a\u0010;\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006\u001a\"\u0010<\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010=\u001a\u00020>\u001a&\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00060'*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010=\u001a\u00020>\u001a\"\u0010@\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010=\u001a\u00020>\u001a,\u0010A\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010=\u001a\u00020>2\b\b\u0002\u0010B\u001a\u00020\u0001\u001a\"\u0010C\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010D\u001a\u00020E\u001a.\u0010F\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u00062\u0012\u0010G\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010H\u001a.\u0010I\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u00062\u0012\u0010G\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010H\u001a<\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00060'*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010=\u001a\u00020>2\u0014\b\u0002\u0010G\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010H\u001a,\u0010K\u001a\u00020L*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u00062\u0012\u0010M\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010H\u001a\"\u0010<\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010=\u001a\u00020E\u001a.\u0010N\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010=\u001a\u00020>2\b\b\u0002\u0010O\u001a\u00020\u0001H\u0002\u001a\u001e\u0010P\u001a\b\u0012\u0004\u0012\u00020\u00060Q*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006\u001a\u001c\u0010R\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006H\u0002\u001a\u001c\u0010S\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00060\u00072\u0006\u0010\b\u001a\u00020\u0006H\u0002\u001a$\u0010T\u001a\u0004\u0018\u00010\u0006*\u00020\u00062\u0006\u0010U\u001a\u00020>2\f\u0010V\u001a\b\u0012\u0004\u0012\u00020\u00060\u0007H\u0002¨\u0006W"}, d2 = {"hasValOrVar", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/KtSourceElement;", "hasVar", "hasPrimaryConstructor", "companionKeyword", "Lcom/intellij/lang/LighterASTNode;", "Lcom/intellij/util/diff/FlyweightCapableTreeStructure;", "node", "constructorKeyword", "dotOperator", "safeAccess", "initKeyword", "whenKeyword", "ifKeyword", "elseKeyword", "returnKeyword", "fieldKeyword", "byKeyword", "nameIdentifier", "operationReference", "isExpression", "getChildrenArray", Argument.Delimiters.none, "(Lcom/intellij/util/diff/FlyweightCapableTreeStructure;Lcom/intellij/lang/LighterASTNode;)[Lcom/intellij/lang/LighterASTNode;", "referenceExpression", "locateReferencedName", "unwrapParenthesesLabelsAndAnnotations", "findExpressionDeep", "rightParenthesis", "objectKeyword", "valOrVarKeyword", "visibilityModifier", "declaration", "modalityModifier", "overrideModifier", "inlineModifier", "typeParametersList", "annotations", Argument.Delimiters.none, "userType", "supertypesList", "getter", "setter", "accessorNamePlaceholder", "modifierList", "primaryConstructor", "valueParameterList", "valueParameters", "typeReference", "receiverTypeReference", "keywordStrategy", "Lorg/jetbrains/kotlin/diagnostics/LightTreePositioningStrategy;", "keywordExtractor", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "defaultValue", "selector", "firstChildExpression", "lastChildExpression", "findChildByType", ModuleXmlParser.TYPE, "Lcom/intellij/psi/tree/IElementType;", "findChildrenByType", "findLastChildByType", "findDescendantByType", "followFunctions", "findDescendantByTypes", "types", "Lcom/intellij/psi/tree/TokenSet;", "findFirstDescendant", "predicate", "Lkotlin/Function1;", "findLastDescendant", "collectDescendantsOfType", "traverseDescendants", Argument.Delimiters.none, "acceptor", "findParentOfType", "strict", "getAncestors", "Lkotlin/sequences/Sequence;", "firstChild", "lastChild", "getParentIfTypeIs", "tokenType", "tree", "org.jetbrains.kotlin:frontend.common-psi"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class LightTreePositioningStrategiesKt {
    public static LighterASTNode a(FlyweightCapableTreeStructure flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        lighterASTNode.getClass();
        return (LighterASTNode) flyweightCapableTreeStructure.getParent(lighterASTNode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LighterASTNode accessorNamePlaceholder(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        KtKeywordToken ktKeywordToken = KtTokens.GET_KEYWORD;
        ktKeywordToken.getClass();
        LighterASTNode lighterASTNodeFindChildByType = findChildByType(flyweightCapableTreeStructure, lighterASTNode, (IElementType) ktKeywordToken);
        if (lighterASTNodeFindChildByType != null) {
            return lighterASTNodeFindChildByType;
        }
        KtKeywordToken ktKeywordToken2 = KtTokens.SET_KEYWORD;
        ktKeywordToken2.getClass();
        LighterASTNode lighterASTNodeFindChildByType2 = findChildByType(flyweightCapableTreeStructure, lighterASTNode, (IElementType) ktKeywordToken2);
        lighterASTNodeFindChildByType2.getClass();
        return lighterASTNodeFindChildByType2;
    }

    public static final List<LighterASTNode> annotations(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        LighterASTNode lighterASTNodeModifierList;
        flyweightCapableTreeStructure.getClass();
        lighterASTNode.getClass();
        IElementType iElementType = KtNodeTypes.TYPE_REFERENCE;
        iElementType.getClass();
        LighterASTNode lighterASTNodeFindChildByType = findChildByType(flyweightCapableTreeStructure, lighterASTNode, iElementType);
        if (lighterASTNodeFindChildByType == null || (lighterASTNodeModifierList = modifierList(flyweightCapableTreeStructure, lighterASTNodeFindChildByType)) == null) {
            return null;
        }
        IElementType iElementType2 = KtNodeTypes.ANNOTATION_ENTRY;
        iElementType2.getClass();
        return collectDescendantsOfType$default(flyweightCapableTreeStructure, lighterASTNodeModifierList, iElementType2, null, 4, null);
    }

    public static boolean b(LighterASTNode lighterASTNode) {
        lighterASTNode.getClass();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LighterASTNode byKeyword(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        KtKeywordToken ktKeywordToken = KtTokens.BY_KEYWORD;
        ktKeywordToken.getClass();
        return findChildByType(flyweightCapableTreeStructure, lighterASTNode, (IElementType) ktKeywordToken);
    }

    public static boolean c(LighterASTNode lighterASTNode) {
        lighterASTNode.getClass();
        return ElementTypeUtils.INSTANCE.isExpression(lighterASTNode);
    }

    public static final List<LighterASTNode> collectDescendantsOfType(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode, IElementType iElementType, Function1<? super LighterASTNode, Boolean> function1) {
        flyweightCapableTreeStructure.getClass();
        lighterASTNode.getClass();
        iElementType.getClass();
        function1.getClass();
        ArrayList arrayList = new ArrayList();
        collectDescendantsOfType$collectDescendantByType(flyweightCapableTreeStructure, iElementType, function1, arrayList, lighterASTNode);
        return arrayList;
    }

    private static final void collectDescendantsOfType$collectDescendantByType(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, IElementType iElementType, Function1<? super LighterASTNode, Boolean> function1, List<LighterASTNode> list, LighterASTNode lighterASTNode) {
        Ref ref = new Ref();
        flyweightCapableTreeStructure.getChildren(lighterASTNode, ref);
        LighterASTNode[] lighterASTNodeArr = (LighterASTNode[]) ref.get();
        if (lighterASTNodeArr != null) {
            int length = lighterASTNodeArr.length;
            for (int i = 0; i < length; i++) {
                LighterASTNode lighterASTNode2 = lighterASTNodeArr[i];
                if (Intrinsics.areEqual(lighterASTNode2 != null ? lighterASTNode2.getTokenType() : null, iElementType) && ((Boolean) function1.invoke(lighterASTNode2)).booleanValue()) {
                    list.add(lighterASTNode2);
                }
                if (lighterASTNode2 != null) {
                    collectDescendantsOfType$collectDescendantByType(flyweightCapableTreeStructure, iElementType, function1, list, lighterASTNode2);
                }
            }
        }
    }

    public static /* synthetic */ List collectDescendantsOfType$default(FlyweightCapableTreeStructure flyweightCapableTreeStructure, LighterASTNode lighterASTNode, IElementType iElementType, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            function1 = new Function1() { // from class: e19
                public final Object invoke(Object obj2) {
                    return Boolean.valueOf(LightTreePositioningStrategiesKt.b((LighterASTNode) obj2));
                }
            };
        }
        return collectDescendantsOfType(flyweightCapableTreeStructure, lighterASTNode, iElementType, function1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LighterASTNode companionKeyword(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        LighterASTNode lighterASTNodeModifierList = modifierList(flyweightCapableTreeStructure, lighterASTNode);
        if (lighterASTNodeModifierList == null) {
            return null;
        }
        KtModifierKeywordToken ktModifierKeywordToken = KtTokens.COMPANION_KEYWORD;
        ktModifierKeywordToken.getClass();
        return findChildByType(flyweightCapableTreeStructure, lighterASTNodeModifierList, (IElementType) ktModifierKeywordToken);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LighterASTNode constructorKeyword(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        KtKeywordToken ktKeywordToken = KtTokens.CONSTRUCTOR_KEYWORD;
        ktKeywordToken.getClass();
        return findChildByType(flyweightCapableTreeStructure, lighterASTNode, (IElementType) ktKeywordToken);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LighterASTNode defaultValue(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        List<LighterASTNode> listReversed;
        Ref ref = new Ref();
        flyweightCapableTreeStructure.getChildren(lighterASTNode, ref);
        LighterASTNode[] lighterASTNodeArr = (LighterASTNode[]) ref.get();
        if (lighterASTNodeArr != null && (listReversed = ArraysKt.reversed(lighterASTNodeArr)) != null) {
            for (LighterASTNode lighterASTNode2 : listReversed) {
                if (lighterASTNode2 != null && !Intrinsics.areEqual(lighterASTNode2.getTokenType(), KtTokens.WHITE_SPACE)) {
                    if (Intrinsics.areEqual(lighterASTNode2.getTokenType(), KtNodeTypes.TYPE_REFERENCE) || Intrinsics.areEqual(lighterASTNode2.getTokenType(), KtTokens.COLON)) {
                        break;
                    }
                    return lighterASTNode2;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LighterASTNode dotOperator(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        KtSingleValueToken ktSingleValueToken = KtTokens.DOT;
        ktSingleValueToken.getClass();
        return findChildByType(flyweightCapableTreeStructure, lighterASTNode, (IElementType) ktSingleValueToken);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LighterASTNode elseKeyword(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        KtKeywordToken ktKeywordToken = KtTokens.ELSE_KEYWORD;
        ktKeywordToken.getClass();
        return findChildByType(flyweightCapableTreeStructure, lighterASTNode, (IElementType) ktKeywordToken);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LighterASTNode fieldKeyword(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        KtKeywordToken ktKeywordToken = KtTokens.FIELD_KEYWORD;
        ktKeywordToken.getClass();
        return findChildByType(flyweightCapableTreeStructure, lighterASTNode, (IElementType) ktKeywordToken);
    }

    public static final LighterASTNode findChildByType(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode, IElementType iElementType) {
        flyweightCapableTreeStructure.getClass();
        lighterASTNode.getClass();
        iElementType.getClass();
        Ref ref = new Ref();
        flyweightCapableTreeStructure.getChildren(lighterASTNode, ref);
        LighterASTNode[] lighterASTNodeArr = (LighterASTNode[]) ref.get();
        if (lighterASTNodeArr != null) {
            int length = lighterASTNodeArr.length;
            for (int i = 0; i < length; i++) {
                LighterASTNode lighterASTNode2 = lighterASTNodeArr[i];
                if (Intrinsics.areEqual(lighterASTNode2 != null ? lighterASTNode2.getTokenType() : null, iElementType)) {
                    return lighterASTNode2;
                }
            }
        }
        return null;
    }

    public static final List<LighterASTNode> findChildrenByType(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode, IElementType iElementType) {
        flyweightCapableTreeStructure.getClass();
        lighterASTNode.getClass();
        iElementType.getClass();
        Ref ref = new Ref();
        flyweightCapableTreeStructure.getChildren(lighterASTNode, ref);
        LighterASTNode[] lighterASTNodeArr = (LighterASTNode[]) ref.get();
        List<LighterASTNode> listFilterNotNull = null;
        if (lighterASTNodeArr != null) {
            ArrayList arrayList = new ArrayList();
            int length = lighterASTNodeArr.length;
            for (int i = 0; i < length; i++) {
                LighterASTNode lighterASTNode2 = lighterASTNodeArr[i];
                if (Intrinsics.areEqual(lighterASTNode2 != null ? lighterASTNode2.getTokenType() : null, iElementType)) {
                    arrayList.add(lighterASTNode2);
                }
            }
            listFilterNotNull = CollectionsKt.filterNotNull(arrayList);
        }
        return listFilterNotNull == null ? CollectionsKt.emptyList() : listFilterNotNull;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x005a  */
    public static final LighterASTNode findDescendantByType(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode, IElementType iElementType, boolean z) {
        boolean z2;
        LighterASTNode lighterASTNode2;
        flyweightCapableTreeStructure.getClass();
        lighterASTNode.getClass();
        iElementType.getClass();
        Ref ref = new Ref();
        flyweightCapableTreeStructure.getChildren(lighterASTNode, ref);
        LighterASTNode[] lighterASTNodeArr = (LighterASTNode[]) ref.get();
        if (lighterASTNodeArr != null) {
            int length = lighterASTNodeArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    lighterASTNode2 = null;
                    break;
                }
                lighterASTNode2 = lighterASTNodeArr[i];
                if (Intrinsics.areEqual(lighterASTNode2 != null ? lighterASTNode2.getTokenType() : null, iElementType)) {
                    break;
                }
                i++;
            }
            if (lighterASTNode2 != null) {
                return lighterASTNode2;
            }
        }
        LighterASTNode[] lighterASTNodeArr2 = (LighterASTNode[]) ref.get();
        if (lighterASTNodeArr2 != null) {
            int length2 = lighterASTNodeArr2.length;
            for (int i2 = 0; i2 < length2; i2++) {
                LighterASTNode lighterASTNode3 = lighterASTNodeArr2[i2];
                if (z) {
                    z2 = false;
                } else if (Intrinsics.areEqual(lighterASTNode3 != null ? lighterASTNode3.getTokenType() : null, KtNodeTypes.FUN)) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                LighterASTNode lighterASTNodeFindDescendantByType = (z2 || lighterASTNode3 == null) ? null : findDescendantByType(flyweightCapableTreeStructure, lighterASTNode3, iElementType, z);
                if (lighterASTNodeFindDescendantByType != null) {
                    return lighterASTNodeFindDescendantByType;
                }
            }
        }
        return null;
    }

    public static /* synthetic */ LighterASTNode findDescendantByType$default(FlyweightCapableTreeStructure flyweightCapableTreeStructure, LighterASTNode lighterASTNode, IElementType iElementType, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        return findDescendantByType(flyweightCapableTreeStructure, lighterASTNode, iElementType, z);
    }

    public static final LighterASTNode findDescendantByTypes(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode, TokenSet tokenSet) {
        LighterASTNode lighterASTNode2;
        flyweightCapableTreeStructure.getClass();
        lighterASTNode.getClass();
        tokenSet.getClass();
        Ref ref = new Ref();
        flyweightCapableTreeStructure.getChildren(lighterASTNode, ref);
        LighterASTNode[] lighterASTNodeArr = (LighterASTNode[]) ref.get();
        if (lighterASTNodeArr != null) {
            int length = lighterASTNodeArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    lighterASTNode2 = null;
                    break;
                }
                lighterASTNode2 = lighterASTNodeArr[i];
                if (tokenSet.contains(lighterASTNode2 != null ? lighterASTNode2.getTokenType() : null)) {
                    break;
                }
                i++;
            }
            if (lighterASTNode2 != null) {
                return lighterASTNode2;
            }
        }
        LighterASTNode[] lighterASTNodeArr2 = (LighterASTNode[]) ref.get();
        if (lighterASTNodeArr2 != null) {
            int length2 = lighterASTNodeArr2.length;
            for (int i2 = 0; i2 < length2; i2++) {
                LighterASTNode lighterASTNode3 = lighterASTNodeArr2[i2];
                LighterASTNode lighterASTNodeFindDescendantByTypes = lighterASTNode3 != null ? findDescendantByTypes(flyweightCapableTreeStructure, lighterASTNode3, tokenSet) : null;
                if (lighterASTNodeFindDescendantByTypes != null) {
                    return lighterASTNodeFindDescendantByTypes;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LighterASTNode findExpressionDeep(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        return findFirstDescendant(flyweightCapableTreeStructure, lighterASTNode, new Function1() { // from class: d19
            public final Object invoke(Object obj) {
                return Boolean.valueOf(LightTreePositioningStrategiesKt.c((LighterASTNode) obj));
            }
        });
    }

    public static final LighterASTNode findFirstDescendant(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode, Function1<? super LighterASTNode, Boolean> function1) {
        LighterASTNode lighterASTNode2;
        flyweightCapableTreeStructure.getClass();
        lighterASTNode.getClass();
        function1.getClass();
        Ref ref = new Ref();
        flyweightCapableTreeStructure.getChildren(lighterASTNode, ref);
        LighterASTNode[] lighterASTNodeArr = (LighterASTNode[]) ref.get();
        if (lighterASTNodeArr != null) {
            int length = lighterASTNodeArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    lighterASTNode2 = null;
                    break;
                }
                lighterASTNode2 = lighterASTNodeArr[i];
                if (lighterASTNode2 != null && ((Boolean) function1.invoke(lighterASTNode2)).booleanValue()) {
                    break;
                }
                i++;
            }
            if (lighterASTNode2 != null) {
                return lighterASTNode2;
            }
        }
        if (lighterASTNodeArr != null) {
            int length2 = lighterASTNodeArr.length;
            for (int i2 = 0; i2 < length2; i2++) {
                LighterASTNode lighterASTNode3 = lighterASTNodeArr[i2];
                LighterASTNode lighterASTNodeFindFirstDescendant = lighterASTNode3 != null ? findFirstDescendant(flyweightCapableTreeStructure, lighterASTNode3, function1) : null;
                if (lighterASTNodeFindFirstDescendant != null) {
                    return lighterASTNodeFindFirstDescendant;
                }
            }
        }
        return null;
    }

    public static final LighterASTNode findLastChildByType(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode, IElementType iElementType) {
        int length;
        flyweightCapableTreeStructure.getClass();
        lighterASTNode.getClass();
        iElementType.getClass();
        Ref ref = new Ref();
        flyweightCapableTreeStructure.getChildren(lighterASTNode, ref);
        LighterASTNode[] lighterASTNodeArr = (LighterASTNode[]) ref.get();
        if (lighterASTNodeArr != null && (length = lighterASTNodeArr.length - 1) >= 0) {
            while (true) {
                int i = length - 1;
                LighterASTNode lighterASTNode2 = lighterASTNodeArr[length];
                if (Intrinsics.areEqual(lighterASTNode2 != null ? lighterASTNode2.getTokenType() : null, iElementType)) {
                    return lighterASTNode2;
                }
                if (i >= 0) {
                    length = i;
                }
            }
        }
        return null;
    }

    public static final LighterASTNode findLastDescendant(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode, Function1<? super LighterASTNode, Boolean> function1) {
        LighterASTNode lighterASTNode2;
        flyweightCapableTreeStructure.getClass();
        lighterASTNode.getClass();
        function1.getClass();
        Ref ref = new Ref();
        flyweightCapableTreeStructure.getChildren(lighterASTNode, ref);
        LighterASTNode[] lighterASTNodeArr = (LighterASTNode[]) ref.get();
        if (lighterASTNodeArr != null) {
            int length = lighterASTNodeArr.length - 1;
            if (length < 0) {
                lighterASTNode2 = null;
                break;
            }
            while (true) {
                int i = length - 1;
                lighterASTNode2 = lighterASTNodeArr[length];
                if (lighterASTNode2 != null && ((Boolean) function1.invoke(lighterASTNode2)).booleanValue()) {
                    break;
                }
                if (i < 0) {
                    lighterASTNode2 = null;
                    break;
                }
                length = i;
            }
            if (lighterASTNode2 != null) {
                return lighterASTNode2;
            }
        }
        lighterASTNodeArr.getClass();
        List listReversed = ArraysKt.reversed(lighterASTNodeArr);
        int size = listReversed.size();
        for (int i2 = 0; i2 < size; i2++) {
            LighterASTNode lighterASTNode3 = (LighterASTNode) listReversed.get(i2);
            LighterASTNode lighterASTNodeFindLastDescendant = lighterASTNode3 != null ? findLastDescendant(flyweightCapableTreeStructure, lighterASTNode3, function1) : null;
            if (lighterASTNodeFindLastDescendant != null) {
                return lighterASTNodeFindLastDescendant;
            }
        }
        return null;
    }

    private static final LighterASTNode findParentOfType(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode, IElementType iElementType, boolean z) {
        if (!z && Intrinsics.areEqual(lighterASTNode.getTokenType(), iElementType)) {
            return lighterASTNode;
        }
        LighterASTNode lighterASTNode2 = (LighterASTNode) flyweightCapableTreeStructure.getParent(lighterASTNode);
        while (lighterASTNode2 != null) {
            if (Intrinsics.areEqual(lighterASTNode2.getTokenType(), iElementType)) {
                return lighterASTNode2;
            }
            lighterASTNode2 = (LighterASTNode) flyweightCapableTreeStructure.getParent(lighterASTNode2);
        }
        return null;
    }

    public static /* synthetic */ LighterASTNode findParentOfType$default(FlyweightCapableTreeStructure flyweightCapableTreeStructure, LighterASTNode lighterASTNode, IElementType iElementType, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        return findParentOfType(flyweightCapableTreeStructure, lighterASTNode, iElementType, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LighterASTNode firstChild(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        Ref ref = new Ref();
        flyweightCapableTreeStructure.getChildren(lighterASTNode, ref);
        LighterASTNode[] lighterASTNodeArr = (LighterASTNode[]) ref.get();
        if (lighterASTNodeArr != null) {
            return (LighterASTNode) ArraysKt.firstOrNull(lighterASTNodeArr);
        }
        return null;
    }

    public static final LighterASTNode firstChildExpression(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        flyweightCapableTreeStructure.getClass();
        lighterASTNode.getClass();
        Ref ref = new Ref();
        flyweightCapableTreeStructure.getChildren(lighterASTNode, ref);
        LighterASTNode[] lighterASTNodeArr = (LighterASTNode[]) ref.get();
        if (lighterASTNodeArr != null) {
            for (LighterASTNode lighterASTNode2 : lighterASTNodeArr) {
                if (lighterASTNode2 != null && ElementTypeUtils.INSTANCE.isExpression(lighterASTNode2)) {
                    return lighterASTNode2;
                }
            }
        }
        return null;
    }

    public static final Sequence<LighterASTNode> getAncestors(final FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        flyweightCapableTreeStructure.getClass();
        lighterASTNode.getClass();
        return SequencesKt.generateSequence(flyweightCapableTreeStructure.getParent(lighterASTNode), new Function1() { // from class: c19
            public final Object invoke(Object obj) {
                return LightTreePositioningStrategiesKt.a(flyweightCapableTreeStructure, (LighterASTNode) obj);
            }
        });
    }

    public static final LighterASTNode[] getChildrenArray(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        flyweightCapableTreeStructure.getClass();
        lighterASTNode.getClass();
        Ref ref = new Ref();
        flyweightCapableTreeStructure.getChildren(lighterASTNode, ref);
        LighterASTNode[] lighterASTNodeArr = (LighterASTNode[]) ref.get();
        return lighterASTNodeArr == null ? new LighterASTNode[0] : lighterASTNodeArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LighterASTNode getParentIfTypeIs(LighterASTNode lighterASTNode, IElementType iElementType, FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure) {
        LighterASTNode lighterASTNode2 = (LighterASTNode) flyweightCapableTreeStructure.getParent(lighterASTNode);
        if (lighterASTNode2 == null || !Intrinsics.areEqual(lighterASTNode2.getTokenType(), iElementType)) {
            return null;
        }
        return lighterASTNode2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LighterASTNode getter(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        Ref ref = new Ref();
        flyweightCapableTreeStructure.getChildren(lighterASTNode, ref);
        LighterASTNode[] lighterASTNodeArr = (LighterASTNode[]) ref.get();
        if (lighterASTNodeArr != null) {
            for (LighterASTNode lighterASTNode2 : lighterASTNodeArr) {
                if (lighterASTNode2 != null && Intrinsics.areEqual(lighterASTNode2.getTokenType(), KtNodeTypes.PROPERTY_ACCESSOR)) {
                    KtKeywordToken ktKeywordToken = KtTokens.GET_KEYWORD;
                    ktKeywordToken.getClass();
                    if (findChildByType(flyweightCapableTreeStructure, lighterASTNode2, (IElementType) ktKeywordToken) != null) {
                        return lighterASTNode2;
                    }
                }
            }
        }
        return null;
    }

    public static final boolean hasPrimaryConstructor(KtSourceElement ktSourceElement) {
        ktSourceElement.getClass();
        return primaryConstructor(ktSourceElement.getTreeStructure(), ktSourceElement.getLighterASTNode()) != null;
    }

    public static final boolean hasValOrVar(KtSourceElement ktSourceElement) {
        ktSourceElement.getClass();
        return valOrVarKeyword(ktSourceElement.getTreeStructure(), ktSourceElement.getLighterASTNode()) != null;
    }

    public static final boolean hasVar(KtSourceElement ktSourceElement) {
        ktSourceElement.getClass();
        FlyweightCapableTreeStructure treeStructure = ktSourceElement.getTreeStructure();
        LighterASTNode lighterASTNode = ktSourceElement.getLighterASTNode();
        KtKeywordToken ktKeywordToken = KtTokens.VAR_KEYWORD;
        ktKeywordToken.getClass();
        return findChildByType((FlyweightCapableTreeStructure<LighterASTNode>) treeStructure, lighterASTNode, (IElementType) ktKeywordToken) != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LighterASTNode ifKeyword(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        KtKeywordToken ktKeywordToken = KtTokens.IF_KEYWORD;
        ktKeywordToken.getClass();
        return findChildByType(flyweightCapableTreeStructure, lighterASTNode, (IElementType) ktKeywordToken);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LighterASTNode initKeyword(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        KtKeywordToken ktKeywordToken = KtTokens.INIT_KEYWORD;
        ktKeywordToken.getClass();
        return findChildByType(flyweightCapableTreeStructure, lighterASTNode, (IElementType) ktKeywordToken);
    }

    public static final LighterASTNode inlineModifier(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        flyweightCapableTreeStructure.getClass();
        lighterASTNode.getClass();
        LighterASTNode lighterASTNodeModifierList = modifierList(flyweightCapableTreeStructure, lighterASTNode);
        if (lighterASTNodeModifierList == null) {
            return null;
        }
        KtModifierKeywordToken ktModifierKeywordToken = KtTokens.INLINE_KEYWORD;
        ktModifierKeywordToken.getClass();
        return findChildByType(flyweightCapableTreeStructure, lighterASTNodeModifierList, (IElementType) ktModifierKeywordToken);
    }

    public static final boolean isExpression(LighterASTNode lighterASTNode) {
        lighterASTNode.getClass();
        return ElementTypeUtils.INSTANCE.isExpression(lighterASTNode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LightTreePositioningStrategy keywordStrategy(final Function2<? super FlyweightCapableTreeStructure<LighterASTNode>, ? super LighterASTNode, ? extends LighterASTNode> function2) {
        return new LightTreePositioningStrategy() { // from class: org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategiesKt.keywordStrategy.1
            @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
            public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
                node.getClass();
                tree.getClass();
                LighterASTNode lighterASTNode = (LighterASTNode) function2.invoke(tree, node);
                return lighterASTNode != null ? LightTreePositioningStrategyKt.markElement(lighterASTNode, startOffset, endOffset, tree, node) : LightTreePositioningStrategies.INSTANCE.getDEFAULT().mark(node, startOffset, endOffset, tree);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LighterASTNode lastChild(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        Object obj;
        Ref ref = new Ref();
        flyweightCapableTreeStructure.getChildren(lighterASTNode, ref);
        Object obj2 = ref.get();
        obj2.getClass();
        Object[] objArr = (Object[]) obj2;
        int length = objArr.length - 1;
        if (length < 0) {
            obj = null;
            break;
        }
        while (true) {
            int i = length - 1;
            obj = objArr[length];
            if (((LighterASTNode) obj) != null) {
                break;
            }
            if (i < 0) {
                obj = null;
                break;
            }
            length = i;
        }
        return (LighterASTNode) obj;
    }

    public static final LighterASTNode lastChildExpression(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        int length;
        flyweightCapableTreeStructure.getClass();
        lighterASTNode.getClass();
        Ref ref = new Ref();
        flyweightCapableTreeStructure.getChildren(lighterASTNode, ref);
        LighterASTNode[] lighterASTNodeArr = (LighterASTNode[]) ref.get();
        if (lighterASTNodeArr != null && (length = lighterASTNodeArr.length - 1) >= 0) {
            while (true) {
                int i = length - 1;
                LighterASTNode lighterASTNode2 = lighterASTNodeArr[length];
                if (lighterASTNode2 != null && ElementTypeUtils.INSTANCE.isExpression(lighterASTNode2)) {
                    return lighterASTNode2;
                }
                if (i >= 0) {
                    length = i;
                }
            }
        }
        return null;
    }

    public static final LighterASTNode modalityModifier(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        flyweightCapableTreeStructure.getClass();
        lighterASTNode.getClass();
        LighterASTNode lighterASTNodeModifierList = modifierList(flyweightCapableTreeStructure, lighterASTNode);
        if (lighterASTNodeModifierList == null) {
            return null;
        }
        TokenSet tokenSet = KtTokens.MODALITY_MODIFIERS;
        tokenSet.getClass();
        return findChildByType(flyweightCapableTreeStructure, lighterASTNodeModifierList, tokenSet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LighterASTNode modifierList(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        IElementType iElementType = KtNodeTypes.MODIFIER_LIST;
        iElementType.getClass();
        return findChildByType(flyweightCapableTreeStructure, lighterASTNode, iElementType);
    }

    public static final LighterASTNode nameIdentifier(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        flyweightCapableTreeStructure.getClass();
        lighterASTNode.getClass();
        KtToken ktToken = KtTokens.IDENTIFIER;
        ktToken.getClass();
        return findChildByType(flyweightCapableTreeStructure, lighterASTNode, (IElementType) ktToken);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LighterASTNode objectKeyword(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        KtKeywordToken ktKeywordToken = KtTokens.OBJECT_KEYWORD;
        ktKeywordToken.getClass();
        return findChildByType(flyweightCapableTreeStructure, lighterASTNode, (IElementType) ktKeywordToken);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LighterASTNode operationReference(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        IElementType iElementType = KtNodeTypes.OPERATION_REFERENCE;
        iElementType.getClass();
        return findChildByType(flyweightCapableTreeStructure, lighterASTNode, iElementType);
    }

    public static final LighterASTNode overrideModifier(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        flyweightCapableTreeStructure.getClass();
        lighterASTNode.getClass();
        LighterASTNode lighterASTNodeModifierList = modifierList(flyweightCapableTreeStructure, lighterASTNode);
        if (lighterASTNodeModifierList == null) {
            return null;
        }
        KtModifierKeywordToken ktModifierKeywordToken = KtTokens.OVERRIDE_KEYWORD;
        ktModifierKeywordToken.getClass();
        return findChildByType(flyweightCapableTreeStructure, lighterASTNodeModifierList, (IElementType) ktModifierKeywordToken);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LighterASTNode primaryConstructor(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        IElementType iElementType = KtNodeTypes.PRIMARY_CONSTRUCTOR;
        iElementType.getClass();
        return findChildByType(flyweightCapableTreeStructure, lighterASTNode, iElementType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LighterASTNode receiverTypeReference(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        List listFilterNotNull;
        Ref ref = new Ref();
        flyweightCapableTreeStructure.getChildren(lighterASTNode, ref);
        LighterASTNode[] lighterASTNodeArr = (LighterASTNode[]) ref.get();
        Object obj = null;
        if (lighterASTNodeArr == null || (listFilterNotNull = ArraysKt.filterNotNull(lighterASTNodeArr)) == null) {
            return null;
        }
        for (Object obj2 : listFilterNotNull) {
            LighterASTNode lighterASTNode2 = (LighterASTNode) obj2;
            if (Intrinsics.areEqual(lighterASTNode2.getTokenType(), KtTokens.COLON) || Intrinsics.areEqual(lighterASTNode2.getTokenType(), KtTokens.LPAR)) {
                return null;
            }
            if (Intrinsics.areEqual(lighterASTNode2.getTokenType(), KtNodeTypes.TYPE_REFERENCE)) {
                obj = obj2;
                break;
            }
        }
        return (LighterASTNode) obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LighterASTNode referenceExpression(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode, boolean z) {
        Ref ref = new Ref();
        flyweightCapableTreeStructure.getChildren(lighterASTNode, ref);
        LighterASTNode[] lighterASTNodeArr = (LighterASTNode[]) ref.get();
        LighterASTNode lighterASTNodeReferenceExpression = null;
        if (lighterASTNodeArr != null) {
            int length = lighterASTNodeArr.length;
            for (int i = 0; i < length; i++) {
                LighterASTNode lighterASTNode2 = lighterASTNodeArr[i];
                if (lighterASTNode2 == null || !ElementTypeUtils.INSTANCE.isExpression(lighterASTNode2)) {
                    if (!Intrinsics.areEqual(lighterASTNode2 != null ? lighterASTNode2.getTokenType() : null, KtNodeTypes.PARENTHESIZED)) {
                    }
                }
                lighterASTNodeReferenceExpression = lighterASTNode2;
                break;
            }
        }
        while (z && lighterASTNodeReferenceExpression != null && Intrinsics.areEqual(lighterASTNodeReferenceExpression.getTokenType(), KtNodeTypes.PARENTHESIZED)) {
            lighterASTNodeReferenceExpression = referenceExpression(flyweightCapableTreeStructure, lighterASTNodeReferenceExpression, true);
        }
        return lighterASTNodeReferenceExpression;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LighterASTNode returnKeyword(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        KtKeywordToken ktKeywordToken = KtTokens.RETURN_KEYWORD;
        ktKeywordToken.getClass();
        return findChildByType(flyweightCapableTreeStructure, lighterASTNode, (IElementType) ktKeywordToken);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LighterASTNode safeAccess(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        KtSingleValueToken ktSingleValueToken = KtTokens.SAFE_ACCESS;
        ktSingleValueToken.getClass();
        return findChildByType(flyweightCapableTreeStructure, lighterASTNode, (IElementType) ktSingleValueToken);
    }

    public static final LighterASTNode selector(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        flyweightCapableTreeStructure.getClass();
        lighterASTNode.getClass();
        Ref ref = new Ref();
        flyweightCapableTreeStructure.getChildren(lighterASTNode, ref);
        LighterASTNode[] lighterASTNodeArr = (LighterASTNode[]) ref.get();
        if (lighterASTNodeArr == null) {
            return null;
        }
        boolean z = false;
        for (LighterASTNode lighterASTNode2 : lighterASTNodeArr) {
            if (lighterASTNode2 != null) {
                IElementType tokenType = lighterASTNode2.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtTokens.DOT) || Intrinsics.areEqual(tokenType, KtTokens.COLONCOLON) || Intrinsics.areEqual(tokenType, KtTokens.SAFE_ACCESS)) {
                    z = true;
                } else if (z && ElementTypeUtils.INSTANCE.isExpression(lighterASTNode2)) {
                    return lighterASTNode2;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LighterASTNode setter(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        Ref ref = new Ref();
        flyweightCapableTreeStructure.getChildren(lighterASTNode, ref);
        LighterASTNode[] lighterASTNodeArr = (LighterASTNode[]) ref.get();
        if (lighterASTNodeArr != null) {
            for (LighterASTNode lighterASTNode2 : lighterASTNodeArr) {
                if (lighterASTNode2 != null && Intrinsics.areEqual(lighterASTNode2.getTokenType(), KtNodeTypes.PROPERTY_ACCESSOR)) {
                    KtKeywordToken ktKeywordToken = KtTokens.SET_KEYWORD;
                    ktKeywordToken.getClass();
                    if (findChildByType(flyweightCapableTreeStructure, lighterASTNode2, (IElementType) ktKeywordToken) != null) {
                        return lighterASTNode2;
                    }
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LighterASTNode supertypesList(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        IElementType iElementType = KtNodeTypes.SUPER_TYPE_LIST;
        iElementType.getClass();
        return findChildByType(flyweightCapableTreeStructure, lighterASTNode, iElementType);
    }

    public static final void traverseDescendants(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode, Function1<? super LighterASTNode, Boolean> function1) {
        flyweightCapableTreeStructure.getClass();
        lighterASTNode.getClass();
        function1.getClass();
        traverseDescendants$traverse(flyweightCapableTreeStructure, function1, lighterASTNode);
    }

    private static final void traverseDescendants$traverse(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, Function1<? super LighterASTNode, Boolean> function1, LighterASTNode lighterASTNode) {
        Ref ref = new Ref();
        flyweightCapableTreeStructure.getChildren(lighterASTNode, ref);
        LighterASTNode[] lighterASTNodeArr = (LighterASTNode[]) ref.get();
        if (lighterASTNodeArr != null) {
            for (LighterASTNode lighterASTNode2 : lighterASTNodeArr) {
                if (lighterASTNode2 != null && ((Boolean) function1.invoke(lighterASTNode2)).booleanValue()) {
                    traverseDescendants$traverse(flyweightCapableTreeStructure, function1, lighterASTNode2);
                }
            }
        }
    }

    public static final LighterASTNode typeParametersList(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        flyweightCapableTreeStructure.getClass();
        lighterASTNode.getClass();
        IElementType iElementType = KtNodeTypes.TYPE_PARAMETER_LIST;
        iElementType.getClass();
        return findChildByType(flyweightCapableTreeStructure, lighterASTNode, iElementType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LighterASTNode typeReference(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        List listFilterNotNull;
        Ref ref = new Ref();
        flyweightCapableTreeStructure.getChildren(lighterASTNode, ref);
        LighterASTNode[] lighterASTNodeArr = (LighterASTNode[]) ref.get();
        Object obj = null;
        if (lighterASTNodeArr == null || (listFilterNotNull = ArraysKt.filterNotNull(lighterASTNodeArr)) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (Object obj2 : listFilterNotNull) {
            if (z) {
                arrayList.add(obj2);
            } else if (Intrinsics.areEqual(((LighterASTNode) obj2).getTokenType(), KtTokens.COLON)) {
                arrayList.add(obj2);
                z = true;
            }
        }
        for (Object obj3 : arrayList) {
            if (Intrinsics.areEqual(((LighterASTNode) obj3).getTokenType(), KtNodeTypes.TYPE_REFERENCE)) {
                obj = obj3;
                break;
            }
        }
        return (LighterASTNode) obj;
    }

    public static final LighterASTNode unwrapParenthesesLabelsAndAnnotations(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        LighterASTNode lighterASTNodeFirstChildExpression;
        flyweightCapableTreeStructure.getClass();
        lighterASTNode.getClass();
        while (true) {
            IElementType tokenType = lighterASTNode.getTokenType();
            if (Intrinsics.areEqual(tokenType, KtNodeTypes.PARENTHESIZED)) {
                lighterASTNodeFirstChildExpression = firstChildExpression(flyweightCapableTreeStructure, lighterASTNode);
                if (lighterASTNodeFirstChildExpression == null) {
                    break;
                }
                lighterASTNode = lighterASTNodeFirstChildExpression;
            } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.LABELED_EXPRESSION)) {
                lighterASTNodeFirstChildExpression = lastChildExpression(flyweightCapableTreeStructure, lighterASTNode);
                if (lighterASTNodeFirstChildExpression == null) {
                    break;
                }
                lighterASTNode = lighterASTNodeFirstChildExpression;
            } else {
                if (!Intrinsics.areEqual(tokenType, KtNodeTypes.ANNOTATED_EXPRESSION) || (lighterASTNodeFirstChildExpression = firstChildExpression(flyweightCapableTreeStructure, lighterASTNode)) == null) {
                    break;
                }
                lighterASTNode = lighterASTNodeFirstChildExpression;
            }
        }
        return lighterASTNode;
    }

    public static final LighterASTNode userType(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        flyweightCapableTreeStructure.getClass();
        lighterASTNode.getClass();
        IElementType iElementType = KtNodeTypes.TYPE_REFERENCE;
        iElementType.getClass();
        LighterASTNode lighterASTNodeFindChildByType = findChildByType(flyweightCapableTreeStructure, lighterASTNode, iElementType);
        if (lighterASTNodeFindChildByType == null) {
            return null;
        }
        IElementType iElementType2 = KtNodeTypes.USER_TYPE;
        iElementType2.getClass();
        return findChildByType(flyweightCapableTreeStructure, lighterASTNodeFindChildByType, iElementType2);
    }

    public static final LighterASTNode valOrVarKeyword(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        flyweightCapableTreeStructure.getClass();
        lighterASTNode.getClass();
        TokenSet tokenSet = KtTokens.VAL_VAR;
        tokenSet.getClass();
        return findChildByType(flyweightCapableTreeStructure, lighterASTNode, tokenSet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LighterASTNode valueParameterList(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        IElementType iElementType = KtNodeTypes.VALUE_PARAMETER_LIST;
        iElementType.getClass();
        return findChildByType(flyweightCapableTreeStructure, lighterASTNode, iElementType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<LighterASTNode> valueParameters(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        List<LighterASTNode> listFindChildrenByType;
        LighterASTNode lighterASTNodeValueParameterList = valueParameterList(flyweightCapableTreeStructure, lighterASTNode);
        if (lighterASTNodeValueParameterList != null) {
            IElementType iElementType = KtNodeTypes.VALUE_PARAMETER;
            iElementType.getClass();
            listFindChildrenByType = findChildrenByType(flyweightCapableTreeStructure, lighterASTNodeValueParameterList, iElementType);
        } else {
            listFindChildrenByType = null;
        }
        return listFindChildrenByType == null ? CollectionsKt.emptyList() : listFindChildrenByType;
    }

    public static final LighterASTNode visibilityModifier(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        flyweightCapableTreeStructure.getClass();
        lighterASTNode.getClass();
        LighterASTNode lighterASTNodeModifierList = modifierList(flyweightCapableTreeStructure, lighterASTNode);
        if (lighterASTNodeModifierList == null) {
            return null;
        }
        TokenSet tokenSet = KtTokens.VISIBILITY_MODIFIERS;
        tokenSet.getClass();
        return findChildByType(flyweightCapableTreeStructure, lighterASTNodeModifierList, tokenSet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LighterASTNode whenKeyword(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode) {
        KtKeywordToken ktKeywordToken = KtTokens.WHEN_KEYWORD;
        ktKeywordToken.getClass();
        return findChildByType(flyweightCapableTreeStructure, lighterASTNode, (IElementType) ktKeywordToken);
    }

    public static final LighterASTNode findChildByType(FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode, TokenSet tokenSet) {
        flyweightCapableTreeStructure.getClass();
        lighterASTNode.getClass();
        tokenSet.getClass();
        Ref ref = new Ref();
        flyweightCapableTreeStructure.getChildren(lighterASTNode, ref);
        LighterASTNode[] lighterASTNodeArr = (LighterASTNode[]) ref.get();
        if (lighterASTNodeArr != null) {
            int length = lighterASTNodeArr.length;
            for (int i = 0; i < length; i++) {
                LighterASTNode lighterASTNode2 = lighterASTNodeArr[i];
                if (tokenSet.contains(lighterASTNode2 != null ? lighterASTNode2.getTokenType() : null)) {
                    return lighterASTNode2;
                }
            }
        }
        return null;
    }
}

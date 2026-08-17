package org.jetbrains.kotlin.fir.lightTree.converter;

import com.intellij.lang.LighterASTNode;
import com.intellij.psi.tree.IElementType;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirArgumentListBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirCallBuilder;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.psi.KtPsiUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u001a\n\u0010\u0004\u001a\u00020\u0002*\u00020\u0005\u001a'\u0010\u0006\u001a\u0002H\u0007\"\b\b\u0000\u0010\u0007*\u00020\b*\u0002H\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\f\u001a/\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00052\u0019\u0010\u0010\u001a\u0015\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0011¢\u0006\u0002\b\u0012H\u0086\bø\u0001\u0000\u001a)\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u00052\u0019\u0010\u0010\u001a\u0015\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0011¢\u0006\u0002\b\u0012\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0015"}, d2 = {"nameAsSafeName", "Lorg/jetbrains/kotlin/name/Name;", Argument.Delimiters.none, "defaultName", "getAsStringWithoutBacktick", "Lcom/intellij/lang/LighterASTNode;", "extractArgumentsFrom", "T", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirCallBuilder;", "container", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/builder/FirCallBuilder;Ljava/util/List;)Lorg/jetbrains/kotlin/fir/expressions/builder/FirCallBuilder;", "isClassLocal", Argument.Delimiters.none, "classNode", "getParent", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "isCallableLocal", "callableNode", "org.jetbrains.kotlin.fir:light-tree2fir"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConverterUtilKt {
    public static final <T extends FirCallBuilder> T extractArgumentsFrom(T t, List<? extends FirExpression> list) {
        t.getClass();
        list.getClass();
        FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
        CollectionsKt.addAll(firArgumentListBuilder.getArguments(), list);
        t.setArgumentList(firArgumentListBuilder.build());
        return t;
    }

    public static final String getAsStringWithoutBacktick(LighterASTNode lighterASTNode) {
        lighterASTNode.getClass();
        return StringsKt.replace$default(lighterASTNode.toString(), "`", Argument.Delimiters.none, false, 4, (Object) null);
    }

    public static final boolean isCallableLocal(LighterASTNode lighterASTNode, Function1<? super LighterASTNode, ? extends LighterASTNode> function1) {
        lighterASTNode.getClass();
        function1.getClass();
        LighterASTNode lighterASTNode2 = (LighterASTNode) function1.invoke(lighterASTNode);
        IElementType tokenType = lighterASTNode2 != null ? lighterASTNode2.getTokenType() : null;
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.KT_FILE) || Intrinsics.areEqual(tokenType, KtNodeTypes.CLASS_BODY)) {
            return false;
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.BLOCK)) {
            LighterASTNode lighterASTNode3 = (LighterASTNode) function1.invoke(lighterASTNode2);
            if (Intrinsics.areEqual(lighterASTNode3 != null ? lighterASTNode3.getTokenType() : null, KtNodeTypes.SCRIPT)) {
                return false;
            }
        }
        return true;
    }

    public static final boolean isClassLocal(LighterASTNode lighterASTNode, Function1<? super LighterASTNode, ? extends LighterASTNode> function1) {
        LighterASTNode lighterASTNode2;
        lighterASTNode.getClass();
        function1.getClass();
        LighterASTNode lighterASTNode3 = (LighterASTNode) function1.invoke(lighterASTNode);
        if (Intrinsics.areEqual((lighterASTNode3 == null || (lighterASTNode2 = (LighterASTNode) function1.invoke(lighterASTNode3)) == null) ? null : lighterASTNode2.getTokenType(), KtNodeTypes.SCRIPT)) {
            return false;
        }
        while (lighterASTNode != null) {
            IElementType tokenType = lighterASTNode.getTokenType();
            lighterASTNode = (LighterASTNode) function1.invoke(lighterASTNode);
            IElementType tokenType2 = lighterASTNode != null ? lighterASTNode.getTokenType() : null;
            if (Intrinsics.areEqual(tokenType, KtNodeTypes.PROPERTY) || Intrinsics.areEqual(tokenType, KtNodeTypes.FUN)) {
                LighterASTNode lighterASTNode4 = lighterASTNode != null ? (LighterASTNode) function1.invoke(lighterASTNode) : null;
                if (Intrinsics.areEqual(tokenType2, KtNodeTypes.KT_FILE)) {
                    return true;
                }
                if (Intrinsics.areEqual(tokenType2, KtNodeTypes.CLASS_BODY)) {
                    if (Intrinsics.areEqual(lighterASTNode4 != null ? lighterASTNode4.getTokenType() : null, KtNodeTypes.OBJECT_DECLARATION)) {
                        LighterASTNode lighterASTNode5 = (LighterASTNode) function1.invoke(lighterASTNode4);
                        if (!Intrinsics.areEqual(lighterASTNode5 != null ? lighterASTNode5.getTokenType() : null, KtNodeTypes.OBJECT_LITERAL)) {
                        }
                    }
                    return true;
                }
                if (Intrinsics.areEqual(tokenType2, KtNodeTypes.BLOCK)) {
                    if (Intrinsics.areEqual(lighterASTNode4 != null ? lighterASTNode4.getTokenType() : null, KtNodeTypes.SCRIPT)) {
                        return true;
                    }
                }
            }
            if (Intrinsics.areEqual(tokenType2, KtNodeTypes.ENUM_ENTRY) || Intrinsics.areEqual(tokenType, KtNodeTypes.BLOCK)) {
                return true;
            }
        }
        return false;
    }

    public static final Name nameAsSafeName(String str, String str2) {
        str2.getClass();
        if (str != null) {
            Name nameIdentifier = Name.identifier(KtPsiUtil.unquoteIdentifier(str));
            nameIdentifier.getClass();
            return nameIdentifier;
        }
        if (str2.length() <= 0) {
            return SpecialNames.NO_NAME_PROVIDED;
        }
        Name nameIdentifier2 = Name.identifier(str2);
        nameIdentifier2.getClass();
        return nameIdentifier2;
    }

    public static /* synthetic */ Name nameAsSafeName$default(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = Argument.Delimiters.none;
        }
        return nameAsSafeName(str, str2);
    }
}

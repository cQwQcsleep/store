package org.jetbrains.kotlin.fir.analysis;

import com.intellij.psi.tree.IElementType;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\u001aM\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00060\u00052\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b0\u0005H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\t\u001aM\u0010\n\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00060\u00052\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b0\u0005H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\t\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u000b"}, d2 = {"isCallTheFirstStatement", Argument.Delimiters.none, "T", "root", "getElementType", "Lkotlin/Function1;", "Lcom/intellij/psi/tree/IElementType;", "getChildren", Argument.Delimiters.none, "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Z", "firstFunctionCallInBlockHasLambdaArgumentWithLabel", "org.jetbrains.kotlin:raw-fir.common"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TraversalsKt {
    public static final <T> boolean firstFunctionCallInBlockHasLambdaArgumentWithLabel(T t, Function1<? super T, ? extends IElementType> function1, Function1<? super T, ? extends List<? extends T>> function2) {
        T t2;
        T next;
        Object objSingleOrNull;
        function1.getClass();
        function2.getClass();
        Iterator<T> it = ((Iterable) function2.invoke(t)).iterator();
        do {
            t2 = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(function1.invoke(next), KtNodeTypes.CALL_EXPRESSION));
        if (next == null) {
            return false;
        }
        for (T t3 : (Iterable) function2.invoke(next)) {
            if (Intrinsics.areEqual(function1.invoke(t3), KtNodeTypes.LAMBDA_ARGUMENT)) {
                t2 = t3;
                break;
            }
        }
        if (t2 == null || (objSingleOrNull = CollectionsKt.singleOrNull((List) function2.invoke(t2))) == null) {
            return false;
        }
        return Intrinsics.areEqual(function1.invoke(objSingleOrNull), KtNodeTypes.LABELED_EXPRESSION);
    }

    public static final <T> boolean isCallTheFirstStatement(T t, Function1<? super T, ? extends IElementType> function1, Function1<? super T, ? extends List<? extends T>> function2) {
        function1.getClass();
        function2.getClass();
        List mutableList = CollectionsKt.toMutableList(CollectionsKt.asReversed((List) function2.invoke(t)));
        while (true) {
            List list = mutableList;
            if (!list.isEmpty()) {
                Object objPopLast = AddToStdlibKt.popLast(mutableList);
                IElementType iElementType = (IElementType) function1.invoke(objPopLast);
                if (!Intrinsics.areEqual(iElementType, KtTokens.LBRACE) && !Intrinsics.areEqual(iElementType, KtTokens.WHITE_SPACE) && !Intrinsics.areEqual(iElementType, KtTokens.DOT) && !Intrinsics.areEqual(iElementType, KtTokens.EOL_COMMENT)) {
                    if (!Intrinsics.areEqual(iElementType, KtNodeTypes.CALL_EXPRESSION)) {
                        if (!Intrinsics.areEqual(iElementType, KtNodeTypes.REFERENCE_EXPRESSION)) {
                            if (!Intrinsics.areEqual(iElementType, KtNodeTypes.DOT_QUALIFIED_EXPRESSION)) {
                                if (!Intrinsics.areEqual(iElementType, KtNodeTypes.ANNOTATION_ENTRY)) {
                                    if (!Intrinsics.areEqual(iElementType, KtNodeTypes.ANNOTATED_EXPRESSION)) {
                                        break;
                                    }
                                    CollectionsKt.addAll(list, CollectionsKt.asReversed((List) function2.invoke(objPopLast)));
                                } else {
                                    continue;
                                }
                            } else {
                                CollectionsKt.addAll(list, CollectionsKt.asReversed((List) function2.invoke(objPopLast)));
                            }
                        } else {
                            continue;
                        }
                    } else {
                        return true;
                    }
                }
            } else {
                break;
            }
        }
        return false;
    }
}

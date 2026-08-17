package org.jetbrains.kotlin.utils;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a8\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\bø\u0001\u0000\u001a2\u0010\u0007\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\bø\u0001\u0000\u001a2\u0010\b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\t"}, d2 = {"joinToEnglishString", "", "E", "", "conjunction", "format", "Lkotlin/Function1;", "joinToEnglishOrString", "joinToEnglishAndString", "org.jetbrains.kotlin:util.runtime"}, k = 2, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final class FormattingHelpersKt {
    public static final <E> String joinToEnglishAndString(Collection<? extends E> collection, Function1<? super E, String> function1) {
        collection.getClass();
        function1.getClass();
        if (collection.isEmpty()) {
            return "";
        }
        if (collection.size() == 1) {
            return (String) function1.invoke(kotlin.collections.CollectionsKt.first(collection));
        }
        StringBuilder sb = new StringBuilder();
        List list = kotlin.collections.CollectionsKt.toList(collection);
        int size = list.size() - 1;
        for (int i = 0; i < size; i++) {
            sb.append((String) function1.invoke(list.get(i)));
            sb.append(", ");
        }
        sb.append("and " + ((String) function1.invoke(kotlin.collections.CollectionsKt.last(list))));
        return sb.toString();
    }

    public static /* synthetic */ String joinToEnglishAndString$default(Collection collection, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1() { // from class: org.jetbrains.kotlin.utils.FormattingHelpersKt.joinToEnglishAndString.1
                public final String invoke(Object obj2) {
                    return String.valueOf(obj2);
                }
            };
        }
        collection.getClass();
        function1.getClass();
        if (collection.isEmpty()) {
            return "";
        }
        if (collection.size() == 1) {
            return (String) function1.invoke(kotlin.collections.CollectionsKt.first(collection));
        }
        StringBuilder sb = new StringBuilder();
        List list = kotlin.collections.CollectionsKt.toList(collection);
        int size = list.size() - 1;
        for (int i2 = 0; i2 < size; i2++) {
            sb.append((String) function1.invoke(list.get(i2)));
            sb.append(", ");
        }
        sb.append("and " + ((String) function1.invoke(kotlin.collections.CollectionsKt.last(list))));
        return sb.toString();
    }

    public static final <E> String joinToEnglishOrString(Collection<? extends E> collection, Function1<? super E, String> function1) {
        collection.getClass();
        function1.getClass();
        if (collection.isEmpty()) {
            return "";
        }
        if (collection.size() == 1) {
            return (String) function1.invoke(kotlin.collections.CollectionsKt.first(collection));
        }
        StringBuilder sb = new StringBuilder();
        List list = kotlin.collections.CollectionsKt.toList(collection);
        int size = list.size() - 1;
        for (int i = 0; i < size; i++) {
            sb.append((String) function1.invoke(list.get(i)));
            sb.append(", ");
        }
        sb.append("or " + ((String) function1.invoke(kotlin.collections.CollectionsKt.last(list))));
        return sb.toString();
    }

    public static /* synthetic */ String joinToEnglishOrString$default(Collection collection, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1() { // from class: org.jetbrains.kotlin.utils.FormattingHelpersKt.joinToEnglishOrString.1
                public final String invoke(Object obj2) {
                    return String.valueOf(obj2);
                }
            };
        }
        collection.getClass();
        function1.getClass();
        if (collection.isEmpty()) {
            return "";
        }
        if (collection.size() == 1) {
            return (String) function1.invoke(kotlin.collections.CollectionsKt.first(collection));
        }
        StringBuilder sb = new StringBuilder();
        List list = kotlin.collections.CollectionsKt.toList(collection);
        int size = list.size() - 1;
        for (int i2 = 0; i2 < size; i2++) {
            sb.append((String) function1.invoke(list.get(i2)));
            sb.append(", ");
        }
        sb.append("or " + ((String) function1.invoke(kotlin.collections.CollectionsKt.last(list))));
        return sb.toString();
    }

    public static final <E> String joinToEnglishString(Collection<? extends E> collection, String str, Function1<? super E, String> function1) {
        collection.getClass();
        str.getClass();
        function1.getClass();
        if (collection.isEmpty()) {
            return "";
        }
        if (collection.size() == 1) {
            return (String) function1.invoke(kotlin.collections.CollectionsKt.first(collection));
        }
        StringBuilder sb = new StringBuilder();
        List list = kotlin.collections.CollectionsKt.toList(collection);
        int size = list.size() - 1;
        for (int i = 0; i < size; i++) {
            sb.append((String) function1.invoke(list.get(i)));
            sb.append(", ");
        }
        sb.append(str + ' ' + ((String) function1.invoke(kotlin.collections.CollectionsKt.last(list))));
        return sb.toString();
    }
}

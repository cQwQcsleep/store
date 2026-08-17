package org.jetbrains.kotlin.js.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.js.common.IdentifierPolicyKt;
import org.jetbrains.kotlin.js.util.NameTable;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0010\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0011\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0002\u0010\u0012J\u001a\u0010\u0013\u001a\u00020\b2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u0015J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\bH\u0016J\u001b\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00020\b¢\u0006\u0002\u0010\u001bJ\u001b\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00028\u00002\u0006\u0010\u001d\u001a\u00020\b¢\u0006\u0002\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\n\u001a\u001e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u000bj\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b`\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/js/util/NameTable;", "T", "Lorg/jetbrains/kotlin/js/util/NameScope;", "parent", "<init>", "(Lorg/jetbrains/kotlin/js/util/NameScope;)V", "reserved", "Ljava/util/HashSet;", "", "Lkotlin/collections/HashSet;", "names", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "suggestedNameLastIdx", "", "", "get", "name", "(Ljava/lang/Object;)Ljava/lang/String;", "dump", "renderKey", "Lkotlin/Function1;", "isReserved", "", "declareStableName", "", "declaration", "(Ljava/lang/Object;Ljava/lang/String;)V", "declareFreshName", "suggestedName", "(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/String;", "findFreshName", "org.jetbrains.kotlin:js.ast"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class NameTable<T> extends NameScope {
    private final HashMap<T, String> names;
    private final NameScope parent;
    private final HashSet<String> reserved;
    private final Map<String, Integer> suggestedNameLastIdx;

    public NameTable(NameScope nameScope) {
        nameScope.getClass();
        this.parent = nameScope;
        this.reserved = new HashSet<>();
        this.names = new HashMap<>();
        this.suggestedNameLastIdx = new LinkedHashMap();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit dump$lambda$0$0(Function1 function1, StringBuilder sb, Map.Entry entry) {
        sb.getClass();
        entry.getClass();
        Object key = entry.getKey();
        Object value = entry.getValue();
        value.getClass();
        sb.append("--- ");
        sb.append((String) function1.invoke(key));
        sb.append(" => ");
        sb.append((String) value);
        return Unit.INSTANCE;
    }

    private final String findFreshName(String suggestedName) {
        if (!isReserved(suggestedName)) {
            return suggestedName;
        }
        Ref.IntRef intRef = new Ref.IntRef();
        Integer num = this.suggestedNameLastIdx.get(suggestedName);
        intRef.element = num != null ? num.intValue() : 0;
        while (isReserved(findFreshName$freshName(suggestedName, intRef))) {
            intRef.element++;
        }
        this.suggestedNameLastIdx.put(suggestedName, Integer.valueOf(intRef.element));
        return findFreshName$freshName(suggestedName, intRef);
    }

    private static final String findFreshName$freshName(String str, Ref.IntRef intRef) {
        return str + '_' + intRef.element;
    }

    public final String declareFreshName(T declaration, String suggestedName) {
        suggestedName.getClass();
        String strFindFreshName = findFreshName(IdentifierPolicyKt.makeValidES5Identifier$default(suggestedName, false, 2, null));
        declareStableName(declaration, strFindFreshName);
        return strFindFreshName;
    }

    public final void declareStableName(T declaration, String name) {
        name.getClass();
        this.names.put(declaration, name);
        this.reserved.add(name);
    }

    public final String dump(final Function1<? super T, String> renderKey) {
        renderKey.getClass();
        StringBuilder sb = new StringBuilder();
        sb.append("Names:\n");
        Set<Map.Entry<T, String>> setEntrySet = this.names.entrySet();
        setEntrySet.getClass();
        AddToStdlibKt.joinToWithBuffer$default(setEntrySet, sb, "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function2() { // from class: pba
            public final Object invoke(Object obj, Object obj2) {
                return NameTable.dump$lambda$0$0(renderKey, (StringBuilder) obj, (Map.Entry) obj2);
            }
        }, 60, (Object) null);
        return sb.toString();
    }

    public final String get(T name) {
        return this.names.get(name);
    }

    @Override // org.jetbrains.kotlin.js.util.NameScope
    public boolean isReserved(String name) {
        name.getClass();
        return this.parent.isReserved(name) || this.reserved.contains(name);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public NameTable() {
        NameScope nameScope = null;
        this(nameScope, 1, nameScope);
    }

    public /* synthetic */ NameTable(NameScope nameScope, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? NameScope.EmptyScope.INSTANCE : nameScope);
    }
}

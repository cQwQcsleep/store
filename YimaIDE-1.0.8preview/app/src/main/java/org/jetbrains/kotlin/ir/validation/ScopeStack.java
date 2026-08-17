package org.jetbrains.kotlin.ir.validation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.ir.validation.ScopeStack;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\u001cB\u0007¢\u0006\u0004\b\u0003\u0010\u0004JJ\u0010\b\u001a\u0002H\t\"\u0004\b\u0001\u0010\t2\u0006\u0010\n\u001a\u00020\u000b2\u001f\b\u0002\u0010\f\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\t0\u0012H\u0002¢\u0006\u0002\u0010\u0013JT\u0010\u0014\u001a\u0002H\t\"\u0004\b\u0001\u0010\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u0015\u001a\u00020\u000b2\u001f\b\u0002\u0010\f\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\t0\u0012¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00028\u0000¢\u0006\u0002\u0010\u0019J\u0013\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00028\u0000¢\u0006\u0002\u0010\u001bR \u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/ir/validation/ScopeStack;", "E", "", "<init>", "()V", "scopes", "", "Lorg/jetbrains/kotlin/ir/validation/ScopeStack$Scope;", "withNestedScope", "R", "isGlobalScope", "", "populateScope", "Lkotlin/Function1;", "", "", "Lkotlin/ExtensionFunctionType;", "block", "Lkotlin/Function0;", "(ZLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withNewScope", "outerScopesAreInvisible", "(ZZLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "addToCurrentScope", "element", "(Ljava/lang/Object;)V", "isVisibleInCurrentScope", "(Ljava/lang/Object;)Z", "Scope", "org.jetbrains.kotlin:ir.validation"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ScopeStack<E> {
    private final List<List<Scope<E>>> scopes = CollectionsKt.mutableListOf(new List[]{new ArrayList()});

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010#\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/ir/validation/ScopeStack$Scope;", "E", "", "isGlobal", "", "<init>", "(Z)V", "()Z", "values", "", "getValues", "()Ljava/util/Set;", "org.jetbrains.kotlin:ir.validation"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Scope<E> {
        private final boolean isGlobal;
        private final Set<E> values = new LinkedHashSet();

        public Scope(boolean z) {
            this.isGlobal = z;
        }

        public final Set<E> getValues() {
            return this.values;
        }

        /* JADX INFO: renamed from: isGlobal, reason: from getter */
        public final boolean getIsGlobal() {
            return this.isGlobal;
        }
    }

    public static Unit a(Set set) {
        set.getClass();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <R> R withNestedScope(boolean isGlobalScope, Function1<? super Set<E>, Unit> populateScope, Function0<? extends R> block) {
        List list = (List) CollectionsKt.last(this.scopes);
        Scope scope = new Scope(isGlobalScope);
        list.add(scope);
        populateScope.invoke(scope.getValues());
        R r = (R) block.invoke();
        list.remove(list.size() - 1);
        return r;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object withNewScope$default(ScopeStack scopeStack, boolean z, boolean z2, Function1 function1, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = false;
        }
        if ((i & 4) != 0) {
            function1 = new Function1() { // from class: zwc
                public final Object invoke(Object obj2) {
                    return ScopeStack.a((Set) obj2);
                }
            };
        }
        return scopeStack.withNewScope(z, z2, function1, function0);
    }

    public final void addToCurrentScope(E element) {
        Set<E> values;
        Scope scope = (Scope) CollectionsKt.lastOrNull((List) CollectionsKt.last(this.scopes));
        if (scope == null || (values = scope.getValues()) == null) {
            return;
        }
        values.add(element);
    }

    public final boolean isVisibleInCurrentScope(E element) {
        Iterable iterable = (Iterable) CollectionsKt.last(this.scopes);
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return false;
        }
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            if (((Scope) it.next()).getValues().contains(element)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R> R withNewScope(boolean isGlobalScope, boolean outerScopesAreInvisible, Function1<? super Set<E>, Unit> populateScope, Function0<? extends R> block) {
        populateScope.getClass();
        block.getClass();
        if (!outerScopesAreInvisible) {
            return (R) withNestedScope(isGlobalScope, populateScope, block);
        }
        List<List<Scope<E>>> list = this.scopes;
        Iterable iterable = (Iterable) CollectionsKt.last(list);
        ArrayList arrayList = new ArrayList();
        for (Object obj : iterable) {
            if (((Scope) obj).getIsGlobal()) {
                arrayList.add(obj);
            }
        }
        list.add(arrayList);
        R r = (R) withNestedScope(false, populateScope, block);
        list.remove(list.size() - 1);
        return r;
    }
}

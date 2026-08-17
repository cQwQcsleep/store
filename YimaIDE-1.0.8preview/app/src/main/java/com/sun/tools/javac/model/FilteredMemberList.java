package com.sun.tools.javac.model;

import com.sun.tools.javac.code.Scope;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.model.FilteredMemberList;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FilteredMemberList extends AbstractList<Symbol> {
    private final Scope scope;

    public FilteredMemberList(Scope scope) {
        this.scope = scope;
    }

    public static /* synthetic */ boolean a(Symbol symbol) {
        return !unwanted(symbol);
    }

    private static boolean unwanted(Symbol symbol) {
        return symbol == null || (symbol.flags() & 4096) != 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public Symbol get(int i) {
        for (Symbol symbol : this.scope.getSymbols(Scope.LookupKind.NON_RECURSIVE)) {
            if (!unwanted(symbol)) {
                int i2 = i - 1;
                if (i == 0) {
                    return symbol;
                }
                i = i2;
            }
        }
        qc6.a();
        return null;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<Symbol> iterator() {
        return this.scope.getSymbols(new Predicate() { // from class: ls4
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return FilteredMemberList.a((Symbol) obj);
            }
        }, Scope.LookupKind.NON_RECURSIVE).iterator();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        Iterator<Symbol> it = this.scope.getSymbols(Scope.LookupKind.NON_RECURSIVE).iterator();
        int i = 0;
        while (it.hasNext()) {
            if (!unwanted(it.next())) {
                i++;
            }
        }
        return i;
    }
}

package com.sun.tools.javac.code;

import com.sun.tools.javac.code.Scope;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Iterators;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Name;
import defpackage.qwc;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Scope {
    private static final Predicate<Symbol> noFilter = null;
    ScopeListenerList listeners = new ScopeListenerList();
    public final Symbol owner;

    public static class CompoundScope extends Scope implements ScopeListener {
        private int mark;
        ListBuffer<Scope> subScopes;

        public CompoundScope(Symbol symbol) {
            super(symbol);
            this.subScopes = new ListBuffer<>();
            this.mark = 0;
        }

        public void appendSubScope(Scope scope) {
            if (scope != null) {
                this.subScopes.append(scope);
                scope.listeners.add(this);
                this.mark++;
                this.listeners.symbolAdded(null, this);
            }
        }

        public int getMark() {
            return this.mark;
        }

        @Override // com.sun.tools.javac.code.Scope
        public Scope getOrigin(Symbol symbol) {
            for (Scope scope : this.subScopes) {
                if (scope.includes(symbol)) {
                    return scope.getOrigin(symbol);
                }
            }
            return null;
        }

        @Override // com.sun.tools.javac.code.Scope
        public Iterable<Symbol> getSymbols(final Predicate<Symbol> predicate, final LookupKind lookupKind) {
            return new Iterable() { // from class: owc
                @Override // java.lang.Iterable
                public final Iterator iterator() {
                    return Iterators.createCompoundIterator(this.b.subScopes, new Function() { // from class: mwc
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return ((Scope) obj).getSymbols(predicate, lookupKind).iterator();
                        }
                    });
                }
            };
        }

        @Override // com.sun.tools.javac.code.Scope
        public Iterable<Symbol> getSymbolsByName(final Name name, final Predicate<Symbol> predicate, final LookupKind lookupKind) {
            return new Iterable() { // from class: nwc
                @Override // java.lang.Iterable
                public final Iterator iterator() {
                    return Iterators.createCompoundIterator(this.b.subScopes, new Function() { // from class: pwc
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return ((Scope) obj).getSymbolsByName(name, predicate, lookupKind).iterator();
                        }
                    });
                }
            };
        }

        @Override // com.sun.tools.javac.code.Scope
        public boolean isStaticallyImported(Symbol symbol) {
            for (Scope scope : this.subScopes) {
                if (scope.includes(symbol)) {
                    return scope.isStaticallyImported(symbol);
                }
            }
            return false;
        }

        public void prependSubScope(Scope scope) {
            if (scope != null) {
                this.subScopes.prepend(scope);
                scope.listeners.add(this);
                this.mark++;
                this.listeners.symbolAdded(null, this);
            }
        }

        @Override // com.sun.tools.javac.code.Scope.ScopeListener
        public void symbolAdded(Symbol symbol, Scope scope) {
            this.mark++;
            this.listeners.symbolAdded(symbol, scope);
        }

        @Override // com.sun.tools.javac.code.Scope.ScopeListener
        public void symbolRemoved(Symbol symbol, Scope scope) {
            this.mark++;
            this.listeners.symbolRemoved(symbol, scope);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("CompoundScope{");
            String str = "";
            for (Scope scope : this.subScopes) {
                sb.append(str);
                sb.append(scope);
                str = ",";
            }
            sb.append("}");
            return sb.toString();
        }
    }

    public interface ImportFilter {
        boolean accepts(Scope scope, Symbol symbol);
    }

    public static class ImportScope extends CompoundScope {
        public ImportScope(Symbol symbol) {
            super(symbol);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v3, types: [A, com.sun.tools.javac.code.Scope] */
        public void finalizeScope() {
            for (List list = this.subScopes.toList(); list.nonEmpty(); list = list.tail) {
                list.head = finalizeSingleScope((Scope) list.head);
            }
        }

        public Scope finalizeSingleScope(Scope scope) {
            if (scope instanceof FilterImportScope) {
                FilterImportScope filterImportScope = (FilterImportScope) scope;
                if (scope.owner.kind == Kinds.Kind.TYP && filterImportScope.isStaticallyImported()) {
                    WriteableScope writeableScopeCreate = WriteableScope.create(scope.owner);
                    Iterator<Symbol> it = scope.getSymbols().iterator();
                    while (it.hasNext()) {
                        writeableScopeCreate.enter(it.next());
                    }
                    writeableScopeCreate.listeners.add(new ScopeListener() { // from class: com.sun.tools.javac.code.Scope.ImportScope.1
                        @Override // com.sun.tools.javac.code.Scope.ScopeListener
                        public void symbolAdded(Symbol symbol, Scope scope2) {
                            Assert.error("The scope is sealed.");
                        }

                        @Override // com.sun.tools.javac.code.Scope.ScopeListener
                        public void symbolRemoved(Symbol symbol, Scope scope2) {
                            Assert.error("The scope is sealed.");
                        }
                    });
                    return writeableScopeCreate;
                }
            }
            return scope;
        }
    }

    public enum LookupKind {
        RECURSIVE,
        NON_RECURSIVE
    }

    public static class NamedImportScope extends ImportScope {
        private final Map<Name, Scope[]> name2Scopes;

        public static class SingleEntryScope extends Scope {
            private final List<Symbol> content;
            private final Scope origin;
            private final Symbol sym;

            public SingleEntryScope(Symbol symbol, Symbol symbol2, Scope scope) {
                super(symbol);
                this.sym = symbol2;
                this.content = List.of(symbol2);
                this.origin = scope;
            }

            @Override // com.sun.tools.javac.code.Scope
            public Scope getOrigin(Symbol symbol) {
                if (this.sym == symbol) {
                    return this.origin;
                }
                return null;
            }

            @Override // com.sun.tools.javac.code.Scope
            public Iterable<Symbol> getSymbols(Predicate<Symbol> predicate, LookupKind lookupKind) {
                return (predicate == null || predicate.test(this.sym)) ? this.content : Collections.EMPTY_LIST;
            }

            @Override // com.sun.tools.javac.code.Scope
            public Iterable<Symbol> getSymbolsByName(Name name, Predicate<Symbol> predicate, LookupKind lookupKind) {
                Symbol symbol = this.sym;
                return (symbol.name == name && (predicate == null || predicate.test(symbol))) ? this.content : Collections.EMPTY_LIST;
            }

            @Override // com.sun.tools.javac.code.Scope
            public boolean isStaticallyImported(Symbol symbol) {
                return false;
            }
        }

        public NamedImportScope(Symbol symbol) {
            super(symbol);
            this.name2Scopes = new HashMap();
        }

        private Scope appendScope(Scope scope, Name name) {
            appendSubScope(scope);
            Scope[] scopeArr = this.name2Scopes.get(name);
            Scope[] scopeArr2 = scopeArr != null ? (Scope[]) Arrays.copyOf(scopeArr, scopeArr.length + 1) : new Scope[1];
            scopeArr2[scopeArr2.length - 1] = scope;
            this.name2Scopes.put(name, scopeArr2);
            return scope;
        }

        @Override // com.sun.tools.javac.code.Scope.ImportScope
        public void finalizeScope() {
            super.finalizeScope();
            for (Scope[] scopeArr : this.name2Scopes.values()) {
                for (int i = 0; i < scopeArr.length; i++) {
                    scopeArr[i] = finalizeSingleScope(scopeArr[i]);
                }
            }
        }

        @Override // com.sun.tools.javac.code.Scope.CompoundScope, com.sun.tools.javac.code.Scope
        public Iterable<Symbol> getSymbolsByName(final Name name, final Predicate<Symbol> predicate, final LookupKind lookupKind) {
            final Scope[] scopeArr = this.name2Scopes.get(name);
            return scopeArr == null ? Collections.EMPTY_LIST : new Iterable() { // from class: swc
                @Override // java.lang.Iterable
                public final Iterator iterator() {
                    return Iterators.createCompoundIterator(Arrays.asList(scopeArr), new Function() { // from class: rwc
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return ((Scope) obj).getSymbolsByName(name, predicate, lookupKind).iterator();
                        }
                    });
                }
            };
        }

        public Scope importByName(Types types, Scope scope, Name name, ImportFilter importFilter, JCTree.JCImport jCImport, BiConsumer<JCTree.JCImport, Symbol.CompletionFailure> biConsumer) {
            return appendScope(new FilterImportScope(types, scope, name, importFilter, jCImport, biConsumer), name);
        }

        public Scope importType(Scope scope, Scope scope2, Symbol symbol) {
            return appendScope(new SingleEntryScope(scope.owner, symbol, scope2), symbol.name);
        }
    }

    public interface ScopeListener {
        void symbolAdded(Symbol symbol, Scope scope);

        void symbolRemoved(Symbol symbol, Scope scope);
    }

    public static class ScopeListenerList {
        List<WeakReference<ScopeListener>> listeners = List.nil();

        private void walkReferences(Symbol symbol, Scope scope, boolean z) {
            ListBuffer listBuffer = new ListBuffer();
            for (WeakReference<ScopeListener> weakReference : this.listeners) {
                ScopeListener scopeListener = weakReference.get();
                if (scopeListener != null) {
                    if (z) {
                        scopeListener.symbolRemoved(symbol, scope);
                    } else {
                        scopeListener.symbolAdded(symbol, scope);
                    }
                    listBuffer.add(weakReference);
                }
            }
            this.listeners = listBuffer.toList();
        }

        public void add(ScopeListener scopeListener) {
            this.listeners = this.listeners.prepend(new WeakReference<>(scopeListener));
        }

        public void symbolAdded(Symbol symbol, Scope scope) {
            walkReferences(symbol, scope, false);
        }

        public void symbolRemoved(Symbol symbol, Scope scope) {
            walkReferences(symbol, scope, true);
        }
    }

    public static class StarImportScope extends ImportScope {
        public StarImportScope(Symbol symbol) {
            super(symbol);
        }

        public void importAll(Types types, Scope scope, ImportFilter importFilter, JCTree.JCImport jCImport, BiConsumer<JCTree.JCImport, Symbol.CompletionFailure> biConsumer) {
            for (Scope scope2 : this.subScopes) {
                Assert.check(scope2 instanceof FilterImportScope);
                FilterImportScope filterImportScope = (FilterImportScope) scope2;
                if (filterImportScope.origin == scope && filterImportScope.filter == importFilter && filterImportScope.imp.staticImport == jCImport.staticImport) {
                    return;
                }
            }
            prependSubScope(new FilterImportScope(types, scope, null, importFilter, jCImport, biConsumer));
        }

        public boolean isFilled() {
            return this.subScopes.nonEmpty();
        }
    }

    public static abstract class WriteableScope extends Scope {
        public WriteableScope(Symbol symbol) {
            super(symbol);
        }

        public static WriteableScope create(Symbol symbol) {
            return new ScopeImpl(symbol);
        }

        public final WriteableScope dup() {
            return dup(this.owner);
        }

        public abstract WriteableScope dup(Symbol symbol);

        public final WriteableScope dupUnshared() {
            return dupUnshared(this.owner);
        }

        public abstract WriteableScope dupUnshared(Symbol symbol);

        public abstract void enter(Symbol symbol);

        public abstract void enterIfAbsent(Symbol symbol);

        public abstract WriteableScope leave();

        public abstract void remove(Symbol symbol);
    }

    public Scope(Symbol symbol) {
        this.owner = symbol;
    }

    public static /* synthetic */ boolean a(Symbol symbol, Symbol symbol2) {
        return symbol2 == symbol;
    }

    public boolean anyMatch(Predicate<Symbol> predicate) {
        return getSymbols(predicate, LookupKind.NON_RECURSIVE).iterator().hasNext();
    }

    public Symbol findFirst(Name name, Predicate<Symbol> predicate) {
        Iterator<Symbol> it = getSymbolsByName(name, predicate).iterator();
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }

    public abstract Scope getOrigin(Symbol symbol);

    public final Iterable<Symbol> getSymbols() {
        return getSymbols(noFilter);
    }

    public abstract Iterable<Symbol> getSymbols(Predicate<Symbol> predicate, LookupKind lookupKind);

    public final Iterable<Symbol> getSymbolsByName(Name name) {
        return getSymbolsByName(name, LookupKind.RECURSIVE);
    }

    public abstract Iterable<Symbol> getSymbolsByName(Name name, Predicate<Symbol> predicate, LookupKind lookupKind);

    public boolean includes(final Symbol symbol, LookupKind lookupKind) {
        return getSymbolsByName(symbol.name, new Predicate() { // from class: nvc
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Scope.a(symbol, (Symbol) obj);
            }
        }, lookupKind).iterator().hasNext();
    }

    public boolean isEmpty() {
        return !getSymbols(LookupKind.NON_RECURSIVE).iterator().hasNext();
    }

    public abstract boolean isStaticallyImported(Symbol symbol);

    public static class ErrorScope extends ScopeImpl {
        public ErrorScope(ScopeImpl scopeImpl, Symbol symbol, Entry[] entryArr) {
            super(scopeImpl, symbol, entryArr);
        }

        @Override // com.sun.tools.javac.code.Scope.ScopeImpl, com.sun.tools.javac.code.Scope
        public /* bridge */ /* synthetic */ boolean anyMatch(Predicate predicate) {
            return super.anyMatch(predicate);
        }

        @Override // com.sun.tools.javac.code.Scope.ScopeImpl, com.sun.tools.javac.code.Scope.WriteableScope
        public WriteableScope dup(Symbol symbol) {
            return new ErrorScope(this, symbol, this.table);
        }

        @Override // com.sun.tools.javac.code.Scope.ScopeImpl, com.sun.tools.javac.code.Scope.WriteableScope
        public WriteableScope dupUnshared(Symbol symbol) {
            return new ErrorScope(this, symbol, (Entry[]) this.table.clone());
        }

        @Override // com.sun.tools.javac.code.Scope.ScopeImpl, com.sun.tools.javac.code.Scope.WriteableScope
        public /* bridge */ /* synthetic */ void enter(Symbol symbol) {
            super.enter(symbol);
        }

        @Override // com.sun.tools.javac.code.Scope.ScopeImpl, com.sun.tools.javac.code.Scope.WriteableScope
        public /* bridge */ /* synthetic */ void enterIfAbsent(Symbol symbol) {
            super.enterIfAbsent(symbol);
        }

        @Override // com.sun.tools.javac.code.Scope.ScopeImpl, com.sun.tools.javac.code.Scope
        public /* bridge */ /* synthetic */ Symbol findFirst(Name name, Predicate predicate) {
            return super.findFirst(name, predicate);
        }

        @Override // com.sun.tools.javac.code.Scope.ScopeImpl, com.sun.tools.javac.code.Scope
        public /* bridge */ /* synthetic */ Scope getOrigin(Symbol symbol) {
            return super.getOrigin(symbol);
        }

        @Override // com.sun.tools.javac.code.Scope.ScopeImpl, com.sun.tools.javac.code.Scope
        public /* bridge */ /* synthetic */ Iterable getSymbols(Predicate predicate, LookupKind lookupKind) {
            return super.getSymbols(predicate, lookupKind);
        }

        @Override // com.sun.tools.javac.code.Scope.ScopeImpl, com.sun.tools.javac.code.Scope
        public /* bridge */ /* synthetic */ Iterable getSymbolsByName(Name name, Predicate predicate, LookupKind lookupKind) {
            return super.getSymbolsByName(name, predicate, lookupKind);
        }

        @Override // com.sun.tools.javac.code.Scope.ScopeImpl, com.sun.tools.javac.code.Scope
        public /* bridge */ /* synthetic */ boolean includes(Symbol symbol) {
            return super.includes(symbol);
        }

        @Override // com.sun.tools.javac.code.Scope.ScopeImpl, com.sun.tools.javac.code.Scope
        public /* bridge */ /* synthetic */ boolean isStaticallyImported(Symbol symbol) {
            return super.isStaticallyImported(symbol);
        }

        @Override // com.sun.tools.javac.code.Scope.ScopeImpl, com.sun.tools.javac.code.Scope.WriteableScope
        public /* bridge */ /* synthetic */ WriteableScope leave() {
            return super.leave();
        }

        @Override // com.sun.tools.javac.code.Scope.ScopeImpl
        public Entry lookup(Name name) {
            Entry entryLookup = super.lookup(name);
            return entryLookup.scope == null ? new Entry(this.owner, null, null, null) : entryLookup;
        }

        @Override // com.sun.tools.javac.code.Scope.ScopeImpl, com.sun.tools.javac.code.Scope.WriteableScope
        public /* bridge */ /* synthetic */ void remove(Symbol symbol) {
            super.remove(symbol);
        }

        @Override // com.sun.tools.javac.code.Scope.ScopeImpl
        public /* bridge */ /* synthetic */ String toString() {
            return super.toString();
        }

        public ErrorScope(Symbol symbol) {
            super(symbol);
        }
    }

    public static class FilterImportScope extends Scope {
        private final BiConsumer<JCTree.JCImport, Symbol.CompletionFailure> cfHandler;
        private final ImportFilter filter;
        private final Name filterName;
        private final JCTree.JCImport imp;
        private final Scope origin;
        private final Types types;

        public abstract class SymbolImporter {
            final boolean inspectSuperTypes;
            Set<Symbol> processed = new HashSet();
            List<Iterable<Symbol>> delegates = List.nil();

            public SymbolImporter(boolean z) {
                this.inspectSuperTypes = z;
            }

            public abstract Iterable<Symbol> doLookup(Symbol.TypeSymbol typeSymbol);

            public List<Iterable<Symbol>> importFrom(Symbol.TypeSymbol typeSymbol, List<Iterable<Symbol>> list) {
                if (typeSymbol == null || !this.processed.add(typeSymbol)) {
                    return list;
                }
                if (this.inspectSuperTypes) {
                    list = importFrom(FilterImportScope.this.types.supertype(typeSymbol.type).tsym, list);
                    Iterator<Type> it = FilterImportScope.this.types.interfaces(typeSymbol.type).iterator();
                    while (it.hasNext()) {
                        list = importFrom(it.next().tsym, list);
                    }
                }
                return list.prepend(doLookup(typeSymbol));
            }
        }

        public FilterImportScope(Types types, Scope scope, Name name, ImportFilter importFilter, JCTree.JCImport jCImport, BiConsumer<JCTree.JCImport, Symbol.CompletionFailure> biConsumer) {
            super(scope.owner);
            this.types = types;
            this.origin = scope;
            this.filterName = name;
            this.filter = importFilter;
            this.imp = jCImport;
            this.cfHandler = biConsumer;
        }

        public static /* synthetic */ Iterator c(final FilterImportScope filterImportScope, List list) {
            filterImportScope.getClass();
            return Iterators.createFilterIterator(Iterators.createCompoundIterator(list, new qwc()), new Predicate() { // from class: com.sun.tools.javac.code.i
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    Scope.FilterImportScope filterImportScope2 = this.b;
                    return filterImportScope2.filter.accepts(filterImportScope2.origin, (Symbol) obj);
                }
            });
        }

        public static /* synthetic */ Iterator e(final FilterImportScope filterImportScope, List list) {
            filterImportScope.getClass();
            return Iterators.createFilterIterator(Iterators.createCompoundIterator(list, new qwc()), new Predicate() { // from class: com.sun.tools.javac.code.k
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    Scope.FilterImportScope filterImportScope2 = this.b;
                    return filterImportScope2.filter.accepts(filterImportScope2.origin, (Symbol) obj);
                }
            });
        }

        @Override // com.sun.tools.javac.code.Scope
        public Scope getOrigin(Symbol symbol) {
            return this.origin;
        }

        @Override // com.sun.tools.javac.code.Scope
        public Iterable<Symbol> getSymbols(final Predicate<Symbol> predicate, final LookupKind lookupKind) {
            Name name = this.filterName;
            if (name != null) {
                return getSymbolsByName(name, predicate, lookupKind);
            }
            try {
                final List<Iterable<Symbol>> listImportFrom = new SymbolImporter(this, this.imp.staticImport) { // from class: com.sun.tools.javac.code.Scope.FilterImportScope.1
                    final /* synthetic */ FilterImportScope this$0;

                    {
                        this.this$0 = this;
                    }

                    @Override // com.sun.tools.javac.code.Scope.FilterImportScope.SymbolImporter
                    public Iterable<Symbol> doLookup(Symbol.TypeSymbol typeSymbol) {
                        return typeSymbol.members().getSymbols(predicate, lookupKind);
                    }
                }.importFrom((Symbol.TypeSymbol) this.origin.owner, List.nil());
                return new Iterable() { // from class: com.sun.tools.javac.code.j
                    @Override // java.lang.Iterable
                    public final Iterator iterator() {
                        return Scope.FilterImportScope.e(this.b, listImportFrom);
                    }
                };
            } catch (Symbol.CompletionFailure e) {
                this.cfHandler.accept(this.imp, e);
                return Collections.EMPTY_LIST;
            }
        }

        @Override // com.sun.tools.javac.code.Scope
        public Iterable<Symbol> getSymbolsByName(final Name name, final Predicate<Symbol> predicate, final LookupKind lookupKind) {
            final FilterImportScope filterImportScope;
            Name name2 = this.filterName;
            if (name2 != null && name2 != name) {
                return Collections.EMPTY_LIST;
            }
            try {
                filterImportScope = this;
                try {
                    final List<Iterable<Symbol>> listImportFrom = new SymbolImporter(filterImportScope, this.imp.staticImport) { // from class: com.sun.tools.javac.code.Scope.FilterImportScope.2
                        final /* synthetic */ FilterImportScope this$0;

                        {
                            this.this$0 = filterImportScope;
                        }

                        @Override // com.sun.tools.javac.code.Scope.FilterImportScope.SymbolImporter
                        public Iterable<Symbol> doLookup(Symbol.TypeSymbol typeSymbol) {
                            return typeSymbol.members().getSymbolsByName(name, predicate, lookupKind);
                        }
                    }.importFrom((Symbol.TypeSymbol) filterImportScope.origin.owner, List.nil());
                    return new Iterable() { // from class: com.sun.tools.javac.code.l
                        @Override // java.lang.Iterable
                        public final Iterator iterator() {
                            return Scope.FilterImportScope.c(this.b, listImportFrom);
                        }
                    };
                } catch (Symbol.CompletionFailure e) {
                    e = e;
                    filterImportScope.cfHandler.accept(filterImportScope.imp, e);
                    return Collections.EMPTY_LIST;
                }
            } catch (Symbol.CompletionFailure e2) {
                e = e2;
                filterImportScope = this;
            }
        }

        @Override // com.sun.tools.javac.code.Scope
        public boolean isStaticallyImported(Symbol symbol) {
            return isStaticallyImported();
        }

        public boolean isStaticallyImported() {
            return this.imp.staticImport;
        }
    }

    public final Iterable<Symbol> getSymbols(Predicate<Symbol> predicate) {
        return getSymbols(predicate, LookupKind.RECURSIVE);
    }

    public final Iterable<Symbol> getSymbolsByName(Name name, Predicate<Symbol> predicate) {
        return getSymbolsByName(name, predicate, LookupKind.RECURSIVE);
    }

    public final Iterable<Symbol> getSymbols(LookupKind lookupKind) {
        return getSymbols(noFilter, lookupKind);
    }

    public final Iterable<Symbol> getSymbolsByName(Name name, LookupKind lookupKind) {
        return getSymbolsByName(name, noFilter, lookupKind);
    }

    public boolean includes(Symbol symbol) {
        return includes(symbol, LookupKind.RECURSIVE);
    }

    public final Symbol findFirst(Name name) {
        return findFirst(name, noFilter);
    }

    public static class Entry {
        public Entry nextSibling;
        public Entry prevSibling;
        public ScopeImpl scope;
        private Entry shadowed;
        public Symbol sym;

        public Entry(Symbol symbol, Entry entry, Entry entry2, ScopeImpl scopeImpl) {
            this.sym = symbol;
            this.shadowed = entry;
            this.nextSibling = entry2;
            this.scope = scopeImpl;
            if (entry2 != null) {
                entry2.prevSibling = this;
            }
        }

        public Entry next(Predicate<Symbol> predicate) {
            Symbol symbol = this.shadowed.sym;
            return (symbol == null || predicate == null || predicate.test(symbol)) ? this.shadowed : this.shadowed.next(predicate);
        }

        public Entry next() {
            return this.shadowed;
        }
    }

    public static class ScopeImpl extends WriteableScope {
        private static final int INITIAL_SIZE = 16;
        private static final Entry sentinel = new Entry(null, null, null, null);
        public Entry elems;
        int hashMask;
        int nelems;
        public ScopeImpl next;
        int removeCount;
        private boolean shared;
        Entry[] table;

        private ScopeImpl(ScopeImpl scopeImpl, Symbol symbol, Entry[] entryArr) {
            super(symbol);
            this.nelems = 0;
            this.removeCount = 0;
            this.next = scopeImpl;
            Assert.check(symbol != null);
            this.table = entryArr;
            this.hashMask = entryArr.length - 1;
        }

        public static /* synthetic */ boolean b(Symbol symbol, Symbol symbol2) {
            return symbol2 == symbol;
        }

        public static /* synthetic */ Iterator c(ScopeImpl scopeImpl, final LookupKind lookupKind, final Predicate predicate) {
            scopeImpl.getClass();
            return new Iterator<Symbol>(scopeImpl) { // from class: com.sun.tools.javac.code.Scope.ScopeImpl.1
                private Entry currEntry;
                private ScopeImpl currScope;
                private int seenRemoveCount;
                final /* synthetic */ ScopeImpl this$0;

                {
                    this.this$0 = scopeImpl;
                    this.currScope = scopeImpl;
                    this.currEntry = scopeImpl.elems;
                    this.seenRemoveCount = scopeImpl.removeCount;
                    update();
                }

                private Symbol doNext() {
                    Entry entry = this.currEntry;
                    Symbol symbol = entry == null ? null : entry.sym;
                    if (entry != null) {
                        this.currEntry = entry.nextSibling;
                    }
                    update();
                    return symbol;
                }

                private void update() {
                    ScopeImpl scopeImpl2;
                    skipToNextMatchingEntry();
                    if (lookupKind == LookupKind.RECURSIVE) {
                        while (this.currEntry == null && (scopeImpl2 = this.currScope.next) != null) {
                            this.currScope = scopeImpl2;
                            this.currEntry = scopeImpl2.elems;
                            this.seenRemoveCount = scopeImpl2.removeCount;
                            skipToNextMatchingEntry();
                        }
                    }
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    Entry entry;
                    if (this.seenRemoveCount != this.currScope.removeCount && (entry = this.currEntry) != null && !entry.scope.includes(entry.sym)) {
                        doNext();
                        this.seenRemoveCount = this.currScope.removeCount;
                    }
                    return this.currEntry != null;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.Iterator
                public Symbol next() {
                    if (hasNext()) {
                        return doNext();
                    }
                    z0e.a();
                    return null;
                }

                public void skipToNextMatchingEntry() {
                    Predicate predicate2;
                    while (true) {
                        Entry entry = this.currEntry;
                        if (entry == null || (predicate2 = predicate) == null || predicate2.test(entry.sym)) {
                            return;
                        } else {
                            this.currEntry = this.currEntry.nextSibling;
                        }
                    }
                }
            };
        }

        public static /* synthetic */ Iterator d(ScopeImpl scopeImpl, Name name, Predicate predicate, LookupKind lookupKind) {
            scopeImpl.getClass();
            return new Iterator<Symbol>(scopeImpl, name, predicate, lookupKind) { // from class: com.sun.tools.javac.code.Scope.ScopeImpl.2
                Entry currentEntry;
                int seenRemoveCount;
                final /* synthetic */ ScopeImpl this$0;
                final /* synthetic */ LookupKind val$lookupKind;
                final /* synthetic */ Name val$name;
                final /* synthetic */ Predicate val$sf;

                {
                    this.val$name = name;
                    this.val$sf = predicate;
                    this.val$lookupKind = lookupKind;
                    this.this$0 = scopeImpl;
                    Entry entryLookup = scopeImpl.lookup(name, predicate);
                    this.currentEntry = entryLookup;
                    ScopeImpl scopeImpl2 = entryLookup.scope;
                    this.seenRemoveCount = scopeImpl2 != null ? scopeImpl2.removeCount : -1;
                }

                private Symbol doNext() {
                    Entry entry = this.currentEntry;
                    this.currentEntry = entry.next(this.val$sf);
                    return entry.sym;
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    Entry entry = this.currentEntry;
                    ScopeImpl scopeImpl2 = entry.scope;
                    if (scopeImpl2 != null && this.seenRemoveCount != scopeImpl2.removeCount && !scopeImpl2.includes(entry.sym)) {
                        doNext();
                    }
                    ScopeImpl scopeImpl3 = this.currentEntry.scope;
                    if (scopeImpl3 != null) {
                        return this.val$lookupKind == LookupKind.RECURSIVE || scopeImpl3 == this.this$0;
                    }
                    return false;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.Iterator
                public Symbol next() {
                    if (hasNext()) {
                        return doNext();
                    }
                    z0e.a();
                    return null;
                }

                @Override // java.util.Iterator
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        private void dble() {
            int i;
            Assert.check(!this.shared);
            Entry[] entryArr = this.table;
            int length = entryArr.length * 2;
            Entry[] entryArr2 = new Entry[length];
            ScopeImpl scopeImpl = this;
            while (true) {
                i = 0;
                if (scopeImpl == null) {
                    break;
                }
                if (scopeImpl.table == entryArr) {
                    Assert.check(scopeImpl == this || scopeImpl.shared);
                    scopeImpl.table = entryArr2;
                    scopeImpl.hashMask = length - 1;
                }
                scopeImpl = scopeImpl.next;
            }
            int length2 = entryArr.length;
            while (true) {
                length2--;
                if (length2 < 0) {
                    this.nelems = i;
                    return;
                }
                Entry entry = entryArr[length2];
                if (entry != null && entry != sentinel) {
                    this.table[getIndex(entry.sym.name)] = entry;
                    i++;
                }
            }
        }

        @Override // com.sun.tools.javac.code.Scope
        public boolean anyMatch(Predicate<Symbol> predicate) {
            return getSymbols(predicate, LookupKind.NON_RECURSIVE).iterator().hasNext();
        }

        @Override // com.sun.tools.javac.code.Scope.WriteableScope
        public WriteableScope dup(Symbol symbol) {
            Assert.check(!this.shared);
            ScopeImpl scopeImpl = new ScopeImpl(this, symbol, this.table, this.nelems);
            this.shared = true;
            return scopeImpl;
        }

        @Override // com.sun.tools.javac.code.Scope.WriteableScope
        public WriteableScope dupUnshared(Symbol symbol) {
            if (!this.shared) {
                return new ScopeImpl(this, symbol, (Entry[]) this.table.clone(), this.nelems);
            }
            Set setNewSetFromMap = Collections.newSetFromMap(new IdentityHashMap());
            for (ScopeImpl scopeImpl = this; scopeImpl != null; scopeImpl = scopeImpl.next) {
                setNewSetFromMap.add(scopeImpl);
            }
            Entry[] entryArr = this.table;
            Entry[] entryArr2 = new Entry[entryArr.length];
            int i = 0;
            for (int i2 = 0; i2 < entryArr.length; i2++) {
                Entry entry = entryArr[i2];
                while (entry != null && entry != sentinel && !setNewSetFromMap.contains(entry.scope)) {
                    entry = entry.shadowed;
                }
                if (entry != null) {
                    i++;
                    entryArr2[i2] = entry;
                }
            }
            return new ScopeImpl(this, symbol, entryArr2, i);
        }

        @Override // com.sun.tools.javac.code.Scope.WriteableScope
        public void enter(Symbol symbol) {
            Assert.check(!this.shared);
            if (this.nelems * 3 >= this.hashMask * 2) {
                dble();
            }
            int index = getIndex(symbol.name);
            Entry entry = this.table[index];
            if (entry == null) {
                entry = sentinel;
                this.nelems++;
            }
            Entry entry2 = new Entry(symbol, entry, this.elems, this);
            this.table[index] = entry2;
            this.elems = entry2;
            this.listeners.symbolAdded(symbol, this);
        }

        @Override // com.sun.tools.javac.code.Scope.WriteableScope
        public void enterIfAbsent(Symbol symbol) {
            ScopeImpl scopeImpl;
            Assert.check(!this.shared);
            Entry entryLookup = lookup(symbol.name);
            while (true) {
                scopeImpl = entryLookup.scope;
                if (scopeImpl != this || entryLookup.sym.kind == symbol.kind) {
                    break;
                } else {
                    entryLookup = entryLookup.next();
                }
            }
            if (scopeImpl != this) {
                enter(symbol);
            }
        }

        @Override // com.sun.tools.javac.code.Scope
        public Symbol findFirst(Name name, Predicate<Symbol> predicate) {
            return lookup(name, predicate).sym;
        }

        public int getIndex(Name name) {
            int iHashCode = name.hashCode();
            int i = this.hashMask;
            int i2 = iHashCode & i;
            int i3 = i - ((iHashCode + (iHashCode >> 16)) << 1);
            int i4 = -1;
            while (true) {
                Entry entry = this.table[i2];
                if (entry != null) {
                    if (entry == sentinel) {
                        if (i4 < 0) {
                            i4 = i2;
                        }
                    } else if (entry.sym.name == name) {
                        break;
                    }
                    i2 = (i2 + i3) & this.hashMask;
                } else if (i4 >= 0) {
                    return i4;
                }
            }
            return i2;
        }

        @Override // com.sun.tools.javac.code.Scope
        public Scope getOrigin(Symbol symbol) {
            for (Entry entryLookup = lookup(symbol.name); entryLookup.scope != null; entryLookup = entryLookup.next()) {
                if (entryLookup.sym == symbol) {
                    return this;
                }
            }
            return null;
        }

        @Override // com.sun.tools.javac.code.Scope
        public Iterable<Symbol> getSymbols(final Predicate<Symbol> predicate, final LookupKind lookupKind) {
            return new Iterable() { // from class: com.sun.tools.javac.code.m
                @Override // java.lang.Iterable
                public final Iterator iterator() {
                    return Scope.ScopeImpl.c(this.b, lookupKind, predicate);
                }
            };
        }

        @Override // com.sun.tools.javac.code.Scope
        public Iterable<Symbol> getSymbolsByName(final Name name, final Predicate<Symbol> predicate, final LookupKind lookupKind) {
            return new Iterable() { // from class: com.sun.tools.javac.code.o
                @Override // java.lang.Iterable
                public final Iterator iterator() {
                    return Scope.ScopeImpl.d(this.b, name, predicate, lookupKind);
                }
            };
        }

        @Override // com.sun.tools.javac.code.Scope
        public boolean includes(Symbol symbol) {
            for (Entry entryLookup = lookup(symbol.name); entryLookup.scope == this; entryLookup = entryLookup.next()) {
                if (entryLookup.sym == symbol) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.sun.tools.javac.code.Scope
        public boolean isStaticallyImported(Symbol symbol) {
            return false;
        }

        @Override // com.sun.tools.javac.code.Scope.WriteableScope
        public WriteableScope leave() {
            Assert.check(!this.shared);
            Entry[] entryArr = this.table;
            ScopeImpl scopeImpl = this.next;
            if (entryArr != scopeImpl.table) {
                return scopeImpl;
            }
            while (true) {
                Entry entry = this.elems;
                boolean z = false;
                if (entry == null) {
                    Assert.check(this.next.shared);
                    ScopeImpl scopeImpl2 = this.next;
                    scopeImpl2.shared = false;
                    scopeImpl2.nelems = this.nelems;
                    return scopeImpl2;
                }
                int index = getIndex(entry.sym.name);
                Entry entry2 = this.table[index];
                Entry entry3 = this.elems;
                if (entry2 == entry3) {
                    z = true;
                }
                Assert.check(z, entry3.sym);
                this.table[index] = this.elems.shadowed;
                this.elems = this.elems.nextSibling;
            }
        }

        public Entry lookup(Name name, Predicate<Symbol> predicate) {
            Entry entry = this.table[getIndex(name)];
            if (entry == null || entry == sentinel) {
                return sentinel;
            }
            while (entry.scope != null) {
                Symbol symbol = entry.sym;
                if (symbol.name == name && (predicate == null || predicate.test(symbol))) {
                    break;
                }
                entry = entry.shadowed;
            }
            return entry;
        }

        @Override // com.sun.tools.javac.code.Scope.WriteableScope
        public void remove(final Symbol symbol) {
            Assert.check(!this.shared);
            Entry entryLookup = lookup(symbol.name, new Predicate() { // from class: com.sun.tools.javac.code.n
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return Scope.ScopeImpl.b(symbol, (Symbol) obj);
                }
            });
            if (entryLookup.scope == null) {
                return;
            }
            int index = getIndex(symbol.name);
            Entry[] entryArr = this.table;
            Entry entry = entryArr[index];
            if (entry == entryLookup) {
                entryArr[index] = entryLookup.shadowed;
            } else {
                while (entry.shadowed != entryLookup) {
                    entry = entry.shadowed;
                }
                entry.shadowed = entryLookup.shadowed;
            }
            if (this.elems == entryLookup) {
                Entry entry2 = entryLookup.nextSibling;
                this.elems = entry2;
                if (entry2 != null) {
                    entry2.prevSibling = null;
                }
            } else {
                Assert.check(entryLookup.prevSibling != null, entryLookup.sym);
                Entry entry3 = entryLookup.prevSibling;
                entry3.nextSibling = entryLookup.nextSibling;
                Entry entry4 = entryLookup.nextSibling;
                if (entry4 != null) {
                    entry4.prevSibling = entry3;
                }
            }
            this.removeCount++;
            this.listeners.symbolRemoved(symbol, this);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("Scope[");
            for (ScopeImpl scopeImpl = this; scopeImpl != null; scopeImpl = scopeImpl.next) {
                if (scopeImpl != this) {
                    sb.append(" | ");
                }
                for (Entry entry = scopeImpl.elems; entry != null; entry = entry.nextSibling) {
                    if (entry != scopeImpl.elems) {
                        sb.append(", ");
                    }
                    sb.append(entry.sym);
                }
            }
            sb.append("]");
            return sb.toString();
        }

        private ScopeImpl(ScopeImpl scopeImpl, Symbol symbol, Entry[] entryArr, int i) {
            this(scopeImpl, symbol, entryArr);
            this.nelems = i;
        }

        public ScopeImpl(Symbol symbol) {
            this(null, symbol, new Entry[16]);
        }

        public Entry lookup(Name name) {
            return lookup(name, Scope.noFilter);
        }
    }
}

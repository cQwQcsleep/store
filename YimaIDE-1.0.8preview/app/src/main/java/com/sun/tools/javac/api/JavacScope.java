package com.sun.tools.javac.api;

import com.sun.source.tree.Scope;
import com.sun.tools.javac.api.JavacScope;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.comp.AttrContext;
import com.sun.tools.javac.comp.Env;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Assert;
import java.util.function.Predicate;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JavacScope implements Scope {
    private static final Predicate<Symbol> VALIDATOR = new Predicate() { // from class: gn7
        @Override // java.util.function.Predicate
        public final boolean test(Object obj) {
            return JavacScope.a((Symbol) obj);
        }
    };
    protected final Env<AttrContext> env;

    public enum ScopeType {
        ORDINARY,
        STAR_IMPORT,
        MODULE_IMPORT
    }

    private JavacScope(Env<AttrContext> env) {
        this.env = (Env) Assert.checkNonNull(env);
    }

    public static /* synthetic */ boolean a(Symbol symbol) {
        symbol.apiComplete();
        return symbol.kind != Kinds.Kind.ERR;
    }

    public static JavacScope create(Env<AttrContext> env) {
        Env<AttrContext> env2 = env.outer;
        return (env2 == null || env2 == env) ? new JavacScope(env) { // from class: com.sun.tools.javac.api.JavacScope.1
            @Override // com.sun.tools.javac.api.JavacScope, com.sun.source.tree.Scope
            public /* bridge */ /* synthetic */ Scope getEnclosingScope() {
                return super.getEnclosingScope();
            }

            @Override // com.sun.tools.javac.api.JavacScope, com.sun.source.tree.Scope
            public Iterable<? extends Element> getLocalElements() {
                com.sun.tools.javac.code.Scope.CompoundScope compoundScope = new com.sun.tools.javac.code.Scope.CompoundScope(this.env.toplevel.packge);
                compoundScope.prependSubScope(this.env.toplevel.toplevelScope);
                compoundScope.prependSubScope(this.env.toplevel.namedImportScope);
                return compoundScope.getSymbols(JavacScope.VALIDATOR);
            }
        } : new JavacScope(env);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof JavacScope)) {
            return false;
        }
        JavacScope javacScope = (JavacScope) obj;
        return this.env.equals(javacScope.env) && getScopeType() == javacScope.getScopeType();
    }

    @Override // com.sun.source.tree.Scope
    public TypeElement getEnclosingClass() {
        Env<AttrContext> env = this.env;
        Env<AttrContext> env2 = env.outer;
        if (env2 == null || env2 == env) {
            return null;
        }
        return env.enclClass.sym;
    }

    @Override // com.sun.source.tree.Scope
    public ExecutableElement getEnclosingMethod() {
        JCTree.JCMethodDecl jCMethodDecl = this.env.enclMethod;
        if (jCMethodDecl == null) {
            return null;
        }
        return jCMethodDecl.sym;
    }

    @Override // com.sun.source.tree.Scope
    public JavacScope getEnclosingScope() {
        Env<AttrContext> env = this.env;
        Env<AttrContext> env2 = env.outer;
        return (env2 == null || env2 == env) ? new JavacScope(env) { // from class: com.sun.tools.javac.api.JavacScope.2
            @Override // com.sun.tools.javac.api.JavacScope, com.sun.source.tree.Scope
            public JavacScope getEnclosingScope() {
                return new JavacScope(this.env) { // from class: com.sun.tools.javac.api.JavacScope.2.1
                    @Override // com.sun.tools.javac.api.JavacScope, com.sun.source.tree.Scope
                    public Iterable<? extends Element> getLocalElements() {
                        return this.env.toplevel.moduleImportScope.getSymbols(JavacScope.VALIDATOR);
                    }

                    @Override // com.sun.tools.javac.api.JavacScope
                    public ScopeType getScopeType() {
                        return ScopeType.MODULE_IMPORT;
                    }

                    @Override // com.sun.tools.javac.api.JavacScope, com.sun.source.tree.Scope
                    public JavacScope getEnclosingScope() {
                        return null;
                    }
                };
            }

            @Override // com.sun.tools.javac.api.JavacScope, com.sun.source.tree.Scope
            public Iterable<? extends Element> getLocalElements() {
                return this.env.toplevel.starImportScope.getSymbols(JavacScope.VALIDATOR);
            }

            @Override // com.sun.tools.javac.api.JavacScope
            public ScopeType getScopeType() {
                return ScopeType.STAR_IMPORT;
            }
        } : create(env2);
    }

    public Env<AttrContext> getEnv() {
        return this.env;
    }

    @Override // com.sun.source.tree.Scope
    public Iterable<? extends Element> getLocalElements() {
        return this.env.info.getLocalElements();
    }

    public ScopeType getScopeType() {
        return ScopeType.ORDINARY;
    }

    public int hashCode() {
        return this.env.hashCode() + getScopeType().hashCode();
    }

    public String toString() {
        return "JavacScope[env=" + this.env + ", scope type=" + getScopeType() + "]";
    }
}

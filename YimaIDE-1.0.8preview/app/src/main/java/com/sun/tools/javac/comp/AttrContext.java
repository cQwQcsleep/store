package com.sun.tools.javac.comp;

import com.sun.tools.javac.code.Lint;
import com.sun.tools.javac.code.Scope;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AttrContext {
    Lint lint;
    JCTree preferredTreeForDiagnostics;
    Scope.WriteableScope scope = null;
    int staticLevel = 0;
    boolean ctorPrologue = false;
    boolean selectSuper = false;
    boolean isSerializable = false;
    boolean isSerializableLambda = false;
    boolean isLambda = false;
    DeferredAttr.AttributionMode attributionMode = DeferredAttr.AttributionMode.FULL;
    boolean isAnonymousDiamond = false;
    boolean isAnonymousNewClass = false;
    boolean visitingServiceImplementation = false;
    boolean allowProtectedAccess = false;
    boolean isPermitsClause = false;
    Resolve.MethodResolutionPhase pendingResolutionPhase = null;
    Symbol enclVar = null;
    Attr.ResultInfo returnResult = null;
    Attr.ResultInfo yieldResult = null;
    Type defaultSuperCallSite = null;

    public AttrContext dup(Scope.WriteableScope writeableScope) {
        AttrContext attrContext = new AttrContext();
        attrContext.scope = writeableScope;
        attrContext.staticLevel = this.staticLevel;
        attrContext.ctorPrologue = this.ctorPrologue;
        attrContext.selectSuper = this.selectSuper;
        attrContext.pendingResolutionPhase = this.pendingResolutionPhase;
        attrContext.lint = this.lint;
        attrContext.enclVar = this.enclVar;
        attrContext.returnResult = this.returnResult;
        attrContext.yieldResult = this.yieldResult;
        attrContext.defaultSuperCallSite = this.defaultSuperCallSite;
        attrContext.isSerializable = this.isSerializable;
        attrContext.isLambda = this.isLambda;
        attrContext.isSerializableLambda = this.isSerializableLambda;
        attrContext.attributionMode = this.attributionMode;
        attrContext.isAnonymousDiamond = this.isAnonymousDiamond;
        attrContext.isAnonymousNewClass = this.isAnonymousNewClass;
        attrContext.preferredTreeForDiagnostics = this.preferredTreeForDiagnostics;
        attrContext.visitingServiceImplementation = this.visitingServiceImplementation;
        attrContext.allowProtectedAccess = this.allowProtectedAccess;
        attrContext.isPermitsClause = this.isPermitsClause;
        return attrContext;
    }

    public Iterable<Symbol> getLocalElements() {
        Scope.WriteableScope writeableScope = this.scope;
        return writeableScope == null ? List.nil() : writeableScope.getSymbols();
    }

    public boolean lastResolveVarargs() {
        Resolve.MethodResolutionPhase methodResolutionPhase = this.pendingResolutionPhase;
        return methodResolutionPhase != null && methodResolutionPhase.isVarargsRequired();
    }

    public String toString() {
        return "AttrContext[" + this.scope.toString() + "]";
    }

    public AttrContext dup() {
        return dup(this.scope);
    }
}

package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class VariableRefBase extends Expression {
    protected Closure _closure;
    protected VariableBase _variable;

    public VariableRefBase(VariableBase variableBase) {
        this._closure = null;
        this._variable = variableBase;
        variableBase.addReference(this);
    }

    public void addParentDependency() {
        SyntaxTreeNode parent = this;
        while (parent != null && !(parent instanceof TopLevelElement)) {
            parent = parent.getParent();
        }
        TopLevelElement topLevelElement = (TopLevelElement) parent;
        if (topLevelElement != null) {
            VariableBase variableBaseLookupParam = this._variable;
            if (variableBaseLookupParam._ignore) {
                if (variableBaseLookupParam instanceof Variable) {
                    variableBaseLookupParam = topLevelElement.getSymbolTable().lookupVariable(this._variable._name);
                } else if (variableBaseLookupParam instanceof Param) {
                    variableBaseLookupParam = topLevelElement.getSymbolTable().lookupParam(this._variable._name);
                }
            }
            topLevelElement.addDependency(variableBaseLookupParam);
        }
    }

    public boolean equals(Object obj) {
        if (obj != this) {
            return (obj instanceof VariableRefBase) && this._variable == ((VariableRefBase) obj)._variable;
        }
        return true;
    }

    public VariableBase getVariable() {
        return this._variable;
    }

    public int hashCode() {
        return Objects.hashCode(this._variable);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public String toString() {
        return "variable-ref(" + this._variable.getName() + '/' + this._variable.getType() + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        Type type = this._type;
        if (type != null) {
            return type;
        }
        if (this._variable.isLocal()) {
            SyntaxTreeNode parent = getParent();
            do {
                if (parent instanceof Closure) {
                    this._closure = (Closure) parent;
                    break;
                }
                if (parent instanceof TopLevelElement) {
                    break;
                }
                parent = parent.getParent();
            } while (parent != 0);
            Closure closure = this._closure;
            if (closure != null) {
                closure.addVariable(this);
            }
        }
        Type type2 = this._variable.getType();
        this._type = type2;
        if (type2 == null) {
            this._variable.typeCheck(symbolTable);
            this._type = this._variable.getType();
        }
        addParentDependency();
        return this._type;
    }

    public VariableRefBase() {
        this._closure = null;
        this._variable = null;
    }
}

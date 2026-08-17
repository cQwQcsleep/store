package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class LocationPathPattern extends Pattern {
    private int _importPrecedence;
    private Template _template;
    private double _priority = Double.NaN;
    private int _position = 0;

    public int getAxis() {
        StepPattern kernelPattern = getKernelPattern();
        if (kernelPattern != null) {
            return kernelPattern.getAxis();
        }
        return 3;
    }

    public double getDefaultPriority() {
        return 0.5d;
    }

    public abstract StepPattern getKernelPattern();

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern
    public final double getPriority() {
        return Double.isNaN(this._priority) ? getDefaultPriority() : this._priority;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Template getTemplate() {
        return this._template;
    }

    public abstract boolean isWildcard();

    public boolean noSmallerThan(LocationPathPattern locationPathPattern) {
        int i = this._importPrecedence;
        int i2 = locationPathPattern._importPrecedence;
        if (i > i2) {
            return true;
        }
        if (i != i2) {
            return false;
        }
        double d = this._priority;
        double d2 = locationPathPattern._priority;
        if (d > d2) {
            return true;
        }
        return d == d2 && this._position > locationPathPattern._position;
    }

    public abstract void reduceKernelPattern();

    public void setTemplate(Template template) {
        this._template = template;
        this._priority = template.getPriority();
        this._importPrecedence = template.getImportPrecedence();
        this._position = template.getPosition();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public String toString() {
        return "root()";
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        return Type.Void;
    }
}

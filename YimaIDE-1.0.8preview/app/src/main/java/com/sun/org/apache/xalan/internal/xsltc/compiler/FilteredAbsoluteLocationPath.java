package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.sun.org.apache.bcel.internal.generic.ASTORE;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class FilteredAbsoluteLocationPath extends Expression {
    private Expression _path;

    public FilteredAbsoluteLocationPath(Expression expression) {
        this._path = expression;
        if (expression != null) {
            expression.setParent(this);
        }
    }

    public Expression getPath() {
        return this._path;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void setParser(Parser parser) {
        super.setParser(parser);
        Expression expression = this._path;
        if (expression != null) {
            expression.setParser(parser);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public String toString() {
        StringBuilder sb = new StringBuilder("FilteredAbsoluteLocationPath(");
        Expression expression = this._path;
        sb.append(expression != null ? expression.toString() : PsiKeyword.NULL);
        sb.append(')');
        return sb.toString();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        if (this._path == null) {
            int iAddInterfaceMethodref = constantPool.addInterfaceMethodref(Constants.DOM_INTF, "getIterator", "()Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;");
            instructionList.append(methodGenerator.loadDOM());
            instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref, 1));
            return;
        }
        int iAddMethodref = constantPool.addMethodref(Constants.DUP_FILTERED_ITERATOR, Const.CONSTRUCTOR_NAME, "(Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;)V");
        LocalVariableGen localVariableGenAddLocalVariable = methodGenerator.addLocalVariable("filtered_absolute_location_path_tmp", Util.getJCRefType("Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;"), null, null);
        this._path.translate(classGenerator, methodGenerator);
        localVariableGenAddLocalVariable.setStart(instructionList.append(new ASTORE(localVariableGenAddLocalVariable.getIndex())));
        instructionList.append(new NEW(constantPool.addClass(Constants.DUP_FILTERED_ITERATOR)));
        instructionList.append(Constants.DUP);
        localVariableGenAddLocalVariable.setEnd(instructionList.append(new ALOAD(localVariableGenAddLocalVariable.getIndex())));
        instructionList.append(new INVOKESPECIAL(iAddMethodref));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        Expression expression = this._path;
        if (expression != null && (expression.typeCheck(symbolTable) instanceof NodeType)) {
            this._path = new CastExpr(this._path, Type.NodeSet);
        }
        Type type = Type.NodeSet;
        this._type = type;
        return type;
    }

    public FilteredAbsoluteLocationPath() {
        this._path = null;
    }
}

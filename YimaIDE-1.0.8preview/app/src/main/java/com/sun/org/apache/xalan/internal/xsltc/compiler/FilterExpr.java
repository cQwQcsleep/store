package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.sun.org.apache.bcel.internal.generic.ASTORE;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.ILOAD;
import com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import com.sun.org.apache.bcel.internal.generic.ISTORE;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeSetType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ReferenceType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class FilterExpr extends Expression {
    private final List<Expression> _predicates;
    private Expression _primary;

    public FilterExpr(Expression expression, List<Expression> list) {
        this._primary = expression;
        this._predicates = list;
        expression.setParent(this);
    }

    private void translateFilterExpr(ClassGenerator classGenerator, MethodGenerator methodGenerator, int i) {
        if (i >= 0) {
            translatePredicates(classGenerator, methodGenerator, i);
        } else {
            this._primary.translate(classGenerator, methodGenerator);
        }
    }

    public Expression getExpr() {
        Expression expression = this._primary;
        return expression instanceof CastExpr ? ((CastExpr) expression).getExpr() : expression;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void setParser(Parser parser) {
        super.setParser(parser);
        this._primary.setParser(parser);
        List<Expression> list = this._predicates;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Expression expression = this._predicates.get(i);
                expression.setParser(parser);
                expression.setParent(this);
            }
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public String toString() {
        return "filter-expr(" + this._primary + ", " + this._predicates + ")";
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        List<Expression> list = this._predicates;
        translateFilterExpr(classGenerator, methodGenerator, list == null ? -1 : list.size() - 1);
    }

    public void translatePredicates(ClassGenerator classGenerator, MethodGenerator methodGenerator, int i) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        if (i < 0) {
            translateFilterExpr(classGenerator, methodGenerator, i);
            return;
        }
        int i2 = i - 1;
        Predicate predicate = (Predicate) this._predicates.get(i);
        translatePredicates(classGenerator, methodGenerator, i2);
        if (predicate.isNthPositionFilter()) {
            int iAddMethodref = constantPool.addMethodref(Constants.NTH_ITERATOR_CLASS, Const.CONSTRUCTOR_NAME, "(Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;I)V");
            LocalVariableGen localVariableGenAddLocalVariable = methodGenerator.addLocalVariable("filter_expr_tmp1", Util.getJCRefType("Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;"), null, null);
            localVariableGenAddLocalVariable.setStart(instructionList.append(new ASTORE(localVariableGenAddLocalVariable.getIndex())));
            predicate.translate(classGenerator, methodGenerator);
            LocalVariableGen localVariableGenAddLocalVariable2 = methodGenerator.addLocalVariable("filter_expr_tmp2", Util.getJCRefType("I"), null, null);
            localVariableGenAddLocalVariable2.setStart(instructionList.append(new ISTORE(localVariableGenAddLocalVariable2.getIndex())));
            instructionList.append(new NEW(constantPool.addClass(Constants.NTH_ITERATOR_CLASS)));
            instructionList.append(Constants.DUP);
            localVariableGenAddLocalVariable.setEnd(instructionList.append(new ALOAD(localVariableGenAddLocalVariable.getIndex())));
            localVariableGenAddLocalVariable2.setEnd(instructionList.append(new ILOAD(localVariableGenAddLocalVariable2.getIndex())));
            instructionList.append(new INVOKESPECIAL(iAddMethodref));
            return;
        }
        int iAddMethodref2 = constantPool.addMethodref(Constants.CURRENT_NODE_LIST_ITERATOR, Const.CONSTRUCTOR_NAME, "(Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;ZLcom/sun/org/apache/xalan/internal/xsltc/dom/CurrentNodeListFilter;ILcom/sun/org/apache/xalan/internal/xsltc/runtime/AbstractTranslet;)V");
        LocalVariableGen localVariableGenAddLocalVariable3 = methodGenerator.addLocalVariable("filter_expr_tmp1", Util.getJCRefType("Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;"), null, null);
        localVariableGenAddLocalVariable3.setStart(instructionList.append(new ASTORE(localVariableGenAddLocalVariable3.getIndex())));
        predicate.translate(classGenerator, methodGenerator);
        LocalVariableGen localVariableGenAddLocalVariable4 = methodGenerator.addLocalVariable("filter_expr_tmp2", Util.getJCRefType(Constants.CURRENT_NODE_LIST_FILTER_SIG), null, null);
        localVariableGenAddLocalVariable4.setStart(instructionList.append(new ASTORE(localVariableGenAddLocalVariable4.getIndex())));
        instructionList.append(new NEW(constantPool.addClass(Constants.CURRENT_NODE_LIST_ITERATOR)));
        instructionList.append(Constants.DUP);
        localVariableGenAddLocalVariable3.setEnd(instructionList.append(new ALOAD(localVariableGenAddLocalVariable3.getIndex())));
        instructionList.append(Constants.ICONST_1);
        localVariableGenAddLocalVariable4.setEnd(instructionList.append(new ALOAD(localVariableGenAddLocalVariable4.getIndex())));
        instructionList.append(methodGenerator.loadCurrentNode());
        instructionList.append(classGenerator.loadTranslet());
        instructionList.append(new INVOKESPECIAL(iAddMethodref2));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        Type typeTypeCheck = this._primary.typeCheck(symbolTable);
        boolean z = this._primary instanceof KeyCall;
        if (!(typeTypeCheck instanceof NodeSetType)) {
            if (!(typeTypeCheck instanceof ReferenceType)) {
                throw new TypeCheckError(this);
            }
            this._primary = new CastExpr(this._primary, Type.NodeSet);
        }
        int size = this._predicates.size();
        for (int i = 0; i < size; i++) {
            Predicate predicate = (Predicate) this._predicates.get(i);
            if (!z) {
                predicate.dontOptimize();
            }
            predicate.typeCheck(symbolTable);
        }
        Type type = Type.NodeSet;
        this._type = type;
        return type;
    }
}

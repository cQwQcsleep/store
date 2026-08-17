package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.INVOKESTATIC;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class NameBase extends FunctionCall {
    private Expression _param;
    private Type _paramType;

    public NameBase(QName qName, List<Expression> list) {
        super(qName, list);
        this._param = null;
        this._paramType = Type.Node;
        this._param = argument(0);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public Type getType() {
        return this._type;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.FunctionCall, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        instructionList.append(methodGenerator.loadDOM());
        if (argumentCount() == 0) {
            instructionList.append(methodGenerator.loadContextNode());
            return;
        }
        Type type = this._paramType;
        if (type == Type.Node) {
            this._param.translate(classGenerator, methodGenerator);
            return;
        }
        Type type2 = Type.Reference;
        Expression expression = this._param;
        if (type == type2) {
            expression.translate(classGenerator, methodGenerator);
            instructionList.append(new INVOKESTATIC(constantPool.addMethodref(Constants.BASIS_LIBRARY_CLASS, "referenceToNodeSet", "(Ljava/lang/Object;)Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;")));
            instructionList.append(methodGenerator.nextNode());
        } else {
            expression.translate(classGenerator, methodGenerator);
            this._param.startIterator(classGenerator, methodGenerator);
            instructionList.append(methodGenerator.nextNode());
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.FunctionCall, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        int iArgumentCount = argumentCount();
        if (iArgumentCount == 0) {
            this._paramType = Type.Node;
        } else {
            if (iArgumentCount != 1) {
                throw new TypeCheckError(this);
            }
            this._paramType = this._param.typeCheck(symbolTable);
        }
        Type type = this._paramType;
        if (type != Type.NodeSet && type != Type.Node && type != Type.Reference) {
            throw new TypeCheckError(this);
        }
        Type type2 = Type.String;
        this._type = type2;
        return type2;
    }

    public NameBase(QName qName) {
        super(qName);
        this._param = null;
        this._paramType = Type.Node;
    }
}

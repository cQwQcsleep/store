package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKESTATIC;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeSetType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ReferenceType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ResultTreeType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class CopyOf extends Instruction {
    private Expression _select;

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void display(int i) {
        indent(i);
        Util.println("CopyOf");
        indent(i + 4);
        Util.println("select " + this._select.toString());
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void parseContents(Parser parser) {
        Expression expression = parser.parseExpression(this, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_SELECT, null);
        this._select = expression;
        if (expression.isDummy()) {
            reportError(this, parser, ErrorMsg.REQUIRED_ATTR_ERR, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_SELECT);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        Type type = this._select.getType();
        int iAddInterfaceMethodref = constantPool.addInterfaceMethodref(Constants.DOM_INTF, com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_COPY_STRING, "(Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;Lcom/sun/org/apache/xml/internal/serializer/SerializationHandler;)V");
        int iAddInterfaceMethodref2 = constantPool.addInterfaceMethodref(Constants.DOM_INTF, com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_COPY_STRING, Constants.CHARACTERS_SIG);
        int iAddInterfaceMethodref3 = constantPool.addInterfaceMethodref(Constants.DOM_INTF, "getDocument", "()I");
        if (type instanceof NodeSetType) {
            instructionList.append(methodGenerator.loadDOM());
            this._select.translate(classGenerator, methodGenerator);
            this._select.startIterator(classGenerator, methodGenerator);
            instructionList.append(methodGenerator.loadHandler());
            instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref, 3));
            return;
        }
        if (type instanceof NodeType) {
            instructionList.append(methodGenerator.loadDOM());
            this._select.translate(classGenerator, methodGenerator);
            instructionList.append(methodGenerator.loadHandler());
            instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref2, 3));
            return;
        }
        if (type instanceof ResultTreeType) {
            this._select.translate(classGenerator, methodGenerator);
            instructionList.append(Constants.DUP);
            instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref3, 1));
            instructionList.append(methodGenerator.loadHandler());
            instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref2, 3));
            return;
        }
        if (!(type instanceof ReferenceType)) {
            instructionList.append(classGenerator.loadTranslet());
            this._select.translate(classGenerator, methodGenerator);
            instructionList.append(methodGenerator.loadHandler());
            instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(Constants.TRANSLET_CLASS, "characters", Constants.CHARACTERSW_SIG)));
            return;
        }
        this._select.translate(classGenerator, methodGenerator);
        instructionList.append(methodGenerator.loadHandler());
        instructionList.append(methodGenerator.loadCurrentNode());
        instructionList.append(methodGenerator.loadDOM());
        instructionList.append(new INVOKESTATIC(constantPool.addMethodref(Constants.BASIS_LIBRARY_CLASS, com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_COPY_STRING, "(Ljava/lang/Object;Lcom/sun/org/apache/xml/internal/serializer/SerializationHandler;ILcom/sun/org/apache/xalan/internal/xsltc/DOM;)V")));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        Type typeTypeCheck = this._select.typeCheck(symbolTable);
        if (!(typeTypeCheck instanceof NodeType) && !(typeTypeCheck instanceof NodeSetType) && !(typeTypeCheck instanceof ReferenceType) && !(typeTypeCheck instanceof ResultTreeType)) {
            this._select = new CastExpr(this._select, Type.String);
        }
        return Type.Void;
    }
}

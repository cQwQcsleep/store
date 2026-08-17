package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import jdk.xml.internal.JdkConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class ValueOf extends Instruction {
    private boolean _escaping = true;
    private boolean _isString = false;
    private Expression _select;

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void display(int i) {
        indent(i);
        Util.println("ValueOf");
        indent(i + 4);
        Util.println("select " + this._select.toString());
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void parseContents(Parser parser) {
        Expression expression = parser.parseExpression(this, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_SELECT, null);
        this._select = expression;
        if (expression.isDummy()) {
            reportError(this, parser, ErrorMsg.REQUIRED_ATTR_ERR, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_SELECT);
            return;
        }
        String attribute = getAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_DISABLE_OUTPUT_ESCAPING);
        if (attribute == null || !attribute.equals(JdkConstants.JDK_YES)) {
            return;
        }
        this._escaping = false;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        int iAddInterfaceMethodref = constantPool.addInterfaceMethodref(Constants.OUTPUT_HANDLER, "setEscaping", "(Z)Z");
        if (!this._escaping) {
            instructionList.append(methodGenerator.loadHandler());
            instructionList.append(new PUSH(constantPool, false));
            instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref, 2));
        }
        if (this._isString) {
            int iAddMethodref = constantPool.addMethodref(Constants.TRANSLET_CLASS, "characters", Constants.CHARACTERSW_SIG);
            instructionList.append(classGenerator.loadTranslet());
            this._select.translate(classGenerator, methodGenerator);
            instructionList.append(methodGenerator.loadHandler());
            instructionList.append(new INVOKEVIRTUAL(iAddMethodref));
        } else {
            int iAddInterfaceMethodref2 = constantPool.addInterfaceMethodref(Constants.DOM_INTF, "characters", Constants.CHARACTERS_SIG);
            instructionList.append(methodGenerator.loadDOM());
            this._select.translate(classGenerator, methodGenerator);
            instructionList.append(methodGenerator.loadHandler());
            instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref2, 3));
        }
        if (this._escaping) {
            return;
        }
        instructionList.append(methodGenerator.loadHandler());
        instructionList.append(Constants.SWAP);
        instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref, 2));
        instructionList.append(Constants.POP);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        Type typeTypeCheck = this._select.typeCheck(symbolTable);
        if (typeTypeCheck != null) {
            Type type = Type.Node;
            if (!typeTypeCheck.identicalTo(type)) {
                if (typeTypeCheck.identicalTo(Type.NodeSet)) {
                    this._select = new CastExpr(this._select, type);
                } else {
                    this._isString = true;
                    Type type2 = Type.String;
                    if (!typeTypeCheck.identicalTo(type2)) {
                        this._select = new CastExpr(this._select, type2);
                    }
                    this._isString = true;
                }
            }
        }
        return Type.Void;
    }
}

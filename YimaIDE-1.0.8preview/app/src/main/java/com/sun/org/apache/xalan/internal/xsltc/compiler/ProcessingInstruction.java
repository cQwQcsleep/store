package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.sun.org.apache.bcel.internal.generic.ASTORE;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKESTATIC;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import com.sun.org.apache.xml.internal.utils.XML11Char;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class ProcessingInstruction extends Instruction {
    private boolean _isLiteral = false;
    private AttributeValue _name;

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void parseContents(Parser parser) {
        String attribute = getAttribute("name");
        if (attribute.length() > 0) {
            boolean zIsLiteral = Util.isLiteral(attribute);
            this._isLiteral = zIsLiteral;
            if (zIsLiteral && !XML11Char.isXML11ValidNCName(attribute)) {
                parser.reportError(3, new ErrorMsg("INVALID_NCNAME_ERR", (Object) attribute, (SyntaxTreeNode) this));
            }
            this._name = AttributeValue.create(this, attribute, parser);
        } else {
            reportError(this, parser, ErrorMsg.REQUIRED_ATTR_ERR, "name");
        }
        if (attribute.equals("xml")) {
            reportError(this, parser, ErrorMsg.ILLEGAL_PI_ERR, "xml");
        }
        parseChildren(parser);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        if (this._isLiteral) {
            instructionList.append(methodGenerator.loadHandler());
            instructionList.append(Constants.DUP);
            this._name.translate(classGenerator, methodGenerator);
        } else {
            LocalVariableGen localVariableGenAddLocalVariable2 = methodGenerator.addLocalVariable2("nameValue", Util.getJCRefType(Constants.STRING_SIG), null);
            this._name.translate(classGenerator, methodGenerator);
            localVariableGenAddLocalVariable2.setStart(instructionList.append(new ASTORE(localVariableGenAddLocalVariable2.getIndex())));
            instructionList.append(new ALOAD(localVariableGenAddLocalVariable2.getIndex()));
            instructionList.append(new INVOKESTATIC(constantPool.addMethodref(Constants.BASIS_LIBRARY_CLASS, "checkNCName", "(Ljava/lang/String;)V")));
            instructionList.append(methodGenerator.loadHandler());
            instructionList.append(Constants.DUP);
            localVariableGenAddLocalVariable2.setEnd(instructionList.append(new ALOAD(localVariableGenAddLocalVariable2.getIndex())));
        }
        instructionList.append(classGenerator.loadTranslet());
        instructionList.append(new GETFIELD(constantPool.addFieldref(Constants.TRANSLET_CLASS, "stringValueHandler", Constants.STRING_VALUE_HANDLER_SIG)));
        instructionList.append(Constants.DUP);
        instructionList.append(methodGenerator.storeHandler());
        translateContents(classGenerator, methodGenerator);
        instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(Constants.STRING_VALUE_HANDLER, "getValueOfPI", "()Ljava/lang/String;")));
        instructionList.append(new INVOKEINTERFACE(constantPool.addInterfaceMethodref(Constants.TRANSLET_OUTPUT_INTERFACE, "processingInstruction", "(Ljava/lang/String;Ljava/lang/String;)V"), 3));
        instructionList.append(methodGenerator.storeHandler());
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        this._name.typeCheck(symbolTable);
        typeCheckContents(symbolTable);
        return Type.Void;
    }
}

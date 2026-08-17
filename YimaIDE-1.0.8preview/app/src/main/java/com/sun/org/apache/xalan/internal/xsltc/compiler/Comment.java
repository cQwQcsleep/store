package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.bcel.internal.generic.StackInstruction;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class Comment extends Instruction {
    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void parseContents(Parser parser) {
        parseChildren(parser);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x001b  */
    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        Text text;
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        if (elementCount() == 1) {
            SyntaxTreeNode syntaxTreeNodeElementAt = elementAt(0);
            if (syntaxTreeNodeElementAt instanceof Text) {
                text = (Text) syntaxTreeNodeElementAt;
            } else {
                text = null;
            }
        } else {
            text = null;
        }
        if (text != null) {
            instructionList.append(methodGenerator.loadHandler());
            if (text.canLoadAsArrayOffsetLength()) {
                text.loadAsArrayOffsetLength(classGenerator, methodGenerator);
                instructionList.append(new INVOKEINTERFACE(constantPool.addInterfaceMethodref(Constants.TRANSLET_OUTPUT_INTERFACE, com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_COMMENT_STRING, "([CII)V"), 4));
                return;
            } else {
                instructionList.append(new PUSH(constantPool, text.getText()));
                instructionList.append(new INVOKEINTERFACE(constantPool.addInterfaceMethodref(Constants.TRANSLET_OUTPUT_INTERFACE, com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_COMMENT_STRING, "(Ljava/lang/String;)V"), 2));
                return;
            }
        }
        instructionList.append(methodGenerator.loadHandler());
        StackInstruction stackInstruction = Constants.DUP;
        instructionList.append(stackInstruction);
        instructionList.append(classGenerator.loadTranslet());
        instructionList.append(new GETFIELD(constantPool.addFieldref(Constants.TRANSLET_CLASS, "stringValueHandler", Constants.STRING_VALUE_HANDLER_SIG)));
        instructionList.append(stackInstruction);
        instructionList.append(methodGenerator.storeHandler());
        translateContents(classGenerator, methodGenerator);
        instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(Constants.STRING_VALUE_HANDLER, "getValue", "()Ljava/lang/String;")));
        instructionList.append(new INVOKEINTERFACE(constantPool.addInterfaceMethodref(Constants.TRANSLET_OUTPUT_INTERFACE, com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_COMMENT_STRING, "(Ljava/lang/String;)V"), 2));
        instructionList.append(methodGenerator.storeHandler());
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        typeCheckContents(symbolTable);
        return Type.String;
    }
}

package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.bcel.internal.generic.StackInstruction;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import jdk.xml.internal.JdkConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class Message extends Instruction {
    private boolean _terminate = false;

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void parseContents(Parser parser) {
        String attribute = getAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_TERMINATE);
        if (attribute != null) {
            this._terminate = attribute.equals(JdkConstants.JDK_YES);
        }
        parseChildren(parser);
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0036  */
    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        instructionList.append(classGenerator.loadTranslet());
        int iElementCount = elementCount();
        if (iElementCount == 0) {
            instructionList.append(new PUSH(constantPool, ""));
        } else if (iElementCount != 1) {
            instructionList.append(methodGenerator.loadHandler());
            instructionList.append(new NEW(constantPool.addClass(Constants.STREAM_XML_OUTPUT)));
            instructionList.append(methodGenerator.storeHandler());
            instructionList.append(new NEW(constantPool.addClass(Constants.STRING_WRITER)));
            StackInstruction stackInstruction = Constants.DUP;
            instructionList.append(stackInstruction);
            instructionList.append(stackInstruction);
            instructionList.append(new INVOKESPECIAL(constantPool.addMethodref(Constants.STRING_WRITER, Const.CONSTRUCTOR_NAME, "()V")));
            instructionList.append(methodGenerator.loadHandler());
            instructionList.append(new INVOKESPECIAL(constantPool.addMethodref(Constants.STREAM_XML_OUTPUT, Const.CONSTRUCTOR_NAME, "()V")));
            instructionList.append(methodGenerator.loadHandler());
            StackInstruction stackInstruction2 = Constants.SWAP;
            instructionList.append(stackInstruction2);
            instructionList.append(new INVOKEINTERFACE(constantPool.addInterfaceMethodref(Constants.TRANSLET_OUTPUT_INTERFACE, "setWriter", "(Ljava/io/Writer;)V"), 2));
            instructionList.append(methodGenerator.loadHandler());
            instructionList.append(new PUSH(constantPool, "UTF-8"));
            instructionList.append(new INVOKEINTERFACE(constantPool.addInterfaceMethodref(Constants.TRANSLET_OUTPUT_INTERFACE, "setEncoding", "(Ljava/lang/String;)V"), 2));
            instructionList.append(methodGenerator.loadHandler());
            instructionList.append(Constants.ICONST_1);
            instructionList.append(new INVOKEINTERFACE(constantPool.addInterfaceMethodref(Constants.TRANSLET_OUTPUT_INTERFACE, "setOmitXMLDeclaration", "(Z)V"), 2));
            instructionList.append(methodGenerator.loadHandler());
            instructionList.append(new INVOKEINTERFACE(constantPool.addInterfaceMethodref(Constants.TRANSLET_OUTPUT_INTERFACE, "startDocument", "()V"), 1));
            translateContents(classGenerator, methodGenerator);
            instructionList.append(methodGenerator.loadHandler());
            instructionList.append(new INVOKEINTERFACE(constantPool.addInterfaceMethodref(Constants.TRANSLET_OUTPUT_INTERFACE, "endDocument", "()V"), 1));
            instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(Constants.STRING_WRITER, "toString", "()Ljava/lang/String;")));
            instructionList.append(stackInstruction2);
            instructionList.append(methodGenerator.storeHandler());
        } else {
            SyntaxTreeNode syntaxTreeNodeElementAt = elementAt(0);
            if (syntaxTreeNodeElementAt instanceof Text) {
                instructionList.append(new PUSH(constantPool, ((Text) syntaxTreeNodeElementAt).getText()));
            } else {
                instructionList.append(methodGenerator.loadHandler());
                instructionList.append(new NEW(constantPool.addClass(Constants.STREAM_XML_OUTPUT)));
                instructionList.append(methodGenerator.storeHandler());
                instructionList.append(new NEW(constantPool.addClass(Constants.STRING_WRITER)));
                StackInstruction stackInstruction3 = Constants.DUP;
                instructionList.append(stackInstruction3);
                instructionList.append(stackInstruction3);
                instructionList.append(new INVOKESPECIAL(constantPool.addMethodref(Constants.STRING_WRITER, Const.CONSTRUCTOR_NAME, "()V")));
                instructionList.append(methodGenerator.loadHandler());
                instructionList.append(new INVOKESPECIAL(constantPool.addMethodref(Constants.STREAM_XML_OUTPUT, Const.CONSTRUCTOR_NAME, "()V")));
                instructionList.append(methodGenerator.loadHandler());
                StackInstruction stackInstruction4 = Constants.SWAP;
                instructionList.append(stackInstruction4);
                instructionList.append(new INVOKEINTERFACE(constantPool.addInterfaceMethodref(Constants.TRANSLET_OUTPUT_INTERFACE, "setWriter", "(Ljava/io/Writer;)V"), 2));
                instructionList.append(methodGenerator.loadHandler());
                instructionList.append(new PUSH(constantPool, "UTF-8"));
                instructionList.append(new INVOKEINTERFACE(constantPool.addInterfaceMethodref(Constants.TRANSLET_OUTPUT_INTERFACE, "setEncoding", "(Ljava/lang/String;)V"), 2));
                instructionList.append(methodGenerator.loadHandler());
                instructionList.append(Constants.ICONST_1);
                instructionList.append(new INVOKEINTERFACE(constantPool.addInterfaceMethodref(Constants.TRANSLET_OUTPUT_INTERFACE, "setOmitXMLDeclaration", "(Z)V"), 2));
                instructionList.append(methodGenerator.loadHandler());
                instructionList.append(new INVOKEINTERFACE(constantPool.addInterfaceMethodref(Constants.TRANSLET_OUTPUT_INTERFACE, "startDocument", "()V"), 1));
                translateContents(classGenerator, methodGenerator);
                instructionList.append(methodGenerator.loadHandler());
                instructionList.append(new INVOKEINTERFACE(constantPool.addInterfaceMethodref(Constants.TRANSLET_OUTPUT_INTERFACE, "endDocument", "()V"), 1));
                instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(Constants.STRING_WRITER, "toString", "()Ljava/lang/String;")));
                instructionList.append(stackInstruction4);
                instructionList.append(methodGenerator.storeHandler());
            }
        }
        instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(Constants.TRANSLET_CLASS, "displayMessage", "(Ljava/lang/String;)V")));
        if (this._terminate) {
            int iAddMethodref = constantPool.addMethodref("java.lang.RuntimeException", Const.CONSTRUCTOR_NAME, "(Ljava/lang/String;)V");
            instructionList.append(new NEW(constantPool.addClass("java.lang.RuntimeException")));
            instructionList.append(Constants.DUP);
            instructionList.append(new PUSH(constantPool, "Termination forced by an xsl:message instruction"));
            instructionList.append(new INVOKESPECIAL(iAddMethodref));
            instructionList.append(Constants.ATHROW);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        typeCheckContents(symbolTable);
        return Type.Void;
    }
}

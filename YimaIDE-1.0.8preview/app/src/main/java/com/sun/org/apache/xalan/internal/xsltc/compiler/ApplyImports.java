package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import com.sun.org.apache.xpath.internal.compiler.Keywords;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class ApplyImports extends Instruction {
    private QName _modeName;
    private int _precedence;

    private int getMinPrecedence(int i) {
        Stylesheet stylesheet = getStylesheet();
        while (true) {
            Stylesheet stylesheet2 = stylesheet._includedFrom;
            if (stylesheet2 == null) {
                return stylesheet.getMinimumDescendantPrecedence();
            }
            stylesheet = stylesheet2;
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void display(int i) {
        indent(i);
        Util.println("ApplyTemplates");
        int i2 = i + 4;
        indent(i2);
        if (this._modeName != null) {
            indent(i2);
            Util.println("mode " + this._modeName);
        }
    }

    public boolean hasWithParams() {
        return hasContents();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void parseContents(Parser parser) {
        getStylesheet().setTemplateInlining(false);
        Template template = getTemplate();
        this._modeName = template.getModeName();
        this._precedence = template.getImportPrecedence();
        parser.getTopLevelStylesheet();
        parseChildren(parser);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        Stylesheet stylesheet = classGenerator.getStylesheet();
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        methodGenerator.getLocalIndex(Keywords.FUNC_CURRENT_STRING);
        instructionList.append(classGenerator.loadTranslet());
        instructionList.append(methodGenerator.loadDOM());
        instructionList.append(methodGenerator.loadIterator());
        instructionList.append(methodGenerator.loadHandler());
        instructionList.append(methodGenerator.loadCurrentNode());
        if (stylesheet.hasLocalParams()) {
            instructionList.append(classGenerator.loadTranslet());
            instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(Constants.TRANSLET_CLASS, Constants.PUSH_PARAM_FRAME, "()V")));
        }
        int i = this._precedence;
        instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(classGenerator.getStylesheet().getClassName(), stylesheet.getMode(this._modeName).functionName(getMinPrecedence(i), i), classGenerator.getApplyTemplatesSigForImport())));
        if (stylesheet.hasLocalParams()) {
            instructionList.append(classGenerator.loadTranslet());
            instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(Constants.TRANSLET_CLASS, Constants.POP_PARAM_FRAME, "()V")));
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        typeCheckContents(symbolTable);
        return Type.Void;
    }
}

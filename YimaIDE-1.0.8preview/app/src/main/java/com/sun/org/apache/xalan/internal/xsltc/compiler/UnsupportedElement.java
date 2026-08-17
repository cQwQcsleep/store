package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.INVOKESTATIC;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class UnsupportedElement extends SyntaxTreeNode {
    private List<SyntaxTreeNode> _fallbacks;
    private boolean _isExtension;
    private ErrorMsg _message;

    public UnsupportedElement(String str, String str2, String str3, boolean z) {
        super(str, str2, str3);
        this._fallbacks = null;
        this._message = null;
        this._isExtension = z;
    }

    private void processFallbacks(Parser parser) {
        List<SyntaxTreeNode> contents = getContents();
        if (contents != null) {
            int size = contents.size();
            for (int i = 0; i < size; i++) {
                SyntaxTreeNode syntaxTreeNode = contents.get(i);
                if (syntaxTreeNode instanceof Fallback) {
                    Fallback fallback = (Fallback) syntaxTreeNode;
                    fallback.activate();
                    fallback.parseContents(parser);
                    if (this._fallbacks == null) {
                        this._fallbacks = new ArrayList();
                    }
                    this._fallbacks.add(syntaxTreeNode);
                }
            }
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void display(int i) {
        indent(i);
        Util.println("Unsupported element = " + this._qname.getNamespace() + ":" + this._qname.getLocalPart());
        displayContents(i + 4);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void parseContents(Parser parser) {
        processFallbacks(parser);
    }

    public void setErrorMessage(ErrorMsg errorMsg) {
        this._message = errorMsg;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        List<SyntaxTreeNode> list = this._fallbacks;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                ((Fallback) this._fallbacks.get(i)).translate(classGenerator, methodGenerator);
            }
            return;
        }
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        int iAddMethodref = constantPool.addMethodref(Constants.BASIS_LIBRARY_CLASS, "unsupported_ElementF", "(Ljava/lang/String;Z)V");
        instructionList.append(new PUSH(constantPool, getQName().toString()));
        instructionList.append(new PUSH(constantPool, this._isExtension));
        instructionList.append(new INVOKESTATIC(iAddMethodref));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        List<SyntaxTreeNode> list = this._fallbacks;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                ((Fallback) this._fallbacks.get(i)).typeCheck(symbolTable);
            }
        }
        return Type.Void;
    }
}

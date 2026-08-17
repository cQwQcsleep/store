package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import com.sun.org.apache.xml.internal.serializer.ElemDesc;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class LiteralAttribute extends Instruction {
    private final String _name;
    private final AttributeValue _value;

    public LiteralAttribute(String str, String str2, Parser parser, SyntaxTreeNode syntaxTreeNode) {
        this._name = str;
        setParent(syntaxTreeNode);
        this._value = AttributeValue.create(this, str2, parser);
    }

    private boolean hasBadChars(String str) {
        for (char c : str.toCharArray()) {
            if (c < ' ' || '~' < c || c == '<' || c == '>' || c == '&' || c == '\"') {
                return true;
            }
        }
        return false;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public boolean contextDependent() {
        return this._value.contextDependent();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void display(int i) {
        indent(i);
        Util.println("LiteralAttribute name=" + this._name + " value=" + this._value);
    }

    public String getName() {
        return this._name;
    }

    public AttributeValue getValue() {
        return this._value;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x004b  */
    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        int i;
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        instructionList.append(methodGenerator.loadHandler());
        instructionList.append(new PUSH(constantPool, this._name));
        this._value.translate(classGenerator, methodGenerator);
        SyntaxTreeNode parent = getParent();
        if (parent instanceof LiteralElement) {
            LiteralElement literalElement = (LiteralElement) parent;
            if (literalElement.allAttributesUnique()) {
                ElemDesc elemDesc = literalElement.getElemDesc();
                boolean z = false;
                if (elemDesc != null) {
                    i = 4;
                    if (elemDesc.isAttrFlagSet(this._name, 4)) {
                        z = true;
                        i = 2;
                    } else if (!elemDesc.isAttrFlagSet(this._name, 2)) {
                        i = 0;
                    }
                } else {
                    i = 0;
                }
                AttributeValue attributeValue = this._value;
                if ((attributeValue instanceof SimpleAttributeValue) && !hasBadChars(((SimpleAttributeValue) attributeValue).toString()) && !z) {
                    i |= 1;
                }
                instructionList.append(new PUSH(constantPool, i));
                instructionList.append(methodGenerator.uniqueAttribute());
                return;
            }
        }
        instructionList.append(methodGenerator.attribute());
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        this._value.typeCheck(symbolTable);
        typeCheckContents(symbolTable);
        return Type.Void;
    }
}

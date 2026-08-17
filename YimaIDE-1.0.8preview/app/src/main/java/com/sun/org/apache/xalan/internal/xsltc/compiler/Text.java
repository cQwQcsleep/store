package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.GETSTATIC;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import jdk.xml.internal.JdkConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class Text extends Instruction {
    private String _text;
    private boolean _escaping = true;
    private boolean _ignore = false;
    private boolean _textElement = true;

    public Text(String str) {
        this._text = str;
    }

    private static boolean isWhitespace(char c) {
        return c == ' ' || c == '\t' || c == '\n' || c == '\r';
    }

    public boolean canLoadAsArrayOffsetLength() {
        return this._text.length() <= 21845;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public boolean contextDependent() {
        return false;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void display(int i) {
        indent(i);
        Util.println("Text");
        indent(i + 4);
        Util.println(this._text);
    }

    public String getText() {
        return this._text;
    }

    public void ignore() {
        this._ignore = true;
    }

    public boolean isIgnore() {
        return this._ignore;
    }

    public boolean isTextElement() {
        return this._textElement;
    }

    public void loadAsArrayOffsetLength(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        XSLTC xsltc = classGenerator.getParser().getXSLTC();
        int iAddCharacterData = xsltc.addCharacterData(this._text);
        this._text.getClass();
        StringBuilder sb = new StringBuilder(Constants.STATIC_CHAR_DATA_FIELD);
        sb.append(xsltc.getCharacterDataCount() - 1);
        instructionList.append(new GETSTATIC(constantPool.addFieldref(xsltc.getClassName(), sb.toString(), Constants.STATIC_CHAR_DATA_FIELD_SIG)));
        instructionList.append(new PUSH(constantPool, iAddCharacterData));
        instructionList.append(new PUSH(constantPool, this._text.length()));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void parseContents(Parser parser) {
        String attribute = getAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_DISABLE_OUTPUT_ESCAPING);
        int i = 0;
        if (attribute != null && attribute.equals(JdkConstants.JDK_YES)) {
            this._escaping = false;
        }
        parseChildren(parser);
        String str = this._text;
        boolean z = this._textElement;
        if (str == null) {
            if (z) {
                this._text = "";
                return;
            } else {
                this._ignore = true;
                return;
            }
        }
        if (z) {
            if (str.length() == 0) {
                this._ignore = true;
                return;
            }
            return;
        }
        if (!(getParent() instanceof LiteralElement)) {
            int length = this._text.length();
            while (i < length && isWhitespace(this._text.charAt(i))) {
                i++;
            }
            if (i == length) {
                this._ignore = true;
                return;
            }
            return;
        }
        String attribute2 = ((LiteralElement) getParent()).getAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_XMLSPACE);
        if (attribute2 == null || !attribute2.equals(SchemaSymbols.ATTVAL_PRESERVE)) {
            int length2 = this._text.length();
            while (i < length2 && isWhitespace(this._text.charAt(i))) {
                i++;
            }
            if (i == length2) {
                this._ignore = true;
            }
        }
    }

    public void setText(String str) {
        if (this._text == null) {
            this._text = str;
            return;
        }
        this._text += str;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        if (!this._ignore) {
            int iAddInterfaceMethodref = constantPool.addInterfaceMethodref(Constants.OUTPUT_HANDLER, "setEscaping", "(Z)Z");
            if (!this._escaping) {
                instructionList.append(methodGenerator.loadHandler());
                instructionList.append(new PUSH(constantPool, false));
                instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref, 2));
            }
            instructionList.append(methodGenerator.loadHandler());
            if (canLoadAsArrayOffsetLength()) {
                int iAddInterfaceMethodref2 = constantPool.addInterfaceMethodref(Constants.OUTPUT_HANDLER, "characters", "([CII)V");
                loadAsArrayOffsetLength(classGenerator, methodGenerator);
                instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref2, 4));
            } else {
                int iAddInterfaceMethodref3 = constantPool.addInterfaceMethodref(Constants.OUTPUT_HANDLER, "characters", "(Ljava/lang/String;)V");
                instructionList.append(new PUSH(constantPool, this._text));
                instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref3, 2));
            }
            if (!this._escaping) {
                instructionList.append(methodGenerator.loadHandler());
                instructionList.append(Constants.SWAP);
                instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref, 2));
                instructionList.append(Constants.POP);
            }
        }
        translateContents(classGenerator, methodGenerator);
    }

    public Text() {
    }
}

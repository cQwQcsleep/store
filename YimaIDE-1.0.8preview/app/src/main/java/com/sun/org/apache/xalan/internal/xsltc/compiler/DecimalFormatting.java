package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.GETSTATIC;
import com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.bcel.internal.generic.StackInstruction;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xml.internal.utils.XML11Char;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class DecimalFormatting extends TopLevelElement {
    private static final String DFS_CLASS = "java.text.DecimalFormatSymbols";
    private static final String DFS_SIG = "Ljava/text/DecimalFormatSymbols;";
    private QName _name = null;

    public static void translateDefaultDFS(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        int iAddMethodref = constantPool.addMethodref(DFS_CLASS, Const.CONSTRUCTOR_NAME, "(Ljava/util/Locale;)V");
        instructionList.append(classGenerator.loadTranslet());
        instructionList.append(new PUSH(constantPool, ""));
        instructionList.append(new NEW(constantPool.addClass(DFS_CLASS)));
        StackInstruction stackInstruction = Constants.DUP;
        instructionList.append(stackInstruction);
        instructionList.append(new GETSTATIC(constantPool.addFieldref(Constants.LOCALE_CLASS, "US", Constants.LOCALE_SIG)));
        instructionList.append(new INVOKESPECIAL(iAddMethodref));
        int iAddMethodref2 = constantPool.addMethodref(DFS_CLASS, "setNaN", "(Ljava/lang/String;)V");
        instructionList.append(stackInstruction);
        instructionList.append(new PUSH(constantPool, "NaN"));
        instructionList.append(new INVOKEVIRTUAL(iAddMethodref2));
        int iAddMethodref3 = constantPool.addMethodref(DFS_CLASS, "setInfinity", "(Ljava/lang/String;)V");
        instructionList.append(stackInstruction);
        instructionList.append(new PUSH(constantPool, com.sun.org.apache.xalan.internal.templates.Constants.ATTRVAL_INFINITY));
        instructionList.append(new INVOKEVIRTUAL(iAddMethodref3));
        instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(Constants.TRANSLET_CLASS, "addDecimalFormat", "(Ljava/lang/String;Ljava/text/DecimalFormatSymbols;)V")));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void parseContents(Parser parser) {
        String attribute = getAttribute("name");
        if (attribute.length() > 0 && !XML11Char.isXML11ValidQName(attribute)) {
            parser.reportError(3, new ErrorMsg("INVALID_QNAME_ERR", (Object) attribute, (SyntaxTreeNode) this));
        }
        QName qNameIgnoreDefaultNs = parser.getQNameIgnoreDefaultNs(attribute);
        this._name = qNameIgnoreDefaultNs;
        if (qNameIgnoreDefaultNs == null) {
            this._name = parser.getQNameIgnoreDefaultNs("");
        }
        SymbolTable symbolTable = parser.getSymbolTable();
        DecimalFormatting decimalFormatting = symbolTable.getDecimalFormatting(this._name);
        QName qName = this._name;
        if (decimalFormatting != null) {
            reportWarning(this, parser, ErrorMsg.SYMBOLS_REDEF_ERR, qName.toString());
        } else {
            symbolTable.addDecimalFormatting(qName, this);
        }
    }

    /* JADX WARN: Code duplicated, block: B:48:0x018a  */
    /* JADX WARN: Code duplicated, block: B:54:0x01a3 A[SYNTHETIC] */
    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.TopLevelElement, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        int iAddMethodref;
        boolean z;
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        int iAddMethodref2 = constantPool.addMethodref(DFS_CLASS, Const.CONSTRUCTOR_NAME, "(Ljava/util/Locale;)V");
        instructionList.append(classGenerator.loadTranslet());
        instructionList.append(new PUSH(constantPool, this._name.toString()));
        instructionList.append(new NEW(constantPool.addClass(DFS_CLASS)));
        StackInstruction stackInstruction = Constants.DUP;
        instructionList.append(stackInstruction);
        instructionList.append(new GETSTATIC(constantPool.addFieldref(Constants.LOCALE_CLASS, "US", Constants.LOCALE_SIG)));
        instructionList.append(new INVOKESPECIAL(iAddMethodref2));
        String attribute = getAttribute("NaN");
        if (attribute == null || attribute.equals("")) {
            int iAddMethodref3 = constantPool.addMethodref(DFS_CLASS, "setNaN", "(Ljava/lang/String;)V");
            instructionList.append(stackInstruction);
            instructionList.append(new PUSH(constantPool, "NaN"));
            instructionList.append(new INVOKEVIRTUAL(iAddMethodref3));
        }
        String attribute2 = getAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_INFINITY);
        if (attribute2 == null || attribute2.equals("")) {
            int iAddMethodref4 = constantPool.addMethodref(DFS_CLASS, "setInfinity", "(Ljava/lang/String;)V");
            instructionList.append(stackInstruction);
            instructionList.append(new PUSH(constantPool, com.sun.org.apache.xalan.internal.templates.Constants.ATTRVAL_INFINITY));
            instructionList.append(new INVOKEVIRTUAL(iAddMethodref4));
        }
        int length = this._attributes.getLength();
        for (int i = 0; i < length; i++) {
            String qName = this._attributes.getQName(i);
            String value = this._attributes.getValue(i);
            if (qName.equals(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_DECIMALSEPARATOR)) {
                iAddMethodref = constantPool.addMethodref(DFS_CLASS, "setDecimalSeparator", "(C)V");
            } else if (qName.equals(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_GROUPINGSEPARATOR)) {
                iAddMethodref = constantPool.addMethodref(DFS_CLASS, "setGroupingSeparator", "(C)V");
            } else if (qName.equals(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_MINUSSIGN)) {
                iAddMethodref = constantPool.addMethodref(DFS_CLASS, "setMinusSign", "(C)V");
            } else if (qName.equals(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_PERCENT)) {
                iAddMethodref = constantPool.addMethodref(DFS_CLASS, "setPercent", "(C)V");
            } else if (qName.equals(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_PERMILLE)) {
                iAddMethodref = constantPool.addMethodref(DFS_CLASS, "setPerMill", "(C)V");
            } else if (qName.equals(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_ZERODIGIT)) {
                iAddMethodref = constantPool.addMethodref(DFS_CLASS, "setZeroDigit", "(C)V");
            } else if (qName.equals(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_DIGIT)) {
                iAddMethodref = constantPool.addMethodref(DFS_CLASS, "setDigit", "(C)V");
            } else {
                if (qName.equals(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_PATTERNSEPARATOR)) {
                    iAddMethodref = constantPool.addMethodref(DFS_CLASS, "setPatternSeparator", "(C)V");
                } else {
                    if (qName.equals("NaN")) {
                        iAddMethodref = constantPool.addMethodref(DFS_CLASS, "setNaN", "(Ljava/lang/String;)V");
                        instructionList.append(Constants.DUP);
                        instructionList.append(new PUSH(constantPool, value));
                        instructionList.append(new INVOKEVIRTUAL(iAddMethodref));
                    } else if (qName.equals(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_INFINITY)) {
                        iAddMethodref = constantPool.addMethodref(DFS_CLASS, "setInfinity", "(Ljava/lang/String;)V");
                        instructionList.append(Constants.DUP);
                        instructionList.append(new PUSH(constantPool, value));
                        instructionList.append(new INVOKEVIRTUAL(iAddMethodref));
                    } else {
                        iAddMethodref = 0;
                        z = false;
                    }
                    z = false;
                }
                if (z) {
                    instructionList.append(Constants.DUP);
                    instructionList.append(new PUSH(constantPool, (int) value.charAt(0)));
                    instructionList.append(new INVOKEVIRTUAL(iAddMethodref));
                }
            }
            z = true;
            if (z) {
                instructionList.append(Constants.DUP);
                instructionList.append(new PUSH(constantPool, (int) value.charAt(0)));
                instructionList.append(new INVOKEVIRTUAL(iAddMethodref));
            }
        }
        instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(Constants.TRANSLET_CLASS, "addDecimalFormat", "(Ljava/lang/String;Ljava/text/DecimalFormatSymbols;)V")));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.TopLevelElement, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        return Type.Void;
    }
}

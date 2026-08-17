package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class AttributeValueTemplate extends AttributeValue {
    static final String DELIMITER = "\ufffe";
    static final int IN_EXPR = 1;
    static final int IN_EXPR_DQUOTES = 3;
    static final int IN_EXPR_SQUOTES = 2;
    static final int OUT_EXPR = 0;

    public AttributeValueTemplate(String str, Parser parser, SyntaxTreeNode syntaxTreeNode) {
        setParent(syntaxTreeNode);
        setParser(parser);
        try {
            parseAVTemplate(str, parser);
        } catch (NoSuchElementException unused) {
            reportError(syntaxTreeNode, parser, ErrorMsg.ATTR_VAL_TEMPLATE_ERR, str);
        }
    }

    private void parseAVTemplate(String str, Parser parser) {
        String str2;
        String strNextToken;
        StringTokenizer stringTokenizer = new StringTokenizer(str, "{}\"'", true);
        StringBuilder sb = new StringBuilder();
        char c = 0;
        loop0: while (true) {
            String strNextToken2 = null;
            while (true) {
                if (!stringTokenizer.hasMoreTokens()) {
                    break loop0;
                }
                if (strNextToken2 != null) {
                    str2 = null;
                } else {
                    str2 = strNextToken2;
                    strNextToken2 = stringTokenizer.nextToken();
                }
                if (strNextToken2.length() == 1) {
                    char cCharAt = strNextToken2.charAt(0);
                    if (cCharAt == '\"') {
                        if (c == 1) {
                            c = 3;
                        } else if (c == 3) {
                            c = 1;
                        }
                        sb.append(strNextToken2);
                    } else if (cCharAt == '\'') {
                        if (c == 1) {
                            c = 2;
                        } else if (c == 2) {
                            c = 1;
                        }
                        sb.append(strNextToken2);
                    } else if (cCharAt != '{') {
                        if (cCharAt != '}') {
                            sb.append(strNextToken2);
                        } else if (c == 0) {
                            strNextToken = stringTokenizer.nextToken();
                            if (strNextToken.equals("}")) {
                                sb.append(strNextToken);
                                break;
                            } else {
                                reportError(getParent(), parser, ErrorMsg.ATTR_VAL_TEMPLATE_ERR, str);
                                strNextToken2 = strNextToken;
                            }
                        } else if (c == 1) {
                            sb.append(DELIMITER);
                            c = 0;
                        } else if (c == 2 || c == 3) {
                            sb.append(strNextToken2);
                        }
                    } else if (c == 0) {
                        strNextToken = stringTokenizer.nextToken();
                        if (strNextToken.equals("{")) {
                            sb.append(strNextToken);
                            break;
                        } else {
                            sb.append(DELIMITER);
                            c = 1;
                            strNextToken2 = strNextToken;
                        }
                    } else if (c == 1 || c == 2 || c == 3) {
                        reportError(getParent(), parser, ErrorMsg.ATTR_VAL_TEMPLATE_ERR, str);
                    }
                } else {
                    sb.append(strNextToken2);
                }
                strNextToken2 = str2;
            }
        }
        if (c != 0) {
            reportError(getParent(), parser, ErrorMsg.ATTR_VAL_TEMPLATE_ERR, str);
        }
        StringTokenizer stringTokenizer2 = new StringTokenizer(sb.toString(), DELIMITER, true);
        while (stringTokenizer2.hasMoreTokens()) {
            String strNextToken3 = stringTokenizer2.nextToken();
            if (strNextToken3.equals(DELIMITER)) {
                addElement(parser.parseExpression(this, stringTokenizer2.nextToken()));
                stringTokenizer2.nextToken();
            } else {
                addElement(new LiteralExpr(strNextToken3));
            }
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public String toString() {
        StringBuilder sb = new StringBuilder("AVT:[");
        int iElementCount = elementCount();
        for (int i = 0; i < iElementCount; i++) {
            sb.append(elementAt(i).toString());
            if (i < iElementCount - 1) {
                sb.append(' ');
            }
        }
        sb.append(']');
        return sb.toString();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        if (elementCount() == 1) {
            ((Expression) elementAt(0)).translate(classGenerator, methodGenerator);
            return;
        }
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        int iAddMethodref = constantPool.addMethodref(Constants.STRING_BUFFER_CLASS, Const.CONSTRUCTOR_NAME, "()V");
        INVOKEVIRTUAL invokevirtual = new INVOKEVIRTUAL(constantPool.addMethodref(Constants.STRING_BUFFER_CLASS, "append", "(Ljava/lang/String;)Ljava/lang/StringBuffer;"));
        int iAddMethodref2 = constantPool.addMethodref(Constants.STRING_BUFFER_CLASS, "toString", "()Ljava/lang/String;");
        instructionList.append(new NEW(constantPool.addClass(Constants.STRING_BUFFER_CLASS)));
        instructionList.append(Constants.DUP);
        instructionList.append(new INVOKESPECIAL(iAddMethodref));
        Iterator<SyntaxTreeNode> itElements = elements();
        while (itElements.hasNext()) {
            ((Expression) itElements.next()).translate(classGenerator, methodGenerator);
            instructionList.append(invokevirtual);
        }
        instructionList.append(new INVOKEVIRTUAL(iAddMethodref2));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        List<SyntaxTreeNode> contents = getContents();
        int size = contents.size();
        for (int i = 0; i < size; i++) {
            Expression expression = (Expression) contents.get(i);
            Type typeTypeCheck = expression.typeCheck(symbolTable);
            Type type = Type.String;
            if (!typeTypeCheck.identicalTo(type)) {
                contents.set(i, new CastExpr(expression, type));
            }
        }
        Type type2 = Type.String;
        this._type = type2;
        return type2;
    }
}

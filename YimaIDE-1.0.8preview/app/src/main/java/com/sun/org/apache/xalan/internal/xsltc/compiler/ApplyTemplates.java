package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeSetType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ReferenceType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ResultTreeType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import com.sun.org.apache.xml.internal.utils.XML11Char;
import com.sun.org.apache.xpath.internal.compiler.Keywords;
import java.util.ArrayList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class ApplyTemplates extends Instruction {
    private String _functionName;
    private QName _modeName;
    private Expression _select;
    private Type _type = null;

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void display(int i) {
        indent(i);
        Util.println("ApplyTemplates");
        int i2 = i + 4;
        indent(i2);
        Util.println("select " + this._select.toString());
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
        String attribute = getAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_SELECT);
        String attribute2 = getAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_MODE);
        if (attribute.length() > 0) {
            this._select = parser.parseExpression(this, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_SELECT, null);
        }
        if (attribute2.length() > 0) {
            if (!XML11Char.isXML11ValidQName(attribute2)) {
                parser.reportError(3, new ErrorMsg("INVALID_QNAME_ERR", (Object) attribute2, (SyntaxTreeNode) this));
            }
            this._modeName = parser.getQNameIgnoreDefaultNs(attribute2);
        }
        this._functionName = parser.getTopLevelStylesheet().getMode(this._modeName).functionName();
        parseChildren(parser);
    }

    /* JADX WARN: Code duplicated, block: B:35:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:52:0x0109 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:54:0x00f9 A[SYNTHETIC] */
    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        boolean z;
        Expression expression;
        Stylesheet stylesheet = classGenerator.getStylesheet();
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        int localIndex = methodGenerator.getLocalIndex(Keywords.FUNC_CURRENT_STRING);
        ArrayList arrayList = new ArrayList();
        for (SyntaxTreeNode syntaxTreeNode : getContents()) {
            if (syntaxTreeNode instanceof Sort) {
                arrayList.add((Sort) syntaxTreeNode);
            }
        }
        if (stylesheet.hasLocalParams() || hasContents()) {
            instructionList.append(classGenerator.loadTranslet());
            instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(Constants.TRANSLET_CLASS, Constants.PUSH_PARAM_FRAME, "()V")));
            translateContents(classGenerator, methodGenerator);
        }
        instructionList.append(classGenerator.loadTranslet());
        Type type = this._type;
        if (type == null || !(type instanceof ResultTreeType)) {
            instructionList.append(methodGenerator.loadDOM());
            int size = arrayList.size();
            Expression expression2 = this._select;
            if (size > 0) {
                Sort.translateSortIterator(classGenerator, methodGenerator, expression2, arrayList);
                int iAddInterfaceMethodref = constantPool.addInterfaceMethodref(Constants.NODE_ITERATOR, Constants.SET_START_NODE, "(I)Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;");
                instructionList.append(methodGenerator.loadCurrentNode());
                instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref, 2));
                z = true;
            } else if (expression2 == null) {
                Mode.compileGetChildren(classGenerator, methodGenerator, localIndex);
            } else {
                expression2.translate(classGenerator, methodGenerator);
            }
            expression = this._select;
            if (expression != null && !z) {
                expression.startIterator(classGenerator, methodGenerator);
            }
            String className = classGenerator.getStylesheet().getClassName();
            instructionList.append(methodGenerator.loadHandler());
            instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(className, this._functionName, classGenerator.getApplyTemplatesSig())));
            for (SyntaxTreeNode syntaxTreeNode2 : getContents()) {
                if (syntaxTreeNode2 instanceof WithParam) {
                    ((WithParam) syntaxTreeNode2).releaseResultTree(classGenerator, methodGenerator);
                }
            }
            if (!stylesheet.hasLocalParams() || hasContents()) {
                instructionList.append(classGenerator.loadTranslet());
                instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(Constants.TRANSLET_CLASS, Constants.POP_PARAM_FRAME, "()V")));
            }
            return;
        }
        if (arrayList.size() > 0) {
            getParser().reportError(4, new ErrorMsg(ErrorMsg.RESULT_TREE_SORT_ERR, (SyntaxTreeNode) this));
        }
        this._select.translate(classGenerator, methodGenerator);
        this._type.translateTo(classGenerator, methodGenerator, Type.NodeSet);
        z = false;
        expression = this._select;
        if (expression != null) {
            expression.startIterator(classGenerator, methodGenerator);
        }
        String className2 = classGenerator.getStylesheet().getClassName();
        instructionList.append(methodGenerator.loadHandler());
        instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(className2, this._functionName, classGenerator.getApplyTemplatesSig())));
        while (r3.hasNext()) {
            if (syntaxTreeNode2 instanceof WithParam) {
                ((WithParam) syntaxTreeNode2).releaseResultTree(classGenerator, methodGenerator);
            }
        }
        if (stylesheet.hasLocalParams()) {
        }
        instructionList.append(classGenerator.loadTranslet());
        instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(Constants.TRANSLET_CLASS, Constants.POP_PARAM_FRAME, "()V")));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        Expression expression = this._select;
        if (expression == null) {
            typeCheckContents(symbolTable);
            return Type.Void;
        }
        Type typeTypeCheck = expression.typeCheck(symbolTable);
        this._type = typeTypeCheck;
        if ((typeTypeCheck instanceof NodeType) || (typeTypeCheck instanceof ReferenceType)) {
            Expression expression2 = this._select;
            Type type = Type.NodeSet;
            this._select = new CastExpr(expression2, type);
            this._type = type;
        }
        Type type2 = this._type;
        if (!(type2 instanceof NodeSetType) && !(type2 instanceof ResultTreeType)) {
            throw new TypeCheckError(this);
        }
        typeCheckContents(symbolTable);
        return Type.Void;
    }
}

package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.BranchHandle;
import com.sun.org.apache.bcel.internal.generic.BranchInstruction;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import com.sun.org.apache.bcel.internal.generic.IFGT;
import com.sun.org.apache.bcel.internal.generic.InstructionHandle;
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
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class ForEach extends Instruction {
    private Expression _select;
    private Type _type;

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void display(int i) {
        indent(i);
        Util.println("ForEach");
        int i2 = i + 4;
        indent(i2);
        Util.println("select " + this._select.toString());
        displayContents(i2);
    }

    public void initializeVariables(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        int iElementCount = elementCount();
        for (int i = 0; i < iElementCount; i++) {
            SyntaxTreeNode syntaxTreeNode = getContents().get(i);
            if (syntaxTreeNode instanceof Variable) {
                ((Variable) syntaxTreeNode).initialize(classGenerator, methodGenerator);
            }
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void parseContents(Parser parser) {
        this._select = parser.parseExpression(this, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_SELECT, null);
        parseChildren(parser);
        if (this._select.isDummy()) {
            reportError(this, parser, ErrorMsg.REQUIRED_ATTR_ERR, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_SELECT);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        instructionList.append(methodGenerator.loadCurrentNode());
        instructionList.append(methodGenerator.loadIterator());
        ArrayList arrayList = new ArrayList();
        Iterator<SyntaxTreeNode> itElements = elements();
        while (itElements.hasNext()) {
            SyntaxTreeNode next = itElements.next();
            if (next instanceof Sort) {
                arrayList.add((Sort) next);
            }
        }
        Type type = this._type;
        if (type == null || !(type instanceof ResultTreeType)) {
            int size = arrayList.size();
            Expression expression = this._select;
            if (size > 0) {
                Sort.translateSortIterator(classGenerator, methodGenerator, expression, arrayList);
            } else {
                expression.translate(classGenerator, methodGenerator);
            }
            if (!(this._type instanceof ReferenceType)) {
                instructionList.append(methodGenerator.loadContextNode());
                instructionList.append(methodGenerator.setStartNode());
            }
        } else {
            instructionList.append(methodGenerator.loadDOM());
            if (arrayList.size() > 0) {
                getParser().reportError(4, new ErrorMsg(ErrorMsg.RESULT_TREE_SORT_ERR, (SyntaxTreeNode) this));
            }
            this._select.translate(classGenerator, methodGenerator);
            this._type.translateTo(classGenerator, methodGenerator, Type.NodeSet);
            instructionList.append(Constants.SWAP);
            instructionList.append(methodGenerator.storeDOM());
        }
        instructionList.append(methodGenerator.storeIterator());
        initializeVariables(classGenerator, methodGenerator);
        BranchHandle branchHandleAppend = instructionList.append((BranchInstruction) new GOTO(null));
        InstructionHandle instructionHandleAppend = instructionList.append(Constants.NOP);
        translateContents(classGenerator, methodGenerator);
        branchHandleAppend.setTarget(instructionList.append(methodGenerator.loadIterator()));
        instructionList.append(methodGenerator.nextNode());
        instructionList.append(Constants.DUP);
        instructionList.append(methodGenerator.storeCurrentNode());
        instructionList.append((BranchInstruction) new IFGT(instructionHandleAppend));
        Type type2 = this._type;
        if (type2 != null && (type2 instanceof ResultTreeType)) {
            instructionList.append(methodGenerator.storeDOM());
        }
        instructionList.append(methodGenerator.storeIterator());
        instructionList.append(methodGenerator.storeCurrentNode());
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        Type typeTypeCheck = this._select.typeCheck(symbolTable);
        this._type = typeTypeCheck;
        if ((typeTypeCheck instanceof ReferenceType) || (typeTypeCheck instanceof NodeType)) {
            this._select = new CastExpr(this._select, Type.NodeSet);
            typeCheckContents(symbolTable);
            return Type.Void;
        }
        if (!(typeTypeCheck instanceof NodeSetType) && !(typeTypeCheck instanceof ResultTreeType)) {
            throw new TypeCheckError(this);
        }
        typeCheckContents(symbolTable);
        return Type.Void;
    }
}

package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.classfile.Field;
import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.DCONST;
import com.sun.org.apache.bcel.internal.generic.ICONST;
import com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import com.sun.org.apache.bcel.internal.generic.PUTFIELD;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.BooleanType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.IntType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.RealType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xpath.internal.XPath;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class Variable extends VariableBase {
    public int getIndex() {
        LocalVariableGen localVariableGen = this._local;
        if (localVariableGen != null) {
            return localVariableGen.getIndex();
        }
        return -1;
    }

    public void initialize(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        if (!isLocal() || this._refs.isEmpty()) {
            return;
        }
        if (this._local == null) {
            this._local = methodGenerator.addLocalVariable2(getEscapedName(), this._type.toJCType(), null);
        }
        Type type = this._type;
        if ((type instanceof IntType) || (type instanceof NodeType) || (type instanceof BooleanType)) {
            instructionList.append(new ICONST(0));
        } else if (type instanceof RealType) {
            instructionList.append(new DCONST(XPath.MATCH_SCORE_QNAME));
        } else {
            instructionList.append(new ACONST_NULL());
        }
        LocalVariableGen localVariableGen = this._local;
        localVariableGen.setStart(instructionList.append(this._type.STORE(localVariableGen.getIndex())));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.VariableBase, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void parseContents(Parser parser) {
        super.parseContents(parser);
        SyntaxTreeNode parent = getParent();
        if (!(parent instanceof Stylesheet)) {
            this._isLocal = true;
            return;
        }
        this._isLocal = false;
        Variable variableLookupVariable = parser.getSymbolTable().lookupVariable(this._name);
        if (variableLookupVariable != null) {
            int importPrecedence = getImportPrecedence();
            int importPrecedence2 = variableLookupVariable.getImportPrecedence();
            if (importPrecedence == importPrecedence2) {
                reportError(this, parser, ErrorMsg.VARIABLE_REDEF_ERR, this._name.toString());
            } else if (importPrecedence2 > importPrecedence) {
                this._ignore = true;
                copyReferences(variableLookupVariable);
                return;
            } else {
                variableLookupVariable.copyReferences(this);
                variableLookupVariable.disable();
            }
        }
        ((Stylesheet) parent).addVariable(this);
        parser.getSymbolTable().addVariable(this);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.TopLevelElement, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        if (this._refs.isEmpty()) {
            this._ignore = true;
        }
        if (this._ignore) {
            return;
        }
        this._ignore = true;
        String escapedName = getEscapedName();
        if (!isLocal()) {
            String signature = this._type.toSignature();
            if (classGenerator.containsField(escapedName) == null) {
                classGenerator.addField(new Field(1, constantPool.addUtf8(escapedName), constantPool.addUtf8(signature), null, constantPool.getConstantPool()));
                instructionList.append(classGenerator.loadTranslet());
                translateValue(classGenerator, methodGenerator);
                instructionList.append(new PUTFIELD(constantPool.addFieldref(classGenerator.getClassName(), escapedName, signature)));
                return;
            }
            return;
        }
        translateValue(classGenerator, methodGenerator);
        boolean z = this._local == null;
        if (z) {
            mapRegister(methodGenerator);
        }
        InstructionHandle instructionHandleAppend = instructionList.append(this._type.STORE(this._local.getIndex()));
        if (z) {
            this._local.setStart(instructionHandleAppend);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.TopLevelElement, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        Expression expression = this._select;
        if (expression != null) {
            this._type = expression.typeCheck(symbolTable);
        } else if (hasContents()) {
            typeCheckContents(symbolTable);
            this._type = Type.ResultTree;
        } else {
            this._type = Type.Reference;
        }
        return Type.Void;
    }
}

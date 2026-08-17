package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.BranchInstruction;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.INVOKESTATIC;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.BooleanType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.IntType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeSetType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.RealType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ReferenceType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ResultTreeType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.runtime.Operators;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class RelationalExpr extends Expression {
    private Expression _left;
    private int _op;
    private Expression _right;

    public RelationalExpr(int i, Expression expression, Expression expression2) {
        this._op = i;
        this._left = expression;
        expression.setParent(this);
        this._right = expression2;
        expression2.setParent(this);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public boolean hasLastCall() {
        return this._left.hasLastCall() || this._right.hasLastCall();
    }

    public boolean hasNodeArgs() {
        return (this._left.getType() instanceof NodeType) || (this._right.getType() instanceof NodeType);
    }

    public boolean hasNodeSetArgs() {
        return (this._left.getType() instanceof NodeSetType) || (this._right.getType() instanceof NodeSetType);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public boolean hasPositionCall() {
        return this._left.hasPositionCall() || this._right.hasPositionCall();
    }

    public boolean hasReferenceArgs() {
        return (this._left.getType() instanceof ReferenceType) || (this._right.getType() instanceof ReferenceType);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void setParser(Parser parser) {
        super.setParser(parser);
        this._left.setParser(parser);
        this._right.setParser(parser);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public String toString() {
        return Operators.getOpNames(this._op) + '(' + this._left + ", " + this._right + ')';
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        if (!hasNodeSetArgs() && !hasReferenceArgs()) {
            translateDesynthesized(classGenerator, methodGenerator);
            synthesize(classGenerator, methodGenerator);
            return;
        }
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        this._left.translate(classGenerator, methodGenerator);
        this._left.startIterator(classGenerator, methodGenerator);
        this._right.translate(classGenerator, methodGenerator);
        this._right.startIterator(classGenerator, methodGenerator);
        instructionList.append(new PUSH(constantPool, this._op));
        instructionList.append(methodGenerator.loadDOM());
        instructionList.append(new INVOKESTATIC(constantPool.addMethodref(Constants.BASIS_LIBRARY_CLASS, "compare", "(" + this._left.getType().toSignature() + this._right.getType().toSignature() + "ILcom/sun/org/apache/xalan/internal/xsltc/DOM;)Z")));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public void translateDesynthesized(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        BranchInstruction branchInstructionLE;
        if (hasNodeSetArgs() || hasReferenceArgs()) {
            translate(classGenerator, methodGenerator);
            desynthesize(classGenerator, methodGenerator);
            return;
        }
        InstructionList instructionList = methodGenerator.getInstructionList();
        this._left.translate(classGenerator, methodGenerator);
        this._right.translate(classGenerator, methodGenerator);
        Type type = this._left.getType();
        boolean z = false;
        if (type instanceof RealType) {
            int i = this._op;
            instructionList.append(type.CMP(i == 3 || i == 5));
            type = Type.Int;
            z = true;
        }
        int i2 = this._op;
        if (i2 == 2) {
            branchInstructionLE = type.LE(z);
        } else if (i2 == 3) {
            branchInstructionLE = type.GE(z);
        } else if (i2 == 4) {
            branchInstructionLE = type.LT(z);
        } else if (i2 != 5) {
            getParser().reportError(2, new ErrorMsg(ErrorMsg.ILLEGAL_RELAT_OP_ERR, (SyntaxTreeNode) this));
            branchInstructionLE = null;
        } else {
            branchInstructionLE = type.GT(z);
        }
        this._falseList.add(instructionList.append(branchInstructionLE));
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0049  */
    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        Type type;
        Type typeTypeCheck = this._left.typeCheck(symbolTable);
        Type typeTypeCheck2 = this._right.typeCheck(symbolTable);
        if ((typeTypeCheck instanceof ResultTreeType) && (typeTypeCheck2 instanceof ResultTreeType)) {
            Expression expression = this._right;
            Type type2 = Type.Real;
            this._right = new CastExpr(expression, type2);
            this._left = new CastExpr(this._left, type2);
            Type type3 = Type.Boolean;
            this._type = type3;
            return type3;
        }
        if (hasReferenceArgs()) {
            boolean z = typeTypeCheck instanceof ReferenceType;
            Type type4 = null;
            if (z) {
                Expression expression2 = this._left;
                if (expression2 instanceof VariableRefBase) {
                    type = ((VariableRefBase) expression2).getVariable().getType();
                } else {
                    type = null;
                }
            } else {
                type = null;
            }
            if (typeTypeCheck2 instanceof ReferenceType) {
                Expression expression3 = this._right;
                if (expression3 instanceof VariableRefBase) {
                    type4 = ((VariableRefBase) expression3).getVariable().getType();
                }
            }
            if (type == null) {
                type = type4;
            } else if (type4 != null) {
                type = Type.Real;
            }
            if (type == null) {
                type = Type.Real;
            }
            this._right = new CastExpr(this._right, type);
            this._left = new CastExpr(this._left, type);
            Type type5 = Type.Boolean;
            this._type = type5;
            return type5;
        }
        if (!hasNodeSetArgs()) {
            if (hasNodeArgs()) {
                if (typeTypeCheck instanceof BooleanType) {
                    Expression expression4 = this._right;
                    Type type6 = Type.Boolean;
                    this._right = new CastExpr(expression4, type6);
                    typeTypeCheck2 = type6;
                }
                if (typeTypeCheck2 instanceof BooleanType) {
                    Expression expression5 = this._left;
                    Type type7 = Type.Boolean;
                    this._left = new CastExpr(expression5, type7);
                    typeTypeCheck = type7;
                }
            }
            MethodType methodTypeLookupPrimop = lookupPrimop(symbolTable, Operators.getOpNames(this._op), new MethodType(Type.Void, typeTypeCheck, typeTypeCheck2));
            if (methodTypeLookupPrimop == null) {
                throw new TypeCheckError(this);
            }
            Type type8 = methodTypeLookupPrimop.argsType().get(0);
            if (!type8.identicalTo(typeTypeCheck)) {
                this._left = new CastExpr(this._left, type8);
            }
            if (!methodTypeLookupPrimop.argsType().get(1).identicalTo(typeTypeCheck2)) {
                this._right = new CastExpr(this._right, type8);
            }
            Type typeResultType = methodTypeLookupPrimop.resultType();
            this._type = typeResultType;
            return typeResultType;
        }
        if (typeTypeCheck2 instanceof NodeSetType) {
            Expression expression6 = this._right;
            Expression expression7 = this._left;
            this._right = expression7;
            this._left = expression6;
            int i = this._op;
            int i2 = 3;
            if (i != 2) {
                if (i == 3) {
                    i2 = 2;
                } else {
                    i2 = 4;
                    if (i == 4) {
                        i2 = 5;
                    }
                }
            }
            this._op = i2;
            typeTypeCheck2 = expression7.getType();
        }
        if (typeTypeCheck2 instanceof NodeType) {
            this._right = new CastExpr(this._right, Type.NodeSet);
        }
        if (typeTypeCheck2 instanceof IntType) {
            this._right = new CastExpr(this._right, Type.Real);
        }
        if (typeTypeCheck2 instanceof ResultTreeType) {
            this._right = new CastExpr(this._right, Type.String);
        }
        Type type9 = Type.Boolean;
        this._type = type9;
        return type9;
    }
}

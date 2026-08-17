package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.ArithmeticInstruction;
import com.sun.org.apache.bcel.internal.generic.BranchHandle;
import com.sun.org.apache.bcel.internal.generic.BranchInstruction;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import com.sun.org.apache.bcel.internal.generic.IFEQ;
import com.sun.org.apache.bcel.internal.generic.IFNE;
import com.sun.org.apache.bcel.internal.generic.IF_ICMPEQ;
import com.sun.org.apache.bcel.internal.generic.IF_ICMPNE;
import com.sun.org.apache.bcel.internal.generic.INVOKESTATIC;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.BooleanType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.IntType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeSetType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NumberType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.RealType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ReferenceType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ResultTreeType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.runtime.Operators;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class EqualityExpr extends Expression {
    private Expression _left;
    private final int _op;
    private Expression _right;

    public EqualityExpr(int i, Expression expression, Expression expression2) {
        this._op = i;
        this._left = expression;
        expression.setParent(this);
        this._right = expression2;
        expression2.setParent(this);
    }

    private void swapArguments() {
        Expression expression = this._left;
        this._left = this._right;
        this._right = expression;
    }

    public Expression getLeft() {
        return this._left;
    }

    public boolean getOp() {
        return this._op != 1;
    }

    public Expression getRight() {
        return this._right;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public boolean hasLastCall() {
        return this._left.hasLastCall() || this._right.hasLastCall();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public boolean hasPositionCall() {
        return this._left.hasPositionCall() || this._right.hasPositionCall();
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
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        Type type = this._left.getType();
        Type type2 = this._right.getType();
        if ((type instanceof BooleanType) || (type instanceof NumberType)) {
            translateDesynthesized(classGenerator, methodGenerator);
            synthesize(classGenerator, methodGenerator);
            return;
        }
        if (type instanceof StringType) {
            int iAddMethodref = constantPool.addMethodref("java.lang.String", "equals", "(Ljava/lang/Object;)Z");
            this._left.translate(classGenerator, methodGenerator);
            this._right.translate(classGenerator, methodGenerator);
            instructionList.append(new INVOKEVIRTUAL(iAddMethodref));
            if (this._op == 1) {
                instructionList.append(Constants.ICONST_1);
                instructionList.append(Constants.IXOR);
                return;
            }
            return;
        }
        if (type instanceof ResultTreeType) {
            if (type2 instanceof BooleanType) {
                this._right.translate(classGenerator, methodGenerator);
                if (this._op == 1) {
                    instructionList.append(Constants.ICONST_1);
                    instructionList.append(Constants.IXOR);
                    return;
                }
                return;
            }
            boolean z = type2 instanceof RealType;
            Expression expression = this._left;
            if (z) {
                expression.translate(classGenerator, methodGenerator);
                type.translateTo(classGenerator, methodGenerator, Type.Real);
                this._right.translate(classGenerator, methodGenerator);
                instructionList.append(Constants.DCMPG);
                BranchHandle branchHandleAppend = instructionList.append(this._op == 0 ? new IFNE(null) : new IFEQ(null));
                instructionList.append(Constants.ICONST_1);
                BranchHandle branchHandleAppend2 = instructionList.append((BranchInstruction) new GOTO(null));
                branchHandleAppend.setTarget(instructionList.append(Constants.ICONST_0));
                branchHandleAppend2.setTarget(instructionList.append(Constants.NOP));
                return;
            }
            expression.translate(classGenerator, methodGenerator);
            Type type3 = Type.String;
            type.translateTo(classGenerator, methodGenerator, type3);
            this._right.translate(classGenerator, methodGenerator);
            if (type2 instanceof ResultTreeType) {
                type2.translateTo(classGenerator, methodGenerator, type3);
            }
            instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref("java.lang.String", "equals", "(Ljava/lang/Object;)Z")));
            if (this._op == 1) {
                instructionList.append(Constants.ICONST_1);
                instructionList.append(Constants.IXOR);
                return;
            }
            return;
        }
        boolean z2 = type instanceof NodeSetType;
        if (z2 && (type2 instanceof BooleanType)) {
            this._left.translate(classGenerator, methodGenerator);
            this._left.startIterator(classGenerator, methodGenerator);
            Type.NodeSet.translateTo(classGenerator, methodGenerator, Type.Boolean);
            this._right.translate(classGenerator, methodGenerator);
            ArithmeticInstruction arithmeticInstruction = Constants.IXOR;
            instructionList.append(arithmeticInstruction);
            if (this._op == 0) {
                instructionList.append(Constants.ICONST_1);
                instructionList.append(arithmeticInstruction);
                return;
            }
            return;
        }
        if (z2 && (type2 instanceof StringType)) {
            this._left.translate(classGenerator, methodGenerator);
            this._left.startIterator(classGenerator, methodGenerator);
            this._right.translate(classGenerator, methodGenerator);
            instructionList.append(new PUSH(constantPool, this._op));
            instructionList.append(methodGenerator.loadDOM());
            instructionList.append(new INVOKESTATIC(constantPool.addMethodref(Constants.BASIS_LIBRARY_CLASS, "compare", "(" + type.toSignature() + type2.toSignature() + "ILcom/sun/org/apache/xalan/internal/xsltc/DOM;)Z")));
            return;
        }
        this._left.translate(classGenerator, methodGenerator);
        this._left.startIterator(classGenerator, methodGenerator);
        this._right.translate(classGenerator, methodGenerator);
        this._right.startIterator(classGenerator, methodGenerator);
        if (type2 instanceof ResultTreeType) {
            Type type4 = Type.String;
            type2.translateTo(classGenerator, methodGenerator, type4);
            type2 = type4;
        }
        instructionList.append(new PUSH(constantPool, this._op));
        instructionList.append(methodGenerator.loadDOM());
        instructionList.append(new INVOKESTATIC(constantPool.addMethodref(Constants.BASIS_LIBRARY_CLASS, "compare", "(" + type.toSignature() + type2.toSignature() + "ILcom/sun/org/apache/xalan/internal/xsltc/DOM;)Z")));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public void translateDesynthesized(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        Type type = this._left.getType();
        InstructionList instructionList = methodGenerator.getInstructionList();
        if (type instanceof BooleanType) {
            this._left.translate(classGenerator, methodGenerator);
            this._right.translate(classGenerator, methodGenerator);
            this._falseList.add(instructionList.append(this._op == 0 ? new IF_ICMPNE(null) : new IF_ICMPEQ(null)));
        } else {
            if (!(type instanceof NumberType)) {
                translate(classGenerator, methodGenerator);
                desynthesize(classGenerator, methodGenerator);
                return;
            }
            this._left.translate(classGenerator, methodGenerator);
            this._right.translate(classGenerator, methodGenerator);
            if (!(type instanceof RealType)) {
                this._falseList.add(instructionList.append(this._op == 0 ? new IF_ICMPNE(null) : new IF_ICMPEQ(null)));
            } else {
                instructionList.append(Constants.DCMPG);
                this._falseList.add(instructionList.append(this._op == 0 ? new IFNE(null) : new IFEQ(null)));
            }
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        Type type;
        Type typeTypeCheck = this._left.typeCheck(symbolTable);
        Type typeTypeCheck2 = this._right.typeCheck(symbolTable);
        if (typeTypeCheck.isSimple() && typeTypeCheck2.isSimple()) {
            if (typeTypeCheck != typeTypeCheck2) {
                if (typeTypeCheck instanceof BooleanType) {
                    this._right = new CastExpr(this._right, Type.Boolean);
                } else if (typeTypeCheck2 instanceof BooleanType) {
                    this._left = new CastExpr(this._left, Type.Boolean);
                } else if ((typeTypeCheck instanceof NumberType) || (typeTypeCheck2 instanceof NumberType)) {
                    Expression expression = this._left;
                    Type type2 = Type.Real;
                    this._left = new CastExpr(expression, type2);
                    this._right = new CastExpr(this._right, type2);
                } else {
                    Expression expression2 = this._left;
                    Type type3 = Type.String;
                    this._left = new CastExpr(expression2, type3);
                    this._right = new CastExpr(this._right, type3);
                }
            }
        } else if (typeTypeCheck instanceof ReferenceType) {
            this._right = new CastExpr(this._right, Type.Reference);
        } else if (typeTypeCheck2 instanceof ReferenceType) {
            this._left = new CastExpr(this._left, Type.Reference);
        } else {
            boolean z = typeTypeCheck instanceof NodeType;
            if (z && typeTypeCheck2 == (type = Type.String)) {
                this._left = new CastExpr(this._left, type);
            } else {
                Type type4 = Type.String;
                if (typeTypeCheck == type4 && (typeTypeCheck2 instanceof NodeType)) {
                    this._right = new CastExpr(this._right, type4);
                } else if (z && (typeTypeCheck2 instanceof NodeType)) {
                    this._left = new CastExpr(this._left, type4);
                    this._right = new CastExpr(this._right, type4);
                } else if (!z || !(typeTypeCheck2 instanceof NodeSetType)) {
                    if ((typeTypeCheck instanceof NodeSetType) && (typeTypeCheck2 instanceof NodeType)) {
                        swapArguments();
                    } else {
                        if (z) {
                            this._left = new CastExpr(this._left, Type.NodeSet);
                        }
                        if (typeTypeCheck2 instanceof NodeType) {
                            this._right = new CastExpr(this._right, Type.NodeSet);
                        }
                        if (typeTypeCheck.isSimple() || ((typeTypeCheck instanceof ResultTreeType) && (typeTypeCheck2 instanceof NodeSetType))) {
                            swapArguments();
                        }
                        if (this._right.getType() instanceof IntType) {
                            this._right = new CastExpr(this._right, Type.Real);
                        }
                    }
                }
            }
        }
        Type type5 = Type.Boolean;
        this._type = type5;
        return type5;
    }
}

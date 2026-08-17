package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.classfile.Field;
import com.sun.org.apache.bcel.internal.generic.ASTORE;
import com.sun.org.apache.bcel.internal.generic.BasicType;
import com.sun.org.apache.bcel.internal.generic.CHECKCAST;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.bcel.internal.generic.PUTFIELD;
import com.sun.org.apache.bcel.internal.generic.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.BooleanType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.FilterGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.IntType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NumberType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ReferenceType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ResultTreeType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TestGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import com.sun.org.apache.xpath.internal.compiler.Keywords;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class Predicate extends Expression implements Closure {
    private Expression _exp;
    private boolean _canOptimize = true;
    private boolean _nthPositionFilter = false;
    private boolean _nthDescendant = false;
    int _ptype = -1;
    private String _className = null;
    private List<VariableRefBase> _closureVars = null;
    private Closure _parentClosure = null;
    private Expression _value = null;
    private Step _step = null;

    public Predicate(Expression expression) {
        this._exp = expression;
        expression.setParent(this);
    }

    private void compileFilter(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        this._className = getXSLTC().getHelperClassName();
        ClassGenerator filterGenerator = new FilterGenerator(this._className, Constants.OBJECT_CLASS, toString(), 33, new String[]{Constants.CURRENT_NODE_LIST_FILTER}, classGenerator.getStylesheet());
        ConstantPoolGen constantPool = filterGenerator.getConstantPool();
        List<VariableRefBase> list = this._closureVars;
        int size = list == null ? 0 : list.size();
        for (int i = 0; i < size; i++) {
            VariableBase variable = this._closureVars.get(i).getVariable();
            filterGenerator.addField(new Field(1, constantPool.addUtf8(variable.getEscapedName()), constantPool.addUtf8(variable.getType().toSignature()), null, constantPool.getConstantPool()));
        }
        InstructionList instructionList = new InstructionList();
        BasicType basicType = Type.BOOLEAN;
        Type jCRefType = Util.getJCRefType("Lcom/sun/org/apache/xalan/internal/xsltc/runtime/AbstractTranslet;");
        Type jCRefType2 = Util.getJCRefType("Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;");
        BasicType basicType2 = Type.INT;
        TestGenerator testGenerator = new TestGenerator(17, basicType, new Type[]{basicType2, basicType2, basicType2, basicType2, jCRefType, jCRefType2}, new String[]{"node", Keywords.FUNC_POSITION_STRING, Keywords.FUNC_LAST_STRING, Keywords.FUNC_CURRENT_STRING, "translet", Constants.ITERATOR_PNAME}, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_TEST, this._className, instructionList, constantPool);
        LocalVariableGen localVariableGenAddLocalVariable = testGenerator.addLocalVariable(Constants.DOCUMENT_PNAME, Util.getJCRefType(Constants.DOM_INTF_SIG), null, null);
        String className = classGenerator.getClassName();
        instructionList.append(filterGenerator.loadTranslet());
        instructionList.append(new CHECKCAST(constantPool.addClass(className)));
        instructionList.append(new GETFIELD(constantPool.addFieldref(className, Constants.DOM_FIELD, Constants.DOM_INTF_SIG)));
        localVariableGenAddLocalVariable.setStart(instructionList.append(new ASTORE(localVariableGenAddLocalVariable.getIndex())));
        testGenerator.setDomIndex(localVariableGenAddLocalVariable.getIndex());
        this._exp.translate(filterGenerator, testGenerator);
        instructionList.append(Constants.IRETURN);
        filterGenerator.addEmptyConstructor(1);
        filterGenerator.addMethod(testGenerator);
        getXSLTC().dumpClass(filterGenerator.getJavaClass());
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Closure
    public void addVariable(VariableRefBase variableRefBase) {
        if (this._closureVars == null) {
            this._closureVars = new ArrayList();
        }
        if (this._closureVars.contains(variableRefBase)) {
            return;
        }
        this._closureVars.add(variableRefBase);
        Closure parentClosure = getParentClosure();
        if (parentClosure != null) {
            parentClosure.addVariable(variableRefBase);
        }
    }

    public void dontOptimize() {
        this._canOptimize = false;
    }

    public Expression getCompareValue() {
        Expression expression = this._value;
        if (expression != null) {
            return expression;
        }
        Expression expression2 = this._exp;
        if (expression2 != null && (expression2 instanceof EqualityExpr)) {
            EqualityExpr equalityExpr = (EqualityExpr) expression2;
            Expression left = equalityExpr.getLeft();
            Expression right = equalityExpr.getRight();
            if (left instanceof LiteralExpr) {
                this._value = left;
                return left;
            }
            if ((left instanceof VariableRefBase) && left.getType() == com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.String) {
                this._value = left;
                return left;
            }
            if (right instanceof LiteralExpr) {
                this._value = right;
                return right;
            }
            if ((right instanceof VariableRefBase) && right.getType() == com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.String) {
                this._value = right;
                return right;
            }
        }
        return null;
    }

    public Expression getExpr() {
        return this._exp;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Closure
    public String getInnerClassName() {
        return this._className;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Closure
    public Closure getParentClosure() {
        if (this._parentClosure == null) {
            SyntaxTreeNode parent = getParent();
            while (!(parent instanceof Closure)) {
                if (!(parent instanceof TopLevelElement) && (parent = parent.getParent()) != 0) {
                }
            }
            this._parentClosure = (Closure) parent;
        }
        return this._parentClosure;
    }

    public int getPosType() {
        if (this._ptype == -1) {
            SyntaxTreeNode parent = getParent();
            if (parent instanceof StepPattern) {
                this._ptype = ((StepPattern) parent).getNodeType();
            } else if (parent instanceof AbsoluteLocationPath) {
                Expression path = ((AbsoluteLocationPath) parent).getPath();
                if (path instanceof Step) {
                    this._ptype = ((Step) path).getNodeType();
                }
            } else if (parent instanceof VariableRefBase) {
                Expression expression = ((VariableRefBase) parent).getVariable().getExpression();
                if (expression instanceof Step) {
                    this._ptype = ((Step) expression).getNodeType();
                }
            } else if (parent instanceof Step) {
                this._ptype = ((Step) parent).getNodeType();
            }
        }
        return this._ptype;
    }

    public Step getStep() {
        Step step = this._step;
        if (step != null) {
            return step;
        }
        Expression expression = this._exp;
        if (expression == null) {
            return null;
        }
        if (expression instanceof EqualityExpr) {
            EqualityExpr equalityExpr = (EqualityExpr) expression;
            Expression left = equalityExpr.getLeft();
            Expression right = equalityExpr.getRight();
            if (left instanceof CastExpr) {
                left = ((CastExpr) left).getExpr();
            }
            if (left instanceof Step) {
                this._step = (Step) left;
            }
            if (right instanceof CastExpr) {
                right = ((CastExpr) right).getExpr();
            }
            if (right instanceof Step) {
                this._step = (Step) right;
            }
        }
        return this._step;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public boolean hasLastCall() {
        return this._exp.hasLastCall();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public boolean hasPositionCall() {
        return this._exp.hasPositionCall();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Closure
    public boolean inInnerClass() {
        return this._className != null;
    }

    public boolean isBooleanTest() {
        return this._exp instanceof BooleanExpr;
    }

    public boolean isNodeValueTest() {
        return (!this._canOptimize || getStep() == null || getCompareValue() == null) ? false : true;
    }

    public boolean isNthDescendant() {
        return this._nthDescendant;
    }

    public boolean isNthPositionFilter() {
        return this._nthPositionFilter;
    }

    public boolean parentIsPattern() {
        return getParent() instanceof Pattern;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void setParser(Parser parser) {
        super.setParser(parser);
        this._exp.setParser(parser);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public String toString() {
        return "pred(" + this._exp + ')';
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        if (this._nthPositionFilter || this._nthDescendant) {
            this._exp.translate(classGenerator, methodGenerator);
            return;
        }
        if (!isNodeValueTest() || !(getParent() instanceof Step)) {
            translateFilter(classGenerator, methodGenerator);
            return;
        }
        this._value.translate(classGenerator, methodGenerator);
        instructionList.append(new CHECKCAST(constantPool.addClass("java.lang.String")));
        instructionList.append(new PUSH(constantPool, ((EqualityExpr) this._exp).getOp()));
    }

    public void translateFilter(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        compileFilter(classGenerator, methodGenerator);
        instructionList.append(new NEW(constantPool.addClass(this._className)));
        instructionList.append(Constants.DUP);
        instructionList.append(new INVOKESPECIAL(constantPool.addMethodref(this._className, Const.CONSTRUCTOR_NAME, "()V")));
        List<VariableRefBase> list = this._closureVars;
        int size = list == null ? 0 : list.size();
        for (int i = 0; i < size; i++) {
            VariableBase variable = this._closureVars.get(i).getVariable();
            com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type type = variable.getType();
            instructionList.append(Constants.DUP);
            Closure parentClosure = this._parentClosure;
            while (parentClosure != null && !parentClosure.inInnerClass()) {
                parentClosure = parentClosure.getParentClosure();
            }
            if (parentClosure != null) {
                instructionList.append(Constants.ALOAD_0);
                instructionList.append(new GETFIELD(constantPool.addFieldref(parentClosure.getInnerClassName(), variable.getEscapedName(), type.toSignature())));
            } else {
                instructionList.append(variable.loadInstruction());
            }
            instructionList.append(new PUTFIELD(constantPool.addFieldref(this._className, variable.getEscapedName(), type.toSignature())));
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type typeTypeCheck = this._exp.typeCheck(symbolTable);
        if (typeTypeCheck instanceof ReferenceType) {
            Expression expression = this._exp;
            com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type type = com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Real;
            this._exp = new CastExpr(expression, type);
            typeTypeCheck = type;
        }
        if (typeTypeCheck instanceof ResultTreeType) {
            this._exp = new CastExpr(this._exp, com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Boolean);
            CastExpr castExpr = new CastExpr(this._exp, com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Real);
            this._exp = castExpr;
            typeTypeCheck = castExpr.typeCheck(symbolTable);
        }
        if (!(typeTypeCheck instanceof NumberType)) {
            if (!(typeTypeCheck instanceof BooleanType)) {
                this._exp = new CastExpr(this._exp, com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Boolean);
            }
            com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type type2 = com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Boolean;
            this._type = type2;
            return type2;
        }
        if (!(typeTypeCheck instanceof IntType)) {
            this._exp = new CastExpr(this._exp, com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int);
        }
        boolean z = false;
        if (this._canOptimize) {
            boolean z2 = (this._exp.hasLastCall() || this._exp.hasPositionCall()) ? false : true;
            this._nthPositionFilter = z2;
            if (z2) {
                SyntaxTreeNode parent = getParent();
                if ((parent instanceof Step) && (parent.getParent() instanceof AbsoluteLocationPath)) {
                    z = true;
                }
                this._nthDescendant = z;
                com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type type3 = com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.NodeSet;
                this._type = type3;
                return type3;
            }
        }
        this._nthDescendant = false;
        this._nthPositionFilter = false;
        PositionCall positionCall = new PositionCall(getParser().getQNameIgnoreDefaultNs(Keywords.FUNC_POSITION_STRING));
        positionCall.setParser(getParser());
        positionCall.setParent(this);
        EqualityExpr equalityExpr = new EqualityExpr(0, positionCall, this._exp);
        this._exp = equalityExpr;
        com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type typeTypeCheck2 = equalityExpr.typeCheck(symbolTable);
        com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type type4 = com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Boolean;
        if (typeTypeCheck2 != type4) {
            this._exp = new CastExpr(this._exp, type4);
        }
        this._type = type4;
        return type4;
    }
}

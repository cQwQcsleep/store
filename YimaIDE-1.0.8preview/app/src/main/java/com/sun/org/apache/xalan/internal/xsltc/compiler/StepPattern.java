package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.classfile.Field;
import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.sun.org.apache.bcel.internal.generic.ASTORE;
import com.sun.org.apache.bcel.internal.generic.BranchHandle;
import com.sun.org.apache.bcel.internal.generic.BranchInstruction;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import com.sun.org.apache.bcel.internal.generic.GOTO_W;
import com.sun.org.apache.bcel.internal.generic.IFLT;
import com.sun.org.apache.bcel.internal.generic.IFNE;
import com.sun.org.apache.bcel.internal.generic.IFNONNULL;
import com.sun.org.apache.bcel.internal.generic.IF_ICMPEQ;
import com.sun.org.apache.bcel.internal.generic.IF_ICMPLT;
import com.sun.org.apache.bcel.internal.generic.IF_ICMPNE;
import com.sun.org.apache.bcel.internal.generic.ILOAD;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import com.sun.org.apache.bcel.internal.generic.ISTORE;
import com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.bcel.internal.generic.PUTFIELD;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import com.sun.org.apache.xml.internal.dtm.Axis;
import com.sun.org.apache.xpath.internal.XPath;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class StepPattern extends RelativePathPattern {
    private static final int GENERAL_CONTEXT = 2;
    private static final int NO_CONTEXT = 0;
    private static final int SIMPLE_CONTEXT = 1;
    protected final int _axis;
    private int _contextCase;
    protected final int _nodeType;
    protected List<Predicate> _predicates;
    private Step _step = null;
    private boolean _isEpsilon = false;
    private double _priority = Double.MAX_VALUE;

    public StepPattern(int i, int i2, List<Predicate> list) {
        this._axis = i;
        this._nodeType = i2;
        this._predicates = list;
    }

    private int analyzeCases() {
        int size = this._predicates.size();
        boolean z = true;
        for (int i = 0; i < size && z; i++) {
            Predicate predicate = this._predicates.get(i);
            if (predicate.isNthPositionFilter() || predicate.hasPositionCall() || predicate.hasLastCall()) {
                z = false;
            }
        }
        if (z) {
            return 0;
        }
        return size == 1 ? 1 : 2;
    }

    private String getNextFieldName() {
        return "__step_pattern_iter_" + getXSLTC().nextStepPatternSerial();
    }

    private void translateGeneralContext(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        int iAddFieldref;
        BranchHandle branchHandleAppend;
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        String nextFieldName = getNextFieldName();
        LocalVariableGen localVariableGenAddLocalVariable = methodGenerator.addLocalVariable("step_pattern_tmp1", Util.getJCRefType("I"), null, null);
        localVariableGenAddLocalVariable.setStart(instructionList.append(new ISTORE(localVariableGenAddLocalVariable.getIndex())));
        LocalVariableGen localVariableGenAddLocalVariable2 = methodGenerator.addLocalVariable("step_pattern_tmp2", Util.getJCRefType("Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;"), null, null);
        if (classGenerator.isExternal()) {
            iAddFieldref = 0;
            branchHandleAppend = null;
        } else {
            classGenerator.addField(new Field(2, constantPool.addUtf8(nextFieldName), constantPool.addUtf8("Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;"), null, constantPool.getConstantPool()));
            iAddFieldref = constantPool.addFieldref(classGenerator.getClassName(), nextFieldName, "Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;");
            instructionList.append(classGenerator.loadTranslet());
            instructionList.append(new GETFIELD(iAddFieldref));
            instructionList.append(Constants.DUP);
            localVariableGenAddLocalVariable2.setStart(instructionList.append(new ASTORE(localVariableGenAddLocalVariable2.getIndex())));
            branchHandleAppend = instructionList.append((BranchInstruction) new IFNONNULL(null));
            instructionList.append(classGenerator.loadTranslet());
        }
        this._step.translate(classGenerator, methodGenerator);
        InstructionHandle instructionHandleAppend = instructionList.append(new ASTORE(localVariableGenAddLocalVariable2.getIndex()));
        if (classGenerator.isExternal()) {
            localVariableGenAddLocalVariable2.setStart(instructionHandleAppend);
        } else {
            instructionList.append(new ALOAD(localVariableGenAddLocalVariable2.getIndex()));
            instructionList.append(new PUTFIELD(iAddFieldref));
            branchHandleAppend.setTarget(instructionList.append(Constants.NOP));
        }
        instructionList.append(methodGenerator.loadDOM());
        instructionList.append(new ILOAD(localVariableGenAddLocalVariable.getIndex()));
        instructionList.append(new INVOKEINTERFACE(constantPool.addInterfaceMethodref(Constants.DOM_INTF, Constants.GET_PARENT, Constants.GET_PARENT_SIG), 2));
        instructionList.append(new ALOAD(localVariableGenAddLocalVariable2.getIndex()));
        instructionList.append(Constants.SWAP);
        instructionList.append(methodGenerator.setStartNode());
        LocalVariableGen localVariableGenAddLocalVariable3 = methodGenerator.addLocalVariable("step_pattern_tmp3", Util.getJCRefType("I"), null, null);
        BranchHandle branchHandleAppend2 = instructionList.append((BranchInstruction) new GOTO(null));
        InstructionHandle instructionHandleAppend2 = instructionList.append(new ALOAD(localVariableGenAddLocalVariable2.getIndex()));
        localVariableGenAddLocalVariable3.setStart(instructionHandleAppend2);
        InstructionHandle instructionHandleAppend3 = instructionList.append(methodGenerator.nextNode());
        instructionList.append(Constants.DUP);
        instructionList.append(new ISTORE(localVariableGenAddLocalVariable3.getIndex()));
        this._falseList.add(instructionList.append((BranchInstruction) new IFLT(null)));
        instructionList.append(new ILOAD(localVariableGenAddLocalVariable3.getIndex()));
        instructionList.append(new ILOAD(localVariableGenAddLocalVariable.getIndex()));
        localVariableGenAddLocalVariable2.setEnd(instructionList.append((BranchInstruction) new IF_ICMPLT(instructionHandleAppend2)));
        localVariableGenAddLocalVariable3.setEnd(instructionList.append(new ILOAD(localVariableGenAddLocalVariable3.getIndex())));
        localVariableGenAddLocalVariable.setEnd(instructionList.append(new ILOAD(localVariableGenAddLocalVariable.getIndex())));
        this._falseList.add(instructionList.append((BranchInstruction) new IF_ICMPNE(null)));
        branchHandleAppend2.setTarget(instructionHandleAppend3);
    }

    private void translateKernel(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        int i = this._nodeType;
        if (i == 1) {
            int iAddInterfaceMethodref = constantPool.addInterfaceMethodref(Constants.DOM_INTF, "isElement", "(I)Z");
            instructionList.append(methodGenerator.loadDOM());
            instructionList.append(Constants.SWAP);
            instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref, 2));
            BranchHandle branchHandleAppend = instructionList.append((BranchInstruction) new IFNE(null));
            this._falseList.add(instructionList.append((BranchInstruction) new GOTO_W(null)));
            branchHandleAppend.setTarget(instructionList.append(Constants.NOP));
            return;
        }
        if (i == 2) {
            int iAddInterfaceMethodref2 = constantPool.addInterfaceMethodref(Constants.DOM_INTF, "isAttribute", "(I)Z");
            instructionList.append(methodGenerator.loadDOM());
            instructionList.append(Constants.SWAP);
            instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref2, 2));
            BranchHandle branchHandleAppend2 = instructionList.append((BranchInstruction) new IFNE(null));
            this._falseList.add(instructionList.append((BranchInstruction) new GOTO_W(null)));
            branchHandleAppend2.setTarget(instructionList.append(Constants.NOP));
            return;
        }
        int iAddInterfaceMethodref3 = constantPool.addInterfaceMethodref(Constants.DOM_INTF, "getExpandedTypeID", Constants.GET_PARENT_SIG);
        instructionList.append(methodGenerator.loadDOM());
        instructionList.append(Constants.SWAP);
        instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref3, 2));
        instructionList.append(new PUSH(constantPool, this._nodeType));
        BranchHandle branchHandleAppend3 = instructionList.append((BranchInstruction) new IF_ICMPEQ(null));
        this._falseList.add(instructionList.append((BranchInstruction) new GOTO_W(null)));
        branchHandleAppend3.setTarget(instructionList.append(Constants.NOP));
    }

    private void translateNoContext(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        instructionList.append(methodGenerator.loadCurrentNode());
        instructionList.append(Constants.SWAP);
        instructionList.append(methodGenerator.storeCurrentNode());
        if (!this._isEpsilon) {
            instructionList.append(methodGenerator.loadCurrentNode());
            translateKernel(classGenerator, methodGenerator);
        }
        Iterator<Predicate> it = this._predicates.iterator();
        while (it.hasNext()) {
            Expression expr = it.next().getExpr();
            expr.translateDesynthesized(classGenerator, methodGenerator);
            this._trueList.append(expr._trueList);
            this._falseList.append(expr._falseList);
        }
        backPatchTrueList(instructionList.append(methodGenerator.storeCurrentNode()));
        BranchHandle branchHandleAppend = instructionList.append((BranchInstruction) new GOTO(null));
        backPatchFalseList(instructionList.append(methodGenerator.storeCurrentNode()));
        this._falseList.add(instructionList.append((BranchInstruction) new GOTO(null)));
        branchHandleAppend.setTarget(instructionList.append(Constants.NOP));
    }

    private void translateSimpleContext(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        LocalVariableGen localVariableGenAddLocalVariable = methodGenerator.addLocalVariable("step_pattern_tmp1", Util.getJCRefType("I"), null, null);
        localVariableGenAddLocalVariable.setStart(instructionList.append(new ISTORE(localVariableGenAddLocalVariable.getIndex())));
        if (!this._isEpsilon) {
            instructionList.append(new ILOAD(localVariableGenAddLocalVariable.getIndex()));
            translateKernel(classGenerator, methodGenerator);
        }
        instructionList.append(methodGenerator.loadCurrentNode());
        instructionList.append(methodGenerator.loadIterator());
        int iAddMethodref = constantPool.addMethodref(Constants.MATCHING_ITERATOR, Const.CONSTRUCTOR_NAME, "(ILcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;)V");
        this._step.translate(classGenerator, methodGenerator);
        LocalVariableGen localVariableGenAddLocalVariable2 = methodGenerator.addLocalVariable("step_pattern_tmp2", Util.getJCRefType("Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;"), null, null);
        localVariableGenAddLocalVariable2.setStart(instructionList.append(new ASTORE(localVariableGenAddLocalVariable2.getIndex())));
        instructionList.append(new NEW(constantPool.addClass(Constants.MATCHING_ITERATOR)));
        instructionList.append(Constants.DUP);
        instructionList.append(new ILOAD(localVariableGenAddLocalVariable.getIndex()));
        localVariableGenAddLocalVariable2.setEnd(instructionList.append(new ALOAD(localVariableGenAddLocalVariable2.getIndex())));
        instructionList.append(new INVOKESPECIAL(iAddMethodref));
        instructionList.append(methodGenerator.loadDOM());
        instructionList.append(new ILOAD(localVariableGenAddLocalVariable.getIndex()));
        instructionList.append(new INVOKEINTERFACE(constantPool.addInterfaceMethodref(Constants.DOM_INTF, Constants.GET_PARENT, Constants.GET_PARENT_SIG), 2));
        instructionList.append(methodGenerator.setStartNode());
        instructionList.append(methodGenerator.storeIterator());
        localVariableGenAddLocalVariable.setEnd(instructionList.append(new ILOAD(localVariableGenAddLocalVariable.getIndex())));
        instructionList.append(methodGenerator.storeCurrentNode());
        Expression expr = this._predicates.get(0).getExpr();
        expr.translateDesynthesized(classGenerator, methodGenerator);
        InstructionHandle instructionHandleAppend = instructionList.append(methodGenerator.storeIterator());
        instructionList.append(methodGenerator.storeCurrentNode());
        expr.backPatchTrueList(instructionHandleAppend);
        BranchHandle branchHandleAppend = instructionList.append((BranchInstruction) new GOTO(null));
        InstructionHandle instructionHandleAppend2 = instructionList.append(methodGenerator.storeIterator());
        instructionList.append(methodGenerator.storeCurrentNode());
        expr.backPatchFalseList(instructionHandleAppend2);
        this._falseList.add(instructionList.append((BranchInstruction) new GOTO(null)));
        branchHandleAppend.setTarget(instructionList.append(Constants.NOP));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.LocationPathPattern
    public int getAxis() {
        return this._axis;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.LocationPathPattern
    public double getDefaultPriority() {
        double d = this._priority;
        if (d != Double.MAX_VALUE) {
            return d;
        }
        if (hasPredicates()) {
            return 0.5d;
        }
        int i = this._nodeType;
        if (i == -1) {
            return -0.5d;
        }
        if (i == 0 || i >= 14) {
            return XPath.MATCH_SCORE_QNAME;
        }
        return -0.5d;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.LocationPathPattern
    public StepPattern getKernelPattern() {
        return this;
    }

    public int getNodeType() {
        return this._nodeType;
    }

    public boolean hasPredicates() {
        List<Predicate> list = this._predicates;
        return list != null && list.size() > 0;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.LocationPathPattern
    public boolean isWildcard() {
        return this._isEpsilon && !hasPredicates();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.LocationPathPattern
    public void reduceKernelPattern() {
        this._isEpsilon = true;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void setParser(Parser parser) {
        super.setParser(parser);
        List<Predicate> list = this._predicates;
        if (list != null) {
            for (Predicate predicate : list) {
                predicate.setParser(parser);
                predicate.setParent(this);
            }
        }
    }

    public StepPattern setPredicates(List<Predicate> list) {
        this._predicates = list;
        return this;
    }

    public void setPriority(double d) {
        this._priority = d;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.LocationPathPattern, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public String toString() {
        String string;
        StringBuffer stringBuffer = new StringBuffer("stepPattern(\"");
        stringBuffer.append(Axis.getNames(this._axis));
        stringBuffer.append("\", ");
        boolean z = this._isEpsilon;
        int i = this._nodeType;
        if (z) {
            string = "epsilon{" + Integer.toString(i) + "}";
        } else {
            string = Integer.toString(i);
        }
        stringBuffer.append(string);
        if (this._predicates != null) {
            stringBuffer.append(", ");
            stringBuffer.append(this._predicates.toString());
        }
        stringBuffer.append(')');
        return stringBuffer.toString();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.LocationPathPattern, com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        if (!hasPredicates()) {
            if (isWildcard()) {
                instructionList.append(Constants.POP);
                return;
            } else {
                translateKernel(classGenerator, methodGenerator);
                return;
            }
        }
        int i = this._contextCase;
        if (i == 0) {
            translateNoContext(classGenerator, methodGenerator);
        } else if (i != 1) {
            translateGeneralContext(classGenerator, methodGenerator);
        } else {
            translateSimpleContext(classGenerator, methodGenerator);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.LocationPathPattern, com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        Step step;
        if (hasPredicates()) {
            Iterator<Predicate> it = this._predicates.iterator();
            while (it.hasNext()) {
                it.next().typeCheck(symbolTable);
            }
            int iAnalyzeCases = analyzeCases();
            this._contextCase = iAnalyzeCases;
            Step step2 = null;
            if (iAnalyzeCases == 1) {
                if (this._predicates.get(0).isNthPositionFilter()) {
                    this._contextCase = 2;
                    step = new Step(this._axis, this._nodeType, this._predicates);
                } else {
                    step = new Step(this._axis, this._nodeType, null);
                }
                step2 = step;
            } else if (iAnalyzeCases == 2) {
                Iterator<Predicate> it2 = this._predicates.iterator();
                while (it2.hasNext()) {
                    it2.next().dontOptimize();
                }
                step2 = new Step(this._axis, this._nodeType, this._predicates);
            }
            if (step2 != null) {
                step2.setParser(getParser());
                step2.typeCheck(symbolTable);
                this._step = step2;
            }
        }
        return this._axis == 3 ? Type.Element : Type.Attribute;
    }
}

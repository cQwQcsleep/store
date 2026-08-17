package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.sun.org.apache.bcel.internal.generic.ASTORE;
import com.sun.org.apache.bcel.internal.generic.CHECKCAST;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.ICONST;
import com.sun.org.apache.bcel.internal.generic.ILOAD;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import com.sun.org.apache.bcel.internal.generic.ISTORE;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import com.sun.org.apache.xml.internal.dtm.Axis;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class Step extends RelativeLocationPath {
    private int _axis;
    private boolean _hadPredicates = false;
    private int _nodeType;
    private List<Predicate> _predicates;

    public Step(int i, int i2, List<Predicate> list) {
        this._axis = i;
        this._nodeType = i2;
        this._predicates = list;
    }

    private boolean hasParentLocationPath() {
        return getParent() instanceof ParentLocationPath;
    }

    private boolean hasParentPattern() {
        SyntaxTreeNode parent = getParent();
        return (parent instanceof ParentPattern) || (parent instanceof ParentLocationPath) || (parent instanceof UnionPathExpr) || (parent instanceof FilterParentPath);
    }

    private boolean hasPredicates() {
        List<Predicate> list = this._predicates;
        return list != null && list.size() > 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.sun.org.apache.xalan.internal.xsltc.compiler.Step] */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode] */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode] */
    private boolean isPredicate() {
        while (this != 0) {
            this = this.getParent();
            if (this instanceof Predicate) {
                return true;
            }
        }
        return false;
    }

    private void translateStep(ClassGenerator classGenerator, MethodGenerator methodGenerator, int i) {
        String str;
        int iLastIndexOf;
        int i2;
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        if (i >= 0) {
            translatePredicates(classGenerator, methodGenerator, i);
            return;
        }
        XSLTC xsltc = getParser().getXSLTC();
        if (this._nodeType >= 14) {
            str = xsltc.getNamesIndex().get(this._nodeType - 14);
            iLastIndexOf = str.lastIndexOf(42);
        } else {
            str = null;
            iLastIndexOf = 0;
        }
        if (this._axis == 2 && (i2 = this._nodeType) != 2 && i2 != -1 && !hasParentPattern() && iLastIndexOf == 0) {
            int iAddInterfaceMethodref = constantPool.addInterfaceMethodref(Constants.DOM_INTF, "getTypedAxisIterator", "(II)Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;");
            instructionList.append(methodGenerator.loadDOM());
            instructionList.append(new PUSH(constantPool, 2));
            instructionList.append(new PUSH(constantPool, this._nodeType));
            instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref, 3));
            return;
        }
        SyntaxTreeNode parent = getParent();
        if (isAbbreviatedDot()) {
            if (this._type == Type.Node) {
                instructionList.append(methodGenerator.loadContextNode());
                return;
            }
            if (!(parent instanceof ParentLocationPath)) {
                int iAddInterfaceMethodref2 = constantPool.addInterfaceMethodref(Constants.DOM_INTF, "getAxisIterator", "(I)Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;");
                instructionList.append(methodGenerator.loadDOM());
                instructionList.append(new PUSH(constantPool, this._axis));
                instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref2, 2));
                return;
            }
            int iAddMethodref = constantPool.addMethodref(Constants.SINGLETON_ITERATOR, Const.CONSTRUCTOR_NAME, "(I)V");
            instructionList.append(new NEW(constantPool.addClass(Constants.SINGLETON_ITERATOR)));
            instructionList.append(Constants.DUP);
            instructionList.append(methodGenerator.loadContextNode());
            instructionList.append(new INVOKESPECIAL(iAddMethodref));
            return;
        }
        if ((parent instanceof ParentLocationPath) && (parent.getParent() instanceof ParentLocationPath) && this._nodeType == 1 && !this._hadPredicates) {
            this._nodeType = -1;
        }
        int i3 = this._nodeType;
        if (i3 != -1) {
            if (i3 != 1) {
                if (i3 == 2) {
                    this._axis = 2;
                } else if (iLastIndexOf > 1) {
                    int iRegisterNamespace = xsltc.registerNamespace(this._axis == 2 ? str.substring(0, iLastIndexOf - 2) : str.substring(0, iLastIndexOf - 1));
                    int iAddInterfaceMethodref3 = constantPool.addInterfaceMethodref(Constants.DOM_INTF, "getNamespaceAxisIterator", "(II)Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;");
                    instructionList.append(methodGenerator.loadDOM());
                    instructionList.append(new PUSH(constantPool, this._axis));
                    instructionList.append(new PUSH(constantPool, iRegisterNamespace));
                    instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref3, 3));
                    return;
                }
            }
            int iAddInterfaceMethodref4 = constantPool.addInterfaceMethodref(Constants.DOM_INTF, "getTypedAxisIterator", "(II)Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;");
            instructionList.append(methodGenerator.loadDOM());
            instructionList.append(new PUSH(constantPool, this._axis));
            instructionList.append(new PUSH(constantPool, this._nodeType));
            instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref4, 3));
            return;
        }
        int iAddInterfaceMethodref5 = constantPool.addInterfaceMethodref(Constants.DOM_INTF, "getAxisIterator", "(I)Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;");
        instructionList.append(methodGenerator.loadDOM());
        instructionList.append(new PUSH(constantPool, this._axis));
        instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref5, 2));
    }

    public void addPredicates(List<Predicate> list) {
        List<Predicate> list2 = this._predicates;
        if (list2 == null) {
            this._predicates = list;
        } else {
            list2.addAll(list);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.RelativeLocationPath
    public int getAxis() {
        return this._axis;
    }

    public int getNodeType() {
        return this._nodeType;
    }

    public List<Predicate> getPredicates() {
        return this._predicates;
    }

    public boolean isAbbreviatedDDot() {
        return this._nodeType == -1 && this._axis == 10;
    }

    public boolean isAbbreviatedDot() {
        return this._nodeType == -1 && this._axis == 13;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.RelativeLocationPath
    public void setAxis(int i) {
        this._axis = i;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void setParser(Parser parser) {
        super.setParser(parser);
        List<Predicate> list = this._predicates;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Predicate predicate = this._predicates.get(i);
                predicate.setParser(parser);
                predicate.setParent(this);
            }
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("step(\"");
        stringBuffer.append(Axis.getNames(this._axis));
        stringBuffer.append("\", ");
        stringBuffer.append(this._nodeType);
        List<Predicate> list = this._predicates;
        if (list != null) {
            for (Predicate predicate : list) {
                stringBuffer.append(", ");
                stringBuffer.append(predicate.toString());
            }
        }
        stringBuffer.append(')');
        return stringBuffer.toString();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        translateStep(classGenerator, methodGenerator, hasPredicates() ? this._predicates.size() - 1 : -1);
    }

    public void translatePredicates(ClassGenerator classGenerator, MethodGenerator methodGenerator, int i) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        if (i < 0) {
            translateStep(classGenerator, methodGenerator, i);
            return;
        }
        int i2 = i - 1;
        Predicate predicate = this._predicates.get(i);
        if (predicate.isNodeValueTest()) {
            Step step = predicate.getStep();
            instructionList.append(methodGenerator.loadDOM());
            if (step.isAbbreviatedDot()) {
                translateStep(classGenerator, methodGenerator, i2);
                instructionList.append(new ICONST(0));
            } else {
                ParentLocationPath parentLocationPath = new ParentLocationPath(this, step);
                step._parent = parentLocationPath;
                this._parent = parentLocationPath;
                try {
                    parentLocationPath.typeCheck(getParser().getSymbolTable());
                } catch (TypeCheckError unused) {
                }
                translateStep(classGenerator, methodGenerator, i2);
                parentLocationPath.translateStep(classGenerator, methodGenerator);
                instructionList.append(new ICONST(1));
            }
            predicate.translate(classGenerator, methodGenerator);
            instructionList.append(new INVOKEINTERFACE(constantPool.addInterfaceMethodref(Constants.DOM_INTF, Constants.GET_NODE_VALUE_ITERATOR, Constants.GET_NODE_VALUE_ITERATOR_SIG), 5));
            return;
        }
        if (predicate.isNthDescendant()) {
            instructionList.append(methodGenerator.loadDOM());
            instructionList.append(new PUSH(constantPool, predicate.getPosType()));
            predicate.translate(classGenerator, methodGenerator);
            instructionList.append(new ICONST(0));
            instructionList.append(new INVOKEINTERFACE(constantPool.addInterfaceMethodref(Constants.DOM_INTF, "getNthDescendant", "(IIZ)Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;"), 4));
            return;
        }
        if (predicate.isNthPositionFilter()) {
            int iAddMethodref = constantPool.addMethodref(Constants.NTH_ITERATOR_CLASS, Const.CONSTRUCTOR_NAME, "(Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;I)V");
            translatePredicates(classGenerator, methodGenerator, i2);
            LocalVariableGen localVariableGenAddLocalVariable = methodGenerator.addLocalVariable("step_tmp1", Util.getJCRefType("Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;"), null, null);
            localVariableGenAddLocalVariable.setStart(instructionList.append(new ASTORE(localVariableGenAddLocalVariable.getIndex())));
            predicate.translate(classGenerator, methodGenerator);
            LocalVariableGen localVariableGenAddLocalVariable2 = methodGenerator.addLocalVariable("step_tmp2", Util.getJCRefType("I"), null, null);
            localVariableGenAddLocalVariable2.setStart(instructionList.append(new ISTORE(localVariableGenAddLocalVariable2.getIndex())));
            instructionList.append(new NEW(constantPool.addClass(Constants.NTH_ITERATOR_CLASS)));
            instructionList.append(Constants.DUP);
            localVariableGenAddLocalVariable.setEnd(instructionList.append(new ALOAD(localVariableGenAddLocalVariable.getIndex())));
            localVariableGenAddLocalVariable2.setEnd(instructionList.append(new ILOAD(localVariableGenAddLocalVariable2.getIndex())));
            instructionList.append(new INVOKESPECIAL(iAddMethodref));
            return;
        }
        int iAddMethodref2 = constantPool.addMethodref(Constants.CURRENT_NODE_LIST_ITERATOR, Const.CONSTRUCTOR_NAME, "(Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;Lcom/sun/org/apache/xalan/internal/xsltc/dom/CurrentNodeListFilter;ILcom/sun/org/apache/xalan/internal/xsltc/runtime/AbstractTranslet;)V");
        translatePredicates(classGenerator, methodGenerator, i2);
        LocalVariableGen localVariableGenAddLocalVariable3 = methodGenerator.addLocalVariable("step_tmp1", Util.getJCRefType("Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;"), null, null);
        localVariableGenAddLocalVariable3.setStart(instructionList.append(new ASTORE(localVariableGenAddLocalVariable3.getIndex())));
        predicate.translateFilter(classGenerator, methodGenerator);
        LocalVariableGen localVariableGenAddLocalVariable4 = methodGenerator.addLocalVariable("step_tmp2", Util.getJCRefType(Constants.CURRENT_NODE_LIST_FILTER_SIG), null, null);
        localVariableGenAddLocalVariable4.setStart(instructionList.append(new ASTORE(localVariableGenAddLocalVariable4.getIndex())));
        instructionList.append(new NEW(constantPool.addClass(Constants.CURRENT_NODE_LIST_ITERATOR)));
        instructionList.append(Constants.DUP);
        localVariableGenAddLocalVariable3.setEnd(instructionList.append(new ALOAD(localVariableGenAddLocalVariable3.getIndex())));
        localVariableGenAddLocalVariable4.setEnd(instructionList.append(new ALOAD(localVariableGenAddLocalVariable4.getIndex())));
        instructionList.append(methodGenerator.loadCurrentNode());
        instructionList.append(classGenerator.loadTranslet());
        if (classGenerator.isExternal()) {
            instructionList.append(new CHECKCAST(constantPool.addClass(classGenerator.getClassName())));
        }
        instructionList.append(new INVOKESPECIAL(iAddMethodref2));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        this._hadPredicates = hasPredicates();
        if (isAbbreviatedDot()) {
            this._type = (hasParentPattern() || hasPredicates() || hasParentLocationPath()) ? Type.NodeSet : Type.Node;
        } else {
            this._type = Type.NodeSet;
        }
        List<Predicate> list = this._predicates;
        if (list != null) {
            Iterator<Predicate> it = list.iterator();
            while (it.hasNext()) {
                it.next().typeCheck(symbolTable);
            }
        }
        return this._type;
    }
}

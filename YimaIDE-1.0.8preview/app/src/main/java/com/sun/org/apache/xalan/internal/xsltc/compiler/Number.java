package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.classfile.Field;
import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.sun.org.apache.bcel.internal.generic.ASTORE;
import com.sun.org.apache.bcel.internal.generic.BranchHandle;
import com.sun.org.apache.bcel.internal.generic.BranchInstruction;
import com.sun.org.apache.bcel.internal.generic.CHECKCAST;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import com.sun.org.apache.bcel.internal.generic.IFNONNULL;
import com.sun.org.apache.bcel.internal.generic.ILOAD;
import com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import com.sun.org.apache.bcel.internal.generic.INVOKESTATIC;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import com.sun.org.apache.bcel.internal.generic.LocalVariableInstruction;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.bcel.internal.generic.PUTFIELD;
import com.sun.org.apache.bcel.internal.generic.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MatchGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeCounterGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.RealType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import com.sun.org.apache.xpath.internal.compiler.Keywords;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class Number extends Instruction implements Closure {
    private static final String[] ClassNames = {"com.sun.org.apache.xalan.internal.xsltc.dom.SingleNodeCounter", "com.sun.org.apache.xalan.internal.xsltc.dom.MultipleNodeCounter", "com.sun.org.apache.xalan.internal.xsltc.dom.AnyNodeCounter"};
    private static final String[] FieldNames = {"___single_node_counter", "___multiple_node_counter", "___any_node_counter"};
    private static final int LEVEL_ANY = 2;
    private static final int LEVEL_MULTIPLE = 1;
    private static final int LEVEL_SINGLE = 0;
    private Pattern _from = null;
    private Pattern _count = null;
    private Expression _value = null;
    private AttributeValueTemplate _lang = null;
    private AttributeValueTemplate _format = null;
    private AttributeValueTemplate _letterValue = null;
    private AttributeValueTemplate _groupingSeparator = null;
    private AttributeValueTemplate _groupingSize = null;
    private int _level = 0;
    private boolean _formatNeeded = false;
    private String _className = null;
    private List<VariableRefBase> _closureVars = null;

    private void compileConstructor(ClassGenerator classGenerator) {
        InstructionList instructionList = new InstructionList();
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        MethodGenerator methodGenerator = new MethodGenerator(1, Type.VOID, new Type[]{Util.getJCRefType(Constants.TRANSLET_INTF_SIG), Util.getJCRefType(Constants.DOM_INTF_SIG), Util.getJCRefType("Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;"), Type.BOOLEAN}, new String[]{Constants.DOM_PNAME, "translet", Constants.ITERATOR_PNAME, "hasFrom"}, Const.CONSTRUCTOR_NAME, this._className, instructionList, constantPool);
        instructionList.append(Constants.ALOAD_0);
        instructionList.append(Constants.ALOAD_1);
        instructionList.append(Constants.ALOAD_2);
        instructionList.append(new ALOAD(3));
        instructionList.append(new ILOAD(4));
        instructionList.append(new INVOKESPECIAL(constantPool.addMethodref(ClassNames[this._level], Const.CONSTRUCTOR_NAME, "(Lcom/sun/org/apache/xalan/internal/xsltc/Translet;Lcom/sun/org/apache/xalan/internal/xsltc/DOM;Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;Z)V")));
        instructionList.append(Constants.RETURN);
        classGenerator.addMethod(methodGenerator);
    }

    private void compileDefault(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        int[] numberFieldIndexes = getXSLTC().getNumberFieldIndexes();
        if (numberFieldIndexes[this._level] == -1) {
            String[] strArr = FieldNames;
            classGenerator.addField(new Field(2, constantPool.addUtf8(strArr[this._level]), constantPool.addUtf8(Constants.NODE_COUNTER_SIG), null, constantPool.getConstantPool()));
            numberFieldIndexes[this._level] = constantPool.addFieldref(classGenerator.getClassName(), strArr[this._level], Constants.NODE_COUNTER_SIG);
        }
        instructionList.append(classGenerator.loadTranslet());
        instructionList.append(new GETFIELD(numberFieldIndexes[this._level]));
        BranchHandle branchHandleAppend = instructionList.append((BranchInstruction) new IFNONNULL(null));
        int iAddMethodref = constantPool.addMethodref(ClassNames[this._level], "getDefaultNodeCounter", "(Lcom/sun/org/apache/xalan/internal/xsltc/Translet;Lcom/sun/org/apache/xalan/internal/xsltc/DOM;Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;)Lcom/sun/org/apache/xalan/internal/xsltc/dom/NodeCounter;");
        instructionList.append(classGenerator.loadTranslet());
        instructionList.append(methodGenerator.loadDOM());
        instructionList.append(methodGenerator.loadIterator());
        instructionList.append(new INVOKESTATIC(iAddMethodref));
        instructionList.append(Constants.DUP);
        instructionList.append(classGenerator.loadTranslet());
        instructionList.append(Constants.SWAP);
        instructionList.append(new PUTFIELD(numberFieldIndexes[this._level]));
        BranchHandle branchHandleAppend2 = instructionList.append((BranchInstruction) new GOTO(null));
        branchHandleAppend.setTarget(instructionList.append(classGenerator.loadTranslet()));
        instructionList.append(new GETFIELD(numberFieldIndexes[this._level]));
        branchHandleAppend2.setTarget(instructionList.append(Constants.NOP));
    }

    private void compileLocals(NodeCounterGenerator nodeCounterGenerator, MatchGenerator matchGenerator, InstructionList instructionList) {
        ConstantPoolGen constantPool = nodeCounterGenerator.getConstantPool();
        LocalVariableGen localVariableGenAddLocalVariable = matchGenerator.addLocalVariable(Constants.ITERATOR_PNAME, Util.getJCRefType("Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;"), null, null);
        int iAddFieldref = constantPool.addFieldref(Constants.NODE_COUNTER, "_iterator", "Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;");
        LocalVariableInstruction localVariableInstruction = Constants.ALOAD_0;
        instructionList.append(localVariableInstruction);
        instructionList.append(new GETFIELD(iAddFieldref));
        localVariableGenAddLocalVariable.setStart(instructionList.append(new ASTORE(localVariableGenAddLocalVariable.getIndex())));
        matchGenerator.setIteratorIndex(localVariableGenAddLocalVariable.getIndex());
        LocalVariableGen localVariableGenAddLocalVariable2 = matchGenerator.addLocalVariable("translet", Util.getJCRefType("Lcom/sun/org/apache/xalan/internal/xsltc/runtime/AbstractTranslet;"), null, null);
        int iAddFieldref2 = constantPool.addFieldref(Constants.NODE_COUNTER, "_translet", Constants.TRANSLET_INTF_SIG);
        instructionList.append(localVariableInstruction);
        instructionList.append(new GETFIELD(iAddFieldref2));
        instructionList.append(new CHECKCAST(constantPool.addClass(Constants.TRANSLET_CLASS)));
        localVariableGenAddLocalVariable2.setStart(instructionList.append(new ASTORE(localVariableGenAddLocalVariable2.getIndex())));
        nodeCounterGenerator.setTransletIndex(localVariableGenAddLocalVariable2.getIndex());
        LocalVariableGen localVariableGenAddLocalVariable3 = matchGenerator.addLocalVariable(Constants.DOCUMENT_PNAME, Util.getJCRefType(Constants.DOM_INTF_SIG), null, null);
        int iAddFieldref3 = constantPool.addFieldref(this._className, "_document", Constants.DOM_INTF_SIG);
        instructionList.append(localVariableInstruction);
        instructionList.append(new GETFIELD(iAddFieldref3));
        localVariableGenAddLocalVariable3.setStart(instructionList.append(new ASTORE(localVariableGenAddLocalVariable3.getIndex())));
        matchGenerator.setDomIndex(localVariableGenAddLocalVariable3.getIndex());
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private void compilePatterns(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        this._className = getXSLTC().getHelperClassName();
        NodeCounterGenerator nodeCounterGenerator = new NodeCounterGenerator(this._className, ClassNames[this._level], Number.class.getName(), 33, null, classGenerator.getStylesheet());
        ConstantPoolGen constantPool = nodeCounterGenerator.getConstantPool();
        List<VariableRefBase> list = this._closureVars;
        int size = list == null ? 0 : list.size();
        for (int i = 0; i < size; i++) {
            VariableBase variable = this._closureVars.get(i).getVariable();
            nodeCounterGenerator.addField(new Field(1, constantPool.addUtf8(variable.getEscapedName()), constantPool.addUtf8(variable.getType().toSignature()), null, constantPool.getConstantPool()));
        }
        compileConstructor(nodeCounterGenerator);
        if (this._from != null) {
            InstructionList instructionList = new InstructionList();
            MatchGenerator matchGenerator = new MatchGenerator(17, Type.BOOLEAN, new Type[]{Type.INT}, new String[]{"node"}, "matchesFrom", this._className, instructionList, constantPool);
            compileLocals(nodeCounterGenerator, matchGenerator, instructionList);
            instructionList.append(matchGenerator.loadContextNode());
            this._from.translate(nodeCounterGenerator, matchGenerator);
            this._from.synthesize(nodeCounterGenerator, matchGenerator);
            instructionList.append(Constants.IRETURN);
            nodeCounterGenerator.addMethod(matchGenerator);
        }
        if (this._count != null) {
            InstructionList instructionList2 = new InstructionList();
            MatchGenerator matchGenerator2 = new MatchGenerator(17, Type.BOOLEAN, new Type[]{Type.INT}, new String[]{"node"}, "matchesCount", this._className, instructionList2, constantPool);
            compileLocals(nodeCounterGenerator, matchGenerator2, instructionList2);
            instructionList2.append(matchGenerator2.loadContextNode());
            this._count.translate(nodeCounterGenerator, matchGenerator2);
            this._count.synthesize(nodeCounterGenerator, matchGenerator2);
            instructionList2.append(Constants.IRETURN);
            nodeCounterGenerator.addMethod(matchGenerator2);
        }
        getXSLTC().dumpClass(nodeCounterGenerator.getJavaClass());
        ConstantPoolGen constantPool2 = classGenerator.getConstantPool();
        InstructionList instructionList3 = methodGenerator.getInstructionList();
        int iAddMethodref = constantPool2.addMethodref(this._className, Const.CONSTRUCTOR_NAME, "(Lcom/sun/org/apache/xalan/internal/xsltc/Translet;Lcom/sun/org/apache/xalan/internal/xsltc/DOM;Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;Z)V");
        instructionList3.append(new NEW(constantPool2.addClass(this._className)));
        instructionList3.append(Constants.DUP);
        instructionList3.append(classGenerator.loadTranslet());
        instructionList3.append(methodGenerator.loadDOM());
        instructionList3.append(methodGenerator.loadIterator());
        instructionList3.append(this._from != null ? Constants.ICONST_1 : Constants.ICONST_0);
        instructionList3.append(new INVOKESPECIAL(iAddMethodref));
        for (int i2 = 0; i2 < size; i2++) {
            VariableBase variable2 = this._closureVars.get(i2).getVariable();
            com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type type = variable2.getType();
            instructionList3.append(Constants.DUP);
            instructionList3.append(variable2.loadInstruction());
            instructionList3.append(new PUTFIELD(constantPool2.addFieldref(this._className, variable2.getEscapedName(), type.toSignature())));
        }
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
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Closure
    public String getInnerClassName() {
        return this._className;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Closure
    public Closure getParentClosure() {
        return null;
    }

    public boolean hasValue() {
        return this._value != null;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Closure
    public boolean inInnerClass() {
        return this._className != null;
    }

    public boolean isDefault() {
        return this._from == null && this._count == null;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void parseContents(Parser parser) {
        int length = this._attributes.getLength();
        for (int i = 0; i < length; i++) {
            String qName = this._attributes.getQName(i);
            String value = this._attributes.getValue(i);
            if (qName.equals("value")) {
                this._value = parser.parseExpression(this, qName, null);
            } else if (qName.equals("count")) {
                this._count = parser.parsePattern(this, qName, null);
            } else if (qName.equals(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_FROM)) {
                this._from = parser.parsePattern(this, qName, null);
            } else if (qName.equals(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_LEVEL)) {
                if (value.equals(com.sun.org.apache.xalan.internal.templates.Constants.ATTRVAL_SINGLE)) {
                    this._level = 0;
                } else if (value.equals(com.sun.org.apache.xalan.internal.templates.Constants.ATTRVAL_MULTI)) {
                    this._level = 1;
                } else if (value.equals("any")) {
                    this._level = 2;
                }
            } else if (qName.equals(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_FORMAT)) {
                this._format = new AttributeValueTemplate(value, parser, this);
                this._formatNeeded = true;
            } else if (qName.equals("lang")) {
                this._lang = new AttributeValueTemplate(value, parser, this);
                this._formatNeeded = true;
            } else if (qName.equals(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_LETTERVALUE)) {
                this._letterValue = new AttributeValueTemplate(value, parser, this);
                this._formatNeeded = true;
            } else if (qName.equals(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_GROUPINGSEPARATOR)) {
                this._groupingSeparator = new AttributeValueTemplate(value, parser, this);
                this._formatNeeded = true;
            } else if (qName.equals(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_GROUPINGSIZE)) {
                this._groupingSize = new AttributeValueTemplate(value, parser, this);
                this._formatNeeded = true;
            }
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        instructionList.append(classGenerator.loadTranslet());
        if (hasValue()) {
            compileDefault(classGenerator, methodGenerator);
            this._value.translate(classGenerator, methodGenerator);
            instructionList.append(new PUSH(constantPool, 0.5d));
            instructionList.append(Constants.DADD);
            instructionList.append(new INVOKESTATIC(constantPool.addMethodref(Constants.MATH_CLASS, Keywords.FUNC_FLOOR_STRING, "(D)D")));
            instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(Constants.NODE_COUNTER, "setValue", "(D)Lcom/sun/org/apache/xalan/internal/xsltc/dom/NodeCounter;")));
        } else if (isDefault()) {
            compileDefault(classGenerator, methodGenerator);
        } else {
            compilePatterns(classGenerator, methodGenerator);
        }
        if (!hasValue()) {
            instructionList.append(methodGenerator.loadContextNode());
            instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(Constants.NODE_COUNTER, Constants.SET_START_NODE, "(I)Lcom/sun/org/apache/xalan/internal/xsltc/dom/NodeCounter;")));
        }
        if (this._formatNeeded) {
            AttributeValueTemplate attributeValueTemplate = this._format;
            if (attributeValueTemplate != null) {
                attributeValueTemplate.translate(classGenerator, methodGenerator);
            } else {
                instructionList.append(new PUSH(constantPool, "1"));
            }
            AttributeValueTemplate attributeValueTemplate2 = this._lang;
            if (attributeValueTemplate2 != null) {
                attributeValueTemplate2.translate(classGenerator, methodGenerator);
            } else {
                instructionList.append(new PUSH(constantPool, "en"));
            }
            AttributeValueTemplate attributeValueTemplate3 = this._letterValue;
            if (attributeValueTemplate3 != null) {
                attributeValueTemplate3.translate(classGenerator, methodGenerator);
            } else {
                instructionList.append(new PUSH(constantPool, ""));
            }
            AttributeValueTemplate attributeValueTemplate4 = this._groupingSeparator;
            if (attributeValueTemplate4 != null) {
                attributeValueTemplate4.translate(classGenerator, methodGenerator);
            } else {
                instructionList.append(new PUSH(constantPool, ""));
            }
            AttributeValueTemplate attributeValueTemplate5 = this._groupingSize;
            if (attributeValueTemplate5 != null) {
                attributeValueTemplate5.translate(classGenerator, methodGenerator);
            } else {
                instructionList.append(new PUSH(constantPool, "0"));
            }
            instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(Constants.NODE_COUNTER, "getCounter", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;")));
        } else {
            instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(Constants.NODE_COUNTER, "setDefaultFormatting", "()Lcom/sun/org/apache/xalan/internal/xsltc/dom/NodeCounter;")));
            instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(Constants.NODE_COUNTER, "getCounter", "()Ljava/lang/String;")));
        }
        instructionList.append(methodGenerator.loadHandler());
        instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(Constants.TRANSLET_CLASS, "characters", Constants.CHARACTERSW_SIG)));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        Expression expression = this._value;
        if (expression != null && !(expression.typeCheck(symbolTable) instanceof RealType)) {
            this._value = new CastExpr(this._value, com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Real);
        }
        Pattern pattern = this._count;
        if (pattern != null) {
            pattern.typeCheck(symbolTable);
        }
        Pattern pattern2 = this._from;
        if (pattern2 != null) {
            pattern2.typeCheck(symbolTable);
        }
        AttributeValueTemplate attributeValueTemplate = this._format;
        if (attributeValueTemplate != null) {
            attributeValueTemplate.typeCheck(symbolTable);
        }
        AttributeValueTemplate attributeValueTemplate2 = this._lang;
        if (attributeValueTemplate2 != null) {
            attributeValueTemplate2.typeCheck(symbolTable);
        }
        AttributeValueTemplate attributeValueTemplate3 = this._letterValue;
        if (attributeValueTemplate3 != null) {
            attributeValueTemplate3.typeCheck(symbolTable);
        }
        AttributeValueTemplate attributeValueTemplate4 = this._groupingSeparator;
        if (attributeValueTemplate4 != null) {
            attributeValueTemplate4.typeCheck(symbolTable);
        }
        AttributeValueTemplate attributeValueTemplate5 = this._groupingSize;
        if (attributeValueTemplate5 != null) {
            attributeValueTemplate5.typeCheck(symbolTable);
        }
        return com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Void;
    }
}

package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.classfile.Field;
import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.sun.org.apache.bcel.internal.generic.ANEWARRAY;
import com.sun.org.apache.bcel.internal.generic.ASTORE;
import com.sun.org.apache.bcel.internal.generic.BasicType;
import com.sun.org.apache.bcel.internal.generic.BranchInstruction;
import com.sun.org.apache.bcel.internal.generic.CHECKCAST;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import com.sun.org.apache.bcel.internal.generic.ILOAD;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import com.sun.org.apache.bcel.internal.generic.LocalVariableInstruction;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.NOP;
import com.sun.org.apache.bcel.internal.generic.ObjectType;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.bcel.internal.generic.PUTFIELD;
import com.sun.org.apache.bcel.internal.generic.TABLESWITCH;
import com.sun.org.apache.bcel.internal.generic.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.CompareGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.IntType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeSortRecordFactGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeSortRecordGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import com.sun.org.apache.xpath.internal.compiler.Keywords;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class Sort extends Instruction implements Closure {
    private AttributeValue _caseOrder;
    private AttributeValue _dataType;
    private AttributeValue _lang;
    private AttributeValue _order;
    private Expression _select;
    private String _className = null;
    private List<VariableRefBase> _closureVars = null;
    private boolean _needsSortRecordFactory = false;

    private static MethodGenerator compileExtract(List<Sort> list, NodeSortRecordGenerator nodeSortRecordGenerator, ConstantPoolGen constantPoolGen, String str) {
        InstructionHandle instructionHandleAppend;
        InstructionList instructionList = new InstructionList();
        ObjectType objectType = Type.STRING;
        Type jCRefType = Util.getJCRefType(Constants.DOM_INTF_SIG);
        Type jCRefType2 = Util.getJCRefType("Lcom/sun/org/apache/xalan/internal/xsltc/runtime/AbstractTranslet;");
        BasicType basicType = Type.INT;
        CompareGenerator compareGenerator = new CompareGenerator(17, objectType, new Type[]{jCRefType, basicType, basicType, jCRefType2, basicType}, new String[]{Constants.DOM_PNAME, Keywords.FUNC_CURRENT_STRING, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_LEVEL, "translet", Keywords.FUNC_LAST_STRING}, "extractValueFromDOM", str, instructionList, constantPoolGen);
        int size = list.size();
        int[] iArr = new int[size];
        InstructionHandle[] instructionHandleArr = new InstructionHandle[size];
        if (size > 1) {
            instructionList.append(new ILOAD(compareGenerator.getLocalIndex(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_LEVEL)));
            instructionHandleAppend = instructionList.append(new NOP());
        } else {
            instructionHandleAppend = null;
        }
        for (int i = 0; i < size; i++) {
            iArr[i] = i;
            Sort sort = list.get(i);
            instructionHandleArr[i] = instructionList.append(Constants.NOP);
            sort.translateSelect(nodeSortRecordGenerator, compareGenerator);
            instructionList.append(Constants.ARETURN);
        }
        if (size > 1) {
            instructionList.insert(instructionHandleAppend, (BranchInstruction) new TABLESWITCH(iArr, instructionHandleArr, instructionList.append(new PUSH(constantPoolGen, ""))));
            instructionList.append(Constants.ARETURN);
        }
        return compareGenerator;
    }

    private static MethodGenerator compileInit(NodeSortRecordGenerator nodeSortRecordGenerator, ConstantPoolGen constantPoolGen, String str) {
        InstructionList instructionList = new InstructionList();
        MethodGenerator methodGenerator = new MethodGenerator(1, Type.VOID, null, null, Const.CONSTRUCTOR_NAME, str, instructionList, constantPoolGen);
        instructionList.append(Constants.ALOAD_0);
        instructionList.append(new INVOKESPECIAL(constantPoolGen.addMethodref(Constants.NODE_SORT_RECORD, Const.CONSTRUCTOR_NAME, "()V")));
        instructionList.append(Constants.RETURN);
        return methodGenerator;
    }

    private static String compileSortRecord(List<Sort> list, ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        XSLTC xsltc = list.get(0).getXSLTC();
        String helperClassName = xsltc.getHelperClassName();
        NodeSortRecordGenerator nodeSortRecordGenerator = new NodeSortRecordGenerator(helperClassName, Constants.NODE_SORT_RECORD, "sort$0.java", 49, new String[0], classGenerator.getStylesheet());
        ConstantPoolGen constantPool = nodeSortRecordGenerator.getConstantPool();
        int size = list.size();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < size; i++) {
            Sort sort = list.get(i);
            sort.setInnerClassName(helperClassName);
            List<VariableRefBase> list2 = sort._closureVars;
            int size2 = list2 == null ? 0 : list2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                VariableRefBase variableRefBase = sort._closureVars.get(i2);
                if (!arrayList.contains(variableRefBase)) {
                    VariableBase variable = variableRefBase.getVariable();
                    nodeSortRecordGenerator.addField(new Field(1, constantPool.addUtf8(variable.getEscapedName()), constantPool.addUtf8(variable.getType().toSignature()), null, constantPool.getConstantPool()));
                    arrayList.add(variableRefBase);
                }
            }
        }
        MethodGenerator methodGeneratorCompileInit = compileInit(nodeSortRecordGenerator, constantPool, helperClassName);
        MethodGenerator methodGeneratorCompileExtract = compileExtract(list, nodeSortRecordGenerator, constantPool, helperClassName);
        nodeSortRecordGenerator.addMethod(methodGeneratorCompileInit);
        nodeSortRecordGenerator.addMethod(methodGeneratorCompileExtract);
        xsltc.dumpClass(nodeSortRecordGenerator.getJavaClass());
        return helperClassName;
    }

    public static void compileSortRecordFactory(List<Sort> list, ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        String strCompileSortRecord = compileSortRecord(list, classGenerator, methodGenerator);
        int size = list.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            z |= list.get(i)._needsSortRecordFactory;
        }
        String strCompileSortRecordFactory = z ? compileSortRecordFactory(list, classGenerator, methodGenerator, strCompileSortRecord) : Constants.NODE_SORT_FACTORY;
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        LocalVariableGen localVariableGenAddLocalVariable = methodGenerator.addLocalVariable("sort_order_tmp", Util.getJCRefType("[Ljava/lang/String;"), null, null);
        instructionList.append(new PUSH(constantPool, size));
        instructionList.append(new ANEWARRAY(constantPool.addClass("java.lang.String")));
        for (int i2 = 0; i2 < size; i2++) {
            Sort sort = list.get(i2);
            instructionList.append(Constants.DUP);
            instructionList.append(new PUSH(constantPool, i2));
            sort.translateSortOrder(classGenerator, methodGenerator);
            instructionList.append(Constants.AASTORE);
        }
        localVariableGenAddLocalVariable.setStart(instructionList.append(new ASTORE(localVariableGenAddLocalVariable.getIndex())));
        LocalVariableGen localVariableGenAddLocalVariable2 = methodGenerator.addLocalVariable("sort_type_tmp", Util.getJCRefType("[Ljava/lang/String;"), null, null);
        instructionList.append(new PUSH(constantPool, size));
        instructionList.append(new ANEWARRAY(constantPool.addClass("java.lang.String")));
        for (int i3 = 0; i3 < size; i3++) {
            Sort sort2 = list.get(i3);
            instructionList.append(Constants.DUP);
            instructionList.append(new PUSH(constantPool, i3));
            sort2.translateSortType(classGenerator, methodGenerator);
            instructionList.append(Constants.AASTORE);
        }
        localVariableGenAddLocalVariable2.setStart(instructionList.append(new ASTORE(localVariableGenAddLocalVariable2.getIndex())));
        LocalVariableGen localVariableGenAddLocalVariable3 = methodGenerator.addLocalVariable("sort_lang_tmp", Util.getJCRefType("[Ljava/lang/String;"), null, null);
        instructionList.append(new PUSH(constantPool, size));
        instructionList.append(new ANEWARRAY(constantPool.addClass("java.lang.String")));
        for (int i4 = 0; i4 < size; i4++) {
            Sort sort3 = list.get(i4);
            instructionList.append(Constants.DUP);
            instructionList.append(new PUSH(constantPool, i4));
            sort3.translateLang(classGenerator, methodGenerator);
            instructionList.append(Constants.AASTORE);
        }
        localVariableGenAddLocalVariable3.setStart(instructionList.append(new ASTORE(localVariableGenAddLocalVariable3.getIndex())));
        LocalVariableGen localVariableGenAddLocalVariable4 = methodGenerator.addLocalVariable("sort_case_order_tmp", Util.getJCRefType("[Ljava/lang/String;"), null, null);
        instructionList.append(new PUSH(constantPool, size));
        instructionList.append(new ANEWARRAY(constantPool.addClass("java.lang.String")));
        for (int i5 = 0; i5 < size; i5++) {
            Sort sort4 = list.get(i5);
            instructionList.append(Constants.DUP);
            instructionList.append(new PUSH(constantPool, i5));
            sort4.translateCaseOrder(classGenerator, methodGenerator);
            instructionList.append(Constants.AASTORE);
        }
        localVariableGenAddLocalVariable4.setStart(instructionList.append(new ASTORE(localVariableGenAddLocalVariable4.getIndex())));
        instructionList.append(new NEW(constantPool.addClass(strCompileSortRecordFactory)));
        instructionList.append(Constants.DUP);
        instructionList.append(methodGenerator.loadDOM());
        instructionList.append(new PUSH(constantPool, strCompileSortRecord));
        instructionList.append(classGenerator.loadTranslet());
        localVariableGenAddLocalVariable.setEnd(instructionList.append(new ALOAD(localVariableGenAddLocalVariable.getIndex())));
        localVariableGenAddLocalVariable2.setEnd(instructionList.append(new ALOAD(localVariableGenAddLocalVariable2.getIndex())));
        localVariableGenAddLocalVariable3.setEnd(instructionList.append(new ALOAD(localVariableGenAddLocalVariable3.getIndex())));
        localVariableGenAddLocalVariable4.setEnd(instructionList.append(new ALOAD(localVariableGenAddLocalVariable4.getIndex())));
        instructionList.append(new INVOKESPECIAL(constantPool.addMethodref(strCompileSortRecordFactory, Const.CONSTRUCTOR_NAME, "(Lcom/sun/org/apache/xalan/internal/xsltc/DOM;Ljava/lang/String;Lcom/sun/org/apache/xalan/internal/xsltc/Translet;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;)V")));
        ArrayList arrayList = new ArrayList();
        for (int i6 = 0; i6 < size; i6++) {
            Sort sort5 = list.get(i6);
            List<VariableRefBase> list2 = sort5._closureVars;
            int size2 = list2 == null ? 0 : list2.size();
            for (int i7 = 0; i7 < size2; i7++) {
                VariableRefBase variableRefBase = sort5._closureVars.get(i7);
                if (!arrayList.contains(variableRefBase)) {
                    VariableBase variable = variableRefBase.getVariable();
                    instructionList.append(Constants.DUP);
                    instructionList.append(variable.loadInstruction());
                    instructionList.append(new PUTFIELD(constantPool.addFieldref(strCompileSortRecordFactory, variable.getEscapedName(), variable.getType().toSignature())));
                    arrayList.add(variableRefBase);
                }
            }
        }
    }

    private void setInnerClassName(String str) {
        this._className = str;
    }

    public static void translateSortIterator(ClassGenerator classGenerator, MethodGenerator methodGenerator, Expression expression, List<Sort> list) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        int iAddMethodref = constantPool.addMethodref(Constants.SORT_ITERATOR, Const.CONSTRUCTOR_NAME, "(Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;Lcom/sun/org/apache/xalan/internal/xsltc/dom/NodeSortRecordFactory;)V");
        LocalVariableGen localVariableGenAddLocalVariable = methodGenerator.addLocalVariable("sort_tmp1", Util.getJCRefType("Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;"), null, null);
        LocalVariableGen localVariableGenAddLocalVariable2 = methodGenerator.addLocalVariable("sort_tmp2", Util.getJCRefType(Constants.NODE_SORT_FACTORY_SIG), null, null);
        if (expression == null) {
            int iAddInterfaceMethodref = constantPool.addInterfaceMethodref(Constants.DOM_INTF, "getAxisIterator", "(I)Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;");
            instructionList.append(methodGenerator.loadDOM());
            instructionList.append(new PUSH(constantPool, 3));
            instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref, 2));
        } else {
            expression.translate(classGenerator, methodGenerator);
        }
        localVariableGenAddLocalVariable.setStart(instructionList.append(new ASTORE(localVariableGenAddLocalVariable.getIndex())));
        compileSortRecordFactory(list, classGenerator, methodGenerator);
        localVariableGenAddLocalVariable2.setStart(instructionList.append(new ASTORE(localVariableGenAddLocalVariable2.getIndex())));
        instructionList.append(new NEW(constantPool.addClass(Constants.SORT_ITERATOR)));
        instructionList.append(Constants.DUP);
        localVariableGenAddLocalVariable.setEnd(instructionList.append(new ALOAD(localVariableGenAddLocalVariable.getIndex())));
        localVariableGenAddLocalVariable2.setEnd(instructionList.append(new ALOAD(localVariableGenAddLocalVariable2.getIndex())));
        instructionList.append(new INVOKESPECIAL(iAddMethodref));
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
        this._needsSortRecordFactory = true;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Closure
    public String getInnerClassName() {
        return this._className;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Closure
    public Closure getParentClosure() {
        return null;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Closure
    public boolean inInnerClass() {
        return this._className != null;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void parseContents(Parser parser) {
        String str = "text";
        SyntaxTreeNode parent = getParent();
        if (!(parent instanceof ApplyTemplates) && !(parent instanceof ForEach)) {
            reportError(this, parser, ErrorMsg.STRAY_SORT_ERR, null);
            return;
        }
        this._select = parser.parseExpression(this, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_SELECT, "string(.)");
        String attribute = getAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_ORDER);
        if (attribute.length() == 0) {
            attribute = com.sun.org.apache.xalan.internal.templates.Constants.ATTRVAL_ORDER_ASCENDING;
        }
        this._order = AttributeValue.create(this, attribute, parser);
        String attribute2 = getAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_DATATYPE);
        if (attribute2.length() == 0) {
            try {
                if (this._select.typeCheck(parser.getSymbolTable()) instanceof IntType) {
                    str = "number";
                }
            } catch (TypeCheckError unused) {
            }
        } else {
            str = attribute2;
        }
        this._dataType = AttributeValue.create(this, str, parser);
        this._lang = AttributeValue.create(this, getAttribute("lang"), parser);
        this._caseOrder = AttributeValue.create(this, getAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_CASEORDER), parser);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
    }

    public void translateCaseOrder(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        this._caseOrder.translate(classGenerator, methodGenerator);
    }

    public void translateLang(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        this._lang.translate(classGenerator, methodGenerator);
    }

    public void translateSelect(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        this._select.translate(classGenerator, methodGenerator);
    }

    public void translateSortOrder(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        this._order.translate(classGenerator, methodGenerator);
    }

    public void translateSortType(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        this._dataType.translate(classGenerator, methodGenerator);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        if (!(this._select.typeCheck(symbolTable) instanceof StringType)) {
            this._select = new CastExpr(this._select, com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.String);
        }
        this._order.typeCheck(symbolTable);
        this._caseOrder.typeCheck(symbolTable);
        this._dataType.typeCheck(symbolTable);
        this._lang.typeCheck(symbolTable);
        return com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Void;
    }

    public static String compileSortRecordFactory(List<Sort> list, ClassGenerator classGenerator, MethodGenerator methodGenerator, String str) {
        int i = 0;
        XSLTC xsltc = list.get(0).getXSLTC();
        String helperClassName = xsltc.getHelperClassName();
        NodeSortRecordFactGenerator nodeSortRecordFactGenerator = new NodeSortRecordFactGenerator(helperClassName, Constants.NODE_SORT_FACTORY, helperClassName + ".java", 49, new String[0], classGenerator.getStylesheet());
        ConstantPoolGen constantPool = nodeSortRecordFactGenerator.getConstantPool();
        int size = list.size();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < size; i2++) {
            Sort sort = list.get(i2);
            List<VariableRefBase> list2 = sort._closureVars;
            int size2 = list2 == null ? i : list2.size();
            int i3 = i;
            while (i3 < size2) {
                VariableRefBase variableRefBase = sort._closureVars.get(i3);
                if (!arrayList.contains(variableRefBase)) {
                    VariableBase variable = variableRefBase.getVariable();
                    nodeSortRecordFactGenerator.addField(new Field(1, constantPool.addUtf8(variable.getEscapedName()), constantPool.addUtf8(variable.getType().toSignature()), null, constantPool.getConstantPool()));
                    arrayList.add(variableRefBase);
                }
                i3++;
                i = i;
            }
        }
        int i4 = i;
        Type[] typeArr = {Util.getJCRefType(Constants.DOM_INTF_SIG), Util.getJCRefType(Constants.STRING_SIG), Util.getJCRefType(Constants.TRANSLET_INTF_SIG), Util.getJCRefType("[Ljava/lang/String;"), Util.getJCRefType("[Ljava/lang/String;"), Util.getJCRefType("[Ljava/lang/String;"), Util.getJCRefType("[Ljava/lang/String;")};
        String[] strArr = {Constants.DOCUMENT_PNAME, "className", "translet", com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_ORDER, "type", "lang", "case_order"};
        InstructionList instructionList = new InstructionList();
        MethodGenerator methodGenerator2 = new MethodGenerator(1, Type.VOID, typeArr, strArr, Const.CONSTRUCTOR_NAME, helperClassName, instructionList, constantPool);
        LocalVariableInstruction localVariableInstruction = Constants.ALOAD_0;
        instructionList.append(localVariableInstruction);
        instructionList.append(Constants.ALOAD_1);
        instructionList.append(Constants.ALOAD_2);
        instructionList.append(new ALOAD(3));
        instructionList.append(new ALOAD(4));
        instructionList.append(new ALOAD(5));
        instructionList.append(new ALOAD(6));
        instructionList.append(new ALOAD(7));
        instructionList.append(new INVOKESPECIAL(constantPool.addMethodref(Constants.NODE_SORT_FACTORY, Const.CONSTRUCTOR_NAME, "(Lcom/sun/org/apache/xalan/internal/xsltc/DOM;Ljava/lang/String;Lcom/sun/org/apache/xalan/internal/xsltc/Translet;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;)V")));
        instructionList.append(Constants.RETURN);
        InstructionList instructionList2 = new InstructionList();
        Type jCRefType = Util.getJCRefType(Constants.NODE_SORT_RECORD_SIG);
        Type[] typeArr2 = new Type[2];
        BasicType basicType = Type.INT;
        typeArr2[i4] = basicType;
        typeArr2[1] = basicType;
        MethodGenerator methodGenerator3 = new MethodGenerator(1, jCRefType, typeArr2, new String[]{"node", Keywords.FUNC_LAST_STRING}, "makeNodeSortRecord", helperClassName, instructionList2, constantPool);
        instructionList2.append(localVariableInstruction);
        instructionList2.append(Constants.ILOAD_1);
        instructionList2.append(Constants.ILOAD_2);
        instructionList2.append(new INVOKESPECIAL(constantPool.addMethodref(Constants.NODE_SORT_FACTORY, "makeNodeSortRecord", "(II)Lcom/sun/org/apache/xalan/internal/xsltc/dom/NodeSortRecord;")));
        instructionList2.append(Constants.DUP);
        instructionList2.append(new CHECKCAST(constantPool.addClass(str)));
        int size3 = arrayList.size();
        for (int i5 = i4; i5 < size3; i5++) {
            VariableBase variable2 = ((VariableRefBase) arrayList.get(i5)).getVariable();
            com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type type = variable2.getType();
            instructionList2.append(Constants.DUP);
            instructionList2.append(Constants.ALOAD_0);
            instructionList2.append(new GETFIELD(constantPool.addFieldref(helperClassName, variable2.getEscapedName(), type.toSignature())));
            instructionList2.append(new PUTFIELD(constantPool.addFieldref(str, variable2.getEscapedName(), type.toSignature())));
        }
        instructionList2.append(Constants.POP);
        instructionList2.append(Constants.ARETURN);
        methodGenerator2.setMaxLocals();
        methodGenerator2.setMaxStack();
        nodeSortRecordFactGenerator.addMethod(methodGenerator2);
        methodGenerator3.setMaxLocals();
        methodGenerator3.setMaxStack();
        nodeSortRecordFactGenerator.addMethod(methodGenerator3);
        xsltc.dumpClass(nodeSortRecordFactGenerator.getJavaClass());
        return helperClassName;
    }
}

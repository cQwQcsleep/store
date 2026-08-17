package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.sun.org.apache.bcel.internal.generic.BasicType;
import com.sun.org.apache.bcel.internal.generic.BranchHandle;
import com.sun.org.apache.bcel.internal.generic.BranchInstruction;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.DUP;
import com.sun.org.apache.bcel.internal.generic.GOTO_W;
import com.sun.org.apache.bcel.internal.generic.IFLT;
import com.sun.org.apache.bcel.internal.generic.ILOAD;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.ISTORE;
import com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import com.sun.org.apache.bcel.internal.generic.SWITCH;
import com.sun.org.apache.bcel.internal.generic.TargetLostException;
import com.sun.org.apache.bcel.internal.generic.Type;
import com.sun.org.apache.bcel.internal.util.InstructionFinder;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NamedMethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import com.sun.org.apache.xpath.internal.compiler.Keywords;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class Mode implements Constants {
    private int _currentIndex;
    private final String _methodName;
    private final QName _name;
    private final Stylesheet _stylesheet;
    private TestSeq[] _testSeq;
    private List<LocationPathPattern> _childNodeGroup = null;
    private TestSeq _childNodeTestSeq = null;
    private List<LocationPathPattern> _attribNodeGroup = null;
    private TestSeq _attribNodeTestSeq = null;
    private List<LocationPathPattern> _idxGroup = null;
    private TestSeq _idxTestSeq = null;
    private Map<Template, Object> _neededTemplates = new LinkedHashMap();
    private Map<Template, Mode> _namedTemplates = new LinkedHashMap();
    private Map<Template, InstructionHandle> _templateIHs = new HashMap();
    private Map<Template, InstructionList> _templateILs = new HashMap();
    private LocationPathPattern _rootPattern = null;
    private Map<Integer, Integer> _importLevels = null;
    private Map<String, Key> _keys = null;
    private List<Template> _templates = new ArrayList();
    private List<LocationPathPattern>[] _patternGroups = new ArrayList[32];

    public Mode(QName qName, Stylesheet stylesheet, String str) {
        this._name = qName;
        this._stylesheet = stylesheet;
        this._methodName = Constants.APPLY_TEMPLATES + str;
    }

    public static /* synthetic */ void a(Mode mode, int i, int i2, ClassGenerator classGenerator, MethodGenerator methodGenerator, InstructionHandle instructionHandle, Template template) {
        mode.getClass();
        int importPrecedence = template.getImportPrecedence();
        if (importPrecedence < i || importPrecedence >= i2) {
            return;
        }
        if (!template.hasContents()) {
            mode._templateIHs.put(template, instructionHandle);
            return;
        }
        InstructionList instructionListCompile = template.compile(classGenerator, methodGenerator);
        instructionListCompile.append((BranchInstruction) new GOTO_W(instructionHandle));
        mode._templateILs.put(template, instructionListCompile);
        mode._templateIHs.put(template, instructionListCompile.getStart());
    }

    private void addPattern(int i, LocationPathPattern locationPathPattern) {
        List<LocationPathPattern> arrayList;
        List<LocationPathPattern>[] listArr = this._patternGroups;
        int length = listArr.length;
        if (i >= length) {
            ArrayList[] arrayListArr = new ArrayList[i * 2];
            System.arraycopy(listArr, 0, arrayListArr, 0, length);
            this._patternGroups = arrayListArr;
        }
        if (i != -1) {
            List<LocationPathPattern>[] listArr2 = this._patternGroups;
            List<LocationPathPattern> arrayList2 = listArr2[i];
            if (arrayList2 == null) {
                arrayList2 = new ArrayList<>(2);
                listArr2[i] = arrayList2;
            }
            arrayList = arrayList2;
        } else if (locationPathPattern.getAxis() == 2) {
            arrayList = this._attribNodeGroup;
            if (arrayList == null) {
                arrayList = new ArrayList<>(2);
                this._attribNodeGroup = arrayList;
            }
        } else {
            arrayList = this._childNodeGroup;
            if (arrayList == null) {
                arrayList = new ArrayList<>(2);
                this._childNodeGroup = arrayList;
            }
        }
        if (arrayList.size() == 0) {
            arrayList.add(locationPathPattern);
            return;
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (locationPathPattern.noSmallerThan(arrayList.get(i2))) {
                arrayList.add(i2, locationPathPattern);
                return;
            }
        }
        arrayList.add(locationPathPattern);
    }

    private void addPatternToGroup(LocationPathPattern locationPathPattern) {
        if (locationPathPattern instanceof IdKeyPattern) {
            addPattern(-1, locationPathPattern);
            return;
        }
        StepPattern kernelPattern = locationPathPattern.getKernelPattern();
        if (kernelPattern != null) {
            addPattern(kernelPattern.getNodeType(), locationPathPattern);
            return;
        }
        LocationPathPattern locationPathPattern2 = this._rootPattern;
        if (locationPathPattern2 == null || locationPathPattern.noSmallerThan(locationPathPattern2)) {
            this._rootPattern = locationPathPattern;
        }
    }

    private void appendTemplateCode(InstructionList instructionList) {
        Iterator<Template> it = this._neededTemplates.keySet().iterator();
        while (it.hasNext()) {
            InstructionList instructionList2 = this._templateILs.get(it.next());
            if (instructionList2 != null) {
                instructionList.append(instructionList2);
            }
        }
    }

    private void appendTestSequences(InstructionList instructionList) {
        InstructionList instructionList2;
        int length = this._testSeq.length;
        for (int i = 0; i < length; i++) {
            TestSeq testSeq = this._testSeq[i];
            if (testSeq != null && (instructionList2 = testSeq.getInstructionList()) != null) {
                instructionList.append(instructionList2);
            }
        }
    }

    private InstructionList compileDefaultRecursion(ClassGenerator classGenerator, MethodGenerator methodGenerator, InstructionHandle instructionHandle) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = new InstructionList();
        String applyTemplatesSig = classGenerator.getApplyTemplatesSig();
        int iAddInterfaceMethodref = constantPool.addInterfaceMethodref(Constants.DOM_INTF, Constants.GET_CHILDREN, "(I)Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;");
        int iAddMethodref = constantPool.addMethodref(getClassName(), functionName(), applyTemplatesSig);
        instructionList.append(classGenerator.loadTranslet());
        instructionList.append(methodGenerator.loadDOM());
        instructionList.append(methodGenerator.loadDOM());
        instructionList.append(new ILOAD(this._currentIndex));
        instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref, 2));
        instructionList.append(methodGenerator.loadHandler());
        instructionList.append(new INVOKEVIRTUAL(iAddMethodref));
        instructionList.append((BranchInstruction) new GOTO_W(instructionHandle));
        return instructionList;
    }

    private InstructionList compileDefaultText(ClassGenerator classGenerator, MethodGenerator methodGenerator, InstructionHandle instructionHandle) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = new InstructionList();
        int iAddInterfaceMethodref = constantPool.addInterfaceMethodref(Constants.DOM_INTF, "characters", Constants.CHARACTERS_SIG);
        instructionList.append(methodGenerator.loadDOM());
        instructionList.append(new ILOAD(this._currentIndex));
        instructionList.append(methodGenerator.loadHandler());
        instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref, 3));
        instructionList.append((BranchInstruction) new GOTO_W(instructionHandle));
        return instructionList;
    }

    public static void compileGetChildren(ClassGenerator classGenerator, MethodGenerator methodGenerator, int i) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        int iAddInterfaceMethodref = constantPool.addInterfaceMethodref(Constants.DOM_INTF, Constants.GET_CHILDREN, "(I)Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;");
        instructionList.append(methodGenerator.loadDOM());
        instructionList.append(new ILOAD(i));
        instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref, 2));
    }

    private void compileNamedTemplate(Template template, ClassGenerator classGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = new InstructionList();
        String strEscape = Util.escape(template.getName().toString());
        int size = (template.isSimpleNamedTemplate() ? template.getParameters().size() : 0) + 4;
        Type[] typeArr = new Type[size];
        String[] strArr = new String[size];
        typeArr[0] = Util.getJCRefType(Constants.DOM_INTF_SIG);
        typeArr[1] = Util.getJCRefType("Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;");
        typeArr[2] = Util.getJCRefType("Lcom/sun/org/apache/xml/internal/serializer/SerializationHandler;");
        typeArr[3] = Type.INT;
        strArr[0] = Constants.DOCUMENT_PNAME;
        strArr[1] = Constants.ITERATOR_PNAME;
        strArr[2] = Constants.TRANSLET_OUTPUT_PNAME;
        strArr[3] = "node";
        for (int i = 4; i < size; i++) {
            typeArr[i] = Util.getJCRefType(Constants.OBJECT_SIG);
            strArr[i] = com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_PARAMVARIABLE_STRING + String.valueOf(i - 4);
        }
        NamedMethodGenerator namedMethodGenerator = new NamedMethodGenerator(1, Type.VOID, typeArr, strArr, strEscape, getClassName(), instructionList, constantPool);
        instructionList.append(template.compile(classGenerator, namedMethodGenerator));
        instructionList.append(Constants.RETURN);
        classGenerator.addMethod(namedMethodGenerator);
    }

    private InstructionList compileNamespaces(ClassGenerator classGenerator, MethodGenerator methodGenerator, boolean[] zArr, boolean[] zArr2, boolean z, InstructionHandle instructionHandle) {
        TestSeq testSeq;
        XSLTC xsltc = classGenerator.getParser().getXSLTC();
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        List<String> namespaceIndex = xsltc.getNamespaceIndex();
        List<String> namesIndex = xsltc.getNamesIndex();
        int size = namespaceIndex.size() + 1;
        int size2 = namesIndex.size();
        InstructionList instructionList = new InstructionList();
        int[] iArr = new int[size];
        InstructionHandle[] instructionHandleArr = new InstructionHandle[size];
        if (size <= 0) {
            return null;
        }
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            instructionHandleArr[i2] = instructionHandle;
            iArr[i2] = i2;
        }
        int i3 = 14;
        boolean z2 = false;
        while (i3 < size2 + 14) {
            if (zArr[i3] && zArr2[i3] == z) {
                String str = namesIndex.get(i3 - 14);
                int iRegisterNamespace = xsltc.registerNamespace(str.substring(i, str.lastIndexOf(58)));
                TestSeq[] testSeqArr = this._testSeq;
                if (i3 < testSeqArr.length && (testSeq = testSeqArr[i3]) != null) {
                    instructionHandleArr[iRegisterNamespace] = testSeq.compile(classGenerator, methodGenerator, instructionHandle);
                    z2 = true;
                }
            }
            i3++;
            i = 0;
        }
        if (!z2) {
            return null;
        }
        int iAddInterfaceMethodref = constantPool.addInterfaceMethodref(Constants.DOM_INTF, "getNamespaceType", Constants.GET_PARENT_SIG);
        instructionList.append(methodGenerator.loadDOM());
        instructionList.append(new ILOAD(this._currentIndex));
        instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref, 2));
        instructionList.append(new SWITCH(iArr, instructionHandleArr, instructionHandle));
        return instructionList;
    }

    private void compileTemplateCalls(final ClassGenerator classGenerator, final MethodGenerator methodGenerator, final InstructionHandle instructionHandle, final int i, final int i2) {
        this._neededTemplates.keySet().stream().forEach(new Consumer() { // from class: com.sun.org.apache.xalan.internal.xsltc.compiler.b
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Mode.a(this.b, i, i2, classGenerator, methodGenerator, instructionHandle, (Template) obj);
            }
        });
    }

    private void compileTemplates(ClassGenerator classGenerator, MethodGenerator methodGenerator, InstructionHandle instructionHandle) {
        Iterator<Template> it = this._namedTemplates.keySet().iterator();
        while (it.hasNext()) {
            compileNamedTemplate(it.next(), classGenerator);
        }
        for (Template template : this._neededTemplates.keySet()) {
            if (template.hasContents()) {
                InstructionList instructionListCompile = template.compile(classGenerator, methodGenerator);
                instructionListCompile.append((BranchInstruction) new GOTO_W(instructionHandle));
                this._templateILs.put(template, instructionListCompile);
                this._templateIHs.put(template, instructionListCompile.getStart());
            } else {
                this._templateIHs.put(template, instructionHandle);
            }
        }
    }

    private void completeTestSequences(int i, List<LocationPathPattern> list) {
        if (list != null) {
            List<LocationPathPattern>[] listArr = this._patternGroups;
            if (listArr[i] == null) {
                listArr[i] = list;
                return;
            }
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                addPattern(i, list.get(i2));
            }
        }
    }

    private void flattenAlternative(Pattern pattern, Template template, Map<String, Key> map) {
        if (pattern instanceof IdKeyPattern) {
            IdKeyPattern idKeyPattern = (IdKeyPattern) pattern;
            idKeyPattern.setTemplate(template);
            if (this._idxGroup == null) {
                this._idxGroup = new ArrayList();
            }
            this._idxGroup.add(idKeyPattern);
            return;
        }
        if (pattern instanceof AlternativePattern) {
            AlternativePattern alternativePattern = (AlternativePattern) pattern;
            flattenAlternative(alternativePattern.getLeft(), template, map);
            flattenAlternative(alternativePattern.getRight(), template, map);
        } else if (pattern instanceof LocationPathPattern) {
            LocationPathPattern locationPathPattern = (LocationPathPattern) pattern;
            locationPathPattern.setTemplate(template);
            addPatternToGroup(locationPathPattern);
        }
    }

    private String getClassName() {
        return this._stylesheet.getClassName();
    }

    private static boolean isAttributeName(String str) {
        return str.charAt(str.lastIndexOf(58) + 1) == '@';
    }

    private static boolean isNamespaceName(String str) {
        return str.lastIndexOf(58) > -1 && str.charAt(str.length() - 1) == '*';
    }

    private int partition(List<Template> list, int i, int i2) {
        Template template = list.get(i);
        int i3 = i - 1;
        int i4 = i2 + 1;
        while (true) {
            i4--;
            if (template.compareTo(list.get(i4)) <= 0) {
                do {
                    i3++;
                } while (template.compareTo(list.get(i3)) < 0);
                if (i3 >= i4) {
                    return i4;
                }
                list.set(i4, list.set(i3, list.get(i4)));
            }
        }
    }

    private void peepHoleOptimization(MethodGenerator methodGenerator) {
        InstructionList instructionList = methodGenerator.getInstructionList();
        InstructionFinder instructionFinder = new InstructionFinder(instructionList);
        Iterator<InstructionHandle[]> itSearch = instructionFinder.search("loadinstruction pop");
        while (itSearch.hasNext()) {
            InstructionHandle[] next = itSearch.next();
            try {
                if (!next[0].hasTargeters() && !next[1].hasTargeters()) {
                    instructionList.delete(next[0], next[1]);
                }
            } catch (TargetLostException unused) {
            }
        }
        Iterator<InstructionHandle[]> itSearch2 = instructionFinder.search("iload iload swap istore");
        while (itSearch2.hasNext()) {
            InstructionHandle[] next2 = itSearch2.next();
            try {
                ILOAD iload = (ILOAD) next2[0].getInstruction();
                ILOAD iload2 = (ILOAD) next2[1].getInstruction();
                ISTORE istore = (ISTORE) next2[3].getInstruction();
                if (!next2[1].hasTargeters() && !next2[2].hasTargeters() && !next2[3].hasTargeters() && iload.getIndex() == iload2.getIndex() && iload2.getIndex() == istore.getIndex()) {
                    instructionList.delete(next2[1], next2[3]);
                }
            } catch (TargetLostException unused2) {
            }
        }
        Iterator<InstructionHandle[]> itSearch3 = instructionFinder.search("loadinstruction loadinstruction swap");
        while (itSearch3.hasNext()) {
            InstructionHandle[] next3 = itSearch3.next();
            try {
                if (!next3[0].hasTargeters() && !next3[1].hasTargeters() && !next3[2].hasTargeters()) {
                    instructionList.insert(next3[0], next3[1].getInstruction());
                    instructionList.delete(next3[1], next3[2]);
                }
            } catch (TargetLostException unused3) {
            }
        }
        Iterator<InstructionHandle[]> itSearch4 = instructionFinder.search("aload aload");
        while (itSearch4.hasNext()) {
            InstructionHandle[] next4 = itSearch4.next();
            try {
                if (!next4[1].hasTargeters()) {
                    if (((ALOAD) next4[0].getInstruction()).getIndex() == ((ALOAD) next4[1].getInstruction()).getIndex()) {
                        instructionList.insert(next4[1], new DUP());
                        instructionList.delete(next4[1]);
                    }
                }
            } catch (TargetLostException unused4) {
            }
        }
    }

    private void prepareTestSequences() {
        List<LocationPathPattern>[] listArr = this._patternGroups;
        List<LocationPathPattern> list = listArr[1];
        List<LocationPathPattern> list2 = listArr[2];
        completeTestSequences(3, this._childNodeGroup);
        completeTestSequences(1, this._childNodeGroup);
        completeTestSequences(7, this._childNodeGroup);
        completeTestSequences(8, this._childNodeGroup);
        completeTestSequences(2, this._attribNodeGroup);
        List<String> namesIndex = this._stylesheet.getXSLTC().getNamesIndex();
        if (list != null || list2 != null || this._childNodeGroup != null || this._attribNodeGroup != null) {
            int length = this._patternGroups.length;
            for (int i = 14; i < length; i++) {
                if (this._patternGroups[i] != null) {
                    if (isAttributeName(namesIndex.get(i - 14))) {
                        completeTestSequences(i, list2);
                        completeTestSequences(i, this._attribNodeGroup);
                    } else {
                        completeTestSequences(i, list);
                        completeTestSequences(i, this._childNodeGroup);
                    }
                }
            }
        }
        this._testSeq = new TestSeq[namesIndex.size() + 14];
        int length2 = this._patternGroups.length;
        for (int i2 = 0; i2 < length2; i2++) {
            List<LocationPathPattern> list3 = this._patternGroups[i2];
            if (list3 != null) {
                TestSeq testSeq = new TestSeq(list3, i2, this);
                testSeq.reduce();
                this._testSeq[i2] = testSeq;
                testSeq.findTemplates(this._neededTemplates);
            }
        }
        List<LocationPathPattern> list4 = this._childNodeGroup;
        if (list4 != null && list4.size() > 0) {
            TestSeq testSeq2 = new TestSeq(this._childNodeGroup, -1, this);
            this._childNodeTestSeq = testSeq2;
            testSeq2.reduce();
            this._childNodeTestSeq.findTemplates(this._neededTemplates);
        }
        List<LocationPathPattern> list5 = this._idxGroup;
        if (list5 != null && list5.size() > 0) {
            TestSeq testSeq3 = new TestSeq(this._idxGroup, this);
            this._idxTestSeq = testSeq3;
            testSeq3.reduce();
            this._idxTestSeq.findTemplates(this._neededTemplates);
        }
        LocationPathPattern locationPathPattern = this._rootPattern;
        if (locationPathPattern != null) {
            this._neededTemplates.put(locationPathPattern.getTemplate(), this);
        }
    }

    private List<Template> quicksort(List<Template> list, int i, int i2) {
        if (i < i2) {
            int iPartition = partition(list, i, i2);
            quicksort(list, i, iPartition);
            quicksort(list, iPartition + 1, i2);
        }
        return list;
    }

    public void addTemplate(Template template) {
        this._templates.add(template);
    }

    public void compileApplyImports(ClassGenerator classGenerator, int i, int i2) {
        int position;
        double priority;
        XSLTC xsltc = classGenerator.getParser().getXSLTC();
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        List<String> namesIndex = xsltc.getNamesIndex();
        this._namedTemplates = new LinkedHashMap();
        this._neededTemplates = new LinkedHashMap();
        this._templateIHs = new HashMap();
        this._templateILs = new HashMap();
        this._patternGroups = new ArrayList[32];
        this._rootPattern = null;
        List<Template> list = this._templates;
        this._templates = new ArrayList();
        for (Template template : list) {
            int importPrecedence = template.getImportPrecedence();
            if (importPrecedence >= i && importPrecedence < i2) {
                addTemplate(template);
            }
        }
        processPatterns(this._keys);
        Type jCRefType = Util.getJCRefType(Constants.DOM_INTF_SIG);
        Type jCRefType2 = Util.getJCRefType("Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;");
        Type jCRefType3 = Util.getJCRefType("Lcom/sun/org/apache/xml/internal/serializer/SerializationHandler;");
        BasicType basicType = Type.INT;
        String[] strArr = {Constants.DOCUMENT_PNAME, Constants.ITERATOR_PNAME, Constants.TRANSLET_OUTPUT_PNAME, "node"};
        InstructionList instructionList = new InstructionList();
        BasicType basicType2 = Type.VOID;
        MethodGenerator methodGenerator = new MethodGenerator(17, basicType2, new Type[]{jCRefType, jCRefType2, jCRefType3, basicType}, strArr, functionName() + '_' + i2, getClassName(), instructionList, classGenerator.getConstantPool());
        methodGenerator.addException("com.sun.org.apache.xalan.internal.xsltc.TransletException");
        LocalVariableGen localVariableGenAddLocalVariable2 = methodGenerator.addLocalVariable2(Keywords.FUNC_CURRENT_STRING, basicType, null);
        this._currentIndex = localVariableGenAddLocalVariable2.getIndex();
        instructionList.append(new ILOAD(methodGenerator.getLocalIndex("node")));
        localVariableGenAddLocalVariable2.setStart(instructionList.append(new ISTORE(this._currentIndex)));
        InstructionList instructionList2 = new InstructionList();
        instructionList2.append(Constants.NOP);
        InstructionList instructionList3 = new InstructionList();
        instructionList3.append(Constants.RETURN);
        InstructionHandle start = instructionList3.getStart();
        InstructionList instructionListCompileDefaultRecursion = compileDefaultRecursion(classGenerator, methodGenerator, start);
        InstructionHandle start2 = instructionListCompileDefaultRecursion.getStart();
        InstructionList instructionListCompileDefaultText = compileDefaultText(classGenerator, methodGenerator, start);
        InstructionHandle start3 = instructionListCompileDefaultText.getStart();
        int size = namesIndex.size() + 14;
        int[] iArr = new int[size];
        for (int i3 = 0; i3 < size; i3++) {
            iArr[i3] = i3;
        }
        boolean[] zArr = new boolean[size];
        boolean[] zArr2 = new boolean[size];
        for (int i4 = 0; i4 < namesIndex.size(); i4++) {
            String str = namesIndex.get(i4);
            int i5 = i4 + 14;
            zArr[i5] = isAttributeName(str);
            zArr2[i5] = isNamespaceName(str);
        }
        compileTemplateCalls(classGenerator, methodGenerator, start, i, i2);
        TestSeq testSeq = this._testSeq[1];
        InstructionHandle instructionHandleCompile = testSeq != null ? testSeq.compile(classGenerator, methodGenerator, start) : start2;
        TestSeq testSeq2 = this._testSeq[2];
        InstructionHandle instructionHandleCompile2 = testSeq2 != null ? testSeq2.compile(classGenerator, methodGenerator, start) : start;
        TestSeq testSeq3 = this._idxTestSeq;
        InstructionList instructionList4 = testSeq3 != null ? testSeq3.getInstructionList() : null;
        TestSeq testSeq4 = this._childNodeTestSeq;
        if (testSeq4 != null) {
            double priority2 = testSeq4.getPriority();
            int position2 = this._childNodeTestSeq.getPosition();
            int position3 = Integer.MIN_VALUE;
            double priority3 = -1.7976931348623157E308d;
            if (testSeq != null) {
                priority = testSeq.getPriority();
                position = testSeq.getPosition();
            } else {
                position = Integer.MIN_VALUE;
                priority = -1.7976931348623157E308d;
            }
            if (priority == Double.NaN || priority < priority2 || (priority == priority2 && position < position2)) {
                instructionHandleCompile = this._childNodeTestSeq.compile(classGenerator, methodGenerator, start);
            }
            TestSeq testSeq5 = this._testSeq[3];
            if (testSeq5 != null) {
                priority3 = testSeq5.getPriority();
                position3 = testSeq5.getPosition();
            }
            int i6 = position3;
            if (priority3 == Double.NaN || priority3 < priority2 || (priority3 == priority2 && i6 < position2)) {
                start3 = this._childNodeTestSeq.compile(classGenerator, methodGenerator, start);
                this._testSeq[3] = this._childNodeTestSeq;
            }
        }
        InstructionHandle instructionHandle = instructionHandleCompile;
        InstructionHandle instructionHandleCompile3 = start3;
        InstructionList instructionList5 = instructionList4;
        InstructionList instructionListCompileNamespaces = compileNamespaces(classGenerator, methodGenerator, zArr2, zArr, false, instructionHandle);
        InstructionHandle start4 = instructionListCompileNamespaces != null ? instructionListCompileNamespaces.getStart() : instructionHandle;
        InstructionHandle start5 = instructionHandleCompile2;
        InstructionList instructionListCompileNamespaces2 = compileNamespaces(classGenerator, methodGenerator, zArr2, zArr, true, start5);
        if (instructionListCompileNamespaces2 != null) {
            start5 = instructionListCompileNamespaces2.getStart();
        }
        InstructionHandle[] instructionHandleArr = new InstructionHandle[size];
        int i7 = 14;
        while (i7 < size) {
            int i8 = i7;
            TestSeq testSeq6 = this._testSeq[i8];
            if (zArr2[i8]) {
                if (zArr[i8]) {
                    instructionHandleArr[i8] = start5;
                } else {
                    instructionHandleArr[i8] = start4;
                }
            } else if (testSeq6 == null) {
                instructionHandleArr[i8] = start;
            } else if (zArr[i8]) {
                instructionHandleArr[i8] = testSeq6.compile(classGenerator, methodGenerator, start5);
            } else {
                instructionHandleArr[i8] = testSeq6.compile(classGenerator, methodGenerator, start4);
            }
            i7 = i8 + 1;
        }
        LocationPathPattern locationPathPattern = this._rootPattern;
        instructionHandleArr[0] = locationPathPattern != null ? getTemplateInstructionHandle(locationPathPattern.getTemplate()) : start2;
        LocationPathPattern locationPathPattern2 = this._rootPattern;
        if (locationPathPattern2 != null) {
            start2 = getTemplateInstructionHandle(locationPathPattern2.getTemplate());
        }
        instructionHandleArr[9] = start2;
        TestSeq testSeq7 = this._testSeq[3];
        if (testSeq7 != null) {
            instructionHandleCompile3 = testSeq7.compile(classGenerator, methodGenerator, instructionHandleCompile3);
        }
        instructionHandleArr[3] = instructionHandleCompile3;
        instructionHandleArr[13] = start;
        instructionHandleArr[1] = start4;
        instructionHandleArr[2] = start5;
        InstructionHandle instructionHandle2 = this._childNodeTestSeq != null ? instructionHandle : start;
        TestSeq testSeq8 = this._testSeq[7];
        if (testSeq8 != null) {
            instructionHandleArr[7] = testSeq8.compile(classGenerator, methodGenerator, instructionHandle2);
        } else {
            instructionHandleArr[7] = instructionHandle2;
        }
        InstructionHandle instructionHandleCompile4 = this._childNodeTestSeq != null ? instructionHandle : start;
        TestSeq testSeq9 = this._testSeq[8];
        if (testSeq9 != null) {
            instructionHandleCompile4 = testSeq9.compile(classGenerator, methodGenerator, instructionHandleCompile4);
        }
        instructionHandleArr[8] = instructionHandleCompile4;
        instructionHandleArr[4] = start;
        instructionHandleArr[11] = start;
        instructionHandleArr[10] = start;
        instructionHandleArr[6] = start;
        instructionHandleArr[5] = start;
        instructionHandleArr[12] = start;
        for (int i9 = 14; i9 < size; i9++) {
            TestSeq testSeq10 = this._testSeq[i9];
            if (testSeq10 == null || zArr2[i9]) {
                if (zArr[i9]) {
                    instructionHandleArr[i9] = start5;
                } else {
                    instructionHandleArr[i9] = start4;
                }
            } else if (zArr[i9]) {
                instructionHandleArr[i9] = testSeq10.compile(classGenerator, methodGenerator, start5);
            } else {
                instructionHandleArr[i9] = testSeq10.compile(classGenerator, methodGenerator, start4);
            }
        }
        if (instructionList5 != null) {
            instructionList2.insert(instructionList5);
        }
        int iAddInterfaceMethodref = constantPool.addInterfaceMethodref(Constants.DOM_INTF, "getExpandedTypeID", Constants.GET_PARENT_SIG);
        instructionList2.append(methodGenerator.loadDOM());
        instructionList2.append(new ILOAD(this._currentIndex));
        instructionList2.append(new INVOKEINTERFACE(iAddInterfaceMethodref, 2));
        instructionList2.append(new SWITCH(iArr, instructionHandleArr, start));
        appendTestSequences(instructionList2);
        appendTemplateCode(instructionList2);
        if (instructionListCompileNamespaces != null) {
            instructionList2.append(instructionListCompileNamespaces);
        }
        if (instructionListCompileNamespaces2 != null) {
            instructionList2.append(instructionListCompileNamespaces2);
        }
        instructionList2.append(instructionListCompileDefaultRecursion);
        instructionList2.append(instructionListCompileDefaultText);
        instructionList.append(instructionList2);
        localVariableGenAddLocalVariable2.setEnd(instructionList2.getEnd());
        instructionList.append(instructionList3);
        peepHoleOptimization(methodGenerator);
        classGenerator.addMethod(methodGenerator);
        this._templates = list;
    }

    /* JADX WARN: Code duplicated, block: B:102:0x02bc  */
    /* JADX WARN: Code duplicated, block: B:111:0x02d9  */
    /* JADX WARN: Code duplicated, block: B:113:0x02dd  */
    /* JADX WARN: Code duplicated, block: B:114:0x02e0  */
    /* JADX WARN: Code duplicated, block: B:117:0x02e7  */
    /* JADX WARN: Code duplicated, block: B:120:0x031f  */
    /* JADX WARN: Code duplicated, block: B:122:0x0324  */
    /* JADX WARN: Code duplicated, block: B:125:0x0345  */
    /* JADX WARN: Code duplicated, block: B:128:0x0353 A[LOOP:4: B:126:0x034d->B:128:0x0353, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:129:0x0371 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Code duplicated, block: B:52:0x01e2  */
    /* JADX WARN: Code duplicated, block: B:54:0x01e8  */
    /* JADX WARN: Code duplicated, block: B:57:0x01ff  */
    /* JADX WARN: Code duplicated, block: B:60:0x020d  */
    /* JADX WARN: Code duplicated, block: B:62:0x0217  */
    /* JADX WARN: Code duplicated, block: B:64:0x021b  */
    /* JADX WARN: Code duplicated, block: B:65:0x021e  */
    /* JADX WARN: Code duplicated, block: B:66:0x0221 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:67:0x0223  */
    /* JADX WARN: Code duplicated, block: B:69:0x0227  */
    /* JADX WARN: Code duplicated, block: B:70:0x022e  */
    /* JADX WARN: Code duplicated, block: B:71:0x0235  */
    /* JADX WARN: Code duplicated, block: B:75:0x023e  */
    /* JADX WARN: Code duplicated, block: B:76:0x0247  */
    /* JADX WARN: Code duplicated, block: B:79:0x024f  */
    /* JADX WARN: Code duplicated, block: B:80:0x0258  */
    /* JADX WARN: Code duplicated, block: B:83:0x0264  */
    /* JADX WARN: Code duplicated, block: B:84:0x0269  */
    /* JADX WARN: Code duplicated, block: B:87:0x0278  */
    /* JADX WARN: Code duplicated, block: B:88:0x027b  */
    /* JADX WARN: Code duplicated, block: B:91:0x0284  */
    /* JADX WARN: Code duplicated, block: B:92:0x028b  */
    /* JADX WARN: Code duplicated, block: B:95:0x0291  */
    /* JADX WARN: Code duplicated, block: B:96:0x0294  */
    /* JADX WARN: Code duplicated, block: B:99:0x029d  */
    public void compileApplyTemplates(ClassGenerator classGenerator) {
        InstructionList instructionList;
        InstructionHandle instructionHandle;
        InstructionHandle instructionHandle2;
        InstructionList instructionListCompileNamespaces;
        InstructionHandle instructionHandle3;
        InstructionHandle start;
        InstructionHandle instructionHandle4;
        InstructionHandle start2;
        InstructionList instructionListCompileNamespaces2;
        InstructionHandle[] instructionHandleArr;
        int i;
        LocationPathPattern locationPathPattern;
        InstructionHandle templateInstructionHandle;
        LocationPathPattern locationPathPattern2;
        InstructionHandle templateInstructionHandle2;
        TestSeq testSeq;
        InstructionHandle instructionHandleCompile;
        InstructionHandle instructionHandle5;
        TestSeq testSeq2;
        InstructionHandle instructionHandleCompile2;
        TestSeq testSeq3;
        int i2;
        Map<Integer, Integer> map;
        TestSeq testSeq4;
        int i3;
        TestSeq testSeq5;
        int position;
        double priority;
        XSLTC xsltc = classGenerator.getParser().getXSLTC();
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        List<String> namesIndex = xsltc.getNamesIndex();
        Type[] typeArr = {Util.getJCRefType(Constants.DOM_INTF_SIG), Util.getJCRefType("Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;"), Util.getJCRefType("Lcom/sun/org/apache/xml/internal/serializer/SerializationHandler;")};
        String[] strArr = {Constants.DOCUMENT_PNAME, Constants.ITERATOR_PNAME, Constants.TRANSLET_OUTPUT_PNAME};
        InstructionList instructionList2 = new InstructionList();
        MethodGenerator methodGenerator = new MethodGenerator(17, Type.VOID, typeArr, strArr, functionName(), getClassName(), instructionList2, classGenerator.getConstantPool());
        methodGenerator.addException("com.sun.org.apache.xalan.internal.xsltc.TransletException");
        com.sun.org.apache.bcel.internal.generic.Instruction instruction = Constants.NOP;
        instructionList2.append(instruction);
        LocalVariableGen localVariableGenAddLocalVariable2 = methodGenerator.addLocalVariable2(Keywords.FUNC_CURRENT_STRING, Type.INT, null);
        this._currentIndex = localVariableGenAddLocalVariable2.getIndex();
        InstructionList instructionList3 = new InstructionList();
        instructionList3.append(instruction);
        InstructionList instructionList4 = new InstructionList();
        instructionList4.append(methodGenerator.loadIterator());
        instructionList4.append(methodGenerator.nextNode());
        instructionList4.append(Constants.DUP);
        instructionList4.append(new ISTORE(this._currentIndex));
        BranchHandle branchHandleAppend = instructionList4.append((BranchInstruction) new IFLT(null));
        BranchHandle branchHandleAppend2 = instructionList4.append((BranchInstruction) new GOTO_W(null));
        branchHandleAppend.setTarget(instructionList4.append(Constants.RETURN));
        InstructionHandle start3 = instructionList4.getStart();
        localVariableGenAddLocalVariable2.setStart(instructionList2.append((BranchInstruction) new GOTO_W(start3)));
        localVariableGenAddLocalVariable2.setEnd(branchHandleAppend2);
        InstructionList instructionListCompileDefaultRecursion = compileDefaultRecursion(classGenerator, methodGenerator, start3);
        InstructionHandle start4 = instructionListCompileDefaultRecursion.getStart();
        InstructionList instructionListCompileDefaultText = compileDefaultText(classGenerator, methodGenerator, start3);
        InstructionHandle start5 = instructionListCompileDefaultText.getStart();
        int size = namesIndex.size() + 14;
        int[] iArr = new int[size];
        for (int i4 = 0; i4 < size; i4++) {
            iArr[i4] = i4;
        }
        boolean[] zArr = new boolean[size];
        boolean[] zArr2 = new boolean[size];
        for (int i5 = 0; i5 < namesIndex.size(); i5++) {
            String str = namesIndex.get(i5);
            int i6 = i5 + 14;
            zArr[i6] = isAttributeName(str);
            zArr2[i6] = isNamespaceName(str);
        }
        compileTemplates(classGenerator, methodGenerator, start3);
        TestSeq testSeq6 = this._testSeq[1];
        InstructionHandle instructionHandleCompile3 = testSeq6 != null ? testSeq6.compile(classGenerator, methodGenerator, start4) : start4;
        TestSeq testSeq7 = this._testSeq[2];
        InstructionHandle instructionHandleCompile4 = testSeq7 != null ? testSeq7.compile(classGenerator, methodGenerator, start5) : start5;
        TestSeq testSeq8 = this._idxTestSeq;
        if (testSeq8 != null) {
            branchHandleAppend2.setTarget(testSeq8.compile(classGenerator, methodGenerator, instructionList3.getStart()));
            instructionList = this._idxTestSeq.getInstructionList();
        } else {
            branchHandleAppend2.setTarget(instructionList3.getStart());
            instructionList = null;
        }
        TestSeq testSeq9 = this._childNodeTestSeq;
        if (testSeq9 != null) {
            double priority2 = testSeq9.getPriority();
            int position2 = this._childNodeTestSeq.getPosition();
            int position3 = Integer.MIN_VALUE;
            double priority3 = -1.7976931348623157E308d;
            if (testSeq6 != null) {
                priority = testSeq6.getPriority();
                position = testSeq6.getPosition();
            } else {
                position = Integer.MIN_VALUE;
                priority = -1.7976931348623157E308d;
            }
            if (priority == Double.NaN || priority < priority2 || (priority == priority2 && position < position2)) {
                instructionHandleCompile3 = this._childNodeTestSeq.compile(classGenerator, methodGenerator, start3);
            }
            TestSeq testSeq10 = this._testSeq[3];
            if (testSeq10 != null) {
                priority3 = testSeq10.getPriority();
                position3 = testSeq10.getPosition();
            }
            int i7 = position3;
            if (priority3 == Double.NaN || priority3 < priority2 || (priority3 == priority2 && i7 < position2)) {
                start5 = this._childNodeTestSeq.compile(classGenerator, methodGenerator, start3);
                this._testSeq[3] = this._childNodeTestSeq;
            } else {
                instructionHandle = instructionHandleCompile3;
                start5 = start5;
            }
            instructionHandle2 = start5;
            instructionListCompileNamespaces = compileNamespaces(classGenerator, methodGenerator, zArr2, zArr, false, instructionHandle);
            instructionHandle3 = instructionHandle;
            if (instructionListCompileNamespaces != null) {
                start = instructionListCompileNamespaces.getStart();
            } else {
                start = instructionHandle3;
            }
            instructionHandle4 = start;
            start2 = instructionHandleCompile4;
            instructionListCompileNamespaces2 = compileNamespaces(classGenerator, methodGenerator, zArr2, zArr, true, start2);
            if (instructionListCompileNamespaces2 != null) {
                start2 = instructionListCompileNamespaces2.getStart();
            }
            instructionHandleArr = new InstructionHandle[size];
            i = 14;
            while (i < size) {
                i3 = i;
                testSeq5 = this._testSeq[i3];
                if (zArr2[i3]) {
                    if (zArr[i3]) {
                        instructionHandleArr[i3] = start2;
                    } else {
                        instructionHandleArr[i3] = instructionHandle4;
                    }
                } else if (testSeq5 != null) {
                    instructionHandleArr[i3] = start3;
                } else if (zArr[i3]) {
                    instructionHandleArr[i3] = testSeq5.compile(classGenerator, methodGenerator, start2);
                } else {
                    instructionHandleArr[i3] = testSeq5.compile(classGenerator, methodGenerator, instructionHandle4);
                }
                i = i3 + 1;
            }
            locationPathPattern = this._rootPattern;
            if (locationPathPattern != null) {
                templateInstructionHandle = getTemplateInstructionHandle(locationPathPattern.getTemplate());
            } else {
                templateInstructionHandle = start4;
            }
            instructionHandleArr[0] = templateInstructionHandle;
            locationPathPattern2 = this._rootPattern;
            if (locationPathPattern2 != null) {
                templateInstructionHandle2 = getTemplateInstructionHandle(locationPathPattern2.getTemplate());
            } else {
                templateInstructionHandle2 = start4;
            }
            instructionHandleArr[9] = templateInstructionHandle2;
            testSeq = this._testSeq[3];
            if (testSeq != null) {
                instructionHandleCompile = testSeq.compile(classGenerator, methodGenerator, instructionHandle2);
            } else {
                instructionHandleCompile = instructionHandle2;
            }
            instructionHandleArr[3] = instructionHandleCompile;
            instructionHandleArr[13] = start3;
            instructionHandleArr[1] = instructionHandle4;
            instructionHandleArr[r13] = start2;
            if (this._childNodeTestSeq != null) {
                instructionHandle5 = instructionHandle3;
            } else {
                instructionHandle5 = start3;
            }
            testSeq2 = this._testSeq[7];
            if (testSeq2 != null) {
                instructionHandleArr[7] = testSeq2.compile(classGenerator, methodGenerator, instructionHandle5);
            } else {
                instructionHandleArr[7] = instructionHandle5;
            }
            if (this._childNodeTestSeq != null) {
                instructionHandleCompile2 = instructionHandle3;
            } else {
                instructionHandleCompile2 = start3;
            }
            testSeq3 = this._testSeq[8];
            if (testSeq3 != null) {
                instructionHandleCompile2 = testSeq3.compile(classGenerator, methodGenerator, instructionHandleCompile2);
            }
            instructionHandleArr[8] = instructionHandleCompile2;
            instructionHandleArr[4] = start3;
            instructionHandleArr[11] = start3;
            instructionHandleArr[10] = start3;
            instructionHandleArr[6] = start3;
            instructionHandleArr[5] = start3;
            instructionHandleArr[12] = start3;
            for (i2 = 14; i2 < size; i2++) {
                testSeq4 = this._testSeq[i2];
                if (testSeq4 != null || zArr2[i2]) {
                    if (zArr[i2]) {
                        instructionHandleArr[i2] = start2;
                    } else {
                        instructionHandleArr[i2] = instructionHandle4;
                    }
                } else if (zArr[i2]) {
                    instructionHandleArr[i2] = testSeq4.compile(classGenerator, methodGenerator, start2);
                } else {
                    instructionHandleArr[i2] = testSeq4.compile(classGenerator, methodGenerator, instructionHandle4);
                }
            }
            if (instructionList != null) {
                instructionList3.insert(instructionList);
            }
            int iAddInterfaceMethodref = constantPool.addInterfaceMethodref(Constants.DOM_INTF, "getExpandedTypeID", Constants.GET_PARENT_SIG);
            instructionList3.append(methodGenerator.loadDOM());
            instructionList3.append(new ILOAD(this._currentIndex));
            instructionList3.append(new INVOKEINTERFACE(iAddInterfaceMethodref, 2));
            instructionList3.append(new SWITCH(iArr, instructionHandleArr, start3));
            appendTestSequences(instructionList3);
            appendTemplateCode(instructionList3);
            if (instructionListCompileNamespaces != null) {
                instructionList3.append(instructionListCompileNamespaces);
            }
            if (instructionListCompileNamespaces2 != null) {
                instructionList3.append(instructionListCompileNamespaces2);
            }
            instructionList3.append(instructionListCompileDefaultRecursion);
            instructionList3.append(instructionListCompileDefaultText);
            instructionList2.append(instructionList3);
            instructionList2.append(instructionList4);
            peepHoleOptimization(methodGenerator);
            classGenerator.addMethod(methodGenerator);
            map = this._importLevels;
            if (map != null) {
                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    compileApplyImports(classGenerator, entry.getValue().intValue(), entry.getKey().intValue());
                }
            }
        }
        instructionHandle = instructionHandleCompile3;
        instructionHandle2 = start5;
        instructionListCompileNamespaces = compileNamespaces(classGenerator, methodGenerator, zArr2, zArr, false, instructionHandle);
        instructionHandle3 = instructionHandle;
        if (instructionListCompileNamespaces != null) {
            start = instructionListCompileNamespaces.getStart();
        } else {
            start = instructionHandle3;
        }
        instructionHandle4 = start;
        start2 = instructionHandleCompile4;
        instructionListCompileNamespaces2 = compileNamespaces(classGenerator, methodGenerator, zArr2, zArr, true, start2);
        if (instructionListCompileNamespaces2 != null) {
            start2 = instructionListCompileNamespaces2.getStart();
        }
        instructionHandleArr = new InstructionHandle[size];
        i = 14;
        while (i < size) {
            i3 = i;
            testSeq5 = this._testSeq[i3];
            if (zArr2[i3]) {
                if (zArr[i3]) {
                    instructionHandleArr[i3] = start2;
                } else {
                    instructionHandleArr[i3] = instructionHandle4;
                }
            } else if (testSeq5 != null) {
                instructionHandleArr[i3] = start3;
            } else if (zArr[i3]) {
                instructionHandleArr[i3] = testSeq5.compile(classGenerator, methodGenerator, start2);
            } else {
                instructionHandleArr[i3] = testSeq5.compile(classGenerator, methodGenerator, instructionHandle4);
            }
            i = i3 + 1;
        }
        locationPathPattern = this._rootPattern;
        if (locationPathPattern != null) {
            templateInstructionHandle = getTemplateInstructionHandle(locationPathPattern.getTemplate());
        } else {
            templateInstructionHandle = start4;
        }
        instructionHandleArr[0] = templateInstructionHandle;
        locationPathPattern2 = this._rootPattern;
        if (locationPathPattern2 != null) {
            templateInstructionHandle2 = getTemplateInstructionHandle(locationPathPattern2.getTemplate());
        } else {
            templateInstructionHandle2 = start4;
        }
        instructionHandleArr[9] = templateInstructionHandle2;
        testSeq = this._testSeq[3];
        if (testSeq != null) {
            instructionHandleCompile = testSeq.compile(classGenerator, methodGenerator, instructionHandle2);
        } else {
            instructionHandleCompile = instructionHandle2;
        }
        instructionHandleArr[3] = instructionHandleCompile;
        instructionHandleArr[13] = start3;
        instructionHandleArr[1] = instructionHandle4;
        instructionHandleArr[r13] = start2;
        if (this._childNodeTestSeq != null) {
            instructionHandle5 = instructionHandle3;
        } else {
            instructionHandle5 = start3;
        }
        testSeq2 = this._testSeq[7];
        if (testSeq2 != null) {
            instructionHandleArr[7] = testSeq2.compile(classGenerator, methodGenerator, instructionHandle5);
        } else {
            instructionHandleArr[7] = instructionHandle5;
        }
        if (this._childNodeTestSeq != null) {
            instructionHandleCompile2 = instructionHandle3;
        } else {
            instructionHandleCompile2 = start3;
        }
        testSeq3 = this._testSeq[8];
        if (testSeq3 != null) {
            instructionHandleCompile2 = testSeq3.compile(classGenerator, methodGenerator, instructionHandleCompile2);
        }
        instructionHandleArr[8] = instructionHandleCompile2;
        instructionHandleArr[4] = start3;
        instructionHandleArr[11] = start3;
        instructionHandleArr[10] = start3;
        instructionHandleArr[6] = start3;
        instructionHandleArr[5] = start3;
        instructionHandleArr[12] = start3;
        while (i2 < size) {
            testSeq4 = this._testSeq[i2];
            if (testSeq4 != null) {
                if (zArr[i2]) {
                    instructionHandleArr[i2] = start2;
                } else {
                    instructionHandleArr[i2] = instructionHandle4;
                }
            } else if (zArr[i2]) {
                instructionHandleArr[i2] = start2;
            } else {
                instructionHandleArr[i2] = instructionHandle4;
            }
        }
        if (instructionList != null) {
            instructionList3.insert(instructionList);
        }
        int iAddInterfaceMethodref2 = constantPool.addInterfaceMethodref(Constants.DOM_INTF, "getExpandedTypeID", Constants.GET_PARENT_SIG);
        instructionList3.append(methodGenerator.loadDOM());
        instructionList3.append(new ILOAD(this._currentIndex));
        instructionList3.append(new INVOKEINTERFACE(iAddInterfaceMethodref2, 2));
        instructionList3.append(new SWITCH(iArr, instructionHandleArr, start3));
        appendTestSequences(instructionList3);
        appendTemplateCode(instructionList3);
        if (instructionListCompileNamespaces != null) {
            instructionList3.append(instructionListCompileNamespaces);
        }
        if (instructionListCompileNamespaces2 != null) {
            instructionList3.append(instructionListCompileNamespaces2);
        }
        instructionList3.append(instructionListCompileDefaultRecursion);
        instructionList3.append(instructionListCompileDefaultText);
        instructionList2.append(instructionList3);
        instructionList2.append(instructionList4);
        peepHoleOptimization(methodGenerator);
        classGenerator.addMethod(methodGenerator);
        map = this._importLevels;
        if (map != null) {
            while (r2.hasNext()) {
                compileApplyImports(classGenerator, entry.getValue().intValue(), entry.getKey().intValue());
            }
        }
    }

    public String functionName(int i, int i2) {
        if (this._importLevels == null) {
            this._importLevels = new LinkedHashMap();
        }
        this._importLevels.put(Integer.valueOf(i2), Integer.valueOf(i));
        return this._methodName + '_' + i2;
    }

    public Stylesheet getStylesheet() {
        return this._stylesheet;
    }

    public InstructionHandle getTemplateInstructionHandle(Template template) {
        return this._templateIHs.get(template);
    }

    public void processPatterns(Map<String, Key> map) {
        this._keys = map;
        List<Template> list = this._templates;
        List<Template> listQuicksort = quicksort(list, 0, list.size() - 1);
        this._templates = listQuicksort;
        for (Template template : listQuicksort) {
            if (template.isNamed() && !template.disabled()) {
                this._namedTemplates.put(template, this);
            }
            Pattern pattern = template.getPattern();
            if (pattern != null) {
                flattenAlternative(pattern, template, map);
            }
        }
        prepareTestSequences();
    }

    public String functionName() {
        return this._methodName;
    }
}

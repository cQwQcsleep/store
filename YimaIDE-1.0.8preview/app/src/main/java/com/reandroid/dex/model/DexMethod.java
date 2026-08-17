package com.reandroid.dex.model;

import com.reandroid.dex.common.Register;
import com.reandroid.dex.common.RegistersTable;
import com.reandroid.dex.data.CodeItem;
import com.reandroid.dex.data.InstructionList;
import com.reandroid.dex.data.MethodDef;
import com.reandroid.dex.data.MethodParameter;
import com.reandroid.dex.id.MethodId;
import com.reandroid.dex.ins.Ins;
import com.reandroid.dex.ins.Opcode;
import com.reandroid.dex.ins.SizeXIns;
import com.reandroid.dex.ins.TryBlock;
import com.reandroid.dex.key.FieldKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.MethodKey;
import com.reandroid.dex.key.StringKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.model.DexClass;
import com.reandroid.dex.model.DexInstruction;
import com.reandroid.dex.model.DexMethod;
import com.reandroid.dex.program.MethodProgram;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.SmaliInstruction;
import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.ComputeIterator;
import com.reandroid.utils.collection.EmptyIterator;
import com.reandroid.utils.collection.EmptyList;
import com.reandroid.utils.collection.FilterIterator;
import com.reandroid.utils.collection.MergingIterator;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DexMethod extends DexDeclaration implements MethodProgram {
    private final DexClass dexClass;
    private int mEditIndex;
    private final MethodDef methodDef;

    public DexMethod(DexClass dexClass, MethodDef methodDef) {
        this.dexClass = dexClass;
        this.methodDef = methodDef;
    }

    public static /* synthetic */ boolean b(Predicate predicate, Ins ins) {
        if (ins instanceof SizeXIns) {
            return predicate.test(((SizeXIns) ins).getKey());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DexInstruction create(Ins ins) {
        return DexInstruction.create(this, ins);
    }

    public static /* synthetic */ boolean d(Predicate predicate, Ins ins) {
        if (!(ins instanceof SizeXIns)) {
            return false;
        }
        Key key = ((SizeXIns) ins).getKey();
        if (key instanceof FieldKey) {
            return predicate.test((FieldKey) key);
        }
        return false;
    }

    private boolean differentPrimitiveForBridging(TypeKey typeKey, TypeKey typeKey2) {
        return typeKey.isPrimitive() ? !typeKey.equals(typeKey2) : typeKey2.isPrimitive();
    }

    public static /* synthetic */ boolean e(DexInstruction dexInstruction) {
        return dexInstruction.is(Opcode.INVOKE_VIRTUAL) || dexInstruction.is(Opcode.INVOKE_VIRTUAL_RANGE);
    }

    private boolean equalsForBridging(MethodKey methodKey, MethodKey methodKey2) {
        int parametersCount;
        if (!methodKey.getDeclaring().equals(methodKey2.getDeclaring()) || methodKey.equals(methodKey2) || (parametersCount = methodKey.getParametersCount()) != methodKey2.getParametersCount()) {
            return false;
        }
        TypeKey returnType = methodKey.getReturnType();
        if (differentPrimitiveForBridging(returnType, methodKey2.getReturnType())) {
            return false;
        }
        boolean zIsPrimitive = returnType.isPrimitive();
        for (int i = 0; i < parametersCount; i++) {
            TypeKey parameter = methodKey.getParameter(i);
            if (differentPrimitiveForBridging(methodKey.getParameter(i), methodKey2.getParameter(i))) {
                return false;
            }
            if (zIsPrimitive) {
                zIsPrimitive = parameter.isPrimitive();
            }
        }
        return !zIsPrimitive;
    }

    public static /* synthetic */ boolean f(Predicate predicate, Ins ins) {
        if (!(ins instanceof SizeXIns)) {
            return false;
        }
        Key key = ((SizeXIns) ins).getKey();
        if (key instanceof MethodKey) {
            return predicate.test((MethodKey) key);
        }
        return false;
    }

    private InstructionList getInstructionList() {
        return getDefinition().getInstructionList();
    }

    public static /* synthetic */ DexMethodParameter h(DexMethod dexMethod, MethodParameter methodParameter) {
        dexMethod.getClass();
        return DexMethodParameter.create(dexMethod, methodParameter);
    }

    public static /* synthetic */ boolean k(MethodKey methodKey, DexMethod dexMethod) {
        DexMethod bridgingMethod = dexMethod.getBridgingMethod();
        if (bridgingMethod != null) {
            return methodKey.equals(bridgingMethod.getKey());
        }
        return false;
    }

    public static /* synthetic */ boolean o(Predicate predicate, Ins ins) {
        if (!(ins instanceof SizeXIns)) {
            return false;
        }
        Key key = ((SizeXIns) ins).getKey();
        if (key instanceof StringKey) {
            return predicate.test((StringKey) key);
        }
        return false;
    }

    public static /* synthetic */ boolean q(Opcode opcode, Opcode opcode2, Opcode opcode3) {
        return opcode3 == opcode || opcode3 == opcode2;
    }

    public static /* synthetic */ boolean v(Predicate predicate, Ins ins) {
        if (!(ins instanceof SizeXIns)) {
            return false;
        }
        Key key = ((SizeXIns) ins).getKey();
        if (key instanceof TypeKey) {
            return predicate.test((TypeKey) key);
        }
        return false;
    }

    public static /* synthetic */ boolean w(Opcode opcode, Opcode opcode2) {
        return opcode2 == opcode;
    }

    public DexInstruction addInstruction(Opcode<?> opcode) {
        return create(getDefinition().getOrCreateInstructionList().createNext(opcode));
    }

    @Override // com.reandroid.dex.model.Dex, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        getDefinition().append(smaliWriter);
    }

    public void clearCode() {
        getDefinition().clearCode();
    }

    public void clearDebug() {
        getDefinition().clearDebug();
    }

    public DexTry createDexTry() {
        return DexTry.create(this, getDefinition().getOrCreateTryBlock().createNext());
    }

    public DexInstruction createInstruction(int i, Opcode<?> opcode) {
        return create(getDefinition().getOrCreateInstructionList().createAt(i, opcode));
    }

    public void ensureLocalRegistersCount(int i) {
        if (i == 0) {
            return;
        }
        RegistersTable registersTable = getRegistersTable();
        if (registersTable == null || i > registersTable.getLocalRegistersCount()) {
            getOrCreateRegistersTable().ensureLocalRegistersCount(i);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return MethodId.equals(true, getId(), ((DexMethod) obj).getId());
    }

    public MethodKey getBridge() {
        DexMethod bridgeMethod = getBridgeMethod();
        if (bridgeMethod != null) {
            return bridgeMethod.getKey();
        }
        return null;
    }

    public DexMethod getBridgeMethod() {
        if (isBridge() || !isVirtual()) {
            return null;
        }
        final MethodKey key = getKey();
        return (DexMethod) CollectionUtil.getSingle(FilterIterator.of(getDexClass().getVirtualMethods(), new Predicate() { // from class: dr3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return DexMethod.k(key, (DexMethod) obj);
            }
        }));
    }

    public MethodKey getBridging() {
        DexInstruction dexInstruction;
        if (!isBridge() || (dexInstruction = (DexInstruction) CollectionUtil.getSingle(FilterIterator.of(getInstructions(), new Predicate() { // from class: yq3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return DexMethod.e((DexInstruction) obj);
            }
        }))) == null) {
            return null;
        }
        MethodKey key = getKey();
        MethodKey methodKey = (MethodKey) dexInstruction.getKey();
        if (equalsForBridging(key, methodKey)) {
            return methodKey;
        }
        return null;
    }

    public DexMethod getBridgingMethod() {
        MethodKey bridging = getBridging();
        if (bridging != null) {
            return getDexClass().getDeclaredMethod(bridging);
        }
        return null;
    }

    public DexMethod getDeclared() {
        DexMethod method;
        DexClass superClass = getDexClass().getSuperClass();
        if (superClass != null && (method = superClass.getMethod(getKey())) != null) {
            return method.getDeclared();
        }
        Iterator<DexClass> interfaceClasses = getDexClass().getInterfaceClasses();
        while (interfaceClasses.hasNext()) {
            DexMethod method2 = interfaceClasses.next().getMethod(getKey());
            if (method2 != null) {
                return method2.getDeclared();
            }
        }
        return this;
    }

    @Override // com.reandroid.dex.model.DexDeclaration
    public DexClass getDexClass() {
        return this.dexClass;
    }

    public Iterator<DexTry> getDexTry(int i) {
        TryBlock tryBlock = getDefinition().getTryBlock();
        return tryBlock == null ? EmptyIterator.of() : DexTry.create(this, i, tryBlock.getTriesForAddress(i));
    }

    public int getEditIndex() {
        return this.mEditIndex;
    }

    public Iterator<DexMethod> getExtending() {
        return new MergingIterator(ComputeIterator.of(getDexClass().getExtending(), new Function() { // from class: ar3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((DexClass) obj).getExtending(this.b.getKey());
            }
        }));
    }

    @Override // com.reandroid.dex.model.DexDeclaration
    public MethodId getId() {
        return getDefinition().getId();
    }

    public Iterator<DexMethod> getImplementations() {
        return new MergingIterator(ComputeIterator.of(getDexClass().getImplementations(), new Function() { // from class: wq3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((DexClass) obj).getImplementations(this.b.getKey());
            }
        }));
    }

    public DexInstruction getInstruction(int i) {
        return create(getDefinition().getInstruction(i));
    }

    public DexInstruction getInstructionAt(int i) {
        return create(getDefinition().getInstructionAt(i));
    }

    public Iterator<DexInstruction> getInstructions() {
        return DexInstruction.create(this, getDefinition().getInstructions());
    }

    public int getInstructionsCount() {
        return getDefinition().getInstructionsCount();
    }

    public Iterator<DexInstruction> getInstructionsIfFieldKey(final Predicate<? super FieldKey> predicate) {
        return getInstructionsIfIns(new Predicate() { // from class: br3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return DexMethod.d(predicate, (Ins) obj);
            }
        });
    }

    public Iterator<DexInstruction> getInstructionsIfIns(Predicate<? super Ins> predicate) {
        return ComputeIterator.of(FilterIterator.of(getDefinition().getInstructions(), predicate), new Function() { // from class: fr3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.create((Ins) obj);
            }
        });
    }

    public Iterator<DexInstruction> getInstructionsIfKey(final Predicate<? super Key> predicate) {
        return getInstructionsIfIns(new Predicate() { // from class: cr3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return DexMethod.b(predicate, (Ins) obj);
            }
        });
    }

    public Iterator<DexInstruction> getInstructionsIfMethodKey(final Predicate<? super MethodKey> predicate) {
        return getInstructionsIfIns(new Predicate() { // from class: vq3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return DexMethod.f(predicate, (Ins) obj);
            }
        });
    }

    public Iterator<DexInstruction> getInstructionsIfOpcode(final Predicate<Opcode<?>> predicate) {
        return getInstructionsIfIns(new Predicate() { // from class: hr3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return predicate.test(((Ins) obj).getOpcode());
            }
        });
    }

    public Iterator<DexInstruction> getInstructionsIfStringKey(final Predicate<? super StringKey> predicate) {
        return getInstructionsIfIns(new Predicate() { // from class: tq3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return DexMethod.o(predicate, (Ins) obj);
            }
        });
    }

    public Iterator<DexInstruction> getInstructionsIfTypeKey(final Predicate<? super TypeKey> predicate) {
        return getInstructionsIfIns(new Predicate() { // from class: ir3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return DexMethod.v(predicate, (Ins) obj);
            }
        });
    }

    public Iterator<DexInstruction> getInstructionsWithOpcode(final Opcode<?> opcode) {
        return getInstructionsIfOpcode(new Predicate() { // from class: zq3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return DexMethod.w(opcode, (Opcode) obj);
            }
        });
    }

    @Override // com.reandroid.dex.model.DexDeclaration, com.reandroid.dex.model.AnnotatedDex, com.reandroid.dex.program.ProgramElement, com.reandroid.dex.data.DefIndex
    public MethodKey getKey() {
        return getId().getKey();
    }

    public List<Register> getLocalFreeRegisters(int i) {
        InstructionList instructionList = getInstructionList();
        return instructionList != null ? instructionList.getLocalFreeRegisters(i) : EmptyList.of();
    }

    public int getLocalRegistersCount() {
        RegistersTable registersTable = getRegistersTable();
        if (registersTable != null) {
            return registersTable.getLocalRegistersCount();
        }
        return 0;
    }

    public String getName() {
        return getDefinition().getName();
    }

    public RegistersTable getOrCreateRegistersTable() {
        return getDefinition().getOrCreateCodeItem();
    }

    public Iterator<DexMethod> getOverriding() {
        return CombiningIterator.two(getExtending(), getImplementations());
    }

    public Iterator<MethodKey> getOverridingKeys() {
        return new MergingIterator(ComputeIterator.of(getDexClass().getOverriding(), new Function() { // from class: gr3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((DexClass) obj).getOverridingKeys(this.b.getKey());
            }
        }));
    }

    public Iterator<DexMethodParameter> getParameters() {
        return ComputeIterator.of(getDefinition().getParameters(), new Function() { // from class: er3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DexMethod.h(this.b, (MethodParameter) obj);
            }
        });
    }

    public int getRegistersCount() {
        RegistersTable registersTable = getRegistersTable();
        if (registersTable != null) {
            return registersTable.getRegistersCount();
        }
        return 0;
    }

    public RegistersTable getRegistersTable() {
        return getDefinition().getCodeItem();
    }

    public DexMethod getSuperMethod() {
        return (DexMethod) CollectionUtil.getFirst(getSuperMethods());
    }

    public Iterator<DexMethod> getSuperMethods() {
        final MethodKey key = getKey();
        return ComputeIterator.of(getDexClass().getSuperTypes(), new Function() { // from class: xq3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((DexClass) obj).getDeclaredMethod(key);
            }
        });
    }

    public boolean hasParameter(int i) {
        return getDefinition().hasParameter(i);
    }

    public DexInstruction parseInstruction(int i, SmaliReader smaliReader) throws IOException {
        SmaliInstruction smaliInstruction = new SmaliInstruction();
        smaliInstruction.parse(smaliReader);
        Ins insCreateAt = getDefinition().getOrCreateInstructionList().createAt(i, smaliInstruction.getOpcode());
        insCreateAt.fromSmali(smaliInstruction);
        return create(insCreateAt);
    }

    public int refreshParameterRegistersCount() {
        RegistersTable registersTable = getRegistersTable();
        if (registersTable == null) {
            return 0;
        }
        int parameterRegistersCount = getKey().getParameterRegistersCount();
        if (!isStatic()) {
            parameterRegistersCount++;
        }
        int localRegistersCount = registersTable.getLocalRegistersCount();
        registersTable.setParameterRegistersCount(parameterRegistersCount);
        registersTable.setRegistersCount(localRegistersCount + parameterRegistersCount);
        return parameterRegistersCount;
    }

    public void removeParameter(int i) {
        getDefinition().removeParameter(i);
    }

    @Override // com.reandroid.dex.model.Dex
    public void removeSelf() {
        getDefinition().removeSelf();
    }

    public void setEditIndex(int i) {
        this.mEditIndex = i;
    }

    public void setLocalRegistersCount(int i) {
        CodeItem orCreateCodeItem = getDefinition().getOrCreateCodeItem();
        if (orCreateCodeItem != null) {
            orCreateCodeItem.setRegistersCount(orCreateCodeItem.getParameterRegistersCount() + i);
        }
    }

    public void setName(String str) {
        getDefinition().setName(str);
    }

    public void setParameterRegistersCount(int i) {
        CodeItem orCreateCodeItem = getDefinition().getOrCreateCodeItem();
        if (orCreateCodeItem != null) {
            orCreateCodeItem.setParameterRegistersCount(i);
        }
    }

    @Override // com.reandroid.dex.model.DexDeclaration
    public MethodDef getDefinition() {
        return this.methodDef;
    }

    public Iterator<DexInstruction> getInstructionsWithOpcode(final Opcode<?> opcode, final Opcode<?> opcode2) {
        return getInstructionsIfOpcode(new Predicate() { // from class: uq3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return DexMethod.q(opcode, opcode2, (Opcode) obj);
            }
        });
    }

    @Deprecated
    public Iterator<DexInstruction> getInstructions(Predicate<? super Ins> predicate) {
        return getInstructionsIfIns(predicate);
    }

    @Deprecated
    public Iterator<DexInstruction> getInstructions(Opcode<?> opcode) {
        return getInstructionsWithOpcode(opcode);
    }

    public Iterator<DexTry> getDexTry() {
        return getDexTry(-1);
    }

    public DexInstruction parseInstruction(SmaliReader smaliReader) throws IOException {
        return parseInstruction(getInstructionsCount(), smaliReader);
    }

    public DexInstruction parseInstruction(String str) throws IOException {
        return parseInstruction(SmaliReader.of(str));
    }
}

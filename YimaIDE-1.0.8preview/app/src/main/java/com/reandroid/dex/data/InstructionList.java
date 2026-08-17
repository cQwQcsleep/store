package com.reandroid.dex.data;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.container.FixedBlockContainer;
import com.reandroid.common.ArraySupplier;
import com.reandroid.dex.base.DexPositionAlign;
import com.reandroid.dex.common.Register;
import com.reandroid.dex.common.RegisterType;
import com.reandroid.dex.common.RegistersTable;
import com.reandroid.dex.data.InstructionList;
import com.reandroid.dex.debug.DebugSequence;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.id.StringId;
import com.reandroid.dex.ins.ConstNumber;
import com.reandroid.dex.ins.ConstNumberLong;
import com.reandroid.dex.ins.ConstString;
import com.reandroid.dex.ins.ExceptionHandler;
import com.reandroid.dex.ins.Ins;
import com.reandroid.dex.ins.InsBlockList;
import com.reandroid.dex.ins.InsNop;
import com.reandroid.dex.ins.Label;
import com.reandroid.dex.ins.NullInstruction;
import com.reandroid.dex.ins.Opcode;
import com.reandroid.dex.ins.RegisterReference;
import com.reandroid.dex.ins.RegistersEditor;
import com.reandroid.dex.ins.RegistersIterator;
import com.reandroid.dex.ins.SizeXIns;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.StringKey;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliFormat;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.SmaliCodeSet;
import com.reandroid.dex.smali.model.SmaliInstruction;
import com.reandroid.dex.smali.model.SmaliMethod;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.collection.ArraySupplierIterator;
import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.ComputeIterator;
import com.reandroid.utils.collection.EmptyIterator;
import com.reandroid.utils.collection.FilterIterator;
import com.reandroid.utils.collection.IterableIterator;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class InstructionList extends FixedBlockContainer implements Iterable<Ins>, SmaliFormat {
    private final DexPositionAlign blockAlign;
    private final CodeItem codeItem;
    private final InsBlockList insBlockList;

    public InstructionList(CodeItem codeItem) {
        super(2);
        this.codeItem = codeItem;
        DexPositionAlign dexPositionAlign = new DexPositionAlign();
        InsBlockList insBlockList = new InsBlockList(dexPositionAlign, codeItem.getInstructionCodeUnitsReference(), codeItem.getInstructionOutsReference(), codeItem.getExtraLines());
        this.insBlockList = insBlockList;
        this.blockAlign = dexPositionAlign;
        addChild(0, insBlockList);
        addChild(1, dexPositionAlign);
    }

    public static /* synthetic */ RegistersIterator b(Ins ins) {
        if (ins instanceof SizeXIns) {
            return ((SizeXIns) ins).getRegistersIterator();
        }
        return null;
    }

    private InsBlockList getInsBlockList() {
        return this.insBlockList;
    }

    private Iterator<RegistersIterator> getRegistersIterators(int i) {
        return ComputeIterator.of(iterator(i), new Function() { // from class: gr6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return InstructionList.b((Ins) obj);
            }
        });
    }

    public static /* synthetic */ Ins k(Opcode opcode, Predicate predicate, Ins ins) {
        if (ins == null || ins.getOpcode() != opcode) {
            return null;
        }
        if (predicate == null || predicate.test(ins)) {
            return ins;
        }
        return null;
    }

    public static /* synthetic */ boolean o(InstructionList instructionList, int i, int i2, Register register) {
        instructionList.getClass();
        int value = register.getValue();
        return value < i && instructionList.isFreeRegister(value, i2);
    }

    public void add(boolean z, int i, Ins ins) {
        InsBlockList insBlockList = getInsBlockList();
        insBlockList.unlink();
        Ins ins2 = insBlockList.get(i);
        Object objLinkLocked = ins2 != null ? insBlockList.linkLocked() : null;
        insBlockList.add(i, ins);
        if (z && ins2 != null) {
            ins2.transferExtraLinesTo(ins);
        }
        insBlockList.unlinkLocked(objLinkLocked);
    }

    public void addLocalRegisters(boolean z, int i) {
        RegistersTable registersTable = getRegistersTable();
        Iterator<RegistersIterator> registersIterators = getRegistersIterators();
        while (registersIterators.hasNext()) {
            RegistersIterator next = registersIterators.next();
            int size = next.isRange() ? 1 : next.size();
            for (int i2 = 0; i2 < size; i2++) {
                RegisterReference registerReference = next.get(i2);
                if (z || registerReference.isParameter()) {
                    registerReference.setRegisterValue(registerReference.getValue() + i);
                }
            }
        }
        registersTable.setRegistersCount(registersTable.getRegistersCount() + i);
    }

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        InsBlockList insBlockList = getInsBlockList();
        Object objLinkLocked = insBlockList.linkLocked();
        smaliWriter.buildLabels(getCodeLabels());
        smaliWriter.setStateWritingInstructions(true);
        for (Ins ins : this) {
            smaliWriter.newLine();
            ins.append(smaliWriter);
        }
        smaliWriter.setStateWritingInstructions(false);
        NullInstruction nullInstruction = getInsBlockList().getNullInstruction();
        if (nullInstruction != null) {
            smaliWriter.newLine();
            nullInstruction.append(smaliWriter);
        }
        insBlockList.unlink(objLinkLocked, false);
    }

    public Iterator<Ins> arrayIterator() {
        return getInsBlockList().arrayIterator();
    }

    public boolean canAddLocalRegisters(int i) {
        if (getRegistersTable().getRegistersCount() + i < 15) {
            return true;
        }
        Iterator<RegistersIterator> registersIterators = getRegistersIterators();
        while (registersIterators.hasNext()) {
            RegistersIterator next = registersIterators.next();
            int size = next.isRange() ? 1 : next.size();
            for (int i2 = 0; i2 < size; i2++) {
                RegisterReference registerReference = next.get(i2);
                if (registerReference.isParameter() && registerReference.getValue() + i > registerReference.getLimit()) {
                    return false;
                }
            }
        }
        return true;
    }

    public Iterator<Ins> clonedIterator() {
        return getInsBlockList().clonedIterator();
    }

    public boolean contains(Ins ins) {
        return getInsBlockList().containsExact(ins);
    }

    public <T1 extends Ins> T1 createAt(int i, Opcode<T1> opcode) {
        if (i == getCount()) {
            return (T1) createNext(opcode);
        }
        T1 t1 = (T1) opcode.newInstance();
        add(i, t1);
        return t1;
    }

    public ConstNumber createConstIntegerAt(int i, int i2, int i3) {
        ConstNumber constNumber;
        if (i2 <= 15 && i3 <= 7 && i3 >= -7) {
            constNumber = (ConstNumber) createAt(i, Opcode.CONST_4);
        } else if (i3 > 32767 || i3 < -32767) {
            constNumber = (65535 & i3) == 0 ? (ConstNumber) createAt(i, Opcode.CONST_HIGH16) : (ConstNumber) createAt(i, Opcode.CONST);
        } else {
            constNumber = (ConstNumber) createAt(i, Opcode.CONST_16);
        }
        constNumber.setRegister(i2);
        constNumber.set(i3);
        return constNumber;
    }

    public ConstNumberLong createConstLongAt(int i, int i2, long j) {
        ConstNumberLong constNumberLong;
        if ((281470681743360L & j) == 0) {
            constNumberLong = (ConstNumberLong) createAt(i, Opcode.CONST_WIDE_HIGH16);
        } else if ((65535 & j) == j) {
            constNumberLong = (ConstNumberLong) createAt(i, Opcode.CONST_WIDE_16);
        } else {
            constNumberLong = (4294967295L & j) == j ? (ConstNumberLong) createAt(i, Opcode.CONST_WIDE_32) : (ConstNumberLong) createAt(i, Opcode.CONST_WIDE);
        }
        constNumberLong.setRegister(i2);
        constNumberLong.set(j);
        return constNumberLong;
    }

    public <T1 extends Ins> T1 createNext(Opcode<T1> opcode) {
        T1 t1 = (T1) opcode.newInstance();
        add(t1);
        return t1;
    }

    public ConstString createStringAt(int i, int i2, StringKey stringKey) {
        StringId stringId = (StringId) getCodeItem().getOrCreateSectionItem(SectionType.STRING_ID, stringKey);
        int idx = stringId.getIdx();
        ConstString constString = (65535 & idx) == idx ? (ConstString) createAt(i, Opcode.CONST_STRING) : (ConstString) createAt(i, Opcode.CONST_STRING_JUMBO);
        constString.setRegister(i2);
        constString.setString(stringId);
        return constString;
    }

    public RegistersEditor editRegisters() {
        return RegistersEditor.fromIns(getCodeItem(), iterator());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.insBlockList.equals(((InstructionList) obj).insBlockList);
    }

    public void fromSmali(int i, SmaliCodeSet smaliCodeSet) {
        InsBlockList insBlockList = getInsBlockList();
        Object objLinkLocked = insBlockList.linkLocked();
        Iterator<SmaliInstruction> instructions = smaliCodeSet.getInstructions();
        while (instructions.hasNext()) {
            SmaliInstruction next = instructions.next();
            createAt(i, next.getOpcode()).fromSmali(next);
            i++;
        }
        insBlockList.unlinkLocked(objLinkLocked);
    }

    public Ins get(int i) {
        return getInsBlockList().get(i);
    }

    public Ins getAtAddress(int i) {
        return getInsBlockList().getAtAddress(i);
    }

    public DexPositionAlign getBlockAlign() {
        return this.blockAlign;
    }

    public CodeItem getCodeItem() {
        return this.codeItem;
    }

    public Iterator<Label> getCodeLabels() {
        return CombiningIterator.two(getInsBlockList().getLabels(), getCodeItem().getTryBlockLabels());
    }

    public int getCodeUnits() {
        return getInsBlockList().getCodeUnits();
    }

    public int getCount() {
        return getInsBlockList().size();
    }

    public DebugInfo getDebugInfo() {
        return getCodeItem().getDebugInfo();
    }

    public DebugSequence getDebugSequence() {
        DebugInfo debugInfo = getDebugInfo();
        if (debugInfo != null) {
            return debugInfo.getDebugSequence();
        }
        return null;
    }

    public List<Register> getLocalFreeRegisters(final int i) {
        final RegistersTable registersTable = getRegistersTable();
        final int localRegistersCount = registersTable.getLocalRegistersCount();
        List<Register> uniqueList = CollectionUtil.toUniqueList(FilterIterator.of(new ArraySupplierIterator(new ArraySupplier<Register>() { // from class: com.reandroid.dex.data.InstructionList.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.reandroid.common.ArraySupplier
            public Register get(int i2) {
                return new Register(i2, false, registersTable);
            }

            @Override // com.reandroid.common.CountSupplier
            public int getCount() {
                return localRegistersCount;
            }
        }), new Predicate() { // from class: hr6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return InstructionList.o(this.b, localRegistersCount, i, (Register) obj);
            }
        }));
        uniqueList.sort(CompareUtil.getComparableComparator());
        return uniqueList;
    }

    public MethodDef getMethodDef() {
        return getCodeItem().getMethodDef();
    }

    public DebugInfo getOrCreateDebugInfo() {
        return getCodeItem().getOrCreateDebugInfo();
    }

    public DebugSequence getOrCreateDebugSequence() {
        return getOrCreateDebugInfo().getDebugSequence();
    }

    public RegistersTable getRegistersTable() {
        return getCodeItem();
    }

    public int hashCode() {
        return this.insBlockList.hashCode();
    }

    public boolean isEmpty() {
        return getInsBlockList().size() == 0;
    }

    public boolean isFreeRegister(int i, int i2) {
        Iterator<RegistersIterator> registersIterators = getRegistersIterators(i2);
        while (registersIterators.hasNext()) {
            for (RegisterReference registerReference : registersIterators.next()) {
                if (registerReference.getValue() == i) {
                    return registerReference.getRegisterType() == RegisterType.WRITE;
                }
            }
        }
        return i != getRegistersTable().getLocalRegistersCount();
    }

    public boolean isLonelyInTryCatch(Ins ins) {
        Iterator extraLines = ins.getExtraLines(ExceptionHandler.TryStartLabel.class);
        boolean z = false;
        if (!extraLines.hasNext()) {
            return false;
        }
        InsBlockList insBlockList = getInsBlockList();
        insBlockList.link(extraLines);
        int codeUnits = ins.getCodeUnits();
        while (extraLines.hasNext()) {
            if (((ExceptionHandler.TryStartLabel) extraLines.next()).getHandler().getCodeUnit() <= codeUnits) {
                z = true;
                break;
            }
        }
        insBlockList.unlink(extraLines);
        return z;
    }

    public <T1 extends Ins> Iterator<T1> iterator(final Opcode<T1> opcode, final Predicate<? super T1> predicate) {
        return ComputeIterator.of(iterator(), new Function() { // from class: ir6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return InstructionList.k(opcode, predicate, (Ins) obj);
            }
        });
    }

    public Iterator<Ins> iteratorByAddress(int i, int i2) {
        Ins atAddress = getAtAddress(i);
        if (atAddress == null) {
            return EmptyIterator.of();
        }
        return iterator(atAddress.getIndex(), getAtAddress(i + i2).getIndex() - atAddress.getIndex());
    }

    public void linkExtraLines() {
        getInsBlockList().link();
    }

    public void merge(InstructionList instructionList) {
        getInsBlockList().merge(instructionList.getInsBlockList());
        getInsBlockList().updateCodeUnits();
    }

    public void moveTo(Ins ins, int i) {
        int index = ins.getIndex();
        if (i == index) {
            return;
        }
        if (index < 0) {
            b1e.a("Removed ins, negative index: ", i);
            return;
        }
        if (i < 0) {
            b1e.a("Negative index: ", i);
            return;
        }
        if (i >= getCount()) {
            kac.a("Size = ", getCount(), ", ", i);
            return;
        }
        InsBlockList insBlockList = getInsBlockList();
        Object objLinkLocked = insBlockList.linkLocked();
        insBlockList.moveTo(ins, i);
        ins.transferExtraLinesTo(get(index));
        insBlockList.unlinkLocked(objLinkLocked);
    }

    public void onEditing(InstructionList instructionList) {
        getInsBlockList().onEditingInternal(instructionList.getInsBlockList());
    }

    public void onRefreshed() {
        super.onRefreshed();
        getInsBlockList().unlink();
    }

    public boolean remove(Ins ins, boolean z) {
        InsBlockList insBlockList = getInsBlockList();
        if (!insBlockList.containsExact(ins)) {
            return false;
        }
        Object objLinkLocked = insBlockList.linkLocked();
        if (!z && isLonelyInTryCatch(ins)) {
            return replaceWithNop(ins) != null;
        }
        Ins orCreateNullInstruction = get(ins.getIndex() + 1);
        if (orCreateNullInstruction == null && ins.hasExtraLines()) {
            orCreateNullInstruction = insBlockList.getOrCreateNullInstruction();
        }
        if (orCreateNullInstruction != null) {
            ins.transferExtraLinesTo(orCreateNullInstruction);
        }
        insBlockList.remove(ins);
        ins.setParent((Block) null);
        ins.setIndex(-1);
        insBlockList.unlinkLocked(objLinkLocked);
        return true;
    }

    public void replace(Ins ins, Ins ins2) {
        if (ins == ins2) {
            return;
        }
        InsBlockList insBlockList = getInsBlockList();
        if (!insBlockList.containsExact(ins)) {
            w01.a("Not a member of this instruction list");
            return;
        }
        Object objLinkLocked = insBlockList.linkLocked();
        insBlockList.set(ins.getIndex(), ins2);
        ins.transferExtraLinesTo(ins2);
        ins.setParent((Block) null);
        ins.setIndex(-1);
        insBlockList.unlinkLocked(objLinkLocked);
    }

    public void replaceKeys(Key key, Key key2) {
        Iterator<Ins> it = iterator();
        while (it.hasNext()) {
            it.next().replaceKeys(key, key2);
        }
    }

    public InsNop replaceWithNop(Ins ins) {
        return (InsNop) replace(ins, Opcode.NOP);
    }

    public void toSmali(SmaliMethod smaliMethod) {
        InsBlockList insBlockList = getInsBlockList();
        Object objLinkLocked = insBlockList.linkLocked();
        SmaliCodeSet codeSet = smaliMethod.getCodeSet();
        int size = insBlockList.size();
        for (int i = 0; i < size; i++) {
            get(i).toSmali(codeSet);
        }
        insBlockList.unlinkLocked(objLinkLocked);
    }

    public String toString() {
        StringWriter stringWriter = new StringWriter();
        SmaliWriter smaliWriter = new SmaliWriter(stringWriter);
        smaliWriter.indentPlus();
        try {
            append(smaliWriter);
            smaliWriter.close();
            return stringWriter.toString();
        } catch (IOException e) {
            return e.toString();
        }
    }

    public Iterator<IdItem> usedIds() {
        return new IterableIterator<Ins, IdItem>(iterator()) { // from class: com.reandroid.dex.data.InstructionList.2
            public Iterator<IdItem> iterator(Ins ins) {
                return ins.usedIds();
            }
        };
    }

    private Iterator<RegistersIterator> getRegistersIterators() {
        return getRegistersIterators(0);
    }

    public <T1 extends Ins> Iterator<T1> iterator(Opcode<T1> opcode) {
        return iterator(opcode, (Predicate) null);
    }

    @Override // java.lang.Iterable
    public Iterator<Ins> iterator() {
        return getInsBlockList().iterator();
    }

    public Iterator<Ins> iterator(int i, int i2) {
        return getInsBlockList().iterator(i, i2);
    }

    public Iterator<Ins> iterator(int i) {
        return getInsBlockList().iterator(i, getCount() - i);
    }

    public void add(int i, Ins ins) {
        add(true, i, ins);
    }

    public void add(Ins ins) {
        add(getInsBlockList().size(), ins);
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0020  */
    public void fromSmali(SmaliCodeSet smaliCodeSet) {
        int count;
        int addressOffset = smaliCodeSet.getAddressOffset();
        if (addressOffset == 0) {
            count = 0;
        } else {
            Ins atAddress = getAtAddress(smaliCodeSet.getAddressOffset());
            if (atAddress != null) {
                count = atAddress.getIndex();
            } else if (addressOffset >= getCodeUnits()) {
                count = getCount();
            } else {
                count = 0;
            }
        }
        fromSmali(count, smaliCodeSet);
    }

    public <T1 extends Ins> T1 replace(Ins ins, Opcode<T1> opcode) {
        if (!contains(ins)) {
            return null;
        }
        T1 t1 = (T1) opcode.newInstance();
        replace(ins, t1);
        return t1;
    }

    public ConstString createStringAt(int i, String str) {
        return createStringAt(i, 0, str);
    }

    public ConstString createStringAt(int i, int i2, String str) {
        return createStringAt(i, i2, StringKey.create(str));
    }

    public ConstString createStringAt(int i, StringKey stringKey) {
        return createStringAt(i, 0, stringKey);
    }

    public ConstNumber createConstIntegerAt(int i, int i2) {
        return createConstIntegerAt(i, 0, i2);
    }

    public void addLocalRegisters(int i) {
        addLocalRegisters(false, i);
    }

    public ConstNumberLong createConstLongAt(int i, long j) {
        return createConstLongAt(i, 0, j);
    }

    public boolean remove(Ins ins) {
        return remove(ins, false);
    }
}

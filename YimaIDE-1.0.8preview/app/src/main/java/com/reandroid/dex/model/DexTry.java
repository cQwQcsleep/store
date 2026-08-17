package com.reandroid.dex.model;

import com.reandroid.dex.base.DexException;
import com.reandroid.dex.data.InstructionList;
import com.reandroid.dex.ins.ExceptionHandler;
import com.reandroid.dex.ins.ExceptionLabel;
import com.reandroid.dex.ins.Ins;
import com.reandroid.dex.ins.TryItem;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.model.DexTry;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.utils.collection.ComputeIterator;
import com.reandroid.utils.collection.EmptyIterator;
import com.reandroid.utils.collection.FilterIterator;
import java.io.IOException;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DexTry extends DexCode {
    private final int address;
    private final DexMethod dexMethod;
    private final TryItem tryItem;

    public DexTry(DexMethod dexMethod, TryItem tryItem, int i) {
        this.dexMethod = dexMethod;
        this.tryItem = tryItem;
        this.address = i;
    }

    public static /* synthetic */ boolean b(DexTry dexTry, Ins ins) {
        dexTry.getClass();
        int address = ins.getAddress();
        return address >= dexTry.getStartAddress() && address < dexTry.getEndAddress();
    }

    public static Iterator<DexTry> create(final DexMethod dexMethod, final int i, Iterator<TryItem> it) {
        return dexMethod == null ? EmptyIterator.of() : ComputeIterator.of(it, new Function() { // from class: or3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DexTry.create(dexMethod, (TryItem) obj, i);
            }
        });
    }

    @Override // com.reandroid.dex.model.Dex, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        TryItem tryItem = getTryItem();
        Iterator<ExceptionHandler> exceptionHandlers = tryItem.getExceptionHandlers();
        if (!exceptionHandlers.hasNext()) {
            smaliWriter.appendComment("Empty try-catch");
            smaliWriter.newLine();
            return;
        }
        Object obj = null;
        Object obj2 = null;
        while (exceptionHandlers.hasNext()) {
            ExceptionLabel startLabel = exceptionHandlers.next().getStartLabel();
            if (!startLabel.isEqualExtraLine(obj2)) {
                smaliWriter.newLine();
                startLabel.appendExtra(smaliWriter);
                obj2 = startLabel;
            }
        }
        smaliWriter.indentPlus();
        smaliWriter.appendAll(getInstructions(), true);
        smaliWriter.indentMinus();
        Iterator<ExceptionHandler> exceptionHandlers2 = tryItem.getExceptionHandlers();
        while (exceptionHandlers2.hasNext()) {
            ExceptionLabel endLabel = exceptionHandlers2.next().getEndLabel();
            if (!endLabel.isEqualExtraLine(obj)) {
                smaliWriter.newLine();
                endLabel.appendExtra(smaliWriter);
                obj = endLabel;
            }
        }
        Iterator<ExceptionHandler> exceptionHandlers3 = tryItem.getExceptionHandlers();
        while (exceptionHandlers3.hasNext()) {
            ExceptionLabel handlerLabel = exceptionHandlers3.next().getHandlerLabel();
            smaliWriter.newLine();
            handlerLabel.appendExtra(smaliWriter);
        }
    }

    public DexCatch getCatch(TypeKey typeKey) {
        return create(getTryItem().getExceptionHandler(typeKey));
    }

    public DexCatch getCatchAll() {
        return create(getTryItem().getCatchAllHandler());
    }

    public int getCatchCount() {
        TryItem tryItem = getTryItem();
        boolean zHasCatchAllHandler = tryItem.hasCatchAllHandler();
        return (zHasCatchAllHandler ? 1 : 0) + tryItem.getCatchTypedHandlersCount();
    }

    public Iterator<DexCatch> getCatches(int i) {
        return ComputeIterator.of(getTryItem().getExceptionHandlersForAddress(i), new Function() { // from class: nr3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.create((ExceptionHandler) obj);
            }
        });
    }

    @Override // com.reandroid.dex.model.DexCode
    public DexMethod getDexMethod() {
        return this.dexMethod;
    }

    public int getEndAddress() {
        TryItem tryItem = getTryItem();
        return tryItem.getStartAddress() + tryItem.getCatchCodeUnit();
    }

    public DexInstruction getFirst() {
        return (DexInstruction) CollectionUtil.getFirst(getInstructions());
    }

    public Iterator<DexInstruction> getInstructions() {
        InstructionList instructionList = getDexMethod().getDefinition().getInstructionList();
        if (instructionList == null) {
            return EmptyIterator.of();
        }
        return DexInstruction.create(getDexMethod(), (Iterator<Ins>) FilterIterator.of(instructionList.iterator(), new Predicate() { // from class: mr3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return DexTry.b(this.b, (Ins) obj);
            }
        }));
    }

    public DexInstruction getLast() {
        return (DexInstruction) CollectionUtil.getLast(getInstructions());
    }

    public DexCatch getOrCreateCatchAll() {
        boolean zHasCatchAllHandler = getTryItem().hasCatchAllHandler();
        DexCatch dexCatchCreate = create(getTryItem().getOrCreateCatchAll());
        if (!zHasCatchAllHandler) {
            dexCatchCreate.setCatchAddress(getEndAddress());
        }
        return dexCatchCreate;
    }

    public int getStartAddress() {
        return getTryItem().getStartAddress();
    }

    public TryItem getTryItem() {
        return this.tryItem;
    }

    @Override // com.reandroid.dex.model.Dex
    public boolean isRemoved() {
        return getDexMethod().isRemoved() || getTryItem().isRemoved();
    }

    @Override // com.reandroid.dex.model.Dex
    public void removeSelf() {
        getTryItem().removeSelf();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    public void setEndAddress(int i) throws DexException {
        int startAddress = getTryItem().getStartAddress();
        if (i >= startAddress) {
            getTryItem().setCatchCodeUnit(i - startAddress);
            return;
        }
        throw new DexException("Invalid try end address " + i + "<" + startAddress);
    }

    public void setFirst(DexInstruction dexInstruction) {
        setStartAddress(dexInstruction.getAddress());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    public void setLast(DexInstruction dexInstruction) throws DexException {
        setEndAddress(dexInstruction.getAddress() + dexInstruction.getCodeUnits());
    }

    public void setStartAddress(int i) {
        getTryItem().setStartAddress(i);
    }

    public boolean traps(TypeKey typeKey, int i) {
        return getTryItem().traps(typeKey, i);
    }

    public boolean trapsCatchAll() {
        return traps(null, this.address);
    }

    @Override // com.reandroid.dex.model.Dex
    public boolean uses(Key key) {
        Iterator<DexCatch> catches = getCatches();
        while (catches.hasNext()) {
            if (catches.next().uses(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean trapsCatchAll(int i) {
        return traps(null, i);
    }

    public boolean traps(TypeKey typeKey) {
        return traps(typeKey, this.address);
    }

    public DexTry(DexMethod dexMethod, TryItem tryItem) {
        this(dexMethod, tryItem, -1);
    }

    public static Iterator<DexTry> create(DexMethod dexMethod, Iterator<TryItem> it) {
        return create(dexMethod, -1, it);
    }

    public DexCatch create(ExceptionHandler exceptionHandler) {
        if (exceptionHandler != null) {
            return new DexCatch(this, exceptionHandler);
        }
        return null;
    }

    public Iterator<DexCatch> getCatches() {
        return getCatches(this.address);
    }

    public static DexTry create(DexMethod dexMethod, TryItem tryItem) {
        return create(dexMethod, tryItem, -1);
    }

    public static DexTry create(DexMethod dexMethod, TryItem tryItem, int i) {
        if (dexMethod == null || tryItem == null) {
            return null;
        }
        return new DexTry(dexMethod, tryItem, i);
    }
}

package com.reandroid.graph;

import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.dex.ins.Opcode;
import com.reandroid.dex.key.FieldKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.PrimitiveKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.model.DexClass;
import com.reandroid.dex.model.DexClassRepository;
import com.reandroid.dex.model.DexField;
import com.reandroid.dex.model.DexInstruction;
import com.reandroid.dex.model.DexMethod;
import com.reandroid.graph.InlineFieldIntResolver;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class InlineFieldIntResolver extends BaseDexClassProcessor {
    private int mResolvedCount;
    private final Predicate<Integer> resourceIdChecker;

    public InlineFieldIntResolver(DexClassRepository dexClassRepository, TableBlock tableBlock) {
        this(dexClassRepository, createChecker(tableBlock));
    }

    public static /* synthetic */ boolean a(TableBlock tableBlock, Integer num) {
        int iIntValue = num.intValue();
        return PackageBlock.isResourceId(iIntValue) && tableBlock.getResource(iIntValue) != null;
    }

    private static Predicate<Integer> createChecker(final TableBlock tableBlock) {
        return new Predicate() { // from class: aq6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return InlineFieldIntResolver.a(tableBlock, (Integer) obj);
            }
        };
    }

    private static Predicate<Integer> createDefaultChecker() {
        return new Predicate() { // from class: zp6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return PackageBlock.isResourceId(((Integer) obj).intValue());
            }
        };
    }

    private Key getValueFromStaticField(DexInstruction dexInstruction) {
        DexField dexField;
        if (!dexInstruction.is(Opcode.SGET)) {
            return null;
        }
        if (TypeKey.TYPE_I.equals(((FieldKey) dexInstruction.getKey()).getType()) && (dexField = (DexField) dexInstruction.findDeclaration()) != null) {
            return dexField.getStaticValue();
        }
        return null;
    }

    private void reset() {
        this.mResolvedCount = 0;
    }

    private void resolve(DexInstruction dexInstruction, Key key) {
        if ((key instanceof PrimitiveKey) && ((PrimitiveKey) key).isInteger()) {
            int iValue = ((PrimitiveKey.IntegerKey) key).value();
            if (this.resourceIdChecker.test(Integer.valueOf(iValue))) {
                Key key2 = dexInstruction.getKey();
                int register = dexInstruction.getRegister();
                DexInstruction dexInstructionReplace = dexInstruction.replace(Opcode.CONST);
                dexInstructionReplace.setRegister(register);
                dexInstructionReplace.setAsInteger(iValue);
                this.mResolvedCount++;
                if (isDebugEnabled()) {
                    debug(key2 + " WITH " + dexInstructionReplace.toString());
                }
            }
        }
    }

    private void scanClass(DexClass dexClass) {
        Iterator<DexMethod> declaredMethods = dexClass.getDeclaredMethods();
        while (declaredMethods.hasNext()) {
            scanMethod(declaredMethods.next());
        }
    }

    private void scanMethod(DexMethod dexMethod) {
        Iterator<DexInstruction> instructions = dexMethod.getInstructions();
        while (instructions.hasNext()) {
            DexInstruction next = instructions.next();
            Key valueFromStaticField = getValueFromStaticField(next);
            if (valueFromStaticField != null) {
                resolve(next, valueFromStaticField);
            }
        }
    }

    @Override // com.reandroid.graph.GraphTask
    public void apply() {
        verbose("Resolving inline resource ids ..");
        reset();
        Iterator<DexClass> dexClasses = getClassRepository().getDexClasses();
        while (dexClasses.hasNext()) {
            scanClass(dexClasses.next());
        }
        verbose("Resolved ids count: " + getResolvedCount());
    }

    public int getResolvedCount() {
        return this.mResolvedCount;
    }

    public InlineFieldIntResolver(DexClassRepository dexClassRepository, Predicate<Integer> predicate) {
        super(dexClassRepository);
        this.resourceIdChecker = predicate;
    }

    public InlineFieldIntResolver(DexClassRepository dexClassRepository) {
        this(dexClassRepository, createDefaultChecker());
    }
}

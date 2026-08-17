package org.jetbrains.kotlin.codegen.optimization.boxing;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter;
import org.jetbrains.kotlin.codegen.optimization.common.StrictBasicValue;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.TypeInsnNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;
import org.jetbrains.org.objectweb.asm.tree.analysis.Value;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0015\b&\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\"\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u000fH\u0014J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0011H\u0004J \u0010\u0019\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u000e2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00110\u001bH\u0016J\u0012\u0010\u001c\u001a\u00020\u0017*\b\u0012\u0004\u0012\u00020\u00110\u001bH\u0002J\u001a\u0010\u001d\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0011H\u0016J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0018\u001a\u00020\u0011H\u0014J\u0010\u0010 \u001a\u00020\u001f2\u0006\u0010\u0012\u001a\u00020\u000eH\u0002J\u0018\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u00112\u0006\u0010#\u001a\u00020\u0011H\u0016J\u0016\u0010$\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u00112\u0006\u0010#\u001a\u00020\u0011J\u0016\u0010%\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u00112\u0006\u0010#\u001a\u00020\u0011J \u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u00112\u0006\u0010#\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\u001fH\u0002J \u0010'\u001a\u00020\u00112\u0006\u0010(\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\u001fH\u0002J\u0010\u0010*\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000bH$J \u0010+\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010,\u001a\u00020\u0014H$J \u0010-\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010.\u001a\u00020\u000b2\u0006\u0010/\u001a\u00020\u000bH$J \u00100\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010.\u001a\u00020\u000b2\u0006\u0010/\u001a\u00020\u000bH$J\u0010\u00101\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000bH$J\u0010\u00102\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000bH$J\u0018\u00103\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\u000b2\u0006\u0010#\u001a\u00020\u000bH$R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\b\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tj\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b`\fX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\tj\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f`\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/boxing/BoxingInterpreter;", "Lorg/jetbrains/kotlin/codegen/optimization/common/OptimizationBasicInterpreter;", "insnList", "Lorg/jetbrains/org/objectweb/asm/tree/InsnList;", "generationState", "Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/InsnList;Lorg/jetbrains/kotlin/codegen/state/GenerationState;)V", "boxingPlaces", "Ljava/util/HashMap;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/optimization/boxing/BoxedBasicValue;", "Lkotlin/collections/HashMap;", "progressionIterators", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "Lorg/jetbrains/kotlin/codegen/optimization/boxing/ProgressionIteratorBasicValue;", "createNewBoxing", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "insn", ModuleXmlParser.TYPE, "Lorg/jetbrains/org/objectweb/asm/Type;", "progressionIterator", "checkUsedValue", Argument.Delimiters.none, "value", "naryOperation", "values", Argument.Delimiters.none, "markBoxedArgumentValues", "unaryOperation", "isExactValue", Argument.Delimiters.none, "isCastToProgression", "merge", "v", "w", "mergeLocalVariableValues", "mergeStackValues", "isLocalVariable", "mergeBoxedHazardous", "boxed", "other", "onNewBoxedValue", "onUnboxing", "resultType", "onAreEqual", "value1", "value2", "onCompareTo", "onMethodCallWithBoxedValue", "onMergeFail", "onMergeSuccess", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class BoxingInterpreter extends OptimizationBasicInterpreter {
    private final HashMap<Integer, BoxedBasicValue> boxingPlaces;
    private final GenerationState generationState;
    private final InsnList insnList;
    private final HashMap<AbstractInsnNode, ProgressionIteratorBasicValue> progressionIterators;

    public BoxingInterpreter(InsnList insnList, GenerationState generationState) {
        insnList.getClass();
        generationState.getClass();
        this.insnList = insnList;
        this.generationState = generationState;
        this.boxingPlaces = new HashMap<>();
        this.progressionIterators = new HashMap<>();
    }

    private final boolean isCastToProgression(AbstractInsnNode insn) {
        insn.getOpcode();
        return SetsKt.setOf(new String[]{"kotlin/ranges/CharProgression", "kotlin/ranges/IntProgression", "kotlin/ranges/LongProgression"}).contains(((TypeInsnNode) insn).desc);
    }

    private final void markBoxedArgumentValues(List<? extends BasicValue> list) {
        for (BasicValue basicValue : list) {
            if (basicValue instanceof BoxedBasicValue) {
                onMethodCallWithBoxedValue((BoxedBasicValue) basicValue);
            }
        }
    }

    private final BasicValue merge(BasicValue v, BasicValue w, boolean isLocalVariable) {
        StrictBasicValue strictBasicValue = StrictBasicValue.UNINITIALIZED_VALUE;
        if (v == strictBasicValue || w == strictBasicValue) {
            return strictBasicValue;
        }
        if (!(v instanceof BoxedBasicValue)) {
            if (w instanceof BoxedBasicValue) {
                return mergeBoxedHazardous((BoxedBasicValue) w, v, isLocalVariable);
            }
            BasicValue basicValueMerge = super.merge(v, w);
            basicValueMerge.getClass();
            return basicValueMerge;
        }
        if (!(w instanceof BoxedBasicValue)) {
            return mergeBoxedHazardous((BoxedBasicValue) v, w, isLocalVariable);
        }
        BoxedBasicValue boxedBasicValue = (BoxedBasicValue) v;
        BoxedBasicValue boxedBasicValue2 = (BoxedBasicValue) w;
        onMergeSuccess(boxedBasicValue, boxedBasicValue2);
        if (v instanceof TaintedBoxedValue) {
            return v;
        }
        if (w instanceof TaintedBoxedValue) {
            return w;
        }
        return !Intrinsics.areEqual(boxedBasicValue.getType(), boxedBasicValue2.getType()) ? mergeBoxedHazardous(boxedBasicValue, w, isLocalVariable) : v;
    }

    private final BasicValue mergeBoxedHazardous(BoxedBasicValue boxed, BasicValue other, boolean isLocalVariable) {
        if (isLocalVariable) {
            return boxed.taint();
        }
        onMergeFail(boxed);
        if (other instanceof BoxedBasicValue) {
            onMergeFail((BoxedBasicValue) other);
        }
        return boxed;
    }

    public final void checkUsedValue(BasicValue value) {
        value.getClass();
        if (value instanceof TaintedBoxedValue) {
            onMergeFail((BoxedBasicValue) value);
        }
    }

    public BasicValue createNewBoxing(AbstractInsnNode insn, Type type, ProgressionIteratorBasicValue progressionIterator) {
        insn.getClass();
        type.getClass();
        HashMap<Integer, BoxedBasicValue> map = this.boxingPlaces;
        Integer numValueOf = Integer.valueOf(this.insnList.indexOf(insn));
        BoxedBasicValue cleanBoxedValue = map.get(numValueOf);
        if (cleanBoxedValue == null) {
            cleanBoxedValue = new CleanBoxedValue(type, insn, progressionIterator, this.generationState);
            onNewBoxedValue(cleanBoxedValue);
            map.put(numValueOf, cleanBoxedValue);
        }
        return cleanBoxedValue;
    }

    public boolean isExactValue(BasicValue value) {
        value.getClass();
        if ((value instanceof ProgressionIteratorBasicValue) || (value instanceof CleanBoxedValue)) {
            return true;
        }
        if (value.getType() == null) {
            return false;
        }
        Type type = value.getType();
        type.getClass();
        return BoxingInterpreterKt.isProgressionClass(type);
    }

    public final BasicValue mergeLocalVariableValues(BasicValue v, BasicValue w) {
        v.getClass();
        w.getClass();
        return merge(v, w, true);
    }

    public final BasicValue mergeStackValues(BasicValue v, BasicValue w) {
        v.getClass();
        w.getClass();
        return merge(v, w, false);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
    public BasicValue naryOperation(AbstractInsnNode insn, List<? extends BasicValue> values) throws AnalyzerException {
        insn.getClass();
        values.getClass();
        Iterator<T> it = values.iterator();
        while (it.hasNext()) {
            checkUsedValue((BasicValue) it.next());
        }
        BasicValue basicValueNaryOperation = super.naryOperation(insn, values);
        BasicValue basicValue = (BasicValue) CollectionsKt.firstOrNull(values);
        if (basicValue != null) {
            if (BoxingInterpreterKt.isBoxing(insn, this.generationState)) {
                markBoxedArgumentValues(values);
                Type type = basicValueNaryOperation.getType();
                type.getClass();
                return createNewBoxing(insn, type, null);
            }
            if (BoxingInterpreterKt.isUnboxing(insn, this.generationState) && (basicValue instanceof BoxedBasicValue)) {
                Type type2 = basicValueNaryOperation.getType();
                type2.getClass();
                onUnboxing(insn, (BoxedBasicValue) basicValue, type2);
                return basicValueNaryOperation;
            }
            if (!BoxingInterpreterKt.isIteratorMethodCall(insn)) {
                if (BoxingInterpreterKt.isNextMethodCallOfProgressionIterator(insn, values)) {
                    ProgressionIteratorBasicValue progressionIteratorBasicValue = basicValue instanceof ProgressionIteratorBasicValue ? (ProgressionIteratorBasicValue) basicValue : null;
                    if (progressionIteratorBasicValue != null) {
                        return createNewBoxing(insn, progressionIteratorBasicValue.getBoxedElementType(), progressionIteratorBasicValue);
                    }
                    x01.a("firstArg should be progression iterator");
                    return null;
                }
                if (BoxingInterpreterKt.isAreEqualIntrinsicForSameTypedBoxedValues(insn, values) && BoxingInterpreterKt.canValuesBeUnboxedForAreEqual(values, this.generationState)) {
                    BasicValue basicValue2 = values.get(0);
                    basicValue2.getClass();
                    BasicValue basicValue3 = values.get(1);
                    basicValue3.getClass();
                    onAreEqual(insn, (BoxedBasicValue) basicValue2, (BoxedBasicValue) basicValue3);
                    return basicValueNaryOperation;
                }
                if (!BoxingInterpreterKt.isJavaLangComparableCompareToForSameTypedBoxedValues(insn, values)) {
                    markBoxedArgumentValues(values);
                    return basicValueNaryOperation;
                }
                BasicValue basicValue4 = values.get(0);
                basicValue4.getClass();
                BasicValue basicValue5 = values.get(1);
                basicValue5.getClass();
                onCompareTo(insn, (BoxedBasicValue) basicValue4, (BoxedBasicValue) basicValue5);
                return basicValueNaryOperation;
            }
            markBoxedArgumentValues(values);
            Type type3 = basicValue.getType();
            type3.getClass();
            boolean zIsProgressionClass = BoxingInterpreterKt.isProgressionClass(type3);
            HashMap<AbstractInsnNode, ProgressionIteratorBasicValue> map = this.progressionIterators;
            if (zIsProgressionClass) {
                ProgressionIteratorBasicValue progressionIteratorBasicValueByProgressionClassType = map.get(insn);
                if (progressionIteratorBasicValueByProgressionClassType == null) {
                    progressionIteratorBasicValueByProgressionClassType = ProgressionIteratorBasicValue.INSTANCE.byProgressionClassType(insn, type3);
                    progressionIteratorBasicValueByProgressionClassType.getClass();
                    map.put(insn, progressionIteratorBasicValueByProgressionClassType);
                }
                return progressionIteratorBasicValueByProgressionClassType;
            }
            ProgressionIteratorBasicValue progressionIteratorBasicValue2 = map.get(insn);
            if (progressionIteratorBasicValue2 != null) {
                progressionIteratorBasicValue2.taint();
            }
        }
        return basicValueNaryOperation;
    }

    public abstract void onAreEqual(AbstractInsnNode insn, BoxedBasicValue value1, BoxedBasicValue value2);

    public abstract void onCompareTo(AbstractInsnNode insn, BoxedBasicValue value1, BoxedBasicValue value2);

    public abstract void onMergeFail(BoxedBasicValue value);

    public abstract void onMergeSuccess(BoxedBasicValue v, BoxedBasicValue w);

    public abstract void onMethodCallWithBoxedValue(BoxedBasicValue value);

    public abstract void onNewBoxedValue(BoxedBasicValue value);

    public abstract void onUnboxing(AbstractInsnNode insn, BoxedBasicValue value, Type resultType);

    @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
    public BasicValue unaryOperation(AbstractInsnNode insn, BasicValue value) {
        insn.getClass();
        value.getClass();
        checkUsedValue(value);
        return (insn.getOpcode() == 192 && isExactValue(value) && !isCastToProgression(insn)) ? value : super.unaryOperation(insn, value);
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
    public BasicValue merge(BasicValue v, BasicValue w) {
        v.getClass();
        w.getClass();
        return mergeStackValues(v, w);
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
    /* JADX INFO: renamed from: naryOperation */
    public /* bridge */ /* synthetic */ Value mo53naryOperation(AbstractInsnNode abstractInsnNode, List list) {
        return naryOperation(abstractInsnNode, (List<? extends BasicValue>) list);
    }
}

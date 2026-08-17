package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.StackValue;
import org.jetbrains.kotlin.codegen.state.KotlinTypeMapperBase;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.AsmTypes;
import org.jetbrains.org.objectweb.asm.Label;
import org.jetbrains.org.objectweb.asm.MethodVisitor;
import org.jetbrains.org.objectweb.asm.commons.InstructionAdapter;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002$%B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u0002J\u000e\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005J\u001e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0016J@\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\b\u0010\u001b\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0016J&\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020!2\u0006\u0010\"\u001a\u00020#R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\f¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/LocalVarRemapper;", Argument.Delimiters.none, "params", "Lorg/jetbrains/kotlin/codegen/inline/Parameters;", "additionalShift", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/codegen/inline/Parameters;I)V", "actualParamsSize", "remapValues", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/StackValue;", "[Lorg/jetbrains/kotlin/codegen/StackValue;", "doRemap", "Lorg/jetbrains/kotlin/codegen/inline/LocalVarRemapper$RemapInfo;", "index", "remap", "visitIincInsn", Argument.Delimiters.none, "var", "increment", "mv", "Lorg/jetbrains/org/objectweb/asm/MethodVisitor;", "visitLocalVariable", ModuleXmlParser.NAME, Argument.Delimiters.none, "desc", "signature", "start", "Lorg/jetbrains/org/objectweb/asm/Label;", "end", "visitVarInsn", "opcode", "Lorg/jetbrains/org/objectweb/asm/commons/InstructionAdapter;", "typeMapper", "Lorg/jetbrains/kotlin/codegen/state/KotlinTypeMapperBase;", "RemapStatus", "RemapInfo", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class LocalVarRemapper {
    private final int actualParamsSize;
    private final int additionalShift;
    private final Parameters params;
    private final StackValue[] remapValues;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/LocalVarRemapper$RemapStatus;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "SHIFT", "REMAPPED", "FAIL", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum RemapStatus {
        SHIFT,
        REMAPPED,
        FAIL;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<RemapStatus> getEntries() {
            return $ENTRIES;
        }
    }

    public LocalVarRemapper(Parameters parameters, int i) {
        int size;
        parameters.getClass();
        this.params = parameters;
        this.additionalShift = i;
        this.remapValues = new StackValue[parameters.getArgsSizeOnStack()];
        int i2 = 0;
        for (ParameterInfo parameterInfo : parameters) {
            int declarationSlot = this.params.getDeclarationSlot(parameterInfo);
            boolean zIsSkippedOrRemapped = parameterInfo.isSkippedOrRemapped();
            StackValue[] stackValueArr = this.remapValues;
            if (zIsSkippedOrRemapped) {
                stackValueArr[declarationSlot] = parameterInfo.isRemapped() ? parameterInfo.getRemapValue() : null;
                if (CapturedParamInfo.INSTANCE.isSynthetic(parameterInfo)) {
                    size = parameterInfo.getType().getSize();
                }
            } else {
                stackValueArr[declarationSlot] = new StackValue.Local(i2, AsmTypes.OBJECT_TYPE, null);
                size = parameterInfo.getType().getSize();
            }
            i2 += size;
        }
        this.actualParamsSize = i2;
    }

    private final RemapInfo doRemap(int index) {
        int argsSizeOnStack;
        if (index < this.params.getArgsSizeOnStack()) {
            ParameterInfo parameterByDeclarationSlot = this.params.getParameterByDeclarationSlot(index);
            StackValue stackValue = this.remapValues[index];
            if (parameterByDeclarationSlot.getIsSkipped() || stackValue == null) {
                return new RemapInfo(parameterByDeclarationSlot, null, null, 6, null);
            }
            if (parameterByDeclarationSlot.isRemapped()) {
                return new RemapInfo(parameterByDeclarationSlot, stackValue, RemapStatus.REMAPPED);
            }
            argsSizeOnStack = ((StackValue.Local) stackValue).index;
        } else {
            argsSizeOnStack = index + (this.actualParamsSize - this.params.getArgsSizeOnStack());
        }
        return new RemapInfo(null, new StackValue.Local(argsSizeOnStack + this.additionalShift, AsmTypes.OBJECT_TYPE, null), RemapStatus.SHIFT);
    }

    public final RemapInfo remap(int index) {
        RemapInfo remapInfoDoRemap = doRemap(index);
        if (RemapStatus.FAIL != remapInfoDoRemap.status) {
            return remapInfoDoRemap;
        }
        ParameterInfo parameterInfo = remapInfoDoRemap.parameterInfo;
        parameterInfo.getClass();
        throw new RuntimeException("Trying to access skipped parameter: " + parameterInfo.getType() + " at " + index);
    }

    public final void visitIincInsn(int var, int increment, MethodVisitor mv) {
        mv.getClass();
        RemapInfo remapInfoRemap = remap(var);
        StackValue stackValue = remapInfoRemap.value;
        if (stackValue instanceof StackValue.Local) {
            mv.visitIincInsn(((StackValue.Local) stackValue).index, increment);
        } else {
            pe1.a("Remapped value should be a local: ", remapInfoRemap.value);
        }
    }

    public final void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index, MethodVisitor mv) {
        name.getClass();
        desc.getClass();
        start.getClass();
        end.getClass();
        mv.getClass();
        RemapInfo remapInfoDoRemap = doRemap(index);
        if (RemapStatus.SHIFT == remapInfoDoRemap.status) {
            StackValue stackValue = remapInfoDoRemap.value;
            stackValue.getClass();
            mv.visitLocalVariable(name, desc, signature, start, end, ((StackValue.Local) stackValue).index);
        }
    }

    public final void visitVarInsn(int opcode, int var, InstructionAdapter mv, KotlinTypeMapperBase typeMapper) {
        mv.getClass();
        typeMapper.getClass();
        RemapInfo remapInfoRemap = remap(var);
        StackValue stackValue = remapInfoRemap.value;
        if (!(stackValue instanceof StackValue.Local)) {
            stackValue.getClass();
            ParameterInfo parameterInfo = remapInfoRemap.parameterInfo;
            parameterInfo.getClass();
            stackValue.put(parameterInfo.getType(), null, mv, typeMapper);
            return;
        }
        boolean zIsStoreInstruction = InlineCodegenUtilsKt.isStoreInstruction(opcode);
        if (remapInfoRemap.parameterInfo != null) {
            opcode = ((StackValue.Local) stackValue).type.getOpcode(zIsStoreInstruction ? 54 : 21);
        }
        StackValue.Local local = (StackValue.Local) stackValue;
        mv.visitVarInsn(opcode, local.index);
        ParameterInfo parameterInfo2 = remapInfoRemap.parameterInfo;
        if (parameterInfo2 == null || zIsStoreInstruction) {
            return;
        }
        StackValue.coerce(local.type, parameterInfo2.getType(), mv);
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B'\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u0092\u0002\u0002\b\n¢\u0006\u0002\n\u0000R\u0017\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\n¢\u0006\u0002\n\u0000R\u0015\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004\u0092\u0002\u0002\b\n¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/LocalVarRemapper$RemapInfo;", Argument.Delimiters.none, "parameterInfo", "Lorg/jetbrains/kotlin/codegen/inline/ParameterInfo;", "value", "Lorg/jetbrains/kotlin/codegen/StackValue;", "status", "Lorg/jetbrains/kotlin/codegen/inline/LocalVarRemapper$RemapStatus;", "<init>", "(Lorg/jetbrains/kotlin/codegen/inline/ParameterInfo;Lorg/jetbrains/kotlin/codegen/StackValue;Lorg/jetbrains/kotlin/codegen/inline/LocalVarRemapper$RemapStatus;)V", "Lkotlin/jvm/JvmField;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class RemapInfo {
        public final ParameterInfo parameterInfo;
        public final RemapStatus status;
        public final StackValue value;

        public /* synthetic */ RemapInfo(ParameterInfo parameterInfo, StackValue stackValue, RemapStatus remapStatus, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(parameterInfo, (i & 2) != 0 ? null : stackValue, (i & 4) != 0 ? RemapStatus.FAIL : remapStatus);
        }

        public RemapInfo(ParameterInfo parameterInfo, StackValue stackValue, RemapStatus remapStatus) {
            remapStatus.getClass();
            this.parameterInfo = parameterInfo;
            this.value = stackValue;
            this.status = remapStatus;
        }
    }
}

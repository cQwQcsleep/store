package org.jetbrains.kotlin.codegen.optimization.boxing;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.AsmUtil;
import org.jetbrains.kotlin.codegen.optimization.common.StrictBasicValue;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000  2\u00020\u0001:\u0001 B1\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0006\u0010\u0017\u001a\u00020\u0018J\u0014\u0010\u001b\u001a\u00020\u00132\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0096\u0082\u0004J\n\u0010\u001e\u001a\u00020\u001fH\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0019\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u000f¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/boxing/ProgressionIteratorBasicValue;", "Lorg/jetbrains/kotlin/codegen/optimization/common/StrictBasicValue;", "iteratorCallInsn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "nextMethodName", Argument.Delimiters.none, "iteratorType", "Lorg/jetbrains/org/objectweb/asm/Type;", "primitiveElementType", "boxedElementType", "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;Ljava/lang/String;Lorg/jetbrains/org/objectweb/asm/Type;Lorg/jetbrains/org/objectweb/asm/Type;Lorg/jetbrains/org/objectweb/asm/Type;)V", "getIteratorCallInsn", "()Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "getNextMethodName", "()Ljava/lang/String;", "getBoxedElementType", "()Lorg/jetbrains/org/objectweb/asm/Type;", "value", Argument.Delimiters.none, "tainted", "getTainted", "()Z", "taint", Argument.Delimiters.none, "nextMethodDesc", "getNextMethodDesc", "equals", "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "Companion", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ProgressionIteratorBasicValue extends StrictBasicValue {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Type boxedElementType;
    private final AbstractInsnNode iteratorCallInsn;
    private final String nextMethodName;
    private final Type primitiveElementType;
    private boolean tainted;

    private ProgressionIteratorBasicValue(AbstractInsnNode abstractInsnNode, String str, Type type, Type type2, Type type3) {
        super(type);
        this.iteratorCallInsn = abstractInsnNode;
        this.nextMethodName = str;
        this.primitiveElementType = type2;
        this.boxedElementType = type3;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.StrictBasicValue
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other != null && Intrinsics.areEqual(ProgressionIteratorBasicValue.class, other.getClass()) && super.equals(other)) {
            return Intrinsics.areEqual(this.primitiveElementType, ((ProgressionIteratorBasicValue) other).primitiveElementType);
        }
        return false;
    }

    public final Type getBoxedElementType() {
        return this.boxedElementType;
    }

    public final AbstractInsnNode getIteratorCallInsn() {
        return this.iteratorCallInsn;
    }

    public final String getNextMethodDesc() {
        return "()" + this.primitiveElementType.getDescriptor();
    }

    public final String getNextMethodName() {
        return this.nextMethodName;
    }

    public final boolean getTainted() {
        return this.tainted;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.StrictBasicValue
    public int hashCode() {
        return (super.hashCode() * 31) + this.nextMethodName.hashCode();
    }

    public final void taint() {
        this.tainted = true;
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000bH\u0002J\u0018\u0010\r\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000b¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/boxing/ProgressionIteratorBasicValue$Companion;", Argument.Delimiters.none, "<init>", "()V", "progressionIteratorValue", "Lorg/jetbrains/kotlin/codegen/optimization/boxing/ProgressionIteratorBasicValue;", "iteratorCallInsn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "typeName", Argument.Delimiters.none, "valuesPrimitiveType", "Lorg/jetbrains/org/objectweb/asm/Type;", "valuesBoxedType", "byProgressionClassType", "progressionClassType", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final ProgressionIteratorBasicValue progressionIteratorValue(AbstractInsnNode iteratorCallInsn, String typeName, Type valuesPrimitiveType, Type valuesBoxedType) {
            String str = "next" + typeName;
            Type objectType = Type.getObjectType("kotlin/collections/" + typeName + "Iterator");
            objectType.getClass();
            return new ProgressionIteratorBasicValue(iteratorCallInsn, str, objectType, valuesPrimitiveType, valuesBoxedType, null);
        }

        public static /* synthetic */ ProgressionIteratorBasicValue progressionIteratorValue$default(Companion companion, AbstractInsnNode abstractInsnNode, String str, Type type, Type type2, int i, Object obj) {
            if ((i & 8) != 0) {
                type2 = AsmUtil.boxType(type);
                type2.getClass();
            }
            return companion.progressionIteratorValue(abstractInsnNode, str, type, type2);
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        public final ProgressionIteratorBasicValue byProgressionClassType(AbstractInsnNode iteratorCallInsn, Type progressionClassType) {
            iteratorCallInsn.getClass();
            progressionClassType.getClass();
            String className = progressionClassType.getClassName();
            if (className == null) {
                return null;
            }
            switch (className.hashCode()) {
                case -1404642343:
                    if (!className.equals("kotlin.ranges.IntRange")) {
                        return null;
                    }
                    Type type = Type.INT_TYPE;
                    type.getClass();
                    return progressionIteratorValue$default(this, iteratorCallInsn, "Int", type, null, 8, null);
                case -1316009897:
                    if (!className.equals("kotlin.ranges.IntProgression")) {
                        return null;
                    }
                    Type type2 = Type.INT_TYPE;
                    type2.getClass();
                    return progressionIteratorValue$default(this, iteratorCallInsn, "Int", type2, null, 8, null);
                case -421901258:
                    if (!className.equals("kotlin.ranges.LongRange")) {
                        return null;
                    }
                    Type type3 = Type.LONG_TYPE;
                    type3.getClass();
                    return progressionIteratorValue$default(this, iteratorCallInsn, "Long", type3, null, 8, null);
                case 1012823964:
                    if (!className.equals("kotlin.ranges.CharRange")) {
                        return null;
                    }
                    Type type4 = Type.CHAR_TYPE;
                    type4.getClass();
                    return progressionIteratorValue$default(this, iteratorCallInsn, "Char", type4, null, 8, null);
                case 1168491994:
                    if (!className.equals("kotlin.ranges.CharProgression")) {
                        return null;
                    }
                    Type type5 = Type.CHAR_TYPE;
                    type5.getClass();
                    return progressionIteratorValue$default(this, iteratorCallInsn, "Char", type5, null, 8, null);
                case 1668431604:
                    if (!className.equals("kotlin.ranges.LongProgression")) {
                        return null;
                    }
                    Type type6 = Type.LONG_TYPE;
                    type6.getClass();
                    return progressionIteratorValue$default(this, iteratorCallInsn, "Long", type6, null, 8, null);
                default:
                    return null;
            }
        }

        private Companion() {
        }
    }

    public /* synthetic */ ProgressionIteratorBasicValue(AbstractInsnNode abstractInsnNode, String str, Type type, Type type2, Type type3, DefaultConstructorMarker defaultConstructorMarker) {
        this(abstractInsnNode, str, type, type2, type3);
    }
}

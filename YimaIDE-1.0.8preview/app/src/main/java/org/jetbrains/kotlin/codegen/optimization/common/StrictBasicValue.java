package org.jetbrains.kotlin.codegen.optimization.common;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0096\u0082\u0004J\n\u0010\n\u001a\u00020\u000bH\u0096\u0080\u0004J\n\u0010\f\u001a\u00020\rH\u0096\u0080\u0004¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/common/StrictBasicValue;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", ModuleXmlParser.TYPE, "Lorg/jetbrains/org/objectweb/asm/Type;", "<init>", "(Lorg/jetbrains/org/objectweb/asm/Type;)V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "Companion", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class StrictBasicValue extends BasicValue {
    public static final StrictBasicValue UNINITIALIZED_VALUE = new StrictBasicValue(null);
    public static final StrictBasicValue INT_VALUE = new StrictBasicValue(Type.INT_TYPE);
    public static final StrictBasicValue FLOAT_VALUE = new StrictBasicValue(Type.FLOAT_TYPE);
    public static final StrictBasicValue LONG_VALUE = new StrictBasicValue(Type.LONG_TYPE);
    public static final StrictBasicValue DOUBLE_VALUE = new StrictBasicValue(Type.DOUBLE_TYPE);
    public static final StrictBasicValue BOOLEAN_VALUE = new StrictBasicValue(Type.BOOLEAN_TYPE);
    public static final StrictBasicValue CHAR_VALUE = new StrictBasicValue(Type.CHAR_TYPE);
    public static final StrictBasicValue BYTE_VALUE = new StrictBasicValue(Type.BYTE_TYPE);
    public static final StrictBasicValue SHORT_VALUE = new StrictBasicValue(Type.SHORT_TYPE);
    public static final StrictBasicValue REFERENCE_VALUE = new StrictBasicValue(Type.getObjectType("java/lang/Object"));
    public static final StrictBasicValue NULL_VALUE = new StrictBasicValue(Type.getObjectType("java/lang/Object"));

    public StrictBasicValue(Type type) {
        super(type);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other != null && Intrinsics.areEqual(other.getClass(), getClass())) {
            StrictBasicValue strictBasicValue = (StrictBasicValue) other;
            StrictBasicValue strictBasicValue2 = NULL_VALUE;
            if (this != strictBasicValue2 && other != strictBasicValue2) {
                return Intrinsics.areEqual(getType(), strictBasicValue.getType());
            }
        }
        return false;
    }

    public int hashCode() {
        Type type = getType();
        if (type != null) {
            return type.hashCode();
        }
        return 0;
    }

    public String toString() {
        String string;
        if (this == REFERENCE_VALUE) {
            string = "R";
        } else if (this == NULL_VALUE) {
            string = "null";
        } else if (this == UNINITIALIZED_VALUE) {
            string = ".";
        } else {
            string = super.toString();
            string.getClass();
        }
        return getClass().getSimpleName() + '(' + string + ')';
    }
}

package org.jetbrains.kotlin.builtins;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\tH\u0007J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\tH\u0007R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/builtins/PrimitiveType$Companion;", "", "<init>", "()V", "NUMBER_TYPES", "", "Lorg/jetbrains/kotlin/builtins/PrimitiveType;", "getByShortName", "name", "", "getByShortArrayName", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PrimitiveType$Companion {
    public /* synthetic */ PrimitiveType$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @JvmStatic
    public final PrimitiveType getByShortArrayName(String name) {
        name.getClass();
        switch (name.hashCode()) {
            case -901856463:
                if (name.equals("BooleanArray")) {
                    return PrimitiveType.BOOLEAN;
                }
                return null;
            case -763279523:
                if (name.equals("ShortArray")) {
                    return PrimitiveType.SHORT;
                }
                return null;
            case -755911549:
                if (name.equals("CharArray")) {
                    return PrimitiveType.CHAR;
                }
                return null;
            case -74930671:
                if (name.equals("ByteArray")) {
                    return PrimitiveType.BYTE;
                }
                return null;
            case 22374632:
                if (name.equals("DoubleArray")) {
                    return PrimitiveType.DOUBLE;
                }
                return null;
            case 601811914:
                if (name.equals("IntArray")) {
                    return PrimitiveType.INT;
                }
                return null;
            case 948852093:
                if (name.equals("FloatArray")) {
                    return PrimitiveType.FLOAT;
                }
                return null;
            case 2104330525:
                if (name.equals("LongArray")) {
                    return PrimitiveType.LONG;
                }
                return null;
            default:
                return null;
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @JvmStatic
    public final PrimitiveType getByShortName(String name) {
        name.getClass();
        switch (name.hashCode()) {
            case 73679:
                if (name.equals("Int")) {
                    return PrimitiveType.INT;
                }
                return null;
            case 2086184:
                if (name.equals("Byte")) {
                    return PrimitiveType.BYTE;
                }
                return null;
            case 2099062:
                if (name.equals("Char")) {
                    return PrimitiveType.CHAR;
                }
                return null;
            case 2374300:
                if (name.equals("Long")) {
                    return PrimitiveType.LONG;
                }
                return null;
            case 67973692:
                if (name.equals("Float")) {
                    return PrimitiveType.FLOAT;
                }
                return null;
            case 79860828:
                if (name.equals("Short")) {
                    return PrimitiveType.SHORT;
                }
                return null;
            case 1729365000:
                if (name.equals("Boolean")) {
                    return PrimitiveType.BOOLEAN;
                }
                return null;
            case 2052876273:
                if (name.equals("Double")) {
                    return PrimitiveType.DOUBLE;
                }
                return null;
            default:
                return null;
        }
    }

    private PrimitiveType$Companion() {
    }
}

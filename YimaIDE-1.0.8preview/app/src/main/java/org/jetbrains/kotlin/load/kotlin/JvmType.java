package org.jetbrains.kotlin.load.kotlin;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.resolve.jvm.JvmPrimitiveType;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \t2\u00020\u0001:\u0004\u0006\u0007\b\tB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0004\u001a\u00020\u0005H\u0096\u0080\u0004\u0082\u0001\u0003\n\u000b\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/load/kotlin/JvmType;", "", "<init>", "()V", "toString", "", "Primitive", "Object", "Array", "Companion", "Lorg/jetbrains/kotlin/load/kotlin/JvmType$Array;", "Lorg/jetbrains/kotlin/load/kotlin/JvmType$Object;", "Lorg/jetbrains/kotlin/load/kotlin/JvmType$Primitive;", "org.jetbrains.kotlin:descriptors.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class JvmType {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Primitive BOOLEAN = new Primitive(JvmPrimitiveType.BOOLEAN);
    private static final Primitive CHAR = new Primitive(JvmPrimitiveType.CHAR);
    private static final Primitive BYTE = new Primitive(JvmPrimitiveType.BYTE);
    private static final Primitive SHORT = new Primitive(JvmPrimitiveType.SHORT);
    private static final Primitive INT = new Primitive(JvmPrimitiveType.INT);
    private static final Primitive FLOAT = new Primitive(JvmPrimitiveType.FLOAT);
    private static final Primitive LONG = new Primitive(JvmPrimitiveType.LONG);
    private static final Primitive DOUBLE = new Primitive(JvmPrimitiveType.DOUBLE);

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/load/kotlin/JvmType$Array;", "Lorg/jetbrains/kotlin/load/kotlin/JvmType;", "elementType", "<init>", "(Lorg/jetbrains/kotlin/load/kotlin/JvmType;)V", "getElementType", "()Lorg/jetbrains/kotlin/load/kotlin/JvmType;", "org.jetbrains.kotlin:descriptors.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Array extends JvmType {
        private final JvmType elementType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Array(JvmType jvmType) {
            super(null);
            jvmType.getClass();
            this.elementType = jvmType;
        }

        public final JvmType getElementType() {
            return this.elementType;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/load/kotlin/JvmType$Object;", "Lorg/jetbrains/kotlin/load/kotlin/JvmType;", "internalName", "", "<init>", "(Ljava/lang/String;)V", "getInternalName", "()Ljava/lang/String;", "org.jetbrains.kotlin:descriptors.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Object extends JvmType {
        private final String internalName;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Object(String str) {
            super(null);
            str.getClass();
            this.internalName = str;
        }

        public final String getInternalName() {
            return this.internalName;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/load/kotlin/JvmType$Primitive;", "Lorg/jetbrains/kotlin/load/kotlin/JvmType;", "jvmPrimitiveType", "Lorg/jetbrains/kotlin/resolve/jvm/JvmPrimitiveType;", "<init>", "(Lorg/jetbrains/kotlin/resolve/jvm/JvmPrimitiveType;)V", "getJvmPrimitiveType", "()Lorg/jetbrains/kotlin/resolve/jvm/JvmPrimitiveType;", "org.jetbrains.kotlin:descriptors.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Primitive extends JvmType {
        private final JvmPrimitiveType jvmPrimitiveType;

        public Primitive(JvmPrimitiveType jvmPrimitiveType) {
            super(null);
            this.jvmPrimitiveType = jvmPrimitiveType;
        }

        public final JvmPrimitiveType getJvmPrimitiveType() {
            return this.jvmPrimitiveType;
        }
    }

    public /* synthetic */ JvmType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public String toString() {
        return JvmTypeFactoryImpl.INSTANCE.toString(this);
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0014\u0010\f\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0014\u0010\u000e\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0014\u0010\u0010\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0014\u0010\u0012\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007R\u0014\u0010\u0014\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/load/kotlin/JvmType$Companion;", "", "<init>", "()V", "BOOLEAN", "Lorg/jetbrains/kotlin/load/kotlin/JvmType$Primitive;", "getBOOLEAN$org_jetbrains_kotlin_descriptors_jvm", "()Lorg/jetbrains/kotlin/load/kotlin/JvmType$Primitive;", "CHAR", "getCHAR$org_jetbrains_kotlin_descriptors_jvm", "BYTE", "getBYTE$org_jetbrains_kotlin_descriptors_jvm", "SHORT", "getSHORT$org_jetbrains_kotlin_descriptors_jvm", "INT", "getINT$org_jetbrains_kotlin_descriptors_jvm", "FLOAT", "getFLOAT$org_jetbrains_kotlin_descriptors_jvm", "LONG", "getLONG$org_jetbrains_kotlin_descriptors_jvm", "DOUBLE", "getDOUBLE$org_jetbrains_kotlin_descriptors_jvm", "org.jetbrains.kotlin:descriptors.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Primitive getBOOLEAN$org_jetbrains_kotlin_descriptors_jvm() {
            return JvmType.BOOLEAN;
        }

        public final Primitive getBYTE$org_jetbrains_kotlin_descriptors_jvm() {
            return JvmType.BYTE;
        }

        public final Primitive getCHAR$org_jetbrains_kotlin_descriptors_jvm() {
            return JvmType.CHAR;
        }

        public final Primitive getDOUBLE$org_jetbrains_kotlin_descriptors_jvm() {
            return JvmType.DOUBLE;
        }

        public final Primitive getFLOAT$org_jetbrains_kotlin_descriptors_jvm() {
            return JvmType.FLOAT;
        }

        public final Primitive getINT$org_jetbrains_kotlin_descriptors_jvm() {
            return JvmType.INT;
        }

        public final Primitive getLONG$org_jetbrains_kotlin_descriptors_jvm() {
            return JvmType.LONG;
        }

        public final Primitive getSHORT$org_jetbrains_kotlin_descriptors_jvm() {
            return JvmType.SHORT;
        }

        private Companion() {
        }
    }

    private JvmType() {
    }
}

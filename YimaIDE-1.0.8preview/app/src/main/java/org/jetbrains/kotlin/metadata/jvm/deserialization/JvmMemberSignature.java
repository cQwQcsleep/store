package org.jetbrains.kotlin.metadata.jvm.deserialization;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\f\rB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\n\u001a\u00020\u0005H\u0086\u0080\u0004J\b\u0010\u000b\u001a\u00020\u0005H&R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007\u0082\u0001\u0002\u000e\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmMemberSignature;", "", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "desc", "getDesc", "toString", "asString", "Method", "Field", "Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmMemberSignature$Field;", "Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmMemberSignature$Method;", "org.jetbrains.kotlin:metadata.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class JvmMemberSignature {

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\n\u001a\u00020\u0003H\u0016J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmMemberSignature$Field;", "Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmMemberSignature;", "name", "", "desc", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getDesc", "asString", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "org.jetbrains.kotlin:metadata.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final /* data */ class Field extends JvmMemberSignature {
        private final String desc;
        private final String name;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Field(String str, String str2) {
            super(null);
            str.getClass();
            str2.getClass();
            this.name = str;
            this.desc = str2;
        }

        public static /* synthetic */ Field copy$default(Field field, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = field.name;
            }
            if ((i & 2) != 0) {
                str2 = field.desc;
            }
            return field.copy(str, str2);
        }

        @Override // org.jetbrains.kotlin.metadata.jvm.deserialization.JvmMemberSignature
        public String asString() {
            return getName() + ':' + getDesc();
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getDesc() {
            return this.desc;
        }

        public final Field copy(String name, String desc) {
            name.getClass();
            desc.getClass();
            return new Field(name, desc);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Field)) {
                return false;
            }
            Field field = (Field) other;
            return Intrinsics.areEqual(this.name, field.name) && Intrinsics.areEqual(this.desc, field.desc);
        }

        @Override // org.jetbrains.kotlin.metadata.jvm.deserialization.JvmMemberSignature
        public String getDesc() {
            return this.desc;
        }

        @Override // org.jetbrains.kotlin.metadata.jvm.deserialization.JvmMemberSignature
        public String getName() {
            return this.name;
        }

        public int hashCode() {
            return (this.name.hashCode() * 31) + this.desc.hashCode();
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\n\u001a\u00020\u0003H\u0016J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmMemberSignature$Method;", "Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmMemberSignature;", "name", "", "desc", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getDesc", "asString", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "org.jetbrains.kotlin:metadata.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final /* data */ class Method extends JvmMemberSignature {
        private final String desc;
        private final String name;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Method(String str, String str2) {
            super(null);
            str.getClass();
            str2.getClass();
            this.name = str;
            this.desc = str2;
        }

        public static /* synthetic */ Method copy$default(Method method, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = method.name;
            }
            if ((i & 2) != 0) {
                str2 = method.desc;
            }
            return method.copy(str, str2);
        }

        @Override // org.jetbrains.kotlin.metadata.jvm.deserialization.JvmMemberSignature
        public String asString() {
            return getName() + getDesc();
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getDesc() {
            return this.desc;
        }

        public final Method copy(String name, String desc) {
            name.getClass();
            desc.getClass();
            return new Method(name, desc);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Method)) {
                return false;
            }
            Method method = (Method) other;
            return Intrinsics.areEqual(this.name, method.name) && Intrinsics.areEqual(this.desc, method.desc);
        }

        @Override // org.jetbrains.kotlin.metadata.jvm.deserialization.JvmMemberSignature
        public String getDesc() {
            return this.desc;
        }

        @Override // org.jetbrains.kotlin.metadata.jvm.deserialization.JvmMemberSignature
        public String getName() {
            return this.name;
        }

        public int hashCode() {
            return (this.name.hashCode() * 31) + this.desc.hashCode();
        }
    }

    public /* synthetic */ JvmMemberSignature(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract String asString();

    public abstract String getDesc();

    public abstract String getName();

    public final String toString() {
        return asString();
    }

    private JvmMemberSignature() {
    }
}

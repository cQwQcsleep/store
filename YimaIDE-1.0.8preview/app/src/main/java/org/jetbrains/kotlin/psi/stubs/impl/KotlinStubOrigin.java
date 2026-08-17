package org.jetbrains.kotlin.psi.stubs.impl;

import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \f2\u00020\u0001:\u0003\f\r\u000eB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH$R\u0012\u0010\u0004\u001a\u00020\u0005X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0002\u000f\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/psi/stubs/impl/KotlinStubOrigin;", "", "<init>", "()V", "kind", "", "getKind", "()I", "serializeContent", "", "dataStream", "Lcom/intellij/psi/stubs/StubOutputStream;", "Companion", "Facade", "MultiFileFacade", "Lorg/jetbrains/kotlin/psi/stubs/impl/KotlinStubOrigin$Facade;", "Lorg/jetbrains/kotlin/psi/stubs/impl/KotlinStubOrigin$MultiFileFacade;", "org.jetbrains.kotlin:psi-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class KotlinStubOrigin {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public /* synthetic */ KotlinStubOrigin(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @JvmStatic
    public static final KotlinStubOrigin deserialize(StubInputStream stubInputStream) {
        return INSTANCE.deserialize(stubInputStream);
    }

    @JvmStatic
    public static final void serialize(KotlinStubOrigin kotlinStubOrigin, StubOutputStream stubOutputStream) throws IOException {
        INSTANCE.serialize(kotlinStubOrigin, stubOutputStream);
    }

    public abstract int getKind();

    public abstract void serializeContent(StubOutputStream dataStream);

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0014J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u000bHÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0014\u0010\n\u001a\u00020\u000b8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/psi/stubs/impl/KotlinStubOrigin$Facade;", "Lorg/jetbrains/kotlin/psi/stubs/impl/KotlinStubOrigin;", "className", "", "jvmClassName", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getClassName", "()Ljava/lang/String;", "getJvmClassName", "kind", "", "getKind", "()I", "serializeContent", "", "dataStream", "Lcom/intellij/psi/stubs/StubOutputStream;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "Companion", "org.jetbrains.kotlin:psi-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final /* data */ class Facade extends KotlinStubOrigin {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final String className;
        private final String jvmClassName;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Facade(String str, String str2) {
            super(null);
            str.getClass();
            this.className = str;
            this.jvmClassName = str2;
        }

        public static /* synthetic */ Facade copy$default(Facade facade, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = facade.className;
            }
            if ((i & 2) != 0) {
                str2 = facade.jvmClassName;
            }
            return facade.copy(str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getClassName() {
            return this.className;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getJvmClassName() {
            return this.jvmClassName;
        }

        public final Facade copy(String className, String jvmClassName) {
            className.getClass();
            return new Facade(className, jvmClassName);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Facade)) {
                return false;
            }
            Facade facade = (Facade) other;
            return Intrinsics.areEqual(this.className, facade.className) && Intrinsics.areEqual(this.jvmClassName, facade.jvmClassName);
        }

        public final String getClassName() {
            return this.className;
        }

        public final String getJvmClassName() {
            return this.jvmClassName;
        }

        @Override // org.jetbrains.kotlin.psi.stubs.impl.KotlinStubOrigin
        public int getKind() {
            return 1;
        }

        public int hashCode() {
            int iHashCode = this.className.hashCode() * 31;
            String str = this.jvmClassName;
            return iHashCode + (str == null ? 0 : str.hashCode());
        }

        @Override // org.jetbrains.kotlin.psi.stubs.impl.KotlinStubOrigin
        public void serializeContent(StubOutputStream dataStream) throws IOException {
            dataStream.getClass();
            dataStream.writeName(this.className);
            dataStream.writeName(this.jvmClassName);
        }

        public String toString() {
            return "Facade(className=" + this.className + ", jvmClassName=" + this.jvmClassName + ')';
        }

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0001b\u0002\b\t¢\u0006\u0002\b\b¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/psi/stubs/impl/KotlinStubOrigin$Facade$Companion;", "", "<init>", "()V", "deserializeContent", "Lorg/jetbrains/kotlin/psi/stubs/impl/KotlinStubOrigin$Facade;", "dataStream", "Lcom/intellij/psi/stubs/StubInputStream;", "deserializeContent$org_jetbrains_kotlin_psi_impl", "Lkotlin/jvm/JvmStatic;", "org.jetbrains.kotlin:psi-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @JvmStatic
            public final Facade deserializeContent$org_jetbrains_kotlin_psi_impl(StubInputStream dataStream) throws IOException {
                dataStream.getClass();
                String nameString = dataStream.readNameString();
                if (nameString == null) {
                    return null;
                }
                return new Facade(nameString, dataStream.readNameString());
            }

            private Companion() {
            }
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0014J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u000bHÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0014\u0010\n\u001a\u00020\u000b8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/psi/stubs/impl/KotlinStubOrigin$MultiFileFacade;", "Lorg/jetbrains/kotlin/psi/stubs/impl/KotlinStubOrigin;", "className", "", "facadeClassName", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getClassName", "()Ljava/lang/String;", "getFacadeClassName", "kind", "", "getKind", "()I", "serializeContent", "", "dataStream", "Lcom/intellij/psi/stubs/StubOutputStream;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "Companion", "org.jetbrains.kotlin:psi-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final /* data */ class MultiFileFacade extends KotlinStubOrigin {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final String className;
        private final String facadeClassName;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MultiFileFacade(String str, String str2) {
            super(null);
            str.getClass();
            str2.getClass();
            this.className = str;
            this.facadeClassName = str2;
        }

        public static /* synthetic */ MultiFileFacade copy$default(MultiFileFacade multiFileFacade, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = multiFileFacade.className;
            }
            if ((i & 2) != 0) {
                str2 = multiFileFacade.facadeClassName;
            }
            return multiFileFacade.copy(str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getClassName() {
            return this.className;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getFacadeClassName() {
            return this.facadeClassName;
        }

        public final MultiFileFacade copy(String className, String facadeClassName) {
            className.getClass();
            facadeClassName.getClass();
            return new MultiFileFacade(className, facadeClassName);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MultiFileFacade)) {
                return false;
            }
            MultiFileFacade multiFileFacade = (MultiFileFacade) other;
            return Intrinsics.areEqual(this.className, multiFileFacade.className) && Intrinsics.areEqual(this.facadeClassName, multiFileFacade.facadeClassName);
        }

        public final String getClassName() {
            return this.className;
        }

        public final String getFacadeClassName() {
            return this.facadeClassName;
        }

        @Override // org.jetbrains.kotlin.psi.stubs.impl.KotlinStubOrigin
        public int getKind() {
            return 2;
        }

        public int hashCode() {
            return (this.className.hashCode() * 31) + this.facadeClassName.hashCode();
        }

        @Override // org.jetbrains.kotlin.psi.stubs.impl.KotlinStubOrigin
        public void serializeContent(StubOutputStream dataStream) throws IOException {
            dataStream.getClass();
            dataStream.writeName(this.className);
            dataStream.writeName(this.facadeClassName);
        }

        public String toString() {
            return "MultiFileFacade(className=" + this.className + ", facadeClassName=" + this.facadeClassName + ')';
        }

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0001b\u0002\b\t¢\u0006\u0002\b\b¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/psi/stubs/impl/KotlinStubOrigin$MultiFileFacade$Companion;", "", "<init>", "()V", "deserializeContent", "Lorg/jetbrains/kotlin/psi/stubs/impl/KotlinStubOrigin$MultiFileFacade;", "dataStream", "Lcom/intellij/psi/stubs/StubInputStream;", "deserializeContent$org_jetbrains_kotlin_psi_impl", "Lkotlin/jvm/JvmStatic;", "org.jetbrains.kotlin:psi-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @JvmStatic
            public final MultiFileFacade deserializeContent$org_jetbrains_kotlin_psi_impl(StubInputStream dataStream) throws IOException {
                String nameString;
                dataStream.getClass();
                String nameString2 = dataStream.readNameString();
                if (nameString2 == null || (nameString = dataStream.readNameString()) == null) {
                    return null;
                }
                return new MultiFileFacade(nameString2, nameString);
            }

            private Companion() {
            }
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007b\u0002\b\rJ\u0016\u0010\u000e\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u000fH\u0007b\u0002\b\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/psi/stubs/impl/KotlinStubOrigin$Companion;", "", "<init>", "()V", "FACADE_KIND", "", "MULTI_FILE_FACADE_KIND", "serialize", "", "origin", "Lorg/jetbrains/kotlin/psi/stubs/impl/KotlinStubOrigin;", "dataStream", "Lcom/intellij/psi/stubs/StubOutputStream;", "Lkotlin/jvm/JvmStatic;", "deserialize", "Lcom/intellij/psi/stubs/StubInputStream;", "org.jetbrains.kotlin:psi-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final KotlinStubOrigin deserialize(StubInputStream dataStream) throws IOException {
            dataStream.getClass();
            int varInt = dataStream.readVarInt();
            if (varInt == 1) {
                return Facade.INSTANCE.deserializeContent$org_jetbrains_kotlin_psi_impl(dataStream);
            }
            if (varInt != 2) {
                return null;
            }
            return MultiFileFacade.INSTANCE.deserializeContent$org_jetbrains_kotlin_psi_impl(dataStream);
        }

        @JvmStatic
        public final void serialize(KotlinStubOrigin origin, StubOutputStream dataStream) throws IOException {
            dataStream.getClass();
            if (origin == null) {
                dataStream.writeVarInt(0);
            } else {
                dataStream.writeVarInt(origin.getKind());
                origin.serializeContent(dataStream);
            }
        }

        private Companion() {
        }
    }

    private KotlinStubOrigin() {
    }
}

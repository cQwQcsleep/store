package org.jetbrains.kotlin.resolve.jvm.diagnostics;

import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmMemberSignature;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/resolve/jvm/diagnostics/ConflictingJvmDeclarationsData;", "", "classInternalName", "", "signature", "Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmMemberSignature;", "signatureDescriptors", "", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmMemberSignature;Ljava/util/Collection;)V", "getClassInternalName", "()Ljava/lang/String;", "getSignature", "()Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmMemberSignature;", "getSignatureDescriptors", "()Ljava/util/Collection;", "org.jetbrains.kotlin:frontend.java"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class ConflictingJvmDeclarationsData {
    private final String classInternalName;
    private final JvmMemberSignature signature;
    private final Collection<DeclarationDescriptor> signatureDescriptors;

    public ConflictingJvmDeclarationsData(String str, JvmMemberSignature jvmMemberSignature, Collection<? extends DeclarationDescriptor> collection) {
        str.getClass();
        jvmMemberSignature.getClass();
        collection.getClass();
        this.classInternalName = str;
        this.signature = jvmMemberSignature;
        this.signatureDescriptors = collection;
    }

    public final String getClassInternalName() {
        return this.classInternalName;
    }

    public final JvmMemberSignature getSignature() {
        return this.signature;
    }

    public final Collection<DeclarationDescriptor> getSignatureDescriptors() {
        return this.signatureDescriptors;
    }
}

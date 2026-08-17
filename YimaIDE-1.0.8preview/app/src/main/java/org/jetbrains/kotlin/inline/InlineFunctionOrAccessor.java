package org.jetbrains.kotlin.inline;

import kotlin.Metadata;
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmMemberSignature;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u0082\u0001\u0002\u0006\u0007ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/inline/InlineFunctionOrAccessor;", "", "jvmMethodSignature", "Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmMemberSignature$Method;", "getJvmMethodSignature", "()Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmMemberSignature$Method;", "Lorg/jetbrains/kotlin/inline/InlineFunction;", "Lorg/jetbrains/kotlin/inline/InlinePropertyAccessor;", "org.jetbrains.kotlin:frontend.java"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface InlineFunctionOrAccessor {
    JvmMemberSignature.Method getJvmMethodSignature();
}

package org.jetbrains.kotlin.inline;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmMemberSignature;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0005HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/inline/InlineFunction;", "Lorg/jetbrains/kotlin/inline/InlineFunctionOrAccessor;", "jvmMethodSignature", "Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmMemberSignature$Method;", "kotlinFunctionName", "", "<init>", "(Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmMemberSignature$Method;Ljava/lang/String;)V", "getJvmMethodSignature", "()Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmMemberSignature$Method;", "getKotlinFunctionName", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.jetbrains.kotlin:frontend.java"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class InlineFunction implements InlineFunctionOrAccessor {
    private final JvmMemberSignature.Method jvmMethodSignature;
    private final String kotlinFunctionName;

    public InlineFunction(JvmMemberSignature.Method method, String str) {
        method.getClass();
        str.getClass();
        this.jvmMethodSignature = method;
        this.kotlinFunctionName = str;
    }

    public static /* synthetic */ InlineFunction copy$default(InlineFunction inlineFunction, JvmMemberSignature.Method method, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            method = inlineFunction.jvmMethodSignature;
        }
        if ((i & 2) != 0) {
            str = inlineFunction.kotlinFunctionName;
        }
        return inlineFunction.copy(method, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final JvmMemberSignature.Method getJvmMethodSignature() {
        return this.jvmMethodSignature;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getKotlinFunctionName() {
        return this.kotlinFunctionName;
    }

    public final InlineFunction copy(JvmMemberSignature.Method jvmMethodSignature, String kotlinFunctionName) {
        jvmMethodSignature.getClass();
        kotlinFunctionName.getClass();
        return new InlineFunction(jvmMethodSignature, kotlinFunctionName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InlineFunction)) {
            return false;
        }
        InlineFunction inlineFunction = (InlineFunction) other;
        return Intrinsics.areEqual(this.jvmMethodSignature, inlineFunction.jvmMethodSignature) && Intrinsics.areEqual(this.kotlinFunctionName, inlineFunction.kotlinFunctionName);
    }

    @Override // org.jetbrains.kotlin.inline.InlineFunctionOrAccessor
    public JvmMemberSignature.Method getJvmMethodSignature() {
        return this.jvmMethodSignature;
    }

    public final String getKotlinFunctionName() {
        return this.kotlinFunctionName;
    }

    public int hashCode() {
        return (this.jvmMethodSignature.hashCode() * 31) + this.kotlinFunctionName.hashCode();
    }

    public String toString() {
        return "InlineFunction(jvmMethodSignature=" + this.jvmMethodSignature + ", kotlinFunctionName=" + this.kotlinFunctionName + ')';
    }
}

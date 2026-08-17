package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/CapturedParamDesc;", Argument.Delimiters.none, "containingLambdaType", "Lorg/jetbrains/org/objectweb/asm/Type;", "fieldName", Argument.Delimiters.none, ModuleXmlParser.TYPE, "isSuspend", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/org/objectweb/asm/Type;Ljava/lang/String;Lorg/jetbrains/org/objectweb/asm/Type;Z)V", "getFieldName", "()Ljava/lang/String;", "getType", "()Lorg/jetbrains/org/objectweb/asm/Type;", "()Z", "containingLambdaName", "getContainingLambdaName", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CapturedParamDesc {
    private final String containingLambdaName;
    private final String fieldName;
    private final boolean isSuspend;
    private final Type type;

    public CapturedParamDesc(Type type, String str, Type type2, boolean z) {
        type.getClass();
        str.getClass();
        type2.getClass();
        this.fieldName = str;
        this.type = type2;
        this.isSuspend = z;
        String internalName = type.getInternalName();
        internalName.getClass();
        this.containingLambdaName = internalName;
    }

    public final String getContainingLambdaName() {
        return this.containingLambdaName;
    }

    public final String getFieldName() {
        return this.fieldName;
    }

    public final Type getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: isSuspend, reason: from getter */
    public final boolean getIsSuspend() {
        return this.isSuspend;
    }

    public /* synthetic */ CapturedParamDesc(Type type, String str, Type type2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(type, str, type2, (i & 8) != 0 ? false : z);
    }
}

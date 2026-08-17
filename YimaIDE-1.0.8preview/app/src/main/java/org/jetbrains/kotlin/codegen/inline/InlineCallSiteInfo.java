package org.jetbrains.kotlin.codegen.inline;

import java.io.File;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.org.objectweb.asm.commons.Method;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0011\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0015R\u0011\u0010\u001d\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0015¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/InlineCallSiteInfo;", Argument.Delimiters.none, "ownerClassName", Argument.Delimiters.none, "method", "Lorg/jetbrains/org/objectweb/asm/commons/Method;", "suppressNonPublicApiObjectInliningError", Argument.Delimiters.none, "inlineScopeVisibility", "Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "file", "Ljava/io/File;", "lineNumber", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;Lorg/jetbrains/org/objectweb/asm/commons/Method;ZLorg/jetbrains/kotlin/descriptors/DescriptorVisibility;Ljava/io/File;I)V", "getOwnerClassName", "()Ljava/lang/String;", "getMethod", "()Lorg/jetbrains/org/objectweb/asm/commons/Method;", "getSuppressNonPublicApiObjectInliningError", "()Z", "getInlineScopeVisibility", "()Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "getFile", "()Ljava/io/File;", "getLineNumber", "()I", "isInlineOrInsideInline", "isInPublicInlineScope", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class InlineCallSiteInfo {
    private final File file;
    private final DescriptorVisibility inlineScopeVisibility;
    private final int lineNumber;
    private final Method method;
    private final String ownerClassName;
    private final boolean suppressNonPublicApiObjectInliningError;

    public InlineCallSiteInfo(String str, Method method, boolean z, DescriptorVisibility descriptorVisibility, File file, int i) {
        str.getClass();
        method.getClass();
        this.ownerClassName = str;
        this.method = method;
        this.suppressNonPublicApiObjectInliningError = z;
        this.inlineScopeVisibility = descriptorVisibility;
        this.file = file;
        this.lineNumber = i;
    }

    public final File getFile() {
        return this.file;
    }

    public final DescriptorVisibility getInlineScopeVisibility() {
        return this.inlineScopeVisibility;
    }

    public final int getLineNumber() {
        return this.lineNumber;
    }

    public final Method getMethod() {
        return this.method;
    }

    public final String getOwnerClassName() {
        return this.ownerClassName;
    }

    public final boolean getSuppressNonPublicApiObjectInliningError() {
        return this.suppressNonPublicApiObjectInliningError;
    }

    public final boolean isInPublicInlineScope() {
        DescriptorVisibility descriptorVisibility = this.inlineScopeVisibility;
        return (descriptorVisibility == null || DescriptorVisibilities.isPrivate(descriptorVisibility)) ? false : true;
    }

    public final boolean isInlineOrInsideInline() {
        return this.inlineScopeVisibility != null;
    }
}

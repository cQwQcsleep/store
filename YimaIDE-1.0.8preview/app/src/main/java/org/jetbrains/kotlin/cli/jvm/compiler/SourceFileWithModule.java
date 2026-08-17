package org.jetbrains.kotlin.cli.jvm.compiler;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001c\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B'\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\rR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/SourceFileWithModule;", "T", Argument.Delimiters.none, "sourceFiles", Argument.Delimiters.none, "isCommon", Argument.Delimiters.none, "moduleName", Argument.Delimiters.none, "<init>", "(Ljava/lang/Iterable;ZLjava/lang/String;)V", "getSourceFiles", "()Ljava/lang/Iterable;", "()Z", "getModuleName", "()Ljava/lang/String;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SourceFileWithModule<T> {
    private final boolean isCommon;
    private final String moduleName;
    private final Iterable<T> sourceFiles;

    /* JADX WARN: Multi-variable type inference failed */
    public SourceFileWithModule(Iterable<? extends T> iterable, boolean z, String str) {
        iterable.getClass();
        this.sourceFiles = iterable;
        this.isCommon = z;
        this.moduleName = str;
    }

    public final String getModuleName() {
        return this.moduleName;
    }

    public final Iterable<T> getSourceFiles() {
        return this.sourceFiles;
    }

    /* JADX INFO: renamed from: isCommon, reason: from getter */
    public final boolean getIsCommon() {
        return this.isCommon;
    }
}

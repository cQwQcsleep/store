package org.jetbrains.kotlin;

import com.intellij.openapi.util.io.FileUtilRt;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0096\u0082\u0004J\n\u0010\u0014\u001a\u00020\u0015H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/KtIoFileSourceFile;", "Lorg/jetbrains/kotlin/KtSourceFile;", "file", "Ljava/io/File;", "<init>", "(Ljava/io/File;)V", "getFile", "()Ljava/io/File;", ModuleXmlParser.NAME, Argument.Delimiters.none, "getName", "()Ljava/lang/String;", ModuleXmlParser.PATH, "getPath", "getContentsAsStream", "Ljava/io/InputStream;", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KtIoFileSourceFile implements KtSourceFile {
    private final File file;

    public KtIoFileSourceFile(File file) {
        file.getClass();
        this.file = file;
    }

    @Override // org.jetbrains.kotlin.KtSourceFile
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        KtIoFileSourceFile ktIoFileSourceFile = other instanceof KtIoFileSourceFile ? (KtIoFileSourceFile) other : null;
        return Intrinsics.areEqual(ktIoFileSourceFile != null ? ktIoFileSourceFile.file : null, this.file);
    }

    @Override // org.jetbrains.kotlin.KtSourceFile
    public InputStream getContentsAsStream() {
        return new FileInputStream(this.file);
    }

    public final File getFile() {
        return this.file;
    }

    @Override // org.jetbrains.kotlin.KtSourceFile
    public String getName() {
        String name = this.file.getName();
        name.getClass();
        return name;
    }

    @Override // org.jetbrains.kotlin.KtSourceFile
    public String getPath() {
        String systemIndependentName = FileUtilRt.toSystemIndependentName(this.file.getPath());
        systemIndependentName.getClass();
        return systemIndependentName;
    }

    @Override // org.jetbrains.kotlin.KtSourceFile
    public int hashCode() {
        return this.file.hashCode();
    }
}

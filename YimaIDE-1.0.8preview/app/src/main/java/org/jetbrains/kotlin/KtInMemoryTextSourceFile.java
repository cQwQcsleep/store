package org.jetbrains.kotlin;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0096\u0082\u0004J\n\u0010\u0014\u001a\u00020\u0015H\u0096\u0080\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/KtInMemoryTextSourceFile;", "Lorg/jetbrains/kotlin/KtSourceFile;", ModuleXmlParser.NAME, Argument.Delimiters.none, ModuleXmlParser.PATH, "text", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/CharSequence;)V", "getName", "()Ljava/lang/String;", "getPath", "getText", "()Ljava/lang/CharSequence;", "getContentsAsStream", "Ljava/io/InputStream;", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KtInMemoryTextSourceFile implements KtSourceFile {
    private final String name;
    private final String path;
    private final CharSequence text;

    public KtInMemoryTextSourceFile(String str, String str2, CharSequence charSequence) {
        str.getClass();
        charSequence.getClass();
        this.name = str;
        this.path = str2;
        this.text = charSequence;
    }

    @Override // org.jetbrains.kotlin.KtSourceFile
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KtInMemoryTextSourceFile)) {
            return false;
        }
        KtInMemoryTextSourceFile ktInMemoryTextSourceFile = (KtInMemoryTextSourceFile) other;
        return Intrinsics.areEqual(ktInMemoryTextSourceFile.text, this.text) && Intrinsics.areEqual(ktInMemoryTextSourceFile.getName(), getName()) && Intrinsics.areEqual(ktInMemoryTextSourceFile.getPath(), getPath());
    }

    @Override // org.jetbrains.kotlin.KtSourceFile
    public InputStream getContentsAsStream() {
        byte[] bytes = this.text.toString().getBytes(Charsets.UTF_8);
        bytes.getClass();
        return new ByteArrayInputStream(bytes);
    }

    @Override // org.jetbrains.kotlin.KtSourceFile
    public String getName() {
        return this.name;
    }

    @Override // org.jetbrains.kotlin.KtSourceFile
    public String getPath() {
        return this.path;
    }

    public final CharSequence getText() {
        return this.text;
    }

    @Override // org.jetbrains.kotlin.KtSourceFile
    public int hashCode() {
        int iHashCode = this.text.hashCode() + (getName().hashCode() * 17);
        String path = getPath();
        return iHashCode + ((path != null ? path.hashCode() : 0) * 31);
    }
}

package org.jetbrains.kotlin.backend.common.output;

import java.io.File;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u000f\u001a\u00020\u0010H&J\b\u0010\u0011\u001a\u00020\u0003H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0012À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/output/OutputFile;", Argument.Delimiters.none, "relativePath", Argument.Delimiters.none, "getRelativePath", "()Ljava/lang/String;", "sourceFiles", Argument.Delimiters.none, "Ljava/io/File;", "getSourceFiles", "()Ljava/util/List;", "generatedForCompilerPlugin", Argument.Delimiters.none, "getGeneratedForCompilerPlugin", "()Z", "asByteArray", Argument.Delimiters.none, "asText", "org.jetbrains.kotlin:util"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface OutputFile {
    byte[] asByteArray();

    String asText();

    default boolean getGeneratedForCompilerPlugin() {
        return false;
    }

    String getRelativePath();

    List<File> getSourceFiles();
}

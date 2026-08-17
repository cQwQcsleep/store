package org.jetbrains.kotlin.modules;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007H&J\u000e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007H&J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007H&J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007H&J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007H&R\u0014\u0010\r\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/modules/Module;", Argument.Delimiters.none, "getModuleName", Argument.Delimiters.none, "getModuleType", "getOutputDirectory", "getFriendPaths", Argument.Delimiters.none, "getSourceFiles", "getCommonSourceFiles", "getClasspathRoots", "getJavaSourceRoots", "Lorg/jetbrains/kotlin/modules/JavaRootPath;", ModuleXmlParser.MODULAR_JDK_ROOT, "getModularJdkRoot", "()Ljava/lang/String;", "org.jetbrains.kotlin:util"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface Module {
    List<String> getClasspathRoots();

    List<String> getCommonSourceFiles();

    List<String> getFriendPaths();

    List<JavaRootPath> getJavaSourceRoots();

    String getModularJdkRoot();

    String getModuleName();

    String getModuleType();

    String getOutputDirectory();

    List<String> getSourceFiles();
}

package org.jetbrains.kotlin.cli.common.modules;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.modules.JavaRootPath;
import org.jetbrains.kotlin.modules.Module;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0003J\u000e\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0003J\u000e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0003J\u000e\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\rJ\u000e\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u0003J\b\u0010\u001d\u001a\u00020\u0003H\u0016J\u000e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\u001fH\u0016J\u000e\u0010 \u001a\b\u0012\u0004\u0012\u00020\r0\u001fH\u0016J\u000e\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00030\u001fH\u0016J\u000e\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00030\u001fH\u0016J\u000e\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00030\u001fH\u0016J\b\u0010$\u001a\u00020\u0003H\u0016J\b\u0010%\u001a\u00020\u0003H\u0016J\n\u0010&\u001a\u00020\u0003H\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/modules/ModuleBuilder;", "Lorg/jetbrains/kotlin/modules/Module;", ModuleXmlParser.NAME, Argument.Delimiters.none, ModuleXmlParser.OUTPUT_DIR, ModuleXmlParser.TYPE, "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "sourceFiles", "Ljava/util/ArrayList;", "commonSourceFiles", "classpathRoots", ModuleXmlParser.JAVA_SOURCE_ROOTS, "Lorg/jetbrains/kotlin/modules/JavaRootPath;", "friendDirs", ModuleXmlParser.MODULAR_JDK_ROOT, "getModularJdkRoot", "()Ljava/lang/String;", "setModularJdkRoot", "(Ljava/lang/String;)V", "addSourceFiles", Argument.Delimiters.none, ModuleXmlParser.PATH, "addCommonSourceFiles", "addClasspathEntry", "addJavaSourceRoot", "rootPath", "addFriendDir", ModuleXmlParser.FRIEND_DIR, "getOutputDirectory", "getFriendPaths", Argument.Delimiters.none, "getJavaSourceRoots", "getSourceFiles", "getCommonSourceFiles", "getClasspathRoots", "getModuleName", "getModuleType", "toString", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ModuleBuilder implements Module {
    private final ArrayList<String> classpathRoots;
    private final ArrayList<String> commonSourceFiles;
    private final ArrayList<String> friendDirs;
    private final ArrayList<JavaRootPath> javaSourceRoots;
    private String modularJdkRoot;
    private final String name;
    private final String outputDir;
    private final ArrayList<String> sourceFiles;
    private final String type;

    public ModuleBuilder(String str, String str2, String str3) {
        str.getClass();
        str2.getClass();
        str3.getClass();
        this.name = str;
        this.outputDir = str2;
        this.type = str3;
        this.sourceFiles = new ArrayList<>();
        this.commonSourceFiles = new ArrayList<>();
        this.classpathRoots = new ArrayList<>();
        this.javaSourceRoots = new ArrayList<>();
        this.friendDirs = new ArrayList<>();
    }

    public final void addClasspathEntry(String path) {
        path.getClass();
        this.classpathRoots.add(path);
    }

    public final void addCommonSourceFiles(String path) {
        path.getClass();
        this.commonSourceFiles.add(path);
    }

    public final void addFriendDir(String friendDir) {
        friendDir.getClass();
        this.friendDirs.add(friendDir);
    }

    public final void addJavaSourceRoot(JavaRootPath rootPath) {
        rootPath.getClass();
        this.javaSourceRoots.add(rootPath);
    }

    public final void addSourceFiles(String path) {
        path.getClass();
        this.sourceFiles.add(path);
    }

    @Override // org.jetbrains.kotlin.modules.Module
    public List<String> getClasspathRoots() {
        return this.classpathRoots;
    }

    @Override // org.jetbrains.kotlin.modules.Module
    public List<String> getCommonSourceFiles() {
        return this.commonSourceFiles;
    }

    @Override // org.jetbrains.kotlin.modules.Module
    public List<String> getFriendPaths() {
        return this.friendDirs;
    }

    @Override // org.jetbrains.kotlin.modules.Module
    public List<JavaRootPath> getJavaSourceRoots() {
        return this.javaSourceRoots;
    }

    @Override // org.jetbrains.kotlin.modules.Module
    public String getModularJdkRoot() {
        return this.modularJdkRoot;
    }

    @Override // org.jetbrains.kotlin.modules.Module
    /* JADX INFO: renamed from: getModuleName, reason: from getter */
    public String getName() {
        return this.name;
    }

    @Override // org.jetbrains.kotlin.modules.Module
    /* JADX INFO: renamed from: getModuleType, reason: from getter */
    public String getType() {
        return this.type;
    }

    @Override // org.jetbrains.kotlin.modules.Module
    /* JADX INFO: renamed from: getOutputDirectory, reason: from getter */
    public String getOutputDir() {
        return this.outputDir;
    }

    @Override // org.jetbrains.kotlin.modules.Module
    public List<String> getSourceFiles() {
        return this.sourceFiles;
    }

    public void setModularJdkRoot(String str) {
        this.modularJdkRoot = str;
    }

    public String toString() {
        return this.name + " (" + this.type + ')';
    }
}

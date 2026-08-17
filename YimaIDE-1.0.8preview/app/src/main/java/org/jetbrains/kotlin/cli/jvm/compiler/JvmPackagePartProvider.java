package org.jetbrains.kotlin.cli.jvm.compiler;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.SmartList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.jvm.compiler.JvmPackagePartProvider;
import org.jetbrains.kotlin.cli.jvm.index.JavaRoot;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.load.kotlin.JvmPackagePartProviderBase;
import org.jetbrains.kotlin.metadata.jvm.deserialization.ModuleMapping;
import org.jetbrains.kotlin.resolve.JvmCompilerDeserializationConfiguration;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001c\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u001d\u001a\u00020\u001eR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR \u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u000f0\u000eX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00138TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/JvmPackagePartProvider;", "Lorg/jetbrains/kotlin/load/kotlin/JvmPackagePartProviderBase;", "Lcom/intellij/openapi/vfs/VirtualFile;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "scope", "Lcom/intellij/psi/search/GlobalSearchScope;", "<init>", "(Lorg/jetbrains/kotlin/config/LanguageVersionSettings;Lcom/intellij/psi/search/GlobalSearchScope;)V", "deserializationConfiguration", "Lorg/jetbrains/kotlin/resolve/JvmCompilerDeserializationConfiguration;", "getDeserializationConfiguration", "()Lorg/jetbrains/kotlin/resolve/JvmCompilerDeserializationConfiguration;", "loadedModules", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/load/kotlin/JvmPackagePartProviderBase$ModuleMappingInfo;", "getLoadedModules", "()Ljava/util/List;", "allPackageNamesCache", Argument.Delimiters.none, Argument.Delimiters.none, "allPackageNames", "getAllPackageNames", "()Ljava/util/Set;", "addRoots", Argument.Delimiters.none, "roots", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/jvm/index/JavaRoot;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmPackagePartProvider extends JvmPackagePartProviderBase<VirtualFile> {
    private Set<String> allPackageNamesCache;
    private final JvmCompilerDeserializationConfiguration deserializationConfiguration;
    private final List<JvmPackagePartProviderBase.ModuleMappingInfo<VirtualFile>> loadedModules;
    private final GlobalSearchScope scope;

    public JvmPackagePartProvider(LanguageVersionSettings languageVersionSettings, GlobalSearchScope globalSearchScope) {
        languageVersionSettings.getClass();
        globalSearchScope.getClass();
        this.scope = globalSearchScope;
        this.deserializationConfiguration = new JvmCompilerDeserializationConfiguration(languageVersionSettings);
        this.loadedModules = new SmartList();
    }

    public static byte[] b(VirtualFile virtualFile) {
        byte[] bArrContentsToByteArray = virtualFile.contentsToByteArray();
        bArrContentsToByteArray.getClass();
        return bArrContentsToByteArray;
    }

    public final void addRoots(List<JavaRoot> roots, CompilerConfiguration configuration) {
        VirtualFile virtualFileFindChild;
        roots.getClass();
        configuration.getClass();
        for (JavaRoot javaRoot : roots) {
            VirtualFile file = javaRoot.getFile();
            if (javaRoot.getType() == JavaRoot.RootType.BINARY && this.scope.contains(file) && (virtualFileFindChild = file.findChild("META-INF")) != null) {
                VirtualFile[] children = virtualFileFindChild.getChildren();
                children.getClass();
                for (final VirtualFile virtualFile : children) {
                    String name = virtualFile.getName();
                    name.getClass();
                    if (StringsKt.endsWith$default(name, "kotlin_module", false, 2, (Object) null)) {
                        Function0 function0 = new Function0() { // from class: oz7
                            public final Object invoke() {
                                return JvmPackagePartProvider.b(virtualFile);
                            }
                        };
                        String string = virtualFile.toString();
                        string.getClass();
                        String path = virtualFile.getPath();
                        path.getClass();
                        ModuleMapping moduleMappingTryLoadModuleMapping = JvmPackagePartProviderKt.tryLoadModuleMapping(function0, string, path, m19getDeserializationConfiguration(), configuration);
                        if (moduleMappingTryLoadModuleMapping != null) {
                            List<JvmPackagePartProviderBase.ModuleMappingInfo<VirtualFile>> loadedModules = getLoadedModules();
                            String nameWithoutExtension = virtualFile.getNameWithoutExtension();
                            nameWithoutExtension.getClass();
                            loadedModules.add(new JvmPackagePartProviderBase.ModuleMappingInfo<>(file, moduleMappingTryLoadModuleMapping, nameWithoutExtension));
                            this.allPackageNamesCache = null;
                        }
                    }
                }
            }
        }
    }

    public Set<String> getAllPackageNames() {
        Set<String> set = this.allPackageNamesCache;
        if (set != null) {
            return set;
        }
        List<JvmPackagePartProviderBase.ModuleMappingInfo<VirtualFile>> loadedModules = getLoadedModules();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = loadedModules.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(linkedHashSet, ((JvmPackagePartProviderBase.ModuleMappingInfo) it.next()).getMapping().getPackageFqName2Parts().keySet());
        }
        this.allPackageNamesCache = linkedHashSet;
        return linkedHashSet;
    }

    public List<JvmPackagePartProviderBase.ModuleMappingInfo<VirtualFile>> getLoadedModules() {
        return this.loadedModules;
    }

    /* JADX INFO: renamed from: getDeserializationConfiguration, reason: from getter and merged with bridge method [inline-methods] */
    public JvmCompilerDeserializationConfiguration m19getDeserializationConfiguration() {
        return this.deserializationConfiguration;
    }
}

package org.jetbrains.kotlin.extensions;

import com.intellij.core.CoreApplicationEnvironment;
import com.intellij.openapi.extensions.ExtensionPoint;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.extensions.ExtensionsArea;
import com.intellij.openapi.project.Project;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u001b\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00028\u0000¢\u0006\u0002\u0010\u0014J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u00162\u0006\u0010\u0010\u001a\u00020\u0011R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/extensions/ProjectExtensionDescriptor;", "T", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/extensions/ExtensionPointDescriptor;", ModuleXmlParser.NAME, Argument.Delimiters.none, "extensionClass", "Ljava/lang/Class;", "<init>", "(Ljava/lang/String;Ljava/lang/Class;)V", "extensionPointName", "Lcom/intellij/openapi/extensions/ExtensionPointName;", "getExtensionPointName", "()Lcom/intellij/openapi/extensions/ExtensionPointName;", "registerExtensionPoint", Argument.Delimiters.none, "project", "Lcom/intellij/openapi/project/Project;", "registerExtension", "extension", "(Lcom/intellij/openapi/project/Project;Ljava/lang/Object;)V", "getInstances", Argument.Delimiters.none, "org.jetbrains.kotlin:util"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class ProjectExtensionDescriptor<T> extends ExtensionPointDescriptor<T> {
    private final ExtensionPointName<T> extensionPointName;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProjectExtensionDescriptor(String str, Class<T> cls) {
        super(str, cls);
        str.getClass();
        cls.getClass();
        this.extensionPointName = ExtensionPointName.Companion.create(str);
    }

    public final ExtensionPointName<T> getExtensionPointName() {
        return this.extensionPointName;
    }

    public final List<T> getInstances(Project project) {
        project.getClass();
        ExtensionsArea extensionArea = project.getExtensionArea();
        extensionArea.getClass();
        if (!extensionArea.hasExtensionPoint(this.extensionPointName.getName())) {
            return CollectionsKt.emptyList();
        }
        Object[] extensions = extensionArea.getExtensionPoint(this.extensionPointName).getExtensions();
        extensions.getClass();
        return ArraysKt.toList(extensions);
    }

    public final void registerExtension(Project project, T extension) {
        project.getClass();
        extension.getClass();
        ExtensionPoint extensionPoint = project.getExtensionArea().getExtensionPoint(this.extensionPointName);
        Object[] extensions = extensionPoint.getExtensions();
        extensions.getClass();
        if (ArraysKt.contains(extensions, extension)) {
            return;
        }
        extensionPoint.registerExtension(extension, project);
    }

    public final void registerExtensionPoint(Project project) {
        project.getClass();
        CoreApplicationEnvironment.registerExtensionPoint(project.getExtensionArea(), this.extensionPointName.getName(), getExtensionClass());
    }
}

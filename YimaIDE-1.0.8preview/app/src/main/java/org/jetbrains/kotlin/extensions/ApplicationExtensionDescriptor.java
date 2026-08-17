package org.jetbrains.kotlin.extensions;

import com.intellij.core.CoreApplicationEnvironment;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.extensions.ExtensionsArea;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\u000b\u001a\u00020\fJ\u001b\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0002\u0010\u0011J\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/extensions/ApplicationExtensionDescriptor;", "T", Argument.Delimiters.none, ModuleXmlParser.NAME, Argument.Delimiters.none, "extensionClass", "Ljava/lang/Class;", "<init>", "(Ljava/lang/String;Ljava/lang/Class;)V", "extensionPointName", "Lcom/intellij/openapi/extensions/ExtensionPointName;", "registerExtensionPoint", Argument.Delimiters.none, "registerExtension", "extension", "disposable", "Lcom/intellij/openapi/Disposable;", "(Ljava/lang/Object;Lcom/intellij/openapi/Disposable;)V", "getInstances", Argument.Delimiters.none, "org.jetbrains.kotlin:util"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class ApplicationExtensionDescriptor<T> {
    private final Class<T> extensionClass;
    private final ExtensionPointName<T> extensionPointName;

    public ApplicationExtensionDescriptor(String str, Class<T> cls) {
        str.getClass();
        cls.getClass();
        this.extensionClass = cls;
        this.extensionPointName = ExtensionPointName.Companion.create(str);
    }

    public final List<T> getInstances() {
        ExtensionsArea extensionArea = ApplicationManager.getApplication().getExtensionArea();
        extensionArea.getClass();
        if (!extensionArea.hasExtensionPoint(this.extensionPointName.getName())) {
            return CollectionsKt.emptyList();
        }
        Object[] extensions = extensionArea.getExtensionPoint(this.extensionPointName).getExtensions();
        extensions.getClass();
        return ArraysKt.toList(extensions);
    }

    public final void registerExtension(T extension, Disposable disposable) {
        extension.getClass();
        disposable.getClass();
        ApplicationManager.getApplication().getExtensionArea().getExtensionPoint(this.extensionPointName).registerExtension(extension, disposable);
    }

    public final void registerExtensionPoint() {
        CoreApplicationEnvironment.registerExtensionPoint(ApplicationManager.getApplication().getExtensionArea(), this.extensionPointName.getName(), this.extensionClass);
    }
}

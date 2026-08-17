package org.jetbrains.kotlin.cli.jvm.compiler;

import com.intellij.openapi.project.Project;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.JvmTarget;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.resolve.BindingContext;
import org.jetbrains.kotlin.resolve.BindingTrace;
import org.jetbrains.kotlin.resolve.BindingTraceContext;
import org.jetbrains.kotlin.resolve.jvm.JvmCodeAnalyzerInitializer;
import org.jetbrains.kotlin.resolve.lazy.KotlinCodeAnalyzer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J0\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@BX\u0086.¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\u000e\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\r@BX\u0086.¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u0011@BX\u0086.¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/CliTraceHolder;", "Lorg/jetbrains/kotlin/resolve/jvm/JvmCodeAnalyzerInitializer;", "project", "Lcom/intellij/openapi/project/Project;", "<init>", "(Lcom/intellij/openapi/project/Project;)V", "getProject", "()Lcom/intellij/openapi/project/Project;", "value", "Lorg/jetbrains/kotlin/resolve/BindingContext;", "bindingContext", "getBindingContext", "()Lorg/jetbrains/kotlin/resolve/BindingContext;", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", ModuleXmlParser.MODULE, "getModule", "()Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "languageVersionSettings", "getLanguageVersionSettings", "()Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "initialize", Argument.Delimiters.none, "trace", "Lorg/jetbrains/kotlin/resolve/BindingTrace;", "codeAnalyzer", "Lorg/jetbrains/kotlin/resolve/lazy/KotlinCodeAnalyzer;", "jvmTarget", "Lorg/jetbrains/kotlin/config/JvmTarget;", "createTrace", "Lorg/jetbrains/kotlin/resolve/BindingTraceContext;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CliTraceHolder extends JvmCodeAnalyzerInitializer {
    private BindingContext bindingContext;
    private LanguageVersionSettings languageVersionSettings;
    private ModuleDescriptor module;
    private final Project project;

    public CliTraceHolder(Project project) {
        project.getClass();
        this.project = project;
    }

    public BindingTraceContext createTrace() {
        return new NoScopeRecordCliBindingTrace(this.project);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final BindingContext getBindingContext() throws UninitializedPropertyAccessException {
        BindingContext bindingContext = this.bindingContext;
        if (bindingContext != null) {
            return bindingContext;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bindingContext");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final LanguageVersionSettings getLanguageVersionSettings() throws UninitializedPropertyAccessException {
        LanguageVersionSettings languageVersionSettings = this.languageVersionSettings;
        if (languageVersionSettings != null) {
            return languageVersionSettings;
        }
        Intrinsics.throwUninitializedPropertyAccessException("languageVersionSettings");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final ModuleDescriptor getModule() throws UninitializedPropertyAccessException {
        ModuleDescriptor moduleDescriptor = this.module;
        if (moduleDescriptor != null) {
            return moduleDescriptor;
        }
        Intrinsics.throwUninitializedPropertyAccessException(ModuleXmlParser.MODULE);
        return null;
    }

    public final Project getProject() {
        return this.project;
    }

    public void initialize(BindingTrace trace, ModuleDescriptor module, KotlinCodeAnalyzer codeAnalyzer, LanguageVersionSettings languageVersionSettings, JvmTarget jvmTarget) {
        trace.getClass();
        module.getClass();
        codeAnalyzer.getClass();
        languageVersionSettings.getClass();
        jvmTarget.getClass();
        BindingContext bindingContext = trace.getBindingContext();
        bindingContext.getClass();
        this.bindingContext = bindingContext;
        this.module = module;
        this.languageVersionSettings = languageVersionSettings;
        if (trace instanceof CliBindingTrace) {
            ((CliBindingTrace) trace).setKotlinCodeAnalyzer(codeAnalyzer);
        } else {
            yba.a("Shared trace is expected to be subclass of ", CliBindingTrace.class.getSimpleName(), " class");
        }
    }
}

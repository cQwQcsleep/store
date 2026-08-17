package org.jetbrains.kotlin.cli.jvm.compiler;

import com.intellij.openapi.project.Project;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.asJava.LightClassGenerationSupport;
import org.jetbrains.kotlin.asJava.classes.ImplUtilsKt;
import org.jetbrains.kotlin.asJava.classes.KtUltraLightSupport;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.jvm.compiler.CliLightClassGenerationSupport;
import org.jetbrains.kotlin.codegen.JvmCodegenUtil;
import org.jetbrains.kotlin.codegen.state.KotlinTypeMapper;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationDescriptor;
import org.jetbrains.kotlin.load.java.components.JavaDeprecationSettings;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.psi.KtAnnotationEntry;
import org.jetbrains.kotlin.psi.KtClassOrObject;
import org.jetbrains.kotlin.psi.KtDeclaration;
import org.jetbrains.kotlin.psi.KtElement;
import org.jetbrains.kotlin.psi.KtFile;
import org.jetbrains.kotlin.resolve.BindingContext;
import org.jetbrains.kotlin.resolve.deprecation.DeprecationResolver;
import org.jetbrains.kotlin.storage.LockBasedStorageManager;
import org.jetbrains.kotlin.storage.StorageManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u001eB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0014J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u0011\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u0011\u001a\u00020\u001dH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/CliLightClassGenerationSupport;", "Lorg/jetbrains/kotlin/asJava/LightClassGenerationSupport;", "traceHolder", "Lorg/jetbrains/kotlin/cli/jvm/compiler/CliTraceHolder;", "project", "Lcom/intellij/openapi/project/Project;", "<init>", "(Lorg/jetbrains/kotlin/cli/jvm/compiler/CliTraceHolder;Lcom/intellij/openapi/project/Project;)V", "getTraceHolder", "()Lorg/jetbrains/kotlin/cli/jvm/compiler/CliTraceHolder;", "ultraLightSupport", "Lorg/jetbrains/kotlin/asJava/classes/KtUltraLightSupport;", "getUltraLightSupport", "()Lorg/jetbrains/kotlin/asJava/classes/KtUltraLightSupport;", "ultraLightSupport$delegate", "Lkotlin/Lazy;", "getUltraLightClassSupport", "element", "Lorg/jetbrains/kotlin/psi/KtElement;", "resolveToDescriptor", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "declaration", "Lorg/jetbrains/kotlin/psi/KtDeclaration;", "analyze", "Lorg/jetbrains/kotlin/resolve/BindingContext;", "analyzeAnnotation", "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationDescriptor;", "Lorg/jetbrains/kotlin/psi/KtAnnotationEntry;", "analyzeWithContent", "Lorg/jetbrains/kotlin/psi/KtClassOrObject;", "CliLightClassSupport", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CliLightClassGenerationSupport extends LightClassGenerationSupport {
    private final Project project;
    private final CliTraceHolder traceHolder;

    /* JADX INFO: renamed from: ultraLightSupport$delegate, reason: from kotlin metadata */
    private final Lazy ultraLightSupport;

    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0014\u001a\u00020\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u001d8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u001b\u0010 \u001a\u00020!8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b\"\u0010#¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/CliLightClassGenerationSupport$CliLightClassSupport;", "Lorg/jetbrains/kotlin/asJava/classes/KtUltraLightSupport;", "project", "Lcom/intellij/openapi/project/Project;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "<init>", "(Lcom/intellij/openapi/project/Project;Lorg/jetbrains/kotlin/config/LanguageVersionSettings;)V", "getLanguageVersionSettings", "()Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "traceHolder", "Lorg/jetbrains/kotlin/cli/jvm/compiler/CliTraceHolder;", "getTraceHolder", "()Lorg/jetbrains/kotlin/cli/jvm/compiler/CliTraceHolder;", "possiblyHasAlias", Argument.Delimiters.none, "file", "Lorg/jetbrains/kotlin/psi/KtFile;", "shortName", "Lorg/jetbrains/kotlin/name/Name;", "moduleDescriptor", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "getModuleDescriptor", "()Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "moduleName", Argument.Delimiters.none, "getModuleName", "()Ljava/lang/String;", "deprecationResolver", "Lorg/jetbrains/kotlin/resolve/deprecation/DeprecationResolver;", "getDeprecationResolver", "()Lorg/jetbrains/kotlin/resolve/deprecation/DeprecationResolver;", "typeMapper", "Lorg/jetbrains/kotlin/codegen/state/KotlinTypeMapper;", "getTypeMapper", "()Lorg/jetbrains/kotlin/codegen/state/KotlinTypeMapper;", "typeMapper$delegate", "Lkotlin/Lazy;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class CliLightClassSupport implements KtUltraLightSupport {
        private final LanguageVersionSettings languageVersionSettings;
        private final Project project;

        /* JADX INFO: renamed from: typeMapper$delegate, reason: from kotlin metadata */
        private final Lazy typeMapper;

        public CliLightClassSupport(Project project, LanguageVersionSettings languageVersionSettings) {
            project.getClass();
            languageVersionSettings.getClass();
            this.project = project;
            this.languageVersionSettings = languageVersionSettings;
            this.typeMapper = ImplUtilsKt.lazyPub(new Function0() { // from class: org.jetbrains.kotlin.cli.jvm.compiler.a
                public final Object invoke() {
                    return CliLightClassGenerationSupport.CliLightClassSupport.a(this.b);
                }
            });
        }

        public static KotlinTypeMapper a(CliLightClassSupport cliLightClassSupport) {
            return new KotlinTypeMapper(cliLightClassSupport.getModuleName(), cliLightClassSupport.getLanguageVersionSettings(), false, CliLightClassGenerationSupport$CliLightClassSupport$typeMapper$2$1.INSTANCE, CliLightClassGenerationSupport$CliLightClassSupport$typeMapper$2$2.INSTANCE);
        }

        private final CliTraceHolder getTraceHolder() {
            LightClassGenerationSupport companion = LightClassGenerationSupport.Companion.getInstance(this.project);
            companion.getClass();
            return ((CliLightClassGenerationSupport) companion).getTraceHolder();
        }

        public DeprecationResolver getDeprecationResolver() {
            StorageManager storageManager = LockBasedStorageManager.NO_LOCKS;
            storageManager.getClass();
            return new DeprecationResolver(storageManager, getLanguageVersionSettings(), JavaDeprecationSettings.INSTANCE);
        }

        public LanguageVersionSettings getLanguageVersionSettings() {
            return this.languageVersionSettings;
        }

        public ModuleDescriptor getModuleDescriptor() {
            return getTraceHolder().getModule();
        }

        public String getModuleName() {
            String moduleName = JvmCodegenUtil.getModuleName(getModuleDescriptor());
            moduleName.getClass();
            return moduleName;
        }

        public KotlinTypeMapper getTypeMapper() {
            return (KotlinTypeMapper) this.typeMapper.getValue();
        }

        public boolean possiblyHasAlias(KtFile file, Name shortName) {
            file.getClass();
            shortName.getClass();
            return true;
        }
    }

    public CliLightClassGenerationSupport(CliTraceHolder cliTraceHolder, Project project) {
        cliTraceHolder.getClass();
        project.getClass();
        this.traceHolder = cliTraceHolder;
        this.project = project;
        this.ultraLightSupport = ImplUtilsKt.lazyPub(new Function0() { // from class: e02
            public final Object invoke() {
                return CliLightClassGenerationSupport.a(this.b);
            }
        });
    }

    public static CliLightClassSupport a(CliLightClassGenerationSupport cliLightClassGenerationSupport) {
        return new CliLightClassSupport(cliLightClassGenerationSupport.project, cliLightClassGenerationSupport.traceHolder.getLanguageVersionSettings());
    }

    private final KtUltraLightSupport getUltraLightSupport() {
        return (KtUltraLightSupport) this.ultraLightSupport.getValue();
    }

    public BindingContext analyze(KtElement element) {
        element.getClass();
        return this.traceHolder.getBindingContext();
    }

    public AnnotationDescriptor analyzeAnnotation(KtAnnotationEntry element) {
        element.getClass();
        return (AnnotationDescriptor) this.traceHolder.getBindingContext().get(BindingContext.ANNOTATION, element);
    }

    public BindingContext analyzeWithContent(KtClassOrObject element) {
        element.getClass();
        return this.traceHolder.getBindingContext();
    }

    public final CliTraceHolder getTraceHolder() {
        return this.traceHolder;
    }

    public KtUltraLightSupport getUltraLightClassSupport(KtElement element) {
        element.getClass();
        if (Intrinsics.areEqual(element.getProject(), this.project)) {
            return getUltraLightSupport();
        }
        w01.a("ULC support created from another project from requested");
        return null;
    }

    public DeclarationDescriptor resolveToDescriptor(KtDeclaration declaration) {
        declaration.getClass();
        return (DeclarationDescriptor) this.traceHolder.getBindingContext().get(BindingContext.DECLARATION_TO_DESCRIPTOR, declaration);
    }
}

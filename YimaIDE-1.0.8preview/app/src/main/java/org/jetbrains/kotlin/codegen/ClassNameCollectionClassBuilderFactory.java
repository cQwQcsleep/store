package org.jetbrains.kotlin.codegen;

import com.intellij.psi.PsiElement;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.diagnostics.JvmDeclarationOrigin;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001:\u0001\u000eB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH$J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/codegen/ClassNameCollectionClassBuilderFactory;", "Lorg/jetbrains/kotlin/codegen/DelegatingClassBuilderFactory;", "delegate", "Lorg/jetbrains/kotlin/codegen/ClassBuilderFactory;", "<init>", "(Lorg/jetbrains/kotlin/codegen/ClassBuilderFactory;)V", "handleClashingNames", Argument.Delimiters.none, "internalName", Argument.Delimiters.none, "origin", "Lorg/jetbrains/kotlin/resolve/jvm/diagnostics/JvmDeclarationOrigin;", "newClassBuilder", "Lorg/jetbrains/kotlin/codegen/DelegatingClassBuilder;", "ClassNameCollectionClassBuilder", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ClassNameCollectionClassBuilderFactory extends DelegatingClassBuilderFactory {

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\n\u001a\u00020\u0005H\u0014JQ\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\f2\b\u0010\u0015\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0016\u001a\u00020\f2\u000e\u0010\u0017\u001a\n\u0012\u0006\b\u0001\u0012\u00020\f0\u0018H\u0016¢\u0006\u0002\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/codegen/ClassNameCollectionClassBuilderFactory$ClassNameCollectionClassBuilder;", "Lorg/jetbrains/kotlin/codegen/DelegatingClassBuilder;", "classCreatedFor", "Lorg/jetbrains/kotlin/resolve/jvm/diagnostics/JvmDeclarationOrigin;", "_delegate", "Lorg/jetbrains/kotlin/codegen/ClassBuilder;", "<init>", "(Lorg/jetbrains/kotlin/codegen/ClassNameCollectionClassBuilderFactory;Lorg/jetbrains/kotlin/resolve/jvm/diagnostics/JvmDeclarationOrigin;Lorg/jetbrains/kotlin/codegen/ClassBuilder;)V", "get_delegate$org_jetbrains_kotlin_backend", "()Lorg/jetbrains/kotlin/codegen/ClassBuilder;", "getDelegate", "classInternalName", Argument.Delimiters.none, "defineClass", Argument.Delimiters.none, "origin", "Lcom/intellij/psi/PsiElement;", "version", Argument.Delimiters.none, "access", ModuleXmlParser.NAME, "signature", "superName", "interfaces", Argument.Delimiters.none, "(Lcom/intellij/psi/PsiElement;IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V", "done", "generateSmapCopyToAnnotation", Argument.Delimiters.none, "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public final class ClassNameCollectionClassBuilder extends DelegatingClassBuilder {
        private final ClassBuilder _delegate;
        private final JvmDeclarationOrigin classCreatedFor;
        private String classInternalName;
        final /* synthetic */ ClassNameCollectionClassBuilderFactory this$0;

        public ClassNameCollectionClassBuilder(ClassNameCollectionClassBuilderFactory classNameCollectionClassBuilderFactory, JvmDeclarationOrigin jvmDeclarationOrigin, ClassBuilder classBuilder) {
            jvmDeclarationOrigin.getClass();
            classBuilder.getClass();
            this.this$0 = classNameCollectionClassBuilderFactory;
            this.classCreatedFor = jvmDeclarationOrigin;
            this._delegate = classBuilder;
        }

        @Override // org.jetbrains.kotlin.codegen.DelegatingClassBuilder, org.jetbrains.kotlin.codegen.ClassBuilder
        public void defineClass(PsiElement origin, int version, int access, String name, String signature, String superName, String[] interfaces) {
            name.getClass();
            superName.getClass();
            interfaces.getClass();
            this.classInternalName = name;
            super.defineClass(origin, version, access, name, signature, superName, interfaces);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
        @Override // org.jetbrains.kotlin.codegen.DelegatingClassBuilder, org.jetbrains.kotlin.codegen.ClassBuilder
        public void done(boolean generateSmapCopyToAnnotation) throws UninitializedPropertyAccessException {
            ClassNameCollectionClassBuilderFactory classNameCollectionClassBuilderFactory = this.this$0;
            String str = this.classInternalName;
            if (str == null) {
                Intrinsics.throwUninitializedPropertyAccessException("classInternalName");
                str = null;
            }
            classNameCollectionClassBuilderFactory.handleClashingNames(str, this.classCreatedFor);
            super.done(generateSmapCopyToAnnotation);
        }

        @Override // org.jetbrains.kotlin.codegen.DelegatingClassBuilder
        /* JADX INFO: renamed from: getDelegate, reason: from getter */
        public ClassBuilder get_delegate() {
            return this._delegate;
        }

        public final ClassBuilder get_delegate$org_jetbrains_kotlin_backend() {
            return this._delegate;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClassNameCollectionClassBuilderFactory(ClassBuilderFactory classBuilderFactory) {
        super(classBuilderFactory);
        classBuilderFactory.getClass();
    }

    public abstract void handleClashingNames(String internalName, JvmDeclarationOrigin origin);

    @Override // org.jetbrains.kotlin.codegen.DelegatingClassBuilderFactory, org.jetbrains.kotlin.codegen.ClassBuilderFactory
    public DelegatingClassBuilder newClassBuilder(JvmDeclarationOrigin origin) {
        origin.getClass();
        ClassBuilder classBuilderNewClassBuilder = getDelegate().newClassBuilder(origin);
        classBuilderNewClassBuilder.getClass();
        return new ClassNameCollectionClassBuilder(this, origin, classBuilderNewClassBuilder);
    }
}

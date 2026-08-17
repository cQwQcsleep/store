package org.jetbrains.kotlin.codegen;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.diagnostics.JvmDeclarationOrigin;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&J\u0014\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0014\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0012\u0010\u0011\u001a\u00070\u0012¢\u0006\u0002\b\u0013H\u0097\u0001b\u0002\b\u0014R\u0014\u0010\u0002\u001a\u00020\u0001X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/codegen/DelegatingClassBuilderFactory;", "Lorg/jetbrains/kotlin/codegen/ClassBuilderFactory;", "delegate", "<init>", "(Lorg/jetbrains/kotlin/codegen/ClassBuilderFactory;)V", "getDelegate", "()Lorg/jetbrains/kotlin/codegen/ClassBuilderFactory;", "newClassBuilder", "Lorg/jetbrains/kotlin/codegen/DelegatingClassBuilder;", "origin", "Lorg/jetbrains/kotlin/resolve/jvm/diagnostics/JvmDeclarationOrigin;", "asBytes", Argument.Delimiters.none, "builder", "Lorg/jetbrains/kotlin/codegen/ClassBuilder;", "asText", Argument.Delimiters.none, "getClassBuilderMode", "Lorg/jetbrains/kotlin/codegen/ClassBuilderMode;", "Lkotlin/jvm/internal/EnhancedNullability;", "Lorg/jetbrains/annotations/NotNull;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class DelegatingClassBuilderFactory implements ClassBuilderFactory {
    private final ClassBuilderFactory delegate;

    public DelegatingClassBuilderFactory(ClassBuilderFactory classBuilderFactory) {
        classBuilderFactory.getClass();
        this.delegate = classBuilderFactory;
    }

    @Override // org.jetbrains.kotlin.codegen.ClassBuilderFactory
    public byte[] asBytes(ClassBuilder builder) {
        ClassBuilderFactory classBuilderFactory = this.delegate;
        builder.getClass();
        return classBuilderFactory.asBytes(((DelegatingClassBuilder) builder).getDelegate());
    }

    @Override // org.jetbrains.kotlin.codegen.ClassBuilderFactory
    public String asText(ClassBuilder builder) {
        ClassBuilderFactory classBuilderFactory = this.delegate;
        builder.getClass();
        return classBuilderFactory.asText(((DelegatingClassBuilder) builder).getDelegate());
    }

    @Override // org.jetbrains.kotlin.codegen.ClassBuilderFactory
    public ClassBuilderMode getClassBuilderMode() {
        ClassBuilderMode classBuilderMode = this.delegate.getClassBuilderMode();
        classBuilderMode.getClass();
        return classBuilderMode;
    }

    public final ClassBuilderFactory getDelegate() {
        return this.delegate;
    }

    @Override // org.jetbrains.kotlin.codegen.ClassBuilderFactory
    public abstract DelegatingClassBuilder newClassBuilder(JvmDeclarationOrigin origin);
}

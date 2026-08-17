package org.jetbrains.kotlin.fir.declarations.builder;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.declarations.FirImport;
import org.jetbrains.kotlin.fir.declarations.impl.FirImportImpl;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010!\u001a\u00020\"R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR+\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u00118F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0007\"\u0004\b \u0010\tÊ\u0001\u0002\b$¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/builder/FirImportBuilder;", Argument.Delimiters.none, "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "importedFqName", "Lorg/jetbrains/kotlin/name/FqName;", "getImportedFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "setImportedFqName", "(Lorg/jetbrains/kotlin/name/FqName;)V", "<set-?>", Argument.Delimiters.none, "isAllUnder", "()Z", "setAllUnder", "(Z)V", "isAllUnder$delegate", "Lkotlin/properties/ReadWriteProperty;", "aliasName", "Lorg/jetbrains/kotlin/name/Name;", "getAliasName", "()Lorg/jetbrains/kotlin/name/Name;", "setAliasName", "(Lorg/jetbrains/kotlin/name/Name;)V", "aliasSource", "getAliasSource", "setAliasSource", "build", "Lorg/jetbrains/kotlin/fir/declarations/FirImport;", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirImportBuilder {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(FirImportBuilder.class, "isAllUnder", "isAllUnder()Z", 0)};
    private Name aliasName;
    private KtSourceElement aliasSource;
    private FqName importedFqName;

    /* JADX INFO: renamed from: isAllUnder$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty isAllUnder = Delegates.INSTANCE.notNull();
    private KtSourceElement source;

    public final FirImport build() {
        return new FirImportImpl(this.source, this.importedFqName, isAllUnder(), this.aliasName, this.aliasSource);
    }

    public final Name getAliasName() {
        return this.aliasName;
    }

    public final KtSourceElement getAliasSource() {
        return this.aliasSource;
    }

    public final FqName getImportedFqName() {
        return this.importedFqName;
    }

    public final KtSourceElement getSource() {
        return this.source;
    }

    public final boolean isAllUnder() {
        return ((Boolean) this.isAllUnder.getValue(this, $$delegatedProperties[0])).booleanValue();
    }

    public final void setAliasName(Name name) {
        this.aliasName = name;
    }

    public final void setAliasSource(KtSourceElement ktSourceElement) {
        this.aliasSource = ktSourceElement;
    }

    public final void setAllUnder(boolean z) {
        this.isAllUnder.setValue(this, $$delegatedProperties[0], Boolean.valueOf(z));
    }

    public final void setImportedFqName(FqName fqName) {
        this.importedFqName = fqName;
    }

    public final void setSource(KtSourceElement ktSourceElement) {
        this.source = ktSourceElement;
    }
}

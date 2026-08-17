package org.jetbrains.kotlin.fir.declarations;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.metadata.deserialization.VersionRequirement;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\"?\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u00042\u000e\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"<set-?>", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/metadata/deserialization/VersionRequirement;", "versionRequirements", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "getVersionRequirements", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Ljava/util/List;", "setVersionRequirements", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Ljava/util/List;)V", "versionRequirements$delegate", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataRegistry$DeclarationDataAccessor;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirVersionRequirementsTableKeyKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(FirVersionRequirementsTableKeyKt.class, "versionRequirements", "getVersionRequirements(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Ljava/util/List;", 1)};
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor versionRequirements$delegate = FirDeclarationDataRegistry.INSTANCE.data(FirVersionRequirementsTableKey.INSTANCE);

    public static final List<VersionRequirement> getVersionRequirements(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        return (List) versionRequirements$delegate.getValue(firDeclaration, $$delegatedProperties[0]);
    }

    public static final void setVersionRequirements(FirDeclaration firDeclaration, List<VersionRequirement> list) {
        firDeclaration.getClass();
        versionRequirements$delegate.setValue(firDeclaration, $$delegatedProperties[0], list);
    }
}

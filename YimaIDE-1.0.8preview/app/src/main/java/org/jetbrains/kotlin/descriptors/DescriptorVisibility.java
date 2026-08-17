package org.jetbrains.kotlin.descriptors;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.resolve.scopes.receivers.ReceiverValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\u000f\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\rH&J\b\u0010\u0017\u001a\u00020\rH&J\u0015\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u0000¢\u0006\u0002\u0010\u001bJ\n\u0010 \u001a\u00020\tH\u0086\u0080\u0004J\b\u0010!\u001a\u00020\u0000H&J\b\u0010\"\u001a\u0004\u0018\u00010#J\u0018\u0010$\u001a\u00020\r2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&H\u0016R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\f\u0010\u000eR\u0012\u0010\u001c\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u000bR\u0012\u0010\u001e\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u000b¨\u0006("}, d2 = {"Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", Argument.Delimiters.none, "<init>", "()V", "delegate", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "getDelegate", "()Lorg/jetbrains/kotlin/descriptors/Visibility;", ModuleXmlParser.NAME, Argument.Delimiters.none, "getName", "()Ljava/lang/String;", "isPublicAPI", Argument.Delimiters.none, "()Z", "isVisible", "receiver", "Lorg/jetbrains/kotlin/resolve/scopes/receivers/ReceiverValue;", "what", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptorWithVisibility;", "from", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "useSpecialRulesForPrivateSealedConstructors", "mustCheckInImports", "compareTo", Argument.Delimiters.none, "visibility", "(Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;)Ljava/lang/Integer;", "internalDisplayName", "getInternalDisplayName", "externalDisplayName", "getExternalDisplayName", "toString", "normalize", "customEffectiveVisibility", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "visibleFromPackage", "fromPackage", "Lorg/jetbrains/kotlin/name/FqName;", "myPackage", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class DescriptorVisibility {
    public final Integer compareTo(DescriptorVisibility visibility) {
        visibility.getClass();
        return getDelegate().compareTo(visibility.getDelegate());
    }

    public final EffectiveVisibility customEffectiveVisibility() {
        return getDelegate().customEffectiveVisibility();
    }

    public abstract Visibility getDelegate();

    public abstract String getExternalDisplayName();

    public abstract String getInternalDisplayName();

    public final String getName() {
        return getDelegate().getName();
    }

    public final boolean isPublicAPI() {
        return getDelegate().getIsPublicAPI();
    }

    public abstract boolean isVisible(ReceiverValue receiver, DeclarationDescriptorWithVisibility what, DeclarationDescriptor from, boolean useSpecialRulesForPrivateSealedConstructors);

    public abstract boolean mustCheckInImports();

    public abstract DescriptorVisibility normalize();

    public final String toString() {
        return getDelegate().toString();
    }

    public boolean visibleFromPackage(FqName fromPackage, FqName myPackage) {
        fromPackage.getClass();
        myPackage.getClass();
        return true;
    }
}

package org.jetbrains.kotlin.fir.extensions;

import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderInternals;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u0000 (2\u00020\u0001:\u0001(B\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0010\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J.\u0010\u0014\u001a\u00020\u00152\u0010\u0010\u0016\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00180\u00172\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0017b\u0002\b\u001dJ*\u0010\u001e\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00172\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0017b\u0002\b\u001dJ*\u0010 \u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020!0\u00172\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0017b\u0002\b\u001dJ\u0010\u0010\"\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u001aH\u0016J\f\u0010$\u001a\u00020\u0015H\u0007b\u0002\b\u001dJ\f\u0010%\u001a\u00020\u0015H\u0007b\u0002\b\u001dJ\u0011\u0010&\u001a\u00020\u0007H\u0001b\u0002\b\u001d¢\u0006\u0002\b'R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0094\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006)"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/FirSwitchableExtensionDeclarationsSymbolProvider;", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "delegate", "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionDeclarationsSymbolProvider;", "<init>", "(Lorg/jetbrains/kotlin/fir/extensions/FirExtensionDeclarationsSymbolProvider;)V", "disabled", Argument.Delimiters.none, "getDisabled", "()Z", "setDisabled", "(Z)V", "symbolNamesProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", "getSymbolNamesProvider", "()Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", "getClassLikeSymbolByClassId", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "getTopLevelCallableSymbolsTo", Argument.Delimiters.none, "destination", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProviderInternals;", "getTopLevelFunctionSymbolsTo", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "getTopLevelPropertySymbolsTo", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "hasPackage", "fqName", "disable", "enable", "isDisabled", "isDisabled$org_jetbrains_kotlin_providers", "Companion", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirSwitchableExtensionDeclarationsSymbolProvider extends FirSymbolProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final FirExtensionDeclarationsSymbolProvider delegate;
    private boolean disabled;
    private final FirSymbolNamesProvider symbolNamesProvider;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirSwitchableExtensionDeclarationsSymbolProvider(FirExtensionDeclarationsSymbolProvider firExtensionDeclarationsSymbolProvider) {
        super(firExtensionDeclarationsSymbolProvider.getSession());
        firExtensionDeclarationsSymbolProvider.getClass();
        this.delegate = firExtensionDeclarationsSymbolProvider;
        this.symbolNamesProvider = new FirSymbolNamesProvider() { // from class: org.jetbrains.kotlin.fir.extensions.FirSwitchableExtensionDeclarationsSymbolProvider$symbolNamesProvider$1
            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public boolean getHasSpecificCallablePackageNamesComputation() {
                return this.this$0.delegate.getSymbolNamesProvider().getHasSpecificCallablePackageNamesComputation();
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public boolean getHasSpecificClassifierPackageNamesComputation() {
                return this.this$0.delegate.getSymbolNamesProvider().getHasSpecificClassifierPackageNamesComputation();
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public Set<String> getPackageNames() {
                if (this.this$0.getDisabled()) {
                    return null;
                }
                return this.this$0.delegate.getSymbolNamesProvider().getPackageNames();
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public Set<String> getPackageNamesWithTopLevelCallables() {
                if (this.this$0.getDisabled()) {
                    return null;
                }
                return this.this$0.delegate.getSymbolNamesProvider().getPackageNamesWithTopLevelCallables();
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public Set<String> getPackageNamesWithTopLevelClassifiers() {
                if (this.this$0.getDisabled()) {
                    return null;
                }
                return this.this$0.delegate.getSymbolNamesProvider().getPackageNamesWithTopLevelClassifiers();
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public Set<Name> getTopLevelCallableNamesInPackage(FqName packageFqName) {
                packageFqName.getClass();
                if (this.this$0.getDisabled()) {
                    return null;
                }
                return this.this$0.delegate.getSymbolNamesProvider().getTopLevelCallableNamesInPackage(packageFqName);
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public Set<Name> getTopLevelClassifierNamesInPackage(FqName packageFqName) {
                packageFqName.getClass();
                if (this.this$0.getDisabled()) {
                    return null;
                }
                return this.this$0.delegate.getSymbolNamesProvider().getTopLevelClassifierNamesInPackage(packageFqName);
            }
        };
    }

    @FirSymbolProviderInternals
    public final void disable() {
        if (getDisabled()) {
            dt1.a("Attempt to disable already disabled ", Reflection.getOrCreateKotlinClass(FirSwitchableExtensionDeclarationsSymbolProvider.class));
        } else {
            setDisabled(true);
        }
    }

    @FirSymbolProviderInternals
    public final void enable() {
        if (getDisabled()) {
            setDisabled(false);
        } else {
            dt1.a("Attempt to enable already enabled ", Reflection.getOrCreateKotlinClass(FirSwitchableExtensionDeclarationsSymbolProvider.class));
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public FirClassLikeSymbol<?> getClassLikeSymbolByClassId(ClassId classId) {
        classId.getClass();
        if (getDisabled()) {
            return null;
        }
        return this.delegate.getClassLikeSymbolByClassId(classId);
    }

    public boolean getDisabled() {
        return this.disabled;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public FirSymbolNamesProvider getSymbolNamesProvider() {
        return this.symbolNamesProvider;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    @FirSymbolProviderInternals
    public void getTopLevelCallableSymbolsTo(List<FirCallableSymbol<?>> destination, FqName packageFqName, Name name) {
        destination.getClass();
        packageFqName.getClass();
        name.getClass();
        if (getDisabled()) {
            return;
        }
        this.delegate.getTopLevelCallableSymbolsTo(destination, packageFqName, name);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    @FirSymbolProviderInternals
    public void getTopLevelFunctionSymbolsTo(List<FirNamedFunctionSymbol> destination, FqName packageFqName, Name name) {
        destination.getClass();
        packageFqName.getClass();
        name.getClass();
        if (getDisabled()) {
            return;
        }
        this.delegate.getTopLevelFunctionSymbolsTo(destination, packageFqName, name);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    @FirSymbolProviderInternals
    public void getTopLevelPropertySymbolsTo(List<FirPropertySymbol> destination, FqName packageFqName, Name name) {
        destination.getClass();
        packageFqName.getClass();
        name.getClass();
        if (getDisabled()) {
            return;
        }
        this.delegate.getTopLevelPropertySymbolsTo(destination, packageFqName, name);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public boolean hasPackage(FqName fqName) {
        fqName.getClass();
        if (getDisabled()) {
            return false;
        }
        return this.delegate.hasPackage(fqName);
    }

    @FirSymbolProviderInternals
    public final boolean isDisabled$org_jetbrains_kotlin_providers() {
        return getDisabled();
    }

    public void setDisabled(boolean z) {
        this.disabled = z;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/FirSwitchableExtensionDeclarationsSymbolProvider$Companion;", Argument.Delimiters.none, "<init>", "()V", "createIfNeeded", "Lorg/jetbrains/kotlin/fir/extensions/FirSwitchableExtensionDeclarationsSymbolProvider;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FirSwitchableExtensionDeclarationsSymbolProvider createIfNeeded(FirSession session) {
            session.getClass();
            FirExtensionDeclarationsSymbolProvider firExtensionDeclarationsSymbolProviderCreateIfNeeded = FirExtensionDeclarationsSymbolProvider.INSTANCE.createIfNeeded(session);
            if (firExtensionDeclarationsSymbolProviderCreateIfNeeded != null) {
                return new FirSwitchableExtensionDeclarationsSymbolProvider(firExtensionDeclarationsSymbolProviderCreateIfNeeded);
            }
            return null;
        }

        private Companion() {
        }
    }
}

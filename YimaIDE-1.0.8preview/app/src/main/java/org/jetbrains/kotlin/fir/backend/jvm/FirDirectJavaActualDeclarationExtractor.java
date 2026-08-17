package org.jetbrains.kotlin.fir.backend.jvm;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.backend.common.actualizer.IrExtraActualDeclarationExtractor;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.backend.Fir2IrClassifierStorage;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.java.JavaSymbolProvider;
import org.jetbrains.kotlin.fir.java.JavaSymbolProviderKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationWithName;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;
import org.jetbrains.kotlin.ir.util.AdditionalIrUtilsKt;
import org.jetbrains.kotlin.name.CallableId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J$\u0010\b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/jvm/FirDirectJavaActualDeclarationExtractor;", "Lorg/jetbrains/kotlin/backend/common/actualizer/IrExtraActualDeclarationExtractor;", "javaSymbolProvider", "Lorg/jetbrains/kotlin/fir/java/JavaSymbolProvider;", "classifierStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "<init>", "(Lorg/jetbrains/kotlin/fir/java/JavaSymbolProvider;Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;)V", "extract", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "expectIrClass", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "expectTopLevelCallables", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationWithName;", "expectCallableId", "Lorg/jetbrains/kotlin/name/CallableId;", "Companion", "org.jetbrains.kotlin:jvm-backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDirectJavaActualDeclarationExtractor extends IrExtraActualDeclarationExtractor {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Fir2IrClassifierStorage classifierStorage;
    private final JavaSymbolProvider javaSymbolProvider;

    private FirDirectJavaActualDeclarationExtractor(JavaSymbolProvider javaSymbolProvider, Fir2IrClassifierStorage fir2IrClassifierStorage) {
        this.javaSymbolProvider = javaSymbolProvider;
        this.classifierStorage = fir2IrClassifierStorage;
    }

    public IrClassSymbol extract(IrClass expectIrClass) {
        expectIrClass.getClass();
        FirRegularClassSymbol classLikeSymbolByClassId = this.javaSymbolProvider.getClassLikeSymbolByClassId(AdditionalIrUtilsKt.getClassIdOrFail(expectIrClass));
        if (classLikeSymbolByClassId == null || !(classLikeSymbolByClassId.getOrigin() instanceof FirDeclarationOrigin.Java.Source)) {
            classLikeSymbolByClassId = null;
        }
        if (classLikeSymbolByClassId != null) {
            return this.classifierStorage.getIrClassSymbol(classLikeSymbolByClassId);
        }
        return null;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/jvm/FirDirectJavaActualDeclarationExtractor$Companion;", Argument.Delimiters.none, "<init>", "()V", "initializeIfNeeded", "Lorg/jetbrains/kotlin/fir/backend/jvm/FirDirectJavaActualDeclarationExtractor;", "platformComponents", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "org.jetbrains.kotlin:jvm-backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FirDirectJavaActualDeclarationExtractor initializeIfNeeded(Fir2IrComponents platformComponents) {
            platformComponents.getClass();
            JavaSymbolProvider javaSymbolProvider = JavaSymbolProviderKt.getJavaSymbolProvider(platformComponents.getSession());
            DefaultConstructorMarker defaultConstructorMarker = null;
            if (javaSymbolProvider != null && FirLanguageSettingsComponentKt.getLanguageVersionSettings(platformComponents.getSession()).supportsFeature(LanguageFeature.MultiPlatformProjects) && FirLanguageSettingsComponentKt.getLanguageVersionSettings(platformComponents.getSession()).supportsFeature(LanguageFeature.DirectJavaActualization)) {
                return new FirDirectJavaActualDeclarationExtractor(javaSymbolProvider, platformComponents.getClassifierStorage(), defaultConstructorMarker);
            }
            return null;
        }

        private Companion() {
        }
    }

    public /* synthetic */ FirDirectJavaActualDeclarationExtractor(JavaSymbolProvider javaSymbolProvider, Fir2IrClassifierStorage fir2IrClassifierStorage, DefaultConstructorMarker defaultConstructorMarker) {
        this(javaSymbolProvider, fir2IrClassifierStorage);
    }

    public List<IrSymbol> extract(List<? extends IrDeclarationWithName> expectTopLevelCallables, CallableId expectCallableId) {
        expectTopLevelCallables.getClass();
        expectCallableId.getClass();
        return CollectionsKt.emptyList();
    }
}

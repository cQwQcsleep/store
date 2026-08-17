package org.jetbrains.kotlin.fir.backend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.backend.common.extensions.DeclarationFinder;
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponent;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponentKt;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.backend.utils.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.scopes.FirScopeKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousObjectSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.ir.IrBuiltIns;
import org.jetbrains.kotlin.ir.IrDiagnosticReporter;
import org.jetbrains.kotlin.ir.KtDiagnosticReporterWithImplicitIrBasedContext;
import org.jetbrains.kotlin.ir.ObsoleteDescriptorBasedAPI;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationWithName;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;
import org.jetbrains.kotlin.ir.declarations.MetadataSource;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrConstructorSymbol;
import org.jetbrains.kotlin.ir.symbols.IrFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.util.ReferenceSymbolTable;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.platform.TargetPlatform;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000à\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001_B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\b\u0010:\u001a\u00020<H\u0016J\u0010\u0010=\u001a\u00020<2\u0006\u0010>\u001a\u00020?H\u0016J,\u0010@\u001a\u0004\u0018\u00010A2\u0006\u0010B\u001a\u00020CH\u0017b\u0018\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(D\u0012\n\b \u0012\u0006\b\n0!8\"J,\u0010E\u001a\u0004\u0018\u00010F2\u0006\u0010B\u001a\u00020CH\u0017b\u0018\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(D\u0012\n\b \u0012\u0006\b\n0!8\"J0\u0010G\u001a\b\u0012\u0004\u0012\u00020I0H2\u0006\u0010B\u001a\u00020CH\u0017b\u0018\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(D\u0012\n\b \u0012\u0006\b\n0!8\"J0\u0010J\u001a\b\u0012\u0004\u0012\u00020K0H2\u0006\u0010L\u001a\u00020MH\u0017b\u0018\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(D\u0012\n\b \u0012\u0006\b\n0!8\"J0\u0010N\u001a\b\u0012\u0004\u0012\u00020O0H2\u0006\u0010L\u001a\u00020MH\u0017b\u0018\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(D\u0012\n\b \u0012\u0006\b\n0!8\"J\u0018\u0010P\u001a\u00020Q2\u0006\u0010R\u001a\u00020S2\u0006\u0010>\u001a\u00020?H\u0016J\u001c\u0010P\u001a\u00020Q2\b\u0010T\u001a\u0004\u0018\u0001092\b\u0010>\u001a\u0004\u0018\u00010?H\u0002J\u000e\u0010U\u001a\u00020Q2\u0006\u0010V\u001a\u00020WR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R \u0010\u0006\u001a\u00020\u00078\u0016X\u0097\u0004r\u0002\b\u0016¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R \u0010\b\u001a\u00020\t8\u0016X\u0097\u0004r\u0002\b\u0016¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0013\u001a\u0004\b\u0018\u0010\u0019R6\u0010\n\u001a\u00020\u000b8\u0016X\u0097\u0004r\u0018\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\n\b \u0012\u0006\b\n0!8\"¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0013\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010#\u001a\u00020$X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020(8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*R\u0014\u0010+\u001a\u00020,8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b-\u0010.R\u0014\u0010/\u001a\u0002008BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b1\u00102R\u0014\u00103\u001a\u0002048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b5\u00106R\u0014\u00107\u001a\b\u0012\u0004\u0012\u00020908X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010:\u001a\u00060;R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010X\u001a\u0004\u0018\u00010Y*\u00020?8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bZ\u0010[R\u0014\u0010\f\u001a\u00020\\X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b]\u0010^¨\u0006`"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrPluginContext;", "Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;", "c", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "irBuiltIns", "Lorg/jetbrains/kotlin/ir/IrBuiltIns;", "moduleDescriptor", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "symbolTable", "Lorg/jetbrains/kotlin/ir/util/ReferenceSymbolTable;", "messageCollector", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "diagnosticReporter", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/ir/IrBuiltIns;Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;Lorg/jetbrains/kotlin/ir/util/ReferenceSymbolTable;Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;)V", "getIrBuiltIns", "()Lorg/jetbrains/kotlin/ir/IrBuiltIns;", "getModuleDescriptor$annotations", "()V", "getModuleDescriptor", "()Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "Lorg/jetbrains/kotlin/ir/ObsoleteDescriptorBasedAPI;", "getSymbolTable$annotations", "getSymbolTable", "()Lorg/jetbrains/kotlin/ir/util/ReferenceSymbolTable;", "getMessageCollector$annotations", "getMessageCollector", "()Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "Lkotlin/Deprecated;", "message", "Consider using diagnosticReporter instead. See https://youtrack.jetbrains.com/issue/KT-78277 for more details", "level", "Lkotlin/DeprecationLevel;", "WARNING", "afterK2", Argument.Delimiters.none, "getAfterK2", "()Z", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "getLanguageVersionSettings", "()Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "platform", "Lorg/jetbrains/kotlin/platform/TargetPlatform;", "getPlatform", "()Lorg/jetbrains/kotlin/platform/TargetPlatform;", "symbolProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "getSymbolProvider", "()Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "metadataDeclarationRegistrar", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "getMetadataDeclarationRegistrar", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "lookupsWithoutSpecificFile", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "finderForBuiltins", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrPluginContext$Finder;", "Lorg/jetbrains/kotlin/backend/common/extensions/DeclarationFinder;", "finderForSource", "fromFile", "Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "referenceClass", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "Please use `finderForBuiltins()` or `finderForSource(fromFile)` instead.", "referenceClassifier", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "referenceConstructors", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/symbols/IrConstructorSymbol;", "referenceFunctions", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "referenceProperties", "Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;", "recordLookup", Argument.Delimiters.none, "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationWithName;", "fqName", "recordLookupsWithoutSpecificFile", "moduleFragment", "Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;", "fileSource", "Lorg/jetbrains/kotlin/KtSourceElement;", "getFileSource", "(Lorg/jetbrains/kotlin/ir/declarations/IrFile;)Lorg/jetbrains/kotlin/KtSourceElement;", "Lorg/jetbrains/kotlin/ir/IrDiagnosticReporter;", "getDiagnosticReporter", "()Lorg/jetbrains/kotlin/ir/IrDiagnosticReporter;", "Finder", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrPluginContext implements IrPluginContext {
    private final boolean afterK2;
    private final Fir2IrComponents c;
    private final IrDiagnosticReporter diagnosticReporter;
    private final Finder finderForBuiltins;
    private final IrBuiltIns irBuiltIns;
    private final Set<FqName> lookupsWithoutSpecificFile;
    private final MessageCollector messageCollector;
    private final ModuleDescriptor moduleDescriptor;
    private final ReferenceSymbolTable symbolTable;

    @Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0012\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u009c\u0001\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00180\u000f\"\f\b\u0000\u0010\u0019*\u0006\u0012\u0002\b\u00030\u001a\"\b\b\u0001\u0010\u001b*\u00020\r\"\n\b\u0002\u0010\u0018\u0018\u0001*\u0002H\u001b2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u001d\u0010\u001c\u001a\u0019\u0012\u0004\u0012\u00020\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00190\u000f0\u001d¢\u0006\u0002\b\u001f2\u001d\u0010 \u001a\u0019\u0012\u0004\u0012\u00020!\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00190\u000f0\u001d¢\u0006\u0002\b\u001f2\u001f\u0010\"\u001a\u001b\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u0002H\u0019\u0012\u0006\u0012\u0004\u0018\u0001H\u001b0#¢\u0006\u0002\b\u001fH\u0082\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrPluginContext$Finder;", "Lorg/jetbrains/kotlin/backend/common/extensions/DeclarationFinder;", "fromFile", "Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrPluginContext;Lorg/jetbrains/kotlin/ir/declarations/IrFile;)V", "getFromFile", "()Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "findClass", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "findClassifier", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "findConstructors", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/symbols/IrConstructorSymbol;", "findFunctions", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "findProperties", "Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;", "referenceCallableSymbols", "R", "F", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "S", "getCallablesFromScope", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "Lkotlin/ExtensionFunctionType;", "getCallablesFromProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "irExtractor", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public final class Finder implements DeclarationFinder {
        private final IrFile fromFile;

        public Finder(IrFile irFile) {
            this.fromFile = irFile;
        }

        @Override // org.jetbrains.kotlin.backend.common.extensions.DeclarationFinder
        public IrClassSymbol findClass(ClassId classId) {
            classId.getClass();
            do {
                Fir2IrPluginContext.this.recordLookup(classId.asSingleFqName(), this.fromFile);
                FirClassLikeSymbol<?> classLikeSymbolByClassId = Fir2IrPluginContext.this.getSymbolProvider().getClassLikeSymbolByClassId(classId);
                if (classLikeSymbolByClassId == null) {
                    return null;
                }
                if (classLikeSymbolByClassId instanceof FirRegularClassSymbol) {
                    return Fir2IrPluginContext.this.c.getClassifierStorage().getIrClassSymbol((FirClassSymbol<?>) classLikeSymbolByClassId);
                }
                if (!(classLikeSymbolByClassId instanceof FirTypeAliasSymbol)) {
                    if (!(classLikeSymbolByClassId instanceof FirAnonymousObjectSymbol)) {
                        bu8.a();
                        return null;
                    }
                    AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
                    wq6.a();
                    return null;
                }
                classId = ConeTypeUtilsKt.getClassId(((FirTypeAliasSymbol) classLikeSymbolByClassId).getResolvedExpandedTypeRef().getConeType());
            } while (classId != null);
            return null;
        }

        @Override // org.jetbrains.kotlin.backend.common.extensions.DeclarationFinder
        public IrSymbol findClassifier(ClassId classId) {
            classId.getClass();
            Fir2IrPluginContext.this.recordLookup(classId.asSingleFqName(), this.fromFile);
            FirClassLikeSymbol<?> classLikeSymbolByClassId = Fir2IrPluginContext.this.getSymbolProvider().getClassLikeSymbolByClassId(classId);
            if (classLikeSymbolByClassId == null) {
                return null;
            }
            if (classLikeSymbolByClassId instanceof FirRegularClassSymbol) {
                return Fir2IrPluginContext.this.c.getClassifierStorage().getIrClassSymbol((FirClassSymbol<?>) classLikeSymbolByClassId);
            }
            if (classLikeSymbolByClassId instanceof FirTypeAliasSymbol) {
                return Fir2IrPluginContext.this.c.getClassifierStorage().getIrTypeAliasSymbol((FirTypeAliasSymbol) classLikeSymbolByClassId);
            }
            if (!(classLikeSymbolByClassId instanceof FirAnonymousObjectSymbol)) {
                bu8.a();
                return null;
            }
            AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
            wq6.a();
            return null;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
        @Override // org.jetbrains.kotlin.backend.common.extensions.DeclarationFinder
        public Collection<IrConstructorSymbol> findConstructors(ClassId classId) throws KotlinIllegalArgumentExceptionWithAttachments {
            FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass;
            classId.getClass();
            Fir2IrPluginContext.this.recordLookup(classId.asSingleFqName(), this.fromFile);
            FirClassLikeSymbol<?> classLikeSymbolByClassId = Fir2IrPluginContext.this.getSymbolProvider().getClassLikeSymbolByClassId(classId);
            if (classLikeSymbolByClassId == null || (firRegularClassSymbolFullyExpandedClass = DeclarationUtilsKt.fullyExpandedClass(classLikeSymbolByClassId, Fir2IrPluginContext.this.c.getSession())) == null) {
                return CollectionsKt.emptyList();
            }
            List<FirConstructorSymbol> declaredConstructors = FirScopeKt.getDeclaredConstructors(ScopeUtilsKt.unsubstitutedScope(Fir2IrPluginContext.this.c, firRegularClassSymbolFullyExpandedClass));
            Fir2IrPluginContext fir2IrPluginContext = Fir2IrPluginContext.this;
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = declaredConstructors.iterator();
            while (it.hasNext()) {
                IrConstructorSymbol irConstructorSymbol$default = Fir2IrDeclarationStorage.getIrConstructorSymbol$default(fir2IrPluginContext.c.getDeclarationStorage(), (FirConstructorSymbol) ((FirCallableSymbol) it.next()), false, 2, null);
                if (irConstructorSymbol$default != null) {
                    arrayList.add(irConstructorSymbol$default);
                }
            }
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : arrayList) {
                if (obj instanceof IrConstructorSymbol) {
                    arrayList2.add(obj);
                }
            }
            return arrayList2;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
        @Override // org.jetbrains.kotlin.backend.common.extensions.DeclarationFinder
        public Collection<IrSimpleFunctionSymbol> findFunctions(CallableId callableId) throws KotlinIllegalArgumentExceptionWithAttachments {
            List<FirNamedFunctionSymbol> topLevelFunctionSymbols;
            FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass;
            callableId.getClass();
            Fir2IrPluginContext.this.recordLookup(callableId.asSingleFqName(), this.fromFile);
            ClassId classId = callableId.getClassId();
            Fir2IrPluginContext fir2IrPluginContext = Fir2IrPluginContext.this;
            if (classId != null) {
                FirClassLikeSymbol<?> classLikeSymbolByClassId = fir2IrPluginContext.getSymbolProvider().getClassLikeSymbolByClassId(classId);
                if (classLikeSymbolByClassId == null || (firRegularClassSymbolFullyExpandedClass = DeclarationUtilsKt.fullyExpandedClass(classLikeSymbolByClassId, Fir2IrPluginContext.this.c.getSession())) == null) {
                    return CollectionsKt.emptyList();
                }
                topLevelFunctionSymbols = FirScopeKt.getFunctions(ScopeUtilsKt.unsubstitutedScope(Fir2IrPluginContext.this.c, firRegularClassSymbolFullyExpandedClass), callableId.getCallableName());
            } else {
                topLevelFunctionSymbols = fir2IrPluginContext.getSymbolProvider().getTopLevelFunctionSymbols(callableId.getPackageName(), callableId.getCallableName());
            }
            Fir2IrPluginContext fir2IrPluginContext2 = Fir2IrPluginContext.this;
            ArrayList arrayList = new ArrayList();
            Iterator it = topLevelFunctionSymbols.iterator();
            while (it.hasNext()) {
                IrFunctionSymbol irFunctionSymbol$default = Fir2IrDeclarationStorage.getIrFunctionSymbol$default(fir2IrPluginContext2.c.getDeclarationStorage(), (FirFunctionSymbol) ((FirCallableSymbol) it.next()), null, false, 6, null);
                if (irFunctionSymbol$default != null) {
                    arrayList.add(irFunctionSymbol$default);
                }
            }
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : arrayList) {
                if (obj instanceof IrSimpleFunctionSymbol) {
                    arrayList2.add(obj);
                }
            }
            return arrayList2;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
        @Override // org.jetbrains.kotlin.backend.common.extensions.DeclarationFinder
        public Collection<IrPropertySymbol> findProperties(CallableId callableId) throws KotlinIllegalArgumentExceptionWithAttachments {
            Collection topLevelPropertySymbols;
            FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass;
            callableId.getClass();
            Fir2IrPluginContext.this.recordLookup(callableId.asSingleFqName(), this.fromFile);
            ClassId classId = callableId.getClassId();
            Fir2IrPluginContext fir2IrPluginContext = Fir2IrPluginContext.this;
            if (classId != null) {
                FirClassLikeSymbol<?> classLikeSymbolByClassId = fir2IrPluginContext.getSymbolProvider().getClassLikeSymbolByClassId(classId);
                if (classLikeSymbolByClassId == null || (firRegularClassSymbolFullyExpandedClass = DeclarationUtilsKt.fullyExpandedClass(classLikeSymbolByClassId, Fir2IrPluginContext.this.c.getSession())) == null) {
                    return CollectionsKt.emptyList();
                }
                List<FirVariableSymbol<?>> properties = FirScopeKt.getProperties(ScopeUtilsKt.unsubstitutedScope(Fir2IrPluginContext.this.c, firRegularClassSymbolFullyExpandedClass), callableId.getCallableName());
                topLevelPropertySymbols = new ArrayList();
                for (Object obj : properties) {
                    if (obj instanceof FirPropertySymbol) {
                        topLevelPropertySymbols.add(obj);
                    }
                }
            } else {
                topLevelPropertySymbols = fir2IrPluginContext.getSymbolProvider().getTopLevelPropertySymbols(callableId.getPackageName(), callableId.getCallableName());
            }
            Fir2IrPluginContext fir2IrPluginContext2 = Fir2IrPluginContext.this;
            ArrayList arrayList = new ArrayList();
            Iterator it = topLevelPropertySymbols.iterator();
            while (it.hasNext()) {
                IrSymbol irPropertySymbol$default = Fir2IrDeclarationStorage.getIrPropertySymbol$default(fir2IrPluginContext2.c.getDeclarationStorage(), (FirPropertySymbol) ((FirCallableSymbol) it.next()), null, 2, null);
                if (irPropertySymbol$default != null) {
                    arrayList.add(irPropertySymbol$default);
                }
            }
            ArrayList arrayList2 = new ArrayList();
            for (Object obj2 : arrayList) {
                if (obj2 instanceof IrPropertySymbol) {
                    arrayList2.add(obj2);
                }
            }
            return arrayList2;
        }

        public final IrFile getFromFile() {
            return this.fromFile;
        }
    }

    public Fir2IrPluginContext(Fir2IrComponents fir2IrComponents, IrBuiltIns irBuiltIns, ModuleDescriptor moduleDescriptor, ReferenceSymbolTable referenceSymbolTable, MessageCollector messageCollector, DiagnosticReporter diagnosticReporter) {
        fir2IrComponents.getClass();
        irBuiltIns.getClass();
        moduleDescriptor.getClass();
        referenceSymbolTable.getClass();
        messageCollector.getClass();
        diagnosticReporter.getClass();
        this.c = fir2IrComponents;
        this.irBuiltIns = irBuiltIns;
        this.moduleDescriptor = moduleDescriptor;
        this.symbolTable = referenceSymbolTable;
        this.messageCollector = messageCollector;
        this.afterK2 = true;
        this.lookupsWithoutSpecificFile = new LinkedHashSet();
        this.finderForBuiltins = new Finder(null);
        this.diagnosticReporter = new KtDiagnosticReporterWithImplicitIrBasedContext(diagnosticReporter, getLanguageVersionSettings());
    }

    private final KtSourceElement getFileSource(IrFile irFile) {
        FirFile fir;
        MetadataSource metadata = irFile.getMetadata();
        FirMetadataSource.File file = metadata instanceof FirMetadataSource.File ? (FirMetadataSource.File) metadata : null;
        if (file == null || (fir = file.getFir()) == null) {
            return null;
        }
        return fir.getSource();
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Consider using diagnosticReporter instead. See https://youtrack.jetbrains.com/issue/KT-78277 for more details")
    public static /* synthetic */ void getMessageCollector$annotations() {
    }

    @ObsoleteDescriptorBasedAPI
    public static /* synthetic */ void getModuleDescriptor$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FirSymbolProvider getSymbolProvider() {
        return FirSymbolProviderKt.getSymbolProvider(this.c.getSession());
    }

    @ObsoleteDescriptorBasedAPI
    public static /* synthetic */ void getSymbolTable$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void recordLookup(FqName fqName, IrFile fromFile) {
        if (fqName == null) {
            return;
        }
        if (fromFile == null) {
            this.lookupsWithoutSpecificFile.add(fqName);
            return;
        }
        FirLookupTrackerComponent lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(this.c.getSession());
        if (lookupTracker == null) {
            return;
        }
        FirLookupTrackerComponentKt.recordFqNameLookup(lookupTracker, fqName, null, getFileSource(fromFile));
    }

    public DeclarationFinder finderForBuiltins() {
        return this.finderForBuiltins;
    }

    public DeclarationFinder finderForSource(IrFile fromFile) {
        fromFile.getClass();
        return new Finder(fromFile);
    }

    public boolean getAfterK2() {
        return this.afterK2;
    }

    public IrDiagnosticReporter getDiagnosticReporter() {
        return this.diagnosticReporter;
    }

    public IrBuiltIns getIrBuiltIns() {
        return this.irBuiltIns;
    }

    public LanguageVersionSettings getLanguageVersionSettings() {
        return FirLanguageSettingsComponentKt.getLanguageVersionSettings(this.c.getSession());
    }

    public MessageCollector getMessageCollector() {
        return this.messageCollector;
    }

    public Fir2IrIrGeneratedDeclarationsRegistrar getMetadataDeclarationRegistrar() {
        return this.c.getAnnotationsFromPluginRegistrar();
    }

    public ModuleDescriptor getModuleDescriptor() {
        return this.moduleDescriptor;
    }

    public TargetPlatform getPlatform() {
        return FirModuleDataKt.getModuleData(this.c.getSession()).getPlatform();
    }

    public ReferenceSymbolTable getSymbolTable() {
        return this.symbolTable;
    }

    public final void recordLookupsWithoutSpecificFile(IrModuleFragment moduleFragment) {
        moduleFragment.getClass();
        FirLookupTrackerComponent lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(this.c.getSession());
        if (lookupTracker == null) {
            return;
        }
        Iterator it = moduleFragment.getFiles().iterator();
        while (it.hasNext()) {
            KtSourceElement fileSource = getFileSource((IrFile) it.next());
            if (fileSource != null) {
                Iterator<FqName> it2 = this.lookupsWithoutSpecificFile.iterator();
                while (it2.hasNext()) {
                    FirLookupTrackerComponentKt.recordFqNameLookup(lookupTracker, it2.next(), null, fileSource);
                }
            }
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Please use `finderForBuiltins()` or `finderForSource(fromFile)` instead.")
    public IrClassSymbol referenceClass(ClassId classId) {
        classId.getClass();
        return finderForBuiltins().findClass(classId);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Please use `finderForBuiltins()` or `finderForSource(fromFile)` instead.")
    public IrSymbol referenceClassifier(ClassId classId) {
        classId.getClass();
        return finderForBuiltins().findClassifier(classId);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Please use `finderForBuiltins()` or `finderForSource(fromFile)` instead.")
    public Collection<IrConstructorSymbol> referenceConstructors(ClassId classId) {
        classId.getClass();
        return finderForBuiltins().findConstructors(classId);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Please use `finderForBuiltins()` or `finderForSource(fromFile)` instead.")
    public Collection<IrSimpleFunctionSymbol> referenceFunctions(CallableId callableId) {
        callableId.getClass();
        return finderForBuiltins().findFunctions(callableId);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Please use `finderForBuiltins()` or `finderForSource(fromFile)` instead.")
    public Collection<IrPropertySymbol> referenceProperties(CallableId callableId) {
        callableId.getClass();
        return finderForBuiltins().findProperties(callableId);
    }

    public void recordLookup(IrDeclarationWithName declaration, IrFile fromFile) {
        declaration.getClass();
        fromFile.getClass();
        recordLookup(IrUtilsKt.getFqNameWhenAvailable(declaration), fromFile);
    }
}

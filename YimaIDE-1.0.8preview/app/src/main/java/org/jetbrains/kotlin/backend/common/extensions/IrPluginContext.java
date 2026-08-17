package org.jetbrains.kotlin.backend.common.extensions;

import java.util.Collection;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.ir.IrDiagnosticReporter;
import org.jetbrains.kotlin.ir.ObsoleteDescriptorBasedAPI;
import org.jetbrains.kotlin.ir.builders.IrGeneratorContext;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationWithName;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrConstructorSymbol;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;
import org.jetbrains.kotlin.ir.util.ReferenceSymbolTable;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.platform.TargetPlatform;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000¤\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0016\u001a\u00020\u0017H&J\u0010\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001aH&J,\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH'b\u0018\b\u001f\u0012\n\b \u0012\u0006\b\n0!8\"\u0012\b\b#\u0012\u0004\b\b($J,\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010\u001d\u001a\u00020\u001eH'b\u0018\b\u001f\u0012\n\b \u0012\u0006\b\n0!8\"\u0012\b\b#\u0012\u0004\b\b($J0\u0010'\u001a\b\u0012\u0004\u0012\u00020)0(2\u0006\u0010\u001d\u001a\u00020\u001eH'b\u0018\b\u001f\u0012\n\b \u0012\u0006\b\n0!8\"\u0012\b\b#\u0012\u0004\b\b($J0\u0010*\u001a\b\u0012\u0004\u0012\u00020+0(2\u0006\u0010,\u001a\u00020-H'b\u0018\b\u001f\u0012\n\b \u0012\u0006\b\n0!8\"\u0012\b\b#\u0012\u0004\b\b($J0\u0010.\u001a\b\u0012\u0004\u0012\u00020/0(2\u0006\u0010,\u001a\u00020-H'b\u0018\b\u001f\u0012\n\b \u0012\u0006\b\n0!8\"\u0012\b\b#\u0012\u0004\b\b($J\u0018\u00100\u001a\u0002012\u0006\u00102\u001a\u0002032\u0006\u0010\u0019\u001a\u00020\u001aH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u0004\u0018\u00010\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0012\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R4\u00104\u001a\u0002058&X§\u0004r\u0018\b\u001f\u0012\b\b#\u0012\u0004\b\b(:\u0012\n\b \u0012\u0006\b\n0!8\"¢\u0006\f\u0012\u0004\b6\u00107\u001a\u0004\b8\u00109R\u001e\u0010;\u001a\u00020<8&X§\u0004r\u0002\b@¢\u0006\f\u0012\u0004\b=\u00107\u001a\u0004\b>\u0010?R\u001e\u0010A\u001a\u00020B8&X§\u0004r\u0002\b@¢\u0006\f\u0012\u0004\bC\u00107\u001a\u0004\bD\u0010Eø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006FÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;", "Lorg/jetbrains/kotlin/ir/builders/IrGeneratorContext;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "getLanguageVersionSettings", "()Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "afterK2", "", "getAfterK2", "()Z", "platform", "Lorg/jetbrains/kotlin/platform/TargetPlatform;", "getPlatform", "()Lorg/jetbrains/kotlin/platform/TargetPlatform;", "diagnosticReporter", "Lorg/jetbrains/kotlin/ir/IrDiagnosticReporter;", "getDiagnosticReporter", "()Lorg/jetbrains/kotlin/ir/IrDiagnosticReporter;", "metadataDeclarationRegistrar", "Lorg/jetbrains/kotlin/backend/common/extensions/IrGeneratedDeclarationsRegistrar;", "getMetadataDeclarationRegistrar", "()Lorg/jetbrains/kotlin/backend/common/extensions/IrGeneratedDeclarationsRegistrar;", "finderForBuiltins", "Lorg/jetbrains/kotlin/backend/common/extensions/DeclarationFinder;", "finderForSource", "fromFile", "Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "referenceClass", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "Lkotlin/Deprecated;", "level", "Lkotlin/DeprecationLevel;", "WARNING", "message", "Please use `finderForBuiltins()` or `finderForSource(fromFile)` instead.", "referenceClassifier", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "referenceConstructors", "", "Lorg/jetbrains/kotlin/ir/symbols/IrConstructorSymbol;", "referenceFunctions", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "referenceProperties", "Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;", "recordLookup", "", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationWithName;", "messageCollector", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "getMessageCollector$annotations", "()V", "getMessageCollector", "()Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "Consider using diagnosticReporter instead. See https://youtrack.jetbrains.com/issue/KT-78277 for more details", "symbolTable", "Lorg/jetbrains/kotlin/ir/util/ReferenceSymbolTable;", "getSymbolTable$annotations", "getSymbolTable", "()Lorg/jetbrains/kotlin/ir/util/ReferenceSymbolTable;", "Lorg/jetbrains/kotlin/ir/ObsoleteDescriptorBasedAPI;", "moduleDescriptor", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "getModuleDescriptor$annotations", "getModuleDescriptor", "()Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface IrPluginContext extends IrGeneratorContext {
    @Deprecated(level = DeprecationLevel.WARNING, message = "Consider using diagnosticReporter instead. See https://youtrack.jetbrains.com/issue/KT-78277 for more details")
    static /* synthetic */ void getMessageCollector$annotations() {
    }

    @ObsoleteDescriptorBasedAPI
    static /* synthetic */ void getModuleDescriptor$annotations() {
    }

    @ObsoleteDescriptorBasedAPI
    static /* synthetic */ void getSymbolTable$annotations() {
    }

    DeclarationFinder finderForBuiltins();

    DeclarationFinder finderForSource(IrFile fromFile);

    boolean getAfterK2();

    IrDiagnosticReporter getDiagnosticReporter();

    LanguageVersionSettings getLanguageVersionSettings();

    MessageCollector getMessageCollector();

    IrGeneratedDeclarationsRegistrar getMetadataDeclarationRegistrar();

    ModuleDescriptor getModuleDescriptor();

    TargetPlatform getPlatform();

    ReferenceSymbolTable getSymbolTable();

    void recordLookup(IrDeclarationWithName declaration, IrFile fromFile);

    @Deprecated(level = DeprecationLevel.WARNING, message = "Please use `finderForBuiltins()` or `finderForSource(fromFile)` instead.")
    IrClassSymbol referenceClass(ClassId classId);

    @Deprecated(level = DeprecationLevel.WARNING, message = "Please use `finderForBuiltins()` or `finderForSource(fromFile)` instead.")
    IrSymbol referenceClassifier(ClassId classId);

    @Deprecated(level = DeprecationLevel.WARNING, message = "Please use `finderForBuiltins()` or `finderForSource(fromFile)` instead.")
    Collection<IrConstructorSymbol> referenceConstructors(ClassId classId);

    @Deprecated(level = DeprecationLevel.WARNING, message = "Please use `finderForBuiltins()` or `finderForSource(fromFile)` instead.")
    Collection<IrSimpleFunctionSymbol> referenceFunctions(CallableId callableId);

    @Deprecated(level = DeprecationLevel.WARNING, message = "Please use `finderForBuiltins()` or `finderForSource(fromFile)` instead.")
    Collection<IrPropertySymbol> referenceProperties(CallableId callableId);
}

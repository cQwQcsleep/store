package org.jetbrains.kotlin.fir.backend.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.backend.common.IrSpecialAnnotationsProvider;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.ParameterDescriptor;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.backend.Fir2IrBuiltinSymbolsContainer;
import org.jetbrains.kotlin.fir.backend.Fir2IrClassifierStorage;
import org.jetbrains.kotlin.fir.backend.Fir2IrCommonMemberStorage;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.backend.Fir2IrConfiguration;
import org.jetbrains.kotlin.fir.backend.Fir2IrConverter;
import org.jetbrains.kotlin.fir.backend.Fir2IrDeclarationStorage;
import org.jetbrains.kotlin.fir.backend.Fir2IrExtensions;
import org.jetbrains.kotlin.fir.backend.Fir2IrImplicitCastInserter;
import org.jetbrains.kotlin.fir.backend.Fir2IrIrGeneratedDeclarationsRegistrar;
import org.jetbrains.kotlin.fir.backend.Fir2IrSymbolsMappingForLazyClasses;
import org.jetbrains.kotlin.fir.backend.Fir2IrTypeConverter;
import org.jetbrains.kotlin.fir.backend.Fir2IrVisibilityConverter;
import org.jetbrains.kotlin.fir.backend.FirMetadataSource;
import org.jetbrains.kotlin.fir.backend.FirProviderWithGeneratedFiles;
import org.jetbrains.kotlin.fir.backend.utils.IrElementsCreationUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.ir.IrLock;
import org.jetbrains.kotlin.ir.IrProvider;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrFactory;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrParameterKind;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.declarations.impl.IrFactoryImpl;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.impl.IrValueParameterSymbolImpl;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.util.IdSignature;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.util.KotlinMangler;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;
import org.jetbrains.kotlin.util.OperatorNameConventions;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u008e\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002\u0081\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0005J\u001c\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0005J\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0005J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0015\u001a\u00020\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0012\u0010\u0019\u001a\u00020\u001aX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0012\u0010\u001d\u001a\u00020\u001eX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0012\u0010!\u001a\u00020\"X\u0096\u0005¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0012\u0010%\u001a\u00020&X\u0096\u0005¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0012\u0010)\u001a\u00020*X\u0096\u0005¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0012\u0010-\u001a\u00020.X\u0096\u0005¢\u0006\u0006\u001a\u0004\b/\u00100R\u0012\u00101\u001a\u000202X\u0096\u0005¢\u0006\u0006\u001a\u0004\b3\u00104R\u0012\u00105\u001a\u000206X\u0096\u0005¢\u0006\u0006\u001a\u0004\b7\u00108R\u0012\u00109\u001a\u00020:X\u0096\u0005¢\u0006\u0006\u001a\u0004\b;\u0010<R\u0012\u0010=\u001a\u00020\u0000X\u0096\u0005¢\u0006\u0006\u001a\u0004\b>\u0010?R\u0012\u0010@\u001a\u00020AX\u0096\u0005¢\u0006\u0006\u001a\u0004\bB\u0010CR\u0012\u0010D\u001a\u00020EX\u0096\u0005¢\u0006\u0006\u001a\u0004\bF\u0010GR\u001a\u0010H\u001a\n\u0012\u0004\u0012\u00020J\u0018\u00010IX\u0096\u0005¢\u0006\u0006\u001a\u0004\bK\u0010LR\u0012\u0010M\u001a\u00020NX\u0096\u0005¢\u0006\u0006\u001a\u0004\bO\u0010PR\u0012\u0010Q\u001a\u00020RX\u0096\u0005¢\u0006\u0006\u001a\u0004\bS\u0010TR\u0012\u0010U\u001a\u00020VX\u0096\u0005¢\u0006\u0006\u001a\u0004\bW\u0010XR\u0018\u0010Y\u001a\b\u0012\u0004\u0012\u00020Z0\nX\u0096\u0005¢\u0006\u0006\u001a\u0004\b[\u0010\\R\u0012\u0010]\u001a\u00020^X\u0096\u0005¢\u0006\u0006\u001a\u0004\b_\u0010`R\u0012\u0010a\u001a\u00020bX\u0096\u0005¢\u0006\u0006\u001a\u0004\bc\u0010dR\u0012\u0010e\u001a\u00020fX\u0096\u0005¢\u0006\u0006\u001a\u0004\bg\u0010hR\u0012\u0010i\u001a\u00020jX\u0096\u0005¢\u0006\u0006\u001a\u0004\bk\u0010lR\u0012\u0010m\u001a\u00020nX\u0096\u0005¢\u0006\u0006\u001a\u0004\bo\u0010pR\u0014\u0010q\u001a\u0004\u0018\u00010rX\u0096\u0005¢\u0006\u0006\u001a\u0004\bs\u0010tR\u0012\u0010u\u001a\u00020vX\u0096\u0005¢\u0006\u0006\u001a\u0004\bw\u0010xR\u0012\u0010y\u001a\u00020zX\u0096\u0005¢\u0006\u0006\u001a\u0004\b{\u0010|R\u0013\u0010}\u001a\u00020~X\u0096\u0005¢\u0006\u0007\u001a\u0005\b\u007f\u0010\u0080\u0001¨\u0006\u0082\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "c", "generatedDataValueClassSyntheticFunctionsStorage", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrCommonMemberStorage$DataValueClassGeneratedMembersInfo;", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Ljava/util/Map;)V", "generateSingleFieldValueClassMembers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "irClass", "generateMultiFieldValueClassMembers", "generateDataClassMembers", "registerCopyOrComponentFunction", Argument.Delimiters.none, "irFunction", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "adapterGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "getAdapterGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "annotationGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "getAnnotationGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "annotationsFromPluginRegistrar", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "getAnnotationsFromPluginRegistrar", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "builtins", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "getBuiltins", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "callGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "getCallGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "callablesGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "getCallablesGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "classifierStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "getClassifierStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "classifiersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "getClassifiersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "configuration", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "getConfiguration", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "converter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "getConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "dataClassMembersGenerator", "getDataClassMembersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "declarationStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "getDeclarationStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "extensions", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "getExtensions", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "filesBeingCompiled", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "getFilesBeingCompiled", "()Ljava/util/Set;", "firProvider", "Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "getFirProvider", "()Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "implicitCastInserter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "getImplicitCastInserter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "irMangler", "Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "getIrMangler", "()Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "irProviders", "Lorg/jetbrains/kotlin/ir/IrProvider;", "getIrProviders", "()Ljava/util/List;", "lazyDeclarationsGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "getLazyDeclarationsGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "lazyFakeOverrideGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "getLazyFakeOverrideGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "lock", "Lorg/jetbrains/kotlin/ir/IrLock;", "getLock", "()Lorg/jetbrains/kotlin/ir/IrLock;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "specialAnnotationsProvider", "Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "getSpecialAnnotationsProvider", "()Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "symbolsMappingForLazyClasses", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "getSymbolsMappingForLazyClasses", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "typeConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "getTypeConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "visibilityConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "getVisibilityConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "MyDataClassMethodsGenerator", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrDataClassMembersGenerator implements Fir2IrComponents {
    private final Fir2IrComponents c;
    private final Map<IrClass, Fir2IrCommonMemberStorage.DataValueClassGeneratedMembersInfo> generatedDataValueClassSyntheticFunctionsStorage;

    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015J\u0014\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a0\u0018H\u0002J4\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\"H\u0002J \u0010$\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u00192\u0006\u0010%\u001a\u00020 H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator$MyDataClassMethodsGenerator;", Argument.Delimiters.none, "irClass", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "origin", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;Lorg/jetbrains/kotlin/ir/declarations/IrClass;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;)V", "getIrClass", "()Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "getKlass", "()Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "getOrigin", "()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "generateDispatchReceiverParameter", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "irFunction", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "generateHeaders", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "calculateSyntheticFirFunctions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "createSyntheticIrFunctionFromAny", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", ModuleXmlParser.NAME, "syntheticCounterpart", "returnType", "Lorg/jetbrains/kotlin/ir/types/IrType;", "otherParameterNeeded", Argument.Delimiters.none, "isOperator", "createSyntheticIrParameter", ModuleXmlParser.TYPE, "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public final class MyDataClassMethodsGenerator {
        private final IrClass irClass;
        private final FirRegularClass klass;
        private final IrDeclarationOrigin origin;
        final /* synthetic */ Fir2IrDataClassMembersGenerator this$0;

        public MyDataClassMethodsGenerator(Fir2IrDataClassMembersGenerator fir2IrDataClassMembersGenerator, IrClass irClass, FirRegularClass firRegularClass, IrDeclarationOrigin irDeclarationOrigin) {
            irClass.getClass();
            firRegularClass.getClass();
            irDeclarationOrigin.getClass();
            this.this$0 = fir2IrDataClassMembersGenerator;
            this.irClass = irClass;
            this.klass = firRegularClass;
            this.origin = irDeclarationOrigin;
        }

        private final Map<Name, FirNamedFunction> calculateSyntheticFirFunctions() {
            FirTypeScope firTypeScopeUnsubstitutedScope = ScopeUtilsKt.unsubstitutedScope(this.this$0, this.klass);
            final Map mapCreateMapBuilder = MapsKt.createMapBuilder();
            for (final Name name : CollectionsKt.listOf(new Name[]{OperatorNameConventions.EQUALS, StandardNames.HASHCODE_NAME, OperatorNameConventions.TO_STRING})) {
                firTypeScopeUnsubstitutedScope.processFunctionsByName(name, new Function1() { // from class: org.jetbrains.kotlin.fir.backend.generators.b
                    public final Object invoke(Object obj) {
                        return Fir2IrDataClassMembersGenerator.MyDataClassMethodsGenerator.calculateSyntheticFirFunctions$lambda$0$0(this.b, mapCreateMapBuilder, name, (FirNamedFunctionSymbol) obj);
                    }
                });
            }
            return MapsKt.build(mapCreateMapBuilder);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Type inference failed for: r3v1, types: [org.jetbrains.kotlin.fir.FirElement, org.jetbrains.kotlin.fir.declarations.FirDeclaration] */
        public static final Unit calculateSyntheticFirFunctions$lambda$0$0(MyDataClassMethodsGenerator myDataClassMethodsGenerator, Map map, Name name, FirNamedFunctionSymbol firNamedFunctionSymbol) {
            firNamedFunctionSymbol.getClass();
            if ((firNamedFunctionSymbol.getOrigin() instanceof FirDeclarationOrigin.Synthetic) && Intrinsics.areEqual(ClassMembersKt.containingClassLookupTag(firNamedFunctionSymbol), myDataClassMethodsGenerator.klass.getSymbol().getLookupTag())) {
                if (!map.containsKey(name)) {
                    map.put(name, firNamedFunctionSymbol.getFir());
                    return Unit.INSTANCE;
                }
                StringBuilder sb = new StringBuilder("Two synthetic functions ");
                sb.append(name);
                sb.append(" were found in data/value class ");
                sb.append(myDataClassMethodsGenerator.klass.getName());
                sb.append(":\n");
                FirNamedFunction firNamedFunction = (FirNamedFunction) map.get(name);
                String strRender = firNamedFunction != null ? UtilsKt.render(firNamedFunction) : null;
                String strRender2 = UtilsKt.render(firNamedFunctionSymbol.getFir());
                sb.append(strRender);
                sb.append('\n');
                sb.append(strRender2);
                throw new IllegalArgumentException(sb.toString().toString());
            }
            return Unit.INSTANCE;
        }

        private final IrSimpleFunction createSyntheticIrFunctionFromAny(Name name, FirNamedFunction syntheticCounterpart, IrType returnType, boolean otherParameterNeeded, boolean isOperator) {
            IrSimpleFunctionSymbol irSimpleFunctionSymbolCreateFunctionSymbol$org_jetbrains_kotlin_fir2ir = this.this$0.c.getDeclarationStorage().createFunctionSymbol$org_jetbrains_kotlin_fir2ir();
            IrFactoryImpl irFactoryImpl = IrFactoryImpl.INSTANCE;
            IrDeclarationOrigin irDeclarationOrigin = this.origin;
            DescriptorVisibility descriptorVisibility = DescriptorVisibilities.PUBLIC;
            descriptorVisibility.getClass();
            IrSimpleFunction irSimpleFunctionCreateSimpleFunction$default = IrFactory.createSimpleFunction$default(irFactoryImpl, -1, -1, irDeclarationOrigin, name, descriptorVisibility, false, false, returnType, Modality.OPEN, irSimpleFunctionSymbolCreateFunctionSymbol$org_jetbrains_kotlin_fir2ir, false, false, isOperator, false, false, (DeserializedContainerSource) null, false, 32768, (Object) null);
            Fir2IrDataClassMembersGenerator fir2IrDataClassMembersGenerator = this.this$0;
            irSimpleFunctionCreateSimpleFunction$default.setMetadata(new FirMetadataSource.Function(syntheticCounterpart));
            Fir2IrCallableDeclarationsGeneratorKt.setParent(irSimpleFunctionCreateSimpleFunction$default, this.irClass);
            Fir2IrCallableDeclarationsGeneratorKt.addDeclarationToParent(irSimpleFunctionCreateSimpleFunction$default, this.irClass);
            List listCreateListBuilder = CollectionsKt.createListBuilder();
            List list = listCreateListBuilder;
            list.add(generateDispatchReceiverParameter(irSimpleFunctionCreateSimpleFunction$default));
            if (otherParameterNeeded) {
                list.add(createSyntheticIrParameter(irSimpleFunctionCreateSimpleFunction$default, ((FirValueParameter) CollectionsKt.first(syntheticCounterpart.getValueParameters())).getName(), fir2IrDataClassMembersGenerator.c.getBuiltins().getAnyNType()));
            }
            irSimpleFunctionCreateSimpleFunction$default.setParameters(CollectionsKt.build(listCreateListBuilder));
            return irSimpleFunctionCreateSimpleFunction$default;
        }

        public static /* synthetic */ IrSimpleFunction createSyntheticIrFunctionFromAny$default(MyDataClassMethodsGenerator myDataClassMethodsGenerator, Name name, FirNamedFunction firNamedFunction, IrType irType, boolean z, boolean z2, int i, Object obj) {
            if ((i & 8) != 0) {
                z = false;
            }
            if ((i & 16) != 0) {
                z2 = false;
            }
            return myDataClassMethodsGenerator.createSyntheticIrFunctionFromAny(name, firNamedFunction, irType, z, z2);
        }

        private final IrValueParameter createSyntheticIrParameter(IrFunction irFunction, Name name, IrType type) {
            IrValueParameter irValueParameterCreateValueParameter = IrFactoryImpl.INSTANCE.createValueParameter(-1, -1, IrDeclarationOrigin.Companion.getDEFINED(), IrParameterKind.Regular, name, type, false, new IrValueParameterSymbolImpl((ParameterDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null), (IrType) null, false, false, false);
            irValueParameterCreateValueParameter.setParent(irFunction);
            return irValueParameterCreateValueParameter;
        }

        public final IrValueParameter generateDispatchReceiverParameter(IrFunction irFunction) {
            irFunction.getClass();
            return IrElementsCreationUtilsKt.declareThisReceiverParameter$default(this.this$0, irFunction, IrUtilsKt.getDefaultType(this.irClass), IrDeclarationOrigin.Companion.getDEFINED(), IrParameterKind.DispatchReceiver, -1, -1, null, null, false, 448, null);
        }

        public final List<FirDeclaration> generateHeaders() {
            MyDataClassMethodsGenerator myDataClassMethodsGenerator;
            ArrayList arrayList = new ArrayList();
            Map<Name, FirNamedFunction> mapCalculateSyntheticFirFunctions = calculateSyntheticFirFunctions();
            ArrayList arrayList2 = new ArrayList();
            Name name = OperatorNameConventions.TO_STRING;
            FirNamedFunction firNamedFunction = mapCalculateSyntheticFirFunctions.get(name);
            if (firNamedFunction != null) {
                arrayList.add(firNamedFunction);
                IrSimpleFunction irSimpleFunctionCreateSyntheticIrFunctionFromAny$default = createSyntheticIrFunctionFromAny$default(this, name, firNamedFunction, this.this$0.c.getBuiltins().getStringType(), false, false, 24, null);
                myDataClassMethodsGenerator = this;
                myDataClassMethodsGenerator.this$0.getDeclarationStorage().cacheGeneratedFunction$org_jetbrains_kotlin_fir2ir(firNamedFunction, irSimpleFunctionCreateSyntheticIrFunctionFromAny$default);
                arrayList2.add(irSimpleFunctionCreateSyntheticIrFunctionFromAny$default);
            } else {
                myDataClassMethodsGenerator = this;
            }
            Name name2 = StandardNames.HASHCODE_NAME;
            FirNamedFunction firNamedFunction2 = mapCalculateSyntheticFirFunctions.get(name2);
            if (firNamedFunction2 != null) {
                arrayList.add(firNamedFunction2);
                IrSimpleFunction irSimpleFunctionCreateSyntheticIrFunctionFromAny$default2 = createSyntheticIrFunctionFromAny$default(myDataClassMethodsGenerator, name2, firNamedFunction2, myDataClassMethodsGenerator.this$0.c.getBuiltins().getIntType(), false, false, 24, null);
                myDataClassMethodsGenerator.this$0.getDeclarationStorage().cacheGeneratedFunction$org_jetbrains_kotlin_fir2ir(firNamedFunction2, irSimpleFunctionCreateSyntheticIrFunctionFromAny$default2);
                arrayList2.add(irSimpleFunctionCreateSyntheticIrFunctionFromAny$default2);
            }
            Name name3 = OperatorNameConventions.EQUALS;
            FirNamedFunction firNamedFunction3 = mapCalculateSyntheticFirFunctions.get(name3);
            if (firNamedFunction3 != null) {
                arrayList.add(firNamedFunction3);
                IrSimpleFunction irSimpleFunctionCreateSyntheticIrFunctionFromAny = myDataClassMethodsGenerator.createSyntheticIrFunctionFromAny(name3, firNamedFunction3, myDataClassMethodsGenerator.this$0.c.getBuiltins().getBooleanType(), true, true);
                myDataClassMethodsGenerator.this$0.getDeclarationStorage().cacheGeneratedFunction$org_jetbrains_kotlin_fir2ir(firNamedFunction3, irSimpleFunctionCreateSyntheticIrFunctionFromAny);
                arrayList2.add(irSimpleFunctionCreateSyntheticIrFunctionFromAny);
            }
            myDataClassMethodsGenerator.this$0.generatedDataValueClassSyntheticFunctionsStorage.put(myDataClassMethodsGenerator.irClass, new Fir2IrCommonMemberStorage.DataValueClassGeneratedMembersInfo(myDataClassMethodsGenerator.this$0.c, myDataClassMethodsGenerator.klass, myDataClassMethodsGenerator.origin, arrayList2));
            return arrayList;
        }

        public final IrClass getIrClass() {
            return this.irClass;
        }

        public final FirRegularClass getKlass() {
            return this.klass;
        }

        public final IrDeclarationOrigin getOrigin() {
            return this.origin;
        }
    }

    public Fir2IrDataClassMembersGenerator(Fir2IrComponents fir2IrComponents, Map<IrClass, Fir2IrCommonMemberStorage.DataValueClassGeneratedMembersInfo> map) {
        fir2IrComponents.getClass();
        map.getClass();
        this.c = fir2IrComponents;
        this.generatedDataValueClassSyntheticFunctionsStorage = map;
    }

    public final List<FirDeclaration> generateDataClassMembers(FirRegularClass klass, IrClass irClass) {
        klass.getClass();
        irClass.getClass();
        return new MyDataClassMethodsGenerator(this, irClass, klass, IrDeclarationOrigin.Companion.getGENERATED_DATA_CLASS_MEMBER()).generateHeaders();
    }

    public final List<FirDeclaration> generateMultiFieldValueClassMembers(FirRegularClass klass, IrClass irClass) {
        klass.getClass();
        irClass.getClass();
        return new MyDataClassMethodsGenerator(this, irClass, klass, IrDeclarationOrigin.Companion.getGENERATED_MULTI_FIELD_VALUE_CLASS_MEMBER()).generateHeaders();
    }

    public final List<FirDeclaration> generateSingleFieldValueClassMembers(FirRegularClass klass, IrClass irClass) {
        klass.getClass();
        irClass.getClass();
        return new MyDataClassMethodsGenerator(this, irClass, klass, IrDeclarationOrigin.Companion.getGENERATED_SINGLE_FIELD_VALUE_CLASS_MEMBER()).generateHeaders();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public AdapterGenerator getAdapterGenerator() {
        return this.c.getAdapterGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public AnnotationGenerator getAnnotationGenerator() {
        return this.c.getAnnotationGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrIrGeneratedDeclarationsRegistrar getAnnotationsFromPluginRegistrar() {
        return this.c.getAnnotationsFromPluginRegistrar();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrBuiltinSymbolsContainer getBuiltins() {
        return this.c.getBuiltins();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public CallAndReferenceGenerator getCallGenerator() {
        return this.c.getCallGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrCallableDeclarationsGenerator getCallablesGenerator() {
        return this.c.getCallablesGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrClassifierStorage getClassifierStorage() {
        return this.c.getClassifierStorage();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrClassifiersGenerator getClassifiersGenerator() {
        return this.c.getClassifiersGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrConfiguration getConfiguration() {
        return this.c.getConfiguration();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrConverter getConverter() {
        return this.c.getConverter();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrDataClassMembersGenerator getDataClassMembersGenerator() {
        return this.c.getDataClassMembersGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrDeclarationStorage getDeclarationStorage() {
        return this.c.getDeclarationStorage();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrExtensions getExtensions() {
        return this.c.getExtensions();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Set<FirFile> getFilesBeingCompiled() {
        return this.c.getFilesBeingCompiled();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public FirProviderWithGeneratedFiles getFirProvider() {
        return this.c.getFirProvider();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrImplicitCastInserter getImplicitCastInserter() {
        return this.c.getImplicitCastInserter();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public KotlinMangler.IrMangler getIrMangler() {
        return this.c.getIrMangler();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public List<IrProvider> getIrProviders() {
        return this.c.getIrProviders();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrLazyDeclarationsGenerator getLazyDeclarationsGenerator() {
        return this.c.getLazyDeclarationsGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrLazyFakeOverrideGenerator getLazyFakeOverrideGenerator() {
        return this.c.getLazyFakeOverrideGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public IrLock getLock() {
        return this.c.getLock();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents, org.jetbrains.kotlin.fir.ScopeSessionHolder
    public ScopeSession getScopeSession() {
        return this.c.getScopeSession();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents, org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.c.getSession();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public IrSpecialAnnotationsProvider getSpecialAnnotationsProvider() {
        return this.c.getSpecialAnnotationsProvider();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrSymbolsMappingForLazyClasses getSymbolsMappingForLazyClasses() {
        return this.c.getSymbolsMappingForLazyClasses();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrTypeConverter getTypeConverter() {
        return this.c.getTypeConverter();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrVisibilityConverter getVisibilityConverter() {
        return this.c.getVisibilityConverter();
    }

    public final void registerCopyOrComponentFunction(IrSimpleFunction irFunction) {
        irFunction.getClass();
        ((Fir2IrCommonMemberStorage.DataValueClassGeneratedMembersInfo) MapsKt.getValue(this.generatedDataValueClassSyntheticFunctionsStorage, IrUtilsKt.getParentAsClass(irFunction))).getGeneratedFunctions().add(irFunction);
    }
}

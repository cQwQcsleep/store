package org.jetbrains.kotlin.fir.backend;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import defpackage.f2f;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.backend.common.IrSpecialAnnotationsProvider;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassConstructorDescriptor;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
import org.jetbrains.kotlin.descriptors.ScriptDescriptor;
import org.jetbrains.kotlin.descriptors.SourceFile;
import org.jetbrains.kotlin.descriptors.VariableDescriptorWithAccessors;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fileClasses.JvmFileClassInfo;
import org.jetbrains.kotlin.fileClasses.JvmFileClassUtil;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.DelegatedWrapperData;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirVisibilityCheckerKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.backend.Fir2IrDeclarationStorage;
import org.jetbrains.kotlin.fir.backend.generators.AdapterGenerator;
import org.jetbrains.kotlin.fir.backend.generators.AnnotationGenerator;
import org.jetbrains.kotlin.fir.backend.generators.CallAndReferenceGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrCallableDeclarationsGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrCallableDeclarationsGeneratorKt;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrClassifiersGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrDataClassMembersGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrLazyDeclarationsGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrLazyFakeOverrideGenerator;
import org.jetbrains.kotlin.fir.backend.utils.ConversionTypeOrigin;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticProperty;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.descriptors.FirBuiltInsPackageFragment;
import org.jetbrains.kotlin.fir.descriptors.FirModuleDescriptor;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.lazy.Fir2IrLazyClass;
import org.jetbrains.kotlin.fir.lazy.Fir2IrLazyConstructor;
import org.jetbrains.kotlin.fir.lazy.Fir2IrLazyProperty;
import org.jetbrains.kotlin.fir.lazy.Fir2IrLazySimpleFunction;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ResolveUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.FirSimpleSyntheticPropertySymbol;
import org.jetbrains.kotlin.fir.resolve.providers.FirProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirProviderKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.ImportedFromObjectOrStaticData;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirBackingFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirDelegateFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirSyntheticPropertyAccessorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.ir.IrLock;
import org.jetbrains.kotlin.ir.IrProvider;
import org.jetbrains.kotlin.ir.declarations.IrAnonymousInitializer;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrConstructor;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent;
import org.jetbrains.kotlin.ir.declarations.IrExternalPackageFragment;
import org.jetbrains.kotlin.ir.declarations.IrFactory;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrLocalDelegatedProperty;
import org.jetbrains.kotlin.ir.declarations.IrParameterKind;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.ir.declarations.IrReplSnippet;
import org.jetbrains.kotlin.ir.declarations.IrScript;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.declarations.IrVariable;
import org.jetbrains.kotlin.ir.declarations.impl.IrFactoryImpl;
import org.jetbrains.kotlin.ir.expressions.IrSyntheticBodyKind;
import org.jetbrains.kotlin.ir.symbols.IrAnonymousInitializerSymbol;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrConstructorSymbol;
import org.jetbrains.kotlin.ir.symbols.IrEnumEntrySymbol;
import org.jetbrains.kotlin.ir.symbols.IrFieldSymbol;
import org.jetbrains.kotlin.ir.symbols.IrFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.IrLocalDelegatedPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrReplSnippetSymbol;
import org.jetbrains.kotlin.ir.symbols.IrScriptSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;
import org.jetbrains.kotlin.ir.symbols.IrValueParameterSymbol;
import org.jetbrains.kotlin.ir.symbols.IrVariableSymbol;
import org.jetbrains.kotlin.ir.symbols.impl.IrClassSymbolImpl;
import org.jetbrains.kotlin.ir.symbols.impl.IrConstructorSymbolImpl;
import org.jetbrains.kotlin.ir.symbols.impl.IrFakeOverrideSymbolBase;
import org.jetbrains.kotlin.ir.symbols.impl.IrFieldFakeOverrideSymbol;
import org.jetbrains.kotlin.ir.symbols.impl.IrFieldSymbolImpl;
import org.jetbrains.kotlin.ir.symbols.impl.IrFunctionFakeOverrideSymbol;
import org.jetbrains.kotlin.ir.symbols.impl.IrLocalDelegatedPropertySymbolImpl;
import org.jetbrains.kotlin.ir.symbols.impl.IrPropertyFakeOverrideSymbol;
import org.jetbrains.kotlin.ir.symbols.impl.IrPropertySymbolImpl;
import org.jetbrains.kotlin.ir.symbols.impl.IrReplSnippetSymbolImpl;
import org.jetbrains.kotlin.ir.symbols.impl.IrScriptSymbolImpl;
import org.jetbrains.kotlin.ir.symbols.impl.IrSimpleFunctionSymbolImpl;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.util.DumpIrTreeOptions;
import org.jetbrains.kotlin.ir.util.IdSignature;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.util.KotlinMangler;
import org.jetbrains.kotlin.ir.util.RenderIrElementKt;
import org.jetbrains.kotlin.load.kotlin.FacadeClassSource;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.psi.KtFile;
import org.jetbrains.kotlin.resolve.jvm.JvmClassName;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerAbiStability;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.PreReleaseInfo;
import org.jetbrains.kotlin.util.OperatorNameConventions;
import org.jetbrains.kotlin.utils.ThreadLocalKt;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Æ\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 ó\u00022\u00020\u0001:\u000eí\u0002î\u0002ï\u0002ð\u0002ñ\u0002ò\u0002ó\u0002B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ \u00100\u001a\u0002012\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020103H\u0007b\u0002\b4J1\u00105\u001a\u000201\"\b\b\u0000\u00106*\u00020'*\b\u0012\u0004\u0012\u0002H6072\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u0002H6\u0012\u0004\u0012\u00020103H\u0082\bJ\u000e\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u000eJ \u0010L\u001a\u00020M2\u0006\u0010N\u001a\u00020\u000b2\u0006\u0010K\u001a\u00020\u000e2\b\b\u0002\u0010O\u001a\u00020PJ(\u0010Q\u001a\u00020M2\u0006\u0010N\u001a\u00020\u000b2\u0006\u0010K\u001a\u00020\u000e2\u0006\u0010O\u001a\u00020P2\u0006\u0010R\u001a\u00020SH\u0002J\u0016\u0010T\u001a\u0002012\u0006\u0010U\u001a\u00020\u00102\u0006\u0010V\u001a\u00020\u0011J\u000e\u0010W\u001a\u00020\u00112\u0006\u0010U\u001a\u00020\u0010J\u001c\u0010X\u001a\u0004\u0018\u00010\u001a2\u0006\u0010Y\u001a\u00020\u00192\n\b\u0002\u0010Z\u001a\u0004\u0018\u00010[J\u001c\u0010X\u001a\u0004\u0018\u00010\u001a2\u0006\u0010Y\u001a\u00020\\2\n\b\u0002\u0010Z\u001a\u0004\u0018\u00010[JD\u0010]\u001a\u00020^2\u0006\u0010Y\u001a\u00020\u00192\b\u0010_\u001a\u0004\u0018\u00010`2\n\b\u0002\u0010a\u001a\u0004\u0018\u00010b2\b\b\u0002\u0010c\u001a\u00020S2\n\b\u0002\u0010Z\u001a\u0004\u0018\u00010[2\b\b\u0002\u0010d\u001a\u00020SJ\r\u0010e\u001a\u00020\u001aH\u0000¢\u0006\u0002\bfJ$\u0010g\u001a\u00020\u001a2\u0006\u0010Y\u001a\u00020\u00192\n\b\u0002\u0010Z\u001a\u0004\u0018\u00010[2\u0006\u0010h\u001a\u00020SH\u0002J\u001a\u0010i\u001a\u00020@2\u0006\u0010j\u001a\u00020k2\b\u0010Z\u001a\u0004\u0018\u00010[H\u0002J\"\u0010l\u001a\u0002012\u0006\u0010Y\u001a\u00020\u00192\u0006\u0010m\u001a\u00020\u001a2\b\u0010Z\u001a\u0004\u0018\u00010[H\u0002J!\u0010n\u001a\u0002Ho\"\b\b\u0000\u0010o*\u00020p*\u0002Ho2\u0006\u0010Y\u001a\u00020\u0019¢\u0006\u0002\u0010qJ\u001d\u0010r\u001a\u0002012\u0006\u0010s\u001a\u00020\\2\u0006\u0010t\u001a\u00020^H\u0000¢\u0006\u0002\buJ\u0010\u0010v\u001a\u0004\u0018\u00010 2\u0006\u0010w\u001a\u00020\u001fJ2\u0010x\u001a\u00020y2\u0006\u0010w\u001a\u00020\u001f2\f\u0010_\u001a\b\u0012\u0004\u0012\u00020{0z2\n\b\u0002\u0010a\u001a\u0004\u0018\u00010b2\b\b\u0002\u0010c\u001a\u00020SJ\u0018\u0010|\u001a\u0002012\u0006\u0010w\u001a\u00020\u001f2\u0006\u0010}\u001a\u00020 H\u0002J\u001a\u0010~\u001a\u00020 2\u0007\u0010\u007f\u001a\u00030\u0080\u00012\t\b\u0002\u0010\u0081\u0001\u001a\u00020SJ\u0014\u0010\u0085\u0001\u001a\u00030\u0086\u00012\b\u0010\u0087\u0001\u001a\u00030\u0086\u0001H\u0002J>\u0010\u0088\u0001\u001a\u00030\u0089\u00012\b\u0010\u0087\u0001\u001a\u00030\u0086\u00012\b\u0010_\u001a\u0004\u0018\u00010`2\n\b\u0002\u0010a\u001a\u0004\u0018\u00010b2\n\b\u0002\u0010Z\u001a\u0004\u0018\u00010[2\b\b\u0002\u0010d\u001a\u00020SJ&\u0010\u008a\u0001\u001a\u00030\u008b\u00012\b\u0010\u0087\u0001\u001a\u00030\u0086\u00012\b\u0010Z\u001a\u0004\u0018\u00010[2\u0006\u0010h\u001a\u00020SH\u0002J\u001e\u0010\u008c\u0001\u001a\u00030\u008b\u00012\b\u0010\u0087\u0001\u001a\u00030\u0086\u00012\b\u0010Z\u001a\u0004\u0018\u00010[H\u0002J'\u0010\u008d\u0001\u001a\u0002012\b\u0010\u0087\u0001\u001a\u00030\u0086\u00012\b\u0010\u008e\u0001\u001a\u00030\u008b\u00012\b\u0010Z\u001a\u0004\u0018\u00010[H\u0002J\u001d\u0010\u008f\u0001\u001a\u00020'2\b\u0010\u0090\u0001\u001a\u00030\u0091\u00012\n\b\u0002\u0010Z\u001a\u0004\u0018\u00010[J \u0010\u0092\u0001\u001a\u00030\u008b\u00012\b\u0010\u0090\u0001\u001a\u00030\u0091\u00012\n\b\u0002\u0010Z\u001a\u0004\u0018\u00010[H\u0002J\u001e\u0010\u0093\u0001\u001a\u00030\u008b\u00012\b\u0010\u0087\u0001\u001a\u00030\u0086\u00012\b\u0010Z\u001a\u0004\u0018\u00010[H\u0002J\u001d\u0010\u0094\u0001\u001a\u0004\u0018\u00010*2\b\u0010\u0087\u0001\u001a\u00030\u0086\u00012\b\u0010Z\u001a\u0004\u0018\u00010[J \u0010\u0095\u0001\u001a\u0005\u0018\u00010\u008b\u00012\b\u0010\u0087\u0001\u001a\u00030\u0086\u00012\b\u0010Z\u001a\u0004\u0018\u00010[H\u0002J\u0012\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u001a2\u0007\u0010\u0097\u0001\u001a\u00020*J\u0012\u0010\u0098\u0001\u001a\u0004\u0018\u00010\u001a2\u0007\u0010\u0097\u0001\u001a\u00020*J\u0012\u0010\u0099\u0001\u001a\u0004\u0018\u00010+2\u0007\u0010\u0097\u0001\u001a\u00020*J\u001b\u0010\u009a\u0001\u001a\u00020'2\b\u0010\u009b\u0001\u001a\u00030\u009c\u00012\b\u0010Z\u001a\u0004\u0018\u00010[J\u001c\u0010\u009d\u0001\u001a\u00020+2\u0007\u0010\u009e\u0001\u001a\u00020>2\b\u0010Z\u001a\u0004\u0018\u00010[H\u0002J\u001c\u0010\u009f\u0001\u001a\u00020*2\u0007\u0010\u009e\u0001\u001a\u00020>2\b\u0010Z\u001a\u0004\u0018\u00010[H\u0002J\u001d\u0010 \u0001\u001a\u00030\u008b\u00012\u0007\u0010\u009e\u0001\u001a\u00020>2\b\u0010Z\u001a\u0004\u0018\u00010[H\u0002J/\u0010¡\u0001\u001a\u0002012\u0007\u0010\u009e\u0001\u001a\u00020>2\b\u0010\u008e\u0001\u001a\u00030\u008b\u00012\b\u0010Z\u001a\u0004\u0018\u00010[2\u0007\u0010¢\u0001\u001a\u00020SH\u0002J\u001d\u0010£\u0001\u001a\u00030\u008b\u00012\u0007\u0010\u009e\u0001\u001a\u00020>2\b\u0010Z\u001a\u0004\u0018\u00010[H\u0002J\u001e\u0010¤\u0001\u001a\u0004\u0018\u00010*2\u0007\u0010\u009e\u0001\u001a\u00020>2\b\u0010Z\u001a\u0004\u0018\u00010[H\u0002J\t\u0010¥\u0001\u001a\u00020+H\u0002J\u0011\u0010¦\u0001\u001a\u00020'2\b\u0010§\u0001\u001a\u00030¨\u0001J\u0019\u0010¦\u0001\u001a\u00020'2\b\u0010\u0090\u0001\u001a\u00030\u0091\u0001H\u0000¢\u0006\u0003\b©\u0001J\u0011\u0010ª\u0001\u001a\u00020'2\b\u0010«\u0001\u001a\u00030¬\u0001J\u0013\u0010\u00ad\u0001\u001a\u00020'2\b\u0010®\u0001\u001a\u00030\u0086\u0001H\u0002J\u0012\u0010¯\u0001\u001a\u0004\u0018\u00010+2\u0007\u0010\u009e\u0001\u001a\u00020>J\u0019\u0010°\u0001\u001a\u0002012\u0007\u0010\u009e\u0001\u001a\u00020>2\u0007\u0010±\u0001\u001a\u00020+J,\u0010²\u0001\u001a\u0002012\u0007\u0010³\u0001\u001a\u00020\u001c2\u0007\u0010´\u0001\u001a\u00020{2\b\u0010µ\u0001\u001a\u00030¶\u00012\u0007\u0010±\u0001\u001a\u00020+J\"\u0010·\u0001\u001a\u00030¸\u00012\u0007\u0010\u009e\u0001\u001a\u00020>2\u0007\u0010´\u0001\u001a\u00020{H\u0000¢\u0006\u0003\b¹\u0001J?\u0010º\u0001\u001a\u00030»\u00012\b\u0010¼\u0001\u001a\u00030½\u00012\t\b\u0002\u0010¾\u0001\u001a\u00020S2\n\b\u0002\u0010¿\u0001\u001a\u00030À\u00012\t\b\u0002\u0010Á\u0001\u001a\u00020S2\t\b\u0002\u0010Â\u0001\u001a\u00020SJ\u0010\u0010\u0096\u0001\u001a\u00020\u001a2\u0007\u0010\u0097\u0001\u001a\u00020.J\u0012\u0010\u0098\u0001\u001a\u0004\u0018\u00010\u001a2\u0007\u0010\u0097\u0001\u001a\u00020.J\u0010\u0010Ã\u0001\u001a\u00020/2\u0007\u0010\u0097\u0001\u001a\u00020.J\u001a\u0010Ä\u0001\u001a\u00030Å\u00012\b\u0010\u0087\u0001\u001a\u00030\u0086\u00012\u0006\u0010_\u001a\u00020`J\u0014\u0010Æ\u0001\u001a\u00030Ç\u00012\b\u0010\u0087\u0001\u001a\u00030\u0086\u0001H\u0002J'\u0010È\u0001\u001a\u00030É\u00012\b\u0010Ê\u0001\u001a\u00030Ë\u00012\u0006\u0010_\u001a\u00020`2\u000b\b\u0002\u0010Ì\u0001\u001a\u0004\u0018\u00010bJ\u0015\u0010Í\u0001\u001a\u00020'2\f\u0010Î\u0001\u001a\u0007\u0012\u0002\b\u00030Ï\u0001J\u0013\u0010Ð\u0001\u001a\u00020/2\b\u0010Ñ\u0001\u001a\u00030Ë\u0001H\u0002J\u0019\u0010Ò\u0001\u001a\u00020#2\u0007\u0010Ó\u0001\u001a\u00020\"2\u0007\u0010Ô\u0001\u001a\u00020{J\u0010\u0010Õ\u0001\u001a\u00020#2\u0007\u0010Ó\u0001\u001a\u00020\"J\u0013\u0010Ö\u0001\u001a\u0004\u0018\u00010<2\b\u0010×\u0001\u001a\u00030Ø\u0001J\r\u0010Ù\u0001\u001a\u00020b*\u00020kH\u0002J,\u0010Ú\u0001\u001a\u00030Û\u00012\f\u0010Ü\u0001\u001a\u0007\u0012\u0002\b\u00030Ý\u00012\n\b\u0002\u0010Z\u001a\u0004\u0018\u00010[2\b\b\u0002\u0010c\u001a\u00020SJZ\u0010Þ\u0001\u001a\u0005\u0018\u0001Hß\u0001\"\u000b\b\u0000\u0010à\u0001\u0018\u0001*\u00020k\"\u000b\b\u0001\u0010ß\u0001\u0018\u0001*\u00020'2\b\u0010á\u0001\u001a\u0003Hà\u00012\b\u0010Z\u001a\u0004\u0018\u00010[2\u0017\u0010â\u0001\u001a\u0012\u0012\u0005\u0012\u0003Hà\u0001\u0012\u0007\u0012\u0005\u0018\u0001Hß\u000103H\u0082\b¢\u0006\u0003\u0010ã\u0001JK\u0010ä\u0001\u001a\u0002Ho\"\b\b\u0000\u0010o*\u00020k2\u0007\u0010å\u0001\u001a\u0002Ho2\b\u0010Z\u001a\u0004\u0018\u00010[2\u001c\u0010æ\u0001\u001a\u0017\u0012\u0004\u0012\u0002Ho\u0012\u0004\u0012\u00020[\u0012\u0006\u0012\u0004\u0018\u0001Ho0ç\u0001H\u0082\b¢\u0006\u0003\u0010è\u0001J\u0014\u0010é\u0001\u001a\u000201H\u0001b\u0003\bë\u0001¢\u0006\u0003\bê\u0001J&\u0010é\u0001\u001a\u0002012\u0016\u0010ì\u0001\u001a\u0011\u0012\u0006\b\u0001\u0012\u00020k\u0012\u0004\u0012\u00020'0í\u0001H\u0003b\u0003\bë\u0001J\u0017\u0010î\u0001\u001a\u0002012\f\u0010ï\u0001\u001a\u0007\u0012\u0002\b\u00030ð\u0001H\u0002J\u0012\u0010ñ\u0001\u001a\u0004\u0018\u00010\u00142\u0007\u0010ò\u0001\u001a\u00020\u0013J\u0010\u0010ó\u0001\u001a\u00020\u00142\u0007\u0010ò\u0001\u001a\u00020\u0013J\u0012\u0010ô\u0001\u001a\u0004\u0018\u00010\u00172\u0007\u0010õ\u0001\u001a\u00020\u0016J\u0010\u0010ö\u0001\u001a\u00020\u00172\u0007\u0010õ\u0001\u001a\u00020\u0016J\u0010\u0010÷\u0001\u001a\u0002012\u0007\u0010ø\u0001\u001a\u00020'J\u0010\u0010ù\u0001\u001a\u0002012\u0007\u0010ø\u0001\u001a\u00020'J&\u0010ú\u0001\u001a\u0002012\u0007\u0010ø\u0001\u001a\u00020'2\u000e\b\u0004\u00102\u001a\b\u0012\u0004\u0012\u0002010zH\u0086\bø\u0001\u0000J;\u0010û\u0001\u001a\u0004\u0018\u00010`2\u0007\u0010ü\u0001\u001a\u00020\u000b2\t\u0010ý\u0001\u001a\u0004\u0018\u00010[2\f\u0010þ\u0001\u001a\u0007\u0012\u0002\b\u00030ð\u00012\u0006\u0010O\u001a\u00020PH\u0000¢\u0006\u0003\bÿ\u0001J$\u0010û\u0001\u001a\u0004\u0018\u00010`2\u0007\u0010\u0080\u0002\u001a\u00020k2\b\u0010Z\u001a\u0004\u0018\u00010[H\u0000¢\u0006\u0003\bÿ\u0001R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00040\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020 0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020#0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010&\u001a\u000e\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020\u001a0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010(\u001a\u000e\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020\u001a0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010)\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020+0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010,\u001a\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020*0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010-\u001a\u000e\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020/0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u00108\u001a\u000e\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020'09X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010;\u001a\u000e\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020<0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010=\u001a\u000e\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020+0\nX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010?\u001a\u001a\u0012\u0004\u0012\u00020@\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020@\u0012\u0004\u0012\u00020+0909X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010A\u001a\b\u0012\u0004\u0012\u00020\u001c0BX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010C\u001a\u00020D8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bG\u0010H\u001a\u0004\bE\u0010FR\u001a\u0010I\u001a\u000e\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020*0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0082\u0001\u001a\u0010\u0012\u0005\u0012\u00030\u0083\u0001\u0012\u0005\u0012\u00030\u0084\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0081\u0002\u001a\u00030\u0082\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0083\u0002\u0010\u0084\u0002R\u0016\u0010\u0085\u0002\u001a\u00030\u0086\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0087\u0002\u0010\u0088\u0002R\u0016\u0010\u0089\u0002\u001a\u00030\u008a\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\b\u008b\u0002\u0010\u008c\u0002R\u0016\u0010\u008d\u0002\u001a\u00030\u008e\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\b\u008f\u0002\u0010\u0090\u0002R\u0016\u0010\u0091\u0002\u001a\u00030\u0092\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0093\u0002\u0010\u0094\u0002R\u0016\u0010\u0095\u0002\u001a\u00030\u0096\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0097\u0002\u0010\u0098\u0002R\u0016\u0010\u0099\u0002\u001a\u00030\u009a\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\b\u009b\u0002\u0010\u009c\u0002R\u0016\u0010\u009d\u0002\u001a\u00030\u009e\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\b\u009f\u0002\u0010 \u0002R\u0016\u0010¡\u0002\u001a\u00030¢\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\b£\u0002\u0010¤\u0002R\u0016\u0010¥\u0002\u001a\u00030¦\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\b§\u0002\u0010¨\u0002R\u0016\u0010©\u0002\u001a\u00030ª\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\b«\u0002\u0010¬\u0002R\u0015\u0010\u00ad\u0002\u001a\u00020\u0000X\u0096\u0005¢\u0006\b\u001a\u0006\b®\u0002\u0010¯\u0002R\u0016\u0010°\u0002\u001a\u00030±\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\b²\u0002\u0010³\u0002R\u001e\u0010´\u0002\u001a\u000b\u0012\u0004\u0012\u00020\u0010\u0018\u00010µ\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\b¶\u0002\u0010·\u0002R\u0016\u0010¸\u0002\u001a\u00030¹\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bº\u0002\u0010»\u0002R\u0016\u0010¼\u0002\u001a\u00030½\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\b¾\u0002\u0010¿\u0002R\u0016\u0010À\u0002\u001a\u00030Á\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bÂ\u0002\u0010Ã\u0002R\u001d\u0010Ä\u0002\u001a\n\u0012\u0005\u0012\u00030Æ\u00020Å\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bÇ\u0002\u0010È\u0002R\u0016\u0010É\u0002\u001a\u00030Ê\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bË\u0002\u0010Ì\u0002R\u0016\u0010Í\u0002\u001a\u00030Î\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bÏ\u0002\u0010Ð\u0002R\u0016\u0010Ñ\u0002\u001a\u00030Ò\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bÓ\u0002\u0010Ô\u0002R\u0016\u0010Õ\u0002\u001a\u00030Ö\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\b×\u0002\u0010Ø\u0002R\u0016\u0010Ù\u0002\u001a\u00030Ú\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bÛ\u0002\u0010Ü\u0002R\u0018\u0010Ý\u0002\u001a\u0005\u0018\u00010Þ\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bß\u0002\u0010à\u0002R\u0016\u0010á\u0002\u001a\u00030â\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bã\u0002\u0010ä\u0002R\u0016\u0010å\u0002\u001a\u00030æ\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bç\u0002\u0010è\u0002R\u0016\u0010é\u0002\u001a\u00030ê\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bë\u0002\u0010ì\u0002\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006ô\u0002"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "c", "sourceModuleDescriptor", "Lorg/jetbrains/kotlin/fir/descriptors/FirModuleDescriptor;", "commonMemberStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrCommonMemberStorage;", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/descriptors/FirModuleDescriptor;Lorg/jetbrains/kotlin/fir/backend/Fir2IrCommonMemberStorage;)V", "fragmentCache", "Ljava/util/concurrent/ConcurrentHashMap;", "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage$ExternalPackageFragments;", "moduleDescriptorCache", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "fileCache", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "scriptCache", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "Lorg/jetbrains/kotlin/ir/declarations/IrScript;", "replSnippetCache", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "Lorg/jetbrains/kotlin/ir/declarations/IrReplSnippet;", "functionCache", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "dataClassGeneratedFunctionsCache", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage$DataClassGeneratedFunctionsStorage;", "constructorCache", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "Lorg/jetbrains/kotlin/ir/symbols/IrConstructorSymbol;", "initializerCache", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousInitializer;", "Lorg/jetbrains/kotlin/ir/declarations/IrAnonymousInitializer;", "propertyCache", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage$PropertyCacheStorage;", "getterForPropertyCache", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "setterForPropertyCache", "backingFieldForPropertyCache", "Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;", "Lorg/jetbrains/kotlin/ir/symbols/IrFieldSymbol;", "propertyForBackingFieldCache", "delegateVariableForPropertyCache", "Lorg/jetbrains/kotlin/ir/symbols/IrLocalDelegatedPropertySymbol;", "Lorg/jetbrains/kotlin/ir/symbols/IrVariableSymbol;", "forEachCachedDeclarationSymbol", Argument.Delimiters.none, "block", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/backend/DelicateDeclarationStorageApi;", "forEachSkipFakeOverrides", "S", Argument.Delimiters.none, "irForFirSessionDependantDeclarationMap", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage$FakeOverrideIdentifier;", "delegatedReverseCache", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "fieldForDelegatedSupertypeCache", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "delegatedClassesMap", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "firClassesWithInheritanceByDelegation", Argument.Delimiters.none, "localStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrLocalCallableStorage;", "getLocalStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrLocalCallableStorage;", "localStorage$delegate", "Lkotlin/properties/ReadWriteProperty;", "propertyForFieldCache", "getDependenciesModuleDescriptor", "moduleData", "getIrExternalPackageFragment", "Lorg/jetbrains/kotlin/ir/declarations/IrExternalPackageFragment;", "fqName", "firOrigin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "getIrExternalOrBuiltInsPackageFragment", "allowBuiltins", Argument.Delimiters.none, "registerFile", "firFile", "irFile", "getIrFile", "getCachedIrFunctionSymbol", "function", "fakeOverrideOwnerLookupTag", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "createAndCacheIrFunction", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "irParent", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationParent;", "predefinedOrigin", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "isLocal", "allowLazyDeclarationsCreation", "createFunctionSymbol", "createFunctionSymbol$org_jetbrains_kotlin_fir2ir", "createMemberFunctionSymbol", "parentIsExternal", "findContainingIrClassSymbol", "callable", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "cacheIrFunctionSymbol", "irFunctionSymbol", "putParametersInScope", "T", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "cacheGeneratedFunction", "firFunction", "irFunction", "cacheGeneratedFunction$org_jetbrains_kotlin_fir2ir", "getCachedIrConstructorSymbol", "constructor", "createAndCacheIrConstructor", "Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;", "Lkotlin/Function0;", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "cacheIrConstructorSymbol", "irConstructorSymbol", "getIrConstructorSymbol", "firConstructorSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "potentiallyExternal", "originalForSyntheticProperty", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage$FirSyntheticPropertyKey;", "Lorg/jetbrains/kotlin/fir/declarations/synthetic/FirSyntheticProperty;", "prepareProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "property", "createAndCacheIrProperty", "Lorg/jetbrains/kotlin/ir/declarations/IrProperty;", "createPropertySymbols", "Lorg/jetbrains/kotlin/fir/backend/PropertySymbols;", "createFakeOverridePropertySymbols", "cacheIrPropertySymbols", "symbols", "getIrPropertySymbol", "firPropertySymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "getIrPropertySymbols", "createAndCacheIrPropertySymbols", "getCachedIrPropertySymbol", "getCachedIrPropertySymbols", "findGetterOfProperty", "propertySymbol", "findSetterOfProperty", "findBackingFieldOfProperty", "getIrSymbolForField", "firFieldSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFieldSymbol;", "getIrSymbolForSupertypeDelegateField", "field", "getIrPropertySymbolForJavaField", "createAndCachePropertySymbolsForJavaField", "cacheIrPropertySymbolsForPureField", "isFakeOverride", "createFakeOverridePropertySymbolsForJavaField", "getCachedIrSymbolForJavaField", "createFieldSymbol", "getIrBackingFieldSymbol", "firBackingFieldSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirBackingFieldSymbol;", "getIrBackingFieldSymbol$org_jetbrains_kotlin_fir2ir", "getIrDelegateFieldSymbol", "delegateFieldSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirDelegateFieldSymbol;", "getIrPropertyForwardedSymbol", "fir", "getCachedIrFieldSymbolForSupertypeDelegateField", "recordSupertypeDelegateFieldMappedToBackingField", "irFieldSymbol", "recordSupertypeDelegationInformation", "containingFirClass", "irClass", "superType", "Lorg/jetbrains/kotlin/ir/types/IrType;", "createSupertypeDelegateIrField", "Lorg/jetbrains/kotlin/ir/declarations/IrField;", "createSupertypeDelegateIrField$org_jetbrains_kotlin_fir2ir", "createAndCacheParameter", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "valueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "useStubForDefaultValueStub", "typeOrigin", "Lorg/jetbrains/kotlin/fir/backend/utils/ConversionTypeOrigin;", "skipDefaultParameter", "forcedDefaultValueConversion", "findDelegateVariableOfProperty", "createAndCacheIrLocalDelegatedProperty", "Lorg/jetbrains/kotlin/ir/declarations/IrLocalDelegatedProperty;", "createLocalDelegatedPropertySymbols", "Lorg/jetbrains/kotlin/fir/backend/LocalDelegatedPropertySymbols;", "createAndCacheIrVariable", "Lorg/jetbrains/kotlin/ir/declarations/IrVariable;", "variable", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "givenOrigin", "getIrValueSymbol", "firVariableSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "getIrVariableSymbol", "firVariable", "createIrAnonymousInitializer", "anonymousInitializer", "containingIrClass", "getIrAnonymousInitializer", "originalDeclarationForDelegated", "irDeclaration", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "computeExternalOrigin", "getIrFunctionSymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrFunctionSymbol;", "firFunctionSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "getCachedIrCallableSymbol", "IS", "FC", "declaration", "cacheGetter", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "calculateFirForLazyDeclaration", "originalDeclaration", "createFakeOverrideIfNeeded", "Lkotlin/Function2;", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;Lkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "fillUnboundSymbols", "fillUnboundSymbols$org_jetbrains_kotlin_fir2ir", "Lorg/jetbrains/kotlin/fir/backend/LeakedDeclarationCaches;", "cache", Argument.Delimiters.none, "generateDeclaration", "originalSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "getCachedIrScript", "script", "createIrScript", "getCachedIrReplSnippet", "snippet", "createIrReplSnippet", "enterScope", "symbol", "leaveScope", "withScope", "findIrParent", "packageFqName", "parentLookupTag", "firBasedSymbol", "findIrParent$org_jetbrains_kotlin_fir2ir", "callableDeclaration", "adapterGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "getAdapterGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "annotationGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "getAnnotationGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "annotationsFromPluginRegistrar", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "getAnnotationsFromPluginRegistrar", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "builtins", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "getBuiltins", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "callGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "getCallGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "callablesGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "getCallablesGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "classifierStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "getClassifierStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "classifiersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "getClassifiersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "configuration", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "getConfiguration", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "converter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "getConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "dataClassMembersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "getDataClassMembersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "declarationStorage", "getDeclarationStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "extensions", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "getExtensions", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "filesBeingCompiled", Argument.Delimiters.none, "getFilesBeingCompiled", "()Ljava/util/Set;", "firProvider", "Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "getFirProvider", "()Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "implicitCastInserter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "getImplicitCastInserter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "irMangler", "Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "getIrMangler", "()Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "irProviders", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/IrProvider;", "getIrProviders", "()Ljava/util/List;", "lazyDeclarationsGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "getLazyDeclarationsGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "lazyFakeOverrideGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "getLazyFakeOverrideGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "lock", "Lorg/jetbrains/kotlin/ir/IrLock;", "getLock", "()Lorg/jetbrains/kotlin/ir/IrLock;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "specialAnnotationsProvider", "Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "getSpecialAnnotationsProvider", "()Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "symbolsMappingForLazyClasses", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "getSymbolsMappingForLazyClasses", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "typeConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "getTypeConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "visibilityConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "getVisibilityConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "ExternalPackageFragments", "DataClassGeneratedFunctionsStorage", "PropertyCacheStorage", "FakeOverrideIdentifier", "NonCachedSourceFacadeContainerSource", "FirSyntheticPropertyKey", "Companion", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrDeclarationStorage implements Fir2IrComponents {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(Fir2IrDeclarationStorage.class, "localStorage", "getLocalStorage()Lorg/jetbrains/kotlin/fir/backend/Fir2IrLocalCallableStorage;", 0)};

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Map<Name, IrSyntheticBodyKind> ENUM_SYNTHETIC_NAMES;
    private final ConcurrentHashMap<IrPropertySymbol, IrFieldSymbol> backingFieldForPropertyCache;
    private final Fir2IrComponents c;
    private final ConcurrentHashMap<FirConstructor, IrConstructorSymbol> constructorCache;
    private final ConcurrentHashMap<FirClass, DataClassGeneratedFunctionsStorage> dataClassGeneratedFunctionsCache;
    private final ConcurrentHashMap<IrLocalDelegatedPropertySymbol, IrVariableSymbol> delegateVariableForPropertyCache;
    private final Map<IrClassSymbol, Map<IrClassSymbol, IrFieldSymbol>> delegatedClassesMap;
    private final ConcurrentHashMap<IrSymbol, FirDeclaration> delegatedReverseCache;
    private final ConcurrentHashMap<FirField, IrFieldSymbol> fieldForDelegatedSupertypeCache;
    private final ConcurrentHashMap<FirFile, IrFile> fileCache;
    private final Set<FirClass> firClassesWithInheritanceByDelegation;
    private final ConcurrentHashMap<FqName, ExternalPackageFragments> fragmentCache;
    private final ConcurrentHashMap<FirFunction, IrSimpleFunctionSymbol> functionCache;
    private final ConcurrentHashMap<IrSymbol, IrSimpleFunctionSymbol> getterForPropertyCache;
    private final ConcurrentHashMap<FirAnonymousInitializer, IrAnonymousInitializer> initializerCache;
    private final Map<FakeOverrideIdentifier, IrSymbol> irForFirSessionDependantDeclarationMap;

    /* JADX INFO: renamed from: localStorage$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty localStorage;
    private final ConcurrentHashMap<FirModuleData, FirModuleDescriptor> moduleDescriptorCache;
    private final ConcurrentHashMap<FirSyntheticPropertyKey, FirSyntheticProperty> originalForSyntheticProperty;
    private final PropertyCacheStorage propertyCache;
    private final ConcurrentHashMap<IrFieldSymbol, IrPropertySymbol> propertyForBackingFieldCache;
    private final ConcurrentHashMap<FirField, IrPropertySymbol> propertyForFieldCache;
    private final ConcurrentHashMap<FirReplSnippet, IrReplSnippet> replSnippetCache;
    private final ConcurrentHashMap<FirScript, IrScript> scriptCache;
    private final ConcurrentHashMap<IrSymbol, IrSimpleFunctionSymbol> setterForPropertyCache;
    private final FirModuleDescriptor sourceModuleDescriptor;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R(\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\b\"\u0004\b\u0010\u0010\n¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage$DataClassGeneratedFunctionsStorage;", Argument.Delimiters.none, "<init>", "()V", "value", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "hashCodeSymbol", "getHashCodeSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "setHashCodeSymbol", "(Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;)V", "toStringSymbol", "getToStringSymbol", "setToStringSymbol", "equalsSymbol", "getEqualsSymbol", "setEqualsSymbol", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class DataClassGeneratedFunctionsStorage {
        private IrSimpleFunctionSymbol equalsSymbol;
        private IrSimpleFunctionSymbol hashCodeSymbol;
        private IrSimpleFunctionSymbol toStringSymbol;

        public final IrSimpleFunctionSymbol getEqualsSymbol() {
            return this.equalsSymbol;
        }

        public final IrSimpleFunctionSymbol getHashCodeSymbol() {
            return this.hashCodeSymbol;
        }

        public final IrSimpleFunctionSymbol getToStringSymbol() {
            return this.toStringSymbol;
        }

        public final void setEqualsSymbol(IrSimpleFunctionSymbol irSimpleFunctionSymbol) {
            this.equalsSymbol = irSimpleFunctionSymbol;
        }

        public final void setHashCodeSymbol(IrSimpleFunctionSymbol irSimpleFunctionSymbol) {
            this.hashCodeSymbol = irSimpleFunctionSymbol;
        }

        public final void setToStringSymbol(IrSimpleFunctionSymbol irSimpleFunctionSymbol) {
            this.toStringSymbol = irSimpleFunctionSymbol;
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001B7\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tR\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage$ExternalPackageFragments;", Argument.Delimiters.none, "fragmentsForDependencies", "Ljava/util/concurrent/ConcurrentHashMap;", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "Lorg/jetbrains/kotlin/ir/declarations/IrExternalPackageFragment;", "builtinFragmentsForDependencies", "fragmentForPrecompiledBinaries", "<init>", "(Ljava/util/concurrent/ConcurrentHashMap;Ljava/util/concurrent/ConcurrentHashMap;Lorg/jetbrains/kotlin/ir/declarations/IrExternalPackageFragment;)V", "getFragmentsForDependencies", "()Ljava/util/concurrent/ConcurrentHashMap;", "getBuiltinFragmentsForDependencies", "getFragmentForPrecompiledBinaries", "()Lorg/jetbrains/kotlin/ir/declarations/IrExternalPackageFragment;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ExternalPackageFragments {
        private final ConcurrentHashMap<FirModuleData, IrExternalPackageFragment> builtinFragmentsForDependencies;
        private final IrExternalPackageFragment fragmentForPrecompiledBinaries;
        private final ConcurrentHashMap<FirModuleData, IrExternalPackageFragment> fragmentsForDependencies;

        public ExternalPackageFragments(ConcurrentHashMap<FirModuleData, IrExternalPackageFragment> concurrentHashMap, ConcurrentHashMap<FirModuleData, IrExternalPackageFragment> concurrentHashMap2, IrExternalPackageFragment irExternalPackageFragment) {
            concurrentHashMap.getClass();
            concurrentHashMap2.getClass();
            irExternalPackageFragment.getClass();
            this.fragmentsForDependencies = concurrentHashMap;
            this.builtinFragmentsForDependencies = concurrentHashMap2;
            this.fragmentForPrecompiledBinaries = irExternalPackageFragment;
        }

        public final ConcurrentHashMap<FirModuleData, IrExternalPackageFragment> getBuiltinFragmentsForDependencies() {
            return this.builtinFragmentsForDependencies;
        }

        public final IrExternalPackageFragment getFragmentForPrecompiledBinaries() {
            return this.fragmentForPrecompiledBinaries;
        }

        public final ConcurrentHashMap<FirModuleData, IrExternalPackageFragment> getFragmentsForDependencies() {
            return this.fragmentsForDependencies;
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\u0016B/\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\b\u0010\tJ\u0019\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0005H\u0086\u0002J\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0013\u001a\u00020\u0004H\u0086\u0002R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0018\u0010\r\u001a\u00020\u0007*\u00020\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage$PropertyCacheStorage;", Argument.Delimiters.none, "normal", "Ljava/util/concurrent/ConcurrentHashMap;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;", "synthetic", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage$PropertyCacheStorage$SyntheticPropertyKey;", "<init>", "(Ljava/util/concurrent/ConcurrentHashMap;Ljava/util/concurrent/ConcurrentHashMap;)V", "getNormal", "()Ljava/util/concurrent/ConcurrentHashMap;", "getSynthetic", "cacheKey", "Lorg/jetbrains/kotlin/fir/declarations/synthetic/FirSyntheticProperty;", "getCacheKey", "(Lorg/jetbrains/kotlin/fir/declarations/synthetic/FirSyntheticProperty;)Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage$PropertyCacheStorage$SyntheticPropertyKey;", "set", Argument.Delimiters.none, "fir", "value", "get", "SyntheticPropertyKey", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class PropertyCacheStorage {
        private final ConcurrentHashMap<FirProperty, IrPropertySymbol> normal;
        private final ConcurrentHashMap<SyntheticPropertyKey, IrPropertySymbol> synthetic;

        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage$PropertyCacheStorage$SyntheticPropertyKey;", Argument.Delimiters.none, "originalFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "dispatchReceiverLookupTag", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;)V", "getOriginalFunction", "()Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "getDispatchReceiverLookupTag", "()Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final /* data */ class SyntheticPropertyKey {
            private final ConeClassLikeLookupTag dispatchReceiverLookupTag;
            private final FirNamedFunction originalFunction;

            public SyntheticPropertyKey(FirNamedFunction firNamedFunction, ConeClassLikeLookupTag coneClassLikeLookupTag) {
                firNamedFunction.getClass();
                this.originalFunction = firNamedFunction;
                this.dispatchReceiverLookupTag = coneClassLikeLookupTag;
            }

            public static /* synthetic */ SyntheticPropertyKey copy$default(SyntheticPropertyKey syntheticPropertyKey, FirNamedFunction firNamedFunction, ConeClassLikeLookupTag coneClassLikeLookupTag, int i, Object obj) {
                if ((i & 1) != 0) {
                    firNamedFunction = syntheticPropertyKey.originalFunction;
                }
                if ((i & 2) != 0) {
                    coneClassLikeLookupTag = syntheticPropertyKey.dispatchReceiverLookupTag;
                }
                return syntheticPropertyKey.copy(firNamedFunction, coneClassLikeLookupTag);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final FirNamedFunction getOriginalFunction() {
                return this.originalFunction;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final ConeClassLikeLookupTag getDispatchReceiverLookupTag() {
                return this.dispatchReceiverLookupTag;
            }

            public final SyntheticPropertyKey copy(FirNamedFunction originalFunction, ConeClassLikeLookupTag dispatchReceiverLookupTag) {
                originalFunction.getClass();
                return new SyntheticPropertyKey(originalFunction, dispatchReceiverLookupTag);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof SyntheticPropertyKey)) {
                    return false;
                }
                SyntheticPropertyKey syntheticPropertyKey = (SyntheticPropertyKey) other;
                return Intrinsics.areEqual(this.originalFunction, syntheticPropertyKey.originalFunction) && Intrinsics.areEqual(this.dispatchReceiverLookupTag, syntheticPropertyKey.dispatchReceiverLookupTag);
            }

            public final ConeClassLikeLookupTag getDispatchReceiverLookupTag() {
                return this.dispatchReceiverLookupTag;
            }

            public final FirNamedFunction getOriginalFunction() {
                return this.originalFunction;
            }

            public int hashCode() {
                int iHashCode = this.originalFunction.hashCode() * 31;
                ConeClassLikeLookupTag coneClassLikeLookupTag = this.dispatchReceiverLookupTag;
                return iHashCode + (coneClassLikeLookupTag == null ? 0 : coneClassLikeLookupTag.hashCode());
            }

            public String toString() {
                return "SyntheticPropertyKey(originalFunction=" + this.originalFunction + ", dispatchReceiverLookupTag=" + this.dispatchReceiverLookupTag + ')';
            }
        }

        public PropertyCacheStorage(ConcurrentHashMap<FirProperty, IrPropertySymbol> concurrentHashMap, ConcurrentHashMap<SyntheticPropertyKey, IrPropertySymbol> concurrentHashMap2) {
            concurrentHashMap.getClass();
            concurrentHashMap2.getClass();
            this.normal = concurrentHashMap;
            this.synthetic = concurrentHashMap2;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
        /* JADX WARN: Multi-variable type inference failed */
        private final SyntheticPropertyKey getCacheKey(FirSyntheticProperty firSyntheticProperty) throws KotlinIllegalArgumentExceptionWithAttachments {
            ConeSimpleKotlinType dispatchReceiverType;
            FirSyntheticPropertyAccessorSymbol getterSymbol = firSyntheticProperty.getSymbol().getGetterSymbol();
            getterSymbol.getClass();
            FirNamedFunction firNamedFunction = (FirNamedFunction) getterSymbol.getDelegateFunctionSymbol().getFir();
            ConeClassLikeLookupTag classLikeLookupTagIfAny = null;
            if (!(firSyntheticProperty.getSymbol() instanceof FirSimpleSyntheticPropertySymbol) && (dispatchReceiverType = firSyntheticProperty.getDispatchReceiverType()) != null) {
                classLikeLookupTagIfAny = ConeTypeUtilsKt.getClassLikeLookupTagIfAny(dispatchReceiverType);
            }
            return new SyntheticPropertyKey(firNamedFunction, classLikeLookupTagIfAny);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
        public final IrPropertySymbol get(FirProperty fir) throws KotlinIllegalArgumentExceptionWithAttachments {
            fir.getClass();
            return fir instanceof FirSyntheticProperty ? this.synthetic.get(getCacheKey((FirSyntheticProperty) fir)) : this.normal.get(fir);
        }

        public final ConcurrentHashMap<FirProperty, IrPropertySymbol> getNormal() {
            return this.normal;
        }

        public final ConcurrentHashMap<SyntheticPropertyKey, IrPropertySymbol> getSynthetic() {
            return this.synthetic;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
        public final void set(FirProperty fir, IrPropertySymbol value) throws KotlinIllegalArgumentExceptionWithAttachments {
            fir.getClass();
            value.getClass();
            if (fir instanceof FirSyntheticProperty) {
                this.synthetic.put(getCacheKey((FirSyntheticProperty) fir), value);
            } else {
                this.normal.put(fir, value);
            }
        }
    }

    static {
        Pair pair = TuplesKt.to(Name.identifier("values"), IrSyntheticBodyKind.ENUM_VALUES);
        Pair pair2 = TuplesKt.to(Name.identifier("valueOf"), IrSyntheticBodyKind.ENUM_VALUEOF);
        Name nameIdentifier = Name.identifier("entries");
        IrSyntheticBodyKind irSyntheticBodyKind = IrSyntheticBodyKind.ENUM_ENTRIES;
        ENUM_SYNTHETIC_NAMES = MapsKt.mapOf(new Pair[]{pair, pair2, TuplesKt.to(nameIdentifier, irSyntheticBodyKind), TuplesKt.to(Name.special("<get-entries>"), irSyntheticBodyKind)});
    }

    public Fir2IrDeclarationStorage(Fir2IrComponents fir2IrComponents, FirModuleDescriptor firModuleDescriptor, final Fir2IrCommonMemberStorage fir2IrCommonMemberStorage) {
        fir2IrComponents.getClass();
        firModuleDescriptor.getClass();
        fir2IrCommonMemberStorage.getClass();
        this.c = fir2IrComponents;
        this.sourceModuleDescriptor = firModuleDescriptor;
        this.fragmentCache = new ConcurrentHashMap<>();
        this.moduleDescriptorCache = new ConcurrentHashMap<>();
        this.fileCache = new ConcurrentHashMap<>();
        this.scriptCache = new ConcurrentHashMap<>();
        this.replSnippetCache = new ConcurrentHashMap<>();
        this.functionCache = fir2IrCommonMemberStorage.getFunctionCache();
        this.dataClassGeneratedFunctionsCache = fir2IrCommonMemberStorage.getDataClassGeneratedFunctionsCache();
        this.constructorCache = fir2IrCommonMemberStorage.getConstructorCache();
        this.initializerCache = new ConcurrentHashMap<>();
        this.propertyCache = new PropertyCacheStorage(fir2IrCommonMemberStorage.getPropertyCache(), fir2IrCommonMemberStorage.getSyntheticPropertyCache());
        this.getterForPropertyCache = fir2IrCommonMemberStorage.getGetterForPropertyCache();
        this.setterForPropertyCache = fir2IrCommonMemberStorage.getSetterForPropertyCache();
        this.backingFieldForPropertyCache = fir2IrCommonMemberStorage.getBackingFieldForPropertyCache();
        this.propertyForBackingFieldCache = fir2IrCommonMemberStorage.getPropertyForBackingFieldCache();
        this.delegateVariableForPropertyCache = fir2IrCommonMemberStorage.getDelegateVariableForPropertyCache();
        this.irForFirSessionDependantDeclarationMap = fir2IrCommonMemberStorage.getIrForFirSessionDependantDeclarationMap();
        this.delegatedReverseCache = new ConcurrentHashMap<>();
        this.fieldForDelegatedSupertypeCache = new ConcurrentHashMap<>();
        this.delegatedClassesMap = fir2IrCommonMemberStorage.getDelegatedClassesInfo();
        this.firClassesWithInheritanceByDelegation = fir2IrCommonMemberStorage.getFirClassesWithInheritanceByDelegation();
        this.localStorage = ThreadLocalKt.threadLocal(new Function0() { // from class: gv4
            public final Object invoke() {
                return Fir2IrDeclarationStorage.e(fir2IrCommonMemberStorage);
            }
        });
        this.propertyForFieldCache = new ConcurrentHashMap<>();
        this.originalForSyntheticProperty = new ConcurrentHashMap<>();
    }

    public static Unit b(FirField firField, ConeClassLikeLookupTag coneClassLikeLookupTag, IrDeclarationParent irDeclarationParent, ExceptionAttachmentBuilder exceptionAttachmentBuilder) {
        exceptionAttachmentBuilder.getClass();
        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "field", firField);
        exceptionAttachmentBuilder.withEntry("fakeOverrideOwnerLookupTag", String.valueOf(coneClassLikeLookupTag));
        exceptionAttachmentBuilder.withEntry("irParent", RenderIrElementKt.render$default(irDeclarationParent, (DumpIrTreeOptions) null, 1, (Object) null));
        return Unit.INSTANCE;
    }

    public static DataClassGeneratedFunctionsStorage c(Function1 function1, Object obj) {
        return (DataClassGeneratedFunctionsStorage) function1.invoke(obj);
    }

    private final void cacheIrConstructorSymbol(FirConstructor constructor, IrConstructorSymbol irConstructorSymbol) {
        this.constructorCache.put(constructor, irConstructorSymbol);
    }

    private final void cacheIrFunctionSymbol(FirFunction function, IrSimpleFunctionSymbol irFunctionSymbol, ConeClassLikeLookupTag fakeOverrideOwnerLookupTag) {
        if (Intrinsics.areEqual(function.getStatus().getVisibility(), Visibilities.Local.INSTANCE) || (function instanceof FirAnonymousFunction)) {
            getLocalStorage().putLocalFunction(function, irFunctionSymbol);
            return;
        }
        if (Fir2IrDeclarationStorageKt.isFakeOverrideOrDelegated(function, fakeOverrideOwnerLookupTag)) {
            FirCallableDeclaration firCallableDeclaration = function;
            while (true) {
                FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                if (originalForSubstitutionOverrideAttr == null) {
                    originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
                    if (originalForSubstitutionOverrideAttr == null) {
                        DelegatedWrapperData delegatedWrapperData = ClassMembersKt.getDelegatedWrapperData(firCallableDeclaration);
                        originalForSubstitutionOverrideAttr = delegatedWrapperData != null ? delegatedWrapperData.getWrapped() : null;
                    }
                }
                if (originalForSubstitutionOverrideAttr == null) {
                    break;
                } else {
                    firCallableDeclaration = originalForSubstitutionOverrideAttr;
                }
            }
            FakeOverrideIdentifier.Companion companion = FakeOverrideIdentifier.INSTANCE;
            FirFunctionSymbol<FirFunction> symbol = ((FirFunction) firCallableDeclaration).getSymbol();
            if (fakeOverrideOwnerLookupTag == null) {
                fakeOverrideOwnerLookupTag = ClassMembersKt.containingClassLookupTag(function);
                fakeOverrideOwnerLookupTag.getClass();
            }
            this.irForFirSessionDependantDeclarationMap.put(companion.invoke(this, symbol, fakeOverrideOwnerLookupTag), irFunctionSymbol);
            return;
        }
        if (!function.getOrigin().getGeneratedAnyMethod()) {
            this.functionCache.put(function, irFunctionSymbol);
            return;
        }
        Name nameOrSpecialName = FirDeclarationUtilKt.getNameOrSpecialName(function);
        if (!OperatorNameConventions.INSTANCE.isComponentN(nameOrSpecialName) && !Intrinsics.areEqual(nameOrSpecialName, StandardNames.DATA_CLASS_COPY) && !getConfiguration().getAllowNonCachedDeclarations()) {
            Fir2IrLazyClass parent = irFunctionSymbol.getOwner().getParent();
            Fir2IrLazyClass fir2IrLazyClass = parent instanceof Fir2IrLazyClass ? parent : null;
            if (!Intrinsics.areEqual(fir2IrLazyClass != null ? fir2IrLazyClass.getOrigin() : null, IrDeclarationOrigin.Companion.getREPL_FROM_OTHER_SNIPPET())) {
                StringBuilder sb = new StringBuilder();
                sb.append("Only componentN functions should be cached this way, but got: " + UtilsKt.render(function));
                sb.append('\n');
                sb.append("fakeOverrideOwnerLookupTag: " + fakeOverrideOwnerLookupTag);
                sb.append('\n');
                sb.append("Dispatch receiver: " + function.getDispatchReceiverType());
                sb.append('\n');
                throw new IllegalArgumentException(sb.toString().toString());
            }
        }
        this.functionCache.put(function, irFunctionSymbol);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:30:0x0062 A[PHI: r1
      0x0062: PHI (r1v5 org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration) = 
      (r1v4 org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration)
      (r1v9 org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration)
     binds: [B:21:0x0047, B:26:0x0055] A[DONT_GENERATE, DONT_INLINE]] */
    private final void cacheIrPropertySymbols(FirProperty property, PropertySymbols symbols, ConeClassLikeLookupTag fakeOverrideOwnerLookupTag) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrPropertySymbol propertySymbol = symbols.getPropertySymbol();
        IrFieldSymbol backingFieldSymbol = symbols.getBackingFieldSymbol();
        if (backingFieldSymbol != null) {
            this.backingFieldForPropertyCache.put(propertySymbol, backingFieldSymbol);
            this.propertyForBackingFieldCache.put(backingFieldSymbol, propertySymbol);
        }
        IrSimpleFunctionSymbol getterSymbol = symbols.getGetterSymbol();
        if (getterSymbol != null) {
            this.getterForPropertyCache.put(propertySymbol, getterSymbol);
        }
        IrSimpleFunctionSymbol setterSymbol = symbols.getSetterSymbol();
        if (setterSymbol != null) {
            this.setterForPropertyCache.put(propertySymbol, setterSymbol);
        }
        if (!Fir2IrDeclarationStorageKt.isFakeOverrideOrDelegated(property, fakeOverrideOwnerLookupTag)) {
            this.propertyCache.set(property, propertySymbol);
            return;
        }
        FirCallableDeclaration firCallableDeclaration = property;
        while (true) {
            FirCallableDeclaration wrapped = null;
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr != null) {
                wrapped = originalForSubstitutionOverrideAttr;
            } else {
                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
                if (originalForSubstitutionOverrideAttr == null) {
                    DelegatedWrapperData delegatedWrapperData = ClassMembersKt.getDelegatedWrapperData(firCallableDeclaration);
                    if (delegatedWrapperData != null) {
                        wrapped = delegatedWrapperData.getWrapped();
                    }
                } else {
                    wrapped = originalForSubstitutionOverrideAttr;
                }
            }
            if (wrapped == null) {
                break;
            } else {
                firCallableDeclaration = wrapped;
            }
        }
        FakeOverrideIdentifier.Companion companion = FakeOverrideIdentifier.INSTANCE;
        FirPropertySymbol symbol = ((FirProperty) firCallableDeclaration).getSymbol();
        if (fakeOverrideOwnerLookupTag == null) {
            fakeOverrideOwnerLookupTag = ClassMembersKt.containingClassLookupTag(property);
            fakeOverrideOwnerLookupTag.getClass();
        }
        this.irForFirSessionDependantDeclarationMap.put(companion.invoke(this, symbol, fakeOverrideOwnerLookupTag), propertySymbol);
    }

    private final void cacheIrPropertySymbolsForPureField(FirField field, PropertySymbols symbols, ConeClassLikeLookupTag fakeOverrideOwnerLookupTag, boolean isFakeOverride) {
        IrPropertySymbol propertySymbol = symbols.getPropertySymbol();
        IrFieldSymbol backingFieldSymbol = symbols.getBackingFieldSymbol();
        if (backingFieldSymbol != null) {
            this.backingFieldForPropertyCache.put(propertySymbol, backingFieldSymbol);
            this.propertyForBackingFieldCache.put(backingFieldSymbol, propertySymbol);
        }
        if (!isFakeOverride) {
            this.propertyForFieldCache.put(field, propertySymbol);
            return;
        }
        FirCallableDeclaration firCallableDeclaration = field;
        while (true) {
            FirCallableDeclaration originalForIntersectionOverrideAttr = null;
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr != null) {
                originalForIntersectionOverrideAttr = originalForSubstitutionOverrideAttr;
            } else if (ClassMembersKt.isIntersectionOverride(firCallableDeclaration)) {
                originalForIntersectionOverrideAttr = ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration);
            }
            if (originalForIntersectionOverrideAttr == null) {
                break;
            } else {
                firCallableDeclaration = originalForIntersectionOverrideAttr;
            }
        }
        FakeOverrideIdentifier.Companion companion = FakeOverrideIdentifier.INSTANCE;
        FirFieldSymbol symbol = ((FirField) firCallableDeclaration).getSymbol();
        if (fakeOverrideOwnerLookupTag == null) {
            fakeOverrideOwnerLookupTag = ClassMembersKt.containingClassLookupTag(field);
            fakeOverrideOwnerLookupTag.getClass();
        }
        this.irForFirSessionDependantDeclarationMap.put(companion.invoke(this, symbol, fakeOverrideOwnerLookupTag), propertySymbol);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    private final IrDeclarationOrigin computeExternalOrigin(FirCallableDeclaration firCallableDeclaration) throws KotlinIllegalArgumentExceptionWithAttachments {
        ImportedFromObjectOrStaticData importedFromObjectOrStaticData;
        FirCallableDeclaration original;
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(firCallableDeclaration);
        Boolean boolValueOf = null;
        FirRegularClassSymbol regularClassSymbol = coneClassLikeLookupTagContainingClassLookupTag != null ? ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) this, coneClassLikeLookupTagContainingClassLookupTag) : null;
        if (regularClassSymbol != null) {
            FirDeclarationOrigin origin = regularClassSymbol.getOrigin();
            boolean z = true;
            if (!(origin instanceof FirDeclarationOrigin.Java) && !Intrinsics.areEqual(origin, FirDeclarationOrigin.Enhancement.INSTANCE)) {
                E fir = regularClassSymbol.getFir();
                FirCallableDeclaration firCallableDeclaration2 = fir instanceof FirCallableDeclaration ? (FirCallableDeclaration) fir : null;
                if (firCallableDeclaration2 == null || (importedFromObjectOrStaticData = FirAbstractImportingScopeKt.getImportedFromObjectOrStaticData(firCallableDeclaration2)) == null || (original = importedFromObjectOrStaticData.getOriginal()) == null || !DeclarationUtilsKt.isJavaOrEnhancement(original)) {
                    z = false;
                }
            }
            boolValueOf = Boolean.valueOf(z);
        }
        return Intrinsics.areEqual(boolValueOf, Boolean.TRUE) ? IrDeclarationOrigin.Companion.getIR_EXTERNAL_JAVA_DECLARATION_STUB() : IrDeclarationOrigin.Companion.getIR_EXTERNAL_DECLARATION_STUB();
    }

    public static /* synthetic */ IrConstructor createAndCacheIrConstructor$default(Fir2IrDeclarationStorage fir2IrDeclarationStorage, FirConstructor firConstructor, Function0 function0, IrDeclarationOrigin irDeclarationOrigin, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            irDeclarationOrigin = null;
        }
        if ((i & 8) != 0) {
            z = false;
        }
        return fir2IrDeclarationStorage.createAndCacheIrConstructor(firConstructor, function0, irDeclarationOrigin, z);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static /* synthetic */ IrSimpleFunction createAndCacheIrFunction$default(Fir2IrDeclarationStorage fir2IrDeclarationStorage, FirFunction firFunction, IrDeclarationParent irDeclarationParent, IrDeclarationOrigin irDeclarationOrigin, boolean z, ConeClassLikeLookupTag coneClassLikeLookupTag, boolean z2, int i, Object obj) throws KotlinIllegalArgumentExceptionWithAttachments {
        if ((i & 4) != 0) {
            irDeclarationOrigin = null;
        }
        if ((i & 8) != 0) {
            z = false;
        }
        if ((i & 16) != 0) {
            coneClassLikeLookupTag = null;
        }
        if ((i & 32) != 0) {
            z2 = false;
        }
        return fir2IrDeclarationStorage.createAndCacheIrFunction(firFunction, irDeclarationParent, irDeclarationOrigin, z, coneClassLikeLookupTag, z2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static /* synthetic */ IrProperty createAndCacheIrProperty$default(Fir2IrDeclarationStorage fir2IrDeclarationStorage, FirProperty firProperty, IrDeclarationParent irDeclarationParent, IrDeclarationOrigin irDeclarationOrigin, ConeClassLikeLookupTag coneClassLikeLookupTag, boolean z, int i, Object obj) throws KotlinIllegalArgumentExceptionWithAttachments {
        if ((i & 4) != 0) {
            irDeclarationOrigin = null;
        }
        if ((i & 8) != 0) {
            coneClassLikeLookupTag = null;
        }
        if ((i & 16) != 0) {
            z = false;
        }
        return fir2IrDeclarationStorage.createAndCacheIrProperty(firProperty, irDeclarationParent, irDeclarationOrigin, coneClassLikeLookupTag, z);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final PropertySymbols createAndCacheIrPropertySymbols(FirProperty property, ConeClassLikeLookupTag fakeOverrideOwnerLookupTag) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirProperty firPropertyCreateFirPropertyFakeOverrideIfNeeded$org_jetbrains_kotlin_fir2ir;
        IrDeclarationParent irDeclarationParentFindIrParent$org_jetbrains_kotlin_fir2ir = findIrParent$org_jetbrains_kotlin_fir2ir(property, fakeOverrideOwnerLookupTag);
        if (irDeclarationParentFindIrParent$org_jetbrains_kotlin_fir2ir == null || !Fir2IrCallableDeclarationsGeneratorKt.isExternalParent(irDeclarationParentFindIrParent$org_jetbrains_kotlin_fir2ir)) {
            PropertySymbols propertySymbolsCreatePropertySymbols = createPropertySymbols(property, fakeOverrideOwnerLookupTag, false);
            cacheIrPropertySymbols(property, propertySymbolsCreatePropertySymbols, fakeOverrideOwnerLookupTag);
            return propertySymbolsCreatePropertySymbols;
        }
        PropertySymbols propertySymbolsCreatePropertySymbols2 = createPropertySymbols(property, fakeOverrideOwnerLookupTag, true);
        FirProperty firProperty = (fakeOverrideOwnerLookupTag == null || (firPropertyCreateFirPropertyFakeOverrideIfNeeded$org_jetbrains_kotlin_fir2ir = getLazyFakeOverrideGenerator().createFirPropertyFakeOverrideIfNeeded$org_jetbrains_kotlin_fir2ir(property, fakeOverrideOwnerLookupTag)) == null) ? property : firPropertyCreateFirPropertyFakeOverrideIfNeeded$org_jetbrains_kotlin_fir2ir;
        if (Fir2IrCallableDeclarationsGenerator.createIrProperty$default(getCallablesGenerator(), firProperty, irDeclarationParentFindIrParent$org_jetbrains_kotlin_fir2ir, propertySymbolsCreatePropertySymbols2, computeExternalOrigin(firProperty), null, true, 16, null) instanceof Fir2IrLazyProperty) {
            cacheIrPropertySymbols(property, propertySymbolsCreatePropertySymbols2, fakeOverrideOwnerLookupTag);
            return propertySymbolsCreatePropertySymbols2;
        }
        k2d.a("Check failed.");
        return null;
    }

    public static /* synthetic */ IrVariable createAndCacheIrVariable$default(Fir2IrDeclarationStorage fir2IrDeclarationStorage, FirVariable firVariable, IrDeclarationParent irDeclarationParent, IrDeclarationOrigin irDeclarationOrigin, int i, Object obj) {
        if ((i & 4) != 0) {
            irDeclarationOrigin = null;
        }
        return fir2IrDeclarationStorage.createAndCacheIrVariable(firVariable, irDeclarationParent, irDeclarationOrigin);
    }

    public static /* synthetic */ IrValueParameter createAndCacheParameter$default(Fir2IrDeclarationStorage fir2IrDeclarationStorage, FirValueParameter firValueParameter, boolean z, ConversionTypeOrigin conversionTypeOrigin, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        boolean z4 = z;
        if ((i & 4) != 0) {
            conversionTypeOrigin = ConversionTypeOrigin.DEFAULT;
        }
        return fir2IrDeclarationStorage.createAndCacheParameter(firValueParameter, z4, conversionTypeOrigin, (i & 8) != 0 ? false : z2, (i & 16) != 0 ? false : z3);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final PropertySymbols createAndCachePropertySymbolsForJavaField(final FirField field, final ConeClassLikeLookupTag fakeOverrideOwnerLookupTag) throws KotlinIllegalArgumentExceptionWithAttachments {
        PropertySymbols propertySymbols;
        FirField firFieldCreateFirFieldFakeOverrideIfNeeded$org_jetbrains_kotlin_fir2ir;
        final IrDeclarationParent irDeclarationParentFindIrParent$org_jetbrains_kotlin_fir2ir = findIrParent$org_jetbrains_kotlin_fir2ir(field, fakeOverrideOwnerLookupTag);
        if (irDeclarationParentFindIrParent$org_jetbrains_kotlin_fir2ir == null) {
            dt1.a("No IR parent found for field ", UtilsKt.render(field));
            return null;
        }
        Function1 function1 = new Function1() { // from class: fv4
            public final Object invoke(Object obj) {
                return Fir2IrDeclarationStorage.b(field, fakeOverrideOwnerLookupTag, irDeclarationParentFindIrParent$org_jetbrains_kotlin_fir2ir, (ExceptionAttachmentBuilder) obj);
            }
        };
        boolean zIsFakeOverride = Fir2IrDeclarationStorageKt.isFakeOverride(field, fakeOverrideOwnerLookupTag);
        boolean z = zIsFakeOverride && field.getStatus().isStatic();
        boolean zIsExternalParent = Fir2IrCallableDeclarationsGeneratorKt.isExternalParent(irDeclarationParentFindIrParent$org_jetbrains_kotlin_fir2ir);
        if (z && !zIsExternalParent) {
            KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Fake-overrides for static field in non-external classes are not allowed: " + UtilsKt.render(field), (Throwable) null);
            ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
            function1.invoke(exceptionAttachmentBuilder);
            kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
            throw kotlinIllegalArgumentExceptionWithAttachments;
        }
        if (zIsFakeOverride && !z && !zIsExternalParent) {
            FirCallableDeclaration firCallableDeclaration = field;
            while (true) {
                FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                if (originalForSubstitutionOverrideAttr == null) {
                    originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
                }
                if (originalForSubstitutionOverrideAttr == null) {
                    break;
                }
                firCallableDeclaration = originalForSubstitutionOverrideAttr;
            }
            propertySymbols = createFakeOverridePropertySymbolsForJavaField((FirField) firCallableDeclaration, fakeOverrideOwnerLookupTag);
        } else {
            if (!Fir2IrCallableDeclarationsGeneratorKt.isExternalParent(irDeclarationParentFindIrParent$org_jetbrains_kotlin_fir2ir)) {
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments2 = new KotlinIllegalArgumentExceptionWithAttachments("Non f/o field with non-external parent: " + UtilsKt.render(field));
                ExceptionAttachmentBuilder exceptionAttachmentBuilder2 = new ExceptionAttachmentBuilder();
                function1.invoke(exceptionAttachmentBuilder2);
                kotlinIllegalArgumentExceptionWithAttachments2.withAttachment("info.txt", exceptionAttachmentBuilder2.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments2;
            }
            IrFieldSymbol irFieldSymbolCreateFieldSymbol = createFieldSymbol();
            IrPropertySymbolImpl irPropertySymbolImpl = new IrPropertySymbolImpl((PropertyDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null);
            getLazyDeclarationsGenerator().createIrPropertyForPureField((fakeOverrideOwnerLookupTag == null || (firFieldCreateFirFieldFakeOverrideIfNeeded$org_jetbrains_kotlin_fir2ir = getLazyFakeOverrideGenerator().createFirFieldFakeOverrideIfNeeded$org_jetbrains_kotlin_fir2ir(field, fakeOverrideOwnerLookupTag)) == null) ? field : firFieldCreateFirFieldFakeOverrideIfNeeded$org_jetbrains_kotlin_fir2ir, irFieldSymbolCreateFieldSymbol, irPropertySymbolImpl, irDeclarationParentFindIrParent$org_jetbrains_kotlin_fir2ir, computeExternalOrigin(field));
            propertySymbols = new PropertySymbols(irPropertySymbolImpl, null, null, irFieldSymbolCreateFieldSymbol);
        }
        cacheIrPropertySymbolsForPureField(field, propertySymbols, fakeOverrideOwnerLookupTag, zIsFakeOverride);
        return propertySymbols;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:40:0x0093  */
    /* JADX WARN: Code duplicated, block: B:43:0x00a3  */
    /* JADX WARN: Multi-variable type inference failed */
    private final PropertySymbols createFakeOverridePropertySymbols(FirProperty property, ConeClassLikeLookupTag fakeOverrideOwnerLookupTag) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrFunctionFakeOverrideSymbol irFunctionFakeOverrideSymbol;
        boolean zBooleanValue;
        FirClassSymbol<?> classSymbol;
        FirClass firClass;
        FirCallableDeclaration firCallableDeclaration = property;
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
                if (originalForSubstitutionOverrideAttr == null) {
                    DelegatedWrapperData delegatedWrapperData = ClassMembersKt.getDelegatedWrapperData(firCallableDeclaration);
                    originalForSubstitutionOverrideAttr = delegatedWrapperData != null ? delegatedWrapperData.getWrapped() : null;
                }
            }
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        PropertySymbols irPropertySymbols$default = getIrPropertySymbols$default(this, ((FirProperty) firCallableDeclaration).getSymbol(), null, 2, null);
        if (Intrinsics.areEqual(Fir2IrDeclarationStorageKt.isStubPropertyForPureField(property), Boolean.TRUE)) {
            dt1.a("What are we doing here? ", UtilsKt.render(property));
            return null;
        }
        IrClassSymbol irClassSymbolFindContainingIrClassSymbol = findContainingIrClassSymbol(property, fakeOverrideOwnerLookupTag);
        IrPropertyFakeOverrideSymbol irPropertyFakeOverrideSymbol = new IrPropertyFakeOverrideSymbol(irPropertySymbols$default.getPropertySymbol(), irClassSymbolFindContainingIrClassSymbol, (IdSignature) null);
        IrSimpleFunctionSymbol getterSymbol = irPropertySymbols$default.getGetterSymbol();
        IrFunctionFakeOverrideSymbol irFunctionFakeOverrideSymbol2 = getterSymbol != null ? new IrFunctionFakeOverrideSymbol(getterSymbol, irClassSymbolFindContainingIrClassSymbol, (IdSignature) null) : null;
        if (property.getIsVar()) {
            FirPropertyAccessor setter = property.getSetter();
            if (setter == null) {
                zBooleanValue = true;
            } else {
                Boolean boolValueOf = (fakeOverrideOwnerLookupTag == null || (classSymbol = ToSymbolUtilsKt.toClassSymbol(this, fakeOverrideOwnerLookupTag)) == null || (firClass = (FirClass) classSymbol.getFir()) == null) ? null : Boolean.valueOf(FirVisibilityCheckerKt.isVisibleInClass(setter, firClass));
                if (boolValueOf != null) {
                    zBooleanValue = boolValueOf.booleanValue();
                } else {
                    zBooleanValue = true;
                }
            }
            if (zBooleanValue) {
                IrSimpleFunctionSymbol setterSymbol = irPropertySymbols$default.getSetterSymbol();
                setterSymbol.getClass();
                irFunctionFakeOverrideSymbol = new IrFunctionFakeOverrideSymbol(setterSymbol, irClassSymbolFindContainingIrClassSymbol, (IdSignature) null);
            } else {
                irFunctionFakeOverrideSymbol = null;
            }
        } else {
            irFunctionFakeOverrideSymbol = null;
        }
        return new PropertySymbols(irPropertyFakeOverrideSymbol, irFunctionFakeOverrideSymbol2, irFunctionFakeOverrideSymbol, null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final PropertySymbols createFakeOverridePropertySymbolsForJavaField(FirField field, ConeClassLikeLookupTag fakeOverrideOwnerLookupTag) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrPropertySymbol irPropertySymbolForJavaField = getIrPropertySymbolForJavaField(field, null);
        IrFieldSymbol irFieldSymbolFindBackingFieldOfProperty = findBackingFieldOfProperty(irPropertySymbolForJavaField);
        if (irFieldSymbolFindBackingFieldOfProperty != null) {
            IrClassSymbol irClassSymbolFindContainingIrClassSymbol = findContainingIrClassSymbol(field, fakeOverrideOwnerLookupTag);
            IrPropertyFakeOverrideSymbol irPropertyFakeOverrideSymbol = new IrPropertyFakeOverrideSymbol(irPropertySymbolForJavaField, irClassSymbolFindContainingIrClassSymbol, (IdSignature) null);
            return new PropertySymbols(irPropertyFakeOverrideSymbol, null, null, new IrFieldFakeOverrideSymbol(irFieldSymbolFindBackingFieldOfProperty, irClassSymbolFindContainingIrClassSymbol, (IdSignature) null, irPropertyFakeOverrideSymbol));
        }
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("No original backing field found for f/o field");
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "field", field);
        exceptionAttachmentBuilder.withEntry("fakeOverrideOwnerLookupTag", String.valueOf(fakeOverrideOwnerLookupTag));
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    private final IrFieldSymbol createFieldSymbol() {
        return new IrFieldSymbolImpl((PropertyDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null);
    }

    private final LocalDelegatedPropertySymbols createLocalDelegatedPropertySymbols(FirProperty property) {
        return new LocalDelegatedPropertySymbols(new IrLocalDelegatedPropertySymbolImpl((VariableDescriptorWithAccessors) null, 1, (DefaultConstructorMarker) null), createFunctionSymbol$org_jetbrains_kotlin_fir2ir(), property.getIsVar() ? createFunctionSymbol$org_jetbrains_kotlin_fir2ir() : null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final IrSimpleFunctionSymbol createMemberFunctionSymbol(FirFunction function, ConeClassLikeLookupTag fakeOverrideOwnerLookupTag, boolean parentIsExternal) throws KotlinIllegalArgumentExceptionWithAttachments {
        if (parentIsExternal || !(function instanceof FirNamedFunction) || !Fir2IrDeclarationStorageKt.isFakeOverrideOrDelegated(function, fakeOverrideOwnerLookupTag)) {
            return createFunctionSymbol$org_jetbrains_kotlin_fir2ir();
        }
        IrClassSymbol irClassSymbolFindContainingIrClassSymbol = findContainingIrClassSymbol(function, fakeOverrideOwnerLookupTag);
        FirCallableDeclaration firCallableDeclaration = function;
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
                if (originalForSubstitutionOverrideAttr == null) {
                    DelegatedWrapperData delegatedWrapperData = ClassMembersKt.getDelegatedWrapperData(firCallableDeclaration);
                    originalForSubstitutionOverrideAttr = delegatedWrapperData != null ? delegatedWrapperData.getWrapped() : null;
                }
            }
            if (originalForSubstitutionOverrideAttr == null) {
                IrSimpleFunctionSymbol irFunctionSymbol$default = getIrFunctionSymbol$default(this, ((FirNamedFunction) firCallableDeclaration).getSymbol(), null, false, 6, null);
                irFunctionSymbol$default.getClass();
                return new IrFunctionFakeOverrideSymbol(irFunctionSymbol$default, irClassSymbolFindContainingIrClassSymbol, (IdSignature) null);
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final PropertySymbols createPropertySymbols(FirProperty property, ConeClassLikeLookupTag fakeOverrideOwnerLookupTag, boolean parentIsExternal) throws KotlinIllegalArgumentExceptionWithAttachments {
        if (!parentIsExternal && Fir2IrDeclarationStorageKt.isFakeOverrideOrDelegated(property, fakeOverrideOwnerLookupTag)) {
            return createFakeOverridePropertySymbols(property, fakeOverrideOwnerLookupTag);
        }
        boolean z = property.getOrigin() instanceof FirDeclarationOrigin.Java;
        return new PropertySymbols(new IrPropertySymbolImpl((PropertyDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null), !z ? createFunctionSymbol$org_jetbrains_kotlin_fir2ir() : null, !z && property.getIsVar() ? createFunctionSymbol$org_jetbrains_kotlin_fir2ir() : null, property.getDelegate() != null || getExtensions().hasBackingField(property, getSession()) ? createFieldSymbol() : null);
    }

    public static Fir2IrLocalCallableStorage e(Fir2IrCommonMemberStorage fir2IrCommonMemberStorage) {
        return new Fir2IrLocalCallableStorage(fir2IrCommonMemberStorage.getLocalCallableCache());
    }

    public static DataClassGeneratedFunctionsStorage f(FirClass firClass) {
        firClass.getClass();
        return new DataClassGeneratedFunctionsStorage();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @LeakedDeclarationCaches
    private final void fillUnboundSymbols(Map<? extends FirCallableDeclaration, ? extends IrSymbol> cache) throws KotlinIllegalArgumentExceptionWithAttachments {
        for (Map.Entry<? extends FirCallableDeclaration, ? extends IrSymbol> entry : cache.entrySet()) {
            FirCallableDeclaration key = entry.getKey();
            if (!entry.getValue().isBound()) {
                FirLazyDeclarationResolverKt.lazyResolveToPhase(key, FirResolvePhase.ANNOTATION_ARGUMENTS);
                generateDeclaration(key.getSymbol());
            }
        }
    }

    private final IrClassSymbol findContainingIrClassSymbol(FirCallableDeclaration callable, ConeClassLikeLookupTag fakeOverrideOwnerLookupTag) {
        if (fakeOverrideOwnerLookupTag == null) {
            if (!ClassMembersKt.isSubstitutionOrIntersectionOverride(callable) && !ClassMembersKt.isDelegated(callable)) {
                AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
                wq6.a();
                return null;
            }
            fakeOverrideOwnerLookupTag = ClassMembersKt.containingClassLookupTag(callable);
        }
        if (fakeOverrideOwnerLookupTag == null) {
            dt1.a("Containing class not found for ", UtilsKt.render(callable));
            return null;
        }
        IrClassSymbol irClassSymbol = getClassifierStorage().getIrClassSymbol(fakeOverrideOwnerLookupTag);
        if (irClassSymbol != null) {
            return irClassSymbol;
        }
        xz8.a("IR class for ", fakeOverrideOwnerLookupTag, " not found");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    private final void generateDeclaration(FirBasedSymbol<?> originalSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        FqName fqNamePackageFqName = UtilsKt.packageFqName(originalSymbol);
        FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(originalSymbol);
        IrDeclarationParent irDeclarationParentFindIrParent$org_jetbrains_kotlin_fir2ir = findIrParent$org_jetbrains_kotlin_fir2ir(fqNamePackageFqName, containingClassSymbol != null ? containingClassSymbol.getLookupTag() : null, originalSymbol, originalSymbol.getOrigin());
        if (originalSymbol instanceof FirPropertySymbol) {
            createAndCacheIrProperty$default(this, (FirProperty) ((FirPropertySymbol) originalSymbol).getFir(), irDeclarationParentFindIrParent$org_jetbrains_kotlin_fir2ir, null, null, false, 20, null);
        } else if (originalSymbol instanceof FirNamedFunctionSymbol) {
            createAndCacheIrFunction$default(this, (FirFunction) ((FirNamedFunctionSymbol) originalSymbol).getFir(), irDeclarationParentFindIrParent$org_jetbrains_kotlin_fir2ir, IrDeclarationOrigin.Companion.getFILLED_FOR_UNBOUND_SYMBOL(), false, null, false, 40, null);
        } else {
            w04.a("Unexpected declaration: ", originalSymbol);
        }
    }

    public static /* synthetic */ IrSimpleFunctionSymbol getCachedIrFunctionSymbol$default(Fir2IrDeclarationStorage fir2IrDeclarationStorage, FirFunction firFunction, ConeClassLikeLookupTag coneClassLikeLookupTag, int i, Object obj) {
        if ((i & 2) != 0) {
            coneClassLikeLookupTag = null;
        }
        return fir2IrDeclarationStorage.getCachedIrFunctionSymbol(firFunction, coneClassLikeLookupTag);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DataClassGeneratedFunctionsStorage getCachedIrFunctionSymbol$lambda$0$0(FirClass firClass) {
        firClass.getClass();
        return new DataClassGeneratedFunctionsStorage();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DataClassGeneratedFunctionsStorage getCachedIrFunctionSymbol$lambda$0$1(Function1 function1, Object obj) {
        return (DataClassGeneratedFunctionsStorage) function1.invoke(obj);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final PropertySymbols getCachedIrPropertySymbols(FirProperty property, ConeClassLikeLookupTag fakeOverrideOwnerLookupTag) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrPropertySymbol cachedIrPropertySymbol = getCachedIrPropertySymbol(property, fakeOverrideOwnerLookupTag);
        if (cachedIrPropertySymbol == null) {
            return null;
        }
        IrSimpleFunctionSymbol irSimpleFunctionSymbolFindGetterOfProperty = findGetterOfProperty(cachedIrPropertySymbol);
        irSimpleFunctionSymbolFindGetterOfProperty.getClass();
        return new PropertySymbols(cachedIrPropertySymbol, irSimpleFunctionSymbolFindGetterOfProperty, findSetterOfProperty(cachedIrPropertySymbol), findBackingFieldOfProperty(cachedIrPropertySymbol));
    }

    private final IrPropertySymbol getCachedIrSymbolForJavaField(FirField field, ConeClassLikeLookupTag fakeOverrideOwnerLookupTag) {
        ConcurrentHashMap<FirField, IrPropertySymbol> concurrentHashMap = this.propertyForFieldCache;
        if (Fir2IrDeclarationStorageKt.isFakeOverrideOrDelegated(field, fakeOverrideOwnerLookupTag)) {
            FakeOverrideIdentifier.Companion companion = FakeOverrideIdentifier.INSTANCE;
            FirCallableDeclaration firCallableDeclaration = field;
            while (true) {
                FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                if (originalForSubstitutionOverrideAttr == null) {
                    originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
                    if (originalForSubstitutionOverrideAttr == null) {
                        DelegatedWrapperData delegatedWrapperData = ClassMembersKt.getDelegatedWrapperData(firCallableDeclaration);
                        originalForSubstitutionOverrideAttr = delegatedWrapperData != null ? delegatedWrapperData.getWrapped() : null;
                    }
                }
                if (originalForSubstitutionOverrideAttr == null) {
                    break;
                }
                firCallableDeclaration = originalForSubstitutionOverrideAttr;
            }
            FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
            if (fakeOverrideOwnerLookupTag == null) {
                fakeOverrideOwnerLookupTag = ClassMembersKt.containingClassLookupTag(field);
                fakeOverrideOwnerLookupTag.getClass();
            }
            IrPropertySymbol irPropertySymbol = (IrSymbol) this.irForFirSessionDependantDeclarationMap.get(companion.invoke(this, symbol, fakeOverrideOwnerLookupTag));
            if (irPropertySymbol != null) {
                return irPropertySymbol;
            }
        } else {
            IrPropertySymbol irPropertySymbol2 = concurrentHashMap.get(field);
            if (irPropertySymbol2 != null) {
                return irPropertySymbol2;
            }
        }
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static /* synthetic */ IrConstructorSymbol getIrConstructorSymbol$default(Fir2IrDeclarationStorage fir2IrDeclarationStorage, FirConstructorSymbol firConstructorSymbol, boolean z, int i, Object obj) throws KotlinIllegalArgumentExceptionWithAttachments {
        if ((i & 2) != 0) {
            z = true;
        }
        return fir2IrDeclarationStorage.getIrConstructorSymbol(firConstructorSymbol, z);
    }

    private final IrExternalPackageFragment getIrExternalOrBuiltInsPackageFragment(FqName fqName, FirModuleData moduleData, FirDeclarationOrigin firOrigin, boolean allowBuiltins) {
        IrExternalPackageFragment irExternalPackageFragment;
        boolean z = allowBuiltins && StandardNames.BUILT_INS_PACKAGE_FQ_NAMES.contains(fqName);
        ConcurrentHashMap<FqName, ExternalPackageFragments> concurrentHashMap = this.fragmentCache;
        ExternalPackageFragments externalPackageFragments = concurrentHashMap.get(fqName);
        if (externalPackageFragments == null) {
            ExternalPackageFragments externalPackageFragments2 = new ExternalPackageFragments(new ConcurrentHashMap(), new ConcurrentHashMap(), getCallablesGenerator().createExternalPackageFragment$org_jetbrains_kotlin_fir2ir(fqName, this.sourceModuleDescriptor));
            ExternalPackageFragments externalPackageFragmentsPutIfAbsent = concurrentHashMap.putIfAbsent(fqName, externalPackageFragments2);
            externalPackageFragments = externalPackageFragmentsPutIfAbsent == null ? externalPackageFragments2 : externalPackageFragmentsPutIfAbsent;
        }
        ExternalPackageFragments externalPackageFragments3 = externalPackageFragments;
        if (Intrinsics.areEqual(firOrigin, FirDeclarationOrigin.Precompiled.INSTANCE)) {
            return externalPackageFragments3.getFragmentForPrecompiledBinaries();
        }
        FirModuleDescriptor dependenciesModuleDescriptor = getDependenciesModuleDescriptor(moduleData);
        if (z) {
            ConcurrentHashMap<FirModuleData, IrExternalPackageFragment> builtinFragmentsForDependencies = externalPackageFragments3.getBuiltinFragmentsForDependencies();
            IrExternalPackageFragment irExternalPackageFragment2 = builtinFragmentsForDependencies.get(moduleData);
            if (irExternalPackageFragment2 == null) {
                IrExternalPackageFragment irExternalPackageFragmentCreateExternalPackageFragment$org_jetbrains_kotlin_fir2ir = getCallablesGenerator().createExternalPackageFragment$org_jetbrains_kotlin_fir2ir(new FirBuiltInsPackageFragment(fqName, dependenciesModuleDescriptor));
                IrExternalPackageFragment irExternalPackageFragmentPutIfAbsent = builtinFragmentsForDependencies.putIfAbsent(moduleData, irExternalPackageFragmentCreateExternalPackageFragment$org_jetbrains_kotlin_fir2ir);
                irExternalPackageFragment2 = irExternalPackageFragmentPutIfAbsent == null ? irExternalPackageFragmentCreateExternalPackageFragment$org_jetbrains_kotlin_fir2ir : irExternalPackageFragmentPutIfAbsent;
            }
            irExternalPackageFragment = irExternalPackageFragment2;
        } else {
            ConcurrentHashMap<FirModuleData, IrExternalPackageFragment> fragmentsForDependencies = externalPackageFragments3.getFragmentsForDependencies();
            IrExternalPackageFragment irExternalPackageFragment3 = fragmentsForDependencies.get(moduleData);
            if (irExternalPackageFragment3 == null) {
                IrExternalPackageFragment irExternalPackageFragmentCreateExternalPackageFragment$org_jetbrains_kotlin_fir2ir2 = getCallablesGenerator().createExternalPackageFragment$org_jetbrains_kotlin_fir2ir(fqName, dependenciesModuleDescriptor);
                IrExternalPackageFragment irExternalPackageFragmentPutIfAbsent2 = fragmentsForDependencies.putIfAbsent(moduleData, irExternalPackageFragmentCreateExternalPackageFragment$org_jetbrains_kotlin_fir2ir2);
                irExternalPackageFragment3 = irExternalPackageFragmentPutIfAbsent2 == null ? irExternalPackageFragmentCreateExternalPackageFragment$org_jetbrains_kotlin_fir2ir2 : irExternalPackageFragmentPutIfAbsent2;
            }
            irExternalPackageFragment = irExternalPackageFragment3;
        }
        irExternalPackageFragment.getClass();
        return irExternalPackageFragment;
    }

    public static /* synthetic */ IrExternalPackageFragment getIrExternalPackageFragment$default(Fir2IrDeclarationStorage fir2IrDeclarationStorage, FqName fqName, FirModuleData firModuleData, FirDeclarationOrigin firDeclarationOrigin, int i, Object obj) {
        if ((i & 4) != 0) {
            firDeclarationOrigin = FirDeclarationOrigin.Library.INSTANCE;
        }
        return fir2IrDeclarationStorage.getIrExternalPackageFragment(fqName, firModuleData, firDeclarationOrigin);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static /* synthetic */ IrFunctionSymbol getIrFunctionSymbol$default(Fir2IrDeclarationStorage fir2IrDeclarationStorage, FirFunctionSymbol firFunctionSymbol, ConeClassLikeLookupTag coneClassLikeLookupTag, boolean z, int i, Object obj) throws KotlinIllegalArgumentExceptionWithAttachments {
        if ((i & 2) != 0) {
            coneClassLikeLookupTag = null;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        return fir2IrDeclarationStorage.getIrFunctionSymbol(firFunctionSymbol, coneClassLikeLookupTag, z);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final IrSymbol getIrPropertyForwardedSymbol(FirProperty fir) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrLocalDelegatedProperty irLocalDelegatedProperty;
        IrDeclarationOrigin defined;
        IrProperty irPropertyOwnerIfBound;
        IrVariable delegate;
        IrVariableSymbol symbol;
        if (fir.getSymbol() instanceof FirLocalPropertySymbol) {
            IrLocalDelegatedPropertySymbol delegatedProperty = getLocalStorage().getDelegatedProperty(fir);
            irLocalDelegatedProperty = delegatedProperty != null ? (IrLocalDelegatedProperty) delegatedProperty.getOwner() : null;
            return (irLocalDelegatedProperty == null || (delegate = irLocalDelegatedProperty.getDelegate()) == null || (symbol = delegate.getSymbol()) == null) ? getIrVariableSymbol(fir) : symbol;
        }
        IrPropertySymbol irPropertySymbol = this.propertyCache.get(fir);
        if (irPropertySymbol != null && (irPropertyOwnerIfBound = Fir2IrDeclarationStorageKt.ownerIfBound(irPropertySymbol)) != null) {
            IrField backingField = irPropertyOwnerIfBound.getBackingField();
            backingField.getClass();
            return backingField.getSymbol();
        }
        IrLocalDelegatedProperty irLocalDelegatedPropertyFindIrParent$org_jetbrains_kotlin_fir2ir = findIrParent$org_jetbrains_kotlin_fir2ir(fir, null);
        irLocalDelegatedProperty = irLocalDelegatedPropertyFindIrParent$org_jetbrains_kotlin_fir2ir instanceof IrDeclaration ? (IrDeclaration) irLocalDelegatedPropertyFindIrParent$org_jetbrains_kotlin_fir2ir : null;
        if (irLocalDelegatedProperty == null || (defined = irLocalDelegatedProperty.getOrigin()) == null) {
            defined = IrDeclarationOrigin.Companion.getDEFINED();
        }
        IrField backingField2 = createAndCacheIrProperty$default(this, fir, irLocalDelegatedPropertyFindIrParent$org_jetbrains_kotlin_fir2ir, defined, null, false, 24, null).getBackingField();
        backingField2.getClass();
        return backingField2.getSymbol();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static /* synthetic */ IrSymbol getIrPropertySymbol$default(Fir2IrDeclarationStorage fir2IrDeclarationStorage, FirPropertySymbol firPropertySymbol, ConeClassLikeLookupTag coneClassLikeLookupTag, int i, Object obj) throws KotlinIllegalArgumentExceptionWithAttachments {
        if ((i & 2) != 0) {
            coneClassLikeLookupTag = null;
        }
        return fir2IrDeclarationStorage.getIrPropertySymbol(firPropertySymbol, coneClassLikeLookupTag);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final IrPropertySymbol getIrPropertySymbolForJavaField(FirField field, ConeClassLikeLookupTag fakeOverrideOwnerLookupTag) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrPropertySymbol cachedIrSymbolForJavaField = getCachedIrSymbolForJavaField(field, fakeOverrideOwnerLookupTag);
        return cachedIrSymbolForJavaField != null ? cachedIrSymbolForJavaField : createAndCachePropertySymbolsForJavaField(field, fakeOverrideOwnerLookupTag).getPropertySymbol();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    private final PropertySymbols getIrPropertySymbols(FirPropertySymbol firPropertySymbol, ConeClassLikeLookupTag fakeOverrideOwnerLookupTag) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirProperty firPropertyPrepareProperty = prepareProperty((FirProperty) firPropertySymbol.getFir());
        PropertySymbols cachedIrPropertySymbols = getCachedIrPropertySymbols(firPropertyPrepareProperty, fakeOverrideOwnerLookupTag);
        return cachedIrPropertySymbols != null ? cachedIrPropertySymbols : createAndCacheIrPropertySymbols(firPropertyPrepareProperty, fakeOverrideOwnerLookupTag);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static /* synthetic */ PropertySymbols getIrPropertySymbols$default(Fir2IrDeclarationStorage fir2IrDeclarationStorage, FirPropertySymbol firPropertySymbol, ConeClassLikeLookupTag coneClassLikeLookupTag, int i, Object obj) throws KotlinIllegalArgumentExceptionWithAttachments {
        if ((i & 2) != 0) {
            coneClassLikeLookupTag = null;
        }
        return fir2IrDeclarationStorage.getIrPropertySymbols(firPropertySymbol, coneClassLikeLookupTag);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final IrFieldSymbol getIrSymbolForSupertypeDelegateField(FirField field, ConeClassLikeLookupTag fakeOverrideOwnerLookupTag) throws KotlinIllegalArgumentExceptionWithAttachments {
        if (!(fakeOverrideOwnerLookupTag == null || Intrinsics.areEqual(ClassMembersKt.dispatchReceiverClassLookupTagOrNull(field), fakeOverrideOwnerLookupTag))) {
            KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Field for supertype delegate accessed with incorrect fakeOverrideOwnerLookupTag");
            ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
            exceptionAttachmentBuilder.withEntry("fakeOverrideOwnerLookupTag", String.valueOf(fakeOverrideOwnerLookupTag));
            FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "field", field);
            kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
            throw kotlinIllegalArgumentExceptionWithAttachments;
        }
        ConcurrentHashMap<FirField, IrFieldSymbol> concurrentHashMap = this.fieldForDelegatedSupertypeCache;
        IrFieldSymbol irFieldSymbol = concurrentHashMap.get(field);
        if (irFieldSymbol == null) {
            IrFieldSymbol irFieldSymbolCreateFieldSymbol = createFieldSymbol();
            IrFieldSymbol irFieldSymbolPutIfAbsent = concurrentHashMap.putIfAbsent(field, irFieldSymbolCreateFieldSymbol);
            irFieldSymbol = irFieldSymbolPutIfAbsent == null ? irFieldSymbolCreateFieldSymbol : irFieldSymbolPutIfAbsent;
        }
        irFieldSymbol.getClass();
        return irFieldSymbol;
    }

    private final IrVariableSymbol getIrVariableSymbol(FirVariable firVariable) {
        IrVariableSymbol variable = getLocalStorage().getVariable(firVariable);
        if (variable != null) {
            return variable;
        }
        b88.a("Cannot find variable ", UtilsKt.render(firVariable), " in local storage");
        return null;
    }

    private final Fir2IrLocalCallableStorage getLocalStorage() {
        return (Fir2IrLocalCallableStorage) this.localStorage.getValue(this, $$delegatedProperties[0]);
    }

    private final FirProperty prepareProperty(FirProperty property) {
        if (property instanceof FirSyntheticProperty) {
            FirSyntheticProperty firSyntheticProperty = (FirSyntheticProperty) property;
            if (firSyntheticProperty.getSymbol() instanceof FirSimpleSyntheticPropertySymbol) {
                ConcurrentHashMap<FirSyntheticPropertyKey, FirSyntheticProperty> concurrentHashMap = this.originalForSyntheticProperty;
                FirSyntheticPropertyKey firSyntheticPropertyKey = new FirSyntheticPropertyKey(firSyntheticProperty);
                FirSyntheticProperty firSyntheticProperty2 = concurrentHashMap.get(firSyntheticPropertyKey);
                if (firSyntheticProperty2 == null) {
                    FirSyntheticProperty firSyntheticPropertyPutIfAbsent = concurrentHashMap.putIfAbsent(firSyntheticPropertyKey, firSyntheticProperty);
                    if (firSyntheticPropertyPutIfAbsent != null) {
                        firSyntheticProperty = firSyntheticPropertyPutIfAbsent;
                    }
                    firSyntheticProperty2 = firSyntheticProperty;
                }
                return firSyntheticProperty2;
            }
        }
        return property;
    }

    public final void cacheGeneratedFunction$org_jetbrains_kotlin_fir2ir(FirNamedFunction firFunction, IrSimpleFunction irFunction) {
        firFunction.getClass();
        irFunction.getClass();
        FirRegularClass containingClass = ResolveUtilsKt.getContainingClass(firFunction);
        containingClass.getClass();
        ConcurrentHashMap<FirClass, DataClassGeneratedFunctionsStorage> concurrentHashMap = this.dataClassGeneratedFunctionsCache;
        final Function1 function1 = new Function1() { // from class: jv4
            public final Object invoke(Object obj) {
                return Fir2IrDeclarationStorage.f((FirClass) obj);
            }
        };
        DataClassGeneratedFunctionsStorage dataClassGeneratedFunctionsStorageComputeIfAbsent = concurrentHashMap.computeIfAbsent(containingClass, new Function() { // from class: kv4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Fir2IrDeclarationStorage.c(function1, obj);
            }
        });
        dataClassGeneratedFunctionsStorageComputeIfAbsent.getClass();
        DataClassGeneratedFunctionsStorage dataClassGeneratedFunctionsStorage = dataClassGeneratedFunctionsStorageComputeIfAbsent;
        IrSimpleFunctionSymbol symbol = irFunction.getSymbol();
        Name nameOrSpecialName = FirDeclarationUtilKt.getNameOrSpecialName(firFunction);
        if (Intrinsics.areEqual(nameOrSpecialName, OperatorNameConventions.EQUALS)) {
            dataClassGeneratedFunctionsStorage.setEqualsSymbol(symbol);
            return;
        }
        if (Intrinsics.areEqual(nameOrSpecialName, OperatorNameConventions.HASH_CODE)) {
            dataClassGeneratedFunctionsStorage.setHashCodeSymbol(symbol);
            return;
        }
        if (Intrinsics.areEqual(nameOrSpecialName, OperatorNameConventions.TO_STRING)) {
            dataClassGeneratedFunctionsStorage.setToStringSymbol(symbol);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Only componentN functions should be cached this way, but got: " + UtilsKt.render(firFunction));
        sb.append('\n');
        sb.append("Dispatch receiver: " + firFunction.getDispatchReceiverType());
        sb.append('\n');
        throw new IllegalStateException(sb.toString().toString());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final IrConstructor createAndCacheIrConstructor(FirConstructor constructor, Function0<? extends IrClass> irParent, IrDeclarationOrigin predefinedOrigin, boolean isLocal) throws KotlinIllegalArgumentExceptionWithAttachments {
        constructor.getClass();
        irParent.getClass();
        return getCallablesGenerator().createIrConstructor(constructor, (IrClass) irParent.invoke(), getIrConstructorSymbol(constructor.getSymbol(), !isLocal), predefinedOrigin, false);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final IrSimpleFunction createAndCacheIrFunction(FirFunction function, IrDeclarationParent irParent, IrDeclarationOrigin predefinedOrigin, boolean isLocal, ConeClassLikeLookupTag fakeOverrideOwnerLookupTag, boolean allowLazyDeclarationsCreation) throws KotlinIllegalArgumentExceptionWithAttachments {
        function.getClass();
        IrFunctionSymbol irFunctionSymbol = getIrFunctionSymbol(function.getSymbol(), fakeOverrideOwnerLookupTag, isLocal);
        irFunctionSymbol.getClass();
        return getCallablesGenerator().createIrFunction(function, irParent, (IrSimpleFunctionSymbol) irFunctionSymbol, predefinedOrigin, isLocal, fakeOverrideOwnerLookupTag, allowLazyDeclarationsCreation);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final IrLocalDelegatedProperty createAndCacheIrLocalDelegatedProperty(FirProperty property, IrDeclarationParent irParent) throws KotlinIllegalArgumentExceptionWithAttachments {
        property.getClass();
        irParent.getClass();
        IrLocalDelegatedProperty irLocalDelegatedPropertyCreateIrLocalDelegatedProperty = getCallablesGenerator().createIrLocalDelegatedProperty(property, irParent, createLocalDelegatedPropertySymbols(property));
        IrSymbol symbol = irLocalDelegatedPropertyCreateIrLocalDelegatedProperty.getSymbol();
        IrVariable delegate = irLocalDelegatedPropertyCreateIrLocalDelegatedProperty.getDelegate();
        if (delegate == null) {
            wec.a("Local delegated property ", RenderIrElementKt.render$default(irLocalDelegatedPropertyCreateIrLocalDelegatedProperty, (DumpIrTreeOptions) null, 1, (Object) null), " has no delegate");
            return null;
        }
        this.delegateVariableForPropertyCache.put(symbol, delegate.getSymbol());
        this.getterForPropertyCache.put(symbol, irLocalDelegatedPropertyCreateIrLocalDelegatedProperty.getGetter().getSymbol());
        IrSimpleFunction setter = irLocalDelegatedPropertyCreateIrLocalDelegatedProperty.getSetter();
        if (setter != null) {
            this.setterForPropertyCache.put(symbol, setter.getSymbol());
        }
        getLocalStorage().putDelegatedProperty(property, symbol);
        return irLocalDelegatedPropertyCreateIrLocalDelegatedProperty;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final IrProperty createAndCacheIrProperty(FirProperty property, IrDeclarationParent irParent, IrDeclarationOrigin predefinedOrigin, ConeClassLikeLookupTag fakeOverrideOwnerLookupTag, boolean allowLazyDeclarationsCreation) throws KotlinIllegalArgumentExceptionWithAttachments {
        property.getClass();
        FirProperty firPropertyPrepareProperty = prepareProperty(property);
        return getCallablesGenerator().createIrProperty(firPropertyPrepareProperty, irParent, getIrPropertySymbols(firPropertyPrepareProperty.getSymbol(), fakeOverrideOwnerLookupTag), predefinedOrigin, fakeOverrideOwnerLookupTag, allowLazyDeclarationsCreation);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final IrVariable createAndCacheIrVariable(FirVariable variable, IrDeclarationParent irParent, IrDeclarationOrigin givenOrigin) throws KotlinIllegalArgumentExceptionWithAttachments {
        variable.getClass();
        irParent.getClass();
        IrVariable irVariableCreateIrVariable = getCallablesGenerator().createIrVariable(variable, irParent, givenOrigin);
        getLocalStorage().putVariable(variable, irVariableCreateIrVariable.getSymbol());
        return irVariableCreateIrVariable;
    }

    public final IrValueParameter createAndCacheParameter(FirValueParameter valueParameter, boolean useStubForDefaultValueStub, ConversionTypeOrigin typeOrigin, boolean skipDefaultParameter, boolean forcedDefaultValueConversion) {
        valueParameter.getClass();
        typeOrigin.getClass();
        IrValueParameter irValueParameterCreateIrParameter$org_jetbrains_kotlin_fir2ir$default = Fir2IrCallableDeclarationsGenerator.createIrParameter$org_jetbrains_kotlin_fir2ir$default(getCallablesGenerator(), valueParameter, useStubForDefaultValueStub, typeOrigin, skipDefaultParameter, forcedDefaultValueConversion, null, 32, null);
        getLocalStorage().putParameter(valueParameter, irValueParameterCreateIrParameter$org_jetbrains_kotlin_fir2ir$default.getSymbol());
        return irValueParameterCreateIrParameter$org_jetbrains_kotlin_fir2ir$default;
    }

    public final IrSimpleFunctionSymbol createFunctionSymbol$org_jetbrains_kotlin_fir2ir() {
        return new IrSimpleFunctionSymbolImpl((FunctionDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final IrAnonymousInitializer createIrAnonymousInitializer(FirAnonymousInitializer anonymousInitializer, IrClass containingIrClass) throws KotlinIllegalArgumentExceptionWithAttachments {
        anonymousInitializer.getClass();
        containingIrClass.getClass();
        IrAnonymousInitializer irAnonymousInitializerCreateIrAnonymousInitializer = getCallablesGenerator().createIrAnonymousInitializer(anonymousInitializer, containingIrClass);
        if (this.initializerCache.put(anonymousInitializer, irAnonymousInitializerCreateIrAnonymousInitializer) == null) {
            return irAnonymousInitializerCreateIrAnonymousInitializer;
        }
        dt1.a("IR for anonymous initializer already exits: ", UtilsKt.render(anonymousInitializer));
        return null;
    }

    public final IrReplSnippet createIrReplSnippet(FirReplSnippet snippet) {
        snippet.getClass();
        if (getCachedIrReplSnippet(snippet) != null) {
            f2f.a("IrReplSnippet already created: ", UtilsKt.render(snippet));
            return null;
        }
        IrReplSnippet irReplSnippetCreateIrReplSnippet = getCallablesGenerator().createIrReplSnippet(snippet, new IrReplSnippetSymbolImpl((Void) null, (IdSignature) null, 3, (DefaultConstructorMarker) null));
        this.replSnippetCache.put(snippet, irReplSnippetCreateIrReplSnippet);
        return irReplSnippetCreateIrReplSnippet;
    }

    public final IrScript createIrScript(FirScript script) {
        script.getClass();
        if (getCachedIrScript(script) != null) {
            f2f.a("IrScript already created: ", UtilsKt.render(script));
            return null;
        }
        IrScript irScriptCreateIrScript = getCallablesGenerator().createIrScript(script, new IrScriptSymbolImpl((ScriptDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null));
        this.scriptCache.put(script, irScriptCreateIrScript);
        return irScriptCreateIrScript;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final IrField createSupertypeDelegateIrField$org_jetbrains_kotlin_fir2ir(FirField field, IrClass irClass) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeKotlinType coneType;
        field.getClass();
        irClass.getClass();
        IrFieldSymbol irFieldSymbolCreateFieldSymbol = createFieldSymbol();
        Fir2IrCallableDeclarationsGenerator callablesGenerator = getCallablesGenerator();
        FirExpression initializer = field.getInitializer();
        if (initializer == null || (coneType = FirTypeUtilsKt.getResolvedType(initializer)) == null) {
            coneType = FirTypeUtilsKt.getConeType(field.getReturnTypeRef());
        }
        IrField irFieldCreateIrField$org_jetbrains_kotlin_fir2ir = callablesGenerator.createIrField$org_jetbrains_kotlin_fir2ir(field, irClass, irFieldSymbolCreateFieldSymbol, coneType, IrDeclarationOrigin.Companion.getDELEGATE());
        this.fieldForDelegatedSupertypeCache.put(field, irFieldSymbolCreateFieldSymbol);
        return irFieldCreateIrField$org_jetbrains_kotlin_fir2ir;
    }

    public final void enterScope(IrSymbol symbol) {
        symbol.getClass();
        if ((symbol instanceof IrSimpleFunctionSymbol) || (symbol instanceof IrConstructorSymbol) || (symbol instanceof IrAnonymousInitializerSymbol) || (symbol instanceof IrPropertySymbol) || (symbol instanceof IrEnumEntrySymbol) || (symbol instanceof IrScriptSymbol) || (symbol instanceof IrReplSnippetSymbol)) {
            getLocalStorage().enterCallable();
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @LeakedDeclarationCaches
    public final void fillUnboundSymbols$org_jetbrains_kotlin_fir2ir() throws KotlinIllegalArgumentExceptionWithAttachments {
        fillUnboundSymbols(this.functionCache);
        fillUnboundSymbols(this.propertyCache.getNormal());
        ConcurrentHashMap<PropertyCacheStorage.SyntheticPropertyKey, IrPropertySymbol> synthetic = this.propertyCache.getSynthetic();
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(synthetic.size()));
        Iterator<T> it = synthetic.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            linkedHashMap.put(((PropertyCacheStorage.SyntheticPropertyKey) entry.getKey()).getOriginalFunction(), entry.getValue());
        }
        fillUnboundSymbols(linkedHashMap);
    }

    public final IrFieldSymbol findBackingFieldOfProperty(IrPropertySymbol propertySymbol) {
        propertySymbol.getClass();
        return this.backingFieldForPropertyCache.get(propertySymbol);
    }

    public final IrVariableSymbol findDelegateVariableOfProperty(IrLocalDelegatedPropertySymbol propertySymbol) {
        propertySymbol.getClass();
        return (IrVariableSymbol) MapsKt.getValue(this.delegateVariableForPropertyCache, propertySymbol);
    }

    public final IrSimpleFunctionSymbol findGetterOfProperty(IrPropertySymbol propertySymbol) {
        propertySymbol.getClass();
        IrSimpleFunctionSymbol irSimpleFunctionSymbol = this.getterForPropertyCache.get(propertySymbol);
        if (irSimpleFunctionSymbol != null) {
            return getSymbolsMappingForLazyClasses().remapFunctionSymbol(irSimpleFunctionSymbol);
        }
        return null;
    }

    public final IrDeclarationParent findIrParent$org_jetbrains_kotlin_fir2ir(FqName packageFqName, ConeClassLikeLookupTag parentLookupTag, FirBasedSymbol<?> firBasedSymbol, FirDeclarationOrigin firOrigin) {
        FirFile firClassifierContainerFileIfAny;
        IrDeclarationOrigin file_class;
        JvmClassName jvmClassNameByFqNameWithoutInnerClasses;
        packageFqName.getClass();
        firBasedSymbol.getClass();
        firOrigin.getClass();
        if (parentLookupTag != null) {
            IrClassSymbol irClassSymbol = getClassifierStorage().getIrClassSymbol(parentLookupTag);
            if (irClassSymbol != null) {
                return irClassSymbol.getOwner();
            }
            return null;
        }
        boolean z = firBasedSymbol instanceof FirCallableSymbol;
        IrExternalPackageFragment irExternalOrBuiltInsPackageFragment = getIrExternalOrBuiltInsPackageFragment(packageFqName, firBasedSymbol.getModuleData(), firOrigin, !z);
        FirProvider firProvider = (!getConfiguration().getAllowNonCachedDeclarations() || Intrinsics.areEqual(firBasedSymbol.getModuleData(), FirModuleDataKt.getModuleData(getSession()))) ? this.c.getFirProvider() : FirProviderKt.getFirProvider(firBasedSymbol.getModuleData().getSession());
        if (z) {
            firClassifierContainerFileIfAny = firProvider.getFirCallableContainerFile((FirCallableSymbol) firBasedSymbol);
        } else {
            if (!(firBasedSymbol instanceof FirClassLikeSymbol)) {
                w04.a("Unknown symbol: ", firBasedSymbol);
                return null;
            }
            firClassifierContainerFileIfAny = firProvider.getFirClassifierContainerFileIfAny((FirClassLikeSymbol<?>) firBasedSymbol);
        }
        if (firClassifierContainerFileIfAny != null) {
            IrFile irFile = this.fileCache.get(firClassifierContainerFileIfAny);
            if (irFile != null) {
                return irFile;
            }
            if (getConfiguration().getAllowNonCachedDeclarations() && z) {
                PsiElement psi = UtilsKt.getPsi(firClassifierContainerFileIfAny);
                PsiFile containingFile = psi != null ? psi.getContainingFile() : null;
                if (containingFile instanceof KtFile) {
                    JvmFileClassInfo fileClassInfoNoResolve = JvmFileClassUtil.getFileClassInfoNoResolve((KtFile) containingFile);
                    JvmClassName jvmClassNameByFqNameWithoutInnerClasses2 = JvmClassName.byFqNameWithoutInnerClasses(fileClassInfoNoResolve.getFileClassFqName());
                    jvmClassNameByFqNameWithoutInnerClasses2.getClass();
                    if (fileClassInfoNoResolve.getWithJvmMultifileClass()) {
                        jvmClassNameByFqNameWithoutInnerClasses = JvmClassName.byFqNameWithoutInnerClasses(fileClassInfoNoResolve.getFacadeClassFqName());
                        file_class = IrDeclarationOrigin.Companion.getJVM_MULTIFILE_CLASS();
                    } else {
                        file_class = IrDeclarationOrigin.Companion.getFILE_CLASS();
                        jvmClassNameByFqNameWithoutInnerClasses = null;
                    }
                    IrDeclarationOrigin irDeclarationOrigin = file_class;
                    Name nameShortName = jvmClassNameByFqNameWithoutInnerClasses2.getFqNameForClassNameWithoutDollars().shortName();
                    NonCachedSourceFacadeContainerSource nonCachedSourceFacadeContainerSource = new NonCachedSourceFacadeContainerSource(jvmClassNameByFqNameWithoutInnerClasses2, jvmClassNameByFqNameWithoutInnerClasses);
                    IrFactoryImpl irFactoryImpl = IrFactoryImpl.INSTANCE;
                    IrClassSymbolImpl irClassSymbolImpl = new IrClassSymbolImpl((ClassDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null);
                    ClassKind classKind = ClassKind.CLASS;
                    DescriptorVisibility descriptorVisibility = DescriptorVisibilities.PUBLIC;
                    descriptorVisibility.getClass();
                    IrClass irClassCreateClass$default = IrFactory.createClass$default(irFactoryImpl, -1, -1, irDeclarationOrigin, nameShortName, descriptorVisibility, irClassSymbolImpl, classKind, Modality.FINAL, false, false, false, false, false, false, false, false, nonCachedSourceFacadeContainerSource, 65280, (Object) null);
                    irClassCreateClass$default.setParent(irExternalOrBuiltInsPackageFragment);
                    IrUtilsKt.createThisReceiverParameter(irClassCreateClass$default);
                    Fir2IrDeclarationStorageKt.setNonCachedSourceFileFacade(irClassCreateClass$default, true);
                    return irClassCreateClass$default;
                }
            }
        }
        return irExternalOrBuiltInsPackageFragment;
    }

    public final IrSimpleFunctionSymbol findSetterOfProperty(IrPropertySymbol propertySymbol) {
        propertySymbol.getClass();
        IrSimpleFunctionSymbol irSimpleFunctionSymbol = this.setterForPropertyCache.get(propertySymbol);
        if (irSimpleFunctionSymbol != null) {
            return getSymbolsMappingForLazyClasses().remapFunctionSymbol(irSimpleFunctionSymbol);
        }
        return null;
    }

    @DelicateDeclarationStorageApi
    public final void forEachCachedDeclarationSymbol(Function1<? super IrSymbol, Unit> block) {
        block.getClass();
        Collection<IrSimpleFunctionSymbol> collectionValues = this.functionCache.values();
        collectionValues.getClass();
        for (IrSymbol irSymbol : collectionValues) {
            if (!(irSymbol instanceof IrFakeOverrideSymbolBase)) {
                block.invoke(irSymbol);
            }
        }
        Collection<IrConstructorSymbol> collectionValues2 = this.constructorCache.values();
        collectionValues2.getClass();
        Iterator<T> it = collectionValues2.iterator();
        while (it.hasNext()) {
            block.invoke(it.next());
        }
        Collection<IrPropertySymbol> collectionValues3 = this.propertyCache.getNormal().values();
        collectionValues3.getClass();
        for (IrSymbol irSymbol2 : collectionValues3) {
            if (!(irSymbol2 instanceof IrFakeOverrideSymbolBase)) {
                block.invoke(irSymbol2);
            }
        }
        Collection<IrPropertySymbol> collectionValues4 = this.propertyCache.getSynthetic().values();
        collectionValues4.getClass();
        for (IrSymbol irSymbol3 : collectionValues4) {
            if (!(irSymbol3 instanceof IrFakeOverrideSymbolBase)) {
                block.invoke(irSymbol3);
            }
        }
        Collection<IrSimpleFunctionSymbol> collectionValues5 = this.getterForPropertyCache.values();
        collectionValues5.getClass();
        for (IrSymbol irSymbol4 : collectionValues5) {
            if (!(irSymbol4 instanceof IrFakeOverrideSymbolBase)) {
                block.invoke(irSymbol4);
            }
        }
        Collection<IrSimpleFunctionSymbol> collectionValues6 = this.setterForPropertyCache.values();
        collectionValues6.getClass();
        for (IrSymbol irSymbol5 : collectionValues6) {
            if (!(irSymbol5 instanceof IrFakeOverrideSymbolBase)) {
                block.invoke(irSymbol5);
            }
        }
        Collection<IrFieldSymbol> collectionValues7 = this.backingFieldForPropertyCache.values();
        collectionValues7.getClass();
        Iterator<T> it2 = collectionValues7.iterator();
        while (it2.hasNext()) {
            block.invoke(it2.next());
        }
        Collection<IrPropertySymbol> collectionValues8 = this.propertyForBackingFieldCache.values();
        collectionValues8.getClass();
        Iterator<T> it3 = collectionValues8.iterator();
        while (it3.hasNext()) {
            block.invoke(it3.next());
        }
        Collection<IrVariableSymbol> collectionValues9 = this.delegateVariableForPropertyCache.values();
        collectionValues9.getClass();
        Iterator<T> it4 = collectionValues9.iterator();
        while (it4.hasNext()) {
            block.invoke(it4.next());
        }
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

    public final IrConstructorSymbol getCachedIrConstructorSymbol(FirConstructor constructor) {
        constructor.getClass();
        return this.constructorCache.get(constructor);
    }

    public final IrFieldSymbol getCachedIrFieldSymbolForSupertypeDelegateField(FirField field) {
        field.getClass();
        return this.fieldForDelegatedSupertypeCache.get(field);
    }

    /* JADX WARN: Code duplicated, block: B:52:0x00e0  */
    public final IrSimpleFunctionSymbol getCachedIrFunctionSymbol(FirNamedFunction function, ConeClassLikeLookupTag fakeOverrideOwnerLookupTag) {
        IrSimpleFunctionSymbol irSimpleFunctionSymbol;
        IrSimpleFunctionSymbol toStringSymbol;
        function.getClass();
        if (Intrinsics.areEqual(function.getStatus().getVisibility(), Visibilities.Local.INSTANCE)) {
            return getLocalStorage().getLocalFunctionSymbol(function);
        }
        if (function.getOrigin().getGeneratedAnyMethod()) {
            FirRegularClass containingClass = ResolveUtilsKt.getContainingClass(function);
            containingClass.getClass();
            ConcurrentHashMap<FirClass, DataClassGeneratedFunctionsStorage> concurrentHashMap = this.dataClassGeneratedFunctionsCache;
            final Function1 function1 = new Function1() { // from class: hv4
                public final Object invoke(Object obj) {
                    return Fir2IrDeclarationStorage.getCachedIrFunctionSymbol$lambda$0$0((FirClass) obj);
                }
            };
            DataClassGeneratedFunctionsStorage dataClassGeneratedFunctionsStorageComputeIfAbsent = concurrentHashMap.computeIfAbsent(containingClass, new Function() { // from class: iv4
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Fir2IrDeclarationStorage.getCachedIrFunctionSymbol$lambda$0$1(function1, obj);
                }
            });
            dataClassGeneratedFunctionsStorageComputeIfAbsent.getClass();
            DataClassGeneratedFunctionsStorage dataClassGeneratedFunctionsStorage = dataClassGeneratedFunctionsStorageComputeIfAbsent;
            Name nameOrSpecialName = FirDeclarationUtilKt.getNameOrSpecialName(function);
            if (Intrinsics.areEqual(nameOrSpecialName, OperatorNameConventions.EQUALS)) {
                toStringSymbol = dataClassGeneratedFunctionsStorage.getEqualsSymbol();
            } else if (Intrinsics.areEqual(nameOrSpecialName, OperatorNameConventions.HASH_CODE)) {
                toStringSymbol = dataClassGeneratedFunctionsStorage.getHashCodeSymbol();
            } else if (Intrinsics.areEqual(nameOrSpecialName, OperatorNameConventions.TO_STRING)) {
                toStringSymbol = dataClassGeneratedFunctionsStorage.getToStringSymbol();
            }
            if (toStringSymbol != null) {
                return getSymbolsMappingForLazyClasses().remapFunctionSymbol(toStringSymbol);
            }
            return null;
        }
        ConcurrentHashMap<FirFunction, IrSimpleFunctionSymbol> concurrentHashMap2 = this.functionCache;
        if (Fir2IrDeclarationStorageKt.isFakeOverrideOrDelegated(function, fakeOverrideOwnerLookupTag)) {
            FakeOverrideIdentifier.Companion companion = FakeOverrideIdentifier.INSTANCE;
            FirCallableDeclaration firCallableDeclaration = function;
            while (true) {
                FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                if (originalForSubstitutionOverrideAttr == null) {
                    originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
                    if (originalForSubstitutionOverrideAttr == null) {
                        DelegatedWrapperData delegatedWrapperData = ClassMembersKt.getDelegatedWrapperData(firCallableDeclaration);
                        originalForSubstitutionOverrideAttr = delegatedWrapperData != null ? delegatedWrapperData.getWrapped() : null;
                    }
                }
                if (originalForSubstitutionOverrideAttr == null) {
                    break;
                }
                firCallableDeclaration = originalForSubstitutionOverrideAttr;
            }
            FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
            if (fakeOverrideOwnerLookupTag == null) {
                fakeOverrideOwnerLookupTag = ClassMembersKt.containingClassLookupTag(function);
                fakeOverrideOwnerLookupTag.getClass();
            }
            IrSymbol irSymbol = this.irForFirSessionDependantDeclarationMap.get(companion.invoke(this, symbol, fakeOverrideOwnerLookupTag));
            if (irSymbol != null) {
                irSimpleFunctionSymbol = (IrSimpleFunctionSymbol) irSymbol;
            } else {
                irSimpleFunctionSymbol = null;
            }
        } else {
            irSimpleFunctionSymbol = concurrentHashMap2.get(function);
            if (irSimpleFunctionSymbol == null) {
                irSimpleFunctionSymbol = null;
            }
        }
        if (irSimpleFunctionSymbol != null) {
            return getSymbolsMappingForLazyClasses().remapFunctionSymbol(irSimpleFunctionSymbol);
        }
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:33:0x006d  */
    public final IrPropertySymbol getCachedIrPropertySymbol(FirProperty property, ConeClassLikeLookupTag fakeOverrideOwnerLookupTag) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrPropertySymbol irPropertySymbol;
        property.getClass();
        FirProperty firPropertyPrepareProperty = prepareProperty(property);
        PropertyCacheStorage propertyCacheStorage = this.propertyCache;
        if (Fir2IrDeclarationStorageKt.isFakeOverrideOrDelegated(firPropertyPrepareProperty, fakeOverrideOwnerLookupTag)) {
            FakeOverrideIdentifier.Companion companion = FakeOverrideIdentifier.INSTANCE;
            FirCallableDeclaration firCallableDeclaration = firPropertyPrepareProperty;
            while (true) {
                FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                if (originalForSubstitutionOverrideAttr == null) {
                    originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
                    if (originalForSubstitutionOverrideAttr == null) {
                        DelegatedWrapperData delegatedWrapperData = ClassMembersKt.getDelegatedWrapperData(firCallableDeclaration);
                        originalForSubstitutionOverrideAttr = delegatedWrapperData != null ? delegatedWrapperData.getWrapped() : null;
                    }
                }
                if (originalForSubstitutionOverrideAttr == null) {
                    break;
                }
                firCallableDeclaration = originalForSubstitutionOverrideAttr;
            }
            FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
            if (fakeOverrideOwnerLookupTag == null) {
                fakeOverrideOwnerLookupTag = ClassMembersKt.containingClassLookupTag(firPropertyPrepareProperty);
                fakeOverrideOwnerLookupTag.getClass();
            }
            IrSymbol irSymbol = this.irForFirSessionDependantDeclarationMap.get(companion.invoke(this, symbol, fakeOverrideOwnerLookupTag));
            if (irSymbol != null) {
                irPropertySymbol = (IrPropertySymbol) irSymbol;
            } else {
                irPropertySymbol = null;
            }
        } else {
            irPropertySymbol = propertyCacheStorage.get(firPropertyPrepareProperty);
            if (irPropertySymbol == null) {
                irPropertySymbol = null;
            }
        }
        if (irPropertySymbol == null) {
            return null;
        }
        return getSymbolsMappingForLazyClasses().remapPropertySymbol(irPropertySymbol);
    }

    public final IrReplSnippet getCachedIrReplSnippet(FirReplSnippet snippet) {
        snippet.getClass();
        return this.replSnippetCache.get(snippet);
    }

    public final IrScript getCachedIrScript(FirScript script) {
        script.getClass();
        return this.scriptCache.get(script);
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

    public final FirModuleDescriptor getDependenciesModuleDescriptor(FirModuleData moduleData) {
        moduleData.getClass();
        ConcurrentHashMap<FirModuleData, FirModuleDescriptor> concurrentHashMap = this.moduleDescriptorCache;
        FirModuleDescriptor firModuleDescriptor = concurrentHashMap.get(moduleData);
        if (firModuleDescriptor == null) {
            FirModuleDescriptor firModuleDescriptorCreateDependencyModuleDescriptor = FirModuleDescriptor.INSTANCE.createDependencyModuleDescriptor(moduleData, this.sourceModuleDescriptor.getBuiltIns());
            FirModuleDescriptor firModuleDescriptorPutIfAbsent = concurrentHashMap.putIfAbsent(moduleData, firModuleDescriptorCreateDependencyModuleDescriptor);
            firModuleDescriptor = firModuleDescriptorPutIfAbsent == null ? firModuleDescriptorCreateDependencyModuleDescriptor : firModuleDescriptorPutIfAbsent;
        }
        firModuleDescriptor.getClass();
        return firModuleDescriptor;
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

    public final IrAnonymousInitializer getIrAnonymousInitializer(FirAnonymousInitializer anonymousInitializer) {
        anonymousInitializer.getClass();
        return (IrAnonymousInitializer) MapsKt.getValue(this.initializerCache, anonymousInitializer);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final IrSymbol getIrBackingFieldSymbol(FirBackingFieldSymbol firBackingFieldSymbol) {
        firBackingFieldSymbol.getClass();
        return getIrPropertyForwardedSymbol((FirProperty) ((FirBackingField) firBackingFieldSymbol.getFir()).getPropertySymbol().getFir());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final IrSymbol getIrBackingFieldSymbol$org_jetbrains_kotlin_fir2ir(FirPropertySymbol firPropertySymbol) {
        firPropertySymbol.getClass();
        return getIrPropertyForwardedSymbol((FirProperty) firPropertySymbol.getFir());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    public final IrConstructorSymbol getIrConstructorSymbol(FirConstructorSymbol firConstructorSymbol, boolean potentiallyExternal) throws KotlinIllegalArgumentExceptionWithAttachments {
        firConstructorSymbol.getClass();
        FirConstructor firConstructor = (FirConstructor) firConstructorSymbol.getFir();
        IrConstructorSymbol cachedIrConstructorSymbol = getCachedIrConstructorSymbol(firConstructor);
        if (cachedIrConstructorSymbol != null) {
            return cachedIrConstructorSymbol;
        }
        IrConstructorSymbolImpl irConstructorSymbolImpl = new IrConstructorSymbolImpl((ClassConstructorDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null);
        cacheIrConstructorSymbol(firConstructor, irConstructorSymbolImpl);
        if (potentiallyExternal) {
            IrDeclarationParent irDeclarationParentFindIrParent$org_jetbrains_kotlin_fir2ir = findIrParent$org_jetbrains_kotlin_fir2ir(firConstructor, null);
            if (Fir2IrCallableDeclarationsGeneratorKt.isExternalParent(irDeclarationParentFindIrParent$org_jetbrains_kotlin_fir2ir)) {
                Fir2IrCallableDeclarationsGenerator callablesGenerator = getCallablesGenerator();
                irDeclarationParentFindIrParent$org_jetbrains_kotlin_fir2ir.getClass();
                if (!(callablesGenerator.createIrConstructor(firConstructor, (IrClass) irDeclarationParentFindIrParent$org_jetbrains_kotlin_fir2ir, irConstructorSymbolImpl, computeExternalOrigin(firConstructor), true) instanceof Fir2IrLazyConstructor)) {
                    k2d.a("Check failed.");
                    return null;
                }
            }
        }
        return irConstructorSymbolImpl;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final IrSymbol getIrDelegateFieldSymbol(FirDelegateFieldSymbol delegateFieldSymbol) {
        delegateFieldSymbol.getClass();
        return getIrPropertyForwardedSymbol((FirProperty) delegateFieldSymbol.getFir());
    }

    public final IrExternalPackageFragment getIrExternalPackageFragment(FqName fqName, FirModuleData moduleData, FirDeclarationOrigin firOrigin) {
        fqName.getClass();
        moduleData.getClass();
        firOrigin.getClass();
        return getIrExternalOrBuiltInsPackageFragment(fqName, moduleData, firOrigin, false);
    }

    public final IrFile getIrFile(FirFile firFile) {
        firFile.getClass();
        IrFile irFile = this.fileCache.get(firFile);
        irFile.getClass();
        return irFile;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final IrFunctionSymbol getIrFunctionSymbol(FirFunctionSymbol<?> firFunctionSymbol, ConeClassLikeLookupTag fakeOverrideOwnerLookupTag, boolean isLocal) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrDeclarationParent irDeclarationParentFindIrParent$org_jetbrains_kotlin_fir2ir;
        FirFunction firFunctionCreateFirFunctionFakeOverrideIfNeeded$org_jetbrains_kotlin_fir2ir;
        firFunctionSymbol.getClass();
        FirFunction firFunction = (FirFunction) firFunctionSymbol.getFir();
        if (firFunction instanceof FirConstructor) {
            return getIrConstructorSymbol$default(this, ((FirConstructor) firFunction).getSymbol(), false, 2, null);
        }
        IrSimpleFunctionSymbol cachedIrFunctionSymbol = getCachedIrFunctionSymbol(firFunction, fakeOverrideOwnerLookupTag);
        if (cachedIrFunctionSymbol != null) {
            return cachedIrFunctionSymbol;
        }
        if (!(firFunction instanceof FirNamedFunction) || isLocal || (irDeclarationParentFindIrParent$org_jetbrains_kotlin_fir2ir = findIrParent$org_jetbrains_kotlin_fir2ir(firFunction, fakeOverrideOwnerLookupTag)) == null || !Fir2IrCallableDeclarationsGeneratorKt.isExternalParent(irDeclarationParentFindIrParent$org_jetbrains_kotlin_fir2ir)) {
            IrSimpleFunctionSymbol irSimpleFunctionSymbolCreateMemberFunctionSymbol = createMemberFunctionSymbol(firFunction, fakeOverrideOwnerLookupTag, false);
            cacheIrFunctionSymbol(firFunction, irSimpleFunctionSymbolCreateMemberFunctionSymbol, fakeOverrideOwnerLookupTag);
            return irSimpleFunctionSymbolCreateMemberFunctionSymbol;
        }
        IrSimpleFunctionSymbol irSimpleFunctionSymbolCreateMemberFunctionSymbol2 = createMemberFunctionSymbol(firFunction, fakeOverrideOwnerLookupTag, true);
        Fir2IrLazyFakeOverrideGenerator lazyFakeOverrideGenerator = getLazyFakeOverrideGenerator();
        if (fakeOverrideOwnerLookupTag == null || (firFunctionCreateFirFunctionFakeOverrideIfNeeded$org_jetbrains_kotlin_fir2ir = lazyFakeOverrideGenerator.createFirFunctionFakeOverrideIfNeeded$org_jetbrains_kotlin_fir2ir((FirNamedFunction) firFunction, fakeOverrideOwnerLookupTag)) == null) {
            firFunctionCreateFirFunctionFakeOverrideIfNeeded$org_jetbrains_kotlin_fir2ir = firFunction;
        }
        FirNamedFunction firNamedFunction = (FirNamedFunction) firFunctionCreateFirFunctionFakeOverrideIfNeeded$org_jetbrains_kotlin_fir2ir;
        if (getCallablesGenerator().createIrFunction(firNamedFunction, irDeclarationParentFindIrParent$org_jetbrains_kotlin_fir2ir, irSimpleFunctionSymbolCreateMemberFunctionSymbol2, computeExternalOrigin(firNamedFunction), false, fakeOverrideOwnerLookupTag, true) instanceof Fir2IrLazySimpleFunction) {
            cacheIrFunctionSymbol(firFunction, irSimpleFunctionSymbolCreateMemberFunctionSymbol2, fakeOverrideOwnerLookupTag);
            return irSimpleFunctionSymbolCreateMemberFunctionSymbol2;
        }
        k2d.a("Check failed.");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public KotlinMangler.IrMangler getIrMangler() {
        return this.c.getIrMangler();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    public final IrSymbol getIrPropertySymbol(FirPropertySymbol firPropertySymbol, ConeClassLikeLookupTag fakeOverrideOwnerLookupTag) throws KotlinIllegalArgumentExceptionWithAttachments {
        firPropertySymbol.getClass();
        FirProperty firPropertyPrepareProperty = prepareProperty((FirProperty) firPropertySymbol.getFir());
        if (firPropertyPrepareProperty.getSymbol() instanceof FirLocalPropertySymbol) {
            IrLocalDelegatedPropertySymbol delegatedProperty = getLocalStorage().getDelegatedProperty(firPropertyPrepareProperty);
            return delegatedProperty != null ? delegatedProperty : getIrVariableSymbol(firPropertyPrepareProperty);
        }
        IrPropertySymbol cachedIrPropertySymbol = getCachedIrPropertySymbol(firPropertyPrepareProperty, fakeOverrideOwnerLookupTag);
        return cachedIrPropertySymbol != null ? cachedIrPropertySymbol : getIrPropertySymbols(firPropertySymbol, fakeOverrideOwnerLookupTag).getPropertySymbol();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public List<IrProvider> getIrProviders() {
        return this.c.getIrProviders();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    public final IrSymbol getIrSymbolForField(FirFieldSymbol firFieldSymbol, ConeClassLikeLookupTag fakeOverrideOwnerLookupTag) throws KotlinIllegalArgumentExceptionWithAttachments {
        firFieldSymbol.getClass();
        FirField firField = (FirField) firFieldSymbol.getFir();
        FirCallableDeclaration firCallableDeclaration = firField;
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
            }
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        FirField firField2 = (FirField) firCallableDeclaration;
        if (fakeOverrideOwnerLookupTag == null) {
            fakeOverrideOwnerLookupTag = firField != firField2 ? ClassMembersKt.dispatchReceiverClassLookupTagOrNull(firField) : null;
        }
        if (Intrinsics.areEqual(firField2.getOrigin(), FirDeclarationOrigin.Synthetic.DelegateField.INSTANCE)) {
            return getIrSymbolForSupertypeDelegateField(firField2, fakeOverrideOwnerLookupTag);
        }
        if (DeclarationUtilsKt.isJavaOrEnhancement(firField2)) {
            return getIrPropertySymbolForJavaField(firField, fakeOverrideOwnerLookupTag);
        }
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Unknown field kind. Only java fields and fields for supertype delegation are supported: " + UtilsKt.render(firField), (Throwable) null);
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "field", firField);
        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "originalField", firField2);
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final IrSymbol getIrValueSymbol(FirVariableSymbol<?> firVariableSymbol) {
        IrValueParameterSymbol parameter;
        firVariableSymbol.getClass();
        FirVariable firVariable = (FirVariable) firVariableSymbol.getFir();
        if (firVariable instanceof FirEnumEntry) {
            return getClassifierStorage().getIrEnumEntrySymbol((FirEnumEntry) firVariable);
        }
        return (!(firVariable instanceof FirValueParameter) || (parameter = getLocalStorage().getParameter((FirValueParameter) firVariable)) == null) ? getIrVariableSymbol(firVariable) : parameter;
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

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final void leaveScope(IrSymbol symbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        symbol.getClass();
        if ((symbol instanceof IrSimpleFunctionSymbol) || (symbol instanceof IrConstructorSymbol) || (symbol instanceof IrAnonymousInitializerSymbol) || (symbol instanceof IrPropertySymbol) || (symbol instanceof IrEnumEntrySymbol) || (symbol instanceof IrScriptSymbol) || (symbol instanceof IrReplSnippetSymbol)) {
            if (getConfiguration().getAllowNonCachedDeclarations()) {
                fillUnboundSymbols(getLocalStorage().getLastCache().getLocalFunctions());
                getExtensions().preserveLocalScope(symbol, getLocalStorage().getLastCache());
            }
            getLocalStorage().leaveCallable();
        }
    }

    public final FirDeclaration originalDeclarationForDelegated(IrDeclaration irDeclaration) {
        irDeclaration.getClass();
        return this.delegatedReverseCache.get(irDeclaration.getSymbol());
    }

    public final <T extends IrFunction> T putParametersInScope(T t, FirFunction firFunction) {
        t.getClass();
        firFunction.getClass();
        List<FirValueParameter> listContextParametersForFunctionOrContainingProperty = FirDeclarationUtilKt.contextParametersForFunctionOrContainingProperty(firFunction);
        List parameters = t.getParameters();
        ArrayList arrayList = new ArrayList();
        for (Object obj : parameters) {
            if (((IrValueParameter) obj).getKind() == IrParameterKind.Context) {
                arrayList.add(obj);
            }
        }
        for (Pair pair : CollectionsKt.zip(listContextParametersForFunctionOrContainingProperty, arrayList)) {
            getLocalStorage().putParameter((FirValueParameter) pair.component1(), ((IrValueParameter) pair.component2()).getSymbol());
        }
        List<FirValueParameter> valueParameters = firFunction.getValueParameters();
        List parameters2 = t.getParameters();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : parameters2) {
            if (((IrValueParameter) obj2).getKind() == IrParameterKind.Regular) {
                arrayList2.add(obj2);
            }
        }
        for (Pair pair2 : CollectionsKt.zip(valueParameters, arrayList2)) {
            getLocalStorage().putParameter((FirValueParameter) pair2.component1(), ((IrValueParameter) pair2.component2()).getSymbol());
        }
        return t;
    }

    public final void recordSupertypeDelegateFieldMappedToBackingField(FirField field, IrFieldSymbol irFieldSymbol) {
        field.getClass();
        irFieldSymbol.getClass();
        this.fieldForDelegatedSupertypeCache.put(field, irFieldSymbol);
    }

    public final void recordSupertypeDelegationInformation(FirClass containingFirClass, IrClass irClass, IrType superType, IrFieldSymbol irFieldSymbol) {
        containingFirClass.getClass();
        irClass.getClass();
        superType.getClass();
        irFieldSymbol.getClass();
        Map<IrClassSymbol, Map<IrClassSymbol, IrFieldSymbol>> map = this.delegatedClassesMap;
        IrClassSymbol symbol = irClass.getSymbol();
        Map<IrClassSymbol, IrFieldSymbol> linkedHashMap = map.get(symbol);
        if (linkedHashMap == null) {
            linkedHashMap = new LinkedHashMap<>();
            map.put(symbol, linkedHashMap);
        }
        Map<IrClassSymbol, IrFieldSymbol> map2 = linkedHashMap;
        IrClassSymbol classOrNull = IrTypesKt.getClassOrNull(superType);
        if (classOrNull == null) {
            if (this.c.getConfiguration().getSkipBodies()) {
                return;
            }
            w04.a("No symbol for type ", superType);
        } else if (map2.containsKey(classOrNull)) {
            wec.a("Delegate info for supertype ", superType, " already stored");
        } else {
            map2.put(classOrNull, irFieldSymbol);
            this.firClassesWithInheritanceByDelegation.add(containingFirClass);
        }
    }

    public final void registerFile(FirFile firFile, IrFile irFile) {
        firFile.getClass();
        irFile.getClass();
        this.fileCache.put(firFile, irFile);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final void withScope(IrSymbol symbol, Function0<Unit> block) throws KotlinIllegalArgumentExceptionWithAttachments {
        symbol.getClass();
        block.getClass();
        enterScope(symbol);
        block.invoke();
        leaveScope(symbol);
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB#\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\r\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J+\u0010\u0013\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage$FakeOverrideIdentifier;", Argument.Delimiters.none, "originalSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "dispatchReceiverLookupTag", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "parentIsExpect", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;Z)V", "getOriginalSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "getDispatchReceiverLookupTag", "()Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "getParentIsExpect", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "Companion", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class FakeOverrideIdentifier {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final ConeClassLikeLookupTag dispatchReceiverLookupTag;
        private final FirCallableSymbol<?> originalSymbol;
        private final boolean parentIsExpect;

        public FakeOverrideIdentifier(FirCallableSymbol<?> firCallableSymbol, ConeClassLikeLookupTag coneClassLikeLookupTag, boolean z) {
            firCallableSymbol.getClass();
            coneClassLikeLookupTag.getClass();
            this.originalSymbol = firCallableSymbol;
            this.dispatchReceiverLookupTag = coneClassLikeLookupTag;
            this.parentIsExpect = z;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ FakeOverrideIdentifier copy$default(FakeOverrideIdentifier fakeOverrideIdentifier, FirCallableSymbol firCallableSymbol, ConeClassLikeLookupTag coneClassLikeLookupTag, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                firCallableSymbol = fakeOverrideIdentifier.originalSymbol;
            }
            if ((i & 2) != 0) {
                coneClassLikeLookupTag = fakeOverrideIdentifier.dispatchReceiverLookupTag;
            }
            if ((i & 4) != 0) {
                z = fakeOverrideIdentifier.parentIsExpect;
            }
            return fakeOverrideIdentifier.copy(firCallableSymbol, coneClassLikeLookupTag, z);
        }

        public final FirCallableSymbol<?> component1() {
            return this.originalSymbol;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final ConeClassLikeLookupTag getDispatchReceiverLookupTag() {
            return this.dispatchReceiverLookupTag;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getParentIsExpect() {
            return this.parentIsExpect;
        }

        public final FakeOverrideIdentifier copy(FirCallableSymbol<?> originalSymbol, ConeClassLikeLookupTag dispatchReceiverLookupTag, boolean parentIsExpect) {
            originalSymbol.getClass();
            dispatchReceiverLookupTag.getClass();
            return new FakeOverrideIdentifier(originalSymbol, dispatchReceiverLookupTag, parentIsExpect);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FakeOverrideIdentifier)) {
                return false;
            }
            FakeOverrideIdentifier fakeOverrideIdentifier = (FakeOverrideIdentifier) other;
            return Intrinsics.areEqual(this.originalSymbol, fakeOverrideIdentifier.originalSymbol) && Intrinsics.areEqual(this.dispatchReceiverLookupTag, fakeOverrideIdentifier.dispatchReceiverLookupTag) && this.parentIsExpect == fakeOverrideIdentifier.parentIsExpect;
        }

        public final ConeClassLikeLookupTag getDispatchReceiverLookupTag() {
            return this.dispatchReceiverLookupTag;
        }

        public final FirCallableSymbol<?> getOriginalSymbol() {
            return this.originalSymbol;
        }

        public final boolean getParentIsExpect() {
            return this.parentIsExpect;
        }

        public int hashCode() {
            return (((this.originalSymbol.hashCode() * 31) + this.dispatchReceiverLookupTag.hashCode()) * 31) + Boolean.hashCode(this.parentIsExpect);
        }

        public String toString() {
            return "FakeOverrideIdentifier(originalSymbol=" + this.originalSymbol + ", dispatchReceiverLookupTag=" + this.dispatchReceiverLookupTag + ", parentIsExpect=" + this.parentIsExpect + ')';
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u0004\u001a\u00020\u00052\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0002R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage$FakeOverrideIdentifier$Companion;", Argument.Delimiters.none, "<init>", "()V", "invoke", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage$FakeOverrideIdentifier;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "c", "originalSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "dispatchReceiverLookupTag", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;)Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage$FakeOverrideIdentifier;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final FakeOverrideIdentifier invoke(Fir2IrComponents fir2IrComponents, FirCallableSymbol<?> firCallableSymbol, ConeClassLikeLookupTag coneClassLikeLookupTag) {
                fir2IrComponents.getClass();
                firCallableSymbol.getClass();
                coneClassLikeLookupTag.getClass();
                FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) fir2IrComponents, coneClassLikeLookupTag);
                boolean z = false;
                if (regularClassSymbol != null && regularClassSymbol.getRawStatus().isExpect()) {
                    z = true;
                }
                return new FakeOverrideIdentifier(firCallableSymbol, coneClassLikeLookupTag, z);
            }

            private Companion() {
            }
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R%\u0010\u0004\u001a\u0013\u0012\t\u0012\u00070\u0006¢\u0006\u0002\b\u0007\u0012\u0004\u0012\u00020\b0\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage$Companion;", Argument.Delimiters.none, "<init>", "()V", "ENUM_SYNTHETIC_NAMES", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "Lkotlin/jvm/internal/EnhancedNullability;", "Lorg/jetbrains/kotlin/ir/expressions/IrSyntheticBodyKind;", "getENUM_SYNTHETIC_NAMES$org_jetbrains_kotlin_fir2ir", "()Ljava/util/Map;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Map<Name, IrSyntheticBodyKind> getENUM_SYNTHETIC_NAMES$org_jetbrains_kotlin_fir2ir() {
            return Fir2IrDeclarationStorage.ENUM_SYNTHETIC_NAMES;
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u001d\u001a\u00020\u001eH\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0016\u0010\u000b\u001a\u0004\u0018\u00010\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001b\u001a\u0004\u0018\u00010\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\t¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage$NonCachedSourceFacadeContainerSource;", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "Lorg/jetbrains/kotlin/load/kotlin/FacadeClassSource;", "className", "Lorg/jetbrains/kotlin/resolve/jvm/JvmClassName;", "facadeClassName", "<init>", "(Lorg/jetbrains/kotlin/resolve/jvm/JvmClassName;Lorg/jetbrains/kotlin/resolve/jvm/JvmClassName;)V", "getClassName", "()Lorg/jetbrains/kotlin/resolve/jvm/JvmClassName;", "getFacadeClassName", "incompatibility", Argument.Delimiters.none, "getIncompatibility", "()Ljava/lang/Void;", "preReleaseInfo", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/PreReleaseInfo;", "getPreReleaseInfo", "()Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/PreReleaseInfo;", "abiStability", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerAbiStability;", "getAbiStability", "()Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerAbiStability;", "presentableString", Argument.Delimiters.none, "getPresentableString", "()Ljava/lang/String;", "jvmClassName", "getJvmClassName", "getContainingFile", "Lorg/jetbrains/kotlin/descriptors/SourceFile;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class NonCachedSourceFacadeContainerSource implements FacadeClassSource, DeserializedContainerSource {
        private final JvmClassName className;
        private final JvmClassName facadeClassName;

        public NonCachedSourceFacadeContainerSource(JvmClassName jvmClassName, JvmClassName jvmClassName2) {
            jvmClassName.getClass();
            this.className = jvmClassName;
            this.facadeClassName = jvmClassName2;
        }

        @Override // org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource
        public DeserializedContainerAbiStability getAbiStability() {
            return DeserializedContainerAbiStability.STABLE;
        }

        public JvmClassName getClassName() {
            return this.className;
        }

        @Override // org.jetbrains.kotlin.descriptors.SourceElement
        public SourceFile getContainingFile() {
            SourceFile sourceFile = SourceFile.NO_SOURCE_FILE;
            sourceFile.getClass();
            return sourceFile;
        }

        public JvmClassName getFacadeClassName() {
            return this.facadeClassName;
        }

        public JvmClassName getJvmClassName() {
            return null;
        }

        @Override // org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource
        public PreReleaseInfo getPreReleaseInfo() {
            return PreReleaseInfo.Companion.getDEFAULT_VISIBLE();
        }

        @Override // org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource
        public String getPresentableString() {
            String internalName = getClassName().getInternalName();
            internalName.getClass();
            return internalName;
        }

        @Override // org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource
        /* JADX INFO: renamed from: getIncompatibility, reason: merged with bridge method [inline-methods] */
        public Void mo247getIncompatibility() {
            return null;
        }
    }

    public static /* synthetic */ IrSimpleFunctionSymbol getCachedIrFunctionSymbol$default(Fir2IrDeclarationStorage fir2IrDeclarationStorage, FirNamedFunction firNamedFunction, ConeClassLikeLookupTag coneClassLikeLookupTag, int i, Object obj) {
        if ((i & 2) != 0) {
            coneClassLikeLookupTag = null;
        }
        return fir2IrDeclarationStorage.getCachedIrFunctionSymbol(firNamedFunction, coneClassLikeLookupTag);
    }

    public final IrSimpleFunctionSymbol findGetterOfProperty(IrLocalDelegatedPropertySymbol propertySymbol) {
        propertySymbol.getClass();
        return (IrSimpleFunctionSymbol) MapsKt.getValue(this.getterForPropertyCache, propertySymbol);
    }

    public final IrSimpleFunctionSymbol findSetterOfProperty(IrLocalDelegatedPropertySymbol propertySymbol) {
        propertySymbol.getClass();
        return this.setterForPropertyCache.get(propertySymbol);
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\u0005\u0010\tJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage$FirSyntheticPropertyKey;", Argument.Delimiters.none, "originalForGetter", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "originalForSetter", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;)V", "property", "Lorg/jetbrains/kotlin/fir/declarations/synthetic/FirSyntheticProperty;", "(Lorg/jetbrains/kotlin/fir/declarations/synthetic/FirSyntheticProperty;)V", "getOriginalForGetter", "()Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "getOriginalForSetter", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class FirSyntheticPropertyKey {
        private final FirNamedFunction originalForGetter;
        private final FirNamedFunction originalForSetter;

        /* JADX WARN: Illegal instructions before constructor call */
        public FirSyntheticPropertyKey(FirSyntheticProperty firSyntheticProperty) {
            firSyntheticProperty.getClass();
            FirNamedFunction delegate = firSyntheticProperty.getGetter().getDelegate();
            FirSyntheticPropertyAccessor setter = firSyntheticProperty.getSetter();
            this(delegate, setter != null ? setter.getDelegate() : null);
        }

        public static /* synthetic */ FirSyntheticPropertyKey copy$default(FirSyntheticPropertyKey firSyntheticPropertyKey, FirNamedFunction firNamedFunction, FirNamedFunction firNamedFunction2, int i, Object obj) {
            if ((i & 1) != 0) {
                firNamedFunction = firSyntheticPropertyKey.originalForGetter;
            }
            if ((i & 2) != 0) {
                firNamedFunction2 = firSyntheticPropertyKey.originalForSetter;
            }
            return firSyntheticPropertyKey.copy(firNamedFunction, firNamedFunction2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final FirNamedFunction getOriginalForGetter() {
            return this.originalForGetter;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final FirNamedFunction getOriginalForSetter() {
            return this.originalForSetter;
        }

        public final FirSyntheticPropertyKey copy(FirNamedFunction originalForGetter, FirNamedFunction originalForSetter) {
            originalForGetter.getClass();
            return new FirSyntheticPropertyKey(originalForGetter, originalForSetter);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FirSyntheticPropertyKey)) {
                return false;
            }
            FirSyntheticPropertyKey firSyntheticPropertyKey = (FirSyntheticPropertyKey) other;
            return Intrinsics.areEqual(this.originalForGetter, firSyntheticPropertyKey.originalForGetter) && Intrinsics.areEqual(this.originalForSetter, firSyntheticPropertyKey.originalForSetter);
        }

        public final FirNamedFunction getOriginalForGetter() {
            return this.originalForGetter;
        }

        public final FirNamedFunction getOriginalForSetter() {
            return this.originalForSetter;
        }

        public int hashCode() {
            int iHashCode = this.originalForGetter.hashCode() * 31;
            FirNamedFunction firNamedFunction = this.originalForSetter;
            return iHashCode + (firNamedFunction == null ? 0 : firNamedFunction.hashCode());
        }

        public String toString() {
            return "FirSyntheticPropertyKey(originalForGetter=" + this.originalForGetter + ", originalForSetter=" + this.originalForSetter + ')';
        }

        public FirSyntheticPropertyKey(FirNamedFunction firNamedFunction, FirNamedFunction firNamedFunction2) {
            firNamedFunction.getClass();
            this.originalForGetter = firNamedFunction;
            this.originalForSetter = firNamedFunction2;
        }
    }

    public final IrSimpleFunctionSymbol getCachedIrFunctionSymbol(FirFunction function, ConeClassLikeLookupTag fakeOverrideOwnerLookupTag) {
        function.getClass();
        return function instanceof FirNamedFunction ? getCachedIrFunctionSymbol((FirNamedFunction) function, fakeOverrideOwnerLookupTag) : getLocalStorage().getLocalFunctionSymbol(function);
    }

    public final IrDeclarationParent findIrParent$org_jetbrains_kotlin_fir2ir(FirCallableDeclaration callableDeclaration, ConeClassLikeLookupTag fakeOverrideOwnerLookupTag) {
        callableDeclaration.getClass();
        FirCallableSymbol<FirCallableDeclaration> symbol = callableDeclaration.getSymbol();
        CallableId callableId = symbol.getCallableId();
        FirDeclarationOrigin origin = callableDeclaration.getOrigin();
        if (fakeOverrideOwnerLookupTag == null) {
            fakeOverrideOwnerLookupTag = ClassMembersKt.containingClassLookupTag(callableDeclaration);
        }
        callableId.getClass();
        return findIrParent$org_jetbrains_kotlin_fir2ir(callableId.getPackageName(), fakeOverrideOwnerLookupTag, symbol, origin);
    }
}

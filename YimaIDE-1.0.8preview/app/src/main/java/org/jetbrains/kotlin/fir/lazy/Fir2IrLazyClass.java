package org.jetbrains.kotlin.fir.lazy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.backend.common.IrSpecialAnnotationsProvider;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.state.InlineClassManglingUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.descriptors.ValueClassRepresentation;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirVisibilityCheckerKt;
import org.jetbrains.kotlin.fir.backend.Fir2IrBuiltinSymbolsContainer;
import org.jetbrains.kotlin.fir.backend.Fir2IrClassifierStorage;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.backend.Fir2IrConfiguration;
import org.jetbrains.kotlin.fir.backend.Fir2IrConverter;
import org.jetbrains.kotlin.fir.backend.Fir2IrDeclarationStorage;
import org.jetbrains.kotlin.fir.backend.Fir2IrExtensions;
import org.jetbrains.kotlin.fir.backend.Fir2IrImplicitCastInserter;
import org.jetbrains.kotlin.fir.backend.Fir2IrIrGeneratedDeclarationsRegistrar;
import org.jetbrains.kotlin.fir.backend.Fir2IrSymbolsMappingForLazyClasses;
import org.jetbrains.kotlin.fir.backend.Fir2IrTypeConverter;
import org.jetbrains.kotlin.fir.backend.Fir2IrTypeConverterKt;
import org.jetbrains.kotlin.fir.backend.Fir2IrVisibilityConverter;
import org.jetbrains.kotlin.fir.backend.FirProviderWithGeneratedFiles;
import org.jetbrains.kotlin.fir.backend.generators.AdapterGenerator;
import org.jetbrains.kotlin.fir.backend.generators.AnnotationGenerator;
import org.jetbrains.kotlin.fir.backend.generators.CallAndReferenceGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrCallableDeclarationsGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrClassifiersGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrDataClassMembersGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrLazyDeclarationsGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrLazyDeclarationsGeneratorKt;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrLazyFakeOverrideGenerator;
import org.jetbrains.kotlin.fir.backend.utils.ConversionTypeOrigin;
import org.jetbrains.kotlin.fir.backend.utils.IrElementsCreationUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.VariousUtilsKt;
import org.jetbrains.kotlin.fir.declarations.DeprecationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.lazy.Fir2IrLazyClass;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.FirScopeProviderKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.IrLock;
import org.jetbrains.kotlin.ir.IrProvider;
import org.jetbrains.kotlin.ir.ObsoleteDescriptorBasedAPI;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.ir.declarations.IrTypeParameter;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.declarations.MetadataSource;
import org.jetbrains.kotlin.ir.declarations.lazy.IrLazyClassBase;
import org.jetbrains.kotlin.ir.declarations.lazy.LazyUtilKt;
import org.jetbrains.kotlin.ir.expressions.IrAnnotation;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.UnsafeDuringIrConstructionAPI;
import org.jetbrains.kotlin.ir.types.IrSimpleType;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.util.KotlinMangler;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¾\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\u00020\u00042\u00020\u00052\u00020\u0006B?\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\u0012\u0010\u0090\u0001\u001a\u00020^2\u0007\u0010\r\u001a\u00030\u0091\u0001H\u0002J\u0013\u0010\u0092\u0001\u001a\u00020^2\b\u0010\u0093\u0001\u001a\u00030\u0094\u0001H\u0002R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\n\u001a\u00020\tX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0015\"\u0004\b\u0019\u0010\u0017R\u001a\u0010\u000b\u001a\u00020\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0014\u0010\r\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R7\u0010%\u001a\b\u0012\u0004\u0012\u00020$0#2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#8V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b*\u0010+\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R \u0010,\u001a\b\u0012\u0004\u0012\u00020-0#X\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010'\"\u0004\b/\u0010)R\u0014\u00100\u001a\u0002018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b2\u00103R\u001e\u00104\u001a\u0002058VX\u0097\u0004r\u0002\b:¢\u0006\f\u0012\u0004\b6\u00107\u001a\u0004\b8\u00109R$\u0010=\u001a\u00020<2\u0006\u0010;\u001a\u00020<8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u001b\u0010B\u001a\u00020C8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bF\u0010G\u001a\u0004\bD\u0010ER$\u0010H\u001a\u00020C2\u0006\u0010;\u001a\u00020C8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bI\u0010E\"\u0004\bJ\u0010KR$\u0010M\u001a\u00020L2\u0006\u0010;\u001a\u00020L8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR\u001a\u0010R\u001a\u00020SX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR$\u0010Y\u001a\u00020X2\u0006\u0010;\u001a\u00020X8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]R$\u0010_\u001a\u00020^2\u0006\u0010;\u001a\u00020^8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b_\u0010`\"\u0004\ba\u0010bR$\u0010c\u001a\u00020^2\u0006\u0010;\u001a\u00020^8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bc\u0010`\"\u0004\bd\u0010bR$\u0010e\u001a\u00020^2\u0006\u0010;\u001a\u00020^8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\be\u0010`\"\u0004\bf\u0010bR$\u0010g\u001a\u00020^2\u0006\u0010;\u001a\u00020^8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bg\u0010`\"\u0004\bh\u0010bR$\u0010i\u001a\u00020^2\u0006\u0010;\u001a\u00020^8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bi\u0010`\"\u0004\bj\u0010bR$\u0010k\u001a\u00020^2\u0006\u0010;\u001a\u00020^8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bk\u0010`\"\u0004\bl\u0010bR$\u0010m\u001a\u00020^2\u0006\u0010;\u001a\u00020^8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bm\u0010`\"\u0004\bn\u0010bR$\u0010o\u001a\u00020^2\u0006\u0010;\u001a\u00020^8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bp\u0010`\"\u0004\bq\u0010bR7\u0010s\u001a\b\u0012\u0004\u0012\u00020r0#2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020r0#8V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\bv\u0010+\u001a\u0004\bt\u0010'\"\u0004\bu\u0010)R7\u0010w\u001a\b\u0012\u0004\u0012\u00020\u000f0#2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000f0#8V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\bz\u0010+\u001a\u0004\bx\u0010'\"\u0004\by\u0010)R1\u0010|\u001a\u0004\u0018\u00010{2\b\u0010\"\u001a\u0004\u0018\u00010{8V@VX\u0096\u008e\u0002¢\u0006\u0014\n\u0005\b\u0081\u0001\u0010+\u001a\u0004\b}\u0010~\"\u0005\b\u007f\u0010\u0080\u0001R=\u0010\u0084\u0001\u001a\f\u0012\u0005\u0012\u00030\u0083\u0001\u0018\u00010\u0082\u00012\u0010\u0010;\u001a\f\u0012\u0005\u0012\u00030\u0083\u0001\u0018\u00010\u0082\u00018V@VX\u0096\u000e¢\u0006\u0010\u001a\u0006\b\u0085\u0001\u0010\u0086\u0001\"\u0006\b\u0087\u0001\u0010\u0088\u0001R2\u0010\u0089\u0001\u001a\n\u0012\u0005\u0012\u00030\u008b\u00010\u008a\u00018VX\u0097\u0084\u0002r\u0003\b\u008f\u0001¢\u0006\u0015\n\u0005\b\u008e\u0001\u0010+\u0012\u0005\b\u008c\u0001\u00107\u001a\u0005\b\u008d\u0001\u0010'R/\u0010\u0096\u0001\u001a\u0005\u0018\u00010\u0095\u00012\t\u0010;\u001a\u0005\u0018\u00010\u0095\u00018V@VX\u0096\u000e¢\u0006\u0010\u001a\u0006\b\u0097\u0001\u0010\u0098\u0001\"\u0006\b\u0099\u0001\u0010\u009a\u0001R\u001a\u0010\u009b\u0001\u001a\u0005\u0018\u00010\u009c\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u009d\u0001\u0010\u009e\u0001R\u0017\u0010\u009f\u0001\u001a\u00020^8VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u009f\u0001\u0010 \u0001R\u0016\u0010¡\u0001\u001a\u00020^8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b¡\u0001\u0010`R\u0016\u0010¢\u0001\u001a\u00030£\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¤\u0001\u0010¥\u0001R\u0016\u0010¦\u0001\u001a\u00030§\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¨\u0001\u0010©\u0001R\u0016\u0010ª\u0001\u001a\u00030«\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¬\u0001\u0010\u00ad\u0001R\u0016\u0010®\u0001\u001a\u00030¯\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b°\u0001\u0010±\u0001R\u0016\u0010²\u0001\u001a\u00030³\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b´\u0001\u0010µ\u0001R\u0016\u0010¶\u0001\u001a\u00030·\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¸\u0001\u0010¹\u0001R\u0016\u0010º\u0001\u001a\u00030»\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¼\u0001\u0010½\u0001R\u0016\u0010¾\u0001\u001a\u00030¿\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÀ\u0001\u0010Á\u0001R\u0016\u0010Â\u0001\u001a\u00030Ã\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÄ\u0001\u0010Å\u0001R\u0016\u0010Æ\u0001\u001a\u00030Ç\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÈ\u0001\u0010É\u0001R\u0016\u0010Ê\u0001\u001a\u00030Ë\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÌ\u0001\u0010Í\u0001R\u0016\u0010Î\u0001\u001a\u00030Ï\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÐ\u0001\u0010Ñ\u0001R\u0016\u0010Ò\u0001\u001a\u00030Ó\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÔ\u0001\u0010Õ\u0001R\u001f\u0010Ö\u0001\u001a\f\u0012\u0005\u0012\u00030Ø\u0001\u0018\u00010×\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÙ\u0001\u0010Ú\u0001R\u0016\u0010Û\u0001\u001a\u00030Ü\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÝ\u0001\u0010Þ\u0001R\u0016\u0010ß\u0001\u001a\u00030à\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bá\u0001\u0010â\u0001R\u0016\u0010ã\u0001\u001a\u00030ä\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bå\u0001\u0010æ\u0001R\u001b\u0010ç\u0001\u001a\t\u0012\u0005\u0012\u00030è\u00010#X\u0096\u0005¢\u0006\u0007\u001a\u0005\bé\u0001\u0010'R\u0016\u0010ê\u0001\u001a\u00030ë\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bì\u0001\u0010í\u0001R\u0016\u0010î\u0001\u001a\u00030ï\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bð\u0001\u0010ñ\u0001R\u0016\u0010ò\u0001\u001a\u00030ó\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bô\u0001\u0010õ\u0001R\u0016\u0010ö\u0001\u001a\u00030÷\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bø\u0001\u0010ù\u0001R\u0016\u0010ú\u0001\u001a\u00030û\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bü\u0001\u0010ý\u0001R\u0018\u0010þ\u0001\u001a\u0005\u0018\u00010ÿ\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0080\u0002\u0010\u0081\u0002R\u0016\u0010\u0082\u0002\u001a\u00030\u0083\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0084\u0002\u0010\u0085\u0002R\u0016\u0010\u0086\u0002\u001a\u00030\u0087\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0088\u0002\u0010\u0089\u0002R\u0016\u0010\u008a\u0002\u001a\u00030\u008b\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\b\u008c\u0002\u0010\u008d\u0002¨\u0006\u008e\u0002"}, d2 = {"Lorg/jetbrains/kotlin/fir/lazy/Fir2IrLazyClass;", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "Lorg/jetbrains/kotlin/fir/lazy/AbstractFir2IrLazyDeclaration;", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/fir/lazy/Fir2IrTypeParametersContainer;", "Lorg/jetbrains/kotlin/ir/declarations/lazy/IrLazyClassBase;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "c", "startOffset", Argument.Delimiters.none, "endOffset", "origin", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "fir", "symbol", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "parent", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationParent;", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;IILorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationParent;)V", "getStartOffset", "()I", "setStartOffset", "(I)V", "getEndOffset", "setEndOffset", "getOrigin", "()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "setOrigin", "(Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;)V", "getFir", "()Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "getSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "<set-?>", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/expressions/IrAnnotation;", "annotations", "getAnnotations", "()Ljava/util/List;", "setAnnotations", "(Ljava/util/List;)V", "annotations$delegate", "Lkotlin/properties/ReadWriteProperty;", "typeParameters", "Lorg/jetbrains/kotlin/ir/declarations/IrTypeParameter;", "getTypeParameters", "setTypeParameters", "source", "Lorg/jetbrains/kotlin/descriptors/SourceElement;", "getSource", "()Lorg/jetbrains/kotlin/descriptors/SourceElement;", "descriptor", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "getDescriptor$annotations", "()V", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "Lorg/jetbrains/kotlin/ir/ObsoleteDescriptorBasedAPI;", InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER, "Lorg/jetbrains/kotlin/name/Name;", ModuleXmlParser.NAME, "getName", "()Lorg/jetbrains/kotlin/name/Name;", "setName", "(Lorg/jetbrains/kotlin/name/Name;)V", "_visibility", "Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "get_visibility", "()Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "_visibility$delegate", "Lkotlin/Lazy;", "visibility", "getVisibility", "setVisibility", "(Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;)V", "Lorg/jetbrains/kotlin/descriptors/Modality;", "modality", "getModality", "()Lorg/jetbrains/kotlin/descriptors/Modality;", "setModality", "(Lorg/jetbrains/kotlin/descriptors/Modality;)V", "attributeOwnerId", "Lorg/jetbrains/kotlin/ir/IrElement;", "getAttributeOwnerId", "()Lorg/jetbrains/kotlin/ir/IrElement;", "setAttributeOwnerId", "(Lorg/jetbrains/kotlin/ir/IrElement;)V", "Lorg/jetbrains/kotlin/descriptors/ClassKind;", "kind", "getKind", "()Lorg/jetbrains/kotlin/descriptors/ClassKind;", "setKind", "(Lorg/jetbrains/kotlin/descriptors/ClassKind;)V", Argument.Delimiters.none, "isCompanion", "()Z", "setCompanion", "(Z)V", "isInner", "setInner", "isData", "setData", "isExternal", "setExternal", "isValue", "setValue", "isExpect", "setExpect", "isFun", "setFun", "hasEnumEntries", "getHasEnumEntries", "setHasEnumEntries", "Lorg/jetbrains/kotlin/ir/types/IrType;", "superTypes", "getSuperTypes", "setSuperTypes", "superTypes$delegate", "sealedSubclasses", "getSealedSubclasses", "setSealedSubclasses", "sealedSubclasses$delegate", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "thisReceiver", "getThisReceiver", "()Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "setThisReceiver", "(Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;)V", "thisReceiver$delegate", "Lorg/jetbrains/kotlin/descriptors/ValueClassRepresentation;", "Lorg/jetbrains/kotlin/ir/types/IrSimpleType;", "valueClassRepresentation", "getValueClassRepresentation", "()Lorg/jetbrains/kotlin/descriptors/ValueClassRepresentation;", "setValueClassRepresentation", "(Lorg/jetbrains/kotlin/descriptors/ValueClassRepresentation;)V", "declarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "getDeclarations$annotations", "getDeclarations", "declarations$delegate", "Lorg/jetbrains/kotlin/ir/symbols/UnsafeDuringIrConstructionAPI;", "shouldBuildStub", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "shouldBuildIrField", "fieldSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFieldSymbol;", "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource;", "metadata", "getMetadata", "()Lorg/jetbrains/kotlin/ir/declarations/MetadataSource;", "setMetadata", "(Lorg/jetbrains/kotlin/ir/declarations/MetadataSource;)V", "moduleName", Argument.Delimiters.none, "getModuleName", "()Ljava/lang/String;", "isNewPlaceForBodyGeneration", "()Ljava/lang/Boolean;", "isK2", "adapterGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "getAdapterGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "annotationGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "getAnnotationGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "annotationsFromPluginRegistrar", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "getAnnotationsFromPluginRegistrar", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "builtins", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "getBuiltins", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "callGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "getCallGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "callablesGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "getCallablesGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "classifierStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "getClassifierStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "classifiersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "getClassifiersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "configuration", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "getConfiguration", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "converter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "getConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "dataClassMembersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "getDataClassMembersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "declarationStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "getDeclarationStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "extensions", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "getExtensions", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "filesBeingCompiled", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "getFilesBeingCompiled", "()Ljava/util/Set;", "firProvider", "Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "getFirProvider", "()Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "implicitCastInserter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "getImplicitCastInserter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "irMangler", "Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "getIrMangler", "()Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "irProviders", "Lorg/jetbrains/kotlin/ir/IrProvider;", "getIrProviders", "lazyDeclarationsGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "getLazyDeclarationsGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "lazyFakeOverrideGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "getLazyFakeOverrideGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "lock", "Lorg/jetbrains/kotlin/ir/IrLock;", "getLock", "()Lorg/jetbrains/kotlin/ir/IrLock;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "specialAnnotationsProvider", "Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "getSpecialAnnotationsProvider", "()Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "symbolsMappingForLazyClasses", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "getSymbolsMappingForLazyClasses", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "typeConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "getTypeConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "visibilityConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "getVisibilityConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrLazyClass extends IrClass implements Fir2IrComponents, AbstractFir2IrLazyDeclaration<FirRegularClass>, Fir2IrTypeParametersContainer, IrLazyClassBase {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(Fir2IrLazyClass.class, "annotations", "getAnnotations()Ljava/util/List;", 0), new MutablePropertyReference1Impl<>(Fir2IrLazyClass.class, "superTypes", "getSuperTypes()Ljava/util/List;", 0), new MutablePropertyReference1Impl<>(Fir2IrLazyClass.class, "sealedSubclasses", "getSealedSubclasses()Ljava/util/List;", 0), new MutablePropertyReference1Impl<>(Fir2IrLazyClass.class, "thisReceiver", "getThisReceiver()Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", 0), new PropertyReference1Impl<>(Fir2IrLazyClass.class, "declarations", "getDeclarations()Ljava/util/List;", 0)};

    /* JADX INFO: renamed from: _visibility$delegate, reason: from kotlin metadata */
    private final Lazy _visibility;

    /* JADX INFO: renamed from: annotations$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty annotations;
    private IrElement attributeOwnerId;
    private final Fir2IrComponents c;

    /* JADX INFO: renamed from: declarations$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty declarations;
    private int endOffset;
    private final FirRegularClass fir;
    private IrDeclarationOrigin origin;

    /* JADX INFO: renamed from: sealedSubclasses$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty sealedSubclasses;
    private int startOffset;

    /* JADX INFO: renamed from: superTypes$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty superTypes;
    private final IrClassSymbol symbol;

    /* JADX INFO: renamed from: thisReceiver$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty thisReceiver;
    public List<? extends IrTypeParameter> typeParameters;

    public Fir2IrLazyClass(Fir2IrComponents fir2IrComponents, int i, int i2, IrDeclarationOrigin irDeclarationOrigin, FirRegularClass firRegularClass, IrClassSymbol irClassSymbol, IrDeclarationParent irDeclarationParent) {
        fir2IrComponents.getClass();
        irDeclarationOrigin.getClass();
        firRegularClass.getClass();
        irClassSymbol.getClass();
        irDeclarationParent.getClass();
        this.c = fir2IrComponents;
        this.startOffset = i;
        this.endOffset = i2;
        this.origin = irDeclarationOrigin;
        this.fir = firRegularClass;
        this.symbol = irClassSymbol;
        setParent(irDeclarationParent);
        m558getSymbol().bind(this);
        getClassifierStorage().preCacheTypeParameters$org_jetbrains_kotlin_fir2ir(getFir());
        this.annotations = createLazyAnnotations();
        this._visibility = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: uv4
            public final Object invoke() {
                return Fir2IrLazyClass.l(this.b);
            }
        });
        this.attributeOwnerId = this;
        this.superTypes = LazyUtilKt.lazyVar(getLock(), new Function0() { // from class: vv4
            public final Object invoke() {
                return Fir2IrLazyClass.j(this.b);
            }
        });
        this.sealedSubclasses = LazyUtilKt.lazyVar(getLock(), new Function0() { // from class: wv4
            public final Object invoke() {
                return Fir2IrLazyClass.f(this.b);
            }
        });
        this.thisReceiver = LazyUtilKt.lazyVar(getLock(), new Function0() { // from class: xv4
            public final Object invoke() {
                return Fir2IrLazyClass.m(this.b);
            }
        });
        this.declarations = LazyUtilKt.lazyVar(getLock(), new Function0() { // from class: yv4
            public final Object invoke() {
                return Fir2IrLazyClass.e(this.b);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit declarations_delegate$lambda$0$0(Fir2IrLazyClass fir2IrLazyClass, List list, FirConstructorSymbol firConstructorSymbol) {
        firConstructorSymbol.getClass();
        FirConstructor firConstructor = (FirConstructor) firConstructorSymbol.getFir();
        if (fir2IrLazyClass.shouldBuildStub(firConstructor)) {
            list.add(Fir2IrDeclarationStorage.getIrConstructorSymbol$default(fir2IrLazyClass.getDeclarationStorage(), firConstructor.getSymbol(), false, 2, null).getOwner());
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit declarations_delegate$lambda$0$1(Fir2IrLazyClass fir2IrLazyClass, List list, FirClassifierSymbol firClassifierSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        firClassifierSymbol.getClass();
        E fir = firClassifierSymbol.getFir();
        FirRegularClass firRegularClass = fir instanceof FirRegularClass ? (FirRegularClass) fir : null;
        if (firRegularClass == null) {
            return Unit.INSTANCE;
        }
        if (Intrinsics.areEqual(FirDeclarationUtilKt.getClassId(firRegularClass).getOuterClassId(), FirDeclarationUtilKt.getClassId(fir2IrLazyClass.getFir())) && fir2IrLazyClass.shouldBuildStub(firRegularClass)) {
            list.add(fir2IrLazyClass.getClassifierStorage().getIrClassSymbol(firRegularClass.getSymbol()).getOwner());
        }
        return Unit.INSTANCE;
    }

    private static final void declarations_delegate$lambda$0$addDeclarationsFromScope(final Fir2IrLazyClass fir2IrLazyClass, final List<IrDeclaration> list, final ConeClassLikeLookupTag coneClassLikeLookupTag, FirContainingNamesAwareScope firContainingNamesAwareScope) {
        if (firContainingNamesAwareScope == null) {
            return;
        }
        for (Name name : firContainingNamesAwareScope.getCallableNames()) {
            firContainingNamesAwareScope.processFunctionsByName(name, new Function1() { // from class: qv4
                public final Object invoke(Object obj) {
                    return Fir2IrLazyClass.declarations_delegate$lambda$0$addDeclarationsFromScope$2(this.b, list, coneClassLikeLookupTag, (FirNamedFunctionSymbol) obj);
                }
            });
            firContainingNamesAwareScope.processPropertiesByName(name, new Function1() { // from class: rv4
                public final Object invoke(Object obj) {
                    return Fir2IrLazyClass.declarations_delegate$lambda$0$addDeclarationsFromScope$3(this.b, list, coneClassLikeLookupTag, (FirVariableSymbol) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit declarations_delegate$lambda$0$addDeclarationsFromScope$2(Fir2IrLazyClass fir2IrLazyClass, List list, ConeClassLikeLookupTag coneClassLikeLookupTag, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        if (fir2IrLazyClass.shouldBuildStub(firNamedFunctionSymbol.getFir())) {
            list.add(Fir2IrDeclarationStorage.getIrFunctionSymbol$default(fir2IrLazyClass.getDeclarationStorage(), firNamedFunctionSymbol, coneClassLikeLookupTag, false, 4, null).getOwner());
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit declarations_delegate$lambda$0$addDeclarationsFromScope$3(Fir2IrLazyClass fir2IrLazyClass, List list, ConeClassLikeLookupTag coneClassLikeLookupTag, FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        if (fir2IrLazyClass.shouldBuildStub(firVariableSymbol.getFir())) {
            if (firVariableSymbol instanceof FirFieldSymbol) {
                FirFieldSymbol firFieldSymbol = (FirFieldSymbol) firVariableSymbol;
                if (fir2IrLazyClass.shouldBuildIrField(firFieldSymbol)) {
                    IrProperty owner = fir2IrLazyClass.getDeclarationStorage().getIrSymbolForField(firFieldSymbol, coneClassLikeLookupTag).getOwner();
                    owner.getClass();
                    list.add(owner);
                }
            } else if (firVariableSymbol instanceof FirPropertySymbol) {
                IrProperty owner2 = fir2IrLazyClass.getDeclarationStorage().getIrPropertySymbol((FirPropertySymbol) firVariableSymbol, coneClassLikeLookupTag).getOwner();
                owner2.getClass();
                list.add(owner2);
            }
        }
        return Unit.INSTANCE;
    }

    public static List e(final Fir2IrLazyClass fir2IrLazyClass) {
        final ArrayList arrayList = new ArrayList();
        FirTypeScope firTypeScopeUnsubstitutedScope = ScopeUtilsKt.unsubstitutedScope(fir2IrLazyClass, fir2IrLazyClass.getFir());
        ConeClassLikeLookupTag lookupTag = fir2IrLazyClass.getFir().getSymbol().getLookupTag();
        firTypeScopeUnsubstitutedScope.processDeclaredConstructors(new Function1() { // from class: sv4
            public final Object invoke(Object obj) {
                return Fir2IrLazyClass.declarations_delegate$lambda$0$0(this.b, arrayList, (FirConstructorSymbol) obj);
            }
        });
        for (Name name : firTypeScopeUnsubstitutedScope.getClassifierNames()) {
            final Function1 function1 = new Function1() { // from class: tv4
                public final Object invoke(Object obj) {
                    return Fir2IrLazyClass.declarations_delegate$lambda$0$1(this.b, arrayList, (FirClassifierSymbol) obj);
                }
            };
            firTypeScopeUnsubstitutedScope.processClassifiersByNameWithSubstitution(name, new Function2<FirClassifierSymbol<?>, ConeSubstitutor, Unit>() { // from class: org.jetbrains.kotlin.fir.lazy.Fir2IrLazyClass$declarations_delegate$lambda$0$$inlined$processClassifiersByName$1
                public final void invoke(FirClassifierSymbol<?> firClassifierSymbol, ConeSubstitutor coneSubstitutor) {
                    firClassifierSymbol.getClass();
                    coneSubstitutor.getClass();
                    function1.invoke(firClassifierSymbol);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((FirClassifierSymbol<?>) obj, (ConeSubstitutor) obj2);
                    return Unit.INSTANCE;
                }
            });
        }
        if (fir2IrLazyClass.getFir().getClassKind() == ClassKind.ENUM_CLASS) {
            for (FirDeclaration firDeclaration : fir2IrLazyClass.getFir().getDeclarations()) {
                if ((firDeclaration instanceof FirEnumEntry) && fir2IrLazyClass.shouldBuildStub(firDeclaration)) {
                    arrayList.add(fir2IrLazyClass.getClassifierStorage().getIrEnumEntrySymbol((FirEnumEntry) firDeclaration).getOwner());
                }
            }
        }
        declarations_delegate$lambda$0$addDeclarationsFromScope(fir2IrLazyClass, arrayList, lookupTag, firTypeScopeUnsubstitutedScope);
        declarations_delegate$lambda$0$addDeclarationsFromScope(fir2IrLazyClass, arrayList, lookupTag, FirScopeProviderKt.staticScopeForBackend(fir2IrLazyClass.getFir(), fir2IrLazyClass.getSession(), fir2IrLazyClass.getScopeSession()));
        return arrayList;
    }

    public static List f(Fir2IrLazyClass fir2IrLazyClass) {
        return fir2IrLazyClass.getFir().getStatus().getModality() == Modality.SEALED ? VariousUtilsKt.getIrSymbolsForSealedSubclasses(fir2IrLazyClass, fir2IrLazyClass.getFir()) : CollectionsKt.emptyList();
    }

    @UnsafeDuringIrConstructionAPI
    public static /* synthetic */ void getDeclarations$annotations() {
    }

    @ObsoleteDescriptorBasedAPI
    public static /* synthetic */ void getDescriptor$annotations() {
    }

    private final DescriptorVisibility get_visibility() {
        Object value = this._visibility.getValue();
        value.getClass();
        return (DescriptorVisibility) value;
    }

    public static List j(Fir2IrLazyClass fir2IrLazyClass) {
        List<FirTypeRef> superTypeRefs = fir2IrLazyClass.getFir().getSuperTypeRefs();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(superTypeRefs, 10));
        Iterator<T> it = superTypeRefs.iterator();
        while (it.hasNext()) {
            arrayList.add(Fir2IrTypeConverterKt.toIrType$default(fir2IrLazyClass, (FirTypeRef) it.next(), (ConversionTypeOrigin) null, 2, (Object) null));
        }
        return arrayList;
    }

    public static DescriptorVisibility l(Fir2IrLazyClass fir2IrLazyClass) {
        return Intrinsics.areEqual(fir2IrLazyClass.getOrigin(), IrDeclarationOrigin.Companion.getREPL_FROM_OTHER_SNIPPET()) ? DescriptorVisibilities.PUBLIC : fir2IrLazyClass.c.getVisibilityConverter().convertToDescriptorVisibility(fir2IrLazyClass.getFir().getStatus().getVisibility());
    }

    public static IrValueParameter m(Fir2IrLazyClass fir2IrLazyClass) {
        IrElementsCreationUtilsKt.setThisReceiver(fir2IrLazyClass, fir2IrLazyClass, fir2IrLazyClass.getFir().getTypeParameters());
        return fir2IrLazyClass.getThisReceiver();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean shouldBuildIrField(FirFieldSymbol fieldSymbol) {
        if (fieldSymbol.getRawStatus().isStatic()) {
            return (getFir().getOrigin() instanceof FirDeclarationOrigin.Java) && !Fir2IrLazyDeclarationsGeneratorKt.isFakeOverride((FirCallableDeclaration) fieldSymbol.getFir(), getFir());
        }
        return true;
    }

    private final boolean shouldBuildStub(FirDeclaration fir) {
        Modality modality;
        if (fir instanceof FirCallableDeclaration) {
            FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) fir;
            FirCallableDeclaration firCallableDeclaration2 = firCallableDeclaration;
            while (ClassMembersKt.isSubstitutionOrIntersectionOverride(firCallableDeclaration2)) {
                FirCallableDeclaration originalForIntersectionOverrideAttr = null;
                FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration2) || (firCallableDeclaration2.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration2) : null;
                if (originalForSubstitutionOverrideAttr != null) {
                    originalForIntersectionOverrideAttr = originalForSubstitutionOverrideAttr;
                } else if (ClassMembersKt.isIntersectionOverride(firCallableDeclaration2)) {
                    originalForIntersectionOverrideAttr = ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration2);
                }
                if (originalForIntersectionOverrideAttr == null) {
                    break;
                }
                firCallableDeclaration2 = originalForIntersectionOverrideAttr;
            }
            if (Intrinsics.areEqual(firCallableDeclaration2.getOrigin(), FirDeclarationOrigin.Synthetic.FakeHiddenInPreparationForNewJdk.INSTANCE)) {
                return false;
            }
            if (Intrinsics.areEqual(DeprecationUtilsKt.isHiddenToOvercomeSignatureClash(firCallableDeclaration), Boolean.TRUE) && ((modality = ((FirMemberDeclaration) fir).getStatus().getModality()) == null || modality == Modality.FINAL)) {
                return false;
            }
        }
        if (!(fir instanceof FirMemberDeclaration)) {
            return true;
        }
        if (fir instanceof FirConstructor) {
            return IrUtilsKt.isObject(this) || IrUtilsKt.isEnumClass(this) || !Visibilities.INSTANCE.isPrivate(((FirMemberDeclaration) fir).getStatus().getVisibility());
        }
        if (fir instanceof FirCallableDeclaration) {
            FirCallableDeclaration firCallableDeclaration3 = (FirCallableDeclaration) fir;
            if (Fir2IrLazyDeclarationsGeneratorKt.isFakeOverride(firCallableDeclaration3, getFir())) {
                return FirVisibilityCheckerKt.getVisibilityChecker(getSession()).isVisibleForOverriding(getFir().getModuleData(), getFir().getSymbol(), firCallableDeclaration3);
            }
        }
        return !Visibilities.INSTANCE.isPrivate(((FirMemberDeclaration) fir).getStatus().getVisibility()) || getConfiguration().getPropagateLazyIrPrivateMembers();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public AdapterGenerator getAdapterGenerator() {
        return this.c.getAdapterGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public AnnotationGenerator getAnnotationGenerator() {
        return this.c.getAnnotationGenerator();
    }

    public List<IrAnnotation> getAnnotations() {
        return (List) this.annotations.getValue(this, $$delegatedProperties[0]);
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrIrGeneratedDeclarationsRegistrar getAnnotationsFromPluginRegistrar() {
        return this.c.getAnnotationsFromPluginRegistrar();
    }

    public IrElement getAttributeOwnerId() {
        return this.attributeOwnerId;
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

    public List<IrDeclaration> getDeclarations() {
        return (List) this.declarations.getValue(this, $$delegatedProperties[4]);
    }

    /* JADX INFO: renamed from: getDescriptor, reason: merged with bridge method [inline-methods] */
    public ClassDescriptor m557getDescriptor() {
        return m558getSymbol().getDescriptor();
    }

    public int getEndOffset() {
        return this.endOffset;
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

    public boolean getHasEnumEntries() {
        return ClassMembersKt.getHasEnumEntries(getFir());
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

    public ClassKind getKind() {
        return getFir().getClassKind();
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

    public MetadataSource getMetadata() {
        return null;
    }

    public Modality getModality() {
        return getFir().getClassKind() == ClassKind.ANNOTATION_CLASS ? Modality.OPEN : getFir().getSymbol().getResolvedStatus().getModality();
    }

    public String getModuleName() {
        String moduleName = DeclarationAttributesKt.getModuleName(getFir());
        if (moduleName != null) {
            return moduleName;
        }
        String stableModuleName = getFir().getModuleData().getStableModuleName();
        if (stableModuleName == null) {
            return null;
        }
        if (StringsKt.startsWith$default(stableModuleName, "<", false, 2, (Object) null) && StringsKt.endsWith$default(stableModuleName, ">", false, 2, (Object) null)) {
            return stableModuleName.substring(1, stableModuleName.length() - 1);
        }
        wec.a("Stable module name is expected to be wrapped in '<>' brackets, but got `", stableModuleName, "` instead");
        return null;
    }

    public Name getName() {
        return getFir().getName();
    }

    public IrDeclarationOrigin getOrigin() {
        return this.origin;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents, org.jetbrains.kotlin.fir.ScopeSessionHolder
    public ScopeSession getScopeSession() {
        return this.c.getScopeSession();
    }

    public List<IrClassSymbol> getSealedSubclasses() {
        return (List) this.sealedSubclasses.getValue(this, $$delegatedProperties[2]);
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents, org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.c.getSession();
    }

    public SourceElement getSource() {
        SourceElement sourceElement = DeclarationAttributesKt.getSourceElement(getFir());
        if (sourceElement != null) {
            return sourceElement;
        }
        SourceElement sourceElement2 = SourceElement.NO_SOURCE;
        sourceElement2.getClass();
        return sourceElement2;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public IrSpecialAnnotationsProvider getSpecialAnnotationsProvider() {
        return this.c.getSpecialAnnotationsProvider();
    }

    public int getStartOffset() {
        return this.startOffset;
    }

    public List<IrType> getSuperTypes() {
        return (List) this.superTypes.getValue(this, $$delegatedProperties[1]);
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrSymbolsMappingForLazyClasses getSymbolsMappingForLazyClasses() {
        return this.c.getSymbolsMappingForLazyClasses();
    }

    public IrValueParameter getThisReceiver() {
        return (IrValueParameter) this.thisReceiver.getValue(this, $$delegatedProperties[3]);
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrTypeConverter getTypeConverter() {
        return this.c.getTypeConverter();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public List<IrTypeParameter> getTypeParameters() throws UninitializedPropertyAccessException {
        List<? extends IrTypeParameter> list = this.typeParameters;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("typeParameters");
        return null;
    }

    public ValueClassRepresentation<IrSimpleType> getValueClassRepresentation() {
        return IrElementsCreationUtilsKt.computeValueClassRepresentation(this, getFir());
    }

    public DescriptorVisibility getVisibility() {
        return get_visibility();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrVisibilityConverter getVisibilityConverter() {
        return this.c.getVisibilityConverter();
    }

    public boolean isCompanion() {
        return getFir().getStatus().isCompanion();
    }

    public boolean isData() {
        return getFir().getStatus().isData();
    }

    public boolean isExpect() {
        return getFir().getStatus().isExpect();
    }

    public boolean isExternal() {
        return getFir().getStatus().isExternal();
    }

    public boolean isFun() {
        return getFir().getStatus().isFun();
    }

    public boolean isInner() {
        return getFir().getStatus().isInner();
    }

    public boolean isK2() {
        return true;
    }

    public Boolean isNewPlaceForBodyGeneration() {
        return Boolean.valueOf(Intrinsics.areEqual(ClassMembersKt.isNewPlaceForBodyGeneration(getFir()), Boolean.TRUE));
    }

    public boolean isValue() {
        FirRegularClass fir = getFir();
        return fir.getStatus().isInline() || fir.getStatus().isValue();
    }

    public void setAnnotations(List<? extends IrAnnotation> list) {
        list.getClass();
        this.annotations.setValue(this, $$delegatedProperties[0], list);
    }

    public void setAttributeOwnerId(IrElement irElement) {
        irElement.getClass();
        this.attributeOwnerId = irElement;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setCompanion(boolean z) throws KotlinNothingValueException {
        AbstractFir2IrLazyDeclarationKt.mutationNotSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setData(boolean z) throws KotlinNothingValueException {
        AbstractFir2IrLazyDeclarationKt.mutationNotSupported();
        throw new KotlinNothingValueException();
    }

    public void setEndOffset(int i) {
        this.endOffset = i;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setExpect(boolean z) throws KotlinNothingValueException {
        AbstractFir2IrLazyDeclarationKt.mutationNotSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setExternal(boolean z) throws KotlinNothingValueException {
        AbstractFir2IrLazyDeclarationKt.mutationNotSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setFun(boolean z) throws KotlinNothingValueException {
        AbstractFir2IrLazyDeclarationKt.mutationNotSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setHasEnumEntries(boolean z) throws KotlinNothingValueException {
        AbstractFir2IrLazyDeclarationKt.mutationNotSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setInner(boolean z) throws KotlinNothingValueException {
        AbstractFir2IrLazyDeclarationKt.mutationNotSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setKind(ClassKind classKind) throws KotlinNothingValueException {
        classKind.getClass();
        AbstractFir2IrLazyDeclarationKt.mutationNotSupported();
        throw new KotlinNothingValueException();
    }

    public void setMetadata(MetadataSource metadataSource) {
        throw new IllegalStateException("We should never need to store metadata of external declarations.");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setModality(Modality modality) throws KotlinNothingValueException {
        modality.getClass();
        AbstractFir2IrLazyDeclarationKt.mutationNotSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setName(Name name) throws KotlinNothingValueException {
        name.getClass();
        AbstractFir2IrLazyDeclarationKt.mutationNotSupported();
        throw new KotlinNothingValueException();
    }

    public void setOrigin(IrDeclarationOrigin irDeclarationOrigin) {
        irDeclarationOrigin.getClass();
        this.origin = irDeclarationOrigin;
    }

    public void setSealedSubclasses(List<? extends IrClassSymbol> list) {
        list.getClass();
        this.sealedSubclasses.setValue(this, $$delegatedProperties[2], list);
    }

    public void setStartOffset(int i) {
        this.startOffset = i;
    }

    public void setSuperTypes(List<? extends IrType> list) {
        list.getClass();
        this.superTypes.setValue(this, $$delegatedProperties[1], list);
    }

    public void setThisReceiver(IrValueParameter irValueParameter) {
        this.thisReceiver.setValue(this, $$delegatedProperties[3], irValueParameter);
    }

    public void setTypeParameters(List<? extends IrTypeParameter> list) {
        list.getClass();
        this.typeParameters = list;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setValue(boolean z) throws KotlinNothingValueException {
        AbstractFir2IrLazyDeclarationKt.mutationNotSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setValueClassRepresentation(ValueClassRepresentation<IrSimpleType> valueClassRepresentation) throws KotlinNothingValueException {
        AbstractFir2IrLazyDeclarationKt.mutationNotSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setVisibility(DescriptorVisibility descriptorVisibility) throws KotlinNothingValueException {
        descriptorVisibility.getClass();
        AbstractFir2IrLazyDeclarationKt.mutationNotSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: renamed from: getSymbol, reason: from getter and merged with bridge method [inline-methods] */
    public IrClassSymbol m558getSymbol() {
        return this.symbol;
    }

    @Override // org.jetbrains.kotlin.fir.lazy.AbstractFir2IrLazyDeclaration
    public FirRegularClass getFir() {
        return this.fir;
    }
}

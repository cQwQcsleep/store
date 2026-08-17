package org.jetbrains.kotlin.fir.lazy;

import defpackage.f2f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.backend.common.IrSpecialAnnotationsProvider;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.state.InlineClassManglingUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirEvaluatorResult;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionHolder;
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
import org.jetbrains.kotlin.fir.backend.Fir2IrSymbolsMappingForLazyClassesKt;
import org.jetbrains.kotlin.fir.backend.Fir2IrTypeConverter;
import org.jetbrains.kotlin.fir.backend.Fir2IrTypeConverterKt;
import org.jetbrains.kotlin.fir.backend.Fir2IrVisibilityConverter;
import org.jetbrains.kotlin.fir.backend.FirProviderWithGeneratedFiles;
import org.jetbrains.kotlin.fir.backend.PropertySymbols;
import org.jetbrains.kotlin.fir.backend.generators.AdapterGenerator;
import org.jetbrains.kotlin.fir.backend.generators.AnnotationGenerator;
import org.jetbrains.kotlin.fir.backend.generators.CallAndReferenceGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrCallableDeclarationsGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrClassifiersGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrDataClassMembersGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrLazyDeclarationsGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrLazyFakeOverrideGenerator;
import org.jetbrains.kotlin.fir.backend.utils.ConstantUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.ConversionTypeOrigin;
import org.jetbrains.kotlin.fir.backend.utils.IrElementsCreationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyGetter;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertySetter;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.lazy.Fir2IrLazyProperty;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.IrLock;
import org.jetbrains.kotlin.ir.IrProvider;
import org.jetbrains.kotlin.ir.ObsoleteDescriptorBasedAPI;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationBase;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent;
import org.jetbrains.kotlin.ir.declarations.IrFactoryHelpersKt;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.IrParameterKind;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrSymbolOwner;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.declarations.MetadataSource;
import org.jetbrains.kotlin.ir.expressions.IrAnnotation;
import org.jetbrains.kotlin.ir.expressions.IrExpressionBody;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.util.DumpIrTreeOptions;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.util.KotlinMangler;
import org.jetbrains.kotlin.ir.util.RenderIrElementKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.NameUtils;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000°\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\u00020\u0004BQ\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0004\b\u0014\u0010\u0015J\u0014\u0010a\u001a\u0004\u0018\u00010b2\b\u0010c\u001a\u0004\u0018\u00010dH\u0002J\"\u0010t\u001a\u00020u2\u0006\u0010v\u001a\u00020w2\b\u0010x\u001a\u0004\u0018\u00010y2\u0006\u0010z\u001a\u00020{H\u0002R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\b\u001a\u00020\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0017\"\u0004\b\u001b\u0010\u0019R\u001a\u0010\t\u001a\u00020\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0014\u0010\u000b\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u001a\u0010\u0012\u001a\u00020\u0013X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010$\"\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020(X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R7\u0010.\u001a\b\u0012\u0004\u0012\u00020-0,2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020-0,8V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b3\u00104\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u001e\u00105\u001a\u0002068VX\u0097\u0004r\u0002\b;¢\u0006\f\u0012\u0004\b7\u00108\u001a\u0004\b9\u0010:R$\u0010=\u001a\u00020\u00132\u0006\u0010<\u001a\u00020\u00138V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b=\u0010$\"\u0004\b>\u0010&R$\u0010?\u001a\u00020\u00132\u0006\u0010<\u001a\u00020\u00138V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b?\u0010$\"\u0004\b@\u0010&R$\u0010A\u001a\u00020\u00132\u0006\u0010<\u001a\u00020\u00138V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bA\u0010$\"\u0004\bB\u0010&R$\u0010C\u001a\u00020\u00132\u0006\u0010<\u001a\u00020\u00138V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bC\u0010$\"\u0004\bD\u0010&R$\u0010E\u001a\u00020\u00132\u0006\u0010<\u001a\u00020\u00138V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bE\u0010$\"\u0004\bF\u0010&R$\u0010G\u001a\u00020\u00132\u0006\u0010<\u001a\u00020\u00138V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bG\u0010$\"\u0004\bH\u0010&R$\u0010J\u001a\u00020I2\u0006\u0010<\u001a\u00020I8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bK\u0010L\"\u0004\bM\u0010NR$\u0010P\u001a\u00020O2\u0006\u0010<\u001a\u00020O@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TR$\u0010V\u001a\u00020U2\u0006\u0010<\u001a\u00020U8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bW\u0010X\"\u0004\bY\u0010ZR\u001b\u0010[\u001a\u00020\\8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b_\u0010`\u001a\u0004\b]\u0010^R\u001c\u0010e\u001a\u0004\u0018\u00010fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bg\u0010h\"\u0004\bi\u0010jR\u001c\u0010k\u001a\u0004\u0018\u00010lX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bm\u0010n\"\u0004\bo\u0010pR\u001c\u0010q\u001a\u0004\u0018\u00010lX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\br\u0010n\"\u0004\bs\u0010pR7\u0010|\u001a\b\u0012\u0004\u0012\u00020(0,2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020(0,8V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u007f\u00104\u001a\u0004\b}\u00100\"\u0004\b~\u00102R/\u0010\u0081\u0001\u001a\u0005\u0018\u00010\u0080\u00012\t\u0010<\u001a\u0005\u0018\u00010\u0080\u00018V@VX\u0096\u000e¢\u0006\u0010\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001\"\u0006\b\u0084\u0001\u0010\u0085\u0001R\u001a\u0010\u0086\u0001\u001a\u0005\u0018\u00010\u0087\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0088\u0001\u0010\u0089\u0001R \u0010\u008a\u0001\u001a\u00030\u008b\u0001X\u0096\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u008c\u0001\u0010\u008d\u0001\"\u0006\b\u008e\u0001\u0010\u008f\u0001R\u0016\u0010\u0090\u0001\u001a\u00030\u0091\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0092\u0001\u0010\u0093\u0001R\u0016\u0010\u0094\u0001\u001a\u00030\u0095\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0096\u0001\u0010\u0097\u0001R\u0016\u0010\u0098\u0001\u001a\u00030\u0099\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u009a\u0001\u0010\u009b\u0001R\u0016\u0010\u009c\u0001\u001a\u00030\u009d\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u009e\u0001\u0010\u009f\u0001R\u0016\u0010 \u0001\u001a\u00030¡\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¢\u0001\u0010£\u0001R\u0016\u0010¤\u0001\u001a\u00030¥\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¦\u0001\u0010§\u0001R\u0016\u0010¨\u0001\u001a\u00030©\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bª\u0001\u0010«\u0001R\u0016\u0010¬\u0001\u001a\u00030\u00ad\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b®\u0001\u0010¯\u0001R\u0016\u0010°\u0001\u001a\u00030±\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b²\u0001\u0010³\u0001R\u0016\u0010´\u0001\u001a\u00030µ\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¶\u0001\u0010·\u0001R\u0016\u0010¸\u0001\u001a\u00030¹\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bº\u0001\u0010»\u0001R\u0016\u0010¼\u0001\u001a\u00030½\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¾\u0001\u0010¿\u0001R\u0016\u0010À\u0001\u001a\u00030Á\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÂ\u0001\u0010Ã\u0001R\u001f\u0010Ä\u0001\u001a\f\u0012\u0005\u0012\u00030Æ\u0001\u0018\u00010Å\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÇ\u0001\u0010È\u0001R\u0016\u0010É\u0001\u001a\u00030Ê\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bË\u0001\u0010Ì\u0001R\u0016\u0010Í\u0001\u001a\u00030Î\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÏ\u0001\u0010Ð\u0001R\u0016\u0010Ñ\u0001\u001a\u00030Ò\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÓ\u0001\u0010Ô\u0001R\u001b\u0010Õ\u0001\u001a\t\u0012\u0005\u0012\u00030Ö\u00010,X\u0096\u0005¢\u0006\u0007\u001a\u0005\b×\u0001\u00100R\u0016\u0010Ø\u0001\u001a\u00030Ù\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÚ\u0001\u0010Û\u0001R\u0016\u0010Ü\u0001\u001a\u00030Ý\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÞ\u0001\u0010ß\u0001R\u0016\u0010à\u0001\u001a\u00030á\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bâ\u0001\u0010ã\u0001R\u0016\u0010ä\u0001\u001a\u00030å\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bæ\u0001\u0010ç\u0001R\u0016\u0010è\u0001\u001a\u00030é\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bê\u0001\u0010ë\u0001R\u0018\u0010ì\u0001\u001a\u0005\u0018\u00010í\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bî\u0001\u0010ï\u0001R\u0016\u0010ð\u0001\u001a\u00030ñ\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bò\u0001\u0010ó\u0001R\u0016\u0010ô\u0001\u001a\u00030õ\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bö\u0001\u0010÷\u0001R\u0016\u0010ø\u0001\u001a\u00030ù\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bú\u0001\u0010û\u0001¨\u0006ü\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/lazy/Fir2IrLazyProperty;", "Lorg/jetbrains/kotlin/ir/declarations/IrProperty;", "Lorg/jetbrains/kotlin/fir/lazy/AbstractFir2IrLazyDeclaration;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "c", "startOffset", Argument.Delimiters.none, "endOffset", "origin", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "fir", "containingClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "symbols", "Lorg/jetbrains/kotlin/fir/backend/PropertySymbols;", "parent", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationParent;", "isFakeOverride", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;IILorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;Lorg/jetbrains/kotlin/fir/backend/PropertySymbols;Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationParent;Z)V", "getStartOffset", "()I", "setStartOffset", "(I)V", "getEndOffset", "setEndOffset", "getOrigin", "()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "setOrigin", "(Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;)V", "getFir", "()Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "getContainingClass", "()Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "()Z", "setFakeOverride", "(Z)V", "symbol", "Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;", "getSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;", "<set-?>", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/expressions/IrAnnotation;", "annotations", "getAnnotations", "()Ljava/util/List;", "setAnnotations", "(Ljava/util/List;)V", "annotations$delegate", "Lkotlin/properties/ReadWriteProperty;", "descriptor", "Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "getDescriptor$annotations", "()V", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "Lorg/jetbrains/kotlin/ir/ObsoleteDescriptorBasedAPI;", InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER, "isVar", "setVar", "isConst", "setConst", "isLateinit", "setLateinit", "isDelegated", "setDelegated", "isExternal", "setExternal", "isExpect", "setExpect", "Lorg/jetbrains/kotlin/name/Name;", ModuleXmlParser.NAME, "getName", "()Lorg/jetbrains/kotlin/name/Name;", "setName", "(Lorg/jetbrains/kotlin/name/Name;)V", "Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "visibility", "getVisibility", "()Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "setVisibility", "(Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;)V", "Lorg/jetbrains/kotlin/descriptors/Modality;", "modality", "getModality", "()Lorg/jetbrains/kotlin/descriptors/Modality;", "setModality", "(Lorg/jetbrains/kotlin/descriptors/Modality;)V", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/ir/types/IrType;", "getType", "()Lorg/jetbrains/kotlin/ir/types/IrType;", "type$delegate", "Lkotlin/Lazy;", "toIrInitializer", "Lorg/jetbrains/kotlin/ir/expressions/IrExpressionBody;", "initializer", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "backingField", "Lorg/jetbrains/kotlin/ir/declarations/IrField;", "getBackingField", "()Lorg/jetbrains/kotlin/ir/declarations/IrField;", "setBackingField", "(Lorg/jetbrains/kotlin/ir/declarations/IrField;)V", "getter", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "getGetter", "()Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "setGetter", "(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;)V", "setter", "getSetter", "setSetter", "initializeAccessor", Argument.Delimiters.none, "accessor", "Lorg/jetbrains/kotlin/fir/lazy/Fir2IrLazyPropertyAccessor;", "firAccessor", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "typeOrigin", "Lorg/jetbrains/kotlin/fir/backend/utils/ConversionTypeOrigin;", "overriddenSymbols", "getOverriddenSymbols", "setOverriddenSymbols", "overriddenSymbols$delegate", "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource;", "metadata", "getMetadata", "()Lorg/jetbrains/kotlin/ir/declarations/MetadataSource;", "setMetadata", "(Lorg/jetbrains/kotlin/ir/declarations/MetadataSource;)V", "containerSource", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "getContainerSource", "()Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "attributeOwnerId", "Lorg/jetbrains/kotlin/ir/IrElement;", "getAttributeOwnerId", "()Lorg/jetbrains/kotlin/ir/IrElement;", "setAttributeOwnerId", "(Lorg/jetbrains/kotlin/ir/IrElement;)V", "adapterGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "getAdapterGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "annotationGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "getAnnotationGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "annotationsFromPluginRegistrar", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "getAnnotationsFromPluginRegistrar", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "builtins", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "getBuiltins", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "callGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "getCallGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "callablesGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "getCallablesGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "classifierStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "getClassifierStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "classifiersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "getClassifiersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "configuration", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "getConfiguration", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "converter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "getConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "dataClassMembersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "getDataClassMembersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "declarationStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "getDeclarationStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "extensions", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "getExtensions", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "filesBeingCompiled", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "getFilesBeingCompiled", "()Ljava/util/Set;", "firProvider", "Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "getFirProvider", "()Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "implicitCastInserter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "getImplicitCastInserter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "irMangler", "Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "getIrMangler", "()Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "irProviders", "Lorg/jetbrains/kotlin/ir/IrProvider;", "getIrProviders", "lazyDeclarationsGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "getLazyDeclarationsGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "lazyFakeOverrideGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "getLazyFakeOverrideGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "lock", "Lorg/jetbrains/kotlin/ir/IrLock;", "getLock", "()Lorg/jetbrains/kotlin/ir/IrLock;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "specialAnnotationsProvider", "Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "getSpecialAnnotationsProvider", "()Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "symbolsMappingForLazyClasses", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "getSymbolsMappingForLazyClasses", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "typeConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "getTypeConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "visibilityConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "getVisibilityConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrLazyProperty extends IrProperty implements Fir2IrComponents, AbstractFir2IrLazyDeclaration<FirProperty> {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(Fir2IrLazyProperty.class, "annotations", "getAnnotations()Ljava/util/List;", 0), new MutablePropertyReference1Impl<>(Fir2IrLazyProperty.class, "overriddenSymbols", "getOverriddenSymbols()Ljava/util/List;", 0)};

    /* JADX INFO: renamed from: annotations$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty annotations;
    private IrElement attributeOwnerId;
    private IrField backingField;
    private final Fir2IrComponents c;
    private final FirRegularClass containingClass;
    private int endOffset;
    private final FirProperty fir;
    private IrSimpleFunction getter;
    private boolean isFakeOverride;
    private IrDeclarationOrigin origin;

    /* JADX INFO: renamed from: overriddenSymbols$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty overriddenSymbols;
    private IrSimpleFunction setter;
    private int startOffset;
    private final IrPropertySymbol symbol;

    /* JADX INFO: renamed from: type$delegate, reason: from kotlin metadata */
    private final Lazy type;
    private DescriptorVisibility visibility;

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [org.jetbrains.kotlin.fir.backend.generators.Fir2IrCallableDeclarationsGenerator] */
    /* JADX WARN: Type inference failed for: r1v0, types: [org.jetbrains.kotlin.fir.backend.Fir2IrComponents, org.jetbrains.kotlin.fir.lazy.AbstractFir2IrLazyDeclaration, org.jetbrains.kotlin.fir.lazy.Fir2IrLazyProperty, org.jetbrains.kotlin.ir.declarations.IrDeclarationBase, org.jetbrains.kotlin.ir.declarations.IrProperty, org.jetbrains.kotlin.ir.declarations.IrSymbolOwner] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9, types: [org.jetbrains.kotlin.fir.lazy.Fir2IrLazyProperty, org.jetbrains.kotlin.ir.IrElement, org.jetbrains.kotlin.ir.declarations.IrDeclarationBase] */
    public Fir2IrLazyProperty(Fir2IrComponents fir2IrComponents, int i, int i2, IrDeclarationOrigin irDeclarationOrigin, FirProperty firProperty, FirRegularClass firRegularClass, PropertySymbols propertySymbols, final IrDeclarationParent irDeclarationParent, boolean z) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrDeclarationBase irDeclarationBaseCreateBackingField$org_jetbrains_kotlin_fir2ir;
        FirExpression firExpression;
        final ?? r1;
        FirExpression firExpression2;
        FirExpression initializer;
        Visibility visibility;
        FirExpression initializer2;
        FirTypeRef returnTypeRef;
        Fir2IrLazyPropertyAccessor fir2IrLazyPropertyAccessor;
        List arrayList;
        List<FirAnnotation> annotations;
        fir2IrComponents.getClass();
        irDeclarationOrigin.getClass();
        firProperty.getClass();
        propertySymbols.getClass();
        irDeclarationParent.getClass();
        final ?? irProperty = new IrProperty();
        irProperty.c = fir2IrComponents;
        irProperty.startOffset = i;
        irProperty.endOffset = i2;
        irProperty.origin = irDeclarationOrigin;
        irProperty.fir = firProperty;
        irProperty.containingClass = firRegularClass;
        irProperty.isFakeOverride = z;
        irProperty.symbol = propertySymbols.getPropertySymbol();
        irProperty.setParent(irDeclarationParent);
        irProperty.m567getSymbol().bind((IrSymbolOwner) irProperty);
        irProperty.getClassifierStorage().preCacheTypeParameters$org_jetbrains_kotlin_fir2ir(irProperty.getFir());
        irProperty.annotations = irProperty.createLazyAnnotations();
        irProperty.visibility = fir2IrComponents.getVisibilityConverter().convertToDescriptorVisibility(irProperty.getFir().getStatus().getVisibility());
        irProperty.type = LazyKt.lazy(new Function0() { // from class: dw4
            public final Object invoke() {
                return Fir2IrLazyProperty.e(this.b);
            }
        });
        Fir2IrLazyPropertyAccessor fir2IrLazyPropertyAccessor2 = null;
        ?? r2 = irProperty;
        if (propertySymbols.getBackingFieldSymbol() == null) {
            irDeclarationBaseCreateBackingField$org_jetbrains_kotlin_fir2ir = null;
            r1 = r2;
        } else if (DeclarationAttributesKt.getHasExplicitBackingField(irProperty.getFir())) {
            FirBackingField backingField = irProperty.getFir().getBackingField();
            IrType irType$default = (backingField == null || (returnTypeRef = backingField.getReturnTypeRef()) == null) ? null : Fir2IrTypeConverterKt.toIrType$default((Fir2IrComponents) irProperty, returnTypeRef, (ConversionTypeOrigin) null, 2, (Object) null);
            FirEvaluatorResult evaluatedInitializer = DeclarationAttributesKt.getEvaluatedInitializer(irProperty.getFir());
            if (evaluatedInitializer == null) {
                firExpression2 = null;
            } else {
                if (evaluatedInitializer instanceof FirEvaluatorResult.CompileTimeException) {
                } else if (evaluatedInitializer instanceof FirEvaluatorResult.Evaluated) {
                    FirElement result = ((FirEvaluatorResult.Evaluated) evaluatedInitializer).getResult();
                    firExpression2 = (FirExpression) (result instanceof FirExpression ? result : null);
                }
                firExpression2 = null;
            }
            FirBackingField backingField2 = irProperty.getFir().getBackingField();
            if (backingField2 == null || (initializer2 = backingField2.getInitializer()) == null) {
                initializer = firExpression2 == null ? irProperty.getFir().getInitializer() : firExpression2;
            } else {
                initializer = initializer2;
            }
            FirBackingField backingField3 = irProperty.getFir().getBackingField();
            irDeclarationBaseCreateBackingField$org_jetbrains_kotlin_fir2ir = irProperty.getCallablesGenerator().createBackingField$org_jetbrains_kotlin_fir2ir(irProperty, irProperty.getFir(), IrDeclarationOrigin.Companion.getPROPERTY_BACKING_FIELD(), propertySymbols.getBackingFieldSymbol(), fir2IrComponents.getVisibilityConverter().convertToDescriptorVisibility((backingField3 == null || (visibility = backingField3.getStatus().getVisibility()) == null) ? irProperty.getFir().getStatus().getVisibility() : visibility), irProperty.getFir().getName(), irProperty.getFir().getIsVal(), initializer, irType$default);
            irDeclarationBaseCreateBackingField$org_jetbrains_kotlin_fir2ir.setInitializer(irProperty.toIrInitializer(initializer));
            r1 = irProperty;
        } else if (irProperty.getFir().getDelegate() != null) {
            FirLazyDeclarationResolverKt.lazyResolveToPhase(irProperty.getFir(), FirResolvePhase.BODY_RESOLVE);
            irDeclarationBaseCreateBackingField$org_jetbrains_kotlin_fir2ir = irProperty.getCallablesGenerator().createBackingField$org_jetbrains_kotlin_fir2ir(irProperty, irProperty.getFir(), IrDeclarationOrigin.Companion.getPROPERTY_DELEGATE(), propertySymbols.getBackingFieldSymbol(), fir2IrComponents.getVisibilityConverter().convertToDescriptorVisibility(irProperty.getFir().getStatus().getVisibility()), NameUtils.propertyDelegateName(irProperty.getFir().getName()), true, irProperty.getFir().getDelegate(), (256 & 256) != 0 ? null : null);
            r1 = this;
        } else {
            IrDeclarationOrigin origin = getOrigin();
            IrDeclarationOrigin.Companion companion = IrDeclarationOrigin.Companion;
            if (Intrinsics.areEqual(origin, companion.getFAKE_OVERRIDE())) {
                r2 = this;
                irDeclarationBaseCreateBackingField$org_jetbrains_kotlin_fir2ir = null;
                r1 = r2;
            } else {
                FirLazyDeclarationResolverKt.lazyResolveToPhase(getFir(), FirResolvePhase.BODY_RESOLVE);
                Fir2IrLazyProperty fir2IrLazyProperty = this;
                irDeclarationBaseCreateBackingField$org_jetbrains_kotlin_fir2ir = getCallablesGenerator().createBackingField$org_jetbrains_kotlin_fir2ir(fir2IrLazyProperty, getFir(), companion.getPROPERTY_BACKING_FIELD(), propertySymbols.getBackingFieldSymbol(), fir2IrComponents.getVisibilityConverter().convertToDescriptorVisibility(getFir().getStatus().getVisibility()), getFir().getName(), getFir().getIsVal(), getFir().getInitializer(), getType());
                FirEvaluatorResult evaluatedInitializer2 = DeclarationAttributesKt.getEvaluatedInitializer(fir2IrLazyProperty.getFir());
                if (evaluatedInitializer2 == null) {
                    firExpression = null;
                } else {
                    if (evaluatedInitializer2 instanceof FirEvaluatorResult.CompileTimeException) {
                    } else if (evaluatedInitializer2 instanceof FirEvaluatorResult.Evaluated) {
                        FirElement result2 = ((FirEvaluatorResult.Evaluated) evaluatedInitializer2).getResult();
                        firExpression = (FirExpression) (result2 instanceof FirExpression ? result2 : null);
                    }
                    firExpression = null;
                }
                irDeclarationBaseCreateBackingField$org_jetbrains_kotlin_fir2ir.setInitializer(fir2IrLazyProperty.toIrInitializer(firExpression == null ? fir2IrLazyProperty.getFir().getInitializer() : firExpression));
                r1 = fir2IrLazyProperty;
            }
        }
        if (irDeclarationBaseCreateBackingField$org_jetbrains_kotlin_fir2ir != null) {
            irDeclarationBaseCreateBackingField$org_jetbrains_kotlin_fir2ir.setParent(r1.getParent());
            FirBackingField backingField4 = r1.getFir().getBackingField();
            if (backingField4 == null || (annotations = backingField4.getAnnotations()) == null) {
                arrayList = null;
            } else {
                arrayList = new ArrayList();
                Iterator it = annotations.iterator();
                while (it.hasNext()) {
                    IrAnnotation irAnnotationConvertToIrAnnotation = r1.getCallGenerator().convertToIrAnnotation((FirAnnotation) it.next());
                    IrAnnotation irAnnotation = irAnnotationConvertToIrAnnotation instanceof IrAnnotation ? irAnnotationConvertToIrAnnotation : null;
                    if (irAnnotation != null) {
                        arrayList.add(irAnnotation);
                    }
                }
            }
            irDeclarationBaseCreateBackingField$org_jetbrains_kotlin_fir2ir.setAnnotations(arrayList == null ? CollectionsKt.emptyList() : arrayList);
        } else {
            irDeclarationBaseCreateBackingField$org_jetbrains_kotlin_fir2ir = null;
        }
        r1.backingField = irDeclarationBaseCreateBackingField$org_jetbrains_kotlin_fir2ir;
        IrSimpleFunctionSymbol getterSymbol = propertySymbols.getGetterSymbol();
        if (getterSymbol != null) {
            Fir2IrComponents fir2IrComponents2 = r1.c;
            int startOffset = r1.getStartOffset();
            int endOffset = r1.getEndOffset();
            IrDeclarationOrigin origin2 = r1.getOrigin();
            IrDeclarationOrigin.Companion companion2 = IrDeclarationOrigin.Companion;
            IrDeclarationOrigin origin3 = Intrinsics.areEqual(origin2, companion2.getIR_EXTERNAL_DECLARATION_STUB()) ? r1.getOrigin() : r1.getFir().getDelegate() != null ? companion2.getDELEGATED_PROPERTY_ACCESSOR() : (Intrinsics.areEqual(r1.getOrigin(), companion2.getFAKE_OVERRIDE()) || Intrinsics.areEqual(r1.getOrigin(), companion2.getDELEGATED_MEMBER()) || !(r1.getFir().getGetter() instanceof FirDefaultPropertyGetter)) ? r1.getOrigin() : companion2.getDEFAULT_PROPERTY_ACCESSOR();
            fir2IrLazyPropertyAccessor = new Fir2IrLazyPropertyAccessor(fir2IrComponents2, startOffset, endOffset, origin3, r1.getFir().getGetter(), false, r1.getFir(), r1.containingClass, getterSymbol, r1.getParent(), r1.getIsFakeOverride(), r1.m567getSymbol());
            r1.initializeAccessor(fir2IrLazyPropertyAccessor, r1.getFir().getGetter(), ConversionTypeOrigin.DEFAULT);
        } else {
            fir2IrLazyPropertyAccessor = null;
        }
        r1.getter = fir2IrLazyPropertyAccessor;
        if (r1.getFir().getIsVar() && propertySymbols.getSetterSymbol() != null) {
            Fir2IrComponents fir2IrComponents3 = r1.c;
            int startOffset2 = r1.getStartOffset();
            int endOffset2 = r1.getEndOffset();
            IrDeclarationOrigin origin4 = r1.getOrigin();
            IrDeclarationOrigin.Companion companion3 = IrDeclarationOrigin.Companion;
            IrDeclarationOrigin origin5 = Intrinsics.areEqual(origin4, companion3.getIR_EXTERNAL_DECLARATION_STUB()) ? r1.getOrigin() : r1.getFir().getDelegate() != null ? companion3.getDELEGATED_PROPERTY_ACCESSOR() : (Intrinsics.areEqual(r1.getOrigin(), companion3.getFAKE_OVERRIDE()) || Intrinsics.areEqual(r1.getOrigin(), companion3.getDELEGATED_MEMBER()) || !(r1.getFir().getSetter() instanceof FirDefaultPropertySetter)) ? r1.getOrigin() : companion3.getDEFAULT_PROPERTY_ACCESSOR();
            Fir2IrLazyPropertyAccessor fir2IrLazyPropertyAccessor3 = new Fir2IrLazyPropertyAccessor(fir2IrComponents3, startOffset2, endOffset2, origin5, r1.getFir().getSetter(), true, r1.getFir(), r1.containingClass, propertySymbols.getSetterSymbol(), r1.getParent(), r1.getIsFakeOverride(), r1.m567getSymbol());
            r1.initializeAccessor(fir2IrLazyPropertyAccessor3, r1.getFir().getSetter(), ConversionTypeOrigin.SETTER);
            fir2IrLazyPropertyAccessor2 = fir2IrLazyPropertyAccessor3;
        }
        r1.setter = fir2IrLazyPropertyAccessor2;
        r1.overriddenSymbols = Fir2IrSymbolsMappingForLazyClassesKt.lazyMappedPropertyListVar(r1.getSymbolsMappingForLazyClasses(), r1.getLock(), new Function0() { // from class: ew4
            public final Object invoke() {
                return Fir2IrLazyProperty.f(this.b, irDeclarationParent);
            }
        });
        r1.attributeOwnerId = r1;
    }

    public static IrType e(Fir2IrLazyProperty fir2IrLazyProperty) {
        return Fir2IrTypeConverterKt.toIrType$default(fir2IrLazyProperty, fir2IrLazyProperty.getFir().getReturnTypeRef(), (ConversionTypeOrigin) null, 2, (Object) null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static List f(Fir2IrLazyProperty fir2IrLazyProperty, IrDeclarationParent irDeclarationParent) throws KotlinIllegalArgumentExceptionWithAttachments {
        if (fir2IrLazyProperty.containingClass == null || !(irDeclarationParent instanceof Fir2IrLazyClass)) {
            return CollectionsKt.emptyList();
        }
        List<Pair<FirPropertySymbol, ConeClassLikeLookupTag>> listComputeFakeOverrideKeys$org_jetbrains_kotlin_fir2ir = fir2IrLazyProperty.getLazyFakeOverrideGenerator().computeFakeOverrideKeys$org_jetbrains_kotlin_fir2ir(fir2IrLazyProperty.containingClass, fir2IrLazyProperty.getFir().getSymbol());
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listComputeFakeOverrideKeys$org_jetbrains_kotlin_fir2ir, 10));
        Iterator<T> it = listComputeFakeOverrideKeys$org_jetbrains_kotlin_fir2ir.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            IrPropertySymbol irPropertySymbol = fir2IrLazyProperty.getDeclarationStorage().getIrPropertySymbol((FirPropertySymbol) pair.component1(), (ConeClassLikeLookupTag) pair.component2());
            irPropertySymbol.getClass();
            arrayList.add(irPropertySymbol);
        }
        return arrayList;
    }

    @ObsoleteDescriptorBasedAPI
    public static /* synthetic */ void getDescriptor$annotations() {
    }

    private final IrType getType() {
        return (IrType) this.type.getValue();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:58:0x0124  */
    private final void initializeAccessor(Fir2IrLazyPropertyAccessor accessor, FirPropertyAccessor firAccessor, ConversionTypeOrigin typeOrigin) throws KotlinIllegalArgumentExceptionWithAttachments {
        Fir2IrLazyProperty fir2IrLazyProperty;
        Fir2IrLazyProperty fir2IrLazyProperty2;
        Fir2IrLazyPropertyAccessor fir2IrLazyPropertyAccessor;
        FirTypeRef returnTypeRef;
        boolean z;
        boolean z2;
        Name name;
        List<FirValueParameter> valueParameters;
        IrType type;
        getDeclarationStorage().enterScope(accessor.m556getSymbol());
        accessor.getClassifiersGenerator().setTypeParameters$org_jetbrains_kotlin_fir2ir(accessor, getFir(), typeOrigin);
        List<IrValueParameter> listCreateListBuilder = CollectionsKt.createListBuilder();
        IrClass parent = getParent();
        IrClass irClass = parent instanceof IrClass ? parent : null;
        if (irClass == null || IrUtilsKt.isFacadeClass(irClass)) {
            irClass = null;
        }
        if (irClass == null || !accessor.shouldHaveDispatchReceiver$org_jetbrains_kotlin_fir2ir(irClass)) {
            fir2IrLazyProperty = this;
        } else {
            List<IrValueParameter> list = listCreateListBuilder;
            IrValueParameter thisReceiver = irClass.getThisReceiver();
            if (thisReceiver == null || (type = thisReceiver.getType()) == null) {
                f2f.a("No this receiver for containing class: ", RenderIrElementKt.render$default(irClass, (DumpIrTreeOptions) null, 1, (Object) null));
                return;
            } else {
                fir2IrLazyProperty = this;
                list.add(IrElementsCreationUtilsKt.declareThisReceiverParameter$default(this, accessor, type, accessor.getOrigin(), IrParameterKind.DispatchReceiver, 0, 0, null, null, false, 496, null));
            }
        }
        fir2IrLazyProperty.getCallablesGenerator().addContextParametersTo(FirDeclarationUtilKt.contextParametersForFunctionOrContainingProperty(r1.getFir()), r1, listCreateListBuilder);
        FirReceiverParameter receiverParameter = fir2IrLazyProperty.getFir().getReceiverParameter();
        if (receiverParameter != null) {
            Fir2IrLazyProperty fir2IrLazyProperty3 = fir2IrLazyProperty;
            fir2IrLazyProperty2 = fir2IrLazyProperty3;
            listCreateListBuilder.add(IrElementsCreationUtilsKt.declareThisReceiverParameter$default(fir2IrLazyProperty3, accessor, Fir2IrTypeConverterKt.toIrType(fir2IrLazyProperty, receiverParameter.getTypeRef(), typeOrigin), accessor.getOrigin(), IrParameterKind.ExtensionReceiver, 0, 0, null, receiverParameter, false, 368, null));
        } else {
            fir2IrLazyProperty2 = fir2IrLazyProperty;
        }
        if (accessor.getIsSetter()) {
            FirValueParameter firValueParameter = (firAccessor == null || (valueParameters = firAccessor.getValueParameters()) == null) ? null : (FirValueParameter) CollectionsKt.firstOrNull(valueParameters);
            List<IrValueParameter> list2 = listCreateListBuilder;
            Fir2IrCallableDeclarationsGenerator callablesGenerator = fir2IrLazyProperty2.getCallablesGenerator();
            int startOffset = accessor.getStartOffset();
            int endOffset = accessor.getEndOffset();
            if (firValueParameter == null || (returnTypeRef = firValueParameter.getReturnTypeRef()) == null) {
                returnTypeRef = accessor.getFir().getReturnTypeRef();
            }
            IrType irType = Fir2IrTypeConverterKt.toIrType(fir2IrLazyProperty2, returnTypeRef, typeOrigin);
            Name name2 = (firValueParameter == null || (name = firValueParameter.getName()) == null || (firAccessor instanceof FirDefaultPropertySetter)) ? null : name;
            if (firValueParameter != null) {
                z = true;
                if (firValueParameter.getIsCrossinline()) {
                    z2 = true;
                }
                if (firValueParameter != null || firValueParameter.getIsNoinline() != z) {
                    z = false;
                }
                IrValueParameter irValueParameterCreateDefaultSetterParameter$org_jetbrains_kotlin_fir2ir = callablesGenerator.createDefaultSetterParameter$org_jetbrains_kotlin_fir2ir(startOffset, endOffset, irType, accessor, firValueParameter, name2, z2, z);
                fir2IrLazyPropertyAccessor = accessor;
                list2.add(irValueParameterCreateDefaultSetterParameter$org_jetbrains_kotlin_fir2ir);
            } else {
                z = true;
            }
            z2 = false;
            if (firValueParameter != null) {
                z = false;
            } else {
                z = false;
            }
            IrValueParameter irValueParameterCreateDefaultSetterParameter$org_jetbrains_kotlin_fir2ir2 = callablesGenerator.createDefaultSetterParameter$org_jetbrains_kotlin_fir2ir(startOffset, endOffset, irType, accessor, firValueParameter, name2, z2, z);
            fir2IrLazyPropertyAccessor = accessor;
            list2.add(irValueParameterCreateDefaultSetterParameter$org_jetbrains_kotlin_fir2ir2);
        } else {
            fir2IrLazyPropertyAccessor = accessor;
        }
        fir2IrLazyPropertyAccessor.setParameters(CollectionsKt.build(listCreateListBuilder));
        fir2IrLazyProperty2.getDeclarationStorage().leaveScope(fir2IrLazyPropertyAccessor.m556getSymbol());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    private final IrExpressionBody toIrInitializer(FirExpression initializer) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrType irType$default;
        ConeKotlinType resolvedType;
        ClassKind classKind;
        FirRegularClassSymbol regularClassSymbol;
        FirRegularClass firRegularClass;
        FirConstructorSymbol firConstructorSymbolPrimaryConstructorIfAny;
        FirRegularClass firRegularClass2 = this.containingClass;
        if (firRegularClass2 == null || (classKind = firRegularClass2.getClassKind()) == null || classKind != ClassKind.ANNOTATION_CLASS) {
            if (!(initializer instanceof FirLiteralExpression)) {
                return null;
            }
            FirLazyDeclarationResolverKt.lazyResolveToPhase(getFir(), FirResolvePhase.BODY_RESOLVE);
            FirExpression initializer2 = getFir().getInitializer();
            if (initializer2 == null || (resolvedType = FirTypeUtilsKt.getResolvedType(initializer2)) == null || (irType$default = Fir2IrTypeConverterKt.toIrType$default(this, resolvedType, (ConversionTypeOrigin) null, 2, (Object) null)) == null) {
                irType$default = Fir2IrTypeConverterKt.toIrType$default(this, FirTypeUtilsKt.getResolvedType(initializer), (ConversionTypeOrigin) null, 2, (Object) null);
            }
            return IrFactoryHelpersKt.createExpressionBody(getFactory(), ConstantUtilsKt.toIrConst((FirLiteralExpression) initializer, irType$default));
        }
        Fir2IrDeclarationStorage declarationStorage = getDeclarationStorage();
        IrPropertySymbol irPropertySymbolM567getSymbol = m567getSymbol();
        declarationStorage.enterScope(irPropertySymbolM567getSymbol);
        Fir2IrDeclarationStorage declarationStorage2 = getDeclarationStorage();
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(getFir());
        if (coneClassLikeLookupTagContainingClassLookupTag != null && (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) declarationStorage2, coneClassLikeLookupTagContainingClassLookupTag)) != null && (firRegularClass = (FirRegularClass) regularClassSymbol.getFir()) != null && (firConstructorSymbolPrimaryConstructorIfAny = DeclarationUtilsKt.primaryConstructorIfAny(firRegularClass, declarationStorage2.getSession())) != null) {
            declarationStorage2.putParametersInScope(Fir2IrDeclarationStorage.getIrConstructorSymbol$default(declarationStorage2.getDeclarationStorage(), firConstructorSymbolPrimaryConstructorIfAny, false, 2, null).getOwner(), (FirFunction) firConstructorSymbolPrimaryConstructorIfAny.getFir());
        }
        FirLazyDeclarationResolverKt.lazyResolveToPhase(getFir(), FirResolvePhase.BODY_RESOLVE);
        IrExpressionBody irExpressionBodyAsCompileTimeIrInitializerForAnnotationParameter = initializer != null ? ConstantUtilsKt.asCompileTimeIrInitializerForAnnotationParameter(this, initializer, FirTypeUtilsKt.getConeType(getFir().getReturnTypeRef())) : null;
        declarationStorage.leaveScope(irPropertySymbolM567getSymbol);
        return irExpressionBodyAsCompileTimeIrInitializerForAnnotationParameter;
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

    public IrField getBackingField() {
        return this.backingField;
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

    public DeserializedContainerSource getContainerSource() {
        return getFir().getContainerSource();
    }

    public final FirRegularClass getContainingClass() {
        return this.containingClass;
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

    public PropertyDescriptor getDescriptor() {
        return m567getSymbol().getDescriptor();
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

    public IrSimpleFunction getGetter() {
        return this.getter;
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

    public MetadataSource getMetadata() {
        return null;
    }

    public Modality getModality() {
        Modality modality = getFir().getStatus().getModality();
        modality.getClass();
        return modality;
    }

    public Name getName() {
        return getFir().getName();
    }

    public IrDeclarationOrigin getOrigin() {
        return this.origin;
    }

    public List<IrPropertySymbol> getOverriddenSymbols() {
        return (List) this.overriddenSymbols.getValue(this, $$delegatedProperties[1]);
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents, org.jetbrains.kotlin.fir.ScopeSessionHolder
    public ScopeSession getScopeSession() {
        return this.c.getScopeSession();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents, org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.c.getSession();
    }

    public IrSimpleFunction getSetter() {
        return this.setter;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public IrSpecialAnnotationsProvider getSpecialAnnotationsProvider() {
        return this.c.getSpecialAnnotationsProvider();
    }

    public int getStartOffset() {
        return this.startOffset;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrSymbolsMappingForLazyClasses getSymbolsMappingForLazyClasses() {
        return this.c.getSymbolsMappingForLazyClasses();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrTypeConverter getTypeConverter() {
        return this.c.getTypeConverter();
    }

    public DescriptorVisibility getVisibility() {
        return this.visibility;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrVisibilityConverter getVisibilityConverter() {
        return this.c.getVisibilityConverter();
    }

    public boolean isConst() {
        return getFir().getStatus().isConst();
    }

    public boolean isDelegated() {
        return getFir().getDelegate() != null;
    }

    public boolean isExpect() {
        return getFir().getStatus().isExpect();
    }

    public boolean isExternal() {
        return getFir().getStatus().isExternal();
    }

    /* JADX INFO: renamed from: isFakeOverride, reason: from getter */
    public boolean getIsFakeOverride() {
        return this.isFakeOverride;
    }

    public boolean isLateinit() {
        return getFir().getStatus().isLateInit();
    }

    public boolean isVar() {
        return getFir().getIsVar();
    }

    public void setAnnotations(List<? extends IrAnnotation> list) {
        list.getClass();
        this.annotations.setValue(this, $$delegatedProperties[0], list);
    }

    public void setAttributeOwnerId(IrElement irElement) {
        irElement.getClass();
        this.attributeOwnerId = irElement;
    }

    public void setBackingField(IrField irField) {
        this.backingField = irField;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setConst(boolean z) throws KotlinNothingValueException {
        AbstractFir2IrLazyDeclarationKt.mutationNotSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setDelegated(boolean z) throws KotlinNothingValueException {
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

    public void setFakeOverride(boolean z) {
        this.isFakeOverride = z;
    }

    public void setGetter(IrSimpleFunction irSimpleFunction) {
        this.getter = irSimpleFunction;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setLateinit(boolean z) throws KotlinNothingValueException {
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

    public void setOverriddenSymbols(List<? extends IrPropertySymbol> list) {
        list.getClass();
        this.overriddenSymbols.setValue(this, $$delegatedProperties[1], list);
    }

    public void setSetter(IrSimpleFunction irSimpleFunction) {
        this.setter = irSimpleFunction;
    }

    public void setStartOffset(int i) {
        this.startOffset = i;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setVar(boolean z) throws KotlinNothingValueException {
        AbstractFir2IrLazyDeclarationKt.mutationNotSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setVisibility(DescriptorVisibility descriptorVisibility) throws KotlinNothingValueException {
        descriptorVisibility.getClass();
        AbstractFir2IrLazyDeclarationKt.mutationNotSupported();
        throw new KotlinNothingValueException();
    }

    @Override // org.jetbrains.kotlin.fir.lazy.AbstractFir2IrLazyDeclaration
    public FirProperty getFir() {
        return this.fir;
    }

    /* JADX INFO: renamed from: getSymbol, reason: from getter and merged with bridge method [inline-methods] */
    public IrPropertySymbol m567getSymbol() {
        return this.symbol;
    }
}

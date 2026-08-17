package org.jetbrains.kotlin.fir.lazy;

import java.util.List;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.backend.common.IrSpecialAnnotationsProvider;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.state.InlineClassManglingUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirEvaluatorResult;
import org.jetbrains.kotlin.fir.FirSession;
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
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrLazyFakeOverrideGenerator;
import org.jetbrains.kotlin.fir.backend.utils.ConstantUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.ConversionTypeOrigin;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.lazy.Fir2IrLazyField;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.IrLock;
import org.jetbrains.kotlin.ir.IrProvider;
import org.jetbrains.kotlin.ir.ObsoleteDescriptorBasedAPI;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrFactoryHelpersKt;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.MetadataSource;
import org.jetbrains.kotlin.ir.declarations.lazy.LazyUtilKt;
import org.jetbrains.kotlin.ir.expressions.IrAnnotation;
import org.jetbrains.kotlin.ir.expressions.IrExpressionBody;
import org.jetbrains.kotlin.ir.symbols.IrFieldSymbol;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.util.KotlinMangler;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000ê\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\u00020\u0004BK\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\b\u001a\u00020\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0015\"\u0004\b\u0019\u0010\u0017R\u001a\u0010\t\u001a\u00020\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0014\u0010\u000b\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R7\u0010'\u001a\b\u0012\u0004\u0012\u00020&0%2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%8V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b,\u0010-\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001e\u0010.\u001a\u00020/8VX\u0097\u0004r\u0002\b4¢\u0006\f\u0012\u0004\b0\u00101\u001a\u0004\b2\u00103R$\u00107\u001a\u0002062\u0006\u00105\u001a\u0002068V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R$\u0010;\u001a\u0002062\u0006\u00105\u001a\u0002068V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b;\u00108\"\u0004\b<\u0010:R$\u0010=\u001a\u0002062\u0006\u00105\u001a\u0002068V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b=\u00108\"\u0004\b>\u0010:R$\u0010@\u001a\u00020?2\u0006\u00105\u001a\u00020?8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR$\u0010F\u001a\u00020E2\u0006\u00105\u001a\u00020E@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR$\u0010L\u001a\u00020K2\u0006\u00105\u001a\u00020K8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR/\u0010R\u001a\u0004\u0018\u00010Q2\b\u0010$\u001a\u0004\u0018\u00010Q8V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\bW\u0010-\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VR(\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u00105\u001a\u0004\u0018\u00010\u0011@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010Y\"\u0004\bZ\u0010[R\u001a\u0010\\\u001a\u00020]X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b^\u0010_\"\u0004\b`\u0010aR(\u0010c\u001a\u0004\u0018\u00010b2\b\u00105\u001a\u0004\u0018\u00010b8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bd\u0010e\"\u0004\bf\u0010gR\u0012\u0010h\u001a\u00020iX\u0096\u0005¢\u0006\u0006\u001a\u0004\bj\u0010kR\u0012\u0010l\u001a\u00020mX\u0096\u0005¢\u0006\u0006\u001a\u0004\bn\u0010oR\u0012\u0010p\u001a\u00020qX\u0096\u0005¢\u0006\u0006\u001a\u0004\br\u0010sR\u0012\u0010t\u001a\u00020uX\u0096\u0005¢\u0006\u0006\u001a\u0004\bv\u0010wR\u0012\u0010x\u001a\u00020yX\u0096\u0005¢\u0006\u0006\u001a\u0004\bz\u0010{R\u0012\u0010|\u001a\u00020}X\u0096\u0005¢\u0006\u0006\u001a\u0004\b~\u0010\u007fR\u0016\u0010\u0080\u0001\u001a\u00030\u0081\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001R\u0016\u0010\u0084\u0001\u001a\u00030\u0085\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0086\u0001\u0010\u0087\u0001R\u0016\u0010\u0088\u0001\u001a\u00030\u0089\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u008a\u0001\u0010\u008b\u0001R\u0016\u0010\u008c\u0001\u001a\u00030\u008d\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u008e\u0001\u0010\u008f\u0001R\u0016\u0010\u0090\u0001\u001a\u00030\u0091\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0092\u0001\u0010\u0093\u0001R\u0016\u0010\u0094\u0001\u001a\u00030\u0095\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0096\u0001\u0010\u0097\u0001R\u0016\u0010\u0098\u0001\u001a\u00030\u0099\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u009a\u0001\u0010\u009b\u0001R\u001f\u0010\u009c\u0001\u001a\f\u0012\u0005\u0012\u00030\u009e\u0001\u0018\u00010\u009d\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u009f\u0001\u0010 \u0001R\u0016\u0010¡\u0001\u001a\u00030¢\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b£\u0001\u0010¤\u0001R\u0016\u0010¥\u0001\u001a\u00030¦\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b§\u0001\u0010¨\u0001R\u0016\u0010©\u0001\u001a\u00030ª\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b«\u0001\u0010¬\u0001R\u001b\u0010\u00ad\u0001\u001a\t\u0012\u0005\u0012\u00030®\u00010%X\u0096\u0005¢\u0006\u0007\u001a\u0005\b¯\u0001\u0010)R\u0016\u0010°\u0001\u001a\u00030±\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b²\u0001\u0010³\u0001R\u0016\u0010´\u0001\u001a\u00030µ\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¶\u0001\u0010·\u0001R\u0016\u0010¸\u0001\u001a\u00030¹\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bº\u0001\u0010»\u0001R\u0016\u0010¼\u0001\u001a\u00030½\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¾\u0001\u0010¿\u0001R\u0016\u0010À\u0001\u001a\u00030Á\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÂ\u0001\u0010Ã\u0001R\u0018\u0010Ä\u0001\u001a\u0005\u0018\u00010Å\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÆ\u0001\u0010Ç\u0001R\u0016\u0010È\u0001\u001a\u00030É\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÊ\u0001\u0010Ë\u0001R\u0016\u0010Ì\u0001\u001a\u00030Í\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÎ\u0001\u0010Ï\u0001R\u0016\u0010Ð\u0001\u001a\u00030Ñ\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÒ\u0001\u0010Ó\u0001¨\u0006Ô\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/lazy/Fir2IrLazyField;", "Lorg/jetbrains/kotlin/ir/declarations/IrField;", "Lorg/jetbrains/kotlin/fir/lazy/AbstractFir2IrLazyDeclaration;", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "c", "startOffset", Argument.Delimiters.none, "endOffset", "origin", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "fir", "containingClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "symbol", "Lorg/jetbrains/kotlin/ir/symbols/IrFieldSymbol;", "correspondingPropertySymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;IILorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;Lorg/jetbrains/kotlin/fir/declarations/FirField;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;Lorg/jetbrains/kotlin/ir/symbols/IrFieldSymbol;Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;)V", "getStartOffset", "()I", "setStartOffset", "(I)V", "getEndOffset", "setEndOffset", "getOrigin", "()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "setOrigin", "(Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;)V", "getFir", "()Lorg/jetbrains/kotlin/fir/declarations/FirField;", "getContainingClass", "()Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "getSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrFieldSymbol;", "<set-?>", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/expressions/IrAnnotation;", "annotations", "getAnnotations", "()Ljava/util/List;", "setAnnotations", "(Ljava/util/List;)V", "annotations$delegate", "Lkotlin/properties/ReadWriteProperty;", "descriptor", "Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "getDescriptor$annotations", "()V", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "Lorg/jetbrains/kotlin/ir/ObsoleteDescriptorBasedAPI;", InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER, Argument.Delimiters.none, "isExternal", "()Z", "setExternal", "(Z)V", "isFinal", "setFinal", "isStatic", "setStatic", "Lorg/jetbrains/kotlin/name/Name;", ModuleXmlParser.NAME, "getName", "()Lorg/jetbrains/kotlin/name/Name;", "setName", "(Lorg/jetbrains/kotlin/name/Name;)V", "Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "visibility", "getVisibility", "()Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "setVisibility", "(Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;)V", "Lorg/jetbrains/kotlin/ir/types/IrType;", ModuleXmlParser.TYPE, "getType", "()Lorg/jetbrains/kotlin/ir/types/IrType;", "setType", "(Lorg/jetbrains/kotlin/ir/types/IrType;)V", "Lorg/jetbrains/kotlin/ir/expressions/IrExpressionBody;", "initializer", "getInitializer", "()Lorg/jetbrains/kotlin/ir/expressions/IrExpressionBody;", "setInitializer", "(Lorg/jetbrains/kotlin/ir/expressions/IrExpressionBody;)V", "initializer$delegate", "getCorrespondingPropertySymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;", "setCorrespondingPropertySymbol", "(Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;)V", "attributeOwnerId", "Lorg/jetbrains/kotlin/ir/IrElement;", "getAttributeOwnerId", "()Lorg/jetbrains/kotlin/ir/IrElement;", "setAttributeOwnerId", "(Lorg/jetbrains/kotlin/ir/IrElement;)V", "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource;", "metadata", "getMetadata", "()Lorg/jetbrains/kotlin/ir/declarations/MetadataSource;", "setMetadata", "(Lorg/jetbrains/kotlin/ir/declarations/MetadataSource;)V", "adapterGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "getAdapterGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "annotationGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "getAnnotationGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "annotationsFromPluginRegistrar", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "getAnnotationsFromPluginRegistrar", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "builtins", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "getBuiltins", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "callGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "getCallGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "callablesGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "getCallablesGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "classifierStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "getClassifierStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "classifiersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "getClassifiersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "configuration", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "getConfiguration", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "converter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "getConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "dataClassMembersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "getDataClassMembersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "declarationStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "getDeclarationStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "extensions", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "getExtensions", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "filesBeingCompiled", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "getFilesBeingCompiled", "()Ljava/util/Set;", "firProvider", "Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "getFirProvider", "()Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "implicitCastInserter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "getImplicitCastInserter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "irMangler", "Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "getIrMangler", "()Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "irProviders", "Lorg/jetbrains/kotlin/ir/IrProvider;", "getIrProviders", "lazyDeclarationsGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "getLazyDeclarationsGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "lazyFakeOverrideGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "getLazyFakeOverrideGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "lock", "Lorg/jetbrains/kotlin/ir/IrLock;", "getLock", "()Lorg/jetbrains/kotlin/ir/IrLock;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "specialAnnotationsProvider", "Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "getSpecialAnnotationsProvider", "()Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "symbolsMappingForLazyClasses", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "getSymbolsMappingForLazyClasses", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "typeConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "getTypeConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "visibilityConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "getVisibilityConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrLazyField extends IrField implements Fir2IrComponents, AbstractFir2IrLazyDeclaration<FirField> {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(Fir2IrLazyField.class, "annotations", "getAnnotations()Ljava/util/List;", 0), new MutablePropertyReference1Impl<>(Fir2IrLazyField.class, "initializer", "getInitializer()Lorg/jetbrains/kotlin/ir/expressions/IrExpressionBody;", 0)};

    /* JADX INFO: renamed from: annotations$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty annotations;
    private IrElement attributeOwnerId;
    private final Fir2IrComponents c;
    private final FirRegularClass containingClass;
    private IrPropertySymbol correspondingPropertySymbol;
    private int endOffset;
    private final FirField fir;

    /* JADX INFO: renamed from: initializer$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty initializer;
    private IrDeclarationOrigin origin;
    private int startOffset;
    private final IrFieldSymbol symbol;
    private DescriptorVisibility visibility;

    public Fir2IrLazyField(Fir2IrComponents fir2IrComponents, int i, int i2, IrDeclarationOrigin irDeclarationOrigin, FirField firField, FirRegularClass firRegularClass, IrFieldSymbol irFieldSymbol, IrPropertySymbol irPropertySymbol) {
        fir2IrComponents.getClass();
        irDeclarationOrigin.getClass();
        firField.getClass();
        irFieldSymbol.getClass();
        this.c = fir2IrComponents;
        this.startOffset = i;
        this.endOffset = i2;
        this.origin = irDeclarationOrigin;
        this.fir = firField;
        this.containingClass = firRegularClass;
        this.symbol = irFieldSymbol;
        m565getSymbol().bind(this);
        this.annotations = createLazyAnnotations();
        this.visibility = fir2IrComponents.getVisibilityConverter().convertToDescriptorVisibility(getFir().getStatus().getVisibility());
        this.initializer = LazyUtilKt.lazyVar(getLock(), new Function0() { // from class: cw4
            public final Object invoke() {
                return Fir2IrLazyField.e(this.b);
            }
        });
        this.correspondingPropertySymbol = irPropertySymbol;
        this.attributeOwnerId = this;
    }

    public static IrExpressionBody e(Fir2IrLazyField fir2IrLazyField) {
        FirExpression initializer;
        FirEvaluatorResult evaluatedInitializer;
        FirCallableDeclaration fir = fir2IrLazyField.getFir();
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(fir) || (fir.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(fir) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(fir) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(fir) : null;
            }
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            fir = originalForSubstitutionOverrideAttr;
        }
        FirField firField = (FirField) fir;
        FirCallableDeclaration propertyIfBackingField = ClassMembersKt.getPropertyIfBackingField(firField);
        FirProperty firProperty = propertyIfBackingField instanceof FirProperty ? (FirProperty) propertyIfBackingField : null;
        if (firProperty == null || (evaluatedInitializer = DeclarationAttributesKt.getEvaluatedInitializer(firProperty)) == null || (evaluatedInitializer instanceof FirEvaluatorResult.CompileTimeException) || !(evaluatedInitializer instanceof FirEvaluatorResult.Evaluated)) {
            initializer = null;
        } else {
            FirElement result = ((FirEvaluatorResult.Evaluated) evaluatedInitializer).getResult();
            if (!(result instanceof FirExpression)) {
                result = null;
            }
            initializer = (FirExpression) result;
        }
        if (initializer == null) {
            initializer = firField.getInitializer();
        }
        if (initializer instanceof FirLiteralExpression) {
            return IrFactoryHelpersKt.createExpressionBody(fir2IrLazyField.getFactory(), ConstantUtilsKt.toIrConst((FirLiteralExpression) initializer, fir2IrLazyField.getType()));
        }
        return null;
    }

    @ObsoleteDescriptorBasedAPI
    public static /* synthetic */ void getDescriptor$annotations() {
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

    public final FirRegularClass getContainingClass() {
        return this.containingClass;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrConverter getConverter() {
        return this.c.getConverter();
    }

    public IrPropertySymbol getCorrespondingPropertySymbol() {
        return this.correspondingPropertySymbol;
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
        return m565getSymbol().getDescriptor();
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

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrImplicitCastInserter getImplicitCastInserter() {
        return this.c.getImplicitCastInserter();
    }

    public IrExpressionBody getInitializer() {
        return (IrExpressionBody) this.initializer.getValue(this, $$delegatedProperties[1]);
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

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents, org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.c.getSession();
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

    public IrType getType() {
        return Fir2IrTypeConverterKt.toIrType$default(this, getFir().getReturnTypeRef(), (ConversionTypeOrigin) null, 2, (Object) null);
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

    public boolean isExternal() {
        return getFir().getStatus().isExternal();
    }

    public boolean isFinal() {
        return getFir().getIsVal();
    }

    public boolean isStatic() {
        return getFir().getStatus().isStatic();
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
    public void setCorrespondingPropertySymbol(IrPropertySymbol irPropertySymbol) throws KotlinNothingValueException {
        AbstractFir2IrLazyDeclarationKt.mutationNotSupported();
        throw new KotlinNothingValueException();
    }

    public void setEndOffset(int i) {
        this.endOffset = i;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setExternal(boolean z) throws KotlinNothingValueException {
        AbstractFir2IrLazyDeclarationKt.mutationNotSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setFinal(boolean z) throws KotlinNothingValueException {
        AbstractFir2IrLazyDeclarationKt.mutationNotSupported();
        throw new KotlinNothingValueException();
    }

    public void setInitializer(IrExpressionBody irExpressionBody) {
        this.initializer.setValue(this, $$delegatedProperties[1], irExpressionBody);
    }

    public void setMetadata(MetadataSource metadataSource) {
        throw new IllegalStateException("We should never need to store metadata of external declarations.");
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

    public void setStartOffset(int i) {
        this.startOffset = i;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setStatic(boolean z) throws KotlinNothingValueException {
        AbstractFir2IrLazyDeclarationKt.mutationNotSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setType(IrType irType) throws KotlinNothingValueException {
        irType.getClass();
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
    public FirField getFir() {
        return this.fir;
    }

    /* JADX INFO: renamed from: getSymbol, reason: from getter and merged with bridge method [inline-methods] */
    public IrFieldSymbol m565getSymbol() {
        return this.symbol;
    }
}

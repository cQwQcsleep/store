package org.jetbrains.kotlin.fir.lazy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
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
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
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
import org.jetbrains.kotlin.fir.backend.Fir2IrSymbolsMappingForLazyClassesKt;
import org.jetbrains.kotlin.fir.backend.Fir2IrTypeConverter;
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
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.lazy.Fir2IrLazyPropertyForPureField;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.IrLock;
import org.jetbrains.kotlin.ir.IrProvider;
import org.jetbrains.kotlin.ir.ObsoleteDescriptorBasedAPI;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent;
import org.jetbrains.kotlin.ir.declarations.IrFactory;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.MetadataSource;
import org.jetbrains.kotlin.ir.declarations.impl.IrFactoryImpl;
import org.jetbrains.kotlin.ir.expressions.IrAnnotation;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.util.IrFakeOverrideUtilsKt;
import org.jetbrains.kotlin.ir.util.KotlinMangler;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000ü\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bR\u000e\u0010\u0003\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR7\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u000f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000f8V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R0\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u000f2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u000f8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u001a\u0010\u0012\"\u0004\b\u001b\u0010\u0014R$\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0017\u001a\u00020\u001c8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R$\u0010\"\u001a\u00020\u001c2\u0006\u0010\u0017\u001a\u00020\u001c8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b#\u0010\u001f\"\u0004\b$\u0010!R$\u0010&\u001a\u00020%2\u0006\u0010\u0017\u001a\u00020%8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001e\u0010+\u001a\u00020,8VX\u0097\u0004r\u0002\b1¢\u0006\f\u0012\u0004\b-\u0010.\u001a\u0004\b/\u00100R$\u00103\u001a\u0002022\u0006\u0010\u0017\u001a\u0002028V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b3\u00104\"\u0004\b5\u00106R$\u00107\u001a\u0002022\u0006\u0010\u0017\u001a\u0002028V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b7\u00104\"\u0004\b8\u00106R$\u00109\u001a\u0002022\u0006\u0010\u0017\u001a\u0002028V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b9\u00104\"\u0004\b:\u00106R$\u0010;\u001a\u0002022\u0006\u0010\u0017\u001a\u0002028V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b;\u00104\"\u0004\b<\u00106R$\u0010=\u001a\u0002022\u0006\u0010\u0017\u001a\u0002028V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b=\u00104\"\u0004\b>\u00106R$\u0010?\u001a\u0002022\u0006\u0010\u0017\u001a\u0002028V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b?\u00104\"\u0004\b@\u00106R$\u0010B\u001a\u00020A2\u0006\u0010\u0017\u001a\u00020A8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR$\u0010H\u001a\u00020G2\u0006\u0010\u0017\u001a\u00020G8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bI\u0010J\"\u0004\bK\u0010LR$\u0010N\u001a\u00020M2\u0006\u0010\u0017\u001a\u00020M8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010RR(\u0010T\u001a\u0004\u0018\u00010S2\b\u0010\u0017\u001a\u0004\u0018\u00010S8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bU\u0010V\"\u0004\bW\u0010XR(\u0010Z\u001a\u0004\u0018\u00010Y2\b\u0010\u0017\u001a\u0004\u0018\u00010Y8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b[\u0010\\\"\u0004\b]\u0010^R(\u0010_\u001a\u0004\u0018\u00010Y2\b\u0010\u0017\u001a\u0004\u0018\u00010Y8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b`\u0010\\\"\u0004\ba\u0010^R$\u0010b\u001a\u0002022\u0006\u0010\u0017\u001a\u0002028V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bb\u00104\"\u0004\bc\u00106R(\u0010e\u001a\u0004\u0018\u00010d2\b\u0010\u0017\u001a\u0004\u0018\u00010d8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bf\u0010g\"\u0004\bh\u0010iR\u0016\u0010j\u001a\u0004\u0018\u00010k8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bl\u0010mR\u001a\u0010n\u001a\u00020oX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bp\u0010q\"\u0004\br\u0010sR\u0014\u0010t\u001a\u00020u8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bv\u0010wR\u0012\u0010x\u001a\u00020yX\u0096\u0005¢\u0006\u0006\u001a\u0004\bz\u0010{R\u0012\u0010|\u001a\u00020}X\u0096\u0005¢\u0006\u0006\u001a\u0004\b~\u0010\u007fR\u0016\u0010\u0080\u0001\u001a\u00030\u0081\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001R\u0016\u0010\u0084\u0001\u001a\u00030\u0085\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0086\u0001\u0010\u0087\u0001R\u0016\u0010\u0088\u0001\u001a\u00030\u0089\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u008a\u0001\u0010\u008b\u0001R\u0016\u0010\u008c\u0001\u001a\u00030\u008d\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u008e\u0001\u0010\u008f\u0001R\u0016\u0010\u0090\u0001\u001a\u00030\u0091\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0092\u0001\u0010\u0093\u0001R\u0016\u0010\u0094\u0001\u001a\u00030\u0095\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0096\u0001\u0010\u0097\u0001R\u0016\u0010\u0098\u0001\u001a\u00030\u0099\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u009a\u0001\u0010\u009b\u0001R\u0016\u0010\u009c\u0001\u001a\u00030\u009d\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u009e\u0001\u0010\u009f\u0001R\u0016\u0010 \u0001\u001a\u00030¡\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¢\u0001\u0010£\u0001R\u0016\u0010¤\u0001\u001a\u00030¥\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¦\u0001\u0010§\u0001R\u0016\u0010¨\u0001\u001a\u00030©\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bª\u0001\u0010«\u0001R\u001f\u0010¬\u0001\u001a\f\u0012\u0005\u0012\u00030®\u0001\u0018\u00010\u00ad\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¯\u0001\u0010°\u0001R\u0016\u0010±\u0001\u001a\u00030²\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b³\u0001\u0010´\u0001R\u0016\u0010µ\u0001\u001a\u00030¶\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b·\u0001\u0010¸\u0001R\u0016\u0010¹\u0001\u001a\u00030º\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b»\u0001\u0010¼\u0001R\u001b\u0010½\u0001\u001a\t\u0012\u0005\u0012\u00030¾\u00010\u000fX\u0096\u0005¢\u0006\u0007\u001a\u0005\b¿\u0001\u0010\u0012R\u0016\u0010À\u0001\u001a\u00030Á\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÂ\u0001\u0010Ã\u0001R\u0016\u0010Ä\u0001\u001a\u00030Å\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÆ\u0001\u0010Ç\u0001R\u0016\u0010È\u0001\u001a\u00030É\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÊ\u0001\u0010Ë\u0001R\u0016\u0010Ì\u0001\u001a\u00030Í\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÎ\u0001\u0010Ï\u0001R\u0016\u0010Ð\u0001\u001a\u00030Ñ\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÒ\u0001\u0010Ó\u0001R\u0018\u0010Ô\u0001\u001a\u0005\u0018\u00010Õ\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÖ\u0001\u0010×\u0001R\u0016\u0010Ø\u0001\u001a\u00030Ù\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÚ\u0001\u0010Û\u0001R\u0016\u0010Ü\u0001\u001a\u00030Ý\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÞ\u0001\u0010ß\u0001R\u0016\u0010à\u0001\u001a\u00030á\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bâ\u0001\u0010ã\u0001¨\u0006ä\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/lazy/Fir2IrLazyPropertyForPureField;", "Lorg/jetbrains/kotlin/ir/declarations/IrProperty;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "c", "field", "Lorg/jetbrains/kotlin/fir/lazy/Fir2IrLazyField;", "symbol", "Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;", "parent", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationParent;", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/lazy/Fir2IrLazyField;Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationParent;)V", "getSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;", "<set-?>", Argument.Delimiters.none, "overriddenSymbols", "getOverriddenSymbols", "()Ljava/util/List;", "setOverriddenSymbols", "(Ljava/util/List;)V", "overriddenSymbols$delegate", "Lkotlin/properties/ReadWriteProperty;", InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER, "Lorg/jetbrains/kotlin/ir/expressions/IrAnnotation;", "annotations", "getAnnotations", "setAnnotations", Argument.Delimiters.none, "startOffset", "getStartOffset", "()I", "setStartOffset", "(I)V", "endOffset", "getEndOffset", "setEndOffset", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "origin", "getOrigin", "()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "setOrigin", "(Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;)V", "descriptor", "Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "getDescriptor$annotations", "()V", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "Lorg/jetbrains/kotlin/ir/ObsoleteDescriptorBasedAPI;", Argument.Delimiters.none, "isVar", "()Z", "setVar", "(Z)V", "isConst", "setConst", "isLateinit", "setLateinit", "isDelegated", "setDelegated", "isExternal", "setExternal", "isExpect", "setExpect", "Lorg/jetbrains/kotlin/name/Name;", ModuleXmlParser.NAME, "getName", "()Lorg/jetbrains/kotlin/name/Name;", "setName", "(Lorg/jetbrains/kotlin/name/Name;)V", "Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "visibility", "getVisibility", "()Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "setVisibility", "(Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;)V", "Lorg/jetbrains/kotlin/descriptors/Modality;", "modality", "getModality", "()Lorg/jetbrains/kotlin/descriptors/Modality;", "setModality", "(Lorg/jetbrains/kotlin/descriptors/Modality;)V", "Lorg/jetbrains/kotlin/ir/declarations/IrField;", "backingField", "getBackingField", "()Lorg/jetbrains/kotlin/ir/declarations/IrField;", "setBackingField", "(Lorg/jetbrains/kotlin/ir/declarations/IrField;)V", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "getter", "getGetter", "()Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "setGetter", "(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;)V", "setter", "getSetter", "setSetter", "isFakeOverride", "setFakeOverride", "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource;", "metadata", "getMetadata", "()Lorg/jetbrains/kotlin/ir/declarations/MetadataSource;", "setMetadata", "(Lorg/jetbrains/kotlin/ir/declarations/MetadataSource;)V", "containerSource", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "getContainerSource", "()Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "attributeOwnerId", "Lorg/jetbrains/kotlin/ir/IrElement;", "getAttributeOwnerId", "()Lorg/jetbrains/kotlin/ir/IrElement;", "setAttributeOwnerId", "(Lorg/jetbrains/kotlin/ir/IrElement;)V", "factory", "Lorg/jetbrains/kotlin/ir/declarations/IrFactory;", "getFactory", "()Lorg/jetbrains/kotlin/ir/declarations/IrFactory;", "adapterGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "getAdapterGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "annotationGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "getAnnotationGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "annotationsFromPluginRegistrar", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "getAnnotationsFromPluginRegistrar", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "builtins", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "getBuiltins", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "callGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "getCallGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "callablesGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "getCallablesGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "classifierStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "getClassifierStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "classifiersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "getClassifiersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "configuration", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "getConfiguration", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "converter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "getConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "dataClassMembersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "getDataClassMembersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "declarationStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "getDeclarationStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "extensions", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "getExtensions", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "filesBeingCompiled", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "getFilesBeingCompiled", "()Ljava/util/Set;", "firProvider", "Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "getFirProvider", "()Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "implicitCastInserter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "getImplicitCastInserter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "irMangler", "Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "getIrMangler", "()Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "irProviders", "Lorg/jetbrains/kotlin/ir/IrProvider;", "getIrProviders", "lazyDeclarationsGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "getLazyDeclarationsGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "lazyFakeOverrideGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "getLazyFakeOverrideGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "lock", "Lorg/jetbrains/kotlin/ir/IrLock;", "getLock", "()Lorg/jetbrains/kotlin/ir/IrLock;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "specialAnnotationsProvider", "Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "getSpecialAnnotationsProvider", "()Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "symbolsMappingForLazyClasses", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "getSymbolsMappingForLazyClasses", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "typeConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "getTypeConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "visibilityConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "getVisibilityConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrLazyPropertyForPureField extends IrProperty implements Fir2IrComponents {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(Fir2IrLazyPropertyForPureField.class, "overriddenSymbols", "getOverriddenSymbols()Ljava/util/List;", 0)};
    private IrElement attributeOwnerId;
    private final Fir2IrComponents c;
    private final Fir2IrLazyField field;

    /* JADX INFO: renamed from: overriddenSymbols$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty overriddenSymbols;
    private final IrPropertySymbol symbol;

    public Fir2IrLazyPropertyForPureField(Fir2IrComponents fir2IrComponents, Fir2IrLazyField fir2IrLazyField, IrPropertySymbol irPropertySymbol, IrDeclarationParent irDeclarationParent) {
        fir2IrComponents.getClass();
        fir2IrLazyField.getClass();
        irPropertySymbol.getClass();
        irDeclarationParent.getClass();
        this.c = fir2IrComponents;
        this.field = fir2IrLazyField;
        this.symbol = irPropertySymbol;
        setParent(irDeclarationParent);
        m569getSymbol().bind(this);
        this.overriddenSymbols = Fir2IrSymbolsMappingForLazyClassesKt.lazyMappedPropertyListVar(getSymbolsMappingForLazyClasses(), getLock(), new Function0() { // from class: jw4
            public final Object invoke() {
                return Fir2IrLazyPropertyForPureField.e(this.b);
            }
        });
        this.attributeOwnerId = this;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static List e(Fir2IrLazyPropertyForPureField fir2IrLazyPropertyForPureField) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirRegularClass containingClass = fir2IrLazyPropertyForPureField.field.getContainingClass();
        if (containingClass == null) {
            return CollectionsKt.emptyList();
        }
        List<Pair<FirFieldSymbol, ConeClassLikeLookupTag>> listComputeFakeOverrideKeys$org_jetbrains_kotlin_fir2ir = fir2IrLazyPropertyForPureField.getLazyFakeOverrideGenerator().computeFakeOverrideKeys$org_jetbrains_kotlin_fir2ir(containingClass, fir2IrLazyPropertyForPureField.field.getFir().getSymbol());
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listComputeFakeOverrideKeys$org_jetbrains_kotlin_fir2ir, 10));
        Iterator<T> it = listComputeFakeOverrideKeys$org_jetbrains_kotlin_fir2ir.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            IrPropertySymbol irSymbolForField = fir2IrLazyPropertyForPureField.getDeclarationStorage().getIrSymbolForField((FirFieldSymbol) pair.component1(), (ConeClassLikeLookupTag) pair.component2());
            irSymbolForField.getClass();
            arrayList.add(irSymbolForField);
        }
        return arrayList;
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
        return CollectionsKt.emptyList();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrIrGeneratedDeclarationsRegistrar getAnnotationsFromPluginRegistrar() {
        return this.c.getAnnotationsFromPluginRegistrar();
    }

    public IrElement getAttributeOwnerId() {
        return this.attributeOwnerId;
    }

    public IrField getBackingField() {
        return this.field;
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
        return null;
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
        return m569getSymbol().getDescriptor();
    }

    public int getEndOffset() {
        return this.field.getEndOffset();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrExtensions getExtensions() {
        return this.c.getExtensions();
    }

    public IrFactory getFactory() {
        return IrFactoryImpl.INSTANCE;
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
        return null;
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
        return Modality.FINAL;
    }

    public Name getName() {
        return this.field.getName();
    }

    public IrDeclarationOrigin getOrigin() {
        return this.field.getOrigin();
    }

    public List<IrPropertySymbol> getOverriddenSymbols() {
        return (List) this.overriddenSymbols.getValue(this, $$delegatedProperties[0]);
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
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public IrSpecialAnnotationsProvider getSpecialAnnotationsProvider() {
        return this.c.getSpecialAnnotationsProvider();
    }

    public int getStartOffset() {
        return this.field.getStartOffset();
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
        return this.field.getVisibility();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrVisibilityConverter getVisibilityConverter() {
        return this.c.getVisibilityConverter();
    }

    public boolean isConst() {
        return this.field.isStatic() && this.field.isFinal();
    }

    public boolean isDelegated() {
        return false;
    }

    public boolean isExpect() {
        return false;
    }

    public boolean isExternal() {
        return false;
    }

    public boolean isFakeOverride() {
        return IrFakeOverrideUtilsKt.isFakeOverride(this.field);
    }

    public boolean isLateinit() {
        return false;
    }

    public boolean isVar() {
        return !this.field.isFinal();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setAnnotations(List<? extends IrAnnotation> list) throws KotlinNothingValueException {
        list.getClass();
        AbstractFir2IrLazyDeclarationKt.mutationNotSupported();
        throw new KotlinNothingValueException();
    }

    public void setAttributeOwnerId(IrElement irElement) {
        irElement.getClass();
        this.attributeOwnerId = irElement;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setBackingField(IrField irField) throws KotlinNothingValueException {
        AbstractFir2IrLazyDeclarationKt.mutationNotSupported();
        throw new KotlinNothingValueException();
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

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setEndOffset(int i) throws KotlinNothingValueException {
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        throw new KotlinNothingValueException();
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
    public void setFakeOverride(boolean z) throws KotlinNothingValueException {
        AbstractFir2IrLazyDeclarationKt.mutationNotSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setGetter(IrSimpleFunction irSimpleFunction) throws KotlinNothingValueException {
        AbstractFir2IrLazyDeclarationKt.mutationNotSupported();
        throw new KotlinNothingValueException();
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

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setOrigin(IrDeclarationOrigin irDeclarationOrigin) throws KotlinNothingValueException {
        irDeclarationOrigin.getClass();
        AbstractFir2IrLazyDeclarationKt.mutationNotSupported();
        throw new KotlinNothingValueException();
    }

    public void setOverriddenSymbols(List<? extends IrPropertySymbol> list) {
        list.getClass();
        this.overriddenSymbols.setValue(this, $$delegatedProperties[0], list);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setSetter(IrSimpleFunction irSimpleFunction) throws KotlinNothingValueException {
        AbstractFir2IrLazyDeclarationKt.mutationNotSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setStartOffset(int i) throws KotlinNothingValueException {
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        throw new KotlinNothingValueException();
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

    /* JADX INFO: renamed from: getSymbol, reason: from getter and merged with bridge method [inline-methods] */
    public IrPropertySymbol m569getSymbol() {
        return this.symbol;
    }
}

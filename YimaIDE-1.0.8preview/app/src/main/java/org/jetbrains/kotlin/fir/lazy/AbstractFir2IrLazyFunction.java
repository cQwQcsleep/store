package org.jetbrains.kotlin.fir.lazy;

import java.util.List;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.backend.common.IrSpecialAnnotationsProvider;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.state.InlineClassManglingUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.Modality;
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
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.lazy.AbstractFir2IrLazyFunction;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.IrLock;
import org.jetbrains.kotlin.ir.IrProvider;
import org.jetbrains.kotlin.ir.ObsoleteDescriptorBasedAPI;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent;
import org.jetbrains.kotlin.ir.declarations.IrFactory;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrTypeParameter;
import org.jetbrains.kotlin.ir.declarations.MetadataSource;
import org.jetbrains.kotlin.ir.declarations.lazy.IrLazyFunctionBase;
import org.jetbrains.kotlin.ir.declarations.lazy.LazyUtilKt;
import org.jetbrains.kotlin.ir.expressions.IrAnnotation;
import org.jetbrains.kotlin.ir.expressions.IrBody;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.util.KotlinMangler;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u008a\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u00032\u00020\u00042\b\u0012\u0004\u0012\u0002H\u00010\u00052\u00020\u00062\u00020\u0007B?\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010m\u001a\u00020\u00132\u0006\u0010n\u001a\u00020oH\u0000¢\u0006\u0002\bpJ\u001c\u0010u\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010w\u0012\n\u0012\b\u0012\u0004\u0012\u00020x0(0vH\u0016R\u0014\u0010\b\u001a\u00020\u0007X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\t\u001a\u00020\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u000b\u001a\u00020\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0019\"\u0004\b\u001d\u0010\u001bR\u001a\u0010\f\u001a\u00020\rX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u001a\u0010\u0012\u001a\u00020\u0013X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010$\"\u0004\b%\u0010&R \u0010'\u001a\b\u0012\u0004\u0012\u00020)0(X\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R$\u0010/\u001a\u00020\u00132\u0006\u0010.\u001a\u00020\u00138V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b/\u0010$\"\u0004\b0\u0010&R$\u00101\u001a\u00020\u00132\u0006\u0010.\u001a\u00020\u00138V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b1\u0010$\"\u0004\b2\u0010&R$\u00103\u001a\u00020\u00132\u0006\u0010.\u001a\u00020\u00138V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b3\u0010$\"\u0004\b4\u0010&R$\u00105\u001a\u00020\u00132\u0006\u0010.\u001a\u00020\u00138V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b5\u0010$\"\u0004\b6\u0010&R\u001e\u00107\u001a\u0002088VX\u0097\u0004r\u0002\b=¢\u0006\f\u0012\u0004\b9\u0010:\u001a\u0004\b;\u0010<R$\u0010>\u001a\u00020\u00132\u0006\u0010.\u001a\u00020\u00138V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b>\u0010$\"\u0004\b?\u0010&R$\u0010@\u001a\u00020\u00132\u0006\u0010.\u001a\u00020\u00138V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b@\u0010$\"\u0004\bA\u0010&R$\u0010B\u001a\u00020\u00132\u0006\u0010.\u001a\u00020\u00138V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bB\u0010$\"\u0004\bC\u0010&R(\u0010F\u001a\u0004\u0018\u00010E2\b\u0010D\u001a\u0004\u0018\u00010E8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR1\u0010M\u001a\u00020L2\u0006\u0010K\u001a\u00020L8V@VX\u0096\u008e\u0002¢\u0006\u0018\n\u0004\bS\u0010T\u0012\u0004\bN\u0010:\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010RR$\u0010V\u001a\u00020U2\u0006\u0010.\u001a\u00020U8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bW\u0010X\"\u0004\bY\u0010ZR\u001c\u0010[\u001a\u0004\u0018\u00010\\X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b]\u0010^\"\u0004\b_\u0010`R\u001a\u0010a\u001a\u00020bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bc\u0010d\"\u0004\be\u0010fR(\u0010h\u001a\u0004\u0018\u00010g2\b\u0010.\u001a\u0004\u0018\u00010g8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bi\u0010j\"\u0004\bk\u0010lR\u0014\u0010q\u001a\u00020r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bs\u0010tR\u0012\u0010y\u001a\u00020zX\u0096\u0005¢\u0006\u0006\u001a\u0004\b{\u0010|R\u0013\u0010}\u001a\u00020~X\u0096\u0005¢\u0006\u0007\u001a\u0005\b\u007f\u0010\u0080\u0001R\u0016\u0010\u0081\u0001\u001a\u00030\u0082\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0083\u0001\u0010\u0084\u0001R\u0016\u0010\u0085\u0001\u001a\u00030\u0086\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0087\u0001\u0010\u0088\u0001R\u0016\u0010\u0089\u0001\u001a\u00030\u008a\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u008b\u0001\u0010\u008c\u0001R\u0016\u0010\u008d\u0001\u001a\u00030\u008e\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u008f\u0001\u0010\u0090\u0001R\u0016\u0010\u0091\u0001\u001a\u00030\u0092\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0093\u0001\u0010\u0094\u0001R\u0016\u0010\u0095\u0001\u001a\u00030\u0096\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0097\u0001\u0010\u0098\u0001R\u0016\u0010\u0099\u0001\u001a\u00030\u009a\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u009b\u0001\u0010\u009c\u0001R\u0016\u0010\u009d\u0001\u001a\u00030\u009e\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u009f\u0001\u0010 \u0001R\u0016\u0010¡\u0001\u001a\u00030¢\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b£\u0001\u0010¤\u0001R\u0016\u0010¥\u0001\u001a\u00030¦\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b§\u0001\u0010¨\u0001R\u0016\u0010©\u0001\u001a\u00030ª\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b«\u0001\u0010¬\u0001R\u001f\u0010\u00ad\u0001\u001a\f\u0012\u0005\u0012\u00030¯\u0001\u0018\u00010®\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b°\u0001\u0010±\u0001R\u0016\u0010²\u0001\u001a\u00030³\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b´\u0001\u0010µ\u0001R\u0016\u0010¶\u0001\u001a\u00030·\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¸\u0001\u0010¹\u0001R\u0016\u0010º\u0001\u001a\u00030»\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¼\u0001\u0010½\u0001R\u001b\u0010¾\u0001\u001a\t\u0012\u0005\u0012\u00030¿\u00010(X\u0096\u0005¢\u0006\u0007\u001a\u0005\bÀ\u0001\u0010+R\u0016\u0010Á\u0001\u001a\u00030Â\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÃ\u0001\u0010Ä\u0001R\u0016\u0010Å\u0001\u001a\u00030Æ\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÇ\u0001\u0010È\u0001R\u0016\u0010É\u0001\u001a\u00030Ê\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bË\u0001\u0010Ì\u0001R\u0016\u0010Í\u0001\u001a\u00030Î\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÏ\u0001\u0010Ð\u0001R\u0016\u0010Ñ\u0001\u001a\u00030Ò\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÓ\u0001\u0010Ô\u0001R\u0018\u0010Õ\u0001\u001a\u0005\u0018\u00010Ö\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b×\u0001\u0010Ø\u0001R\u0016\u0010Ù\u0001\u001a\u00030Ú\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÛ\u0001\u0010Ü\u0001R\u0016\u0010Ý\u0001\u001a\u00030Þ\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bß\u0001\u0010à\u0001R\u0016\u0010á\u0001\u001a\u00030â\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bã\u0001\u0010ä\u0001¨\u0006å\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/lazy/AbstractFir2IrLazyFunction;", "F", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "Lorg/jetbrains/kotlin/ir/declarations/lazy/IrLazyFunctionBase;", "Lorg/jetbrains/kotlin/fir/lazy/AbstractFir2IrLazyDeclaration;", "Lorg/jetbrains/kotlin/fir/lazy/Fir2IrTypeParametersContainer;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "c", "startOffset", Argument.Delimiters.none, "endOffset", "origin", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "symbol", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "parent", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationParent;", "isFakeOverride", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;IILorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationParent;Z)V", "getC", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "getStartOffset", "()I", "setStartOffset", "(I)V", "getEndOffset", "setEndOffset", "getOrigin", "()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "setOrigin", "(Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;)V", "getSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "()Z", "setFakeOverride", "(Z)V", "typeParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/declarations/IrTypeParameter;", "getTypeParameters", "()Ljava/util/List;", "setTypeParameters", "(Ljava/util/List;)V", InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER, "isTailrec", "setTailrec", "isSuspend", "setSuspend", "isOperator", "setOperator", "isInfix", "setInfix", "descriptor", "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "getDescriptor$annotations", "()V", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "Lorg/jetbrains/kotlin/ir/ObsoleteDescriptorBasedAPI;", "isInline", "setInline", "isExternal", "setExternal", "isExpect", "setExpect", "value", "Lorg/jetbrains/kotlin/ir/expressions/IrBody;", "body", "getBody", "()Lorg/jetbrains/kotlin/ir/expressions/IrBody;", "setBody", "(Lorg/jetbrains/kotlin/ir/expressions/IrBody;)V", "<set-?>", "Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "visibility", "getVisibility$annotations", "getVisibility", "()Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "setVisibility", "(Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;)V", "visibility$delegate", "Lkotlin/properties/ReadWriteProperty;", "Lorg/jetbrains/kotlin/descriptors/Modality;", "modality", "getModality", "()Lorg/jetbrains/kotlin/descriptors/Modality;", "setModality", "(Lorg/jetbrains/kotlin/descriptors/Modality;)V", "correspondingPropertySymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;", "getCorrespondingPropertySymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;", "setCorrespondingPropertySymbol", "(Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;)V", "attributeOwnerId", "Lorg/jetbrains/kotlin/ir/IrElement;", "getAttributeOwnerId", "()Lorg/jetbrains/kotlin/ir/IrElement;", "setAttributeOwnerId", "(Lorg/jetbrains/kotlin/ir/IrElement;)V", "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource;", "metadata", "getMetadata", "()Lorg/jetbrains/kotlin/ir/declarations/MetadataSource;", "setMetadata", "(Lorg/jetbrains/kotlin/ir/declarations/MetadataSource;)V", "shouldHaveDispatchReceiver", "containingClass", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "shouldHaveDispatchReceiver$org_jetbrains_kotlin_fir2ir", "factory", "Lorg/jetbrains/kotlin/ir/declarations/IrFactory;", "getFactory", "()Lorg/jetbrains/kotlin/ir/declarations/IrFactory;", "createLazyAnnotations", "Lkotlin/properties/ReadWriteProperty;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/expressions/IrAnnotation;", "adapterGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "getAdapterGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "annotationGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "getAnnotationGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "annotationsFromPluginRegistrar", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "getAnnotationsFromPluginRegistrar", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "builtins", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "getBuiltins", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "callGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "getCallGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "callablesGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "getCallablesGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "classifierStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "getClassifierStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "classifiersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "getClassifiersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "configuration", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "getConfiguration", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "converter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "getConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "dataClassMembersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "getDataClassMembersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "declarationStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "getDeclarationStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "extensions", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "getExtensions", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "filesBeingCompiled", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "getFilesBeingCompiled", "()Ljava/util/Set;", "firProvider", "Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "getFirProvider", "()Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "implicitCastInserter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "getImplicitCastInserter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "irMangler", "Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "getIrMangler", "()Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "irProviders", "Lorg/jetbrains/kotlin/ir/IrProvider;", "getIrProviders", "lazyDeclarationsGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "getLazyDeclarationsGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "lazyFakeOverrideGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "getLazyFakeOverrideGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "lock", "Lorg/jetbrains/kotlin/ir/IrLock;", "getLock", "()Lorg/jetbrains/kotlin/ir/IrLock;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "specialAnnotationsProvider", "Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "getSpecialAnnotationsProvider", "()Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "symbolsMappingForLazyClasses", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "getSymbolsMappingForLazyClasses", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "typeConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "getTypeConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "visibilityConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "getVisibilityConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractFir2IrLazyFunction<F extends FirCallableDeclaration> extends IrSimpleFunction implements Fir2IrComponents, AbstractFir2IrLazyDeclaration<F>, Fir2IrTypeParametersContainer, IrLazyFunctionBase {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(AbstractFir2IrLazyFunction.class, "visibility", "getVisibility()Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", 0)};
    private IrElement attributeOwnerId;
    private final Fir2IrComponents c;
    private IrPropertySymbol correspondingPropertySymbol;
    private int endOffset;
    private boolean isFakeOverride;
    private IrDeclarationOrigin origin;
    private int startOffset;
    private final IrSimpleFunctionSymbol symbol;
    public List<? extends IrTypeParameter> typeParameters;

    /* JADX INFO: renamed from: visibility$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty visibility;

    public AbstractFir2IrLazyFunction(Fir2IrComponents fir2IrComponents, int i, int i2, IrDeclarationOrigin irDeclarationOrigin, IrSimpleFunctionSymbol irSimpleFunctionSymbol, IrDeclarationParent irDeclarationParent, boolean z) {
        fir2IrComponents.getClass();
        irDeclarationOrigin.getClass();
        irSimpleFunctionSymbol.getClass();
        irDeclarationParent.getClass();
        this.c = fir2IrComponents;
        this.startOffset = i;
        this.endOffset = i2;
        this.origin = irDeclarationOrigin;
        this.symbol = irSimpleFunctionSymbol;
        this.isFakeOverride = z;
        setParent(irDeclarationParent);
        this.visibility = LazyUtilKt.lazyVar(getLock(), new Function0() { // from class: jm
            public final Object invoke() {
                return AbstractFir2IrLazyFunction.e(this.b);
            }
        });
        this.attributeOwnerId = this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static DescriptorVisibility e(AbstractFir2IrLazyFunction abstractFir2IrLazyFunction) {
        return abstractFir2IrLazyFunction.c.getVisibilityConverter().convertToDescriptorVisibility(((FirMemberDeclaration) abstractFir2IrLazyFunction.getFir()).getStatus().getVisibility());
    }

    @ObsoleteDescriptorBasedAPI
    public static /* synthetic */ void getDescriptor$annotations() {
    }

    public static /* synthetic */ void getVisibility$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.lazy.AbstractFir2IrLazyDeclaration
    public ReadWriteProperty<Object, List<IrAnnotation>> createLazyAnnotations() {
        return super.createLazyAnnotations();
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

    public IrElement getAttributeOwnerId() {
        return this.attributeOwnerId;
    }

    public IrBody getBody() {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrBuiltinSymbolsContainer getBuiltins() {
        return this.c.getBuiltins();
    }

    public final Fir2IrComponents getC() {
        return this.c;
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

    public FunctionDescriptor getDescriptor() {
        return m556getSymbol().getDescriptor();
    }

    public int getEndOffset() {
        return this.endOffset;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrExtensions getExtensions() {
        return this.c.getExtensions();
    }

    @Override // org.jetbrains.kotlin.fir.lazy.AbstractFir2IrLazyDeclaration
    public IrFactory getFactory() {
        return super.getFactory();
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

    public MetadataSource getMetadata() {
        return null;
    }

    public Modality getModality() {
        return getFir().getSymbol().getResolvedStatus().getModality();
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

    public DescriptorVisibility getVisibility() {
        return (DescriptorVisibility) this.visibility.getValue(this, $$delegatedProperties[0]);
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrVisibilityConverter getVisibilityConverter() {
        return this.c.getVisibilityConverter();
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

    public boolean isInfix() {
        return getFir().getStatus().isInfix();
    }

    public boolean isInline() {
        return getFir().getStatus().isInline();
    }

    public boolean isOperator() {
        return getFir().getStatus().isOperator();
    }

    public boolean isSuspend() {
        return getFir().getStatus().isSuspend();
    }

    public boolean isTailrec() {
        return getFir().getStatus().isTailRec();
    }

    public void setAttributeOwnerId(IrElement irElement) {
        irElement.getClass();
        this.attributeOwnerId = irElement;
    }

    public void setBody(IrBody irBody) {
        if (irBody == null) {
            return;
        }
        AbstractFir2IrLazyDeclarationKt.mutationNotSupported();
        wq6.a();
    }

    public void setCorrespondingPropertySymbol(IrPropertySymbol irPropertySymbol) {
        this.correspondingPropertySymbol = irPropertySymbol;
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

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setInfix(boolean z) throws KotlinNothingValueException {
        AbstractFir2IrLazyDeclarationKt.mutationNotSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setInline(boolean z) throws KotlinNothingValueException {
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
    public void setOperator(boolean z) throws KotlinNothingValueException {
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
    public void setSuspend(boolean z) throws KotlinNothingValueException {
        AbstractFir2IrLazyDeclarationKt.mutationNotSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public void setTailrec(boolean z) throws KotlinNothingValueException {
        AbstractFir2IrLazyDeclarationKt.mutationNotSupported();
        throw new KotlinNothingValueException();
    }

    public void setTypeParameters(List<? extends IrTypeParameter> list) {
        list.getClass();
        this.typeParameters = list;
    }

    public void setVisibility(DescriptorVisibility descriptorVisibility) {
        descriptorVisibility.getClass();
        this.visibility.setValue(this, $$delegatedProperties[0], descriptorVisibility);
    }

    public final boolean shouldHaveDispatchReceiver$org_jetbrains_kotlin_fir2ir(IrClass containingClass) {
        containingClass.getClass();
        return (getFir().getStatus().isStatic() || IrUtilsKt.isFacadeClass(containingClass)) ? false : true;
    }

    /* JADX INFO: renamed from: getSymbol, reason: collision with other method in class and from getter and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public IrSimpleFunctionSymbol m556getSymbol() {
        return this.symbol;
    }
}

package org.jetbrains.kotlin.fir.backend.utils;

import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.backend.common.IrSpecialAnnotationsProvider;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.backend.Fir2IrBuiltinSymbolsContainer;
import org.jetbrains.kotlin.fir.backend.Fir2IrClassifierStorage;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.backend.Fir2IrConfiguration;
import org.jetbrains.kotlin.fir.backend.Fir2IrConversionScope;
import org.jetbrains.kotlin.fir.backend.Fir2IrConverter;
import org.jetbrains.kotlin.fir.backend.Fir2IrDeclarationStorage;
import org.jetbrains.kotlin.fir.backend.Fir2IrExtensions;
import org.jetbrains.kotlin.fir.backend.Fir2IrImplicitCastInserter;
import org.jetbrains.kotlin.fir.backend.Fir2IrIrGeneratedDeclarationsRegistrar;
import org.jetbrains.kotlin.fir.backend.Fir2IrSymbolsMappingForLazyClasses;
import org.jetbrains.kotlin.fir.backend.Fir2IrTypeConverter;
import org.jetbrains.kotlin.fir.backend.Fir2IrVisibilityConverter;
import org.jetbrains.kotlin.fir.backend.Fir2IrVisitor;
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
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.types.ConeIntegerLiteralType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.ir.IrLock;
import org.jetbrains.kotlin.ir.IrProvider;
import org.jetbrains.kotlin.ir.declarations.IrFactoryHelpersKt;
import org.jetbrains.kotlin.ir.declarations.impl.IrFactoryImpl;
import org.jetbrains.kotlin.ir.expressions.IrConst;
import org.jetbrains.kotlin.ir.expressions.IrConstKind;
import org.jetbrains.kotlin.ir.expressions.IrExpressionBody;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.util.KotlinMangler;
import org.jetbrains.kotlin.types.ConstantValueKind;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u0012\u0010\u0003\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006\u001a\f\u0010\u0007\u001a\u00020\u0001*\u00020\bH\u0002\u001a'\u0010\t\u001a\u00020\n*\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fR\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"getIrConstKind", "Lorg/jetbrains/kotlin/ir/expressions/IrConstKind;", "Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;", "toIrConst", "Lorg/jetbrains/kotlin/ir/expressions/IrConst;", "irType", "Lorg/jetbrains/kotlin/ir/types/IrType;", "toIrConstKind", "Lorg/jetbrains/kotlin/types/ConstantValueKind;", "asCompileTimeIrInitializerForAnnotationParameter", "Lorg/jetbrains/kotlin/ir/expressions/IrExpressionBody;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "components", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "expectedTypeForAnnotationArgument", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lorg/jetbrains/kotlin/ir/expressions/IrExpressionBody;", "org.jetbrains.kotlin:fir2ir"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConstantUtilsKt {
    /* JADX WARN: Type inference failed for: r0v0, types: [org.jetbrains.kotlin.fir.backend.Fir2IrComponents, org.jetbrains.kotlin.fir.backend.utils.ConstantUtilsKt$asCompileTimeIrInitializerForAnnotationParameter$componentsWithReplacedCallGenerator$1] */
    public static final IrExpressionBody asCompileTimeIrInitializerForAnnotationParameter(final Fir2IrComponents fir2IrComponents, FirExpression firExpression, ConeKotlinType coneKotlinType) {
        fir2IrComponents.getClass();
        firExpression.getClass();
        ?? r0 = new Fir2IrComponents() { // from class: org.jetbrains.kotlin.fir.backend.utils.ConstantUtilsKt$asCompileTimeIrInitializerForAnnotationParameter$componentsWithReplacedCallGenerator$1
            private CallAndReferenceGenerator _callGenerator;

            @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
            public AdapterGenerator getAdapterGenerator() {
                return fir2IrComponents.getAdapterGenerator();
            }

            @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
            public AnnotationGenerator getAnnotationGenerator() {
                return fir2IrComponents.getAnnotationGenerator();
            }

            @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
            public Fir2IrIrGeneratedDeclarationsRegistrar getAnnotationsFromPluginRegistrar() {
                return fir2IrComponents.getAnnotationsFromPluginRegistrar();
            }

            @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
            public Fir2IrBuiltinSymbolsContainer getBuiltins() {
                return fir2IrComponents.getBuiltins();
            }

            @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
            public CallAndReferenceGenerator getCallGenerator() {
                CallAndReferenceGenerator callAndReferenceGenerator = this._callGenerator;
                callAndReferenceGenerator.getClass();
                return callAndReferenceGenerator;
            }

            @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
            public Fir2IrCallableDeclarationsGenerator getCallablesGenerator() {
                return fir2IrComponents.getCallablesGenerator();
            }

            @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
            public Fir2IrClassifierStorage getClassifierStorage() {
                return fir2IrComponents.getClassifierStorage();
            }

            @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
            public Fir2IrClassifiersGenerator getClassifiersGenerator() {
                return fir2IrComponents.getClassifiersGenerator();
            }

            @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
            public Fir2IrConfiguration getConfiguration() {
                return fir2IrComponents.getConfiguration();
            }

            @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
            public Fir2IrConverter getConverter() {
                return fir2IrComponents.getConverter();
            }

            @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
            public Fir2IrDataClassMembersGenerator getDataClassMembersGenerator() {
                return fir2IrComponents.getDataClassMembersGenerator();
            }

            @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
            public Fir2IrDeclarationStorage getDeclarationStorage() {
                return fir2IrComponents.getDeclarationStorage();
            }

            @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
            public Fir2IrExtensions getExtensions() {
                return fir2IrComponents.getExtensions();
            }

            @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
            public Set<FirFile> getFilesBeingCompiled() {
                return fir2IrComponents.getFilesBeingCompiled();
            }

            @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
            public FirProviderWithGeneratedFiles getFirProvider() {
                return fir2IrComponents.getFirProvider();
            }

            @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
            public Fir2IrImplicitCastInserter getImplicitCastInserter() {
                return fir2IrComponents.getImplicitCastInserter();
            }

            @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
            public KotlinMangler.IrMangler getIrMangler() {
                return fir2IrComponents.getIrMangler();
            }

            @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
            public List<IrProvider> getIrProviders() {
                return fir2IrComponents.getIrProviders();
            }

            @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
            public Fir2IrLazyDeclarationsGenerator getLazyDeclarationsGenerator() {
                return fir2IrComponents.getLazyDeclarationsGenerator();
            }

            @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
            public Fir2IrLazyFakeOverrideGenerator getLazyFakeOverrideGenerator() {
                return fir2IrComponents.getLazyFakeOverrideGenerator();
            }

            @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
            public IrLock getLock() {
                return fir2IrComponents.getLock();
            }

            @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents, org.jetbrains.kotlin.fir.ScopeSessionHolder
            public ScopeSession getScopeSession() {
                return fir2IrComponents.getScopeSession();
            }

            @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents, org.jetbrains.kotlin.fir.SessionHolder
            public FirSession getSession() {
                return fir2IrComponents.getSession();
            }

            @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
            public IrSpecialAnnotationsProvider getSpecialAnnotationsProvider() {
                return fir2IrComponents.getSpecialAnnotationsProvider();
            }

            @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
            public Fir2IrSymbolsMappingForLazyClasses getSymbolsMappingForLazyClasses() {
                return fir2IrComponents.getSymbolsMappingForLazyClasses();
            }

            @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
            public Fir2IrTypeConverter getTypeConverter() {
                return fir2IrComponents.getTypeConverter();
            }

            @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
            public Fir2IrVisibilityConverter getVisibilityConverter() {
                return fir2IrComponents.getVisibilityConverter();
            }

            public final CallAndReferenceGenerator get_callGenerator() {
                return this._callGenerator;
            }

            public final void set_callGenerator(CallAndReferenceGenerator callAndReferenceGenerator) {
                this._callGenerator = callAndReferenceGenerator;
            }
        };
        Fir2IrConversionScope fir2IrConversionScope = new Fir2IrConversionScope(fir2IrComponents.getConfiguration());
        Fir2IrVisitor fir2IrVisitor = new Fir2IrVisitor(r0, fir2IrConversionScope);
        r0.set_callGenerator(new CallAndReferenceGenerator(r0, fir2IrVisitor, fir2IrConversionScope));
        boolean z = fir2IrVisitor._annotationMode;
        fir2IrVisitor._annotationMode = true;
        try {
            return IrFactoryHelpersKt.createExpressionBody(IrFactoryImpl.INSTANCE, Fir2IrVisitor.convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(fir2IrVisitor, firExpression, false, coneKotlinType, 2, null));
        } finally {
            fir2IrVisitor._annotationMode = z;
        }
    }

    public static /* synthetic */ IrExpressionBody asCompileTimeIrInitializerForAnnotationParameter$default(Fir2IrComponents fir2IrComponents, FirExpression firExpression, ConeKotlinType coneKotlinType, int i, Object obj) {
        if ((i & 2) != 0) {
            coneKotlinType = null;
        }
        return asCompileTimeIrInitializerForAnnotationParameter(fir2IrComponents, firExpression, coneKotlinType);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final IrConstKind getIrConstKind(FirLiteralExpression firLiteralExpression) throws KotlinIllegalArgumentExceptionWithAttachments {
        firLiteralExpression.getClass();
        ConstantValueKind kind = firLiteralExpression.getKind();
        if (!Intrinsics.areEqual(kind, ConstantValueKind.IntegerLiteral.INSTANCE) && !Intrinsics.areEqual(kind, ConstantValueKind.UnsignedIntegerLiteral.INSTANCE)) {
            return toIrConstKind(firLiteralExpression.getKind());
        }
        ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(firLiteralExpression);
        resolvedType.getClass();
        ConstantValueKind constKind = FirTypeUtilsKt.toConstKind(ConeIntegerLiteralType.getApproximatedType$default((ConeIntegerLiteralType) resolvedType, null, 1, null));
        constKind.getClass();
        return toIrConstKind(constKind);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:30:0x0065  */
    public static final IrConst toIrConst(FirLiteralExpression firLiteralExpression, IrType irType) throws KotlinIllegalArgumentExceptionWithAttachments {
        int endOffset;
        Object value;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        firLiteralExpression.getClass();
        irType.getClass();
        KtSourceElement source = firLiteralExpression.getSource();
        int i = -1;
        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
            endOffset = -1;
        } else {
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                endOffset = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        endOffset = -1;
                    } else {
                        if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                            startOffset = source != null ? source.getStartOffset() : -1;
                        } else {
                            startOffset = numStartOffsetSkippingComments.intValue();
                        }
                        endOffset = source != null ? source.getEndOffset() : -1;
                        i = startOffset;
                    }
                }
            }
        }
        IrConstKind irConstKind = getIrConstKind(firLiteralExpression);
        Object value2 = firLiteralExpression.getValue();
        Long l = value2 instanceof Long ? (Long) value2 : null;
        if (l != null) {
            long jLongValue = l.longValue();
            if (Intrinsics.areEqual(irConstKind, IrConstKind.Byte.INSTANCE)) {
                value = Byte.valueOf((byte) jLongValue);
            } else if (Intrinsics.areEqual(irConstKind, IrConstKind.Short.INSTANCE)) {
                value = Short.valueOf((short) jLongValue);
            } else if (Intrinsics.areEqual(irConstKind, IrConstKind.Int.INSTANCE)) {
                value = Integer.valueOf((int) jLongValue);
            } else if (Intrinsics.areEqual(irConstKind, IrConstKind.Float.INSTANCE)) {
                value = Float.valueOf(jLongValue);
            } else {
                value = Intrinsics.areEqual(irConstKind, IrConstKind.Double.INSTANCE) ? Double.valueOf(jLongValue) : Long.valueOf(jLongValue);
            }
        } else {
            value = firLiteralExpression.getValue();
        }
        return BuildersKt.IrConstImpl(i, endOffset, IrTypesKt.removeAnnotations(irType), irConstKind, value);
    }

    private static final IrConstKind toIrConstKind(ConstantValueKind constantValueKind) {
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Null.INSTANCE)) {
            return IrConstKind.Null.INSTANCE;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Boolean.INSTANCE)) {
            return IrConstKind.Boolean.INSTANCE;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Char.INSTANCE)) {
            return IrConstKind.Char.INSTANCE;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Byte.INSTANCE)) {
            return IrConstKind.Byte.INSTANCE;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Short.INSTANCE)) {
            return IrConstKind.Short.INSTANCE;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Int.INSTANCE)) {
            return IrConstKind.Int.INSTANCE;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Long.INSTANCE)) {
            return IrConstKind.Long.INSTANCE;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.UnsignedByte.INSTANCE)) {
            return IrConstKind.Byte.INSTANCE;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.UnsignedShort.INSTANCE)) {
            return IrConstKind.Short.INSTANCE;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.UnsignedInt.INSTANCE)) {
            return IrConstKind.Int.INSTANCE;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.UnsignedLong.INSTANCE)) {
            return IrConstKind.Long.INSTANCE;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.String.INSTANCE)) {
            return IrConstKind.String.INSTANCE;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Float.INSTANCE)) {
            return IrConstKind.Float.INSTANCE;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Double.INSTANCE)) {
            return IrConstKind.Double.INSTANCE;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.IntegerLiteral.INSTANCE) || Intrinsics.areEqual(constantValueKind, ConstantValueKind.UnsignedIntegerLiteral.INSTANCE)) {
            j2d.a();
            return null;
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Error.INSTANCE)) {
            j2d.a();
            return null;
        }
        bu8.a();
        return null;
    }
}

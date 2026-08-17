package org.jetbrains.kotlin.backend.jvm;

import kotlin.Metadata;
import org.jetbrains.kotlin.backend.common.IrSpecialAnnotationsProvider;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.ir.IrBuiltIns;
import org.jetbrains.kotlin.ir.builders.declarations.DeclarationBuildersKt;
import org.jetbrains.kotlin.ir.builders.declarations.IrClassBuilder;
import org.jetbrains.kotlin.ir.builders.declarations.IrFunctionBuilder;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrConstructor;
import org.jetbrains.kotlin.ir.declarations.IrFactory;
import org.jetbrains.kotlin.ir.declarations.impl.IrExternalPackageFragmentImpl;
import org.jetbrains.kotlin.ir.declarations.impl.IrFactoryImpl;
import org.jetbrains.kotlin.ir.expressions.IrAnnotation;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;
import org.jetbrains.kotlin.ir.expressions.impl.IrAnnotationImpl;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrConstructorSymbol;
import org.jetbrains.kotlin.ir.symbols.impl.DescriptorlessExternalPackageFragmentSymbol;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.load.java.JvmAnnotationNames;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0018B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\b\u0010\u0010\u001a\u00020\u000eH\u0016J\b\u0010\u0011\u001a\u00020\u000eH\u0016J\b\u0010\u0012\u001a\u00020\u000eH\u0016J\f\u0010\u0013\u001a\u00020\u0014*\u00020\bH\u0002J\u0014\u0010\u0015\u001a\u00020\b*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/JvmIrSpecialAnnotationSymbolProvider;", "Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "kotlinJvmInternalPackage", "Lorg/jetbrains/kotlin/ir/declarations/impl/IrExternalPackageFragmentImpl;", "kotlinInternalIrPackage", "enhancedNullabilityAnnotationInfo", "Lorg/jetbrains/kotlin/backend/jvm/JvmIrSpecialAnnotationSymbolProvider$AnnotationInfo;", "flexibleNullabilityAnnotationInfo", "flexibleMutabilityAnnotationInfo", "rawTypeAnnotationInfo", "flexibleArrayElementVarianceAnnotationInfo", "generateEnhancedNullabilityAnnotation", "Lorg/jetbrains/kotlin/ir/expressions/IrAnnotation;", "generateFlexibleNullabilityAnnotation", "generateFlexibleMutabilityAnnotation", "generateRawTypeAnnotation", "generateFlexibleArrayElementVarianceAnnotation", "toAnnotation", "Lorg/jetbrains/kotlin/ir/expressions/impl/IrAnnotationImpl;", "getAnnotationInfo", "Lorg/jetbrains/kotlin/name/ClassId;", "irPackage", "AnnotationInfo", "org.jetbrains.kotlin:backend.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class JvmIrSpecialAnnotationSymbolProvider extends IrSpecialAnnotationsProvider {
    public static final JvmIrSpecialAnnotationSymbolProvider INSTANCE;
    private static final AnnotationInfo enhancedNullabilityAnnotationInfo;
    private static final AnnotationInfo flexibleArrayElementVarianceAnnotationInfo;
    private static final AnnotationInfo flexibleMutabilityAnnotationInfo;
    private static final AnnotationInfo flexibleNullabilityAnnotationInfo;
    private static final IrExternalPackageFragmentImpl kotlinInternalIrPackage;
    private static final IrExternalPackageFragmentImpl kotlinJvmInternalPackage;
    private static final AnnotationInfo rawTypeAnnotationInfo;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/JvmIrSpecialAnnotationSymbolProvider$AnnotationInfo;", "", "defaultType", "Lorg/jetbrains/kotlin/ir/types/IrType;", "constructorSymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrConstructorSymbol;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/types/IrType;Lorg/jetbrains/kotlin/ir/symbols/IrConstructorSymbol;)V", "getDefaultType", "()Lorg/jetbrains/kotlin/ir/types/IrType;", "getConstructorSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrConstructorSymbol;", "org.jetbrains.kotlin:backend.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class AnnotationInfo {
        private final IrConstructorSymbol constructorSymbol;
        private final IrType defaultType;

        public AnnotationInfo(IrType irType, IrConstructorSymbol irConstructorSymbol) {
            irType.getClass();
            irConstructorSymbol.getClass();
            this.defaultType = irType;
            this.constructorSymbol = irConstructorSymbol;
        }

        public final IrConstructorSymbol getConstructorSymbol() {
            return this.constructorSymbol;
        }

        public final IrType getDefaultType() {
            return this.defaultType;
        }
    }

    static {
        JvmIrSpecialAnnotationSymbolProvider jvmIrSpecialAnnotationSymbolProvider = new JvmIrSpecialAnnotationSymbolProvider();
        INSTANCE = jvmIrSpecialAnnotationSymbolProvider;
        DescriptorlessExternalPackageFragmentSymbol descriptorlessExternalPackageFragmentSymbol = new DescriptorlessExternalPackageFragmentSymbol();
        FqName fqName = JvmAnnotationNames.KOTLIN_JVM_INTERNAL;
        fqName.getClass();
        IrExternalPackageFragmentImpl irExternalPackageFragmentImpl = new IrExternalPackageFragmentImpl(descriptorlessExternalPackageFragmentSymbol, fqName);
        kotlinJvmInternalPackage = irExternalPackageFragmentImpl;
        IrExternalPackageFragmentImpl irExternalPackageFragmentImpl2 = new IrExternalPackageFragmentImpl(new DescriptorlessExternalPackageFragmentSymbol(), IrBuiltIns.Companion.getKOTLIN_INTERNAL_IR_FQN());
        kotlinInternalIrPackage = irExternalPackageFragmentImpl2;
        StandardClassIds.Annotations annotations = StandardClassIds.Annotations.INSTANCE;
        enhancedNullabilityAnnotationInfo = jvmIrSpecialAnnotationSymbolProvider.getAnnotationInfo(annotations.getEnhancedNullability(), irExternalPackageFragmentImpl);
        flexibleNullabilityAnnotationInfo = jvmIrSpecialAnnotationSymbolProvider.getAnnotationInfo(annotations.getFlexibleNullability(), irExternalPackageFragmentImpl2);
        flexibleMutabilityAnnotationInfo = jvmIrSpecialAnnotationSymbolProvider.getAnnotationInfo(annotations.getFlexibleMutability(), irExternalPackageFragmentImpl2);
        rawTypeAnnotationInfo = jvmIrSpecialAnnotationSymbolProvider.getAnnotationInfo(annotations.getRawTypeAnnotation(), irExternalPackageFragmentImpl2);
        flexibleArrayElementVarianceAnnotationInfo = jvmIrSpecialAnnotationSymbolProvider.getAnnotationInfo(annotations.getFlexibleArrayElementVariance(), irExternalPackageFragmentImpl2);
    }

    private JvmIrSpecialAnnotationSymbolProvider() {
    }

    private final AnnotationInfo getAnnotationInfo(ClassId classId, IrExternalPackageFragmentImpl irExternalPackageFragmentImpl) {
        IrFactoryImpl irFactoryImpl = IrFactoryImpl.INSTANCE;
        IrClassBuilder irClassBuilder = new IrClassBuilder();
        irClassBuilder.setKind(ClassKind.ANNOTATION_CLASS);
        irClassBuilder.setName(classId.getShortClassName());
        IrClass irClassBuildClass = DeclarationBuildersKt.buildClass(irFactoryImpl, irClassBuilder);
        IrUtilsKt.createThisReceiverParameter(irClassBuildClass);
        irClassBuildClass.setParent(irExternalPackageFragmentImpl);
        IrFactory factory = irClassBuildClass.getFactory();
        IrFunctionBuilder irFunctionBuilder = new IrFunctionBuilder();
        irFunctionBuilder.setPrimary(true);
        irFunctionBuilder.setReturnType(IrUtilsKt.getDefaultType(irClassBuildClass));
        IrConstructor irConstructorBuildConstructor = DeclarationBuildersKt.buildConstructor(factory, irFunctionBuilder);
        irClassBuildClass.getDeclarations().add(irConstructorBuildConstructor);
        irConstructorBuildConstructor.setParent(irClassBuildClass);
        IrClassSymbol symbol = irClassBuildClass.getSymbol();
        for (Object obj : symbol.getOwner().getDeclarations()) {
            if (obj instanceof IrConstructor) {
                return new AnnotationInfo(IrTypesKt.getDefaultType(symbol), ((IrConstructor) obj).getSymbol());
            }
        }
        hb9.a("No element of given type found");
        return null;
    }

    private final IrAnnotationImpl toAnnotation(AnnotationInfo annotationInfo) {
        return BuildersKt.fromSymbolOwner$default(IrAnnotationImpl.Companion, annotationInfo.getDefaultType(), annotationInfo.getConstructorSymbol(), (IrStatementOrigin) null, 4, (Object) null);
    }

    public IrAnnotation generateEnhancedNullabilityAnnotation() {
        return toAnnotation(enhancedNullabilityAnnotationInfo);
    }

    public IrAnnotation generateFlexibleArrayElementVarianceAnnotation() {
        return toAnnotation(flexibleArrayElementVarianceAnnotationInfo);
    }

    public IrAnnotation generateFlexibleMutabilityAnnotation() {
        return toAnnotation(flexibleMutabilityAnnotationInfo);
    }

    public IrAnnotation generateFlexibleNullabilityAnnotation() {
        return toAnnotation(flexibleNullabilityAnnotationInfo);
    }

    public IrAnnotation generateRawTypeAnnotation() {
        return toAnnotation(rawTypeAnnotationInfo);
    }
}

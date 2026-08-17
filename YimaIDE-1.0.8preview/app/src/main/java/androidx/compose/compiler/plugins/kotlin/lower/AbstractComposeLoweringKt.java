package androidx.compose.compiler.plugins.kotlin.lower;

import androidx.compose.compiler.plugins.kotlin.ComposeFqNames;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.fir.backend.FirMetadataSource;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.declarations.IrAnnotationContainer;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationsKt;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrMetadataSourceOwner;
import org.jetbrains.kotlin.ir.declarations.IrParameterKind;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.declarations.MetadataSource;
import org.jetbrains.kotlin.ir.expressions.IrAnnotation;
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.util.AdditionalIrUtilsKt;
import org.jetbrains.kotlin.ir.util.DeepCopyTypeRemapper;
import org.jetbrains.kotlin.ir.util.PatchDeclarationParentsKt;
import org.jetbrains.kotlin.ir.visitors.IrVisitorsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.resolve.descriptorUtil.DescriptorUtilsKt;
import org.jetbrains.kotlin.utils.exceptions.PlatformExceptionUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000v\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\u001a\u0012\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\n\u0010\f\u001a\u00020\u0003*\u00020\r\u001a-\u0010\u000e\u001a\u0002H\u000f\"\u0004\b\u0000\u0010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0013H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a\n\u0010\u0015\u001a\u00020\u0016*\u00020\u0006\u001a\u0014\u0010\u0017\u001a\u00020\u0003*\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002\u001a.\u0010\u001b\u001a\u0002H\u000f\"\n\b\u0000\u0010\u000f\u0018\u0001*\u00020\u001c*\u0002H\u000f2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eH\u0080\b¢\u0006\u0002\u0010 \u001a\n\u0010!\u001a\u00020\u0003*\u00020\"\u001a\n\u0010#\u001a\u00020\u0003*\u00020\"\u001a\u0014\u0010$\u001a\u0004\u0018\u00010%*\u00020\u001e2\u0006\u0010&\u001a\u00020'\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0017\u0010\u0007\u001a\u0004\u0018\u00010\b*\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b\"\u001b\u0010(\u001a\b\u0012\u0004\u0012\u00020%0)*\u00020\u001e8F¢\u0006\u0006\u001a\u0004\b*\u0010+\"\u0015\u0010,\u001a\u00020\u0003*\u00020%8F¢\u0006\u0006\u001a\u0004\b,\u0010-\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006."}, d2 = {"unsafeSymbolsRegex", "Lkotlin/text/Regex;", "hasAnnotationSafe", "", "Lorg/jetbrains/kotlin/ir/declarations/IrAnnotationContainer;", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "annotationClass", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "Lorg/jetbrains/kotlin/ir/expressions/IrConstructorCall;", "getAnnotationClass", "(Lorg/jetbrains/kotlin/ir/expressions/IrConstructorCall;)Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "hasFirDeclaration", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "includeFileNameInExceptionTrace", "T", "file", "Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "body", "Lkotlin/Function0;", "(Lorg/jetbrains/kotlin/ir/declarations/IrFile;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "topLevelName", "", "isSubclassOf", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "copyWithNewTypeParams", "Lorg/jetbrains/kotlin/ir/IrElement;", "source", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "target", "(Lorg/jetbrains/kotlin/ir/IrElement;Lorg/jetbrains/kotlin/ir/declarations/IrFunction;Lorg/jetbrains/kotlin/ir/declarations/IrFunction;)Lorg/jetbrains/kotlin/ir/IrElement;", "isSyntheticComposableFunction", "Lorg/jetbrains/kotlin/ir/types/IrType;", "isKComposableFunction", "firstParameterOfKind", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "kind", "Lorg/jetbrains/kotlin/ir/declarations/IrParameterKind;", "namedParameters", "", "getNamedParameters", "(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;)Ljava/util/List;", "isReceiver", "(Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;)Z", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class AbstractComposeLoweringKt {
    private static final Regex unsafeSymbolsRegex = new Regex("[ <>]");

    public static final /* synthetic */ <T extends IrElement> T copyWithNewTypeParams(T t, IrFunction irFunction, IrFunction irFunction2) {
        t.getClass();
        irFunction.getClass();
        irFunction2.getClass();
        AbstractComposeLoweringKt$copyWithNewTypeParams$typeParamsAwareSymbolRemapper$1 abstractComposeLoweringKt$copyWithNewTypeParams$typeParamsAwareSymbolRemapper$1 = new AbstractComposeLoweringKt$copyWithNewTypeParams$typeParamsAwareSymbolRemapper$1(irFunction, irFunction2);
        DeepCopyTypeRemapper deepCopyTypeRemapper = new DeepCopyTypeRemapper(abstractComposeLoweringKt$copyWithNewTypeParams$typeParamsAwareSymbolRemapper$1);
        DeepCopyPreservingMetadata deepCopyPreservingMetadata = new DeepCopyPreservingMetadata(abstractComposeLoweringKt$copyWithNewTypeParams$typeParamsAwareSymbolRemapper$1, new copyWithNewTypeParams.typeParamRemapper.1(deepCopyTypeRemapper, irFunction, irFunction2));
        deepCopyTypeRemapper.setDeepCopy(deepCopyPreservingMetadata);
        IrVisitorsKt.acceptVoid(t, abstractComposeLoweringKt$copyWithNewTypeParams$typeParamsAwareSymbolRemapper$1);
        IrElement irElementPatchDeclarationParents = PatchDeclarationParentsKt.patchDeclarationParents(t.transform(deepCopyPreservingMetadata, (Object) null), irFunction2);
        Intrinsics.reifiedOperationMarker(1, "T");
        return (T) irElementPatchDeclarationParents;
    }

    public static final IrValueParameter firstParameterOfKind(IrFunction irFunction, IrParameterKind irParameterKind) {
        Object next;
        irFunction.getClass();
        irParameterKind.getClass();
        Iterator it = irFunction.getParameters().iterator();
        while (it.hasNext()) {
            next = it.next();
            if (((IrValueParameter) next).getKind() == irParameterKind) {
                return (IrValueParameter) next;
            }
        }
        next = null;
        return (IrValueParameter) next;
    }

    public static final IrClassSymbol getAnnotationClass(IrConstructorCall irConstructorCall) {
        irConstructorCall.getClass();
        return IrTypesKt.getClassOrNull(irConstructorCall.getType());
    }

    public static final List<IrValueParameter> getNamedParameters(IrFunction irFunction) {
        irFunction.getClass();
        List parameters = irFunction.getParameters();
        ArrayList arrayList = new ArrayList();
        for (Object obj : parameters) {
            IrValueParameter irValueParameter = (IrValueParameter) obj;
            if (irValueParameter.getKind() == IrParameterKind.Regular || irValueParameter.getKind() == IrParameterKind.Context) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final boolean hasAnnotationSafe(IrAnnotationContainer irAnnotationContainer, FqName fqName) {
        ClassDescriptor descriptor;
        irAnnotationContainer.getClass();
        fqName.getClass();
        List annotations = irAnnotationContainer.getAnnotations();
        if ((annotations instanceof Collection) && annotations.isEmpty()) {
            return false;
        }
        Iterator it = annotations.iterator();
        while (it.hasNext()) {
            IrClassSymbol annotationClass = getAnnotationClass((IrAnnotation) it.next());
            if (Intrinsics.areEqual(fqName, (annotationClass == null || (descriptor = annotationClass.getDescriptor()) == null) ? null : DescriptorUtilsKt.getFqNameSafe(descriptor))) {
                return true;
            }
        }
        return false;
    }

    public static final boolean hasFirDeclaration(IrDeclaration irDeclaration) {
        irDeclaration.getClass();
        IrMetadataSourceOwner irMetadataSourceOwner = irDeclaration instanceof IrMetadataSourceOwner ? (IrMetadataSourceOwner) irDeclaration : null;
        MetadataSource metadata = irMetadataSourceOwner != null ? irMetadataSourceOwner.getMetadata() : null;
        FirMetadataSource firMetadataSource = metadata instanceof FirMetadataSource ? (FirMetadataSource) metadata : null;
        return (firMetadataSource != null ? firMetadataSource.getFir() : null) != null;
    }

    public static final <T> T includeFileNameInExceptionTrace(IrFile irFile, Function0<? extends T> function0) throws Exception {
        irFile.getClass();
        function0.getClass();
        try {
            return (T) function0.invoke();
        } catch (Exception e) {
            PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
            throw new Exception("IR lowering failed at: " + IrDeclarationsKt.getName(irFile), e);
        }
    }

    public static final boolean isKComposableFunction(IrType irType) {
        IrClass owner;
        irType.getClass();
        IrClassSymbol classOrNull = IrTypesKt.getClassOrNull(irType);
        if (classOrNull != null && (owner = classOrNull.getOwner()) != null) {
            String strAsString = owner.getName().asString();
            strAsString.getClass();
            if (StringsKt.startsWith$default(strAsString, "KComposableFunction", false, 2, (Object) null) && Intrinsics.areEqual(AdditionalIrUtilsKt.getPackageFqName(owner), ComposeFqNames.INSTANCE.getInternalPackage())) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isReceiver(IrValueParameter irValueParameter) {
        irValueParameter.getClass();
        return irValueParameter.getKind() == IrParameterKind.ExtensionReceiver || irValueParameter.getKind() == IrParameterKind.DispatchReceiver;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isSubclassOf(IrClass irClass, ClassId classId) {
        IrClass owner;
        List superTypes = irClass.getSuperTypes();
        if ((superTypes instanceof Collection) && superTypes.isEmpty()) {
            return false;
        }
        Iterator it = superTypes.iterator();
        while (it.hasNext()) {
            IrClassSymbol classOrNull = IrTypesKt.getClassOrNull((IrType) it.next());
            if (Intrinsics.areEqual((classOrNull == null || (owner = classOrNull.getOwner()) == null) ? null : AdditionalIrUtilsKt.getClassId(owner), classId)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isSyntheticComposableFunction(IrType irType) {
        IrClass owner;
        irType.getClass();
        IrClassSymbol classOrNull = IrTypesKt.getClassOrNull(irType);
        if (classOrNull != null && (owner = classOrNull.getOwner()) != null) {
            String strAsString = owner.getName().asString();
            strAsString.getClass();
            if (StringsKt.startsWith$default(strAsString, "ComposableFunction", false, 2, (Object) null) && Intrinsics.areEqual(AdditionalIrUtilsKt.getPackageFqName(owner), ComposeFqNames.INSTANCE.getInternalPackage())) {
                return true;
            }
        }
        return false;
    }

    public static final String topLevelName(FqName fqName) {
        fqName.getClass();
        return StringsKt.substringBefore$default(fqName.asString(), ".", (String) null, 2, (Object) null);
    }
}

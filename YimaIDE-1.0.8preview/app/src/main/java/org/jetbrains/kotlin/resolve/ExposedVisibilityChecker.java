package org.jetbrains.kotlin.resolve;

import com.intellij.psi.PsiElement;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor;
import org.jetbrains.kotlin.descriptors.ClassConstructorDescriptor;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.ConstructorDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptorWithVisibility;
import org.jetbrains.kotlin.descriptors.DescriptorUtilKt;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.DescriptorWithRelation;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.EffectiveVisibilityUtilsKt;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.ParameterDescriptor;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
import org.jetbrains.kotlin.descriptors.TypeAliasDescriptor;
import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor;
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory3;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactoryForDeprecation3;
import org.jetbrains.kotlin.diagnostics.Errors;
import org.jetbrains.kotlin.psi.KtClassOrObject;
import org.jetbrains.kotlin.psi.KtConstructor;
import org.jetbrains.kotlin.psi.KtFunction;
import org.jetbrains.kotlin.psi.KtModifierListOwner;
import org.jetbrains.kotlin.psi.KtParameter;
import org.jetbrains.kotlin.psi.KtPrimaryConstructor;
import org.jetbrains.kotlin.psi.KtProperty;
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry;
import org.jetbrains.kotlin.psi.KtTypeAlias;
import org.jetbrains.kotlin.psi.KtTypeParameter;
import org.jetbrains.kotlin.psi.KtTypeReference;
import org.jetbrains.kotlin.psi.psiUtil.KtPsiUtilKt;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.KotlinTypeKt;
import org.jetbrains.kotlin.types.SimpleType;
import org.jetbrains.kotlin.types.TypeUtils;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007JO\u0010\b\u001a\u00020\t\"\b\b\u0000\u0010\n*\u00020\u000b2\u001e\u0010\f\u001a\u001a\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0010\u001a\u0002H\n2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000fH\u0002¢\u0006\u0002\u0010\u0013JO\u0010\u0014\u001a\u00020\t\"\b\b\u0000\u0010\n*\u00020\u000b2\u001e\u0010\f\u001a\u001a\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000e0\u00152\u0006\u0010\u0010\u001a\u0002H\n2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000fH\u0002¢\u0006\u0002\u0010\u0016J\u0016\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ\u001e\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#J\u0016\u0010$\u001a\u00020\t2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(J \u0010)\u001a\u00020\u00182\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\b\b\u0002\u0010\"\u001a\u00020#J \u0010.\u001a\u00020\u00182\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\b\b\u0002\u0010\"\u001a\u00020#J\"\u00103\u001a\u00020\u00182\b\u00104\u001a\u0004\u0018\u0001052\u0006\u00106\u001a\u0002072\u0006\u0010\"\u001a\u00020#H\u0002J\u0018\u00108\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0018\u00109\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lorg/jetbrains/kotlin/resolve/ExposedVisibilityChecker;", Argument.Delimiters.none, "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "trace", "Lorg/jetbrains/kotlin/resolve/BindingTrace;", "<init>", "(Lorg/jetbrains/kotlin/config/LanguageVersionSettings;Lorg/jetbrains/kotlin/resolve/BindingTrace;)V", "reportExposure", Argument.Delimiters.none, "E", "Lcom/intellij/psi/PsiElement;", "diagnostic", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory3;", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "Lorg/jetbrains/kotlin/descriptors/DescriptorWithRelation;", "element", "elementVisibility", "restrictingDescriptor", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory3;Lcom/intellij/psi/PsiElement;Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;Lorg/jetbrains/kotlin/descriptors/DescriptorWithRelation;)V", "reportExposureForDeprecation", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactoryForDeprecation3;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactoryForDeprecation3;Lcom/intellij/psi/PsiElement;Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;Lorg/jetbrains/kotlin/descriptors/DescriptorWithRelation;)V", "checkClassHeader", Argument.Delimiters.none, "klass", "Lorg/jetbrains/kotlin/psi/KtClassOrObject;", "classDescriptor", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "checkDeclarationWithVisibility", "modifierListOwner", "Lorg/jetbrains/kotlin/psi/KtModifierListOwner;", "descriptor", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptorWithVisibility;", "visibility", "Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "checkTypeAlias", "typeAlias", "Lorg/jetbrains/kotlin/psi/KtTypeAlias;", "typeAliasDescriptor", "Lorg/jetbrains/kotlin/descriptors/TypeAliasDescriptor;", "checkFunction", "function", "Lorg/jetbrains/kotlin/psi/KtFunction;", "functionDescriptor", "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "checkProperty", "property", "Lorg/jetbrains/kotlin/psi/KtProperty;", "propertyDescriptor", "Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "checkMemberReceiver", "typeReference", "Lorg/jetbrains/kotlin/psi/KtTypeReference;", "memberDescriptor", "Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor;", "checkSupertypes", "checkParameterBounds", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ExposedVisibilityChecker {
    private final LanguageVersionSettings languageVersionSettings;
    private final BindingTrace trace;

    public ExposedVisibilityChecker(LanguageVersionSettings languageVersionSettings, BindingTrace bindingTrace) {
        languageVersionSettings.getClass();
        this.languageVersionSettings = languageVersionSettings;
        this.trace = bindingTrace;
    }

    public static /* synthetic */ boolean checkFunction$default(ExposedVisibilityChecker exposedVisibilityChecker, KtFunction ktFunction, FunctionDescriptor functionDescriptor, DescriptorVisibility descriptorVisibility, int i, Object obj) {
        if ((i & 4) != 0) {
            descriptorVisibility = functionDescriptor.getVisibility();
            descriptorVisibility.getClass();
        }
        return exposedVisibilityChecker.checkFunction(ktFunction, functionDescriptor, descriptorVisibility);
    }

    private final boolean checkMemberReceiver(KtTypeReference typeReference, CallableMemberDescriptor memberDescriptor, DescriptorVisibility visibility) {
        ParameterDescriptor extensionReceiverParameter;
        if (typeReference == null || (extensionReceiverParameter = memberDescriptor.getExtensionReceiverParameter()) == null) {
            return true;
        }
        EffectiveVisibility effectiveVisibilityEffectiveVisibility$default = EffectiveVisibilityUtilsKt.effectiveVisibility$default((DeclarationDescriptorWithVisibility) memberDescriptor, visibility, false, 2, (Object) null);
        KotlinType type = extensionReceiverParameter.getType();
        type.getClass();
        DescriptorWithRelation descriptorWithRelationLeastPermissiveDescriptor = EffectiveVisibilityUtilsKt.leastPermissiveDescriptor(type, effectiveVisibilityEffectiveVisibility$default);
        if (descriptorWithRelationLeastPermissiveDescriptor == null) {
            return true;
        }
        DiagnosticFactory3<KtTypeReference, EffectiveVisibility, DescriptorWithRelation, EffectiveVisibility> diagnosticFactory3 = Errors.EXPOSED_RECEIVER_TYPE;
        diagnosticFactory3.getClass();
        reportExposure(diagnosticFactory3, typeReference, effectiveVisibilityEffectiveVisibility$default, descriptorWithRelationLeastPermissiveDescriptor);
        return false;
    }

    private final boolean checkParameterBounds(KtClassOrObject klass, ClassDescriptor classDescriptor) {
        boolean z = true;
        EffectiveVisibility effectiveVisibilityEffectiveVisibility$default = EffectiveVisibilityUtilsKt.effectiveVisibility$default(classDescriptor, false, 1, null);
        List typeParameters = klass.getTypeParameters();
        typeParameters.getClass();
        List declaredTypeParameters = classDescriptor.getDeclaredTypeParameters();
        declaredTypeParameters.getClass();
        int i = 0;
        for (Object obj : declaredTypeParameters) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) obj;
            if (i >= typeParameters.size()) {
                break;
            }
            for (KotlinType kotlinType : typeParameterDescriptor.getUpperBounds()) {
                kotlinType.getClass();
                DescriptorWithRelation descriptorWithRelationLeastPermissiveDescriptor = EffectiveVisibilityUtilsKt.leastPermissiveDescriptor(kotlinType, effectiveVisibilityEffectiveVisibility$default);
                if (descriptorWithRelationLeastPermissiveDescriptor != null) {
                    DiagnosticFactory3<KtTypeParameter, EffectiveVisibility, DescriptorWithRelation, EffectiveVisibility> diagnosticFactory3 = Errors.EXPOSED_TYPE_PARAMETER_BOUND;
                    diagnosticFactory3.getClass();
                    Object obj2 = typeParameters.get(i);
                    obj2.getClass();
                    reportExposure(diagnosticFactory3, (PsiElement) obj2, effectiveVisibilityEffectiveVisibility$default, descriptorWithRelationLeastPermissiveDescriptor);
                    z = false;
                    break;
                }
            }
            i = i2;
        }
        return z;
    }

    public static /* synthetic */ boolean checkProperty$default(ExposedVisibilityChecker exposedVisibilityChecker, KtProperty ktProperty, PropertyDescriptor propertyDescriptor, DescriptorVisibility descriptorVisibility, int i, Object obj) {
        if ((i & 4) != 0) {
            descriptorVisibility = propertyDescriptor.getVisibility();
            descriptorVisibility.getClass();
        }
        return exposedVisibilityChecker.checkProperty(ktProperty, propertyDescriptor, descriptorVisibility);
    }

    private final boolean checkSupertypes(KtClassOrObject klass, ClassDescriptor classDescriptor) {
        EffectiveVisibility effectiveVisibilityEffectiveVisibility$default = EffectiveVisibilityUtilsKt.effectiveVisibility$default(classDescriptor, false, 1, null);
        boolean z = classDescriptor.getKind() == ClassKind.INTERFACE;
        List superTypeListEntries = klass.getSuperTypeListEntries();
        Collection supertypes = classDescriptor.getTypeConstructor().getSupertypes();
        supertypes.getClass();
        int i = 0;
        boolean z2 = true;
        for (Object obj : supertypes) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            KotlinType kotlinType = (KotlinType) obj;
            if (i >= superTypeListEntries.size()) {
                break;
            }
            ClassDescriptor classDescriptor2 = TypeUtils.getClassDescriptor(kotlinType);
            if (classDescriptor2 != null) {
                if ((classDescriptor2.getKind() == ClassKind.INTERFACE) == z) {
                    kotlinType.getClass();
                    DescriptorWithRelation descriptorWithRelationLeastPermissiveDescriptor = EffectiveVisibilityUtilsKt.leastPermissiveDescriptor(kotlinType, effectiveVisibilityEffectiveVisibility$default);
                    if (descriptorWithRelationLeastPermissiveDescriptor != null) {
                        DiagnosticFactory3<KtSuperTypeListEntry, EffectiveVisibility, DescriptorWithRelation, EffectiveVisibility> diagnosticFactory3 = z ? Errors.EXPOSED_SUPER_INTERFACE : Errors.EXPOSED_SUPER_CLASS;
                        diagnosticFactory3.getClass();
                        reportExposure(diagnosticFactory3, (PsiElement) superTypeListEntries.get(i), effectiveVisibilityEffectiveVisibility$default, descriptorWithRelationLeastPermissiveDescriptor);
                        z2 = false;
                    }
                }
            }
            i = i2;
        }
        return z2;
    }

    private final <E extends PsiElement> void reportExposure(DiagnosticFactory3<E, EffectiveVisibility, DescriptorWithRelation, EffectiveVisibility> diagnostic, E element, EffectiveVisibility elementVisibility, DescriptorWithRelation restrictingDescriptor) {
        BindingTrace bindingTrace = this.trace;
        if (bindingTrace == null) {
            return;
        }
        EffectiveVisibility effectiveVisibility = restrictingDescriptor.effectiveVisibility();
        if (this.languageVersionSettings.supportsFeature(LanguageFeature.PrivateInFileEffectiveVisibility) || !Intrinsics.areEqual(elementVisibility, EffectiveVisibility.PrivateInFile.INSTANCE)) {
            bindingTrace.report(diagnostic.on(element, elementVisibility, restrictingDescriptor, effectiveVisibility));
        } else {
            bindingTrace.report(Errors.EXPOSED_FROM_PRIVATE_IN_FILE.on(element, elementVisibility, restrictingDescriptor, effectiveVisibility));
        }
    }

    private final <E extends PsiElement> void reportExposureForDeprecation(DiagnosticFactoryForDeprecation3<E, EffectiveVisibility, DescriptorWithRelation, EffectiveVisibility> diagnostic, E element, EffectiveVisibility elementVisibility, DescriptorWithRelation restrictingDescriptor) {
        BindingTrace bindingTrace = this.trace;
        if (bindingTrace == null) {
            return;
        }
        bindingTrace.report(diagnostic.on(this.languageVersionSettings, element, elementVisibility, restrictingDescriptor, restrictingDescriptor.effectiveVisibility()));
    }

    public final boolean checkClassHeader(KtClassOrObject klass, ClassDescriptor classDescriptor) {
        ClassConstructorDescriptor unsubstitutedPrimaryConstructor;
        klass.getClass();
        classDescriptor.getClass();
        boolean zCheckSupertypes = checkSupertypes(klass, classDescriptor) & checkParameterBounds(klass, classDescriptor);
        KtPrimaryConstructor primaryConstructor = klass.getPrimaryConstructor();
        return (primaryConstructor == null || (unsubstitutedPrimaryConstructor = classDescriptor.getUnsubstitutedPrimaryConstructor()) == null) ? zCheckSupertypes : checkFunction$default(this, primaryConstructor, unsubstitutedPrimaryConstructor, null, 4, null) & zCheckSupertypes;
    }

    public final boolean checkDeclarationWithVisibility(KtModifierListOwner modifierListOwner, DeclarationDescriptorWithVisibility descriptor, DescriptorVisibility visibility) {
        modifierListOwner.getClass();
        descriptor.getClass();
        visibility.getClass();
        if ((modifierListOwner instanceof KtFunction) && (descriptor instanceof FunctionDescriptor)) {
            return checkFunction((KtFunction) modifierListOwner, (FunctionDescriptor) descriptor, visibility);
        }
        if ((modifierListOwner instanceof KtProperty) && (descriptor instanceof PropertyDescriptor)) {
            return checkProperty((KtProperty) modifierListOwner, (PropertyDescriptor) descriptor, visibility);
        }
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x004f  */
    public final boolean checkFunction(KtFunction function, FunctionDescriptor functionDescriptor, DescriptorVisibility visibility) {
        boolean z;
        function.getClass();
        functionDescriptor.getClass();
        visibility.getClass();
        EffectiveVisibility effectiveVisibilityEffectiveVisibility$default = EffectiveVisibilityUtilsKt.effectiveVisibility$default((DeclarationDescriptorWithVisibility) functionDescriptor, visibility, false, 2, (Object) null);
        if (functionDescriptor instanceof ConstructorDescriptor) {
            ClassDescriptor constructedClass = ((ConstructorDescriptor) functionDescriptor).getConstructedClass();
            constructedClass.getClass();
            if (DescriptorUtilKt.isSealed(constructedClass) && KtPsiUtilKt.visibilityModifier(function) == null) {
                effectiveVisibilityEffectiveVisibility$default = EffectiveVisibility.PrivateInClass.INSTANCE;
            }
        }
        if (function instanceof KtConstructor) {
            z = true;
        } else {
            KotlinType returnType = functionDescriptor.getReturnType();
            DescriptorWithRelation descriptorWithRelationLeastPermissiveDescriptor = returnType != null ? EffectiveVisibilityUtilsKt.leastPermissiveDescriptor(returnType, effectiveVisibilityEffectiveVisibility$default) : null;
            if (descriptorWithRelationLeastPermissiveDescriptor != null) {
                DiagnosticFactory3<PsiElement, EffectiveVisibility, DescriptorWithRelation, EffectiveVisibility> diagnosticFactory3 = Errors.EXPOSED_FUNCTION_RETURN_TYPE;
                diagnosticFactory3.getClass();
                KtFunction nameIdentifier = function.getNameIdentifier();
                if (nameIdentifier == null) {
                    nameIdentifier = function;
                }
                reportExposure(diagnosticFactory3, nameIdentifier, effectiveVisibilityEffectiveVisibility$default, descriptorWithRelationLeastPermissiveDescriptor);
                z = false;
            } else {
                z = true;
            }
        }
        List<ValueParameterDescriptor> valueParameters = functionDescriptor.getValueParameters();
        valueParameters.getClass();
        int i = 0;
        for (Object obj : valueParameters) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            ValueParameterDescriptor valueParameterDescriptor = (ValueParameterDescriptor) obj;
            if (i < function.getValueParameters().size()) {
                PsiElement psiElement = (KtParameter) function.getValueParameters().get(i);
                KotlinType type = valueParameterDescriptor.getType();
                type.getClass();
                DescriptorWithRelation descriptorWithRelationLeastPermissiveDescriptor2 = EffectiveVisibilityUtilsKt.leastPermissiveDescriptor(type, effectiveVisibilityEffectiveVisibility$default);
                if (descriptorWithRelationLeastPermissiveDescriptor2 != null) {
                    DiagnosticFactory3<KtParameter, EffectiveVisibility, DescriptorWithRelation, EffectiveVisibility> diagnosticFactory4 = Errors.EXPOSED_PARAMETER_TYPE;
                    diagnosticFactory4.getClass();
                    psiElement.getClass();
                    reportExposure(diagnosticFactory4, psiElement, effectiveVisibilityEffectiveVisibility$default, descriptorWithRelationLeastPermissiveDescriptor2);
                } else if ((functionDescriptor instanceof ClassConstructorDescriptor) && psiElement.hasValOrVar()) {
                    BindingTrace bindingTrace = this.trace;
                    PropertyDescriptor constructedClass2 = bindingTrace != null ? (PropertyDescriptor) bindingTrace.get(BindingContext.VALUE_PARAMETER_AS_PROPERTY, valueParameterDescriptor) : null;
                    if (constructedClass2 == null) {
                        constructedClass2 = ((ClassConstructorDescriptor) functionDescriptor).getConstructedClass();
                        constructedClass2.getClass();
                    }
                    EffectiveVisibility effectiveVisibilityEffectiveVisibility$default2 = EffectiveVisibilityUtilsKt.effectiveVisibility$default((DeclarationDescriptorWithVisibility) constructedClass2, (DescriptorVisibility) null, false, 3, (Object) null);
                    KotlinType type2 = valueParameterDescriptor.getType();
                    type2.getClass();
                    DescriptorWithRelation descriptorWithRelationLeastPermissiveDescriptor3 = EffectiveVisibilityUtilsKt.leastPermissiveDescriptor(type2, effectiveVisibilityEffectiveVisibility$default2);
                    if (descriptorWithRelationLeastPermissiveDescriptor3 != null) {
                        DiagnosticFactoryForDeprecation3<PsiElement, EffectiveVisibility, DescriptorWithRelation, EffectiveVisibility> diagnosticFactoryForDeprecation3 = Errors.EXPOSED_PROPERTY_TYPE_IN_CONSTRUCTOR;
                        diagnosticFactoryForDeprecation3.getClass();
                        PsiElement nameIdentifier2 = psiElement.getNameIdentifier();
                        if (nameIdentifier2 != null) {
                            psiElement = nameIdentifier2;
                        }
                        reportExposureForDeprecation(diagnosticFactoryForDeprecation3, psiElement, effectiveVisibilityEffectiveVisibility$default2, descriptorWithRelationLeastPermissiveDescriptor3);
                    }
                }
                z = false;
            }
            i = i2;
        }
        return checkMemberReceiver(function.getReceiverTypeReference(), functionDescriptor, visibility) & z;
    }

    public final boolean checkProperty(KtProperty property, PropertyDescriptor propertyDescriptor, DescriptorVisibility visibility) {
        property.getClass();
        propertyDescriptor.getClass();
        visibility.getClass();
        boolean z = false;
        EffectiveVisibility effectiveVisibilityEffectiveVisibility$default = EffectiveVisibilityUtilsKt.effectiveVisibility$default((DeclarationDescriptorWithVisibility) propertyDescriptor, visibility, false, 2, (Object) null);
        KotlinType type = propertyDescriptor.getType();
        type.getClass();
        DescriptorWithRelation descriptorWithRelationLeastPermissiveDescriptor = EffectiveVisibilityUtilsKt.leastPermissiveDescriptor(type, effectiveVisibilityEffectiveVisibility$default);
        if (descriptorWithRelationLeastPermissiveDescriptor != null) {
            DiagnosticFactory3<PsiElement, EffectiveVisibility, DescriptorWithRelation, EffectiveVisibility> diagnosticFactory3 = Errors.EXPOSED_PROPERTY_TYPE;
            diagnosticFactory3.getClass();
            KtProperty nameIdentifier = property.getNameIdentifier();
            if (nameIdentifier == null) {
                nameIdentifier = property;
            }
            reportExposure(diagnosticFactory3, nameIdentifier, effectiveVisibilityEffectiveVisibility$default, descriptorWithRelationLeastPermissiveDescriptor);
        } else {
            z = true;
        }
        return checkMemberReceiver(property.getReceiverTypeReference(), propertyDescriptor, visibility) & z;
    }

    public final void checkTypeAlias(KtTypeAlias typeAlias, TypeAliasDescriptor typeAliasDescriptor) {
        EffectiveVisibility effectiveVisibilityEffectiveVisibility$default;
        DescriptorWithRelation descriptorWithRelationLeastPermissiveDescriptor;
        typeAlias.getClass();
        typeAliasDescriptor.getClass();
        SimpleType expandedType = typeAliasDescriptor.getExpandedType();
        if (KotlinTypeKt.isError(expandedType) || (descriptorWithRelationLeastPermissiveDescriptor = EffectiveVisibilityUtilsKt.leastPermissiveDescriptor(expandedType, (effectiveVisibilityEffectiveVisibility$default = EffectiveVisibilityUtilsKt.effectiveVisibility$default((DeclarationDescriptorWithVisibility) typeAliasDescriptor, (DescriptorVisibility) null, false, 3, (Object) null)))) == null) {
            return;
        }
        DiagnosticFactory3<PsiElement, EffectiveVisibility, DescriptorWithRelation, EffectiveVisibility> diagnosticFactory3 = Errors.EXPOSED_TYPEALIAS_EXPANDED_TYPE;
        diagnosticFactory3.getClass();
        KtTypeAlias nameIdentifier = typeAlias.getNameIdentifier();
        if (nameIdentifier != null) {
            typeAlias = nameIdentifier;
        }
        reportExposure(diagnosticFactory3, typeAlias, effectiveVisibilityEffectiveVisibility$default, descriptorWithRelationLeastPermissiveDescriptor);
    }

    public /* synthetic */ ExposedVisibilityChecker(LanguageVersionSettings languageVersionSettings, BindingTrace bindingTrace, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(languageVersionSettings, (i & 2) != 0 ? null : bindingTrace);
    }
}

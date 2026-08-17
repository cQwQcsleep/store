package org.jetbrains.kotlin.cfg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.diagnostics.WhenMissingCase;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.psi.KtExpression;
import org.jetbrains.kotlin.psi.KtQualifiedExpression;
import org.jetbrains.kotlin.psi.KtSimpleNameExpression;
import org.jetbrains.kotlin.psi.KtWhenCondition;
import org.jetbrains.kotlin.psi.KtWhenConditionIsPattern;
import org.jetbrains.kotlin.psi.KtWhenConditionWithExpression;
import org.jetbrains.kotlin.psi.KtWhenEntry;
import org.jetbrains.kotlin.psi.KtWhenExpression;
import org.jetbrains.kotlin.resolve.BindingContext;
import org.jetbrains.kotlin.resolve.DescriptorUtils;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.TypeUtils;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b \u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0002J\u0014\u0010\u0016\u001a\u00020\u0012*\u00020\u00132\u0006\u0010\u0017\u001a\u00020\nH\u0002J\u0016\u0010\u0018\u001a\u0004\u0018\u00010\n*\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J,\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u001e\u001a\u00020\u001f2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u0019\u001a\u00020\u001aH\u0004J\u0010\u0010 \u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\nH\u0002R\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t*\u00020\n8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\t*\u00020\n8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\fR\u001e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\t*\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\fR\u0018\u0010\u0011\u001a\u00020\u0012*\u00020\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\""}, d2 = {"Lorg/jetbrains/kotlin/cfg/WhenOnClassExhaustivenessChecker;", "Lorg/jetbrains/kotlin/cfg/WhenExhaustivenessChecker;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "getReference", "Lorg/jetbrains/kotlin/psi/KtSimpleNameExpression;", "expression", "Lorg/jetbrains/kotlin/psi/KtExpression;", "enumEntries", "", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "getEnumEntries", "(Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;)Ljava/util/Set;", "deepSealedSubclasses", "getDeepSealedSubclasses", "subclasses", "getSubclasses", "negated", "", "Lorg/jetbrains/kotlin/psi/KtWhenCondition;", "getNegated", "(Lorg/jetbrains/kotlin/psi/KtWhenCondition;)Z", "isRelevant", "checkedDescriptor", "getCheckedDescriptor", "context", "Lorg/jetbrains/kotlin/resolve/BindingContext;", "getMissingClassCases", "", "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase;", "whenExpression", "Lorg/jetbrains/kotlin/psi/KtWhenExpression;", "createWhenMissingCaseForClassOrEnum", "classDescriptor", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class WhenOnClassExhaustivenessChecker implements WhenExhaustivenessChecker {
    private final WhenMissingCase createWhenMissingCaseForClassOrEnum(ClassDescriptor classDescriptor) {
        ClassId classIdForNonLocalClass = DescriptorUtils.getClassIdForNonLocalClass(classDescriptor);
        classIdForNonLocalClass.getClass();
        if (classDescriptor.getKind() != ClassKind.ENUM_ENTRY) {
            ClassId classIdForNonLocalClass2 = DescriptorUtils.getClassIdForNonLocalClass(classDescriptor);
            classIdForNonLocalClass2.getClass();
            return new WhenMissingCase.IsTypeCheckIsMissing(classIdForNonLocalClass2, classDescriptor.getKind().isSingleton(), classDescriptor.getDeclaredTypeParameters().size());
        }
        ClassId outerClassId = classIdForNonLocalClass.getOuterClassId();
        if (outerClassId != null) {
            return new WhenMissingCase.EnumCheckIsMissing(new CallableId(outerClassId, classIdForNonLocalClass.getShortClassName()));
        }
        k2d.a("Enum should have class id");
        return null;
    }

    private final ClassDescriptor getCheckedDescriptor(KtWhenCondition ktWhenCondition, BindingContext bindingContext) {
        KtExpression expression;
        KtSimpleNameExpression reference;
        if (ktWhenCondition instanceof KtWhenConditionIsPattern) {
            KotlinType kotlinType = (KotlinType) bindingContext.get(BindingContext.TYPE, ((KtWhenConditionIsPattern) ktWhenCondition).getTypeReference());
            if (kotlinType == null) {
                return null;
            }
            return TypeUtils.getClassDescriptor(kotlinType);
        }
        if ((ktWhenCondition instanceof KtWhenConditionWithExpression) && (expression = ((KtWhenConditionWithExpression) ktWhenCondition).getExpression()) != null && (reference = getReference(expression)) != null) {
            Object obj = bindingContext.get(BindingContext.REFERENCE_TARGET, reference);
            if (obj instanceof ClassDescriptor) {
                return (ClassDescriptor) obj;
            }
        }
        return null;
    }

    private final boolean getNegated(KtWhenCondition ktWhenCondition) {
        KtWhenConditionIsPattern ktWhenConditionIsPattern = ktWhenCondition instanceof KtWhenConditionIsPattern ? (KtWhenConditionIsPattern) ktWhenCondition : null;
        if (ktWhenConditionIsPattern != null) {
            return ktWhenConditionIsPattern.isNegated();
        }
        return false;
    }

    private final KtSimpleNameExpression getReference(KtExpression expression) {
        if (expression instanceof KtSimpleNameExpression) {
            return (KtSimpleNameExpression) expression;
        }
        if (expression instanceof KtQualifiedExpression) {
            return getReference(((KtQualifiedExpression) expression).getSelectorExpression());
        }
        return null;
    }

    private final Set<ClassDescriptor> getSubclasses(ClassDescriptor classDescriptor) {
        if (classDescriptor.getModality() == Modality.SEALED) {
            return getDeepSealedSubclasses(classDescriptor);
        }
        return classDescriptor.getKind() == ClassKind.ENUM_CLASS ? getEnumEntries(classDescriptor) : SetsKt.setOf(classDescriptor);
    }

    private final boolean isRelevant(KtWhenCondition ktWhenCondition, ClassDescriptor classDescriptor) {
        return !(ktWhenCondition instanceof KtWhenConditionWithExpression) || DescriptorUtils.isObject(classDescriptor) || DescriptorUtils.isEnumEntry(classDescriptor);
    }

    public final Set<ClassDescriptor> getDeepSealedSubclasses(ClassDescriptor classDescriptor) {
        classDescriptor.getClass();
        Collection<ClassDescriptor> sealedSubclasses = classDescriptor.getSealedSubclasses();
        sealedSubclasses.getClass();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (ClassDescriptor classDescriptor2 : sealedSubclasses) {
            classDescriptor2.getClass();
            CollectionsKt.addAll(linkedHashSet, getSubclasses(classDescriptor2));
        }
        return linkedHashSet;
    }

    public final Set<ClassDescriptor> getEnumEntries(ClassDescriptor classDescriptor) {
        classDescriptor.getClass();
        Collection allDescriptors = DescriptorUtils.getAllDescriptors(classDescriptor.getUnsubstitutedInnerClassesScope());
        allDescriptors.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj : allDescriptors) {
            if (DescriptorUtils.isEnumEntry((DeclarationDescriptor) obj)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : arrayList) {
            if (obj2 instanceof ClassDescriptor) {
                arrayList2.add(obj2);
            }
        }
        return CollectionsKt.toSet(arrayList2);
    }

    public final List<WhenMissingCase> getMissingClassCases(KtWhenExpression whenExpression, Set<? extends ClassDescriptor> subclasses, BindingContext context) {
        whenExpression.getClass();
        subclasses.getClass();
        context.getClass();
        if (subclasses.isEmpty()) {
            return CollectionsKt.listOf(WhenMissingCase.Unknown.INSTANCE);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = whenExpression.getEntries().iterator();
        while (it.hasNext()) {
            KtWhenCondition[] conditions = ((KtWhenEntry) it.next()).getConditions();
            conditions.getClass();
            for (KtWhenCondition ktWhenCondition : conditions) {
                ktWhenCondition.getClass();
                boolean negated = getNegated(ktWhenCondition);
                ClassDescriptor checkedDescriptor = getCheckedDescriptor(ktWhenCondition, context);
                if (checkedDescriptor != null) {
                    Set<ClassDescriptor> subclasses2 = getSubclasses(checkedDescriptor);
                    Set<ClassDescriptor> set = subclasses2;
                    if (!(set instanceof Collection) || !set.isEmpty()) {
                        Iterator<T> it2 = set.iterator();
                        while (it2.hasNext()) {
                            if (subclasses.contains((ClassDescriptor) it2.next())) {
                                if (!isRelevant(ktWhenCondition, checkedDescriptor)) {
                                    break;
                                }
                                if (!negated) {
                                    linkedHashSet.addAll(subclasses2);
                                    break;
                                }
                                Set<ClassDescriptor> set2 = subclasses2;
                                if (!linkedHashSet.containsAll(set2)) {
                                    linkedHashSet.addAll(subclasses);
                                    linkedHashSet.removeAll(set2);
                                    break;
                                }
                                return CollectionsKt.emptyList();
                            }
                        }
                    }
                }
            }
        }
        Set setMinus = SetsKt.minus(subclasses, linkedHashSet);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(setMinus, 10));
        Iterator it3 = setMinus.iterator();
        while (it3.hasNext()) {
            arrayList.add(createWhenMissingCaseForClassOrEnum((ClassDescriptor) it3.next()));
        }
        return arrayList;
    }
}

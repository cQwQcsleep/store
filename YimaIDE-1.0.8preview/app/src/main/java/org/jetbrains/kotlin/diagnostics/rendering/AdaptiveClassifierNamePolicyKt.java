package org.jetbrains.kotlin.diagnostics.rendering;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.CallableDescriptor;
import org.jetbrains.kotlin.descriptors.ClassifierDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.ParameterDescriptor;
import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor;
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor;
import org.jetbrains.kotlin.diagnostics.DiagnosticBaseContext;
import org.jetbrains.kotlin.diagnostics.rendering.AdaptiveClassifierNamePolicyKt;
import org.jetbrains.kotlin.name.FqNameUnsafe;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.renderer.ClassifierNamePolicy;
import org.jetbrains.kotlin.resolve.descriptorUtil.DescriptorUtilsKt;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.SimpleType;
import org.jetbrains.kotlin.types.SpecialTypesKt;
import org.jetbrains.kotlin.types.UnwrappedType;
import org.jetbrains.kotlin.types.typeUtil.TypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000;\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001c\n\u0000\n\u0002\u0010#\n\u0000*\u0001\u0006\u001a\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u000e\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\fH\u0002\u001a&\u0010\u000e\u001a\u00020\u000f2\u000e\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\n0\u0013H\u0002\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0010\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u0014"}, d2 = {"adaptiveClassifierPolicy", "Lorg/jetbrains/kotlin/renderer/ClassifierNamePolicy;", "Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext;", "getAdaptiveClassifierPolicy", "(Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext;)Lorg/jetbrains/kotlin/renderer/ClassifierNamePolicy;", "ADAPTIVE_CLASSIFIER_POLICY_KEY", "org/jetbrains/kotlin/diagnostics/rendering/AdaptiveClassifierNamePolicyKt$ADAPTIVE_CLASSIFIER_POLICY_KEY$1", "Lorg/jetbrains/kotlin/diagnostics/rendering/AdaptiveClassifierNamePolicyKt$ADAPTIVE_CLASSIFIER_POLICY_KEY$1;", "collectClassifiersFqNames", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqNameUnsafe;", "objectsToRender", Argument.Delimiters.none, Argument.Delimiters.none, "collectMentionedClassifiersFqNames", Argument.Delimiters.none, "contextObjects", Argument.Delimiters.none, CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, Argument.Delimiters.none, "org.jetbrains.kotlin:frontend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AdaptiveClassifierNamePolicyKt {
    private static final AdaptiveClassifierNamePolicyKt$ADAPTIVE_CLASSIFIER_POLICY_KEY$1 ADAPTIVE_CLASSIFIER_POLICY_KEY = new RenderingContext.Key<ClassifierNamePolicy>() { // from class: org.jetbrains.kotlin.diagnostics.rendering.AdaptiveClassifierNamePolicyKt$ADAPTIVE_CLASSIFIER_POLICY_KEY$1
        @Override // org.jetbrains.kotlin.diagnostics.rendering.RenderingContext.Key
        /* JADX INFO: renamed from: compute, reason: avoid collision after fix types in other method */
        public ClassifierNamePolicy compute2(Collection<? extends Object> objectsToRender, DiagnosticBaseContext diagnosticContext) {
            objectsToRender.getClass();
            diagnosticContext.getClass();
            Set setCollectClassifiersFqNames = AdaptiveClassifierNamePolicyKt.collectClassifiersFqNames(objectsToRender);
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Object obj : setCollectClassifiersFqNames) {
                Name nameShortNameOrSpecial = ((FqNameUnsafe) obj).shortNameOrSpecial();
                Object arrayList = linkedHashMap.get(nameShortNameOrSpecial);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    linkedHashMap.put(nameShortNameOrSpecial, arrayList);
                }
                ((List) arrayList).add(obj);
            }
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                if (((List) entry.getValue()).size() > 1) {
                    linkedHashMap2.put(entry.getKey(), entry.getValue());
                }
            }
            ArrayList arrayList2 = new ArrayList(linkedHashMap2.size());
            Iterator it = linkedHashMap2.entrySet().iterator();
            while (it.hasNext()) {
                arrayList2.add((Name) ((Map.Entry) it.next()).getKey());
            }
            return new AdaptiveClassifierNamePolicy(arrayList2);
        }

        @Override // org.jetbrains.kotlin.diagnostics.rendering.RenderingContext.Key
        public /* bridge */ /* synthetic */ ClassifierNamePolicy compute(Collection collection, DiagnosticBaseContext diagnosticBaseContext) {
            return compute2((Collection<? extends Object>) collection, diagnosticBaseContext);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public static final Set<FqNameUnsafe> collectClassifiersFqNames(Collection<? extends Object> collection) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        collectMentionedClassifiersFqNames(collection, linkedHashSet);
        return linkedHashSet;
    }

    private static final void collectMentionedClassifiersFqNames(Iterable<? extends Object> iterable, final Set<FqNameUnsafe> set) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : iterable) {
            if (obj instanceof KotlinType) {
                arrayList.add(obj);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            TypeUtilsKt.contains((KotlinType) it.next(), new Function1() { // from class: jv
                public final Object invoke(Object obj2) {
                    return Boolean.valueOf(AdaptiveClassifierNamePolicyKt.collectMentionedClassifiersFqNames$lambda$1$0(set, (UnwrappedType) obj2));
                }
            });
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : iterable) {
            if (obj2 instanceof Iterable) {
                arrayList2.add(obj2);
            }
        }
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            collectMentionedClassifiersFqNames((Iterable) it2.next(), set);
        }
        ArrayList arrayList3 = new ArrayList();
        for (Object obj3 : iterable) {
            if (obj3 instanceof ClassifierDescriptor) {
                arrayList3.add(obj3);
            }
        }
        Iterator it3 = arrayList3.iterator();
        while (it3.hasNext()) {
            set.add(DescriptorUtilsKt.getFqNameUnsafe((DeclarationDescriptor) ((ClassifierDescriptor) it3.next())));
        }
        ArrayList arrayList4 = new ArrayList();
        for (Object obj4 : iterable) {
            if (obj4 instanceof TypeParameterDescriptor) {
                arrayList4.add(obj4);
            }
        }
        Iterator it4 = arrayList4.iterator();
        while (it4.hasNext()) {
            List<KotlinType> upperBounds = ((TypeParameterDescriptor) it4.next()).getUpperBounds();
            upperBounds.getClass();
            collectMentionedClassifiersFqNames(upperBounds, set);
        }
        ArrayList<CallableDescriptor> arrayList5 = new ArrayList();
        for (Object obj5 : iterable) {
            if (obj5 instanceof CallableDescriptor) {
                arrayList5.add(obj5);
            }
        }
        for (CallableDescriptor callableDescriptor : arrayList5) {
            List<TypeParameterDescriptor> typeParameters = callableDescriptor.getTypeParameters();
            KotlinType returnType = callableDescriptor.getReturnType();
            List<ValueParameterDescriptor> valueParameters = callableDescriptor.getValueParameters();
            ParameterDescriptor dispatchReceiverParameter = callableDescriptor.getDispatchReceiverParameter();
            KotlinType type = null;
            KotlinType type2 = dispatchReceiverParameter != null ? dispatchReceiverParameter.getType() : null;
            ParameterDescriptor extensionReceiverParameter = callableDescriptor.getExtensionReceiverParameter();
            if (extensionReceiverParameter != null) {
                type = extensionReceiverParameter.getType();
            }
            collectMentionedClassifiersFqNames(CollectionsKt.listOf(new Object[]{typeParameters, returnType, valueParameters, type2, type}), set);
        }
    }

    private static final void collectMentionedClassifiersFqNames$addMentionedTypeConstructor(KotlinType kotlinType, Set<FqNameUnsafe> set) {
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        if (declarationDescriptor != null) {
            set.add(DescriptorUtilsKt.getFqNameUnsafe((DeclarationDescriptor) declarationDescriptor));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean collectMentionedClassifiersFqNames$lambda$1$0(Set set, UnwrappedType unwrappedType) {
        unwrappedType.getClass();
        collectMentionedClassifiersFqNames$addMentionedTypeConstructor(unwrappedType, set);
        SimpleType abbreviation = SpecialTypesKt.getAbbreviation(unwrappedType);
        if (abbreviation == null) {
            return false;
        }
        collectMentionedClassifiersFqNames$addMentionedTypeConstructor(abbreviation, set);
        return false;
    }

    public static final ClassifierNamePolicy getAdaptiveClassifierPolicy(RenderingContext renderingContext) {
        renderingContext.getClass();
        return (ClassifierNamePolicy) renderingContext.get(ADAPTIVE_CLASSIFIER_POLICY_KEY);
    }
}

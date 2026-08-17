package org.jetbrains.kotlin.ir.util;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0007H&J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\tH&J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0007H&J\u001e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u0010H&R\u0012\u0010\u0011\u001a\u00020\u0012X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0015À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/ir/util/IdSignatureComposer;", "", "composeSignature", "Lorg/jetbrains/kotlin/ir/util/IdSignature;", "descriptor", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "composeEnumEntrySignature", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "composeFieldSignature", "Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "composeAnonInitSignature", "withFileSignature", "", "fileSignature", "Lorg/jetbrains/kotlin/ir/util/IdSignature$FileSignature;", "body", "Lkotlin/Function0;", "mangler", "Lorg/jetbrains/kotlin/ir/util/KotlinMangler$DescriptorMangler;", "getMangler", "()Lorg/jetbrains/kotlin/ir/util/KotlinMangler$DescriptorMangler;", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface IdSignatureComposer {
    IdSignature composeAnonInitSignature(ClassDescriptor descriptor);

    IdSignature composeEnumEntrySignature(ClassDescriptor descriptor);

    IdSignature composeFieldSignature(PropertyDescriptor descriptor);

    IdSignature composeSignature(DeclarationDescriptor descriptor);

    KotlinMangler.DescriptorMangler getMangler();

    void withFileSignature(IdSignature.FileSignature fileSignature, Function0<Unit> body);
}

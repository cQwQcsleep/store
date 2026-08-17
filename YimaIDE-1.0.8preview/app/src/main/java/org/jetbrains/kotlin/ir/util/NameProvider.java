package org.jetbrains.kotlin.ir.util;

import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u0006J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/ir/util/NameProvider;", "", "nameForDeclaration", "Lorg/jetbrains/kotlin/name/Name;", "descriptor", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "DEFAULT", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface NameProvider {

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/ir/util/NameProvider$DEFAULT;", "Lorg/jetbrains/kotlin/ir/util/NameProvider;", "<init>", "()V", "nameForDeclaration", "Lorg/jetbrains/kotlin/name/Name;", "descriptor", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class DEFAULT implements NameProvider {
        public static final DEFAULT INSTANCE = new DEFAULT();

        private DEFAULT() {
        }

        @Override // org.jetbrains.kotlin.ir.util.NameProvider
        public Name nameForDeclaration(DeclarationDescriptor descriptor) {
            descriptor.getClass();
            Name name = descriptor.getName();
            name.getClass();
            return name;
        }
    }

    Name nameForDeclaration(DeclarationDescriptor descriptor);
}

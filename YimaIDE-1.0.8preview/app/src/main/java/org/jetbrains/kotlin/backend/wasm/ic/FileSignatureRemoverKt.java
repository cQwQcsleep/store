package org.jetbrains.kotlin.backend.wasm.ic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.ir.util.IdSignature;
import org.jetbrains.kotlin.ir.util.IdSignature$SpecialFakeOverrideSignature;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u0014\u0010\u0005\u001a\u00020\u0006*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u0014\u0010\u0007\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u0014\u0010\u0007\u001a\u00020\u0001*\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u0014\u0010\u0007\u001a\u00020\u0001*\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u0014\u0010\u0007\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u0014\u0010\u0007\u001a\u00020\u0001*\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u0014\u0010\u0007\u001a\u00020\u0001*\u00020\f2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u0014\u0010\u0007\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002¨\u0006\u000e"}, d2 = {"fileSignatureErasure", "Lorg/jetbrains/kotlin/ir/util/IdSignature;", "idSignature", "moduleName", "", "containsFileSignature", "", "rebuildSignature", "Lorg/jetbrains/kotlin/ir/util/IdSignature$AccessorSignature;", "Lorg/jetbrains/kotlin/ir/util/IdSignature$CompositeSignature;", "Lorg/jetbrains/kotlin/ir/util/IdSignature$FileLocalSignature;", "Lorg/jetbrains/kotlin/ir/util/IdSignature$LoweredDeclarationSignature;", "Lorg/jetbrains/kotlin/ir/util/IdSignature$SpecialFakeOverrideSignature;", "Lorg/jetbrains/kotlin/ir/util/IdSignature$FileSignature;", "org.jetbrains.kotlin:backend.wasm"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class FileSignatureRemoverKt {
    private static final boolean containsFileSignature(IdSignature idSignature, String str) {
        if (idSignature instanceof IdSignature.AccessorSignature) {
            return containsFileSignature(((IdSignature.AccessorSignature) idSignature).getPropertySignature(), str);
        }
        if (idSignature instanceof IdSignature.CommonSignature) {
            return false;
        }
        if (idSignature instanceof IdSignature.CompositeSignature) {
            IdSignature.CompositeSignature compositeSignature = (IdSignature.CompositeSignature) idSignature;
            return containsFileSignature(compositeSignature.getContainer(), str) || containsFileSignature(compositeSignature.getInner(), str);
        }
        if (idSignature instanceof IdSignature.FileLocalSignature) {
            return containsFileSignature(((IdSignature.FileLocalSignature) idSignature).getContainer(), str);
        }
        if (idSignature instanceof IdSignature.LocalSignature) {
            return false;
        }
        if (idSignature instanceof IdSignature.LoweredDeclarationSignature) {
            return containsFileSignature(((IdSignature.LoweredDeclarationSignature) idSignature).getOriginal(), str);
        }
        if (idSignature instanceof IdSignature.ScopeLocalDeclaration) {
            return false;
        }
        if (!(idSignature instanceof IdSignature$SpecialFakeOverrideSignature)) {
            if (idSignature instanceof IdSignature.FileSignature) {
                return true;
            }
            bu8.a();
            return false;
        }
        IdSignature$SpecialFakeOverrideSignature idSignature$SpecialFakeOverrideSignature = (IdSignature$SpecialFakeOverrideSignature) idSignature;
        if (!containsFileSignature(idSignature$SpecialFakeOverrideSignature.getMemberSignature(), str)) {
            List<IdSignature> overriddenSignatures = idSignature$SpecialFakeOverrideSignature.getOverriddenSignatures();
            if (!(overriddenSignatures instanceof Collection) || !overriddenSignatures.isEmpty()) {
                Iterator<T> it = overriddenSignatures.iterator();
                while (it.hasNext()) {
                    if (containsFileSignature((IdSignature) it.next(), str)) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    public static final IdSignature fileSignatureErasure(IdSignature idSignature, String str) {
        idSignature.getClass();
        str.getClass();
        return containsFileSignature(idSignature, str) ? rebuildSignature(idSignature, str) : idSignature;
    }

    private static final IdSignature rebuildSignature(IdSignature idSignature, String str) {
        if (idSignature instanceof IdSignature.AccessorSignature) {
            return rebuildSignature((IdSignature.AccessorSignature) idSignature, str);
        }
        if (idSignature instanceof IdSignature.CommonSignature) {
            return idSignature;
        }
        if (idSignature instanceof IdSignature.CompositeSignature) {
            return rebuildSignature((IdSignature.CompositeSignature) idSignature, str);
        }
        if (idSignature instanceof IdSignature.FileLocalSignature) {
            return rebuildSignature((IdSignature.FileLocalSignature) idSignature, str);
        }
        if (idSignature instanceof IdSignature.LocalSignature) {
            return idSignature;
        }
        if (idSignature instanceof IdSignature.LoweredDeclarationSignature) {
            return rebuildSignature((IdSignature.LoweredDeclarationSignature) idSignature, str);
        }
        if (idSignature instanceof IdSignature.ScopeLocalDeclaration) {
            return idSignature;
        }
        if (idSignature instanceof IdSignature$SpecialFakeOverrideSignature) {
            return rebuildSignature((IdSignature$SpecialFakeOverrideSignature) idSignature, str);
        }
        if (idSignature instanceof IdSignature.FileSignature) {
            return rebuildSignature((IdSignature.FileSignature) idSignature, str);
        }
        bu8.a();
        return null;
    }

    private static final IdSignature rebuildSignature(IdSignature.AccessorSignature accessorSignature, String str) {
        return new IdSignature.AccessorSignature(rebuildSignature(accessorSignature.getPropertySignature(), str), accessorSignature.getAccessorSignature());
    }

    private static final IdSignature rebuildSignature(IdSignature.CompositeSignature compositeSignature, String str) {
        return new IdSignature.CompositeSignature(rebuildSignature(compositeSignature.getContainer(), str), rebuildSignature(compositeSignature.getInner(), str));
    }

    private static final IdSignature rebuildSignature(IdSignature.FileLocalSignature fileLocalSignature, String str) {
        return new IdSignature.FileLocalSignature(rebuildSignature(fileLocalSignature.getContainer(), str), fileLocalSignature.getId());
    }

    private static final IdSignature rebuildSignature(IdSignature.LoweredDeclarationSignature loweredDeclarationSignature, String str) {
        return new IdSignature.LoweredDeclarationSignature(rebuildSignature(loweredDeclarationSignature.getOriginal(), str), loweredDeclarationSignature.getStage(), loweredDeclarationSignature.getIndex());
    }

    private static final IdSignature rebuildSignature(IdSignature$SpecialFakeOverrideSignature idSignature$SpecialFakeOverrideSignature, String str) {
        IdSignature idSignatureRebuildSignature = rebuildSignature(idSignature$SpecialFakeOverrideSignature.getMemberSignature(), str);
        List<IdSignature> overriddenSignatures = idSignature$SpecialFakeOverrideSignature.getOverriddenSignatures();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(overriddenSignatures, 10));
        Iterator<T> it = overriddenSignatures.iterator();
        while (it.hasNext()) {
            arrayList.add(rebuildSignature((IdSignature) it.next(), str));
        }
        return new IdSignature$SpecialFakeOverrideSignature(idSignatureRebuildSignature, arrayList);
    }

    private static final IdSignature rebuildSignature(IdSignature.FileSignature fileSignature, String str) {
        String str2 = str + fileSignature.getFileName();
        return new IdSignature.FileSignature(str2, FqName.ROOT, str2);
    }
}

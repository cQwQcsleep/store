package com.reandroid.dex.model;

import com.reandroid.dex.common.AccessFlag;
import com.reandroid.dex.common.IdDefinition;
import com.reandroid.dex.common.Modifier;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.ProgramKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.program.AccessibleProgram;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class DexDeclaration extends Dex implements AccessibleDex {
    @Override // com.reandroid.dex.program.AccessibleProgram
    public void addAccessFlag(AccessFlag accessFlag) {
        getDefinition().addAccessFlag(accessFlag);
    }

    @Override // com.reandroid.dex.program.AccessibleProgram
    public Iterator<? extends Modifier> getAccessFlags() {
        return getDefinition().getAccessFlags();
    }

    @Override // com.reandroid.dex.model.Dex
    public DexClassRepository getClassRepository() {
        DexLayout dexLayout = getDexLayout();
        if (dexLayout != null) {
            return dexLayout.getRootRepository();
        }
        return null;
    }

    public TypeKey getDefining() {
        return getKey().getDeclaring();
    }

    public abstract IdDefinition<?> getDefinition();

    public abstract DexClass getDexClass();

    public DexDirectory getDexDirectory() {
        DexFile dexFile = getDexFile();
        if (dexFile != null) {
            return dexFile.getDexDirectory();
        }
        return null;
    }

    public DexFile getDexFile() {
        return getDexLayout().getDexFile();
    }

    public DexLayout getDexLayout() {
        if (getClass() != DexClass.class) {
            return getDexClass().getDexLayout();
        }
        ib0.a("getDexFile() must be override for: ", getClass());
        return null;
    }

    public abstract IdItem getId();

    public abstract ProgramKey getKey();

    public String getPackageName() {
        return getDefining().getPackageName();
    }

    public boolean hasAccessFlag(AccessFlag accessFlag, AccessFlag accessFlag2, AccessFlag accessFlag3) {
        return hasAccessFlag(accessFlag) && hasAccessFlag(accessFlag2) && hasAccessFlag(accessFlag3);
    }

    public int hashCode() {
        ProgramKey key = getKey();
        if (key != null) {
            return key.hashCode();
        }
        return 0;
    }

    public boolean isAccessibleTo(DexClass dexClass) {
        DexClass dexClass2 = getDexClass();
        TypeKey defining = dexClass.getDefining();
        if (dexClass2.isAccessibleTo(defining)) {
            return dexClass2.getDefining().equals(defining) || dexClass2 == this || isAccessibleTo(defining);
        }
        return false;
    }

    public boolean isInSameDirectory(DexDirectory dexDirectory) {
        return getDexDirectory() == dexDirectory;
    }

    public boolean isInSameFile(DexDeclaration dexDeclaration) {
        if (dexDeclaration == null) {
            return false;
        }
        if (dexDeclaration == this) {
            return true;
        }
        DexLayout dexLayout = getDexLayout();
        return dexLayout != null && dexLayout == dexDeclaration.getDexLayout();
    }

    @Override // com.reandroid.dex.model.Dex
    public boolean isRemoved() {
        IdDefinition<?> definition = getDefinition();
        return definition == null || definition.isRemoved();
    }

    @Override // com.reandroid.dex.program.AccessibleProgram
    public void removeAccessFlag(AccessFlag accessFlag) {
        getDefinition().removeAccessFlag(accessFlag);
    }

    @Override // com.reandroid.dex.model.Dex
    public String toString() {
        return Modifier.toString(getAccessFlags()) + getKey();
    }

    @Override // com.reandroid.dex.model.Dex
    public boolean uses(Key key) {
        if (getKey().equals(key)) {
            return false;
        }
        return getDefinition().uses(key);
    }

    @Override // com.reandroid.dex.model.AccessibleDex, com.reandroid.dex.model.AnnotatedDex
    public AccessibleProgram getProgramElement() {
        return getDefinition();
    }

    public boolean hasAccessFlag(AccessFlag accessFlag, AccessFlag accessFlag2) {
        return hasAccessFlag(accessFlag) && hasAccessFlag(accessFlag2);
    }

    public boolean hasAccessFlag(AccessFlag accessFlag) {
        return accessFlag.isSet(getAccessFlagsValue());
    }

    public boolean isAccessibleTo(TypeKey typeKey) {
        if (getDefining().equals(typeKey)) {
            return true;
        }
        if (isInternal()) {
            return getPackageName().equals(typeKey.getPackageName());
        }
        return !isPrivate();
    }
}

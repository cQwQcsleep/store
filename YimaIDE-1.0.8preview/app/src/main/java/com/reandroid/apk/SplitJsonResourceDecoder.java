package com.reandroid.apk;

import com.reandroid.arsc.ARSCLib;
import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.chunk.StagedAlias;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.arsc.chunk.TypeBlock;
import com.reandroid.arsc.container.SpecTypePair;
import com.reandroid.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class SplitJsonResourceDecoder {
    private final TableBlock tableBlock;

    public SplitJsonResourceDecoder(TableBlock tableBlock) {
        this.tableBlock = tableBlock;
    }

    private void decodeSplitPackageJson(File file, PackageBlock packageBlock) throws IOException {
        File file2 = new File(file, packageBlock.buildDecodeDirectoryName());
        writeSplitPackageInfoJson(file2, packageBlock);
        Iterator<SpecTypePair> it = packageBlock.listSpecTypePairs().iterator();
        while (it.hasNext()) {
            Iterator<TypeBlock> it2 = it.next().getTypeBlockArray().listItems().iterator();
            while (it2.hasNext()) {
                writeSplitTypeJsonFiles(file2, it2.next());
            }
        }
    }

    private void writeSplitPackageInfoJson(File file, PackageBlock packageBlock) throws IOException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("arsc_lib_version", ARSCLib.getVersion());
        jSONObject.put(PackageBlock.NAME_package_id, packageBlock.getId());
        jSONObject.put(PackageBlock.NAME_package_name, packageBlock.getName());
        StagedAlias stagedAliasMergeAll = StagedAlias.mergeAll(packageBlock.getStagedAliasList().getChildes());
        if (stagedAliasMergeAll != null) {
            jSONObject.put(PackageBlock.NAME_staged_aliases, stagedAliasMergeAll.getStagedAliasEntryArray().m30toJson());
        }
        jSONObject.write(new File(file, PackageBlock.JSON_FILE_NAME));
    }

    private void writeSplitTypeJsonFiles(File file, TypeBlock typeBlock) throws IOException {
        typeBlock.toJson().write(new File(file, typeBlock.buildUniqueDirectoryName() + ".json"));
    }

    public void decodeSplitJsonFiles(File file) throws IOException {
        Iterator<PackageBlock> it = this.tableBlock.listPackages().iterator();
        while (it.hasNext()) {
            decodeSplitPackageJson(file, it.next());
        }
    }
}

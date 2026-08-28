package rikka.shizuku;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.io.FileDescriptor;
import java.util.Objects;
import obfuse.NPStringFog;

/* loaded from: /workspace/unpacked/classes.dex */
public class ShizukuBinderWrapper implements IBinder {
    private final IBinder original;

    public ShizukuBinderWrapper(IBinder iBinder) {
        this.original = (IBinder) Objects.requireNonNull(iBinder);
    }

    @Override // android.os.IBinder
    public void dump(FileDescriptor fileDescriptor, String[] strArr) throws RemoteException {
        this.original.dump(fileDescriptor, strArr);
    }

    @Override // android.os.IBinder
    public void dumpAsync(FileDescriptor fileDescriptor, String[] strArr) throws RemoteException {
        this.original.dumpAsync(fileDescriptor, strArr);
    }

    @Override // android.os.IBinder
    public String getInterfaceDescriptor() throws RemoteException {
        return this.original.getInterfaceDescriptor();
    }

    @Override // android.os.IBinder
    public boolean isBinderAlive() {
        return this.original.isBinderAlive();
    }

    @Override // android.os.IBinder
    public void linkToDeath(IBinder.DeathRecipient deathRecipient, int i) throws RemoteException {
        this.original.linkToDeath(deathRecipient, i);
    }

    @Override // android.os.IBinder
    public boolean pingBinder() {
        return this.original.pingBinder();
    }

    @Override // android.os.IBinder
    public IInterface queryLocalInterface(String str) {
        return null;
    }

    @Override // android.os.IBinder
    public boolean transact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        boolean z = !Shizuku.isPreV11() && Shizuku.getVersion() >= 13;
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken(NPStringFog.decode("031F084F1D090E1F07050543120B1311000040393E09071B120E073D151F17070202"));
            parcelObtain.writeStrongBinder(this.original);
            parcelObtain.writeInt(i);
            if (z) {
                parcelObtain.writeInt(i2);
            }
            parcelObtain.appendFrom(parcel, 0, parcel.dataSize());
            if (z) {
                Shizuku.transactRemote(parcelObtain, parcel2, 0);
            } else {
                Shizuku.transactRemote(parcelObtain, parcel2, i2);
            }
            return true;
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // android.os.IBinder
    public boolean unlinkToDeath(IBinder.DeathRecipient deathRecipient, int i) {
        return this.original.unlinkToDeath(deathRecipient, i);
    }
}

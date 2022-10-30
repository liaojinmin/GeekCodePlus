package me.GeekCodePlus.Libraries;

import sun.misc.Unsafe;

import java.io.File;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;

public final class LibrariesClassLoader {


    private MethodHandles.Lookup lookup;
    private Unsafe unsafe;

    public LibrariesClassLoader() {
        super();
    }

    {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
            Field lookupField = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
            Object lookupBase = unsafe.staticFieldBase(lookupField);
            long lookupOffset = unsafe.staticFieldOffset(lookupField);
            lookup = (MethodHandles.Lookup) unsafe.getObject(lookupBase, lookupOffset);
        } catch (Throwable ignore) {
            ignore.printStackTrace();
        }
    }

    public void addPath(File file, ClassLoader loader) {
        try {
            Field ucpField;
            try {
                ucpField = URLClassLoader.class.getDeclaredField("ucp");
            } catch (NoSuchFieldError | NoSuchFieldException e) {
                try {
                    ucpField = loader.getClass().getDeclaredField("ucp");
                } catch (NoSuchFieldError | NoSuchFieldException e1) {
                    ucpField = loader.getClass().getSuperclass().getDeclaredField("ucp");
                }
            }
            long ucpOffset = unsafe.objectFieldOffset(ucpField);
            Object ucp = unsafe.getObject(loader, ucpOffset);
            MethodHandle methodHandle = lookup.findVirtual(ucp.getClass(), "addURL", MethodType.methodType(void.class, URL.class));
            methodHandle.invoke(ucp, file.toURI().toURL());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}

package com.example.lamia.charitycalc;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatDrawableManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        useCompatVectorIfNeeded();
    }

    private void useCompatVectorIfNeeded() {
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt == 21 || sdkInt == 22) { //vector drawables scale correctly in API level 23
            try {
                AppCompatDrawableManager drawableManager = AppCompatDrawableManager.get();
                Class<?> inflateDelegateClass = Class.forName("android.support.v7.widget.AppCompatDrawableManager$InflateDelegate");
                Class<?> vdcInflateDelegateClass = Class.forName("android.support.v7.widget.AppCompatDrawableManager$VdcInflateDelegate");

                Constructor<?> constructor = vdcInflateDelegateClass.getDeclaredConstructor();
                constructor.setAccessible(true);
                Object vdcInflateDelegate = constructor.newInstance();

                Class<?> args[] = {String.class, inflateDelegateClass};
                Method addDelegate = AppCompatDrawableManager.class.getDeclaredMethod("addDelegate", args);
                addDelegate.setAccessible(true);
                addDelegate.invoke(drawableManager, "vector", vdcInflateDelegate);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}

package top.hendrixshen.magiclib.compat.minecraft.math;

import com.mojang.math.Matrix4f;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector4f;
import top.hendrixshen.magiclib.compat.annotation.Remap;

public interface Matrix4fCompatApi {
    static Matrix4f createScaleMatrixCompat(float f, float g, float h) {
        Matrix4f matrix4f = new Matrix4f();
        matrix4f.set(0, 0, f);
        matrix4f.set(1, 1, g);
        matrix4f.set(2, 2, h);
        matrix4f.set(3, 3, 1);
        return matrix4f;
    }

    @Remap(value = "method_22668", dup = true)
    default void setIdentityCompat() {
    }

    @Remap(value = "method_31544", dup = true)
    default void multiplyWithTranslationCompat(float f, float g, float h) {

    }

    @Remap(value = "method_22672", dup = true)
    default void multiplyCompat(Matrix4f matrix4f) {
    }

    @Remap(value = "method_22670", dup = true)
    default void multiplyCompat(Quaternion quaternion) {
    }

    @Remap(value = "method_22673", dup = true)
    default Matrix4f copyCompat() {
        return null;
    }

    default void multiplyVector4f(Vector4f vector4f) {
        // vector4f.transform(matrix4f);
    }
}
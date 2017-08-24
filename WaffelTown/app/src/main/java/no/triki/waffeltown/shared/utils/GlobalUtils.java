package no.triki.waffeltown.shared.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Jonas on 24/08/2017.
 */

public class GlobalUtils {

    public static Bitmap byteArrayToBitmap(byte[] data) {
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, float degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, stream);
        return stream.toByteArray();
    }

    public static RequestBody byteArrayToImage(byte[] data) {
        return RequestBody.create(MediaType.parse("image/jpeg"), data);
    }

    public static Bitmap setImageViewWithByteArray(ImageView view, byte[] data) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        bitmap = rotateBitmap(bitmap, 90f);
        view.setImageBitmap(bitmap);
        return bitmap;
    }

    public static RequestBody plainTextToRequestBody(String text) {
        return RequestBody.create(MediaType.parse("text/plain"), text);
    }

    public static RequestBody jsonToRequestBody(String json) {
        return RequestBody.create(MediaType.parse("application/json"), json);
    }
}

package loofer.org.largeimageview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

import loofer.org.largeimageview.view.LargeImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView mIvLarge;
    private LargeImageView mLargeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initSimple();

        mLargeImageView = (LargeImageView) findViewById(R.id.largeImageView);

        try {
            InputStream inputStream = getAssets().open("qmsht.jpg");
            mLargeImageView.setInputStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initSimple() {
        mIvLarge = (ImageView) findViewById(R.id.iv_large);

        try {
            InputStream inputStream = getAssets().open("pet.jpg");

            //获取图片宽高
            BitmapFactory.Options tmpOptions = new BitmapFactory.Options();
            //不产生bitmap对象，只获取宽高
            tmpOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, null, tmpOptions);
            int width = tmpOptions.outWidth;
            int height = tmpOptions.outHeight;

            //设置图片中心区域
            //false，创建一个输入流的复制，并且一直使用它
            // true，程序也有可能会创建一个输入流的深度复制
            BitmapRegionDecoder bitmapRegionDecoder = BitmapRegionDecoder.newInstance(inputStream, false);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            Bitmap bitmap = bitmapRegionDecoder.decodeRegion(new Rect(width / 2 - 100, height / 2 - 100, width / 2 + 100, height / 2 + 100), options);
            mIvLarge.setImageBitmap(bitmap);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

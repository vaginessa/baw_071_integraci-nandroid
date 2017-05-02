package mx.com.baware.exampleapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by aguilarzhe on 4/10/17.
 */

public class ImageViewActivity extends AppCompatActivity {
    public static final String IMAGE_ARG = "imagearg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview);
        ImageView imageView = (ImageView) findViewById(R.id.imageview);

        if(getIntent().hasExtra(IMAGE_ARG)) {
            File image = new File(getIntent().getStringExtra(IMAGE_ARG));
            String src = getIntent().getStringExtra("imagearg");
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(),bmOptions);
            bitmap = Bitmap.createScaledBitmap(bitmap, 300, 300,true);
            imageView.setImageBitmap(bitmap);
        }
    }
}

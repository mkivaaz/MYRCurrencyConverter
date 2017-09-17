package magic.com.myrcurrencyconverter;

import android.content.Intent;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;

import java.io.InputStream;

import magic.com.myrcurrencyconverter.SVG.SvgDecoder;
import magic.com.myrcurrencyconverter.SVG.SvgDrawableTranscoder;
import magic.com.myrcurrencyconverter.SVG.SvgSoftwareLayerSetter;
import magic.com.myrcurrencyconvertor.R;

public class CountryDetails extends AppCompatActivity {
    private GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);

        String CountryName = null;
        String Flag = null;
        Intent intent = getIntent();
        CountryName = intent.getStringExtra("CountryName");
        Flag  = intent.getStringExtra("Flag" );

        TextView Fullname = (TextView) findViewById(R.id.countryFname);
        ImageView flag = (ImageView) findViewById(R.id.flag);
        Fullname.setText(CountryName);
        requestBuilder = Glide.with(getBaseContext())
                .using(Glide.buildStreamModelLoader(Uri.class, getBaseContext()), InputStream.class)
                .from(Uri.class)
                .as(SVG.class)
                .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                .sourceEncoder(new StreamEncoder())
                .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
                .decoder(new SvgDecoder())
                .placeholder(R.drawable.loading)
                .animate(android.R.anim.fade_in)
                .listener(new SvgSoftwareLayerSetter<Uri>());

        Uri uri = Uri.parse(Flag);
        requestBuilder
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                // SVG cannot be serialized so it's not worth to cache it
                .load(uri)
                .into(flag);

    }
}


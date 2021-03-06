package nicolas.quillon.iem.gestion_comics.ui.Vue;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nicolas.quillon.iem.gestion_comics.Presenter.DetailsPresenter;
import nicolas.quillon.iem.gestion_comics.Presenter.DetailsView;
import nicolas.quillon.iem.gestion_comics.R;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class ComicDetailsActivity extends AppCompatActivity implements DetailsView{

    private TextView textViewTitre ;
    private TextView textViewSynopsis;
    private TextView textViewInfo;
    private TextView textViewDate;
    private TextView textViewPrice;
    private TextView textViewNbPages;
    private TextView textViewDiamondCode;
    private TextView textViewCredit;
    private ImageView imageViewComic;

    private DetailsPresenter detailsPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_details);

        initializeView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_menu, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.shareButton:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,detailsPresenter.getTitleComic());
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, detailsPresenter.getSharedText());
                startActivity(Intent.createChooser(sharingIntent, "Share"));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void initializeView(){
        setTitle(R.string.title_details);

        detailsPresenter = new DetailsPresenter(this, this, (int) getIntent().getExtras().get("index"));

        textViewTitre = (TextView) findViewById(R.id.textViewTitre);
        textViewTitre.setText(detailsPresenter.getTitleComic());

        textViewSynopsis = (TextView) findViewById(R.id.Synopsis);
        textViewSynopsis.setText(detailsPresenter.getSynopsisComic());

        textViewInfo = (TextView) findViewById(R.id.textViewInfo);

        textViewDate = (TextView) findViewById(R.id.textViewDate);
        textViewDate.setText(detailsPresenter.getDateComic());

        textViewPrice = (TextView) findViewById(R.id.textViewPrice);
        textViewPrice.setText(detailsPresenter.getPriceComic());

        textViewNbPages = (TextView) findViewById(R.id.textViewNbPage);
        textViewNbPages.setText(detailsPresenter.getnbPageComic());

        textViewDiamondCode = (TextView) findViewById(R.id.textViewDiamondCode);
        textViewDiamondCode.setText(detailsPresenter.getDiamondComic());

        textViewCredit = (TextView) findViewById(R.id.textViewCredit);
        textViewCredit.setText(detailsPresenter.getCreditComic());

        imageViewComic = (ImageView) findViewById(R.id.imageViewComic);
        Picasso.with(this).load(detailsPresenter.getImageComic()).resize(240, 350).into(imageViewComic);


    }
}

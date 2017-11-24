package lyhoangvinh.com.jocky_mvp.ui.Home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yalantis.ucrop.UCrop;

import java.io.File;

import lyhoangvinh.com.jocky_mvp.DatabaseHelper.DriverController;
import lyhoangvinh.com.jocky_mvp.Model.Driver;
import lyhoangvinh.com.jocky_mvp.R;
import lyhoangvinh.com.jocky_mvp.ui.Login.LoginActivity;
import lyhoangvinh.com.jocky_mvp.utils.crop.BaseActivity;
import lyhoangvinh.com.jocky_mvp.utils.crop.CropImageUtil;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private LinearLayout lnUploadPicture;
    private int IMAGE_CAPTURE_REQUEST_CODE = 123;
    private TextView tvNameMenu;
    private ImageView imvAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
//        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_home);
        tvNameMenu = (TextView) headerView.findViewById(R.id.tvNameMenu);
        imvAvatar = (ImageView) headerView.findViewById(R.id.imvAvatar);
        init();
        initEvent();
        loadUser();

    }

    private void init(){
        lnUploadPicture = (LinearLayout) findViewById(R.id.lnBtnUploadSelfie);
    }

    private void initEvent(){
        lnUploadPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, IMAGE_CAPTURE_REQUEST_CODE);
            }
        });
    }

    private void loadUser(){
        tvNameMenu.setText(DriverController.getCurrentDriver(this).getLastname());
        Glide.with(this)
                .load(DriverController.getCurrentDriver(this).getPicture())
                .into(imvAvatar);
        Log.d("HomeActivity", DriverController.getCurrentDriver(this).getLastname());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == CropImageUtil.PICK_IMAGE_CHOOSER_REQUEST_CODE) {
                Uri selectedUri = CropImageUtil.getPickImageResultUri(this, data);
                if (selectedUri != null) {
                    startCropActivity(selectedUri);
                } else {
                    Toast.makeText(this, R.string.toast_cannot_retrieve_selected_image, Toast.LENGTH_SHORT).show();
                }
            } else if (requestCode == UCrop.REQUEST_CROP) {
                //handleCropResult(data);
            }
        }
        if (resultCode == UCrop.RESULT_ERROR) {
            handleCropError(data);
        }
    }

    private void startCropActivity(@NonNull Uri uri) {
        String destinationFileName = "avatar_crop";
        destinationFileName += ".jpg";

        UCrop.Options options = new UCrop.Options();
//        options.setMaxBitmapSize();
        options.setHideBottomControls(true);
        options.setToolbarColor(getResources().getColor(R.color.colorPrimaryDark));
        options.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        options.setToolbarTitle("Crop Photo");
        UCrop uCrop = UCrop.of(uri, Uri.fromFile(new File(this.getCacheDir(), destinationFileName)))
                .withAspectRatio(1, 1)
                .withMaxResultSize(500, 500)
                .withOptions(options);
        uCrop.start(this);
    }

    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    private void handleCropError(@NonNull Intent result) {
        final Throwable cropError = UCrop.getError(result);
        if (cropError != null) {
            Toast.makeText(this, cropError.getMessage(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, R.string.toast_unexpected_error, Toast.LENGTH_SHORT).show();
        }
    }

//    private void handleCropResult(@NonNull Intent result) {
//        final Uri resultUri = UCrop.getOutput(result);
//        if (resultUri != null) {
//            imvAvatar.setImageURI(resultUri);
//            // TODO: upload image to server
//            presenter.userUpdatePicture(
//                    Functions.toRequestBody(Functions.getUser(getActivity()).getToken()),
//                    Functions.prepareFilePart(getActivity(), "picture", resultUri));
//        } else {
//            Toast.makeText(getActivity(), R.string.toast_cannot_retrieve_cropped_image, Toast.LENGTH_SHORT).show();
//        }
//    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }else if (id == R.id.nav_logout){
            Driver.deleteAll(Driver.class);
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

package com.navadhan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.navadhan.databinding.ActivityImageToTextBinding;
import com.navadhan.model.HouseholdData;
import com.navadhan.viewmodel.ImageToTextViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ImageToTextActivity extends AppCompatActivity implements View.OnClickListener {
HouseholdData householdData;
String document_type;
ActivityImageToTextBinding binding;
ImageToTextViewModel imageToTextViewModel;
private static final int PICK_IMAGE = 100;
Uri image_URI;
Bitmap bitmap;
String parserText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_to_text);
        imageToTextViewModel = ViewModelProviders.of(this).get(ImageToTextViewModel.class);
        binding.setViewModel(imageToTextViewModel);
        document_type = getIntent().getStringExtra("document_type");
        householdData = (HouseholdData) getIntent().getSerializableExtra("household_data");
        binding.selectimg.setOnClickListener(this::onClick);
        binding.extractimg.setOnClickListener(this::onClick);
        binding.update.setOnClickListener(this::onClick);
        binding.nextBtn.setOnClickListener(this::onClick);
        imageToTextViewModel.getResponseupdateDetails().observe(this,responseModel -> {
            JSONObject jsonObject;

                try {
                    if (responseModel.getJsonObject() != null) {
                        if (responseModel.getJsonObject().getString("status").equals("1")) {
                            binding.nextBtn.setVisibility(View.VISIBLE);
                            Toast.makeText(this, responseModel.getJsonObject().getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                }

        });

     }

    @Override
    public void onClick(View v) {
        String document = getIntent().getStringExtra("document_type");
        switch (v.getId()){
            case R.id.selectimg:
                    openGallery();
                break;
            case R.id.extractimg:


                switch (document){
                    case "PAN":
                        parserText= imageToTextViewModel.extractPanCrad(bitmap);
                        binding.tvContent.setText(parserText);
                        break;
                    case "Aaddhar Front":
                        parserText=imageToTextViewModel.extractAdharCardFront(bitmap);
                        binding.tvContent.setText(parserText);
                        break;
                    case "Aaddhar Back":
                        parserText=imageToTextViewModel.extractAdharCardBack(bitmap);
                        binding.tvContent.setText(parserText);
                        break;
                    case "Voter ID":
                        parserText=imageToTextViewModel.extractVoterId(bitmap);
                        binding.tvContent.setText(parserText);
                        break;
                    case "Driving License":
                        break;
                    default:
                        Toast.makeText(ImageToTextActivity.this, "not a valid document type.", Toast.LENGTH_SHORT).show();
                        break;
                }
                break;
            case R.id.update:
                switch (document){
                    case "PAN":
                        imageToTextViewModel.updatePanIntoDatabase(householdData.getFamily_member_name(),householdData.getMobile_no(),parserText);
                        break;
                    case "Aaddhar Front":
                        imageToTextViewModel.updateAdharIntoDatabase(householdData.getFamily_member_name(),householdData.getMobile_no(),parserText);
                        break;
                    case "Aaddhar Back":

                        break;
                    case "Voter ID":
                        imageToTextViewModel.updateVoterIntoDatabase(householdData.getFamily_member_name(),householdData.getMobile_no(),parserText);
                        break;
                    case "Driving License":
                        break;
                    default:
                        Toast.makeText(ImageToTextActivity.this, "not a valid document type.", Toast.LENGTH_SHORT).show();
                        break;
                }
                break;
            case R.id.nextBtn:
                        imageToTextViewModel.shiftToNextKyc(householdData);
            default:
                break;

        }
    }
    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode==PICK_IMAGE){
            image_URI = data.getData();
            try {
                InputStream pic = getContentResolver().openInputStream(image_URI);
                bitmap = BitmapFactory.decodeStream(pic);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
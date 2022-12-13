package com.rortegag.boker.main.navigation.categories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.rortegag.boker.R;

public class CategoriesFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_categories, container, false);

        /*
                List<Volume> googleVolumes = googleBooks.volumes().list("category:'Young Adult'").execute().getItems();
                try {
                    for (Volume volume : googleVolumes) {
                        Volume.VolumeInfo volumeInfo = volume.getVolumeInfo();
                        List<String> categories = volumeInfo.getCategories();
                        for (String category : categories) {
                            Log.e("category", category.trim());
                        }
                    }
                } catch (NullPointerException e) {
                    Log.e("category", "NA");
                }
                */

        return root;
    }
}
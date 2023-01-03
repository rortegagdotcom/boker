package com.rortegag.boker.main.navigation.genres;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rortegag.boker.R;
import com.rortegag.boker.main.ViewSynopsisActivity;

public class GenresFragment extends Fragment
{
    private CardView
            cardGenreAutobiographyBiography,
            cardGenreEssay,
            cardGenreFable,
            cardGenreHistory,
            cardGenreLegend,
            cardGenreMyth,
            cardGenreNovel,
            cardGenrePoetry,
            cardGenreStory,
            cardGenreTheater;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_genres, container, false);
        cardGenreAutobiographyBiography = root.findViewById(R.id.cardGenreAutobiographyBiography);
        cardGenreEssay = root.findViewById(R.id.cardGenreEssay);
        cardGenreFable = root.findViewById(R.id.cardGenreFable);
        cardGenreHistory = root.findViewById(R.id.cardGenreHistory);
        cardGenreLegend = root.findViewById(R.id.cardGenreLegend);
        cardGenreMyth = root.findViewById(R.id.cardGenreMyth);
        cardGenreNovel = root.findViewById(R.id.cardGenreNovel);
        cardGenrePoetry = root.findViewById(R.id.cardGenrePoetry);
        cardGenreStory = root.findViewById(R.id.cardGenreStory);
        cardGenreTheater = root.findViewById(R.id.cardGenreTheater);

        Context context = root.getContext();

        cardGenreAutobiographyBiography.setOnClickListener(new GenreClickListener(context, "subject:autobiography%20&%20biography"));
        cardGenreEssay.setOnClickListener(new GenreClickListener(context, "subject:essay"));
        cardGenreFable.setOnClickListener(new GenreClickListener(context, "subject:fable"));
        cardGenreHistory.setOnClickListener(new GenreClickListener(context, "subject:history"));
        cardGenreLegend.setOnClickListener(new GenreClickListener(context, "subject:legend"));
        cardGenreMyth.setOnClickListener(new GenreClickListener(context, "subject:myth"));
        cardGenreNovel.setOnClickListener(new GenreClickListener(context, "subject:novel"));
        cardGenrePoetry.setOnClickListener(new GenreClickListener(context, "subject:poetry"));
        cardGenreStory.setOnClickListener(new GenreClickListener(context, "subject:story"));
        cardGenreTheater.setOnClickListener(new GenreClickListener(context, "subject:theater"));

        return root;
    }

    private class GenreClickListener implements View.OnClickListener {
        private Context context;
        private String genre;

        public GenreClickListener(Context context, String category) {
            this.context = context;
            this.genre = category;
        }

        @Override
        public void onClick(View v) {
            Bundle bundleGenre = new Bundle();
            bundleGenre.putString("genre", genre);
            Intent intent = new Intent(context, ViewSynopsisActivity.class);
            intent.putExtras(bundleGenre);
            startActivity(intent);
        }
    }
}
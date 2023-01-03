package com.rortegag.boker.main.navigation.categories;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.rortegag.boker.R;
import com.rortegag.boker.main.ViewSynopsisActivity;

public class CategoriesFragment extends Fragment {

    private CardView
            cardCategoryAgriculture,
            cardCategoryArt,
            cardCategoryArchitecture,
            cardCategoryAutobiographyBiography,
            cardCategoryBusinessEconomics,
            cardCategoryClassic,
            cardCategoryComputers,
            cardCategoryCookbooks,
            cardCategoryDesign,
            cardCategoryEducation,
            cardCategoryFiction,
            cardCategoryFantasy,
            cardCategoryGovernment,
            cardCategoryHealthFitness,
            cardCategoryHistory,
            cardCategoryHumor,
            cardCategoryJuvenile,
            cardCategoryJuvenileFiction,
            cardCategoryJuvenileNonfiction,
            cardCategoryLanguageArtsDisciplines,
            cardCategoryLaw,
            cardCategoryLiteraryCriticism,
            cardCategoryMathematics,
            cardCategoryMusic,
            cardCategoryMystery,
            cardCategoryPerformingArts,
            cardCategoryPets,
            cardCategoryPhilosophy,
            cardCategoryPhotography,
            cardCategoryPoliticalScience,
            cardCategoryPsychology,
            cardCategoryReligion,
            cardCategoryRomance,
            cardCategoryScience,
            cardCategorySelfHelp,
            cardCategoryScienceFiction,
            cardCategorySocialScience,
            cardCategoryTechnologyEngineering,
            cardCategoryTelecommunication,
            cardCategoryTerror;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_categories, container, false);
        cardCategoryAgriculture = root.findViewById(R.id.cardCategoryAgriculture);
        cardCategoryArt = root.findViewById(R.id.cardCategoryArt);
        cardCategoryArchitecture = root.findViewById(R.id.cardCategoryArchitecture);
        cardCategoryAutobiographyBiography = root.findViewById(R.id.cardCategoryAutobiographyBiography);
        cardCategoryBusinessEconomics = root.findViewById(R.id.cardCategoryBusinessEconomics);
        cardCategoryClassic = root.findViewById(R.id.cardCategoryClassic);
        cardCategoryComputers = root.findViewById(R.id.cardCateogryComputers);
        cardCategoryCookbooks = root.findViewById(R.id.cardCategoryCookbooks);
        cardCategoryDesign = root.findViewById(R.id.cardCategoryDesign);
        cardCategoryEducation = root.findViewById(R.id.cardCategoryEducation);
        cardCategoryFiction = root.findViewById(R.id.cardCategoryFiction);
        cardCategoryFantasy = root.findViewById(R.id.cardCategoryFantasy);
        cardCategoryGovernment = root.findViewById(R.id.cardCategoryGovernment);
        cardCategoryHealthFitness = root.findViewById(R.id.cardCategoryHealthFitness);
        cardCategoryHistory = root.findViewById(R.id.cardCategoryHistory);
        cardCategoryHumor = root.findViewById(R.id.cardCategoryHumor);
        cardCategoryJuvenile = root.findViewById(R.id.cardCateogryJuvenile);
        cardCategoryJuvenileFiction = root.findViewById(R.id.cardCategoryJuvenileFiction);
        cardCategoryJuvenileNonfiction = root.findViewById(R.id.cardCategoryJuvenileNonfiction);
        cardCategoryLanguageArtsDisciplines = root.findViewById(R.id.cardCategoryLanguageArtsDisciplines);
        cardCategoryLaw = root.findViewById(R.id.cardCategoryLaw);
        cardCategoryLiteraryCriticism = root.findViewById(R.id.cardCategoryLiteraryCriticism);
        cardCategoryMathematics = root.findViewById(R.id.cardCategoryMathematics);
        cardCategoryMusic = root.findViewById(R.id.cardCategoryMusic);
        cardCategoryMystery = root.findViewById(R.id.cardCategoryMystery);
        cardCategoryPerformingArts = root.findViewById(R.id.cardCategoryPerformingArts);
        cardCategoryPets = root.findViewById(R.id.cardCategoryPets);
        cardCategoryPhilosophy = root.findViewById(R.id.cardCategoryPhilosophy);
        cardCategoryPhotography = root.findViewById(R.id.cardCategoryPhotography);
        cardCategoryPoliticalScience = root.findViewById(R.id.cardCategoryPoliticalScience);
        cardCategoryPsychology = root.findViewById(R.id.cardCategoryPsychology);
        cardCategoryReligion = root.findViewById(R.id.cardCategoryReligion);
        cardCategoryRomance = root.findViewById(R.id.cardCategoryRomance);
        cardCategoryScience = root.findViewById(R.id.cardCategoryScience);
        cardCategorySelfHelp = root.findViewById(R.id.cardCategorySelfHelp);
        cardCategoryScienceFiction = root.findViewById(R.id.cardCategoryScienceFiction);
        cardCategorySocialScience = root.findViewById(R.id.cardCategorySocialScience);
        cardCategoryTechnologyEngineering = root.findViewById(R.id.cardCategoryTechnologyEngineering);
        cardCategoryTelecommunication = root.findViewById(R.id.cardCategoryTelecommunication);
        cardCategoryTerror = root.findViewById(R.id.cardCategoryTerror);

        Context context = root.getContext();

        cardCategoryAgriculture.setOnClickListener(new CategoryClickListener(context, "category:agriculture"));
        cardCategoryArt.setOnClickListener(new CategoryClickListener(context, "category:art"));
        cardCategoryArchitecture.setOnClickListener(new CategoryClickListener(context, "category:architecture"));
        cardCategoryAutobiographyBiography.setOnClickListener(new CategoryClickListener(context, "category:autobiography%20&%20biography"));
        cardCategoryBusinessEconomics.setOnClickListener(new CategoryClickListener(context, "category:business%20&%20economics"));
        cardCategoryClassic.setOnClickListener(new CategoryClickListener(context, "category:classic"));
        cardCategoryComputers.setOnClickListener(new CategoryClickListener(context, "category:computers"));
        cardCategoryCookbooks.setOnClickListener(new CategoryClickListener(context, "category:cookbooks"));
        cardCategoryDesign.setOnClickListener(new CategoryClickListener(context, "category:design"));
        cardCategoryEducation.setOnClickListener(new CategoryClickListener(context, "category:education"));
        cardCategoryFiction.setOnClickListener(new CategoryClickListener(context, "category:fiction"));
        cardCategoryFantasy.setOnClickListener(new CategoryClickListener(context, "category:fantasy"));
        cardCategoryGovernment.setOnClickListener(new CategoryClickListener(context, "category:government%20publications"));
        cardCategoryHealthFitness.setOnClickListener(new CategoryClickListener(context, "category:health%20&%20fitness"));
        cardCategoryHistory.setOnClickListener(new CategoryClickListener(context, "category:history"));
        cardCategoryHumor.setOnClickListener(new CategoryClickListener(context, "category:humor"));
        cardCategoryJuvenile.setOnClickListener(new CategoryClickListener(context, "category:juvenile"));
        cardCategoryJuvenileFiction.setOnClickListener(new CategoryClickListener(context, "category:juvenile%20fiction"));
        cardCategoryJuvenileNonfiction.setOnClickListener(new CategoryClickListener(context, "category:juvenile%20nonfiction"));
        cardCategoryLanguageArtsDisciplines.setOnClickListener(new CategoryClickListener(context, "language%20arts%20&%20disciplines"));
        cardCategoryLaw.setOnClickListener(new CategoryClickListener(context, "category:law"));
        cardCategoryLiteraryCriticism.setOnClickListener(new CategoryClickListener(context, "category:literary%20criticism"));
        cardCategoryMathematics.setOnClickListener(new CategoryClickListener(context, "category:mathematics"));
        cardCategoryMusic.setOnClickListener(new CategoryClickListener(context, "category:music"));
        cardCategoryMystery.setOnClickListener(new CategoryClickListener(context, "category:mystery"));
        cardCategoryPerformingArts.setOnClickListener(new CategoryClickListener(context, "category:performing%20arts"));
        cardCategoryPets.setOnClickListener(new CategoryClickListener(context, "category:pets"));
        cardCategoryPhilosophy.setOnClickListener(new CategoryClickListener(context, "category:philosophy"));
        cardCategoryPhotography.setOnClickListener(new CategoryClickListener(context, "category:photography"));
        cardCategoryPoliticalScience.setOnClickListener(new CategoryClickListener(context, "category:political%20science"));
        cardCategoryPsychology.setOnClickListener(new CategoryClickListener(context, "category:psychology"));
        cardCategoryReligion.setOnClickListener(new CategoryClickListener(context, "category:religion"));
        cardCategoryRomance.setOnClickListener(new CategoryClickListener(context, "category:romance"));
        cardCategoryScience.setOnClickListener(new CategoryClickListener(context, "category:science"));
        cardCategorySelfHelp.setOnClickListener(new CategoryClickListener(context, "category:self%20help"));
        cardCategoryScienceFiction.setOnClickListener(new CategoryClickListener(context, "category:science%20fiction"));
        cardCategorySocialScience.setOnClickListener(new CategoryClickListener(context, "category:social%20science"));
        cardCategoryTechnologyEngineering.setOnClickListener(new CategoryClickListener(context, "category:technology%20&%20engineering"));
        cardCategoryTelecommunication.setOnClickListener(new CategoryClickListener(context, "category:telecommunication"));
        cardCategoryTerror.setOnClickListener(new CategoryClickListener(context, "category:terror"));

        return root;
    }

    private class CategoryClickListener implements View.OnClickListener {
        private Context context;
        private String category;

        public CategoryClickListener(Context context, String category) {
            this.context = context;
            this.category = category;
        }

        @Override
        public void onClick(View v) {
            Bundle bundleCategory = new Bundle();
            bundleCategory.putString("category", category);
            Intent intent = new Intent(context, ViewSynopsisActivity.class);
            intent.putExtras(bundleCategory);
            startActivity(intent);
        }
    }
}


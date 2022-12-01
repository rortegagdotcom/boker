package com.rortegag.boker.main.ui.saved_books;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.rortegag.boker.R;

public class SavedBooksFragment extends Fragment {

    private ListView listBooks;
    private ArrayList<Map<String,Object>> savedBooks;
    private String[] titleArr = {"Libro 1", "Libro 2", "Libro 3", "Libro 4", "Libro 5"};
    private String[] autorArr = {"Autor 1", "Autor 2", "Autor 3", "Autor 4", "Autor 5"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_saved_books, container, false);
        listBooks = root.findViewById(R.id.listBooks);
        savedBooks = new ArrayList<Map<String,Object>>();

        for(int i=0;i<titleArr.length;i++) {
            Map<String,Object> listItemMap = new HashMap<String,Object>();
            listItemMap.put("Titulo", titleArr[i]);
            listItemMap.put("Autor", autorArr[i]);
            savedBooks.add(listItemMap);
        }

        SimpleAdapter adapter = new SimpleAdapter(this.getContext(), savedBooks, android.R.layout.simple_list_item_2, new String[]{"Titulo", "Autor"}, new int[]{android.R.id.text1, android.R.id.text2});

        listBooks.setAdapter(adapter);

        return root;
    }
}
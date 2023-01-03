package com.rortegag.boker.main.navigation.saved_books;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rortegag.boker.R;
import com.rortegag.boker.models.book.Book;

public class SavedBooksFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private ListView listListBook;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_saved_books, container, false);
        listListBook = root.findViewById(R.id.listListBook);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("listbooks");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Book> books = new ArrayList<>();
                for (DataSnapshot bookSnapshot : snapshot.child(firebaseAuth.getUid()).getChildren()) {
                    String title = bookSnapshot.child("title").getValue(String.class);
                    String isbn = bookSnapshot.child("isbn").getValue(String.class);
                    String genre = bookSnapshot.child("genre").getValue(String.class);
                    String author = bookSnapshot.child("author").getValue(String.class);
                    String synopsis = bookSnapshot.child("synopsis").getValue(String.class);
                    books.add(new Book(title, isbn, genre, author, synopsis));
                }
                
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return root;
    }
}
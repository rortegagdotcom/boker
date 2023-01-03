package com.rortegag.boker.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rortegag.boker.R;
import com.rortegag.boker.models.book.Book;

public class ViewBookActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private Bundle bundle;

    private TextView txtTitleBook, txtISBNBook, txtGenreBook, txtAuthorBook;

    private ImageView imgFavoriteBook, imgBackBook;

    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance("https://boker-369819-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReference = firebaseDatabase.getReference();

        txtTitleBook = findViewById(R.id.txtTitleBook);
        txtISBNBook = findViewById(R.id.txtISBNBook);
        txtGenreBook = findViewById(R.id.txtGenreBook);
        txtAuthorBook = findViewById(R.id.txtAuthorBook);
        imgFavoriteBook = findViewById(R.id.imgFavoriteBook);
        imgBackBook = findViewById(R.id.imgBackBook);

        bundle = getIntent().getExtras();
        book = (Book) bundle.getSerializable("book");

        txtTitleBook.setText(book.getTitle());
        txtISBNBook.setText(book.getIsbn());
        txtGenreBook.setText(book.getGenre());
        txtAuthorBook.setText(book.getAuthor());

        imgBackBook.setOnClickListener(v -> {
            finish();
        });

        imgFavoriteBook.setOnClickListener(v -> {
            String uid = firebaseAuth.getUid();
            databaseReference.child("listbooks").child(uid).child(book.getIsbn()).setValue(book);
            Toast.makeText(this, "Book added to favorites.", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
package com.rortegag.boker.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.books.v1.Books;
import com.google.api.services.books.v1.BooksRequestInitializer;
import com.google.api.services.books.v1.model.Volume;
import com.rortegag.boker.R;
import com.rortegag.boker.main.navigation.home.HomeFragment;
import com.rortegag.boker.models.book.Book;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class ViewSynopsisActivity extends AppCompatActivity {

    private TextView txtViewSynopsis;
    private ImageView imgBackSynopsis, imgViewBook, imgNextSynopsis;

    private List<Book> bookList;

    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_synopsis);
        txtViewSynopsis = findViewById(R.id.txtViewSynopsis);
        imgBackSynopsis = findViewById(R.id.imgBackSynopsis);
        imgViewBook = findViewById(R.id.imgViewBook);
        imgNextSynopsis = findViewById(R.id.imgNextSynopsis);
        bookList = new ArrayList<>();

        LoadBooksTask loadBooksTask = new LoadBooksTask(this, getIntent().getExtras());

        loadBooksTask.execute();

        imgBackSynopsis.setOnClickListener(v -> {
            try {
                txtViewSynopsis.setText(bookList.get(i--).getSynopsis());
            } catch (IndexOutOfBoundsException e) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        imgViewBook.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("book", bookList.get(i));
            Intent intent = new Intent(this, ViewBookActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        });

        imgNextSynopsis.setOnClickListener(v -> {
            try {
                txtViewSynopsis.setText(bookList.get(i++).getSynopsis());
            } catch (IndexOutOfBoundsException e) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(this, "No more books to display.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private class LoadBooksTask extends AsyncTask<Void, Void, List<Book>> {

        private Context context;
        private Bundle bundleType;

        public LoadBooksTask(Context context, Bundle bundleType) {
            this.context = context;
            this.bundleType = bundleType;
        }

        @Override
        protected List<Book> doInBackground(Void... voids) {
            try {
                HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
                JsonFactory jsonFactory = GsonFactory.getDefaultInstance();
                Books googleBooks = new Books.Builder(httpTransport, jsonFactory, null)
                        .setGoogleClientRequestInitializer(new BooksRequestInitializer())
                        .setApplicationName("Boker")
                        .setGoogleClientRequestInitializer(new BooksRequestInitializer("AIzaSyAx6mvx4-J-TFCAHBNYWsdL0oUyKudAov4"))
                        .build();
                List<Volume> googleVolumes = getListVolume(googleBooks);
                String title, isbn = null, genre, author, synopsis, enhancedSynopsis;
                for (Volume volume : googleVolumes) {
                    try {
                        title = volume.getVolumeInfo().getTitle();
                    } catch (NullPointerException e) {
                        title = null;
                    }
                    try {
                        if(volume.getVolumeInfo().getIndustryIdentifiers().get(0).getType().contentEquals("ISBN_13")) {
                            isbn = volume.getVolumeInfo().getIndustryIdentifiers().get(0).getIdentifier();
                        } else if (volume.getVolumeInfo().getIndustryIdentifiers().get(1).getType().contentEquals("ISBN_13")) {
                            isbn = volume.getVolumeInfo().getIndustryIdentifiers().get(1).getIdentifier();
                        }
                    } catch (IndexOutOfBoundsException | NullPointerException e) {
                        isbn = null;
                    }
                    try {
                        genre = volume.getVolumeInfo().getCategories().get(0);
                    } catch (NullPointerException e) {
                        genre = null;
                    }
                    try {
                        author = volume.getVolumeInfo().getAuthors().get(0);
                    } catch (NullPointerException e) {
                        author = null;
                    }
                    try {
                        synopsis = volume.getVolumeInfo().getDescription();
                    } catch (NullPointerException e) {
                        synopsis = null;
                    }
                    try {
                        if (title != null && isbn != null && genre != null && author != null && synopsis != null && !title.isEmpty() && !isbn.isEmpty() && !genre.isEmpty() && !author.isEmpty() && !synopsis.isEmpty()) {
                            try {
                                enhancedSynopsis = synopsis.replace(". ", ".\n\n");
                            } catch (NullPointerException e) {
                                enhancedSynopsis = synopsis;
                            }
                            bookList.add(new Book(title, isbn, genre, author, enhancedSynopsis));
                        }
                    } catch (NullPointerException e) {
                        Toast.makeText(context, "Error when entering when displaying the book data.", Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (GeneralSecurityException | IOException e) {
                Toast.makeText(context, "Error when getting data from Google Books API.", Toast.LENGTH_SHORT).show();
            }

            return bookList;
        }

        @Override
        protected void onPostExecute(List<Book> books) {
            txtViewSynopsis.setText(books.get(i).getSynopsis());
        }

        private List<Volume> getListVolume(Books googleBooks) {
            String type;
            if (bundleType.containsKey("category")) {
                type = bundleType.getString("category");
            } else if (bundleType.containsKey("genre")) {
                type = bundleType.getString("genre");
            } else {
                type = "NA";
            }
            List<Volume> googleVolumes = null;
            try {
                do {
                    googleVolumes = googleBooks.volumes()
                            .list(type + "+language:english")
                            .execute()
                            .getItems();
                } while (googleVolumes == null || googleVolumes.isEmpty());
            } catch (IOException e) { e.printStackTrace(); }
            return googleVolumes;
        }
    }

}
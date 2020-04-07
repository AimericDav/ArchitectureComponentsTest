package com.aimeric.architecturecomponentstest;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class NoteDaoTest {

    // FOR DATA
    private NoteDatabase mDatabase;

    // DATA SET FOR TEST
    private static int NOTE_ID = 1;
    private static Note NOTE_DEMO = new Note(NOTE_ID,"Manger", "Desc", 5);


    @Before
    public void initDatabase() throws Exception {

        Context context = ApplicationProvider.getApplicationContext();
        this.mDatabase = Room.inMemoryDatabaseBuilder(context,
                NoteDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDatabase() throws Exception {
        mDatabase.close();
    }

    @Test
    public void insertAndGetNote() throws InterruptedException {

        // BEFORE : Adding a new note
        System.out.println("STOP");
        this.mDatabase.noteDao.insert(NOTE_DEMO);

        // TEST
        Note note = LiveDataTestUtil.getValue(this.mDatabase.noteDao.getNote(NOTE_ID));
        assertTrue(note.getName().equals(NOTE_DEMO.getName()) && note.getId() == NOTE_ID);

    }

}

package ru.netology

import Comment
import NotFoundComment
import NotFoundNote
import Note
import NoteService
import org.testng.annotations.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class MainKtTest {
    @BeforeTest
    fun clearBeforeTest() {
        NoteService.clear()
    }

    @Test
    fun addNote() {
        NoteService.clear()
        val note = NoteService.addNote(1, "title1", "text1", "11.11.11", 1)
        val note2 = NoteService.addNote(2, "title2", "text2", "22.12.22", 1)
        val referenceRes = Note(id = 1, ownerId = 1, title = "title1", text = "text1", date = "11.11.11", privacy = 1)
        assertEquals(referenceRes, note)
    }

    @Test
    fun editNote() {
     val result = NoteService.editNote(
            1, 11, "3333", "3333", "21.12.21", 1
        )
        assertEquals(true, result)

    }

    @Test
    fun deleteNote() {
        val result = NoteService.deleteNote(2)
        assertEquals(true, result)

    }

    @Test
    fun createComment() {
        val comment = NoteService.createComment(2, 1, "Comment1", false)
        val referenceRes = Comment(id = 1, noteId = 2, ownerId = 1, message = "Comment1", isDeleted = false)
        assertEquals(referenceRes, comment)
    }

    @Test
    fun editComment() {
        NoteService.addNote(1, "title1", "text1", "11.11.11", 1)
        NoteService.createComment(1, 11, "Comment1", false)
        val result = NoteService.editComment(id = 1, noteId = 1, ownerId = 11, message = "Comment2", isDeleted = false)
        assertEquals(true, result)

    }

    @Test
    fun deleteComment() {
        NoteService.addNote(21, "title1", "text1", "11.11.11", 1)
        NoteService.createComment(1, 11, "Comment1", false)
        val result = NoteService.deleteComment(id = 1)
        assertEquals(true, result)

    }

    @Test
    fun getById(){
        NoteService.clear()
        NoteService.addNote(1, "title1", "text1", "11.11.11", 1)
        val result = NoteService.getById(1)
        val reference = Note(id = 1, ownerId = 1, title = "title1", text = "text1", date = "11.11.11", privacy = 1)
        assertEquals( reference, result)
    }


}
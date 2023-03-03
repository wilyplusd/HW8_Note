data class Note(
    val id: Int = 0,
    val ownerId: Int,
    val title: String,
    val text: String,
    val date: String,
    val privacy: Int = 3,
)

data class Comment(
    val id: Int = 0,
    val noteId: Int,
    val ownerId: Int,
    val message: String,
    val isDeleted: Boolean = false
)

public class NotFoundNote : Exception("Not found note!") {}
public class NotFoundComment : Exception("Not found comment!") {}

object NoteService {
    private var notes = mutableListOf<Note>()
    private var comments = mutableListOf<Comment>()
    private var deleteComments = mutableListOf<Comment>()
    private var idNote: Int = 0

    // метод getFriendsNotes не смогла реализовать, у меня нет друзей, так как нет реализации у людей.

    fun addNote(ownerId: Int, title: String, text: String, date: String, privacy: Int): Note {
        var id = if (notes.isEmpty()) {
            1
        } else {
            (notes.last().id + 1).toInt()
        }
        notes.add(Note(id = id, ownerId = ownerId, title = title, text = text, date = date, privacy = privacy))
        return notes.last()
    }

    fun editNote(id: Int, ownerId: Int, title: String, text: String, date: String, privacy: Int): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (note.id == id) {
                notes[index] = note.copy(
                    id = id,
                    text = text,
                    ownerId = ownerId,
                    date = date,
                    title = title,
                    privacy = privacy
                )
                return true
            }
        }
        throw NotFoundNote()
    }

    fun deleteNote(id: Int): Boolean {
        if (notes.isNotEmpty()) {
            for (note in notes) {
                if (note.id == id) {
                    notes.remove(note)
                    if (comments.isNotEmpty()) {
                        for (comment in comments) {
                            if (comment.noteId == id) {
                                deleteComments.add(comment)
                                comments.remove(comment)

                            }
                        }
                    }
                    return true

                }
            }
        }
        throw NotFoundNote()
    }

    fun getOwnerId(ownerId: Int): List<Note> {
        val returnNotes = mutableListOf<Note>()
        if (notes.isNotEmpty()) {
            for ((index, note) in notes.withIndex()) {
                if (note.ownerId == ownerId) {
                    returnNotes.add(note)
                }
            }
            if (returnNotes.isNotEmpty()) {
                return returnNotes
            }

        }
        throw NotFoundNote()
    }

    fun getAllComments(): List<Comment> {
        val returnComments = mutableListOf<Comment>()
        for (comment in comments) {
            if (!comment.isDeleted) {
                returnComments.add(comment)
            }

        }
        if (returnComments.isNotEmpty()) {
            return returnComments
        }
        throw NotFoundComment()
    }

    fun getById(id: Int): Note {
        if (notes.isNotEmpty()) {
            for (note in notes) {
                if (note.id == id) {
                    return note
                }
            }
        }
        throw NotFoundNote()
    }

    fun getComments(noteId: Int): List<Comment> {
        val returnComments = mutableListOf<Comment>()
        if (comments.isNotEmpty()) {
            for (comment in comments) {
                if (comment.noteId == noteId && !comment.isDeleted) {
                    returnComments.add(comment)
                }

            }
            if (returnComments.isNotEmpty()) {
                return returnComments
            }
        }
        throw NotFoundComment()
    }

    fun createComment(noteId: Int, ownerId: Int, message: String, isDeleted: Boolean): Comment {
        var id: Int = 0
        if (notes.isNotEmpty()) {
            for (note in notes) {
                if (note.id == noteId) {
                    id = if (comments.isEmpty()) {
                        1
                    } else {
                        (comments.last().id + 1).toInt()
                    }
                    comments.add(Comment(id = id, noteId = noteId, ownerId = ownerId, message = message, isDeleted = isDeleted))
                    return comments.last()
                }
            }
        }
        throw NotFoundNote()
    }

    fun editComment(id: Int, noteId: Int, ownerId: Int, message: String, isDeleted:Boolean): Boolean {
        if (notes.isNotEmpty()) {
            for (note in notes) {
                if (note.id == noteId) {
                    if (comments.isNotEmpty()) {
                        for ((index, comment) in comments.withIndex()) {
                            if (comment.id == id) {
                                comments[index] = comment.copy(
                                    id = id,
                                    noteId = noteId,
                                    ownerId = ownerId,
                                    message = message,
                                    isDeleted = isDeleted
                                )
                                return true
                            }
                        }
                        throw NotFoundComment()
                    }
                }

            }

        }
        throw NotFoundNote()
    }

    fun deleteComment(id: Int): Boolean {
        if (comments.isNotEmpty()) {
            for ((index, comment) in comments.withIndex()) {
                if (comment.id == id) {
                    comments[index] = comment.copy(
                        id = comment.id,
                        noteId = comment.noteId,
                        ownerId = comment.ownerId,
                        message = comment.message,
                        isDeleted = true
                    )
                    return true
                }
            }
        }
        throw NotFoundComment()
    }
    fun clear() {
        notes.clear()
        comments.clear()
    }

}


fun main() {
//
//    NoteService.addNote(11, "title1", "text1", "11.11.11", 1)
//    NoteService.addNote(11, "title2", "text2", "21.12.21", 1)
//    NoteService.addNote(11, "title3", "text3", "31.03.23", 1)
//    NoteService.deleteNote(2)
//    //NoteService.editNote(2, 11, "title2", "text2", "21.12.21", 1)
//    NoteService.editNote(3, 11, "title", "text", "21.12.21", 1)
//    print("\n" + NoteService.getOwnerId(11))
//    NoteService.createComment(1, 11, "Comment1", false)
//    NoteService.createComment(3, 31, "Comment2", false)
//    NoteService.editComment(1, 3, 31, "text", false)
//    NoteService.createComment(1, 4, "message1", false)
//    NoteService.createComment(3, 4, "message2", false)
//    NoteService.deleteComment(4)
//    print ("\n" + NoteService.getAllComments())
//    print ("\n" + NoteService.getComments(3))
//    print ("\n" + NoteService.getById(3))
}
document.addEventListener('DOMContentLoaded', () => {
    const notesContainer = document.getElementById('notes-container');
    const addNoteBtn = document.getElementById('add-note-btn');

    // --- API Interaction Functions ---

    // 1. GET: Load all notes from the Spring API
    async function loadNotesFromApi() {
        try {
            const response = await fetch('/notes_info');
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const notes = await response.json();
            console.log("Data received from API:", notes); // For debugging
            notes.forEach(createNoteElement);
        } catch (error) {
            console.error("Could not load notes:", error);
        }
    }

    // 2. POST: Add a new note via the Spring API
    async function addNote() {
        const newNoteData = {
            note_content: '',
            note_title: 'Untitled Note' // Ensure this matches your Java field name
        };

        try {
            const response = await fetch('/notes_info', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(newNoteData)
            });

            if (response.ok) {
                const savedNote = await response.json();
                createNoteElement(savedNote);
            } else {
                alert('Failed to add note to the server.');
            }
        } catch (error) {
            console.error("Error adding note:", error);
        }
    }

    // 3. PUT: Update an existing note via the Spring API
    async function updateNote(noteId, newContent, newTitle) {
        const updatedNoteData = {
            note_id: noteId,
            note_content: newContent,
            note_title: newTitle // Ensure this matches your Java field name
        };

        try {
            const response = await fetch('/notes_info', {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(updatedNoteData)
            });

            if (!response.ok) {
                alert('Failed to update note on the server.');
            }
        } catch (error) {
            console.error("Error updating note:", error);
        }
    }

    // 4. DELETE: Delete a note via the Spring API
    async function deleteNote(id, element) {
        try {
            const response = await fetch(`/notes_info/${id}`, {
                method: 'DELETE'
            });
            if (response.ok) {
                element.remove();
            } else {
                alert('Failed to delete note on the server.');
            }
        } catch (error) {
            console.error("Error deleting note:", error);
        }
    }

    // --- UI/DOM Functions ---

    function createNoteElement(note) {
        const noteDiv = document.createElement('div');
        noteDiv.classList.add('note');
        noteDiv.style.setProperty('--rotation', Math.floor(Math.random() * 6) - 3);

        // Template string using note.note_title and formatted dates
        noteDiv.innerHTML = `
            <div class="note-header">
                <input type="text" class="note-title" value="${note.note_title || 'Untitled'}">
                <span class="note-date">Created: ${new Date(note.created_on).toLocaleString()}</span>
                <span class="note-date">Modified: ${new Date(note.modified_on).toLocaleString()}</span>
            </div>
            <textarea class="note-content">${note.note_content || ''}</textarea>
            <button class="delete-btn" data-id="${note.note_id}">Delete</button>
        `;

        // Listener for the title input
        noteDiv.querySelector('.note-title').addEventListener('input', (e) => {
            // Pass the ID, existing content, and new title
            updateNote(note.note_id, note.note_content, e.target.value);
        });

        // Listener for the content textarea
        noteDiv.querySelector('.note-content').addEventListener('input', (e) => {
            // Pass the ID, new content, and existing title
            updateNote(note.note_id, e.target.value, note.note_title);
        });

        // Listener for delete button
        noteDiv.querySelector('.delete-btn').addEventListener('click', (e) => {
            const noteId = e.target.getAttribute('data-id');
            deleteNote(noteId, noteDiv);
        });

        notesContainer.appendChild(noteDiv);
    }

    // --- Initialization ---
    addNoteBtn.addEventListener('click', addNote);

    // Load initial data from Spring Boot when the page loads
    loadNotesFromApi();
});

import {Component, signal} from '@angular/core';
import {Note} from '../../models/note.model';
import {NoteCardComponent} from './note-card/note-card.component';
import {NoteService} from '../../services/note.service';
import {PrimaryButtonComponent} from '../../components/primary-button/primary-button.component';
import {Router} from '@angular/router';
import {TagService} from '../../services/tag.service';

@Component({
  selector: 'app-notes-list',
  imports: [
    NoteCardComponent,
    PrimaryButtonComponent,
  ],
  templateUrl: './notes-list.component.html',
  styleUrl: './notes-list.component.css'
})
export class NotesListComponent {
  notes = signal<Note[]>([]);

  constructor(private noteService: NoteService, private router: Router, private tagService: TagService) {
    this.router = router;
    this.noteService = noteService;
    this.notes.set(noteService.getNotes());
  }

  addNewNote() {
    let newNote = this.noteService.createNote();
    this.notes.update((notes) => [...notes, newNote]);
    this.router.navigate(['/note'], {state: {'note': newNote}});
  }
}
